package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/9/30.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class IFsetPayPwdBean {

    /**
     * data : {"is_set":"1"}
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
         * is_set : 1
         */

        private String is_set;

        public String getIs_set() {
            return is_set;
        }

        public void setIs_set(String is_set) {
            this.is_set = is_set;
        }
    }
}
