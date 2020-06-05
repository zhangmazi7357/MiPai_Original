package com.hym.eshoplib.bean.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2017/7/25.
 * <p>
 * Description 地址列表
 * <p>
 * otherTips
 */

public class AddressListBean implements Serializable{


    /**
     * data : {"currentpage":1,"totalpage":1,"info":[{"is_default":"1","address":"我的收获地址","mobile":"18940105285","consignee_id":"741","name":"明明","region_id":"519","region_name":"辽宁省沈阳市苏家屯区","userid":"748"},{"is_default":"0","address":"test address3","mobile":"18940105285","consignee_id":"724","name":"test","region_id":"514","region_name":"辽宁省沈阳市和平区","userid":"748"}],"totalnum":"2"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * currentpage : 1
         * totalpage : 1
         * info : [{"is_default":"1","address":"我的收获地址","mobile":"18940105285","consignee_id":"741","name":"明明","region_id":"519","region_name":"辽宁省沈阳市苏家屯区","userid":"748"},{"is_default":"0","address":"test address3","mobile":"18940105285","consignee_id":"724","name":"test","region_id":"514","region_name":"辽宁省沈阳市和平区","userid":"748"}]
         * totalnum : 2
         */

        private int currentpage;
        private int totalpage;
        private String totalnum;
        private List<InfoBean> info;

        public int getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(int currentpage) {
            this.currentpage = currentpage;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable{
            /**
             * is_default : 1
             * address : 我的收获地址
             * mobile : 18940105285
             * consignee_id : 741
             * name : 明明
             * region_id : 519
             * region_name : 辽宁省沈阳市苏家屯区
             * userid : 748
             */

            private String is_default;
            private String address;
            private String mobile;
            private String consignee_id;
            private String name;
            private String region_id;
            private String region_name;
            private String userid;

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getConsignee_id() {
                return consignee_id;
            }

            public void setConsignee_id(String consignee_id) {
                this.consignee_id = consignee_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
