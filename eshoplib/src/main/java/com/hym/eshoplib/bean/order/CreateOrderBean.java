package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/4/12.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class CreateOrderBean {

    /**
     * data : {"child_order_id":"347","child_order_number":"D-20180412-11312297-505549","consignee_address":"你要相信我摸摸哦哦哦浏览量","consignee_mobile":"18888888888","consignee_name":"空军建军节","coupon_amount":0,"ctime":"2018-04-12 11:31:22","freight_amount":"10.00","get_time":"","invoice":"1","is_refund":0,"operator":"891","pay_status":0,"payment_amount":30,"process":"1:2:3:4:5:6","remark":"1","sale_coupon":"0","status":1,"store_id":"5","total_amount":20,"userid":"891","username":"18940105285"}
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
         * child_order_id : 347
         * child_order_number : D-20180412-11312297-505549
         * consignee_address : 你要相信我摸摸哦哦哦浏览量
         * consignee_mobile : 18888888888
         * consignee_name : 空军建军节
         * coupon_amount : 0
         * ctime : 2018-04-12 11:31:22
         * freight_amount : 10.00
         * get_time :
         * invoice : 1
         * is_refund : 0
         * operator : 891
         * pay_status : 0
         * payment_amount : 30
         * process : 1:2:3:4:5:6
         * remark : 1
         * sale_coupon : 0
         * status : 1
         * store_id : 5
         * total_amount : 20
         * userid : 891
         * username : 18940105285
         */

        private String child_order_id;
        private String child_order_number;
        private String consignee_address;
        private String consignee_mobile;
        private String consignee_name;
        private int coupon_amount;
        private String ctime;
        private String freight_amount;
        private String get_time;
        private String invoice;
        private int is_refund;
        private String operator;
        private int pay_status;
        private int payment_amount;
        private String process;
        private String remark;
        private String sale_coupon;
        private int status;
        private String store_id;
        private int total_amount;
        private String userid;
        private String username;

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

        public String getConsignee_address() {
            return consignee_address;
        }

        public void setConsignee_address(String consignee_address) {
            this.consignee_address = consignee_address;
        }

        public String getConsignee_mobile() {
            return consignee_mobile;
        }

        public void setConsignee_mobile(String consignee_mobile) {
            this.consignee_mobile = consignee_mobile;
        }

        public String getConsignee_name() {
            return consignee_name;
        }

        public void setConsignee_name(String consignee_name) {
            this.consignee_name = consignee_name;
        }

        public int getCoupon_amount() {
            return coupon_amount;
        }

        public void setCoupon_amount(int coupon_amount) {
            this.coupon_amount = coupon_amount;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getFreight_amount() {
            return freight_amount;
        }

        public void setFreight_amount(String freight_amount) {
            this.freight_amount = freight_amount;
        }

        public String getGet_time() {
            return get_time;
        }

        public void setGet_time(String get_time) {
            this.get_time = get_time;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getPayment_amount() {
            return payment_amount;
        }

        public void setPayment_amount(int payment_amount) {
            this.payment_amount = payment_amount;
        }

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSale_coupon() {
            return sale_coupon;
        }

        public void setSale_coupon(String sale_coupon) {
            this.sale_coupon = sale_coupon;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
