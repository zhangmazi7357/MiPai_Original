package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OrderDetailBeanMipai {

    /**
     * data : {"button":{"cancel":1,"check_comm":0,"collect_confirm":0,"collect_notice":0,"comment":0,"cust":0,"delete":0,"pay":0,"refrefund":0,"refund":0,"refuse":0,"shoot_confirm":0,"shoot_notice":0,"wait_comm":0,"yuyue_confirm":0,"yuyue_notice":1},"buy_num":"1","category_name":"PPT方案","comment_id":"","ctime":"2018-09-20 14:16:40","cust_status":"0","dtime":"0","is_notice":1,"log_id":"56","log_userid":"1869","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","money":"800.00","order_number":"20180920-14164056-571025","orderstatus":"0","price":"800.00","status":"0","store_del":"0","store_name":"神司工作室","store_userid":"1859","user_del":"0"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getRemaining_time() {
            return remaining_time;
        }

        public void setRemaining_time(String remaining_time) {
            this.remaining_time = remaining_time;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        /**
         * button : {"cancel":1,"check_comm":0,"collect_confirm":0,"collect_notice":0,"comment":0,"cust":0,"delete":0,"pay":0,"refrefund":0,"refund":0,"refuse":0,"shoot_confirm":0,"shoot_notice":0,"wait_comm":0,"yuyue_confirm":0,"yuyue_notice":1}
         * buy_num : 1
         * category_name : PPT方案
         * comment_id :
         * ctime : 2018-09-20 14:16:40
         * cust_status : 0
         * dtime : 0
         * is_notice : 1
         * log_id : 56
         * log_userid : 1869
         * logo : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
         * money : 800.00
         * order_number : 20180920-14164056-571025
         * orderstatus : 0
         * price : 800.00
         * status : 0
         * store_del : 0
         * store_name : 神司工作室
         * store_userid : 1859
         * user_del : 0
         */

        private String service_id;
        private String remaining_time;
        private String category_id;
        private ButtonBean button;
        private String buy_num;
        private String category_name;
        private String comment_id;
        private String ctime;
        private String cust_status;
        private String dtime;
        private int is_notice;
        private String log_id;
        private String log_userid;
        private String logo;
        private String money;
        private String order_number;
        private String orderstatus;
        private String price;
        private String status;
        private String store_del;
        private String store_name;
        private String store_userid;
        private String user_del;
        private String auth;
        private String store_id;
        private String side_id;
        private String side_nick;
        private String tel;
        private String cust_money;
        private String user_reason;
        private String refund_type;
        private String invoice;
        private String side_avatar;
        private String auth_store;
        private String auth_user;
        private String product_name;

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getAuth_store() {
            return auth_store;
        }

        public void setAuth_store(String auth_store) {
            this.auth_store = auth_store;
        }

        public String getAuth_user() {
            return auth_user;
        }

        public void setAuth_user(String auth_user) {
            this.auth_user = auth_user;
        }

        public String getSide_avatar() {
            return side_avatar;
        }

        public void setSide_avatar(String side_avatar) {
            this.side_avatar = side_avatar;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getUser_reason() {
            return user_reason;
        }

        public void setUser_reason(String user_reason) {
            this.user_reason = user_reason;
        }

        public String getCust_money() {
            return cust_money;
        }

        public void setCust_money(String cust_money) {
            this.cust_money = cust_money;
        }

        public String getSide_nick() {
            return side_nick;
        }

        public void setSide_nick(String side_nick) {
            this.side_nick = side_nick;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSide_id() {
            return side_id;
        }

        public void setSide_id(String side_id) {
            this.side_id = side_id;
        }

        private String reason;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public ButtonBean getButton() {
            return button;
        }

        public void setButton(ButtonBean button) {
            this.button = button;
        }

        public String getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(String buy_num) {
            this.buy_num = buy_num;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getCust_status() {
            return cust_status;
        }

        public void setCust_status(String cust_status) {
            this.cust_status = cust_status;
        }

        public String getDtime() {
            return dtime;
        }

        public void setDtime(String dtime) {
            this.dtime = dtime;
        }

        public int getIs_notice() {
            return is_notice;
        }

        public void setIs_notice(int is_notice) {
            this.is_notice = is_notice;
        }

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getLog_userid() {
            return log_userid;
        }

        public void setLog_userid(String log_userid) {
            this.log_userid = log_userid;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStore_del() {
            return store_del;
        }

        public void setStore_del(String store_del) {
            this.store_del = store_del;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_userid() {
            return store_userid;
        }

        public void setStore_userid(String store_userid) {
            this.store_userid = store_userid;
        }

        public String getUser_del() {
            return user_del;
        }

        public void setUser_del(String user_del) {
            this.user_del = user_del;
        }

        public static class ButtonBean {
            /**
             * cancel : 1
             * check_comm : 0
             * collect_confirm : 0
             * collect_notice : 0
             * comment : 0
             * cust : 0
             * delete : 0
             * pay : 0
             * refrefund : 0
             * refund : 0
             * refuse : 0
             * shoot_confirm : 0
             * shoot_notice : 0
             * wait_comm : 0
             * yuyue_confirm : 0
             * yuyue_notice : 1
             */

            private int cancel;
            private int check_comm;
            private int collect_confirm;
            private int collect_notice;
            private int comment;
            private int cust;
            private int delete;
            private int pay;
            private int refrefund;
            private int refund;
            private int refuse;
            private int shoot_confirm;
            private int shoot_notice;
            private int wait_comm;
            private int yuyue_confirm;
            private int yuyue_notice;
            private int accept;

            public int getAccept() {
                return accept;
            }

            public void setAccept(int accept) {
                this.accept = accept;
            }

            public int getCancel() {
                return cancel;
            }

            public void setCancel(int cancel) {
                this.cancel = cancel;
            }

            public int getCheck_comm() {
                return check_comm;
            }

            public void setCheck_comm(int check_comm) {
                this.check_comm = check_comm;
            }

            public int getCollect_confirm() {
                return collect_confirm;
            }

            public void setCollect_confirm(int collect_confirm) {
                this.collect_confirm = collect_confirm;
            }

            public int getCollect_notice() {
                return collect_notice;
            }

            public void setCollect_notice(int collect_notice) {
                this.collect_notice = collect_notice;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public int getCust() {
                return cust;
            }

            public void setCust(int cust) {
                this.cust = cust;
            }

            public int getDelete() {
                return delete;
            }

            public void setDelete(int delete) {
                this.delete = delete;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public int getRefrefund() {
                return refrefund;
            }

            public void setRefrefund(int refrefund) {
                this.refrefund = refrefund;
            }

            public int getRefund() {
                return refund;
            }

            public void setRefund(int refund) {
                this.refund = refund;
            }

            public int getRefuse() {
                return refuse;
            }

            public void setRefuse(int refuse) {
                this.refuse = refuse;
            }

            public int getShoot_confirm() {
                return shoot_confirm;
            }

            public void setShoot_confirm(int shoot_confirm) {
                this.shoot_confirm = shoot_confirm;
            }

            public int getShoot_notice() {
                return shoot_notice;
            }

            public void setShoot_notice(int shoot_notice) {
                this.shoot_notice = shoot_notice;
            }

            public int getWait_comm() {
                return wait_comm;
            }

            public void setWait_comm(int wait_comm) {
                this.wait_comm = wait_comm;
            }

            public int getYuyue_confirm() {
                return yuyue_confirm;
            }

            public void setYuyue_confirm(int yuyue_confirm) {
                this.yuyue_confirm = yuyue_confirm;
            }

            public int getYuyue_notice() {
                return yuyue_notice;
            }

            public void setYuyue_notice(int yuyue_notice) {
                this.yuyue_notice = yuyue_notice;
            }
        }
    }
}
