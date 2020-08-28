package com.hym.eshoplib.fragment.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
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
import com.hjq.toast.ToastUtils;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.CreateOrderBean;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.bean.shop.AttachResultBean;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.bean.shop.ShopCommentsBean;
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
import com.hym.eshoplib.mz.iconproduct.HomeIconProductBean;
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
import cn.hym.superlib.mz.utils.MzStringUtil;
import cn.hym.superlib.mz.utils.SizeUtils;
import cn.hym.superlib.mz.widgets.MzShopDetailTitleView;
import cn.hym.superlib.mz.widgets.TabWithScrollView;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.DialogView;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 产品详情 - 图片 ;
 */
public class ShopDetailsImageFragment extends BaseKitFragment implements
        View.OnClickListener {


    private String TAG = "ShopDetailsImageFragment";

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

    @BindView(R.id.rl_right)
    RelativeLayout rlRight;


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


    // 地址

    @BindView(R.id.l_address)
    LinearLayout lAddress;

    @BindView(R.id.proAddress)
    TextView proAddress;

    // 距离
    @BindView(R.id.proDistance)
    TextView proDistance;

    @BindView(R.id.tv_add_shoppingcart)
    TextView tvAddShoppingcart;

    @BindView(R.id.tv_go_pay)
    TextView tvGoPay;


    private LatLonPoint point;


    //////////////////////// 工作室详情  ///////////////////

    //   个人简介;
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

    @BindView(R.id.tv_renzheng_1)
    TextView mzRenzheng1;      // 获得奖项 认证

    @BindView(R.id.tv_renzheng_2)
    TextView mzRenzheng2;        // 毕业院校认证;

    // 性别 ;
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


    // 页面改动 -- 滑动页面切换tab
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

    // 项目
    @BindView(R.id.ll_shop_project)
    LinearLayout llShopProject;

    // 评论
    @BindView(R.id.ll_shop_comment)
    LinearLayout llShopComment;

    @BindView(R.id.commentAll)
    TextView commentAll;

    @BindView(R.id.commentRv)
    RecyclerView commentRv;

    @BindView(R.id.commentNoView)
    TextView commentNoView;

    @BindView(R.id.commentMore)
    Button commentMore;


    // 项目详情
    @BindView(R.id.ll_shop_detail)
    LinearLayout llShopDetail;


    // 工作室详情 ;
    @BindView(R.id.ll_shop_container)
    LinearLayout llShopContainer;


    // 彩蛋 ;
    @BindView(R.id.colorEgg)
    MzShopDetailTitleView colorEgg;

    // caseId
    private String colorEgg_caseID = "";

    // 推荐商品
    @BindView(R.id.ll_shop_recommend)
    LinearLayout llShopRecommend;

    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Unbinder unbinder;
    private GoodDetailModel.DataBean db;
    private GoodDetailModel data;               // 商品详情  --- 吧 ;
    private BaseListAdapter<String> adapter;
    private int select_position;
    private String name;
    private int count = 1;
    private SnappingStepper stepper;
    private ServiceDetailBean mData;
    private String category_id;
    private List<ServiceDetailBean.DataBean.CateListBean> cate_list;
    private BaseListAdapter<ServiceDetailBean.DataBean.CateListBean> adapter1;


    //  产品列表 传递过来的 摄影棚位置坐标点
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

        showBackButton();
        setTitle(getArguments().getString("title"));

        initScrollAndTab();

        //定位当前位置;
        MapManager.getInstance().location(_mActivity, true, new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
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

        tvReport.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvReport.getPaint().setAntiAlias(true);//抗锯齿
        tvBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


        rvRecommendGoods.setNestedScrollingEnabled(false);
        rvRecommendGoods.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        tvReport.setOnClickListener(this);
        rlClickWorkhome.setOnClickListener(this);
        rlContactHim.setOnClickListener(this);
        rlCallPhone.setOnClickListener(this);
        rlCollection.setOnClickListener(this);
        tvAddShoppingcart.setOnClickListener(this);
        tvGoPay.setOnClickListener(this);

        shopBack.setOnClickListener(this);
        shopShare.setOnClickListener(this);

        // 点击地址去导航;
        lAddress.setOnClickListener(this);

        // 彩蛋   -- 添加评论 ;
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
//                    ToastUtil.toast("caseId 为空 ");
                }

                return false;
            }
        });

    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        data = (GoodDetailModel) bundle.getSerializable("data");


        db = data.getData();
        String presentPrice = RemoveZeroUtil.subZeroAndDot(db.getPresent_price());
        tvTotalPrice.setText(presentPrice);

        colorEgg_caseID = db.getCase_id();

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
        if (!TextUtils.isEmpty(store_rank)) {
            float v = Float.parseFloat(store_rank);
            ratingbar.setRating(v);
        }


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

        // 工作室详情 ;
        ShopApi.GetContentDetail(db.getContent_id(), new ResponseImpl<ServiceDetailBean>() {
            @Override
            public void onSuccess(ServiceDetailBean data) {


                mData = data;
                category_id = data.getData().getCategory_id();
                cate_list = data.getData().getCate_list();

                // 盲猜模特 ;
                if (category_id.equals("46")) {
                    rlCallPhone.setVisibility(View.GONE);

                    setCategoryId46(data);
                }
                setDetailData(data);

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


        // 评论
        getComment();


        // 推荐 ;  2 - 图片
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
                                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                            ActionActivity.ShopVideoDetail);
                                    bundle.putSerializable("data", data);
                                    bundle.putString("title", "产品详情");
                                    ActionActivity.start(_mActivity, bundle);
                                } else if (data.getData().getType().equals("2")) {
                                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                            ActionActivity.ShopDetail);
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


    }


    @Override
    public boolean showToolBar() {
        return false;
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
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1,
                    "加入购物车", new View.OnClickListener() {
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
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1,
                    "", null, "加入购物车",
                    new View.OnClickListener() {
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
                    },
                    true);

        } else {

            //立即购买
            MipaiDialogUtil.showSpetificDialog(_mActivity, "", adapter1,
                    "", null, "立即预约",
                    new View.OnClickListener() {
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


                            ShopApi.CreateDetailOrder(_mActivity, db.getContent_id(),
                                    String.valueOf(count), db.getCase_id(),
                                    new ResponseImpl<CreateOrderBean>() {
                                        @Override
                                        public void onSuccess(CreateOrderBean data) {
                                            //付款
                                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,
                                                    EshopActionActivity.Action_order_order_pay);

                                            bundle.putString("id", data.getData().getOrder_number());
                                            bundle.putString("needPay", data.getData().getMoney() + "");
                                            bundle.putString("id2", data.getData().getLog_id());
                                            EshopActionActivity.start(_mActivity, bundle);

                                        }
                                    }, CreateOrderBean.class);
                        }
                    },
                    true);

        }
    }

    // 付款的回调监听 ;
    private GoToPayDialogInterface listener = new GoToPayDialogInterface() {
        @Override
        public void getCoutn(int count) {


            ShopApi.CreateDetailOrder(_mActivity,
                    db.getContent_id(),
                    count + "", db.getCase_id(),

                    new ResponseImpl<CreateOrderBean>() {
                        @Override
                        public void onSuccess(CreateOrderBean data) {

                            //付款
                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,
                                    EshopActionActivity.Action_order_order_pay);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_shoppingcart:           // 加入购物车，现在是隐藏状态
                showSelectTypeDialog(1);
                break;
            case R.id.tv_go_pay:                     // 付款

                if (db == null) {
                    ToastUtils.show("数据异常,请稍后再试");
                }


                // 提交订单
                MipaiDialogUtil.showGoToPayDialog(_mActivity,
                        db.getPresent_price(),
                        db.getTitle(),
                        listener);


                break;

            case R.id.tv_report:                    // 举报

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
            case R.id.rl_click_workhome:            // 进入工作室页面 ；

                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,
                        ActionActivity.Action_ShopDetail);
                bundle.putString("id", db.getContent_id());
                ActionActivity.start(_mActivity, bundle);

                break;

            case R.id.rl_contact_him:                 // 联系客服
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

            case R.id.rl_call_phone:                  // 打电话
                if (!TextUtils.isEmpty(data.getData().getTel())) {
                    Intent Intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + data.getData().getTel()));
                    startActivity(Intent);
                }
                break;

            case R.id.rl_collection:                    //收藏按钮 ;

                ShopApi.AddFavorite(data.getData().getContent_id(), "case",
                        new ResponseImpl<AddFavouriteBean>() {
                            @Override
                            public void onSuccess(AddFavouriteBean data) {

                                if (data.getData().getStatus() == 1) {
                                    ivB03.setBackgroundResource(R.mipmap.icon_collection_light);
                                    ToastUtils.show("收藏");
                                } else {
                                    ivB03.setBackgroundResource(R.mipmap.icon_collection);
                                    ToastUtils.show("取消收藏");
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

            case R.id.l_address:
                // 定位不成功  或者 没有计算出距离 ;
                if (TextUtils.isEmpty(proAddress.getText()) || TextUtils.isEmpty(proDistance.getText())) {
                    return;
                }
                choiceMap();
                break;
        }
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


    // 计算距离
    private void addAddressDistance() {

        GoodDetailModel.DataBean item = this.data.getData();


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

    // 工作室详情
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


        // 如果 都没有 隐藏这玩意
//        if (TextUtils.isEmpty(remark)
//                && TextUtils.isEmpty(job)
//                && TextUtils.isEmpty(awards_memo)
//                && TextUtils.isEmpty(university)
//                && TextUtils.isEmpty(education)
//                && TextUtils.isEmpty(major)) {
//
//            llShopContainer.setVisibility(View.GONE);
//        } else {
//            llShopContainer.setVisibility(View.VISIBLE);
//        }


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


    //  category_id  == 46   模特演员
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

        scrollView.setTranslationY(SizeUtils.getMeasuredHeight(toolBar) + statusBarHeight());
        int bannerHeight = SizeUtils.getMeasuredHeight(rvList);

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


    // 评论请求
    private void getComment() {
        String caseId = data.getData().getCase_id();

        commentRv.setLayoutManager(new LinearLayoutManager(_mActivity));

        ShopCommentAdapter adapter = new ShopCommentAdapter(null);
        commentRv.setAdapter(adapter);

        // 查看全部评论
        commentAll.setOnClickListener(v -> {
            Intent intent = new Intent(_mActivity, AllShopCommentActivity.class);
            intent.putExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID, caseId);
            intent.putExtra(MzConstant.KEY_COMMENT_SHARE, data);
            startActivity(intent);
        });

        // 更多评价 ;
        commentMore.setOnClickListener(v -> {
            Intent intent = new Intent(_mActivity, AllShopCommentActivity.class);
            intent.putExtra(MzConstant.KEY_COMMENT_SHARE, data);
            intent.putExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID, caseId);
            startActivity(intent);

        });

        // 商品评论
        MzNewApi.getComment(caseId, "1",
                new ResponseImpl<MzShopCommentBean>() {
                    @Override
                    public void onSuccess(MzShopCommentBean data) {

                        // 评论列表
                        List<MzShopCommentBean.DataBean.InfoBean> infoBeanList = data.getData().getInfo();

                        // 标签 ;
                        List<MzShopCommentBean.DataBean.TagsBean> tagList = data.getData().getTags();

                        // TODO  这里的标签要不要展示c

                        if (infoBeanList.size() == 0) {
                            commentNoView.setVisibility(View.VISIBLE);
                            commentMore.setVisibility(View.GONE);
                            commentAll.setClickable(false);

                        } else {

                            commentNoView.setVisibility(View.GONE);
                            commentMore.setVisibility(View.VISIBLE);

                            commentAll.setText("好评度 " + data.getData().getComment_rate());
                            commentAll.setClickable(true);

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


    //选择地图
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

                if (point != null) {
                    MapManager.getInstance().jumpMap(_mActivity, mapPackName, point);
                    dialogView.dismiss();
                }
            }
        };


        dialogView.findViewById(R.id.aMap).setOnClickListener(listener);
        dialogView.findViewById(R.id.baiduMap).setOnClickListener(listener);
        dialogView.findViewById(R.id.tentcentMap).setOnClickListener(listener);


    }

}
