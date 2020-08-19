package com.hym.eshoplib.fragment.goods;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhouwei.library.CustomPopWindow;
import com.hjq.base.BaseDialog;
import com.hjq.base.BaseDialogFragment;
import com.hjq.dialog.MessageDialog;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.alipay.AliPay;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.bean.order.AliPayBean;
import com.hym.eshoplib.bean.order.PayTypeBean;
import com.hym.eshoplib.bean.order.VipOrderBean;
import com.hym.eshoplib.bean.order.WxpayBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.bean.shop.AttachResultBean;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.event.WxPayResultEvent;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.eshoplib.listener.AppBarStateChangeListener;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.pay.Constants;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import constant.StringConstants;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/22.
 * <p>
 * Description 工作室详情
 * <p>
 * otherTips
 */

public class GoodsDetaiFragment extends BaseKitFragment implements AliPay.PayResultListener {

    private String TAG = "GoodsDetaiFragment";

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.tv_years)
    TextView tvYears;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn1)
    SuperButton btn1;
    @BindView(R.id.btn_2)
    SuperButton btn2;
    @BindView(R.id.tv_current_goods)
    TextView tvCurrentGoods;
    @BindView(R.id.ll_current_goods)
    LinearLayout llCurrentGoods;
    @BindView(R.id.fl_fragment_container)
    FrameLayout flFragmentContainer;
    @BindView(R.id.tv_attach_business)
    TextView tvAttachBusiness;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_add_shoppingcart)
    TextView tvAddShoppingcart;
    @BindView(R.id.tv_buy_now)
    TextView tvBuyNow;
    Unbinder unbinder;
    // int current_type = 1;//当前工作室类型 按首页功能按键排序
    BaseListAdapter<ServiceDetailBean.DataBean.CateListBean> adapter;
    int select_position = 0;//当前选中的类别
    int count = 1;//购买数量至少一个
    String name = "";//购买种类名称
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_call_phone)
    TextView tvCallPhone;
    private SnappingStepper stepper;
    String shop_id;
    ServiceDetailBean detailBean;
    private CustomPopWindow mCustomPopWindow;
    ImageView iv_right = null;
    // private UMShareListener mShareListener;
    private ShareAction mShareAction;

    public static GoodsDetaiFragment newInstance(Bundle args) {
        GoodsDetaiFragment fragment = new GoodsDetaiFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentViewResId() {
        return R.layout.fragment_goods_detail;
    }


    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        int type = getArguments().getInt("type", 1);

        if (type == 46) {
            tvCallPhone.setVisibility(View.GONE);
        }

        // content_id
        shop_id = getArguments().getString("id", "");
        showBackButton();
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    setTitle("");


                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    setTitle(tvName.getText().toString());


                } else {
                    //中间状态
                    if (TextUtils.isEmpty(getTv_title().getText())) {
                        setTitle(tvName.getText().toString());
                    }

                }
            }
        });
        //选择分类
        llCurrentGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                showSelectTypeDialog(0);

            }
        });

        iv_right = setRight_iv(R.drawable.ic_more_mipai, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(_mActivity).inflate(R.layout.pop_menu, null);
                //处理popWindow 显示内容
                handleLogic(contentView);
                //创建并显示popWindow
                mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(_mActivity)
                        .setView(contentView)
                        .create();
                //.showAsDropDown(iv_right,100,0);
                PopupWindow popupWindow = mCustomPopWindow.getPopupWindow();
                popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                popupWindow.showAsDropDown(v, -115, 0);
                //mCustomPopWindow.showAsDropDown(iv_right,100,0);

            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        ShopApi.GetContentDetail(shop_id, new ResponseImpl<ServiceDetailBean>() {
            @Override
            public void onSuccess(ServiceDetailBean data) {
                detailBean = data;
                //加载工作室相关信息的fragment
                Bundle bunddle = new Bundle();
                // bunddle.putInt("type", current_type); //用来判断产品类型a版时使用 已废弃
                bunddle.putSerializable("data", detailBean);
                GoodsDetailPagerFragment pagerFragment = GoodsDetailPagerFragment.newInstance(bunddle);
                loadRootFragment(R.id.fl_fragment_container, pagerFragment);
                bindeDetail();
            }

            @Override
            public void dataRes(int code, String data) {
                super.dataRes(code, data);

            }
        }, ServiceDetailBean.class);
    }

    //设置店铺详情
    private void bindeDetail() {
        if (detailBean == null) {
            return;
        }
        //默认服务名称,默认选中第一个
        name = detailBean.getData().getCate_list().get(0).getCategory_name();
        ImageUtil.getInstance().loadImage(GoodsDetaiFragment.this, detailBean.getData().getLogo(), ivAvatar);//logo
        tvName.setText(detailBean.getData().getStore_name() + "");//店铺名字
        ratingbar.setRating(Float.parseFloat(detailBean.getData().getRank_average()));//星级
        tvLocation.setText("所在城市：" + (TextUtils.isEmpty(detailBean.getData().getRegion_name()) ? "暂无" : detailBean.getData().getRegion_name()));//所在城市
        if (detailBean.getData().getCategory_id().equals("46")) {
            tvTimes.setText("拍摄周期：" + detailBean.getData().getProduction_cycle() + "天");
        } else if (detailBean.getData().getCategory_id().equals("40")) {
            tvTimes.setText("工作周期：" + detailBean.getData().getProduction_cycle() + "天");
        } else {
            tvTimes.setText("制作周期：" + detailBean.getData().getProduction_cycle() + "天");
        }
        tvYears.setText("从业年限：" + (TextUtils.isEmpty(detailBean.getData().getEmployment_time()) ? "暂无" : detailBean.getData().getEmployment_time() + "年"));
        tvPrice.setText("￥：" + detailBean.getData().getCate_list().get(0).getPrice());
        btn1.setVisibility(View.GONE);   //发票情况
        btn2.setVisibility(View.GONE); //退款情况
        btn2.setText("定制产品不接受退款");
        btn1.setText("不提供发票");
        String refound_type = detailBean.getData().getRefund_type();
        String invoice_type = detailBean.getData().getInvoice();
        switch (refound_type) {
            case "1":
                btn2.setText("接受不满意全额退款");
                break;
            case "2":
                btn2.setText("接受不满意部分退款");
                break;
            case "3":
                btn2.setText("定制产品不接受退款");
                break;

        }
        if (invoice_type.equals("1")) {
            btn1.setText("可开发票");
        } else {
            btn1.setText("不提供发票");
        }
        if (detailBean.getData().getIs_favorite() == 1) {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
        } else {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
        }

        tvCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //detailBean.getData().get
            }
        });

        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailBean == null) {
                    return;
                }
                //收藏
                ShopApi.AddFavorite(detailBean.getData().getStore_id(), "store", new ResponseImpl<AddFavouriteBean>() {
                    @Override
                    public void onSuccess(AddFavouriteBean data) {
                        if (data.getData().getStatus() == 1) {
                            tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
                        } else {
                            tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
                        }

                    }
                }, AddFavouriteBean.class);

            }
        });
        if (detailBean.getData().getAuth_store().equals("1")) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_person_rt);
        } else if (detailBean.getData().getAuth_store().equals("2")) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_business_rt);
        } else {
            ivVip.setVisibility(View.GONE);
        }
        switch (detailBean.getData().getCategory_id()) {
            case "1":
                //文案策划
                // tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认收货");
                //tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
                tvTips.setVisibility(View.GONE);
                break;
            case "2":
                //导演
                break;
            case "3":
                //摄像师

                break;
            case "4":
                //剪辑师

                break;
            case "5":
                //影视团队

                break;
            case "6":
                //三维动画

                break;
            case "7":
                //平面设计
                break;
            case "8":
                //图片摄影

                break;
            case "46":
                tvTips.setVisibility(View.GONE);

                break;
            case "40":
                tvTips.setVisibility(View.GONE);

                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //显示选择框
    private void showSelectTypeDialog(final int i) {
        if (detailBean == null) {
            ToastUtil.toast("数据异常");
            return;
        }
        //选择数量
        //设置header
        final View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_type_dialog, null, false);
        ImageView iv_logo = header.findViewById(R.id.iv_logo);
        TextView tv_shop_name = header.findViewById(R.id.tv_shop_name);
        final TextView tv_price = header.findViewById(R.id.tv_price);
        ImageUtil.getInstance().loadImage(GoodsDetaiFragment.this, detailBean.getData().getLogo(), iv_logo);
        tv_shop_name.setText(detailBean.getData().getStore_name() + "");
        tv_price.setText("￥：" + detailBean.getData().getCate_list().get(select_position).getPrice());
        //设置footer
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_type_dialog, null, false);
        TextView tv_life_circle = footer.findViewById(R.id.tv_life_circle);
        TextView tv_sub_title = footer.findViewById(R.id.tv_sub_title);
        switch (detailBean.getData().getCategory_id()) {
            case "1":
                //文案策划
                // tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认收货");
                //tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
                tvTips.setVisibility(View.GONE);
                break;
            case "2":
                //导演
                tv_sub_title.setText("宣传片时长以5分钟为单位,如超出请增加购买数量");
                break;
            case "3":
                //摄像师
                tv_sub_title.setText("购买数量单位为天");
                break;
            case "4":
                //剪辑师
                tv_sub_title.setText("宣传片时长以5分钟为单位,如超出请增加购买数量");
                break;
            case "5":
                //影视团队
                tv_sub_title.setText("宣传片时长以5分钟为单位,如超出请增加购买数量");
                tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
                break;
            case "6":
                //三维动画
                tv_sub_title.setText("以秒钟为单位,如超出请增加购买数量");
                break;
            case "7":
                //平面设计
                break;
            case "8":
                //图片摄影
                tv_sub_title.setText("购买数量单位为天");
                break;
            case "46":
                tvTips.setVisibility(View.GONE);
                tv_sub_title.setText("购买数量单位为天");
                break;
            case "40":
                tvTips.setVisibility(View.GONE);
                tv_sub_title.setText("购买数量单位为天");
                break;
        }
