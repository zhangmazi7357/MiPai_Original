package com.hym.loginmodule.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hym.loginmodule.fragment.mipai.BindPhoneFragment;
import com.hym.loginmodule.fragment.mipai.QuickLoginFragment;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.WebFragment;
import cn.hym.superlib.interfaces.action.IFragmentAction;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/12/15.
 * <p>
 * Description 登录功能载体activity
 * <p>
 * otherTips
 */

public class LoginMainActivity extends BaseActionActivity implements IFragmentAction {
    public static final int ModelType_Login = 0x01;//登录模块包括，引导页，语言选择，和具体登录操作
    public static final int Action_guide = 0x11;//引导页
    public static final int Action_language_select = 0x12;//语言选择；
    public static final int Action_login = 0x13;//具体登录操作
    public static final int Action_bind_phone = 0x14;//绑定手机号
    public static final int Action_web = 0x15;//网页

    public static void start(Activity from, Bundle args) {
        Intent intent = new Intent(from, LoginMainActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivity(intent);
    }

    public static void startForresult(Activity from, Bundle args, int reqCode) {
        Intent intent = new Intent(from, LoginMainActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent, reqCode);
    }

    @Override
    public IFragmentAction getAction() {
        return this;
    }

    @Override
    public SupportFragment getTargetFragment(int model_type, int action) {
        //bundle 可传可不传，ActionActivity 会重新设置fragment,setArguments()方法
        SupportFragment fragment = null;
        if (model_type == ModelType_Login) {
            switch (action) {
                case Action_guide:
                    break;
                case Action_language_select:
                    //fragment = LanguagesSelectFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_login:
                    fragment = QuickLoginFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_bind_phone:
                    fragment = BindPhoneFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_web:
                    fragment = WebFragment.newInstance(getIntent().getExtras());
                    break;
            }
        }
        return fragment;
    }

    //    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        return new DefaultHorizontalAnimator();
//    }
}
