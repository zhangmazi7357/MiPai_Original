package com.hym.eshoplib.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lib_amap.MapManager;
import com.hym.eshoplib.BuildConfig;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.adapter.FirstPagerShopItemAdapter;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.bean.city.RegionBean;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.CommentForMeBean;
import com.hym.eshoplib.bean.home.HomeDataBean;
import com.hym.eshoplib.bean.home.HomeVadieoListBean;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.bean.home.SystemMessageListBean;
import com.hym.eshoplib.bean.home.TipsMessageBean;
import com.hym.eshoplib.event.ShowGuideEvent;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.util.OnBottomPosCallback2;
import com.hym.eshoplib.util.OnTopPosCallbac3;
import com.hym.eshoplib.widgets.JzvdStdVolumeAfterFullscreen;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import app.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.event.lgoin.UnLoginEvent;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.GlideBanerImageLoader;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.utils.view.SystemBarUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import io.rong.imkit.RongIM;
import io.rong.pushperm.ResultCallback;
import io.rong.pushperm.RongPushPremissionsCheckHelper;
import utils.Rom;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.interfaces.HighLightInterface;
import zhy.com.highlight.position.OnBottomPosCallback;
import zhy.com.highlight.position.OnLeftPosCallback;
import zhy.com.highlight.position.OnRightPosCallback;
import zhy.com.highlight.position.OnTopPosCallback;
import zhy.com.highlight.shape.RectLightShape;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/6/22.
 * <p>
 * Description 京东模式tab
 * <p>
 * otherTips
 */

