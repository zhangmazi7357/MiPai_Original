package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.hym.eshoplib.http.me.MeApi;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;

import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 设置支付密码步骤1
 * <p>
 * otherTips
 */

public class SetPayPwdFragmentStep1 extends BaseLoginFragment{
    //设置支付密码 和忘记支付密码，需要验证手机号， 修改支付密码不验证，需要验证旧密码
    int type=-1;
    public static SetPayPwdFragmentStep1 newInstance(Bundle args) {
        SetPayPwdFragmentStep1 fragment = new SetPayPwdFragmentStep1();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean(et1.getText().toString(), "9");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        type=getArguments().getInt("type",-1);
        showBackButton();
        //// 1设置支付密码，2修改支付密码 3，忘记支付密码
        switch (type){
            case 1:
                setTitle("设置支付密码");
                break;
//            case 2:
//                setTitle("修改支付密码");
//                break;
            case 3:
                setTitle("忘记支付密码");
                break;
        }
        ll3.setVisibility(View.GONE);
        tvSubFunction.setVisibility(View.GONE);
        btnConfirm.setText("下一步");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //去下一步

                if(et1.getText().toString().length()!=11){
                    ToastUtil.toast("请输入正确手机号");
                    return;
                }
                if(TextUtils.isEmpty(et2.getText().toString())){
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                MeApi.CheckVerify(et1.getText().toString(), et2.getText().toString(), new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        start(SetPayPwdFragmentStep2.newInstance(getArguments()));
                    }
                },Object.class);
                //start(SetPayPwdFragmentStep2.newInstance(getArguments()));


            }
        });

    }
}
