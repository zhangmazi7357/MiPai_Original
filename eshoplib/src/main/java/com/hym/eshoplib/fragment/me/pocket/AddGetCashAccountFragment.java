package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.GetCashAccountBean;
import com.hym.eshoplib.http.me.MeApi;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class AddGetCashAccountFragment extends BaseKitFragment {
    public static AddGetCashAccountFragment newInstance(Bundle args) {
        AddGetCashAccountFragment fragment = new AddGetCashAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    int type = 0;// 0 支付宝账号 1银行卡账号
    @BindView(R.id.tv_1)
    RequiredTextView tv1;
    @BindView(R.id.et_1)
    ClearEditText et1;
    @BindView(R.id.tv_2)
    RequiredTextView tv2;
    @BindView(R.id.et_2)
    ClearEditText et2;
    @BindView(R.id.tv_3)
    RequiredTextView tv3;
    @BindView(R.id.et_3)
    ClearEditText et3;
    @BindView(R.id.tv_4)
    RequiredTextView tv4;
    @BindView(R.id.et_4)
    ClearEditText et4;
    Unbinder unbinder;
    boolean isedit=false;//是否是编辑
    GetCashAccountBean.DataBean data;
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_add_account;
    }

    @Override
    public void doLogic() {
        data= (GetCashAccountBean.DataBean) getArguments().getSerializable("data");
        isedit=getArguments().getBoolean("isedit",false);
        final int type=getArguments().getInt("type",0);
        showBackButton();
        setTitle("添加提现账户");
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=et1.getText().toString();
                String str2=et2.getText().toString();
                String str3=et3.getText().toString();
                String str4=et4.getText().toString();
                if(type==0){
                    if(TextUtils.isEmpty(str1)){
                        ToastUtil.toast("请输入支付宝账号");

                        return;
                    }
                    if(TextUtils.isEmpty(str2)){
                        ToastUtil.toast("请输入支付宝昵称");
                        return;
                    }
                    if(TextUtils.isEmpty(str3)){
                        ToastUtil.toast("请输入支付宝账号的真实姓名");
                        return;
                    }
                    if(str4.length()!=11){
                        ToastUtil.toast("请输入支付宝绑定的手机号");
                        return;
                    }
                    hideSoftInput();
                    if(isedit){
                        MeApi.EditBank(data.getUserbank_id(), str2, str1, str3, str4, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("添加成功");
                                EventBus.getDefault().post(new UpdateDataEvent());
                                pop();
                            }
                        },Object.class);
                    }else {
                        MeApi.AddBank("1", str2, str1, str3, str4, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("添加成功");
                                EventBus.getDefault().post(new UpdateDataEvent());
                                pop();
                            }
                        },Object.class);
                    }

                }else if(type==1){
                    if(TextUtils.isEmpty(str1)){
                        ToastUtil.toast("请输入银行卡号");
                        return;
                    }
                    if(TextUtils.isEmpty(str2)){
                        ToastUtil.toast("请输入开户银行");
                        return;
                    }
                    if(TextUtils.isEmpty(str3)){
                        ToastUtil.toast("请输入持卡人的真实姓名");
                        return;
                    }
                    if(str4.length()!=11){
                        ToastUtil.toast("请输入开卡时预留手机号");
                        return;
                    }
                    hideSoftInput();
                    if(isedit){
                        MeApi.EditBank(data.getUserbank_id(), str2,str1 , str3, str4, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("添加成功");
                                EventBus.getDefault().post(new UpdateDataEvent());
                                pop();
                            }
                        },Object.class);
                    }else {
                        MeApi.AddBank("2", str2,str1 , str3, str4, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("添加成功");
                                EventBus.getDefault().post(new UpdateDataEvent());
                                pop();
                            }
                        },Object.class);
                    }
                }


            }
        });
        if(type==0){
            if(isedit){
                setTitle("编辑支付宝账号");
                data= (GetCashAccountBean.DataBean) getArguments().getSerializable("data");
                if(data!=null){
                    et2.setText(data.getBankname());
                    et1.setText(data.getBankcard());
                    et3.setText(data.getBankuser());
                    et4.setText(data.getPhone());
                }
            }
            //支付宝
            tv2.setText("支付宝昵称");
            tv1.setText("支付宝账号");

            tv3.setText("真实姓名");
            tv4.setText("支付宝绑定手机号");

            et2.setHint("请输入支付宝昵称");
            et1.setHint("请输入支付宝账号");

            et3.setHint("请输入支付宝账号的真实姓名");
            et4.setHint("请输入支付宝绑定的手机号");
        }else if(type==1){
            if(isedit){
                setTitle("编辑银行卡账号");
                if(data!=null){
                    et1.setText(data.getBankcard());
                    et2.setText(data.getBankname());
                    et3.setText(data.getBankuser());
                    et4.setText(data.getPhone());
                }
            }
            //银行卡
            tv1.setText("银行卡号");
            tv2.setText("开户银行");
            tv3.setText("真实姓名");
            tv4.setText("银行预留手机号");
            et1.setHint("请输入银行卡号");
            et2.setHint("请输入开户银行");
            et3.setHint("请输入持卡人的真实姓名");
            et4.setHint("请输入开卡时预留手机号");

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
