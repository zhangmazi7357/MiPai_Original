package com.hym.eshoplib.bean.me;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/25.
 * <p>
 * Description 售后列表
 * <p>
 * otherTips
 */

public class CustomServiceListBean implements Serializable{

    /**
     * data : {"currentpage":1,"info":[{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"123","content_id":"2","ctime":"2018-04-25 10:59:33","cust_resualt":"0","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"0","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"130","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"},{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"8","content_id":"2","ctime":"2018-04-15 16:04:23","cust_resualt":"1","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"1","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"8","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"},{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"7","content_id":"2","ctime":"2018-04-15 16:03:52","cust_resualt":"1","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"1","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"7","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"}],"totalnum":"3","totalpage":1}
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
         * currentpage : 1
         * info : [{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"123","content_id":"2","ctime":"2018-04-25 10:59:33","cust_resualt":"0","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"0","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"130","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"},{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"8","content_id":"2","ctime":"2018-04-15 16:04:23","cust_resualt":"1","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"1","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"8","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"},{"bn":"P15237725089796","buy_num":"1","buy_price":"36888.00","child_order_id":"7","content_id":"2","ctime":"2018-04-15 16:03:52","cust_resualt":"1","cust_status":"0","goods_image":"http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg","goods_name":"三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB","is_comment":"1","is_delivery":"0","is_invoice":"0","is_package":"0","item_id":"7","order_status_memo":"可申请","payment_amount":"36888.00","price_cost":"35888.00","property_relate":"","refund_sn":"0","sale_coupon":"0.00","sale_gift_id":"0","sale_gift_num":"0","sale_id":"0","sale_memo":"无","sale_price":"0.00","sale_type":"0","unit_id":"1"}]
         * totalnum : 3
         * totalpage : 1
         */

        private int currentpage;
        private String totalnum;
        private int totalpage;
        private List<InfoBean> info;

        public int getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(int currentpage) {
            this.currentpage = currentpage;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable{
            /**
             * bn : P15237725089796
             * buy_num : 1
             * buy_price : 36888.00
             * child_order_id : 123
             * content_id : 2
             * ctime : 2018-04-25 10:59:33
             * cust_resualt : 0
             * cust_status : 0
             * goods_image : http://jzshop.liandao.mobi/uploads/57/34/4b/e7/61/c3eb6bbdda469f549c64d2.jpg
             * goods_name : 三星（SAMSUNG） Gold Elite 三星S8+双曲屏面 智能手机高端私人订制 3um 24Kt 黄金 64GB
             * is_comment : 0
             * is_delivery : 0
             * is_invoice : 0
             * is_package : 0
             * item_id : 130
             * order_status_memo : 可申请
             * payment_amount : 36888.00
             * price_cost : 35888.00
             * property_relate :
             * refund_sn : 0
             * sale_coupon : 0.00
             * sale_gift_id : 0
             * sale_gift_num : 0
             * sale_id : 0
             * sale_memo : 无
             * sale_price : 0.00
             * sale_type : 0
             * unit_id : 1
             */

            private String bn;
            private String buy_num;
            private String buy_price;
            private String child_order_id;
            private String content_id;
            private String ctime;
            private String cust_resualt;
            private String cust_status;
            private String goods_image;
            private String goods_name;
            private String is_comment;
            private String is_delivery;
            private String is_invoice;
            private String is_package;
            private String item_id;
            private String order_status_memo;
            private String payment_amount;
            private String price_cost;
            private String property_relate;
            private String refund_sn;
            private String sale_coupon;
            private String sale_gift_id;
            private String sale_gift_num;
            private String sale_id;
            private String sale_memo;
            private String sale_price;
            private String sale_type;
            private String unit_id;

            public String getBn() {
                return bn;
            }

            public void setBn(String bn) {
                this.bn = bn;
            }

            public String getBuy_num() {
                return buy_num;
            }

            public void setBuy_num(String buy_num) {
                this.buy_num = buy_num;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public String getChild_order_id() {
                return child_order_id;
            }

            public void setChild_order_id(String child_order_id) {
                this.child_order_id = child_order_id;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getCust_resualt() {
                return cust_resualt;
            }

            public void setCust_resualt(String cust_resualt) {
                this.cust_resualt = cust_resualt;
            }

            public String getCust_status() {
                return cust_status;
            }

            public void setCust_status(String cust_status) {
                this.cust_status = cust_status;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public String getIs_delivery() {
                return is_delivery;
            }

            public void setIs_delivery(String is_delivery) {
                this.is_delivery = is_delivery;
            }

            public String getIs_invoice() {
                return is_invoice;
            }

            public void setIs_invoice(String is_invoice) {
                this.is_invoice = is_invoice;
            }

            public String getIs_package() {
                return is_package;
            }

            public void setIs_package(String is_package) {
                this.is_package = is_package;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getOrder_status_memo() {
                return order_status_memo;
            }

            public void setOrder_status_memo(String order_status_memo) {
                this.order_status_memo = order_status_memo;
            }

            public String getPayment_amount() {
                return payment_amount;
            }

            public void setPayment_amount(String payment_amount) {
                this.payment_amount = payment_amount;
            }

            public String getPrice_cost() {
                return price_cost;
            }

            public void setPrice_cost(String price_cost) {
                this.price_cost = price_cost;
            }

            public String getProperty_relate() {
                return property_relate;
            }

            public void setProperty_relate(String property_relate) {
                this.property_relate = property_relate;
            }

            public String getRefund_sn() {
                return refund_sn;
            }

            public void setRefund_sn(String refund_sn) {
                this.refund_sn = refund_sn;
            }

            public String getSale_coupon() {
                return sale_coupon;
            }

            public void setSale_coupon(String sale_coupon) {
                this.sale_coupon = sale_coupon;
            }

            public String getSale_gift_id() {
                return sale_gift_id;
            }

            public void setSale_gift_id(String sale_gift_id) {
                this.sale_gift_id = sale_gift_id;
            }

            public String getSale_gift_num() {
                return sale_gift_num;
            }

            public void setSale_gift_num(String sale_gift_num) {
                this.sale_gift_num = sale_gift_num;
            }

            public String getSale_id() {
                return sale_id;
            }

            public void setSale_id(String sale_id) {
                this.sale_id = sale_id;
            }

            public String getSale_memo() {
                return sale_memo;
            }

            public void setSale_memo(String sale_memo) {
                this.sale_memo = sale_memo;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public String getSale_type() {
                return sale_type;
            }

            public void setSale_type(String sale_type) {
                this.sale_type = sale_type;
            }

            public String getUnit_id() {
                return unit_id;
            }

            public void setUnit_id(String unit_id) {
                this.unit_id = unit_id;
            }
        }
    }
}
