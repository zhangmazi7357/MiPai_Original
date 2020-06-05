package com.hym.loginmodule.fragment.mipai;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.hym.loginmodule.R;
import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;
import com.hym.loginmodule.http.LoginApi;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;

/**
 * Created by 胡彦明 on 2018/6/28.
 * <p>
 * Description 注册
 * <p>
 * otherTips
 */

public class RegisterFragment extends BaseLoginFragment {


    public static RegisterFragment newInstance(Bundle args) {
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    CheckCodeBean bean;

    @Override
    public void doLogic() {
        super.doLogic();
        //设置协议阅读 单选框
        tv_protocal_title.setText("我已阅读并同意");
        tv_protocal_title.setSelected(false);
        tv_protocal_title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked, 0, 0, 0);
        tv_protocal_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_protocal_title.isSelected()) {
                    tv_protocal_title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked, 0, 0, 0);
                    tv_protocal_title.setSelected(false);
                } else {
                    tv_protocal_title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_checked, 0, 0, 0);
                    tv_protocal_title.setSelected(true);
                }

            }
        });
        setTitle("注册");
        ll4.setVisibility(View.VISIBLE);
        et3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvSubFunction.setVisibility(View.GONE);
        llAgreement.setVisibility(View.VISIBLE);
        btnConfirm.setText("注册");
        //注册
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et1.getText().toString();
                String code = et2.getText().toString();
                String pwd = et3.getText().toString();
                String pwd2 = et4.getText().toString();
                if (phone.length() != 11) {
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                if (!pwd.equals(pwd2)) {
                    ToastUtil.toast("两次密码输入不一致");
                    return;
                }
                if (pwd.length() < 6 || pwd.length() > 20) {
                    ToastUtil.toast("请输入6-20位密码");
                    return;
                }
                if (!tv_protocal_title.isSelected()) {
                    ToastUtil.toast("请阅读相关协议并勾选");
                    return;
                }


                LoginApi.signUp("", phone, "", pwd, code, new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        UserUtil.setIsLogin(_mActivity, true);
                        UserUtil.saveToken(_mActivity, data.getData().getToken());//token
                        String rongToken = data.getData().getRongcloud_token();
                        if (!TextUtils.isEmpty(rongToken)) {
                            UserUtil.saveRongYunToken(_mActivity, rongToken);//融云
                        } else {
                            Logger.d("融云token为空");
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("id", data.getData().getUserid());
                        bundle.putString("name", data.getData().getNickname());
                        bundle.putString("url", data.getData().getAvatar());
                        EventBus.getDefault().post(new LoginEvent(bundle));
                        //SharePreferenceUtil.setStringData(_mActivity, "region", data.getData().getRegion_name());//区域
                        ToastUtil.toast("注册成功");
                        _mActivity.finish();
                    }
                }, LoginBean.class);


            }
        });
    }

    @Override
    public CheckCodeBean getCheckCodeBean() {
        bean = new CheckCodeBean();
        bean.setCodeType("1");
        bean.setPhone(et1.getText().toString());
        return bean;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
