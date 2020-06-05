package com.hym.loginmodule.fragment.mipai;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.hym.loginmodule.R;
import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;
import com.hym.loginmodule.http.LoginApi;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;

/**
 * Created by 胡彦明 on 2018/7/18.
 * <p>
 * Description 密码登录
 * <p>
 * otherTips
 */

public class PasswordLoginFragment extends BaseLoginFragment {
    public static PasswordLoginFragment newInstance(Bundle args) {
        PasswordLoginFragment fragment = new PasswordLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        setTitle("密码登录");
        tvSendCode.setVisibility(View.GONE);
        ll2.setVisibility(View.GONE);
        rlThird.setVisibility(View.GONE);
        tvSubFunction.setText("忘记密码");
        tvSubFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ForgetPasswordFragment.newInstance(new Bundle()));

            }
        });
        et1.setHint("请输入注册手机号");
        et3.setHint("请输入密码");
        et3.setSingleLine();
        et3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone=et1.getText().toString();
                String pwd=et3.getText().toString();
                if(phone.length()!=11){
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.toast("请输入密码");
                    return;
                }
                LoginApi.login(_mActivity, phone, "", pwd, "", new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        SharePreferenceUtil.setStringData(_mActivity,"lastphone",phone);
                        UserUtil.setIsLogin(_mActivity,true);
                        UserUtil.saveToken(_mActivity,data.getData().getToken());//token
                        UserUtil.saveRongYunToken(_mActivity,data.getData().getRongcloud_token());//融云
                        String token = data.getData().getToken();
                        Log.e("loginToken",token);
//                       SharePreferenceUtil.setStringData(_mActivity, "region",(TextUtils.isEmpty(data.getData().getRegion_name())?"":data.getData().getRegion_name()));//区域
                        Bundle bundle=new Bundle();
                        bundle.putString("id",data.getData().getUserid());
                        bundle.putString("name",data.getData().getNickname());
                        bundle.putString("url",data.getData().getAvatar());
                        EventBus.getDefault().post(new LoginEvent(bundle));
                        ToastUtil.toast("登录成功");
                        _mActivity.finish();
                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        et3.getText().clear();
                    }
                },LoginBean.class);
            }

//             LoginApi.login(_mActivity, phone, email, pwd, language, new ResponseImpl<LoginBean>() {
//                @Override
//                public void onSuccess(LoginBean data) {
//                    int perfected = data.getData().getPerfected();
//                    String regionName = data.getData().getRegion_name();
//                    String token = data.getData().getToken();
//                    UserUtil.saveToken(_mActivity, token);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("region", regionName);
//                    SharePreferenceUtil.setStringData(_mActivity, "region", regionName);
//                    if (perfected != 1) {
//                        //去完善资料
//                        start(PerfectInformationFragment.newInstance(bundle));
//                    } else {
//                        //登录成功，发送通知，更新主界面的城市
//                        ToastUtil.toast("登录成功");
//                        EventBus.getDefault().post(new LoginEvent(bundle));
//                        _mActivity.finish();
//
//                    }
//
//                }
//
//                @Override
//                public void onDataError(String status, String errormessage) {
//                    if (status.equals("301")) {
//                        //账号还未注册
//                        if (!TextUtils.isEmpty(phone)) {
//                            ToastUtil.toast("登录的手机号还未注册");
//                        } else {
//                            ToastUtil.toast("输入的邮箱未注册");
//                        }
//
//                    } else {
//                        super.onDataError(status, errormessage);
//                    }
//                }
//            }, LoginBean.class);

        });
        if(!TextUtils.isEmpty(SharePreferenceUtil.getStringData(_mActivity,"lastphone"))){
            et1.setText(SharePreferenceUtil.getStringData(_mActivity,"lastphone"));
        }
        ivSeePwd.setVisibility(View.VISIBLE);
        ivSeePwd.setSelected(false);
        ivSeePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivSeePwd.isSelected()){
                    ivSeePwd.setSelected(false);
                    ivSeePwd.setImageResource(R.drawable.ic_eye_closed);
                    //et3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    et3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et3.setSelection(et3.getText().length());
                }else {
                    ivSeePwd.setSelected(true);
                    ivSeePwd.setImageResource(R.drawable.ic_eyeopen);
                    et3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et3.setSelection(et3.getText().length());
                }
            }
        });


    }

    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean("11111111111", "1");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
