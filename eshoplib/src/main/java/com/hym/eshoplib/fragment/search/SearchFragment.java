package com.hym.eshoplib.fragment.search;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.SearchHistoryBean;
import com.hym.eshoplib.fragment.search.mz.MzSearchResultActivity;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.home.HomeApi;
import com.hym.eshoplib.mz.MzConstant;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2017/5/31.
 * <p>
 * Description 搜索
 * <p>
 * otherTips
 */

public class SearchFragment extends BaseKitFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.fl_hot)
    TagFlowLayout flHot;
    @BindView(R.id.fl_history)
    TagFlowLayout flHistory;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
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
    @BindView(R.id.btn_clear)
    Button btnClear;

    public static SearchFragment newInstance(Bundle args) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_search;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public void doLogic() {
        ivBack.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.VISIBLE);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keywords = etSearch.getText().toString();
                    goSearch(keywords);
                }
                return false;
            }
        });
        swipeLayout.setOnRefreshListener(this);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogManager.getInstance().initSimpleDialog(_mActivity, "清除记录", "您确定要清除历史搜索记录么",
                        "取消", "确认", new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();
                                deleteHistory();
                            }
                        }).show();


            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getHistory();
    }

    @OnClick({R.id.tv_right, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    pop();
                } else {
                    _mActivity.finish();
                }
                break;
            case R.id.tv_search:

                //搜索按钮
                String keywords = etSearch.getText().toString();
                goSearch(keywords);

                break;
        }
    }

    private void getHistory() {
        dissMissDialog();
        GoodsApi.getSearchHistory(new ResponseImpl<SearchHistoryBean>() {
            @Override
            public void onSuccess(SearchHistoryBean data) {
                List<String> hots = data.getData().getHotword();
                List<String> history = data.getData().getHistory();
                flHot.setAdapter(new TagAdapter<String>(hots) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_search_tab, parent, false);
                        textView.setText(s);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goSearch(textView.getText().toString());
                            }
                        });
                        return textView;
                    }
                });
                flHistory.setAdapter(new TagAdapter<String>(history) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_search_tab, parent, false);
                        textView.setText(s);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goSearch(textView.getText().toString());
                            }
                        });
                        return textView;
                    }
                });
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
                showSoftInput(etSearch);
            }
        }, SearchHistoryBean.class);
    }


    @Override
    public void onRefresh() {
        getHistory();


    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return swipeLayout;
    }

    private void goSearch(String keywords) {
        hideSoftInput();

        //
//        Intent intent = new Intent(_mActivity, MzSearchResultActivity.class);
//        intent.putExtra(MzConstant.KEY_SEARCH_KEYWORD, keywords);
//        startActivity(intent);


        Bundle bundle = new Bundle();
        bundle.putString("keywords", keywords);
        start(SearchResultFragment.newInstance(bundle));

    }

    private void deleteHistory() {
        HomeApi.deleteHistory(new ResponseImpl<Object>() {
            @Override
            public void onStart(int mark) {
                setShowProgressDialog(true);
                super.onStart(mark);
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
            }

            @Override
            public void onSuccess(Object data) {
                flHistory.setAdapter(new TagAdapter(new ArrayList()) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        return null;
                    }
                });
            }
        }, Object.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void onSupportVisible() {
        super.onSupportVisible();
        getHistory();
    }
}
