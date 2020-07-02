package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.adapter.ListDropDownAdapter;
import com.hym.eshoplib.adapter.MeiTuanAdapter;
import com.hym.eshoplib.adapter.MeituanHeaderAdapter;
import com.hym.eshoplib.bean.city.CityBean;
import com.hym.eshoplib.bean.city.CityHeaderBean;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.bean.goods.CategoryListBean;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.widgets.callback.SuspensionDecoration;
import com.hym.eshoplib.widgets.sidebar.bean.BaseIndexPinyinBean;
import com.hym.eshoplib.widgets.sidebar.widget.SuperSideBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.widgets.view.DropDownMenuUpdate;
import constant.StringConstants;

/**
 * Created by 胡彦明 on 2018/2/23.
 * <p>
 * Description 工作室列表
 * <p>
 * otherTips
 */

public class ShopListFragment extends BaseKitFragment {
    @BindView(R.id.dropDownMenu)
    DropDownMenuUpdate dropDownMenu;
    Unbinder unbinder;
    private ArrayList<String> headers = new ArrayList<>();
    private List<String> order_by;//排序
    private List<CategoryListBean.DataBean> filters_list;//筛选
    private List<View> popupViews = new ArrayList<>();
    private ListDropDownAdapter priceAdapter, categoryAdapter;
    ShopSourceListFragment fragment;
    String region_id = "";//城市id
    String category_id = "";//工作室类型id，可能是一级id也可能是子服务_id
    String order = "";//排序类别-非必须(1：时间，2：价格，3：好频率，4：星级，默认时间)
    String sort = "";//排序-非必须（0：升序，1：降序，默认降序）
    TextView tv_title;
    int current_type = 1;//当前工作室类型 按首页功能按键排序
    RecyclerView rv_city;//
    RecyclerView rv_city_1;//城市列表省级
    RecyclerView rv_city_2;//城市列表市级
    BaseListAdapter<ServerCityBean.DataBean.InfoBean> adapter_1;
    BaseListAdapter<ServerCityBean.DataBean.InfoBean> adapter_2;
    SuperSideBar superSideBar;
    TextView superTvHint;
    private MeiTuanAdapter meiTuanAdapter;
    private MeituanHeaderAdapter headerAdapter;
    private SuspensionDecoration mDecoration;
    private LinearLayoutManager layoutManager;

    private List<CityBean> mDatas = new ArrayList<>();
    // 头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    // 设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //城市
    private List<ServerCityBean.DataBean.InfoBean> citys;//一级
    private List<ServerCityBean.DataBean.InfoBean> citys2;//二级
    //演员更多 下拉
    TextView tv_male, tv_famale;
    EditText et_min_age, et_max_age;
    SuperButton btn_clear;
    Button btn_confirm;
    String gender, age_min, age_max;

    public static ShopListFragment newInstance(Bundle args) {
        ShopListFragment fragment = new ShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_list;
    }

    @Override
    public void doLogic() {
        showBackButton();
        //String region_name= SharePreferenceUtil.getStringData(_mActivity,"region_name");
        //region_id=SharePreferenceUtil.getStringData(_mActivity,"region_id");
//        if(TextUtils.isEmpty(region_name)){
//            headers.add("地区");
//        }else {
//            headers.add(region_name);
//        }
        headers.add("全国");
        headers.add("智能排序");
        current_type = getArguments().getInt("type", 1);
        category_id = current_type + "";
        switch (current_type) {
            case 1:
                //文案策划
                tv_title = setTitle(StringConstants.tab_1);
                headers.add("全部");
                break;
            case 2:
                //导演（无筛选）
                tv_title = setTitle(StringConstants.tab_2);
                break;
            case 3:
                //摄像师
                tv_title = setTitle(StringConstants.tab_3);
                headers.add("全部");
                break;
            case 4:
                //剪辑师
                tv_title = setTitle(StringConstants.tab_4);
                headers.add("全部");
                break;
            case 5:
                //影视制作团队
                tv_title = setTitle(StringConstants.tab_5);
                headers.add("全部");
                break;
            case 6:
                //三维动画师（无筛选）
                tv_title = setTitle(StringConstants.tab_6);
                break;
            case 7:
                //平面设计师
                headers.add("全部");
                tv_title = setTitle(StringConstants.tab_7);
                break;
            case 8:
                //图片摄影师
                headers.add("全部");
                tv_title = setTitle(StringConstants.tab_8);
                break;
            case 46:
                headers.add("全部");
                headers.add("更多");
                tv_title = setTitle(StringConstants.tab_9);
                break;
            case 40:
                headers.add("全部");
                tv_title = setTitle(StringConstants.tab_10);
                break;
        }


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //有筛选项则获取筛选
        if (current_type != 2 && current_type != 6) {
            getFilterList();
        } else {
            initSource();
        }

    }

