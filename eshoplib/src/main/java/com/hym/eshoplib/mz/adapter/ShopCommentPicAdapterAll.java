package com.hym.eshoplib.mz.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;

import java.util.List;

import cn.hym.superlib.mz.utils.SizeUtils;

/**
 * 商品评论图片 - 全部评论 ;
 * 判断图片的数量
 * 会有三种布局显示方式
 */
public class ShopCommentPicAdapterAll extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShopCommentPicAdapterAll(List<String> data) {
        super(R.layout.mz_shop_comment_pic_all, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImageView iv = helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(item)
                .placeholder(R.drawable.picture_icon_placeholder)
                .into(iv);

//        List<String> data = getData();
//        if (data.size() == 1) {
//
//            changeIvSize(iv, 228);
//        } else if (data.size() == 2) {
//
//            changeIvSize(iv, 170);
//
//        } else if (data.size() >= 3) {
//
//        }
        //如果要分类型了

    }


    private void changeIvSize(ImageView iv, int value) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(SizeUtils.dp2px(value), SizeUtils.dp2px(value));
        iv.setLayoutParams(params);

    }
}
