package com.hym.loginmodule.event;

/**
 * Created by 陈晖 on 2018/4/15.
 */

public class WeChatLoginEvent {

    /**
     * 0表示发起登陆
     * 1表示登陆成功
     */
    private int status;
    public int bindtype=0;//当等于1时候是从设置里发起绑定，直接调用绑定不调用桑拿房登录接口

    public WeChatLoginEvent(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
