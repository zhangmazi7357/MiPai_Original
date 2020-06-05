package com.hym.loginmodule.bean;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 修改语言后，本地城市名也改变
 * <p>
 * otherTips
 */

public class ChangeLanguageBean {

    /**
     * data : {"region_name":"北京"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * region_name : 北京
         */

        private String region_name;

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
