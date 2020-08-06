package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/27.
 * <p>
 * Description 更多视频
 * <p>
 * otherTips
 */

public class MoreVadieoListFragment extends BaseListFragment<ShopProductsBean.DataBean.InfoBean> {


    Unbinder unbinder;
    @BindView(R.id.head_status_bar)
    View headStatusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.tv_right)
    TextView tvRight;
//    @BindView(R.id.iv_toolbar_right)
//    ImageView ivToolbarRight;
//    @BindView(R.id.ll_toolbar_bg)
//    LinearLayout llToolbarBg;

    public static MoreVadieoListFragment newInstance(Bundle args) {
        MoreVadieoListFragment fragment = new MoreVadieoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_search_result;
    }

    @Override
    public void excuteLogic() {
        setIsshowTop(true);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        etSearch.setVisibility(View.VISIBLE);
        tvSearch.setVisibility(View.GONE);
        etSearch.setHint("搜索视频");
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onRefresh();
                hideSoftInput();
                return false;
            }
        });
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle= BaseActionActivity.getActionBundle(ActionActivity.ModelType_Home,ActionActivity.Action_vadieo_detail);
               // bundle.putInt("type",1);//从店铺产品中进入详情，不显示进入店铺按钮
                bundle.putString("id",getAdapter().getData().get(position).getCase_id());
                ActionActivity.start(_mActivity,bundle );
            }
        });
        View empty_view = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv = empty_view.findViewById(R.id.iv_icon);
        TextView tv_mesg = empty_view.findViewById(R.id.tv_message);
        iv.setImageResource(R.drawable.ic_no_search_result);
        tv_mesg.setText("没有搜到相关内容");
        getAdapter().setEmptyView(empty_view);
    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        ShopApi.getProductsList4(etSearch.getText().toString(),pageNum + "","1", new ResponseImpl<ShopProductsBean>() {
            @Override
            public void onSuccess(ShopProductsBean data) {
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(Integer.parseInt(data.getData().getTotalpage()),pageNum,data.getData().getInfo(),getAdapter()));
                }
            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
                getAdapter().notifyDataSetChanged();
            }
        },ShopProductsBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, ShopProductsBean.DataBean.InfoBean item, int position) {
        View diver = helper.getView(R.id.view_diver);
        if (position == 0) {
            diver.setVisibility(View.VISIBLE);
        } else {
            diver.setVisibility(View.GONE);
        }
        ImageView iv_bg=helper.getView(R.id.iv_bg);
        ScreenUtil.ViewAdapter(_mActivity, iv_bg, 16, 9, 20);
        ImageUtil.getInstance().loadRoundCornerImage(MoreVadieoListFragment.this,item.getImage_default(),iv_bg,5);
        helper.setText(R.id.tv_title,item.getTitle()+"");
        ImageUtil.getInstance().loadImage(MoreVadieoListFragment.this,item.getLogo(), (ImageView) helper.getView(R.id.iv_shop_logo));
        helper.setText(R.id.tv_shop_name,item.getStore_name());
        MaterialRatingBar ratingBar=helper.getView(R.id.ratingbar);
        ratingBar.setRating(Float.parseFloat(item.getStore_rank()));
        helper.setText(R.id.tv_time_long,item.getLength()+"");
        helper.setText(R.id.tv_see_time,item.getViews()+"次观看");
        TextView tv_zan=helper.getView(R.id.tv_zan);
        if(item.getIs_agree().equals("1")){
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_check,0,0,0);
        }else {
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zan_uncheck,0,0,0);
        }
        tv_zan.setText(item.getAgree()+"");

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

    @Override
    public boolean openEventBus() {
        return true;

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateData(UpdateDataEvent event) {
        onRefresh();
    }
}
