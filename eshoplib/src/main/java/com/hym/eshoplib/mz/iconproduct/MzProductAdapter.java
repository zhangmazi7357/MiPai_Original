package com.hym.eshoplib.mz.iconproduct;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;

import cn.hym.superlib.mz.utils.MzStringUtil;

class MzProductAdapter extends BaseQuickAdapter<HomeIconProductBean.DataBean.VideoBean, MzProductAdapter.ViewHolder> {

    private Context mContext;
    private LatLonPoint dest;

    public void setDest(LatLonPoint dest) {
        this.dest = dest;
        notifyDataSetChanged();
    }

    public MzProductAdapter(Context context, List<HomeIconProductBean.DataBean.VideoBean> data) {
        super(R.layout.mz_adapter_icon_product, data);
        this.mContext = context;

    }

    @Override
    protected void convert(ViewHolder helper, HomeIconProductBean.DataBean.VideoBean item) {


        Glide.with(mContext)
                .load(item.getImage_default())
                .into(helper.proImg);

        helper.proTitle.setText(item.getTitle());
        helper.proPrice.setText(item.getPresent_price());

        if (TextUtils.isEmpty(item.getOriginal_price()) || item.getOriginal_price().equals("0")) {
            helper.oldPrice.setVisibility(View.GONE);
        }
        helper.oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        helper.oldPrice.setText(item.getOriginal_price());

        String tags = item.getTags();

        if (!TextUtils.isEmpty(tags)) {
            String[] subTags = MzStringUtil.splitComma(tags);
            List<String> subTagList = Arrays.asList(subTags);
            helper.flowLayout.setAdapter(new TagAdapter<String>(subTagList) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView root = (TextView) LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.mz_tag_textview, parent, false);
                    root.setText(s);
                    return root;
                }
            });
        }


    }

    class ViewHolder extends BaseViewHolder {
        private ImageView proImg;
        private TextView proTitle;
        private TextView proPrice;
        private TagFlowLayout flowLayout;
        private TextView oldPrice;


        public ViewHolder(View view) {
            super(view);
            proImg = view.findViewById(R.id.iv_view);
            proTitle = view.findViewById(R.id.tv_describe);
            proPrice = view.findViewById(R.id.tv_one_money);
            oldPrice = view.findViewById(R.id.tv_before_price);
            flowLayout = view.findViewById(R.id.flowLayout);
//            proAddress = view.findViewById(R.id.pro_address);
//            proDistance = view.findViewById(R.id.proDistance);
        }
    }

}
