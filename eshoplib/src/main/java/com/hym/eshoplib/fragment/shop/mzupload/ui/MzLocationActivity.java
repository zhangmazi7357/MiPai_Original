package com.hym.eshoplib.fragment.shop.mzupload.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.lib_amap.MapManager;
import com.hym.eshoplib.R;
import com.hym.eshoplib.fragment.shop.mzupload.POISearchAdapter;
import com.hym.eshoplib.mz.MzConstant;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.mz.widgets.MzHeaderBar;

/**
 * 摄影棚位置
 */
public class MzLocationActivity extends AppCompatActivity {

    private String TAG = "LOCATION";
    private MapView mapView;
    private RecyclerView poiRecyclerView;
    private POISearchAdapter poiSearchAdapter;
    private EditText etPoi;
    private MzHeaderBar headerBar;
    private TextView tvSure;
    private AMap aMap;

    private double latitudes;
    private double longitudes;
    private String mzAddress;


    private boolean showRv = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_location);

        initMap(savedInstanceState);
        initRecyclerView();

        tvSure = findViewById(R.id.tv_sure);

        headerBar = findViewById(R.id.mzHeaderBar);


        headerBar.setHeaderBarListener(new MzHeaderBar.HeaderBarListener() {
            @Override
            public void back() {
                finish();
            }

            @Override
            public void rightButton() {

                // 透传 address 和经纬度 ;


            }
        });

        etPoi = findViewById(R.id.et_poi);
        etPoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = s.toString();

                if (TextUtils.isEmpty(keyWord)) {
                    showRv = true;

                }


                if (!TextUtils.isEmpty(keyWord) && showRv) {
                    poiSearchAdapter.setDatas(null);
                    MapManager.getInstance()
                            .tipSearch(MzLocationActivity.this, keyWord,
                                    new Inputtips.InputtipsListener() {
                                        @Override
                                        public void onGetInputtips(List<Tip> list, int i) {

                                            poiSearchAdapter.setDatas(list);

                                        }
                                    });


                }
            }
        });


        tvSure.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mzAddress)) {
                Toast.makeText(MzLocationActivity.this, "需要选择摄影棚地址", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            intent.putExtra(MzConstant.VALUE_PRODUCT_LOCATION_ADDRESS, mzAddress);
            intent.putExtra(MzConstant.VALUE_PRODUCT_LOCATION_LAT, latitudes);
            intent.putExtra(MzConstant.VALUE_PRODUCT_LOCATION_LON, longitudes);

            setResult(MzConstant.RESULT_CODE_UPLOAD, intent);
            finish();
        });

    }


    private void initMap(Bundle savedInstanceState) {


        mapView = findViewById(R.id.mapView);

        // 创建地图
        aMap = MapManager.getInstance().createMap(mapView, savedInstanceState);


    }


    private void initRecyclerView() {
        poiRecyclerView = findViewById(R.id.poiRecyclerView);
        poiSearchAdapter = new POISearchAdapter();
        poiRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        poiRecyclerView.setAdapter(poiSearchAdapter);

        poiSearchAdapter.setPoiListener(new POISearchAdapter.POIListener() {
            @Override
            public void onClick(int position) {

                /**
                 *   智能提示中点击了 显示在搜索栏中 就不要再调用智能搜索了;
                 */
                showRv = false;


                Tip item = poiSearchAdapter.getItemPosition(position);
                mzAddress = item.getAddress() + item.getName();

                LatLonPoint point = item.getPoint();
                latitudes = point.getLatitude();
                longitudes = point.getLongitude();

                poiSearchAdapter.setDatas(null);

                if (!TextUtils.isEmpty(mzAddress)) {
                    etPoi.setText(mzAddress);
                    etPoi.setSelection(mzAddress.length());
                }


                /**
                 *   通过关键字搜索；
                 */
//                keyWordSearch(address);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        // 显示一个小蓝点;
        MapManager.getInstance().locationEnable(2000,
                MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER,
                new AMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Bundle extras = location.getExtras();
//                        Log.e("TAG", "latitude: " + latitude + ",longitude = " + longitude);
                    }
                });


    }


    private void markerListener() {
        aMap.addOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

//                isClickMarkerShowRv = true;

                marker.showInfoWindow();
//                Log.e(TAG, "marker点击事件" + JSONObject.toJSONString(marker));
                LatLng position = marker.getOptions().getPosition();
                latitudes = position.latitude;
                longitudes = position.longitude;
                mzAddress = marker.getTitle() + marker.getSnippet();
                if (!TextUtils.isEmpty(mzAddress)) {
                    etPoi.setHint(mzAddress);
                }

                return true;
            }
        });
    }


    private void keyWordSearch(String address) {
        MapManager.getInstance()
                .poiSearch(MzLocationActivity.this,
                        address, "010", new PoiSearch.OnPoiSearchListener() {
                            @Override
                            public void onPoiSearched(PoiResult poiResult, int i) {
//                                        Log.e(TAG, "onPoiSearched: " + JSONObject.toJSONString(poiResult));


                                ArrayList<PoiItem> pois = poiResult.getPois();

                                for (int j = 0; j < pois.size(); j++) {
                                    PoiItem poiItem = pois.get(j);
                                    LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                                    double latitude = latLonPoint.getLatitude();
                                    double longitude = latLonPoint.getLongitude();

                                    LatLng point = new LatLng(latitude, longitude);

                                    /**
                                     *   先不添加marker ;
                                     */
//                                            MapManager.getInstance()
//                                                    .addMarker(point, poiItem.getTitle(), poiItem.getSnippet());

                                }

                            }

                            @Override
                            public void onPoiItemSearched(PoiItem poiItem, int i) {
                                Log.e(TAG, "onPoiItemSearched: " + JSONObject.toJSONString(poiItem));
                            }
                        });
    }
}