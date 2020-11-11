package com.hym.eshoplib.fragment.home;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.hjq.toast.ToastUtils;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.CreateOrderBean;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.bean.order.PayTypeBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.bean.shop.AttachResultBean;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.fragment.search.mz.model.LngLonModel;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.eshoplib.listener.GoToPayDialogInterface;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.adapter.ShopCommentAdapter;
import com.hym.eshoplib.mz.adapter.ShopPicAdapter;
import com.hym.eshoplib.mz.adapter.ShopProjectPicAdapter;
import com.hym.eshoplib.mz.shopdetail.MzShopCommentBean;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.util.RemoveZeroUtil;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.manager.ShareBean;
import cn.hym.superlib.manager.ShareDialog;
import cn.hym.superlib.mz.listener.FragmentKeyDownListener;
import cn.hym.superlib.mz.utils.MzStringUtil;
import cn.hym.superlib.mz.utils.MzUtil;
import cn.hym.superlib.mz.utils.SizeUtils;
import cn.hym.superlib.mz.widgets.MzShopDetailTitleView;
import cn.hym.superlib.mz.widgets.TabWithScrollView;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.DialogView;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import constant.StringConstants;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static android.media.AudioManager.STREAM_MUSIC;

public class ShopDetailsVideoFragment extends BaseKitFragment
        implements View.OnClickListener, FragmentKeyDownListener {


    private String TAG = "ShopDetailsVideoFragment";

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_before_price)
    TextView tvBeforePrice;


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

    @BindView(R.id.speaker)
    ImageView speaker;


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

    @BindView(R.id.tv_pic_count)
    TextView tvPicCount;

    @BindView(R.id.shooting_day_time)
    TextView shootingDayTime;

    // 摄影师
    @BindView(R.id.tv_photographer)
    TextView tvPhotographer;

    // 化妆师
    @BindView(R.id.tv_dresser)
    TextView tvDresser;

    //----------
    @BindView(R.id.v_shooting_day_time)
    TextView vShootingDayTime;

    @BindView(R.id.v_photographer)
    TextView vPhotographer;

    @BindView(R.id.v_dresser)
    TextView vDresser;


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
    @BindView(R.id.ll_des)
    LinearLayout llRemark;
    @BindView(R.id.tv_des)
    TextView mzRemark;

    // 获得奖项;
    @BindView(R.id.ll_award)
    LinearLayout llAward;
    @BindView(R.id.tv_award_name)
    TextView mzAward;

    // 毕业院校
    @BindView(R.id.ll_school)
    LinearLayout llSchool;
    @BindView(R.id.tv_study_level)
    TextView mzSchool;

    // 专业
    @BindView(R.id.ll_zhuanye)
    LinearLayout llMajor;
    @BindView(R.id.tv_zhuanye)
    TextView mzMajor;

    //学历
    @BindView(R.id.ll_xueli)
    LinearLayout llEducation;
    @BindView(R.id.tv_xueli)
    TextView mzEducation;

    //  从业经历
    @BindView(R.id.ll_work)
    LinearLayout llJob;
    @BindView(R.id.tv_work)
    TextView mzJob;

    // 获得奖项 认证
    @BindView(R.id.tv_renzheng_1)
    TextView mzRenzheng1;

    // 毕业院校认证;
    @BindView(R.id.tv_renzheng_2)
    TextView mzRenzheng2;

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

    // 评论
    @BindView(R.id.ll_shop_comment)
    LinearLayout llShopComment;

    // 评论  标签 ;
    @BindView(R.id.commentTabLayout)
    TagFlowLayout commentTabLayout;

    @BindView(R.id.commentAll)
    TextView commentAll;
    @BindView(R.id.commentRv)
    RecyclerView commentRv;
    @BindView(R.id.commentNoView)
    TextView commentNoView;
    @BindView(R.id.commentMore)
    TextView commentMore;

    @BindView(R.id.ll_shop_detail)
    LinearLayout llShopDetail;

    @BindView(R.id.tv_project_detail)
    TextView tvProjectDetail;
    @BindView(R.id.rv_project_detail)
    RecyclerView rvProjectDetail;

    @BindView(R.id.ll_shop_recommend)
    LinearLayout llShopRecommend;


    // 彩蛋
    @BindView(R.id.colorEgg)
    MzShopDetailTitleView colorEgg;

    private String colorEgg_caseID = "";

    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Unbinder unbinder;
    private GoodDetailModel.DataBean db;
    private GoodDetailModel data;
    private BaseDialog pay_dialog;
    private BaseListAdapter<PayTypeBean> pay_adapter;

    private SnappingStepper stepper;
    private String name;
    private int count = 1;
    private int select_position;
    private BaseListAdapter<ServiceDetailBean.DataBean.CateListBean> adapter1;
    private String category_id;
    private List<ServiceDetailBean.DataBean.CateListBean> cate_list;
    private ServiceDetailBean mData;


    // 地址
    @BindView(R.id.l_address)
    LinearLayout lAddress;

    @BindView(R.id.proAddress)
    TextView proAddress;

    // 距离
    @BindView(R.id.proDistance)
    TextView proDistance;

    // 定位 当前位置
    private LatLonPoint dest;

    // 目标位置
    private LatLonPoint point;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
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

