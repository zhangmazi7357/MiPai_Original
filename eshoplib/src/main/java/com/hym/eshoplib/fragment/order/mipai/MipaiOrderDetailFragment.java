package com.hym.eshoplib.fragment.order.mipai;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhouwei.library.CustomPopWindow;
import com.hjq.dialog.MessageDialog;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.bean.order.OrderDetailBeanMipai;
import com.hym.eshoplib.bean.order.ReasonBean;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DateUtil;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 订单详情
 * <p>
 * otherTips
 */

public class MipaiOrderDetailFragment extends BaseKitFragment {
    @BindView(R.id.ll_order_status)
    LinearLayout llOrderStatus;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;
    @BindView(R.id.tv_oder_number_title)
    TextView tvOderNumberTitle;
    @BindView(R.id.tv_oder_time_title)
    TextView tvOderTimeTitle;
    @BindView(R.id.btn_copy)
    SuperButton btnCopy;
    Unbinder unbinder;
    BaseListAdapter<String> adapter;
    int select_position = -1;
    String log_id;
    @BindView(R.id.tv_status)
    SuperTextView tvStatus;
    @BindView(R.id.tv_total)
    SuperTextView tvTotal;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    String reason_id;
    @BindView(R.id.btn_1)
    TextView btn1;
    @BindView(R.id.btn_2)
    TextView btn2;
    @BindView(R.id.view_button_diver)
    View viewButtonDiver;
    @BindView(R.id.btn_3)
    TextView btn3;
    @BindView(R.id.btn_4)
    TextView btn4;
    @BindView(R.id.ll_buttons)
    LinearLayout llButtons;
    boolean hasinit = false;
    @BindView(R.id.tv_time_line_1)
    TextView tvTimeLine1;
    @BindView(R.id.tv_time_line_2)
    TextView tvTimeLine2;
    @BindView(R.id.iv_step_2)
    ImageView ivStep2;
    @BindView(R.id.tv_time_line_3)
    TextView tvTimeLine3;
    @BindView(R.id.tv_time_line_4)
    TextView tvTimeLine4;
    @BindView(R.id.iv_step_3)
    ImageView ivStep3;
    @BindView(R.id.ll_time_line)
    LinearLayout llTimeLine;
    String changePrice = "";
    OrderDetailBeanMipai dataBean;
    @BindView(R.id.tv_alarm)
    TextView tvAlarm;
    @BindView(R.id.tv_refound_reason)
    TextView tvRefoundReason;
    @BindView(R.id.tv_refound_money)
    TextView tvRefoundMoney;
    @BindView(R.id.ll_refound_detail)
    LinearLayout llRefoundDetail;
    CountDownTimer timer;//计时器
    Boolean isTimerRunning = false;//判断计时器是否在工作
    long cuountDowntime;
    @BindView(R.id.btn1)
    SuperButton btn_1;
    @BindView(R.id.btn2)
    SuperButton btn_2;
    @BindView(R.id.iv_buyer_avantar)
    ImageView ivBuyerAvantar;
    @BindView(R.id.tv_buyer_nick)
    TextView tvBuyerNick;
    @BindView(R.id.ll_buyer)
    LinearLayout llBuyer;
    @BindView(R.id.tv_refuse_reason)
    TextView tvRefuseReason;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.iv_vip_shop)
    ImageView ivVipShop;
    private CustomPopWindow mCustomPopWindow;
    OrderDetailBeanMipai bean;
