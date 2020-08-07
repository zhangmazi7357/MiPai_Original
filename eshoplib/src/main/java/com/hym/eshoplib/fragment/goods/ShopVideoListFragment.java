package com.hym.eshoplib.fragment.goods;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseListGridFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/7/26.
 * <p>
 * Description 店铺视频 或者图片
 * <p>
 * otherTips
 */

public class ShopVideoListFragment extends BaseListGridFragment<ShopProductsBean.DataBean.InfoBean> {
    ServiceDetailBean detailBean;
    Unbinder unbinder;

    public static ShopVideoListFragment newInstance(Bundle args) {
        ShopVideoListFragment fragment = new ShopVideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_shop_vadieo;
    }

    @Override
    public void excuteLogic() {
        detailBean = (ServiceDetailBean) getArguments().getSerializable("data");
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeApi.getProductDetailData(new BaseFragment.ResponseImpl<GoodDetailModel>() {
                    @Override
                    public void onSuccess(GoodDetailModel data) {
                        if (data.getData().getType().equals("1")){
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopVideoDetail);
                            bundle.putSerializable("data", data);
                            bundle.putString("title", "产品详情");
                            ActionActivity.start(_mActivity, bundle);
                        }else if (data.getData().getType().equals("2")){
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopDetail);
                            bundle.putSerializable("data", data);
                            bundle.putString("title", "产品详情");
                            ActionActivity.start(_mActivity, bundle);
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                    }
                }, GoodDetailModel.class, getAdapter().getData().get(position).getCase_id());
            }
        });
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        TextView tv_message = emptyView.findViewById(R.id.tv_message);
        tv_message.setText("暂无产品");
        ImageView iv_icon = emptyView.findViewById(R.id.iv_icon);
        iv_icon.setImageResource(R.drawable.ic_no_products);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_icon.getLayoutParams();
        layoutParams.setMargins(0, ScreenUtil.dip2px(_mActivity, 50), 0, 0);
        iv_icon.setLayoutParams(layoutParams);
        getAdapter().setEmptyView(emptyView);
    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        if (detailBean == null || TextUtils.isEmpty(detailBean.getData().getStore_id())) {
            dissMissDialog();
            getRefreshLayout().setRefreshing(false);
            ToastUtil.toast("数据异常");
            return;
        }
        ShopApi.getProductsList2(detailBean.getData().getStore_id(), pageNum + "", new ResponseImpl<ShopProductsBean>() {
            @Override
            public void onSuccess(ShopProductsBean data) {
                if (refresh) {
                    setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                } else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                }
            }
        }, ShopProductsBean.class);
    }

    @Override
    public void bindData(BaseViewHolder helper, ShopProductsBean.DataBean.InfoBean item, int position) {
        ImageView iv_play = helper.getView(R.id.iv_play);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_time_long = helper.getView(R.id.tv_time_long);
        TextView beforePrice = helper.getView(R.id.tv_before_price);
        beforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        TextView tv_see_time = helper.getView(R.id.tv_see_time); //金额

        TextView tvRmb = helper.getView(R.id.tv_rmb);
        // tvRmb.setVisibility(View.GONE);

        if (item.getType().equals("1")) {
            //视频
            //iv_play.setVisibility(View.VISIBLE);
            tv_time_long.setVisibility(View.VISIBLE);
            //tv_time_long.setText(item.getLength());
        } else if (item.getType().equals("2")) {
            //图片
            //iv_play.setVisibility(View.GONE);
            // tv_time_long.setVisibility(View.GONE);
        }
        if (item.getOriginal_price().equals("0") ||TextUtils.isEmpty(item.getOriginal_price())){
            beforePrice.setVisibility(View.GONE);
        }else{
            beforePrice.setText("¥"+item.getOriginal_price());
        }
        tv_title.setText(item.getTitle() + "");
        tv_see_time.setText(item.getPresent_price());
        tv_time_long.setText(item.getWeight()+"人付款");
        ImageView iv = helper.getView(R.id.iv_vadieo);
        ScreenUtil.ViewAdapter(_mActivity, iv, 16, 9, 20);
        Glide.with(_mActivity).load(item.getImage_default()).into(iv);
        //ImageUtil.getInstance().loadRoundCornerImage(ShopVadieoListFragment.this, item.getImage_default(), iv, 5);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
