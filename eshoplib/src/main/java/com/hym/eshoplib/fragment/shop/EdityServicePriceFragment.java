package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.CategoryListBean;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/9/3.
 * <p>
 * Description 编辑服务价格
 * <p>
 * otherTips
 */

public class EdityServicePriceFragment extends BaseListFragment<ShopProductsBean.DataBean.InfoBean>{
    String id;
    TextView tv_required;
    LinearLayout ll_type_container;
    ArrayList<PriceBean>priceBeen=new ArrayList<>();
    HashMap<String,String>maps=new HashMap<>();
    int type=-1;//1 视频类型 2图片类型
    TextView tv_upload;
    ShopDetailBean data;
    RequiredTextView tv_type;
    TextView tv_tips;
    public static EdityServicePriceFragment newInstance(Bundle args) {
        EdityServicePriceFragment fragment = new EdityServicePriceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_shop_products;
    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void excuteLogic() {
        getRefreshLayout().setEnabled(false);
        //id=getArguments().getString("id","");
        data= (ShopDetailBean) getArguments().getSerializable("data");
        setShowProgressDialog(true);
        showBackButton();
        setTitle("服务价格");
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maps.isEmpty()){
                    ToastUtil.toast("请输入服务金额");
                    return;
                }
                String json=getJson();
                Logger.d("json="+json);
                if(count==0){
                    ToastUtil.toast("请至少设置一项服务价格");
                    return;
                }
                ShopApi.addPrice(json, new ResponseImpl<Object>() {
                    @Override
                    public void onStart(int mark) {
                        setShowProgressDialog(true);
                        super.onStart(mark);
                    }
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("修改成功");
                        _mActivity.finish();

                    }
                    @Override
                    public void onFinish(int mark) {
                        super.onFinish(mark);
                        setShowProgressDialog(false);
                    }
                },Object.class);
            }
        });
        View header= LayoutInflater.from(_mActivity).inflate(R.layout.header_openshop_step3,null,false);
        tv_tips=header.findViewById(R.id.tv_tips);
        LinearLayout ll_product=header.findViewById(R.id.ll_product);
        ll_product.setVisibility(View.GONE);
        ll_type_container=header.findViewById(R.id.ll_type_container);
         tv_type=header.findViewById(R.id.tv_type);
         getAdapter().addHeaderView(header);
        //设置提示
        switch (data.getData().getBase().getCategory_id()){
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
                tv_tips.setText("(至少添一项,以天为单位)");
                break;
            case "46":
                tv_tips.setText("（以天为单位）");
                break;
            case "40":
                tv_tips.setText("（以天为单位）");
                break;
        }
        //获取分类
        setData();

    }

    private void setData() {
        if(data==null){
            return;
        }
        GoodsApi.GetStoreCategory(data.getData().getBase().getStore_id(), new ResponseImpl<CategoryListBean>() {
            @Override
            public void onSuccess(final CategoryListBean data) {
                tv_type.setText(EdityServicePriceFragment.this.data.getData().getBase().getCategory_name());
                ll_type_container.removeAllViews();
                for(int i=0;i<data.getData().size();i++){
                    View item=LayoutInflater.from(_mActivity).inflate(R.layout.item_input_price,null,false);
                    TextView tv_title=item.findViewById(R.id.tv_title);
                    ClearEditText et_input=item.findViewById(R.id.et_intput);
                    TextView tv_sub_title=item.findViewById(R.id.tv_sub_title);
                    tv_title.setText(data.getData().get(i).getCategory_name());
                    String price=data.getData().get(i).getPrice();
                    if(!TextUtils.isEmpty(data.getData().get(i).getMemo())){
                        tv_sub_title.setText(data.getData().get(i).getMemo());
                        tv_sub_title.setVisibility(View.VISIBLE);
                    }else {
                        tv_sub_title.setVisibility(View.GONE);
                    }
                    final String id=data.getData().get(i).getCategory_id();
                    maps.put(id,price);
                    et_input.setText(price);
                    et_input.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            maps.put(id,s.toString());

                        }
                    });
                    ll_type_container.addView(item);
                }

            }
        }, CategoryListBean.class);
      }

    int count;
    private String getJson() {
        priceBeen.clear();
        String json="";
        Iterator<String>it=maps.keySet().iterator();
        count=maps.size();
        while (it.hasNext()){
            String id=it.next();
            String price=maps.get(id);
            if(TextUtils.isEmpty(price)){
                count-=1;
            }
            PriceBean bean=new PriceBean(id,price);
            priceBeen.add(bean);
        }
        return json=JSON.toJSONString(priceBeen);
    }



    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        dissMissDialog();
        getRefreshLayout().setRefreshing(false);

    }

    @Override
    public void bindData(BaseViewHolder helper, final ShopProductsBean.DataBean.InfoBean item, final int position) {
        ImageView iv_image=helper.getView(R.id.iv_image);
        final ImageView iv_delete=helper.getView(R.id.iv_delete);
        ImageView iv_play=helper.getView(R.id.iv_play);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_see_time=helper.getView(R.id.tv_see_time);//观看次数
        TextView tv_time=helper.getView(R.id.tv_time);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        },Object.class);

                    }
                }).show();
            }
        });
        ImageUtil.getInstance().loadImage(EdityServicePriceFragment.this,item.getImage_default(),iv_image);
        tv_name.setText(item.getTitle()+"");
        String type=item.getType();
        if(type.equals("1")){
             //视频
            //iv_play.setVisibility(View.VISIBLE);
            //tv_see_time.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.VISIBLE);
            tv_time.setText(item.getLength()+"");
        }else {
            //图片
            //iv_play.setVisibility(View.GONE);
            tv_see_time.setVisibility(View.GONE);
            tv_time.setVisibility(View.GONE);
        }



    }
    private class PriceBean{
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
}
