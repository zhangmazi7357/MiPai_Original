package com.hym.eshoplib.fragment.news;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.HomeDataBean;
import com.hym.eshoplib.bean.news.NewsListBean;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.http.news.NewsApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.ChatUtils;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 胡彦明 on 2018/8/2.
 * <p>
 * Description 新闻正文
 * <p>
 * otherTips
 */

public class NewsDetailFragment extends BaseKitFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    Unbinder unbinder;
    String url;
    String title;
    String id;
    NewsListBean.DataBean.InfoBean bean;
    HomeDataBean.DataBean.AgencyBean bean2;
    @BindView(R.id.btn_join)
    SuperButton btnJoin;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;

    public static NewsDetailFragment newInstance(Bundle args) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("需求详情");
        mShareListener = new CustomShareListener(_mActivity);
        mShareAction = new ShareAction(_mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(mShareListener);
        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                String url = "";
                String title = "";
                String memo = "";
                String image = "";
                if (bean != null) {
                    url = bean.getUrl();
                    title = bean.getTitle();
                    memo = bean.getMemo();
                    image = bean.getImage();
                } else {
                    url = bean2.getUrl();
                    title = bean2.getTitle();
                    memo = bean2.getMemo();
                    image = bean2.getImage();
                }
                UMWeb web = new UMWeb(url + "&share=1");
                //  web.setTitle(title+" | "+memo);
                web.setTitle(title);
                web.setThumb(new UMImage(_mActivity, image));
                web.setDescription(memo);
                mShareAction.withMedia(web);
                mShareAction.open(config);
            }
        });
        bean = (NewsListBean.DataBean.InfoBean) getArguments().getSerializable("data");
        bean2 = (HomeDataBean.DataBean.AgencyBean) getArguments().getSerializable("data2");
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChat();
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (bean != null) {
            tvZan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean != null && bean.getAgency_id() != null) {
                        if (bean.getIs_agree() == 1) {
                            ToastUtil.toast(R.string.changeLike);
                            return;
                        }
                        ShopApi.Addagree2(bean.getAgency_id(), new ResponseImpl<AddFavouriteBean>() {
                            @Override
                            public void onSuccess(AddFavouriteBean data) {
                                if (data.getData().getStatus() == 1) {
                                    tvZan.setSelected(true);
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_check, 0, 0);
                                    bean.setIs_agree(1);
                                } else {
                                    tvZan.setSelected(false);
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_uncheck, 0, 0);
                                }
                                tvZan.setText(data.getData().getCount() + "");
                                UpdateDataEvent event = new UpdateDataEvent();
                                event.type = 5;
                                EventBus.getDefault().post(event);
                            }
                        }, AddFavouriteBean.class);
                    }

                }
            });
            tvTitle.setText(bean.getTitle() + "");
            tvFrom.setText("来源：" + bean.getFrom());
            tvTime.setText("发布时间：" + bean.getCtime());
            if (bean.getIs_agree() == 1) {
                tvZan.setSelected(true);
                tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_check, 0, 0);
            } else {
                tvZan.setSelected(false);
                tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_uncheck, 0, 0);
            }
            Logger.d("agree=" + bean.getAgree());
            tvZan.setText(bean.getAgree() + "");
            webview.loadUrl(bean.getUrl());
            //更新次数
            NewsApi.AppendViewinfo(bean.getAgency_id(), new ResponseImpl<Object>() {
                @Override
                public void onSuccess(Object data) {

                }
            }, Object.class);

        }
        if (bean2 != null) {
            Logger.d("isagree=" + bean2.getIs_agree());
            tvZan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean2 != null && bean2.getAgency_id() != null) {
                        if (bean2.getIs_agree() == 1) {
                            ToastUtil.toast(R.string.changeLike);
                            return;
                        }
                        ShopApi.Addagree2(bean2.getAgency_id(), new ResponseImpl<AddFavouriteBean>() {
                            @Override
                            public void onSuccess(AddFavouriteBean data) {
                                if (data.getData().getStatus() == 1) {
                                    tvZan.setSelected(true);
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_check, 0, 0);
                                    bean2.setIs_agree(1);
                                } else {
                                    tvZan.setSelected(false);
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_uncheck, 0, 0);
                                }
                                tvZan.setText(data.getData().getCount() + "");
                                UpdateDataEvent event = new UpdateDataEvent();
                                event.type = 5;
                                EventBus.getDefault().post(event);
                            }
                        }, AddFavouriteBean.class);
                    }

                }
            });
            tvTitle.setText(bean2.getTitle() + "");
            tvFrom.setText("来源：" + bean2.getFrom());
            tvTime.setText("发布时间：" + bean2.getCtime());
            if (bean2.getIs_agree() == 1) {
                tvZan.setSelected(true);
                tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_check, 0, 0);
            } else {
                tvZan.setSelected(false);
                tvZan.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_zan_uncheck, 0, 0);
            }
            Logger.d("agree=" + bean2.getAgree());
            tvZan.setText(bean2.getAgree() + "");
            webview.loadUrl(bean2.getUrl());
            //更新次数
            NewsApi.AppendViewinfo(bean2.getAgency_id(), new ResponseImpl<Object>() {
                @Override
                public void onSuccess(Object data) {

                }
            }, Object.class);
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

    private void startChat() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            ToastUtil.toast("请先登录");
        } else {
            //如果没连接则先连接
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                reconnect(UserUtil.getRongYunToken(_mActivity));
            } else {
                ChatUtils.ChatToCustomService(_mActivity,1);
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
                ChatUtils.ChatToCustomService(_mActivity,1);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                ToastUtil.toast("聊天异常：" + e.toString());
                Logger.d("聊天异常：" + e.toString());
            }
        });

    }
}
