package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseTabViewPagerFragment;

/**
 * Created by 胡彦明 on 2018/8/17.
 * <p>
 * Description 工作室详情的3个tab
 * <p>
 * otherTips
 */

public class GoodsDetailPagerFragment extends BaseTabViewPagerFragment {
    public static GoodsDetailPagerFragment newInstance(Bundle args) {
        GoodsDetailPagerFragment fragment = new GoodsDetailPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Fragment[] fragments = new Fragment[3];

    @Override
    public Fragment[] getSupportFragments() {
        fragments[0] = ShopDetailFragment.newInstance(getArguments());
        fragments[1] = ShopVadieoListFragment.newInstance(getArguments());
        fragments[2] = ShopCommentsListFragment.newInstance(getArguments());
        return fragments;
    }

    @Override
    public List<Class<? extends Fragment>> getClasses() {
        List<Class<? extends Fragment>> classes = new ArrayList<>();
        classes.add(ShopDetailFragment.class);
        classes.add(ShopVadieoListFragment.class);
        classes.add(ShopCommentsListFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity("详情", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("产品", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("评价", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getCurrent_index() {
        return 1;
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
