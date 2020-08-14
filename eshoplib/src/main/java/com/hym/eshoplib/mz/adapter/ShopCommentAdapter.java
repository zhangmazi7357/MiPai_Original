package com.hym.eshoplib.mz.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.mz.shopdetail.MzShopCommentBean;

import java.util.List;

import cn.hym.superlib.mz.utils.MzStringUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


/**
 * 商品评论 ;
 */
public class ShopCommentAdapter extends BaseQuickAdapter<MzShopCommentBean.DataBean.InfoBean,
        BaseViewHolder> {

    public ShopCommentAdapter(@Nullable List<MzShopCommentBean.DataBean.InfoBean> data) {
        super(R.layout.mz_adapter_shop_comment, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MzShopCommentBean.DataBean.InfoBean item) {

        helper.setText(R.id.username, MzStringUtil.hideUserName(item.getNickname()));

        int position = helper.getLayoutPosition();
        Log.e(TAG, "position = " + position);
        int itemCount = getItemCount();
        View divider = helper.getView(R.id.divider);
        divider.setVisibility(View.VISIBLE);
        if (position == itemCount - 1) {
            divider.setVisibility(View.GONE);
        }


        ImageView headerView = helper.getView(R.id.header);
        Glide.with(mContext).load(item.getAvatar()).into(headerView);
        helper.setText(R.id.commentContent, item.getContent());

        String score = item.getScore();
        MaterialRatingBar ratingBar = helper.getView(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(score));

        RecyclerView commentImgRv = helper.getView(R.id.commentImgRv);
        String images = item.getImages();


    }


}
