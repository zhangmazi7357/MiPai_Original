package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/4/12.
 * <p>
 * Description 微信支付签名回执
 * <p>
 * otherTips
 */

public class WxpayBean {

    /**
     * data : {"prepay_id":"wx201708181120043422714e170127559014","sign":"0007DB04A3D072163A93E4FC9CE4C813","nonce_str":"HTIOj0ooMVbyGeDT","timestamp":"20170818112004"}
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
         * prepay_id : wx201708181120043422714e170127559014
         * sign : 0007DB04A3D072163A93E4FC9CE4C813
         * nonce_str : HTIOj0ooMVbyGeDT
         * timestamp : 20170818112004
         */

        private String prepay_id;
        private String sign;
        private String nonce_str;
        private String timestamp;

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
