package com.hym.eshoplib.fragment.search;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseTabViewPagerFragment;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/7/27.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class SearchResultFragment extends BaseTabViewPagerFragment {
    @BindView(R.id.head_status_bar)
    View headStatusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_toolbar_right)
    ImageView ivToolbarRight;
    @BindView(R.id.ll_toolbar_bg)
    LinearLayout llToolbarBg;
    Unbinder unbinder;

    public static SearchResultFragment newInstance(Bundle args) {
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Fragment[] fragments = new Fragment[3];

    @Override
    public Fragment[] getSupportFragments() {
        fragments[0] = SearchImageListFragment.newInstance(getArguments());
        fragments[1] = SearchVadieoListFragment.newInstance(getArguments());
        fragments[2] = SearchShopListFragment.newInstance(getArguments());
        return fragments;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public List<Class<? extends Fragment>> getClasses() {
        List<Class<? extends Fragment>> classes = new ArrayList<>();
        classes.add(SearchImageListFragment.class);
        classes.add(SearchVadieoListFragment.class);
        classes.add(SearchShopListFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity("拍照片", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("拍视频", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("工作室", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public void doLogic() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        //getTabLayout().setIndicatorMargin(ScreenUtil.dip2px(_mActivity,20),0,ScreenUtil.dip2px(_mActivity,20),0);
        etSearch.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.GONE);
        etSearch.setText(getArguments().getString("keywords",""));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                ( (SearchShopListFragment)fragments[0]).search(etSearch.getText().toString());
                ( (SearchVadieoListFragment)fragments[1]).search(etSearch.getText().toString());
                ( (SearchImageListFragment)fragments[2]).search(etSearch.getText().toString());
                hideSoftInput();
                return false;
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
}
