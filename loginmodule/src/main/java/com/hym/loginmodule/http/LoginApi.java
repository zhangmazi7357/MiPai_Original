package com.hym.loginmodule.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.hym.httplib.interfaces.IHttpResultListener;

import java.io.File;

import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2017/7/20.
 * <p>
 * Description 接口复用管理，抽出具体参数层，post调用底层方法不关心具体参数，所以可以在此层任意扩展任何参数
 * <p>
 * otherTips
 */

public class LoginApi {
    //注册
    public static <T> void signUp(String language, String phone, String email, String pwd,
                                  String verify, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Signup");
        if (!TextUtils.isEmpty(phone)) {
            request.AddParems("phone", phone);
        }
//        if (!TextUtils.isEmpty(email)) {
//            request.AddParems("email", email);
//        }
        request.AddParems("password", pwd);
        request.AddParems("verify", verify);
        //request.addParamsNotEmpty("language",language);
        post(request, listener, clazz);

    }

    //获取验证码
    public static <T> void getCode(Context context, String language,
                                   String phone, String vtype, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Send");
//        request.AddParems("phone", phone);
//        request.AddParems("vtype", vtype);//验证码类型-必须（1：注册，2：快捷登录，3：忘记密码，4：三方绑定，7：绑定手机，8：修改手机）
//        post(request, listener, clazz);
        request.AddParems("phone", phone);
        request.AddParems("vtype", vtype);//验证码类型-必须

        // request.AddParems("language", language);
        String token = UserUtil.getToken(context);
        if (!TextUtils.isEmpty(token)) {
            request.AddParems("token", token);
        }
        post(request, listener, clazz);

    }

    //获取邮箱验证码
    public static <T> void getEmailCode(String email, String vtype, String language, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("SendVerifyEmail");
        request.AddParems("email", email);
        request.AddParems("vtype", vtype);//验证码类型-必须（1：注册，2：会计登录，3：忘记密码，4：三方绑定，6：设置或修改密码，7：绑定邮箱，8：修改邮箱）
        request.AddParems("language", language);//语言类型-必须（1：中文，2：英文，3：日文）
        post(request, listener, clazz);

    }

    //登录
    public static <T> void login(Context context, String phone, String email, String password,
                                 String language, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Login");
        //  request.AddParems("language", language);//语言类型-必须（1：中文，2：英文，3：日文）
        if (!TextUtils.isEmpty(phone)) {
            request.AddParems("phone", phone);
        }
//        if (!TextUtils.isEmpty(email)) {
//            request.AddParems("email", email);
//        }
        String token = UserUtil.getToken(context);
        if (!TextUtils.isEmpty(token)) {
            request.AddParems("localtoken", token);
        }
        request.AddParems("password", password);
//        request.AddParems("language", language);
        request.addParamsNotEmpty("device_type", "1");
        request.addParamsNotEmpty("channelid", SharePreferenceUtil.getStringData(context, "channelid"));

        Log.e("=== ", "login: " + JSONObject.toJSONString(request));
        post(request, listener, clazz);

    }

    //获取本地token
    public static <T> void getLocalToken(String language, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetLocalKey");
        request.AddParems("language", language);//语言类型-必须（1：中文，2：英文，3：日文）
        post(request, listener, clazz);
    }

    //切换语言
    public static <T> void changeLanguage(String language, String token, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ChangeLanguage");
        request.AddParems("token", token);
        request.AddParems("language", language);//语言类型-必须（1：中文，2：英文，3：日文）
        post(request, listener, clazz);
    }

    //三方登陆
    public static <T> void otherLogin(String language, String thirdid, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("OtherLogin");
        request.AddParems("language", "1");//语言类型-必须（1：中文，2：英文，3：日文）
        request.AddParems("thirdid", thirdid); //三方openID-必须
        post(request, listener, clazz);
    }

    //设置密码
    public static <T> void setPassword(Context context, String phone, String email, String verify, String password, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ForgetPass");
        if (!TextUtils.isEmpty(phone)) {
            request.AddParems("phone", phone);
        }
        if (!TextUtils.isEmpty(email)) {
            request.AddParems("email", email);
        }
        if (!TextUtils.isEmpty(verify)) {
            request.AddParems("verify", verify);
        }
        String token = UserUtil.getToken(context);
        if (!TextUtils.isEmpty(token)) {
            request.AddParems("token", token);
        }
        request.AddParems("password", password);
        post(request, listener, clazz);
    }

    //绑定
    public static <T> void reset(Context context, String phone, String email, String verify, String password, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ResetPhone");
        if (!TextUtils.isEmpty(phone)) {
            request.AddParems("phone", phone);
        }
        if (!TextUtils.isEmpty(email)) {
            request.AddParems("email", email);
        }
        if (!TextUtils.isEmpty(verify)) {
            request.AddParems("verify", verify);
        }
        String token = UserUtil.getToken(context);
        if (!TextUtils.isEmpty(token)) {
            request.AddParems("token", token);
        }
        request.AddParems("type", type);
        request.addParamsNotEmpty("password", password);
        post(request, listener, clazz);
    }

    //登出
    public static <T> void logOut(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Logout");
        request.AddParems("token", UserUtil.getToken(context));
        post(request, listener, clazz);

    }

    public static <T> void uploadFile(Context contexts, File[] files, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Public");
        request.setClassName("AddAtachment");
        request.AddParems("token", UserUtil.getToken(contexts));
        request.setFilesKey("attachment[]");
        request.setFiles(files);
        post(request, listener, clazz);

    }

    //完善资料
    public static <T> void perfectData(Context context, String gender, String nickname, String age, String attachment_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("FinishInfo");
        request.AddParems("token", UserUtil.getToken(context));
        if (gender.equals("男")) {
            gender = "1";
        } else {
            gender = "2";
        }
        request.AddParems("gender", gender);
        request.AddParems("nickname", nickname);
        request.AddParems("age", age);
        request.AddParems("attachment_id", attachment_id);
        post(request, listener, clazz);
    }

    //获取用户信息
    public static <T> void getUserinfo(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetUserinfo");
        request.AddParems("token", UserUtil.getToken(context));
        post(request, listener, clazz);

    }

    //获取本地token
    public static <T> void quikeLogin(String phone, String verify, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("PhoneLogin");
        request.AddParems("phone", phone);
        request.AddParems("verify", verify);
        post(request, listener, clazz);
    }

    //绑定手机号
    public static <T> void bindPhone(Context context, String phone, String verify, String thirdid, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("OtherBinding");
        if (!TextUtils.isEmpty(phone)) {
            request.AddParems("phone", phone);
        }
        if (!TextUtils.isEmpty(verify)) {
            request.AddParems("verify", verify);
        }
        String token = UserUtil.getToken(context);
        if (!TextUtils.isEmpty(token)) {
            request.AddParems("token", token);
        }
        request.AddParems("thirdid", thirdid);
        post(request, listener, clazz);
    }
}
