package cn.hym.superlib.utils.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.ColorInt;

/**
 * Created by 胡彦明 on 2018/2/23.
 * <p>
 * Description 图形背景工具
 * <p>
 * otherTips
 */

public class ShapeUtil {
    //根据颜色获取背景drawable
    public  static GradientDrawable getBgDrawableByColor(Context context,@ColorInt int color){
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(ScreenUtil.dip2px(context,5));
        drawable.setColor(color);
        return drawable;
    }
    public  static GradientDrawable getBgDrawableByColor(Context context,@ColorInt int color,int radious){
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(ScreenUtil.dip2px(context,radious));
        drawable.setColor(color);
        return drawable;
    }

}
