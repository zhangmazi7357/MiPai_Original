package com.hym.eshoplib.fragment.search.mz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.hym.eshoplib.bean.mz.upload.ProductOneTypeBean;
import com.hym.eshoplib.bean.mz.upload.ProductTwoTypeBean;
import com.hym.eshoplib.databinding.MzActivitySearchResultBinding;
import com.hym.eshoplib.fragment.search.mz.adapter.MzSearchSortAdapter;
import com.hym.eshoplib.fragment.search.mz.viewmodel.MzSearchViewModel;
import com.hym.eshoplib.http.mz.MzNewApi;
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

    // 十大 icon  标签
    private BaseListAdapter<ProductOneTypeBean.DataBean> oneTagAdapter;
    private BaseListAdapter<ProductTwoTypeBean.DataBean> twoTagAdapter;
    private int tagPosition = 0;
    private String twoTagId = "";


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
        searchViewModel.setContent(keyWord);


        initTab();
        intDropDownMenu1();

        // tab 监听
        searchViewModel.getType().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.dropDownMenu.clearDropDownMenu();

                switch (integer) {
                    case 1:
                        intDropDownMenu1();
                        break;
                    case 2:
                        intDropDownMenu2();
                        break;
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchViewModel.setContent(s.toString());

            }
        });


    }


    private void initTab() {

        MzSearchAllFragment allFragment = new MzSearchAllFragment();
        MzSearchShopFragment shopFragment = new MzSearchShopFragment();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        containerView = LayoutInflater.from(this)
                .inflate(R.layout.mz_result_container_view,
                        null, false);

        transaction.add(R.id.mz_container, allFragment).show(allFragment);
        transaction.add(R.id.mz_container, shopFragment).hide(shopFragment);


        transaction.commit();

        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("全部"));
        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("工作室"));


        // 类型 切换
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


    private void intDropDownMenu1() {
        List<String> tabs = new ArrayList<>();
        List<View> pops = new ArrayList<>();

        tabs.add("全部");
        tabs.add("智能排序");
        tabs.add("全国");


        View viewAll = popsView_All();
        pops.add(viewAll);

        View viewSort = popsView_Sort();
        pops.add(viewSort);


        View viewCity = popsView_City();
        pops.add(viewCity);

        binding.dropDownMenu.setDropDownMenu(tabs, pops, containerView);
    }

    private void intDropDownMenu2() {
        List<String> tabs = new ArrayList<>();
        List<View> pops = new ArrayList<>();

//        tabs.add("全部");
        tabs.add("智能排序");
        tabs.add("全国");


//        View viewAll = popsView_All();
//        pops.add(viewAll);

        View viewSort = popsView_Sort();
        pops.add(viewSort);


        View viewCity = popsView_City();
        pops.add(viewCity);

        binding.dropDownMenu.setDropDownMenu(tabs, pops, containerView);
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

                // TODO  切换城市要重新搜索 ;
                searchViewModel.setRegionId(regionId);

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

                /*
                   改变智能排序类型
                   如果没有点击智能排序, liveData = null;
                 */

                Integer sortType = null;
                switch (position) {
                    case 0:
                        sortType = 1;
                        break;

                    case 1:
                        sortType = 2;
                        break;

                    case 2:
                        sortType = 3;
                        break;

                    case 3:
                        sortType = 4;
                        break;

                }

                searchViewModel.setSearchSortType(sortType);

            }
        });

        sortRv.setAdapter(adapter);

        return sortRv;
    }

    // 全部  十大 icon  ;
    private View popsView_All() {
        View root = LayoutInflater.from(this)
                .inflate(R.layout.fragment_select_city_2, null, false);

        RecyclerView oneTagRv = root.findViewById(R.id.rv_list_1);
        RecyclerView twoTagRv = root.findViewById(R.id.rv_list_2);

        oneTagRv.setLayoutManager(new LinearLayoutManager(this));
        twoTagRv.setLayoutManager(new LinearLayoutManager(this));

        oneTagAdapter = new BaseListAdapter<ProductOneTypeBean.DataBean>(R.layout.item_check,
                null) {
            @Override
            public void handleView(BaseViewHolder helper, ProductOneTypeBean.DataBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(String.valueOf(item.getTitle()));

                if (tagPosition == position) {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.mipaiTextColorSelect));

                } else {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.black));

                }

            }

        };

        oneTagRv.setAdapter(oneTagAdapter);

        oneTagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tagPosition = position;
                oneTagAdapter.notifyDataSetChanged();

                if (position == 0) {

                    binding.dropDownMenu.setTabText("全部");
                    binding.dropDownMenu.closeMenu();

                } else {

                    String oneTypeId = oneTagAdapter.getData().get(position).getOnetype_id();

                    MzNewApi.getTwoType(new ResponseImpl<ProductTwoTypeBean>() {
                        @Override
                        public void onSuccess(ProductTwoTypeBean data) {

                            List<ProductTwoTypeBean.DataBean> allList = data.getData();

                            // 过滤 二级标签
                            List<ProductTwoTypeBean.DataBean> twoTypeList = new ArrayList<>();

                            for (int i = 0; i < allList.size(); i++) {
                                ProductTwoTypeBean.DataBean dataBean = allList.get(i);

                                if (oneTypeId.equals(dataBean.getOnetype_id())) {
                                    twoTypeList.add(dataBean);
                                }

                            }

                            twoTagAdapter.setNewData(twoTypeList);

                        }
                    }, ProductTwoTypeBean.class);

                }


            }
        });


        twoTagAdapter = new BaseListAdapter<ProductTwoTypeBean.DataBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ProductTwoTypeBean.DataBean item, int position) {
                TextView text = helper.getView(R.id.text);

                text.setText(String.valueOf(item.getTitle()));

                if (twoTagId.equals(item.getTwotype_id())) {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.mipaiTextColorSelect));
                } else {
                    text.setTextColor(ContextCompat.getColor(MzSearchResultActivity.this,
                            R.color.black));
                }
            }
        };

        twoTagRv.setAdapter(twoTagAdapter);

        twoTagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                ProductTwoTypeBean.DataBean dataBean = twoTagAdapter.getData().get(position);
                twoTagAdapter.notifyDataSetChanged();
                binding.dropDownMenu.setTabText(dataBean.getTitle());
                binding.dropDownMenu.closeMenu();


                searchViewModel.setTag(dataBean);


            }
        });


        // 一级 标签;
        MzNewApi.getOneType(new ResponseImpl<ProductOneTypeBean>() {
            @Override
            public void onSuccess(ProductOneTypeBean data) {

                List<ProductOneTypeBean.DataBean> tags = new ArrayList<>();
                ProductOneTypeBean.DataBean bean = new ProductOneTypeBean.DataBean();

                bean.setTitle("全部");
                tags.add(bean);
                tags.addAll(data.getData());

                oneTagAdapter.setNewData(tags);
            }
        }, ProductOneTypeBean.class);


        return root;
    }


}