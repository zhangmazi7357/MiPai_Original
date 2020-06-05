package com.hym.eshoplib.fragment.shop;

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
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description 工作室信息 两个tab
 * <p>
 * otherTips
 */

public class ShopInfoPagerFragment extends BaseTabViewPagerFragment{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Fragment[] getFragments() {
        return fragments;
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    public static ShopInfoPagerFragment newInstance(Bundle args) {
        ShopInfoPagerFragment fragment = new ShopInfoPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    Fragment[] fragments = new Fragment[2];
    @Override
    public Fragment[] getSupportFragments() {
        fragments[0]= ShopInfoFragment.newInstance(getArguments());
        String cateId = getArguments().getString("type");
        Bundle bundle = new Bundle();
        bundle.putString("cateId",cateId);
        fragments[1]= ShopProductListFragment.newInstance(bundle);
        return fragments;
    }

    @Override
    public List<Class<? extends Fragment>> getClasses() {
        List<Class<? extends Fragment>> classes = new ArrayList<>();
        classes.add(ShopInfoFragment.class);
        classes.add(ShopProductListFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity("工作室信息", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("产品", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("设置工作室信息");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
