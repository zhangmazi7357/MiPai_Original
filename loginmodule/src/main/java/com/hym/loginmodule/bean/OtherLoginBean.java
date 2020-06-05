package com.hym.loginmodule.bean;

/**
 * Created by 胡彦明 on 2018/1/13.
 * <p>
 * Description 登录回执
 * <p>
 * otherTips
 */

public class OtherLoginBean {

    /**
     * data : {"token":"569cc81978db31265a138f539594f4ba","perfected":0}
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
         * token : 569cc81978db31265a138f539594f4ba
         * perfected : 0
         * region_name : 北京
         */

        private String token;
        private int perfected;
        private String region_name;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getPerfected() {
            return perfected;
        }

        public void setPerfected(int perfected) {
            this.perfected = perfected;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getRegion_name() {
            return region_name;
        }
    }
}
