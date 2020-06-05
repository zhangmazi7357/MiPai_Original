package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/10/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class LogDetailBean {

    /**
     * data : {"log_id":"5","value":"100.00","cause":"充值","ctime":"2018-07-27 14:49:49","type":"1","after_value":"100","order_number":"S-20180726-1532584819","paytype":"1","log_number":"0","pay_account":"2018041639476826478294654","account_number":"zhifubao@zhifubao.cn","deposit_bank":"zhifubao@zhifubao.cn","account":"二毛","remark":"我的钱包提现到支付宝账号"}
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
         * log_id : 5
         * value : 100.00
         * cause : 充值
         * ctime : 2018-07-27 14:49:49
         * type : 1
         * after_value : 100
         * order_number : S-20180726-1532584819
         * paytype : 1
         * log_number : 0
         * pay_account : 2018041639476826478294654
         * account_number : zhifubao@zhifubao.cn
         * deposit_bank : zhifubao@zhifubao.cn
         * account : 二毛
         * remark : 我的钱包提现到支付宝账号
         */

        private String log_id;
        private String value;
        private String cause;
        private String ctime;
        private String type;
        private String after_value;
        private String order_number;
        private String paytype;
        private String log_number;
        private String pay_account;
        private String account_number;
        private String deposit_bank;
        private String account;
        private String remark;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAfter_value() {
            return after_value;
        }

        public void setAfter_value(String after_value) {
            this.after_value = after_value;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getLog_number() {
            return log_number;
        }

        public void setLog_number(String log_number) {
            this.log_number = log_number;
        }

        public String getPay_account() {
            return pay_account;
        }

        public void setPay_account(String pay_account) {
            this.pay_account = pay_account;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getDeposit_bank() {
            return deposit_bank;
        }

        public void setDeposit_bank(String deposit_bank) {
            this.deposit_bank = deposit_bank;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
