package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.AddFavouriteBean;
import com.hym.eshoplib.bean.shop.ProductDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
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
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import constant.StringConstants;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/8/1.
 * <p>
 * Description 视频详情
 * <p>
 * otherTips
 */

public class VadieoDetailFragment extends BaseKitFragment {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    Unbinder unbinder;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_like)
    TextView tvLike;
    int type = 0;//1的是从工作室详情页面进入就不再显示 查看工作室按钮
    @BindView(R.id.view_diver)
    View viewDiver;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_see_time)
    TextView tvSeeTime;
    @BindView(R.id.tv_time_long)
    TextView tvTimeLong;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_vadieo_type)
    TextView tvVadieoType;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_other)
    TextView tvOther;
    ProductDetailBean data;
    String id;
    @BindView(R.id.ll_btns)
    LinearLayout llBtns;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;

    public static VadieoDetailFragment newInstance(Bundle args) {
        VadieoDetailFragment fragment = new VadieoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_vadieo_detail;
    }

    @Override
    public void doLogic() {
        ScreenUtil.ViewAdapter(_mActivity, videoplayer, 16, 9, 20);
        type = getArguments().getInt("type", 0);
        id = getArguments().getString("id", "");//产品id
        setTitle("视频详情");
        showBackButton();
        if (type == 1) {
            rlShop.setVisibility(View.GONE);
            viewDiver.setVisibility(View.GONE);
        } else {
            viewDiver.setVisibility(View.VISIBLE);
            rlShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content_id = data.getData().getContent_id();
                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                    bundle.putString("id", content_id);
                    ActionActivity.start(_mActivity, bundle);
                }
            });
        }
        if (!TextUtils.isEmpty(id)) {
            ShopApi.AppendPlayinfo(id, new ResponseImpl<Object>() {
                @Override
                public void onSuccess(Object data) {

                }
            }, Object.class);
        }


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getProductDetail(id, new ResponseImpl<ProductDetailBean>() {
            @Override
            public void onSuccess(final ProductDetailBean result) {
                //如果是官方则不能进入详情
                if (result.getData().getUserid().equals("2010")) {
                    rlShop.setVisibility(View.GONE);
                    viewDiver.setVisibility(View.GONE);
                }
                data = result;
                tvTitle.setText(data.getData().getTitle() + "");
                String isverify = data.getData().getIs_verify();
                switch (isverify) {
                    case "0":
                        tvSeeTime.setText("待审核");
                        llBtns.setVisibility(View.GONE);
                        break;
                    case "1":
                        //tv_see_time.setText("审核通过");
                        tvSeeTime.setText(data.getData().getViews() + "次观看");
                        mShareListener = new CustomShareListener(_mActivity);
                        mShareAction = new ShareAction(_mActivity).setDisplayList(
                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                                .setCallback(mShareListener);
                        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ShareBoardConfig config = new ShareBoardConfig();
                                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                                UMWeb web = new UMWeb(data.getData().getShare_url());
                                //web.setTitle(data.getData().getTitle() + " | "+ StringConstants.Slogan);
                                web.setTitle(data.getData().getTitle());
                                web.setThumb(new UMImage(_mActivity, data.getData().getImage_default()));
                                web.setDescription(StringConstants.Slogan);
                                mShareAction.withMedia(web);
                                mShareAction.open(config);
                            }
                        });
                        llBtns.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        tvSeeTime.setText("审核未通过");
                        llBtns.setVisibility(View.GONE);
                        break;
                }
                tvTimeLong.setText(data.getData().getLength() + "");
                // 工作室信息
                tvShopName.setText(data.getData().getStore_name() + "");
                ImageUtil.getInstance().loadImage(VadieoDetailFragment.this, data.getData().getStore_logo(), ivLogo);
                ratingbar.setRating(Float.parseFloat(data.getData().getStore_rank()));
                //产品信息
                tvType.setText(data.getData().getIndustry() + "");
                tvVadieoType.setText(data.getData().getVideo() + "");
                tvRegion.setText(data.getData().getRegion_name() + "");
                tvOther.setText(data.getData().getOther() + "");
                //收藏
                if (data.getData().getIs_favorite() == 1) {
                    //已经收藏
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
                } else {
                    //没有收藏
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
                }
                tvLike.setText(data.getData().getAgree_count() + "");
                //点赞
                if (data.getData().getIs_agree() == 1) {
                    //已经点赞
                    tvLike.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_like_select, 0, 0);
                } else {
                    //没有点赞
                    tvLike.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_like_unselect, 0, 0);
                }
                tvLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getData().getIs_agree() == 1) {
                            ToastUtil.toast(R.string.changeLike);
                        } else {
                            //调用点赞接口
                            ShopApi.Addagree(data.getData().getCase_id(), new ResponseImpl<AddFavouriteBean>() {
                                @Override
                                public void onSuccess(AddFavouriteBean bean) {
                                    if (bean.getData().getStatus() == 1) {
                                        result.getData().setIs_agree(1);
                                        tvLike.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_like_select, 0, 0);
                                        tvLike.setText((Integer.parseInt(data.getData().getAgree_count()) + 1) + "");
                                        UpdateDataEvent event = new UpdateDataEvent();
                                        event.type = 6;
                                        EventBus.getDefault().post(event);
                                    }
                                }
                            }, AddFavouriteBean.class);
                        }
                    }
                });
                tvCollect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用收藏接口
                        ShopApi.AddFavorite(data.getData().getCase_id(), "case", new ResponseImpl<AddFavouriteBean>() {
                            @Override
                            public void onSuccess(AddFavouriteBean data) {
                                if (data.getData().getStatus() == 1) {
                                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_check, 0, 0);
                                } else {
                                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_collect_uncheck, 0, 0);
                                }

                            }
                        }, AddFavouriteBean.class);
                    }
                });
                //视频
                videoplayer.setUp(
                        result.getData().getFilepath(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                        result.getData().getTitle() + "");
                ImageUtil.getInstance().loadImage(_mActivity, result.getData().getImage_default(), videoplayer.thumbImageView);
                videoplayer.setType(1);//详情模式下不显示时间和观看次数和标题
                videoplayer.titleTextView.setVisibility(View.GONE);
                videoplayer.tv_see_count.setVisibility(View.GONE);
                videoplayer.tv_time_long.setVisibility(View.GONE);
                autoPlayVideo();

                if(data.getData().getAuth().equals("1")){
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_person_rt);
                }else if(data.getData().getAuth().equals("2")){
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_business_rt);

                }

            }
        }, ProductDetailBean.class);

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

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        JCVideoPlayer.releaseAllVideos();
        super.onDestroy();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    private void autoPlayVideo() {
        if (!JCUtils.isWifiConnected(getContext()) || SharePreferenceUtil.getBooleangData(_mActivity, "wifyautoplay", true) == false) {
            return;
        }
        videoplayer.startButton.performClick();
    }
}
