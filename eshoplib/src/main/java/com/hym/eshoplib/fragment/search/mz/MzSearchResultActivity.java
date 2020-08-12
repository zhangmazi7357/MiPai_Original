package com.hym.eshoplib.fragment.search.mz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.databinding.MzActivitySearchResultBinding;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.mz.MzConstant;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.mz.MzBaseActivity;

/**
 * mz 搜索结果;
 */
public class MzSearchResultActivity extends MzBaseActivity {


    private String TAG = "MzSearchResultActivity";
    private MzActivitySearchResultBinding binding;

    private View containerView;   // fragment的容器;

    private RecyclerView proRv;    // 省份列表
    private RecyclerView cityRv;     // 城市列表
    private BaseListAdapter<ServerCityBean.DataBean.InfoBean> proAdapter;
    private BaseListAdapter<ServerCityBean.DataBean.InfoBean> cityAdapter;

    private int selectPosition = 0;
    private int searchType = 1;                   // 搜索类型 1:产品 ，2: 工作室

    private String keyWord = "";
    private String regionId = "";           // 城市id ;

    private MzSearchViewModel searchViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MzActivitySearchResultBinding
                .inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        searchViewModel = new ViewModelProvider(this)
                .get(MzSearchViewModel.class);

        Intent intent = getIntent();
        keyWord = intent.getStringExtra(MzConstant.KEY_SEARCH_KEYWORD);
        binding.etSearch.setText(keyWord);


        initTab();
        intDropDownMenu();


    }


    @Override
    protected void onStart() {
        super.onStart();

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        itemChange();


    }


    private void initTab() {


        MzSearchAllFragment allFragment = new MzSearchAllFragment();
        MzSearchShopFragment shopFragment = new MzSearchShopFragment();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        containerView = LayoutInflater.from(this).inflate(R.layout.mz_result_container_view, null, false);
        transaction.add(R.id.mz_container, allFragment).show(allFragment);
        transaction.add(R.id.mz_container, shopFragment).hide(shopFragment);


        transaction.commit();

        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("全部"));
        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("工作室"));


        binding.mzTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                binding.dropDownMenu.closeMenu();
                switch (tab.getPosition()) {
                    case 0:

                        fragmentTransaction.show(allFragment).hide(shopFragment).commit();
                        searchType = 1;
                        break;
                    case 1:
                        fragmentTransaction.show(shopFragment).hide(allFragment).commit();
                        searchType = 2;
                        break;

                }
                searchViewModel.setType(searchType);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    private void intDropDownMenu() {
        List<String> tabs = new ArrayList<>();
        tabs.add("全国");
        tabs.add("智能排序");
        tabs.add("全部");

        List<View> pops = new ArrayList<>();

        View viewCity = popsView_City();
        pops.add(viewCity);

        View viewSort = popsView_Sort();
        pops.add(viewSort);

        View viewAll = new View(this);
        pops.add(viewAll);


        binding.dropDownMenu.setDropDownMenu(tabs, pops, containerView);


        //  4   因为还有竖向分割线 ;  单独把 全部拿出来修改一下 ;
        FrameLayout container = binding.dropDownMenu.getContainer(4);
        TextView tabAll = (TextView) container.getChildAt(0);
        tabAll.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_down), null);

        container.setOnClickListener(v -> {

            binding.dropDownMenu.closeMenu();
        });


    }


    // 全国的 View ;
    private View popsView_City() {

        View root = LayoutInflater.from(this)
                .inflate(R.layout.fragment_select_city_2, null, false);

        proRv = root.findViewById(R.id.rv_list_1);
        cityRv = root.findViewById(R.id.rv_list_2);

        proRv.setLayoutManager(new LinearLayoutManager(this));
        cityRv.setLayoutManager(new LinearLayoutManager(this));


        proAdapter = new BaseListAdapter<ServerCityBean.DataBean.InfoBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ServerCityBean.DataBean.InfoBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(String.valueOf(item.getRegion_name()));

                if (selectPosition == position) {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.mipaiTextColorSelect));

                } else {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
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
                    binding.dropDownMenu.setTabText("全国");
                    binding.dropDownMenu.closeMenu();
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
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.mipaiTextColorSelect));
                } else {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
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

                binding.dropDownMenu.setTabText(cityAdapter.getData().get(position).getRegion_name());
                binding.dropDownMenu.closeMenu();
//                goSearch();
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
        RecyclerView sortRv = new RecyclerView(this);

        sortRv.setBackgroundColor(getResources().getColor(R.color.white));
        sortRv.setLayoutManager(new LinearLayoutManager(this));


        MzSearchSortAdapter adapter = new MzSearchSortAdapter(this);

        adapter.setSearchSortClickListener(new MzSearchSortAdapter.SearchSortClickListener() {
            @Override
            public void onItemClick(int position, String sortTitle) {

                adapter.setSelectPosition(position);

                binding.dropDownMenu.setTabText(sortTitle);
                binding.dropDownMenu.closeMenu();
            }
        });

        sortRv.setAdapter(adapter);

        return sortRv;
    }


    private void itemChange() {
        searchViewModel.setContent(keyWord);

        searchViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                search();
            }
        });

        searchViewModel.getType().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });


    }


    private void search() {
        searchViewModel.search()
                .observe(this, new Observer<MzSearchResultModel>() {
            @Override
            public void onChanged(MzSearchResultModel data) {
               // type = 1
                Log.e(TAG, "onChanged: " + data);

            }
        });


    }


}