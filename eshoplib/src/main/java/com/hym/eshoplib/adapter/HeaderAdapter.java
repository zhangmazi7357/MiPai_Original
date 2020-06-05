
package com.hym.eshoplib.adapter;

import android.content.Context;
import android.view.View;

import com.hym.eshoplib.R;
import com.hym.eshoplib.widgets.adapter.BaseViewHolder;
import com.hym.eshoplib.widgets.adapter.SuperBaseAdapter;

import java.util.List;

/**
 * Created by super南仔 on 2017-05-09. 类备注： 需要传入的参数:
 */
public class HeaderAdapter extends SuperBaseAdapter<String> {
    OnHeaderViewClick listener;

    public OnHeaderViewClick getListener() {
        return listener;
    }

    public void setListener(OnHeaderViewClick listener) {
        this.listener = listener;
    }

    public HeaderAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final String item, int position) {
        holder.setText(R.id.tvName, item);
        holder.setOnClickListener(R.id.tvName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onCityClick(item);
                }
            }
        });
        holder.setOnClickListener(R.id.tvLocation, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onRelocationClick();
                }
            }
        });
    }

    @Override
    protected int getItemViewLayoutId(int position, String item) {
        return R.layout.adapter_city_header_layout;
    }
    public interface OnHeaderViewClick{
        public void onCityClick(String name);
        public void onRelocationClick();
    }
}
