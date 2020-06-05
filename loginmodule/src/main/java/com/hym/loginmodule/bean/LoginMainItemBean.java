package com.hym.loginmodule.bean;

import androidx.annotation.DrawableRes;

/**
 * Created by 胡彦明 on 2018/1/8.
 * <p>
 * Description 登录，主界面
 * <p>
 * otherTips
 */

public class LoginMainItemBean {
    @DrawableRes int icon_res;
    String name;

    public int getIcon_res() {
        return icon_res;
    }

    public void setIcon_res(int icon_res) {
        this.icon_res = icon_res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginMainItemBean( int icon_res, String name) {
        this.icon_res = icon_res;
        this.name = name;
    }
}
