package com.hym.eshoplib.fragment.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ShopListBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2019/8/22.
 * <p>
 * Description 搜索工作室
 * <p>
 * otherTips
 */

public class SearchShopListFragment extends BaseListFragment<ShopListBean.DataBean.InfoBean> {
    private String keywords;

    public static SearchShopListFragment newInstance(Bundle args) {
        SearchShopListFragment fragment = new SearchShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_shop_list;
    }

    @Override
    public void excuteLogic() {
        keywords = getArguments().getString("keywords", "");
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String content_id = getAdapter().getData().get(position).getContent_id();
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                // bundle.putInt("type",getArguments().getInt("type",1));//工作室类型，对应首页
                bundle.putString("id", content_id);
                ActionActivity.start(_mActivity, bundle);
            }
        });
//        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
//        ImageView iv_icon=emptyView.findViewById(R.id.iv_icon);
//        iv_icon.setImageResource(R.drawable.ic_empty_service);
//        TextView tv_message=emptyView.findViewById(R.id.tv_message);
//        //tv_message.setText("暂无相关内容，请尝试其他筛选条件～");
//        tv_message.setText("");
//        getAdapter().setEmptyView(emptyView);
    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        ShopApi.searchShopList(keywords, pageNum + "", new ResponseImpl<ShopListBean>() {
            @Override
            public void onSuccess(ShopListBean data) {
                int total = Integer.parseInt(data.getData().getTotalpage());
                if (refresh) {
                    setPageNum(HttpResultUtil.onRefreshSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                } else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        }, ShopListBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, ShopListBean.DataBean.InfoBean item, int position) {
        View diver = helper.getView(R.id.view_diver);
        if (position == 0) {
            diver.setVisibility(View.VISIBLE);
        } else {
            diver.setVisibility(View.GONE);
        }
        ImageView iv_logo = helper.getView(R.id.iv_icon);
        TextView tv_shop_name = helper.getView(R.id.tv_title);
        TextView tv_memo = helper.getView(R.id.tv_des);
        TextView tv_price = helper.getView(R.id.tv_price);
        MaterialRatingBar ratingBar = helper.getView(R.id.ratingbar);
        TextView tv_comments = helper.getView(R.id.tv_comments);
        TextView tv_good_comment = helper.getView(R.id.tv_good_comments);
        ImageUtil.getInstance().loadImage(SearchShopListFragment.this, item.getLogo(), iv_logo);
        tv_shop_name.setText(item.getStore_name() + "");
        tv_memo.setText("个人简介：\n" + (TextUtils.isEmpty(item.getRemark()) ? "暂无" : item.getRemark()));
        tv_price.setText("￥：" + item.getPrice());
        ratingBar.setRating(Float.parseFloat(item.getRank_average()));
        // tv_comments.setText("评价："+item.getComment());
        tv_comments.setText("成交量：" + item.getOrder_count());
        tv_good_comment.setText("好评率：" + item.getPraise_rate() + "%");
        ImageView iv_vip = helper.getView(R.id.iv_vip);
        if (item.getAuth().equals("1")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_person_rt);
        } else if (item.getAuth().equals("2")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_business_rt);
        } else {
            iv_vip.setVisibility(View.GONE);
        }
    }


    public void search(String key) {
        keywords = key;
        onRefresh();
    }
}
