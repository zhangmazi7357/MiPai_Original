package cn.hym.superlib.adapter;

import androidx.annotation.IntRange;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by  胡彦明 on 2017/5/9.
 * <p>
 * Description 通用列表适配器
 * <p>
 * OtherTips
 */

public abstract class BaseListAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    public BaseListAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        int position=helper.getLayoutPosition() - getHeaderLayoutCount();
        handleView(helper,item,position);

    }

    @Override
    public void remove(@IntRange(from = 0L) int position) {
//        mData.remove(position);
//        notifyDataSetChanged();
        mData.remove(position);
        int internalPosition = position + getHeaderLayoutCount();
        notifyItemRemoved(internalPosition);
        if (mData.size() == 0) {
            notifyDataSetChanged();
        }
        notifyItemRangeChanged(internalPosition, mData.size() + getHeaderLayoutCount()+getFooterLayoutCount()-internalPosition);

    }

    public abstract void handleView(BaseViewHolder helper, T item, int position) ;


}
