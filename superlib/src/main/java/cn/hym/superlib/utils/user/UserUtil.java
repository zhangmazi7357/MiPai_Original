package cn.hym.superlib.utils.user;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import cn.hym.superlib.utils.common.SharePreferenceUtil;

/**
 * Created by  胡彦明 on 2017/5/8.
 * <p>
 * Description
 * <p>
 * OtherTips
 */

public class UserUtil {
    public static final String KEY_ISLOGIN = "key_islogin";
    public static final String KEY_CUTTENT_USER = "key_current_user";
    public static final String KEY_TOKEN = "key_token";
    public static final String KEY_ROONGYUN_TOKEN = "key_yongyun_token";
    public static final String KEY_USERID = "key_userid";

    //用户是否登录
    public static void setIsLogin(Context context, boolean isLogin) {
        SharePreferenceUtil.setBooleanData(context, KEY_ISLOGIN, isLogin);
    }

    public static boolean getIsLogin(Context context) {
        return SharePreferenceUtil.getBooleangData(context, KEY_ISLOGIN);
    }

    public static <T> void saveCurrentUser(Context context, T user) {
        String userJson = JSON.toJSONString(user);
        SharePreferenceUtil.setStringData(context, KEY_CUTTENT_USER, userJson);
    }

    public static <T> Object getCurrentUser(Context context, Class<T> tClass) {
        Object userBean = null;
        String userJson = SharePreferenceUtil.getStringData(context, KEY_CUTTENT_USER);
        userBean = JSON.parseObject(userJson, tClass);
        return userBean;
    }

    //存储token
    public static void saveToken(Context context, String token) {
        SharePreferenceUtil.setStringData(context, KEY_TOKEN, token);

    }

    public static String getToken(Context context) {
        return SharePreferenceUtil.getStringData(context, KEY_TOKEN);
    }

    //存储融云token
    public static void saveRongYunToken(Context context, String token) {
        SharePreferenceUtil.setStringData(context, KEY_ROONGYUN_TOKEN, token);

    }

    public static String getRongYunToken(Context context) {
        return SharePreferenceUtil.getStringData(context, KEY_ROONGYUN_TOKEN);
    }

    //存储userid
    public static void saveUserId(Context context, String id) {
        SharePreferenceUtil.setStringData(context, KEY_USERID, id);

    }

    public static String getUserId(Context context) {
        return SharePreferenceUtil.getStringData(context, KEY_USERID);
    }

}
