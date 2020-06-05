package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/12.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class IndustryListBean  {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 5
         * category_name : 餐饮食品
         * category_en_name : Legal services
         * category_japan_name : 法律
         * type : 1
         * pid : 0
         * is_img : 0
         */

        private String category_id;
        private String category_name;
        private String category_en_name;
        private String category_japan_name;
        private String type;
        private String pid;
        private String is_img;

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

        public String getCategory_en_name() {
            return category_en_name;
        }

        public void setCategory_en_name(String category_en_name) {
            this.category_en_name = category_en_name;
        }

        public String getCategory_japan_name() {
            return category_japan_name;
        }

        public void setCategory_japan_name(String category_japan_name) {
            this.category_japan_name = category_japan_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getIs_img() {
            return is_img;
        }

        public void setIs_img(String is_img) {
            this.is_img = is_img;
        }
    }
}
