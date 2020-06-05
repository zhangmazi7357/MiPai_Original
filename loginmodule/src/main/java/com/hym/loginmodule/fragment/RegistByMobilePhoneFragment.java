package com.hym.loginmodule.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.http.LoginApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;

/**
 * Created by 胡彦明 on 2018/1/12.
 * <p>
 * Description 手机号注册
 * <p>
 * otherTips
 */
@Deprecated
public class RegistByMobilePhoneFragment extends BaseKitFragment {
    CountDownTimer timer;//计时器
    Boolean isTimerRunning = false;//判断计时器是否在工作
    long cuountDowntime = 60 * 1000;
    @BindView(R2.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R2.id.et_1)
    EditText et1;
    @BindView(R2.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R2.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R2.id.et_2)
    EditText et2;
    @BindView(R2.id.iv_icon_3)
    ImageView ivIcon3;
    @BindView(R2.id.et_3)
    EditText et3;
    @BindView(R2.id.et_4)
    EditText et4;
    @BindView(R2.id.btn_confirm)
    SuperButton btnConfirm;
    @BindView(R2.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R2.id.tv_show)
    TextView tvShow;
    @BindView(R2.id.tv_agreement)
    TextView tvAgreement;
    Unbinder unbinder;

    public static final String KEY_REGISTER_TYPE = "register_type_key";

    //邮箱注册
    public static final int TYPE_REGIST_EMAIL = 10;

    //手机号注册
    public static final int TYPE_REGIST_PHONE = 11;

    //手机号设置密码
    public static final int TYPE_PHONE_PASSWORD = 12;

    //邮箱设置密码
    public static final int TYPE_EMAIL_PASSWORD = 13;

    //手机号修改密码
    public static final int TYPE_UPDATE_PHONE_PASSWORD = 14;

    //邮箱修改密码
    public static final int TYPE_UPDATE_EMAIL_PASSWORD = 15;

    private int intentType;

