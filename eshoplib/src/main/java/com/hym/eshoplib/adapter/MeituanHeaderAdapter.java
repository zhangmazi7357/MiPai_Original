
package com.hym.eshoplib.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.CityHeaderBean;
import com.hym.eshoplib.widgets.adapter.BaseViewHolder;
import com.hym.eshoplib.widgets.adapter.SuperHeaderAndFooterAdapter;


/**
 * Created by zhangxutong . Date: 16/08/28
 */

public class MeituanHeaderAdapter extends SuperHeaderAndFooterAdapter {
    private Context mContext;

    HeaderAdapter.OnHeaderViewClick listener;

    public HeaderAdapter.OnHeaderViewClick getListener() {
        return listener;
    }

    public void setListener(HeaderAdapter.OnHeaderViewClick listener) {
        this.listener = listener;
    }

    public MeituanHeaderAdapter(RecyclerView.Adapter mInnerAdapter, Context mContext) {
        super(mContext, mInnerAdapter);
        this.mContext = mContext;
    }

    @Override
    protected void onBindHeaderHolder(BaseViewHolder holder, int headerPos, int layoutId, Object o) {
        switch (layoutId) {
            case R.layout.view_city_header_layout:
                final CityHeaderBean meituanHeaderBean = (CityHeaderBean) o;
                // 网格
                RecyclerView recyclerView = holder.getView(R.id.header_location_recycleview);
                HeaderAdapter adapter=new HeaderAdapter(mContext, meituanHeaderBean.getCityList());
                if(listener!=null){
                    adapter.setListener(listener);
                }
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
                break;
            default:
                break;
        }
    }

}
