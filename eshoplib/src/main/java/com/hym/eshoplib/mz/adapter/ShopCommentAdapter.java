package com.hym.eshoplib.mz.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopCommentsBean;

import java.util.List;

/**
 * 商品评论 ;
 */
public class ShopCommentAdapter extends BaseQuickAdapter<ShopCommentsBean.DataBean.InfoBean, BaseViewHolder> {


    public ShopCommentAdapter(@Nullable List<ShopCommentsBean.DataBean.InfoBean> data) {
        super(R.layout.mz_adapter_shop_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCommentsBean.DataBean.InfoBean item) {

    }


}
