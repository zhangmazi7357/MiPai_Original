package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ShopListBean {

    /**
     * data : {"totalnum":2,"currentpage":"1","totalpage":"1","info":[{"store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_id":"259","content_id":"19","title":null,"price":"800","rank_average":"0","praise_rate":"0","remark":"制作者信息","comment":"0"},{"store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_id":"259","content_id":"20","title":null,"price":"900","rank_average":"0","praise_rate":"0","remark":"制作者信息","comment":"0"}]}
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
         * totalnum : 2
         * currentpage : 1
         * totalpage : 1
         * info : [{"store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_id":"259","content_id":"19","title":null,"price":"800","rank_average":"0","praise_rate":"0","remark":"制作者信息","comment":"0"},{"store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_id":"259","content_id":"20","title":null,"price":"900","rank_average":"0","praise_rate":"0","remark":"制作者信息","comment":"0"}]
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
             * store_name : 神司工作室
             * logo : http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png
             * store_id : 259
             * content_id : 19
             * title : null
             * price : 800
             * rank_average : 0
             * praise_rate : 0
             * remark : 制作者信息
             * comment : 0
             */

            private String store_name;
            private String logo;
            private String store_id;
            private String content_id;
            private Object title;
            private String price;
            private String rank_average;
            private String praise_rate;
            private String remark;
            private String comment;
            private String auth;
            private String order_count;

            public String getOrder_count() {
                return order_count;
            }

            public void setOrder_count(String order_count) {
                this.order_count = order_count;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
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

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRank_average() {
                return rank_average;
            }

            public void setRank_average(String rank_average) {
                this.rank_average = rank_average;
            }

            public String getPraise_rate() {
                return praise_rate;
            }

            public void setPraise_rate(String praise_rate) {
                this.praise_rate = praise_rate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }
}
