package com.hym.eshoplib.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.hym.eshoplib.databinding.MzActivityAllShopComemntBinding;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.adapter.ShopCommentAdapterAll;
import com.hym.eshoplib.mz.shopdetail.MzShopCommentBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.List;

import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.mz.widgets.MzHeaderBar;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.DialogView;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * 商品全部评论 ;
 */
public class AllShopCommentActivity extends MzBaseActivity {

    private String TAG = "AllShopCommentActivity";
    private String caseId = "";

    private MzActivityAllShopComemntBinding binding;
    private ShopCommentAdapterAll adapter;
    private int page = 1;
    private int totalPage = 0;
    private int pageSize = 10;

    private View headerView;

    private GoodDetailModel shareBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MzActivityAllShopComemntBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        caseId = intent.getStringExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID);
        shareBean = (GoodDetailModel) intent.getSerializableExtra(MzConstant.KEY_COMMENT_SHARE);


        binding.mzHeaderBar.setHeaderBarListener(new MzHeaderBar.HeaderBarListener() {
            @Override
            public void back() {
                finish();
            }

            @Override
            public void rightButton() {
            }
        });

        binding.mzHeaderBar.setRightDrawableListener(new MzHeaderBar.RightDrawableListener() {
            @Override
            public void onRightDrawableClick() {
                // TODO  分享 ;
                share(shareBean);
            }
        });

        initRecyclerView();
        getComment();


    }


    private void initRecyclerView() {
        binding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShopCommentAdapterAll(null);

//        binding.mRecyclerView.setAdapter(adapter);

        adapter.bindToRecyclerView(binding.mRecyclerView);

        headerView = LayoutInflater.from(this)
                .inflate(R.layout.mz_all_shop_comment_header, null, false);
        adapter.addHeaderView(headerView);

        // 要点吗;
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        // 取消掉默认的第一次 就loadMore ;
        adapter.setEmptyView(R.layout.mz_invite_empty_layout, binding.mRecyclerView);
        adapter.disableLoadMoreIfNotFullPage();

        // 加载下一页 ;
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (page > totalPage) {
                    adapter.loadMoreEnd();
                } else {
                    getComment();
                }
            }
        }, binding.mRecyclerView);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_replay:
                        MzShopCommentBean.DataBean.InfoBean item = (MzShopCommentBean.DataBean.InfoBean) adapter.getItem(position);
                        replay(item);
                        break;
                }
            }
        });


    }


    // 商品评论
    private void getComment() {

        MzNewApi.getComment(caseId, String.valueOf(page), new ResponseImpl<MzShopCommentBean>() {
            @Override
            public void onSuccess(MzShopCommentBean data) {

                MzShopCommentBean.DataBean dataBean = data.getData();
                List<MzShopCommentBean.DataBean.InfoBean> infoBeanList = dataBean.getInfo();
                if (data.getData().getTotalpage() != null) {
                    totalPage = Integer.parseInt(data.getData().getTotalpage());
                }

                setTag(dataBean);

                // 评论列表 ;
                setData(infoBeanList);


            }
        }, MzShopCommentBean.class);

    }

    private void setTag(MzShopCommentBean.DataBean dataBean) {


        // 好评度
        String comment_rate = dataBean.getComment_rate();
        if (!TextUtils.isEmpty(comment_rate)) {

            TextView commentRate = headerView.findViewById(R.id.commentRate);
            commentRate.setText("好评度 " + comment_rate);
        }
        // 标签 ;
        List<MzShopCommentBean.DataBean.TagsBean> tags = dataBean.getTags();

        if (tags != null && tags.size() > 0) {
            TagFlowLayout flowLayout = headerView.findViewById(R.id.flowLayout);
            flowLayout.setAdapter(new TagAdapter<MzShopCommentBean.DataBean.TagsBean>(tags) {

                @Override
                public View getView(FlowLayout parent, int position, MzShopCommentBean.DataBean.TagsBean tagsBean) {

                    TextView root = (TextView) LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.mz_tag_comment, parent, false);

                    String tagText = tagsBean.getName() + "   " + tagsBean.getNums();
                    root.setText(tagText);
                    return root;
                }
            });
        }
    }

    // set 评论数据
    private void setData(List<MzShopCommentBean.DataBean.InfoBean> list) {
        int size = list == null ? 0 : list.size();


        if (size > 0) {
            adapter.addData(list);
        }

        if (size < pageSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }
    }

    // 分享
    private void share(GoodDetailModel data) {
        BaseKitFragment.CustomShareListener mShareListener = new BaseKitFragment.CustomShareListener(this);
        ShareAction mShareAction = new ShareAction(this)
                .setDisplayList(
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.SINA)
                .setCallback(mShareListener);

        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        String url = "";
        String title = "";
        String memo = "";
        String image = "";

        if (data != null) {
            url = data.getData().getShare_url();
            title = data.getData().getTitle();
            memo = data.getData().getDetails();
            image = data.getData().getImage_default();
        } else {
            url = data.getData().getShare_url();
            title = data.getData().getTitle();
            memo = data.getData().getDetails();
            image = data.getData().getImage_default();
        }
        UMWeb web = new UMWeb(url + "&share=1");
        web.setTitle(title);
        web.setThumb(new UMImage(this, image));
        web.setDescription(memo);
        mShareAction.withMedia(web);
        mShareAction.open(config);
    }

    // 回复评论
    private void replay(MzShopCommentBean.DataBean.InfoBean item) {
        DialogView dialogView = DialogManager.getInstance()
                .initView(this, R.layout.mz_dialog_comment_replay, Gravity.BOTTOM);
        dialogView.show();

        EditText replayEt = dialogView.findViewById(R.id.et_replay);
        Button replayBt = dialogView.findViewById(R.id.bt_replay);

        // 确定按钮 ;
        replayBt.setOnClickListener(v -> {

            MzNewApi.sendComment(item.getCase_id(), "",
                    replayEt.getText().toString(), "",
                    "", "", item.getId(),
                    new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {

                            Log.e(TAG, "replay - onSuccess: ");

                            // 评论回复成功以后 刷新 评论列表 ;

                            dialogView.dismiss();
                            adapter.setNewData(null);

                            page = 1;
                            getComment();


                        }

                    },
                    Object.class);


        });


    }


}