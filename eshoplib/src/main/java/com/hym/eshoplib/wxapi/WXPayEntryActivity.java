package com.hym.eshoplib.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.event.WxPayResultEvent;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.pay.Constants;


public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        //Logger.d(TAG, "onPayFinish, errCode = " + resp.errCode+"===errorStr"+resp.errStr);

        Log.e("TAG", "onResp: 微信支付 " + JSONObject.toJSONString(resp));

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Logger.d("code=" + resp.errCode);
            switch (String.valueOf(resp.errCode)) {
                case "0":
                    EventBus.getDefault().post(new WxPayResultEvent(0));
                    finish();
                    break;
                case "-1":
                    EventBus.getDefault().post(new WxPayResultEvent(-1));
                    finish();
                    break;
                case "-2":
                    EventBus.getDefault().post(new WxPayResultEvent(-2));
                    finish();
                    break;
            }


        }
    }

}