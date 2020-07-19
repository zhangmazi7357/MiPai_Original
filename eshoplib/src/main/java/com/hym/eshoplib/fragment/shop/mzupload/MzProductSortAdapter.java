package com.hym.eshoplib.fragment.shop.mzupload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.eshoplib.R;

import java.util.List;

/**
 * 产品分类 Adapter  ；
 */
public class MzProductSortAdapter extends RecyclerView.Adapter<MzProductSortAdapter.ViewHolder> {


    private int selectPosition = -1;
    private List<String> datas;

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mz_adapter_product_sort, parent, false);


        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setSelected(selectPosition == position);
        if (selectPosition == position) {
            holder.checkIv.setVisibility(View.VISIBLE);
        } else {
            holder.checkIv.setVisibility(View.GONE);
        }

        holder.sort.setText(datas.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = position;
                notifyItemChanged(selectPosition);
                notifyDataSetChanged();

                if (clickListener != null) {
                    clickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    public String getItemPosition(int position) {
        return datas.get(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sort;
        ImageView checkIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sort = itemView.findViewById(R.id.sort);
            checkIv = itemView.findViewById(R.id.checkIv);
        }
    }


    public interface MzClickListener {
        void onClick(int position);
    }

    private MzClickListener clickListener;

    public void setClickListener(MzClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
