package com.hym.loginmodule.bean;

/**
 * Created by 胡彦明 on 2018/3/12.
 * <p>
 * Description 个人信息详细
 * <p>
 * otherTips
 */

public class PersonBean {

    /**
     * data : {"userid":"891","username":"18940105285","nickname":"逝水无痕","phone":"18940105285","email":"","avatar":"http://jzshop.liandao.mobi/uploads/87/ec/cd/3a/13/0cc5ba7680e5a3dca93c11.jpg","gender":"1","age":"2017/8/17","bts_score":"1810","bts_bean":"0","favorites_count":"4","release_count":"3","house_order_count":3,"activity_count":"1","is_setpass":"1","is_q":"0","is_msg":0,"q_count":"1","user_lv":"2","lv_name":"企业会员（签约俱乐部）","lv_end_time":"2018-10-16 22:11:54","consignee":{"consignee_name":"你明哥","consignee_phone":null,"consignee_region_name":"北京北京市东城区","consignee_address":"大望路金地"}}
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
         * userid : 891
         * username : 18940105285
         * nickname : 逝水无痕
         * phone : 18940105285
         * email :
         * avatar : http://jzshop.liandao.mobi/uploads/87/ec/cd/3a/13/0cc5ba7680e5a3dca93c11.jpg
         * gender : 1
         * age : 2017/8/17
         * bts_score : 1810
         * bts_bean : 0
         * favorites_count : 4
         * release_count : 3
         * house_order_count : 3
         * activity_count : 1
         * is_setpass : 1
         * is_q : 0
         * is_msg : 0
         * q_count : 1
         * user_lv : 2
         * lv_name : 企业会员（签约俱乐部）
         * lv_end_time : 2018-10-16 22:11:54
         * consignee : {"consignee_name":"你明哥","consignee_phone":null,"consignee_region_name":"北京北京市东城区","consignee_address":"大望路金地"}
         */

        private String userid;
        private String username;
        private String nickname;
        private String phone;
        private String email;
        private String avatar;
        private String gender;
        private String age;
        private String bts_score;
        private String bts_bean;
        private String favorites_count;
        private String release_count;
        private int house_order_count;
        private String activity_count;
        private String is_setpass;
        private String is_q;
        private int is_msg;
        private String q_count;
        private String user_lv;
        private String lv_name;
        private String lv_end_time;
        private ConsigneeBean consignee;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBts_score() {
            return bts_score;
        }

        public void setBts_score(String bts_score) {
            this.bts_score = bts_score;
        }

        public String getBts_bean() {
            return bts_bean;
        }

        public void setBts_bean(String bts_bean) {
            this.bts_bean = bts_bean;
        }

        public String getFavorites_count() {
            return favorites_count;
        }

        public void setFavorites_count(String favorites_count) {
            this.favorites_count = favorites_count;
        }

        public String getRelease_count() {
            return release_count;
        }

        public void setRelease_count(String release_count) {
            this.release_count = release_count;
        }

        public int getHouse_order_count() {
            return house_order_count;
        }

        public void setHouse_order_count(int house_order_count) {
            this.house_order_count = house_order_count;
        }

        public String getActivity_count() {
            return activity_count;
        }

        public void setActivity_count(String activity_count) {
            this.activity_count = activity_count;
        }

        public String getIs_setpass() {
            return is_setpass;
        }

        public void setIs_setpass(String is_setpass) {
            this.is_setpass = is_setpass;
        }

        public String getIs_q() {
            return is_q;
        }

        public void setIs_q(String is_q) {
            this.is_q = is_q;
        }

        public int getIs_msg() {
            return is_msg;
        }

        public void setIs_msg(int is_msg) {
            this.is_msg = is_msg;
        }

        public String getQ_count() {
            return q_count;
        }

        public void setQ_count(String q_count) {
            this.q_count = q_count;
        }

        public String getUser_lv() {
            return user_lv;
        }

        public void setUser_lv(String user_lv) {
            this.user_lv = user_lv;
        }

        public String getLv_name() {
            return lv_name;
        }

        public void setLv_name(String lv_name) {
            this.lv_name = lv_name;
        }

        public String getLv_end_time() {
            return lv_end_time;
        }

        public void setLv_end_time(String lv_end_time) {
            this.lv_end_time = lv_end_time;
        }

        public ConsigneeBean getConsignee() {
            return consignee;
        }

        public void setConsignee(ConsigneeBean consignee) {
            this.consignee = consignee;
        }

        public static class ConsigneeBean {
            /**
             * consignee_name : 你明哥
             * consignee_phone : null
             * consignee_region_name : 北京北京市东城区
             * consignee_address : 大望路金地
             */

            private String consignee_name;
            private Object consignee_phone;
            private String consignee_region_name;
            private String consignee_address;

            public String getConsignee_name() {
                return consignee_name;
            }

            public void setConsignee_name(String consignee_name) {
                this.consignee_name = consignee_name;
            }

            public Object getConsignee_phone() {
                return consignee_phone;
            }

            public void setConsignee_phone(Object consignee_phone) {
                this.consignee_phone = consignee_phone;
            }

            public String getConsignee_region_name() {
                return consignee_region_name;
            }

            public void setConsignee_region_name(String consignee_region_name) {
                this.consignee_region_name = consignee_region_name;
            }

            public String getConsignee_address() {
                return consignee_address;
            }

            public void setConsignee_address(String consignee_address) {
                this.consignee_address = consignee_address;
            }
        }
    }
}