public class HomeFragmentJDStyle extends BaseKitFragment implements
        View.OnClickListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_left)
    TextView tvLeft;

    @BindView(R.id.et_search)
    ClearEditText etSearch;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_toolbar_right)
    ImageView ivToolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.view_head)
    View viewHead;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.fl_layout)
    FrameLayout flLayout;
    @BindView(R.id.ll_top_tab)
    LinearLayout llTopTab;
    @BindView(R.id.tv_comment)
    TextView tvComment;

    //    推荐
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;

    @BindView(R.id.tv_photo)
    TextView tvPhoto;
    @BindView(R.id.rl_rl_take_photo)
    RelativeLayout rlRlTakePhoto;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.rl_rl_take_video)
    RelativeLayout rlRlTakeVideo;
    @BindView(R.id.tv_comment_line)
    TextView tvCommentLine;
    @BindView(R.id.tv_photo_line)
    TextView tvPhotoLine;
    @BindView(R.id.tv_video_line)
    TextView tvVideoLine;
    private int mDistanceY;
    LinearLayout tvFunction1;
    LinearLayout tvFunction2;
    LinearLayout tvFunction3;
    LinearLayout tvFunction4;
    LinearLayout tvFunction5;
    LinearLayout tvFunction6;
    LinearLayout tvFunction7;
    LinearLayout tvFunction8;
    LinearLayout tvFunction9;
    LinearLayout tvFunction10;
    TextView tv_more_news, tv_more_vadieos;
    Banner banner;
    //MarqueeView newMarquee;
    MarqueeView simpleMarqueeView;
    LinearLayoutManager linearLayoutManager;
    public int firstVisibleItem, lastVisibleItem, visibleCount;
    BaseListAdapter<HomeVadieoListBean.DataBean.VideoBean> adapter;
    List<HomeDataBean.DataBean.BannerBean> bannerBeen;
    List<HomeDataBean.DataBean.AgencyBean> agencyBeen;
    FrameLayout fl_banner;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    String key = "first_guide";
    private FirstPagerShopItemAdapter firstPagerShopItemAdapter;
    private TextView etInput;
    private TextView tvForMe;
    private ArrayList<SpecialTimeLimteBean.DataBean> firstPagerShopBeans;

    private int tabSelect = 1;
    private static final int RLCOMMENT = 1;
    private static final int PHOTO = 2;
    private static final int VIDEO = 3;
    private RecyclerView rvFooter;
    private ShopListAdapter shopListAdapter;
    private List<SpecialTimeLimteBean.DataBean.VideoBean> rvFooterComment = new ArrayList<>();
    private List<SpecialTimeLimteBean.DataBean.VideoBean> rvFooterTakePhoto = new ArrayList<>();
    private List<SpecialTimeLimteBean.DataBean.VideoBean> rvFooterTakeVideo = new ArrayList<>();
    private int commentPage;
    private int takePhotoPage;
    private int videoPger;


    public static HomeFragmentJDStyle newInstance(Bundle args) {
        HomeFragmentJDStyle fragment = new HomeFragmentJDStyle();
        fragment.setArguments(args);
        return fragment;
    }

    boolean hasinit = false;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_home_jd;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public void doLogic() {

        SharePreferenceUtil.setBooleanData(_mActivity, "isauth", false);
        //设置状态栏高度
        int statusBarheight = SystemBarUtil.getSystemBarHeight(_mActivity);
        viewHead.getLayoutParams().height = statusBarheight;
        //设置adapter
        tvLeft.setText("定位中...");

        // 推荐、拍照片、拍视频
        initTopTab();

        // initrecyclerview();
        initRecycleview();

        setListeners();

        initMap();

        //引导设置推送权限
        if (!SharePreferenceUtil.getBooleangData(_mActivity, "isSet")) {
            RongPushPremissionsCheckHelper.checkPermissionsAndShowDialog(_mActivity, new ResultCallback() {
                @Override
                public void onAreadlyOpened(String value) {

                }

                @Override
                public boolean onBeforeShowDialog(String value) {
                    return false;
                }

                @Override
                public void onGoToSetting(String value) {

                }

                @Override
                public void onFailed(String value, FailedType type) {

                }
            });
            SharePreferenceUtil.setBooleanData(_mActivity, "isSet", true);
        }
    }

    private void initTopTab() {
        //      推荐
        rlComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSelect = RLCOMMENT;
                //clickComment(view);
                topComment();
                if (rvFooterComment.size() <= 1) {
                    initHomeComment(1);
                } else {
                    shopListAdapter.setNewData(rvFooterComment);
                    shopListAdapter.notifyDataSetChanged();
                }
                SpecialTimeLimteBean.DataBean dataBean1 = new SpecialTimeLimteBean.DataBean();
                dataBean1.setItemType(3);
                dataBean1.setSelected(1);
                firstPagerShopItemAdapter.setData(2, dataBean1);
                firstPagerShopItemAdapter.notifyItemChanged(2);
            }
        });
        // 拍照片
        rlRlTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSelect = PHOTO;
                //clickPhoto(view);
                topPhoto();
                if (rvFooterTakePhoto.size() <= 1) {
                    initHomeTakePhoto(1);
                } else {
                    shopListAdapter.setNewData(rvFooterTakePhoto);
                    shopListAdapter.notifyDataSetChanged();
                }
                SpecialTimeLimteBean.DataBean dataBean1 = new SpecialTimeLimteBean.DataBean();
                dataBean1.setItemType(3);
                dataBean1.setSelected(2);
                firstPagerShopItemAdapter.setData(2, dataBean1);
                firstPagerShopItemAdapter.notifyItemChanged(2);
            }
        });

        // 拍视频
        rlRlTakeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSelect = VIDEO;
                //clicikVideo(view);
                topVideo();
                if (rvFooterTakeVideo.size() < 1) {
                    initVideoData(1);
                } else {
                    shopListAdapter.setNewData(rvFooterTakeVideo);
                    shopListAdapter.notifyDataSetChanged();
                }
                SpecialTimeLimteBean.DataBean dataBean1 = new SpecialTimeLimteBean.DataBean();
                dataBean1.setItemType(3);
                dataBean1.setSelected(3);
                firstPagerShopItemAdapter.setData(2, dataBean1);
                firstPagerShopItemAdapter.notifyItemChanged(2);
            }
        });
    }

    private void topVideo() {
        tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvVideo.setTextColor(Color.parseColor("#FF5203"));
        tvVideoLine.setVisibility(View.VISIBLE);

        tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvPhoto.setTextColor(Color.parseColor("#292929"));
        tvPhotoLine.setVisibility(View.INVISIBLE);

        tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvComment.setTextColor(Color.parseColor("#292929"));
        tvCommentLine.setVisibility(View.INVISIBLE);
    }

    private void topPhoto() {
        tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvPhoto.setTextColor(Color.parseColor("#FF5203"));
        tvPhotoLine.setVisibility(View.VISIBLE);

        tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvVideo.setTextColor(Color.parseColor("#292929"));
        tvVideoLine.setVisibility(View.INVISIBLE);

        tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvComment.setTextColor(Color.parseColor("#292929"));
        tvCommentLine.setVisibility(View.INVISIBLE);
    }

    private void topComment() {
        tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvComment.setTextColor(Color.parseColor("#FF5203"));
        tvCommentLine.setVisibility(View.VISIBLE);

        tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvPhoto.setTextColor(Color.parseColor("#292929"));
        tvPhotoLine.setVisibility(View.INVISIBLE);

        tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvVideo.setTextColor(Color.parseColor("#292929"));
        tvVideoLine.setVisibility(View.INVISIBLE);
    }

    private void initRecycleview() {
        linearLayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(linearLayoutManager);
        firstPagerShopBeans = new ArrayList<>();

        SpecialTimeLimteBean.DataBean dataBean = new SpecialTimeLimteBean.DataBean();
        dataBean.setItemType(1);
        firstPagerShopBeans.add(dataBean);

        SpecialTimeLimteBean.DataBean dataBean1 = new SpecialTimeLimteBean.DataBean();
        dataBean1.setItemType(2);
        firstPagerShopBeans.add(dataBean1);

        firstPagerShopItemAdapter = new FirstPagerShopItemAdapter(firstPagerShopBeans);
        // 一大溜作为 headerView
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_home_function_btns, null, false);

        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.home_item_footer_view, null, false);
        findViews(header);
        initfooter(footer);

        firstPagerShopItemAdapter.addHeaderView(header);
        firstPagerShopItemAdapter.addFooterView(footer);

        rvList.setAdapter(firstPagerShopItemAdapter);
        banner.setIndicatorGravity(BannerConfig.CENTER);

        ScreenUtil.ViewAdapter(_mActivity, fl_banner, 16, 9);

        //首页多布局 Adapter 点击事件
        firstPagerShopItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String case_id = "";
                switch (view.getId()) {
                    case R.id.rl_top_click1:
                        case_id = firstPagerShopBeans.get(0).getVideo().get(0).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_top_click2:
                        case_id = firstPagerShopBeans.get(0).getVideo().get(1).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_top_click3:
                        case_id = firstPagerShopBeans.get(0).getVideo().get(2).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_top_click4:
                        case_id = firstPagerShopBeans.get(0).getVideo().get(3).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_top_click5:
                        case_id = firstPagerShopBeans.get(0).getVideo().get(4).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_bottom_click1:
                        case_id = firstPagerShopBeans.get(1).getVideo().get(0).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.rl_bottom_click2:
                        case_id = firstPagerShopBeans.get(1).getVideo().get(1).getCase_id();
                        shopDetail(case_id);
                        break;
                    case R.id.tv_tehui_odd_more:
                        SpecialTimeLimteBean.DataBean tehuiAddMore = firstPagerShopBeans.get(0);
                        Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.moreshop);
                        bundle.putSerializable("data", tehuiAddMore);
                        bundle.putSerializable("title", "限时特惠");
                        ActionActivity.start(_mActivity, bundle);
                        break;
                    case R.id.tv_strict_select_more:
                        SpecialTimeLimteBean.DataBean yanXuanAddMore = firstPagerShopBeans.get(1);
                        Bundle bundle1 = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.moreshop);
                        bundle1.putSerializable("data", yanXuanAddMore);
                        bundle1.putSerializable("title", "觅拍严选");
                        ActionActivity.start(_mActivity, bundle1);
                        break;
                    case R.id.rl_comment:
                        tabSelect = RLCOMMENT;
                        clickComment(view);
                        topComment();
                        if (rvFooterComment.size() <= 1) {
                            initHomeComment(1);
                        } else {
                            shopListAdapter.setNewData(rvFooterComment);
                            shopListAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.rl_rl_take_photo:
                        tabSelect = PHOTO;
                        clickPhoto(view);
                        topPhoto();
                        if (rvFooterTakePhoto.size() <= 1) {
                            initHomeTakePhoto(1);
                        } else {
                            shopListAdapter.setNewData(rvFooterTakePhoto);
                            shopListAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.rl_rl_take_video:
                        tabSelect = VIDEO;
                        clicikVideo(view);
                        topVideo();
                        if (rvFooterTakeVideo.size() < 1) {
                            initVideoData(1);
                        } else {
                            shopListAdapter.setNewData(rvFooterTakeVideo);
                            shopListAdapter.notifyDataSetChanged();
                        }
                        break;
                }
            }

        });
    }

    // 获取到商品详情然后跳转到商品详情页;
    private void shopDetail(String case_id) {

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


    private void SetTraslateToorble() {
        toolbar.setBackgroundColor(Color.argb(0, 255, 255, 255));
        viewHead.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.transparent));
        tvLeft.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
        ivToolbarRight.setImageResource(R.drawable.ic_gologin);
        flSearch.setBackgroundResource(R.drawable.shape_white_oval);
        ivSearch.setImageResource(R.drawable.ic_search);
        tvSearch.setHintTextColor(Color.parseColor("#a0a0a0"));
    }

    private void initfooter(View footer) {
        rvFooter = (RecyclerView) footer.findViewById(R.id.rv_footer);
        rvFooter.setLayoutManager(new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false));
        SpecialTimeLimteBean.DataBean.VideoBean videoBean = new SpecialTimeLimteBean.DataBean.VideoBean();
        rvFooterComment.add(videoBean);
        shopListAdapter = new ShopListAdapter(R.layout.item_shop, rvFooterComment);

        shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String case_id = "";
                if (tabSelect == 1) {
                    case_id = rvFooterComment.get(position).getCase_id();
                } else if (tabSelect == 2) {
                    case_id = rvFooterTakePhoto.get(position).getCase_id();
                } else if (tabSelect == 3) {
                    case_id = rvFooterTakeVideo.get(position).getCase_id();
                }

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
        rvFooter.setAdapter(shopListAdapter);
    }

    // 辣眼睛...
    private void clicikVideo(View view) {
        LinearLayout llayout2 = (LinearLayout) view.getParent();
        TextView tvComment2 = llayout2.findViewById(R.id.tv_comment);
        TextView tvPhoto2 = llayout2.findViewById(R.id.tv_photo);
        TextView tvVideo2 = llayout2.findViewById(R.id.tv_video);
        TextView tvCommentLine2 = llayout2.findViewById(R.id.tv_comment_line);
        TextView tvPhotoLine2 = llayout2.findViewById(R.id.tv_photo_line);
        TextView tvVideoLine2 = llayout2.findViewById(R.id.tv_video_line);

        tvVideo2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvVideo2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvVideo2.setTextColor(Color.parseColor("#FF5203"));
        tvVideoLine2.setVisibility(View.VISIBLE);

        tvPhoto2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvPhoto2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvPhoto2.setTextColor(Color.parseColor("#292929"));
        tvPhotoLine2.setVisibility(View.INVISIBLE);

        tvComment2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvComment2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvComment2.setTextColor(Color.parseColor("#292929"));
        tvCommentLine2.setVisibility(View.INVISIBLE);
    }

    private void clickPhoto(View view) {
        LinearLayout llayout1 = (LinearLayout) view.getParent();
        TextView tvComment1 = llayout1.findViewById(R.id.tv_comment);
        TextView tvPhoto1 = llayout1.findViewById(R.id.tv_photo);
        TextView tvVideo1 = llayout1.findViewById(R.id.tv_video);
        TextView tvCommentLine1 = llayout1.findViewById(R.id.tv_comment_line);
        TextView tvPhotoLine1 = llayout1.findViewById(R.id.tv_photo_line);
        TextView tvVideoLine1 = llayout1.findViewById(R.id.tv_video_line);

        tvPhoto1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvPhoto1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvPhoto1.setTextColor(Color.parseColor("#FF5203"));
        tvPhotoLine1.setVisibility(View.VISIBLE);

        tvVideo1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvVideo1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvVideo1.setTextColor(Color.parseColor("#292929"));
        tvVideoLine1.setVisibility(View.INVISIBLE);

        tvComment1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvComment1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvComment1.setTextColor(Color.parseColor("#292929"));
        tvCommentLine1.setVisibility(View.INVISIBLE);
    }

    private void clickComment(View view) {
        LinearLayout llayout = (LinearLayout) view.getParent();
        TextView tvComment = llayout.findViewById(R.id.tv_comment);
        TextView tvPhoto = llayout.findViewById(R.id.tv_photo);
        TextView tvVideo = llayout.findViewById(R.id.tv_video);
        TextView tvCommentLine = llayout.findViewById(R.id.tv_comment_line);
        TextView tvPhotoLine = llayout.findViewById(R.id.tv_photo_line);
        TextView tvVideoLine = llayout.findViewById(R.id.tv_video_line);

        tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        tvComment.setTextColor(Color.parseColor("#FF5203"));
        tvCommentLine.setVisibility(View.VISIBLE);

        tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvPhoto.setTextColor(Color.parseColor("#292929"));
        tvPhotoLine.setVisibility(View.INVISIBLE);

        tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        tvVideo.setTextColor(Color.parseColor("#292929"));
        tvVideoLine.setVisibility(View.INVISIBLE);
    }

    private void checkUpload(List<HomeDataBean.DataBean.AndroidVersionBean> data) {
        /**
         *  应用宝 https://a.app.qq.com/o/simple.jsp?pkgname=com.hym.eshoplib
         *  华为http://app.hicloud.com/app/C100483943
         *  小米http://app.mi.com/details?id=com.hym.eshoplib
         *  魅族http://app.meizu.com/apps/public/detail?package_name=com.hym.eshoplib
         */
        String url = "";
        //华为，小米，三星，vivo,oppo
        if (data == null) {
            return;
        }
        // 去各个市场下载更新应用;
        if (Rom.isEmui()) {
            //华为
            for (HomeDataBean.DataBean.AndroidVersionBean bean : data) {
                if (bean.getV_name().equals("华为") && Integer.parseInt(bean.getV_code()) > BuildConfig.VERSION_CODE) {
                    url = "http://app.hicloud.com/app/C100483943";
                    break;
                }
            }
            Logger.d("华为");
        } else if (Rom.isMiui()) {
            //小米
            for (HomeDataBean.DataBean.AndroidVersionBean bean : data) {
                if (bean.getV_name().equals("小米") && Integer.parseInt(bean.getV_code()) > BuildConfig.VERSION_CODE) {
                    url = ("http://app.mi.com/details?id=com.hym.eshoplib");
                    break;
                }
            }
            Logger.d("小米");
        } else if (Rom.isFlyme()) {
            //魅族
            for (HomeDataBean.DataBean.AndroidVersionBean bean : data) {
                if (bean.getV_name().equals("魅族") && Integer.parseInt(bean.getV_code()) > BuildConfig.VERSION_CODE) {
                    url = ("http://app.meizu.com/apps/public/detail?package_name=com.hym.eshoplib");
                    break;
                }
            }
            Logger.d("魅族");
        } else {
            //跳转应用宝
            for (HomeDataBean.DataBean.AndroidVersionBean bean : data) {
                if (bean.getV_name().equals("应用宝") && Integer.parseInt(bean.getV_code()) > BuildConfig.VERSION_CODE) {
                    url = ("https://a.app.qq.com/o/simple.jsp?pkgname=com.hym.eshoplib");
                    break;
                }
            }
        }


        if (!TextUtils.isEmpty(url)) {
            final Uri uri = Uri.parse(url);

            MipaiDialogUtil.showUpdateDialog(_mActivity, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    _mActivity.finish();

                }
            }, null);
        }
    }

    private void initMap() {
        mLocationClient = new AMapLocationClient(App.instance);
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        Logger.d("城市=" + amapLocation.getCity());
                        //tvLeft.setText(amapLocation.getCity());
                        final String city = amapLocation.getCity();

                        HomeApi.ChangeRegion(city, new ResponseImpl<RegionBean>() {
                            @Override
                            public void onSuccess(RegionBean data) {
                                SharePreferenceUtil.setStringData(_mActivity, "region_name", city);
                                SharePreferenceUtil.setStringData(_mActivity, "region_id", data.getData().getRegion_id());
                                tvLeft.setText(city);

                            }
                        }, RegionBean.class);

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Logger.d("location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        tvLeft.setText("定位失败...");
                    }
                }
                ;

            }
        };
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);


        //启动定位
        if (TextUtils.isEmpty(SharePreferenceUtil.getStringData(_mActivity, "region_name"))) {
            mLocationClient.startLocation();
        }


    }

    //banner 左下角有个 “世界广播” 跑马灯  。。。。。
    private void initMarqueen() {
        final List<String> datas = new ArrayList<>();
        HomeApi.GetAccept(new ResponseImpl<TipsMessageBean>() {
            @Override
            public void onSuccess(TipsMessageBean data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    datas.add(data.getData().get(i).getMemo());
                }
                simpleMarqueeView.startWithList(datas);
            }
        }, TipsMessageBean.class);
    }


    private void updateBanner() {
        if (banner == null || bannerBeen == null) {
            return;
        }
        ArrayList<String> data = new ArrayList<>();
        for (HomeDataBean.DataBean.BannerBean bean : bannerBeen) {
            data.add(bean.getFilepath());
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
              /*  Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopDetail);
                // bundle.putString("url", url);
                bundle.putString("title", "产品详情");
                ActionActivity.start(_mActivity, bundle);*/
               /* Intent intent = new Intent(_mActivity,ShopDetailAcitivty.class);
                startActivity(intent);*/
                String id = bannerBeen.get(position).getSpecification_id();
                String url = bannerBeen.get(position).getLink_url();
                String gotype = bannerBeen.get(position).getGo_type();
                HomeDataBean.DataBean.BannerBean.news_info news = bannerBeen.get(position).getNews_info();
                if (gotype.equals("2")) {
                    //跳转新闻
//                    Logger.d("news="+news);
                    HomeDataBean.DataBean.AgencyBean agnecy = new HomeDataBean.DataBean.AgencyBean();
                    agnecy.setIs_agree(news.getIs_agree());
                    agnecy.setAgency_id(news.getAgency_id());
                    agnecy.setAgree(news.getAgree());
                    agnecy.setCategory_id(news.getCategory_id());
                    agnecy.setCtime(news.getCtime());
                    agnecy.setFrom(news.getFrom());
                    agnecy.setImage(news.getImage());
                    agnecy.setImage_default(news.getImage_default());
                    agnecy.setTitle(news.getTitle());
                    agnecy.setMemo(news.getTitle());
                    agnecy.setUrl(news.getUrl());
                    Bundle bundle = getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news_detail);
                    bundle.putSerializable("data2", agnecy);
                    ActionActivity.start(_mActivity, bundle);
                } else if (gotype.equals("3")) {
                    //工作室
                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                    bundle.putString("id", id);
                    ActionActivity.start(_mActivity, bundle);
                } else if (gotype.equals("1")) {
                    //跳转外联
                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_web);
                    bundle.putString("url", url);
                    bundle.putString("title", bannerBeen.get(position).getTitle());
                    ActionActivity.start(_mActivity, bundle);
                } else if (gotype.equals("4")) {
                    shopDetail(id);
                } else if (gotype.equals("5")) {
                    SystemMessageListBean.DataBean.InfoBean bean = new SystemMessageListBean.DataBean.InfoBean();
                    bean.setUrl(bannerBeen.get(position).getUrl());
                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_system_messgae_detail);
                    bundle.putSerializable("data", bean);
                    ActionActivity.start(_mActivity, bundle);
                } else if (gotype.equals("6")) {
                    shopDetail(id);
                }
                //1链接、2新闻、3工作室主页，4：视频产品，5：平台消息，6：图片产品
            }
        });
        banner.setImageLoader(new GlideBanerImageLoader());
        banner.setImages(data);
        banner.start();
      /*  banner.isAutoPlay(true);
        banner.update(data);*/
    }

    private void setListeners() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData(null);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                switch (tabSelect) {
                    case RLCOMMENT:
                        initHomeComment(++commentPage);
                        break;
                    case PHOTO:
                        initHomeTakePhoto(++takePhotoPage);
                        break;
                    case VIDEO:
                        initVideoData(++videoPger);
                        break;
                }
            }
        });


        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_city));
            }
        });

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean scrollState = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: //滚动停止
                        scrollState = false;
                        //autoPlayVideo(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING: //手指拖动
                        scrollState = true;
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING: //惯性滚动
                        scrollState = true;
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                visibleCount = lastVisibleItem - firstVisibleItem;
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = toolbar.getBottom();
                View viewByPosition0 = linearLayoutManager.findViewByPosition(0);

                // 根据滑动位置 改变 toolBar
                if (viewByPosition0 != null) {
                    int top0 = viewByPosition0.getTop();

                    if (top0 + toolbarHeight >= 0) {
                        toolbar.setBackgroundColor(Color.argb(0, 255, 255, 255));
                        viewHead.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.transparent));
                        tvLeft.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                        tvLeft.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
                        ivToolbarRight.setImageResource(R.drawable.ic_gologin);
                        flSearch.setBackgroundResource(R.drawable.shape_white_oval);
                        ivSearch.setImageResource(R.drawable.ic_search);
                        tvSearch.setHintTextColor(Color.parseColor("#a0a0a0"));
                        Log.e("大于0", "大于0");
                        //refreshLayout.setEnabled(true);
                    } else {
                        Log.e("大于0", "小于0");
                        //滑动
                        toolbar.setBackgroundResource(R.color.white);
                        viewHead.setBackgroundResource(R.drawable.shape_toolbar_bg);
                        tvLeft.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                        tvLeft.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow_black, 0);
                        ivToolbarRight.setImageResource(R.drawable.ic_gologin_black);
                        flSearch.setBackgroundResource(R.drawable.shape_grayd8d8d8_oval);
                        ivSearch.setImageResource(R.drawable.ic_search_white);
                        tvSearch.setHintTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                    }
                }
                View viewByPosition = linearLayoutManager.findViewByPosition(3);

                if (viewByPosition != null) {
                    int top = viewByPosition.getTop();
                    if (toolbarHeight >= top) {
                        llTopTab.setVisibility(View.VISIBLE);
                    } else {
                        llTopTab.setVisibility(View.GONE);
                    }
                }
            }
        });


        ivToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                LoginMainActivity.start(_mActivity, bundle);
            }
        });
        tvFunction1.setOnClickListener(this);
        tvFunction2.setOnClickListener(this);
        tvFunction3.setOnClickListener(this);
        tvFunction4.setOnClickListener(this);
        tvFunction5.setOnClickListener(this);
        tvFunction6.setOnClickListener(this);
        tvFunction7.setOnClickListener(this);
        tvFunction8.setOnClickListener(this);
        tvFunction9.setOnClickListener(this);
        tvFunction10.setOnClickListener(this);

        tv_more_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新闻列表
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news));
            }
        });
        tv_more_vadieos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更多视频
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_more_vadieo));
            }
        });
        //搜索
        flSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_search));
            }
        });

        etInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_Need_search));
            }
        });

        tvForMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_Need_search));
            }
        });
    }

    com.sunsky.marqueeview.MarqueeView needsMarqueen;


    private void findViews(View header) {
        tvFunction1 = header.findViewById(R.id.ll_function_tab_1);
        tvFunction2 = header.findViewById(R.id.ll_function_tab_2);
        tvFunction3 = header.findViewById(R.id.ll_function_tab_3);
        tvFunction4 = header.findViewById(R.id.ll_function_tab_4);
        tvFunction5 = header.findViewById(R.id.ll_function_tab_5);
        tvFunction6 = header.findViewById(R.id.ll_function_tab_6);
        tvFunction7 = header.findViewById(R.id.ll_function_tab_7);
        tvFunction8 = header.findViewById(R.id.ll_function_tab_8);
        tvFunction9 = header.findViewById(R.id.ll_function_tab_9);
        tvFunction10 = header.findViewById(R.id.ll_function_tab_10);


        // setFunctionName();
        tv_more_news = header.findViewById(R.id.tv_more_news);
        tv_more_vadieos = header.findViewById(R.id.tv_more_vadieo);
        TextView tvNeedTitle = header.findViewById(R.id.tv_need_title);
        TextView tvIWhat = header.findViewById(R.id.tv_need_i_what);
        // newMarquee = header.findViewById(R.id.news_marqueen);
        banner = header.findViewById(R.id.banner);
        simpleMarqueeView = header.findViewById(R.id.simpleMarqueeView);
        fl_banner = header.findViewById(R.id.fl_banner);
        needsMarqueen = header.findViewById(R.id.upview1);
        etInput = header.findViewById(R.id.et_input);
        tvForMe = header.findViewById(R.id.tv_for_me);
        //tvNeedTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        tvIWhat.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        tvForMe.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     * 条数为奇数时做了些处理：奇数时最后一个没有内容 将第一个拼接到最后显示
     */
    private void setViewTwoLines() {

        if (agencyBeen == null) {
            return;
        }
        final List<String> data = new ArrayList<>();

        for (HomeDataBean.DataBean.AgencyBean bean : agencyBeen) {
            data.add(bean.getTitle() + "");
        }

        List<View> views = new ArrayList<>();
        views.clear();//记得加这句话，不然可能会产生重影现象

        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(_mActivity)
                    .inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = moreView.findViewById(R.id.tv1);
            TextView tv2 = moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news_detail);
                    bundle.putSerializable("data2", agencyBeen.get(position));
                    ActionActivity.startForResult(HomeFragmentJDStyle.this, bundle, 0x01);

                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.size() > position + 1) {
                        Bundle bundle = getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news_detail);
                        bundle.putSerializable("data2", agencyBeen.get(position + 1));
                        ActionActivity.startForResult(HomeFragmentJDStyle.this, bundle, 0x01);
                        // ToastUtil.toast(position + "你点击了" + data.get(position + 1).toString());
                    } else {
                        Bundle bundle = getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news_detail);
                        bundle.putSerializable("data2", agencyBeen.get(0));
                        ActionActivity.startForResult(HomeFragmentJDStyle.this, bundle, 0x01);
                        // ToastUtil.toast(position + "你点击了" + data.get(0).toString());

                    }
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {//奇数条
                tv2.setText(data.get(i + 1).toString());
            } else {//偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.setText(data.get(0).toString());
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        needsMarqueen.setViews(views);
    }


    int p = 1;

    public void getVideoList(final boolean refresh, final int pageNum) {

        if (SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeApi.GetVideo(pageNum + "", new ResponseImpl<HomeVadieoListBean>() {
                        @Override
                        public void onSuccess(HomeVadieoListBean data) {
                            int total = data.getData().getTotalpage();
                            if (refresh) {
                                p = HttpResultUtil.onRefreshSuccess(total, pageNum, data.getData().getVideo(), adapter);
                            } else {
                                p = HttpResultUtil.onLoardMoreSuccess(total, pageNum, data.getData().getVideo(), adapter);
                            }
                        }
                    }, HomeVadieoListBean.class);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeApi.GetVideo(pageNum + "", new ResponseImpl<HomeVadieoListBean>() {
                        @Override
                        public void onSuccess(HomeVadieoListBean data) {
                            int total = data.getData().getTotalpage();
                            if (refresh) {
                                p = HttpResultUtil.onRefreshSuccess(total, pageNum, data.getData().getVideo(), adapter);
                            } else {
                                p = HttpResultUtil.onLoardMoreSuccess(total, pageNum, data.getData().getVideo(), adapter);
                            }
                        }
                    }, HomeVadieoListBean.class);
                }
            }, 200);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        switch (tabSelect) {
            case RLCOMMENT:
                initHomeComment(++commentPage);
                break;
            case PHOTO:
                initHomeTakePhoto(++takePhotoPage);
                break;
            case VIDEO:
                initVideoData(++videoPger);
                break;
        }

        // getVadieoList(false, p);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        //这个...
        HomeApi.GetAdvert(new ResponseImpl<HomeDataBean>() {
            @Override
            public void onStart(int mark) {
                if (!hasinit) {
                    setShowProgressDialog(true);
                }
                super.onStart(mark);
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
            }

            @Override
            public void onSuccess(HomeDataBean data) {
                List<HomeDataBean.DataBean.AndroidVersionBean> bean = data.getData().getAndroid_version();
                Logger.d("版本更新=" + bean);
                Logger.d(BuildConfig.VERSION_CODE + "");
                if (bean != null) {
                    checkUpload(bean);
                }
                refreshLayout.finishRefresh(true);//传入false表示刷新失败
                //banner
                bannerBeen = data.getData().getBanner();

                updateBanner();
                //需求中心
                agencyBeen = data.getData().getAgency();

                // 跑马灯 搞两行
                setViewTwoLines();
                //initnewsMarqueen();
                //限时特惠
                initSpecialTimeLimteData();
                if (tabSelect == 1) {
                    initHomeComment(1);
                } else if (tabSelect == 2) {
                    initHomeTakePhoto(1);
                } else if (tabSelect == 3) {
                    initVideoData(1);
                }
                //为我推荐
                initCommentForMe();

                // Banner 左下角的跑马灯 ;
                initMarqueen();
                // 显示需要引导的高亮布局
                checkFirst(4);
                hasinit = true;
                //获取产品
                p = 1;
                //  getVideoList(true, p);

            }

            @Override
            public void onDataError(String status, String errorMessage) {
                super.onDataError(status, errorMessage);
                if (refreshLayout == null) {
                    return;
                }

                refreshLayout.finishRefresh(false);     //传入false表示刷新失败
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                if (refreshLayout == null) {
                    return;
                }
                refreshLayout.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onFailed(Exception e) {
                super.onFailed(e);
                if (refreshLayout == null) {
                    return;
                }
                refreshLayout.finishRefresh(true);//传入false表示刷新失败
            }
        }, HomeDataBean.class);
    }

    // 首页推荐
    private void initHomeComment(final int page) {
        commentPage = page;
        HomeApi.getHomeCommentData(page, new ResponseImpl<SpecialTimeLimteBean>() {
            @Override
            public void onSuccess(SpecialTimeLimteBean data) {
                SpecialTimeLimteBean.DataBean data1 = data.getData();
                List<SpecialTimeLimteBean.DataBean.VideoBean> video = data1.getVideo();
                if (page == 1) {
                    if (rvFooterComment.size() > 0) {
                        rvFooterComment.clear();
                    }
                    rvFooterComment.addAll(video);
                    shopListAdapter.setNewData(video);
                    shopListAdapter.notifyDataSetChanged();
                } else {
                    rvFooterComment.addAll(video);
                    shopListAdapter.addData(video);
                    shopListAdapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore(true);
                }
            }
        }, SpecialTimeLimteBean.class);
    }

    // 首页拍照片
    private void initHomeTakePhoto(int page) {
        takePhotoPage = page;
        HomeApi.getTakePhotoData(page, new ResponseImpl<SpecialTimeLimteBean>() {
            @Override
            public void onSuccess(SpecialTimeLimteBean data) {
                SpecialTimeLimteBean.DataBean data1 = data.getData();
                List<SpecialTimeLimteBean.DataBean.VideoBean> video = data1.getVideo();
                if (takePhotoPage == 1) {
                    if (rvFooterTakePhoto.size() > 0) {
                        rvFooterTakePhoto.clear();
                    }
                    rvFooterTakePhoto.addAll(video);
                    shopListAdapter.setNewData(video);
                    shopListAdapter.notifyDataSetChanged();
                } else {
                    rvFooterTakePhoto.addAll(video);
                    shopListAdapter.addData(video);
                    shopListAdapter.notifyDataSetChanged();
                    // firstPagerShopItemAdapter.loadMoreComplete();
                    refreshLayout.finishLoadMore(true);
                    shopListAdapter.loadMoreComplete();
                }
            }
        }, SpecialTimeLimteBean.class);
    }

    // 首页拍视频
    private void initVideoData(final int page) {
        videoPger = page;
        HomeApi.getTakeVedioData(page, new ResponseImpl<SpecialTimeLimteBean>() {
            @Override
            public void onSuccess(SpecialTimeLimteBean data) {
                SpecialTimeLimteBean.DataBean data1 = data.getData();
                List<SpecialTimeLimteBean.DataBean.VideoBean> video = data1.getVideo();
                if (page == 1) {
                    if (rvFooterTakeVideo.size() > 0) {
                        rvFooterTakeVideo.clear();
                    }
                    rvFooterTakeVideo.addAll(video);
                    shopListAdapter.setNewData(video);
                    shopListAdapter.notifyDataSetChanged();
                } else {
                    rvFooterTakeVideo.addAll(video);
                    shopListAdapter.addData(video);
                    shopListAdapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore(true);
                    // shopListAdapter.loadMoreComplete();
                   /* if (video.size() > 0){
                        firstPagerShopItemAdapter.loadMoreComplete();
                    }else{
                        firstPagerShopItemAdapter.loadMoreEnd();
                    }*/
                }
            }
        }, SpecialTimeLimteBean.class);
    }

    // 觅拍严选
    private void initSelectData() {
        HomeApi.getStrictSelectData(1 + "", new ResponseImpl<SpecialTimeLimteBean>() {

            @Override
            public void onSuccess(SpecialTimeLimteBean data) {

                SpecialTimeLimteBean.DataBean data1 = data.getData();
                data1.setItemType(2);
                firstPagerShopBeans.add(data1);

                SpecialTimeLimteBean.DataBean dataBean = new SpecialTimeLimteBean.DataBean();
                dataBean.setSelected(tabSelect);
                dataBean.setItemType(3);
                firstPagerShopBeans.add(dataBean);

                firstPagerShopItemAdapter.setNewData(firstPagerShopBeans);
                firstPagerShopItemAdapter.notifyDataSetChanged();
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

    // 中间部分 为我推荐
    private void initCommentForMe() {
        GoodsApi.getCommentForMeData(new ResponseImpl<CommentForMeBean>() {
            @Override
            public void onSuccess(CommentForMeBean data) {
                //commentForMeData = data;
                String word = "";
                List<String> hotword = data.getData().getHotword();
                if (hotword.size() > 0) {
                    for (int i = 0; i < hotword.size(); i++) {
                        word += hotword.get(i) + " ";
                    }
                }
                etInput.setText(word);
            }

            @Override
            public void onDataError(String status, String errormessage) {
                super.onDataError(status, errormessage);
            }

            @Override
            public void onFailed(Exception e) {
                super.onFailed(e);

            }
        }, CommentForMeBean.class);
    }

    // 限时特惠
    private void initSpecialTimeLimteData() {
        HomeApi.getSpecialTimeLimteData(1 + "", new ResponseImpl<SpecialTimeLimteBean>() {
            @Override
            public void onSuccess(SpecialTimeLimteBean data) {
                if (firstPagerShopBeans.size() > 0) {
                    firstPagerShopBeans.clear();
                }
                SpecialTimeLimteBean.DataBean data1 = data.getData();
                data1.setItemType(1);
                firstPagerShopBeans.add(data1);
                initSelectData();
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

    // 显示引导页   使用布局高亮 ;
    private void checkFirst(int guide) {
        /**
         *  1.广告片+diy既     s1=1   s2=2   引导 = 文案策划+导演+摄像师+剪辑师+演员/模特+化妆师    guide=1
         *  2.宣传片+diy 既     s1=2  s2=2   引导 = 导演+摄像师+剪辑师+演员/模特+化妆师           guide=2
         *  3.电商视频+diy 既  s1=3  s2=2   引导 = 摄像师+剪辑师+演员/模特+化妆师                guide=3
         *  4.个人视频+diy 既  s1=4  s2=2   引导 = 摄像师+剪辑师+化妆师                         guide=4
         *  5,视频团队                                                                   guide=5
         *  6.第一个跳过第二个算则diy s1=0 s2=2 引导=导演+摄像师+剪辑师                         guide=6
         *  0.不引导                                                                     guide=0
         *
         *
         */
        //如果是第一次进入页面判断是否需要引导
        //SharePreferenceUtil.setBooleanData(_mActivity, key, false);
        if (!SharePreferenceUtil.getBooleangData(_mActivity, key, false)) {
            //判断引导内容
            // int guide=getArguments().getInt("guide",0);
            switch (guide) {
                case 0:

                    //Logger.d("不引导");
                    break;
                case 1:
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_1, R.layout.layout_empty_guide, new OnLeftPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_2, R.layout.layout_empty_guide, new OnLeftPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_3, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_4, R.layout.layout_empty_guide, new OnTopPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_9, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_10, R.layout.layout_diy_guide, new OnLeftPosCallback(75), new RectLightShape(0, 0, 0, 0, 0));
                    mHightLight.show();
                    Logger.d("广告片+diy");
                    break;
                case 2:
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_2, R.layout.layout_empty_guide, new OnLeftPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_3, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_4, R.layout.layout_empty_guide, new OnTopPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_9, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_10, R.layout.layout_diy_guide, new OnLeftPosCallback(75), new RectLightShape(0, 0, 0, 0, 0));
                    mHightLight.show();

                    Logger.d("宣传片+diy");
                    break;
                case 3:
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_3, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_4, R.layout.layout_empty_guide, new OnTopPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_9, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_10, R.layout.layout_diy_guide, new OnLeftPosCallback(75), new RectLightShape(0, 0, 0, 0, 0));
                    mHightLight.show();

                    Logger.d("电商视频+diy");
                    break;
                case 4:
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_2, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_3, R.layout.layout_empty_guide, new OnBottomPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_4, R.layout.layout_diy_guide, new OnBottomPosCallback2(0), new RectLightShape(0, 0, 0, 0, 0))
                            //.addHighLight(R.id.tv_function_10,R.layout.layout_diy_guide,new OnLeftPosCallback(75),new RectLightShape(0,0,0,0,0))
                            .setClickCallback(new HighLightInterface.OnClickCallback() {
                                @Override
                                public void onClick() {
                                    if (mHightLight != null) {
                                        mHightLight.remove();
                                    }
                                    mHightLight2 = new HighLight(_mActivity)//
                                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                                            .addHighLight(R.id.ll_function_tab_5, R.layout.layout_group_guide, new OnRightPosCallback(10), new RectLightShape(0, 0, 0, 0, 0)).setClickCallback(new HighLightInterface.OnClickCallback() {
                                                @Override
                                                public void onClick() {
                                                    if (mHightLight2 != null) {
                                                        mHightLight2.remove();
                                                    }
                                                    mHightLight2 = new HighLight(_mActivity)//
                                                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                                                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                                                            .addHighLight(R.id.ll_input, R.layout.layout_need_guide, new OnTopPosCallbac3(), new RectLightShape(0, 0, 0, 0, 0)).setClickCallback(new HighLightInterface.OnClickCallback() {
                                                                @Override
                                                                public void onClick() {
                                                                    if (mHightLight2 != null) {
                                                                        mHightLight2.remove();
                                                                    }
                                                                    EventBus.getDefault().post(new ShowGuideEvent());
                                                                }
                                                            }).show();
                                                }
                                            }).show();

                                }
                            });
                    mHightLight.show();
                    Logger.d("个人视频+diy");
                    break;
                case 5:
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_5, R.layout.layout_group_guide, new OnRightPosCallback(10), new RectLightShape(0, 0, 0, 0, 0));
                    mHightLight.show();
                    Logger.d("选择团队直接 对应视频团队");
                    break;
                case 6:
                    Logger.d("第一个跳过第二个diy");
                    mHightLight = new HighLight(_mActivity)//
                            .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                            .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                            .addHighLight(R.id.ll_function_tab_2, R.layout.layout_empty_guide, new OnLeftPosCallback(0), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_3, R.layout.layout_empty_guide, new OnBottomPosCallback(), new RectLightShape(0, 0, 0, 0, 0))
                            .addHighLight(R.id.ll_function_tab_4, R.layout.layout_diy_guide, new OnBottomPosCallback(), new RectLightShape(0, 0, 0, 0, 0));
                    mHightLight.show();
                    break;
            }
            //引导出现后，则设置第一次启动完毕，同时不再出现
            SharePreferenceUtil.setBooleanData(_mActivity, key, true);
        } else {
            if (!hasinit) {
                EventBus.getDefault().post(new ShowGuideEvent());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        // 布局中间部分 tab 默认选择第一个 “推荐”
        tabSelect = 1;
        return rootView;
    }


    private HighLight mHightLight, mHightLight2;

    @Override
    public void onClick(View v) {

        // 布局靠上网格布局中的 职位;
        int type = 1;
        switch (v.getId()) {
            case R.id.ll_function_tab_1:
                type = 1;
                break;
            case R.id.ll_function_tab_2:
                type = 8;
                break;
            case R.id.ll_function_tab_3:
                type = 3;
                break;
            case R.id.ll_function_tab_4:
                type = 4;
                break;
            case R.id.ll_function_tab_5:
                type = 5;
                break;
            case R.id.ll_function_tab_6:
                type = 6;
                break;
            case R.id.ll_function_tab_7:
                type = 7;
                break;
            case R.id.ll_function_tab_8:
                type = 2;
                break;
            case R.id.ll_function_tab_9:
                //演员
                type = 46;
                break;
            case R.id.ll_function_tab_10:
                //化妆师
                type = 40;
                break;
        }

        Bundle bundle = getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopList);
        bundle.putInt("type", type);
        ActionActivity.start(_mActivity, bundle);

    }

    @Override
    public boolean onBackPressedSupport() {
        if (JzvdStd.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    // 自动播放视频
    private void autoPlayVideo(RecyclerView view) {
        if (!JCUtils.isWifiConnected(getContext()) || SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true) == false) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.ll_vedieo_contailnner) != null) {
                JzvdStdVolumeAfterFullscreen videoPlayerStandard1 = (JzvdStdVolumeAfterFullscreen) view.getChildAt(i).findViewById(R.id.videoplayer);
                LinearLayout linearLayout = view.getChildAt(i).findViewById(R.id.ll_vedieo_contailnner);
                Rect rect = new Rect();
                linearLayout.getLocalVisibleRect(rect);
                int videoheight3 = linearLayout.getHeight();
                //当前播放器能完全显示
                if (rect.top == 0 && rect.bottom == videoheight3) {
                    if (videoPlayerStandard1.currentState == Jzvd.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == Jzvd.CURRENT_STATE_ERROR) {
                        //调用开始播放的按钮
                        videoPlayerStandard1.startButton.performClick();

                    }
                    return;
                }

            }
//            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.videoplayer) != null) {
//                JzvdStdVolumeAfterFullscreen videoPlayerStandard1 = (JzvdStdVolumeAfterFullscreen) view.getChildAt(i).findViewById(R.id.videoplayer);
//                Rect rect = new Rect();
//                videoPlayerStandard1.getLocalVisibleRect(rect);
//                int videoheight3 = videoPlayerStandard1.getHeight();
//                //当前播放器能完全显示
//                if (rect.top == 0 && rect.bottom == videoheight3) {
//                    if (videoPlayerStandard1.currentState == Jzvd.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == Jzvd.CURRENT_STATE_ERROR) {
//                        //调用开始播放的按钮
//                        videoPlayerStandard1.startButton.performClick();
//
//                    }
//                    return;
//                }
//
//            }

        }
        Jzvd.releaseAllVideos();

    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        String region_name = SharePreferenceUtil.getStringData(_mActivity, "region_name");
        tvLeft.setText(TextUtils.isEmpty(region_name) ? "北京市" : region_name);
        //Logger.d("islogin=");
        if (!UserUtil.getIsLogin(_mActivity)) {
            ivToolbarRight.setVisibility(View.VISIBLE);
        } else {
            ivToolbarRight.setVisibility(View.INVISIBLE);

        }

        simpleMarqueeView.startFlipping();

        needsMarqueen.startFlipping();


    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        Jzvd.releaseAllVideos();

        simpleMarqueeView.stopFlipping();
        // newMarquee.stopFlipping();
        needsMarqueen.stopFlipping();
    }

    public void scrollTotop() {
        if (rvList != null) {
            rvList.smoothScrollToPosition(0);
        }

    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(UnLoginEvent event) {
        //用户未登录，或者登录失效，进入登录activity
        //UserUtil.saveToken(_mActivity, "");
        RongIM.getInstance().logout();
        UserUtil.setIsLogin(_mActivity, false);
        Bundle bundle = new Bundle();
        bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
        bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
        LoginMainActivity.start(_mActivity, bundle);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataNews(UpdateDataEvent event) {
        if (event != null && event.type == 5) {
            //更新新闻 改变点赞状态
            HomeApi.getHomeData(new ResponseImpl<HomeDataBean>() {
                @Override
                public void onSuccess(HomeDataBean data) {
                    bannerBeen = data.getData().getBanner();
                    agencyBeen = data.getData().getAgency();

                }

            }, HomeDataBean.class);

        }
        if (event != null && event.type == 6) {
            //更新新闻更新视频
            initData(null);

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLogin(LoginEvent event) {
        ivToolbarRight.setVisibility(View.INVISIBLE);

        HomeApi.getHomeData(new ResponseImpl<HomeDataBean>() {

            @Override
            public void onSuccess(HomeDataBean data) {
                agencyBeen = data.getData().getAgency();

            }

        }, HomeDataBean.class);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}

