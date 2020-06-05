package com.hym.httplib.interfaces;

/**
 * Created by 胡彦明 on 2017/6/27.
 * <p>
 * Description http回调接口
 * <p>
 * otherTips
 */

/**
 *
 * @param <R> 回执类型
 * @param
 */
public interface IHttpResultListener<R>{

    public void onStart(int mark);

    public void onSuccess(R data);

    public void onDataError(String status, String errormessage);

    public void onEmptyData();

    public void onFailed(Exception e);

    public void onFinish(int mark);

    public void dataRes(int mark, String data);//原始数据，用于自定义 解析
}
