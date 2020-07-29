package com.hym.httplib.nohttp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hym.httplib.interfaces.IHttpManager;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.hym.httplib.model.BaseResult;
import com.hym.httplib.model.HttpRequest;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.nohttp.Binary;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡彦明 on 2017/6/29.
 * <p>
 * Description nohttp 实现网络请求
 * <p>
 * otherTips
 */

public class NoHttpImpl implements IHttpManager<String, Object, BaseResult> {
    private RequestQueue requestQueue;//线程队列
    private final int threadPoolCount = 5;//最大线程数
    private static final int REQUEST_POST = 0x01;
    private static final int REQUEST_GET = 0x02;
    Context context;

    @Override
    public void init(Context context, boolean debug) {
        this.context = context;
        InitializationConfig config = InitializationConfig.newBuilder(context)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
//                .cacheStore(
//                        // 如果不使用缓存，setEnable(false)禁用。
//                        new DBCacheStore(context).setEnable(true)
//                )
//                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
//                .cookieStore(
//                        // 如果不维护cookie，setEnable(false)禁用。
//                        new DBCookieStore(context).setEnable(true)
//                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
//                .networkExecutor(new OkHttpNetworkExecutor())
                // 全局通用Header，add是添加，多次调用add不会覆盖上次add。
//                .addHeader()
//                // 全局通用Param，add是添加，多次调用add不会覆盖上次add。
//                .addParam()
//                .sslSocketFactory() // 全局SSLSocketFactory。
//                .hostnameVerifier() // 全局HostnameVerifier。
                .retry(5) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();

        // 高级初始化
        NoHttp.initialize(config);

        // 一般初始化
//        NoHttp.initialize(context);
        com.yanzhenjie.nohttp.Logger.setDebug(debug);
        com.yanzhenjie.nohttp.Logger.setTag("IHttpManager_NoHttpImpl");
    }

    private RequestQueue createRequestQueue() {
        return NoHttp.newRequestQueue(threadPoolCount);//默认5个线程
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = createRequestQueue();
        }
        return requestQueue;
    }

    @Override
    public <R> void post(HttpRequest<String, Object> request, final IHttpResultListener<R> listener, Class<R> clazz) {
        Request<String> nohttprequest = getRequest(request, REQUEST_POST);
        excute(request.getMark(), nohttprequest, listener, clazz);

    }

    @Override
    public <R> void get(HttpRequest<String, Object> request, IHttpResultListener<R> listener, Class<R> clazz) {
        Request<String> nohttprequest = getRequest(request, REQUEST_GET);
        excute(request.getMark(), nohttprequest, listener, clazz);
    }

    @Override
    public <R> void postFile(HttpRequest<String, Object> request, IHttpResultListener<R> listener, Class<R> clazz) {
        Request<String> nohttprequest = getRequest(request, REQUEST_POST);
        excute(request.getMark(), nohttprequest, listener, clazz);
    }

    //判断是否成功
    @Override
    public boolean isSuccess(BaseResult result) {
        return result == null ? false : result.isSucceed();
    }

    //判断是否为空数据
    @Override
    public boolean isEmpty(BaseResult result) {
        return result == null ? false : result.isEmpty();
    }

    //获取请求对象
    private Request<String> getRequest(HttpRequest<String, Object> request, int requestType) {
        if (request == null || TextUtils.isEmpty(request.getUrl())) {
            return null;
        }
        String url = request.getUrl();
        Request<String> nohttpRequest = null;
        switch (requestType) {
            case REQUEST_POST:
                nohttpRequest = NoHttp.createStringRequest(url, RequestMethod.POST);
                //文件集合
                if (request.getFiles() != null && !TextUtils.isEmpty(request.getFilesKey())) {
                    List<Binary> filelist = new ArrayList<>();
                    File[] files = request.getFiles();

                    for (File file : files) {
                        filelist.add(new FileBinary(file));
                    }
                    nohttpRequest.add(request.getFilesKey(), filelist);

                }
                //单个文件
                if (request.getFile() != null && !TextUtils.isEmpty(request.getFileName())) {
                    nohttpRequest.add(request.getFileName(), request.getFile());
                }
                //请求参数
                if (request.getParams() != null) {
                    nohttpRequest.add(request.getParams());
                }
                break;
            case REQUEST_GET:
                nohttpRequest = NoHttp.createStringRequest(url, RequestMethod.GET);
                break;
        }
        return nohttpRequest;

    }

    /**
     * 执行请求
     *
     * @param what          标记
     * @param nohttprequest nohttp请求
     * @param listener      回执监听
     * @param clazz         回执对象claass
     * @param <R>           回执对象实体类型
     */
    private <R> void excute(int what, Request<String> nohttprequest, final IHttpResultListener<R> listener, final Class<R> clazz) {
        if (nohttprequest == null || listener == null) {
            return;
        }
        getRequestQueue().add(what, nohttprequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                listener.onStart(what);

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String dataString = response.get();
//                Log.e("======", "onSucceed: " +response.toString() );
                try {
                    NohttpResult result = JSON.parseObject(dataString, NohttpResult.class);
                    String res = result.getData();
                    String error = result.getError();
                    listener.dataRes(what, res);//返回原始数据
                    if (isSuccess(result)) {
                        //请求成功,解析
                        String data = result.getData();
                        listener.onSuccess(JSON.parseObject(data, clazz));
                    } else if (isEmpty(result)) {
                        //空数据
                        listener.onEmptyData();
                    } else {
                        //请求成功但是数据异常,比如账号已经被注册等。。。
                        listener.onDataError(result.getStatus(), error);
                    }

                } catch (Exception e) {
                    //请求成功但是解析失败,比如服务器返回了脏数据，或者服务器数据做了改动，并没有通知前端进行更改导致的解析时异常
                    listener.onDataError("6666", "ParseDataException=" + e.toString());
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                listener.onFailed(response.getException());


            }

            @Override
            public void onFinish(int what) {
                listener.onFinish(what);


            }
        });
    }
}
