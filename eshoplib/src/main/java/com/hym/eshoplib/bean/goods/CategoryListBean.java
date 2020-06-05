package com.hym.eshoplib.bean.goods;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/8/3.
 * <p>
 * Description 分类列表
 * <p>
 * otherTips
 */

public class CategoryListBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 1
         * category_name : 文案策划
         * category_en_name :
         * category_japan_name : ニュース
         * pid : 0
         * memo :
         * type : 0
         */

        private String category_id;
        private String category_name;
        private String category_en_name;
        private String category_japan_name;
        private String pid;
        private String memo;
        private String type;
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
