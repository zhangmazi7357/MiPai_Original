package com.hym.eshoplib.bean.shop;

/**
 * Created by 胡彦明 on 2018/9/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class AddFavouriteBean {

    /**
     * data : {"status":1}
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
         * status : 1
         */

        private int status;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
