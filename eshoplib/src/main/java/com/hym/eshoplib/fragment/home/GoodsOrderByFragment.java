package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.goods.GoodsDetailActivity;
import com.hym.eshoplib.bean.home.GoodsListBean;
import com.hym.eshoplib.bean.shoppingcart.ShoppingcarCountBean;
import com.hym.eshoplib.http.home.EshopHomeApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.action.BaseActionEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/3/20.
 * <p>
 * Description 商品排序 销量，价格
 * <p>
 * otherTips
 */

public class GoodsOrderByFragment extends BaseListFragment<GoodsListBean.DataBean.InfoBean>{
    String search;
    String category_id;
    String order;//排序类别-非必须(价格:2, 销量:3,默认销量)
    String sort;//排序-非必须（0：降序，1：升序，默认降序）
    ImageView iv_shopping_car,ivBack,ivRight;
    TextView tv_num,tvSearch,tvRight;
    TextView tv_sales,tv_price;
    private boolean hasinit=false;
    FrameLayout fl_sales,fl_price;
    public static GoodsOrderByFragment newInstance(Bundle args) {
        GoodsOrderByFragment fragment = new GoodsOrderByFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_goods_orderby;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }


    @Override
    public int getItemRestId() {
        return R.layout.item_goods_gridlist;
    }
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(_mActivity,2);
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        tvSearch= (TextView) view.findViewById(R.id.tv_search);
        ivBack= (ImageView) view.findViewById(R.id.iv_back);
        tvRight= (TextView) view.findViewById(R.id.tv_right);
        ivRight= (ImageView) view.findViewById(R.id.iv_toolbar_right);
        iv_shopping_car= (ImageView) view.findViewById(R.id.iv_shopping_car);
        tv_num= (TextView) view.findViewById(R.id.tv_num);
        getVs_header().setLayoutResource(R.layout.layout_orderby);
        View header=getVs_header().inflate();
        tv_sales= (TextView) header.findViewById(R.id.tv_sales);
        tv_price= (TextView) header.findViewById(R.id.tv_price);
        fl_sales= (FrameLayout) header.findViewById(R.id.fl_sales);
        fl_price= (FrameLayout) header.findViewById(R.id.fl_price);
        fl_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String order;//排序类别-非必须(价格:2, 销量:3,默认销量)
                //String sort;//排序-非必须（2：降序，1：升序，默认降序）
                order="3";
                tv_sales.setSelected(!tv_sales.isSelected());
                if(tv_sales.isSelected()){
                    //升序
                    sort="1";
                    tv_sales.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_1),null);
                }else {
                    //降序
                    sort="2";
                    tv_sales.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_2),null);
                }
               //将价格设置到 默认状态，同时select 设置为false
                tv_price.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_3),null);
                tv_price.setSelected(false);
                Logger.d("id="+category_id+",search="+search+",order="+order+",sort="+sort);
                onRefresh();

            }
        });
        fl_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order="2";
                tv_price.setSelected(!tv_price.isSelected());
                if(tv_price.isSelected()){
                    //升序
                    sort="1";
                    tv_price.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_1),null);
                }else {
                    //降序
                    sort="2";
                    tv_price.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_2),null);
                }
                //将销量设置到 默认状态 同时select 设置为true ,因为，销量默认是降序
                tv_sales.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_type_3),null);
                tv_sales.setSelected(true);
                Logger.d("id="+category_id+",search="+search+",order="+order+",sort="+sort);
                onRefresh();
            }
        });

    }

    @Override
    public void excuteLogic() {
        setContainerColor(R.color.resource_gray_efefef);
        tvSearch.setVisibility(View.VISIBLE);
        tvSearch.setHint(R.string.EnterProductName);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("type","2");
                startForResult(SearchGoodsFragment.newInstance(bundle),0x01);

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    pop();
                } else {
                    _mActivity.finish();
                }
            }
        });
        //处理的toolbar的各种显示和点击
        tvRight.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.ic_message);
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseActionEvent(1));
            }
        });
        category_id=getArguments().getString("id");
        search=getArguments().getString("keywords","");
        tvSearch.setText(search);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("id",getAdapter().getData().get(position).getSpecification_id());
                GoodsDetailActivity.start(_mActivity,bundle);
            }
        });
        iv_shopping_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopActionActivity.start(_mActivity, BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Shoppingcart,EshopActionActivity.Action_shoppingcart_main));
            }
        });

    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        EshopHomeApi.GetGoodsList(_mActivity, category_id,search, order, sort, pageNum + "", new ResponseImpl<GoodsListBean>() {
            @Override
            public void onSuccess(GoodsListBean data) {
                int total=data.getData().getTotalpage();
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }
                hasinit=true;
                getShoppingCarCount(hasinit);

            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
                hasinit=true;
                getShoppingCarCount(hasinit);

            }
        },GoodsListBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, GoodsListBean.DataBean.InfoBean item, int position) {
        String url=item.getImage_default();
        ImageView iv_icon= helper.getView(R.id.iv_icon);
        helper.setText(R.id.tv_name,item.getName()+"");
        helper.setText(R.id.tv_price,item.getPrice()+" RMB");
        //适配
        RelativeLayout rl=helper.getView(R.id.rl_container);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) rl.getLayoutParams();
        if(position%2>0){
            //右侧
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity,5),ScreenUtil.dip2px(_mActivity,10),ScreenUtil.dip2px(_mActivity,10),0);
        }else {
            //左侧
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity,10),ScreenUtil.dip2px(_mActivity,10),ScreenUtil.dip2px(_mActivity,5),0);
        }
        rl.setLayoutParams(layoutParams);
        //适配图片比例16比9
        int height=(ScreenUtil.getScreenWidth(_mActivity)/2-ScreenUtil.dip2px(_mActivity,35));
        ViewGroup.LayoutParams ivLayoutParams=iv_icon.getLayoutParams();
        ivLayoutParams.height=height;
        iv_icon.setLayoutParams(ivLayoutParams);
        //加载图片
        ImageUtil.getInstance().loadImage(GoodsOrderByFragment.this,url, iv_icon);
        helper.getView(R.id.ll_area).setVisibility(View.GONE);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //获取购物车数量
        getShoppingCarCount(hasinit);
    }
    private void getShoppingCarCount(boolean hasinit) {
        if(hasinit){
            ShoppingCarApi.getShoppingCarCount(_mActivity, new ResponseImpl<ShoppingcarCountBean>() {
                @Override
                public void onStart(int mark) {
                    setShowProgressDialog(false);
                    super.onStart(mark);
                }

                @Override
                public void onFinish(int mark) {
                    super.onFinish(mark);
                    setShowProgressDialog(true);
                }
                @Override
                public void onSuccess(ShoppingcarCountBean data) {
                    tv_num.setVisibility(View.GONE);
                    try {
                        int count=Integer.parseInt(data.getData().getQuantity());
                        if(count==0){
                            tv_num.setVisibility(View.GONE);
                        } else if(count>99){
                            tv_num.setText("99+");
                            tv_num.setVisibility(View.VISIBLE);
                        }else {
                            tv_num.setText(count+"");
                            tv_num.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception e){

                    }
                }

            },ShoppingcarCountBean.class);
        }
    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0x01:
                if(resultCode==RESULT_OK){
                    search= data.getString("keywords","");
                    tvSearch.setText(search);
                    //搜索
                    onRefresh();

                }
                break;
        }
    }
}
