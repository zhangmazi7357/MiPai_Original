package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/12/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class BusinessAuthInfoBean {

    /**
     * data : {"address":"明年","au_img":[{"attachment_id":"12214","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/14/e0/80/27/9b/18d905949769f5da48d829.jpg","type":"2","userid":"2173"}],"compny_name":"恩","compny_type":"cn.hym.superlib.widg","credit_code":"54646","id_img":[{"attachment_id":"12221","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/82/31/77/ec/f2/76482b5ba0c991584d5e8e.png","type":"3","userid":"2173"},{"attachment_id":"12222","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/76/64/e5/21/96/27a01f8012e1ce0fb3b103.png","type":"3","userid":"2173"},{"attachment_id":"12217","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/bf/7c/85/1f/5c/c33facda7716c2d1d3cebf.png","type":"3","userid":"2173"}],"idcard":"210112198808899546","realname":"啊","status":"0","type":"2"}
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
         * address : 明年
         * au_img : [{"attachment_id":"12214","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/14/e0/80/27/9b/18d905949769f5da48d829.jpg","type":"2","userid":"2173"}]
         * compny_name : 恩
         * compny_type : cn.hym.superlib.widg
         * credit_code : 54646
         * id_img : [{"attachment_id":"12221","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/82/31/77/ec/f2/76482b5ba0c991584d5e8e.png","type":"3","userid":"2173"},{"attachment_id":"12222","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/76/64/e5/21/96/27a01f8012e1ce0fb3b103.png","type":"3","userid":"2173"},{"attachment_id":"12217","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/bf/7c/85/1f/5c/c33facda7716c2d1d3cebf.png","type":"3","userid":"2173"}]
         * idcard : 210112198808899546
         * realname : 啊
         * status : 0
         * type : 2
         */

        private String address;
        private String compny_name;
        private String compny_type;
        private String credit_code;
        private String idcard;
        private String realname;
        private String status;
        private String type;
        private List<AuImgBean> au_img;
        private List<IdImgBean> id_img;
        private String phone;
        private String linkname;
        private String linkphone;
        private String email;
        private String rejection_reason;

        public String getRejection_reason() {
            return rejection_reason;
        }

        public void setRejection_reason(String rejection_reason) {
            this.rejection_reason = rejection_reason;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCompny_name() {
            return compny_name;
        }

        public void setCompny_name(String compny_name) {
            this.compny_name = compny_name;
        }

        public String getCompny_type() {
            return compny_type;
        }

        public void setCompny_type(String compny_type) {
            this.compny_type = compny_type;
        }

        public String getCredit_code() {
            return credit_code;
        }

        public void setCredit_code(String credit_code) {
            this.credit_code = credit_code;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<AuImgBean> getAu_img() {
            return au_img;
        }

        public void setAu_img(List<AuImgBean> au_img) {
            this.au_img = au_img;
        }

        public List<IdImgBean> getId_img() {
            return id_img;
        }

        public void setId_img(List<IdImgBean> id_img) {
            this.id_img = id_img;
        }

        public static class AuImgBean {
            /**
             * attachment_id : 12214
             * bak : 0
             * filepath : http://mptest.liandao.mobi/uploads/14/e0/80/27/9b/18d905949769f5da48d829.jpg
             * type : 2
             * userid : 2173
             */

            private String attachment_id;
            private String bak;
            private String filepath;
            private String type;
            private String userid;

            public String getAttachment_id() {
                return attachment_id;
            }

            public void setAttachment_id(String attachment_id) {
                this.attachment_id = attachment_id;
            }

            public String getBak() {
                return bak;
            }

            public void setBak(String bak) {
                this.bak = bak;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }

        public static class IdImgBean {
            /**
             * attachment_id : 12221
             * bak : 0
             * filepath : http://mptest.liandao.mobi/uploads/82/31/77/ec/f2/76482b5ba0c991584d5e8e.png
             * type : 3
             * userid : 2173
             */

            private String attachment_id;
            private String bak;
            private String filepath;
            private String type;
            private String userid;

            public String getAttachment_id() {
                return attachment_id;
            }

            public void setAttachment_id(String attachment_id) {
                this.attachment_id = attachment_id;
            }

            public String getBak() {
                return bak;
            }

            public void setBak(String bak) {
                this.bak = bak;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
