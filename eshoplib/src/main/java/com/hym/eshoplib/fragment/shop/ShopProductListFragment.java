package com.hym.eshoplib.fragment.shop;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description 编辑工作室信息里边的 产品列表，区别于工作室详情的产品列表
 * <p>
 * otherTips
 */

public class ShopProductListFragment extends BaseListFragment<ShopProductsBean.DataBean.InfoBean> {
    private String TAG = "ShopListProductList";
    String id;
    String token;
    int type;

    public static ShopProductListFragment newInstance(Bundle args) {
        ShopProductListFragment fragment = new ShopProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_shop_products;
    }

    @Override
    public void excuteLogic() {
        type = getProducionsType(getArguments().getString("type"));
        Logger.d("type=" + type);

        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //进入产品详情
                String type = getAdapter().getData().get(position).getType();
                String id = getAdapter().getData().get(position).getCase_id();
                Bundle bundle;
                if (type.equals("1")) {
                    //视频
                    bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_vadieo_detail);
                    bundle.putInt("type", 1);//从店铺产品中进入详情，不显示进入店铺按钮
                    bundle.putString("id", id);
                    ActionActivity.start(_mActivity, bundle);

                } else if (type.equals("2")) {
                    //图片
                    //去图片详情
                    bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.Action_image_detail);
                    bundle.putInt("type", 1);//从店铺产品中进入详情，不显示进入店铺按钮
                    bundle.putString("id", id);
                    ActionActivity.start(_mActivity, bundle);
                }
            }
        });
        getVs_footer().setLayoutResource(R.layout.footer_btn);
        View view = getVs_footer().inflate();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                //避免软键盘没收起时跳入新界面BUG
                if (TextUtils.isEmpty(token)) {
                    ToastUtil.toast("数据或网络异常，请下拉刷新数据后重试");
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Bundle bundle = new Bundle();
                        bundle.putString("token", getArguments().getString("token", ""));

                        // 弹窗 -- 设置工作室信息页 - 产品 - 上传产品 ;
                        DialogUtil.getSelectDialog(_mActivity,
                                "视频",
                                "图片",
                                new DialogUtil.OnSelectDialogListener() {
                                    @Override
                                    public void onBtnOneClick(Dialog dialog) {
                                        dialog.dismiss();

                                        Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_uploadVideo);
                                        bundle.putString("token", token);
                                        bundle.putString("cateId", getArguments().getString("cateId"));
                                        ActionActivity.startForResult(ShopProductListFragment.this, bundle, 0x01);

                                    }

                                    @Override
                                    public void onBtnTwoClick(Dialog dialog) {
                                        dialog.dismiss();
                                        Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_uploadImage);
                                        bundle.putString("token", token);
                                        bundle.putString("cateId", getArguments().getString("cateId"));
                                        ActionActivity.startForResult(ShopProductListFragment.this, bundle, 0x01);

                                    }
                                });

                    }
                }, 500);

            }
        });


    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {

        /**
         *  产品列表  七牛 token
         */
        ShopApi.getProductsList(pageNum + "",
                new ResponseImpl<ShopProductsBean>() {
                    @Override
                    public void onSuccess(ShopProductsBean data) {

                        id = data.getData().getStore_id();
                        token = data.getData().getQiniu_token();
                        type = getProducionsType(id);
                        if (refresh) {
                            setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                        } else {
                            setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                        }
                    }


                    @Override
                    public void onEmptyData() {
                        super.onEmptyData();
                        getAdapter().setNewData(null);
                        getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void dataRes(int code, String data) {
                        // super.dataRes(code, data);
                        Log.e(TAG, "dataRes: ");
                        ShopProductsBean bean = JSON.parseObject(data, ShopProductsBean.class);
                        id = bean.getData().getStore_id();
                        token = bean.getData().getQiniu_token();
                        type = getProducionsType(id);
                        Logger.d("id=" + id + ",token=" + token);
                    }
                }, ShopProductsBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, final ShopProductsBean.DataBean.InfoBean item, int position) {
        ImageView iv_image = helper.getView(R.id.iv_image);
        ScreenUtil.ViewAdapter(_mActivity, iv_image, 16, 9, 20);
        final ImageView iv_delete = helper.getView(R.id.iv_delete);
        ImageView iv_play = helper.getView(R.id.iv_play);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_see_time = helper.getView(R.id.tv_see_time);//观看次数
        /*TextView tvShopWeight = helper.getView(R.id.tv_time_long);
        TextView tvBeforePrice = helper.getView(R.id.tv_before_price);*/
        TextView tv_time = helper.getView(R.id.tv_time);
        final String type = item.getType();
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtil.getSelectDialog(_mActivity, "编辑", "删除", new DialogUtil.OnSelectDialogListener() {
                    @Override
                    public void onBtnOneClick(Dialog dialog) {
                        dialog.dismiss();
                        //ToastUtil.toast("编辑");
                        if (TextUtils.isEmpty(token)) {
                            ToastUtil.toast("数据异常请退出重试");
                            return;
                        }
                        Bundle bundle;

                        if (type.equals("1")) {
                            //视频
                            bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Edit_vadieo);
                            bundle.putString("token", token);
                            bundle.putString("id", item.getCase_id());
                            bundle.putString("cateId", getArguments().getString("cateId"));
                            ActionActivity.startForResult(ShopProductListFragment.this, bundle, 0x02);
                        } else {

                            bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Edit_image);
                            bundle.putString("token", token);
                            bundle.putString("id", item.getCase_id());
                            bundle.putString("cateId", getArguments().getString("cateId"));
                            ActionActivity.startForResult(ShopProductListFragment.this, bundle, 0x02);
                        }


                    }

                    @Override
                    public void onBtnTwoClick(Dialog dialog) {
                        dialog.dismiss();
                        DialogUtil.getTowButtonDialog(_mActivity, "提示", "您确定要删除此产品么", "取消", "确定", new DialogUtil.OnDialogHandleListener() {
                            @Override
                            public void onCancleClick(SweetAlertDialog sDialog) {

                            }

                            @Override
                            public void onConfirmeClick(SweetAlertDialog sDialog) {
                                //删除产品
                                ShopApi.deleteProduct(item.getCase_id(), new ResponseImpl<Object>() {
                                    @Override
                                    public void onSuccess(Object data) {
                                        onRefresh();
                                    }
                                }, Object.class);

                            }
                        }).show();

                    }
                });

            }
        });
        ImageUtil.getInstance().loadImage(ShopProductListFragment.this, item.getImage_default(), iv_image);
        tv_name.setText(item.getTitle() + "");
        switch (item.getIs_verify()) {
            case "0":
                tv_see_time.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                tv_see_time.setText("待审核");
                break;
            case "1":
                tv_see_time.setText("审核通过");
                tv_see_time.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                break;
            case "-1":
                tv_see_time.setText("审核未通过");
                tv_see_time.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                break;
        }
        if (type.equals("1")) {
            //视频
            //iv_play.setVisibility(View.VISIBLE);
            tv_see_time.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.VISIBLE);
            tv_time.setText(item.getLength() + "");
        } else {
            //图片
            //iv_play.setVisibility(View.GONE);
            tv_see_time.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.GONE);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 0x01) {
            //上传产品成功刷新产品列表
            // ToastUtil.toast("刷新产品列表");
            onRefresh();
        }
        if (requestCode == requestCode && requestCode == 0x02) {
            //编辑产品成功
            onRefresh();
        }
    }


    public int getProducionsType(String s) {
        //1视频 2图片
        int type = -1;
        try {
            int shopType = Integer.parseInt(s);
            if (shopType == 1 || shopType == 7 || shopType == 8) {
                type = 2;
            } else {
                type = 1;
            }
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return type;
    }
}
