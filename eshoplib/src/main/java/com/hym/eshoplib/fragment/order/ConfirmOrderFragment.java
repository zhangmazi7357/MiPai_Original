package com.hym.eshoplib.fragment.order;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.address.AddressListBean;
import com.hym.eshoplib.bean.invoice.SelectInvoiceBean;
import com.hym.eshoplib.bean.order.CreateOrderBean;
import com.hym.eshoplib.bean.order.GetTotalCountBean;
import com.hym.eshoplib.fragment.address.AddressListFragment;
import com.hym.eshoplib.http.address.AddressApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/3/27.
 * <p>
 * Description 确认订单
 * <p>
 * otherTips
 */
@Deprecated
public class ConfirmOrderFragment extends BaseListFragment<GetTotalCountBean.DataBean.GoodsBean> {
    GetTotalCountBean data;
    TextView tv_price, tv_confirm, tv_select_all, tvSelectAddress;
    TextView tv_name, tv_phone, tv_content;
    RelativeLayout rl_select_address;
    String address_id;
    View footer;
    String specification_id;
    HashMap<Integer, SelectInvoiceBean> map = new HashMap<>();
    HashMap<Integer,SuperTextView>tv_map=new HashMap<>();
    public static ConfirmOrderFragment newInstance(Bundle args) {
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_confirm_order;
    }

    @Override
    public void excuteLogic() {
        SoftHideKeyBoardUtil.assistActivity(_mActivity, new SoftHideKeyBoardUtil.onSoftChangeListener() {
            @Override
            public void onSoftChange(boolean show) {
                if (show) {
                    footer.setVisibility(View.GONE);
                } else {
                    footer.setVisibility(View.VISIBLE);
                }
            }
        });
        data = (GetTotalCountBean) getArguments().getSerializable("data");
        specification_id = getArguments().getString("id", "");
        getRefreshLayout().setEnabled(false);
        showBackButton();
        setTitle(R.string.ConfirmOrder);
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_confirm_order, null, false);
        tvSelectAddress = (TextView) header.findViewById(R.id.tv_select_address);
        rl_select_address = (RelativeLayout) header.findViewById(R.id.rl_select_address);
        tv_name = (TextView) header.findViewById(R.id.tv_name);
        tv_phone = (TextView) header.findViewById(R.id.tv_phone);
        tv_content = (TextView) header.findViewById(R.id.tv_content);
        tvSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSelect", true);
                startForResult(AddressListFragment.newInstance(bundle), 0x01);
            }
        });
        rl_select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSelect", true);
                startForResult(AddressListFragment.newInstance(bundle), 0x01);
            }
        });
        getAdapter().addHeaderView(header);
        getVs_footer().setLayoutResource(R.layout.layout_shoppingcart_bottom);
        footer = getVs_footer().inflate();
        ImageView iv_select_all = (ImageView) footer.findViewById(R.id.iv_select_all);
        iv_select_all.setVisibility(View.GONE);
        tv_select_all = (TextView) footer.findViewById(R.id.tv_select_all);
        tv_select_all.setVisibility(View.GONE);
        tv_price = (TextView) footer.findViewById(R.id.tv_price);
        tv_confirm = (TextView) footer.findViewById(R.id.tv_go_pay);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("提交订单");
                if (data == null) {
                    return;
                }
                if (TextUtils.isEmpty(address_id)) {
                    ToastUtil.toast("请选择收货地址");
                    return;
                }
                String c_type = getArguments().getString("c_type");
