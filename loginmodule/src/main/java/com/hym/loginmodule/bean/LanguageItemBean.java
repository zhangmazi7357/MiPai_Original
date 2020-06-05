package com.hym.loginmodule.bean;

import androidx.annotation.DrawableRes;

/**
 * Created by 胡彦明 on 2018/1/8.
 * <p>
 * Description 语言选择实体
 * <p>
 * otherTips
 */

public class LanguageItemBean {
    String languageTag;//语言映射标签 比如中文zh 日文ja 英文en，可也以是中文简体zh-cn，zh-tw,en-hk等
    @DrawableRes int icon_res;
    String name;

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public int getIcon_res() {
        return icon_res;
    }

    public void setIcon_res(int icon_res) {
        this.icon_res = icon_res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LanguageItemBean(String languageTag, int icon_res, String name) {
        this.languageTag = languageTag;
        this.icon_res = icon_res;
        this.name = name;
    }
}
