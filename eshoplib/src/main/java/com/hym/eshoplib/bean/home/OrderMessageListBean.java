package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/16.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OrderMessageListBean {

    /**
     * data : {"totalnum":1,"currentpage":"1","totalpage":"1","info":[{"msg_id":"2498","to_id":"1859","content":"您有新的预约订单","date":"2018-07-18 00:00:00","status":"1","content_id":"7","title":"您有新的预约订单","category_name":"PPT方案","order_number":"20180718-13421998-485752","by_num":"1","store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"}]}
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
         * info : [{"msg_id":"2498","to_id":"1859","content":"您有新的预约订单","date":"2018-07-18 00:00:00","status":"1","content_id":"7","title":"您有新的预约订单","category_name":"PPT方案","order_number":"20180718-13421998-485752","by_num":"1","store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"}]
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
             * msg_id : 2498
             * to_id : 1859
             * content : 您有新的预约订单
             * date : 2018-07-18 00:00:00
             * status : 1
             * content_id : 7
             * title : 您有新的预约订单
             * category_name : PPT方案
             * order_number : 20180718-13421998-485752
             * by_num : 1
             * store_name : 神司工作室
             * logo : http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png
             */

            private String msg_id;
            private String to_id;
            private String content;
            private String date;
            private String status;
            private String content_id;
            private String title;
            private String category_name;
            private String order_number;
            private String by_num;
            private String store_name;
            private String logo;
            private String auth;

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

            public String getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(String msg_id) {
                this.msg_id = msg_id;
            }

            public String getTo_id() {
                return to_id;
            }

            public void setTo_id(String to_id) {
                this.to_id = to_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getBy_num() {
                return by_num;
            }

            public void setBy_num(String by_num) {
                this.by_num = by_num;
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
        }
    }
}
