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
     * data : {"user_num":"1","money":"900.01","price":"900.01","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","store_name":"神司工作室","nickname":"网红视频工作室","avatar":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","phone":"18940105285","email":null,"category_id":"12","category_name":"文案策划-WORD方案","label_list":[{"label_name":"店主超赞"},{"label_name":"品质超高"},{"label_name":"服务超好"}],"ctime":"2018-09-20 14:04:11","rank_type":"5"}
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
         * money : 900.01
         * price : 900.01
         * logo : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
         * store_name : 神司工作室
         * nickname : 网红视频工作室
         * avatar : http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg
         * phone : 18940105285
         * email : null
         * category_id : 12
         * category_name : 文案策划-WORD方案
         * label_list : [{"label_name":"店主超赞"},{"label_name":"品质超高"},{"label_name":"服务超好"}]
         * ctime : 2018-09-20 14:04:11
         * rank_type : 5
         */

        private String user_num;
        private String money;
        private String price;
        private String logo;
        private String store_name;
        private String nickname;
        private String avatar;
        private String phone;
        private Object email;
        private String category_id;
        private String category_name;
        private String ctime;
        private String rank_type;
        private List<LabelListBean> label_list;
        private String auth_user;
        private String auth_store;

        public String getAuth_user() {
            return auth_user;
        }

        public void setAuth_user(String auth_user) {
            this.auth_user = auth_user;
        }

        public String getAuth_store() {
            return auth_store;
        }

        public void setAuth_store(String auth_store) {
            this.auth_store = auth_store;
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

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
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

        public List<LabelListBean> getLabel_list() {
            return label_list;
        }

        public void setLabel_list(List<LabelListBean> label_list) {
            this.label_list = label_list;
        }

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
    }
}
