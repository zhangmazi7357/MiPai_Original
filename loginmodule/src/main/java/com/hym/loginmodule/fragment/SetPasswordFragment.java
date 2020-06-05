package com.hym.loginmodule.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
public class SetPasswordFragment extends BaseKitFragment {

    @BindView(R2.id.fl_set_password_by_phone)
    FrameLayout flSetPasswordByPhone;

    @BindView(R2.id.fl_set_password_by_email)
    FrameLayout flSetPasswordByEmail;

    Unbinder unbinder;

    public static SetPasswordFragment newInstance() {

        Bundle args = new Bundle();
        SetPasswordFragment fragment = new SetPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_set_password;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle(R.string.SetPassword);
        flSetPasswordByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //手机号修改密码
                start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_UPDATE_PHONE_PASSWORD));
            }
        });
        flSetPasswordByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //邮箱修改密码
                start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_UPDATE_EMAIL_PASSWORD));
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
