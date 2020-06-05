
package com.hym.eshoplib.bean.city;


import com.hym.eshoplib.widgets.sidebar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by super南仔 on 2017-05-09. 类备注：仿美团城市列表 HeaderView Bean<br/>
 * 需要传入的参数:
 */
public class CityHeaderBean extends BaseIndexPinyinBean {

    private List<String> cityList;
    // 悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public CityHeaderBean() {
    }

    public CityHeaderBean(List<String> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public CityHeaderBean setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public CityHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }
}
