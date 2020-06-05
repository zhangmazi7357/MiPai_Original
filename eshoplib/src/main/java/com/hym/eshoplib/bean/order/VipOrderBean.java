package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2019/9/3.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class VipOrderBean {

    /**
     * data : {"ordernumber":"20190903-17084398-979854"}
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
         * ordernumber : 20190903-17084398-979854
         */

        private String ordernumber;

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }
    }
}
