package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/19.
 * <p>
 * Description 商品分类二级列表
 * <p>
 * otherTips
 */

public class ClassicSecondListBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 9
         * store_id : 5
         * category_name : 手机
         * son : [{"category_id":"45","store_id":"5","category_name":"苹果","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"},{"category_id":"46","store_id":"5","category_name":"华为","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"},{"category_id":"47","store_id":"5","category_name":"vivo","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"},{"category_id":"48","store_id":"5","category_name":"oppo","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"}]
         */

        private String category_id;
        private String store_id;
        private String category_name;
        private List<SonBean> son;

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

        public List<SonBean> getSon() {
            return son;
        }

        public void setSon(List<SonBean> son) {
            this.son = son;
        }

        public static class SonBean {
            /**
             * category_id : 45
             * store_id : 5
             * category_name : 苹果
             * image_default : http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg
             */

            private String category_id;
            private String store_id;
            private String category_name;
            private String image_default;

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

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }
        }
    }
}
