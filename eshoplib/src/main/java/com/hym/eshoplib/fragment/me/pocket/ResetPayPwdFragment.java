package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.fragment.me.SetPayPwdFragmentStep1;
import com.hym.eshoplib.fragment.me.SetPayPwdFragmentStep2;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.widgets.PayPsdInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/9/30.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ResetPayPwdFragment extends BaseKitFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.password)
    PayPsdInputView password;
    @BindView(R.id._tv_forget_pwd)
    TextView TvForgetPwd;
    Unbinder unbinder;

    public static ResetPayPwdFragment newInstance(Bundle args) {
        ResetPayPwdFragment fragment = new ResetPayPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_set_pay_pwd;
    }

    @Override
    public void doLogic() {
        setTitle("修改支付密码");
        showBackButton();
        tvTitle.setText("输入旧密码");
        TvForgetPwd.setVisibility(View.VISIBLE);
        TvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("type",3);
                start(SetPayPwdFragmentStep1.newInstance(bundle));
            }
        });
        password.setComparePassword(new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference(String oldPsd, String newPsd) {
                //ToastUtil.toast("diff");
            }

            @Override
            public void onEqual(String psd) {
               // ToastUtil.toast("equal");

            }

            @Override
            public void inputFinished(String inputPsd) {
               // ToastUtil.toast("finish");
                MeApi.CheckPaypass(inputPsd, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("type",2);
                        start(SetPayPwdFragmentStep2.newInstance(bundle));

                    }
                },Object.class);

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
