package com.hym.eshoplib.bean.order;

/**
 * Created by 陈晖 on 2018/4/15.
 */

public class AliPayBean {


    /**
     * data : {"sign":"oAWSw1XLe03Bm1sjshrM79tIVnwuRXVzCg6hGUGWFFSeiY4J4v41VMVktBHSTU+IGusGFRgck9E24Y7/f+GJcktcfceYP15lZkZNfdc5x8xyxoza1vxYX6tLaOsXbsog8VAhFys/JFMxO/Zig0+gFyzklOsdS/0JTGHENZG91Cw=","sign_type":"RSA","str":"_input_charset=utf-8&body=jxqz&notify_url=http%3A%2F%2F112.64.173.178%3A20033%2Fbts%2Findex%2Falinotify&out_trade_no=121395920170801165804&partner=2088121750701646&payment_type=1&return_url=http%3A%2F%2F112.64.173.178%3A20033%2Fbts%2Falipay%2Falipaynotify&seller_id=2088121750701646&service=alipay.wap.create.direct.pay.by.user&show_url=http%3A%2F%2F112.64.173.178%3A20033%2Fgoods&subject=jxqz&total_fee=1.00&sign=oAWSw1XLe03Bm1sjshrM79tIVnwuRXVzCg6hGUGWFFSeiY4J4v41VMVktBHSTU%2BIGusGFRgck9E24Y7%2Ff%2BGJcktcfceYP15lZkZNfdc5x8xyxoza1vxYX6tLaOsXbsog8VAhFys%2FJFMxO%2FZig0%2BgFyzklOsdS%2F0JTGHENZG91Cw%3D&sign_type=RSA"}
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
         * sign : oAWSw1XLe03Bm1sjshrM79tIVnwuRXVzCg6hGUGWFFSeiY4J4v41VMVktBHSTU+IGusGFRgck9E24Y7/f+GJcktcfceYP15lZkZNfdc5x8xyxoza1vxYX6tLaOsXbsog8VAhFys/JFMxO/Zig0+gFyzklOsdS/0JTGHENZG91Cw=
         * sign_type : RSA
         * str : _input_charset=utf-8&body=jxqz&notify_url=http%3A%2F%2F112.64.173.178%3A20033%2Fbts%2Findex%2Falinotify&out_trade_no=121395920170801165804&partner=2088121750701646&payment_type=1&return_url=http%3A%2F%2F112.64.173.178%3A20033%2Fbts%2Falipay%2Falipaynotify&seller_id=2088121750701646&service=alipay.wap.create.direct.pay.by.user&show_url=http%3A%2F%2F112.64.173.178%3A20033%2Fgoods&subject=jxqz&total_fee=1.00&sign=oAWSw1XLe03Bm1sjshrM79tIVnwuRXVzCg6hGUGWFFSeiY4J4v41VMVktBHSTU%2BIGusGFRgck9E24Y7%2Ff%2BGJcktcfceYP15lZkZNfdc5x8xyxoza1vxYX6tLaOsXbsog8VAhFys%2FJFMxO%2FZig0%2BgFyzklOsdS%2F0JTGHENZG91Cw%3D&sign_type=RSA
         */

        private String sign;
        private String sign_type;
        private String str;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
