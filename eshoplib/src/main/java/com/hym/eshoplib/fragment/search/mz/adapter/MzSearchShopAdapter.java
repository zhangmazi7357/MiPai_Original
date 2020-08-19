package com.hym.eshoplib.fragment.search.mz.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.fragment.search.mz.model.MzSearchShopModel;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 搜索 工作室
 */
public class MzSearchShopAdapter extends BaseQuickAdapter<MzSearchShopModel.DataBean.InfoBean, BaseViewHolder> {


    public MzSearchShopAdapter(@Nullable List data) {
        super(R.layout.item_shop_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MzSearchShopModel.DataBean.InfoBean item) {

        // 头像
        ImageView headerView = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getLogo()).into(headerView);

        // 名称 ;
        if (!TextUtils.isEmpty(item.getStore_name())) {
            helper.setText(R.id.tv_title, item.getStore_name());
        }

        // 简介
        if (!TextUtils.isEmpty(item.getDescription())) {
            helper.setText(R.id.tv_des, "个人简介 \n" + item.getDescription());
        }

        //成交量
        helper.setText(R.id.tv_comments, "成交量: " + item.getOrder_count());

        // 星星
        MaterialRatingBar ratingBar = helper.getView(R.id.ratingbar);
        ratingBar.setVisibility(View.GONE);

    }
}
