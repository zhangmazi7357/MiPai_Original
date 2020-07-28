package com.hym.eshoplib.mz.iconproduct;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.example.lib_amap.MapManager;
import com.hym.eshoplib.R;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;

import java.util.List;

import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.mz.widgets.MzHeaderBar;

/**
 * icon  显示产品列表
 */
public class MzProductListActivity extends MzBaseActivity implements AMapLocationListener {

    private String TAG = "MzProductList";
    private int iconId = 0;
    private MzHeaderBar headerBar;
    private RecyclerView mRecyclerView;
    private MzProductAdapter adapter;
    private TextView noView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_product_list);
        getBundle();

        getProductList();


        //定位当前位置;
        MapManager.getInstance().location(this, true, this);
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

        adapter = new MzProductAdapter(this, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);


        MzNewApi.getProductList(String.valueOf(iconId), new ResponseImpl<HomeIconProductBean>() {
            @Override
            public void onSuccess(HomeIconProductBean data) {
                List<HomeIconProductBean.DataBean> list = data.getData();

                if (list != null && list.size() > 0) {
                    adapter.setNewData(list);
                    noView.setVisibility(View.GONE);
                } else {
                    noView.setVisibility(View.VISIBLE);
                }
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

        adapter.setDest(dest);
    }
}