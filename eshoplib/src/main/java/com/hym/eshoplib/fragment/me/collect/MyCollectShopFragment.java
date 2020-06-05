package com.hym.eshoplib.fragment.me.collect;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.me.MyCollectShopBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/27.
 * <p>
 * Description 我收藏的工作室
 * <p>
 * otherTips
 */

public class MyCollectShopFragment extends BaseListFragment<MyCollectShopBean.DataBean.InfoBean>{

    public static MyCollectShopFragment newInstance(Bundle args) {
        MyCollectShopFragment fragment = new MyCollectShopFragment();
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
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String content_id=getAdapter().getData().get(position).getContent_id();
                Bundle bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,ActionActivity.Action_ShopDetail);
                bundle.putString("id",content_id);
                ActionActivity.start(_mActivity,bundle );
            }
        });
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv_icon=emptyView.findViewById(R.id.iv_icon);
        iv_icon.getLayoutParams().height= ScreenUtil.dip2px(_mActivity,103);
        iv_icon.getLayoutParams().width= ScreenUtil.dip2px(_mActivity,103);
        iv_icon.setImageResource(R.drawable.ic_empty_collect);
        TextView tv_message=emptyView.findViewById(R.id.tv_message);
        tv_message.setText("当前还没有收藏哦~");
        getAdapter().setEmptyView(emptyView);
        getAdapter().setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                String confirm = getResources().getString(R.string.Confirm);
                String cancle = getResources().getString(R.string.Cancel);
                Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "提示", "您确定要取消收藏么", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                    @Override
                    public void onCancleClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();

                    }

                    @Override
                    public void onConfirmeClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        ShopApi.AddFavorite(getAdapter().getData().get(position).getStore_id(), "store", new ResponseImpl<AddFavouriteBean>() {
                            @Override
                            public void onSuccess(AddFavouriteBean data) {
                               getAdapter().remove(position);
                            }
                        }, AddFavouriteBean.class);
                    }
                });
                pDialog.show();
                return true;
            }
        });
    }



    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        MeApi.getCollectShop(pageNum + "", new ResponseImpl<MyCollectShopBean>() {
            @Override
            public void onSuccess(MyCollectShopBean data) {
                int total=Integer.parseInt(data.getData().getTotalpage());
                if(refresh){
                    HttpResultUtil.onRefreshSuccess(total,pageNum,data.getData().getInfo(),getAdapter());
                }else {
                    HttpResultUtil.onLoardMoreSuccess(total,pageNum,data.getData().getInfo(),getAdapter());
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        },MyCollectShopBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, MyCollectShopBean.DataBean.InfoBean item, int position) {
        View diver=helper.getView(R.id.view_diver);
        if(position==0){
            diver.setVisibility(View.VISIBLE);
        }else {
            diver.setVisibility(View.GONE);
        }
        ImageUtil.getInstance().loadImage(MyCollectShopFragment.this,item.getLogo(), (ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title,item.getStore_name()+"");
        helper.setText(R.id.tv_des,"个人简介\n"+item.getRemark());
        helper.setText(R.id.tv_price,"￥："+item.getPrice());
        MaterialRatingBar ratingBar=helper.getView(R.id.ratingbar);
        ratingBar.setRating(Float.parseFloat(item.getRank_average()));
        helper.setText(R.id.tv_comments,"评价："+item.getComment());
        helper.setText(R.id.tv_good_comments,"好评："+item.getPraise_rate()+"%");


    }

}
