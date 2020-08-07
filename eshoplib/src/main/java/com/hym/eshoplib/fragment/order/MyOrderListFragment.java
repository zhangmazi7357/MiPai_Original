package com.hym.eshoplib.fragment.order;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.order.OrderListBeanMiPai;
import com.hym.eshoplib.bean.order.ReasonBean;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 我的订单列表
 * <p>
 * otherTips
 */

public class MyOrderListFragment extends BaseListFragment<OrderListBeanMiPai.DataBean.InfoBean> {
    int type = 0;
    String reason_id;//取消预约的理由id

    public static MyOrderListFragment newInstance(Bundle args) {
        MyOrderListFragment fragment = new MyOrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_order_list;
    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Override
    public void excuteLogic() {
        setIsshowTop(true);
        type = getArguments().getInt("type", 0);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //进入订单详情
                Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_detail);
                bundle.putString("id", getAdapter().getData().get(position).getLog_id());
                EshopActionActivity.start(_mActivity, bundle);

            }
        });
        View empty_view = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv = empty_view.findViewById(R.id.iv_icon);
        TextView tv_mesg = empty_view.findViewById(R.id.tv_message);
        iv.setImageResource(R.drawable.ic_empty_order);
        tv_mesg.setText("当前还没有订单哦~");
        getAdapter().setEmptyView(empty_view);

    }


    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        String orderStatus = "";
        switch (type) {
            case 0:
                //全部
                break;
            case 1:
                //待接收预约
                orderStatus = "1";
                break;
            case 2:
                //待付款
                orderStatus = "2";
                break;
            case 3:
                //待确认收货
                orderStatus = "3";
                break;
            case 4:
                //待评价
                orderStatus = "4";
                break;
            case 5:
                //退款 售后
                orderStatus = "5";
                break;
        }
        //订单状态-非必须，默认全部（1:待接受预约,2:待付款,3:待确认收货(2+3),4:评价中心,5退款/售后）
        OrderApi.getUserOrderList(orderStatus, pageNum + "", new ResponseImpl<OrderListBeanMiPai>() {
            @Override
            public void onSuccess(OrderListBeanMiPai data) {
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
        }, OrderListBeanMiPai.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, final OrderListBeanMiPai.DataBean.InfoBean item, int position) {
        CardView cardView = helper.getView(R.id.cardview);
        //适配间距
        RelativeLayout rl = helper.getView(R.id.rl_container);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) rl.getLayoutParams();
        if (position == 0) {
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10));
        } else {
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity, 10), 0, ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10));
        }
        //订单关闭按钮
        TextView tv_order_close = helper.getView(R.id.tv_order_close);
        if (item.getStatus().equals("1")) {
            //已取消
            tv_order_close.setVisibility(View.VISIBLE);
        } else {
            tv_order_close.setVisibility(View.GONE);
        }
        SuperButton btn_1 = helper.getView(R.id.btn_1);//左边按钮
        Button btn_2 = helper.getView(R.id.btn_2);//右边按钮
        RelativeLayout rl_buyer = helper.getView(R.id.rl_buyer);//卖家查看订单需要显示买家头像和nick
        ImageView iv_avantar = helper.getView(R.id.iv_avatar);//买家头像
        TextView tv_nick = helper.getView(R.id.tv_nick);//买家昵称
        //订单信息
        ImageView iv_shop_logo = helper.getView(R.id.iv_icon);//工作室logo
        TextView tv_shop_name = helper.getView(R.id.tv_title);//工作室名称
        TextView tv_type = helper.getView(R.id.tv_type);//购买种类
        TextView tv_count = helper.getView(R.id.tv_count);//购买数量
        TextView tv_price = helper.getView(R.id.tv_price);//订单价格
        ImageUtil.getInstance().loadImage(MyOrderListFragment.this, item.getLogo(), iv_shop_logo);
        tv_shop_name.setText(item.getStore_name() + "");
        tv_type.setText("类别：" + item.getCategory_name());
        tv_price.setText("￥：" + item.getMoney());
        tv_count.setText("x" + item.getBuy_num());
        //判断订单是买家还是卖家
        final String is_store = item.getIs_store();
        if (is_store.equals("1")) {
            //是卖家
            //显示买家信息
            rl_buyer.setVisibility(View.VISIBLE);
            ImageUtil.getInstance().loadCircleImage(MyOrderListFragment.this, item.getAvatar(), iv_avantar);
            tv_nick.setText(item.getNickname() + "");
            cardView.setCardElevation(10);
        } else {
            //是买家
            //不显示买家信息
            rl_buyer.setVisibility(View.GONE);
            cardView.setCardElevation(0);
        }
        //判断按钮
        btn_1.setVisibility(View.GONE);
        btn_2.setVisibility(View.GONE);
        btn_1.setShapeSolidColor(Color.parseColor("#f3ebd8"));
        btn_1.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
        btn_2.setBackgroundResource(R.drawable.selector_btn);
        btn_2.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
        OrderListBeanMiPai.DataBean.InfoBean.ButtonBean buttonBean = item.getButton();
        if (buttonBean.getCancel() == 1) {
            btn_1.setVisibility(View.VISIBLE);
            //如果订单没被接受预约，则显示取消预约，如果订单被接受则显示取消订单
            if (buttonBean.getPay() == 1) {
                //对于买家说，显示待付款按钮则相当于 被接受预约
                btn_1.setText("取消订单");
                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCancleDilalog("2", "请选择取消订单的理由", item.getLog_id(), false);
                    }
                });
            } else if (buttonBean.getAccept() == 1) {
                //已接受预约
                //卖家来说，显示已接受预约 相当于被接受预约
                btn_1.setText("取消订单");
                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCancleDilalog("5", "请选择取消订单的理由", item.getLog_id(), false);
                    }
                });

            } else {
                //买家取消预约
                btn_1.setText("取消预约");
                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCancleDilalog("2", "请选择取消预约的理由", item.getLog_id(), false);
                    }
                });
            }

        }
