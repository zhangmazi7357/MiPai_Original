package com.hym.eshoplib.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.UnReadMessageBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.event.MainMessageEvent;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.event.RefreshChatListEvent;
import com.hym.eshoplib.event.ShowGuideEvent;
import com.hym.eshoplib.event.ShowToastEvent;
import com.hym.eshoplib.fragment.home.HomeFragmentJDStyle;
import com.hym.eshoplib.fragment.home.TestFragment;
import com.hym.eshoplib.fragment.me.MeFragment;
import com.hym.eshoplib.fragment.message.MessageMainFragment;
import com.hym.eshoplib.fragment.shoppingcart.ShoppingcartFragment;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.util.ChatUtils;
import com.hym.eshoplib.util.OnTopPosCallbac2;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.hym.loginmodule.bean.LocalTokenBean;
import com.hym.loginmodule.bean.LoginBean;
import com.hym.loginmodule.event.NeedPerfectInformationEvent;
import com.hym.loginmodule.event.WeChatLoginEvent;
import com.hym.loginmodule.http.LoginApi;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.App;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.activity.base.BaseMainActivity;
import cn.hym.superlib.event.action.BaseActionEvent;
import cn.hym.superlib.event.language.LanguageChangeEvent;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.languages.utils.AppLanguageUtils;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.utils.view.SystemBarUtil;
import cn.hym.superlib.widgets.bottombar.BottomBarItem;
import cn.hym.superlib.widgets.bottombar.BottomBarLayout;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import me.yokeyword.fragmentation.SupportFragment;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.interfaces.HighLightInterface;
import zhy.com.highlight.shape.RectLightShape;

public class MainActivity extends BaseMainActivity {

