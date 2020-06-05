package com.hym.eshoplib.fragment.me.Openshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.IndustryListBean;
import com.hym.eshoplib.http.shopapi.ShopApi;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/29.
 * <p>
 * Description 选择行业 有二级分类
 * <p>
 * otherTips
 */

public class SelectIndustryFragment extends BaseListFragment<IndustryListBean.DataBean>{
    String category_id;
    String category_name;
    int current_position=-1;
    String type;
    public static SelectIndustryFragment newInstance(Bundle args) {
        SelectIndustryFragment fragment = new SelectIndustryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_check;
    }

    @Override
    public void excuteLogic() {
        type=getArguments().getString("type");//分类类型-必须（1：行业，2：视频，3：图片
        category_id=getArguments().getString("id","");
        category_name=getArguments().getString("name","");
        if(TextUtils.isEmpty(category_name)){
            switch (type){
                case "1":
                    setTitle("行业类型");
                    break;
                case "2":
                    setTitle("视频类型");
                    break;
                case "3":
                    setTitle("图片类型");
                    break;
            }
        }else {
            setTitle(category_name);
        }
        setShowProgressDialog(true);
        showBackButton();
        //setTitle("工作室类别");
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                current_position=position;
                getAdapter().notifyDataSetChanged();


            }
        });
        setRight_tv("完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_position==-1){
                    ToastUtil.toast("请选择一个分类");
                    return;
                }
                Bundle bundle=new Bundle();
                String id=getAdapter().getData().get(current_position).getCategory_id();
                String name=getAdapter().getData().get(current_position).getCategory_name();
                bundle.putString("id",id);
                bundle.putString("name",name);
                bundle.putString("type",type);
                if(TextUtils.isEmpty(category_id)){
                    //说明是一级，则进入下一级，一共二级
                    start(SelectIndustryFragment.newInstance(bundle));

                }else {
                    //则返回
                    Intent intent=new Intent();
                    intent.putExtras(bundle);
                    _mActivity.setResult(Activity.RESULT_OK,intent);
                    _mActivity.finish();
                }
            }
        });

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        ShopApi.getIndustryList(category_id, type, new ResponseImpl<IndustryListBean>() {
            @Override
            public void onSuccess(IndustryListBean data) {
                getAdapter().setNewData(data.getData());
            }
        },IndustryListBean.class);
    }

    @Override
    public void bindData(BaseViewHolder helper, IndustryListBean.DataBean item, int position) {
        ImageView iv_check=helper.getView(R.id.iv_select);
        if(current_position==position){
            iv_check.setVisibility(View.VISIBLE);
        }else {
            iv_check.setVisibility(View.GONE);
        }
        helper.setText(R.id.text,item.getCategory_name()+"");
    }


}
