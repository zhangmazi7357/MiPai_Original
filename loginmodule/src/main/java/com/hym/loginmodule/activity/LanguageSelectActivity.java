package com.hym.loginmodule.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hym.loginmodule.fragment.LanguagesSelectFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.lgoin.UnLoginEvent;
import cn.hym.superlib.interfaces.action.IFragmentAction;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2018/2/20.
 * <p>
 * Description 语言选择
 * <p>
 * otherTips
 */

public class LanguageSelectActivity extends BaseActionActivity implements IFragmentAction{
    public static void startForresult(Activity from, Bundle args, int reqCode) {
        Intent intent = new Intent(from, LanguageSelectActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent, reqCode);
    }
    public static void startForresult(Fragment from, Bundle args, int reqCode) {
        Intent intent = new Intent(from.getActivity(), LanguageSelectActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent, reqCode);
    }
    @Override
    public IFragmentAction getAction() {
        return this;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(UnLoginEvent event) {
        //用户未登录，或者登录失效，进入登录activity
        Logger.d("收到登录失效广播");
        Bundle bundle = new Bundle();
        bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
        bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
        LoginMainActivity.start(this, bundle);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public SupportFragment getTargetFragment(int model_type, int action) {
        return LanguagesSelectFragment.newInstance(getIntent().getExtras());
    }
}
