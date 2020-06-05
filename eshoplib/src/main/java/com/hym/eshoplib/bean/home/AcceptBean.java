package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class AcceptBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * phone : 176****8910
         * nickname : 1111
         * category_name : 三维动画服务
         * content_id : 76
         */

        private String phone;
        private String nickname;
        private String category_name;
        private String content_id;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }
    }
}
