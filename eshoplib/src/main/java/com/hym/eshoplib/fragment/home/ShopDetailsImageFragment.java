package com.hym.eshoplib.fragment.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.base.BaseDialog;
import com.hjq.base.BaseDialogFragment;
import com.hjq.dialog.MessageDialog;
import com.hjq.toast.ToastUtils;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.alipay.AliPay;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.CreateOrderBean;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.bean.order.AliPayBean;
import com.hym.eshoplib.bean.order.PayTypeBean;
import com.hym.eshoplib.bean.order.VipOrderBean;
import com.hym.eshoplib.bean.order.WxpayBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.bean.shop.AttachResultBean;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.fragment.goods.VipRuleFragment;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.eshoplib.listener.GoToPayDialogInterface;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.util.RemoveZeroUtil;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.pay.Constants;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ShopDetailsImageFragment extends BaseKitFragment implements
        View.OnClickListener, AliPay.PayResultListener {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_before_price)
    TextView tvBeforePrice;
    @BindView(R.id.iv_notify)
    ImageView ivNotify;
    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_buy_count)
    TextView tvBuyCount;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @BindView(R.id.tv_who_work)
    TextView tvWhoWork;
    @BindView(R.id.tv_is_shimin)
    TextView tvIsShimin;
    @BindView(R.id.iv_rating01)
    ImageView ivRating01;
    @BindView(R.id.iv_rating02)
    ImageView ivRating02;
    @BindView(R.id.iv_rating03)
    ImageView ivRating03;
    @BindView(R.id.iv_rating04)
    ImageView ivRating04;
    @BindView(R.id.iv_rating05)
    ImageView ivRating05;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tv_0)
    TextView tv0;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_team_introduction)
    TextView tvTeamIntroduction;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_project_content)
    TextView tvProjectContent;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_recommend_goods)
    TextView tvRecommendGoods;
    @BindView(R.id.rv_recommend_goods)
    RecyclerView rvRecommendGoods;
    @BindView(R.id.iv_b_01)
    ImageView ivB01;
    @BindView(R.id.rl_contact_him)
    RelativeLayout rlContactHim;
    @BindView(R.id.iv_b_02)
    ImageView ivB02;
    @BindView(R.id.rl_call_phone)
    RelativeLayout rlCallPhone;
    @BindView(R.id.iv_b_03)
    ImageView ivB03;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.ll_rating)
    LinearLayout llRating;
    @BindView(R.id.tv_shoot_time)
    TextView tvShootTime;
    @BindView(R.id.tv_loca)
    TextView tvLoca;
    @BindView(R.id.tv_qicai)
    TextView tvQicai;
    @BindView(R.id.shooting_day_time)
    TextView shootingDayTime;
    @BindView(R.id.tv_staffing)
    TextView tvStaffing;
    @BindView(R.id.tv_team_introduce)
    TextView tvTeamIntroduce;
    @BindView(R.id.tv_project_detail)
    TextView tvProjectDetail;
    @BindView(R.id.rl_click_workhome)
    RelativeLayout rlClickWorkhome;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_add_shoppingcart)
    TextView tvAddShoppingcart;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;

    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Unbinder unbinder;
    private GoodDetailModel.DataBean db;
    private GoodDetailModel data;
    private BaseListAdapter<String> adapter;
    private BaseDialog message_dialog;
    private BaseDialog pay_dialog;
    private BaseListAdapter<PayTypeBean> pay_adapter;
    private int pay_position;
    private int select_position;
    private String name;
    private int count = 1;
    private SnappingStepper stepper;
    private ServiceDetailBean mData;
    private String category_id;
    private List<ServiceDetailBean.DataBean.CateListBean> cate_list;
    private BaseListAdapter<ServiceDetailBean.DataBean.CateListBean> adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle(getArguments().getString("title"));
        tvReport.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvReport.getPaint().setAntiAlias(true);//抗锯齿
        tvBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        mShareListener = new CustomShareListener(_mActivity);

        mShareAction = new ShareAction(_mActivity)
                .setDisplayList(
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.SINA)
                .setCallback(mShareListener);


        rvRecommendGoods.setLayoutManager(new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false));
        tvReport.setOnClickListener(this);
        rlClickWorkhome.setOnClickListener(this);
        rlContactHim.setOnClickListener(this);
        rlCallPhone.setOnClickListener(this);
        rlCollection.setOnClickListener(this);
        tvAddShoppingcart.setOnClickListener(this);
        tvGoPay.setOnClickListener(this);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        data = (GoodDetailModel) getArguments().getSerializable("data");
        db = data.getData();
        String presentPrice = RemoveZeroUtil.subZeroAndDot(db.getPresent_price());
        tvTotalPrice.setText(presentPrice);
        if (TextUtils.isEmpty(db.getOriginal_price()) || db.getOriginal_price().equals("0")) {
            tvBeforePrice.setVisibility(View.GONE);
        } else {
            String originalPrice = RemoveZeroUtil.subZeroAndDot(db.getOriginal_price());
            tvBeforePrice.setText("原价" + originalPrice);
        }

        tvBuyCount.setText("销量" + data.getData().getWeight());
        tvDescribe.setText(db.getTitle());
        tvWhoWork.setText(db.getStore_name());
        // int childCount = llRating.getChildCount();
        String store_rank = db.getStore_rank();
        float v = Float.parseFloat(store_rank);
        ratingbar.setRating(v);
        /*long round = Math.round(v);
        showRing(childCount, round);*/
        Glide.with(_mActivity).load(db.getStore_logo()).into(ivUserPhoto);
        if (TextUtils.isEmpty(db.getLength()) || db.getLength().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvShootTime.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        tvShootTime.setText(db.getEquipment());
        if (TextUtils.isEmpty(db.getEquipment()) || db.getEquipment().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvQicai.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        tvQicai.setText(db.getEquipment());
        if (!TextUtils.isEmpty(db.getShooting_time())) {
           /* long shootTime = Long.parseLong(db.getShooting_time());
            long day = shootTime / 1000 / (60 * 60 * 24);
            shootingDayTime.setText(day + "天");*/
            shootingDayTime.setText(db.getShooting_time());
        } else {
            LinearLayout llShootTime = (LinearLayout) shootingDayTime.getParent();
            llShootTime.setVisibility(View.GONE);
            shootingDayTime.setText("");
        }
       /* long shootTime = Long.parseLong(db.getShooting_time());
        long day = shootTime / 1000 / (60 * 60 * 24);
        shootingDayTime.setText(day + "天");*/
        if (TextUtils.isEmpty(db.getStaffing()) || db.getStaffing().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvStaffing.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(db.getOther()) || db.getOther().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvLoca.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        tvStaffing.setText(db.getStaffing());
        tvTeamIntroduce.setText(db.getIntroduce());
        tvProjectDetail.setText(db.getDetails());
        tvLoca.setText(db.getOther());
        if (db.getAuth() == 1) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_person_rt);
        } else if (db.getAuth() == 2) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_business_rt);
        } else {
            ivVip.setVisibility(View.GONE);
        }
        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new BaseListAdapter<String>(R.layout.item_image_gridlist, null) {
            @Override
            public void handleView(BaseViewHolder helper, String item, int position) {
                ImageView iv = helper.getView(R.id.iv_image);
                LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) iv.getLayoutParams();
                int screenWidth = ScreenUtil.getScreenWidth(_mActivity);
                int width = (screenWidth - ScreenUtil.dip2px(_mActivity, 40)) / 3;
                layoutParam.height = width;
                layoutParam.width = width;
                if (position % 3 == 0) {
                    //左侧
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                } else if (position % 3 == 1) {
                    //中间
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                } else {
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                }
                ImageUtil.getInstance().loadImage(ShopDetailsImageFragment.this, item, iv);

            }
        };

        ShopApi.GetContentDetail(db.getContent_id(), new ResponseImpl<ServiceDetailBean>() {
            @Override
            public void onSuccess(ServiceDetailBean data) {
                mData = data;
                category_id = data.getData().getCategory_id();
                cate_list = data.getData().getCate_list();
                if (category_id.equals("46")) {
                    rlCallPhone.setVisibility(View.GONE);
                }
            }
        }, ServiceDetailBean.class);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int width = ScreenUtil.getScreenWidth(_mActivity);
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                ImagePagerActivity.startImagePagerActivity(_mActivity, adapter.getData(), position, imageSize);
            }
        });

        rvList.setAdapter(adapter);
        List<String> attachment = data.getData().getAttachment();
        adapter.setNewData(attachment);

        HomeApi.getDetailComment("2", new ResponseImpl<SpecialTimeLimteBean>() {
            @Override
            public void onSuccess(final SpecialTimeLimteBean data) {
                final List<SpecialTimeLimteBean.DataBean.VideoBean> vbData = data.getData().getVideo();
                ShopListAdapter shopListAdapter = new ShopListAdapter(R.layout.item_shop, vbData);
                rvRecommendGoods.setAdapter(shopListAdapter);
                shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        String case_id = vbData.get(position).getCase_id();
                        HomeApi.getProductDetailData(new BaseFragment.ResponseImpl<GoodDetailModel>() {
                            @Override
                            public void onSuccess(GoodDetailModel data) {
                                if (data.getData().getType().equals("1")) {
                                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopVideoDetail);
                                    bundle.putSerializable("data", data);
                                    bundle.putString("title", "产品详情");
                                    ActionActivity.start(_mActivity, bundle);
                                } else if (data.getData().getType().equals("2")) {
                                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopDetail);
                                    bundle.putSerializable("data", data);
                                    bundle.putString("title", "产品详情");
                                    ActionActivity.start(_mActivity, bundle);
                                }
                            }

                            @Override
                            public void onFailed(Exception e) {
                                super.onFailed(e);
                            }

                            @Override
                            public void onDataError(String status, String errormessage) {
                                super.onDataError(status, errormessage);
                            }
                        }, GoodDetailModel.class, case_id);
                    }
                });

            }

            @Override
            public void onDataError(String status, String errormessage) {
                super.onDataError(status, errormessage);
            }

            @Override
            public void onFailed(Exception e) {
                super.onFailed(e);
            }
        }, SpecialTimeLimteBean.class);

        // 分享按钮;
        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                String url = "";
                String title = "";
                String memo = "";
                String image = "";

                if (data != null) {
                    url = data.getData().getShare_url();
                    title = data.getData().getTitle();
                    memo = data.getData().getDetails();
                    image = data.getData().getImage_default();
                } else {
                    url = data.getData().getShare_url();
                    title = data.getData().getTitle();
                    memo = data.getData().getDetails();
                    image = data.getData().getImage_default();
                }
                UMWeb web = new UMWeb(url + "&share=1");
                //  web.setTitle(title+" | "+memo);
                web.setTitle(title);
                web.setThumb(new UMImage(_mActivity, image));
                web.setDescription(memo);
                mShareAction.withMedia(web);
                mShareAction.open(config);
            }
        });


    }

    private void showRing(int childCount, long round) {
        for (int i = 0; i < childCount; i++) {
            if (i <= round - 1) {
                llRating.getChildAt(i).setBackgroundResource(R.drawable.icon_full_start);
            }
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.shopdetail_image_activity;
    }

    public static SupportFragment newInstance(Bundle args) {
        ShopDetailsImageFragment fragment = new ShopDetailsImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void showSelectTypeDialog(final int i) {
        if (mData == null) {
            ToastUtil.toast("数据异常");
            return;
        }
        //选择数量
        //设置header
        final View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_type_dialog, null, false);
        ImageView iv_logo = header.findViewById(R.id.iv_logo);
        TextView tv_shop_name = header.findViewById(R.id.tv_shop_name);
        final TextView tv_price = header.findViewById(R.id.tv_price);
        ImageUtil.getInstance().loadImage(ShopDetailsImageFragment.this, mData.getData().getLogo(), iv_logo);
        tv_shop_name.setText(mData.getData().getStore_name() + "");
        tv_price.setText("￥：" + mData.getData().getCate_list().get(select_position).getPrice());
        //设置footer
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_type_dialog, null, false);
        TextView tv_life_circle = footer.findViewById(R.id.tv_life_circle);
        TextView tv_sub_title = footer.findViewById(R.id.tv_sub_title);
        switch (category_id) {
            case "1":
                //文案策划
                // tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认收货");
                //tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
                // tvTips.setVisibility(View.GONE);
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
                // tvTips.setVisibility(View.GONE);
                tv_sub_title.setText("购买数量单位为天");
                break;
            case "40":
                // tvTips.setVisibility(View.GONE);
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
                // tvCurrentGoods.setText("\"" + name + "\"" + "数量x" + count);
            }
        });
        adapter1 = new BaseListAdapter<ServiceDetailBean.DataBean.CateListBean>(R.layout.item_spec_btn, cate_list) {
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
                            // tvPrice.setText(price);
                            //Logger.d("price="+item.getPrice()+",name="+name);
                            /*tvCurrentGoods.setText("\"" + name + "\"" + "数量x" + count);*/
                            select_position = position;
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        adapter1.addHeaderView(header);
        adapter1.addFooterView(footer);
        if (i == 0) {
            //选择规格
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1, "加入购物车", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (data == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (data.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    //加入购物车
                    ShoppingCarApi.addToShoppingCar(_mActivity, data.getData().getContent_id(), count + "", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("加入购物车成功，请到购物车中查看");
                        }
                    }, Object.class);
                }
            }, "立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getData().getAuth() == 0) {
                        //弹出认证
                        Dialog dilog = MipaiDialogUtil.getAuthDialog(_mActivity, "认证信息", "完善资料才可以预约合作哦", new MipaiDialogUtil.OnBtnSlectListener() {
                            @Override
                            public void on1(Dialog dialog) {
                                dialog.dismiss();
                                //个人认证
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Personal);
                                bundle.putString("type", data.getData().getAuth() + "");
                                ActionActivity.start(_mActivity, bundle);

                            }

                            @Override
                            public void on2(Dialog dialog) {
                                dialog.dismiss();
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Business);
                                bundle.putString("type", mData.getData().getAuth_store());
                                ActionActivity.start(_mActivity, bundle);

                            }
                        });
                        dilog.show();
                        return;
                    }
                    MipaiDialogUtil.dismiss();

                    if (data == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (data.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    //立即预约
                    ShopApi.attachNow(data.getData().getContent_id(), count + "", new ResponseImpl<AttachResultBean>() {
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
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1, "", null, "加入购物车", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (data == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (data.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    ShoppingCarApi.addToShoppingCar(_mActivity, data.getData().getContent_id(), count + "", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("加入购物车成功，请到购物车中查看");
                        }
                    }, Object.class);
                }
            }, true);
        } else {
            //立即购买
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1, "", null, "立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (mData == null) {
                        ToastUtil.toast("数据异常");
                        return;
                    }
                    if (mData.getData().getIs_mine().equals("1")) {
                        ToastUtil.toast("不能购买自己工作室的服务");
                        return;
                    }
                    ShopApi.CreateDetailOrder(_mActivity, db.getContent_id(), count + "", db.getCase_id(), new ResponseImpl<CreateOrderBean>() {
                        @Override
                        public void onSuccess(CreateOrderBean data) {
                            //付款
                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_pay);
                            bundle.putString("id", data.getData().getOrder_number());
                            bundle.putString("needPay", data.getData().getMoney() + "");
                            bundle.putString("id2", data.getData().getLog_id());
                            EshopActionActivity.start(_mActivity, bundle);
                        }
                    }, CreateOrderBean.class);
                }
            }, true);
        }
    }

    // 付款的回调监听 ;
    private GoToPayDialogInterface listener = new GoToPayDialogInterface() {
        @Override
        public void getCoutn(int count) {
            ShopApi.CreateDetailOrder(_mActivity, db.getContent_id(), count + "", db.getCase_id(), new ResponseImpl<CreateOrderBean>() {
                @Override
                public void onSuccess(CreateOrderBean data) {
                    //付款
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_pay);
                    bundle.putString("id", data.getData().getOrder_number());
                    bundle.putString("needPay", data.getData().getMoney() + "");
                    bundle.putString("id2", data.getData().getLog_id());
                    EshopActionActivity.start(_mActivity, bundle);
                }
            }, CreateOrderBean.class);
        }

        @Override
        public void getPrice(double price) {

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_shoppingcart:        // 加入购物车，现在是隐藏状态
                showSelectTypeDialog(1);
                break;
            case R.id.tv_go_pay:            // 付款
                if (db == null) {
                    ToastUtils.show("数据异常,请稍后再试");
                }
                MipaiDialogUtil.showGoToPayDialog(_mActivity,
                        db.getPresent_price(),
                        db.getTitle(),
                        listener);
                // showSelectTypeDialog(2);
                break;
            case R.id.tv_report:    // 举报

                break;
            case R.id.rl_click_workhome:

                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,
                        ActionActivity.Action_ShopDetail);
                bundle.putString("id", db.getContent_id());
                ActionActivity.start(_mActivity, bundle);

                break;
            case R.id.rl_contact_him:     // 联系客服
                if (data == null) {
                    ToastUtil.toast("数据异常");
                    return;
                }
                if (data.getData().getIs_mine().equals("1")) {
                    ToastUtil.toast("不能与自己的工作室聊天");
                    return;
                }
                if (!UserUtil.getIsLogin(_mActivity)) {
                    ToastUtil.toast("请先登录");
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                    bundle1.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                    LoginMainActivity.start(_mActivity, bundle1);
                } else {
                    //如果没连接则先连接
                    //判断是不是vip

                    MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
                        @Override
                        public void onSuccess(final MedetailBean data) {

                            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                reconnect(UserUtil.getRongYunToken(_mActivity));
                            } else {
                                RongIM.getInstance().startPrivateChat(_mActivity, mData.getData().getUserid(), mData.getData().getStore_name());
                            }
                            // data.getData().setLv_is_true("0");
                        /*    if (!data.getData().getLv_is_true().equals("1")) {
                                //不是会员 弹出购买会员或者分享
                                getVipStatus(data);
                            } else {
                                //是会员，正常连接聊天
                                if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                    reconnect(UserUtil.getRongYunToken(_mActivity));
                                } else {
                                    RongIM.getInstance().startPrivateChat(_mActivity, data.getData().getUserid(), data.getData().getIs_store());
                                }
                            }*/
                        }
                    }, MedetailBean.class);


                }
                break;
            case R.id.rl_call_phone:            // 打电话
                if (!TextUtils.isEmpty(data.getData().getTel())) {
                    Intent Intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + data.getData().getTel()));
                    startActivity(Intent);
                }
                break;
            case R.id.rl_collection:    //收藏按钮 ;

                ShopApi.AddFavorite(data.getData().getContent_id(), "case", new ResponseImpl<AddFavouriteBean>() {
                    @Override
                    public void onSuccess(AddFavouriteBean data) {
                        if (data.getData().getStatus() == 1) {
                            ivB03.setBackgroundResource(R.mipmap.icon_collection_light);
                            ToastUtils.show("收藏");
                            // tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
                        } else {
                            ivB03.setBackgroundResource(R.mipmap.icon_collection);
                            ToastUtils.show("取消收藏");
                            // tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
                        }
                    }
                }, AddFavouriteBean.class);
                break;
        }
    }

    // 开通 VIP
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
        ShareAction mShareAction = new ShareAction(_mActivity)
                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE);

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
                    pay_dialog = new BaseDialogFragment.Builder(_mActivity)
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
                                                    aliPay = new AliPay(_mActivity, ShopDetailsImageFragment.this);
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
                RongIM.getInstance().startPrivateChat(_mActivity, mData.getData().getUserid(), mData.getData().getStore_name());
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Logger.d("---onError--" + e);
            }
        });
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
