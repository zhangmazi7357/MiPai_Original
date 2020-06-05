package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/19.
 * <p>
 * Description 我的收藏商品
 * <p>
 * otherTips
 */

public class MyCollectGoodsListBean {


    /**
     * data : {"currentpage":"1","info":[{"content":"1","ctime":"2018-04-19 20:19:33","favorite_id":"382","image_default":"http://jzshop.liandao.mobi/uploads/c3/ea/30/c1/d1/a4896a027f7c28aee6d791.jpg","name":"Apple 苹果 iPhone X 全面屏4G手机 深空灰色 64GB","price":"7638.00","price_market":"7638.00","type":"goods","userid":"891"}],"totalnum":"1","totalpage":"1"}
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
         * currentpage : 1
         * info : [{"content":"1","ctime":"2018-04-19 20:19:33","favorite_id":"382","image_default":"http://jzshop.liandao.mobi/uploads/c3/ea/30/c1/d1/a4896a027f7c28aee6d791.jpg","name":"Apple 苹果 iPhone X 全面屏4G手机 深空灰色 64GB","price":"7638.00","price_market":"7638.00","type":"goods","userid":"891"}]
         * totalnum : 1
         * totalpage : 1
         */

        private String currentpage;
        private String totalnum;
        private String totalpage;
        private List<InfoBean> info;

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
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
             * content : 1
             * ctime : 2018-04-19 20:19:33
             * favorite_id : 382
             * image_default : http://jzshop.liandao.mobi/uploads/c3/ea/30/c1/d1/a4896a027f7c28aee6d791.jpg
             * name : Apple 苹果 iPhone X 全面屏4G手机 深空灰色 64GB
             * price : 7638.00
             * price_market : 7638.00
             * type : goods
             * userid : 891
             */

            private String content;
            private String ctime;
            private String favorite_id;
            private String image_default;
            private String name;
            private String price;
            private String price_market;
            private String type;
            private String userid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getFavorite_id() {
                return favorite_id;
            }

            public void setFavorite_id(String favorite_id) {
                this.favorite_id = favorite_id;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPrice_market() {
                return price_market;
            }

            public void setPrice_market(String price_market) {
                this.price_market = price_market;
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
