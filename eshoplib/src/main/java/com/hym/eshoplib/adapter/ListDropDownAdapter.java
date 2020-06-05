package com.hym.eshoplib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hym.eshoplib.R;

import java.util.List;


public class ListDropDownAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public ListDropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_default_drop_down, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        String data=list.get(position);
        if(data.contains(",")){
            viewHolder.mText.setText(data.split(",")[0]);
            viewHolder.subText.setText(" 一 "+data.split(",")[1]);
        }else {
            viewHolder.mText.setText(list.get(position));
            viewHolder.subText.setText("");
        }

        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.mipaiTextColorSelect));
                //viewHolder.mText.setBackgroundResource(R.color.check_bg);
            } else {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.mipaiTextColorNormal));

                //viewHolder.mText.setBackgroundResource(R.color.white);
            }
        }
    }

    static class ViewHolder {

        TextView mText,subText;

        ViewHolder(View view) {
            mText= (TextView) view.findViewById(R.id.text);
            subText=view.findViewById(R.id.subtext);

        }
    }
}
