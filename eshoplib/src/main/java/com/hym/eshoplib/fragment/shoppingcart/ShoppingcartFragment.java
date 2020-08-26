package com.hym.eshoplib.fragment.shoppingcart;

import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.shop.AttachResultBean;
import com.hym.eshoplib.bean.shoppingcart.ShoppingcatrtListBeanMipai;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/3/26.
 * <p>
 * Description 购物车
 * <p>
 * otherTips
 */

public class ShoppingcartFragment extends BaseListFragment<ShoppingcatrtListBeanMipai.DataBean> {
    public static ShoppingcartFragment newInstance(Bundle args) {
        ShoppingcartFragment fragment = new ShoppingcartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_shopping_cart;
    }

    @Override
    public void excuteLogic() {
        setIsshowTop(true);
        getIv_add().setVisibility(View.VISIBLE);
        getIv_add().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRv_list().smoothScrollToPosition(0);
            }
        });
        setContainerColor(R.color.resource_gray_f1f1f1);
        setTitle(R.string.Shoppingcart);
        //删除购物车
        getAdapter().setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                String confirm = getResources().getString(R.string.Confirm);
                String cancle = getResources().getString(R.string.Cancel);

                DialogManager.getInstance().initSimpleDialog(_mActivity, "提示", "您确定要删这个商品么",
                        cancle, confirm, new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();
                                ShoppingCarApi.deleGoods(_mActivity, getAdapter().getData().get(position).getCart_id(),
                                        new ResponseImpl<Object>() {
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
                                            public void onSuccess(Object data) {
                                                onRefresh();

                                            }
                                        },
                                        Object.class);


                            }
                        }).show();


                return false;
            }
        });
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv = emptyView.findViewById(R.id.iv_icon);
        iv.setImageResource(R.drawable.ic_shoppingcart_empty);
        getAdapter().setEmptyView(emptyView);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String content_id = getAdapter().getData().get(position).getContent_id();
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                bundle.putString("id", content_id);
                ActionActivity.start(_mActivity, bundle);
                // ActionActivity.start(_mActivity, BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail));
            }
        });


    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        refreshData();

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        refreshData();
    }

    private void refreshData() {
        ShoppingCarApi.cartGetAll(_mActivity, new ResponseImpl<ShoppingcatrtListBeanMipai>() {
            @Override
            public void onSuccess(ShoppingcatrtListBeanMipai data) {
                getAdapter().setNewData(data.getData());

            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        }, ShoppingcatrtListBeanMipai.class);

    }


    @Override
    public void bindData(BaseViewHolder helper, final ShoppingcatrtListBeanMipai.DataBean item, final int position) {
        final ImageView iv_logo = helper.getView(R.id.iv_icon);
        ImageUtil.getInstance().loadImage(ShoppingcartFragment.this, item.getLogo(), iv_logo);
        TextView tv_shop_name = helper.getView(R.id.tv_name);
        tv_shop_name.setText(item.getStore_name() + "");
        TextView tv_type = helper.getView(R.id.tv_type);
        tv_type.setText("类别：" + item.getCategory_name());
        TextView tv_price = helper.getView(R.id.tv_price);
        tv_price.setText("￥：" + item.getPrice());

        TextView tv_attach_now = helper.getView(R.id.tv_attach_now);
        SnappingStepper stepper = helper.getView(R.id.stepper);
        stepper.setValue(Integer.parseInt(item.getQuantity()));


        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                Logger.d("改变数量value=" + value);
                ShopApi.changeCount(item.getCart_id(), value + "", new ResponseImpl<Object>() {
                    @Override
                    public void onStart(int mark) {
                        showProgressDialog();
                        super.onStart(mark);
                    }

                    @Override
                    public void onFinish(int mark) {
                        super.onFinish(mark);
                        setShowProgressDialog(false);
                    }

                    @Override
                    public void onSuccess(Object data) {

                    }
                }, Object.class);

            }
        });

        tv_attach_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即预约
                if (getAdapter().getData().get(position).getAuth_user().equals("0")) {
                    // 弹出认证
                    Dialog dilog = MipaiDialogUtil.getAuthDialog(_mActivity, "认证信息", "完善资料才可以预约合作哦", new MipaiDialogUtil.OnBtnSlectListener() {
                        @Override
                        public void on1(Dialog dialog) {
                            dialog.dismiss();
                            //个人认证
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Personal);
                            ActionActivity.start(_mActivity, bundle);

                        }

                        @Override
                        public void on2(Dialog dialog) {
                            dialog.dismiss();
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth_Business);
                            ActionActivity.start(_mActivity, bundle);

                        }
                    });
                    dilog.show();
                    return;
                }
                String content_id = getAdapter().getData().get(position).getContent_id();
                String cart_id = getAdapter().getData().get(position).getCart_id();
                ShopApi.attachNowShoppingcart(content_id, cart_id, new ResponseImpl<AttachResultBean>() {
                    @Override
                    public void onSuccess(AttachResultBean data) {
                        ToastUtil.toast("已提交预约");
                        Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_order_detail);
                        bundle.putString("id", data.getData().getLog_id());
                        EshopActionActivity.start(_mActivity, bundle);

                    }
                }, AttachResultBean.class);
            }
        });
        if (item.getIs_down().equals("1")) {
            //已下架
            stepper.setVisibility(View.GONE);
            tv_attach_now.setVisibility(View.GONE);
            tv_price.setText("该服务已下架无法接受预约");
            tv_price.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
        } else {
            stepper.setVisibility(View.VISIBLE);
            tv_attach_now.setVisibility(View.VISIBLE);
            tv_price.setTextColor(ContextCompat.getColor(_mActivity, android.R.color.holo_red_light));
        }
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


}
