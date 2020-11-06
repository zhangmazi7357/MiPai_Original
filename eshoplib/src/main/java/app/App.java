package app;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
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

    private AppLifecycleObserver appLifecycleObserver;
    private static final int NOTIFICATION_ID = 1001;


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


        // 注册全局捕获异常
        CrashHandler.getInstance().init(this);


        //
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx425c93f17a0de199", "b6df3f90ada2704b129457d51e8a1ab0");
        PlatformConfig.setSinaWeibo("998280657", "cb74b9785c962af35df49d18500c5080", "http://jzweb.liandao.mobi/");

        PlatformConfig.setQQZone("101523866", "5d445487c5423c665520073be7b762ee");

        RongIM.init(this);
        setMyExtensionModule();


        // 监听 是否App 进入后台 ；
        appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);


        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {

                MeApi.getUserinfo(s, new IHttpResultListener<MedetailBean>() {
                    @Override
                    public void onStart(int mark) {

                    }

                    @Override
                    public void onSuccess(MedetailBean data) {

                        RongIM.getInstance().refreshUserInfoCache(
                                new UserInfo(data.getData().getUserid(),
                                        data.getData().getNickname(),
                                        Uri.parse(data.getData().getAvatar())
                                )
                        );

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
                }, MedetailBean.class);
                return null;
            }
        }, true);

        RongIM.getInstance().setConversationClickListener(new RongIM.ConversationClickListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType,
                                               UserInfo userInfo, String s) {
                //ToastUtil.toast("头像点击="+userInfo.getUserId()+",s="+s);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType,
                                                   UserInfo userInfo, String s) {
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


        RongIM.getInstance().setMessageAttachedUserInfo(true);

        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
                                                                      @Override
                                                                      public void onCountChanged(int i) {
                                                                          //Logger.d("未读消息数="+i); 发送通知更新消息数
                                                                          EventBus.getDefault().post(new MessageEvent());
                                                                      }
                                                                  },
                Conversation.ConversationType.PRIVATE);




//        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
//            @Override
//            public boolean onReceived(Message message, int i) {
//                return false;
//            }
//        });


        if (UserUtil.getIsLogin(this)) {
            //如果用户已经登录并且连接已经断开的情况下调用连接
            reconnect(UserUtil.getRongYunToken(this));
        }

        //初始化极光
        JPushInterface.setDebugMode(isDebug());
        JPushInterface.init(this);


//        JPushInterface.addTags(this,10001,);
        //   JPushInterface.setAlias(this, 10001, "Jp");


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


    public static void reconnect(String token) {

        if (RongIM.getInstance().getCurrentConnectionStatus()
                .equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Logger.d("---onTokenIncorrect--");
                }

                @Override
                public void onSuccess(String s) {
//                    Logger.d("---onSuccess--" + s);
//                    Log.e("TAG", "RongIM - connect - onSuccess: ");
                    //连接成功后进入 聊天界面
                    EventBus.getDefault().post(new MessageEvent());
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {
//                    Log.e("TAG", "RongIM - connect  - onError: " + e);
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


    class AppLifecycleObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onEnterForeground() {

//            Log.e("TAG", "前台 : ");

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onEnterBackground() {
//            Log.e("TAG", " 在后台 : ");

            RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
                @Override
                public boolean onReceived(Message message, int i) {

//                    Log.e("TAG", " 后台 onReceived: ");
//                    sendNotification(message);


                    return false;
                }
            });

        }


    }

    //发通知
    private void sendNotification(Message message) {
        //1、NotificationManager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /** 2、Builder->Notification
         *  必要属性有三项
         *  小图标，通过 setSmallIcon() 方法设置
         *  标题，通过 setContentTitle() 方法设置
         *  内容，通过 setContentText() 方法设置*/
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentText("查看新消息")//设置通知内容
                .setContentTitle("您有一条新消息")//设置通知标题
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)//不能缺少的一个属性
                .setSubText("Subtext")
                .setTicker("新消息")
                .setWhen(System.currentTimeMillis());//设置通知时间，默认为系统发出通知的时间，通常不用设置

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("001", "my_channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
            builder.setChannelId("001");
        }

        // 设置会话界面
        Intent intent = new Intent(instance, RongConversationaActivity.class);
        intent.setAction(Intent.ACTION_VIEW);


        Uri uri;
        uri = Uri.parse("rong://" + getPackageName()).buildUpon()
                .appendPath("conversation")
                .appendPath(message.getConversationType().getName().toLowerCase())
                .appendQueryParameter("title", "name")
                .appendQueryParameter("targetId", message.getSenderUserId())
                .build();
        intent.setData(uri);
//
        PendingIntent pendingIntent = PendingIntent.getActivity(instance, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification n = builder.build();
        //3、manager.notify()
        manager.notify(NOTIFICATION_ID, n);
    }
}
