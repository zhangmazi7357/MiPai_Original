package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/20.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class CommentDetailBean {


    /**
     * data : {"user_num":"1","money":"0.01","price":"28000.00","logo":"http://mpai.liandao.mobi/uploads/5c/46/a6/17/68/8cccb1ca3a2a7651ebb4b1.jpeg","store_name":"风行工作室","nickname":"红***牛","avatar":"http://mpai.liandao.mobi/uploads/07/34/c4/66/f6/d379a53ea45506beda953a.jpeg","phone":"17611020312","email":null,"category_id":"18","store_userid":"4344","log_userid":"5019","category_name":"视频团队-专业级","label_list":[],"ctime":"2020-08-21 16:04:01","rank_type":null,"auth_store":1,"auth_user":0}
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
         * user_num : 1
         * money : 0.01
         * price : 28000.00
         * logo : http://mpai.liandao.mobi/uploads/5c/46/a6/17/68/8cccb1ca3a2a7651ebb4b1.jpeg
         * store_name : 风行工作室
         * nickname : 红***牛
         * avatar : http://mpai.liandao.mobi/uploads/07/34/c4/66/f6/d379a53ea45506beda953a.jpeg
         * phone : 17611020312
         * email : null
         * category_id : 18
         * store_userid : 4344
         * log_userid : 5019
         * category_name : 视频团队-专业级
         * label_list : []
         * ctime : 2020-08-21 16:04:01
         * rank_type : null
         * auth_store : 1
         * auth_user : 0
         */

        private String user_num;
        private String money;
        private String price;
        private String logo;
        private String store_name;
        private String nickname;
        private String avatar;
        private String phone;
        private String email;
        private String category_id;
        private String store_userid;
        private String log_userid;
        private String category_name;
        private String ctime;
        private String rank_type;
        private int auth_store;
        private int auth_user;
        private String content;
        private String images;
        private List<LabelListBean> label_list;


        public static class LabelListBean {
            /**
             * label_name : 店主超赞
             */

            private String label_name;

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }


        }

        public String getUser_num() {
            return user_num;
        }

        public void setUser_num(String user_num) {
            this.user_num = user_num;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getStore_userid() {
            return store_userid;
        }

        public void setStore_userid(String store_userid) {
            this.store_userid = store_userid;
        }

        public String getLog_userid() {
            return log_userid;
        }

        public void setLog_userid(String log_userid) {
            this.log_userid = log_userid;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getRank_type() {
            return rank_type;
        }

        public void setRank_type(String rank_type) {
            this.rank_type = rank_type;
        }

        public int getAuth_store() {
            return auth_store;
        }

        public void setAuth_store(int auth_store) {
            this.auth_store = auth_store;
        }

        public int getAuth_user() {
            return auth_user;
        }

        public void setAuth_user(int auth_user) {
            this.auth_user = auth_user;
        }

        public List<LabelListBean> getLabel_list() {
            return label_list;
        }

        public void setLabel_list(List<LabelListBean> label_list) {
            this.label_list = label_list;
        }


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}
