
package com.hym.eshoplib.bean.city;


import com.hym.eshoplib.widgets.sidebar.bean.BaseIndexPinyinBean;

/**
 * Created by super南仔 on 2017-05-04. 类备注： 需要传入的参数:
 */
public class CityBean extends BaseIndexPinyinBean {

    private String name;// 城市的名称
    private boolean isTop;// 是否是最上面 不需要被转化成拼音
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    @Override
    public String getTarget() {
        return name;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }

    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
