package com.hym.eshoplib.fragment.me.Openshop;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.CategoryListBean;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.fragment.shop.UpLoadImageFragment;
import com.hym.eshoplib.fragment.shop.UpLoadVideoFragment;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/9/3.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OpenShopStep3 extends BaseListFragment<ShopProductsBean.DataBean.InfoBean> {
    String id;
    TextView tv_type;
    TextView tv_required;
    String name;
    LinearLayout ll_type_container;
    ArrayList<PriceBean> priceBeen = new ArrayList<>();
    HashMap<String, String> maps = new HashMap<>();
    // int type=-1;//1 视频类型 2图片类型
    TextView tv_upload;
    TextView tv_tips;

    public static OpenShopStep3 newInstance(Bundle args) {
        OpenShopStep3 fragment = new OpenShopStep3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_shop_products;
    }

    @Override
    public void excuteLogic() {

        id = getArguments().getString("id", "");
        name = getArguments().getString("name", "");
        setShowProgressDialog(true);
        showBackButton();
        setTitle("申请开设工作室");
        setRight_tv("提交审核", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                if (maps.isEmpty()) {
                    ToastUtil.toast("请至少设置一种服务的价格");
                    return;
                }

                String json = getJson();
                Logger.d("json=" + json);
                ShopApi.addPrice(json, new ResponseImpl<Object>() {
                    @Override
                    public void onStart(int mark) {
                        setShowProgressDialog(true);
                        super.onStart(mark);
                    }

                    @Override
                    public void onSuccess(Object data) {
//                        ToastUtil.toast("提交成功请耐心等待审核，我们会第一时间通知您审核结果");
//                        _mActivity.finish();
                        start(OpenSuccessFragment.newInstance(new Bundle()));

                    }

                    @Override
                    public void onFinish(int mark) {
                        super.onFinish(mark);
                        setShowProgressDialog(false);
                    }
                }, Object.class);


            }
        });
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_openshop_step3, null, false);
        tv_tips = header.findViewById(R.id.tv_tips);
        ll_type_container = header.findViewById(R.id.ll_type_container);
        tv_type = header.findViewById(R.id.tv_type);
        tv_required = header.findViewById(R.id.tv_required);
        //type=getProducionsType(id);
        tv_type.setText(name);
//        if(type==1){
//            //视频类型
//            //tv_required.setText("(上传AVI/MP4文件且不超过500M)");
//        }else if(type==2){
//            //图片类型
//           // tv_required.setText("(上传PNG/JPG文件且不超过3M)");
//        }
        getAdapter().addHeaderView(header);
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_upload_product, null, false);
        tv_upload = footer.findViewById(R.id.tv_upload);
        tv_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                //避免软键盘没收起时跳入新界面BUG
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Bundle bundle = new Bundle();
                        bundle.putString("token", getArguments().getString("token", ""));
                        //catid
                        bundle.putString("cateId", id);

                        DialogUtil.getSelectDialog(_mActivity,
                                "视频",
                                "图片",
                                new DialogUtil.OnSelectDialogListener() {
                                    @Override
                                    public void onBtnOneClick(Dialog dialog) {
                                        dialog.dismiss();
                                        startForResult(UpLoadVideoFragment.newInstance(bundle), 0x01);

                                    }

                                    @Override
                                    public void onBtnTwoClick(Dialog dialog) {
                                        dialog.dismiss();
                                        startForResult(UpLoadImageFragment.newInstance(bundle), 0x01);

                                    }
                                });

