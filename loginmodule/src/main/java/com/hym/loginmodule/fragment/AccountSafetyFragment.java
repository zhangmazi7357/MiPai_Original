package com.hym.loginmodule.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.bean.PersonBean;
import com.hym.loginmodule.http.LoginApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 陈晖 on 2018/4/12.
 * 
 */

@Deprecated
public class AccountSafetyFragment extends BaseKitFragment {
    @BindView(R2.id.tv_bind_phone)
    TextView tvBindPhone;
    @BindView(R2.id.tv_bind_email)
    TextView tvBindEmail;
    @BindView(R2.id.tv_set_password)
    TextView tvSetPassword;
    @BindView(R2.id.tv_phone)
    TextView tvPhone;
    @BindView(R2.id.tv_bind)
    TextView tvBind;
    @BindView(R2.id.tv_set)
    TextView tvSet;
    Unbinder unbinder;

    public static AccountSafetyFragment newInstance() {
        Bundle args = new Bundle();
        AccountSafetyFragment fragment = new AccountSafetyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_account_safety;
    }

    @Override
    public void doLogic() {
        final String notify = getString(R.string.Not_Linked);
        showBackButton();
        setTitle(R.string.Accountandsecurity);
        tvBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定手机号
                if (TextUtils.isEmpty(tvPhone.getText().toString()) || notify.equals(tvPhone.getText().toString() + "")) {
                    start(BindDataFragment.newInstance(BindDataFragment.TYPE_BIND_PHONE));
                } else {
                    start(ChangeAccountFragment.newInstance(ChangeAccountFragment.TYPE_CHANGE_PHONE, tvPhone.getText().toString()));
                }

            }
        });
        tvBindEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定邮箱号
                if (TextUtils.isEmpty(tvBind.getText().toString()) || notify.equals(tvBind.getText().toString())) {
                    start(BindDataFragment.newInstance(BindDataFragment.TYPE_BIND_EMAIL));
                } else {
                    start(ChangeAccountFragment.newInstance(ChangeAccountFragment.TYPE_CHANGE_EMAIL, tvBind.getText().toString()));
                }
            }
        });
        tvSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //密码设置
                start(SetPasswordFragment.newInstance());
            }
        });
        http();
    }

    private void http() {
        LoginApi.getUserinfo(getContext(), new ResponseImpl<PersonBean>() {
            @Override
            public void onSuccess(PersonBean data) {
                if (TextUtils.isEmpty(data.getData().getPhone())) {
                    tvPhone.setText(getString(R.string.Not_Linked));
                } else {
                    tvPhone.setText(data.getData().getPhone() + "");
                }
                if (TextUtils.isEmpty(data.getData().getEmail())) {
                    tvBind.setText(getString(R.string.Not_Linked));
                } else {
                    tvBind.setText(data.getData().getEmail() + "");
                }
                if ("1".equals(data.getData().getIs_setpass() + "")) {
                    tvSet.setText("已设置");
                } else {
                    tvSet.setText("未设置");
                }
//                tvName.setText(data.getData().getNickname()+"");
//                tvSex.setText(data.getData().getGender().equals("1")?genders.get(0):genders.get(1));
//                tvAge.setText(data.getData().getAge()+"");
            }
        }, PersonBean.class);

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
