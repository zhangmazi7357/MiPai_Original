package com.hym.eshoplib.fragment.me;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.loginmodule.util.GlideCacheUtil;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 通用设置
 * <p>
 * otherTips
 */

public class SettingCommonFragment extends BaseKitFragment {
    @BindView(R.id.tv_1)
    SuperTextView tv1;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_3)
    SuperTextView tv3;
    Unbinder unbinder;

    public static SettingCommonFragment newInstance(Bundle args) {
        SettingCommonFragment fragment = new SettingCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_setting_common;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("通用");
        tv3.setRightString(GlideCacheUtil.getInstance().getCacheSize(getContext()));
        switchButton.setChecked(SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true));
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SharePreferenceUtil.setBooleanData(_mActivity, "wifyautoplay", isChecked);
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


    @OnClick({R.id.tv_1, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                start(NoticeSetFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_3:

                DialogManager.getInstance().initSimpleDialog(_mActivity, "提示", "您确定要清除缓存么",
                        "取消", "确认", new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();

                                GlideCacheUtil.getInstance().clearImageAllCache(getContext());
                                tv3.setRightString("0B");

                            }
                        }).show();


                break;
        }
    }
}
