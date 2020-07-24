package com.hym.eshoplib.fragment.shop.mzupload.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductTagBean;

import java.util.List;

/**
 * 产品标签 ;
 */
public class MzProductTagAdapter extends RecyclerView.Adapter<MzProductTagAdapter.ViewHolder> {


    private List<ProductTagBean> datas;
    private TagClickListener tagClickListener;

    public void setTagClickListener(TagClickListener tagClickListener) {
        this.tagClickListener = tagClickListener;
    }

    public void setDatas(List<ProductTagBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    public ProductTagBean getItemPosition(int position) {
        return datas.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mz_adapter_product_one_type, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductTagBean bean = datas.get(position);

        String tag = bean.getTag();
        boolean checked = bean.isChecked();

        if (!TextUtils.isEmpty(tag)) {
            holder.sort.setText(tag);
        }

        holder.checkIv.setVisibility(checked ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bean.setChecked(!checked);
                notifyItemChanged(position);
                notifyDataSetChanged();

                if (tagClickListener != null) {
                    tagClickListener.tagClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
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


    public interface TagClickListener {
        void tagClick(int position);
    }
}
