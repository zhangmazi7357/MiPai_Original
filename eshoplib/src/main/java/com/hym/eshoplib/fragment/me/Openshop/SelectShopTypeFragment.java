package com.hym.eshoplib.fragment.me.Openshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopTypeBean;
import com.hym.eshoplib.http.shopapi.ShopApi;

import cn.hym.superlib.fragment.base.BaseListFragment;

/**
 * Created by 胡彦明 on 2018/8/29.
 * <p>
 * Description 选择工作室类型，对应首页的8个
 * <p>
 * otherTips
 */

public class SelectShopTypeFragment extends BaseListFragment<ShopTypeBean.DataBean>{
    ShopTypeBean.DataBean data;
    public static SelectShopTypeFragment newInstance(Bundle args) {
        SelectShopTypeFragment fragment = new SelectShopTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_check;
    }

    @Override
    public void excuteLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("工作室类别");
        data= (ShopTypeBean.DataBean) getArguments().getSerializable("data");
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

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        ShopApi.getShopType(new ResponseImpl<ShopTypeBean>() {
            @Override
            public void onSuccess(ShopTypeBean data) {
                getAdapter().setNewData(data.getData());
            }
        },ShopTypeBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, ShopTypeBean.DataBean item, int position) {
        ImageView iv_check=helper.getView(R.id.iv_select);
        if(data!=null&&data.getCategory_id().equals(item.getCategory_id())){
            iv_check.setVisibility(View.VISIBLE);
        }else {
            iv_check.setVisibility(View.GONE);
        }
        helper.setText(R.id.text,item.getCategory_name()+"");

    }
}
