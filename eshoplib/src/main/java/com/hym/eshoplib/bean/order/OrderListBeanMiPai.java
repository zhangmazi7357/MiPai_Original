package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/19.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OrderListBeanMiPai {

    /**
     * data : {"totalnum":1,"currentpage":"1","totalpage":"1","info":[{"log_id":"51","order_number":"20180918-16165454-100971","buy_num":"2","money":"1600.00","ctime":"2018-09-18 16:16:54","status":"0","orderstatus":"0","cust_status":"0","price":"800.00","store_name":"神司工作室","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","category_name":"PPT方案","nickname":"网红视频工作室","avatar":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","log_userid":"1869","store_userid":"1859","dtime":"0","store_del":"0","user_del":"0","is_store":"0","button":{"yuyue_notice":1,"yuyue_confirm":0,"cancel":1,"pay":0,"shoot_notice":0,"collect_notice":0,"cust":0,"shoot_confirm":0,"collect_confirm":0,"comment":0,"delete":0,"refuse":0,"refund":0,"refrefund":0},"comment_id":""}]}
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
         * totalnum : 1
         * currentpage : 1
         * totalpage : 1
         * info : [{"log_id":"51","order_number":"20180918-16165454-100971","buy_num":"2","money":"1600.00","ctime":"2018-09-18 16:16:54","status":"0","orderstatus":"0","cust_status":"0","price":"800.00","store_name":"神司工作室","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","category_name":"PPT方案","nickname":"网红视频工作室","avatar":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","log_userid":"1869","store_userid":"1859","dtime":"0","store_del":"0","user_del":"0","is_store":"0","button":{"yuyue_notice":1,"yuyue_confirm":0,"cancel":1,"pay":0,"shoot_notice":0,"collect_notice":0,"cust":0,"shoot_confirm":0,"collect_confirm":0,"comment":0,"delete":0,"refuse":0,"refund":0,"refrefund":0},"comment_id":""}]
         */

        private int totalnum;
        private String currentpage;
        private String totalpage;
        private List<InfoBean> info;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * log_id : 51
             * order_number : 20180918-16165454-100971
             * buy_num : 2
             * money : 1600.00
             * ctime : 2018-09-18 16:16:54
             * status : 0
             * orderstatus : 0
             * cust_status : 0
             * price : 800.00
             * store_name : 神司工作室
             * logo : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
             * category_name : PPT方案
             * nickname : 网红视频工作室
             * avatar : http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg
             * log_userid : 1869
             * store_userid : 1859
             * dtime : 0
             * store_del : 0
             * user_del : 0
             * is_store : 0
             * button : {"yuyue_notice":1,"yuyue_confirm":0,"cancel":1,"pay":0,"shoot_notice":0,"collect_notice":0,"cust":0,"shoot_confirm":0,"collect_confirm":0,"comment":0,"delete":0,"refuse":0,"refund":0,"refrefund":0}
             * comment_id :
             */

            private String log_id;
            private String order_number;
            private String buy_num;
            private String money;
            private String ctime;
            private String status;
            private String orderstatus;
            private String cust_status;
            private String price;
            private String store_name;
            private String logo;
            private String category_name;
            private String nickname;
            private String avatar;
            private String log_userid;
            private String store_userid;
            private String dtime;
            private String store_del;
            private String user_del;
            private String is_store;
            private ButtonBean button;
            private String comment_id;
            private String auth_store;
            private String auth_user;

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

            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getBuy_num() {
                return buy_num;
            }

            public void setBuy_num(String buy_num) {
                this.buy_num = buy_num;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getOrderstatus() {
                return orderstatus;
            }

            public void setOrderstatus(String orderstatus) {
                this.orderstatus = orderstatus;
            }

            public String getCust_status() {
                return cust_status;
            }

            public void setCust_status(String cust_status) {
                this.cust_status = cust_status;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getLog_userid() {
                return log_userid;
            }

            public void setLog_userid(String log_userid) {
                this.log_userid = log_userid;
            }

            public String getStore_userid() {
                return store_userid;
            }

            public void setStore_userid(String store_userid) {
                this.store_userid = store_userid;
            }

            public String getDtime() {
                return dtime;
            }

            public void setDtime(String dtime) {
                this.dtime = dtime;
            }

            public String getStore_del() {
                return store_del;
            }

            public void setStore_del(String store_del) {
                this.store_del = store_del;
            }

            public String getUser_del() {
                return user_del;
            }

            public void setUser_del(String user_del) {
                this.user_del = user_del;
            }

            public String getIs_store() {
                return is_store;
            }

            public void setIs_store(String is_store) {
                this.is_store = is_store;
            }

            public ButtonBean getButton() {
                return button;
            }

            public void setButton(ButtonBean button) {
                this.button = button;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public static class ButtonBean {
                /**
                 * yuyue_notice : 1
                 * yuyue_confirm : 0
                 * cancel : 1
                 * pay : 0
                 * shoot_notice : 0
                 * collect_notice : 0
                 * cust : 0
                 * shoot_confirm : 0
                 * collect_confirm : 0
                 * comment : 0
                 * delete : 0
                 * refuse : 0
                 * refund : 0
                 * refrefund : 0
                 */

                private int yuyue_notice;
                private int yuyue_confirm;
                private int cancel;
                private int pay;
                private int shoot_notice;
                private int collect_notice;
                private int cust;
                private int shoot_confirm;
                private int collect_confirm;
                private int comment;
                private int delete;
                private int refuse;
                private int refund;
                private int refrefund;
                private int wait_comm;
                private int check_comm;
                private int accept;

                public int getAccept() {
                    return accept;
                }

                public void setAccept(int accept) {
                    this.accept = accept;
                }

                public int getCheck_comm() {
                    return check_comm;
                }

                public void setCheck_comm(int check_comm) {
                    this.check_comm = check_comm;
                }

                public int getWait_comm() {
                    return wait_comm;
                }

                public void setWait_comm(int wait_comm) {
                    this.wait_comm = wait_comm;
                }

                public int getYuyue_notice() {
                    return yuyue_notice;
                }

                public void setYuyue_notice(int yuyue_notice) {
                    this.yuyue_notice = yuyue_notice;
                }

                public int getYuyue_confirm() {
                    return yuyue_confirm;
                }

                public void setYuyue_confirm(int yuyue_confirm) {
                    this.yuyue_confirm = yuyue_confirm;
                }

                public int getCancel() {
                    return cancel;
                }

                public void setCancel(int cancel) {
                    this.cancel = cancel;
                }

                public int getPay() {
                    return pay;
                }

                public void setPay(int pay) {
                    this.pay = pay;
                }

                public int getShoot_notice() {
                    return shoot_notice;
                }

                public void setShoot_notice(int shoot_notice) {
                    this.shoot_notice = shoot_notice;
                }

                public int getCollect_notice() {
                    return collect_notice;
                }

                public void setCollect_notice(int collect_notice) {
                    this.collect_notice = collect_notice;
                }

                public int getCust() {
                    return cust;
                }

                public void setCust(int cust) {
                    this.cust = cust;
                }

                public int getShoot_confirm() {
                    return shoot_confirm;
                }

                public void setShoot_confirm(int shoot_confirm) {
                    this.shoot_confirm = shoot_confirm;
                }

                public int getCollect_confirm() {
                    return collect_confirm;
                }

                public void setCollect_confirm(int collect_confirm) {
                    this.collect_confirm = collect_confirm;
                }

                public int getComment() {
                    return comment;
                }

                public void setComment(int comment) {
                    this.comment = comment;
                }

                public int getDelete() {
                    return delete;
                }

                public void setDelete(int delete) {
                    this.delete = delete;
                }

                public int getRefuse() {
                    return refuse;
                }

                public void setRefuse(int refuse) {
                    this.refuse = refuse;
                }

                public int getRefund() {
                    return refund;
                }

                public void setRefund(int refund) {
                    this.refund = refund;
                }

                public int getRefrefund() {
                    return refrefund;
                }

                public void setRefrefund(int refrefund) {
                    this.refrefund = refrefund;
                }
            }
        }
    }
}
