package com.hym.eshoplib.adapter;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.EshopHomeDataBean;
import com.hym.imagelib.ImageUtil;

import java.util.List;


public class SortButtonAdapter extends BaseAdapter {
    private Fragment fragment;
    private LayoutInflater inflater;
    private List<EshopHomeDataBean.DataBean.CategoryBean> data;
    public SortButtonAdapter(Fragment fragment, List<EshopHomeDataBean.DataBean.CategoryBean> data) {
        this.fragment = fragment;
        this.inflater = LayoutInflater.from(fragment.getContext());
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public EshopHomeDataBean.DataBean.CategoryBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (null == view) {
            view = inflater.inflate(R.layout.item_button, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(position).getCategory_name());
        ImageUtil.getInstance().loadCircleImage(fragment,data.get(position).getFilepath(),holder.icon);
        return view;
    }
    class ViewHolder {
        ImageView icon;
        TextView name;
    }

}
