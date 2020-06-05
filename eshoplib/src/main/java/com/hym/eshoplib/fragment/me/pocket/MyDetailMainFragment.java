package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseTabFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 钱包明细
 * <p>
 * otherTips
 */

public class MyDetailMainFragment extends BaseTabFragment{
    public static MyDetailMainFragment newInstance(Bundle args) {
        MyDetailMainFragment fragment = new MyDetailMainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    MyLogListFragment fragment;
    @Override
    public SupportFragment[] getSupportFragments() {
        SupportFragment[] fragments=new SupportFragment[1];
        fragment= MyLogListFragment.newInstance(getArguments());
        fragments[0]= fragment;
        return fragments;
    }

    @Override
    public List<Class<? extends SupportFragment>> getClasses() {
        List<Class<? extends SupportFragment>> classes=new ArrayList<>();
        classes.add( MyLogListFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity>tabs=new ArrayList<>();
        tabs.add(new TabEntity(getResources().getString(R.string.All), R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("收入", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("支出", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public void doLogic() {
        getTabLayout().setTextsize(12);
        getTabLayout().setIndicatorMargin(20,7,20,7);
        showBackButton();
        setTitle("明细");
        getTabLayout().setCurrentTab(getArguments().getInt("type",0));

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean isOneFragment() {
        return true;
    }

    @Override
    public void tabChange(int position) {
        super.tabChange(position);
        fragment.changeType(position);
    }
}
