package cn.hym.superlib.event.action;

import android.content.Intent;

/**
 * Created by 胡彦明 on 2018/3/19.
 * <p>
 * Description 用于解决在不同moudle 中，调用不同界面的导航event ，之后模块化开发时候会变为路由形式，暂时过度
 * <p>
 * otherTips
 */

public class BaseActionEvent {
    //type 1,由其他模块进入主界面的消息模块
    int type=0;
    Intent intent;
    public BaseActionEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
