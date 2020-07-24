package com.hym.eshoplib.fragment.shop.mzupload.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductTwoTypeBean;

import java.util.List;

/**
 * 二级分类 ;
 */
public class MzProductTwoTypeAdapter extends RecyclerView.Adapter<MzProductTwoTypeAdapter.ViewHolder> {

    private List<ProductTwoTypeBean.DataBean> datas;
    private TwoTypeClickListener twoTypeClickListener;

    public void setTwoTypeClickListener(TwoTypeClickListener twoTypeCliclListener) {
        this.twoTypeClickListener = twoTypeCliclListener;
    }

    public void setDatas(List<ProductTwoTypeBean.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.mz_adapter_product_two_type, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductTwoTypeBean.DataBean data = datas.get(position);

        boolean checked = data.isChecked();
        String title = data.getTitle();
        holder.twoTv.setText(title);


        holder.twoIv.setVisibility(checked ? View.VISIBLE : View.GONE);


        // item 点击事件，点击了以后 check状态反向
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data.setChecked(!checked);

                notifyItemChanged(position);
                notifyDataSetChanged();

                if (twoTypeClickListener != null) {
                    twoTypeClickListener.twoTypeClick(position);
                }
            }
        });

    }

    public ProductTwoTypeBean.DataBean getItemPosition(int position) {

        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView twoTv;
        ImageView twoIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            twoTv = itemView.findViewById(R.id.two_tv);
            twoIv = itemView.findViewById(R.id.two_iv);
        }

    }


    public interface TwoTypeClickListener {
        void twoTypeClick(int position);
    }
}
