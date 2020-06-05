package com.hym.eshoplib.fragment.city;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.adapter.HeaderAdapter;
import com.hym.eshoplib.adapter.MeiTuanAdapter;
import com.hym.eshoplib.adapter.MeituanHeaderAdapter;
import com.hym.eshoplib.bean.city.CityBean;
import com.hym.eshoplib.bean.city.CityHeaderBean;
import com.hym.eshoplib.bean.city.RegionBean;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.widgets.callback.SuspensionDecoration;
import com.hym.eshoplib.widgets.sidebar.bean.BaseIndexPinyinBean;
import com.hym.eshoplib.widgets.sidebar.widget.SuperSideBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import app.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/7/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class SelectCityFragment extends BaseKitFragment {
    @BindView(R.id.super_recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.super_side_bar)
    SuperSideBar superSideBar;
    @BindView(R.id.super_tv_hint)
    TextView superTvHint;
    Unbinder unbinder;
    @BindView(R.id.head_status_bar)
    View headStatusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
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
    @BindView(R.id.ll_toolbar_bg)
    LinearLayout llToolbarBg;
    private MeiTuanAdapter meiTuanAdapter;
    private MeituanHeaderAdapter headerAdapter;
    private SuspensionDecoration mDecoration;
    private LinearLayoutManager layoutManager;

    private List<CityBean> mDatas = new ArrayList<>();
    // 头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    // 设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    List<ServerCityBean.DataBean.InfoBean> citys;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    String city_name="";
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_select_city;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    public static SelectCityFragment newInstance(Bundle args) {
        SelectCityFragment fragment = new SelectCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });
        //etSearch.setVisibility(View.VISIBLE);
        //etSearch.setHint("城市名/拼音");
        tvSearch.setVisibility(View.VISIBLE);
        tvSearch.setHint("搜索/城市名");
        tvSearch.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(SearchCityResultFragment.newInstance(new Bundle()));
            }
        });
        tvRight.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        initAdapter();
        initData();
        initMap();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    HomeApi.ChangeRegion(etSearch.getText().toString(), new ResponseImpl<RegionBean>() {
                        @Override
                        public void onSuccess(RegionBean data) {
                            SharePreferenceUtil.setStringData(_mActivity,"region_name",etSearch.getText().toString());
                            SharePreferenceUtil.setStringData(_mActivity,"region_id",data.getData().getRegion_id());
                            CityHeaderBean header1 = mHeaderDatas.get(0);
                            header1.getCityList().clear();
                            header1.getCityList().add(etSearch.getText().toString());
                            headerAdapter.notifyDataSetChanged();
                            ToastUtil.toast("定位成功");

                        }
                    },RegionBean.class);

                }
                return false;
            }
        });

    }
    private void initMap() {
        mLocationClient = new AMapLocationClient(App.instance);
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                dissMissDialog();
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        Logger.d("城市=" + amapLocation.getCity());
                        //tvLeft.setText(amapLocation.getCity());
                        city_name=amapLocation.getCity();
                        HomeApi.ChangeRegion(city_name, new ResponseImpl<RegionBean>() {
                            @Override
                            public void onSuccess(RegionBean data) {
                                SharePreferenceUtil.setStringData(_mActivity,"region_name",city_name);
                                SharePreferenceUtil.setStringData(_mActivity,"region_id",data.getData().getRegion_id());
                                ToastUtil.toast("定位成功");
                                _mActivity.finish();

                            }
                        },RegionBean.class);
//                        CityHeaderBean header1 = mHeaderDatas.get(0);
//                        header1.getCityList().clear();
//                        header1.getCityList().add(city_name);
//                        headerAdapter.notifyDataSetChanged();
//                        ToastUtil.toast("定位成功");
//                        _mActivity.finish();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Logger.d("location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        ToastUtil.toast("定位失败请确认定位权限是否开启");
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

    private void initAdapter() {
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new CityHeaderBean(locationCity, "当前定位城市", "定"));
//        List<String> recentCitys = new ArrayList<>();
//        mHeaderDatas.add(new CityHeaderBean(recentCitys, "最近访问城市", "近"));
//        List<String> hotCitys = new ArrayList<>();
//        mHeaderDatas.add(new CityHeaderBean(hotCitys, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);

        meiTuanAdapter = new MeiTuanAdapter(_mActivity, mDatas);

        headerAdapter = new MeituanHeaderAdapter(meiTuanAdapter, _mActivity);
        headerAdapter.setHeaderView(0, R.layout.view_city_header_layout, mHeaderDatas.get(0));
        headerAdapter.setListener(new HeaderAdapter.OnHeaderViewClick() {
            @Override
            public void onCityClick(String name) {
                //ToastUtil.toast("城市="+name);
                if(TextUtils.isEmpty(city_name)){
                    ToastUtil.toast("请先定位");
                    return;
                }
                HomeApi.ChangeRegion(city_name, new ResponseImpl<RegionBean>() {
                    @Override
                    public void onSuccess(RegionBean data) {
                        SharePreferenceUtil.setStringData(_mActivity,"region_name",city_name);
                        SharePreferenceUtil.setStringData(_mActivity,"region_id",data.getData().getRegion_id());
                        _mActivity.finish();

                    }
                },RegionBean.class);

            }

            @Override
            public void onRelocationClick() {
               // ToastUtil.toast("重新定位");
                //ToastUtil.toast("定位成功");
                if(mLocationClient!=null){
                    showProgressDialog();
                    mLocationClient.startLocation();
                }

            }
        });
        recyclerView.setAdapter(headerAdapter);

        mDecoration = new SuspensionDecoration(_mActivity, mDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                        getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                        getResources().getDisplayMetrics()))
                .setColorTitleFont(
                        _mActivity.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(headerAdapter.getHeaderViewCount() - mHeaderDatas.size());
        recyclerView.addItemDecoration(mDecoration);// 添加字母悬浮利用添加分割线的方式
//        recyclerView.addItemDecoration(
//                SuperDivider.newBitmapDivider().setStartSkipCount(1).setEndSkipCount(0));
        superSideBar.setmPressedShowTextView(superTvHint)// 设置滑动的字母A,B,C
                .setNeedRealIndex(true)// 设置需要真实的索引
                .setmLayoutManager(layoutManager);// 设置RecyclerView的LayoutManager
        meiTuanAdapter.setListener(new MeiTuanAdapter.OnCityClickListener() {
            @Override
            public void onClicke(int position,String id, String name) {
                Logger.d("id="+id+",name="+name);
                SharePreferenceUtil.setStringData(_mActivity,"region_name",name);
                SharePreferenceUtil.setStringData(_mActivity,"region_id",id);
                _mActivity.finish();
            }
        });
    }

    private void initData() {
        CityHeaderBean header1 = mHeaderDatas.get(0);
        header1.getCityList().clear();
        String region_name=SharePreferenceUtil.getStringData(_mActivity,"region_name");
        if(TextUtils.isEmpty(region_name)){
            header1.getCityList().add("暂无位置信息");
            headerAdapter.notifyDataSetChanged();
        }else {
            city_name=region_name;
            header1.getCityList().add(region_name);
            headerAdapter.notifyDataSetChanged();
        }

        ShopApi.getCityList(new ResponseImpl<ServerCityBean>() {
            @Override
            public void onSuccess(ServerCityBean data) {
                citys = data.getData().getInfo();
                for (int i = 0; i < citys.size(); i++) {
                    CityBean weChatBean = new CityBean();
                    weChatBean.setName(data.getData().getInfo().get(i).getRegion_name());
                    weChatBean.setId(data.getData().getInfo().get(i).getRegion_id());
                    mDatas.add(weChatBean);
                }
                mSourceDatas.addAll(mDatas);
                superSideBar.getDataHelper().sortSourceDatas(mDatas);
                meiTuanAdapter.setDatas(mDatas);
                meiTuanAdapter.notifyDataSetChanged();
                superSideBar.setSourceDatas(mSourceDatas);// 设置数据
                mDecoration.setDatas(mSourceDatas);
                headerAdapter.notifyDataSetChanged();
            }
        }, ServerCityBean.class);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