//                if (c_type.equals("1")) {
//                    //立即购买
//
//                } else {
//                    //购物车
//                }
                String invoice = "";
                ArrayList<SelectInvoiceBean> list = new ArrayList<SelectInvoiceBean>();
                for (int i = 0; i < getAdapter().getData().size(); i++) {
                    list.add(map.get(i));
                }
                Logger.d(list);
                invoice = JSON.toJSONString(list);
                Logger.d(invoice);
                OrderApi.createOrder(_mActivity, address_id, c_type, specification_id,
                        data.getData().getGoods().size() + "", invoice, "", "", new ResponseImpl<CreateOrderBean>() {
                            @Override
                            public void onSuccess(CreateOrderBean bean) {
                                //生成订单
                                Bundle bundle = new Bundle();
                                bundle.putDouble("needPay",Double.parseDouble(data.getData().getPayment_amount()));
                               // bundle.putString("address_id", address_id);
                               // bundle.putSerializable("data", data);
                               // bundle.putString("c_type", getArguments().getString("c_type"));
                                bundle.putString("id",bean.getData().getChild_order_number());
                                bundle.putString("order_id", bean.getData().getChild_order_id());
                                start(SelectPaymentFragment.newInstance(bundle));
                            }
                        }, CreateOrderBean.class);


            }
        });
        tv_confirm.setText(R.string.SubmitOrder);
        getdefaultAddress();

    }
    //获取默认地址
    private void getdefaultAddress() {
        AddressApi.getAddressList(_mActivity,"1", new ResponseImpl<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean data) {
                List<AddressListBean.DataBean.InfoBean> dataBean = data.getData().getInfo();
                if (dataBean != null && dataBean.size() > 0) {
                    AddressListBean.DataBean.InfoBean bean = dataBean.get(0);
                    setAddress(bean);
                }


            }


        }, AddressListBean.class);

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        if (data != null) {
            getAdapter().setNewData(data.getData().getGoods());
            tv_price.setText(data.getData().getTotal_amount() + " RMB");
        }
        getRefreshLayout().setRefreshing(false);
        dissMissDialog();

    }

    @Override
    public void bindData(BaseViewHolder helper, GetTotalCountBean.DataBean.GoodsBean item, final int position) {
        helper.getView(R.id.et_buyer_message).clearFocus();
        ImageView iv_shop_icon = helper.getView(R.id.iv_shop);
        ImageUtil.getInstance().loadImage(ConfirmOrderFragment.this, item.getStore_image_default(), iv_shop_icon);
        helper.setText(R.id.tv_shop_name, item.getStore_name() + "");
        SuperTextView tv_freight = helper.getView(R.id.tv_freight);
        tv_freight.setRightString(item.getFreight_amount() + "");
        SuperTextView tv_price = helper.getView(R.id.tv_price);
        tv_price.setRightString(item.getTotal() + "RMB");
        LinearLayout ll_goods = helper.getView(R.id.ll_goods);
        ll_goods.removeAllViews();
        //设置店铺商品
        for (GetTotalCountBean.DataBean.GoodsBean.StoreGoodsBean bean : item.getStore_goods()) {
            View view = LayoutInflater.from(_mActivity).inflate(R.layout.item_order_goods, null, false);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
            TextView tv_goods_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
            ImageUtil.getInstance().loadImage(ConfirmOrderFragment.this, bean.getImage_default(), iv_icon);
            tv_name.setText(bean.getName() + "");
            tv_type.setText(getResources().getString(R.string.Specification)+bean.getSpecdetail() );
            tv_goods_price.setText(bean.getPrice() + " RMB");
            tv_count.setText("x" + bean.getBuy_num());
            ll_goods.addView(view);

        }
        SuperTextView tvInvoice = helper.getView(R.id.tv_invoice);
        tvInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("发票");
                Bundle bundle=new Bundle();
                bundle.putInt("position",position);
                bundle.putSerializable("data",map.get(position));
                startForResult(SelectInvoiceFragment.newInstance(bundle),0x02);
            }
        });
        tv_map.put(position,tvInvoice);
        SelectInvoiceBean bean = new SelectInvoiceBean();
        bean.setInvoice("1");
        bean.setInvoice_type("1");
        bean.setStore_id(item.getStoreid());
        map.put(position, bean);
        final EditText et_message = helper.getView(R.id.et_buyer_message);
        et_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.get(position).setMemo(s.toString() + "");

            }
        });


    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //选择地址
                case 0x01:
                    if (data != null && data.getSerializable("data") != null) {
                        AddressListBean.DataBean.InfoBean bean = (AddressListBean.DataBean.InfoBean) data.getSerializable("data");
                        setAddress(bean);
                    }
                    break;
                case 0x02:
                    //选择发票
                    if(data!=null &&data.getSerializable("data")!=null){
                        SelectInvoiceBean bean= (SelectInvoiceBean) data.getSerializable("data");
                        int position=data.getInt("position");
                        map.put(position,bean);
                        if(bean.getInvoice().equals("1")){
                            if(bean.getInvoice_type().equals("1")){
                                tv_map.get(position).setRightString("个人");
                            }else {
                                tv_map.get(position).setRightString("公司");
                            }
                        }else if(bean.getInvoice().equals("2")){
                            tv_map.get(position).setRightString("增值税发票");
                        }
                    }
                    break;
            }
        }
    }

    private void setAddress(AddressListBean.DataBean.InfoBean bean) {
        address_id = bean.getConsignee_id();
        tvSelectAddress.setVisibility(View.GONE);
        rl_select_address.setVisibility(View.VISIBLE);
        tv_name.setText(bean.getName() + "");
        tv_phone.setText(bean.getMobile() + "");
        tv_content.setText(bean.getAddress() + "");
    }
}
