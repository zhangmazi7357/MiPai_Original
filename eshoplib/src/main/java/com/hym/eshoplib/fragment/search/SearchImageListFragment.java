package com.hym.eshoplib.fragment.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/27.
 * <p>
 * Description 搜索结果页图片列表
 * <p>
 * otherTips
 */

public class SearchImageListFragment extends BaseListFragment<ShopProductsBean.DataBean.InfoBean> {
    String keywords = "";

    public static SearchImageListFragment newInstance(Bundle args) {
        SearchImageListFragment fragment = new SearchImageListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_search_result;
    }

    @Override
    public void excuteLogic() {
        keywords = getArguments().getString("keywords", "");

        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeApi.getProductDetailData(new BaseFragment.ResponseImpl<GoodDetailModel>() {
                    @Override
                    public void onSuccess(GoodDetailModel data) {
                        if (data.getData().getType().equals("1")) {
                            Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home, ActionActivity.ShopVideoDetail);
                            bundle.putSerializable("data", data);
                            bundle.putString("title", "产品详情");
                            ActionActivity.start(_mActivity, bundle);
                        } else if (data.getData().getType().equals("2")) {
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

               /* Bundle bundle=new Bundle();
                bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,ActionActivity.Action_image_detail);
              //  bundle.putInt("type",1);//从店铺产品中进入详情，不显示进入店铺按钮
                bundle.putString("id",getAdapter().getData().get(position).getCase_id());
                ActionActivity.start(_mActivity, bundle);*/
            }
        });

//        View empty_view = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
//        ImageView iv = empty_view.findViewById(R.id.iv_icon);
//        TextView tv_mesg = empty_view.findViewById(R.id.tv_message);
//        iv.setImageResource(R.drawable.ic_no_search_result);
//        tv_mesg.setText("没有搜到相关内容");
//        getAdapter().setEmptyView(empty_view);
    }


    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {

        ShopApi.getProductsList3(keywords, pageNum + "", "2", new ResponseImpl<ShopProductsBean>() {
            @Override
            public void onSuccess(ShopProductsBean data) {
                if (refresh) {
                    setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                } else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()), pageNum, data.getData().getInfo(), getAdapter()));
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
                // getAdapter().notifyDataSetChanged();
            }
        }, ShopProductsBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, ShopProductsBean.DataBean.InfoBean item, int position) {
        View diver = helper.getView(R.id.view_diver);
        if (position == 0) {
            diver.setVisibility(View.VISIBLE);
        } else {
            diver.setVisibility(View.GONE);
        }
        helper.getView(R.id.iv_play).setVisibility(View.GONE);
        helper.getView(R.id.tv_time_long).setVisibility(View.GONE);
        ImageView iv_bg = helper.getView(R.id.iv_bg);
        ScreenUtil.ViewAdapter(_mActivity, iv_bg, 16, 9, 20);
        ImageUtil.getInstance().loadRoundCornerImage(SearchImageListFragment.this, item.getImage_default(), iv_bg, 5);
        helper.setText(R.id.tv_title, item.getTitle() + "");
        ImageUtil.getInstance().loadCircleImage(SearchImageListFragment.this, item.getLogo(), (ImageView) helper.getView(R.id.iv_shop_logo));
        helper.setText(R.id.tv_shop_name, item.getStore_name());
        MaterialRatingBar ratingBar = helper.getView(R.id.ratingbar);
        ratingBar.setRating(Float.parseFloat(item.getStore_rank()));
        helper.setText(R.id.tv_see_time, item.getViews() + "次观看");
        TextView tv_zan = helper.getView(R.id.tv_zan);
        if (item.getIs_agree().equals("1")) {
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_check, 0, 0, 0);
        } else {
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_uncheck, 0, 0, 0);
        }
        tv_zan.setText(item.getAgree() + "");
        ImageView iv_vip = helper.getView(R.id.iv_vip);
        if (item.getAuth().equals("1")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_person_circle);
        } else if (item.getAuth().equals("2")) {
            iv_vip.setVisibility(View.VISIBLE);
            iv_vip.setImageResource(R.drawable.ic_business_circle);
        } else {
            iv_vip.setVisibility(View.GONE);
        }


    }

    public void search(String key) {
        keywords = key;
        onRefresh();
    }
}
