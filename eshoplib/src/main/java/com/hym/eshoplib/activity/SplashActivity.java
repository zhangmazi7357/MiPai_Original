package com.hym.eshoplib.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;

import com.hym.eshoplib.R;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.hym.loginmodule.bean.LocalTokenBean;
import com.hym.loginmodule.http.LoginApi;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.utils.HttpStatusUtil;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;


/**
 * 开屏页
 */

public class SplashActivity extends AppCompatActivity {
    private static Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 0x01);//自定义的code
        }


        setContentView(R.layout.splash_activtiy);
        String token = UserUtil.getToken(this);

        if (TextUtils.isEmpty(token)) {
            //没有token 说明还没登录过则获取本地token
            // Logger.d("未登录过，获取本地token");
            LoginApi.getLocalToken("1",
                    new ResponseImpl<LocalTokenBean>() {
                        @Override
                        public void onSuccess(LocalTokenBean data) {
                            String token = data.getData().getLocaltoken();
                            UserUtil.saveToken(SplashActivity.this, token);
                        }
                    }, LocalTokenBean.class);

        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!SharePreferenceUtil.getBooleangData(SplashActivity.this, "first_guide")) {
                    //如果是第一次使用软件则进入引导页面，
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                finish();
//                if(!SharePreferenceUtil.getBooleangData(SplashActivity.this, LanguagesSelectFragment.key_has_selected)){
//                    //用户没有选择过语言，则需要选择语言
//                    Bundle bundle=new Bundle();
//                    bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
//                    bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_language_select);
//                    bundle.putBoolean("canback",false);
//                    LanguageSelectActivity.startForresult(SplashActivity.this,bundle,0x11);
//                }else {
//                    //已经选择了语言，则进入主页
//                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                    finish();
//                }
            }
        }, 3000);

    }

    /**
     * 屏蔽掉返回键
     */
    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //选择语言完毕
            case 0x11:
                if (resultCode == RESULT_OK) {
                    //startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } else {
                    //没有选择语言直接返回，则关闭 ，第一次进入必须选择语言
                    finish();
                }
                break;
        }
    }

    public abstract class ResponseImpl<T> implements IHttpResultListener<T> {
        @Override
        public void onStart(int mark) {
            //请求开始
            //showProgressDialog();
        }

        @Override
        public void onFinish(int mark) {
            //请求结束
            //dissMissDialog();
        }

        @Override
        public void onDataError(String status, String errormessage) {
            //请求成功但是数据异常
            HttpStatusUtil.handleErrorStatus(status, errormessage);
            Logger.d(errormessage);
            ToastUtil.toast(errormessage);
            //dissMissDialog();
        }

        @Override
        public void onFailed(Exception e) {
            //由于网络问题导致的失败
            Logger.d(e.toString());
            ToastUtil.toast("Internet Error,please check your Internet connecttion and try it again");
            // dissMissDialog();

        }

        @Override
        public void onEmptyData() {
            Logger.d("空数据");

        }

        @Override
        public void dataRes(int code, String data) {
            //请求回来的原始数据未处理过的
            Logger.d("sourceData=" + data);

        }
    }
}
