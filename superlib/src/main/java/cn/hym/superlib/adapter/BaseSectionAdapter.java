package cn.hym.superlib.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by 胡彦明 on 2017/7/6.
 * <p>
 * Description 分组布局adapter
 * <p>
 * otherTips
 */

public  abstract class BaseSectionAdapter<T extends SectionEntity> extends BaseSectionQuickAdapter<T , BaseViewHolder> {


    public BaseSectionAdapter(int layoutResId, int sectionHeadResId, List<T> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, T item) {
        int position=helper.getLayoutPosition() - getHeaderLayoutCount();
        handleHead(helper,item,position);

    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
       int position=helper.getLayoutPosition() - getHeaderLayoutCount();
       handleView(helper,item,position);

    }
    public abstract void handleHead(BaseViewHolder helper, T item, int position) ;
    public abstract void handleView(BaseViewHolder helper, T item, int position) ;
}
