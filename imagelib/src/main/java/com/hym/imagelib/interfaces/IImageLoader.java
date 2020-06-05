package com.hym.imagelib.interfaces;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import android.view.View;

/**
 * Created by 胡彦明 on 2017/6/25.
 * <p>
 * Description 图片加载接口
 * <p>
 * otherTips
 */

public interface IImageLoader<T extends View> {
     //加载图片
     <P>  void  loadImage(Context context, P path, T target);
     <P>  void  loadImage(Activity activity, P path, T target);
     <P>  void  loadImage(Fragment fragment, P path, T target);

    //加载圆形图片
     <P>  void loadCircleImage(Context context, P path, T target);
     <P>  void loadCircleImage(Activity activity, P path, T target);
     <P>  void loadCircleImage(Fragment fragment, P path, T target);

    //加载圆角图片
     <P>  void loadRoundCornerImage(Context context, P path, T target, int radius );
     <P>  void loadRoundCornerImage(Activity activity, P path, T target, int radius);
     <P> void  loadRoundCornerImage(Fragment fragment, P path, T target, int radius);

    <P>  void loadGif(Fragment fragment, P path, T target);

}
