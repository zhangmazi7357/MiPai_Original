package com.hym.eshoplib.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.event.CollectionEvent;
import com.hym.eshoplib.event.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.hym.superlib.utils.view.SystemBarUtil;
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


                // TODO 通知收款页 收款成功;
                if ("收款成功".equals(title)) {

                    EventBus.getDefault().post(new CollectionEvent());

                } else {


                    customNotification(context, bundle);
                    toast(context, bundle);


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


    // 自定义通知栏 ;
    private void customNotification(Context context, Bundle bundle) {

        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

        // 使用notification
        // 使用广播或者通知进行内容的显示
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title);

        builder.setDefaults(Notification.DEFAULT_SOUND);
        manager.notify(2, builder.build());

    }

    private void toast(Context context, Bundle bundle) {
        View layoutView = LayoutInflater.from(context)
                .inflate(R.layout.toast_top, null);

        //设置文本的参数 设置加载文本文件的参数，必须通过LayoutView获取。
        TextView timeView = layoutView.findViewById(R.id.tv_assist_toast_time);
        TextView contentView = layoutView.findViewById(R.id.tv_assist_toast_content);
        TextView titletView = layoutView.findViewById(R.id.tv_assist_toast_title);

        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

//        timeView.setText(title);
        titletView.setText(title);
        contentView.setText(message);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置TextView的宽度为 屏幕宽度
        layoutView.setLayoutParams(layoutParams);
        //获得屏幕的宽度
        //创建toast对象，
        Toast toast = new Toast(context);
        //把要Toast的布局文件放到toast的对象中
        toast.setView(layoutView);
        toast.setDuration(toast.LENGTH_LONG);


        // 状态栏高度
        int statusBarHeight = SystemBarUtil.getSystemBarHeight(context);

        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, statusBarHeight);

        toast.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置Toast可以布局到系统状态栏的下面
        toast.show();
    }

}
