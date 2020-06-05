package com.hym.loginmodule.utils;

import android.content.Context;

import com.hym.loginmodule.R;

import cn.hym.superlib.utils.common.SharePreferenceUtil;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 多语言工具
 * <p>
 * otherTips
 */

public class LanguageUtil {
    //根据本地存储的语言类型，获取语言type
    public static String getLanguageTypeBytag(String tag){
        String type="1";
        if(tag.equals("en")){
            type="2";
        }else if(tag.equals("ja")){
            type="3";
        }
        return type;
    }
    //根据本地存储的语言类型，获取语言type 无参
    public static String getLanguageTypeBytag(Context context){
        String tag= SharePreferenceUtil.getStringData(context,context.getString(R.string.app_language_pref_key));
        String type="1";
        if(tag.equals("en")){
            type="2";
        }else if(tag.equals("ja")){
            type="3";
        }
        return type;
    }

}
