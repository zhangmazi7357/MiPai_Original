package cn.hym.superlib.application;

import android.content.Context;
import android.content.res.Configuration;
import androidx.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.hym.httplib.HttpInitHelper;
import com.hym.httplib.nohttp.NoHttpImpl;
import com.hym.imagelib.ImageInitHelper;
import com.hym.imagelib.impl.ImageLoaderImplGlideV45;

import cn.hym.superlib.R;
import cn.hym.superlib.config.CommonConfig;
import cn.hym.superlib.helper.LibrayInitHelper;
import cn.hym.superlib.languages.constants.ConstantLanguages;
import cn.hym.superlib.languages.utils.AppLanguageUtils;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.ApiExcuter;

/**
 * Created by 胡彦明 on 2017/6/25.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public abstract class KitBaseApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        onLanguageChange();
        LibrayInitHelper.getInstance().init(this,isDebug());//初始化lib库
        ImageInitHelper.init(new ImageLoaderImplGlideV45());//初始化图片加载库，默认使用glide 3.8版本
        HttpInitHelper.init(this,new NoHttpImpl(),isDebug());//初始化http 工具，默认使用nohttp
        ApiExcuter.setContext(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        if(CommonConfig.USE_MUILTY_LANGUAGES){
            super.attachBaseContext(AppLanguageUtils.attachBaseContext(base, getAppLanguage(base)));
        }else {
            super.attachBaseContext(base);
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onLanguageChange();
    }
    private void onLanguageChange() {
        if(CommonConfig.USE_MUILTY_LANGUAGES){
            AppLanguageUtils.changeAppLanguage(this, AppLanguageUtils.getSupportLanguage(getAppLanguage(this)));
        }

    }
    private String getAppLanguage(Context context) {
        String languages= SharePreferenceUtil.getStringData(context,context.getString(R.string.app_language_pref_key));
        if(TextUtils.isEmpty(languages)){
            languages= ConstantLanguages.CHINESE;
        }
        return languages;
    }
    public abstract boolean isDebug();

}
