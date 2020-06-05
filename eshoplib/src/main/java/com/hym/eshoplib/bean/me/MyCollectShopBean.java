package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class MyCollectShopBean {

    /**
     * data : {"totalnum":"2","currentpage":"1","totalpage":"1","info":[{"favorite_id":"821","type":"store","content":"259","userid":"1869","specification_id":null,"description":null,"ctime":null,"content_id":"19","title":null,"price":"800.00","rank_average":"5","praise_rate":"100","max_price":"0.00","store_name":"神司工作室","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg"},{"favorite_id":"820","type":"store","content":"265","userid":"1869","specification_id":null,"description":null,"ctime":null,"content_id":"35","title":null,"price":"200.00","rank_average":"0","praise_rate":"0","max_price":"0.00","store_name":"嘻嘻童年","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg"}]}
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
         * info : [{"favorite_id":"821","type":"store","content":"259","userid":"1869","specification_id":null,"description":null,"ctime":null,"content_id":"19","title":null,"price":"800.00","rank_average":"5","praise_rate":"100","max_price":"0.00","store_name":"神司工作室","logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg"},{"favorite_id":"820","type":"store","content":"265","userid":"1869","specification_id":null,"description":null,"ctime":null,"content_id":"35","title":null,"price":"200.00","rank_average":"0","praise_rate":"0","max_price":"0.00","store_name":"嘻嘻童年","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg"}]
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
             * favorite_id : 821
             * type : store
             * content : 259
             * userid : 1869
             * specification_id : null
             * description : null
             * ctime : null
             * content_id : 19
             * title : null
             * price : 800.00
             * rank_average : 5
             * praise_rate : 100
             * max_price : 0.00
             * store_name : 神司工作室
             * logo : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
             */

            private String favorite_id;
            private String type;
            private String content;
            private String userid;
            private Object specification_id;
            private Object description;
            private Object ctime;
            private String content_id;
            private Object title;
            private String price;
            private String rank_average;
            private String praise_rate;
            private String max_price;
            private String store_name;
            private String logo;
            private String comment;
            private String remark;
            private String store_id;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getFavorite_id() {
                return favorite_id;
            }

            public void setFavorite_id(String favorite_id) {
                this.favorite_id = favorite_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public Object getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(Object specification_id) {
                this.specification_id = specification_id;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getCtime() {
                return ctime;
            }

            public void setCtime(Object ctime) {
                this.ctime = ctime;
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

            public String getMax_price() {
                return max_price;
            }

            public void setMax_price(String max_price) {
                this.max_price = max_price;
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
