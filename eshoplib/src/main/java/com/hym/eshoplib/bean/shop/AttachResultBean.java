package com.hym.eshoplib.bean.shop;

/**
 * Created by 胡彦明 on 2018/9/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class AttachResultBean {

    /**
     * data : {"log_id":"6","order_number":"20180709-15160048-554910","money":900}
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
         * log_id : 6
         * order_number : 20180709-15160048-554910
         * money : 900
         */

        private String log_id;
        private String order_number;
        private int money;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
