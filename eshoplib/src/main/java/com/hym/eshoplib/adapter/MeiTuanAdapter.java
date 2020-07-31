
package com.hym.eshoplib.adapter;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.CityBean;

import java.util.List;

/**
 * Created by super南仔 on 2017-05-09. 类备注： 需要传入的参数:
 */
public class MeiTuanAdapter extends RecyclerView.Adapter<MeiTuanAdapter.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    int select_position = -1;
    OnCityClickListener listener;
    String region_id;

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public void setListener(OnCityClickListener listener) {
        this.listener = listener;
    }

    public MeiTuanAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public MeiTuanAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    public int getSelect_position() {
        return select_position;
    }

    public void setSelect_position(int select_position) {
        this.select_position = select_position;
    }

    @Override
    public MeiTuanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.adapter_city_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final MeiTuanAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getName());

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
                select_position = position;
                if (listener != null) {
                    listener.onClicke(position, cityBean.getId(), cityBean.getName());
                }

            }
        });
        if (select_position == position) {
            holder.tvCity.setTextColor(ContextCompat.getColor(mContext, R.color.mipaiTextColorSelect));

        } else {
            holder.tvCity.setTextColor(ContextCompat.getColor(mContext, R.color.mipaiTextColorNormal));
        }
        if (select_position == -1) {
            if (region_id != null && region_id.equals(cityBean.getId())) {
                holder.tvCity.setTextColor(ContextCompat.getColor(mContext, R.color.mipaiTextColorSelect));
            }
        }
        //处理分割线
        if (position + 1 <= mDatas.size() - 1) {
            String p1 = Pinyin.toPinyin(cityBean.getName().charAt(0));
            String p2 = Pinyin.toPinyin(mDatas.get(position + 1).getName().charAt(0));
            // Logger.d("p1="+p1+",p2="+p2);
            if (!p1.substring(0, 1).equals(p2.substring(0, 1))) {
                holder.diver.setVisibility(View.GONE);
            } else {
                holder.diver.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        View content, diver;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.super_tv_name);
            content = itemView.findViewById(R.id.content);
            diver = itemView.findViewById(R.id.view_diver);
        }
    }

    public interface OnCityClickListener {
        public void onClicke(int position, String id, String name);
    }
}
