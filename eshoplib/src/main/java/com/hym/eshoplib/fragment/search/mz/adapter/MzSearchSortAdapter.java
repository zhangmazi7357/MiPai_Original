package com.hym.eshoplib.fragment.search.mz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.eshoplib.R;


/**
 *
 */
public class MzSearchSortAdapter extends RecyclerView.Adapter<MzSearchSortAdapter.ViewHolder> {

    private String[] sorts = {"智能排序", "价格排序", "好评优先", "星级排序"};

    private SearchSortClickListener searchSortClickListener;
    private Context context;
    private LayoutInflater inflater;

    public MzSearchSortAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private int selectPosition = 0;

    public void setSearchSortClickListener(SearchSortClickListener searchSortClickListener) {
        this.searchSortClickListener = searchSortClickListener;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.mz_adapter_search_sort, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String sortTitle = sorts[position];
        holder.sort.setText(sortTitle);

        if (selectPosition != -1) {
            if (selectPosition == position) {
                holder.sort.setTextColor(context.getResources().getColor(R.color.mipaiTextColorSelect));
            } else {
                holder.sort.setTextColor(context.getResources().getColor(R.color.mipaiTextColorNormal));


            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchSortClickListener != null) {
                    searchSortClickListener.onItemClick(position, sortTitle);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return sorts.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sort;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sort = itemView.findViewById(R.id.sort);
        }
    }


    public interface SearchSortClickListener {
        void onItemClick(int position, String sortTitle);
    }
}
