package com.hym.eshoplib.bean.order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2017/7/26.
 * <p>
 * Description 计算商品总价
 * <p>
 * otherTips
 */

public class GetTotalCountBean implements Serializable{


    /**
     * data : {"goods":[{"freight_amount":"0.00","store_goods":[{"buy_num":"1","image_default":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","name":"测试商品","price":"123210.00","property_related":"39:117","specdetail":"颜色:蓝色;","specification_id":"447"}],"store_image_default":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","store_name":"测试店铺","storeid":"228","total":"123210.00"}],"is_lv_user":0,"order_id":"20180404-10415854-505053","payment_amount":"123210.00","sale_coupon":"0.00","total_amount":"123210.00","user_balance":"0"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * goods : [{"freight_amount":"0.00","store_goods":[{"buy_num":"1","image_default":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","name":"测试商品","price":"123210.00","property_related":"39:117","specdetail":"颜色:蓝色;","specification_id":"447"}],"store_image_default":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","store_name":"测试店铺","storeid":"228","total":"123210.00"}]
         * is_lv_user : 0
         * order_id : 20180404-10415854-505053
         * payment_amount : 123210.00
         * sale_coupon : 0.00
         * total_amount : 123210.00
         * user_balance : 0
         */

        private int is_lv_user;
        private String order_id;
        private String payment_amount;
        private String sale_coupon;
        private String total_amount;
        private String user_balance;
        private List<GoodsBean> goods;

        public int getIs_lv_user() {
            return is_lv_user;
        }

        public void setIs_lv_user(int is_lv_user) {
            this.is_lv_user = is_lv_user;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPayment_amount() {
            return payment_amount;
        }

        public void setPayment_amount(String payment_amount) {
            this.payment_amount = payment_amount;
        }

        public String getSale_coupon() {
            return sale_coupon;
        }

        public void setSale_coupon(String sale_coupon) {
            this.sale_coupon = sale_coupon;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean implements Serializable{
            /**
             * freight_amount : 0.00
             * store_goods : [{"buy_num":"1","image_default":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","name":"测试商品","price":"123210.00","property_related":"39:117","specdetail":"颜色:蓝色;","specification_id":"447"}]
             * store_image_default : http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png
             * store_name : 测试店铺
             * storeid : 228
             * total : 123210.00
             */

            private String freight_amount;
            private String store_image_default;
            private String store_name;
            private String storeid;
            private String total;
            private List<StoreGoodsBean> store_goods;

            public String getFreight_amount() {
                return freight_amount;
            }

            public void setFreight_amount(String freight_amount) {
                this.freight_amount = freight_amount;
            }

            public String getStore_image_default() {
                return store_image_default;
            }

            public void setStore_image_default(String store_image_default) {
                this.store_image_default = store_image_default;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStoreid() {
                return storeid;
            }

            public void setStoreid(String storeid) {
                this.storeid = storeid;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<StoreGoodsBean> getStore_goods() {
                return store_goods;
            }

            public void setStore_goods(List<StoreGoodsBean> store_goods) {
                this.store_goods = store_goods;
            }

            public static class StoreGoodsBean implements Serializable{
                /**
                 * buy_num : 1
                 * image_default : http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg
                 * name : 测试商品
                 * price : 123210.00
                 * property_related : 39:117
                 * specdetail : 颜色:蓝色;
                 * specification_id : 447
                 */

                private String buy_num;
                private String image_default;
                private String name;
                private String price;
                private String property_related;
                private String specdetail;
                private String specification_id;

                public String getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(String buy_num) {
                    this.buy_num = buy_num;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getProperty_related() {
                    return property_related;
                }

                public void setProperty_related(String property_related) {
                    this.property_related = property_related;
                }

                public String getSpecdetail() {
                    return specdetail;
                }

                public void setSpecdetail(String specdetail) {
                    this.specdetail = specdetail;
                }

                public String getSpecification_id() {
                    return specification_id;
                }

                public void setSpecification_id(String specification_id) {
                    this.specification_id = specification_id;
                }
            }
        }
    }
}
