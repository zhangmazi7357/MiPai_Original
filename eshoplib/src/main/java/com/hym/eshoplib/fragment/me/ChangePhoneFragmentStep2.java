package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.hym.eshoplib.http.me.MeApi;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;

import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 更换手机号
 * <p>
 * otherTips
 */

public class ChangePhoneFragmentStep2 extends BaseLoginFragment{
    public static ChangePhoneFragmentStep2 newInstance(Bundle args) {
        ChangePhoneFragmentStep2 fragment = new ChangePhoneFragmentStep2();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean(et1.getText().toString(), "8");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        showBackButton();
        setTitle("更换手机号");
        tv1.setText("新手机号");
        et1.setHint("请输入要更换的手机号");
        tv3.setText("登录密码");
        et3.setHint("请输入登录密码");
        et3.setSingleLine();
        et3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvSubFunction.setVisibility(View.GONE);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                String phone=et1.getText().toString();
                String code=et2.getText().toString();
                String pwd=et3.getText().toString();
                if(phone.length()!=11){
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.toast("请输入登录密码");
                    return;
                }
                MeApi.ResetPhone(phone, code, pwd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("修改成功,下次登录请使用修改后的手机号");
                       _mActivity.finish();
                    }
                },Object.class);
            }
        });
        btnConfirm.setText("确认");

    }
}
