package com.hym.eshoplib.adapter;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;

import java.util.List;

import cn.hym.superlib.utils.view.ScreenUtil;

public class ShopListAdapter extends BaseQuickAdapter<SpecialTimeLimteBean.DataBean.VideoBean,BaseViewHolder>{
    public ShopListAdapter(int layoutResId, @Nullable List<SpecialTimeLimteBean.DataBean.VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialTimeLimteBean.DataBean.VideoBean item) {
        if (item == null){
            return;
        }
        ImageView ivView = helper.getView(R.id.iv_view);
        ScreenUtil.ViewAdapter(mContext, ivView, 16, 9, 20);
        Glide.with(mContext).load(item.getImage_default()).into(ivView);
       // ImageUtil.getInstance().loadRoundCornerImage(mContext, item.getImage_default(), ivView, 6);
        //Glide.with(mContext).load(item.getImage_default()).into(ivView);
        TextView tvBeforePrice = helper.getView(R.id.tv_before_price);
        if (TextUtils.isEmpty(item.getOriginal_price()) || item.getOriginal_price().equals("0")){
            tvBeforePrice.setVisibility(View.GONE);
        }
        tvBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        helper.setText(R.id.tv_describe,item.getTitle())
                .setText(R.id.tv_pay_count,item.getWeight()+"人付款")
                .setText(R.id.tv_one_money,item.getPresent_price())
                .setText(R.id.tv_before_price,item.getOriginal_price())
        ;
    }
}
