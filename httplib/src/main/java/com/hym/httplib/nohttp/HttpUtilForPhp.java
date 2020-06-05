package com.hym.httplib.nohttp;

import com.hym.httplib.HttpManager;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.hym.httplib.interfaces.IHttpUtil;
import com.hym.httplib.model.HttpRequest;

/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description 针对镰刀科技 接口规则 php服务器规则实现
 * <p>
 * otherTips
 */
@Deprecated
public class HttpUtilForPhp implements IHttpUtil{
    public  String apiAddress=getServerIp()+"/api";//接口api地址
    private static volatile HttpUtilForPhp httpUtilForPhp;
    private HttpUtilForPhp() {

    }
    public static HttpUtilForPhp getInstance() {
        if (httpUtilForPhp == null) {
            synchronized (HttpUtilForPhp.class) {
                if (httpUtilForPhp == null) {
                    httpUtilForPhp = new  HttpUtilForPhp();
                }
            }
        }
        return httpUtilForPhp;
    }

    @Override
    public String getServerIp() {
        return "http://zcshop.liandao.mobi";//服务器地址
    }
    @Override
    public HttpRequest getHttpRequest() {
        HttpRequestForPhp request=new  HttpRequestForPhp();
        request.setUrl(apiAddress);
        return request;
    }

    @Override
    public void apiexcuter(HttpRequest request, IHttpResultListener listener, Class clazz) {
        HttpManager.getInstance().post(request,listener,clazz);

    }

    public  class HttpRequestForPhp extends HttpRequest<String,String>{
        public static final int defaultMark=0x01;

        public HttpRequestForPhp(){
            setMark(defaultMark);
        }
        //接口名
        public void setApp(String app){
            AddParems("app",app);
        }
        //方法名
        public void setClassName(String className){
            AddParems("class",className);
        }

    }
}