//    @BindView(R.id.v_diver)
//    View vDiver;

    public static MipaiOrderDetailFragment newInstance(Bundle args) {
        MipaiOrderDetailFragment fragment = new MipaiOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_mipaiorder_detail;
    }

    @Override
    public void doLogic() {
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) {
                    return;
                }
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                // bundle.putInt("type",getArguments().getInt("type",1));//工作室类型，对应首页
                bundle.putString("id", bean.getData().getService_id());
                ActionActivity.start(_mActivity, bundle);
            }
        });
        setShowProgressDialog(true);
        showBackButton();
        setTitle("订单详情");
        log_id = getArguments().getString("id", "");
        setRight_iv(R.drawable.ic_more_mipai, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(_mActivity).inflate(R.layout.pop_menu, null);
                //处理popWindow 显示内容
                handleLogic(contentView);
                //创建并显示popWindow
                mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(_mActivity)
                        .setView(contentView)
                        .create();
                //.showAsDropDown(iv_right,100,0);
                PopupWindow popupWindow = mCustomPopWindow.getPopupWindow();
                popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                popupWindow.showAsDropDown(v, -115, 0);
                //mCustomPopWindow.showAsDropDown(iv_right,100,0);

            }
        });


    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                Intent intent = new Intent(_mActivity, MainActivity.class);
                switch (v.getId()) {
                    case R.id.menu1:
                        //首页
                        intent.putExtra("position", 0);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu2:
                        //消息
                        intent.putExtra("position", 1);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu3:
                        //我的
                        intent.putExtra("position", 3);
                        startActivity(intent);
                        _mActivity.finish();
                        break;
                    case R.id.menu4:
                        //分享
                        break;

                }

            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setVisibility(View.GONE);

    }

    private SpannableString getTipsType(int type) {
        SpannableString spannableString = null;
        if (type == 1) {
            spannableString = new SpannableString
                    ("1.付款后服务款项暂由平台托管;" + "\n" +
                            "2.制作方服务结束后,付款方需要点击\"确认收货\";" + "\n" +
                            "3.之后款项自动转入制作方的账户" + "\n" +
                            "4.平台保障，不满意免费重拍  400-900-5957");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 11, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 35, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 49, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 70, 74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString = new SpannableString
                    ("1.付款后服务款项暂由平台托管;" + "\n" +
                            "2.前期拍摄结束后,付款方点击\"确认拍摄结束\"，则30%首付款转入制作方账户;" + "\n" +
                            "3.直至制作方服务结束，付款方点击\"确认收货\"后,剩余70%款项自动转入制作方账户");
//                            +"\n"+ "4.平台保障，不满意免费重拍  400-900-5957");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 11, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 32, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 42, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 74, 79, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 84, 89, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 94, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (TextUtils.isEmpty(log_id)) {
            ToastUtil.toast("数据异常");
            pop();
            return;
        }



        OrderApi.UserContentDetail(log_id, new ResponseImpl<OrderDetailBeanMipai>() {
            @Override
            public void onSuccess(final OrderDetailBeanMipai data) {
                //发票
                bean = data;
                if (TextUtils.isEmpty(bean.getData().getReason())) {
                    tvRefuseReason.setVisibility(View.GONE);
                } else {
                    tvRefuseReason.setVisibility(View.VISIBLE);
                    tvRefuseReason.setText(bean.getData().getReason());
                }
                btn_1.setVisibility(View.GONE);   //发票情况
                btn_2.setVisibility(View.GONE); //退款情况
                btn_2.setText("定制产品不接受退款");
                btn_1.setText("不提供发票");
                String refound_type = data.getData().getRefund_type();
                String invoice_type = data.getData().getInvoice();
                switch (refound_type) {
                    case "1":
                        btn_2.setText("接受不满意全额退款");
                        break;
                    case "2":
                        btn_2.setText("接受不满意部分退款");
                        break;
                    case "3":
                        btn_2.setText("定制产品不接受退款");
                        break;

                }
                if (invoice_type.equals("1")) {
                    btn_1.setText("可开发票");
                } else {
                    btn_1.setText("不提供发票");
                }
                if (data.getData().getCategory_id().equals("5")) {
                    tvTips.setText(getTipsType(2));
                } else {
                    tvTips.setText(getTipsType(1));
                }
                dataBean = data;
                llTimeLine.setVisibility(View.GONE);
                //刷新数据后，提示列表更新
                if (hasinit) {
                    postUpdate();
                }
                hasinit = true;
                final OrderDetailBeanMipai.DataBean item = data.getData();
                ImageUtil.getInstance().loadImage(MipaiOrderDetailFragment.this, data.getData().getLogo(), ivIcon);
                if (data.getData().getProduct_name() != null){
                    tvTitle.setText(data.getData().getProduct_name() + "");
                }else{
                    tvTitle.setText(data.getData().getStore_name() + "");
                }
                tvDes.setText("购买类别：" + data.getData().getCategory_name());
                tvPrice.setText("￥：" + data.getData().getPrice());
                tvCount.setText("x" + data.getData().getBuy_num());
                tvTotal.setRightString("￥：" + data.getData().getMoney());
                tvOrderNum.setText(data.getData().getOrder_number() + "");
                tvTime.setText(data.getData().getCtime());
                if (data.getData().getStatus().equals("1")) {
                    //订单已经取消
                    tvStatus.setVisibility(View.VISIBLE);
                    String reason = data.getData().getReason();
                    tvStatus.setRightString(reason + "");
                    tvStatus.setLeftString("交易关闭");
                } else {
                    tvStatus.setVisibility(View.GONE);
                }
                final String is_store = data.getData().getAuth().equals("1") ? "2" : "1";
                ImageUtil.getInstance().loadCircleImage(MipaiOrderDetailFragment.this, item.getSide_avatar(), ivBuyerAvantar);
                tvBuyerNick.setText(item.getSide_nick() + "");
                if (data.getData().getAuth().equals("1")) {
                    //买家
                    btn1.setText("联系卖家");
                    llBuyer.setVisibility(View.GONE);
                    // vDiver.setVisibility(View.GONE);
                } else {
                    //卖家
                    btn1.setText("联系买家");
                    llBuyer.setVisibility(View.VISIBLE);
                    //vDiver.setVisibility(View.VISIBLE);

                }
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ToastUtil.toast(btn1.getText().toString());
                        //调用融云聊天
                        if (!UserUtil.getIsLogin(_mActivity)) {
                            ToastUtil.toast("请先登录");
                            Bundle bundle = new Bundle();
                            bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
                            bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
                            LoginMainActivity.start(_mActivity, bundle);
                        } else {
                            //如果没连接则先连接
                            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                                reconnect(UserUtil.getRongYunToken(_mActivity));
                            } else {
                                RongIM.getInstance().startPrivateChat(_mActivity, item.getSide_id(), item.getSide_nick());
                            }

                        }
                    }
                });
                llTips.setVisibility(View.VISIBLE);
                tvAlarm.setVisibility(View.GONE);
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn4.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                btn4.setBackgroundResource(R.drawable.shape_toolbar_bg);
                OrderDetailBeanMipai.DataBean.ButtonBean buttonBean = data.getData().getButton();
                //取消预约，或者取消订单按钮
                if (buttonBean.getCancel() == 1) {
                    btn3.setVisibility(View.VISIBLE);
                    //如果订单没被接受预约，则显示取消预约，如果订单被接受则显示取消订单
                    if (buttonBean.getPay() == 1) {
                        //对于买家说，显示待付款按钮则相当于 被接受预约
                        btn3.setText("取消订单");
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCancleDilalog("2", "请选择取消订单的理由", item.getLog_id(), false);
                            }
                        });
                    } else if (buttonBean.getAccept() == 1) {
                        //已接受预约
                        //卖家来说，显示已接受预约 相当于被接受预约
                        btn3.setText("取消订单");
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCancleDilalog("5", "请选择取消订单的理由", item.getLog_id(), false);
                            }
                        });

                    } else {
                        //买家取消预约
                        btn3.setText("取消预约");
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCancleDilalog("2", "请选择取消预约的理由", item.getLog_id(), false);
                            }
                        });
                    }

                }
                //提醒卖家接受预约，相当于订单还未被接受，此状态3个按钮，联系卖家，取消预约，提醒接受，适配UI
                if (buttonBean.getYuyue_notice() == 1) {
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 2;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 3;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 3;
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    String time = item.getDtime();
                    if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                        //判断时间戳是否已经提醒预约
                        long current = System.currentTimeMillis();
                        long timestemp = Long.parseLong(time) * 1000L;
                        long hour = (current - timestemp) / (1000 * 60 * 60L);
                        if (hour < 1) {
                            btn4.setBackgroundColor(Color.parseColor("#c0c0c0"));
                            btn4.setText("已提醒卖家接受预约");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //覆盖原有监听避免复用时错误操作

                                }
                            });
                            return;
                        }
                    }
                    btn4.setText("提醒对方接受预约");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToastUtil.toast("提醒接受预约");
                            ShopApi.EditNotice(item.getLog_id(), "1", new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("提醒成功,请等待对方确认");
                                    long timestemp = System.currentTimeMillis() / 1000L;
                                    item.setDtime(timestemp + "");
                                    initData(null);

                                }

                                @Override
                                public void onDataError(String status, String errormessage) {
                                    super.onDataError(status, errormessage);
                                    initData(null);
                                }
                            }, Object.class);

                        }
                    });
                }
                if (buttonBean.getAccept() == 1) {
                    //// TODO: 2018/10/16  显示倒计时
                    tvAlarm.setVisibility(View.VISIBLE);
                    long remain = Long.parseLong(item.getRemaining_time()) * 1000L;
                    long currentTime = System.currentTimeMillis();
                    long middleTime = remain - currentTime;//差值时间
                    if (middleTime <= 0) {
                        tvAlarm.setText("订单已取消");
                    } else {
                        tvAlarm.setText("剩余" + DateUtil.formatTime(middleTime, -1) + "未付款订单将自动取消");
                    }
                    cuountDowntime = middleTime;
                    if (isTimerRunning) {
                        return;
                    }
                    if (timer == null) {
                        initTimer();
                    }
                    CancelTimer();
                    timer.start();
                    isTimerRunning = true;

                    //卖家待付款：已经接受预约，3个按钮 联系买家，取消订单，已接收预约
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 2;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 3;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 3;
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //覆盖原有监听避免复用时错误操作

                        }
                    });
                    btn4.setText("已接受预约");
                    btn4.setBackgroundColor(Color.parseColor("#c0c0c0"));
                }
                if (buttonBean.getYuyue_confirm() == 1) {
                    //卖家：待接收预约，4个按钮 联系买家，修改价格 拒绝预约 接受预约
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                    layoutParams2.weight = 1;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn2.setText("修改价格");
                    btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_change_price, 0, 0);
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ToastUtil.toast("修改价格");
                            changePrice = "";
                            MipaiDialogUtil.showChangePrice(_mActivity, new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    //ToastUtil.toast("价格="+s.toString());
                                    changePrice = s.toString();

                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (TextUtils.isEmpty(changePrice)) {
                                        ToastUtil.toast("请输入修改价格");
                                        return;
                                    }
                                    MipaiDialogUtil.dismiss();
                                    OrderApi.ChangeTotal(item.getLog_id(), changePrice, new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ShopApi.editeOrder(item.getLog_id(), "1", "", "", new ResponseImpl<Object>() {
                                                @Override
                                                public void onSuccess(Object data) {
                                                    ToastUtil.toast("您已经接受对方的预约，请尽快联系买方");
                                                    initData(null);
                                                }

                                                @Override
                                                public void onDataError(String status, String errormessage) {
                                                    super.onDataError(status, errormessage);
                                                    initData(null);
                                                }
                                            }, Object.class);

                                            //直接接受预约

//                                            ToastUtil.toast("改价成功，请点击接受预约并提醒买家刷新订单后查看");
//                                            initData(null);

                                        }
                                    }, Object.class);
                                }
                            });
                        }
                    });
                    btn3.setText("拒绝预约");
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showCancleDilalog("3", "请选择拒绝预约的理由", item.getLog_id(), true);
                        }
                    });
                    btn4.setText("接受预约");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new MessageDialog.Builder(_mActivity).
                                    setTitle("提示").
                                    setMessage("是否需要修改价格").
                                    setCancel("是").setConfirm("否").setListener(new MessageDialog.OnListener() {
                                @Override
                                public void confirm(Dialog dialog) {
                                    ShopApi.editeOrder(item.getLog_id(), "1", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("您已经接受对方的预约，请尽快联系买方");
                                            initData(null);
                                        }

                                        @Override
                                        public void onDataError(String status, String errormessage) {
                                            super.onDataError(status, errormessage);
                                            initData(null);
                                        }
                                    }, Object.class);
                                }

                                @Override
                                public void cancel(Dialog dialog) {
                                    btn2.performClick();
                                }
                            }).setMessageGravity(Gravity.CENTER).show();
                        }
                    });

                }
                if (buttonBean.getPay() == 1) {
                    //买家待付款
                    tvAlarm.setVisibility(View.VISIBLE);
                    long remain = Long.parseLong(item.getRemaining_time()) * 1000L;
                    long currentTime = System.currentTimeMillis();
                    long middleTime = remain - currentTime;//差值时间
                    Logger.d("remain=" + remain + ",current=" + currentTime + ",middleTime=" + middleTime);
                    if (middleTime <= 0) {
                        tvAlarm.setText("订单已取消");
                    } else {
                        tvAlarm.setText("剩余" + DateUtil.formatTime(middleTime, -1) + "未付款订单将自动取消");
                    }
                    cuountDowntime = middleTime;
                    if (isTimerRunning) {
                        return;
                    }
                    if (timer == null) {
                        initTimer();
                    }
                    CancelTimer();
                    timer.start();
                    isTimerRunning = true;
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 2;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 3;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 3;
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setText("付款");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //付款
                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_pay);
                            bundle.putString("id", item.getOrder_number());
                            bundle.putString("needPay", item.getMoney());
                            bundle.putString("id2", item.getLog_id());
                            EshopActionActivity.start(_mActivity, bundle);
                        }
                    });
                }
                if (buttonBean.getCollect_confirm() == 1) {
                    //确认收货相当于进行中
                    showTimeLine(1);
                    //买家确认收货状态，4个按钮 联系卖家，拨打电话，申请退款 确认收货
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                    layoutParams2.weight = 1;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn2.setText("拨打电话");
                    btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_attach_phone, 0, 0);
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToastUtil.toast("拨打电话");
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + item.getTel());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    btn3.setText("申请退款");
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToastUtil.toast("申请退款");
                            Bundle bundle = new Bundle();
                            bundle.putString("id", item.getLog_id());
                            start(AddRefundFragment.newInstance(bundle));

                        }
                    });
                    btn4.setText("确认收货");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String confirm = getResources().getString(R.string.Confirm);
                            String cancle = getResources().getString(R.string.Cancel);
                            Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "确认收货", "请确认已收到产品，剩余款项将支付给与您合作的制作方", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                                @Override
                                public void onCancleClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }

                                @Override
                                public void onConfirmeClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    ShopApi.editeOrder(item.getLog_id(), "5", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("确认收货成功，请给个评价吧^_^");
                                            initData(null);
                                        }

                                        @Override
                                        public void onDataError(String status, String errormessage) {
                                            super.onDataError(status, errormessage);
                                            initData(null);
                                        }
                                    }, Object.class);


                                }
                            });
                            pDialog.show();
                        }
                    });

                }
                if (buttonBean.getShoot_confirm() == 1) {
                    //确认拍摄结束进行中
                    showTimeLine(1);
                    //买家确认拍摄结束4个按钮，联系卖家，拨打电话 申请退款确认拍摄结束
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                    layoutParams2.weight = 1;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn2.setText("拨打电话");
                    btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_attach_phone, 0, 0);
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToastUtil.toast("拨打电话");
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + item.getTel());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    btn3.setText("申请退款");
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ToastUtil.toast("申请退款");
                            Bundle bundle = new Bundle();
                            bundle.putString("id", item.getLog_id());
                            start(AddRefundFragment.newInstance(bundle));
                        }
                    });
                    btn4.setText("确认拍摄结束");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String confirm = getResources().getString(R.string.Confirm);
                            String cancle = getResources().getString(R.string.Cancel);
                            Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "确认拍摄结束", "请确认前期拍摄工作已完成，款项的30%将支付给与您合作的制作方", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                                @Override
                                public void onCancleClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }

                                @Override
                                public void onConfirmeClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    //确认拍摄结束
                                    ShopApi.editeOrder(item.getLog_id(), "4", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("确认拍摄结束成功");
                                            initData(null);
                                        }

                                        @Override
                                        public void onDataError(String status, String errormessage) {
                                            super.onDataError(status, errormessage);
                                            initData(null);
                                        }
                                    }, Object.class);


                                }
                            });
                            pDialog.show();
                        }
                    });

                }
                if (buttonBean.getShoot_notice() == 1) {
                    //提醒确认拍摄结束相当于进行中
                    showTimeLine(1);
                    //卖方提醒买方确认拍摄结束,两个按钮 联系买家，提醒确认拍摄结束
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn4.setVisibility(View.VISIBLE);
                    String time = item.getDtime();
                    if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                        //判断时间戳是否已经提醒预约
                        long current = System.currentTimeMillis();
                        long timestemp = Long.parseLong(time) * 1000L;
                        long hour = (current - timestemp) / (1000 * 60 * 60L);
                        //Logger.d("current="+current+",timestpem="+timestemp+",差="+(current-timestemp)+",hour="+hour);
                        if (hour < 1) {
                            btn4.setText("已提醒买家确认拍摄结束");
                            btn4.setBackgroundColor(Color.parseColor("#c0c0c0"));
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            return;
                        }
                    }
                    btn4.setText("提醒对方确认拍摄结束");
                    btn4.setPadding(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //提醒对方确认拍摄结束
                            ShopApi.EditNotice(item.getLog_id(), "2", new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("提醒成功,请等待对方确认");
                                    initData(null);

                                }

                                @Override
                                public void onDataError(String status, String errormessage) {
                                    super.onDataError(status, errormessage);
                                    initData(null);
                                }
                            }, Object.class);

                        }
                    });
                }
                if (buttonBean.getCollect_notice() == 1) {
                    //提醒确认收货相当于进行中
                    showTimeLine(1);
                    //卖家 待收货状态，除影视团队订单类型之外，显示两个按钮 联系买家，提醒对方确认收货
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn4.setVisibility(View.VISIBLE);
                    String time = item.getDtime();
                    if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                        //判断时间戳是否已经提醒预约
                        long current = System.currentTimeMillis();
                        long timestemp = Long.parseLong(time) * 1000L;
                        long hour = (current - timestemp) / (1000 * 60 * 60L);
                        //Logger.d("current="+current+",timestpem="+timestemp+",差="+(current-timestemp)+",hour="+hour);
                        if (hour < 1) {
                            btn4.setText("已提醒买家确认收货");
                            btn4.setBackgroundColor(Color.parseColor("#c0c0c0"));
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //覆盖原有监听避免复用时错误操作

                                }
                            });
                            return;
                        }
                    }
                    btn4.setText("提醒对方确认收货");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToastUtil.toast("提醒接受预约");
                            ShopApi.EditNotice(item.getLog_id(), "3", new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("提醒成功,请等待对方确认");
                                    long timestemp = System.currentTimeMillis() / 1000L;
                                    item.setDtime(timestemp + "");
                                    initData(null);

                                }

                                @Override
                                public void onDataError(String status, String errormessage) {
                                    super.onDataError(status, errormessage);
                                    initData(null);
                                }
                            }, Object.class);

                        }
                    });

                }
                if (buttonBean.getComment() == 1) {
                    //去评价相当于未评价 等于完成状态
                    showTimeLine(2);
                    //买家去评价待评价状态 三个个按钮 联系卖家，拨打电话和评价
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                    layoutParams2.weight = 1;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn2.setVisibility(View.VISIBLE);
                    btn2.setText("拨打电话");
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + item.getTel());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    btn3.setVisibility(View.VISIBLE);
                    btn3.setText("申请售后");
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.dismiss();
                                    //ToastUtil.toast("联系客服功能，等M版，觅拍官方工作室开通后，连接");
                                    attach();
                                }
                            });
                        }
                    });
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setText("评价");
                    tvRefuseReason.setVisibility(View.GONE);
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_add_comment);
                            bundle.putString("id", item.getLog_id());
                            bundle.putString("url", item.getLogo());
                            EshopActionActivity.start(_mActivity, bundle);

                        }
                    });
                }
                if (buttonBean.getWait_comm() == 1) {
                    //等待评价 等于完成状态
                    showTimeLine(2);
                    //等待买家评价
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setText("待评价");
                    btn4.setBackgroundColor(Color.parseColor("#c0c0c0"));
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
                //通用 查看评价 进入评价详情
                if (buttonBean.getCheck_comm() == 1) {
                    //查看评价相当于项目完成
                    showTimeLine(3);
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                    layoutParams1.weight = 1;
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                    layoutParams2.weight = 1;
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                    layoutParams3.weight = 1;
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                    layoutParams4.weight = 1;
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setText("查看评价");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_comment_detail);
                            bundle.putString("id", item.getComment_id());
                            EshopActionActivity.start(_mActivity, bundle);

                        }
                    });
                    //如果是买家查看评价 增加拨打电话
                    if (!is_store.equals("1")) {
                        layoutParams3.weight = 1;
                        layoutParams4.weight = 1;
                        btn2.setVisibility(View.VISIBLE);
                        btn2.setText("拨打电话");
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                Uri data = Uri.parse("tel:" + item.getTel());
                                intent.setData(data);
                                startActivity(intent);
                            }
                        });
                        btn3.setVisibility(View.VISIBLE);
                        btn3.setText("申请售后");
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MipaiDialogUtil.dismiss();
                                        // ToastUtil.toast("联系客服功能，等M版，觅拍官方工作室开通后，连接");
                                        attach();
                                    }
                                });
                            }
                        });
                    }

                }
                if (buttonBean.getDelete() == 1) {
                    btn4.setVisibility(View.VISIBLE);
                    btn4.setText("删除订单");
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //删除订单
                            String confirm = getResources().getString(R.string.Confirm);
                            String cancle = getResources().getString(R.string.Cancel);
                            Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "删除订单", "您确定要删除此订单么", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                                @Override
                                public void onCancleClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }

                                @Override
                                public void onConfirmeClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    ShopApi.delete(item.getLog_id(), new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("删除成功");
                                            postUpdate();
                                            pop();
                                        }

                                        @Override
                                        public void onDataError(String status, String errormessage) {
                                            super.onDataError(status, errormessage);
                                            initData(null);
                                        }
                                    }, Object.class);

                                }
                            });
                            pDialog.show();
                        }
                    });

                }

                //售后。
                switch (item.getCust_status()) {
                    //取消流程，状态，1：申请已提交，退款中，2：退款成功，3：拒绝退款,4:同意退款（平台处理中）
                    case "1":
                        llRefoundDetail.setVisibility(View.VISIBLE);
                        tvRefoundReason.setText(item.getUser_reason() + "");
                        tvRefoundMoney.setText("￥" + item.getCust_money() + "");
                        llTips.setVisibility(View.GONE);
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setLeftString("买家已申请退款");
                        tvStatus.setRightString("退款等待受理中");
                        tvStatus.setLeftTvDrawableLeft(getResources().getDrawable(R.drawable.ic_order_alarm));
                        if (is_store.equals("1")) {
                            //拒绝退款，同意退款
                            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) btn3.getLayoutParams();
                            layoutParams3.weight = 2;
                            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                            layoutParams4.weight = 2;
                            btn3.setVisibility(View.VISIBLE);
                            btn4.setVisibility(View.VISIBLE);
                            btn3.setText("拒绝退款");
                            btn4.setText("同意退款");
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //拒绝退款
                                    MipaiDialogUtil.dismiss();
                                    reason_id = "";
                                    final BaseListAdapter<ReasonBean.DataBean> adapter = new BaseListAdapter<ReasonBean.DataBean>(R.layout.item_check, null) {
                                        public int select_position = -1;

                                        @Override
                                        public void handleView(BaseViewHolder helper, final ReasonBean.DataBean item, final int position) {
                                            TextView tv_content = helper.getView(R.id.text);
                                            if (select_position == position) {

                                                tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_checked, 0);
                                            } else {
                                                tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_unchecked, 0);
                                            }
                                            ;
                                            tv_content.setText(item.getMemo());
                                            tv_content.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    select_position = position;
                                                    reason_id = item.getReason_id();
                                                    notifyDataSetChanged();
                                                }
                                            });


                                        }
                                    };
                                    ShopApi.GetReasonList("4", new ResponseImpl<ReasonBean>() {
                                        @Override
                                        public void onStart(int mark) {
                                            setShowProgressDialog(true);
                                            super.onStart(mark);
                                        }

                                        @Override
                                        public void onFinish(int mark) {
                                            super.onFinish(mark);
                                            setShowProgressDialog(false);
                                        }

                                        @Override
                                        public void onSuccess(ReasonBean data) {
                                            adapter.setNewData(data.getData());
                                            MipaiDialogUtil.showSpetificDialog(_mActivity, "请选择拒绝退款原因", adapter, "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    //加入购物车
                                                    MipaiDialogUtil.dismiss();
                                                }
                                            }, "确认", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    MipaiDialogUtil.dismiss();
                                                    ShopApi.editeOrder(log_id, "7", "", reason_id, new ResponseImpl<Object>() {
                                                        @Override
                                                        public void onSuccess(Object data) {
                                                            ToastUtil.toast("您已拒绝退款");
                                                            initData(null);
                                                        }

                                                        @Override
                                                        public void onDataError(String status, String errormessage) {
                                                            super.onDataError(status, errormessage);
                                                            initData(null);
                                                        }
                                                    }, Object.class);

                                                }
                                            }, false);
                                        }
                                    }, ReasonBean.class);


                                }
                            });
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //同意退款
                                    ShopApi.editeOrder(log_id, "6", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("您已同意退款");
                                            initData(null);
                                        }

                                        @Override
                                        public void onDataError(String status, String errormessage) {
                                            super.onDataError(status, errormessage);
                                            initData(null);
                                        }
                                    }, Object.class);

                                }
                            });
                        } else {
                            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                            layoutParams4.weight = 2;
                            btn2.setVisibility(View.VISIBLE);
                            btn2.setText("拨打电话");
                            btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_attach_phone, 0, 0);
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    Uri data = Uri.parse("tel:" + item.getTel());
                                    intent.setData(data);
                                    startActivity(intent);
                                }
                            });
                            btn4.setText("申请售后");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            MipaiDialogUtil.dismiss();
                                            attach();
                                        }
                                    });
                                }
                            });

                        }
                        break;
                    case "4":
                        llRefoundDetail.setVisibility(View.VISIBLE);
                        tvRefoundReason.setText(item.getUser_reason() + "");
                        tvRefoundMoney.setText("￥" + item.getCust_money() + "");
                        llTips.setVisibility(View.GONE);
                        LinearLayout.LayoutParams layoutParams_1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                        layoutParams_1.weight = 1;
                        LinearLayout.LayoutParams layoutParams_4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                        layoutParams_4.weight = 1;
                        btn4.setVisibility(View.VISIBLE);
                        btn4.setText("申请售后");
                        btn4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MipaiDialogUtil.dismiss();
                                        //ToastUtil.toast("联系客服功能，等M版，觅拍官方工作室开通后，连接");
                                        attach();
                                    }
                                });
                            }
                        });
                        tvStatus.setLeftTvDrawableLeft(getResources().getDrawable(R.drawable.ic_order_alarm));
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setLeftString("平台处理中,请耐心等待");
                        tvStatus.setRightString("");
                        break;
                    case "2":
                        llRefoundDetail.setVisibility(View.VISIBLE);
                        tvRefoundReason.setText(item.getUser_reason() + "");
                        tvRefoundMoney.setText("￥" + item.getCust_money() + "");
                        llTips.setVisibility(View.GONE);
                        tvStatus.setLeftTvDrawableLeft(getResources().getDrawable(R.drawable.ic_order_accept));
                        tvStatus.setVisibility(View.VISIBLE);
                        if (is_store.equals("1")) {
                            //卖家退款成功
                            tvStatus.setLeftString("退款成功");
                            tvStatus.setRightString("");
                            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                            layoutParams4.weight = 1;
                            btn4.setVisibility(View.VISIBLE);
                            btn4.setText("申请售后");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            MipaiDialogUtil.dismiss();
                                            attach();
                                        }
                                    });
                                }
                            });
                        } else {
                            tvStatus.setLeftString("退款成功");
                            tvStatus.setRightString("退款详情请到“我的钱包”中查看");
                            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                            layoutParams2.weight = 1;
                            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                            layoutParams4.weight = 2;
                            btn2.setVisibility(View.VISIBLE);
                            btn2.setText("拨打电话");
                            btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_attach_phone, 0, 0);
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    Uri data = Uri.parse("tel:" + item.getTel());
                                    intent.setData(data);
                                    startActivity(intent);
                                }
                            });
                            btn4.setVisibility(View.VISIBLE);
                            btn4.setText("申请售后");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            MipaiDialogUtil.dismiss();
                                            attach();
                                        }
                                    });

                                }
                            });
                        }
                        break;
                    case "3":
                        llRefoundDetail.setVisibility(View.VISIBLE);
                        tvRefoundReason.setText(item.getUser_reason() + "");
                        tvRefoundMoney.setText("￥" + item.getCust_money() + "");
                        llTips.setVisibility(View.GONE);
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setLeftTvDrawableLeft(getResources().getDrawable(R.drawable.ic_order_refuse));
                        tvStatus.setLeftString("拒绝退款");
                        tvStatus.setRightString(item.getReason() + "");
                        if (is_store.equals("1")) {
                            //卖家拒绝退款
                            btn4.setVisibility(View.VISIBLE);
                            btn4.setText("申请售后");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            MipaiDialogUtil.dismiss();
                                            attach();
                                        }
                                    });
                                }
                            });

                        } else {
                            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
                            layoutParams1.weight = 1;
                            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) btn4.getLayoutParams();
                            layoutParams4.weight = 2;
                            btn2.setVisibility(View.VISIBLE);
                            btn2.setText("拨打电话");
                            btn2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_attach_phone, 0, 0);
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    Uri data = Uri.parse("tel:" + item.getTel());
                                    intent.setData(data);
                                    startActivity(intent);
                                }
                            });
                            btn4.setVisibility(View.VISIBLE);
                            btn4.setText("申请售后");
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MipaiDialogUtil.customService(_mActivity, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            MipaiDialogUtil.dismiss();
                                            attach();
                                        }
                                    });
                                }
                            });
                        }
                        break;

                }
                if (item.getAuth_user().equals("1")) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_person_circle);
                } else if (item.getAuth_user().equals("2")) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_business_circle);
                } else {
                    ivVip.setVisibility(View.GONE);
                }
                if (item.getAuth_store().equals("1")) {
                    ivVipShop.setVisibility(View.VISIBLE);
                    ivVipShop.setImageResource(R.drawable.ic_person_rt);
                } else if (item.getAuth_store().equals("2")) {
                    ivVipShop.setVisibility(View.VISIBLE);
                    ivVipShop.setImageResource(R.drawable.ic_business_rt);
                } else {
                    ivVipShop.setVisibility(View.GONE);
                }


            }
        }, OrderDetailBeanMipai.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.btn_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_copy:
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setPrimaryClip(ClipData.newPlainText(null, tvOrderNum.getText().toString()));
                ToastUtil.toast("复制成功");
                break;
        }
    }

    //显示取消订单或者取消预约dialog
    private void showCancleDilalog(String type, final String title, final String log_id, final boolean isRefused) {
        //取消预约或者取消订单的原因
        MipaiDialogUtil.dismiss();
        reason_id = "";
        final BaseListAdapter<ReasonBean.DataBean> adapter = new BaseListAdapter<ReasonBean.DataBean>(R.layout.item_check, null) {
            public int select_position = -1;

            @Override
            public void handleView(BaseViewHolder helper, final ReasonBean.DataBean item, final int position) {
                TextView tv_content = helper.getView(R.id.text);
                if (select_position == position) {

                    tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_checked, 0);
                } else {
                    tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_unchecked, 0);
                }
                ;
                tv_content.setText(item.getMemo());
                tv_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        select_position = position;
                        reason_id = item.getReason_id();
                        notifyDataSetChanged();
                    }
                });


            }
        };
        ShopApi.GetReasonList(type, new ResponseImpl<ReasonBean>() {
            @Override
            public void onStart(int mark) {
                setShowProgressDialog(true);
                super.onStart(mark);
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
            }

            @Override
            public void onSuccess(ReasonBean data) {
                adapter.setNewData(data.getData());
                MipaiDialogUtil.showSpetificDialog(_mActivity, title, adapter, "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //加入购物车
                        MipaiDialogUtil.dismiss();
                    }
                }, "确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(reason_id)) {
                            ToastUtil.toast("请选择原因");
                            return;
                        }
                        MipaiDialogUtil.dismiss();
                        Logger.d("reason_id" + reason_id + ",log_id" + log_id);
                        //ToastUtil.toast("预约已取消");
                        if (isRefused) {
                            //拒绝预约 走编辑订单接口
                            ShopApi.editeOrder(log_id, "2", "", reason_id, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("您已拒绝预约");
                                    initData(null);
                                }

                                @Override
                                public void onDataError(String status, String errormessage) {
                                    super.onDataError(status, errormessage);
                                    initData(null);
                                }
                            }, Object.class);

                        } else {
                            //取消预约
                            ShopApi.cancleOrder(log_id, reason_id, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("预约已取消");
                                    initData(null);

                                }

                                @Override
                                public void onDataError(String status, String errormessage) {
                                    super.onDataError(status, errormessage);
                                    initData(null);
                                }
                            }, Object.class);
                        }


                    }
                }, false);
            }
        }, ReasonBean.class);

    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(UpdateDataEvent event) {
        //支付成功
        if (event.type == 1) {
            return;
        }
        initData(null);

    }

    private void postUpdate() {
        UpdateDataEvent event = new UpdateDataEvent();
        event.type = 1;
        EventBus.getDefault().post(event);
    }

    //显示项目进行状态
    private void showTimeLine(int step) {

        switch (step) {
            case 1:
                //进行中
                ivStep2.setImageResource(R.drawable.ic_step_gray);
                ivStep3.setImageResource(R.drawable.ic_step_gray);
                tvTimeLine1.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine2.setBackgroundResource(R.drawable.ic_time_line_gray);
                tvTimeLine3.setBackgroundResource(R.drawable.ic_time_line_gray);
                tvTimeLine4.setBackgroundResource(R.drawable.ic_time_line_gray);
                break;
            case 2:
                //确认收货但是未评价
                ivStep2.setImageResource(R.drawable.ic_step_orange);
                ivStep3.setImageResource(R.drawable.ic_step_gray);
                tvTimeLine1.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine2.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine3.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine4.setBackgroundResource(R.drawable.ic_time_line_gray);
                break;
            case 3:
                //评价完成
                ivStep2.setImageResource(R.drawable.ic_step_orange);
                ivStep3.setImageResource(R.drawable.ic_step_orange);
                tvTimeLine1.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine2.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine3.setBackgroundResource(R.drawable.ic_time_line_orange);
                tvTimeLine4.setBackgroundResource(R.drawable.ic_time_line_orange);
                break;

        }
        llTimeLine.setVisibility(View.VISIBLE);

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
                Logger.d("---onSuccess--" + s);
                //连接成功后进入 聊天界面
//                if (mDialog != null)
//                    mDialog.dismiss();
//                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
//                finish();
                RongIM.getInstance().startPrivateChat(_mActivity, dataBean.getData().getSide_id(), dataBean.getData().getSide_nick());
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                ToastUtil.toast("聊天异常：" + e.toString());
                Logger.d("聊天异常：" + e.toString());
            }
        });

    }

    @Override
    public void onDestroy() {
        CancelTimer();
        timer = null;
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CancelTimer();
        timer = null;
        unbinder.unbind();
    }

    //取消计时器
    private void CancelTimer() {
        if (timer == null) {
            isTimerRunning = false;
            return;
        }
        timer.cancel();
        isTimerRunning = false;
    }

    //初始化计时器
    private void initTimer() {
        timer = new CountDownTimer(cuountDowntime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                if (tvAlarm == null) {
                    return;
                }

                tvAlarm.setText("剩余" + DateUtil.formatTime(millisUntilFinished, -1) + "未付款订单将自动取消");

            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                if (tvAlarm == null) {
                    return;
                }
                tvAlarm.setText("订单已取消");
            }
        };
    }

    public void attach() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            ToastUtil.toast("请先登录");
            Bundle bundle = new Bundle();
            bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
            bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
            LoginMainActivity.start(_mActivity, bundle);
        } else {
            //如果没连接则先连接
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                reconnect(UserUtil.getRongYunToken(_mActivity));
            } else {
                RongIM.getInstance().startPrivateChat(_mActivity, "2010", "觅拍官方");
            }

        }
    }

}


