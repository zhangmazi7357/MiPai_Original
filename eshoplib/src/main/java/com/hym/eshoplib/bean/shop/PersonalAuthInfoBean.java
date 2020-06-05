package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/12/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class PersonalAuthInfoBean {

    /**
     * data : {"address":"沈阳","au_img":[],"email":"996647@qq.com","id_img":[{"attachment_id":"12213","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/d4/67/5f/c7/54/10d3fa254cf7ffd35b3a11.jpg","type":"1","userid":"2173"},{"attachment_id":"12214","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/14/e0/80/27/9b/18d905949769f5da48d829.jpg","type":"1","userid":"2173"},{"attachment_id":"11969","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/a5/0b/ed/13/a9/9e9cfa54e5f3e1e5dd6c62.jpg","type":"1","userid":"2173"}],"idcard":"210112198586869898","linkphone":"18888888888","phone":"18888888888","status":"0","type":"1"}
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
         * address : 沈阳
         * au_img : []
         * email : 996647@qq.com
         * id_img : [{"attachment_id":"12213","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/d4/67/5f/c7/54/10d3fa254cf7ffd35b3a11.jpg","type":"1","userid":"2173"},{"attachment_id":"12214","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/14/e0/80/27/9b/18d905949769f5da48d829.jpg","type":"1","userid":"2173"},{"attachment_id":"11969","bak":"0","filepath":"http://mptest.liandao.mobi/uploads/a5/0b/ed/13/a9/9e9cfa54e5f3e1e5dd6c62.jpg","type":"1","userid":"2173"}]
         * idcard : 210112198586869898
         * linkphone : 18888888888
         * phone : 18888888888
         * status : 0
         * type : 1
         */

        private String address;
        private String email;
        private String idcard;
        private String linkphone;
        private String linkname;
        private String phone;
        private String status;
        private String type;
        private List<?> au_img;
        private List<IdImgBean> id_img;
        private String realname;
        private String rejection_reason;

        public String getRejection_reason() {
            return rejection_reason;
        }

        public void setRejection_reason(String rejection_reason) {
            this.rejection_reason = rejection_reason;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public List<?> getAu_img() {
            return au_img;
        }

        public void setAu_img(List<?> au_img) {
            this.au_img = au_img;
        }

        public List<IdImgBean> getId_img() {
            return id_img;
        }

        public void setId_img(List<IdImgBean> id_img) {
            this.id_img = id_img;
        }

        public static class IdImgBean {
            /**
             * attachment_id : 12213
             * bak : 0
             * filepath : http://mptest.liandao.mobi/uploads/d4/67/5f/c7/54/10d3fa254cf7ffd35b3a11.jpg
             * type : 1
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
