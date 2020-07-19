package com.hym.eshoplib.fragment.shop.mzupload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.help.Tip;
import com.hym.eshoplib.R;

import java.util.List;


/**
 * poi
 */
class POISearchAdapter extends RecyclerView.Adapter<POISearchAdapter.ViewHolder> {


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

        if (tip != null)
            holder.poi.setText(tip.getDistrict() + tip.getAddress());

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

        TextView poi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poi = itemView.findViewById(R.id.tvPoi);

        }
    }


    public interface POIListener {
        void onClick(int position);
    }


}