package com.hym.eshoplib.bean.home;

public class CreateOrderBean {
    /**
     * data : {"log_id":"5124","money":50.5,"order_number":"20191125-14115155-559999"}
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
         * log_id : 5124
         * money : 50.5
         * order_number : 20191125-14115155-559999
         */

        private String log_id;
        private double money;
        private String order_number;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }
    }
}
