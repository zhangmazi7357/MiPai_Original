package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
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
import com.hym.eshoplib.listener.ShopItemHeightListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseFragment;

public class TabFragment extends BaseFragment {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    Unbinder unbinder;
    private List<SpecialTimeLimteBean.DataBean.VideoBean> al = new ArrayList<>();
    private ShopListAdapter shopListAdapter;
    private int height;
    private ShopItemHeightListener commentListen;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(gridLayoutManager);
        SpecialTimeLimteBean.DataBean.VideoBean videoBean = new SpecialTimeLimteBean.DataBean.VideoBean();
        SpecialTimeLimteBean.DataBean.VideoBean videoBean1 = new SpecialTimeLimteBean.DataBean.VideoBean();
        al.add(videoBean);
        al.add(videoBean1);
        shopListAdapter = new ShopListAdapter(R.layout.item_shop, al);
        rvView.setAdapter(shopListAdapter);
        shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String case_id = al.get(position).getCase_id();
                HomeApi.getProductDetailData(new ResponseImpl<GoodDetailModel>() {
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
                }, GoodDetailModel.class, case_id);
            }
        });
    }

    public static TabFragment newInstance(FragmentActivity _mActivity, String s) {
        TabFragment localNewsTabFragment = new TabFragment();
        Bundle localBundle = new Bundle();
        localBundle.putString("title", s);
        localNewsTabFragment.setArguments(localBundle);
        return localNewsTabFragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public int getContentViewResId() {
        return R.layout.tab_item;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    public int itemHeight() {
        return height;
    }

    public void updataData(List<SpecialTimeLimteBean.DataBean.VideoBean> data1) {

        if (al.size() > 0) {
            al.clear();
        }
        al = data1;
        if (shopListAdapter != null) {
            shopListAdapter.setNewData(data1);
            shopListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void adddataData(List<SpecialTimeLimteBean.DataBean.VideoBean> video) {
/*
        if (al.size()>0){
            al.clear();
        }
*/
        al = video;
        if (shopListAdapter != null) {
            shopListAdapter.addData(video);
            shopListAdapter.notifyDataSetChanged();
        }
    }
}
