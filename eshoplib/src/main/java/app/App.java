package app;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import androidx.multidex.MultiDex;
import android.view.View;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.RongConversationaActivity;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.rongyun.MyExtensionModule;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.hym.superlib.application.KitBaseApplication;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;


/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class App extends KitBaseApplication {
    public static App instance;
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(com.hym.eshoplib.R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

    }
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "a9ff41288a", isDebug());
        instance = this;
        ToastUtil.init(this);

        //
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx425c93f17a0de199", "b6df3f90ada2704b129457d51e8a1ab0");
        PlatformConfig.setSinaWeibo("998280657", "cb74b9785c962af35df49d18500c5080", "http://jzweb.liandao.mobi/");

        PlatformConfig.setQQZone("101523866", "5d445487c5423c665520073be7b762ee");

        RongIM.init(this);
        setMyExtensionModule();

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                MeApi.getUserinfo(s,new IHttpResultListener<MedetailBean>() {
                    @Override
                    public void onStart(int mark) {

                    }

                    @Override
                    public void onSuccess(MedetailBean data) {

                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(data.getData().getUserid(),data.getData().getNickname() , Uri.parse(data.getData().getAvatar())));

                    }

                    @Override
                    public void onDataError(String status, String errormessage) {

                    }

                    @Override
                    public void onEmptyData() {

                    }

                    @Override
                    public void onFailed(Exception e) {

                    }

                    @Override
                    public void onFinish(int mark) {

                    }

                    @Override
                    public void dataRes(int mark, String data) {

                    }
                },MedetailBean.class);
                return null;
            }
        },true);
        RongIM.getInstance().setConversationClickListener(new RongIM.ConversationClickListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                //ToastUtil.toast("头像点击="+userInfo.getUserId()+",s="+s);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                boolean b=isApplicationInBackground(instance);
                Logger.d("后台="+b);
                // 如果应用在后台 就弹出悬浮窗

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(instance);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 通知渠道的id
                    String id = "my_channel_01";
                    // 用户可以看到的通知渠道的名字.
                    CharSequence name ="聊天消息";
                    // 用户可以看到的通知渠道的描述
                    String description = "用于接收聊天消息提醒";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    //注意Name和description不能为null或者""
                    NotificationChannel mChannel = new NotificationChannel(id, name, importance);
                    // 配置通知渠道的属性
                    mChannel.setDescription(description);
                    // 设置通知出现时的闪灯（如果 android 设备支持的话）
                    mChannel.enableLights(false);
                    mChannel.setLightColor(Color.RED);
                    // 设置通知出现时的震动（如果 android 设备支持的话）
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{1000, 500, 2000});
                    //最后在notificationmanager中创建该通知渠道
                    notificationManager.createNotificationChannel(mChannel);
                    builder.setChannelId(id);
                }
                builder.setContentInfo("补充内容");
                // 判断消息类型  根据自己需求进行判断
                if (message.getContent() instanceof TextMessage){
                    String textMessageContent = ((TextMessage) message.getContent()).getContent();
                    builder.setContentText(textMessageContent);
                }else if (message.getContent() instanceof ImageMessage){
                    builder.setContentText("图片消息");
                }else if (message.getContent() instanceof VoiceMessage){
                    builder.setContentText("语音消息");
                }else {
                    builder.setContentText("其他消息");
                }
                //设置弹窗以及通知栏显示内容
                builder.setContentTitle(message.getContent().getUserInfo().getName());
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setTicker("新消息");
                // 是否自动消失
                builder.setAutoCancel(true);
                // 是否显示时间
                builder.setShowWhen(true);
                // 设置接收时间
                builder.setWhen(message.getReceivedTime());

                // 设置会话界面
                Intent intent = new Intent(instance, RongConversationaActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri;
                uri =  Uri.parse("rong://" + getPackageName()).buildUpon()
                        .appendPath("conversation").appendPath(message.getConversationType().getName().toLowerCase())
                        .appendQueryParameter("title", message.getContent().getUserInfo().getName())
                        .appendQueryParameter("targetId", message.getContent().getUserInfo().getUserId())
                        .build();
                intent.setData(uri);
                PendingIntent pendingIntent = PendingIntent.getActivity(instance, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pendingIntent);
                Notification notification = builder.build();
                notificationManager.notify(message.getMessageId(), notification);

                return true;

            }
        });
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
            @Override
            public void onCountChanged(int i) {
                //Logger.d("未读消息数="+i); 发送通知更新消息数
                EventBus.getDefault().post(new MessageEvent());
            }
        }, Conversation.ConversationType.PRIVATE);
        if(UserUtil.getIsLogin(this)){
            //如果用户已经登录并且连接已经断开的情况下调用连接
            reconnect(UserUtil.getRongYunToken(this));
        }

        //初始化极光
        JPushInterface.setDebugMode(isDebug());
        JPushInterface.init(this);


//        JPushInterface.addTags(this,10001,);
        JPushInterface.setAlias(this,10001,"Jp");



        //static 代码段可以防止内存泄露




    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public boolean isDebug() {
        return true;
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static void reconnect(String token) {
        if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Logger.d("---onTokenIncorrect--");
                }

                @Override
                public void onSuccess(String s) {
                    Logger.d("---onSuccess--" + s);
                    //连接成功后进入 聊天界面
                    EventBus.getDefault().post(new MessageEvent());
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {
                    Logger.d("---onError--" + e);
                }
            });
        }
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }

    /**
     *  判断应用是否在后台运行 （针对Android5.0以后）
     * @param context
     * @return
     */
    public boolean isApplicationInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                //前台程序
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}
