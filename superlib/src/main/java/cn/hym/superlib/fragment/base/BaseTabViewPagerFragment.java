package cn.hym.superlib.fragment.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.R;

/**
 * Created by 胡彦明 on 2018/3/12.
 * <p>
 * Description 上边是导航栏 下边是ViewPager+多个fragment的 tab类型的fragment
 * <p>
 * otherTips
 */

public abstract class BaseTabViewPagerFragment extends BaseKitFragment {
    CommonTabLayout tabLayout;
    ViewPager vp;
    private Fragment[] mFragments;//要加载的fragment,
    private ArrayList<CustomTabEntity> tabs;//tab数量，tab必须与fragment，的数量必须一致
    private List<Class<? extends Fragment>> mFragmensClasses;//用户恢复fragment类对象
    private int current_index = 0;//当前选中tab
    private static final String SAVED_KEY_TAB = "cunrrent_tab_index";
    FrameLayout fl_fragments;
    public View statusspace;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_base_tab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = getSupportFragments();
        mFragmensClasses = getClasses();
        if (mFragments == null || mFragmensClasses == null || mFragments.length != mFragmensClasses.size()) {
            throw new IllegalArgumentException("fragment集合大小必须与class集合大小相等");
        }
        tabs = getTabs();
        if (tabs == null || tabs.size() != mFragments.length) {
            throw new IllegalArgumentException("tab数量必须与fragment数量相等");
        }
        if (mFragments.length == 0) {
            throw new IllegalArgumentException("fragments不能为空");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //保存当前tab用于恢复
        // Logger.d("意外销毁时进行保存");
        outState.putInt(SAVED_KEY_TAB, current_index);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initViews(View view, @Nullable Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        fl_fragments = (FrameLayout) view.findViewById(R.id.fl_fragments);
        fl_fragments.setVisibility(View.GONE);
        statusspace = view.findViewById(R.id.head_status_bar);
        tabLayout = (CommonTabLayout) view.findViewById(R.id.tablayout);
        vp = view.findViewById(R.id.viewPager);
        if (savedInstanceState == null) {
            initTab();
        }
    }

    private void initTab() {
        Logger.d("initTab");
        if (tabLayout == null || tabLayout.getTabCount() > 0) {
            return;
        }
        tabLayout.setTabData(tabs);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initViewPager();
    }

    private void initViewPager() {
        vp.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setOffscreenPageLimit(3);
        vp.setCurrentItem(getCurrent_index());
    }

    public int getCurrent_index() {
        return 0;

    }

    public CommonTabLayout getTabLayout() {
        return tabLayout;
    }

    public abstract Fragment[] getSupportFragments();

    public abstract List<Class<? extends Fragment>> getClasses();

    public abstract ArrayList<CustomTabEntity> getTabs();

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position).getTabTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

    }

}
