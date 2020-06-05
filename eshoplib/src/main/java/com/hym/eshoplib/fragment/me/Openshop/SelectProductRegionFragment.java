package com.hym.eshoplib.fragment.me.Openshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ProductRegionBean;
import com.hym.eshoplib.http.shopapi.ShopApi;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/9/13.
 * <p>
 * Description 选择产品区域
 * <p>
 * otherTips
 */

public class SelectProductRegionFragment extends BaseListFragment<ProductRegionBean.DataBean>{
    String region_id;
    public static SelectProductRegionFragment newInstance(Bundle args) {
        SelectProductRegionFragment fragment = new SelectProductRegionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    int current_position=-1;
    @Override
    public int getItemRestId() {
        return R.layout.item_check;
    }

    @Override
    public void excuteLogic() {
        region_id=getArguments().getString("id","");
        showBackButton();
        setTitle("所在地区");
        setRight_tv("完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_position==-1){
                    ToastUtil.toast("请选择所在地区");
                    return;
                }
                Bundle bundle=new Bundle();
                String id=getAdapter().getData().get(current_position).getRegion_id();
                String name=getAdapter().getData().get(current_position).getRegion_name();
                bundle.putString("id",id);
                bundle.putString("name",name);
                Intent intent=new Intent();
                intent.putExtras(bundle);
                _mActivity.setResult(Activity.RESULT_OK,intent);
                _mActivity.finish();

            }
        });
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                current_position=position;
                getAdapter().notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        ShopApi.getProductRegion(new ResponseImpl<ProductRegionBean>() {
            @Override
            public void onSuccess(ProductRegionBean data) {
                getAdapter().setNewData(data.getData());

            }
        },ProductRegionBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, ProductRegionBean.DataBean item, int position) {
        ImageView iv_check=helper.getView(R.id.iv_select);
        if(current_position==position||item.getRegion_id().equals(region_id)){
            iv_check.setVisibility(View.VISIBLE);
        }else {
            iv_check.setVisibility(View.GONE);
        }
        helper.setText(R.id.text,item.getRegion_name()+"");
    }
}
