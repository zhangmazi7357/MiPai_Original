package com.hym.eshoplib.fragment.order;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.alipay.AliPay;
import com.hym.eshoplib.bean.me.IFsetPayPwdBean;
import com.hym.eshoplib.bean.order.AliPayBean;
import com.hym.eshoplib.bean.order.UserAssetBean;
import com.hym.eshoplib.bean.order.WxpayBean;
import com.hym.eshoplib.event.WxPayResultEvent;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.widgets.PayPsdInputView;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.pay.Constants;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/4/7.
 * <p>
 * Description 选择支付方式
 * <p>
 * otherTips
 */

public class SelectPaymentFragment extends BaseKitFragment implements AliPay.PayResultListener {

    private String TAG = "SelectPaymentFragment";


    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    //你所注册的APP Id
    private static final String CONFIG_CLIENT_ID = "AThPuZyC45M2y9TjRu6Ns31-Y2cJMZqjlteUm7iJhcDb1U0ZjAbYGHjAaZvB4P5A_wiJKsr8ddSXCW7G";
    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
    private static PayPalConfiguration config = new PayPalConfiguration().environment(CONFIG_ENVIRONMENT).clientId(CONFIG_CLIENT_ID);

    public static SelectPaymentFragment newInstance(Bundle args) {
        SelectPaymentFragment fragment = new SelectPaymentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(_mActivity, PayPalService.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        _mActivity.startService(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // _mActivity.stopService(new Intent(_mActivity, PayPalService.class));
    }

    @BindView(R2.id.tv_balance)
    TextView tvBalance;
    @BindView(R2.id.iv_balance)
    ImageView ivBalance;
    @BindView(R2.id.iv_wechatpay)
    ImageView ivWechatpay;
    @BindView(R2.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R2.id.iv_paypal)
    ImageView ivPaypal;
    @BindView(R2.id.tv_needpay)
    TextView tvNeedpay;
    @BindView(R2.id.tv_range)
    TextView tvRange;
    @BindView(R2.id.tv_realpay)
    TextView tvRealpay;
    @BindView(R2.id.tv_confirm)
    TextView tvConfirm;
    Unbinder unbinder;
    Double balance;
    String needPay;
    int payPosition = -1;
    String child_order_id;
    String use_balance = "0";//使用余额
    //String order_id;
    AliPay aliPay;
    String log_id;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_select_paytype;
    }

    @Override
    public void doLogic() {
        SoftHideKeyBoardUtil.assistActivity(_mActivity, new SoftHideKeyBoardUtil.onSoftChangeListener() {
            @Override
            public void onSoftChange(boolean show) {
                if (!show) {
                    MipaiDialogUtil.dismiss();
                }
            }
        });
        setShowProgressDialog(true);
        showBackButton();
        setTitle("提交订单");
        aliPay = new AliPay(getActivity(), this);
        log_id = getArguments().getString("id2");
        child_order_id = getArguments().getString("id", "");

        //order_id = getArguments().getString("order_id", "");
        needPay = getArguments().getString("needPay");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = "";
                if (payPosition == -1) {
                    ToastUtil.toast("请选择支付方式");
                    return;
                }
                if (ivBalance.isSelected()) {
                    //使用余额
                    use_balance = "3";
                } else {
                    //不使用余额
                    use_balance = "0";
                }

                //支付
                switch (payPosition) {
                    case 0:     // 余额
                        //ToastUtil.toast("余额支付开发中,请选择支付宝支付");
                        //先判断 是否设置了支付密码


                        MeApi.IssetPaypass(new ResponseImpl<IFsetPayPwdBean>() {
                            @Override
                            public void onSuccess(IFsetPayPwdBean data) {

                                if (data.getData().getIs_set().equals("1")) {
                                    //设置了支付密码弹出 密码输入框
                                    PayPsdInputView et = MipaiDialogUtil.showInputPwdDialog(_mActivity,
                                            "￥" + needPay, "",
                                            new PayPsdInputView.onPasswordListener() {
                                                @Override
                                                public void onDifference(String oldPsd, String newPsd) {

                                                }

                                                @Override
                                                public void onEqual(String psd) {

                                                }

                                                @Override
                                                public void inputFinished(String inputPsd) {
                                                    MipaiDialogUtil.dismiss();

                                                    //调用余额支付
                                                    OrderApi.aliPayMipai("activity", child_order_id,
                                                            "3", inputPsd,
                                                            new ResponseImpl<AliPayBean>() {
                                                                @Override
                                                                public void onSuccess(AliPayBean data) {

                                                                }

                                                                @Override
                                                                public void onDataError(String status, String errormessage) {
                                                                    super.onDataError(status, errormessage);

                                                                    if (status.equals("200")) {
                                                                        //ToastUtil.toast("余额支付成功");
                                                                        EventBus.getDefault().post(new UpdateDataEvent());
                                                                        Bundle bundle = new Bundle();
                                                                        bundle.putString("id", log_id);
                                                                        startWithPop(PaySuccessFragment.newInstance(bundle));
                                                                    }

                                                                }
                                                            },

                                                            AliPayBean.class);


                                                }
                                            },
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    //ToastUtil.toast("忘记密码");
                                                    //忘记支付密码
                                                    Bundle bundle = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_SetPayPwd);
                                                    bundle.putInt("type", 3);
                                                    ActionActivity.start(_mActivity, bundle);

                                                }
                                            });
                                    showSoftInput(et);

                                } else {


                                    DialogManager.getInstance().initSimpleDialog(_mActivity, "提示",
                                            "您当前未设置支付密码，是否立即设置?",
                                            "继续支付", "立即设置",
                                            new SimpleDialog.SimpleDialogOnClickListener() {
                                                @Override
                                                public void negativeClick(Dialog dialog) {
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void positiveClick(Dialog dialog) {
                                                    dialog.dismiss();

                                                    Bundle bundle = getActionBundle(ActionActivity.ModelType_me,
                                                            ActionActivity.Action_SetPayPwd);
                                                    bundle.putInt("type", 1);
                                                    ActionActivity.start(_mActivity, bundle);

                                                }
                                            }).show();


                                }
                            }
                        }, IFsetPayPwdBean.class);


                        break;
                    case 1:
                        //ToastUtil.toast("微信支付开发中，请选择支付宝支付");
                        //微信
                        OrderApi.WxPay(_mActivity, child_order_id, "activity", new ResponseImpl<WxpayBean>() {
                            @Override
                            public void onSuccess(WxpayBean data) {
                                //调启微信支付
                                //微信支付
                                IWXAPI api = WXAPIFactory.createWXAPI(_mActivity, Constants.APP_ID);
                                api.registerApp(Constants.APP_ID);
                                PayReq req = new PayReq();
                                req.appId = Constants.APP_ID;
                                req.nonceStr = data.getData().getNonce_str();
                                req.packageValue = "Sign=WXPay";
                                req.partnerId = Constants.partnerId;
                                req.prepayId = data.getData().getPrepay_id();
                                req.timeStamp = data.getData().getTimestamp();
                                req.sign = data.getData().getSign();
                                api.sendReq(req);
                            }

                            @Override
                            public void onFailed(Exception e) {
                                super.onFailed(e);
                            }

                            @Override
                            public void onDataError(String status, String errormessage) {
                                super.onDataError(status, errormessage);
                            }
                        }, WxpayBean.class);
                        break;
                    case 2:
                        // ToastUtil.toast("支付宝支付");
                        //支付宝
                        OrderApi.aliPayMipai("activity", child_order_id, "2", password, new ResponseImpl<AliPayBean>() {
                            @Override
                            public void onSuccess(AliPayBean data) {
                                aliPay.pay(data.getData().getStr());
                            }
                        }, AliPayBean.class);
                        break;

                }
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        OrderApi.getAsset(_mActivity, new ResponseImpl<UserAssetBean>() {
            @Override
            public void onSuccess(UserAssetBean data) {
                balance = Double.parseDouble(data.getData().getCredit());
                setViews();
            }
        }, UserAssetBean.class);


    }

    private void setViews() {
        upDatePayType();
        //设置余额
        tvBalance.setText("（￥" + balance + ")");
        tvNeedpay.setText("￥" + needPay);
//        若余额内有金额时，默认为勾选状态，可手动取消勾选
//
//        若余额内无金额时，默认为未勾选状态
//
//        若选择余额支付，则订单总金额大于余额时，余额全部支付，剩余金额使用第三方支付
//
//        订单总金额小于余额时，余额可全部支付，第三方支付项无法选择，勾选按钮消失（此时取消勾选余额支付，第三方支付勾选按钮出现）
        ivBalance.setImageResource(R.drawable.ic_pay_uncheck);
        tvRealpay.setText("￥" + needPay + "RMB");
//        if (balance > 0) {
//            //使用余额支付
//            ivBalance.setImageResource(R.drawable.ic_pay_check);
//            ivBalance.setSelected(true);
//            if (balance > Double.parseDouble(needPay)) {
//                //余额大于订单
//                tvRealpay.setText("0.00RMB");
//            } else {
//                //余额小于订单
//                tvRealpay.setText((Double.parseDouble(needPay) - balance) + "RMB");
//                tvRange.setText("(" + needPay + "-" + balance + ")");
//
//            }
//
//
//        } else {
//            //不使用余额支付
//            ivBalance.setImageResource(R.drawable.ic_pay_uncheck);
//            tvRealpay.setText(needPay + "RMB");
//        }

        // 余额
        ivBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 0;
                upDatePayType();
            }
        });
        // 微信
        ivWechatpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 1;
                upDatePayType();
            }
        });
        // 支付宝
        ivAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 2;
                upDatePayType();
            }
        });
        ivPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 3;
                upDatePayType();

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
        unbinder.unbind();
    }

    private void upDatePayType() {
        switch (payPosition) {
            case 0:
                //余额支付
                if (balance <= 0) {
                    ToastUtil.toast("余额不足");
                    return;
                }
                ivBalance.setImageResource(R.drawable.ic_pay_check);
                ivWechatpay.setImageResource(R.drawable.ic_pay_uncheck);
                ivAlipay.setImageResource(R.drawable.ic_pay_uncheck);
                ivPaypal.setImageResource(R.drawable.ic_pay_uncheck);
                break;

            case 1:
                //微信支付
                ivBalance.setImageResource(R.drawable.ic_pay_uncheck);
                ivWechatpay.setImageResource(R.drawable.ic_pay_check);
                ivAlipay.setImageResource(R.drawable.ic_pay_uncheck);
                ivPaypal.setImageResource(R.drawable.ic_pay_uncheck);
                break;
            case 2:
                //支付宝支付
                ivBalance.setImageResource(R.drawable.ic_pay_uncheck);
                ivWechatpay.setImageResource(R.drawable.ic_pay_uncheck);
                ivAlipay.setImageResource(R.drawable.ic_pay_check);
                ivPaypal.setImageResource(R.drawable.ic_pay_uncheck);
                break;

        }
    }

    @Override
    public boolean openEventBus() {
        return true;
    }


    /**
     * 微信支付
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wxPayResutl(WxPayResultEvent event) {
        if (event.getCode() == 0) {
            //成功
            // ToastUtil.toast("微信支付成功");
//            Bundle bundle = new Bundle();
//            bundle.putString("id", order_id);
//            start(PaySuccessFragment.newInstance(bundle));
            EventBus.getDefault().post(new UpdateDataEvent());
            Bundle bundle = new Bundle();
            bundle.putString("id", log_id);
            start(PaySuccessFragment.newInstance(bundle));
            // pop();
        } else {
            ToastUtil.toast("支付失败");
        }

    }

    @Override
    public void paySuccess() {
        //支付宝支付成功
        //ToastUtil.toast("支付宝支付成功");
//        Bundle bundle = new Bundle();
//        bundle.putString("id", order_id);
//        start(PaySuccessFragment.newInstance(bundle));
        EventBus.getDefault().post(new UpdateDataEvent());
        Bundle bundle = new Bundle();
        bundle.putString("id", log_id);
        startWithPop(PaySuccessFragment.newInstance(bundle));
    }

    @Override
    public void payFail() {
        //支付宝支付失败
        ToastUtil.toast("支付失败");
    }
}
