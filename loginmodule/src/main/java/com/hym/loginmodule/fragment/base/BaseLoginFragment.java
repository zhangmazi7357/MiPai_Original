package com.hym.loginmodule.fragment.base;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.hym.loginmodule.constans.Config;
import com.hym.loginmodule.http.LoginApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.WebFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/7/12.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public abstract class BaseLoginFragment extends BaseKitFragment {
    CountDownTimer timer;//计时器
    Boolean isTimerRunning = false;//判断计时器是否在工作
    long cuountDowntime = 60 * 1000;
    Unbinder unbinder;
    @BindView(R2.id.tv_1)
    public TextView tv1;
    @BindView(R2.id.tv_send_code)
    public TextView tvSendCode;
    @BindView(R2.id.et_1)
    public EditText et1;
    @BindView(R2.id.ll_1)
    public LinearLayout ll1;
    @BindView(R2.id.tv_2)
    public TextView tv2;
    @BindView(R2.id.et_2)
    public EditText et2;
    @BindView(R2.id.ll_2)
    public LinearLayout ll2;
    @BindView(R2.id.tv_3)
    public TextView tv3;
    @BindView(R2.id.et_3)
    public EditText et3;
    @BindView(R2.id.ll_3)
    public LinearLayout ll3;
    @BindView(R2.id.btn_confirm)
    public Button btnConfirm;
    @BindView(R2.id.tv_sub_function)
    public TextView tvSubFunction;
    @BindView(R2.id.ll_third_title)
    public LinearLayout llThirdTitle;
    @BindView(R2.id.tv_wechat)
    public TextView tvWechat;
    @BindView(R2.id.tv_pwd)
    public TextView tvPwd;
    @BindView(R2.id.rl_third)
    public RelativeLayout rlThird;
    @BindView(R2.id.ll_agreement)
    public LinearLayout llAgreement;
    @BindView(R2.id.ll_main)
    LinearLayout llMain;
    @BindView(R2.id.tv_4)
    public TextView tv4;
    @BindView(R2.id.et_4)
    public EditText et4;
    @BindView(R2.id.ll_4)
    public LinearLayout ll4;
    @BindView(R2.id.tv_agreement_1)
    TextView tvAgreement1;
    @BindView(R2.id.tv_agreement_2)
    TextView tvAgreement2;
    @BindView(R2.id.iv_see_pwd)
    public ImageView ivSeePwd;
    @BindView(R2.id.tv_protocal_title)
    public TextView tv_protocal_title;

    @Override
    public int getContentViewResId() {
        return R.layout.fg_common_login;
    }

    @Override
    public void doLogic() {
        //适配
        int width = ScreenUtil.getScreenWidth(_mActivity);
        int height = ScreenUtil.getScreenHeight(_mActivity);
//        Logger.d("width="+width+",height="+height);
        if (height / width >= 2) {
            int paddinglr = width / 15;
            llMain.setPadding(paddinglr, ScreenUtil.dip2px(_mActivity, 50), paddinglr, 0);
            int btn_height = (width - 2 * paddinglr) / 7;
            btnConfirm.getLayoutParams().height = btn_height;
        }
        showBackButton();
        tvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCodeBean bean = getCheckCodeBean();
                Logger.d(bean.toString());

                if (bean != null && !TextUtils.isEmpty(bean.getPhone())
                        && bean.getPhone().length() == 11 &&
                        !TextUtils.isEmpty(bean.getCodeType())) {

                    getCheckCode(bean.getPhone(), bean.getCodeType());

                } else {
                    ToastUtil.toast("请输入正确的手机号");
                }

            }
        });
        et4.setSingleLine();
        et4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvAgreement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("1");
                Bundle bundle = BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login, LoginMainActivity.Action_web);
                bundle.putString("url", Config.url_1);
                bundle.putString("title", "觅拍用户服务协议");
                start(WebFragment.newInstance(bundle));

            }
        });
        tvAgreement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("2");
                Bundle bundle = BaseActionActivity.getActionBundle(LoginMainActivity.ModelType_Login, LoginMainActivity.Action_web);
                bundle.putString("url", Config.url_2);
                bundle.putString("title", "觅拍隐私保护政策");
                start(WebFragment.newInstance(bundle));
            }
        });
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
        hideSoftInput();
        CancelTimer();
        timer = null;
        unbinder.unbind();
        ;
    }

    @Override
    public void onDestroy() {
        hideSoftInput();
        CancelTimer();
        timer = null;
        super.onDestroy();
    }

    //初始化计时器
    private void initTimer() {
        timer = new CountDownTimer(cuountDowntime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                if (tvSendCode == null) {
                    return;
                }
                tvSendCode.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                if (tvSendCode == null) {
                    return;
                }
                tvSendCode.setText("重新发送");
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
    private void getCheckCode(String phone, String type) {
        if (phone.length() != 11) {
            ToastUtil.toast("请输入正确的手机号");
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
        getCode(phone, type);
    }

    private void getCode(String inputData, String type) {
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
        ToastUtil.toast("发送成功，请注意查收");
        //验证码类型-必须（1：注册，2：快捷登录，3：忘记密码，4：三方绑定，6：设置或修改密码，7：绑定手机，8：修改手机，9：支付密码有关）

        LoginApi.getCode(_mActivity, language, inputData, type, new ResponseImpl<Object>() {
            @Override
            public void onSuccess(Object data) {
                Log.e("======", "验证码 = " + JSONObject.toJSONString(data));
                ToastUtil.toast("发送成功，请注意查收");

            }

            @Override
            public void onDataError(String status, String errormessage) {
                super.onDataError(status, errormessage);
                CancelTimer();
                ToastUtil.toast(errormessage + "");
                tvSendCode.setText("重新发送");
                tvSendCode.setEnabled(true);

            }



            @Override
            public void onFailed(Exception e) {
                super.onFailed(e);
                ToastUtil.toast("exception=" + e.toString());
                tvSendCode.setText("重新发送");
                tvSendCode.setEnabled(true);

            }
        }, Object.class);


    }

    public abstract CheckCodeBean getCheckCodeBean();

    public class CheckCodeBean {

        public CheckCodeBean(String phone, String codeType) {
            this.phone = phone;
            this.codeType = codeType;
        }

        public CheckCodeBean() {

        }

        public String phone;
        public String codeType;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCodeType() {
            return codeType;
        }

        public void setCodeType(String codeTypee) {
            this.codeType = codeTypee;
        }

        @Override
        public String toString() {
            return "CheckCodeBean{" +
                    "phone='" + phone + '\'' +
                    ", codeType='" + codeType + '\'' +
                    '}';
        }
    }


}
