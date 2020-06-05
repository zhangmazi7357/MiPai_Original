package com.hym.eshoplib.fragment.address;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.address.AddressListBean;
import com.hym.eshoplib.http.address.AddressApi;
import com.suke.widget.SwitchButton;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.RegexUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/3/23.
 * <p>
 * Description 添加收货地址,编辑收货地址
 * <p>
 * otherTips
 */

public class AddAddressFragment extends BaseKitFragment {
    boolean isEdit = false;
    ClearEditText etName;
    ClearEditText etPhone;
    ClearEditText et_address;
    Unbinder unbinder;
    AddressListBean.DataBean.InfoBean bean;
    SwitchButton btn_isdefault;
    public static AddAddressFragment newInstance(Bundle args) {
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_add_address;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        etName= (ClearEditText) view.findViewById(R.id.et_name);
        etPhone= (ClearEditText) view.findViewById(R.id.et_phone);
        et_address= (ClearEditText) view.findViewById(R.id.et_address);
        btn_isdefault= (SwitchButton) view.findViewById(R.id.switch_button);
    }

    @Override
    public void doLogic() {
        showBackButton();
        bean= (AddressListBean.DataBean.InfoBean) getArguments().getSerializable("data");
        isEdit = getArguments().getBoolean("isEdit", false);
        if (isEdit) {
            setTitle(R.string.Edit);
            if(bean==null){
                ToastUtil.toast("data error");
                pop();
                return;
            }
            etName.setText(bean.getName()+"");
            etPhone.setText(bean.getMobile()+"");
            et_address.setText(bean.getAddress()+"");
            if(bean.getIs_default().equals("1")){
                btn_isdefault.setChecked(true);
            }else {
                btn_isdefault.setChecked(false);
            }
        } else {
            setTitle(R.string.Addshippingaddress);
        }
        setRight_tv(R.string.Save, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =etName.getText().toString();
                String phone=etPhone.getText().toString();
                String address=et_address.getText().toString();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.toast(getResources().getString(R.string.PleaseEnterReceiverName));
                    return;
                }
                if(TextUtils.isEmpty(phone)|| !RegexUtil.isMobilePhoneNum(phone)){
                    ToastUtil.toast(getResources().getString(R.string.PleaseEnterMobileNo));
                    return;
                }
                if(TextUtils.isEmpty(address)||address.length()<5){
                    ToastUtil.toast(getResources().getString(R.string.DetailedAddress));
                    return;
                }
                if(isEdit){
                    AddressApi.addOrEditeAddress(_mActivity, btn_isdefault.isChecked()?"1":"0",bean.getConsignee_id(), name, phone, address, true, new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("add success");
                            setFragmentResult(RESULT_OK,new Bundle());
                            pop();
                        }
                    },Object.class);
                }else {
                    AddressApi.addOrEditeAddress(_mActivity,btn_isdefault.isChecked()?"1":"0", "", name, phone, address, false, new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("add success");
                            setFragmentResult(RESULT_OK,new Bundle());
                            pop();
                        }
                    },Object.class);
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