//                Log.e(TAG, " 定位 =  " + aMapLocation);

                double longitude = aMapLocation.getLongitude();
                double latitude = aMapLocation.getLatitude();
                dest = new LatLonPoint(latitude, longitude);

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
        rvRecommendGoods.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tvReport.setOnClickListener(this);
        rlClickWorkhome.setOnClickListener(this);
        rlContactHim.setOnClickListener(this);
        rlCallPhone.setOnClickListener(this);
        rlCollection.setOnClickListener(this);
        tvGoPay.setOnClickListener(this);
        tvAddShoppingcart.setOnClickListener(this);


        shopBack.setOnClickListener(this);
        shopShare.setOnClickListener(this);

        // 点击地址导航
        lAddress.setOnClickListener(this);

        // 彩蛋。
        colorEgg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!TextUtils.isEmpty(colorEgg_caseID)) {
                    //  需要的参数   log_id   LogoUrl ;
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,
                            EshopActionActivity.Action_order_add_comment);


                    // caseId
                    bundle.putString(MzConstant.KEY_ORDER_CASE_ID, colorEgg_caseID);

                    EshopActionActivity.start(_mActivity, bundle);
                } else {
                    ToastUtil.toast("caseId 为空 ");
                }

                return false;

            }
        });

    }


    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        data = (GoodDetailModel) getArguments().getSerializable("data");

        db = data.getData();


        colorEgg_caseID = db.getCase_id();

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
        tvShootTime.setText(db.getLength());

        //拍摄器材
        if (TextUtils.isEmpty(db.getEquipment()) || db.getEquipment().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvQicai.getParent();
            llShootTime.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(db.getNums())) {
            LinearLayout llPicCount = (LinearLayout) tvPicCount.getParent();
            llPicCount.setVisibility(View.GONE);
        }
        tvPicCount.setText(db.getNums());


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

            shootingDayTime.setText(db.getShooting_time());
        } else {
            LinearLayout llShootTime = (LinearLayout) shootingDayTime.getParent();
            llShootTime.setVisibility(View.GONE);
            shootingDayTime.setText("");
            vShootingDayTime.setVisibility(View.GONE);
        }

        // 摄影师
        if (TextUtils.isEmpty(db.getPhotographer()) || db.getPhotographer().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvPhotographer.getParent();
            llShootTime.setVisibility(View.GONE);
            vPhotographer.setVisibility(View.GONE);
        }
        tvPhotographer.setText(db.getPhotographer());

        // 化妆师
        if (TextUtils.isEmpty(db.getDresser()) || db.getDresser().equals("0")) {
            LinearLayout llDresser = (LinearLayout) tvDresser.getParent();
            llDresser.setVisibility(View.GONE);
            vDresser.setVisibility(View.GONE);
        }
        tvDresser.setText(db.getDresser());

        if (TextUtils.isEmpty(db.getOther()) || db.getOther().equals("0")) {
            LinearLayout llShootTime = (LinearLayout) tvLoca.getParent();
            llShootTime.setVisibility(View.GONE);

        }

