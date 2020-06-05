package com.hym.loginmodule.fragment.mipai;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.fragment.base.BaseLoginFragment;
import com.hym.loginmodule.http.LoginApi;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;

/**
 * Created by 胡彦明 on 2018/10/11.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class BindPhoneFragment extends BaseLoginFragment{
    String third_id;
    boolean type=false;
    public static BindPhoneFragment newInstance(Bundle args) {
        BindPhoneFragment fragment = new BindPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        getArguments().getBoolean("type",false);
        llAgreement.setVisibility(View.VISIBLE);
        third_id=getArguments().getString("id","");
        setTitle("绑定手机号");
        showBackButton();
        ll3.setVisibility(View.GONE);
        tvSubFunction.setVisibility(View.GONE);
        llAgreement.setVisibility(View.VISIBLE);
        btnConfirm.setText("登录");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(third_id)){
                    ToastUtil.toast("数据异常请退出重试");
                    return;
                }
                String phone=et1.getText().toString();
                String code=et2.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.toast("请输入正确绑定的手机号");

                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.toast("请输入验证码");
                    return;
                }
                LoginApi.bindPhone(_mActivity, phone, code, third_id, new ResponseImpl<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        UserUtil.setIsLogin(_mActivity,true);
                        UserUtil.saveToken(_mActivity,data.getData().getToken());//token
                        UserUtil.saveRongYunToken(_mActivity,data.getData().getRongcloud_token());//融云
                       // SharePreferenceUtil.setStringData(_mActivity, "region",(TextUtils.isEmpty(data.getData().getRegion_name())?"":data.getData().getRegion_name()));//区域
                        Bundle bundle=new Bundle();
                        bundle.putString("id",data.getData().getUserid());
                        bundle.putString("name",data.getData().getNickname());
                        bundle.putString("url",data.getData().getAvatar());
                        EventBus.getDefault().post(new LoginEvent(bundle));
                        ToastUtil.toast("绑定成功");
                        if(type){
                            pop();
                        }else {
                            _mActivity.finish();
                        }
                    }
                },LoginBean.class);

            }
        });
    }

    @Override
    public CheckCodeBean getCheckCodeBean() {
        return new CheckCodeBean(et1.getText().toString(),"4");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


    }
}
