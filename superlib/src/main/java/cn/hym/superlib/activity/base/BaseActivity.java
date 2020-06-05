package cn.hym.superlib.activity.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import cn.hym.superlib.R;
import cn.hym.superlib.config.CommonConfig;
import cn.hym.superlib.languages.utils.AppLanguageUtils;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description 与具体业务无关的最底层的BaseActivity 需要继承后才可以使用，不建议直接使用。
 * <p>
 * OtherTips
 */

public abstract class BaseActivity extends SupportActivity {
    private View baseView;//在使用自定义toolbar时候的根布局 =toolBarView+childView
    public LinearLayout ll_root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) {
            throw new RuntimeException("layoutResID==0 have u create your layout?");
        }

        if (showToolBar() && getToolBarResId() > 0) {
            //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
            baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null, false);//根布局
            ll_root = (LinearLayout) baseView.findViewById(R.id.ll_base_root);
            ViewStub mVs_toolbar = (ViewStub) baseView.findViewById(R.id.vs_toolbar);//toolbar容器
            FrameLayout fl_container = (FrameLayout) baseView.findViewById(R.id.fl_container);//子布局容器
            mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
            mVs_toolbar.inflate();//填充toolbar
            LayoutInflater.from(this).inflate(layoutResID, fl_container, true);//子布局
            setContentView(baseView);
        } else {
            //不显示通用toolbar
            super.setContentView(layoutResID);

        }

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new RuntimeException("please use setContentView(@LayoutRes int layoutResID) instead");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseView = null;
    }

    //是否使用多语言
    @Override
    protected void attachBaseContext(Context newBase) {
        if (CommonConfig.USE_MUILTY_LANGUAGES) {
            super.attachBaseContext(AppLanguageUtils.attachBaseContext(newBase, newBase.getString(R.string.app_language_pref_key)));
        } else {
            super.attachBaseContext(newBase);
        }
    }


    //获取contentView 资源id
    public abstract int getContentViewResId();

    //是否显示通用toolBar
    public abstract boolean showToolBar();

    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
    public abstract int getToolBarResId();
}
