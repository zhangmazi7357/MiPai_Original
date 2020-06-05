package com.hym.eshoplib.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.bean.goods.BasicInfo;

import java.util.List;

public class TakeInfoAdapter extends BaseQuickAdapter<BasicInfo,BaseViewHolder> {


    public TakeInfoAdapter(int layoutResId, @Nullable List<BasicInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BasicInfo item) {

    }
}
