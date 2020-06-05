package com.hym.eshoplib.event;


public class WxPayResultEvent {
    public WxPayResultEvent(int code) {
        this.code = code;
    }
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private int code;//0,成功，-1失败错误，-2，用户取消

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