//        if(current_type==5){
//            tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
//        }
        stepper = footer.findViewById(R.id.stepper);
        stepper.setValue(count);
        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                count = value;
                tvCurrentGoods.setText("\"" + name + "\"" + "数量x" + count);
            }
        });
        adapter = new BaseListAdapter<ServiceDetailBean.DataBean.CateListBean>(R.layout.item_spec_btn, detailBean.getData().getCate_list()) {
            @Override
            public void handleView(BaseViewHolder helper, final ServiceDetailBean.DataBean.CateListBean item, final int position) {
                TextView tv_title = helper.getView(R.id.tv_title);
                TextView tv_type = helper.getView(R.id.tv_type);
                tv_type.setText(item.getCategory_name() + "");
                helper.setText(R.id.tv_memo, item.getMemo() + "");

                if (position == 0) {
                    tv_title.setVisibility(View.VISIBLE);
                } else {
                    tv_title.setVisibility(View.GONE);
                }
                if (item.getIs_spec().equals("0")) {
                    //已下架
                    tv_type.setBackgroundResource(R.drawable.shape_graye3e3e3_solid_conner5);
                    tv_type.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                } else {
                    if (select_position == position) {
                        tv_type.setBackgroundResource(R.drawable.selector_btn);
                        tv_type.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                    } else {
                        tv_type.setBackgroundResource(R.drawable.shape_f3ebd8_conner5);
                        tv_type.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                    }
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (item.getIs_spec().equals("0")) {
                                return;
                            }
                            String price = "￥：" + item.getPrice();
                            name = item.getCategory_name() + "";
                            tv_price.setText(price);
                            tvPrice.setText(price);
                            //Logger.d("price="+item.getPrice()+",name="+name);
                            tvCurrentGoods.setText("\"" + name + "\"" + "数量x" + count);
                            select_position = position;
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);
        if (i == 0) {
            //选择规格
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter, "加入购物车", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (detailBean == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (detailBean.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    //加入购物车
                    ShoppingCarApi.addToShoppingCar(_mActivity, adapter.getData().get(select_position).getContent_id(), count + "", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("加入购物车成功，请到购物车中查看");
                        }
                    }, Object.class);
                }
            }, "立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (detailBean.getData().getAuth_user().equals("0")) {
                        //弹出认证
                        Dialog dilog = MipaiDialogUtil.getAuthDialog(_mActivity, "认证信息", "完善资料才可以预约合作哦", new MipaiDialogUtil.OnBtnSlectListener() {
                            @Override
                            public void on1(Dialog dialog) {
                                dialog.dismiss();
                                //个人认证
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Personal);
                                bundle.putString("type", detailBean.getData().getAuth_user());
                                ActionActivity.start(_mActivity, bundle);

                            }

                            @Override
                            public void on2(Dialog dialog) {
                                dialog.dismiss();
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Business);
                                bundle.putString("type", detailBean.getData().getAuth_store());
                                ActionActivity.start(_mActivity, bundle);

                            }
                        });
                        dilog.show();
                        return;
                    }
                    MipaiDialogUtil.dismiss();

                    if (detailBean == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (detailBean.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    //立即预约
                    ShopApi.attachNow(adapter.getData().get(select_position).getContent_id(), count + "", new ResponseImpl<AttachResultBean>() {
                        @Override
                        public void onSuccess(AttachResultBean data) {
                            ToastUtil.toast("已提交预约");
                            Bundle bundle = new Bundle();
                            bundle.putString("id", data.getData().getLog_id());
                            start(MipaiOrderDetailFragment.newInstance(bundle));
                        }
                    }, AttachResultBean.class);
                }
            }, true);


        } else if (i == 1) {
            //加入购物车
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter, "", null, "加入购物车", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (detailBean == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (detailBean.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    ShoppingCarApi.addToShoppingCar(_mActivity, adapter.getData().get(select_position).getContent_id(), count + "", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("加入购物车成功，请到购物车中查看");
                        }
                    }, Object.class);


                }
            }, true);
        } else {
            //立即购买
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter, "", null, "立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (detailBean == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (detailBean.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    ShopApi.attachNow(adapter.getData().get(select_position).getContent_id(), count + "", new ResponseImpl<AttachResultBean>() {
                        @Override
                        public void onSuccess(AttachResultBean data) {
                            ToastUtil.toast("已提交预约");
                            Bundle bundle = new Bundle();
                            bundle.putString("id", data.getData().getLog_id());
                            start(MipaiOrderDetailFragment.newInstance(bundle));
                        }
                    }, AttachResultBean.class);

                }
            }, true);
        }
    }

    BaseDialog pay_dialog;
    BaseListAdapter<PayTypeBean> pay_adapter;
    int pay_position = -1;

    @OnClick({R.id.tv_attach_business, R.id.tv_collect, R.id.tv_add_shoppingcart, R.id.tv_buy_now, R.id.iv_avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_attach_business:
                //ToastUtil.toast("联系卖家功能需要在B版中接入融云sdk才可以查看");
                if (detailBean == null) {
                    ToastUtil.toast("数据异常");
                    return;
                }
                if (detailBean.getData().getIs_mine().equals("1")) {
                    ToastUtil.toast("不能与自己的工作室聊天");
                    return;
                }
                if (!UserUtil.getIsLogin(_mActivity)) {
                    ToastUtil.toast("请先登录");
                    Bundle bundle = new Bundle();
                    bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                    bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                    LoginMainActivity.start(_mActivity, bundle);
                } else {
                    //如果没连接则先连接
                    MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
                        @Override
                        public void onSuccess(final MedetailBean data) {
                            //是会员，正常连接聊天
                            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                reconnect(UserUtil.getRongYunToken(_mActivity));
                            } else {
                                RongIM.getInstance().startPrivateChat(_mActivity, detailBean.getData().getUserid(), detailBean.getData().getStore_name());
                            }
                            // data.getData().setLv_is_true("0");
                            /*if (!data.getData().getLv_is_true().equals("1")) {
                                //不是会员 弹出购买会员或者分享
                                getVipStatus(data);
                            } else {
                                //是会员，正常连接聊天
                                if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                    reconnect(UserUtil.getRongYunToken(_mActivity));
                                } else {
                                    RongIM.getInstance().startPrivateChat(_mActivity, detailBean.getData().getUserid(), detailBean.getData().getStore_name());
                                }
                            }*/
                        }
                    }, MedetailBean.class);
                }
                break;
