package com.hym.eshoplib.fragment.search.mz.model;

import java.util.List;

/*
   工作室搜索 ;
 */
public class MzSearchShopModel {


    /**
     * data : {"totalnum":"3","currentpage":"1","totalpage":"1","info":[{"store_id":"1007","store_name":"韩熹","rank_average":"1","praise_rate":"0","logo":"/2c/77/bb/bf/17/58db73c1d07cd38dd79a69.jpeg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0},{"store_id":"551","store_name":"韩铭恩影视工作室","rank_average":"1","praise_rate":"0","logo":"/d3/d5/ad/40/42/e1eae5985c1837273c4c43.jpg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0},{"store_id":"446","store_name":"韩璐","rank_average":"1","praise_rate":"0","logo":"/58/41/7e/ee/dc/dd07eb20fed88001cfc18c.jpeg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0}]}
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
         * totalnum : 3
         * currentpage : 1
         * totalpage : 1
         * info : [{"store_id":"1007","store_name":"韩熹","rank_average":"1","praise_rate":"0","logo":"/2c/77/bb/bf/17/58db73c1d07cd38dd79a69.jpeg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0},{"store_id":"551","store_name":"韩铭恩影视工作室","rank_average":"1","praise_rate":"0","logo":"/d3/d5/ad/40/42/e1eae5985c1837273c4c43.jpg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0},{"store_id":"446","store_name":"韩璐","rank_average":"1","praise_rate":"0","logo":"/58/41/7e/ee/dc/dd07eb20fed88001cfc18c.jpeg","description":null,"ctime":null,"content_id":null,"title":null,"price":null,"max_price":"0.00","order_count":0}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
        private List<InfoBean> info;

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
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
             * store_id : 1007
             * store_name : 韩熹
             * rank_average : 1
             * praise_rate : 0
             * logo : /2c/77/bb/bf/17/58db73c1d07cd38dd79a69.jpeg
             * description : null
             * ctime : null
             * content_id : null
             * title : null
             * price : null
             * max_price : 0.00
             * order_count : 0
             */

            private String store_id;
            private String store_name;
            private String rank_average;
            private String praise_rate;
            private String logo;
            private String description;
            private String ctime;
            private String content_id;
            private String title;
            private String price;
            private String max_price;
            private int order_count;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMax_price() {
                return max_price;
            }

            public void setMax_price(String max_price) {
                this.max_price = max_price;
            }

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
            }
        }
    }
}
