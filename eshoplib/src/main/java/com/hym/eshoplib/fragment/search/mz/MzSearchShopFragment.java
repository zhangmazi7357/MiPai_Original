package com.hym.eshoplib.fragment.search.mz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.databinding.FragmentMzSearchShopBinding;
import com.hym.eshoplib.fragment.search.mz.model.MzSearchAllModel;
import com.hym.eshoplib.fragment.search.mz.viewmodel.MzSearchViewModel;

/**
 * mz 搜索结果 工作室 Fragment ;
 */
public class MzSearchShopFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentMzSearchShopBinding binding;
    private MzSearchViewModel viewModel;

    private String TAG = "MzSearchShopFragment";

    private int page = 1;
    private int totalPage = 1;

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


    /**
     * 监听 搜索内容
     * 智能排序
     * 类型
     * 除了页码 如果搜索条件变化了 page 赋值 =1 刷新页面 ;
     */
    private void itemChange() {

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

                                search(true);

                            }
                        });


        // 监听类型 只管  全部 ;
        Integer value = viewModel.getType().getValue();
        if (value == 1) {
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
        binding.shopRv.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));

        

    }

    private void search(boolean isRefresh) {


        viewModel.search(page)
                .observe(this, new Observer<MzSearchAllModel>() {
                    @Override
                    public void onChanged(MzSearchAllModel data) {
                        binding.swipe.setRefreshing(false);

                        Log.e(TAG, "onChanged: " + JSONObject.toJSONString(data));
                        totalPage = Integer.parseInt(data.getData().getTotalpage());

//                        setData(isRefresh, data.getData().getInfo());


                    }
                });


    }


    @Override
    public void onRefresh() {

    }
}