package com.hym.eshoplib.fragment.message;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.SystemMessageListBean;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import utils.Rom;

/**
 * Created by 胡彦明 on 2018/8/2.
 * <p>
 * Description 系统消息详情
 * <p>
 * otherTips
 */

public class SystemMessageDetailFragment extends BaseKitFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    Unbinder unbinder;
    SystemMessageListBean.DataBean.InfoBean bean;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    public static SystemMessageDetailFragment newInstance(Bundle args) {
        SystemMessageDetailFragment fragment = new SystemMessageDetailFragment();
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
        setTitle("消息详情");
        tvFrom.setVisibility(View.GONE);
        tvZan.setVisibility(View.GONE);
        //分享
        mShareListener = new CustomShareListener(_mActivity);
        mShareAction = new ShareAction(_mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA)
                .setCallback(mShareListener);
        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   ShareBoardConfig config = new ShareBoardConfig();
                   config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                   UMWeb web = new UMWeb(bean.getUrl()+"&share=1");
                   // web.setTitle(bean.getTitle()+" | "+bean.getMemo());
                   web.setTitle(bean.getTitle());
                   if(!Rom.isFlyme()){
                       web.setThumb(new UMImage(_mActivity, bean.getImage_default()));

                   }
                   web.setDescription(bean.getMemo());
                   mShareAction.withMedia(web);
                   mShareAction.open(config);
               }catch (Exception e){
                   Logger.d(e.toString());
               }
            }
        });
        tvTitle.setVisibility(View.GONE);
        tvTime.setVisibility(View.GONE);



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        bean = (SystemMessageListBean.DataBean.InfoBean) getArguments().getSerializable("data");
        if (bean != null) {
//            tvTitle.setText(bean.getTitle() + "");
//            tvTime.setText("发布时间："+bean.getDate());

            webview.loadUrl(bean.getUrl());

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
}
