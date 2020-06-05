package com.hym.httplib.nohttp;

import android.text.TextUtils;
import com.hym.httplib.model.BaseResult;

/**
 * Created by 胡彦明 on 2017/6/30.
 * <p>
 * Description 当前只针对，镰刀科技php服务器接口定义规则，nohtpp框架 通信结果判断
 * <p>
 * otherTips
 */

public class NohttpResult extends BaseResult {
    private String status;
    private String error;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @Override
    public boolean isSucceed() {
        if(!TextUtils.isEmpty(status)){
            if (status.equals("0")){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isEmpty() {
        if(!TextUtils.isEmpty(status)){
            if (status.equals("400")){
                return true;
            }
        }
        return false;
    }
}
