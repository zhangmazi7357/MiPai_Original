package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/5/12.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class PayPalPayBean {

    /**
     * data : {"business_name":"Beijinfo订单支付","currency":"USD","memo":"Beijinfo订单支付","child_order_number":"D-20180402-17475497-102484","payment_amount":0.01}
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
         * business_name : Beijinfo订单支付
         * currency : USD
         * memo : Beijinfo订单支付
         * child_order_number : D-20180402-17475497-102484
         * payment_amount : 0.01
         */

        private String business_name;
        private String currency;
        private String memo;
        private String child_order_number;
        private double payment_amount;

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getChild_order_number() {
            return child_order_number;
        }

        public void setChild_order_number(String child_order_number) {
            this.child_order_number = child_order_number;
        }

        public double getPayment_amount() {
            return payment_amount;
        }

        public void setPayment_amount(double payment_amount) {
            this.payment_amount = payment_amount;
        }
    }
}
