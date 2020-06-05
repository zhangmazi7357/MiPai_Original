package com.hym.eshoplib.bean.invoice;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/11.
 * <p>
 * Description 增票资质
 * <p>
 * otherTips
 */

public class InvoiceBean {

    /**
     * data : {"id":"12","userid":"891","company":"稻田","taxpayer_number":"1234564","registered_address":"软件园","tel":"18888888888","bank":"工商银行","bank_account":"张博","status":"0","attachment_s":[{"filepath":"http://jzshop.liandao.mobi/uploads/00/88/53/e3/4c/7f032e4339c5f90c91c8be.jpg"}]}
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
         * id : 12
         * userid : 891
         * company : 稻田
         * taxpayer_number : 1234564
         * registered_address : 软件园
         * tel : 18888888888
         * bank : 工商银行
         * bank_account : 张博
         * status : 0
         * attachment_s : [{"filepath":"http://jzshop.liandao.mobi/uploads/00/88/53/e3/4c/7f032e4339c5f90c91c8be.jpg"}]
         */

        private String id;
        private String userid;
        private String company;
        private String taxpayer_number;
        private String registered_address;
        private String tel;
        private String bank;
        private String bank_account;
        private String status;
        private List<AttachmentSBean> attachment_s;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getTaxpayer_number() {
            return taxpayer_number;
        }

        public void setTaxpayer_number(String taxpayer_number) {
            this.taxpayer_number = taxpayer_number;
        }

        public String getRegistered_address() {
            return registered_address;
        }

        public void setRegistered_address(String registered_address) {
            this.registered_address = registered_address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBank_account() {
            return bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<AttachmentSBean> getAttachment_s() {
            return attachment_s;
        }

        public void setAttachment_s(List<AttachmentSBean> attachment_s) {
            this.attachment_s = attachment_s;
        }

        public static class AttachmentSBean {
            /**
             * filepath : http://jzshop.liandao.mobi/uploads/00/88/53/e3/4c/7f032e4339c5f90c91c8be.jpg
             */

            private String filepath;

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }
        }
    }
}
