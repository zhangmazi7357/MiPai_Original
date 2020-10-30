package com.hym.eshoplib.mz.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.mz.shopdetail.MzShopCommentBean;

import java.util.Arrays;
import java.util.List;

import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.mz.utils.MzStringUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


/**
 * 商品评论 ;
 */
public class ShopCommentAdapter<T> extends BaseQuickAdapter<MzShopCommentBean.DataBean.InfoBean,
        BaseViewHolder> {

    public ShopCommentAdapter(@Nullable List<MzShopCommentBean.DataBean.InfoBean> data) {
        super(R.layout.mz_adapter_shop_comment, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MzShopCommentBean.DataBean.InfoBean item) {

        helper.setText(R.id.username, MzStringUtil.hideUserName(item.getNickname()));

        int position = helper.getLayoutPosition();
//        Log.e(TAG, "position = " + position);
        int itemCount = getItemCount();
        View divider = helper.getView(R.id.divider);
        divider.setVisibility(View.VISIBLE);
        if (position == itemCount - 1) {
            divider.setVisibility(View.GONE);
        }


        // 头像 ;
        ImageView headerView = helper.getView(R.id.header);
        Glide.with(mContext).load(item.getAvatar())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(headerView);
        helper.setText(R.id.commentContent, item.getContent());


        MaterialRatingBar ratingBar = helper.getView(R.id.ratingBar);

        if (!TextUtils.isEmpty(item.getScore())) {
            String score = item.getScore();
            ratingBar.setRating(Float.parseFloat(score));
        }


        // 图片
        RecyclerView commentImgRv = helper.getView(R.id.commentImgRv);
        commentImgRv.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        ShopPicAdapter picAdapter = new ShopPicAdapter(null);

        commentImgRv.setAdapter(picAdapter);

        String images = item.getImages();
        String[] strings = MzStringUtil.splitComma(images);


        if (strings != null && strings.length > 0) {
            commentImgRv.setVisibility(View.VISIBLE);
            List<String> imgList = Arrays.asList(strings);
            picAdapter.setNewData(imgList);


            picAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    int width = ScreenUtil.getScreenWidth(mContext);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                    ImagePagerActivity.startImagePagerActivity(mContext, imgList, position, imageSize);

                }
            });

        } else {
            commentImgRv.setVisibility(View.GONE);
        }


    }


}
