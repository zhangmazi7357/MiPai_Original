package com.hym.eshoplib.mz.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 商品 ...... 图片
 */
public class ShopProjectPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShopProjectPicAdapter(@Nullable List<String> data) {
        super(R.layout.mz_shop_project_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        ImageView img = helper.getView(R.id.pic);

        int screenWidth = ((FragmentActivity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = img.getLayoutParams();


        Glide.with(mContext)
                .asBitmap()
                .load(item)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();


                        // 动态设置 ImageView的宽高尺寸 ;
                        double heights = ((double) height / width) * screenWidth;
                        lp.height = (int) heights;
                        lp.width = screenWidth;
                        img.setLayoutParams(lp);



                        Glide.with(mContext)
                                .load(item)
                                .into(img);

                    }
                });


        helper.addOnClickListener(R.id.pic);

    }
}
