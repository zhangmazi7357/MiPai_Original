package com.hym.eshoplib.bean.me;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 提现账户列表
 * <p>
 * otherTips
 */

public class GetCashAccountBean implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * userbank_id : 355
         * userid : 1859
         * bankname : zhifubao@zhifubao.cn
         * bankuser : 二毛
         * bankcard : zhifubao@zhifubao.cn
         * is_default : 0
         * type : 1
         * phone : 18888888888
         */

        private String userbank_id;
        private String userid;
        private String bankname;
        private String bankuser;
        private String bankcard;
        private String is_default;
        private String type;
        private String phone;

        public String getUserbank_id() {
            return userbank_id;
        }

        public void setUserbank_id(String userbank_id) {
            this.userbank_id = userbank_id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getBankuser() {
            return bankuser;
        }

        public void setBankuser(String bankuser) {
            this.bankuser = bankuser;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
