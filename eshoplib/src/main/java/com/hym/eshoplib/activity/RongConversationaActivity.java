package com.hym.eshoplib.activity;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.bean.shop.ShopIdBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.activity.base.BasekitActivity;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.push.RongPushClient;

/**
 * Created by 胡彦明 on 2018/8/20.
 * <p>
 * Description 融云会话activity
 * <p>
 * otherTips
 */

public class RongConversationaActivity extends BasekitActivity {
    TextView tvTips;
    String title = "";
    private ConversationFragment conversationFragment;

    @Override
    public void doLogic() {
        showBackButton();
        SoftHideKeyBoardUtil.assistActivity(this);
        String xxx = getIntent().getData().getQueryParameter("title");//获取消息title
        final String targetId = getIntent().getData().getQueryParameter("targetId");//
//        setTitle(title);
        addConversationFragment();

        if (!TextUtils.isEmpty(targetId)) {
            MeApi.getUserinfo(targetId, new ResponseImpl<MedetailBean>() {
                @Override
                public void onSuccess(MedetailBean data) {
                    title = data.getData().getNickname();
                    setTitle(title);

                    RongIM.getInstance()
                            .refreshUserInfoCache(new UserInfo(data.getData().getUserid(),
                                    data.getData().getNickname(),
                                    Uri.parse(data.getData().getAvatar())));

                    //开启会话
                    RongIM.getInstance().startConversation(RongConversationaActivity.this,
                            Conversation.ConversationType.PRIVATE, targetId, title, null);

                }

                @Override
                public void onDataError(String status, String errormessage) {

                }

            }, MedetailBean.class);


        }

        if (targetId.equals("2010")) {
            tvTips = findViewById(R.id.tv_tips);
            tvTips.setVisibility(View.VISIBLE);
            return;
        }
        if (targetId.equals("3681")) {
            tvTips = findViewById(R.id.tv_tips);
            tvTips.setVisibility(View.VISIBLE);
            tvTips.setText("请将拍摄需求告知\"小觅\"，将为您第一时间匹配最合适的工作室");
            return;
        }


        setRight_iv(R.drawable.ic_more_mipai, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.getSelectDialog(RongConversationaActivity.this, "进入工作室", "清空聊天记录", new DialogUtil.OnSelectDialogListener() {
                    @Override
                    public void onBtnOneClick(final Dialog dialog) {
                        //获取工作室id 进入详情
                        ShopApi.GetStoreid(targetId, new ResponseImpl<ShopIdBean>() {
                            @Override
                            public void onSuccess(ShopIdBean data) {
                                String id = data.getData();
                                if (id.equals("0")) {
                                    ToastUtil.toast("对方没有工作室或工作室正在审核中");
                                    return;
                                }
                                dialog.dismiss();
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                                bundle.putString("id", id);
                                ActionActivity.start(RongConversationaActivity.this, bundle);

                            }
                        }, ShopIdBean.class);

                    }

                    @Override
                    public void onBtnTwoClick(Dialog dialog) {
                        dialog.dismiss();

                        DialogManager.getInstance().initSimpleDialog(RongConversationaActivity.this, "提示", "您确定要清空聊天记录么",
                                "取消", "确定", new SimpleDialog.SimpleDialogOnClickListener() {
                                    @Override
                                    public void negativeClick(Dialog dialog) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void positiveClick(Dialog dialog) {
                                        dialog.dismiss();
                                        //清空聊天记录
                                        RongIM.getInstance()
                                                .clearMessages(Conversation.ConversationType.PRIVATE, targetId,
                                                        new RongIMClient.ResultCallback<Boolean>() {
                                                            @Override
                                                            public void onSuccess(Boolean aBoolean) {
                                                                ToastUtil.toast("清除成功");

                                                            }

                                                            @Override
                                                            public void onError(RongIMClient.ErrorCode errorCode) {

                                                            }
                                                        });
                                    }
                                }).show();


                    }
                });
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.conversation;
    }


    private void addConversationFragment() {
        // 添加会话界面
        conversationFragment = new ConversationFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationFragment);
        transaction.commit();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshConversation() {

    }

}
