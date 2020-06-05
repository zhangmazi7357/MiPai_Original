package cn.hym.superlib.utils.common;

import android.app.Application;
import androidx.annotation.StringRes;

import com.hjq.toast.ToastUtils;

/**
 * Created by 胡彦明 on 2017/6/13.
 * <p>
 * Description 提示工具
 * <p>
 * otherTips
 */
public class ToastUtil {
    public static void init(Application context) {
        ToastUtils.init(context);
    }



    public static void toast(CharSequence msg){
        ToastUtils.show(msg);
    }
    public static void toast(@StringRes int msg){
        ToastUtils.show(msg);
    }
}
