package com.hym.eshoplib.fragment.order;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.goods.GoodsDetailActivity;
import com.hym.eshoplib.bean.order.OrderDetailBean;
import com.hym.eshoplib.fragment.goods.PublishCommentsFragment;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.imagelib.ImageUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/4/14.
 * <p>
 * Description 订单详情
 * <p>
 * otherTips
 */

public class OrderDetailFragment extends BaseKitFragment {
    @BindView(R2.id.tv_tips)
    SuperTextView tvTips;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_phone)
    TextView tvPhone;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.rl_select_address)
    RelativeLayout rlSelectAddress;
    @BindView(R2.id.iv_shop)
    ImageView ivShop;
    @BindView(R2.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R2.id.tv_status)
    TextView tvStatus;
    @BindView(R2.id.ll_shop)
    LinearLayout llShop;
    @BindView(R2.id.view_diver)
    View viewDiver;
    @BindView(R2.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R2.id.tv_freight)
    TextView tvFreight;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R2.id.btn_1)
    SuperButton btn1;
    @BindView(R2.id.btn_2)
    SuperButton btn2;
    @BindView(R2.id.ll_buttons)
    LinearLayout llButtons;
    Unbinder unbinder;
    String order_id;
    public static OrderDetailFragment newInstance(Bundle args) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle(R.string.Detailsoforder);
        order_id=getArguments().getString("id","");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        OrderApi.DetailOrder(_mActivity, order_id, new ResponseImpl<OrderDetailBean>() {
            @Override
            public void onSuccess(final OrderDetailBean data) {
                //收货地址
                tvName.setText(getResources().getString(R.string.Receiver)+"："+data.getData().getConsignee_name());
                tvPhone.setText(data.getData().getConsignee_mobile()+"");
                tvContent.setText(data.getData().getConsignee_address()+"");
                //店铺信息
                ImageUtil.getInstance().loadImage(OrderDetailFragment.this, data.getData().getStore_logo(), ivShop);
                tvShopName.setText(data.getData().getStore_name()+"");
                //订单状态
                tvStatus.setText(data.getData().getStatus_name()+"");
                //价格
                tvPrice.setText(data.getData().getPayment_amount()+"RMB");
                tvFreight.setText("("+getResources().getString(R.string.Freight)+"："+(TextUtils.isEmpty(data.getData().getFreight_amount())?"0":data.getData().getFreight_amount())+" RMB)");
                llGoods.removeAllViews();
                //设置店铺商品
                for (final OrderDetailBean.DataBean.ItemsBean bean : data.getData().getItems()) {
                    View view = LayoutInflater.from(_mActivity).inflate(R.layout.item_order_goods, null, false);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle=new Bundle();
                            bundle.putString("id",bean.getSpecification_id());
                            GoodsDetailActivity.start(_mActivity,bundle);
                        }
                    });
                    ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                    TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
                    TextView tv_goods_price = (TextView) view.findViewById(R.id.tv_price);
                    TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
                    ImageUtil.getInstance().loadImage(OrderDetailFragment.this, bean.getGoods_image(), iv_icon);
                    tv_name.setText(bean.getGoods_name() + "");
                    tv_type.setText(bean.getProperty_relate() + "");
                    tv_goods_price.setText(TextUtils.isEmpty(bean.getBuy_price())?"0":bean.getBuy_price() + " RMB");
                    tv_count.setText("x" + bean.getBuy_num());
                    //设置评价按钮
                    LinearLayout ll_review= (LinearLayout) view.findViewById(R.id.ll_review);
                    View diver=view.findViewById(R.id.view_diver);
                    SuperButton btn_review= (SuperButton) view.findViewById(R.id.btn_review);
                    if(data.getData().getPay_status().equals("1")&&data.getData().getStatus().equals("7")){
                        //待评价订单，每个商品可以评价
                        ll_review.setVisibility(View.VISIBLE);
                        //diver.setVisibility(View.VISIBLE);
                        btn_review.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //ToastUtil.toast("去评价");
                                Bundle bundle=new Bundle();
                                bundle.putString("order_id",order_id);
                                bundle.putString("id",bean.getSpecification_id());
                                bundle.putString("url",bean.getGoods_image());
                                bundle.putString("name",bean.getGoods_name());
                                start(  PublishCommentsFragment.newInstance(bundle));

                            }
                        });

                    }else {
                        ll_review.setVisibility(View.GONE);
                        diver.setVisibility(View.GONE);
                    }
                    llGoods.addView(view);

                }
                //设置日志信息
                String log=getResources().getString(R.string.Ordernumber)+"："+data.getData().getChild_order_number()+"\n"+
                           getResources().getString(R.string.Createdtime)+"："+data.getData().getCtime()+"\n";
                if(!TextUtils.isEmpty(data.getData().getPay_time())){
                    log+=getResources().getString(R.string.Paymenttime)+data.getData().getPay_time()+"\n";
                }
                if(!TextUtils.isEmpty(data.getData().getDelivery_time())){
                    log+=getResources().getString(R.string.Deliverytime)+"："+data.getData().getDelivery_time()+"\n";
                }
                if(!TextUtils.isEmpty(data.getData().getDeal_time())){
                    log+=getResources().getString(R.string.Transactiontime)+"："+data.getData().getDeal_time();
                }
                tvOrderStatus.setText(log);
                //根据订单状态设置按钮
                tvTips.setVisibility(View.GONE);
                String status=data.getData().getStatus();
                if(data.getData().getPay_status().equals("1")){
                    //已支付
                    if(status.equals("5")){
                        //待收货
                        llButtons.setVisibility(View.VISIBLE);
                        btn1.setText(R.string.Logistics);
                        btn2.setText(R.string.Received);
                        btn1.setVisibility(View.VISIBLE);
                        btn2.setVisibility(View.VISIBLE);
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // ToastUtil.toast("查看物流");
                                Bundle bundle = EshopActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,EshopActionActivity.Action_order_order_logitics);
                                bundle.putString("id",data.getData().getChild_order_id());
                                bundle.putString("url",data.getData().getItems().get(0).getGoods_image());
                                EshopActionActivity.start(_mActivity,bundle);
                            }
                        });
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // ToastUtil.toast("确认收货");
                                String confirm = getResources().getString(R.string.Confirm);
                                String cancle = getResources().getString(R.string.Cancel);
                                Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, getResources().getString(R.string.ConfirmOrder), "请确认货物已经收到", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                                    @Override
                                    public void onCancleClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();

                                    }

                                    @Override
                                    public void onConfirmeClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                        OrderApi.ConfirmOrder(_mActivity,data.getData().getChild_order_id(), new ResponseImpl<Object>() {
                                            @Override
                                            public void onSuccess(Object data) {
                                               EventBus.getDefault().post(new UpdateDataEvent());
                                                _mActivity.finish();
                                            }
                                        },Object.class);

                                    }
                                });
                                pDialog.show();
                            }
                        });
                        //待收货订单显示确认时间

                        if(data.getData().getRemaining_time()>0){
                            tvTips.setVisibility(View.VISIBLE);
                            tvTips.setLeftString("剩余"+data.getData().getRemaining_time()+"小时自动确认订单");
                        }

                    }else if(status.equals("7")){
                        //待评价
//                                              llButtons.setVisibility(View.VISIBLE);
//                        btn1.setVisibility(View.GONE);
//                        btn2.setText(R.string.review);
//                        btn2.setVisibility(View.VISIBLE);
//                        btn2.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                ToastUtil.toast("评价");
//                            }
//                        });

                    }


                }else {
                    //待支付
                    llButtons.setVisibility(View.VISIBLE);
                    btn1.setText(R.string.Cancelorder);
                    btn2.setText(R.string.Payment2);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           // ToastUtil.toast("取消订单");
                            String confirm = getResources().getString(R.string.Confirm);
                            String cancle = getResources().getString(R.string.Cancel);
                            Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, getResources().getString(R.string.Cancel), "确定要取消订单吗?", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                                @Override
                                public void onCancleClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }

                                @Override
                                public void onConfirmeClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    OrderApi.CancelOrder(_mActivity,data.getData().getChild_order_id(), new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            EventBus.getDefault().post(new UpdateDataEvent());
                                            _mActivity.finish();

                                        }
                                    },Object.class);

                                }
                            });
                            pDialog.show();
                        }
                    });
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.toast("支付");
                        }
                    });

                }


            }
        },OrderDetailBean.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
