package com.hym.eshoplib.fragment.search.mz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.mz.upload.ProductTwoTypeBean;
import com.hym.eshoplib.databinding.FragmentMzSearchAllBinding;
import com.hym.eshoplib.fragment.search.mz.adapter.MzSearchAllAdapter;
import com.hym.eshoplib.fragment.search.mz.model.LngLonModel;
import com.hym.eshoplib.fragment.search.mz.model.MzSearchAllModel;
import com.hym.eshoplib.fragment.search.mz.viewmodel.MzSearchViewModel;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.iconproduct.HomeIconProductBean;
import com.hym.eshoplib.mz.iconproduct.MzProductAdapter;

import java.util.List;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.mz.MzBaseFragment;

/**
 * mz - 搜索全部结果 Fragment
 */
public class MzSearchAllFragment extends MzBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = "MzSearchAllFragment";

    FragmentMzSearchAllBinding binding;
    private MzSearchViewModel viewModel;

    private MzSearchAllAdapter adapter;

    private MzProductAdapter iconAdapter;

    private int page = 0;
    private int totalPage = 0;
    private int pageSize = 10;

    private int iconPage = 0;
    private int iconTotalPage = 0;
    private int iconPageSize = 10;

    private boolean isSearch = true;
    private String oneTypeId = "";
    private String twoTypeId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMzSearchAllBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(MzSearchViewModel.class);

        return binding.getRoot();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
        itemChange();

    }

    /**
     * 监听 搜索内容
     * 智能排序
     * 类型
     * 除了页码 如果搜索条件变化了 page 赋值 =1 刷新页面 ;
     */
    private void itemChange() {

        // 监听类型 只管  全部 ;
        Integer value = viewModel.getType().getValue();

        //TODO  可以 加一个是不是十大icon搜索的判断 ;

        if (value == 1) {
            // 搜索内容改变  去 搜索 ;
            viewModel.getContent().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    search(true);

                }
            });

            // 智能排序类型改变 去搜索 ;
            viewModel.getSearchSortType()
                    .observe(getViewLifecycleOwner(),
                            new Observer<Integer>() {
                                @Override
                                public void onChanged(Integer integer) {
//                                Log.e(TAG, "onChanged:  sortType");


                                    search(true);

                                }
                            });

            // 城市
            viewModel.getRegionId().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    search(true);
                }
            });


            // 类型 ;
            viewModel.getType().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {

                    search(true);
                }
            });

            //全部
            viewModel.getTagLiveData().observe(getViewLifecycleOwner(), new Observer<ProductTwoTypeBean.DataBean>() {
                @Override
                public void onChanged(ProductTwoTypeBean.DataBean dataBean) {

                    oneTypeId = dataBean.getOnetype_id();
                    twoTypeId = dataBean.getTwotype_id();

                    iconProductList(oneTypeId, twoTypeId, true);
                }
            });


        }


    }


    private void search(boolean isRefresh) {

        binding.allRv.setVisibility(View.VISIBLE);
        binding.iconRv.setVisibility(View.GONE);


        isSearch = true;

        viewModel.searchAll(page)
                .observe(getViewLifecycleOwner(), new Observer<MzSearchAllModel>() {
                    @Override
                    public void onChanged(MzSearchAllModel data) {
                        binding.swipe.setRefreshing(false);

                        totalPage = Integer.parseInt(data.getData().getTotalpage());


                        List<MzSearchAllModel.DataBean.InfoBean> info = data.getData().getInfo();

                        setData(isRefresh, data.getData().getInfo());


                    }
                });


    }


    private void iconProductList(String oneTypeId, String twoTypeId, boolean isRefresh) {
        binding.allRv.setVisibility(View.GONE);
        binding.iconRv.setVisibility(View.VISIBLE);

        isSearch = false;

        MzNewApi.getProductList(oneTypeId, String.valueOf(iconPage), twoTypeId, new ResponseImpl<HomeIconProductBean>() {
            @Override
            public void onSuccess(HomeIconProductBean data) {

                Log.e(TAG, "onSuccess: " + JSONObject.toJSONString(data));

                binding.swipe.setRefreshing(false);

                iconTotalPage = data.getData().getTotalpage();
                List<HomeIconProductBean.DataBean.VideoBean> list = data.getData().getVideo();


                setIconData(isRefresh, list);

            }
        }, HomeIconProductBean.class);
    }

    private void initRecyclerView() {
        binding.swipe.setOnRefreshListener(this);

        binding.allRv.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));

        adapter = new MzSearchAllAdapter(null);


        adapter.bindToRecyclerView(binding.allRv);

        //设置空布局
        adapter.setEmptyView(R.layout.mz_invite_empty_layout, binding.allRv);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                // 跳到产品详情 ;
                MzSearchAllModel.DataBean.InfoBean item = (MzSearchAllModel.DataBean.InfoBean) adapter.getItem(position);

                String caseId = item.getCase_id();