//        tvTeamIntroduce.setText(db.getIntroduce());

        tvProjectDetail.setText(db.getDetails());

        String project_img = db.getProject_img();
        projectImage(project_img);

        tvLoca.setText(db.getAddress());

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


        getComment();


        // 工作室详情 ;
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


        //////////////////////////       视频
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

                DialogView dialog = DialogManager.getInstance()
                        .initView(_mActivity, R.layout.mz_dialog_shopdetail_report, Gravity.CENTER);
                dialog.show();
                dialog.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
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
                share2();

                break;

            case R.id.l_address:

                if (TextUtils.isEmpty(proAddress.getText()) || TextUtils.isEmpty(proDistance.getText())) {
                    return;
                }

                choiceMap();

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
        // wifi 自动播放 ;
        if (!JCUtils.isWifiConnected(getContext())
                || SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true) == false) {
            return;
        }
        // 自动播放  而且 静音播放 ;
        videoplayer.setMute(true);
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

        String lon = data.getData().getLon();
        String lat = data.getData().getLat();
        String address = data.getData().getAddress();


        proAddress.setText(address);

        if (TextUtils.isEmpty(lon) || TextUtils.isEmpty(lat)) {
            return;
        }

        if (!TextUtils.isEmpty(lon) && !TextUtils.isEmpty(lat) && dest != null) {
            double longitute = Double.parseDouble(lon);
            double latitude = Double.parseDouble(lat);

            if (longitute != 0 && latitude != 0) {

                point = new LatLonPoint(latitude, longitute);
                ArrayList<LatLonPoint> list = new ArrayList();
                list.add(point);


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


    // 设置工作室详情
    private void setDetailData(ServiceDetailBean data) {

        // Log.e(TAG, "工作室详情 = " + JSONObject.toJSONString(data));

        ServiceDetailBean.DataBean shopDetailData = data.getData();
        // 个人简介
        String remark = shopDetailData.getRemark();
        // 从业经历
        String job = shopDetailData.getJob();
        // 获得奖项
        String awards_memo = shopDetailData.getAwards_memo();
        // 毕业学校
        String university = shopDetailData.getUniversity();
        // 教育经历
        String education = shopDetailData.getEducation();
        // 专业
        String major = shopDetailData.getMajor();


        if (TextUtils.isEmpty(remark)) {
//            mzRemark.setText("暂无");
            llRemark.setVisibility(View.GONE);
        } else {
            llRemark.setVisibility(View.VISIBLE);
            mzRemark.setText(remark);
        }

        if (TextUtils.isEmpty(job)) {
//            mzJob.setText("暂无");
            llJob.setVisibility(View.GONE);

        } else {
            llJob.setVisibility(View.VISIBLE);
            mzJob.setText(job);
        }

        if (TextUtils.isEmpty(awards_memo)) {
//            mzAward.setText("暂无");
            llAward.setVisibility(View.GONE);
        } else {
            llAward.setVisibility(View.VISIBLE);
            mzAward.setText(awards_memo);
        }

        if (TextUtils.isEmpty(university)) {
//            mzSchool.setText("暂无");
            llSchool.setVisibility(View.GONE);
        } else {
            llSchool.setVisibility(View.VISIBLE);
            mzSchool.setText(university);
        }

        if (TextUtils.isEmpty(education)) {
//            mzEducation.setText("暂无");
            llEducation.setVisibility(View.GONE);

        } else {
            llEducation.setVisibility(View.VISIBLE);
            mzEducation.setText(education);
        }

        if (TextUtils.isEmpty(major)) {
//            mzMajor.setText("暂无");
            llMajor.setVisibility(View.GONE);
        } else {
            llMajor.setVisibility(View.VISIBLE);
            mzMajor.setText(major);
        }


        // 认证 ;
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
        if (mShareAction.getPlatform() == SHARE_MEDIA.WEIXIN_CIRCLE) {
            title = title + " | " + StringConstants.Slogan;
        }else{
            memo =StringConstants.Slogan;
        }
        web.setTitle(title);
        web.setThumb(new UMImage(_mActivity, image));
        web.setDescription(memo);
        mShareAction.withMedia(web);
        mShareAction.open(config);
    }

    private void share2() {
        GoodDetailModel.DataBean data = this.data.getData();
        String url = data.getShare_url();
        String title = data.getTitle();
        String details = data.getDetails();
        String image_default = data.getImage_default();

        ShareBean bean = new ShareBean(url, title, details, image_default);
        new ShareDialog(bean).show(getChildFragmentManager(), "1");
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
        String[] tabList = new String[]{"项目", "详情", "评论", "推荐"};

        for (String s : tabList) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }

        List<View> views = new ArrayList<>();
        views.add(llShopProject);
        views.add(llShopDetail);
        views.add(llShopComment);
        views.add(llShopRecommend);

        scrollView.setAnchorList(views);
        scrollView.setupWithTabLayout(tabLayout);


        // 这个本来是要做成渐变色的，现在后悔了不想做了。
        scrollView.setTranslationY(SizeUtils.getMeasuredHeight(toolBar) + statusBarHeight());
//        int bannerHeight = SizeUtils.getMeasuredHeight(rvList);

        scrollView.setOnScrollCallback(new TabWithScrollView.OnScrollCallback() {
            @Override
            public void onScrollCallback(int l, int t, int oldl, int oldt) {
//                setBgAlphaChange(t, bannerHeight);

            }
        });

    }


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


    // 评论请求
    private void getComment() {
        String caseId = data.getData().getCase_id();

//        List<MzShopCommentBean.DataBean.InfoBean> commentList = new ArrayList<>();
        commentRv.setLayoutManager(new LinearLayoutManager(_mActivity));

        ShopCommentAdapter<MzShopCommentBean.DataBean.InfoBean> adapter = new ShopCommentAdapter(null);
        commentRv.setAdapter(adapter);


        commentAll.setOnClickListener(v -> {
            Intent intent = new Intent(_mActivity, AllShopCommentActivity.class);
            intent.putExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID, caseId);
            intent.putExtra(MzConstant.KEY_COMMENT_SHARE, data);
            startActivity(intent);
        });

        // 更多评价 ;
        commentMore.setOnClickListener(v -> {
            Intent intent = new Intent(_mActivity, AllShopCommentActivity.class);
            intent.putExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID, caseId);
            intent.putExtra(MzConstant.KEY_COMMENT_SHARE, data);
            startActivity(intent);

        });

        MzNewApi.getComment(caseId, "1",
                new ResponseImpl<MzShopCommentBean>() {
                    @Override
                    public void onSuccess(MzShopCommentBean data) {

                        List<MzShopCommentBean.DataBean.InfoBean> infoBeanList = data.getData().getInfo();

                        // 标签 ;
                        List<MzShopCommentBean.DataBean.TagsBean> tagList = data.getData().getTags();
                        if (tagList != null && tagList.size() > 0) {


                            commentTabLayout.setAdapter(new TagAdapter<MzShopCommentBean.DataBean.TagsBean>(tagList) {
                                @Override
                                public View getView(FlowLayout parent, int position, MzShopCommentBean.DataBean.TagsBean tagsBean) {

                                    TextView root = (TextView) LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.mz_tag_comment_mini, parent, false);

                                    String tagText = tagsBean.getName() + "   " + tagsBean.getNums();
                                    root.setText(tagText);
                                    return root;
                                }
                            });
                        }


                        if (infoBeanList.size() == 0) {
                            commentNoView.setVisibility(View.VISIBLE);
                            commentMore.setVisibility(View.GONE);
                            commentAll.setClickable(false);
                        } else {

                            commentNoView.setVisibility(View.GONE);
                            commentMore.setVisibility(View.VISIBLE);

                            commentAll.setText("好评度 " + data.getData().getComment_rate());
                            commentAll.setClickable(true);


                            // 商品详情页要显示的评论 ;
                            List<MzShopCommentBean.DataBean.InfoBean> commentList;
                            if (infoBeanList.size() > 1) {
                                commentList = infoBeanList.subList(0, 2);
                            } else {
                                commentList = infoBeanList.subList(0, 1);

                            }

                            adapter.setNewData(commentList);
                        }
                    }

                }, MzShopCommentBean.class);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void choiceMap() {
        DialogView dialogView = DialogManager.getInstance().initView(getContext(), R.layout.mz_map_dialog, Gravity.BOTTOM);
        dialogView.show();


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapPackName = "";

                switch (v.getId()) {
                    case R.id.aMap:
                        mapPackName = MapManager.aMapPackageName;
                        break;
                    case R.id.baiduMap:
                        mapPackName = MapManager.baiduMapPackageName;
                        break;

                    case R.id.tentcentMap:
                        mapPackName = MapManager.tencentMapPackageName;
                        break;
                }

                MapManager.getInstance().jumpMap(_mActivity, mapPackName, point);
                dialogView.dismiss();
            }
        };


        dialogView.findViewById(R.id.aMap).setOnClickListener(listener);
        dialogView.findViewById(R.id.baiduMap).setOnClickListener(listener);
        dialogView.findViewById(R.id.tentcentMap).setOnClickListener(listener);


    }


    /**
     * 监听按键  ;
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        AudioManager am = (AudioManager) _mActivity.getSystemService(Service.AUDIO_SERVICE);

        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_DOWN:

                return true;

            case KeyEvent.KEYCODE_VOLUME_UP:


                return true;
        }
        return false;

    }

    // 项目详情图片 ;
    private void projectImage(String images) {

        String[] strings = MzStringUtil.splitComma(images);

        rvProjectDetail.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        ShopPicAdapter adapter = new ShopPicAdapter(null);

//        rvProjectDetail.setLayoutManager(new LinearLayoutManager(_mActivity));
//        ShopProjectPicAdapter adapter = new ShopProjectPicAdapter(null);

        rvProjectDetail.setAdapter(adapter);

        if (strings != null && strings.length > 0) {
            rvProjectDetail.setVisibility(View.VISIBLE);
            List<String> imgList = Arrays.asList(strings);
            adapter.setNewData(imgList);


            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    int width = ScreenUtil.getScreenWidth(_mActivity);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                    ImagePagerActivity.startImagePagerActivity(_mActivity, imgList, position, imageSize);

                }
            });

        } else {
            rvProjectDetail.setVisibility(View.GONE);
        }


    }


}
