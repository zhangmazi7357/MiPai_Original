package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import constant.StringConstants;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/8/1.
 * <p>
 * Description 图片详情
 * <p>
 * otherTips
 */

public class ImageDetailFragment extends BaseKitFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_like)
    TextView tvLike;
    Unbinder unbinder;
    RelativeLayout rlShop;
    BaseListAdapter<String> adapter;
    View diver;
    String id;
    TextView tv_title, tv_see_time;
    ImageView iv_logo,iv_vip;
    TextView tv_shop_name;
    MaterialRatingBar ratingBar;
    TextView tv_type, tv_image_type, tv_region, tv_ohter;
    @BindView(R.id.ll_btns)
    LinearLayout llBtns;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    public static ImageDetailFragment newInstance(Bundle args) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    int type = 0;//1的是从工作室详情页面进入就不再显示 查看工作室按钮
    ProductDetailBean data;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_image_detail;
    }

    @Override
    public void doLogic() {
        id = getArguments().getString("id", "");//产品id
        setTitle("图片详情");
        showBackButton();
        type = getArguments().getInt("type", 0);
        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new BaseListAdapter<String>(R.layout.item_image_gridlist, null) {
            @Override
            public void handleView(BaseViewHolder helper, String item, int position) {
                ImageView iv = helper.getView(R.id.iv_image);
                LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) iv.getLayoutParams();
                int screenWidth = ScreenUtil.getScreenWidth(_mActivity);
                int width = (screenWidth - ScreenUtil.dip2px(_mActivity, 40)) / 3;
                layoutParam.height = width;
                layoutParam.width = width;
                if (position % 3 == 0) {
                    //左侧
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                } else if (position % 3 == 1) {
                    //中间
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                } else {
                    layoutParam.setMargins(ScreenUtil.dip2px(_mActivity, 8), 0, 0, ScreenUtil.dip2px(_mActivity, 15));
                }
                ImageUtil.getInstance().loadImage(ImageDetailFragment.this, item, iv);

            }
        };
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_image_detail, null, false);
        iv_vip=footer.findViewById(R.id.iv_vip);
        iv_logo = footer.findViewById(R.id.iv_logo);
        tv_shop_name = footer.findViewById(R.id.tv_shop_name);
        ratingBar = footer.findViewById(R.id.ratingbar);
        tv_type = footer.findViewById(R.id.tv_type);
        tv_image_type = footer.findViewById(R.id.tv_image_type);
        tv_region = footer.findViewById(R.id.tv_region);
        tv_ohter = footer.findViewById(R.id.tv_other);
        diver = footer.findViewById(R.id.view_diver);
        rlShop = footer.findViewById(R.id.rl_shop);
        if (type == 1) {
            rlShop.setVisibility(View.GONE);
            diver.setVisibility(View.GONE);
        } else {
            rlShop.setVisibility(View.VISIBLE);
            diver.setVisibility(View.VISIBLE);
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
        adapter.addFooterView(footer);
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_image_detail, null, false);
        tv_title = header.findViewById(R.id.tv_title);
        tv_see_time = header.findViewById(R.id.tv_see_time);
        adapter.addHeaderView(header);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int width = ScreenUtil.getScreenWidth(_mActivity);
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                ImagePagerActivity.startImagePagerActivity(_mActivity, adapter.getData(), position, imageSize);
            }
        });
        rvList.setAdapter(adapter);
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
                data = result;
                String isverify = data.getData().getIs_verify();
                switch (isverify) {
                    case "0":
                        tv_see_time.setText("待审核");
                        llBtns.setVisibility(View.GONE);
                        break;
                    case "1":
                        //tv_see_time.setText("审核通过");
                        tv_see_time.setText(data.getData().getViews() + "次观看");
                        mShareListener = new CustomShareListener(_mActivity);
                        mShareAction = new ShareAction(_mActivity).setDisplayList(
                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA)
                                .setCallback(mShareListener);
                        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ShareBoardConfig config = new ShareBoardConfig();
                                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                                UMWeb web = new UMWeb(data.getData().getShare_url());
                               // web.setTitle(data.getData().getTitle()+" | "+ StringConstants.Slogan);
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
                        tv_see_time.setText("审核未通过");
                        llBtns.setVisibility(View.GONE);
                        break;
                }
                adapter.setNewData(data.getData().getAttachment());
                tv_title.setText(data.getData().getTitle() + "");

                // 工作室信息
                tv_shop_name.setText(data.getData().getStore_name() + "");
                if(data.getData().getAuth().equals("1")){
                   // tv_shop_name.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_vip_1,0);
                    iv_vip.setVisibility(View.VISIBLE);
                    iv_vip.setImageResource(R.drawable.ic_person_rt);
                }else if(data.getData().getAuth().equals("2")){
                    iv_vip.setVisibility(View.VISIBLE);
                    iv_vip.setImageResource(R.drawable.ic_business_rt);
                   // tv_shop_name.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_vip_2,0);
                }
                ImageUtil.getInstance().loadImage(ImageDetailFragment.this, data.getData().getStore_logo(), iv_logo);
                ratingBar.setRating(Float.parseFloat(data.getData().getStore_rank()));
                //产品信息
                tv_type.setText(data.getData().getIndustry() + "");
                tv_image_type.setText(data.getData().getVideo() + "");
                tv_region.setText(data.getData().getRegion_name() + "");
                tv_ohter.setText(data.getData().getOther() + "");
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
                                        tvLike.setText((Integer.parseInt(data.getData().getAgree_count())+1)+"");
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
}
