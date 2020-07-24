package com.hym.eshoplib.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.home.FirstPagerShopBean;
import com.hym.eshoplib.bean.home.SpecialTimeLimteBean;

import java.util.List;

import butterknife.BindView;

/**
 * 首页 multiType
 */
public class FirstPagerShopItemAdapter extends BaseMultiItemQuickAdapter<SpecialTimeLimteBean.DataBean, BaseViewHolder> {


    public FirstPagerShopItemAdapter(List<SpecialTimeLimteBean.DataBean> data) {
        super(data);
        addItemType(FirstPagerShopBean.TEHUI, R.layout.item_tehui);
        addItemType(FirstPagerShopBean.YANXUAN, R.layout.item_yanxuan);
        addItemType(FirstPagerShopBean.TABLE, R.layout.item_tablelayout);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialTimeLimteBean.DataBean item) {


        if (item.getItemType() == FirstPagerShopBean.TEHUI) {
            if (item == null || item.getVideo() == null || item.getVideo().size() < 5) {
                return;
            }
            TextView tvBeforePrice = helper.getView(R.id.tv_before_price);
            if (TextUtils.isEmpty(item.getVideo().get(0).getPresent_price())) {
                tvBeforePrice.setVisibility(View.GONE);
            }
            tvBeforePrice.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_xianshi_title, item.getVideo().get(0).getTitle());
            TextView view = helper.getView(R.id.tv_before_price);
            TextView xian = helper.getView(R.id.xian);
            xian.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
            view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            helper.setText(R.id.tv_one_count, item.getVideo().get(0).getWeight() + "人付款")
                    .setText(R.id.tv_one_money, item.getVideo().get(0).getPresent_price())
                    .setText(R.id.tv_before_price, item.getVideo().get(0).getOriginal_price())
                    .setText(R.id.tv_two_xianshi_title1, item.getVideo().get(1).getTitle())
                    // .setText(R.id.tv_two_xianshi_price1, "2上价格")
                    .setText(R.id.tv_two1_money, item.getVideo().get(1).getPresent_price())
                    .setText(R.id.tv_two2_money, item.getVideo().get(2).getPresent_price())
                    .setText(R.id.tv_two_xianshi_title, item.getVideo().get(3).getTitle())
                    // .setText(R.id.tv_two_xianshi_price, "2下价格")
                    .setText(R.id.tv_two3_money, item.getVideo().get(3).getPresent_price())
                    .setText(R.id.tv_two4_money, item.getVideo().get(4).getPresent_price())
                    .setText(R.id.tv_two_xianshi_title2, item.getVideo().get(2).getTitle())
                    .setText(R.id.tv_two_xianshi_title4, item.getVideo().get(4).getTitle())
            ;

            ImageView one = helper.getView(R.id.iv_one_pic);
            ImageView two = helper.getView(R.id.iv_two_1);
            ImageView three = helper.getView(R.id.iv_two_2);
            ImageView four = helper.getView(R.id.iv_two_3);
            ImageView five = helper.getView(R.id.iv_two_4);
            Glide.with(mContext).load(item.getVideo().get(0).getImage_default()).into(one);
            Glide.with(mContext).load(item.getVideo().get(1).getImage_default()).into(two);
            Glide.with(mContext).load(item.getVideo().get(2).getImage_default()).into(three);
            Glide.with(mContext).load(item.getVideo().get(3).getImage_default()).into(four);
            Glide.with(mContext).load(item.getVideo().get(4).getImage_default()).into(five);
            helper.addOnClickListener(R.id.rl_top_click1)
                    .addOnClickListener(R.id.rl_top_click2)
                    .addOnClickListener(R.id.rl_top_click3)
                    .addOnClickListener(R.id.rl_top_click4)
                    .addOnClickListener(R.id.rl_top_click5)
                    .addOnClickListener(R.id.tv_tehui_odd_more)
            ;
        } else if (item.getItemType() == FirstPagerShopBean.YANXUAN) {
            if (item == null || item.getVideo() == null || item.getVideo().size() <= 0) {
                return;
            }
            TextView view = helper.getView(R.id.tv_one_before_price);
            TextView yanXuan = helper.getView(R.id.yan_xian);
            yanXuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
            view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            TextView view1 = helper.getView(R.id.tv_before_price);
            view1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            if (TextUtils.isEmpty(item.getVideo().get(0).getOriginal_price())) {
                view.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(item.getVideo().get(1).getOriginal_price())) {
                view1.setVisibility(View.GONE);
            }
            ImageView onePic = helper.getView(R.id.iv_one_pic);
            ImageView twoPic = helper.getView(R.id.iv_two_pic);
            helper.setText(R.id.tv_yanxuan_1title, item.getVideo().get(0).getTitle())
                    .setText(R.id.tv_one_count, item.getVideo().get(0).getWeight() + "人付款")
                    .setText(R.id.tv_one_money, item.getVideo().get(0).getPresent_price())
                    .setText(R.id.tv_one_before_price, item.getVideo().get(0).getOriginal_price());
            Glide.with(mContext).load(item.getVideo().get(0).getImage_default()).into(onePic);
            if (item.getVideo().size() >= 2) {
                helper.setText(R.id.tv_yanxuan_2title, item.getVideo().get(1).getTitle())
                        .setText(R.id.tv_two_count, item.getVideo().get(1).getWeight() + "人付款")
                        .setText(R.id.tv_two_money, item.getVideo().get(1).getPresent_price())
                        .setText(R.id.tv_before_price, item.getVideo().get(1).getOriginal_price());
                Glide.with(mContext).load(item.getVideo().get(1).getImage_default()).into(twoPic);
            }
            helper.addOnClickListener(R.id.rl_bottom_click1)
                    .addOnClickListener(R.id.rl_bottom_click2)
                    .addOnClickListener(R.id.tv_strict_select_more)
            ;
        } else if (item.getItemType() == FirstPagerShopBean.TABLE) {
            TextView tvComment = helper.getView(R.id.tv_comment);
            TextView tvPhoto = helper.getView(R.id.tv_photo);
            TextView tvVideo = helper.getView(R.id.tv_video);
            TextView tvCommentLine = helper.getView(R.id.tv_comment_line);
            TextView tvPhotoLine = helper.getView(R.id.tv_photo_line);
            TextView tvVideoLine = helper.getView(R.id.tv_video_line);

            if (item.getSelected() == 1) {
                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                tvComment.setTextColor(Color.parseColor("#FF5203"));
                tvCommentLine.setVisibility(View.VISIBLE);

                tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvPhoto.setTextColor(Color.parseColor("#292929"));
                tvPhotoLine.setVisibility(View.INVISIBLE);

                tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvVideo.setTextColor(Color.parseColor("#292929"));
                tvVideoLine.setVisibility(View.INVISIBLE);
            } else if (item.getSelected() == 2) {
                tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                tvPhoto.setTextColor(Color.parseColor("#FF5203"));
                tvPhotoLine.setVisibility(View.VISIBLE);

                tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvVideo.setTextColor(Color.parseColor("#292929"));
                tvVideoLine.setVisibility(View.INVISIBLE);

                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvComment.setTextColor(Color.parseColor("#292929"));
                tvCommentLine.setVisibility(View.INVISIBLE);
            } else {
                tvVideo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tvVideo.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                tvVideo.setTextColor(Color.parseColor("#FF5203"));
                tvVideoLine.setVisibility(View.VISIBLE);

                tvPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvPhoto.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvPhoto.setTextColor(Color.parseColor("#292929"));
                tvPhotoLine.setVisibility(View.INVISIBLE);

                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvComment.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tvComment.setTextColor(Color.parseColor("#292929"));
                tvCommentLine.setVisibility(View.INVISIBLE);
            }

            helper.addOnClickListener(R.id.rl_comment)
                    .addOnClickListener(R.id.rl_rl_take_photo)
                    .addOnClickListener(R.id.rl_rl_take_video);

        }
    }
}

