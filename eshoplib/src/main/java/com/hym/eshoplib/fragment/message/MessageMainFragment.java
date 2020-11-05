package com.hym.eshoplib.fragment.message;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.UnReadMessageBean;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.http.home.HomeApi;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import app.App;
import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.fragment.base.BaseTabViewPagerFragment;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.SystemBarUtil;
import io.rong.common.fwlog.LogEntity;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by 胡彦明 on 2018/8/2.
 * <p>
 * Description 消息主页
 * <p>
 * otherTips
 */

public class MessageMainFragment extends BaseTabViewPagerFragment {

    public static MessageMainFragment newInstance(Bundle args) {
        MessageMainFragment fragment = new MessageMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    Fragment[] fragments = new Fragment[3];
    ConversationListFragment mConversationListFragment;
    boolean isDebug = true;
    private Conversation.ConversationType[] mConversationsTypes = null;

    @Override
    public Fragment[] getSupportFragments() {

        initConversationList();

        fragments[0] = SystemMessageFragment.newInstance(new Bundle());
        fragments[1] = mConversationListFragment;
        fragments[2] = OrderMessageFragment.newInstance(new Bundle());
        return fragments;
    }

    @Override
    public List<Class<? extends Fragment>> getClasses() {
        List<Class<? extends Fragment>> classes = new ArrayList<>();
        classes.add(SystemMessageFragment.class);
        classes.add(ConversationListFragment.class);
        classes.add(OrderMessageFragment.class);
        return classes;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabs() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity("平台消息", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("聊天信息", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("交易提醒", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return tabs;
    }

    @Override
    public int getCurrent_index() {
        return 2;
    }

    @Override
    public void doLogic() {
        int statusBarheight = SystemBarUtil.getSystemBarHeight(_mActivity);
        statusspace.getLayoutParams().height = statusBarheight;
        //getTabLayout().showMsg(2, 7);
        try {
            getTabLayout().getMsgView(0).setTextSize(10);
            getTabLayout().getMsgView(1).setTextSize(10);
            getTabLayout().getMsgView(2).setTextSize(10);

        } catch (Exception e) {
            Logger.d(e.toString());

        }

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + _mActivity.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();

                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {

                uri = Uri.parse("rong://" + _mActivity.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        if (UserUtil.getIsLogin(_mActivity)) {
            App.reconnect(UserUtil.getRongYunToken(_mActivity));
            //更新数据
            updateMessageCount();
        } else {
            //非登录状态清空聊天数字
//            getTabLayout().hideMsg(0);
//            getTabLayout().hideMsg(1);
//            getTabLayout().hideMsg(2);

        }


    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(LoginEvent event) {
        Logger.d("收到登录");
        if (mConversationListFragment != null) {
            Logger.d("刷新ui");
            Uri uri;
            uri = Uri.parse("rong://" + _mActivity.getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP,
                    Conversation.ConversationType.PUBLIC_SERVICE,
                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
                    Conversation.ConversationType.SYSTEM,
                    Conversation.ConversationType.DISCUSSION
            };
            mConversationListFragment.setUri(uri);

        }

    }

    /**
     *  收到RongIM 消息的通知
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataMsg(MessageEvent event) {
        updateMessageCount();

        mConversationListFragment.onRestoreUI();


    }

    private void updateMessageCount() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            return;
        }
        //更新未读消息
        HomeApi.GetNewMsg(new ResponseImpl<UnReadMessageBean>() {
            @Override
            public void onSuccess(UnReadMessageBean data) {
                final int system = Integer.parseInt(data.getData().getSystem());
                final int order = Integer.parseInt(data.getData().getOrder());

                if (system > 0) {
                    getTabLayout().showMsg(0, system);
                } else {
                    getTabLayout().hideMsg(0);
                }
                if (order > 0) {
                    getTabLayout().showMsg(2, order);
                } else {
                    getTabLayout().hideMsg(2);
                }


                RongIM.getInstance()
                        .getTotalUnreadCount(new RongIMClient.ResultCallback<Integer>() {
                            @Override
                            public void onSuccess(Integer integer) {

                                try {
                                    if (integer > 0) {
                                        getTabLayout().showMsg(1, integer);
                                    } else {
                                        getTabLayout().hideMsg(1);
                                    }

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

}
