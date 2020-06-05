package com.hym.eshoplib.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hym.eshoplib.R;
import com.hym.imagelib.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.activity.base.BasekitActivity;
import cn.hym.superlib.utils.common.SharePreferenceUtil;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首次启动
 * Date: 2017/7/24
 * Email: 541567595@qq.com
 */

public class GuideActivity extends BasekitActivity {

    private ConvenientBanner banner;

    private List<BannerItem> bannerItemList;

    String key = "first_guide";

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public void doLogic() {
        //skip();
        //如果已经调用过引导页则跳过
        if (SharePreferenceUtil.getBooleangData(this, key)) {
            skip();
            return;
        }
        banner = (ConvenientBanner) findViewById(R.id.banner);
        bannerItemList = new ArrayList<>();
        initBanner();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_guide;
    }

    private void initBanner() {
        bannerItemList.add(new BannerItem(R.drawable.sp_1));
        bannerItemList.add(new BannerItem(R.drawable.sp_2));
        bannerItemList.add(new BannerItem(R.drawable.sp_4));
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                skip();
/*
                if (position == 2) {

                    skip();
                }
*/
            }
        });
        banner.setPages(new CBViewHolderCreator<BannerHolderView>() {
            @Override
            public BannerHolderView createHolder() {
                return new BannerHolderView();
            }
        }, bannerItemList).setPointViewVisible(true)    //设置指示器是否可见
//                .setPageIndicator(new int[]{R.mipmap.img_banner_nor, R.mipmap.img_banner_sel})
                .setCanLoop(false);
    }


    private void skip() {
        //第一次启动，引导页完毕 跳入定制页面
        // SharePreferenceUtil.setBooleanData(GuideActivity.this, key, true);
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        // ActionActivity.start(this,ActionActivity.getActionBundle(ActionActivity.ModelType_Home,ActionActivity.Action_guide));
        finish();
    }

    public class BannerItem {
        public BannerItem(String image) {
            this.image = image;
        }


        public BannerItem(int imageId) {
            this.imageId = imageId;
        }

        private int imageId;

        public int getImageId() {
            return imageId;
        }

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public class BannerHolderView implements Holder<BannerItem> {
        private View view;

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_banner, null, false);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerItem data) {
            ImageView iv = (ImageView) view.findViewById(R.id.img_main_banner);
            ImageUtil.getInstance().loadImage(context, TextUtils.isEmpty(data.getImage()) ? data.getImageId() : data.getImage(), iv);
            TextView tv = view.findViewById(R.id.tv_skip);
            //tv.setVisibility(View.GONE);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skip();
                }
            });
//        Glide.with(context)
//                .load(TextUtils.isEmpty(data.getImage()) ? data.getImageId() : data.getImage()).
//                placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
//                .into(iv);
//        Logger.d("banner="+MyUrlUtil.PICIP+data.getUrl());
            // iv.setImageResource(data.getImageRes());
//        ImageHelper.disPlay(context, data.getImage(), iv);
        }
    }

}
