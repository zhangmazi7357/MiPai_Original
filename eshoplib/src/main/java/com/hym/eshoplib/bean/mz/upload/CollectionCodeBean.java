package com.hym.eshoplib.bean.mz.upload;

/**
 * 生成收款码 Bean
 */
public class CollectionCodeBean {

    /**
     * data : {"code_url":"weixin://wxpay/bizpayurl?pr=d2rYnMq00","nonce_str":"hv7XCy3GDsOytp4G","prepay_id":"wx0317205389695354a8d57f4c4c36500000","sign":"0","timestamp":1604395253}
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
         * code_url : weixin://wxpay/bizpayurl?pr=d2rYnMq00
         * nonce_str : hv7XCy3GDsOytp4G
         * prepay_id : wx0317205389695354a8d57f4c4c36500000
         * sign : 0
         * timestamp : 1604395253
         */

        private String code_url;
        private String nonce_str;
        private String prepay_id;
        private String sign;
        private int timestamp;

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

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

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