//                LngLonModel lngLonModel = new LngLonModel(item.getLon(), item.getLat(), item.getAddress());

                HomeApi.getProductDetailData(new ResponseImpl<GoodDetailModel>() {
                    @Override
                    public void onSuccess(GoodDetailModel data) {


                        // 视频详情 ;
                        if (data.getData().getType().equals("1")) {

                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopVideoDetail);

                            // 产品详情
                            bundle.putSerializable("data", data);

                            // 产品 地址 经纬度。
//                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);

                            bundle.putString("title", "产品详情");
                            ActionActivity.start(getActivity(), bundle);


                            // 图片详情 ;
                        } else if (data.getData().getType().equals("2")) {


                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopDetail);
                            bundle.putSerializable("data", data);

                            // item 包含了 经纬度 ;
//                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);

                            bundle.putString("title", "产品详情");
                            ActionActivity.start(getActivity(), bundle);

                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        binding.swipe.setRefreshing(false);
                    }


                }, GoodDetailModel.class, caseId);


            }
        });

        // 取消掉默认的第一次 就loadMore ;
        adapter.disableLoadMoreIfNotFullPage();

        //加载更多 ;
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (page > totalPage) {
                    adapter.loadMoreEnd();
                } else {

                    search(false);
                }
            }
        }, binding.allRv);


        //////////////////  icon   列表 ///////////////////
        binding.iconRv.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        iconAdapter = new MzProductAdapter(getContext(), null);

        iconAdapter.bindToRecyclerView(binding.iconRv);

        iconAdapter.setEmptyView(R.layout.mz_invite_empty_layout, binding.iconRv);
        iconAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeIconProductBean.DataBean.VideoBean item = iconAdapter.getItem(position);
                String caseId = item.getCase_id();


                HomeApi.getProductDetailData(new ResponseImpl<GoodDetailModel>() {
                    @Override
                    public void onSuccess(GoodDetailModel data) {


                        // 视频详情 ;
                        if (data.getData().getType().equals("1")) {

                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopVideoDetail);

                            // 产品详情
                            bundle.putSerializable("data", data);

                            // 产品 地址 经纬度。
//                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);

                            bundle.putString("title", "产品详情");
                            ActionActivity.start(getActivity(), bundle);


                            // 图片详情 ;
                        } else if (data.getData().getType().equals("2")) {


                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,
                                    ActionActivity.ShopDetail);
                            bundle.putSerializable("data", data);

                            // item 包含了 经纬度 ;
//                            bundle.putSerializable(MzConstant.KEY_HOME_ICON_PRODUCT, lngLonModel);

                            bundle.putString("title", "产品详情");
                            ActionActivity.start(getActivity(), bundle);

                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        binding.swipe.setRefreshing(false);
                    }


                }, GoodDetailModel.class, caseId);


            }
        });
        iconAdapter.disableLoadMoreIfNotFullPage();
        iconAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                iconPage++;
                if (iconPage > iconTotalPage) {
                    iconAdapter.loadMoreEnd();
                } else {
                    iconProductList(oneTypeId, twoTypeId, false);

                }
            }
        }, binding.iconRv);

        onRefresh();
    }


    private void setData(boolean isRefresh, List<MzSearchAllModel.DataBean.InfoBean> list) {
        int size = list == null ? 0 : list.size();

        if (isRefresh) {

            adapter.setNewData(list);
            binding.allRv.smoothScrollToPosition(0);

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


    private void setIconData(boolean isRefresh, List<HomeIconProductBean.DataBean.VideoBean> list) {
        int size = list == null ? 0 : list.size();

        if (isRefresh) {

            iconAdapter.setNewData(list);
            binding.iconRv.smoothScrollToPosition(0);

        } else {

            if (size > 0) {
                iconAdapter.addData(list);
            }

        }

        if (size < iconPageSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            iconAdapter.loadMoreEnd(isRefresh);
        } else {
            iconAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {


        if (isSearch) {
            page = 1;
            adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
//        Log.e(TAG, "search onRefresh");
            search(true);
        } else {
            iconPage = 1;
            iconAdapter.setEnableLoadMore(false);
            iconProductList(oneTypeId, twoTypeId, true);
        }
    }

}