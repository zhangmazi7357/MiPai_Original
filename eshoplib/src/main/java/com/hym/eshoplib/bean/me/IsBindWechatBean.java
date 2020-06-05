package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/10/11.
 * <p>
 * Description 是否绑定微信
 * <p>
 * otherTips
 */

public class IsBindWechatBean {

    /**
     * data : {"binding":0}
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
         * binding : 0
         */

        private int binding;

        public int getBinding() {
            return binding;
        }

        public void setBinding(int binding) {
            this.binding = binding;
        }
    }
}
