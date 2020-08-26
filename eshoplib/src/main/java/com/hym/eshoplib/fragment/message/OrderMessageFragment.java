package com.hym.eshoplib.fragment.message;

import android.app.Dialog;
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
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.home.OrderMessageListBean;
import com.hym.eshoplib.event.MessageEvent;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.me.MeApi;
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
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/8/2.
 * <p>
 * Description 订单消息
 * <p>
 * otherTips
 */

public class OrderMessageFragment extends BaseListFragment<OrderMessageListBean.DataBean.InfoBean> {
    public static OrderMessageFragment newInstance(Bundle args) {
        OrderMessageFragment fragment = new OrderMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_order_message;
    }

    @Override
    public void excuteLogic() {
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv_icon = emptyView.findViewById(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.rc_conversation_list_empty);
        TextView tv_message = emptyView.findViewById(R.id.tv_message);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv_message.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        tv_message.setLayoutParams(layoutParams);
        tv_message.setText("暂无交易提醒");
        tv_message.setTextColor(Color.parseColor("#999999"));
        getAdapter().setEmptyView(emptyView);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                HomeApi.ReadMsg(getAdapter().getData().get(position).getMsg_id(), new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        MessageEvent event = new MessageEvent();
                        event.type = 1;
                        EventBus.getDefault().post(event);
                        getAdapter().getData().get(position).setStatus("0");
                        getAdapter().notifyDataSetChanged();
                        Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_detail);
                        bundle.putString("id", getAdapter().getData().get(position).getContent_id());
                        EshopActionActivity.start(_mActivity, bundle);
                    }
                }, Object.class);


            }
        });
        getAdapter().setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {


                DialogManager.getInstance().initSimpleDialog(_mActivity, "删除消息",
                        "您确定要删除这条消息么", "取消", "确定",
                        new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();

                                HomeApi.DeleteMsg(getAdapter().getData().get(position).getMsg_id(), new ResponseImpl<Object>() {
                                    @Override
                                    public void onSuccess(Object data) {
                                        getAdapter().remove(position);
                                    }
                                }, Object.class);


                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        HomeApi.GetMsg(pageNum + "", new ResponseImpl<OrderMessageListBean>() {
            @Override
            public void onSuccess(OrderMessageListBean data) {
                int total = Integer.parseInt(data.getData().getTotalpage());
                if (refresh) {
                    setPageNum(HttpResultUtil.onRefreshSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                } else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        }, OrderMessageListBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, OrderMessageListBean.DataBean.InfoBean item, int position) {
        TextView tv_status = helper.getView(R.id.tv_status);
        ImageView iv_read = helper.getView(R.id.iv_read);
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_type = helper.getView(R.id.tv_type);
        TextView tv_count = helper.getView(R.id.tv_count);
        TextView tv_order_num = helper.getView(R.id.tv_order_num);
        helper.setText(R.id.tv_time, item.getDate() + "");
//        switch (position){
//            case 0:
//                tv_status.setText("卖家提醒您确认收货");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorSelect));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_alarm,0,0,0);
//                break;
//            case 1:
//                tv_status.setText("卖家提醒您确认拍摄结束");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorSelect));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_alarm,0,0,0);
//                break;
//            case 2:
//                tv_status.setText("卖家已接受预约");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorNormal));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_accept,0,0,0);
//                break;
//            case 3:
//                tv_status.setText("卖家已拒绝预约");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorNormal));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_refuse,0,0,0);
//                break;
//            case 4:
//                tv_status.setText("卖家已取消订单");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorNormal));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_refuse,0,0,0);
//                break;
//            case 5:
//                tv_status.setText("卖家同意退款");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorNormal));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_accept,0,0,0);
//                break;
//            case 6:
//                tv_status.setText("卖家拒绝退款");
//                tv_status.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorNormal));
//                tv_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_order_refuse,0,0,0);
//                break;
//        }
        tv_status.setText(item.getContent() + "");
        if (item.getStatus().equals("0")) {
            iv_read.setVisibility(View.GONE);
        } else {
            iv_read.setVisibility(View.VISIBLE);
        }
        ImageUtil.getInstance().loadImage(OrderMessageFragment.this, item.getLogo(), iv_icon);
        tv_title.setText(item.getStore_name() + "");
        tv_type.setText("类别：" + item.getCategory_name());
        tv_count.setText("x" + item.getBy_num());
        tv_order_num.setText("订单编号：" + item.getOrder_number());
        ImageView iv_vip = helper.getView(R.id.iv_vip);
        if (item.getAuth().equals("1")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_person_rt);
        } else if (item.getAuth().equals("2")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_business_rt);
        } else {
            iv_vip.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataMsg(MessageEvent event) {
        if (event.type == -1) {
            onRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLogin(LoginEvent event) {
        onRefresh();
        Logger.d("更新订单消息");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLogin(UnLoginEvent event) {
        getAdapter().setNewData(null);
    }
}
