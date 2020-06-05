package com.hym.loginmodule.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.loginmodule.R;
import com.hym.loginmodule.bean.LoginMainItemBean;
import com.hym.loginmodule.event.NeedPerfectInformationEvent;
import com.hym.loginmodule.event.WeChatLoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2017/12/15.
 * <p>
 * Description 登录主界面
 * <p>
 * otherTips
 */
@Deprecated
public class LoginMainFragment extends BaseListFragment<LoginMainItemBean> {

    public static LoginMainFragment newInstance(Bundle args) {
        LoginMainFragment fragment = new LoginMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_login_main;
    }

    @Override
    public void excuteLogic() {
        showBackButton();
        setTitle(R.string.Login_Registration);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //手机号登录
                        start(LoginByMobilePhoneFrament.newInstance(LoginByMobilePhoneFrament.TYPE_LOGIN_PHONE));
                        break;
                    case 1:
                        //邮箱登录
                        start(LoginByMobilePhoneFrament.newInstance(LoginByMobilePhoneFrament.TYPE_LOGIN_EMAIL));
                        break;
                    case 2:
                        //微信登录
                        EventBus.getDefault().post(new WeChatLoginEvent(0));
                        break;
                    case 3:
                        //手机号注册
                        start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_REGIST_PHONE));
                        break;
                    case 4:
                        //邮箱注册
                        start(RegistByMobilePhoneFragment.newInstance(RegistByMobilePhoneFragment.TYPE_REGIST_EMAIL));
                        break;
                }

            }
        });
        getRefreshLayout().setEnabled(false);

    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        getAdapter().addData(new LoginMainItemBean(R.drawable.ic_phone_login, getResources().getString(R.string.LoginwithMobileNo)));
        getAdapter().addData(new LoginMainItemBean(R.drawable.ic_email_login, getResources().getString(R.string.Loginwith_EmailAddress)));
        getAdapter().addData(new LoginMainItemBean(R.drawable.ic_wechat_login, getResources().getString(R.string.LoginwithWechat)));

        getAdapter().addData(new LoginMainItemBean(R.drawable.ic_phone_regist, getResources().getString(R.string.RegisterwithMobileNo)));
        getAdapter().addData(new LoginMainItemBean(R.drawable.ic_email_login, getResources().getString(R.string.RegisterwithEmailAddress)));
        dissMissDialog();


    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void bindData(BaseViewHolder helper, LoginMainItemBean item, int position) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        String name = item.getName() + "";
        iv_icon.setImageResource(item.getIcon_res());
        helper.setText(R.id.tv_name, name);
        TextView tvTitle = helper.getView(R.id.tv_title);
        if (position == 0) {
            tvTitle.getLayoutParams().height=ScreenUtil.dip2px(_mActivity,45);
            tvTitle.setText("一 " + getResources().getString(R.string.Login) + " 一");
            tvTitle.setVisibility(View.VISIBLE);
        } else if (position == 3) {
            tvTitle.getLayoutParams().height=ScreenUtil.dip2px(_mActivity,100);
            tvTitle.setText("一 " + getResources().getString(R.string.Registration) + " 一");
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    @Subscribe
    public void getThirdLoginResult(WeChatLoginEvent weChatLoginEvent) {
        if (weChatLoginEvent.getStatus() == 1) {
            _mActivity.finish();
        }
    }

    @Subscribe
    public void needPerfectMessage(NeedPerfectInformationEvent needPerfectInformationEvent) {
        start(PerfectInformationFragment.newInstance(new Bundle()));
    }

    @Override
    public boolean openEventBus() {
        return true;
    }
}
