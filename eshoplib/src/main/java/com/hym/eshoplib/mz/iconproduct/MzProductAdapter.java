package com.hym.eshoplib.mz.iconproduct;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lib_amap.MapManager;
import com.hym.eshoplib.R;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.mz.utils.MzStringUtil;

class MzProductAdapter extends BaseQuickAdapter<HomeIconProductBean.DataBean, MzProductAdapter.ViewHolder> {

    private Context mContext;
    private LatLonPoint dest;

    public void setDest(LatLonPoint dest) {
        this.dest = dest;
        notifyDataSetChanged();
    }

    public MzProductAdapter(Context context, List<HomeIconProductBean.DataBean> data) {
        super(R.layout.mz_adapter_icon_product, data);
        this.mContext = context;

    }

    @Override
    protected void convert(ViewHolder helper, HomeIconProductBean.DataBean item) {

        Glide.with(mContext)
                .load(item.getImage_default())
                .into(helper.proImg);

        helper.proTitle.setText(item.getTitle());
        helper.proPrice.setText("¥ " + item.getPresent_price());
        helper.proAddress.setText(item.getAddress());

        String lon = item.getLon();
        String lat = item.getLat();


        if (!TextUtils.isEmpty(lon) && !TextUtils.isEmpty(lat)) {
            LatLonPoint point = new LatLonPoint(Double.parseDouble(lon), Double.parseDouble(lat));
            ArrayList<LatLonPoint> list = new ArrayList();
            list.add(point);

            MapManager.getInstance().calculateInstance(mContext, dest, list,
                    DistanceSearch.TYPE_DISTANCE, new DistanceSearch.OnDistanceSearchListener() {
                        @Override
                        public void onDistanceSearched(DistanceResult distanceResult, int i) {
                            List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
                            DistanceItem distanceItem = distanceResults.get(0);
                            float distance = distanceItem.getDistance();
                            String result = MzStringUtil.distance(distance);
                            helper.proDistance.setText("距您" + result);
                        }
                    });

        }
    }

    class ViewHolder extends BaseViewHolder {
        private ImageView proImg;
        private TextView proTitle;
        private TextView proPrice;
        private TextView proAddress;
        private TextView proDistance;

        public ViewHolder(View view) {
            super(view);
            proImg = view.findViewById(R.id.pro_img);
            proTitle = view.findViewById(R.id.pro_title);
            proPrice = view.findViewById(R.id.pro_price);
            proAddress = view.findViewById(R.id.pro_address);
            proDistance = view.findViewById(R.id.proDistance);
        }
    }

}
