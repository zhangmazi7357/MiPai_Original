package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.bean.goods.GoodsSearchHistoryBean;
import com.hym.eshoplib.http.CommonApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseListFragment;

/**
 * Created by 胡彦明 on 2018/2/24.
 * <p>
 * Description 搜索商品
 * <p>
 * otherTips
 */

public class SearchGoodsFragment extends BaseListFragment<String> {

    @BindView(R2.id.head_status_bar)
    View headStatusBar;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_right)
    TextView tvRight;
    @BindView(R2.id.et_search)
    EditText etSearch;
    @BindView(R2.id.tv_search)
    TextView tvSearch;
//    @BindView(R2.id.fl_search)
//    FrameLayout flSearch;
//    @BindView(R2.id.ll_toolbar_bg)
//    LinearLayout llToolbarBg;
    Unbinder unbinder;
    String type="1";//1默认，正常进入，搜索后跳入搜索结果界面，2，从搜索界面进入，只返回

    public static SearchGoodsFragment newInstance(Bundle args) {
        SearchGoodsFragment fragment = new SearchGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_goods_search_history;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public void excuteLogic() {
        type=getArguments().getString("type","1");
        String keyWords=getArguments().getString("keywords");
        etSearch.setHint(getResources().getString(R.string.EnterProductName));
        etSearch.setText(keyWords);
        etSearch.requestFocus();
        showSoftInput(etSearch);
        ivBack.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult("");
            }
        });
        etSearch.setVisibility(View.VISIBLE);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                hideSoftInput();
                setResult(getAdapter().getData().get(position));
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hideSoftInput();
                setResult(etSearch.getText().toString());
                return false;
            }
        });
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etSearch==null){
                    return;
                }
                if(hasFocus){
                    etSearch.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                }else {
                    etSearch.setGravity(Gravity.CENTER);
                }
            }
        });
        View header=LayoutInflater.from(_mActivity).inflate(R.layout.header_search_goods,null,false);
        View footer=LayoutInflater.from(_mActivity).inflate(R.layout.footer_search_goods,null,false);
        SuperButton btn_clear_all=(SuperButton) footer.findViewById(R.id.btn_clear_all);
        btn_clear_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonApi.deleteSearchHistory(_mActivity, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        getAdapter().getData().clear();
                        getAdapter().notifyDataSetChanged();
                       // ToastUtil.toast("Success");

                    }
                },Object.class);
            }
        });
        getAdapter().addHeaderView(header);
        getAdapter().addFooterView(footer);


    }
    public void setResult(String result){
        Bundle bundle=new Bundle();
        bundle.putString("keywords",result);
       if(type.equals("2")){
           setFragmentResult(RESULT_OK,bundle);
           pop();
       }else {
           //进入搜索结果
           startWithPop(GoodsOrderByFragment.newInstance(bundle));

       }
    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        CommonApi.getSearchHistory(_mActivity, new ResponseImpl<GoodsSearchHistoryBean>() {
            @Override
            public void onSuccess(GoodsSearchHistoryBean data) {
                getAdapter().setNewData(data.getData().getHistory());

            }
        },GoodsSearchHistoryBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, String item, int position) {
        helper.setText(R.id.tv_name,item);

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
