package com.hym.loginmodule.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.http.LoginApi;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;

/**
 * Created by 胡彦明 on 2018/1/13.
 * <p>
 * Description 手机号登录
 * <p>
 * otherTips
 */

@Deprecated
public class LoginByMobilePhoneFrament extends BaseKitFragment {

    private String TAG = "LoginByMobilePhoneFrament";
    public static final String KEY_LOGIN_TYPE = "login_type_key";

    public static final int TYPE_LOGIN_EMAIL = 0;

    public static final int TYPE_LOGIN_PHONE = 1;

    @BindView(R2.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R2.id.et_2)
    EditText et2;
    @BindView(R2.id.iv_icon_3)
    ImageView ivIcon3;
    @BindView(R2.id.et_3)
    EditText et3;
    @BindView(R2.id.btn_confirm)
    SuperButton btnConfirm;
    @BindView(R2.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R2.id.tv_forget_password)
    TextView tvForgetPassword;

    private int loginType = TYPE_LOGIN_EMAIL;

    Unbinder unbinder;

    Bundle bundle;

    public static LoginByMobilePhoneFrament newInstance(int loginType) {
        Bundle args = new Bundle();
        LoginByMobilePhoneFrament fragment = new LoginByMobilePhoneFrament();
        args.putInt(KEY_LOGIN_TYPE, loginType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_loginby_mobile_phone;
    }

    @Override
    public void doLogic() {
        bundle = getArguments();
        loginType = bundle.getInt(KEY_LOGIN_TYPE);
        //手机号登陆
        if (loginType == TYPE_LOGIN_PHONE) {
            ivIcon2.setImageResource(R.drawable.ic_phone_login);
            et2.setHint(R.string.CNMobileonly);
            et2.setInputType(InputType.TYPE_CLASS_NUMBER);

        } else {
            //e-mail登陆
            ivIcon2.setImageResource(R.drawable.ic_email_login);
            et2.setHint(R.string.EnterEmailAddress);
            et2.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }
        if (loginType == TYPE_LOGIN_PHONE) {
            setTitle(R.string.LoginwithMobileNo);
        } else {
            setTitle(R.string.Loginwith_EmailAddress);
        }

        showBackButton();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                Login();

            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginType == TYPE_LOGIN_PHONE) {
                    start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_UPDATE_PHONE_PASSWORD));
                } else {
                    start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_UPDATE_EMAIL_PASSWORD));
                }
            }
        });
    }

    private void Login() {
        hideSoftInput();
        String loginData = et2.getText().toString();
        if (loginType == TYPE_LOGIN_PHONE) {
            if (loginData.length() != 11) {
                ToastUtil.toast("Phone Number's Length required 11  ");
                return;
            }
        } else {
            if (TextUtils.isEmpty(loginData)) {
                ToastUtil.toast("Please enter the correct e-mail account  ");
                return;
            }
        }

        String pwd = et3.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6 || pwd.length() > 20) {
            ToastUtil.toast("Password(6-20 characters)");
            return;
        }
        //语言类型-必须（1：中文，2：英文，3：日文）
        String language = SharePreferenceUtil.getStringData(_mActivity, _mActivity.getResources().getString(R.string.app_language_pref_key));
        if (TextUtils.isEmpty(language)) {
            language = "1";//默认中文
        }
        if (language.equals("zh")) {
            language = "1";
        }
        if (language.equals("en")) {
            language = "2";
        }
        if (language.equals("ja")) {
            language = "3";
        }
        if (loginType == TYPE_LOGIN_PHONE) {
            login(loginData, "", pwd, language);
        } else {
            login("", loginData, pwd, language);
        }
    }

    /**
     * 登陆
     */
    private void login(final String phone, String email, String pwd, String language) {
        LoginApi.login(_mActivity, phone, email, pwd, language, new ResponseImpl<LoginBean>() {
            @Override
            public void onSuccess(LoginBean data) {
                int perfected = data.getData().getPerfected();
                String regionName = data.getData().getRegion_name();
                String token = data.getData().getToken();

                Log.e(TAG, "手机号登录  = " + JSONObject.toJSONString(data));

                UserUtil.saveToken(_mActivity, token);
                Bundle bundle = new Bundle();
                bundle.putString("region", regionName);
                SharePreferenceUtil.setStringData(_mActivity, "region", regionName);
                if (perfected != 1) {
                    //去完善资料
                    start(PerfectInformationFragment.newInstance(bundle));
                } else {
                    //登录成功，发送通知，更新主界面的城市
                    ToastUtil.toast("登录成功");
                    EventBus.getDefault().post(new LoginEvent(bundle));
                    _mActivity.finish();

                }

            }

            @Override
            public void onDataError(String status, String errormessage) {
                if (status.equals("301")) {
                    //账号还未注册
                    if (!TextUtils.isEmpty(phone)) {
                        ToastUtil.toast("登录的手机号还未注册");
                    } else {
                        ToastUtil.toast("输入的邮箱未注册");
                    }

                } else {
                    super.onDataError(status, errormessage);
                }
            }
        }, LoginBean.class);

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

    @Override
    public void onDestroy() {
        hideSoftInput();
        super.onDestroy();
    }
}
