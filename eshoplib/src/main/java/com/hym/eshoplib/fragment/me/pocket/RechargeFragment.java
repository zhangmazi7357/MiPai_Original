package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.RechargeOrderBean;
import com.hym.eshoplib.http.me.MeApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/6.
 * <p>
 * Description 充值
 * <p>
 * otherTips
 */

public class RechargeFragment extends BaseKitFragment {
    Unbinder unbinder;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_recharge)
    Button btnRecharge;

    public static RechargeFragment newInstance(Bundle args) {
        RechargeFragment fragment = new RechargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_recharge;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("充值");
//        etMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                   etMoney.setGravity(Gravity.CENTER);
//                }else {
//                    etMoney.setGravity(Gravity.RIGHT);
//                }
//            }
//        });
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
                    if(s.toString().contains(".")){
                        String []result=s.toString().split("\\.");
                        Logger.d(result);
                        if(result[1].length()>2){
                            etMoney.setText(s.toString().substring(0,s.toString().length()-1));
                            etMoney.setSelection(s.toString().length()-1);
                            ToastUtil.toast("最多只能输入两位小数");
                        }
                    }
                }catch (Exception e){
                    Logger.d(e.toString());

                }

            }
        });

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

    @OnClick(R.id.btn_recharge)
    public void onViewClicked() {
        final String money=etMoney.getText().toString();
        if(TextUtils.isEmpty(money)){
            ToastUtil.toast("请输入要充值的金额");
            return;
        }
        MeApi.ScoreUpdate(money, new ResponseImpl<RechargeOrderBean>() {
            @Override
            public void onSuccess(RechargeOrderBean data) {
                Bundle bundle=new Bundle();
                bundle.putString("needPay",money);
                bundle.putString("id",data.getData().getOrdernumber());
                start(RechargePaymentFragment.newInstance(bundle));
            }
        },RechargeOrderBean.class);

    }
}
