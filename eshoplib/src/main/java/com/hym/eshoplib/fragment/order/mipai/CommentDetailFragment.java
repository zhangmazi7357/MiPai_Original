package com.hym.eshoplib.fragment.order.mipai;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.order.CommentDetailBean;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/9/20.
 * <p>
 * Description 评价详情
 * <p>
 * otherTips
 */

public class CommentDetailFragment extends BaseKitFragment {


    private String TAG = "CommentDetailFragment";

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_total_price)
    SuperTextView tvTotalPrice;
    @BindView(R.id.laybel_1)
    SuperButton laybel1;
    @BindView(R.id.laybel_2)
    SuperButton laybel2;
    @BindView(R.id.laybel_3)
    SuperButton laybel3;
    Unbinder unbinder;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.fl_avatar)
    FrameLayout flAvatar;
    @BindView(R.id.iv_vip_shop)
    ImageView ivVipShop;
    @BindView(R.id.fl_commnents)
    TagFlowLayout flCommnents;

    public static CommentDetailFragment newInstance(Bundle args) {
        CommentDetailFragment fragment = new CommentDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    String id;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_comment_detail;
    }

    @Override
    public void doLogic() {
        // commentId
        id = getArguments().getString("id", "");
        showBackButton();
        setTitle("评价详情");
        setShowProgressDialog(true);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        MzNewApi.getCommentInfo(id, new ResponseImpl<CommentDetailBean>() {
            @Override
            public void onSuccess(CommentDetailBean data) {


                ImageUtil.getInstance()
                        .loadCircleImage(CommentDetailFragment.this,
                                data.getData().getAvatar(), ivAvatar);

                tvName.setText(data.getData().getNickname() + "");
                tvTime.setText(data.getData().getCtime());
                String rank_type = data.getData().getRank_type();
                if (!TextUtils.isEmpty(rank_type)) {
                    ratingbar.setRating(Float.parseFloat(rank_type));
                }


                ImageUtil.getInstance()
                        .loadImage(CommentDetailFragment.this,
                                data.getData().getLogo(), ivIcon);

                CommentDetailBean.DataBean item = data.getData();


                tvShopName.setText(item.getStore_name());

                tvType.setText("类别：" + item.getCategory_name());
                tvPrice.setText("￥：" + item.getPrice());
                tvCount.setText("x" + item.getUser_num());
                tvTotalPrice.setRightString("￥：" + data.getData().getMoney());


                // 还有标签 ；
                List<CommentDetailBean.DataBean.LabelListBean> labels = data.getData().getLabel_list();
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < labels.size(); i++) {
                    arr.add(labels.get(i).getLabel_name());
                }
                flCommnents.setAdapter(new TagAdapter<String>(arr) {
                    @Override
                    public View getView(FlowLayout parent, final int p, String s) {
                        final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_comment_tab, parent, false);
                        textView.setText(s);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        return textView;
                    }
                });

                switch (labels.size()) {
                    case 1:
                        laybel1.setVisibility(View.VISIBLE);
                        laybel1.setText(labels.get(0).getLabel_name());
                        break;
                    case 2:
                        laybel1.setVisibility(View.VISIBLE);
                        laybel1.setText(labels.get(0).getLabel_name());
                        laybel2.setVisibility(View.VISIBLE);
                        laybel2.setText(labels.get(1).getLabel_name());
                        break;
                    case 3:
                        laybel1.setVisibility(View.VISIBLE);
                        laybel1.setText(labels.get(0).getLabel_name());
                        laybel2.setVisibility(View.VISIBLE);
                        laybel2.setText(labels.get(1).getLabel_name());
                        laybel3.setVisibility(View.VISIBLE);
                        laybel3.setText(labels.get(2).getLabel_name());
                        break;
                }
                if (data.getData().getAuth_user() == 1) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_person_circle);
                } else if (data.getData().getAuth_user() == 2) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_business_circle);
                } else {
                    ivVip.setVisibility(View.GONE);
                }

                if (data.getData().getAuth_store() == 1) {
                    ivVipShop.setVisibility(View.VISIBLE);
                    ivVipShop.setImageResource(R.drawable.ic_person_rt);
                } else if (data.getData().getAuth_store() == 2) {
                    ivVipShop.setVisibility(View.VISIBLE);
                    ivVipShop.setImageResource(R.drawable.ic_business_rt);
                } else {
                    ivVipShop.setVisibility(View.GONE);
                }


            }

            @Override
            public void dataRes(int code, String data) {
                super.dataRes(code, data);
             //   Log.e(TAG, "dataRes: " + data);
            }
        }, CommentDetailBean.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
