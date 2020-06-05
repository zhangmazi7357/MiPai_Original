package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.BuildConfig;
import com.hym.eshoplib.R;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.hym.loginmodule.constans.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.WebFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/11/9.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class AboutMipaiFragment extends BaseKitFragment {
    @BindView(R.id.tv_1)
    SuperTextView tv1;
    @BindView(R.id.tv_2)
    SuperTextView tv2;
    @BindView(R.id.tv_3)
    SuperTextView tv3;
    @BindView(R.id.tv_4)
    SuperTextView tv4;
    @BindView(R.id.tv_5)
    SuperTextView tv5;
    @BindView(R.id.tv_6)
    SuperTextView tv6;
    Unbinder unbinder;

    public static AboutMipaiFragment newInstance(Bundle args) {
        AboutMipaiFragment fragment = new AboutMipaiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_about_mipai;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("关于觅拍");
        tv3.setRightString("版本号 "+ BuildConfig.VERSION_NAME);

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

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                Bundle bundle2= BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login,LoginMainActivity.Action_web);
                bundle2.putString("url", Config.url_3);
                bundle2.putString("title", "关于觅拍");
                start(WebFragment.newInstance(bundle2));
                break;
            case R.id.tv_2:
                //意见反馈
                start(UserFeedBackFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_3:
                //版本更新
                break;
            case R.id.tv_4:
                break;
            case R.id.tv_5:
                Bundle bundle5= BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login,LoginMainActivity.Action_web);
                bundle5.putString("url", Config.url_1);
                bundle5.putString("title", "觅拍用户服务协议");
                start(WebFragment.newInstance(bundle5));
                break;
            case R.id.tv_6:
                Bundle bundle= BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login,LoginMainActivity.Action_web);
                bundle.putString("url", Config.url_2);
                bundle.putString("title", "觅拍隐私保护政策");
                start(WebFragment.newInstance(bundle));
                break;
        }
    }
}
