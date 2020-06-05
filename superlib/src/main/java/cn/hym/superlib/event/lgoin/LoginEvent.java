package cn.hym.superlib.event.lgoin;

import android.os.Bundle;

/**
 * Created by 胡彦明 on 2018/1/13.
 * <p>
 * Description 登录成功发出通知
 * <p>
 * otherTips
 */

public class LoginEvent {
    Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public LoginEvent(Bundle bundle) {
        this.bundle = bundle;
    }
}
