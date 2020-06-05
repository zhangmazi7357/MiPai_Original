package com.hym.eshoplib.fragment.me.pocket;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.me.BeanListBean;
import com.hym.eshoplib.http.me.MeApi;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 流水列表
 * <p>
 * otherTips
 */

public class MyLogListFragment extends BaseListFragment<BeanListBean.DataBean.InfoBean> {
    int type = 0;

    public static MyLogListFragment newInstance(Bundle args) {
        MyLogListFragment fragment = new MyLogListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_monye_log;
    }



    @Override
    public void excuteLogic() {
        type = getArguments().getInt("type", 0);
        View empty_view = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv = empty_view.findViewById(R.id.iv_icon);
        TextView tv_mesg = empty_view.findViewById(R.id.tv_message);
        iv.setImageResource(R.drawable.ic_empty_money_log);
        tv_mesg.setText("当前还没有信息哦~");
        getAdapter().setEmptyView(empty_view);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_me,ActionActivity.Action_LogDetail);
                bundle.putString("id",getAdapter().getData().get(position).getLog_id());
                ActionActivity.start(_mActivity,bundle);
            }
        });

    }
    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
//        switch (type) {
//            case 0:
//                //全部
//                break;
//            case 1:
//                //收入
//
//                break;
//            case 2:
//                //支出
//
//                break;
//
//        }
        MeApi.BeanList(type + "", pageNum + "", new ResponseImpl<BeanListBean>() {
            @Override
            public void onSuccess(BeanListBean data) {
                int total=Integer.parseInt(data.getData().getTotalpage());
                int current=Integer.parseInt(data.getData().getCurrentpage());
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(total,current,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total,current,data.getData().getInfo(),getAdapter()));
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        },BeanListBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, BeanListBean.DataBean.InfoBean item, int position) {
        View diver=helper.getView(R.id.view_diver);
        if(position==0){
            diver.setVisibility(View.VISIBLE);
        }else {
            diver.setVisibility(View.GONE);
        }
        TextView tv_banlance=helper.getView(R.id.tv_banlance);
        TextView tv_time=helper.getView(R.id.tv_time);
        TextView tv_title=helper.getView(R.id.tv_title);
        TextView tv_count=helper.getView(R.id.tv_count);
        tv_title.setText(item.getCause()+"");//变动说明
        tv_banlance.setText("钱包余额："+item.getAfter_value());
        tv_time.setText(item.getCtime().substring(0,10));
        String type=item.getType();
        //1：充值， 2：消费， 3：收入，4：提现，5：收入（退款）
        switch (type){
            case "1":
                tv_count.setText("+"+item.getValue());
                tv_count.setTextColor(Color.parseColor("#77b2eb"));
                break;
            case "2":
                tv_count.setText("-"+item.getValue());
                tv_count.setTextColor(Color.parseColor("#cb2929"));
                break;
            case "3":
                tv_count.setText("+"+item.getValue());
                tv_count.setTextColor(Color.parseColor("#77b2eb"));
                break;
            case "4":
                tv_count.setText("-"+item.getValue());
                tv_count.setTextColor(Color.parseColor("#cb2929"));
                break;
            case "5":
                tv_count.setText("+"+item.getValue());
                tv_count.setTextColor(Color.parseColor("#77b2eb"));
                break;
        }
    }

    public void changeType(int type) {
        this.type = type;
        onRefresh();
    }



}
