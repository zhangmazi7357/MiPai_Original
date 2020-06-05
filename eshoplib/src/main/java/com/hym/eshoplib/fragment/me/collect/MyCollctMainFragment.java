package com.hym.eshoplib.fragment.me.collect;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseTabViewPagerFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/7/27.
 * <p>
 * Description 我的收藏
 * <p>
 * otherTips
 */

public class MyCollctMainFragment extends BaseTabViewPagerFragment {


    public static MyCollctMainFragment newInstance(Bundle args) {
        MyCollctMainFragment fragment = new MyCollctMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Fragment[] fragments = new Fragment[2];

    @Override
    public Fragment[] getSupportFragments() {
        fragments[0] =  MyColledProductionListFragment.newInstance(new Bundle());
        fragments[1] =  MyCollectShopFragment.newInstance(new Bundle());
        return fragments;
    }


    @Override
    public List<Class<? extends Fragment>> getClasses() {
        List<Class<? extends Fragment>> classes = new ArrayList<>();
        classes.add(MyColledProductionListFragment.class);
        classes.add(MyCollectShopFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity("产品", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("工作室", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("我的收藏");
        getTabLayout().setIndicatorMargin(ScreenUtil.dip2px(_mActivity,20),0,ScreenUtil.dip2px(_mActivity,20),0);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


}
