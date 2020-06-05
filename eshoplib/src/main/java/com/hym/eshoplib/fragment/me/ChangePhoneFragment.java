package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.http.me.MeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 更换手机号码
 * <p>
 * otherTips
 */

public class ChangePhoneFragment extends BaseKitFragment {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_change)
    Button btnChange;
    Unbinder unbinder;

    public static ChangePhoneFragment newInstance(Bundle args) {
        ChangePhoneFragment fragment = new ChangePhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_change_phone;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("更换手机号");
        btnChange.setText("更换手机号码");


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
            @Override
            public void onSuccess(MedetailBean data) {
                String phone=data.getData().getPhone();
                if(phone!=null&&phone.length()==11){
                    //tvPhone.setText(phone.substring(0,3)+"****"+phone.substring(7,11));
                    tvPhone.setText(phone);
                }

            }
        },MedetailBean.class);

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

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        start(ChangePhoneFragmentStep2.newInstance(new Bundle()));

    }
}
