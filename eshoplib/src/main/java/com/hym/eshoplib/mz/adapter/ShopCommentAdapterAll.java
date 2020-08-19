package com.hym.eshoplib.mz.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.mz.shopdetail.MzShopCommentBean;

import java.util.Arrays;
import java.util.List;

import cn.hym.superlib.mz.utils.MzStringUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


/**
 * 全部 - 商品评论 ;
 */
public class ShopCommentAdapterAll extends BaseQuickAdapter<MzShopCommentBean.DataBean.InfoBean,
        BaseViewHolder> {


    private int isReplay = 0;

    public void setIsReplay(int isReplay) {
        this.isReplay = isReplay;

    }

    public ShopCommentAdapterAll(@Nullable List<MzShopCommentBean.DataBean.InfoBean> data) {
        super(R.layout.mz_adapter_shop_comment_all, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MzShopCommentBean.DataBean.InfoBean item) {

        helper.setText(R.id.username, MzStringUtil.hideUserName(item.getNickname()));

        helper.setText(R.id.date, MzStringUtil.splitTime(item.getCtime()));


        // 分割线 ;
        int position = helper.getLayoutPosition();
        int itemCount = getItemCount();
        View divider = helper.getView(R.id.divider);
        divider.setVisibility(View.VISIBLE);
        if (position == itemCount - 1) {
            divider.setVisibility(View.GONE);
        }


        ImageView headerView = helper.getView(R.id.header);
        Glide.with(mContext).load(item.getAvatar()).into(headerView);
        helper.setText(R.id.commentContent, item.getContent());


        MaterialRatingBar ratingBar = helper.getView(R.id.ratingBar);
        String score = item.getScore();
        if (!TextUtils.isEmpty(score)) {
            ratingBar.setRating(Float.parseFloat(score));
        }

        // 图片
        RecyclerView commentImgRv = helper.getView(R.id.commentImgRv);
        commentImgRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        ShopCommentPicAdapterAll picAdapterAll = new ShopCommentPicAdapterAll(null);

        commentImgRv.setAdapter(picAdapterAll);


        String images = item.getImages();
        String[] strings = MzStringUtil.splitComma(images);

        if (strings != null && strings.length > 0) {
            commentImgRv.setVisibility(View.VISIBLE);
            List<String> imgList = Arrays.asList(strings);
            picAdapterAll.setNewData(imgList);
        } else {
            commentImgRv.setVisibility(View.GONE);
        }


        // 回复
        List<MzShopCommentBean.DataBean.InfoBean.ReplayBean> replay = item.getReplay();
        ConstraintLayout replayContainer = helper.getView(R.id.container_replay);
        if (replay != null && replay.size() > 0) {
            replayContainer.setVisibility(View.VISIBLE);
            MzShopCommentBean.DataBean.InfoBean.ReplayBean replayBean = replay.get(0);
            String content = replayBean.getContent();
            helper.setText(R.id.tv_replay, content);

        } else {
            replayContainer.setVisibility(View.GONE);

        }

        ImageView ivReplay = helper.getView(R.id.iv_replay);

        // isReplay = 0 不可以回复
        if (isReplay == 0) {
            ivReplay.setVisibility(View.GONE);

        } else {

            // 如果已经回复过，就不要再回复了。
            if (replay.size() == 0) {
                ivReplay.setVisibility(View.VISIBLE);
            }

        }

        //  回复的点击事件
        helper.addOnClickListener(R.id.iv_replay);


    }


}