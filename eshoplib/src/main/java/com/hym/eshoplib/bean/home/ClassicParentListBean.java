package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/19.
 * <p>
 * Description 商品分类一级列表
 * <p>
 * otherTips
 */

public class ClassicParentListBean {

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
         * store_id : 5
         * category_name : 数码
         */

        private String category_id;
        private String store_id;
        private String category_name;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
