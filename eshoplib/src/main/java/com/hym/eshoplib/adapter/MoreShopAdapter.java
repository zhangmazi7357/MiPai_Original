package com.hym.eshoplib.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;

import java.util.List;

public class MoreShopAdapter extends BaseQuickAdapter<SpecialTimeLimteBean.DataBean.VideoBean,BaseViewHolder>{

    public MoreShopAdapter(int layoutResId, @Nullable List<SpecialTimeLimteBean.DataBean.VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialTimeLimteBean.DataBean.VideoBean item) {

    }
}
