package com.hym.loginmodule.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.http.LoginApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/1/12.
 * <p>
 * Description 绑定手机号，
 * <p>
 * otherTips
 */

@Deprecated
public class BindDataFragment extends BaseKitFragment {
    CountDownTimer timer;//计时器
    Boolean isTimerRunning = false;//判断计时器是否在工作
    long cuountDowntime = 60 * 1000;

    Unbinder unbinder;

    public static final String KEY_REGISTER_TYPE = "bind_type_key";

    //邮箱注册
    public static final int TYPE_BIND_EMAIL = 20;

    //手机号注册
    public static final int TYPE_BIND_PHONE = 21;
    @BindView(R2.id.et_1)
    EditText et1;
    @BindView(R2.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R2.id.tv_2)
    TextView tv2;
    @BindView(R2.id.et_2)
    EditText et2;
    @BindView(R2.id.btn_confirm)
    Button btnConfirm;


    private int intentType;

    public static BindDataFragment newInstance(int intentType) {
        Bundle args = new Bundle();
        BindDataFragment fragment = new BindDataFragment();
        args.putInt(KEY_REGISTER_TYPE, intentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_bind_data;
    }

    @Override
    public void doLogic() {
        intentType = getArguments().getInt(KEY_REGISTER_TYPE, TYPE_BIND_PHONE);
        showBackButton();
        switch (intentType) {
            case TYPE_BIND_PHONE:
                setTitle(getResources().getString(R.string.Link_Mobile));
                //ivIcon1.setImageResource(R.drawable.ic_phone_login);
               // ivIcon2.setImageResource(R.drawable.ic_sms_code);
                et1.setHint(R.string.CN_Mobile_only);
                et1.setInputType(InputType.TYPE_CLASS_NUMBER);
                et2.setHint(R.string.SendSMSCode);
                et2.setInputType(InputType.TYPE_CLASS_NUMBER);

                break;
            case TYPE_BIND_EMAIL:
                setTitle(getResources().getString(R.string.Link_Email));
               // ivIcon1.setImageResource(R.drawable.ic_email_login);
               // ivIcon2.setImageResource(R.drawable.ic_sms_code);
                et1.setHint(R.string.Enter_Email);
                et1.setInputType(InputType.TYPE_CLASS_TEXT);
                et2.setInputType(InputType.TYPE_CLASS_NUMBER);
                et2.setHint(R.string.EnterEmailCode);
                break;
        }
        btnConfirm.setText(R.string.Confirm);
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
//        String pwd=et2.getText().toString();
        switch (intentType) {
            case TYPE_BIND_PHONE:
                if (registerData.length() != 11) {
                    ToastUtil.toast("Phone Number's Length required 11  ");
                    return;
                }
                break;
            case TYPE_BIND_EMAIL:
                String regex = "\\w+@\\w+(\\.\\w+)+";
                if (!registerData.matches(regex)) {
                    ToastUtil.toast("Please enter the correct e-mail account  ");
                    return;
                }
//                if(TextUtils.isEmpty(pwd)){
//                    ToastUtil.toast(getResources().getString(R.string.EnterPassword));
//                }
                break;
        }

        String code = et2.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.toast("code is empty");
            return;
        }
        signUp(registerData, "", code);

    }

    private void signUp(String inputData, String pwd, String code) {
        switch (intentType) {
            case TYPE_BIND_PHONE:
                LoginApi.reset(getContext(), inputData, "", code, pwd, "7", new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("Bind success");
                        pop();
                    }
                }, Object.class);
                break;
            case TYPE_BIND_EMAIL:
                LoginApi.reset(getContext(), "", inputData, code, pwd, "7", new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("Bind success");
                        setFragmentResult(RESULT_OK, new Bundle());
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
            case TYPE_BIND_PHONE:
                if (inputData.length() != 11) {
                    ToastUtil.toast("Phone Number's Length required 11  ");
                    return;
                }
                break;
            case TYPE_BIND_EMAIL:
                String regex = "\\w+@\\w+(\\.\\w+)+";
                if (!inputData.matches(regex)) {
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
            case TYPE_BIND_EMAIL:
                LoginApi.getEmailCode(inputData, "7", language, new ResponseImpl<Object>() {
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

            case TYPE_BIND_PHONE:
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
}
