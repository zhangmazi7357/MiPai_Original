package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/14.
 * <p>
 * Description 订单详情
 * <p>
 * otherTips
 */

public class OrderDetailBean {

    /**
     * data : {"child_order_id":"418","child_order_number":"D-20180414-11184856-999997","store_id":"5","status":"1","pay_status":"0","consignee_name":"你明哥","consignee_region_name":"北京北京市东城区","consignee_address":"大望路金地","consignee_email":null,"consignee_mobile":"18888888888","total_amount":"20.00","payment_amount":"30.00","ctime":"2018-04-14 11:18:48","pay_time":"","pay_number":null,"payment_alias":null,"invoice":"1","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"http://jzshop.liandao.mobi/uploads/9e/71/ce/93/fd/b56a4fe25ae8b2c26a1168.jpg","goods_num":1,"status_name":"待付款","items":[{"item_id":"424","specification_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","is_comment":"0","cust_status":"0","property_relate":""}],"remaining_time":69,"delivery_time":"","deal_time":""}
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
         * child_order_id : 418
         * child_order_number : D-20180414-11184856-999997
         * store_id : 5
         * status : 1
         * pay_status : 0
         * consignee_name : 你明哥
         * consignee_region_name : 北京北京市东城区
         * consignee_address : 大望路金地
         * consignee_email : null
         * consignee_mobile : 18888888888
         * total_amount : 20.00
         * payment_amount : 30.00
         * ctime : 2018-04-14 11:18:48
         * pay_time :
         * pay_number : null
         * payment_alias : null
         * invoice : 1
         * freight_amount : 10.00
         * store_name : 老赔台球商贸公司
         * store_logo : http://jzshop.liandao.mobi/uploads/9e/71/ce/93/fd/b56a4fe25ae8b2c26a1168.jpg
         * goods_num : 1
         * status_name : 待付款
         * items : [{"item_id":"424","specification_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","is_comment":"0","cust_status":"0","property_relate":""}]
         * remaining_time : 69
         * delivery_time :
         * deal_time :
         */

        private String child_order_id;
        private String child_order_number;
        private String store_id;
        private String status;
        private String pay_status;
        private String consignee_name;
        private String consignee_region_name;
        private String consignee_address;
        private Object consignee_email;
        private String consignee_mobile;
        private String total_amount;
        private String payment_amount;
        private String ctime;
        private String pay_time;
        private Object pay_number;
        private Object payment_alias;
        private String invoice;
        private String freight_amount;
        private String store_name;
        private String store_logo;
        private int goods_num;
        private String status_name;
        private int remaining_time;
        private String delivery_time;
        private String deal_time;
        private List<ItemsBean> items;

        public String getChild_order_id() {
            return child_order_id;
        }

        public void setChild_order_id(String child_order_id) {
            this.child_order_id = child_order_id;
        }

        public String getChild_order_number() {
            return child_order_number;
        }

        public void setChild_order_number(String child_order_number) {
            this.child_order_number = child_order_number;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getConsignee_name() {
            return consignee_name;
        }

        public void setConsignee_name(String consignee_name) {
            this.consignee_name = consignee_name;
        }

        public String getConsignee_region_name() {
            return consignee_region_name;
        }

        public void setConsignee_region_name(String consignee_region_name) {
            this.consignee_region_name = consignee_region_name;
        }

        public String getConsignee_address() {
            return consignee_address;
        }

        public void setConsignee_address(String consignee_address) {
            this.consignee_address = consignee_address;
        }

        public Object getConsignee_email() {
            return consignee_email;
        }

        public void setConsignee_email(Object consignee_email) {
            this.consignee_email = consignee_email;
        }

        public String getConsignee_mobile() {
            return consignee_mobile;
        }

        public void setConsignee_mobile(String consignee_mobile) {
            this.consignee_mobile = consignee_mobile;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getPayment_amount() {
            return payment_amount;
        }

        public void setPayment_amount(String payment_amount) {
            this.payment_amount = payment_amount;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public Object getPay_number() {
            return pay_number;
        }

        public void setPay_number(Object pay_number) {
            this.pay_number = pay_number;
        }

        public Object getPayment_alias() {
            return payment_alias;
        }

        public void setPayment_alias(Object payment_alias) {
            this.payment_alias = payment_alias;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getFreight_amount() {
            return freight_amount;
        }

        public void setFreight_amount(String freight_amount) {
            this.freight_amount = freight_amount;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public int getRemaining_time() {
            return remaining_time;
        }

        public void setRemaining_time(int remaining_time) {
            this.remaining_time = remaining_time;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getDeal_time() {
            return deal_time;
        }

        public void setDeal_time(String deal_time) {
            this.deal_time = deal_time;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * item_id : 424
             * specification_id : 414
             * goods_name : 野豹台球杆职业y5皮头（运费到付）
             * goods_image : http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg
             * buy_price : 20.00
             * buy_num : 1
             * is_comment : 0
             * cust_status : 0
             * property_relate :
             */

            private String item_id;
            private String specification_id;
            private String goods_name;
            private String goods_image;
            private String buy_price;
            private String buy_num;
            private String is_comment;
            private String cust_status;
            private String property_relate;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public String getBuy_num() {
                return buy_num;
            }

            public void setBuy_num(String buy_num) {
                this.buy_num = buy_num;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public String getCust_status() {
                return cust_status;
            }

            public void setCust_status(String cust_status) {
                this.cust_status = cust_status;
            }

            public String getProperty_relate() {
                return property_relate;
            }

            public void setProperty_relate(String property_relate) {
                this.property_relate = property_relate;
            }
        }
    }
}
