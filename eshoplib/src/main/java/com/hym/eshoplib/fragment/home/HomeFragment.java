package com.hym.eshoplib.fragment.home;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.VideoConstant;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodsDetailBean;
import com.hym.eshoplib.listener.AppBarStateChangeListener;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.view.GlideBanerImageLoader;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/6/22.
 * <p>
 * Description 首页
 * <p>
 * otherTips
 */
@Deprecated
public class HomeFragment extends BaseKitFragment implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {


    Unbinder unbinder;
    BaseListAdapter<String> adapter;
    TextView tvFunction1;
    TextView tvFunction2;
    TextView tvFunction3;
    TextView tvFunction4;
    TextView tvFunction5;
    TextView tvFunction6;
    TextView tvFunction7;
    TextView tvFunction8;
    public int firstVisibleItem, lastVisibleItem, visibleCount;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    //    @BindView(R.id.easylayout)
//    EasyRefreshLayout easylayout;

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
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.simpleMarqueeView)
    SimpleMarqueeView simpleMarqueeView;
    @BindView(R.id.ll_marqueen)
    LinearLayout llMarqueen;
    SimpleMarqueeView newMarquee;
    TextView tv_more_news, tv_more_vadieos;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.fl_frame)
    FrameLayout flFrame;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout refreshLayout;

    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void doLogic() {
       // toolbarLayout.setContentScrim(getResources().getDrawable(R.drawable.shape_toolbar_bg));
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
                    refreshLayout.setEnableRefresh(true);
//                    flFrame.setVisibility(View.GONE);
//                    banner.setVisibility(View.VISIBLE);


                }else if(state == State.COLLAPSED){

                    //折叠状态
                    refreshLayout.setEnableRefresh(false);
//                    flFrame.setVisibility(View.VISIBLE);
//                    banner.setVisibility(View.GONE);


                }else {
                    //中间状态
//                    flFrame.setVisibility(View.GONE);
//                    banner.setVisibility(View.VISIBLE);


                }
            }
        });
//        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rvList.getLayoutManager();
//                if (verticalOffset >= 0) {
//                    // refreshLayout.setEnabled(true);
//
//                    refreshLayout.setEnableRefresh(true);
//                } else {
//                    //refreshLayout.setEnabled(false);
//
//                    refreshLayout.setEnableRefresh(false);
//                }
//
//
//            }
//        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshLayout.setRefreshing(false);
//            }
//        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_city));
            }
        });
        ScreenUtil.ViewAdapter(_mActivity, appBar, 16, 9);
//        ViewGroup.LayoutParams layoutParams = llMarqueen.getLayoutParams();
//        layoutParams.width = ScreenUtil.getScreenWidth(_mActivity) / 2;
      //  llMarqueen.setMinimumWidth( ScreenUtil.getScreenWidth(_mActivity) / 2);

        banner.setIndicatorGravity(BannerConfig.CENTER);
        ivToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                LoginMainActivity.start(_mActivity, bundle);
            }
        });
        // swipeLayout.setDistanceToTriggerSync(200);
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        rvList.setLayoutManager(linearLayoutManager);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean scrollState = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: //滚动停止
                        scrollState = false;
                        autoPlayVideo(recyclerView);
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


            }


        });
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        adapter = new BaseListAdapter<String>(R.layout.item_videoview, data) {
            @Override
            public void handleView(BaseViewHolder helper, String item, int position) {
                JCVideoPlayerStandard standard = helper.getView(R.id.videoplayer);
                ScreenUtil.ViewAdapter(_mActivity, standard, 16, 9, 20);
                standard.setUp(
                        VideoConstant.videoUrls[0][position], JCVideoPlayer.SCREEN_LAYOUT_LIST,
                        VideoConstant.videoTitles[0][position]);
                ImageUtil.getInstance().loadImage(standard.getContext(), VideoConstant.videoThumbs[0][position], standard.thumbImageView);

            }
        };
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_home_function_btns, null, false);
        findViews(header);
        setListeners();
        adapter.addHeaderView(header);
        adapter.setOnLoadMoreListener(this, rvList);
        rvList.setAdapter(adapter);
        updateBanner(null);
        initMarqueen();
        initnewsMarqueen();

    }

    private void initMarqueen() {
        final List<String> datas = Arrays.asList("188xxxx5658 用户已经成功预约剪辑师", "188xxxx6666 用户已经完成ppt制作交易", "188xxxx8888 用户已经成功成为动画剪辑师");
        SimpleMF<String> marqueeFactory = new SimpleMF(_mActivity);
        marqueeFactory.setData(datas);
        simpleMarqueeView.setMarqueeFactory(marqueeFactory);
        simpleMarqueeView.startFlipping();

    }

    private void initnewsMarqueen() {
        final List<String> datas = Arrays.asList("第三届社会主义核心价值观主题大会开幕", "世界杯已经沦为惨剧杯各路豪强分分出局", "觅拍app即将上线敬请关注！");
        SimpleMF<String> marqueeFactory = new SimpleMF(_mActivity);
        marqueeFactory.setData(datas);
        newMarquee.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View mView, Object mData, int mPosition) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_news_detail));
            }
        });
        newMarquee.setMarqueeFactory(marqueeFactory);
        newMarquee.startFlipping();

    }


    private void updateBanner(List<GoodsDetailBean.DataBean.ContentAttachmentBean> bannerData) {
        if (banner == null) {
            return;
        }
        ArrayList<Integer> data = new ArrayList<>();
//        for(GoodsDetailBean.DataBean.ContentAttachmentBean bean:bannerData){
//            data.add(bean.getFilepath());
//        }
//        data.add(R.drawable.ic_demo_banner_1);
//        data.add(R.drawable.ic_demo_banner_2);
//        data.add(R.drawable.ic_demo_banner_3);
        banner.setImageLoader(new GlideBanerImageLoader());
        banner.isAutoPlay(true);
        banner.update(data);
    }

    private void findViews(View header) {
      /*  tvFunction1 = header.findViewById(R.id.tv_function_1);
        tvFunction2 = header.findViewById(R.id.tv_function_2);
        tvFunction3 = header.findViewById(R.id.tv_function_3);
        tvFunction4 = header.findViewById(R.id.tv_function_4);
        tvFunction5 = header.findViewById(R.id.tv_function_5);
        tvFunction6 = header.findViewById(R.id.tv_function_6);
        tvFunction7 = header.findViewById(R.id.tv_function_7);
        tvFunction8 = header.findViewById(R.id.tv_function_8);*/
        tv_more_news = header.findViewById(R.id.tv_more_news);
        tv_more_vadieos = header.findViewById(R.id.tv_more_vadieo);
        newMarquee = header.findViewById(R.id.news_marqueen);

    }

    private void setListeners() {
        flSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_search));
            }
        });
