package com.hym.eshoplib.bean.me;

import java.io.Serializable;

/**
 * Created by 胡彦明 on 2018/3/12.
 * <p>
 * Description 个人信息详细
 * <p>
 * otherTips
 */

public class MedetailBean implements Serializable{


    /**
     * data : {"aboutus_url":"http://jzweb.liandao.mobi/index.php/Home/cas/aboutus/type/app/language/1","activity_count":"1","age":"2005-3-15","agreement_url":"http://jzweb.liandao.mobi/index.php/Home/cas/agreement/type/app/language/1","avatar":"http://jzshop.liandao.mobi/uploads/64/e6/65/87/06/6bdd09d37bb496a90b2208.jpg","bts_bean":"100000","bts_score":"1810","consignee":{"consignee_address":"大望路金地","consignee_name":"你明哥","consignee_region_name":"北京北京市东城区"},"email":"","favorites_count":"9","feedback_url":"http://jzweb.liandao.mobi/index.php/Home/cas/feedback/type/app/language/1/token/23d3e747b53f4a6c7710b5be9d79b023","gender":"2","house_order_count":8,"is_msg":1,"is_q":"0","is_setpass":"1","lv_end_time":"2018-10-16 22:11:54","lv_name":"企业会员（签约俱乐部）","nickname":"明哥哥","phone":"18940105285","problem_url":"http://jzweb.liandao.mobi/index.php/Home/cas/system_problem/type/app/language/1","q_count":"1","release_count":"1","user_lv":"2","userid":"891","username":"18940105285"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        public String getPaypass_set() {
            return paypass_set;
        }

        public void setPaypass_set(String paypass_set) {
            this.paypass_set = paypass_set;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        /**
         * aboutus_url : http://jzweb.liandao.mobi/index.php/Home/cas/aboutus/type/app/language/1
         * activity_count : 1
         * age : 2005-3-15
         * agreement_url : http://jzweb.liandao.mobi/index.php/Home/cas/agreement/type/app/language/1
         * avatar : http://jzshop.liandao.mobi/uploads/64/e6/65/87/06/6bdd09d37bb496a90b2208.jpg
         * bts_bean : 100000
         * bts_score : 1810
         * consignee : {"consignee_address":"大望路金地","consignee_name":"你明哥","consignee_region_name":"北京北京市东城区"}
         * email :
         * favorites_count : 9
         * feedback_url : http://jzweb.liandao.mobi/index.php/Home/cas/feedback/type/app/language/1/token/23d3e747b53f4a6c7710b5be9d79b023
         * gender : 2
         * house_order_count : 8
         * is_msg : 1
         * is_q : 0
         * is_setpass : 1
         * lv_end_time : 2018-10-16 22:11:54
         * lv_name : 企业会员（签约俱乐部）
         * nickname : 明哥哥
         * phone : 18940105285
         * problem_url : http://jzweb.liandao.mobi/index.php/Home/cas/system_problem/type/app/language/1
         * q_count : 1
         * release_count : 1
         * user_lv : 2
         * userid : 891
         * username : 18940105285
         */
        private String category_id;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        private String share_url;
        private String service_id;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        private String paypass_set;
        private String aboutus_url;
        private String activity_count;
        private String age;
        private String agreement_url;
        private String avatar;
        private String bts_bean;
        private String bts_score;
        private ConsigneeBean consignee;
        private String email;
        private String favorites_count;
        private String feedback_url;
        private String gender;
        private int house_order_count;
        private int is_msg;
        private String is_q;
        private String is_setpass;
        private String lv_end_time;
        private String lv_name;
        private String nickname;
        private String phone;
        private String problem_url;
        private String q_count;
        private String release_count;
        private String user_lv;
        private String userid;
        private String username;
        private String pay;
        private String send;
        private String get;
        private String comm;
        private String is_store;
        private String rank_average;
        private String secret_phone;
        private String auth_personal;
        private String auth_business;
        private String lv_is_true;
        private String share_video_url;

        public String getShare_video_url() {
            return share_video_url;
        }

        public void setShare_video_url(String share_video_url) {
            this.share_video_url = share_video_url;
        }

        public String getLv_is_true() {
            return lv_is_true;
        }

        public void setLv_is_true(String lv_is_true) {
            this.lv_is_true = lv_is_true;
        }

        public String getAuth_personal() {
            return auth_personal;
        }

