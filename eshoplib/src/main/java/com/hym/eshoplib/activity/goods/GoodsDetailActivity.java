package com.hym.eshoplib.activity.goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.gridlayout.widget.GridLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.goods.GoodsCommentsBean;
import com.hym.eshoplib.bean.goods.GoodsDetailBean;
import com.hym.eshoplib.bean.order.GetTotalCountBean;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shoppingcar.ShoppingCarApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.activity.base.BasekitActivity;
import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.GlideBanerImageLoader;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by 胡彦明 on 2018/3/1.
 * <p>
 * Description 商品详情
 * <p>
 * otherTips
 */

public class GoodsDetailActivity extends BasekitActivity {
    ImageView iv_like;
    String specification_id;
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_count)
    TextView tvCount;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_selet_type)
    SuperTextView tvSeletType;
    @BindView(R2.id.stepper)
    SnappingStepper stepper;
    @BindView(R2.id.iv_icon)
    ImageView ivIcon;
    @BindView(R2.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R2.id.tv_all)
    TextView tvAll;
    @BindView(R2.id.tv_goods_count)
    TextView tvGoodsCount;
    @BindView(R2.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R2.id.fl_fragments)
    FrameLayout flFragments;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;
    @BindView(R2.id.btn_help)
    SuperTextView btnHelp;
    @BindView(R2.id.btn_add_shopping_cart)
    SuperTextView btnAddShoppingCart;
    @BindView(R2.id.tv_buy_now)
    TextView tvBuyNow;
    @BindView(R2.id.ll_review)
    LinearLayout llReview;
    @BindView(R2.id.tv_review_count)
    TextView tvReviewCount;
    @BindView(R2.id.ll_review_items)
    LinearLayout ll_review_items;
    @BindView(R2.id.view_diver)
    View view_diver;
    @BindView(R2.id.wv_goods_detail)
    WebView webView;
    GoodsDetailBean data;
    int count = 1;//购买数量

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private SupportFragment[] fragments = new SupportFragment[3];
    boolean isDrop = false;
    List<String> images_str = new ArrayList<>();

    public static void start(Activity from, Bundle bundle) {
        Intent intent = new Intent(from, GoodsDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    public void initViews() {
        setShowProgressDialog(true);
        super.initViews();
        ButterKnife.bind(this);
        ScreenUtil.ViewAdapter(this, banner, 1, 1);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //imagesize是作为loading时的图片size
                int width = ScreenUtil.getScreenWidth(GoodsDetailActivity.this);
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                ImagePagerActivity.startImagePagerActivity(GoodsDetailActivity.this, images_str, position, imageSize);
            }
        });
        btnHelp.getCenterTextView().setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_help), null, null);
        btnHelp.getCenterTextView().setCompoundDrawablePadding(ScreenUtil.dip2px(GoodsDetailActivity.this, 1));
        btnHelp.getCenterTextView().setGravity(Gravity.CENTER);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    private void setListeners() {
        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                count = value;
            }
        });
        tvSeletType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bundle bundle = BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_spec);
                    bundle.putSerializable("data", data);
                    EshopActionActivity.startFroresult(GoodsDetailActivity.this, bundle, 0x01);
                } catch (Exception e) {
                    Logger.d(e.toString());
                }
            }
        });
        btnAddShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShoppingCart();
            }
        });
        tvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNow();

            }
        });
        llReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.toast("查看更多评论");
                Bundle bundle = BaseActionActivity.getActionBundle
                        (EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_comments);
                bundle.putString("id", specification_id);
                EshopActionActivity.
                        start(GoodsDetailActivity.this, bundle);
            }
        });
        //联系客服
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BaseActionActivity.getActionBundle
                        (EshopActionActivity.ModelType_Eshop, EshopActionActivity.Action_eshop_service);
                bundle.putString("id", specification_id);
                EshopActionActivity.
                        start(GoodsDetailActivity.this, bundle);
            }
        });

    }

    private void buyNow() {
        int stock = Integer.parseInt(data.getData().getStock());
        if (stock <= 0) {
            ToastUtil.toast("库存不足");
            return;
        }
        //立即购买
        //String specification_id,String buy_num,String coupon_log_id,
        OrderApi.gettTotalForCreateOrder(GoodsDetailActivity.this, specification_id, count + "", "", new ResponseImpl<GetTotalCountBean>() {
            @Override
            public void onStart(int mark) {
                setShowProgressDialog(true);
                super.onStart(mark);
            }

            @Override
            public void onFinish(int mark) {
                setShowProgressDialog(false);
                super.onFinish(mark);
            }

            @Override
            public void onSuccess(GetTotalCountBean data) {
                Bundle bundle = BaseActionActivity.getActionBundle
                        (EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_confirm_order);
                bundle.putSerializable("data", data);
                bundle.putString("id", specification_id);
                bundle.putString("c_type", "1");
                EshopActionActivity.
                        start(GoodsDetailActivity.this, bundle);

            }
        }, GetTotalCountBean.class);
    }

    private void AddShoppingCart() {
        int limit = Integer.parseInt(data.getData().getStock());
        if (count > limit) {
            ToastUtil.toast("库存不足");
            return;
        }
        ShoppingCarApi.addToShoppingCar(GoodsDetailActivity.this, specification_id, count + "", new ResponseImpl<Object>() {
            @Override
            public void onSuccess(Object data) {
                ToastUtil.toast("添加成功");

            }
        }, Object.class);
    }

    public void changeHeight() {
//        ViewGroup.LayoutParams layoutParams = flFragments.getLayoutParams();
//        if (isDrop) {
//            layoutParams.height = ScreenUtil.dip2px(HouseInfoActivity.this, 135);
//            isDrop = false;
//            iv_drop.setRotation(180);
//
//        } else {
//            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            isDrop = true;
//            iv_drop.setRotation(0);
//
//        }
//        flFragments.setLayoutParams(layoutParams);
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle(R.string.ProductDetails);
        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toast("分享");
            }
        });
        iv_like = setRight_iv2(R.drawable.selector_like, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(specification_id)) {
                    return;
                }
                GoodsApi.changeLike(GoodsDetailActivity.this, specification_id, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if (iv_like.isSelected()) {
                            iv_like.setSelected(false);
                        } else {
                            iv_like.setSelected(true);
                        }
                        EventBus.getDefault().post(new UpdateDataEvent());
                    }
                }, Object.class);

            }
        });

        try {
            specification_id = getIntent().getExtras().getString("id", "");
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        if (TextUtils.isEmpty(specification_id)) {
            ToastUtil.toast("数据异常");
            finish();
            return;
        }
        initTab();
        initFragments();
        setListeners();
        getData();


    }

    //获取数据
    private void getData() {
        GoodsApi.getGoodsDetail(this, specification_id, new ResponseImpl<GoodsDetailBean>() {
            @Override
            public void onSuccess(GoodsDetailBean data) {
                GoodsDetailActivity.this.data = data;
                updateBanner(data.getData().getContent_attachment());
                tvTitle.setText(data.getData().getName() + "");
                tvCount.setText(getResources().getString(R.string.Stock) + " （" + data.getData().getStock() + ")");
                tvPrice.setText(data.getData().getPrice() + " RMB");
                ImageUtil.getInstance().loadRoundCornerImage(GoodsDetailActivity.this, data.getData().getStore_image_default(), ivIcon, 5);
                tvShopName.setText(data.getData().getStore_name() + "");
                tvGoodsCount.setText(data.getData().getStore_goods_count() + "");
                if (data.getData().getIs_favorite() == 1) {
                    iv_like.setSelected(true);
                } else {
                    iv_like.setSelected(false);
                }
                //设置详情
                webView.loadUrl(data.getData().getDetail_url());
                //获取评论
                GoodsApi.GetCommentList(GoodsDetailActivity.this, specification_id, "", "3", "1", new ResponseImpl<GoodsCommentsBean>() {
                    @Override
                    public void onSuccess(GoodsCommentsBean data) {
                        ll_review_items.removeAllViews();
                        if (data.getData().getInfo() != null && data.getData().getInfo().size() > 0) {
                            llReview.setVisibility(View.VISIBLE);
                            view_diver.setVisibility(View.VISIBLE);
                            List<GoodsCommentsBean.DataBean.InfoBean> bean = data.getData().getInfo();
                            tvReviewCount.setText("(" + bean.size() + ")");
                            for (GoodsCommentsBean.DataBean.InfoBean coments : bean) {
                                View item = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.item_shopgoods_comments, null, false);
                                ImageView iv_icon = (ImageView) item.findViewById(R.id.iv_icon);
                                TextView tv_name = (TextView) item.findViewById(R.id.tv_name);
                                TextView tv_time = (TextView) item.findViewById(R.id.tv_time);
                                TextView tv_spe = (TextView) item.findViewById(R.id.tv_spec);
                                TextView tv_type = (TextView) item.findViewById(R.id.tv_type);
                                TextView tv_content = (TextView) item.findViewById(R.id.tv_content);
                                GridLayout gridView = (GridLayout) item.findViewById(R.id.girdlayout);
                                ImageUtil.getInstance().loadCircleImage(GoodsDetailActivity.this, coments.getAvatar(), iv_icon);
                                tv_name.setText(coments.getNickname() + "");
                                tv_time.setText(coments.getCtime() + "");
                                tv_spe.setText(coments.getProperty() + "");
                                tv_content.setText(coments.getContent() + "");
                                //添加图片
                                final List<String> images = coments.getAttachment();
                                gridView.removeAllViews();
                                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                for (int i = 0; i < images.size(); i++) {
                                    String url = images.get(i);
                                    ImageView iv = new ImageView(GoodsDetailActivity.this);
                                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                    GridLayout.Spec rowSpec = GridLayout.spec(i / 3, 1f);
                                    GridLayout.Spec columnSpec = GridLayout.spec(i % 3, 1f);
                                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
                                    layoutParams.setMargins(0, ScreenUtil.dip2px(GoodsDetailActivity.this, 5), ScreenUtil.dip2px(GoodsDetailActivity.this, 5), 0);
                                    layoutParams.height = (ScreenUtil.getScreenWidth(GoodsDetailActivity.this) / 2 - ScreenUtil.dip2px(GoodsDetailActivity.this, 20)) / 3;
                                    layoutParams.width = (ScreenUtil.getScreenWidth(GoodsDetailActivity.this) / 2 - ScreenUtil.dip2px(GoodsDetailActivity.this, 20)) / 3;
                                    iv.setLayoutParams(layoutParams);
                                    ImageUtil.getInstance().loadImage(GoodsDetailActivity.this, url, iv);
                                    final int image_position = i;
                                    iv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
                                            ImagePagerActivity.startImagePagerActivity((GoodsDetailActivity.this), images, image_position, imageSize);
                                        }
                                    });
                                    gridView.addView(iv);
                                }
                                ll_review_items.addView(item);

                            }


                        } else {
                            llReview.setVisibility(View.GONE);
                            view_diver.setVisibility(View.GONE);
                        }

                    }
                }, GoodsCommentsBean.class);


            }
        }, GoodsDetailBean.class);
    }


    private void initFragments() {
//        fragments[0] = HouseDetailFragment.newInstance(new Bundle());
//        fragments[1] = ServiceFragment.newInstance(new Bundle());
//        fragments[2] = SurroundFragment.newInstance(new Bundle());
//        loadMultipleRootFragment(R.id.fl_fragments, 0, fragments);
    }

    private void initTab() {
        tablayout = (CommonTabLayout) findViewById(R.id.tablayout);
        mTabEntities.add(new TabEntity(getResources().getString(R.string.ProductDetails), R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mTabEntities.add(new TabEntity(getResources().getString(R.string.Reviews), R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tablayout.setTabData(mTabEntities);
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //只有点击才会触发
//                showHideFragment(fragments[position], fragments[current_index]);
//                current_index = position;
//                isDrop = true;
//                changeHeight();


            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    //更新banner数据
    private void updateBanner(List<GoodsDetailBean.DataBean.ContentAttachmentBean> bannerData) {
        if (banner == null) {
            return;
        }
        ArrayList<String> data = new ArrayList<>();
        for (GoodsDetailBean.DataBean.ContentAttachmentBean bean : bannerData) {
            data.add(bean.getFilepath());
        }
        banner.setImageLoader(new GlideBanerImageLoader());
        banner.isAutoPlay(true);
        banner.update(data);
        images_str = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0x01) {
            try {
                Bundle bundle = data.getExtras();
                String spid = bundle.getString("spid", "");
                int type = bundle.getInt("type", 0);
                //判断是否需要更新数据
                updateDataBySpid(spid);
                if (!TextUtils.isEmpty(spid)) {
                    switch (type) {
                        case 1:
                            //加入购物车
                            // ToastUtil.toast("加入购物车");
                            AddShoppingCart();
                            break;
                        case 2:
                            //立即购买
                            // ToastUtil.toast("立即购买");
                            buyNow();
                            break;
                    }
                }

            } catch (Exception e) {
                Logger.d(e.toString());
            }

        }
    }

    //根据规格更新数据
    private void updateDataBySpid(String spid) {
        try {
            List<GoodsDetailBean.DataBean.PropertylistBean> propertylistBeen = data.getData().getPropertylist();
            for (GoodsDetailBean.DataBean.PropertylistBean bean : propertylistBeen) {
                if (bean.getSpecification_id().equals(spid)) {
                    //相等则设置为选择项
                    specification_id = spid;
                    if (bean.getIs_checkd().equals("1")) {
                        //如果已经是选中，则不更新数据
                    } else {
                        //更新数据
                        bean.setIs_checkd("1");
                        getData();
                    }
                } else {
                    //非选择项
                    bean.setIs_checkd("0");

                }
            }
        } catch (Exception e) {
            Logger.d(e.toString());
        }

    }


}
