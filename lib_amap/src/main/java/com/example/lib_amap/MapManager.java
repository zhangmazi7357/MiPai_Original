package com.example.lib_amap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.DistanceSearch;

import java.util.List;

/**
 * 高德地图统一管理器
 */
public class MapManager {

    private static MapManager INSTANCE = null;
    private AMap aMap;

    private String TAG = "Map";

    private MapManager() {
    }

    public static MapManager getInstance() {
        if (INSTANCE == null) {
            synchronized (MapManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MapManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 初始化地图控制器对象
     *
     * @param mapView
     * @return
     */
    public AMap initMap(MapView mapView) {
        if (aMap == null) {
            return mapView.getMap();
        }
        return aMap;
    }

    /**
     * 创建地图
     *
     * @param mapView
     * @param bundle
     */
    public AMap createMap(MapView mapView, Bundle bundle) {
        if (mapView != null) {
            mapView.onCreate(bundle);
            if (aMap == null) {
                aMap = mapView.getMap();
            }
        }
        return aMap;
    }

    /**
     * 重新绘制地图
     *
     * @param mapView
     */
    public void onResumeMap(MapView mapView) {
        if (mapView != null) {
            mapView.onResume();
        }
    }

    /**
     * 暂停绘制地图
     *
     * @param mapView
     */
    public void onPauseMap(MapView mapView) {
        if (mapView != null) {
            mapView.onPause();
        }
    }

    /**
     * 保存地图当前状态
     *
     * @param mapView
     * @param bundle
     */
    public void onSaveInstanceState(MapView mapView, Bundle bundle) {
        if (mapView != null) {
            mapView.onSaveInstanceState(bundle);
        }
    }

    /**
     * 销毁地图
     *
     * @param mapView
     */
    public void onDestroy(MapView mapView) {
        if (mapView != null) {
            mapView.onDestroy();
        }
    }


    /**
     * 定位
     *
     * @param context
     * @param listener
     */
    public AMapLocationClient location(Context context, boolean onceLocation, AMapLocationListener listener) {
        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(listener);


        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();

        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationClientOption.setInterval(2000);

        // 设置高德地图是否只定位一次;
        locationClientOption.setOnceLocation(onceLocation);

        //设置定位参数
        mLocationClient.setLocationOption(locationClientOption);


        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mLocationClient.startLocation();


        return mLocationClient;

    }


    /**
     * 显示定位小蓝点
     * url  :https://lbs.amap.com/api/android-sdk/guide/create-map/mylocation
     *
     * @param interval     时间间隔
     * @param locationType
     * @throws Throwable
     */
    public void locationEnable(long interval, int locationType,
                               AMap.OnMyLocationChangeListener listener) {
        locationEnable(interval, true, true, locationType, listener);
    }

    /**
     * 设置定位小蓝点
     *
     * @param interval           连续定位模式下的定位间隔，只在连续定位模式下生效
     * @param showLocationButton 是否显示定位按钮，一点就回到当前位置
     * @param showLocationEnable 是否显示定位蓝点，不显示就不定位了
     * @param locationType       定位模式， 8种呢  MyLocationStyle.LOCATION_TYPE...
     * @throws Throwable
     */
    public void locationEnable(long interval,
                               boolean showLocationButton,
                               boolean showLocationEnable,
                               int locationType,
                               AMap.OnMyLocationChangeListener listener
    ) {
        MyLocationStyle locationStyle = new MyLocationStyle();
        // 设置连续定位模式下的定位间隔，只在连续定位模式下生效。
        locationStyle.interval(interval);

        // 设置蓝点展现模式 -- > 8种模式
        locationStyle.myLocationType(locationType);
        if (aMap == null) {
            try {
                throw new Throwable("请先初始化 AMap 地图控制器对象");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        // 设置定位蓝点 的 Style ;
        aMap.setMyLocationStyle(locationStyle);

        // 设置是否显示定位按钮，非必须设置，默认显示。
        aMap.getUiSettings().setMyLocationButtonEnabled(showLocationButton);
        // 设置是否显示定位小蓝点， true表示显示并定位，false表示不显示并且不定位。默认为false
        aMap.setMyLocationEnabled(showLocationEnable);

        // 监听定位回调 ,可以返回定位的经纬度
        aMap.setOnMyLocationChangeListener(listener);

    }

    /**
     * 在地图上绘制默认 Marker
     *
     * @param position 经纬度值  ###必填
     * @param title    marker 的名称
     * @param snippet  marker 的描述
     * @return
     */
    public Marker addMarker(LatLng position, String title, String snippet) {
        if (aMap != null)
            return aMap.addMarker(new MarkerOptions().position(position).title(title).snippet(snippet));
        return null;

    }


    /**
     * 地图坐标点距离计算
     *
     * @param context
     * @param dest       要查询的目的地
     * @param lonPoints  要查询的起点，支持多个点
     * @param searchType 设置测量方式，支出直线（DistanceSearch.TYPE_DISTANCE）和驾车（DistanceSearch.TYPE_DRIVING_DISTANCE）
     * @param listener   监听返回的距离结果
     */
    public void calculateInstance(Context context,
                                  LatLonPoint dest,
                                  List<LatLonPoint> lonPoints,
                                  int searchType,
                                  DistanceSearch.OnDistanceSearchListener listener) {

        DistanceSearch distanceSearch = new DistanceSearch(context);
        distanceSearch.setDistanceSearchListener(listener);
        DistanceSearch.DistanceQuery distanceQuery = new DistanceSearch.DistanceQuery();

        Log.e(TAG, "计算距离  起点 = " + dest.getLongitude() + "," + dest.getLatitude());
        Log.e(TAG, "计算距离: 终点 =  " + lonPoints.get(0).getLongitude() + "," + lonPoints.get(0).getLatitude());
        // 设置查询起点，支持多个点
        distanceQuery.setOrigins(lonPoints);
        // 设置查询目的地
        distanceQuery.setDestination(dest);
        //设置测量方式，支持直线和驾车
        distanceQuery.setType(searchType);

        // 发送请求距离
        distanceSearch.calculateRouteDistanceAsyn(distanceQuery);
    }


    /**
     * 智能提示
     *
     * @param context
     * @param keyWord
     * @param inputtipsListener
     */
    public void tipSearch(Context context, String keyWord, Inputtips.InputtipsListener inputtipsListener) {
        // 实现输入提示
        InputtipsQuery inputquery = new InputtipsQuery(keyWord, "北京");
        inputquery.setCityLimit(true);//限制在当前城市

        //
        Inputtips inputTips = new Inputtips(context, inputquery);
        inputTips.setInputtipsListener(inputtipsListener);


        // 实时请求
        inputTips.requestInputtipsAsyn();
    }


    /**
     * POI检索
     * url  : https://lbs.amap.com/api/android-sdk/guide/map-data/poi#inputtips
     *
     * @param keyWord  keyWord表示搜索字符串，
     * @param cityCode 表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
     */
    public void poiSearch(Context context,
                          String keyWord,
                          String cityCode,
                          PoiSearch.OnPoiSearchListener poiSearchListener
    ) {
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", cityCode);

        // 设置每页最多返回多少条poiitem
        query.setPageSize(100);
        //设置查询页码
//        query.setPageNum(1);

        PoiSearch poiSearch = new PoiSearch(context, query);
        // 设置 poi 搜索监听;
        poiSearch.setOnPoiSearchListener(poiSearchListener);

        // 发送poi 请求
        poiSearch.searchPOIAsyn();

        // 通过 PoiSearch.OnPoiSearchListener的回调 onPoiSearched() 将搜索点绘制在地图上


    }

}