    //获取筛选列表
    public void getFilterList() {
        GoodsApi.getCategory(current_type + "", new ResponseImpl<CategoryListBean>() {
            @Override
            public void onSuccess(CategoryListBean data) {
                filters_list = data.getData();
                initSource();
            }
        }, CategoryListBean.class);

    }

    //初始化数据
    private void initSource() {
        try {
            //城市
            final View view1 = LayoutInflater.from(_mActivity).inflate(R.layout.fragment_select_city_2, null, false);
            rv_city_1 = view1.findViewById(R.id.rv_list_1);
            rv_city_2 = view1.findViewById(R.id.rv_list_2);
//            rv_city = view1.findViewById(R.id.super_recycle_view);
//            superSideBar = view1.findViewById(R.id.super_side_bar);
//            superTvHint = view1.findViewById(R.id.super_tv_hint);
//            layoutManager = new LinearLayoutManager(_mActivity);
//            rv_city.setLayoutManager(layoutManager);
            // initAdapter();
            //initData();
            initAdapter2();
            intitData2();

            //智能排序
            final ListView view2 = new ListView(_mActivity);
            view2.setDividerHeight(0);
            order_by = getDataByType(1);
            priceAdapter = new ListDropDownAdapter(_mActivity, order_by);
            view2.setAdapter(priceAdapter);
            //筛选
            final ListView view3 = new ListView(_mActivity);
            view3.setDividerHeight(0);
            categoryAdapter = new ListDropDownAdapter(_mActivity, getDataByType(2));
            view3.setAdapter(categoryAdapter);
            //演员的更多
            View view4 = LayoutInflater.from(_mActivity).inflate(R.layout.view_actor_more, null, false);
            tv_male = view4.findViewById(R.id.tv_male);
            tv_famale = view4.findViewById(R.id.tv_famale);
            et_min_age = view4.findViewById(R.id.et_min_age);
            et_max_age = view4.findViewById(R.id.et_max_age);
            btn_clear = view4.findViewById(R.id.btn_clear);
            btn_confirm = view4.findViewById(R.id.btn_confirm);
            tv_male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv_male.isSelected()) {
                        return;
                    }
                    tv_male.setSelected(true);
                    tv_famale.setSelected(false);
                    tv_male.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                    tv_famale.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));


                }
            });
            tv_famale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv_famale.isSelected()) {
                        return;
                    }
                    tv_famale.setSelected(true);
                    tv_male.setSelected(false);
                    tv_famale.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                    tv_male.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                }
            });
            btn_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_famale.setSelected(false);
                    tv_male.setSelected(false);
                    tv_famale.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                    tv_male.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                    et_min_age.getText().clear();
                    et_max_age.getText().clear();
                    gender = "";
                    age_min = "";
                    age_max = "";
                }
            });
            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv_male.isSelected()) {
                        gender = "1";
                    }
                    if (tv_famale.isSelected()) {
                        gender = "2";
                    }
                    age_min = et_min_age.getText().toString();
                    age_max = et_max_age.getText().toString();
                    dropDownMenu.closeMenu();
                    goSearch();

                }
            });
            //init popupViews
            popupViews.add(view1);
            popupViews.add(view2);
            if (headers.size() >= 3) {
                //有筛选才加入
                popupViews.add(view3);
            }
            if (headers.size() >= 4) {
                popupViews.add(view4);
            }
            //Logger.d("popsize="+popupViews.size()+",,header="+headers.size());
            view2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //order=排序类别-非必须(1：时间，2：价格，3：好频率，4：星级，默认时间)
                    //sort= 排序-非必须（0：升序，1：降序，默认降序）
                    priceAdapter.setCheckItem(position);
                    switch (position) {
                        case 0:
                            //智能排序
                            dropDownMenu.setTabText(headers.get(1));
                            order = "";
                            sort = "";
                            break;
                        case 1:
                            //价格降序
                            order = "2";
                            sort = "1";
                            dropDownMenu.setTabText(order_by.get(position));
                            break;
                        case 2:
                            //价格升序
                            order = "2";
                            sort = "0";
                            dropDownMenu.setTabText(order_by.get(position));
                            break;
                        case 3:
                            //好评优先
                            order = "3";
                            sort = "1";
                            dropDownMenu.setTabText(order_by.get(position));
                            break;
                        case 4:
                            //星级最高
                            order = "4";
                            sort = "1";
                            dropDownMenu.setTabText(order_by.get(position));
                            break;
                    }
                    dropDownMenu.closeMenu();
                    goSearch();
                }
            });
            view3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    categoryAdapter.setCheckItem(position);
                    if (position == 0) {
                        dropDownMenu.setTabText(headers.get(2));
                        category_id = current_type + "";
                    } else {
//                        String memo=TextUtils.isEmpty(filters_list.get(position-1).getMemo())?"":filters_list.get(position-1).getMemo();
                        dropDownMenu.setTabText(filters_list.get(position - 1).getCategory_name());
                        category_id = filters_list.get(position - 1).getCategory_id();
                    }
                    dropDownMenu.closeMenu();
                    goSearch();
                }
            });
            //init context view
            View contentView = LayoutInflater.from(_mActivity).inflate(R.layout.layout_flcontainer, null, false);
            //init dropdownview
            dropDownMenu.setDropDownMenu(headers, popupViews, contentView);
            Bundle bundle = new Bundle();
            bundle.putInt("type", current_type);
            //fragment = ShopSourceListFragment.newInstance(getArguments());
            fragment = ShopSourceListFragment.newInstance(bundle);
            loadRootFragment(R.id.fl_fragment_container, fragment);
            goSearch();

        } catch (Exception e) {
            Logger.d(e.toString());
        }
    }


    //设置城市列表左右
    public int select_position = 0;

    private void initAdapter2() {
        rv_city_1.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_city_2.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter_1 = new BaseListAdapter<ServerCityBean.DataBean.InfoBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ServerCityBean.DataBean.InfoBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(item.getRegion_name() + "");
                if (select_position == position) {
                    text.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                } else {
                    text.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                }

            }

        };
        adapter_1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                select_position = position;
                adapter_1.notifyDataSetChanged();
                if (position == 0) {
                    region_id = "";
                    dropDownMenu.setTabText("全国");
                    dropDownMenu.closeMenu();
                    goSearch();
                } else {
                    String pid = adapter_1.getData().get(position).getRegion_id();
                    ShopApi.getCityList2(pid, new ResponseImpl<ServerCityBean>() {
                        @Override
                        public void onSuccess(ServerCityBean data) {
                            adapter_2.setNewData(data.getData().getInfo());

                        }
                    }, ServerCityBean.class);

                }
            }
        });
        rv_city_1.setAdapter(adapter_1);
        adapter_2 = new BaseListAdapter<ServerCityBean.DataBean.InfoBean>(R.layout.item_check, null) {
            @Override
            public void handleView(BaseViewHolder helper, ServerCityBean.DataBean.InfoBean item, int position) {
                TextView text = helper.getView(R.id.text);
                text.setText(item.getRegion_name() + "");
                if (region_id.equals(item.getRegion_id())) {
                    text.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                } else {
                    text.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
                }
            }
        };
        adapter_2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                region_id = adapter_2.getData().get(position).getRegion_id();
                adapter_2.notifyDataSetChanged();
                dropDownMenu.setTabText(adapter_2.getData().get(position).getRegion_name());
                dropDownMenu.closeMenu();
                goSearch();
            }
        });
        rv_city_2.setAdapter(adapter_2);
    }

    private void intitData2() {
        //1级城市列表
        ShopApi.getCityList2("", new ResponseImpl<ServerCityBean>() {
            @Override
            public void onSuccess(ServerCityBean data) {
                citys = new ArrayList<ServerCityBean.DataBean.InfoBean>();
                ServerCityBean.DataBean.InfoBean bean = new ServerCityBean.DataBean.InfoBean();
                bean.setRegion_name("全国");
                citys.add(bean);
                citys.addAll(data.getData().getInfo());
                adapter_1.setNewData(citys);

            }
        }, ServerCityBean.class);
    }


    private void goSearch() {
        fragment.search(region_id, order, sort, category_id, gender, age_min, age_max);
    }

    public ArrayList<String> getDataByType(int type) {
        ArrayList<String> data = new ArrayList<>();
        switch (type) {
            case 1:
                //智能排序
                data.add("智能排序");
                data.add("价格降序");
                data.add("价格升序");
                data.add("好评优先");
                data.add("星级最高");
                break;
            case 2:
                //筛选
                if (filters_list != null) {
                    data.add("全部");
                    for (CategoryListBean.DataBean bean : filters_list) {
                        if (!TextUtils.isEmpty(bean.getCategory_name())) {
                            String memo = bean.getMemo();
                            if (!TextUtils.isEmpty(memo)) {
                                data.add(bean.getCategory_name() + "," + memo);
                            } else {
                                data.add(bean.getCategory_name());
                            }

                        }
                    }
                }
                break;

        }

        return data;
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

    @Override
    public boolean onBackPressedSupport() {
        if (dropDownMenu.isShowing()) {
            dropDownMenu.closeMenu();
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Deprecated
    private void initAdapter() {
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        meiTuanAdapter = new MeiTuanAdapter(_mActivity, mDatas);
        meiTuanAdapter.setRegion_id(region_id);
        meiTuanAdapter.setListener(new MeiTuanAdapter.OnCityClickListener() {
            @Override
            public void onClicke(int position, String id, String name) {
                Logger.d("id=" + id + ",name=" + name);
                region_id = id;
                dropDownMenu.setTabText(name);
                dropDownMenu.closeMenu();
                headerAdapter.notifyDataSetChanged();
                goSearch();

            }
        });

        headerAdapter = new MeituanHeaderAdapter(meiTuanAdapter, _mActivity);
        rv_city.setAdapter(headerAdapter);
        mDecoration = new SuspensionDecoration(_mActivity, mDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                        getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                        getResources().getDisplayMetrics()))
                .setColorTitleFont(
                        _mActivity.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(headerAdapter.getHeaderViewCount() - mHeaderDatas.size());
        rv_city.addItemDecoration(mDecoration);// 添加字母悬浮利用添加分割线的方式
//        recyclerView.addItemDecoration(
//                SuperDivider.newBitmapDivider().setStartSkipCount(1).setEndSkipCount(0));
        superSideBar.setmPressedShowTextView(superTvHint)// 设置滑动的字母A,B,C
                .setNeedRealIndex(true)// 设置需要真实的索引
                .setmLayoutManager(layoutManager);// 设置RecyclerView的LayoutManager
    }

    //获取城市数据
    @Deprecated
    private void initData() {
        ShopApi.getCityList(new ResponseImpl<ServerCityBean>() {
            @Override
            public void onSuccess(ServerCityBean data) {
                citys = data.getData().getInfo();
                for (int i = 0; i < citys.size(); i++) {
                    CityBean weChatBean = new CityBean();
                    weChatBean.setId(citys.get(i).getRegion_id());
                    weChatBean.setName(citys.get(i).getRegion_name());
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

}
