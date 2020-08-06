package com.hym.eshoplib.fragment.city;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.RegionBean;
import com.hym.eshoplib.bean.city.SearchCityBean;
import com.hym.eshoplib.http.home.HomeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/10/30.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class SearchCityResultFragment extends BaseListFragment<SearchCityBean.DataBean.InfoBean> {
    public static SearchCityResultFragment newInstance(Bundle args) {
        SearchCityResultFragment fragment = new SearchCityResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
    Unbinder unbinder;
    String name = "";
    TextView tv_mesg;

    @Override
    public int getItemRestId() {
        return R.layout.item_check;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public void excuteLogic() {
        etSearch.setVisibility(View.VISIBLE);
        etSearch.setHint("搜索/城市名");
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name = etSearch.getText().toString();
                    if (TextUtils.isEmpty(name)) {
                        ToastUtil.toast("请输入城市名");

                    } else {
                        onRefresh();
                    }
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = etSearch.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    onRefresh();
                }
            }
        });
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ToastUtil.toast("选择城市");
                hideSoftInput();
                final String name = getAdapter().getData().get(position).getRegion_name();
                HomeApi.ChangeRegion(name, new ResponseImpl<RegionBean>() {
                    @Override
                    public void onSuccess(RegionBean data) {
                        SharePreferenceUtil.setStringData(_mActivity, "region_name", name);
                        SharePreferenceUtil.setStringData(_mActivity, "region_id", data.getData().getRegion_id());
                        ToastUtil.toast("定位成功");
                        _mActivity.finish();
                    }
                }, RegionBean.class);
            }
        });
        etSearch.requestFocus();
        showSoftInput(etSearch);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        View empty_view = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv = empty_view.findViewById(R.id.iv_icon);
        iv.getLayoutParams().height = ScreenUtil.dip2px(_mActivity, 180);
        iv.getLayoutParams().width = ScreenUtil.dip2px(_mActivity, 130);
        tv_mesg = empty_view.findViewById(R.id.tv_message);
        iv.setImageResource(R.drawable.ic_empty_city);
        tv_mesg.setText("搜索您的所在城市");
        getAdapter().setEmptyView(empty_view);

    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        if (TextUtils.isEmpty(name)) {
            getAdapter().setNewData(null);
            dissMissDialog();
            getRefreshLayout().setRefreshing(false);
            return;
        }
        HomeApi.GetArea(etSearch.getText().toString(), new ResponseImpl<SearchCityBean>() {
            @Override
            public void onSuccess(SearchCityBean data) {
                int total = data.getData().getTotalpage();
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
                tv_mesg.setText("暂无该城市");
            }
        }, SearchCityBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, SearchCityBean.DataBean.InfoBean item, int position) {
        helper.setText(R.id.text, item.getRegion_name() + "");

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
