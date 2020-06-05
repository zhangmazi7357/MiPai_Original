package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.bean.shop.ShopCommentsBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/26.
 * <p>
 * Description 店铺评论
 * <p>
 * otherTips
 */

public class ShopCommentsListFragment extends BaseListFragment<ShopCommentsBean.DataBean.InfoBean> {
    ServiceDetailBean detailBean;
    public static ShopCommentsListFragment newInstance(Bundle args) {
        ShopCommentsListFragment fragment = new ShopCommentsListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public boolean showToolBar() {
        return false;
    }



    @Override
    public int getItemRestId() {
        return R.layout.item_comments;
    }

    @Override
    public void excuteLogic() {
        detailBean= (ServiceDetailBean) getArguments().getSerializable("data");
        View header=LayoutInflater.from(_mActivity).inflate(R.layout.header_goods_comments,null,false);
        SuperTextView tv_comments=header.findViewById(R.id.tv_comments);
        if(detailBean!=null){
            tv_comments.setLeftString("评价："+detailBean.getData().getComment());
            tv_comments.setRightString("好评："+detailBean.getData().getPraise_rate()+"%");
        }
        getAdapter().addHeaderView(header);
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv_icon=emptyView.findViewById(R.id.iv_icon);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) iv_icon.getLayoutParams();
        layoutParams.setMargins(0,ScreenUtil.dip2px(_mActivity,50),0,0);
        iv_icon.setLayoutParams(layoutParams);
        iv_icon.setImageResource(R.drawable.ic_no_comment);
        TextView tv_message=emptyView.findViewById(R.id.tv_message);
        tv_message.setText("暂无评价");
        getAdapter().setEmptyView(emptyView);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle= BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,EshopActionActivity.Action_order_comment_detail);
                bundle.putString("id",getAdapter().getData().get(position).getId());
                EshopActionActivity.start(_mActivity,bundle);
            }
        });



    }

    @Override
    public void getData(final boolean refresh, int pageSize,final int pageNum) {
        if(detailBean==null|| TextUtils.isEmpty(detailBean.getData().getStore_id())){
            dissMissDialog();
            getRefreshLayout().setRefreshing(false);
            ToastUtil.toast("数据异常");
            return;
        }
        ShopApi.getShopComments(detailBean.getData().getContent_id(), pageNum + "", new ResponseImpl<ShopCommentsBean>() {
            @Override
            public void onSuccess(ShopCommentsBean data) {
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }
            }
        },ShopCommentsBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, ShopCommentsBean.DataBean.InfoBean item, final int position) {
        View diver=helper.getView(R.id.view_diver);
        View bottom_diver=helper.getView(R.id.view_bottom_diver);
        if(position==0){
            diver.setVisibility(View.GONE);
        }else {
            diver.setVisibility(View.VISIBLE);
        }
//        if(position==getAdapter().getData().size()-1){
//            bottom_diver.setVisibility(View.VISIBLE);
//        }else {
//            bottom_diver.setVisibility(View.GONE);
//        }
        ImageView iv_avantar=helper.getView(R.id.iv_avatar);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_type=helper.getView(R.id.tv_spec);
        TextView tv_count=helper.getView(R.id.tv_count);
        TextView tv_time=helper.getView(R.id.tv_time);
        MaterialRatingBar ratingBar=helper.getView(R.id.ratingbar);
        ImageUtil.getInstance().loadCircleImage(ShopCommentsListFragment.this,item.getAvatar(),iv_avantar);
        tv_name.setText(item.getNickname());
        tv_type.setText("类别："+item.getCategory_name());
        tv_count.setText("x"+item.getNum());
        tv_time.setText(item.getCtime()+"");
        ratingBar.setRating(Float.parseFloat(item.getRank_type()));
        List<String>labels= item.getLabel_list();
        SuperButton laybel_1=helper.getView(R.id.laybel_1);
        SuperButton laybel_2=helper.getView(R.id.laybel_2);
        SuperButton laybel_3=helper.getView(R.id.laybel_3);
        laybel_1.setVisibility(View.GONE);
        laybel_2.setVisibility(View.GONE);
        laybel_3.setVisibility(View.GONE);
        switch (labels.size()){
            case 1:
                laybel_1.setVisibility(View.VISIBLE);
                laybel_1.setText(labels.get(0));
                break;
            case 2:
                laybel_1.setVisibility(View.VISIBLE);
                laybel_1.setText(labels.get(0));
                laybel_2.setVisibility(View.VISIBLE);
                laybel_2.setText(labels.get(1));
                break;
            case 3:
                laybel_1.setVisibility(View.VISIBLE);
                laybel_1.setText(labels.get(0));
                laybel_2.setVisibility(View.VISIBLE);
                laybel_2.setText(labels.get(1));
                laybel_3.setVisibility(View.VISIBLE);
                laybel_3.setText(labels.get(2));
                break;
        }
        ImageView iv_vip=helper.getView(R.id.iv_vip);
        if(item.getAuth().equals("1")){
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_person_circle);
        }else if(item.getAuth().equals("2")){
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_business_circle);
        }else {
            iv_vip.setVisibility(View.GONE);
        }
        TagFlowLayout tagFlowLayout=helper.getView(R.id.fl_commnents);
        tagFlowLayout.setAdapter(new TagAdapter<String>(labels) {
            @Override
            public View getView(FlowLayout parent,final int p, String s) {
                final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_comment_tab, parent, false);
                textView.setText(s);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle= BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,EshopActionActivity.Action_order_comment_detail);
                        bundle.putString("id",getAdapter().getData().get(position).getId());
                        EshopActionActivity.start(_mActivity,bundle);
                    }
                });
                return textView;
            }
        });


    }



}
