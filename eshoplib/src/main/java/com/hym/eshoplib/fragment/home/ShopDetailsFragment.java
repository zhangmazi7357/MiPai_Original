package com.hym.eshoplib.fragment.home;

import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.goods.GoodDetailModel;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ShopDetailsFragment extends BaseKitFragment implements View.OnClickListener {
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_before_price)
    TextView tvBeforePrice;
    @BindView(R.id.bn_shop)
    Banner bnShop;
    @BindView(R.id.iv_notify)
    ImageView ivNotify;
    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_buy_count)
    TextView tvBuyCount;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @BindView(R.id.tv_who_work)
    TextView tvWhoWork;
    @BindView(R.id.tv_is_shimin)
    TextView tvIsShimin;
    @BindView(R.id.iv_rating01)
    ImageView ivRating01;
    @BindView(R.id.iv_rating02)
    ImageView ivRating02;
    @BindView(R.id.iv_rating03)
    ImageView ivRating03;
    @BindView(R.id.iv_rating04)
    ImageView ivRating04;
    @BindView(R.id.iv_rating05)
    ImageView ivRating05;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tv_0)
    TextView tv0;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_team_introduction)
    TextView tvTeamIntroduction;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_project_content)
    TextView tvProjectContent;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_recommend_goods)
    TextView tvRecommendGoods;
    @BindView(R.id.rv_recommend_goods)
    RecyclerView rvRecommendGoods;
    @BindView(R.id.iv_b_01)
    ImageView ivB01;
    @BindView(R.id.rl_contact_him)
    RelativeLayout rlContactHim;
    @BindView(R.id.iv_b_02)
    ImageView ivB02;
    @BindView(R.id.rl_call_phone)
    RelativeLayout rlCallPhone;
    @BindView(R.id.iv_b_03)
    ImageView ivB03;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.ll_rating)
    LinearLayout llRating;
    @BindView(R.id.tv_shoot_time)
    TextView tvShootTime;
    @BindView(R.id.tv_loca)
    TextView tvLoca;
    @BindView(R.id.tv_qicai)
    TextView tvQicai;
    @BindView(R.id.shooting_day_time)
    TextView shootingDayTime;
    @BindView(R.id.tv_staffing)
    TextView tvStaffing;
//    @BindView(R.id.tv_team_introduce)
//    TextView tvTeamIntroduce;
    @BindView(R.id.tv_project_detail)
    TextView tvProjectDetail;
    @BindView(R.id.rl_click_workhome)
    RelativeLayout rlClickWorkhome;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Unbinder unbinder;
    private GoodDetailModel.DataBean db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle(getArguments().getString("title"));
        tvReport.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvReport.getPaint().setAntiAlias(true);//抗锯齿
        tvBeforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mShareListener = new CustomShareListener(_mActivity);
        mShareAction = new ShareAction(_mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(mShareListener);
        tvReport.setOnClickListener(this);
        rlClickWorkhome.setOnClickListener(this);
        setRight_iv(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                String url = "";
                String title = "";
                String memo = "";
                String image = "";
            /*    if (bean != null) {
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
                mShareAction.open(config);*/
            }
        });
     /*   bean = (NewsListBean.DataBean.InfoBean) getArguments().getSerializable("data");
        bean2 = (HomeDataBean.DataBean.AgencyBean) getArguments().getSerializable("data2");
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChat();
            }
        });*/
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GoodDetailModel data = (GoodDetailModel) getArguments().getSerializable("data");
        db = data.getData();
        tvTotalPrice.setText(db.getPresent_price());
        tvBeforePrice.setText("原价" + db.getOriginal_price());
        tvBuyCount.setText("销量" + data.getData().getAgree_count());
        tvDescribe.setText(db.getTitle());
        tvWhoWork.setText(db.getStore_name());
        // int childCount = llRating.getChildCount();
        String store_rank = db.getStore_rank();
        float v = Float.parseFloat(store_rank);
        ratingbar.setRating(v);
        /*long round = Math.round(v);
        showRing(childCount, round);*/
        Glide.with(_mActivity).load(db.getStore_logo()).into(ivUserPhoto);
        tvShootTime.setText(db.getLength());
        tvQicai.setText(db.getEquipment());
        long shootTime = Long.parseLong(db.getShooting_time());
        long day = shootTime / 1000 / (60 * 60 * 24);
        shootingDayTime.setText(day + "天");
        tvStaffing.setText(db.getStaffing());
//        tvTeamIntroduce.setText(db.getIntroduce());
        tvProjectDetail.setText(db.getDetails());
        tvLoca.setText(db.getRegion_id());
        if (db.getAuth() == 1) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_person_rt);
        } else if (db.getAuth() == 2) {
            ivVip.setVisibility(View.VISIBLE);
            ivVip.setImageResource(R.drawable.ic_business_rt);
        } else {
            ivVip.setVisibility(View.GONE);
        }
/*        rvView.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false));
        ArrayList<BasicInfo> al = new ArrayList<>();
        BasicInfo f1 = new BasicInfo();
        BasicInfo f2 = new BasicInfo();
        BasicInfo f3 = new BasicInfo();
        BasicInfo f4 = new BasicInfo();
        BasicInfo f5 = new BasicInfo();
        al.add(f1);
        al.add(f2);
        al.add(f3);
        al.add(f4);
        al.add(f5);
        TakeInfoAdapter takeInfoAdapter = new TakeInfoAdapter(R.layout.take_item, al);
        rvView.setAdapter(takeInfoAdapter);*/
    }

    private void showRing(int childCount, long round) {
        for (int i = 0; i < childCount; i++) {
            if (i <= round - 1) {
                llRating.getChildAt(i).setBackgroundResource(R.drawable.icon_full_start);
            }
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.shopdetail_activity;
    }

    public static SupportFragment newInstance(Bundle args) {
        ShopDetailsFragment fragment = new ShopDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_report:

                break;
            case R.id.rl_click_workhome:
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                bundle.putString("id", db.getContent_id());
                ActionActivity.start(_mActivity, bundle);
                break;
        }
    }

    /*//工作室
     */
}
