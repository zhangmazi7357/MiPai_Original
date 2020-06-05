package com.hym.eshoplib.fragment.message;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.home.SystemMessageListBean;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.event.lgoin.UnLoginEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/8/2.
 * <p>
 * Description 系统消息
 * <p>
 * otherTips
 */

public class SystemMessageFragment extends BaseListFragment<SystemMessageListBean.DataBean.InfoBean>{
    public static SystemMessageFragment newInstance(Bundle args) {
        SystemMessageFragment fragment = new SystemMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public boolean showToolBar() {
        return false;
    }


    @Override
    public int getItemRestId() {
        return R.layout.item_system_message;
    }

    @Override
    public void excuteLogic() {

        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv_icon=emptyView.findViewById(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.rc_conversation_list_empty);
        TextView tv_message=emptyView.findViewById(R.id.tv_message);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) tv_message.getLayoutParams();
        layoutParams.setMargins(0,0,0,0);
        tv_message.setLayoutParams(layoutParams);
        tv_message.setText("暂无平台消息");
        tv_message.setTextColor(Color.parseColor("#999999"));
        getAdapter().setEmptyView(emptyView);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                HomeApi.ReadMsg(getAdapter().getData().get(position).getMsg_id(), new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        MessageEvent event=new MessageEvent();
                        event.type=1;
                        EventBus.getDefault().post(event);
                        getAdapter().getData().get(position).setStatus("0");
                        getAdapter().notifyDataSetChanged();
                        //进入系统消息详情
                        if(getAdapter().getData().get(position).getType().equals("5")){
                            Bundle bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_system_messgae_detail);
                            bundle.putSerializable("data",getAdapter().getData().get(position));
                            ActionActivity.start(_mActivity,bundle);
                        }else {
                            ToastUtil.toast("已标记为已读");

                        }

                    }
                },Object.class);
            }
        });
        getAdapter().setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                DialogUtil.getTowButtonDialog(_mActivity, "删除消息", "您确定要删除这条消息么", "取消", "确定", new DialogUtil.OnDialogHandleListener() {
                    @Override
                    public void onCancleClick(SweetAlertDialog sDialog) {

                    }

                    @Override
                    public void onConfirmeClick(SweetAlertDialog sDialog) {
                        //删除消息
                        HomeApi.DeleteMsg(getAdapter().getData().get(position).getMsg_id(), new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                getAdapter().remove(position);
                            }
                        },Object.class);
                    }
                }).show();
                return true;
            }
        });

    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        HomeApi.GetSystemMsg(pageNum+"", new ResponseImpl<SystemMessageListBean>() {
            @Override
            public void onSuccess(SystemMessageListBean data) {
                int total=Integer.parseInt(data.getData().getTotalpage());
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }

            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        },SystemMessageListBean.class);



    }

    @Override
    public void bindData(BaseViewHolder helper, SystemMessageListBean.DataBean.InfoBean item, int position) {
        helper.setText(R.id.tv_time,item.getDate()+"");
        ImageUtil.getInstance().loadImage(SystemMessageFragment.this,item.getImage_default(), (ImageView) helper.getView(R.id.iv_bg));
        helper.setText(R.id.tv_title,item.getTitle()+"");
        helper.setText(R.id.tv_des,item.getMemo());
        ImageView iv_read=helper.getView(R.id.iv_read);
        if(item.getStatus().equals("0")){
            iv_read.setVisibility(View.GONE);
        }else {
            iv_read.setVisibility(View.VISIBLE);
        }
        ScreenUtil.ViewAdapter(_mActivity, helper.getView(R.id.iv_bg), 5, 2);

    }

    @Override
    public boolean openEventBus() {
        return true;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataMsg(MessageEvent event){
        if(event.type==-1){
            onRefresh();
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLogin(LoginEvent event){
        onRefresh();
        Logger.d("更新系统消息");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLogin(UnLoginEvent event){
       getAdapter().setNewData(null);
    }
}