//        refreshLayout.setLoadMoreModel(LoadModel.NONE);
//        refreshLayout.addEasyEvent(this);
//        refreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
//            @Override
//            public void onLoadMore() {
//
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 5; i++) {
//                    list.add("this is  new load data >>>>");
//                }
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.loadMoreComplete();
//                        //easylayout.closeLoadView();
////                        int postion = adapter.getData().size();
////                        adapter.getData().addAll(list);
////                        adapter.notifyDataSetChanged();
////                        rvList.scrollToPosition(postion);
//                    }
//                }, 500);
//
//            }
//
//            @Override
//            public void onRefreshing() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        List<String> list = new ArrayList<>();
////                        for (int i = 0; i < 20; i++) {
////                            list.add("this is refresh data >>>" + new Date().toLocaleString());
////                        }
////                        adapter.setNewData(list);
//                        refreshLayout.refreshComplete();
//                    }
//                }, 1000);
//
//            }
//        });
        tvFunction1.setOnClickListener(this);
        tvFunction2.setOnClickListener(this);
        tvFunction3.setOnClickListener(this);
        tvFunction4.setOnClickListener(this);
        tvFunction5.setOnClickListener(this);
        tvFunction6.setOnClickListener(this);
        tvFunction7.setOnClickListener(this);
        tvFunction8.setOnClickListener(this);
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
        // swipeLayout.setOnRefreshListener(this);
        //adapter.setOnLoadMoreListener(this,rvList);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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

    @Override
    public void onClick(View v) {
        int type = 1;
        switch (v.getId()) {
         /*   case R.id.tv_function_1:
                type = 1;
                break;
            case R.id.tv_function_2:
                type = 2;
                break;
            case R.id.tv_function_3:
                type = 3;
                break;
            case R.id.tv_function_4:
                type = 4;
                break;
            case R.id.tv_function_5:
                type = 5;
                break;
            case R.id.tv_function_6:
                type = 6;
                break;
            case R.id.tv_function_7:
                type = 7;
                break;
            case R.id.tv_function_8:
                type = 8;
                break;*/
        }
        Bundle bundle = getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopList);
        bundle.putInt("type", type);
        ActionActivity.start(_mActivity, bundle);

    }

    @Override
    public void onLoadMoreRequested() {
        adapter.loadMoreEnd(false);

    }

    @Override
    public boolean onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    private void autoPlayVideo(RecyclerView view) {
        if (!JCUtils.isWifiConnected(getContext())) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.videoplayer) != null) {
                JCVideoPlayerStandard videoPlayerStandard1 = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.videoplayer);
                Rect rect = new Rect();
                videoPlayerStandard1.getLocalVisibleRect(rect);
                int videoheight3 = videoPlayerStandard1.getHeight();
                //当前播放器能完全显示
                if (rect.top == 0 && rect.bottom == videoheight3) {
                    if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                        //调用开始播放的按钮
                        videoPlayerStandard1.startButton.performClick();

                    }
                    return;
                }

            }

        }
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onStart() {
        super.onStart();
        simpleMarqueeView.startFlipping();
        newMarquee.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        simpleMarqueeView.stopFlipping();
        newMarquee.stopFlipping();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        simpleMarqueeView.startFlipping();
        newMarquee.startFlipping();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        JCVideoPlayer.releaseAllVideos();
        simpleMarqueeView.stopFlipping();
        newMarquee.stopFlipping();
    }


    public void scrollTotop() {
        rvList.smoothScrollToPosition(0);
    }


}
