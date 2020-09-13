package com.hym.eshoplib.fragment.me;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.bean.me.IsBindWechatBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.loginmodule.bean.LocalTokenBean;
import com.hym.loginmodule.event.NeedPerfectInformationEvent;
import com.hym.loginmodule.event.WeChatLoginEvent;
import com.hym.loginmodule.fragment.mipai.BindPhoneFragment;
import com.hym.loginmodule.http.LoginApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import io.rong.imkit.RongIM;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 账号与安全
 * <p>
 * otherTips
 */

public class AccountFragment extends BaseKitFragment {
    @BindView(R.id.tv_1)
    SuperTextView tv1;
    @BindView(R.id.tv_2)
    SuperTextView tv2;
    @BindView(R.id.tv_3)
    SuperTextView tv3;
    @BindView(R.id.tv_4)
    SuperTextView tv4;

    @BindView(R.id.tv_5)
    SuperTextView tv5;

    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;
    int paypwd_type = -1;// 1设置支付密码，2修改支付密码 3，忘记支付密码

    public static AccountFragment newInstance(Bundle args) {
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("账号与安全");
        btnLogout.setVisibility(View.GONE);
        tv1.setLeftString("更换手机号");
        tv1.setRightString(getArguments().getString("phone", ""));
        tv2.setLeftString("微信账号");
        //tv2.setRightString("已关联");
        //tv3.setLeftString("修改登录密码");
        tv4.setVisibility(View.GONE);
        // tv4.setLeftString("设置支付密码");


    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
            @Override
            public void onSuccess(MedetailBean data) {
                if (data.getData().getPaypass_set().equals("1")) {
                    //修改
                    tv4.setLeftString("设置支付密码");
                    tv4.setVisibility(View.VISIBLE);
                    paypwd_type = 2;
                } else {
                    //设置
                    tv4.setLeftString("设置支付密码");
                    tv4.setVisibility(View.VISIBLE);
                    paypwd_type = 1;
                }
                if (data.getData().getIs_setpass().equals("1")) {
                    tv3.setLeftString("设置登录密码");
                    tv3.setRightString("已设置");
                } else {
                    tv3.setLeftString("设置登录密码");
                    tv3.setRightString("未设置");
                }
            }
        }, MedetailBean.class);
//        MeApi.IssetPaypass(new ResponseImpl<IFsetPayPwdBean>() {
//            @Override
//            public void onSuccess(IFsetPayPwdBean data) {
//
//                if(data.getData().getIs_set().equals("1")){
//                    tv4.setLeftString("修改支付密码");
//                    tv4.setVisibility(View.VISIBLE);
//                    paypwd_type=2;
//                }else {
//                    tv4.setLeftString("设置支付密码");
//                    tv4.setVisibility(View.VISIBLE);
//                    paypwd_type=1;
//                }
//            }
//        },IFsetPayPwdBean.class);
        MeApi.OtherInfo(new ResponseImpl<IsBindWechatBean>() {
            @Override
            public void onSuccess(IsBindWechatBean data) {
                if (data.getData().getBinding() == 1) {
                    tv2.setRightString("已绑定");
                } else {
                    tv2.setRightString("未绑定");
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ToastUtil.toast("关联");
                            WeChatLoginEvent event = new WeChatLoginEvent(0);
                            event.bindtype = 1;
                            EventBus.getDefault().post(event);
                        }
                    });
                }
            }
        }, IsBindWechatBean.class);


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

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_ChangePhone));
                break;
            case R.id.tv_2:
                //绑定微信账号
                // start(SettingCommonFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_3:
                //设置登录密码
                Bundle bundle_set = new Bundle();
                bundle_set.putString("title", tv3.getLeftString());
                start(SetOrResetLoginPwdFragment.newInstance(bundle_set));
                break;
            case R.id.tv_4:
                //设置支付密码
                if (paypwd_type == -1) {
                    ToastUtil.toast("数据异常");
                    return;
                }
                if (paypwd_type == 2) {
                    //修改支付密码
                    Bundle bundle = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_ResetSetPayPwd);
                    ActionActivity.start(_mActivity, bundle);
                } else {
                    //设置支付密码
                    Bundle bundle = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_SetPayPwd);
                    bundle.putInt("type", paypwd_type);
                    ActionActivity.start(_mActivity, bundle);
                }

                break;

            case R.id.tv_5:

                Log.e("TAG", "注销: " );
                LoginApi.logOut(_mActivity, new ResponseImpl<LocalTokenBean>() {
                    @Override
                    public void onSuccess(LocalTokenBean data) {
                        UserUtil.setIsLogin(_mActivity, false);
                        UserUtil.saveToken(_mActivity, data.getData().getLocaltoken());
                        RongIM.getInstance().logout();
                        SharePreferenceUtil.setStringData(_mActivity, "channelid", "");
                        // ToastUtil.toast("退出登录成功");
                        Intent intent = new Intent(_mActivity, MainActivity.class);
                        intent.putExtra("position", 0);
                        SharePreferenceUtil.setBooleanData(_mActivity, "isauth", false);
                        _mActivity.startActivity(intent);
                        _mActivity.finish();
                    }
                }, LocalTokenBean.class);

                break;

        }
    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goBind(NeedPerfectInformationEvent event) {
        //绑定手机
        if (TextUtils.isEmpty(event.id)) {
            // ToastUtil.toast("绑定成功");
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("id", event.id);
            bundle.putBoolean("type", true);//从设置的关联进入，关联成功后 pop 否则关闭activyt
            start(BindPhoneFragment.newInstance(bundle));
        }


    }
}