//        if(buttonBean.getCancel()==1){
//            btn_1.setVisibility(View.VISIBLE);
//            //取消预约按钮
//            if(is_store.equals("1")){
//                btn_1.setText("取消订单");
//                btn_1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showCancleDilalog("请选择取消订单的理由",item.getLog_id(),false);
//                    }
//                });
//            }else {
//                btn_1.setText("取消预约");
//                btn_1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showCancleDilalog("请选择取消预约的理由",item.getLog_id(),false);
//                    }
//                });
//            }
//
//        }
        if (buttonBean.getDelete() == 1) {
            btn_1.setVisibility(View.VISIBLE);
            btn_1.setText("删除订单");
            btn_1.setOnClickListener(new View.OnClickListener() {
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
                                    onRefresh();
                                }
                            }, Object.class);

                        }
                    });
                    pDialog.show();
                }
            });

        }
        //买家
        if (buttonBean.getYuyue_notice() == 1) {
            btn_2.setVisibility(View.VISIBLE);
            String time = item.getDtime();
            if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                //判断时间戳是否已经提醒预约
                long current = System.currentTimeMillis();
                long timestemp = Long.parseLong(time) * 1000L;
                long hour = (current - timestemp) / (1000 * 60 * 60L);

                Logger.d("current=" + current + ",timestpem=" + timestemp + ",差=" + (current - timestemp) + ",hour=" + hour);
                if (hour < 1) {
                    btn_2.setText("已提醒卖家接受预约");
                    // btn_2.setTextColor(Color.parseColor("#a3a3a3"));
                    btn_2.setBackgroundResource(R.drawable.shape_graye3e3e3_solid_conner5);
                    btn_2.setPadding(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
                    btn_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    return;
                }
            }
            btn_2.setText("提醒对方接受预约");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ToastUtil.toast("提醒接受预约");
                    ShopApi.EditNotice(item.getLog_id(), "1", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("提醒成功,请等待对方确认");
                            long timestemp = System.currentTimeMillis() / 1000L;
                            item.setDtime(timestemp + "");
                            getAdapter().notifyDataSetChanged();

                        }
                    }, Object.class);

                }
            });
        }

        if (buttonBean.getPay() == 1) {
            //付款
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("付款");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //付款
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_pay);
                    bundle.putString("id", item.getOrder_number());
                    bundle.putString("id2", item.getLog_id());
                    bundle.putString("needPay", item.getMoney());
                    EshopActionActivity.start(_mActivity, bundle);
                }
            });
        }
        if (buttonBean.getShoot_confirm() == 1) {
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("确认拍摄结束");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DialogManager.getInstance().initSimpleDialog(_mActivity,
                            "确认拍摄结束",
                            "请确认前期拍摄工作已完成，款项的30%将支付给与您合作的制作方",
                            "取消", "确认", new SimpleDialog.SimpleDialogOnClickListener() {
                                @Override
                                public void negativeClick(Dialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void positiveClick(Dialog dialog) {
                                    dialog.dismiss();
                                    //确认拍摄结束
                                    ShopApi.editeOrder(item.getLog_id(), "4", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("操作成功，制作方完成全部制作后，会提醒您确认收货。");
                                            onRefresh();
                                        }
                                    }, Object.class);
                                }
                            }).show();

                }
            });
        }
        if (buttonBean.getCollect_confirm() == 1) {
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("确认收货");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    DialogManager.getInstance().initSimpleDialog(_mActivity, "确认收货", "请确认已收到产品，剩余款项将支付给与您合作的制作方",
                            "取消", "确认", new SimpleDialog.SimpleDialogOnClickListener() {
                                @Override
                                public void negativeClick(Dialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void positiveClick(Dialog dialog) {
                                    dialog.dismiss();
                                    ShopApi.editeOrder(item.getLog_id(), "5", "", "", new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            ToastUtil.toast("确认收货成功，请给个评价吧^_^");
                                            onRefresh();
                                        }
                                    }, Object.class);
                                }
                            }).show();
                }
            });
        }
        if (buttonBean.getComment() == 1) {
            //买家去评价
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("评价");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_add_comment);
                    Logger.d("log_id=" + item.getLog_id());
                    bundle.putString("id", item.getLog_id());
                    bundle.putString("url", item.getLogo());
                    EshopActionActivity.start(_mActivity, bundle);

                }
            });
        }

        //卖家
        if (buttonBean.getRefuse() == 1) {
            //拒绝预约
            btn_1.setVisibility(View.VISIBLE);
            btn_1.setText("拒绝预约");
            btn_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCancleDilalog("3", "请选择拒绝预约的理由", item.getLog_id(), true);
                }
            });
        }
        if (buttonBean.getYuyue_confirm() == 1) {
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("接受预约");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!item.getAuth_store().equals("1") && item.getAuth_store().equals("2")) {
                        //如果工作室没认证
                    }
                    ShopApi.editeOrder(item.getLog_id(), "1", "", "", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("您已经接受对方的预约，请尽快联系买方");
                            onRefresh();
                        }
                    }, Object.class);


                }
            });
        }
        if (buttonBean.getShoot_notice() == 1) {
            // TODO: 2018/9/19 要判断已提醒
            //提醒买方确认拍摄结束,
            btn_2.setVisibility(View.VISIBLE);
            String time = item.getDtime();
            if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                //判断时间戳是否已经提醒预约
                long current = System.currentTimeMillis();
                long timestemp = Long.parseLong(time) * 1000L;
                long hour = (current - timestemp) / (1000 * 60 * 60L);
                //Logger.d("current="+current+",timestpem="+timestemp+",差="+(current-timestemp)+",hour="+hour);
                if (hour < 1) {
                    btn_2.setText("已提醒买家确认拍摄结束");
                    // btn_2.setTextColor(Color.parseColor("#ffffff"));
                    btn_2.setPadding(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
                    btn_2.setBackgroundResource(R.drawable.shape_graye3e3e3_solid_conner5);
                    btn_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    return;
                }
            }
            btn_2.setText("提醒对方确认拍摄结束");
            btn_2.setPadding(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //提醒对方确认拍摄结束
                    ShopApi.EditNotice(item.getLog_id(), "2", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("提醒成功,请等待对方确认");
                            long timestemp = System.currentTimeMillis() / 1000L;
                            item.setDtime(timestemp + "");
                            getAdapter().notifyDataSetChanged();

                        }
                    }, Object.class);

                }
            });
        }
        if (buttonBean.getCollect_notice() == 1) {
            // TODO: 2018/9/19 要判断已提醒
            //提醒买方确认收货
            btn_2.setVisibility(View.VISIBLE);
            String time = item.getDtime();
            if (!TextUtils.isEmpty(time) && !time.equals("0")) {
                //判断时间戳是否已经提醒预约
                long current = System.currentTimeMillis();
                long timestemp = Long.parseLong(time) * 1000L;
                long hour = (current - timestemp) / (1000 * 60 * 60L);
                //Logger.d("current="+current+",timestpem="+timestemp+",差="+(current-timestemp)+",hour="+hour);
                if (hour < 1) {
                    btn_2.setText("已提醒买家确认收货");
                    //btn_2.setTextColor(Color.parseColor("#a3a3a3"));
                    btn_2.setPadding(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
                    btn_2.setBackgroundResource(R.drawable.shape_graye3e3e3_solid_conner5);
                    btn_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    return;
                }
            }
            btn_2.setText("提醒对方确认收货");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ToastUtil.toast("提醒接受预约");
                    ShopApi.EditNotice(item.getLog_id(), "3", new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("提醒成功,请等待对方确认");
                            long timestemp = System.currentTimeMillis() / 1000L;
                            item.setDtime(timestemp + "");
                            getAdapter().notifyDataSetChanged();

                        }
                    }, Object.class);

                }
            });

        }
        if (buttonBean.getWait_comm() == 1) {
            //等待买家评价
            btn_2.setVisibility(View.VISIBLE);
            //改变颜色
            btn_2.setText("待评价");
            // btn_2.setTextColor(Color.parseColor("#a3a3a3"));
            btn_2.setBackgroundResource(R.drawable.shape_graye3e3e3_solid_conner5);
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        //通用 查看评价 进入评价详情
        if (buttonBean.getCheck_comm() == 1) {
            btn_2.setVisibility(View.VISIBLE);
            btn_2.setText("查看评价");
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_comment_detail);
                    bundle.putString("id", item.getComment_id());
                    EshopActionActivity.start(_mActivity, bundle);

                }
            });
        }
        ImageView iv_vip = helper.getView(R.id.iv_vip);
        ImageView iv_vip_shop = helper.getView(R.id.iv_vip_shop);
        if (item.getAuth_user().equals("1")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_person_circle);
        } else if (item.getAuth_user().equals("2")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_business_circle);
        } else {
            iv_vip.setVisibility(View.GONE);
        }
        if (item.getAuth_store().equals("1")) {
            iv_vip_shop.setVisibility(View.VISIBLE);
            iv_vip_shop.setImageResource(R.drawable.ic_person_rt);
        } else if (item.getAuth_store().equals("2")) {
            iv_vip_shop.setVisibility(View.VISIBLE);
            iv_vip_shop.setImageResource(R.drawable.ic_business_rt);
        } else {
            iv_vip_shop.setVisibility(View.GONE);
        }

    }

    public void changeType(int type) {
        this.type = type;
        onRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateData(UpdateDataEvent event) {
        onRefresh();
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
                            ShopApi.editeOrder(log_id, "3", "", reason_id, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("您已拒绝预约");
                                    onRefresh();
                                }
                            }, Object.class);

                        } else {
                            //取消预约
                            ShopApi.cancleOrder(log_id, reason_id, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("预约已取消");
                                    onRefresh();
                                }
                            }, Object.class);
                        }


                    }
                }, false);
            }
        }, ReasonBean.class);

    }


}
