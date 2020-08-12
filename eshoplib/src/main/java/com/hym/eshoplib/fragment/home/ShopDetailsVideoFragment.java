package com.hym.eshoplib.fragment.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lib_amap.MapManager;
import com.google.android.material.tabs.TabLayout;
import com.hjq.base.BaseDialog;
import com.hjq.base.BaseDialogFragment;
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
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.eshoplib.listener.GoToPayDialogInterface;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.iconproduct.HomeIconProductBean;
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
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.mz.utils.MzStringUtil;
import cn.hym.superlib.mz.utils.SizeUtils;
import cn.hym.superlib.mz.widgets.TabWithScrollView;
import cn.hym.superlib.pay.Constants;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ShopDetailsVideoFragment extends BaseKitFragment implements View.OnClickListener {

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


    //    @BindView(R.id.tv_1)
//    TextView tv1;
//    @BindView(R.id.tv_team_introduction)
//    TextView tvTeamIntroduction;
//    @BindView(R.id.tv_2)
//    TextView tv2;
//    @BindView(R.id.tv_project_content)
//    TextView tvProjectContent;
//    @BindView(R.id.tv_3)
//    TextView tv3;
//    @BindView(R.id.tv_recommend_goods)
//    TextView tvRecommendGoods;
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
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;
    @BindView(R.id.tv_add_shoppingcart)
    TextView tvAddShoppingcart;


    //////////////////////// 工作室详情  ///////////////////
    @BindView(R.id.tv_des)
    TextView mzRemark;    //   个人简介;

    @BindView(R.id.tv_award_name)
    TextView mzAward;       // 获得奖项;

    @BindView(R.id.tv_study_level)
    TextView mzSchool;         // 毕业院校

    @BindView(R.id.tv_zhuanye)
    TextView mzMajor;   // 专业

    @BindView(R.id.tv_xueli)
    TextView mzEducation;          //学历

    @BindView(R.id.tv_work)
    TextView mzJob;            //  从业经历

    @BindView(R.id.tv_renzheng_1)
    TextView mzRenzheng1;      // 获得奖项 认证

    @BindView(R.id.tv_renzheng_2)
    TextView mzRenzheng2;        // 毕业院校认证;

    @BindView(R.id.ll_gender)
    LinearLayout ll_gender;

    @BindView(R.id.ll_age)
    LinearLayout ll_age;
    @BindView(R.id.ll_height)
    LinearLayout ll_height;
    @BindView(R.id.ll_weight)
    LinearLayout ll_weight;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_age)
    TextView tv_age;

    @BindView(R.id.tv_height)
    TextView tv_height;
    @BindView(R.id.tv_weight)
    TextView tv_weight;

    @BindView(R.id.diver_1)
    View diver_1;
    @BindView(R.id.diver_2)
    View diver_2;
    @BindView(R.id.diver_3)
    View diver_3;
    @BindView(R.id.diver_4)
    View diver_4;


    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.shop_back)
    ImageView shopBack;                         // 返回键

    @BindView(R.id.shop_share)
    ImageView shopShare;                        // 分享按钮

    @BindView(R.id.toolBar)
    LinearLayout toolBar;                       // 顶部工具栏

    @BindView(R.id.scrollView)
    TabWithScrollView scrollView;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.ll_shop_project)
    LinearLayout llShopProject;

    @BindView(R.id.ll_shop_detail)
    LinearLayout llShopDetail;

    @BindView(R.id.ll_shop_recommend)
    LinearLayout llShopRecommend;


    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Unbinder unbinder;
    private GoodDetailModel.DataBean db;
    private GoodDetailModel data;
    private BaseDialog pay_dialog;
    private BaseListAdapter<PayTypeBean> pay_adapter;
    private int pay_position;

    private SnappingStepper stepper;
    private String name;
    private int count = 1;
    private int select_position;
    private BaseListAdapter<ServiceDetailBean.DataBean.CateListBean> adapter1;
    private String category_id;
    private List<ServiceDetailBean.DataBean.CateListBean> cate_list;
    private ServiceDetailBean mData;


    // 地址
    @BindView(R.id.proAddress)
    TextView proAddress;

    // 距离
    @BindView(R.id.proDistance)
    TextView proDistance;

    private LatLonPoint dest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void doLogic() {

        statusBarHeight();
        initScrollAndTab();

        //定位当前位置;
        MapManager.getInstance().location(_mActivity, true, new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                double longitude = aMapLocation.getLongitude();
                double latitude = aMapLocation.getLatitude();
                dest = new LatLonPoint(longitude, latitude);

                if (longitude == 0 || latitude == 0) {
                    ToastUtil.toast("定位失败");
                } else {
                    addAddressDistance();
                }

            }
        });


        ScreenUtil.ViewAdapter(_mActivity, videoplayer, 16, 9, 20);
        showBackButton();
        setTitle(getArguments().getString("title"));
        tvReport.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvReport.getPaint().setAntiAlias(true);//抗锯齿
        tvBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mShareListener = new CustomShareListener(_mActivity);
        mShareAction = new ShareAction(_mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(mShareListener);
        rvRecommendGoods.setLayoutManager(new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false));
        tvReport.setOnClickListener(this);
        rlClickWorkhome.setOnClickListener(this);
        rlContactHim.setOnClickListener(this);
        rlCallPhone.setOnClickListener(this);
        rlCollection.setOnClickListener(this);
        tvGoPay.setOnClickListener(this);
        tvAddShoppingcart.setOnClickListener(this);


        shopBack.setOnClickListener(this);
        shopShare.setOnClickListener(this);

    }


    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        data = (GoodDetailModel) getArguments().getSerializable("data");

        db = data.getData();

        String presentPrice = RemoveZeroUtil.subZeroAndDot(db.getPresent_price());
        tvTotalPrice.setText(presentPrice);

        if (!TextUtils.isEmpty(db.getOriginal_price()) || db.getOriginal_price().equals("0")) {
            tvBeforePrice.setVisibility(View.GONE);
        } else {
            String originalPrice = RemoveZeroUtil.subZeroAndDot(db.getOriginal_price());
            tvBeforePrice.setText("原价" + originalPrice);
        }

        if (TextUtils.isEmpty(db.getLength()) || db.getLength().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvShootTime.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        tvShootTime.setText(db.getEquipment());

        if (TextUtils.isEmpty(db.getEquipment()) || db.getEquipment().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvQicai.getParent();
            llShootTime.setVisibility(View.GONE);
        }
        tvBuyCount.setText("销量" + data.getData().getWeight());
        tvDescribe.setText(db.getTitle());
        tvWhoWork.setText(db.getStore_name());


        String store_rank = db.getStore_rank();
        float v = Float.parseFloat(store_rank);
        ratingbar.setRating(v);

        Glide.with(_mActivity).load(db.getStore_logo()).into(ivUserPhoto);

        tvShootTime.setText(db.getLength());
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

        // 推荐 ;
        HomeApi.getDetailComment("1", new ResponseImpl<SpecialTimeLimteBean>() {
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

        ShopApi.GetContentDetail(db.getContent_id(),
                new ResponseImpl<ServiceDetailBean>() {
                    @Override
                    public void onSuccess(ServiceDetailBean data) {
                        mData = data;
                        category_id = data.getData().getCategory_id();
                        cate_list = data.getData().getCate_list();

                        if (category_id.equals("46")) {
                            rlCallPhone.setVisibility(View.GONE);
                            setCategoryId46(data);
                        }

                        setDetailData(data);

                    }
                }, ServiceDetailBean.class);


        //视频
        videoplayer.setUp(data.getData().getFilepath(),
                JCVideoPlayer.SCREEN_LAYOUT_LIST,
                data.getData().getTitle() + "");

        ImageUtil.getInstance().loadImage(_mActivity, data.getData().getImage_default(),
                videoplayer.thumbImageView);

        //详情模式下不显示时间和观看次数和标题
        videoplayer.setType(1);
        videoplayer.titleTextView.setVisibility(View.GONE);
        videoplayer.tv_see_count.setVisibility(View.GONE);
        videoplayer.tv_time_long.setVisibility(View.GONE);
        autoPlayVideo();


    }


    @Override
    public int getContentViewResId() {
        return R.layout.shopdetail_video_activity;
    }

    public static SupportFragment newInstance(Bundle args) {
        ShopDetailsVideoFragment fragment = new ShopDetailsVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    //显示选择框
    private void showSelectTypeDialog(final int i) {
        if (data == null) {
            ToastUtil.toast("数据异常");
            return;
        }

        //设置header
        final View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_type_dialog, null, false);
        ImageView iv_logo = header.findViewById(R.id.iv_logo);
        TextView tv_shop_name = header.findViewById(R.id.tv_shop_name);
        final TextView tv_price = header.findViewById(R.id.tv_price);
        ImageUtil.getInstance().loadImage(ShopDetailsVideoFragment.this, data.getData().getImage_default(), iv_logo);
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
                    ShoppingCarApi.addToShoppingCar(_mActivity, data.getData().getContent_id(),
                            String.valueOf(count), new ResponseImpl<Object>() {
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
                    ShopApi.attachNow(data.getData().getContent_id(),
                            String.valueOf(count), new ResponseImpl<AttachResultBean>() {
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
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1, "", null, "立即购买", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    if (mData == null && data != null) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_shoppingcart:
                showSelectTypeDialog(1);
                break;
            case R.id.tv_go_pay:
                //showSelectTypeDialog(2);
                showGoToPayDialog();
                break;
            case R.id.tv_report:

                break;
            case R.id.rl_click_workhome:
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                bundle.putString("id", db.getContent_id());
                ActionActivity.start(_mActivity, bundle);
                break;
            case R.id.rl_contact_him:
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
                    MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
                        @Override
                        public void onSuccess(final MedetailBean data) {

                            //是会员，正常连接聊天
                            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                reconnect(UserUtil.getRongYunToken(_mActivity));
                            } else {
                                RongIM.getInstance().startPrivateChat(_mActivity, mData.getData().getUserid(), mData.getData().getStore_name());
                            }
                        }
                    }, MedetailBean.class);
                }
                break;
            case R.id.rl_call_phone:
                if (!TextUtils.isEmpty(data.getData().getTel())) {
                    Intent Intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + data.getData().getTel()));
                    startActivity(Intent);
                }
                break;
            case R.id.rl_collection:
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

            case R.id.shop_back:

                pop();

                break;
            case R.id.shop_share:
                share();

                break;
        }
    }

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

    private void showGoToPayDialog() {
        if (db == null) {
            ToastUtils.show("数据异常,请稍后再试");
        }
        MipaiDialogUtil.showGoToPayDialog(_mActivity, db.getPresent_price(), db.getTitle(), listener);
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

    private void autoPlayVideo() {
        if (!JCUtils.isWifiConnected(getContext()) || SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true) == false) {
            return;
        }
        videoplayer.startButton.performClick();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        JCVideoPlayer.releaseAllVideos();
        super.onDestroy();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }


    // 计算距离
    private void addAddressDistance() {
        Bundle bundle = getArguments();
        HomeIconProductBean.DataBean.VideoBean item = (HomeIconProductBean.DataBean.VideoBean) bundle.getSerializable(MzConstant.KEY_HOME_ICON_PRODUCT);


        String lon = item.getLon();
        String lat = item.getLat();

        String address = item.getAddress();
        proAddress.setText(address);

        if (TextUtils.isEmpty(lon) || TextUtils.isEmpty(lat)) {
            return;
        }

        if (!TextUtils.isEmpty(lon) && !TextUtils.isEmpty(lat) && dest != null) {
            double longitute = Double.parseDouble(lon);
            double latitude = Double.parseDouble(lat);

            if (longitute != 0 && latitude != 0) {

                LatLonPoint point = new LatLonPoint(longitute, latitude);
                ArrayList<LatLonPoint> list = new ArrayList();
                list.add(point);
//                Log.e(TAG, "计算");

                MapManager.getInstance().calculateInstance(_mActivity, dest, list,
                        DistanceSearch.TYPE_DISTANCE,
                        new DistanceSearch.OnDistanceSearchListener() {
                            @Override
                            public void onDistanceSearched(DistanceResult distanceResult, int i) {
                                List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
                                DistanceItem distanceItem = distanceResults.get(0);
                                float distance = distanceItem.getDistance();
                                String result = MzStringUtil.distance(distance);
                                proDistance.setText("距您" + result);
                            }
                        });
            }

        }


    }


    // 工作室详情
    private void setDetailData(ServiceDetailBean data) {
        mzRemark.setText(TextUtils.isEmpty(data.getData().getRemark()) ? "暂无" : data.getData().getRemark());//个人简介
        mzJob.setText(TextUtils.isEmpty(data.getData().getJob()) ? "暂无" : data.getData().getJob());
        mzAward.setText(TextUtils.isEmpty(data.getData().getAwards_memo()) ? "暂无" : data.getData().getAwards_memo());
        mzSchool.setText(TextUtils.isEmpty(data.getData().getUniversity()) ? "暂无" : data.getData().getUniversity());
        mzEducation.setText(TextUtils.isEmpty(data.getData().getEducation()) ? "暂无" : data.getData().getEducation());
        mzMajor.setText(TextUtils.isEmpty(data.getData().getMajor()) ? "暂无" : data.getData().getMajor());
        if (data.getData().getCertificate_auth().equals("1")) {
            mzRenzheng1.setText("已认证");
        } else {
            mzRenzheng1.setText("未认证");
        }
        if (data.getData().getXuelizs_auth().equals("1")) {
            mzRenzheng2.setText("已认证");
        } else {
            mzRenzheng2.setText("未认证");
        }
    }


    //  category_id  == 46
    private void setCategoryId46(ServiceDetailBean data) {
        ll_gender.setVisibility(View.VISIBLE);
        ll_age.setVisibility(View.VISIBLE);
        ll_height.setVisibility(View.VISIBLE);
        ll_weight.setVisibility(View.VISIBLE);
        diver_1.setVisibility(View.VISIBLE);
        diver_2.setVisibility(View.VISIBLE);
        diver_3.setVisibility(View.VISIBLE);
        diver_4.setVisibility(View.VISIBLE);
        String gender = "";
        if (data.getData().getGender().equals("1")) {
            gender = "男";
        }
        if (data.getData().getGender().equals("2")) {
            gender = "女";
        }
        tv_gender.setText(gender);
        tv_age.setText(data.getData().getAge() + "岁");
        tv_height.setText(data.getData().getHeight() + "cm");
        tv_weight.setText(data.getData().getWeight() + "kg");
    }

    // 分享 ;
    private void share() {
        mShareListener = new CustomShareListener(_mActivity);
        mShareAction = new ShareAction(_mActivity)
                .setDisplayList(
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.SINA)
                .setCallback(mShareListener);

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
        web.setTitle(title);
        web.setThumb(new UMImage(_mActivity, image));
        web.setDescription(memo);
        mShareAction.withMedia(web);
        mShareAction.open(config);
    }


    // 设置状态栏高度
    private int statusBarHeight() {
        int height = 0;
        int resourceId = getContext().getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getContext().getResources().getDimensionPixelSize(resourceId);
        }

        statusBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, height));
        return height;
    }


    private void initScrollAndTab() {
        //  toolBar.setAlpha(0);
        toolBar.setBackgroundColor(setAlpha(R.color.white, 1));
        String[] tabList = new String[]{"项目", /*"评论",*/ "详情", "推荐"};

        for (String s : tabList) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }

        List<View> views = new ArrayList<>();
        views.add(llShopProject);
        views.add(llShopDetail);
        views.add(llShopRecommend);

        scrollView.setAnchorList(views);
        scrollView.setupWithTabLayout(tabLayout);

        scrollView.setTranslationY(SizeUtils.getMeasuredHeight(toolBar) + statusBarHeight());