//                        if(type==1){
//                            //上传视频
//                            startForResult(UpLoadVadieoFragment.newInstance(bundle),0x01);
//                        }else if(type==2){
//                            //上传图片
//                            startForResult(UpLoadImageFragment.newInstance(bundle),0x01);
//                        }
                    }
                }, 500);

            }
        });
        getAdapter().addFooterView(footer);

        //获取分类
        GoodsApi.getCategory(id, new ResponseImpl<CategoryListBean>() {
            @Override
            public void onSuccess(final CategoryListBean data) {
                ll_type_container.removeAllViews();
                for (int i = 0; i < data.getData().size(); i++) {
                    View item = LayoutInflater.from(_mActivity).inflate(R.layout.item_input_price, null, false);
                    TextView tv_title = item.findViewById(R.id.tv_title);
                    ClearEditText et_input = item.findViewById(R.id.et_intput);
                    TextView tv_sub_title = item.findViewById(R.id.tv_sub_title);
                    tv_title.setText(data.getData().get(i).getCategory_name());
                    if (!TextUtils.isEmpty(data.getData().get(i).getMemo())) {
                        tv_sub_title.setText(data.getData().get(i).getMemo());
                        tv_sub_title.setVisibility(View.VISIBLE);
                    } else {
                        tv_sub_title.setVisibility(View.GONE);
                    }
                    final String id = data.getData().get(i).getCategory_id();
                    et_input.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            maps.put(id, s.toString());

                        }
                    });
                    ll_type_container.addView(item);
                }

            }
        }, CategoryListBean.class);
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
        //设置提示
        switch (id) {
            case "1":
                //文案策划
                // tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认收货");
                //tv_life_circle.setText("立即预约 —> 接受预约 —> 付款 —> 确认拍摄结束 —> 确认收货");
                break;
            case "2":
                //导演
                tv_tips.setText("（以5分钟为单位）");
                break;
            case "3":
                //摄像师
                tv_tips.setText("(至少添一项,以天为单位)");
                break;
            case "4":
                //剪辑师
                tv_tips.setText("（至少添一项,以5分钟为单位）");
                break;
            case "5":
                //影视团队
                tv_tips.setText("（至少添一项,以5分钟为单位）");

                break;
            case "6":
                //三维动画
                tv_tips.setText("（以秒钟为单位）");
                break;
            case "7":
                //平面设计
                tv_tips.setText("（至少添一项）");
                break;
            case "8":
                //图片摄影
                tv_tips.setText("(至少添一项,价格以天为单位)");
                break;
            case "46":
                tv_tips.setText("（至少添一项,价格以天为单位）");
                break;
            case "40":
                tv_tips.setText("（至少添一项,价格以天为单位）");
                break;
        }


    }

    private String getJson() {
        priceBeen.clear();
        String json = "";
        Iterator<String> it = maps.keySet().iterator();
        while (it.hasNext()) {
            String id = it.next();
            String price = maps.get(id);
            PriceBean bean = new PriceBean(id, price);
            priceBeen.add(bean);
        }
        return json = JSON.toJSONString(priceBeen);
    }


    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        if (TextUtils.isEmpty(id)) {
            dissMissDialog();
            getRefreshLayout().setRefreshing(false);
            return;
        }
        ShopApi.getProductsList(pageNum + "", new ResponseImpl<ShopProductsBean>() {
            @Override
            public void onSuccess(ShopProductsBean data) {
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
        }, ShopProductsBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, final ShopProductsBean.DataBean.InfoBean item, final int position) {
        ImageView iv_image = helper.getView(R.id.iv_image);
        final ImageView iv_delete = helper.getView(R.id.iv_delete);
        ImageView iv_play = helper.getView(R.id.iv_play);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_see_time = helper.getView(R.id.tv_see_time);//观看次数
        TextView tv_time = helper.getView(R.id.tv_time);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogManager.getInstance().initSimpleDialog(_mActivity, "提示", "您确定要删除此产品么",
                        "取消", "确定", new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();
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
        ImageUtil.getInstance().loadImage(OpenShopStep3.this, item.getImage_default(), iv_image);
        tv_name.setText(item.getTitle() + "");
        String type = item.getType();
        if (type.equals("1")) {
            //视频
            //iv_play.setVisibility(View.VISIBLE);
            //tv_see_time.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.VISIBLE);
            tv_time.setText(item.getLength() + "");
        } else {
            //图片
            //iv_play.setVisibility(View.GONE);
            tv_see_time.setVisibility(View.GONE);
            tv_time.setVisibility(View.GONE);
        }


    }

    //    public int getProducionsType(String s){
//        //1视频 2图片
//        int type=-1;
//        try{
//            int shopType=Integer.parseInt(s);
//            if(shopType==1||shopType==7||shopType==8){
//                type=2;
//            }else {
//                type=1;
//            }
//        }catch (Exception e){
//            Logger.d(e.toString());
//        }
//        return type;
//    }
    private class PriceBean {
        public PriceBean(String category_id, String price) {
            this.category_id = category_id;
            this.price = price;
        }

        String category_id;
        String price;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0x01) {
            //上传产品成功刷新产品列表
            // ToastUtil.toast("刷新产品列表");
            onRefresh();
        }
    }
}
