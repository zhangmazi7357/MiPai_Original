package com.hym.imagelib.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hym.imagelib.interfaces.IImageLoader;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description 使用glide作为图片加载工具 版本4.5
 * <p>
 * otherTips
 */

public class ImageLoaderImplGlideV45 implements IImageLoader<ImageView> {
    //普通加载
    @Override
    public <P> void loadImage(Context context, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(context, path);
        loadImageByRequestBuilder(builder, target);

    }

    @Override
    public <P> void loadImage(Activity activity, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(activity, path);
        loadImageByRequestBuilder(builder, target);
    }

    @Override
    public <P> void loadImage(Fragment fragment, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(fragment, path);
        loadImageByRequestBuilder(builder, target);
    }

    @Override
    public <P> void loadGif(Fragment fragment, P path, ImageView target) {
        Glide.with(fragment).asGif().load(path).into(target);
    }

    //圆形
    @Override
    public <P> void loadCircleImage(Context context, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(context, path);
        builder.apply(RequestOptions.circleCropTransform()).into(target);
        //loadImageByRequestBuilder(builder,target);
    }

    @Override
    public <P> void loadCircleImage(Activity activity, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(activity, path);
        builder.apply(RequestOptions.circleCropTransform()).into(target);
        // loadImageByRequestBuilder(builder,target);
    }

    @Override
    public <P> void loadCircleImage(Fragment fragment, P path, ImageView target) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(fragment, path);
        builder.apply(RequestOptions.circleCropTransform()).into(target);
        //loadImageByRequestBuilder(builder,target);
    }


    //圆角,margin 默认为=0
    @Override
    public <P> void loadRoundCornerImage(Context context, P path, ImageView target, int radius) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(context, path);
        RequestOptions options = bitmapTransform(new RoundedCornersTransformation(dip2px(context, radius), 0)).diskCacheStrategy(DiskCacheStrategy.ALL);
        builder.apply(options).into(target);
        //loadImageByRequestBuilder(builder,target);
    }

    @Override
    public <P> void loadRoundCornerImage(Fragment fragment, P path, ImageView target, int radius) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(fragment, path);
        RequestOptions options = bitmapTransform(new RoundedCornersTransformation(dip2px(fragment.getContext(), radius), 0)).diskCacheStrategy(DiskCacheStrategy.ALL);
        ;
        builder.apply(options).into(target);
        //loadImageByRequestBuilder(builder,target);
    }

    @Override
    public <P> void loadRoundCornerImage(Activity activity, P path, ImageView target, int radius) {
        RequestBuilder<Drawable> builder = getDefaultRequestBuilder(activity, path);
        RequestOptions options = bitmapTransform(new RoundedCornersTransformation(dip2px(activity, radius), 0)).diskCacheStrategy(DiskCacheStrategy.ALL);
        builder.apply(options).into(target);
        // loadImageByRequestBuilder(builder,target);
    }


    //获取默认builder
    public <P> RequestBuilder<Drawable> getDefaultRequestBuilder(Context context, P path) {
        return Glide.with(context).load(path);

    }

    public <P> RequestBuilder<Drawable> getDefaultRequestBuilder(Activity activity, P path) {
        return Glide.with(activity).load(path);

    }

    public <P> RequestBuilder<Drawable> getDefaultRequestBuilder(Fragment fragment, P path) {
        return Glide.with(fragment).load(path);

    }

    //根据自定义Bulder加载图片
    public void loadImageByRequestBuilder(RequestBuilder<Drawable> builder, ImageView target) {

        builder.into(target);

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