//            case R.id.tv_collect:
//                tvCollect.setSelected(!tvCollect.isSelected());
//                if (tvCollect.isSelected()) {
//                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
//                } else {
//                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
//                }
//                break;
            case R.id.tv_add_shoppingcart:
                showSelectTypeDialog(1);
                break;
            case R.id.tv_buy_now:
                if (detailBean == null) {
                    return;
                }
                if (detailBean.getData().getAuth_user().equals("0")) {
                    //弹出认证
                    Dialog dilog = MipaiDialogUtil.getAuthDialog(_mActivity, "认证信息", "完善资料才可以预约合作哦", new MipaiDialogUtil.OnBtnSlectListener() {
                        @Override
                        public void on1(Dialog dialog) {
                            dialog.dismiss();
                            //个人认证
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Personal);
                            bundle.putString("type", detailBean.getData().getAuth_user());
                            ActionActivity.start(_mActivity, bundle);

                        }

                        @Override
                        public void on2(Dialog dialog) {
                            dialog.dismiss();
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Business);
                            bundle.putString("type", detailBean.getData().getAuth_store());
                            ActionActivity.start(_mActivity, bundle);

                        }
                    });
                    dilog.show();
                    return;
                }
                showSelectTypeDialog(2);
                break;
            case R.id.iv_avatar:
                if (detailBean == null || detailBean.getData() == null || TextUtils.isEmpty(detailBean.getData().getLogo())) {
                    return;
                }
                int width = ScreenUtil.getScreenWidth(_mActivity);
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                List<String> url = new ArrayList<>();
                url.add(detailBean.getData().getLogo());
                ImagePagerActivity.startImagePagerActivity(_mActivity, url, 0, imageSize);
                break;
        }
    }

    BaseDialog message_dialog;

    private void getVipStatus(final MedetailBean data) {
        message_dialog = new MessageDialog.Builder(_mActivity).
                setTitle("温馨提示").
                setMessage("开通会员与工作室无限畅聊\n\n" +
                        "方式一：19.9/月开通会员" + "\n\n" +
                        "方式二：分享至朋友圈免费开通会员一个月").
                setCancel("分享至朋友圈").setConfirm("立即付款").setListener(new MessageDialog.OnListener() {
            @Override
            public void confirm(Dialog dialog) {
                //付款的方式获得 vip 资格
                pay();
            }

            @Override
            public void cancel(Dialog dialog) {
                share(data);
            }
        }).setDetail("了解详情", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message_dialog != null && message_dialog.isShowing()) {
                    message_dialog.dismiss();

                }
                start(VipRuleFragment.newInstance(new Bundle()));
            }
        }).show();


    }

    private void share(MedetailBean data) {
        //分享到朋友圈
        ShareAction mShareAction = new ShareAction(_mActivity).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);

        UMWeb web = new UMWeb(data.getData().getShare_video_url());
        web.setTitle("我在“觅拍”拍视频 时尚酷炫新玩法");
        web.setThumb(new UMImage(_mActivity, R.mipmap.ic_launcher));
        web.setDescription("我在“觅拍”拍视频 时尚酷炫新玩法");
        mShareAction.withMedia(web);
        mShareAction.setCallback(new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {

                OrderApi.shareVipOrder(_mActivity, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("恭喜您!开通会员成功");
                    }
                }, Object.class);


            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtil.toast("分享失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                // Logger.d("取消分享");
                ToastUtil.toast("分享取消");
            }
        });
        mShareAction.open(config);

    }

    //付款方式获得订单
    private void pay() {
        //付款
        OrderApi.createVipOrder(_mActivity, new ResponseImpl<VipOrderBean>() {
            @Override
            public void onSuccess(final VipOrderBean data) {
                final ArrayList<PayTypeBean> payType = new ArrayList<PayTypeBean>();
                payType.add(new PayTypeBean(2));
                payType.add(new PayTypeBean(3));
                if (pay_dialog == null) {
                    View dialogView = LayoutInflater.from(_mActivity).inflate(R.layout.dialog_menu, null, false);
                    RecyclerView rvlist = dialogView.findViewById(R.id.rv_dialog_menu_list);
                    rvlist.setLayoutManager(new LinearLayoutManager(_mActivity));
                    pay_adapter = new BaseListAdapter<PayTypeBean>(R.layout.item_pay_type, null) {
                        @Override
                        public void handleView(BaseViewHolder helper, PayTypeBean item, final int position) {
                            ImageView iv_icon = helper.getView(R.id.iv_icon);
                            TextView tv_name = helper.getView(R.id.tv_name);
                            ImageView iv_select = helper.getView(R.id.iv_select);
                            switch (item.type) {
                                case 2:
                                    //微信
                                    iv_icon.setImageResource(R.drawable.ic_wechat_pay);
                                    tv_name.setText("微信支付");
                                    break;
                                case 3:
                                    //支付宝
                                    tv_name.setText("支付宝支付");
                                    iv_icon.setImageResource(R.drawable.ic_alipay);
                                    break;
                            }
                            if (pay_position == position) {
                                iv_select.setImageResource(R.drawable.ic_cb_checked);
                            } else {
                                iv_select.setImageResource(R.drawable.ic_cb_unchecked);
                            }


                        }
                    };
                    pay_adapter.setNewData(payType);
                    pay_adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter a, View view, int position) {
                            pay_position = position;
                            pay_adapter.notifyDataSetChanged();
                        }
                    });
                    rvlist.setAdapter(pay_adapter);
                    pay_dialog =
                            new BaseDialogFragment.Builder(_mActivity)
                                    .setContentView(dialogView)
                                    .setGravity(Gravity.BOTTOM)
                                    .setAnimStyle(BaseDialog.AnimStyle.BOTTOM).setCancelable(true)
                                    .setOnClickListener(R.id.tv_dialog_menu_cancel, new BaseDialog.OnClickListener() {
                                        @Override
                                        public void onClick(Dialog dialog, View view) {
                                            if (pay_position < 0) {
                                                ToastUtil.toast("请选择支付方式");
                                                return;
                                            }
                                            pay_dialog.dismiss();
                                            switch (pay_adapter.getData().get(pay_position).type) {

                                                case 2:
                                                    //微信支付
                                                    OrderApi.WxPay(_mActivity, data.getData().getOrdernumber(), "pay", new ResponseImpl<WxpayBean>() {
                                                        @Override
                                                        public void onSuccess(WxpayBean data) {
                                                            //调启微信支付
                                                            //微信支付
                                                            IWXAPI api = WXAPIFactory.createWXAPI(_mActivity, Constants.APP_ID);
                                                            api.registerApp(Constants.APP_ID);
                                                            PayReq req = new PayReq();
                                                            req.appId = Constants.APP_ID;
                                                            req.nonceStr = data.getData().getNonce_str();
                                                            req.packageValue = "Sign=WXPay";
                                                            req.partnerId = Constants.partnerId;
                                                            req.prepayId = data.getData().getPrepay_id();
                                                            req.timeStamp = data.getData().getTimestamp() + "";
                                                            req.sign = data.getData().getSign();
                                                            api.sendReq(req);
                                                        }
                                                    }, WxpayBean.class);
                                                    break;
                                                case 3:
                                                    //支付宝支付
                                                    OrderApi.aliPayMipai("pay", data.getData().getOrdernumber(), "", "", new ResponseImpl<AliPayBean>() {
                                                        @Override
                                                        public void onSuccess(AliPayBean data) {
                                                            AliPay aliPay;
                                                            aliPay = new AliPay(_mActivity, GoodsDetaiFragment.this);
                                                            aliPay.pay(data.getData().getStr());
                                                        }
                                                    }, AliPayBean.class);

                                                    break;

                                            }


                                        }
                                    }).setWidth(ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 20)).create();
                }
                if (pay_dialog != null && !pay_dialog.isShowing()) {

                    if (pay_adapter != null) {
                        pay_adapter.notifyDataSetChanged();
                    }
                    pay_dialog.show();


                }
            }
        }, VipOrderBean.class);
    }

    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.d("---onTokenIncorrect--");
            }

            @Override
            public void onSuccess(String s) {
                Logger.d("---onSuccess--" + s);
                //连接成功后进入 聊天界面
                RongIM.getInstance().startPrivateChat(_mActivity, detailBean.getData().getUserid(), detailBean.getData().getStore_name());
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Logger.d("---onError--" + e);
            }
        });

    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                Intent intent = new Intent(_mActivity, MainActivity.class);
                switch (v.getId()) {
                    case R.id.menu1:
                        //首页
                        intent.putExtra("position", 0);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu2:
                        //消息
                        intent.putExtra("position", 1);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu3:
                        //我的
                        intent.putExtra("position", 3);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu4:
                        //分享
                        //mShareListener = new CustomShareListener(_mActivity);
                        mShareAction = new ShareAction(_mActivity).setDisplayList(
                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA);
                        ShareBoardConfig config = new ShareBoardConfig();
                        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                        try {
                            if (detailBean == null || detailBean.getData() == null || TextUtils.isEmpty(detailBean.getData().getShare_url())) {
                                ToastUtil.toast("数据异常");
                                return;
                            }
                        } catch (Exception e) {


                        }
                        UMWeb web = new UMWeb(detailBean.getData().getShare_url());
                        web.setTitle(detailBean.getData().getStore_name() + " | " + StringConstants.Slogan);
                        web.setThumb(new UMImage(_mActivity, detailBean.getData().getLogo()));
                        if (!TextUtils.isEmpty(detailBean.getData().getRemark())) {
                            web.setDescription(detailBean.getData().getRemark());
                        } else {
                            web.setDescription(" ");
                        }
                        mShareAction.withMedia(web);
                        mShareAction.setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                //Logger.d("分享成功");


                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                //Logger.d("分享失败");
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {
                                // Logger.d("取消分享");
                            }
                        });
                        mShareAction.open(config);
                        break;

                }

            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);

    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wxPayResutl(WxPayResultEvent event) {
        if (event.getCode() == 0) {
            ToastUtil.toast("恭喜您!开通会员成功");
        } else {
            ToastUtil.toast("支付失败");
        }


    }

    @Override
    public void paySuccess() {
        //支付宝支付成功
        ToastUtil.toast("恭喜您!开通会员成功");
    }

    @Override
    public void payFail() {
        //支付宝支付失败
        ToastUtil.toast("支付宝支付失败");

    }


}
