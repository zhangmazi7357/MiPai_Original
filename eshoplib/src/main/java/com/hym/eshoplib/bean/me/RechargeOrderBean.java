package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/10/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class RechargeOrderBean {

    /**
     * data : {"ordernumber":"S-20180726-1532584819","pay_order_id":"36"}
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
         * ordernumber : S-20180726-1532584819
         * pay_order_id : 36
         */

        private String ordernumber;
        private String pay_order_id;

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getPay_order_id() {
            return pay_order_id;
        }

        public void setPay_order_id(String pay_order_id) {
            this.pay_order_id = pay_order_id;
        }
    }
}
