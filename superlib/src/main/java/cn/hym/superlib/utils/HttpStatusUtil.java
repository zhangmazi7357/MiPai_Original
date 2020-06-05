package cn.hym.superlib.utils;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.event.lgoin.UnLoginEvent;

/**
 * Created by 胡彦明 on 2017/12/16.
 * <p>
 * Description http 错误状态码处理
 * <p>
 * otherTips
 */

public class HttpStatusUtil {
    public static void handleErrorStatus(String status,String errormessage){
        if(status.equals("300")){
            //登录失效
            //ToastUtil.toast("Please login");
            EventBus.getDefault().post(new UnLoginEvent());
            Logger.d("status="+status+"message="+errormessage+"==登录失效发送广播");
        }
    }
}
