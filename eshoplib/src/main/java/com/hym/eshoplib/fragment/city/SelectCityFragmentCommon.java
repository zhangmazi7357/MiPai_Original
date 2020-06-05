package com.hym.eshoplib.fragment.city;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.adapter.MeiTuanAdapter;
import com.hym.eshoplib.adapter.MeituanHeaderAdapter;
import com.hym.eshoplib.bean.city.CityBean;
import com.hym.eshoplib.bean.city.CityHeaderBean;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.widgets.callback.SuspensionDecoration;
import com.hym.eshoplib.widgets.sidebar.bean.BaseIndexPinyinBean;
import com.hym.eshoplib.widgets.sidebar.widget.SuperSideBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/8/28.
 * <p>
 * Description 不带定位的选择城市
 * <p>
 * otherTips
 */

public class SelectCityFragmentCommon extends BaseKitFragment {
    @BindView(R.id.super_recycle_view)
    RecyclerView superRecycleView;
    @BindView(R.id.super_side_bar)
    SuperSideBar superSideBar;
    @BindView(R.id.super_tv_hint)
    TextView superTvHint;
    Unbinder unbinder;
    LinearLayoutManager layoutManager;
    private List<CityBean> mDatas = new ArrayList<>();
    // 头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    // 设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    private MeiTuanAdapter meiTuanAdapter;
    private MeituanHeaderAdapter headerAdapter;
    private SuspensionDecoration mDecoration;

    public static SelectCityFragmentCommon newInstance(Bundle args) {
        SelectCityFragmentCommon fragment = new SelectCityFragmentCommon();
        fragment.setArguments(args);
        return fragment;
    }

    List<ServerCityBean.DataBean.InfoBean> citys;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_select_city;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("选择城市");
        layoutManager = new LinearLayoutManager(_mActivity);
        superRecycleView.setLayoutManager(layoutManager);
        initAdapter();

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getCityList(new ResponseImpl<ServerCityBean>() {
            @Override
            public void onSuccess(ServerCityBean data) {
                citys = data.getData().getInfo();
                for (int i = 0; i < citys.size(); i++) {
                    CityBean weChatBean = new CityBean();
                    weChatBean.setName(data.getData().getInfo().get(i).getRegion_name());
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
        meiTuanAdapter = new MeiTuanAdapter(_mActivity, mDatas);
        meiTuanAdapter.setListener(new MeiTuanAdapter.OnCityClickListener() {
            @Override
            public void onClicke(int position, String id,String name) {
                //headerAdapter.notifyDataSetChanged();

            }
        });

        headerAdapter = new MeituanHeaderAdapter(meiTuanAdapter, _mActivity);
        superRecycleView.setAdapter(headerAdapter);

        mDecoration = new SuspensionDecoration(_mActivity, mDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                        getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                        getResources().getDisplayMetrics()))
                .setColorTitleFont(
                        _mActivity.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(headerAdapter.getHeaderViewCount() - mHeaderDatas.size());
        superRecycleView.addItemDecoration(mDecoration);// 添加字母悬浮利用添加分割线的方式
        superSideBar.setmPressedShowTextView(superTvHint)// 设置滑动的字母A,B,C
                .setNeedRealIndex(true)// 设置需要真实的索引
                .setmLayoutManager(layoutManager);// 设置RecyclerView的LayoutManager
        meiTuanAdapter.setListener(new MeiTuanAdapter.OnCityClickListener() {
            @Override
            public void onClicke(int position,String id, String name) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",findData(name));
                setFragmentResult(RESULT_OK,bundle);
                pop();

            }
        });

    }

    private Serializable findData(String name) {
        ServerCityBean.DataBean.InfoBean city=null;
        for(ServerCityBean.DataBean.InfoBean bean:citys){
            if(bean.getRegion_name().trim().equals(name)){
                city=bean;
                break;
            }
        }
        return city;
    }


}
