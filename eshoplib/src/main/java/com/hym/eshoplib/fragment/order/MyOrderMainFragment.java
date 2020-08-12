package com.hym.eshoplib.fragment.order;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseTabScrollFragment;
import cn.hym.superlib.utils.view.ScreenUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 我的订单主fragment
 * <p>
 * otherTips
 */

public class MyOrderMainFragment extends BaseTabScrollFragment {
    public static MyOrderMainFragment newInstance(Bundle args) {
        MyOrderMainFragment fragment = new MyOrderMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    MyOrderListFragment fragment;

    @Override
    public SupportFragment[] getSupportFragments() {
        SupportFragment[] fragments = new SupportFragment[1];
        fragment = MyOrderListFragment.newInstance(getArguments());
        fragments[0] = fragment;
        return fragments;
    }

    @Override
    public List<Class<? extends SupportFragment>> getClasses() {
        List<Class<? extends SupportFragment>> classes = new ArrayList<>();
        classes.add(MyOrderListFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity(getResources().getString(R.string.All), R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("待接受预约", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("待付款", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("待收货", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("评价", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("退款/售后", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public void doLogic() {
        int width = ScreenUtil.getScreenWidth(_mActivity);
        int height = ScreenUtil.getScreenHeight(_mActivity);
        //Logger.d("width="+width+",height="+height);
//        if(height/width>=2){
//            getTabLayout().setTextsize(10);
//        }else {
//            getTabLayout().setTextsize(12);
//        }
//        getTabLayout().setIndicatorMargin(10,7,10,7);
        showBackButton();
        setTitle(R.string.MyOrder);
        //  getTabLayout().setCurrentTab(getArguments().getInt("type",0));
    }

    @Override
    public int getSelectPosition() {
        return getArguments().getInt("type", 0);
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