    private String TAG = "MainActivity";
    //    private UMShareAPI mShareAPI = null;
    SupportFragment[] fragments;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        UMShareAPI.get(this).onSaveInstanceState(outState);

    }


    @Override
    public SupportFragment[] getSupportFragments() {
        fragments = new SupportFragment[5];
        fragments[0] = HomeFragmentJDStyle.newInstance(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        fragments[1] = MessageMainFragment.newInstance(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        fragments[2] = TestFragment.newInstance(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        fragments[3] = ShoppingcartFragment.newInstance(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        fragments[4] = MeFragment.newInstance(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        return fragments;
    }

    BottomBarItem itemTest;

    @Override
    public List<Class<? extends SupportFragment>> getClasses() {
        List<Class<? extends SupportFragment>> a = new ArrayList<>();
        a.add(HomeFragmentJDStyle.class);
        a.add(MessageMainFragment.class);
        a.add(TestFragment.class);
        a.add(ShoppingcartFragment.class);
        a.add(MeFragment.class);
        return a;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bottombar.getBottomItem(intent.getIntExtra("position", 0)).performClick();
    }

    @Override
    public List<BottomBarItem> getBottomItems() {
        List<BottomBarItem> items = new ArrayList<>();
        BottomBarItem itemHome = getDefaultBottomBarItem();

        itemHome.setmIconNormalResourceId(R.drawable.home_normal);
        itemHome.setmIconSelectedResourceId(R.drawable.home_light);
        itemHome.setmIconHeight(ScreenUtil.dip2px(this, 25));
        itemHome.setmIconWidth(ScreenUtil.dip2px(this, 25));
        itemHome.setmText(getResources().getString(R.string.Homepage));
        itemHome.setmTextSize(ScreenUtil.dip2px(this, 14));


        BottomBarItem itemExplore = getDefaultBottomBarItem();
        itemExplore.setmIconNormalResourceId(R.drawable.message_nomal);
        itemExplore.setmIconSelectedResourceId(R.drawable.message_light);
        itemExplore.setmIconHeight(ScreenUtil.dip2px(this, 25));
        itemExplore.setmIconWidth(ScreenUtil.dip2px(this, 25));
        itemExplore.setmText("消息");
        itemExplore.setmTextSize(ScreenUtil.dip2px(this, 14));

        itemTest = getDefaultBottomBarItem();
        itemTest.setmIconNormalResourceId(R.drawable.kefu_nomal);
        itemTest.setmIconSelectedResourceId(R.drawable.kefu_light);
        /*itemTest.setmIconWidth(ScreenUtil.dip2px(this, 50));
        itemTest.setmIconHeight(ScreenUtil.dip2px(this, 50));*/
        itemTest.setmIconHeight(ScreenUtil.dip2px(this, 25));
        itemTest.setmIconWidth(ScreenUtil.dip2px(this, 25));
        itemTest.setmText("客服推荐");
        itemTest.setmTextSize(ScreenUtil.dip2px(this, 14));

        BottomBarItem itemOnlineMail = getDefaultBottomBarItem();
        itemOnlineMail.setmIconNormalResourceId(R.drawable.shopcar_nomal);
        itemOnlineMail.setmIconSelectedResourceId(R.drawable.shopcar_light);
        itemOnlineMail.setmIconHeight(ScreenUtil.dip2px(this, 25));
        itemOnlineMail.setmIconWidth(ScreenUtil.dip2px(this, 25));
        itemOnlineMail.setmText(getResources().getString(R.string.Shoppingcart));
        itemOnlineMail.setmTextSize(ScreenUtil.dip2px(this, 14));


        BottomBarItem itemMyAccount = getDefaultBottomBarItem();
        itemMyAccount.setmIconNormalResourceId(R.drawable.me_nomal);
        itemMyAccount.setmIconSelectedResourceId(R.drawable.me_light);
        itemMyAccount.setmIconHeight(ScreenUtil.dip2px(this, 25));
        itemMyAccount.setmIconWidth(ScreenUtil.dip2px(this, 25));
        itemMyAccount.setmText(getResources().getString(R.string.MyAccount));
        itemMyAccount.setmTextSize(ScreenUtil.dip2px(this, 14));

        items.add(itemHome);
        items.add(itemExplore);
        items.add(itemTest);
        items.add(itemOnlineMail);
        items.add(itemMyAccount);
        return items;
    }

    @Subscribe
    public void wechatLogin(final WeChatLoginEvent event) {
        if (event.getStatus() == 0)
            UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    //ToastUtil.toast("撤销中");

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    // ToastUtil.toast("撤销完成");

                    UMShareAPI.get(MainActivity.this)
                            .getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN,
                                    new UMAuthListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                            ToastUtil.toast("授权中");
                                        }

                                        @Override
                                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                                            //ToastUtil.toast("授权成功");
                                            final String openId = map.get("unionid");
//                                            Log.e(TAG, "Wechat返回个人信息" + map);
                                            if (event.bindtype == 1) {
                                                //从设置进入直接调用绑定
                                                EventBus.getDefault().post(new NeedPerfectInformationEvent(openId));
                                                return;
                                            }

                                            LoginApi.otherLogin("", openId, new ResponseImpl<LoginBean>() {
                                                @Override
                                                public void onSuccess(LoginBean data) {
                                                    //已经绑定过直接进入
                                                    //ToastUtil.toast("登录成功");
                                                    UserUtil.setIsLogin(MainActivity.this, true);
                                                    UserUtil.saveToken(MainActivity.this, data.getData().getToken());//token
                                                    UserUtil.saveRongYunToken(MainActivity.this, data.getData().getRongcloud_token());//融云

                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("id", data.getData().getUserid());
                                                    bundle.putString("name", data.getData().getNickname());
                                                    bundle.putString("url", data.getData().getAvatar());
                                                    EventBus.getDefault().post(new LoginEvent(bundle));
                                                    EventBus.getDefault().post(new NeedPerfectInformationEvent());


                                                }

                                                @Override
                                                public void onDataError(String status, String errormessage) {
                                                    super.onDataError(status, errormessage);
                                                    if (status.equals("2")) {
                                                        //未绑定过
                                                        ToastUtil.toast("请先绑定手机号");
                                                        EventBus.getDefault().post(new NeedPerfectInformationEvent(openId));
                                                    }
                                                }
                                            }, LoginBean.class);
                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                                    Logger.d(share_media.toString() + ",thorw=" + throwable);
//                                            Log.e(TAG, "授权失败: " + share_media.toString() + ",thorw=" + throwable);
                                            ToastUtil.toast("授权失败");
                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media, int i) {
                                            ToastUtil.toast("授权取消");
                                        }
                                    });
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    //ToastUtil.toast("撤销错误");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    // ToastUtil.toast("撤销成功");

                }
            });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0x11) {
            //选择语言完毕
            Logger.d("语言切换完毕");
            String newLanguage = SharePreferenceUtil.getStringData(MainActivity.this, getResources().getString(R.string.app_language_pref_key));
            AppLanguageUtils.changeAppLanguage(this, newLanguage);
            AppLanguageUtils.changeAppLanguage(App.instance, newLanguage);
            // recreate();
        } else {
//            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onItemSelected(BottomBarItem bottomBarItem, int position) {
        if (position == 2) {
            // 客服推荐
            startChat();
        } else {
            super.onItemSelected(bottomBarItem, position);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mShareAPI = UMShareAPI.get(this);
        EventBus.getDefault().register(this);
        String token = UserUtil.getToken(MainActivity.this);

        if (TextUtils.isEmpty(token)) {
            //没有token 说明还没登录过则获取本地token
            // Logger.d("未登录过，获取本地token");
            LoginApi.getLocalToken("1", new ResponseImpl<LocalTokenBean>() {
                @Override
                public void onSuccess(LocalTokenBean data) {
                    String token = data.getData().getLocaltoken();
                    UserUtil.saveToken(MainActivity.this, token);
                }
            }, LocalTokenBean.class);

        }

        bottombar.setInteruptClick(new BottomBarLayout.onInteruptClick() {
            @Override
            public boolean interupt(int index) {
                if (index > 0) {
                    if (!UserUtil.getIsLogin(MainActivity.this)) {
                        ToastUtil.toast("请先登录");
                        Bundle bundle = new Bundle();
                        bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                        bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                        LoginMainActivity.start(MainActivity.this, bundle);
                        return true;

                    }
                }
                return false;
            }
        });
        bottombar.getBottomItem(getIntent().getIntExtra("position", 0)).performClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        UMShareAPI.get(this).release();
    }

    @Override
    public void doLogic() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showGuide(ShowGuideEvent event) {
        showGuide();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateUserInfo(LanguageChangeEvent event) {
        startActivity(new Intent(MainActivity.this, SplashActivity.class));
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAction(BaseActionEvent event) {
        switch (event.getType()) {
            case 1:
                //进入消息界面
                //消息
//                ActionActivity.start(MainActivity.this,
//                        BaseActionActivity.getActionBundle(ActionActivity.ModelType_Message, ActionActivity.Action_message_main));
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 极光推送 ;
        String channelid = SharePreferenceUtil.getStringData(this, "channelid");
        if (TextUtils.isEmpty(channelid)) {
            SharePreferenceUtil.setStringData(this, "channelid", JPushInterface.getRegistrationID(this));
            Log.e("TAG", "channelId = " + channelid);
        } else {
//            Logger.d("id不为空=" + channelid);
        }


        // 动态注册广播;
//        registerBrocast();

        rongCloudService();

        //更新  第二个 tab 上面消息数
        updateMessageCount();


    }


    private void rongCloudService() {
        // 连接RongCloud；
        if (!TextUtils.isEmpty(UserUtil.getRongYunToken(this))) {

            RongIMClient.connect(UserUtil.getRongYunToken(this),
                    new RongIMClient.ConnectCallback() {
                        @Override
                        public void onTokenIncorrect() {
                            Log.e(TAG, " Main中连接Rong - onTokenIncorrect: ");
                        }

                        @Override
                        public void onSuccess(String s) {
                            Log.e(TAG, "Main中连接Rong - onSuccess: " + s);
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {
                            Log.e(TAG, "Main中连接Rong - onError: " + errorCode);
                        }
                    });

            RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
                @Override
                public boolean onReceived(Message message, int i) {


                    Log.e(TAG, " 接收消息 = " + JSONObject.toJSONString(message));


                    // 通知 各部门 更新消息
                    EventBus.getDefault().post(new MessageEvent());

                    EventBus.getDefault().post(new RefreshChatListEvent());


                    // 显示消息 Toast;
                    ShowToastEvent showToastEvent = new ShowToastEvent();
                    showToastEvent.setMessage(message);
                    EventBus.getDefault().post(showToastEvent);


                    return false;
                }
            });
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataMsg(MessageEvent event) {
        //  Log.e(TAG, "收到通知的推送，更新系统消息");
        updateMessageCount();


    }


    // 更新 第二个tab 上的消息数字 ;
    private void updateMessageCount() {
        if (!UserUtil.getIsLogin(this)) {
            //清空聊天数字
            try {
                bottombar.setUnread(1, 0);
            } catch (Exception e) {
                Logger.d("消息异常=" + e.toString());
            }
            return;
        }

        //更新未读消息
        HomeApi.GetNewMsg(new ResponseImpl<UnReadMessageBean>() {
            @Override
            public void onSuccess(UnReadMessageBean data) {
                final int system = Integer.parseInt(data.getData().getSystem());
                final int order = Integer.parseInt(data.getData().getOrder());

                RongIM.getInstance().getTotalUnreadCount(new RongIMClient.ResultCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        int total = system + order + integer;
                        try {
                            bottombar.setUnread(1, total);
                        } catch (Exception e) {
                            Logger.d("消息异常=" + e.toString());
                        }
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });

            }
        }, UnReadMessageBean.class);

    }

    @Override
    public void onItemReSelected(BottomBarItem bottomBarItem, int position) {
        super.onItemReSelected(bottomBarItem, position);

        if (position == 0 && fragments != null && fragments[0] != null) {
            HomeFragmentJDStyle fragment = (HomeFragmentJDStyle) fragments[0];
            fragment.scrollTotop();
            // ToastUtil.toast("reselect="+position);

        } else if (position == 2) {
            // ToastUtil.toast("平台推荐2");
            startChat();
        }
    }

    private void startChat() {
        if (!UserUtil.getIsLogin(this)) {
            ToastUtil.toast("请先登录");
        } else {
            //如果没连接则先连接
            if (RongIM.getInstance().getCurrentConnectionStatus().
                    equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {

                reconnect(UserUtil.getRongYunToken(this));

            } else {

                ChatUtils.ChatToCustomService(this, 2);
            }

        }
    }

    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.d("---onTokenIncorrect--");
                ToastUtil.toast("聊天token异常");
            }

            @Override
            public void onSuccess(String s) {


                Log.e(TAG, "融云 connect =" + s);
                //连接成功后进入 聊天界面
//                if (mDialog != null)
//                    mDialog.dismiss();
//                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
//                finish();
                ChatUtils.ChatToCustomService(MainActivity.this, 2);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                ToastUtil.toast("聊天异常：" + e.toString());
//                Logger.d("聊天异常：" + e.toString());
                Log.e(TAG, "融云 onError: ");
            }
        });

    }

    private HighLight mHightLight;


    // 引导高亮布局
    public void showGuide() {

        mHightLight = new HighLight(this)//
                .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                .intercept(true)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                .addHighLight(itemTest, R.layout.layout_custom_guide,
                        new OnTopPosCallbac2(),
                        new RectLightShape(0, 0, 0, 0, 0));

        mHightLight.setClickCallback(new HighLightInterface.OnClickCallback() {
            @Override
            public void onClick() {
                mHightLight.remove();

            }
        });
        mHightLight.show();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(MainMessageEvent event) {


        sendNotification();

    }

    private void sendNotification() {
        //1、NotificationManager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /** 2、Builder->Notification
         *  必要属性有三项
         *  小图标，通过 setSmallIcon() 方法设置
         *  标题，通过 setContentTitle() 方法设置
         *  内容，通过 setContentText() 方法设置*/
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentInfo("您有一条新消息")
                .setContentText("您有一条新消息")//设置通知内容
                .setContentTitle("您有一条新消息")//设置通知标题
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)//不能缺少的一个属性
                .setTicker("新消息")
                .setWhen(System.currentTimeMillis());//设置通知时间，默认为系统发出通知的时间，通常不用设置

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("001", "mipai_channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
            builder.setChannelId("001");
        }

        Notification n = builder.build();
        //3、manager.notify()
        manager.notify(2, n);
    }


    private void registerBrocast() {
        Intent it = new Intent("io.rong.push.intent.MESSAGE_ARRIVED");
        it.setComponent(new ComponentName("com.hym.eshoplib.receiver",
                "com.hym.eshoplib.receiver.RongIMPushMessageReceiver"));

        sendBroadcast(it);

    }


    /**
     * 显示接收到消息的 toast
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRongIMToast(ShowToastEvent event) {

        toast(this, event.message);
    }

    String nickname = "";
    String headerUrl = "";

    private void toast(Context context, Message message) {
        View layoutView = LayoutInflater.from(context)
                .inflate(R.layout.toast_top, null);

        //设置文本的参数 设置加载文本文件的参数，必须通过LayoutView获取。
        ImageView header = layoutView.findViewById(R.id.header);
        TextView timeView = layoutView.findViewById(R.id.tv_assist_toast_time);
        TextView contentView = layoutView.findViewById(R.id.tv_assist_toast_content);
        TextView titletView = layoutView.findViewById(R.id.tv_assist_toast_title);

        // message 信息
        String obj = message.getObjectName();
        String targetId = message.getTargetId();

        if (TextUtils.isEmpty(targetId))
            return;


        MessageContent content = message.getContent();
        List<String> searchableWord = content.getSearchableWord();

        String sss = searchableWord.toString();
        String msgContent = sss.substring(1, sss.length() - 1);


        MeApi.getUserinfo(targetId, new ResponseImpl<MedetailBean>() {
            @Override
            public void onSuccess(MedetailBean data) {

                Log.e(TAG, "onSuccess: " + JSONObject.toJSONString(data));
                nickname = data.getData().getNickname();
                headerUrl = data.getData().getAvatar();

                titletView.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
                if (!TextUtils.isEmpty(headerUrl)) {
                    Glide.with(MainActivity.this).load(headerUrl)
                            .into(header);
                } else {
                    Glide.with(MainActivity.this).load(R.mipmap.ic_launcher).into(header);
                }

            }

            @Override
            public void onDataError(String status, String errormessage) {

            }

        }, MedetailBean.class);


        contentView.setText(TextUtils.isEmpty(msgContent) ? "" : msgContent);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;

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

        layoutView.setClickable(true);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,
                        RongConversationaActivity.class);
                intent.setAction(Intent.ACTION_VIEW);

                Uri uri;
                uri = Uri.parse("rong://" + getPackageName()).buildUpon()
                        .appendPath("conversation")
                        .appendPath(message.getConversationType().getName().toLowerCase())
                        .appendQueryParameter("title", "name")
                        .appendQueryParameter("targetId", targetId)
                        .build();
                intent.setData(uri);

                startActivity(intent);

            }
        });


    }


}
