package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hym.eshoplib.R;
import com.hym.eshoplib.fragment.shop.BusinessAuthFragment;
import com.hym.eshoplib.fragment.shop.PersonalAuthFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/12/12.
 * <p>
 * Description 认证
 * <p>
 * otherTips
 */

public class AuthFragment extends BaseKitFragment {
    Unbinder unbinder;
    @BindView(R.id.btn_personal)
    Button btnPersonal;
    @BindView(R.id.btn_business)
    Button btnBusiness;

    public static AuthFragment newInstance(Bundle args) {
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_auth;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("认证方式");
        String type1 = getArguments().getString("type1", "");
        String type2 = getArguments().getString("type2", "");
        //0 未申请 1 待认证 2 已认证 3已拒绝
        switch (type1) {
            case "0":
                btnPersonal.setText("立即认证");
                break;
            case "1":
                btnPersonal.setText("待审核");
                break;
            case "2":
                btnPersonal.setText("已认证");
                break;
            case "3":
                btnPersonal.setText("未通过");
                break;
        }
        switch (type2) {
            case "0":
                btnBusiness.setText("立即认证");
                break;
            case "1":
                btnBusiness.setText("待审核");
                break;
            case "2":
                btnBusiness.setText("已认证");
                break;
            case "3":
                btnBusiness.setText("未通过");
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

    @OnClick({R.id.btn_personal, R.id.btn_business})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_personal:
                bundle.putString("type", getArguments().getString("type1", ""));
                start(PersonalAuthFragment.newInstance(bundle));
                break;
            case R.id.btn_business:
                bundle.putString("type", getArguments().getString("type2", ""));
                start(BusinessAuthFragment.newInstance(bundle));
                break;
        }
    }
}
