package cn.hym.superlib.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.hym.superlib.R;
import cn.hym.superlib.adapter.BaseListAdapter;

/**
 * Created by 胡彦明 on 2017/7/27.
 * <p>
 * Description 通用列表类型fragment
 * <p>
 * otherTips
 */

public abstract class BaseListFragment<E> extends BaseKitFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private RecyclerView rv_list;
    private LinearLayout ll_container;
    private BaseListAdapter<E> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewStub vs_header, vs_footer;//扩展头部,脚部
    private int pageNum = 1;//当前页数;
    private int pageSize = 10;//默认每页条数
    public List<E> data;
    private int defaultRefreshingTime = 0;//默认刷新延迟时间,
    private ImageView iv_add;
    private TextView tv_add;
    private boolean isshowTop=false;



    public void setIsshowTop(boolean isshowTop) {
        this.isshowTop = isshowTop;
    }

    //根布局id
    @Override
    public int getContentViewResId() {
        return R.layout.layout_commonlist;
    }

    public void setDefaultRefreshingTime(int defaultRefreshingTime) {
        this.defaultRefreshingTime = defaultRefreshingTime;
    }

    public RecyclerView getRv_list() {
        return rv_list;
    }

    public RecyclerView getCustomRvList() {
        return null;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ViewStub getVs_header() {
        return vs_header;
    }

    public ViewStub getVs_footer() {
        return vs_footer;
    }

    public ImageView getIv_add() {
        return iv_add;
    }

    public TextView getTv_add() {
        return tv_add;
    }



    public void setRv_list(RecyclerView rv_list) {
        this.rv_list = rv_list;
    }

    //设置跟布局背景色
    public void setContainerColor(@ColorRes int color) {
        if (ll_container != null) {
            ll_container.setBackgroundColor(ContextCompat.getColor(_mActivity, color));
        }
    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return swipeRefreshLayout;
    }

    public BaseListAdapter<E> getAdapter() {
        if (adapter == null) {
            initAdapter();
        }
        return adapter;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        if (getCustomRvList() != null) {
            rv_list = getCustomRvList();
        } else {
            rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        }
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        vs_header = (ViewStub) view.findViewById(R.id.vs_header);
        vs_footer = (ViewStub) view.findViewById(R.id.vs_footer);
        ll_container = (LinearLayout) view.findViewById(R.id.ll_container);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        iv_add = view.findViewById(R.id.iv_add);
        tv_add =  view.findViewById(R.id.tv_add);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onScroll(recyclerView,dx,dy);

            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_list.smoothScrollToPosition(0);
            }
        });
//        iv_add.setVisibility(View.VISIBLE);


    }
    public void onScroll(RecyclerView recyclerView, int dx, int dy){
        boolean canScrollVertically=rv_list.canScrollVertically(-1);
//                Logger.d("canScrollVertically="+canScrollVertically);
        if(canScrollVertically){
            if(isshowTop){
                iv_add.setVisibility(View.VISIBLE);
            }
        }else {
            //滑到顶部
            iv_add.setVisibility(View.GONE);
        }
    }

    @Override
    public void doLogic() {
        initAdapter();
        initRecyclerView();
        swipeRefreshLayout.setOnRefreshListener(this);
//        iv_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rv_list.smoothScrollToPosition(0);
//            }
//        });
//        iv_add.setVisibility(View.VISIBLE);
        excuteLogic();

    }

    private void initRecyclerView() {
        rv_list.setLayoutManager(getLayoutManager());
        rv_list.setAdapter(adapter);

    }

    private void initAdapter() {
        adapter = new BaseListAdapter<E>(getItemRestId(), data) {
            @Override
            public void handleView(BaseViewHolder helper, E item, int position) {
                bindData(helper, item, position);

            }
        };
        if (enableLoadMore()) {
            adapter.setOnLoadMoreListener(this, rv_list);
        }


    }

    //是否可以加载更多
    public boolean enableLoadMore() {
        return true;
    }

    //获取布局管理器，默认线性
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(_mActivity);
    }

    public RecyclerView.LayoutManager getGridLayoutManager() {
        return new GridLayoutManager(_mActivity,2);
    }


    //初始化数据，初始化数据时，显示dialog
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setShowProgressDialog(true);
        showProgressDialog();
        getData(true, pageSize, pageNum);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        //下拉刷新
        setShowProgressDialog(false);
        pageNum = 1;
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        if (defaultRefreshingTime > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getData(true, pageSize, pageNum);
                }
            }, defaultRefreshingTime);
        } else {
            getData(true, pageSize, pageNum);
        }


    }

    //加载更多
    @Override
    public void onLoadMoreRequested() {
        //加载更多
        setShowProgressDialog(false);
        if (defaultRefreshingTime > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getData(false, pageSize, pageNum);
                }
            }, defaultRefreshingTime);
        } else {
            getData(false, pageSize, pageNum);
        }
    }

    public abstract
    @LayoutRes
    int getItemRestId();

    public abstract void excuteLogic();//执行逻辑

    public abstract void getData(boolean refresh, int pageSize, int pageNum);

    public abstract void bindData(BaseViewHolder helper, E item, int position);
}