        public void setAuth_personal(String auth_personal) {
            this.auth_personal = auth_personal;
        }

        public String getAuth_business() {
            return auth_business;
        }

        public void setAuth_business(String auth_business) {
            this.auth_business = auth_business;
        }

        public String getSecret_phone() {
            return secret_phone;
        }

        public void setSecret_phone(String secret_phone) {
            this.secret_phone = secret_phone;
        }

        public String getIs_store() {
            return is_store;
        }

        public void setIs_store(String is_store) {
            this.is_store = is_store;
        }

        public String getRank_average() {
            return rank_average;
        }

        public void setRank_average(String rank_average) {
            this.rank_average = rank_average;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getSend() {
            return send;
        }

        public void setSend(String send) {
            this.send = send;
        }

        public String getGet() {
            return get;
        }

        public void setGet(String get) {
            this.get = get;
        }

        public String getComm() {
            return comm;
        }

        public void setComm(String comm) {
            this.comm = comm;
        }

        public String getAboutus_url() {
            return aboutus_url;
        }

        public void setAboutus_url(String aboutus_url) {
            this.aboutus_url = aboutus_url;
        }

        public String getActivity_count() {
            return activity_count;
        }

        public void setActivity_count(String activity_count) {
            this.activity_count = activity_count;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAgreement_url() {
            return agreement_url;
        }

        public void setAgreement_url(String agreement_url) {
            this.agreement_url = agreement_url;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBts_bean() {
            return bts_bean;
        }

        public void setBts_bean(String bts_bean) {
            this.bts_bean = bts_bean;
        }

        public String getBts_score() {
            return bts_score;
        }

        public void setBts_score(String bts_score) {
            this.bts_score = bts_score;
        }

        public ConsigneeBean getConsignee() {
            return consignee;
        }

        public void setConsignee(ConsigneeBean consignee) {
            this.consignee = consignee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFavorites_count() {
            return favorites_count;
        }

        public void setFavorites_count(String favorites_count) {
            this.favorites_count = favorites_count;
        }

        public String getFeedback_url() {
            return feedback_url;
        }

        public void setFeedback_url(String feedback_url) {
            this.feedback_url = feedback_url;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getHouse_order_count() {
            return house_order_count;
        }

        public void setHouse_order_count(int house_order_count) {
            this.house_order_count = house_order_count;
        }

        public int getIs_msg() {
            return is_msg;
        }

        public void setIs_msg(int is_msg) {
            this.is_msg = is_msg;
        }

        public String getIs_q() {
            return is_q;
        }

        public void setIs_q(String is_q) {
            this.is_q = is_q;
        }

        public String getIs_setpass() {
            return is_setpass;
        }

        public void setIs_setpass(String is_setpass) {
            this.is_setpass = is_setpass;
        }

        public String getLv_end_time() {
            return lv_end_time;
        }

        public void setLv_end_time(String lv_end_time) {
            this.lv_end_time = lv_end_time;
        }

        public String getLv_name() {
            return lv_name;
        }

        public void setLv_name(String lv_name) {
            this.lv_name = lv_name;
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

        public String getProblem_url() {
            return problem_url;
        }

        public void setProblem_url(String problem_url) {
            this.problem_url = problem_url;
        }

        public String getQ_count() {
            return q_count;
        }

        public void setQ_count(String q_count) {
            this.q_count = q_count;
        }

        public String getRelease_count() {
            return release_count;
        }

        public void setRelease_count(String release_count) {
            this.release_count = release_count;
        }

        public String getUser_lv() {
            return user_lv;
        }

        public void setUser_lv(String user_lv) {
            this.user_lv = user_lv;
        }

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

        public static class ConsigneeBean implements Serializable{
            /**
             * consignee_address : 大望路金地
             * consignee_name : 你明哥
             * consignee_region_name : 北京北京市东城区
             */

            private String consignee_address;
            private String consignee_name;
            private String consignee_region_name;

            public String getConsignee_address() {
                return consignee_address;
            }

            public void setConsignee_address(String consignee_address) {
                this.consignee_address = consignee_address;
            }

            public String getConsignee_name() {
                return consignee_name;
            }

            public void setConsignee_name(String consignee_name) {
                this.consignee_name = consignee_name;
            }

            public String getConsignee_region_name() {
                return consignee_region_name;
            }

            public void setConsignee_region_name(String consignee_region_name) {
                this.consignee_region_name = consignee_region_name;
            }
        }
    }
}
