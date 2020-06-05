package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/30.
 * <p>
 * Description 交易信息
 * <p>
 * otherTips
 */

public class TipsMessageBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 您的工作室审核通过
         * to_id : 1974
         * phone : 测试账号186
         * nickname : null
         * category_name : null
         * content_id : null
         * memo : 测试账号186已申请成功为--工作室
         */

        private String title;
        private String to_id;
        private String phone;
        private Object nickname;
        private Object category_name;
        private Object content_id;
        private String memo;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTo_id() {
            return to_id;
        }

        public void setTo_id(String to_id) {
            this.to_id = to_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getCategory_name() {
            return category_name;
        }

        public void setCategory_name(Object category_name) {
            this.category_name = category_name;
        }

        public Object getContent_id() {
            return content_id;
        }

        public void setContent_id(Object content_id) {
            this.content_id = content_id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
