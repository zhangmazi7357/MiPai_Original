package com.hym.eshoplib.fragment.me;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.hym.loginmodule.bean.LocalTokenBean;
import com.hym.loginmodule.constans.Config;
import com.hym.loginmodule.http.LoginApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.WebFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.user.UserUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.rong.imkit.RongIM;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 设置
 * <p>
 * otherTips
 */

public class SettingFragment extends BaseKitFragment {
    @BindView(R.id.tv_1)
    SuperTextView tv1;
    @BindView(R.id.tv_2)
    SuperTextView tv2;
    @BindView(R.id.tv_3)
    SuperTextView tv3;
    @BindView(R.id.tv_4)
    SuperTextView tv4;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;

    public static SettingFragment newInstance(Bundle args) {
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("设置");


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

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:

                start(AccountFragment.newInstance(getArguments()));
                break;
            case R.id.tv_2:
                start(SettingCommonFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_3:
                // ToastUtil.toast("帮助中心有文案后添加");
                Bundle bundle = BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login, LoginMainActivity.Action_web);
                bundle.putString("url", Config.url_4);
                bundle.putString("title", "帮助中心");
                start(WebFragment.newInstance(bundle));
                break;
            case R.id.tv_4:
                //ToastUtil.toast("关于觅拍有文案后添加");
//                Bundle bundle2= BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login,LoginMainActivity.Action_web);
//                bundle2.putString("url", Config.url_3);
//                bundle2.putString("title", "关于觅拍");
//                start(WebFragment.newInstance(bundle2));
                start(AboutMipaiFragment.newInstance(new Bundle()));
                break;
            case R.id.btn_logout:

                DialogManager.getInstance().initSimpleDialog(getContext(), "提示",
                        "您确定要退出吗", "取消", "确认", new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();

                                // 退出登录 ;
                                LoginApi.logOut(_mActivity, new ResponseImpl<LocalTokenBean>() {
                                    @Override
                                    public void onSuccess(LocalTokenBean data) {
                                        UserUtil.setIsLogin(_mActivity, false);
                                        UserUtil.saveToken(_mActivity, data.getData().getLocaltoken());
                                        RongIM.getInstance().logout();
                                        SharePreferenceUtil.setStringData(_mActivity, "channelid", "");
                                        ToastUtil.toast("退出登录成功");
                                        Intent intent = new Intent(_mActivity, MainActivity.class);
                                        intent.putExtra("position", 0);
                                        SharePreferenceUtil.setBooleanData(_mActivity, "isauth", false);
                                        _mActivity.startActivity(intent);
                                        _mActivity.finish();
                                    }
                                }, LocalTokenBean.class);

                            }
                        }).show();


                break;
        }
    }

}
