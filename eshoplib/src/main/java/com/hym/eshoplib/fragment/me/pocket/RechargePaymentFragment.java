package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.alipay.AliPay;
import com.hym.eshoplib.bean.order.AliPayBean;
import com.hym.eshoplib.bean.order.WxpayBean;
import com.hym.eshoplib.event.WxPayResultEvent;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.pay.Constants;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/4/7.
 * <p>
 * Description 充值支付方式
 * <p>
 * otherTips
 */

public class RechargePaymentFragment extends BaseKitFragment implements AliPay.PayResultListener {

    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.view_diver_balance)
    View viewDiverBalance;
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
    String needPay ;
    int payPosition = -1;
    AliPay aliPay;
    String child_order_id;
    public static RechargePaymentFragment newInstance(Bundle args) {
        RechargePaymentFragment fragment = new RechargePaymentFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public int getContentViewResId() {
        return R.layout.fragment_select_paytype;
    }

    @Override
    public void doLogic() {
        needPay = getArguments().getString("needPay");
        child_order_id=getArguments().getString("id","");
        tvRealpay.setText("￥"+needPay);
        llBalance.setVisibility(View.GONE);
        viewDiverBalance.setVisibility(View.GONE);
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
        setTitle("支付金额");
        aliPay = new AliPay(getActivity(), this);
        //data= (GetTotalCountBean) getArguments().getSerializable("data");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(child_order_id)){
                    ToastUtil.toast("数据异常");
                    return;
                }
                //调用支付
                if(payPosition==-1){
                    ToastUtil.toast("请选择支付方式");
                    return;
                }
                switch (payPosition) {
                    case 1:
                        //微信
                        OrderApi.WxPay(_mActivity, child_order_id, "vip", new ResponseImpl<WxpayBean>() {
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
                        }, WxpayBean.class);
                        break;
                    case 2:
                        //支付宝
                        OrderApi.aliPayMipai("vip", child_order_id, "","", new ResponseImpl<AliPayBean>() {
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
        setViews();


    }

    private void setViews() {
        upDatePayType();
        ivWechatpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 1;
                upDatePayType();
            }
        });
        ivAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPosition = 2;
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
            //startWithPop(RechargeSuccessFragment.newInstance(new Bundle()));
            start(RechargeSuccessFragment.newInstance(new Bundle()));
            //ToastUtil.toast("微信充值成功");
        } else {
            ToastUtil.toast("支付失败");
        }

    }

    @Override
    public void paySuccess() {
        //支付宝支付成功
        start(RechargeSuccessFragment.newInstance(new Bundle()));
      //  ToastUtil.toast("支付宝充值成功");;
    }

    @Override
    public void payFail() {
        //支付宝支付失败
        ToastUtil.toast("支付失败");
    }}
