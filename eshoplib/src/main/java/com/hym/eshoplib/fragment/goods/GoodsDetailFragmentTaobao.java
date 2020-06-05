package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.CategoryListBean;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.eshoplib.widgets.SlideDetailsLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.bean.local.TabEntity;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;
import cn.hym.superlib.widgets.view.ColorFilterImageView;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/7/22.
 * <p>
 * Description
 * <p>
 * otherTips
 */
@Deprecated
public class GoodsDetailFragmentTaobao extends BaseKitFragment implements SlideDetailsLayout.OnSlideDetailsListener {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.tv_years)
    TextView tvYears;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_current_goods)
    TextView tvCurrentGoods;
    @BindView(R.id.ll_current_goods)
    LinearLayout llCurrentGoods;
    @BindView(R.id.tv_see_certificate)
    TextView tvSeeCertificate;
    @BindView(R.id.tv_see_shcool)
    TextView tvSeeShcool;
    @BindView(R.id.sv_goods_info)
    ScrollView svGoodsInfo;
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.fl_fragments)
    FrameLayout flFragments;
    @BindView(R.id.sv_switch)
    SlideDetailsLayout svSwitch;
    @BindView(R.id.iv_gotop)
    ColorFilterImageView ivGotop;
    Unbinder unbinder;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tablayout_1)
    CommonTabLayout tablayout1;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.tv_attach_business)
    TextView tvAttachBusiness;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_add_shoppingcart)
    TextView tvAddShoppingcart;
    @BindView(R.id.tv_buy_now)
    TextView tvBuyNow;
    private ArrayList<CustomTabEntity> tabs = new ArrayList<>(); //真正tab
    private ArrayList<CustomTabEntity> tabs1 = new ArrayList<>();//用于占位
    String current_title = "案例展示";
    SupportFragment mFragments[] = new SupportFragment[2];
    int current_type = 1;//当前工作室类型 按首页功能按键排序
    BaseListAdapter<CategoryListBean.DataBean> adapter;
    int select_position = 0;//当前选中的类别
    int count = 1;//购买数量至少一个
    String name = "";//购买种类名称
    private SnappingStepper stepper;

    public static GoodsDetailFragmentTaobao newInstance(Bundle args) {
        GoodsDetailFragmentTaobao fragment = new GoodsDetailFragmentTaobao();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_goods_info_taobao;
    }

    @Override
    public void doLogic() {
        // TODO: 2018/8/5  右上角mennu
        current_type = getArguments().getInt("type", 1);
        switch (current_type) {
            case 1:
                //文案策划
                break;
            case 2:
                //导演（无筛选）

                break;
            case 3:
                //摄影师

                break;
            case 4:
                //剪辑师

                break;
            case 5:
                //影视制作团队

                break;
            case 6:
                //三维动画师（无筛选）

                break;
            case 7:
                //平面设计师

                break;
            case 8:
                //ppt 大神（无筛选）

                break;
        }
        showBackButton();
        //占位
        tabs1.add(new TabEntity("案例展示", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs1.add(new TabEntity("评价", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tablayout1.setTabData(tabs1);
        tabs.add(new TabEntity("案例展示", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tabs.add(new TabEntity("评价", R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        tablayout.setTabData(tabs);
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    current_title = "案例展示";
                    showHideFragment(mFragments[0], mFragments[1]);
                } else {
                    current_title = "评价";
                    showHideFragment(mFragments[1], mFragments[0]);
                }
                setTitle(current_title);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        svSwitch.setOnSlideDetailsListener(this);
        ivGotop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svGoodsInfo.smoothScrollTo(0, 0);
                svSwitch.smoothClose(true);
            }
        });
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svGoodsInfo.smoothScrollTo(0, 0);
                svSwitch.smoothClose(true);
            }
        });
        //加载产品列表/评价列表
        Bundle bunddle=new Bundle();
        bunddle.putInt("type",current_type);
        mFragments[0] = ShopVadieoListFragment.newInstance(bunddle);
        mFragments[1] = ShopCommentsListFragment.newInstance(new Bundle());
        loadMultipleRootFragment(R.id.fl_fragments, 0, mFragments);
        //选择分类
        llCurrentGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
              showSelectTypeDialog(0);

            }
        });
    }
    //显示选择框
    private void showSelectTypeDialog(int i) {
        //选择数量
        final View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_type_dialog, null, false);
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_type_dialog, null, false);
        stepper = footer.findViewById(R.id.stepper);
        stepper.setValue(count);
        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                count = value;
            }
        });
        adapter = new BaseListAdapter<CategoryListBean.DataBean>(R.layout.item_spec_btn, null) {
            @Override
            public void handleView(BaseViewHolder helper, final CategoryListBean.DataBean item, final int position) {
                TextView tv_title = helper.getView(R.id.tv_title);
                TextView tv_type = helper.getView(R.id.tv_type);
                tv_type.setText(item.getCategory_name() + "");
                helper.setText(R.id.tv_memo, item.getMemo() + "");

                if (position == 0) {
                    tv_title.setVisibility(View.VISIBLE);
                } else {
                    tv_title.setVisibility(View.GONE);
                }
                if (select_position == position) {
                    tv_type.setBackgroundResource(R.drawable.selector_btn);
                    tv_type.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                } else {
                    tv_type.setBackgroundResource(R.drawable.shape_f3ebd8_conner5);
                    tv_type.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                }
                tv_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name=item.getCategory_name() + "";
                        select_position = position;
                        notifyDataSetChanged();
                    }
                });
            }
        };
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        if(i==0){
            //选择规格
            MipaiDialogUtil.showSpetificDialog(_mActivity, "",adapter, "加入购物车", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //加入购物车
                    tvCurrentGoods.setText(name + "数量x" + count);
                    MipaiDialogUtil.dismiss();
                    ToastUtil.toast("加入购物车成功，请到购物车中查看");
                }
            }, "立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    //立即预约
                    Bundle bundle=new Bundle();
                    start(MipaiOrderDetailFragment.newInstance(bundle));

                }
            },true);


        }else if(i==1){
            //加入购物车
            MipaiDialogUtil.showSpetificDialog(_mActivity,"", adapter,"",null,"", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvCurrentGoods.setText(name + "数量x" + count);
                    MipaiDialogUtil.dismiss();

                }
            },true);
        }else {
            //立即购买
            MipaiDialogUtil.showSpetificDialog(_mActivity,"", adapter,"",null,"立即预约", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MipaiDialogUtil.dismiss();
                    tvCurrentGoods.setText(name + "数量x" + count);
                    Bundle bundle=new Bundle();
                    start(MipaiOrderDetailFragment.newInstance(bundle));
                }
            },true);


        }
        //有筛选项则获取筛选
        if (current_type != 2 && current_type != 6 && current_type != 8) {
            GoodsApi.getCategory(current_type + "", new ResponseImpl<CategoryListBean>() {
                @Override
                public void onSuccess(CategoryListBean data) {
                    adapter.setNewData(data.getData());
                }
            }, CategoryListBean.class);
        }
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            ivGotop.setVisibility(View.VISIBLE);
            llTab.setVisibility(View.VISIBLE);
            tablayout1.setVisibility(View.GONE);
            setTitle(current_title);


        } else {
            //当前为商品详情页
            ivGotop.setVisibility(View.GONE);
            llTab.setVisibility(View.GONE);
            tablayout1.setVisibility(View.VISIBLE);
            setTitle("");


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

    @OnClick({R.id.tv_attach_business, R.id.tv_collect, R.id.tv_add_shoppingcart, R.id.tv_buy_now,R.id.tv_see_shcool,R.id.tv_see_certificate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_attach_business:
                ToastUtil.toast("联系卖家功能需要在B版中接入融云sdk才可以查看");
                break;
            case R.id.tv_collect:
                tvCollect.setSelected(!tvCollect.isSelected());
                if(tvCollect.isSelected()){
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_collect_uncheck,0,0);
                }else{
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_collect_check,0,0);
                }
                break;
            case R.id.tv_add_shoppingcart:
                showSelectTypeDialog(1);
                break;
            case R.id.tv_buy_now:
                showSelectTypeDialog(2);
                break;
            case R.id.tv_see_shcool:
                start(CertificateListFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_see_certificate:
                start(CertificateListFragment.newInstance(new Bundle()));
                break;
        }
    }

}
