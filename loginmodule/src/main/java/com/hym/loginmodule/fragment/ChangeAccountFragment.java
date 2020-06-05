package com.hym.loginmodule.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 陈晖 on 2018/4/12.
 */

@Deprecated
public class ChangeAccountFragment extends BaseKitFragment {

    @BindView(R2.id.iv_top)
    ImageView ivTop;
    @BindView(R2.id.tv_account_notify)
    TextView tvAccountNotify;
    @BindView(R2.id.tv_account_notify2)
    TextView tvAccountNotify2;
    @BindView(R2.id.btn_confirm)
    SuperButton btnConfirm;
    Unbinder unbinder;

    //更换邮箱账号
    public static final int TYPE_CHANGE_EMAIL = 20;

    //更换手机账号
    public static final int TYPE_CHANGE_PHONE = 21;

    int changetype;

    String account;

    public static ChangeAccountFragment newInstance(int value, String account) {

        Bundle args = new Bundle();
        args.putInt("CHANGEACCOUNTKEY", value);
        args.putString("CHANGEACCOUNT", account);
        ChangeAccountFragment fragment = new ChangeAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_change_account;
    }

    @Override
    public void doLogic() {
        showBackButton();
        changetype = getArguments().getInt("CHANGEACCOUNTKEY", TYPE_CHANGE_PHONE);
        account = getArguments().getString("CHANGEACCOUNT");
        switch (changetype) {
            case TYPE_CHANGE_EMAIL:
                setTitle(R.string.Change_Email);
                ivTop.setImageResource(R.drawable.ic_email_large);
                tvAccountNotify.setText(getString(R.string.Current_Mobile_is) + account);
                tvAccountNotify2.setText(R.string.email_long_message);
                btnConfirm.setText(R.string.Change_Email);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //更换手机号码
                        start(ChangePersonalDataFragment.newInstance(ChangePersonalDataFragment.TYPE_CHANGE_EMAIL));
                    }
                });
                break;
            case TYPE_CHANGE_PHONE:
                setTitle(R.string.Change_Mobile);
                ivTop.setImageResource(R.drawable.ic_phone_large);
                tvAccountNotify.setText(getString(R.string.Current_Mobile_is) + account);
                tvAccountNotify2.setText(R.string.phone_long_message);
                btnConfirm.setText(R.string.Change_Mobile);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //更换邮箱账号
                        start(ChangePersonalDataFragment.newInstance(ChangePersonalDataFragment.TYPE_CHANGE_PHONE));
                    }
                });
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
}
