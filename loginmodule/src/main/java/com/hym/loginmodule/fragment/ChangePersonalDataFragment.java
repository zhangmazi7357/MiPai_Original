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
import android.widget.TextView;

import com.allen.library.SuperButton;
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
 * Description 手机号注册
 * <p>
 * otherTips
 */
@Deprecated
public class ChangePersonalDataFragment extends BaseKitFragment {
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
    @BindView(R2.id.btn_confirm)
    SuperButton btnConfirm;
    Unbinder unbinder;

    public static final String KEY_INTENT = "KEY_INTENT_CHANGE_PERSONAL_DATA";

    public static final int TYPE_CHANGE_EMAIL = 21;

    public static final int TYPE_CHANGE_PHONE = 22;

    private int intentType;

    public static ChangePersonalDataFragment newInstance(int intentType) {
        Bundle args = new Bundle();
        ChangePersonalDataFragment fragment = new ChangePersonalDataFragment();
        args.putInt(KEY_INTENT, intentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_change_email_address;
    }

    @Override
    public void doLogic() {
        intentType = getArguments().getInt(KEY_INTENT);
        et1.setHint(getString(R.string.EnterPassword));
        btnConfirm.setText(R.string.Confirm);
        ivIcon1.setImageResource(R.drawable.ic_pwd);
        ivIcon3.setImageResource(R.drawable.ic_sms_code);
        if (intentType == TYPE_CHANGE_EMAIL) {
            //更改邮箱地址
            setTitle(R.string.Change_Email);
            ivIcon2.setImageResource(R.drawable.ic_phone_login);
            et2.setHint(R.string.Enter_Email2);
            et2.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            et3.setHint(R.string.EnterEmailCode);
        } else {
            //更改手机号码
            setTitle(R.string.Change_Mobile);
            ivIcon2.setImageResource(R.drawable.ic_email_login);
            et2.setHint(R.string.CN_Mobile_Only);
            et2.setInputType(InputType.TYPE_CLASS_NUMBER);
            et3.setHint(R.string.SendSMSCode);
        }
        showBackButton();
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
        String data = et2.getText().toString();
        if (intentType == TYPE_CHANGE_EMAIL) {
            String regex = "\\w+@\\w+(\\.\\w+)+";
            if (!data.matches(regex)) {
                ToastUtil.toast("Please enter the correct e-mail account  ");
                return;
            }
        } else {
            if (data.length() != 11) {
                ToastUtil.toast("Phone Number's Length required 11  ");
                return;
            }
        }

        String code = et3.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.toast("code is empty");
            return;
        }
        String pwd = et1.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6 || pwd.length() > 20) {
            ToastUtil.toast("Password(6-20 characters)");
            return;
        }
        signUp(data, pwd, code);

    }

    private void signUp(String data, String pwd, String code) {
        if (intentType == TYPE_CHANGE_EMAIL) {
            LoginApi.reset(getContext(), "", data, code, pwd, "8", new ResponseImpl<Object>() {
                @Override
                public void onSuccess(Object data) {
                    ToastUtil.toast("Modify mailbox address success");
                    pop();
                }
            }, Object.class);

        } else {
            LoginApi.reset(getContext(), data, "", code, pwd, "8", new ResponseImpl<Object>() {
                @Override
                public void onSuccess(Object data) {
                    ToastUtil.toast("Modify mailbox address success");
                    pop();
                }
            }, Object.class);
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
        String inputData = et2.getText().toString();
        String regex = "\\w+@\\w+(\\.\\w+)+";
        if (!inputData.matches(regex)) {
            ToastUtil.toast("Please enter the correct e-mail account  ");
            return;
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
        if (intentType == TYPE_CHANGE_EMAIL) {
            LoginApi.getEmailCode(inputData, "8", language, new ResponseImpl<Object>() {
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

        } else {
            LoginApi.getCode(_mActivity,language, inputData, "8", new ResponseImpl<Object>() {
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
