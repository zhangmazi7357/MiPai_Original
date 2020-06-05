package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.widgets.PayPsdInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 设置支付密码步骤2
 * <p>
 * otherTips
 */

public class SetPayPwdFragmentStep2 extends BaseKitFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.password)
    PayPsdInputView password;
    Unbinder unbinder;
    int type=-1;
    int count=1;
    public static SetPayPwdFragmentStep2 newInstance(Bundle args) {
        SetPayPwdFragmentStep2 fragment = new SetPayPwdFragmentStep2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_set_pay_pwd;
    }

    @Override
    public void doLogic() {
        type=getArguments().getInt("type",-1);
        showBackButton();
        switch (type){
            case 1:
                setTitle("设置支付密码");
                tvTitle.setText("设置支付密码");
                break;
            case 2:
                setTitle("修改支付密码");
                tvTitle.setText("输入新密码");
                break;
            case 3:
                setTitle("忘记支付密码");
                tvTitle.setText("输入新密码");
                break;
        }
        password.setComparePassword(new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference(String oldPsd, String newPsd) {
                ToastUtil.toast("两次密码输入不一致");
                password.cleanPsd();
                password.setComparePassword("");
                count=1;
                switch (type){
                    case 1:
                        tvTitle.setText("设置支付密码");
                        break;
                    case 2:
                        tvTitle.setText("输入新密码");
                        break;
                    case 3:
                        tvTitle.setText("输入新密码");
                        break;
                }


            }

            @Override
            public void onEqual(String psd) {
                MeApi.SetPaypassword(psd, psd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        String msg="";
                        switch (type){
                            case 1:
                                msg="设置成功，请妥善保管您的密码别告诉任何人哦";
                                break;
                            case 2:
                                msg="设置成功，请妥善保管您的密码别告诉任何人哦";
                                break;
                            case 3:
                                msg="设置成功，请妥善保管您的密码别告诉任何人哦";
                                break;
                        }
                        ToastUtil.toast(msg);
                        _mActivity.finish();
                    }
                },Object.class);


            }

            @Override
            public void inputFinished(String inputPsd) {
                if(count==1){
                    password.setComparePassword(inputPsd);
                    password.cleanPsd();
                    count=2;
                    switch (type){
                        case 1:
                            tvTitle.setText("确认支付密码");
                            break;
                        case 2:
                            tvTitle.setText("确认新密码");
                            break;
                        case 3:
                            tvTitle.setText("确认新密码");
                            break;
                    }

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
}
