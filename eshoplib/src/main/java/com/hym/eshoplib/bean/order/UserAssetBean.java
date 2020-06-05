package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/4/7.
 * <p>
 * Description 用户余额
 * <p>
 * otherTips
 */

public class UserAssetBean {

    /**
     * data : {"credit":"175.00"}
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
         * credit : 175.00
         */

        private String credit;

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }
    }
}
