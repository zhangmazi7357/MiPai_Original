package com.hym.eshoplib.fragment.shop.mzupload;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.services.help.Tip;
import com.hym.eshoplib.R;

import java.util.List;

import io.rong.common.fwlog.LogEntity;


/**
 * poi
 */
public class POISearchAdapter extends RecyclerView.Adapter<POISearchAdapter.ViewHolder> {


    private List<Tip> datas;
    private POIListener poiListener;

    public void setDatas(List<Tip> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setPoiListener(POIListener poiListener) {
        this.poiListener = poiListener;
    }

    public Tip getItemPosition(int position) {
        return datas.get(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.mz_adapter_poi, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Tip tip = datas.get(position);

        Log.e("===", "tip : " + JSONObject.toJSONString(tip));

        if (tip != null) {
            holder.poiName.setText(tip.getName());
            holder.poiAddress.setText(tip.getAddress());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (poiListener != null) {
                    poiListener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView poiName;
        TextView poiAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poiName = itemView.findViewById(R.id.poiName);
            poiAddress = itemView.findViewById(R.id.poiAddress);

        }
    }


    public interface POIListener {
        void onClick(int position);
    }


}
