package com.hym.httplib.interfaces;

import com.hym.httplib.model.HttpRequest;

/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description  http 工具抽象接口
 * <p>
 * otherTips
 */

public interface IHttpUtil {
    public String getServerIp();//服务器ip
    public HttpRequest getHttpRequest();//获取默认请求参数
    public void apiexcuter(HttpRequest request, IHttpResultListener listener, Class clazz);//api执行器
}