//        int bannerHeight = SizeUtils.getMeasuredHeight(rvList);

        scrollView.setOnScrollCallback(new TabWithScrollView.OnScrollCallback() {
            @Override
            public void onScrollCallback(int l, int t, int oldl, int oldt) {
//                setBgAlphaChange(t, bannerHeight);

            }
        });

    }


    //
    private int setAlpha(@ColorRes int resId, int alpha) {
        int color = getColor(resId);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);

    }


    private int getColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getColor(resId);
        } else {
            return getContext().getResources().getColor(resId);
        }
    }


    private void setBgAlphaChange(int t, float height) {
        assert toolBar != null;
        if (t <= 0) {
            tabLayout.setAlpha(1);
            //
        } else if (t < height) {
            float scale = (float) t / height;
            if (scale > 0.2f) {
                tabLayout.setVisibility(View.VISIBLE);
            } else {
                tabLayout.setVisibility(View.GONE);
            }

            tabLayout.setAlpha(scale);
            toolBar.setBackgroundColor(setAlpha(R.color.white, (int) (255 * scale)));
        }
    }


    // 取得评论
    private void getComment() {

        String caseId = data.getData().getCase_id();

//        MzNewApi.getComment(caseId, 1, new ResponseImpl<T>() {
//            @Override
//            public void onSuccess(T data) {
//
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
