package com.hym.eshoplib.fragment.order;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.mylibrary.TimeLineMarkerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.bean.order.LogicalsInfoBean;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.imagelib.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/4/14.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class LogicalsFragment extends BaseListFragment<LogicalsInfoBean.DataBean.ExpressListBean> {
    @BindView(R2.id.iv_icon)
    ImageView ivIcon;
    @BindView(R2.id.tv_status)
    TextView tvStatus;
    @BindView(R2.id.tv_express_name)
    TextView tvExpressName;
    @BindView(R2.id.tv_phone)
    TextView tvPhone;
    @BindView(R2.id.tv_address)
    TextView tvAddress;
    Unbinder unbinder;

    public static LogicalsFragment newInstance(Bundle args) {
        LogicalsFragment fragment = new LogicalsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    String id;

    @Override
    public int getItemRestId() {
        return R.layout.item_time_line;
    }

    @Override
    public void excuteLogic() {
        String url=getArguments().getString("url");
        id = getArguments().getString("id", "");
        showBackButton();
        setTitle(R.string.Logisticdetails);
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_logicals, null, false);
        unbinder = ButterKnife.bind(this, header);
        getRefreshLayout().setEnabled(false);
        ImageUtil.getInstance().loadImage(LogicalsFragment.this,url,ivIcon);
        getAdapter().addHeaderView(header);

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        OrderApi.GetExpress(_mActivity, id, new ResponseImpl<LogicalsInfoBean>() {
            @Override
            public void onSuccess(LogicalsInfoBean data) {
                tvStatus.setText(data.getData().getStatus()+"");
                tvExpressName.setText(data.getData().getExpress_name()+"："+data.getData().getExpress_num());
                tvPhone.setText(getResources().getString(R.string.ExpressPhone)+"："+data.getData().getExpress_tel());
                tvAddress.setText("【"+getResources().getString(R.string.Deliveryaddress)+"】 "+data.getData().getConsignee_address());
                getAdapter().setNewData(data.getData().getExpress_list());

            }
        }, LogicalsInfoBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, LogicalsInfoBean.DataBean.ExpressListBean item, int position) {
        TimeLineMarkerView timeLine = helper.getView(R.id.time_view);
        if (position == 0) {
            timeLine.setMarkerDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.ic_right_small));
            timeLine.setEndLine(new ColorDrawable(getResources().getColor(R.color.resource_gray_e1e1e1)));
            timeLine.setBeginLine(new ColorDrawable(getResources().getColor(R.color.white)));
            timeLine.setMarkerSize(14);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) timeLine.getLayoutParams();
            lp.setMargins(ScreenUtil.dip2px(_mActivity, 5), 0, ScreenUtil.dip2px(_mActivity, 5), 0);


        } else if (position == getAdapter().getData().size() - 1) {
            timeLine.setMarkerDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.shape_gray_circle_radius_4));
            timeLine.setEndLine(new ColorDrawable(getResources().getColor(R.color.white)));
            timeLine.setBeginLine(new ColorDrawable(getResources().getColor(R.color.resource_gray_e1e1e1)));
            timeLine.setMarkerSize(8);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) timeLine.getLayoutParams();
            lp.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, ScreenUtil.dip2px(_mActivity, 5), 0);
        } else {
            timeLine.setMarkerDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.shape_gray_circle_radius_4));
            timeLine.setEndLine(new ColorDrawable(getResources().getColor(R.color.resource_gray_e1e1e1)));
            timeLine.setBeginLine(new ColorDrawable(getResources().getColor(R.color.resource_gray_e1e1e1)));
            timeLine.setMarkerSize(8);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) timeLine.getLayoutParams();
            lp.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, ScreenUtil.dip2px(_mActivity, 5), 0);

        }
        helper.setText(R.id.tv_time, item.getTime() + "");
        helper.setText(R.id.tv_message, item.getContext() + "");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
