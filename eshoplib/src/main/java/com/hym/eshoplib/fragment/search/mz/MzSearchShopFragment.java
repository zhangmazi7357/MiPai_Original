package com.hym.eshoplib.fragment.search.mz;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.databinding.FragmentMzSearchShopBinding;
import com.hym.eshoplib.fragment.search.mz.adapter.MzSearchShopAdapter;
import com.hym.eshoplib.fragment.search.mz.model.MzSearchAllModel;
import com.hym.eshoplib.fragment.search.mz.model.MzSearchShopModel;
import com.hym.eshoplib.fragment.search.mz.viewmodel.MzSearchViewModel;

import java.util.List;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.utils.common.ToastUtil;
import io.rong.common.fwlog.LogEntity;

/**
 * mz 搜索结果 工作室 Fragment ;
 */
public class MzSearchShopFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentMzSearchShopBinding binding;
    private MzSearchViewModel viewModel;

    private String TAG = "MzSearchShopFragment";

    private MzSearchShopAdapter adapter;
    private int page = 0;
    private int totalPage = 0;
    private int pageSize = 10;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMzSearchShopBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MzSearchViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        itemChange();
        initRecyclerView();
    }


    /**
     * 监听 搜索内容
     * 智能排序
     * 类型
     * 除了页码 如果搜索条件变化了 page 赋值 = 1 刷新页面 ;
     */
    private void itemChange() {


        // 监听类型 工作室 ;
        Integer value = viewModel.getType().getValue();

        if (value == 2) {



            // 搜索内容改变  去 搜索 ;
            viewModel.getContent().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    search(true);

                }
            });

            // 智能排序类型改变 去搜索 ;
            viewModel.getSearchSortType().observe(getViewLifecycleOwner(),
                    new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {

                            search(true);

                        }
                    });

            //城市 ;
            viewModel.getRegionId().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    search(true);
                }
            });


            viewModel.getType().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {

                    search(true);
                }
            });

        }


    }


    private void initRecyclerView() {
        binding.swipe.setOnRefreshListener(this);
        binding.shopRv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MzSearchShopAdapter(null);

//        binding.shopRv.setAdapter(adapter);
        adapter.bindToRecyclerView(binding.shopRv);

        // 设置空布局
        adapter.setEmptyView(R.layout.mz_invite_empty_layout, binding.shopRv);
        // 取消掉默认的第一次 就loadMore ;
        adapter.disableLoadMoreIfNotFullPage();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                // TODO   , 工作室 contentId  和 工作室类型 ;
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,
                        ActionActivity.Action_ShopDetail);

                MzSearchShopModel.DataBean.InfoBean item = (MzSearchShopModel.DataBean.InfoBean) adapter.getItem(position);

                // bundle.putInt("type",getArguments().getInt("type",1));//工作室类型，对应首页

                bundle.putString("id", item.getContent_id());

                // bundle.putInt("type", type);

                ActionActivity.start(getActivity(), bundle);


            }
        });

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
        }, binding.shopRv);

        // 今天先刷新一下;
        //  onRefresh();

    }

    private void search(boolean isRefresh) {

        viewModel.searchShop(page)
                .observe(getViewLifecycleOwner(), new Observer<MzSearchShopModel>() {
                    @Override
                    public void onChanged(MzSearchShopModel data) {
                        binding.swipe.setRefreshing(false);

                        String totalpage = data.getData().getTotalpage();
                        if (!TextUtils.isEmpty(totalpage)) {
                            totalPage = Integer.parseInt(totalpage);
                        }

                        setData(isRefresh, data.getData().getInfo());


                    }
                });


    }

    private void setData(boolean isRefresh, List<MzSearchShopModel.DataBean.InfoBean> list) {
        int size = list == null ? 0 : list.size();

        if (isRefresh) {

            adapter.setNewData(list);
            binding.shopRv.smoothScrollToPosition(0);

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
        search(true);
    }


}