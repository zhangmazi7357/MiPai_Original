package com.hym.eshoplib.fragment.home;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.goods.GoodsDetailActivity;
import com.hym.eshoplib.adapter.SortButtonAdapter;
import com.hym.eshoplib.bean.home.EshopHomeDataBean;
import com.hym.eshoplib.bean.shoppingcart.ShoppingcarCountBean;
import com.hym.eshoplib.http.home.EshopHomeApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.action.BaseActionEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.GlideBanerImageLoader;
import cn.hym.superlib.utils.view.ScreenUtil;
import fj.mtsortbutton.lib.DynamicSoreView;
import fj.mtsortbutton.lib.Interface.IDynamicSore;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 商城首页
 * <p>
 * otherTips
 */

public class EshopMainFragment extends BaseListFragment<EshopHomeDataBean.DataBean.InfoBean> implements IDynamicSore {
    ImageView iv_shopping_car, ivBack, ivRight;
    TextView tv_num, tvSearch, tvRight;
    View header;
    FrameLayout flSerach;
    private boolean hasinit = false;
    SortButtonAdapter adapter;

    public static EshopMainFragment newInstance(Bundle args) {
        EshopMainFragment fragment = new EshopMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Banner banner;
    DynamicSoreView dynamicSoreView;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_eshop_main;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_goods_gridlist;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(_mActivity, 2);
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        flSerach = (FrameLayout) view.findViewById(R.id.fl_search);
        tvSearch = (TextView) view.findViewById(R.id.tv_search);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        ivRight = (ImageView) view.findViewById(R.id.iv_toolbar_right);
        header = LayoutInflater.from(_mActivity).inflate(R.layout.header_eshop_main, null, false);
        banner = (Banner) header.findViewById(R.id.banner);
        dynamicSoreView = (DynamicSoreView) header.findViewById(R.id.dynamicSoreView);
        iv_shopping_car = (ImageView) view.findViewById(R.id.iv_shopping_car);
        tv_num = (TextView) view.findViewById(R.id.tv_num);
    }

    @Override
    public void excuteLogic() {
        setContainerColor(R.color.resource_gray_efefef);
        tvSearch.setVisibility(View.VISIBLE);
        tvSearch.setHint(R.string.EnterProductName);
        flSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = BaseActionActivity.getActionBundle
                        (EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_search);
                EshopActionActivity.
                        start(_mActivity, bundle);


            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopActionActivity.start(_mActivity, BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_classic));
            }
        });
        //处理的toolbar的各种显示和点击
        ivBack.setImageResource(R.drawable.ic_classic);
        tvRight.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.ic_message);
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseActionEvent(1));
            }
        });
        //适配banner
        ScreenUtil.ViewAdapter(_mActivity, banner, 16, 9);
        dynamicSoreView.setiDynamicSore(this);
        dynamicSoreView.setGridView(R.layout.viewpager_page);
        dynamicSoreView.showIndicater(false);
        getAdapter().addHeaderView(header);
        iv_shopping_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EshopActionActivity.start(_mActivity, BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Shoppingcart, EshopActionActivity.Action_shoppingcart_main));
            }
        });
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", getAdapter().getData().get(position).getSpecification_id());
                GoodsDetailActivity.start(_mActivity, bundle);
            }
        });

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (SharePreferenceUtil.getBooleangData(_mActivity, "newmsg")) {
            ivRight.setImageResource(R.drawable.ic_message_unread);
        } else {
            ivRight.setImageResource(R.drawable.ic_message);
        }
        //获取购物车数量
        Logger.d("Visiable");
        getShoppingCarCount(hasinit);
    }

    private void getShoppingCarCount(boolean hasinit) {
        if (hasinit) {
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
                        int count = Integer.parseInt(data.getData().getQuantity());
                        if (count == 0) {
                            tv_num.setVisibility(View.GONE);
                        } else if (count > 99) {
                            tv_num.setText("99+");
                            tv_num.setVisibility(View.VISIBLE);
                        } else {
                            tv_num.setText(count + "");
                            tv_num.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {

                    }
                }
            }, ShoppingcarCountBean.class);
        }
    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        EshopHomeApi.getHomeData(_mActivity, pageNum + "", new ResponseImpl<EshopHomeDataBean>() {
            @Override
            public void onSuccess(EshopHomeDataBean data) {
                int total = data.getData().getTotalpage();
                if (refresh) {
                    updateBanner(data.getData().getBanner());
                    initTab(data.getData().getCategory());
                    setPageNum(HttpResultUtil.onRefreshSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                } else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total, pageNum, data.getData().getInfo(), getAdapter()));
                }
                hasinit = true;
                getShoppingCarCount(hasinit);

            }

            @Override
            public void onDataError(String status, String errormessage) {
                super.onDataError(status, errormessage);
                hasinit = false;
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
            }
        }, EshopHomeDataBean.class);

    }

    private void initTab(List<EshopHomeDataBean.DataBean.CategoryBean> category) {
        dynamicSoreView.init(category);
        dynamicSoreView.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.white));
    }

    private void updateBanner(List<EshopHomeDataBean.DataBean.BannerBean> bannerData) {
        if (banner == null) {
            return;
        }
        ArrayList<String> data = new ArrayList<>();
        for (EshopHomeDataBean.DataBean.BannerBean bean : bannerData) {
            data.add(bean.getFilepath());
        }
        banner.setImageLoader(new GlideBanerImageLoader());
        banner.isAutoPlay(true);
        banner.update(data);

    }

    @Override
    public void bindData(BaseViewHolder helper, EshopHomeDataBean.DataBean.InfoBean item, int position) {
        String url = item.getImage_default();
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        helper.setText(R.id.tv_name, item.getName() + "");
        helper.setText(R.id.tv_price, item.getPrice() + " RMB");
        //适配
        RelativeLayout rl = helper.getView(R.id.rl_container);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl.getLayoutParams();
        if (position % 2 > 0) {
            //右侧
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity, 5), ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10), 0);
        } else {
            //左侧
            layoutParams.setMargins(ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 10), ScreenUtil.dip2px(_mActivity, 5), 0);
        }
        rl.setLayoutParams(layoutParams);
        //适配图片比例16比9
        int height = (ScreenUtil.getScreenWidth(_mActivity) / 2 - ScreenUtil.dip2px(_mActivity, 35));
        ViewGroup.LayoutParams ivLayoutParams = iv_icon.getLayoutParams();
        ivLayoutParams.height = height;
        iv_icon.setLayoutParams(ivLayoutParams);
        //加载图片
        ImageUtil.getInstance().loadImage(EshopMainFragment.this, url, iv_icon);
        helper.getView(R.id.ll_area).setVisibility(View.GONE);

    }

    //tab回调，对grideview 设置数据
    @Override
    public void setGridView(View view, final int type, List data) {
        List<EshopHomeDataBean.DataBean.CategoryBean> buttons = data;
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        dynamicSoreView.setNumColumns(gridView);
        adapter = new SortButtonAdapter(EshopMainFragment.this, buttons);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ToastUtil.toast("第"+type+"页"+position);
                Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_orderby);
                String category_id = adapter.getItem(position).getCategory_id();
                String name = adapter.getItem(position).getCategory_name();
                bundle.putString("id", category_id);
                bundle.putString("name", name);
                EshopActionActivity.start(_mActivity, bundle);
            }
        });

    }

}
