package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/11/22.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class GetCashInfoBean {

    /**
     * data : {"p_total":0.77,"total":100.23,"memo":"额外扣除¥0.77手续费(费率0.76%)"}
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
         * p_total : 0.77
         * total : 100.23
         * memo : 额外扣除¥0.77手续费(费率0.76%)
         */

        private double p_total;
        private double total;
        private String memo;

        public double getP_total() {
            return p_total;
        }

        public void setP_total(double p_total) {
            this.p_total = p_total;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
