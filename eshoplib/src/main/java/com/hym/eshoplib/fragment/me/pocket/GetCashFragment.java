package com.hym.eshoplib.fragment.me.pocket;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.me.GetCashAccountBean;
import com.hym.eshoplib.bean.me.GetCashInfoBean;
import com.hym.eshoplib.bean.me.IFsetPayPwdBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.widgets.PayPsdInputView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 提现
 * <p>
 * otherTips
 */

public class GetCashFragment extends BaseKitFragment {
    String num;
    @BindView(R.id.et_money)
    ClearEditText etMoney;
    @BindView(R.id.tv_avalable)
    TextView tvAvalable;
    @BindView(R.id.tv_get_all)
    TextView tvGetAll;
    @BindView(R.id.tv_getcash)
    SuperTextView tvGetcash;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    Unbinder unbinder;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    GetCashAccountBean.DataBean bean;

    public static GetCashFragment newInstance(Bundle args) {
        GetCashFragment fragment = new GetCashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_getcash;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("提现");
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d(s.toString());
                try {
                    if (s.toString().contains(".")) {
                        String[] result = s.toString().split("\\.");
                        Logger.d(result);
                        if (result[1].length() > 2) {
                            etMoney.setText(s.toString().substring(0, s.toString().length() - 1));
                            etMoney.setSelection(s.toString().length() - 1);
                            ToastUtil.toast("最多只能输入两位小数");
                        }
                    }
                } catch (Exception e) {
                    Logger.d(e.toString());

                }

            }
        });
        num = getArguments().getString("num", "");
        tvAvalable.setText("可用余额" + num + "元");
        tvGetAll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText(num);

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = etMoney.getText().toString();
                if (TextUtils.isEmpty(money)) {
                    ToastUtil.toast("请输入提现金额");
                    return;
                }
                if (bean == null || TextUtils.isEmpty(bean.getUserbank_id())) {
                    ToastUtil.toast("请选择提现账户");
                    return;
                }
                MeApi.IssetPaypass(new ResponseImpl<IFsetPayPwdBean>() {
                    @Override
                    public void onSuccess(IFsetPayPwdBean data) {
                        if (data.getData().getIs_set().equals("1")) {
                            //设置了支付密码弹出 密码输入框
                            MeApi.PoundageInfo(etMoney.getText().toString(), new ResponseImpl<GetCashInfoBean>() {
                                @Override
                                public void onSuccess(GetCashInfoBean data) {
                                    PayPsdInputView et = MipaiDialogUtil.showInputPwdDialog(_mActivity,
                                            "￥" + data.getData().getTotal(),
                                            data.getData().getMemo(),
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
                                                    //提现
                                                    MeApi.WithdrawsCash(bean.getUserbank_id(), etMoney.getText().toString(),
                                                            inputPsd, new ResponseImpl<Object>() {
                                                                @Override
                                                                public void onSuccess(Object data) {

                                                                    ToastUtil.toast("提现申请成功，平台会尽快处理您的提现请求");
                                                                    EventBus.getDefault().post(new UpdateDataEvent());
                                                                    pop();
                                                                }
                                                            }, Object.class);


                                                }
                                            }, new View.OnClickListener() {
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
                                }
                            }, GetCashInfoBean.class);


                        } else {
                            //未设置 弹出提示框
                            String confirm = "立即设置";
                            String cancle = "暂不提现";

                            DialogManager.getInstance().initSimpleDialog(_mActivity, "提示",
                                    "您当前未设置支付密码,是否立即设置?", cancle, confirm, new SimpleDialog.SimpleDialogOnClickListener() {
                                        @Override
                                        public void negativeClick(Dialog dialog) {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void positiveClick(Dialog dialog) {
                                            dialog.dismiss();
                                            //去设置支付密码
                                            Bundle bundle = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_SetPayPwd);
                                            bundle.putInt("type", 1);
                                            ActionActivity.start(_mActivity, bundle);
                                        }
                                    }).show();


                        }
                    }
                }, IFsetPayPwdBean.class);


            }
        });
        tvGetcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (bean != null) {
                    bundle.putString("id", bean.getUserbank_id());
                }
                startForResult(SelectAccountFragment.newInstance(bundle), 0x01);
            }
        });
        llAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (bean != null) {
                    bundle.putString("id", bean.getUserbank_id());
                }
                startForResult(SelectAccountFragment.newInstance(bundle), 0x01);
            }
        });


    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x01) {
                bean = (GetCashAccountBean.DataBean) data.getSerializable("data");
                if (bean != null) {
                    tvGetcash.setVisibility(View.GONE);
                    llAccount.setVisibility(View.VISIBLE);
                    String type = bean.getType();
                    if (type.equals("1")) {
                        //支付宝
                        tv1.setText("支付宝账号");
                        tv2.setText("支付宝昵称：" + bean.getBankname());
                        tv3.setText("支付宝账号：" + bean.getBankcard());
                        tv4.setText("真实姓名：" + bean.getBankuser());
                        tv5.setText("支付宝绑定手机号：" + bean.getPhone());
                    } else if (type.equals("2")) {
                        //银行卡号
                        tv1.setText("银行卡号");
                        tv2.setText("银行卡号：" + bean.getBankcard());
                        tv3.setText("开户银行：" + bean.getBankname());
                        tv4.setText("真实姓名：" + bean.getBankuser());
                        tv5.setText("银行预留手机号：" + bean.getPhone());

                    }
                }
            }
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
}
