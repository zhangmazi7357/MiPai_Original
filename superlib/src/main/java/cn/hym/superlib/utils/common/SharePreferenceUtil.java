package cn.hym.superlib.utils.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description
 * <p>
 * OtherTips
 */

public class SharePreferenceUtil {

    public static final String SP_KEY = "COMMON_SP_KEY";

    public static void setStringData(Context context, String key, String data) {
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null || data == null) {
            throw new NullPointerException("key or data con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, data);
        editor.commit();


    }

    public static void setBooleanData(Context context, String key, Boolean data) {
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null || data == null) {
            throw new NullPointerException("key or data con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, data);
        editor.commit();

    }

    public static void setIntData(Context context, String key, int data) {
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null) {
            throw new NullPointerException("key con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, data);
        editor.commit();


    }


    public static String getStringData(Context context, String key) {
        String result;
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null) {
            throw new NullPointerException("key con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        result = sp.getString(key, "");
        return result;
    }

    public static boolean getBooleangData(Context context, String key) {
        Boolean result;
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null) {
            throw new NullPointerException("key con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        result = sp.getBoolean(key, false);
        return result;
    }

    public static boolean getBooleangData(Context context, String key, boolean defaultvalue) {
        Boolean result;
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null) {
            throw new NullPointerException("key con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        result = sp.getBoolean(key, defaultvalue);
        return result;
    }

    public static int getIntData(Context context, String key) {
        int result;
        if (context == null) {
            throw new NullPointerException("context con't be null");
        }
        if (key == null) {
            throw new NullPointerException("key con't be null");
        }
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        result = sp.getInt(key, 0);
        return result;
    }

}
