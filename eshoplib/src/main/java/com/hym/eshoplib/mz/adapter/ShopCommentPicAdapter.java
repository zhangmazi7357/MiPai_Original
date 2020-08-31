package com.hym.eshoplib.mz.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;

import java.util.List;

/**
 * 商品评论图片 -
 */
public class ShopCommentPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShopCommentPicAdapter(@Nullable List<String> data) {
        super(R.layout.mz_shop_comment_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        ImageView view = helper.getView(R.id.pic);


        Glide.with(mContext)
                .load(item)
                .placeholder(R.drawable.picture_icon_placeholder)
                .into(view);


        helper.addOnClickListener(R.id.pic);

    }
}
