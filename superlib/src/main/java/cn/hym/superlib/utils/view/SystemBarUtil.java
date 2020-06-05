package cn.hym.superlib.utils.view;

import android.content.Context;
import android.os.Build;
import cn.hym.superlib.utils.common.SharePreferenceUtil;

/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description 系统状态栏工具
 * <p>
 * OtherTips
 */

public class SystemBarUtil {
    public static final String SP_KEY_HASINIT="HASINIT";//是否已经获取过
    public static final String SP_KEY_SYSTEMBARHEIGHT="SYSTEMBARHEIGHT";//系统状态栏高度
    /**
     *
     * @param context
     * @return dp
     */
    public static int getSystemBarHeight(Context context){
        //对于一个手机来说，系统状态栏是固定的，所以只获取一次存入sp，并且反射方性能低，不建议多次获取
        int height_px=0;
        boolean hasInit= SharePreferenceUtil.getBooleangData(context,SP_KEY_HASINIT);
        if(hasInit){
            //直接从sp里获取
            height_px=SharePreferenceUtil.getIntData(context,SP_KEY_SYSTEMBARHEIGHT);
        } else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                try {
                    Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                    Object object = clazz.newInstance();
                    int height = Integer.parseInt(clazz.getField("status_bar_height")
                            .get(object).toString());
                    height_px = context.getResources().getDimensionPixelSize(height);
                    SharePreferenceUtil.setBooleanData(context,SP_KEY_HASINIT,true);//标记已经初始化
                    SharePreferenceUtil.setIntData(context,SP_KEY_SYSTEMBARHEIGHT,height_px);//将高度dp值存入sp
                } catch (Exception e) {
                    e.printStackTrace();
                    SharePreferenceUtil.setBooleanData(context,SP_KEY_HASINIT,false);//标记初始化失败
                }
            }
        }
        return height_px;
    }

}
