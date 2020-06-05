package com.hym.eshoplib.bean.shoppingcart;

import java.util.List;

/**
 * Created by 胡彦明 on 2017/8/2.
 * <p>
 * Description 购物车列表
 * <p>
 * otherTips
 */

public class ShoppingCarListBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * store_id : 5
         * store_name : 胖大厨
         * carts : [{"memo":"点点滴滴","stock":"30","image_default":"http://laopei.china-dt.com/uploads/27/4d/28/00/43/7d71c4e681b03c79523ebd.png","is_del":"0","relate":"","sale_vloum":"27","quantity":"8","specification_id":"15","cart_id":"22","price":"14.00","status":"1","name":"测试一下","is_shelf":"1","price_market":"222.00"},{"memo":"1111","stock":"18","image_default":"","is_del":"0","relate":"颜色:白色,","sale_vloum":"2","quantity":"1","specification_id":"23","cart_id":"23","price":"10.00","status":"1","name":"海边的卡夫卡[颜色:白色,]","is_shelf":"1","price_market":"0.00"},{"memo":"鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅鹅","stock":"100","image_default":"http://laopei.china-dt.com/uploads/4c/bf/cf/17/23/9a5034d3aeb7e1f9b1d629.jpg","is_del":"0","relate":"口感:干红,","sale_vloum":"0","quantity":"1","specification_id":"31","cart_id":"58","price":"100.00","status":"1","name":"红酒002[口感:干红,]","is_shelf":"1","price_market":"120.00"}]
         */

        private String store_id;
        private String store_name;
        private String store_image_default;
        private List<CartsBean> carts;

        public String getStore_image_default() {
            return store_image_default;
        }

        public void setStore_image_default(String store_image_default) {
            this.store_image_default = store_image_default;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public List<CartsBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartsBean> carts) {
            this.carts = carts;
        }

        public static class CartsBean {
            /**
             * memo : 点点滴滴
             * stock : 30
             * image_default : http://laopei.china-dt.com/uploads/27/4d/28/00/43/7d71c4e681b03c79523ebd.png
             * is_del : 0
             * relate :
             * sale_vloum : 27
             * quantity : 8
             * specification_id : 15
             * cart_id : 22
             * price : 14.00
             * status : 1
             * name : 测试一下
             * is_shelf : 1
             * price_market : 222.00
             */

            private String memo;
            private String stock;
            private String image_default;
            private String is_del;
            private String relate;
            private String sale_vloum;
            private String quantity;
            private String specification_id;
            private String cart_id;
            private String price;
            private String status;
            private String name;
            private String is_shelf;
            private String price_market;

            private String store_id;//店铺id 用来全选
            private boolean showShop=false;//自定义字段，是否显示店铺名称
            private String shopUrl;//自定义字段，商铺Logo
            private String shopName;//自定义字段，商铺名称
            private boolean isSelectAllShop=false;//店铺是否被全选

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public boolean isSelectAllShop() {
                return isSelectAllShop;
            }

            public void setSelectAllShop(boolean selectAllShop) {
                isSelectAllShop = selectAllShop;
            }

            public String getShopUrl() {
                return shopUrl;
            }

            public void setShopUrl(String shopUrl) {
                this.shopUrl = shopUrl;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public boolean isShowShop() {
                return showShop;
            }

            public void setShowShop(boolean showShop) {
                this.showShop = showShop;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getRelate() {
                return relate;
            }

            public void setRelate(String relate) {
                this.relate = relate;
            }

            public String getSale_vloum() {
                return sale_vloum;
            }

            public void setSale_vloum(String sale_vloum) {
                this.sale_vloum = sale_vloum;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIs_shelf() {
                return is_shelf;
            }

            public void setIs_shelf(String is_shelf) {
                this.is_shelf = is_shelf;
            }

            public String getPrice_market() {
                return price_market;
            }

            public void setPrice_market(String price_market) {
                this.price_market = price_market;
            }
        }
    }
}
