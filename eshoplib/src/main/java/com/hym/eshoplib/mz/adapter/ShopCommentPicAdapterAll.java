package com.hym.eshoplib.mz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;

import java.util.List;

import cn.hym.superlib.mz.utils.SizeUtils;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * 商品评论图片 - 全部评论 ;
 * 判断图片的数量
 * 会有三种布局显示方式
 */
public class ShopCommentPicAdapterAll extends RecyclerView.Adapter<ShopCommentPicAdapterAll.ViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private List<String> datas;

    private OnPicItemClickListener onPicItemClickListener;

    public void setOnPicItemClickListener(OnPicItemClickListener onPicItemClickListener) {
        this.onPicItemClickListener = onPicItemClickListener;
    }

    public ShopCommentPicAdapterAll(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mz_shop_comment_pic_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  228  170  112
        if (datas.size() == 1) {
            changeSize(holder.fl, 228);
        } else if (datas.size() == 2) {
            changeSize(holder.fl, 170);
        } else if (datas.size() == 3) {
            changeSize(holder.fl, 112);
        }

        String item = datas.get(position);
        Glide.with(mContext)
                .load(item)
                .placeholder(R.drawable.picture_icon_placeholder)
                .into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPicItemClickListener != null) {
                    onPicItemClickListener.onPicClick(position);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fl;
        ImageView iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fl);
            iv = itemView.findViewById(R.id.iv);
        }
    }



    // 改变尺寸 ;
    private void changeSize(FrameLayout fl, int size) {
        fl.getLayoutParams().width = ScreenUtil.dip2px(mContext, size);
        fl.getLayoutParams().height = ScreenUtil.dip2px(mContext, size);

    }


    public interface OnPicItemClickListener {
        void onPicClick(int position);
    }
}
