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
import com.hym.eshoplib.bean.me.MyCollectProductBean;
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
 * Description 我的收藏产品
 * <p>
 * otherTips
 */

public class MyColledProductionListFragment extends BaseListFragment<MyCollectProductBean.DataBean.InfoBean>{

    public static MyColledProductionListFragment newInstance(Bundle args) {
        MyColledProductionListFragment fragment = new MyColledProductionListFragment();
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
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle;
                if(getAdapter().getData().get(position).getType().equals("1")){
                    //去视频详情
                    bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,ActionActivity.Action_vadieo_detail);
                    bundle.putInt("type",1);//从店铺产品中进入详情，不显示进入店铺按钮
                    bundle.putString("id",getAdapter().getData().get(position).getCase_id());
                    ActionActivity.start(_mActivity,bundle );
                }else {
                    //去图片详情
                    bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,ActionActivity.Action_image_detail);
                    bundle.putInt("type",1);//从店铺产品中进入详情，不显示进入店铺按钮
                    bundle.putString("id",getAdapter().getData().get(position).getCase_id());
                    ActionActivity.start(_mActivity, bundle);
                }
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
                        ShopApi.AddFavorite(getAdapter().getData().get(position).getCase_id(), "case", new ResponseImpl<AddFavouriteBean>() {
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

       MeApi.getCollectProduct(pageNum + "", new ResponseImpl<MyCollectProductBean>() {
            @Override
            public void onSuccess(MyCollectProductBean data) {
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }
            }
        },MyCollectProductBean.class);
    }

    @Override
    public void bindData(BaseViewHolder helper, MyCollectProductBean.DataBean.InfoBean item, int position) {
        View diver=helper.getView(R.id.view_diver);
        if(position==0){
            diver.setVisibility(View.VISIBLE);
        }else {
            diver.setVisibility(View.GONE);
        }
        ImageView iv_play=helper.getView(R.id.iv_play);
        TextView tv_title=helper.getView(R.id.tv_title);
        TextView tv_time_long=helper.getView(R.id.tv_time_long);
        TextView tv_see_time=helper.getView(R.id.tv_see_time);
        if(item.getType().equals("1")){
            //视频
          //  iv_play.setVisibility(View.VISIBLE);
            tv_time_long.setVisibility(View.VISIBLE);
            tv_time_long.setText(item.getLength());
        }else if(item.getType().equals("2")){
            //图片
          //  iv_play.setVisibility(View.GONE);
            tv_time_long.setVisibility(View.GONE);
        }
        tv_title.setText(item.getTitle()+"");
        tv_see_time.setText(item.getViews()+"次观看");
        ImageView iv=helper.getView(R.id.iv_bg);
        ScreenUtil.ViewAdapter(_mActivity, iv, 16, 9, 20);
        ImageUtil.getInstance().loadRoundCornerImage(MyColledProductionListFragment.this,item.getImage_default(),iv,5);
        //工作室信息
        ImageUtil.getInstance().loadCircleImage(MyColledProductionListFragment.this,item.getLogo(), (ImageView) helper.getView(R.id.iv_shop_logo));
        helper.setText(R.id.tv_shop_name,item.getStore_name()+"");
        MaterialRatingBar ratingBar=helper.getView(R.id.ratingbar);
        ratingBar.setRating(Float.parseFloat(item.getStore_rank()));
        TextView tv_zan=helper.getView(R.id.tv_zan);
        if(item.getIs_agree()==1){
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_check,0,0,0);
        }else {
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_uncheck,0,0,0);
        }
        tv_zan.setText(item.getAgree()+"");

    }
}
