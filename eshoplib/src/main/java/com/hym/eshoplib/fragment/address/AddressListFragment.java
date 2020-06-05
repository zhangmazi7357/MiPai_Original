package com.hym.eshoplib.fragment.address;

import android.app.Dialog;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.address.AddressListBean;
import com.hym.eshoplib.http.address.AddressApi;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/3/23.
 * <p>
 * Description 我的收货地址
 * <p>
 * otherTips
 */

public class AddressListFragment extends BaseListFragment<AddressListBean.DataBean.InfoBean>{
    boolean isSelect=false;//true 为选择地址，则返回地址数据
    public static AddressListFragment newInstance(Bundle args) {
        AddressListFragment fragment = new AddressListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_recieve_address;
    }

    @Override
    public void excuteLogic() {
        isSelect=getArguments().getBoolean("isSelect",false);
        showBackButton();
        setTitle(R.string.Deliveryaddress);
        if(isSelect){
            getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("data",getAdapter().getData().get(position));
                    setFragmentResult(RESULT_OK,bundle);
                    pop();

                }
            });
        }
        getVs_footer().setLayoutResource(R.layout.layout_three_btn);
        View footer=getVs_footer().inflate();
        SuperTextView tv_help= (SuperTextView) footer.findViewById(R.id.btn_help);
        SuperTextView tv_add= (SuperTextView) footer.findViewById(R.id.btn_add_shopping_cart);
        TextView tv_buy_now= (TextView) footer.findViewById(R.id.tv_buy_now);
        tv_help.setVisibility(View.GONE);
        tv_add.setVisibility(View.GONE);
        tv_buy_now.setText(R.string.Addshippingaddress);
        tv_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加收货地址
                startForResult(AddAddressFragment.newInstance(new Bundle()),0x01);
            }
        });

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        AddressApi.getAddressList(_mActivity, "", new ResponseImpl<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean data) {
                getAdapter().setNewData(data.getData().getInfo());
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        },AddressListBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, final AddressListBean.DataBean.InfoBean item, final int position) {
        TextView tv_isdefault=helper.getView(R.id.tv_default);
        if(item.getIs_default().equals("1")){
            tv_isdefault.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
            tv_isdefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_checked,0,0,0);
        }else {
            tv_isdefault.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
            tv_isdefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
        }
        tv_isdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("切换默认");
                if(!item.getIs_default().equals("1")){
                    AddressApi.addOrEditeAddress(_mActivity,"1",item.getConsignee_id(), item.getName(), item.getMobile(), item.getAddress(), true, new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            for(int i=0;i<getAdapter().getData().size();i++){
                                if(i==position){
                                    getAdapter().getData().get(i).setIs_default("1");
                                }else{
                                    getAdapter().getData().get(i).setIs_default("0");
                                }


                            }
                            getAdapter().notifyDataSetChanged();
                        }
                    },Object.class);
                }
            }
        });
        helper.setText(R.id.tv_name,getResources().getString(R.string.Receiver)+"："+item.getName()+"");
        helper.setText(R.id.tv_phone,item.getMobile()+"");
        helper.setText(R.id.tv_detail,item.getAddress()+"");
        ImageView iv_edit=helper.getView(R.id.iv_edit_address);
        ImageView iv_delete=helper.getView(R.id.iv_delete_address);
        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",item);
                bundle.putBoolean("isEdit",true);
                startForResult(AddAddressFragment.newInstance(bundle),0x02);
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id=item.getConsignee_id();
                String confirm = getResources().getString(R.string.Confirm);
                String cancle = getResources().getString(R.string.Cancel);
                Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "Delete", "Do you want to Delete this Address?", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                    @Override
                    public void onCancleClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();

                    }

                    @Override
                    public void onConfirmeClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        AddressApi.deleteAddress(_mActivity, id, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                getAdapter().remove(position);
                            }
                        },Object.class);

                    }
                });
                pDialog.show();
            }

        });


    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        onRefresh();
    }
}
