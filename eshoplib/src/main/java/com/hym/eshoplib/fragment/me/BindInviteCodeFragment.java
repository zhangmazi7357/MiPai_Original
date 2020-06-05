package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.widgets.PayPsdInputView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 绑定邀请码
 * <p>
 * otherTips
 */

public class BindInviteCodeFragment extends BaseKitFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.password)
    PayPsdInputView password;
    Unbinder unbinder;
    int count = 1;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id._tv_forget_pwd)
    TextView TvForgetPwd;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    public static BindInviteCodeFragment newInstance(Bundle args) {
        BindInviteCodeFragment fragment = new BindInviteCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_set_pay_pwd;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("绑定邀请码");
        tvTitle.setText("请填写邀请码");
        password.setVisibility(View.VISIBLE);
        password.setDrawText(true);
        etCode.setVisibility(View.GONE);
//        etCode.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=password.getPasswordString();
                Logger.d("code="+code);
                if(code.length()!=6){
                    ToastUtil.toast("请输入正确的邀请码");
                    return;
                }
                String mecode=getArguments().getString("code","");
                if(code.equalsIgnoreCase(mecode)){
                    ToastUtil.toast("不能填写自己的邀请码");
                    return;
                }
                MeApi.AddInviter(code, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("绑定成功");
                        setFragmentResult(RESULT_OK,new Bundle());
                        pop();
                    }
                },Object.class);

            }
        });
//        etCode.requestFocus();
        showSoftInput(password);

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
