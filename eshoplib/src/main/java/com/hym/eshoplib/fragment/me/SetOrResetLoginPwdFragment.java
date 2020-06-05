package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.hym.loginmodule.fragment.base.BaseLoginFragment;
import com.hym.loginmodule.http.LoginApi;

import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 设置或修改登录密码
 * <p>
 * otherTips
 */

public class SetOrResetLoginPwdFragment extends BaseLoginFragment{
    public static SetOrResetLoginPwdFragment newInstance(Bundle args) {
        SetOrResetLoginPwdFragment fragment = new SetOrResetLoginPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean(et1.getText().toString(), "3");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        showBackButton();
        String title=getArguments().getString("title","");
        setTitle(title);
        ll4.setVisibility(View.VISIBLE);
        et1.setHint("请输入注册手机号");
        if(title.equals("设置登录密码")){
            tv3.setText("密码");
        } else {
            tv3.setText("新密码");
        }

        et3.setHint("请输入6-20位新密码");
        et3.setSingleLine();
        et3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et4.setHint("确认新密码");
        tvSubFunction.setVisibility(View.GONE);
        btnConfirm.setText("确认");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=et1.getText().toString();
                String code=et2.getText().toString();
                String newPwd=et3.getText().toString();
                String pwd2= et4.getText().toString();
                if(phone.length()!=11){
                    ToastUtil.toast("请输入注册手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                if(newPwd.length()<6||newPwd.length()>20){
                    ToastUtil.toast("请输入6-20位新密码");
                    return;
                }
                if(!newPwd.equals(pwd2)){
                    ToastUtil.toast("两次密码输入不一致");
                    return;
                }
                LoginApi.setPassword(getContext(), phone, "", code, newPwd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("请妥善保管您的密码别告诉任何人哦");
                        pop();
                    }
                }, Object.class);
            }
        });

    }
}
