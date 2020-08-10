package com.hym.loginmodule.fragment.mipai;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.event.NeedPerfectInformationEvent;
import com.hym.loginmodule.event.WeChatLoginEvent;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;
import com.hym.loginmodule.http.LoginApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/6/28.
 * <p>
 * Description 快捷登录/既登录主页
 * <p>
 * otherTips
 */

public class QuickLoginFragment extends BaseLoginFragment {

    public static QuickLoginFragment newInstance(Bundle args) {
        QuickLoginFragment fragment = new QuickLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        setTitle("快捷登录");
        llAgreement.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llAgreement.getLayoutParams();
        params.setMargins(0, 0, 0, ScreenUtil.dip2px(_mActivity, 25));
        ll3.setVisibility(View.GONE);
        rlThird.setVisibility(View.VISIBLE);
        tvWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("三方登录在B版才可以实现，因为需要集成微信sdk");
                EventBus.getDefault().post(new WeChatLoginEvent(0));

            }
        });
        tvPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(PasswordLoginFragment.newInstance(new Bundle()));

            }
        });
        tvSubFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册
                start(RegisterFragment.newInstance(new Bundle()));
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = et1.getText().toString();
                String code = et2.getText().toString();
                if (phone.length() != 11) {
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                LoginApi.quikeLogin(phone, code, new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        SharePreferenceUtil.setStringData(_mActivity, "lastphone", phone);
                        UserUtil.setIsLogin(_mActivity, true);
                        UserUtil.saveToken(_mActivity, data.getData().getToken());//token
                        UserUtil.saveRongYunToken(_mActivity, data.getData().getRongcloud_token());//融云
                        SharePreferenceUtil.setStringData(_mActivity, "region", (TextUtils.isEmpty(data.getData().getRegion_name()) ? "" : data.getData().getRegion_name()));//区域

                        Bundle bundle = new Bundle();
                        bundle.putString("id", data.getData().getUserid());
                        bundle.putString("name", data.getData().getNickname());
                        bundle.putString("url", data.getData().getAvatar());
                        EventBus.getDefault().post(new LoginEvent(bundle));
                        ToastUtil.toast("登录成功");
                        _mActivity.finish();
                    }
                }, LoginBean.class);
            }
        });
        if (!TextUtils.isEmpty(SharePreferenceUtil.getStringData(_mActivity, "lastphone"))) {
            et1.setText(SharePreferenceUtil.getStringData(_mActivity, "lastphone"));
        }


    }

    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean(et1.getText().toString(), "2");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goBind(NeedPerfectInformationEvent event) {
        //绑定手机
        if (TextUtils.isEmpty(event.id)) {
            ToastUtil.toast("登录成功");
            _mActivity.finish();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("id", event.id);
            start(BindPhoneFragment.newInstance(bundle));
        }


    }
}
