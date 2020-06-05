package com.hym.eshoplib.bean.city;

/**
 * Created by 胡彦明 on 2018/9/30.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class RegionBean {

    /**
     * data : {"token":"4c46d97b29e1dc2596e871dea5dc6e6e","region_id":"23","userid":"1710"}
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
         * token : 4c46d97b29e1dc2596e871dea5dc6e6e
         * region_id : 23
         * userid : 1710
         */

        private String token;
        private String region_id;
        private String userid;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
