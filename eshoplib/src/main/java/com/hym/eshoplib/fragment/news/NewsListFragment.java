package com.hym.eshoplib.fragment.news;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.news.NewsListBean;
import com.hym.eshoplib.http.news.NewsApi;
import com.hym.eshoplib.util.ChatUtils;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.user.UserUtil;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 胡彦明 on 2018/8/1.
 * <p>
 * Description 新闻列表
 * <p>
 * otherTips
 */

public class NewsListFragment extends BaseListFragment<NewsListBean.DataBean.InfoBean>{
    public static NewsListFragment newInstance(Bundle args) {
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_news;
    }

    @Override
    public void excuteLogic() {
        setIsshowTop(true);
        showBackButton();
        setTitle("需求中心");
        getIv_add().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布新需求
                //ToastUtil.toast("发布新需求");
                startChat();

            }
        });
        getIv_add().setImageResource(R.drawable.ic_publish_needs);
        getIv_add().setVisibility(View.VISIBLE);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 2018/9/17  调用浏览次数接口
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",getAdapter().getData().get(position));
               // bundle.putString("name",getAdapter().getData().get(position).getTitle());
               // bundle.putString("url",getAdapter().getData().get(position).getUrl());
                start(NewsDetailFragment.newInstance(bundle));
            }
        });

    }
    private void startChat() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            ToastUtil.toast("请先登录");
        } else {
            //如果没连接则先连接
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                reconnect(UserUtil.getRongYunToken(_mActivity));
            } else {
                ChatUtils.ChatToCustomService(_mActivity,2);
            }

        }
    }

    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.d("---onTokenIncorrect--");
                ToastUtil.toast("聊天token异常");
            }

            @Override
            public void onSuccess(String s) {
                Logger.d("---onSuccess--" + s);
                //连接成功后进入 聊天界面
//                if (mDialog != null)
//                    mDialog.dismiss();
//                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
//                finish();
                ChatUtils.ChatToCustomService(_mActivity,2);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                ToastUtil.toast("聊天异常：" + e.toString());
                Logger.d("聊天异常：" + e.toString());
            }
        });

    }

    @Override
    public void onScroll(RecyclerView recyclerView, int dx, int dy) {

    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        NewsApi.getNewsList(pageNum + "", new ResponseImpl<NewsListBean>() {
            @Override
            public void onSuccess(NewsListBean data) {
                if(refresh){
                    setPageNum( HttpResultUtil.onRefreshSuccess(data.getData().getTotalpage(),pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                   setPageNum( HttpResultUtil.onLoardMoreSuccess(data.getData().getTotalpage(),pageNum,data.getData().getInfo(),getAdapter()));
                }

            }
        },NewsListBean.class);

    }

    @Override
    public void bindData(BaseViewHolder helper, NewsListBean.DataBean.InfoBean item, int position) {
        ImageUtil.getInstance().loadImage(NewsListFragment.this,item.getImage(), (ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title,item.getTitle()+"");
        helper.setText(R.id.tv_content,item.getMemo()+"");
        helper.setText(R.id.tv_source, "来源："+ item.getFrom()+" "+item.getCtime()+"");
        helper.setText(R.id.tv_see_count,item.getViews()+"次浏览");


    }

    @Override
    public boolean openEventBus() {
        return true;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateUserInfo(UpdateDataEvent event) {
       onRefresh();
    }
}
