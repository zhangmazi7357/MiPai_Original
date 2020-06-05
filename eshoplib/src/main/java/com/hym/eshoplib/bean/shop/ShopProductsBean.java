package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/11.
 * <p>
 * Description 产品列表
 * <p>
 * otherTips
 */

public class ShopProductsBean {

    /**
     * data : {"totalnum":"1","currentpage":1,"totalpage":"1","info":[{"case_id":"12","type":"1","store_id":"266","title":"哈哈","views":"0","length":"00:09","filepath":"http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4","image_default":"http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","store_name":"网红视频工作室","agree":"2","is_agree":"1","content_id":"38"}]}
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
         * info : [{"case_id":"12","type":"1","store_id":"266","title":"哈哈","views":"0","length":"00:09","filepath":"http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4","image_default":"http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","store_name":"网红视频工作室","agree":"2","is_agree":"1","content_id":"38"}]
         */

        private String totalnum;
        private int currentpage;
        private String totalpage;
        private List<InfoBean> info;
        private String qiniu_token;
        private String store_id;

        public String getQiniu_token() {
            return qiniu_token;
        }

        public void setQiniu_token(String qiniu_token) {
            this.qiniu_token = qiniu_token;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public int getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(int currentpage) {
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
            public String getIs_verify() {
                return is_verify;
            }

            public void setIs_verify(String is_verify) {
                this.is_verify = is_verify;
            }

            /**
             * case_id : 12
             * type : 1
             * store_id : 266
             * title : 哈哈
             * views : 0
             * length : 00:09
             * filepath : http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4
             * image_default : http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg
             * logo : http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg
             * store_name : 网红视频工作室
             * agree : 2
             * is_agree : 1
             * content_id : 38
             */

            private String is_verify;
            private String case_id;
            private String type;
            private String store_id;
            private String title;
            private String views;
            private String length;
            private String filepath;
            private String image_default;
            private String logo;
            private String store_name;
            private String agree;
            private String is_agree;
            private String content_id;
            private String store_rank;
            private String auth;
            private String original_price;
            private String present_price;
            private String weight;

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
            }

            public String getPresent_price() {
                return present_price;
            }

            public void setPresent_price(String present_price) {
                this.present_price = present_price;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

            public String getStore_rank() {
                return store_rank;
            }

            public void setStore_rank(String store_rank) {
                this.store_rank = store_rank;
            }

            public String getCase_id() {
                return case_id;
            }

            public void setCase_id(String case_id) {
                this.case_id = case_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getAgree() {
                return agree;
            }

            public void setAgree(String agree) {
                this.agree = agree;
            }

            public String getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(String is_agree) {
                this.is_agree = is_agree;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }
        }
    }
}
