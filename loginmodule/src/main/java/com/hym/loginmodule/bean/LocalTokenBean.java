package com.hym.loginmodule.bean;

/**
 * Created by 胡彦明 on 2018/1/18.
 * <p>
 * Description 获取本地token
 * <p>
 * otherTips
 */

public class LocalTokenBean {


    /**
     * data : {"localtoken":"781c726a2d56c0c20baf92f3dc74e7d5"}
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
         * localtoken : 781c726a2d56c0c20baf92f3dc74e7d5
         */

        private String localtoken;

        public String getLocaltoken() {
            return localtoken;
        }

        public void setLocaltoken(String localtoken) {
            this.localtoken = localtoken;
        }
    }
}
