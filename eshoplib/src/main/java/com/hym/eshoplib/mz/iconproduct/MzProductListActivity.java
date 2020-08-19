package com.hym.eshoplib.mz.iconproduct;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.fragment.search.mz.model.LngLonModel;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;

import java.util.List;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.mz.widgets.MzHeaderBar;


/**
 * icon  显示产品列表
 */
public class MzProductListActivity extends MzBaseActivity implements AMapLocationListener,
        SwipeRefreshLayout.OnRefreshListener {

    private String TAG = "MzProductList";
    private int iconId = 0;
    private MzHeaderBar headerBar;
    private RecyclerView mRecyclerView;
    private MzProductAdapter adapter;
    private TextView noView;
    private SwipeRefreshLayout swipe;

    private int page = 1;
    private int totalPage = 0;
    private int pageSize = 0;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_product_list);
        getBundle();

        getProductList();

        getProductLists(true);


    }

    private void getBundle() {
        headerBar = findViewById(R.id.headerBar);
        String title = "";
        iconId = getIntent().getIntExtra(MzConstant.KEY_HOME_ICON_ID, 0);
        switch (iconId) {
            case 1:
                title = getResources().getString(R.string.mz_home_tab_1);
                break;
            case 2:
                title = getResources().getString(R.string.mz_home_tab_2);

                break;
            case 3:
                title = getResources().getString(R.string.mz_home_tab_3);
                break;
            case 4:
                title = getResources().getString(R.string.mz_home_tab_4);

                break;
            case 5:
                title = getResources().getString(R.string.mz_home_tab_5);

                break;
            case 6:
                title = getResources().getString(R.string.mz_home_tab_6);

                break;
            case 7:
                title = getResources().getString(R.string.mz_home_tab_7);

                break;
            case 8:
                title = getResources().getString(R.string.mz_home_tab_8);

                break;
            case 9:
                title = getResources().getString(R.string.mz_home_tab_9);

                break;
            case 10:
                title = getResources().getString(R.string.mz_home_tab_10);
                break;

        }

        headerBar.setTitle(title);
        headerBar.setHeaderBarListener(new MzHeaderBar.HeaderBarListener() {
            @Override
            public void back() {
                finish();
            }

            @Override
            public void rightButton() {

            }
        });
    }

    private void getProductList() {

        mRecyclerView = findViewById(R.id.mRecyclerView);
        noView = findViewById(R.id.noView);
        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        adapter = new MzProductAdapter(this, null);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                HomeIconProductBean.DataBean.VideoBean bean =
                        (HomeIconProductBean.DataBean.VideoBean) adapter.getItem(position);

                String case_id = bean.getCase_id();

                LngLonModel lngLonModel = new LngLonModel(bean.getLon(), bean.getLat(), bean.getAddress());

//                Log.e(TAG, " == " + JSONObject.toJSONString(bean));

                HomeApi.getProductDetailData(new ResponseImpl<GoodDetailModel>() {
                    @Override
                    public void onSuccess(GoodDetailModel data) {


                        // 视频 ;
                        if (data.getData().getType().equals("1")) {

                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopVideoDetail);

                            // 产品详情
                            bundle.putSerializable("data", data);

                            // 产品 地址 经纬度。
                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);


                            bundle.putString("title", "产品详情");
                            ActionActivity.start(MzProductListActivity.this, bundle);

                            // 图片 ;
                        } else if (data.getData().getType().equals("2")) {


                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopDetail);
                            bundle.putSerializable("data", data);

                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);
                            bundle.putString("title", "产品详情");
                            ActionActivity.start(MzProductListActivity.this, bundle);

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


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (page > totalPage) {
                    adapter.loadMoreEnd();
                } else {
                    getProductLists(false);
                }
            }
        }, mRecyclerView);


    }


    private void getProductLists(boolean isRefresh) {


        MzNewApi.getProductList(String.valueOf(iconId), String.valueOf(page),
                new ResponseImpl<HomeIconProductBean>() {
                    @Override
                    public void onSuccess(HomeIconProductBean data) {

//                        Log.e(TAG, "  产品列表 = onSuccess: " + JSONObject.toJSON(data));
                        HomeIconProductBean.DataBean dataBean = data.getData();
                        List<HomeIconProductBean.DataBean.VideoBean> list = dataBean.getVideo();

                        currentPage = dataBean.getCurrentpage();
                        totalPage = dataBean.getTotalpage();
                        swipe.setRefreshing(false);

                        if (list != null && list.size() > 0) {

                            setData(isRefresh, list);

                        } else {
                            noView.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
//                        Log.e(TAG, "onError: " + e.getMessage());
                        swipe.setRefreshing(false);
                        adapter.loadMoreFail();
                        adapter.setEnableLoadMore(true);
                    }
                }, HomeIconProductBean.class);

    }

    /**
     * 高德 定位地址 ;
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        LatLonPoint dest = new LatLonPoint(longitude, latitude);

        if (longitude != 0 && latitude != 0) {
            adapter.setDest(dest);
        }
    }

    private void setData(boolean isRefresh, List<HomeIconProductBean.DataBean.VideoBean> list) {
        int size = list == null ? 0 : list.size();

        if (isRefresh) {
            adapter.setNewData(list);
        } else {
            if (size > 0) {
                adapter.addData(list);
            }
        }

        if (size < pageSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        getProductLists(true);
    }


}