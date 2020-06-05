package com.hym.eshoplib.fragment.goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.GoodsDetailBean;

import java.util.List;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.tools.DividerGridItemDecoration;

/**
 * Created by 胡彦明 on 2018/1/18.
 * <p>
 * Description 规格
 * <p>
 * otherTips
 */

public class SpecListFragment extends BaseListFragment<GoodsDetailBean.DataBean.PropertylistBean> {
    List<GoodsDetailBean.DataBean.PropertylistBean> data;
    public static SpecListFragment newInstance(Bundle args) {
        SpecListFragment fragment = new SpecListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    int type=0;// 0普通，返回，x或者返回键， 1，加入购物车，2立即购买

    @Override
    public int getItemRestId() {
        return R.layout.item_spec;
    }

    @Override
    public void excuteLogic() {
        data= ((GoodsDetailBean) getArguments().getSerializable("data")).getData().getPropertylist();
        setLeft_iv(R.drawable.ic_close_x, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                type=0;
                onBackPressedSupport();
            }
        });
        getRefreshLayout().setEnabled(false);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getRv_list().getLayoutParams();
        layoutParams.setMargins(ScreenUtil.dip2px(_mActivity,10),0,ScreenUtil.dip2px(_mActivity,10),0);
        getRv_list().addItemDecoration(new DividerGridItemDecoration(_mActivity,R.drawable.shape_diver_white_width_6,false));
        getVs_header().setLayoutResource(R.layout.header_spec);
        View header=getVs_header().inflate();
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                        setSelect(position);
                        adapter.notifyDataSetChanged();


            }
        });
        getVs_footer().setLayoutResource(R.layout.layout_three_btn);
        View footer=getVs_footer().inflate();
        SuperTextView tv_1= (SuperTextView) footer.findViewById(R.id.btn_help);
        tv_1.setVisibility(View.GONE);
        SuperTextView tv_addshoppingcart= (SuperTextView) footer.findViewById(R.id.btn_add_shopping_cart);
        TextView tv_buyNow= (TextView) footer.findViewById(R.id.tv_buy_now);
        tv_addshoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=1;
                onBackPressedSupport();
            }
        });
        tv_buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=2;
                onBackPressedSupport();
            }
        });

    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        getAdapter().setNewData(data);
        dissMissDialog();


    }

    @Override
    public void bindData(BaseViewHolder helper, GoodsDetailBean.DataBean.PropertylistBean item, int position) {
        ImageView iv_select=helper.getView(R.id.iv_select);
        TextView tv_city=helper.getView(R.id.tv_city);
        tv_city.setText(item.getPtitle());
        if(item.getIs_checkd().equals("1")){
            tv_city.setBackgroundResource(R.drawable.shape_orangef9f1ef_solid_conner5);
            tv_city.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_orange_f2927e));
            iv_select.setVisibility(View.VISIBLE);
        }else {
            tv_city.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_585858));
            tv_city.setBackgroundResource(R.drawable.shape_grayf6f6f6_solid_conner5);
            iv_select.setVisibility(View.GONE);
        }

    }
    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(_mActivity, 3);
    }

    @Override
    public boolean onBackPressedSupport() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("spid",getSpid());
        bundle.putInt("type",type);
        intent.putExtras(bundle);
        _mActivity.setResult(Activity.RESULT_OK,intent);
        _mActivity.finish();
        return true;
    }

    private String getSpid() {
        String id="";
        for(int i=0;i<getAdapter().getData().size();i++){
             if(getAdapter().getData().get(i).getIs_checkd().equals("1")){
                 id=getAdapter().getData().get(i).getSpecification_id();
                 break;
             }
        }
        return id;

    }

    private void setSelect(int position){
        for(int i=0;i<getAdapter().getData().size();i++){
            if(position==i){
                getAdapter().getData().get(i).setIs_checkd("1");
            }else {
                getAdapter().getData().get(i).setIs_checkd("0");
            }
        }
    }
}
