package com.hym.eshoplib.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.UnReadMessageBean;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.event.RefreshChatListEvent;
import com.hym.eshoplib.event.ShowGuideEvent;
import com.hym.eshoplib.fragment.home.HomeFragmentJDStyle;
import com.hym.eshoplib.fragment.home.TestFragment;
import com.hym.eshoplib.fragment.me.MeFragment;
import com.hym.eshoplib.fragment.message.MessageMainFragment;
import com.hym.eshoplib.fragment.shoppingcart.ShoppingcartFragment;
import com.hym.eshoplib.http.home.HomeApi;
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
import cn.hym.superlib.widgets.bottombar.BottomBarItem;
import cn.hym.superlib.widgets.bottombar.BottomBarLayout;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
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

        rongCloudService();

        //更新  第二个 tab 上面消息数
        updateMessageCount();


    }


    // 暂时先不放 Service ;
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

            RongIMClient.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
                @Override
                public boolean onReceived(Message message, int i) {


                    // 通知 各部门 更新消息
                    EventBus.getDefault().post(new MessageEvent());

                    // 刷新会话列表
                    EventBus.getDefault().post(new RefreshChatListEvent());

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


}
