package com.hym.eshoplib.bean.shoppingcart;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ShoppingcatrtListBeanMipai {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * quantity : 1
         * content_id : 20
         * cart_id : 511
         * price : 900.00
         * logo : http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png
         * store_name : 神司工作室
         * category_name : WORD方案
         * total : 1800.02
         */

        private String quantity;
        private String content_id;
        private String cart_id;
        private String price;
        private String logo;
        private String store_name;
        private String category_name;
        private String total;
        private String is_down;
        private String auth;
        private String auth_user;

        public String getAuth_user() {
            return auth_user;
        }

        public void setAuth_user(String auth_user) {
            this.auth_user = auth_user;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getIs_down() {
            return is_down;
        }

        public void setIs_down(String is_down) {
            this.is_down = is_down;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
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

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