    public static RegistByMobilePhoneFragment newInstance(int intentType) {
        Bundle args = new Bundle();
        RegistByMobilePhoneFragment fragment = new RegistByMobilePhoneFragment();
        args.putInt(KEY_REGISTER_TYPE, intentType);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getContentViewResId() {
        return R.layout.fg_regist_or_forgetpwd;
    }

    @Override
    public void doLogic() {
        intentType = getArguments().getInt(KEY_REGISTER_TYPE, TYPE_REGIST_PHONE);
        showBackButton();
        switch (intentType) {
            //手机注册
            case TYPE_REGIST_PHONE:
                setTitle(R.string.RegisterwithMobileNo);
                ivIcon1.setImageResource(R.drawable.ic_phone_login);
                et1.setHint(R.string.CNMobileonly);
                et1.setInputType(InputType.TYPE_CLASS_NUMBER);
                et2.setHint(R.string.SendSMSCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Registration);
                tvShow.setVisibility(View.VISIBLE);
                tvAgreement.setVisibility(View.VISIBLE);
                break;
            //手机号设置密码
            case TYPE_PHONE_PASSWORD:
                setTitle(R.string.SetPassword);
                ivIcon1.setImageResource(R.drawable.ic_phone_login);
                et1.setHint(R.string.CNMobileonly);
                et1.setInputType(InputType.TYPE_CLASS_NUMBER);
                et2.setHint(R.string.SendSMSCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Confirm);
                tvShow.setVisibility(View.INVISIBLE);
                tvAgreement.setVisibility(View.INVISIBLE);
                break;
            case TYPE_REGIST_EMAIL:
                setTitle(R.string.RegisterwithEmailAddress);
                ivIcon1.setImageResource(R.drawable.ic_email_login);
                et1.setHint(R.string.EnterEmailAddress);
                et1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                et2.setHint(R.string.EnterEmailCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Registration);
                tvShow.setVisibility(View.VISIBLE);
                tvAgreement.setVisibility(View.VISIBLE);
                break;
            case TYPE_EMAIL_PASSWORD:
                setTitle(R.string.SetPassword);
                ivIcon1.setImageResource(R.drawable.ic_email_login);
                et1.setHint(R.string.EnterEmailAddress);
                et1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                et2.setHint(R.string.EnterEmailCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Confirm);
                tvShow.setVisibility(View.INVISIBLE);
                tvAgreement.setVisibility(View.INVISIBLE);
                break;
            case TYPE_UPDATE_PHONE_PASSWORD:
                setTitle(R.string.UpdatePhonePassword);
                ivIcon1.setImageResource(R.drawable.ic_phone_login);
                et1.setHint(R.string.CNMobileonly);
                et1.setInputType(InputType.TYPE_CLASS_NUMBER);
                et2.setHint(R.string.SendSMSCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Confirm);
                tvShow.setVisibility(View.INVISIBLE);
                tvAgreement.setVisibility(View.INVISIBLE);
                break;
            case TYPE_UPDATE_EMAIL_PASSWORD:
                setTitle(R.string.UpdateEmailPassword);
                ivIcon1.setImageResource(R.drawable.ic_email_login);
                et1.setHint(R.string.EnterEmailAddress);
                et1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                et2.setHint(R.string.EnterEmailCode);
                et3.setHint(R.string.EnterNewPassword);
                btnConfirm.setText(R.string.Confirm);
                tvShow.setVisibility(View.INVISIBLE);
                tvAgreement.setVisibility(View.INVISIBLE);
                break;
        }
        ivIcon2.setImageResource(R.drawable.ic_sms_code);
        ivIcon3.setImageResource(R.drawable.ic_pwd);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSth();

            }
        });
        tvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCheckCode();
            }
        });

    }

    private void doSth() {
        String registerData = et1.getText().toString();
        switch (intentType) {
            case TYPE_REGIST_PHONE:
            case TYPE_PHONE_PASSWORD:
            case TYPE_UPDATE_PHONE_PASSWORD:
                if (registerData.length() != 11) {
                    ToastUtil.toast("Phone Number's Length required 11  ");
                    return;
                }
                break;
            case TYPE_REGIST_EMAIL:
            case TYPE_EMAIL_PASSWORD:
            case TYPE_UPDATE_EMAIL_PASSWORD:
                if (TextUtils.isEmpty(registerData)) {
                    ToastUtil.toast("Please enter the correct e-mail account  ");
                    return;
                }
                break;
        }

        String code = et2.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.toast("code is empty");
            return;
        }
        String pwd = et3.getText().toString();
        String pwd2=et4.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6 || pwd.length() > 20) {
            ToastUtil.toast("Password(6-20 characters)");
            return;
        }
        if(!pwd.trim().equals(pwd2.trim())){
            ToastUtil.toast("两次密码不一致");
            return;
        }

        signUp(registerData, pwd, code);

    }

    private void signUp(String inputData, String pwd, String code) {
        hideSoftInput();
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
        switch (intentType) {
            case TYPE_REGIST_PHONE:
                LoginApi.signUp(language,inputData, "", pwd, code, new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        ToastUtil.toast("Registration success");
                        //pop();
                        registSuccess(data);
                    }
                }, LoginBean.class);
                break;
            case TYPE_REGIST_EMAIL:
                LoginApi.signUp(language,"", inputData, pwd, code, new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        ToastUtil.toast("Registration success");
                        //pop();
                        registSuccess(data);
                    }
                }, LoginBean.class);
                break;
            case TYPE_PHONE_PASSWORD:
            case TYPE_UPDATE_PHONE_PASSWORD:
                LoginApi.setPassword(getContext(), inputData, "", code, pwd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("Set the password success");
                        pop();
                    }
                }, Object.class);
                break;
            case TYPE_EMAIL_PASSWORD:
            case TYPE_UPDATE_EMAIL_PASSWORD:
                LoginApi.setPassword(getContext(), "", inputData, code, pwd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("Set the password success");
                        pop();
                    }
                }, Object.class);
                break;
        }
    }

    //初始化计时器
    private void initTimer() {
        timer = new CountDownTimer(cuountDowntime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                tvSendCode.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                tvSendCode.setText(R.string.SendSMScode);
                tvSendCode.setEnabled(true);
            }
        };
    }

    //取消计时器
    private void CancelTimer() {
        if (timer == null) {
            isTimerRunning = false;
            return;
        }
        timer.cancel();
        isTimerRunning = false;
    }

    //获取验证码
    private void getCheckCode() {
        String inputData = et1.getText().toString();
        switch (intentType) {
            case TYPE_REGIST_PHONE:
            case TYPE_PHONE_PASSWORD:
                if (inputData.length() != 11) {
                    ToastUtil.toast("Phone Number's Length required 11  ");
                    return;
                }
                break;
            case TYPE_REGIST_EMAIL:
            case TYPE_EMAIL_PASSWORD:
                if (TextUtils.isEmpty(inputData)) {
                    ToastUtil.toast("Please enter the correct e-mail account  ");
                    return;
                }
                break;
        }

        if (isTimerRunning) {
            return;
        }
        if (timer == null) {
            initTimer();
        }
        CancelTimer();
        timer.start();
        isTimerRunning = true;
        tvSendCode.setEnabled(false);
        String languages = SharePreferenceUtil.getStringData(_mActivity, _mActivity.getString(R.string.app_language_pref_key));
//        LoginApi.getCode(LanguageUtil.getLanguageTypeBytag(languages), et1.getText().toString(), "1", new ResponseImpl<Object>() {
//            @Override
//            public void onSuccess(Object data) {
//                ToastUtil.toast("send success please wait");
//            }
//        }, Object.class);
//        LoginApi.getCode(LanguageUtil.getLanguageTypeBytag(languages),phone, "1", new ResponseImpl<Object>() {
//            @Override
//            public void onSuccess(Object data) {
//                ToastUtil.toast("send success please wait");
        getCode(inputData);
    }

    private void getCode(String inputData) {
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
        switch (intentType) {
            case TYPE_REGIST_EMAIL:
                LoginApi.getEmailCode(inputData, "1", language, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("send success please wait");

                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        CancelTimer();
                        ToastUtil.toast("DataError=" + errormessage);
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        ToastUtil.toast("exception=" + e.toString());
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }
                }, Object.class);
                break;

            case TYPE_REGIST_PHONE:
                LoginApi.getCode(_mActivity,language, inputData, "1", new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("send success please wait");

                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        CancelTimer();
                        ToastUtil.toast("DataError=" + errormessage);
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        ToastUtil.toast("exception=" + e.toString());
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }
                }, Object.class);

                break;
            case TYPE_PHONE_PASSWORD:
            case TYPE_UPDATE_PHONE_PASSWORD:
                LoginApi.getCode(_mActivity,language, inputData, "3", new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("send success please wait");

                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        CancelTimer();
                        ToastUtil.toast("DataError=" + errormessage);
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        ToastUtil.toast("exception=" + e.toString());
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }
                }, Object.class);

                break;
            case TYPE_EMAIL_PASSWORD:
            case TYPE_UPDATE_EMAIL_PASSWORD:
                LoginApi.getEmailCode(inputData, "6", language, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("send success please wait");

                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        CancelTimer();
                        ToastUtil.toast("DataError=" + errormessage);
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        ToastUtil.toast("exception=" + e.toString());
                        tvSendCode.setText(R.string.SendSMScode);
                        tvSendCode.setEnabled(true);

                    }
                }, Object.class);
                break;

        }
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
        CancelTimer();
        timer = null;
        super.onDestroy();
    }
    //注册成功
    private void registSuccess(LoginBean data) {
        int perfected = data.getData().getPerfected();
        String regionName = data.getData().getRegion_name();
        String token = data.getData().getToken();
        UserUtil.saveToken(_mActivity, token);
        Bundle bundle = new Bundle();
        bundle.putString("region", regionName);
        SharePreferenceUtil.setStringData(_mActivity, "region", regionName);
        start(PerfectInformationFragment.newInstance(bundle));
        //去完善资料
    }
}
