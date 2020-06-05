package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.adapter.ShopListAdapter;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;
import com.hym.eshoplib.http.home.HomeApi;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseFragment;
import cn.hym.superlib.fragment.base.BaseKitFragment;

public class MoreShopListFragment extends BaseKitFragment {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    Unbinder unbinder;
    @BindView(R.id.srf_layout)
    SmartRefreshLayout srfLayout;
    private ShopListAdapter shopListAdapter;
    private int currentPage = 1;
    private boolean isRefrash;
    private String title;

    public static MoreShopListFragment newInstance(Bundle args) {
        MoreShopListFragment fragment = new MoreShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLogic() {
        showBackButton();
        title = getArguments().getString("title");
        setTitle(title);
        srfLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefrash = true;
                getData(isRefrash);
            }
        });

        srfLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefrash = false;
                getData(isRefrash);
            }
        });
    }

    private void getData(final boolean refrash) {
        if (refrash){
            currentPage= 1;
        }else{
            currentPage++;
        }
        if (title.equals("限时特惠")){
            HomeApi.getSpecialTimeLimteData(currentPage+"",new ResponseImpl<SpecialTimeLimteBean>() {
                @Override
                public void onSuccess(SpecialTimeLimteBean data) {
                    if (refrash){
                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();
                        shopListAdapter.setNewData(video);
                        shopListAdapter.notifyDataSetChanged();
                        srfLayout.finishRefresh();
                    }else{
                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();
                        shopListAdapter.addData(video);
                        //shopListAdapter.setNewData(video);
                        shopListAdapter.notifyDataSetChanged();
                        srfLayout.finishLoadMore();
                    }
                }
            }, SpecialTimeLimteBean.class);
        }else if (title.equals("觅拍严选")){
            HomeApi.getStrictSelectData(currentPage+"",new ResponseImpl<SpecialTimeLimteBean>() {
                @Override
                public void onSuccess(SpecialTimeLimteBean data) {
                    if (refrash){
                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();
                        shopListAdapter.setNewData(video);
                        shopListAdapter.notifyDataSetChanged();
                        srfLayout.finishRefresh();
                    }else{
                        List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getData().getVideo();
                        shopListAdapter.addData(video);
                        //shopListAdapter.setNewData(video);
                        shopListAdapter.notifyDataSetChanged();
                        srfLayout.finishLoadMore();
                    }
                }
            }, SpecialTimeLimteBean.class);
        }
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SpecialTimeLimteBean.DataBean data = (SpecialTimeLimteBean.DataBean) getArguments().getSerializable("data");
        final List<SpecialTimeLimteBean.DataBean.VideoBean> video = data.getVideo();
        rvView.setLayoutManager(new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false));
        shopListAdapter = new ShopListAdapter(R.layout.item_shop, video);
        rvView.setAdapter(shopListAdapter);
        shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String case_id = video.get(position).getCase_id();
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
                }, GoodDetailModel.class, case_id);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_add_more;
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
