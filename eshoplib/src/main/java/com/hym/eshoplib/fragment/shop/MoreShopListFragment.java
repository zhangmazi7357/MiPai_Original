package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.fragment.search.mz.MzSearchResultActivity;
import com.hym.eshoplib.fragment.search.mz.adapter.MzSearchSortAdapter;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.luck.picture.lib.tools.ValueOf;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.widgets.view.DropDownMenuUpdate;

/**
 * 限时特惠 && 觅拍严选
 */
public class MoreShopListFragment extends BaseKitFragment {


    private String TAG = "MoreShopListFragment";
    Unbinder unbinder;


    private ShopListAdapter shopListAdapter;
    private int currentPage = 1;
    private boolean isRefresh;
    private String title;

    @BindView(R.id.dropDownMenu)
    DropDownMenuUpdate dropDownMenu;


    private RecyclerView rvView;
    private SmartRefreshLayout srfLayout;


    private int selectPosition = 0;
    private String regionId = "";           // 城市id ;
    private String sortType = "";           // 智能搜索类型;

    private BaseListAdapter<ServerCityBean.DataBean.InfoBean> proAdapter;
    private BaseListAdapter<ServerCityBean.DataBean.InfoBean> cityAdapter;

    public static MoreShopListFragment newInstance(Bundle args) {
        MoreShopListFragment fragment = new MoreShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        showBackButton();
        title = getArguments().getString("title");
        setTitle(title);

        initDropDownMenu();

        srfLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                getData(true);
            }
        });

        srfLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                getData(false);
            }
        });

    }

    private void getData(final boolean refresh) {

        if (refresh) {
            currentPage = 1;
        } else {
            currentPage++;
        }


        if (title.equals("限时特惠")) {

            strict(refresh, sortType, regionId);
        } else if (title.equals("觅拍严选")) {

            // 觅拍严选 ;
            select(refresh, sortType, regionId);
        }


    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        SpecialTimeLimteBean.DataBean data =
                (SpecialTimeLimteBean.DataBean) getArguments().getSerializable("data");

        final List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getVideo();

        rvView.setLayoutManager(new GridLayoutManager(_mActivity, 2,
                LinearLayoutManager.VERTICAL, false));


        shopListAdapter = new ShopListAdapter(R.layout.item_shop, video);
        rvView.setAdapter(shopListAdapter);

        shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String case_id = video.get(position).getCase_id();

                //  产品详情
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
    public int getContentViewResId() {
        return R.layout.fragment_add_more;
    }


    // 添加筛选框 ;
    private void initDropDownMenu() {
        List<String> tabs = new ArrayList<>();
        tabs.add("全国");
        tabs.add("智能排序");
        tabs.add("全部");

        List<View> pops = new ArrayList<>();

        View viewCity = popsView_City();
        pops.add(viewCity);

        View viewSort = popsView_Sort();
        pops.add(viewSort);

        View viewAll = new View(_mActivity);
        pops.add(viewAll);


        View rvContainer = LayoutInflater.from(_mActivity)
                .inflate(R.layout.mz_fragment_add_more, null, false);

        rvView = rvContainer.findViewById(R.id.rv_view);
        srfLayout = rvContainer.findViewById(R.id.srf_layout);

        dropDownMenu.setDropDownMenu(tabs, pops, rvContainer);


        //  4   因为还有竖向分割线 ;  单独把 全部拿出来修改一下 ;
        FrameLayout containerAll = dropDownMenu.getContainer(4);
        TextView tabAll = (TextView) containerAll.getChildAt(0);
        tabAll.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_down), null);

        // 全部 点击事件
        containerAll.setOnClickListener(v -> {
            dropDownMenu.closeMenu();

        });


    }


    // 全国的 View ;
    private View popsView_City() {

        RecyclerView proRv;
        RecyclerView cityRv;


        View root = LayoutInflater.from(_mActivity)
                .inflate(R.layout.fragment_select_city_2, null, false);

        proRv = root.findViewById(R.id.rv_list_1);
        cityRv = root.findViewById(R.id.rv_list_2);

        proRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        cityRv.setLayoutManager(new LinearLayoutManager(_mActivity));


        proAdapter = new BaseListAdapter<ServerCityBean.DataBean.InfoBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ServerCityBean.DataBean.InfoBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(String.valueOf(item.getRegion_name()));

                if (selectPosition == position) {
                    text.setTextColor(ContextCompat.getColor(_mActivity,
                            R.color.mipaiTextColorSelect));

                } else {
                    text.setTextColor(ContextCompat.getColor(_mActivity,
                            R.color.black));

                }

            }

        };
        proRv.setAdapter(proAdapter);

        proAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectPosition = position;
                proAdapter.notifyDataSetChanged();

                if (position == 0) {
                    regionId = "";
                    dropDownMenu.setTabText("全国");
                    dropDownMenu.closeMenu();
//                    goSearch();

                } else {

                    String pid = proAdapter.getData().get(position).getRegion_id();

                    ShopApi.getCityList2(pid, new ResponseImpl<ServerCityBean>() {
                        @Override
                        public void onSuccess(ServerCityBean data) {
                            cityAdapter.setNewData(data.getData().getInfo());

                        }
                    }, ServerCityBean.class);

                }
            }
        });
        cityAdapter = new BaseListAdapter<ServerCityBean.DataBean.InfoBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ServerCityBean.DataBean.InfoBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(String.valueOf(item.getRegion_name()));
                if (regionId.equals(item.getRegion_id())) {
                    text.setTextColor(ContextCompat.getColor(_mActivity,
                            R.color.mipaiTextColorSelect));
                } else {
                    text.setTextColor(ContextCompat.getColor(_mActivity,
                            R.color.black));
                }
            }
        };

        cityRv.setAdapter(cityAdapter);

        cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                regionId = cityAdapter.getData().get(position).getRegion_id();
                cityAdapter.notifyDataSetChanged();

                dropDownMenu.setTabText(cityAdapter.getData().get(position).getRegion_name());
                dropDownMenu.closeMenu();

                //TODO 切换城市之后要重新搜索 ;

                if (title.equals("限时特惠")) {

                    strict(true, sortType, regionId);
                } else if (title.equals("觅拍严选")) {

                    // 觅拍严选 ;
                    select(true, sortType, regionId);
                }


            }
        });


        // 省份列表
        ShopApi.getCityList2("", new ResponseImpl<ServerCityBean>() {
            @Override
            public void onSuccess(ServerCityBean data) {
                List<ServerCityBean.DataBean.InfoBean> citys = new ArrayList<ServerCityBean.DataBean.InfoBean>();
                ServerCityBean.DataBean.InfoBean bean = new ServerCityBean.DataBean.InfoBean();
                bean.setRegion_name("全国");
                citys.add(bean);
                citys.addAll(data.getData().getInfo());
                proAdapter.setNewData(citys);

            }
        }, ServerCityBean.class);

        return root;
    }


    // 智能排序 View;
    private View popsView_Sort() {
        RecyclerView sortRv = new RecyclerView(_mActivity);

        sortRv.setBackgroundColor(getResources().getColor(R.color.white));
        sortRv.setLayoutManager(new LinearLayoutManager(_mActivity));


        MzSearchSortAdapter adapter = new MzSearchSortAdapter(_mActivity);


        adapter.setSearchSortClickListener(new MzSearchSortAdapter.SearchSortClickListener() {
            @Override
            public void onItemClick(int position, String sortTitle) {

                adapter.setSelectPosition(position);

                dropDownMenu.setTabText(sortTitle);
                dropDownMenu.closeMenu();

                /*
                   改变智能排序类型
                   如果没有点击智能排序, liveData = null;
                 */

                switch (position) {
                    case 0:
                        sortType = "1";
                        break;

                    case 1:
                        sortType = "2";
                        break;

                    case 2:
                        sortType = "3";
                        break;

                    case 3:
                        sortType = "4";
                        break;

                }

                if (title.equals("限时特惠")) {

                    strict(true, sortType, regionId);

                } else if (title.equals("觅拍严选")) {

                    // 觅拍严选 ;
                    select(true, sortType, regionId);
                }


            }
        });

        sortRv.setAdapter(adapter);

        return sortRv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void strict(boolean refresh, String sort_type, String regionId) {

        // 限时特惠
        HomeApi.getSpecialTimeLimteData(String.valueOf(currentPage), sort_type, regionId,
                new ResponseImpl<SpecialTimeLimteBean>() {
                    @Override
                    public void onSuccess(SpecialTimeLimteBean data) {
                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();

                        if (refresh) {

                            shopListAdapter.setNewData(video);
                            shopListAdapter.notifyDataSetChanged();
                            srfLayout.finishRefresh();

                        } else {

                            shopListAdapter.addData(video);
                            shopListAdapter.notifyDataSetChanged();
                            srfLayout.finishLoadMore();

                        }
                    }
                }, SpecialTimeLimteBean.class);
    }


    private void select(boolean refresh, String sort_type, String regionId) {

        HomeApi.getStrictSelectData(String.valueOf(currentPage), sort_type, regionId,
                new ResponseImpl<SpecialTimeLimteBean>() {
                    @Override
                    public void onSuccess(SpecialTimeLimteBean data) {

                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();

                        if (refresh) {
                            shopListAdapter.setNewData(video);
                            shopListAdapter.notifyDataSetChanged();
                            srfLayout.finishRefresh();
                        } else {
                            shopListAdapter.addData(video);
                            //shopListAdapter.setNewData(video);
                            shopListAdapter.notifyDataSetChanged();
                            srfLayout.finishLoadMore();
                        }
                    }
                }, SpecialTimeLimteBean.class);
    }
}
