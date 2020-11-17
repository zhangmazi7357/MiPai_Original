package com.hym.eshoplib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.event.CollectionEvent;
import com.hym.eshoplib.event.MainMessageEvent;
import com.hym.eshoplib.event.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";
    private static final int NOTIFICATION_ID = 1005;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Bundle bundle = intent.getExtras();
//            Log.e(TAG, "[MyReceiver] onReceive - " + intent.getAction() +
//                    ", extras: " + printBundle(bundle));


            // 注册极光推送
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.e(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

                // 自定义消息
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

                Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " +
                        bundle.getString(JPushInterface.EXTRA_MESSAGE));


                // 通知
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

//                Log.e(TAG, "[MyReceiver] 接收到推送下来的通知");
//                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
                String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                String message = bundle.getString(JPushInterface.EXTRA_ALERT);
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);


//                Log.e(TAG, "收到推送 = title = " + title + " \n"
//                        + "message = " + message + "\n"
//                        + "extra = " + extra);

                // TODO 通知收款页 收款成功;
                if ("收款成功".equals(title)) {

                    EventBus.getDefault().post(new CollectionEvent());
//                    Toast.makeText(context, "收款成功", Toast.LENGTH_SHORT).show();

                } else {

                    // 去告知首页 ,您有一条新消息 ;
                    EventBus.getDefault().post(new MainMessageEvent());


                    //SharePreferenceUtil.setBooleanData(context,"newmsg",true);
                    // 更新未读消息 ;
                    EventBus.getDefault().post(new MessageEvent());

                }

                // 用户点开了 通知
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.e(TAG, "[MyReceiver] 用户点击打开了通知");

                openNotification(context, bundle);

                //打开自定义的Activity
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("position", 1);
                i.putExtras(bundle);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);


                // 富文本 ??
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.e(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));

                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

                // 连接状态改变 ??
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.e(TAG, "[MyReceiver]" + intent.getAction() +
                        " connected state change to " + connected);

            } else {
                Log.e(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {

        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {

            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
//                    Logger.e(TAG, "Get message extra JSON error!");
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


    // 接收 推送过来的通知 ;
    private void receivingNotification(Context context, Bundle bundle) {
//        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//        Log.e(TAG, " title : " + title);
//        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
//        Log.e(TAG, "message : " + message);
//        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//        Log.e(TAG, "extras : " + extras);

//        sendNotification(context);

    }

    // 用户打开了 通知
    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("myKey");

        } catch (Exception e) {
            Log.e(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }

        Log.e(TAG, "打开通知  ，  myValue = " + myValue);

//        if (TYPE_THIS.equals(myValue)) {
//            Intent mIntent = new Intent(context, ThisActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        } else if (TYPE_ANOTHER.equals(myValue)) {
//            Intent mIntent = new Intent(context, AnotherActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        }
    }


}
