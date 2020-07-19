package com.hym.eshoplib.fragment.shop.mzupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.lib_amap.MapManager;
import com.hym.eshoplib.R;

import java.util.List;

/**
 * 摄影棚位置
 */
public class MzLocationActivity extends AppCompatActivity {

    private String TAG = "LOCATION";
    private MapView mapView;
    private RecyclerView poiRecyclerView;
    private POISearchAdapter poiSearchAdapter;
    private EditText etPoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_location);

        initMap(savedInstanceState);
        initRecyclerView();
    }


    private void initMap(Bundle savedInstanceState) {


        mapView = findViewById(R.id.mapView);

        // 创建地图
        MapManager.getInstance().createMap(mapView, savedInstanceState);


    }


    private void initRecyclerView() {
        poiRecyclerView = findViewById(R.id.poiRecyclerView);
        poiSearchAdapter = new POISearchAdapter();
        poiRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        poiRecyclerView.setAdapter(poiSearchAdapter);

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
                if (!TextUtils.isEmpty(keyWord)) {

                    poiRecyclerView.setVisibility(View.VISIBLE);

                    MapManager.getInstance().poiSearch(MzLocationActivity.this,
                            keyWord, "",
                            new PoiSearch.OnPoiSearchListener() {
                                @Override
                                public void onPoiSearched(PoiResult poiResult, int i) {
                                    /**
                                     *  这里可以将poi  搜索到的结果在地图上绘制出点 ;
                                     */
                                    Log.e(TAG, "onPoiSearched: " + JSONObject.toJSONString(poiResult) + ",i=" + i);

                                }

                                @Override
                                public void onPoiItemSearched(PoiItem poiItem, int i) {

                                }
                            },
                            // 智能联想的链表
                            new Inputtips.InputtipsListener() {
                                @Override
                                public void onGetInputtips(List<Tip> list, int i) {
                                    Log.e(TAG, "onGetInputtips: " + JSONObject.toJSON(list));

                                    poiSearchAdapter.setDatas(list);

                                }
                            });
                } else {

                    poiRecyclerView.setVisibility(View.GONE);

                }
            }
        });

        poiSearchAdapter.setPoiListener(new POISearchAdapter.POIListener() {
            @Override
            public void onClick(int position) {

                /**
                 *    画点 ?
                 */
                Tip item = poiSearchAdapter.getItemPosition(position);
                etPoi.setText(item.getDistrict() + item.getAddress());
                poiRecyclerView.setVisibility(View.GONE);

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
                        Log.e("TAG", "latitude: " + latitude + ",longitude = " + longitude);
                    }
                });


    }
}