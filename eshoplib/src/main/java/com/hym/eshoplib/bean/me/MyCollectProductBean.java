package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class MyCollectProductBean {


    /**
     * data : {"currentpage":"1","info":[{"case_id":"6","content":"6","ctime":"2018-09-21 10:26:24","favorite_id":"826","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/76/b7/33/18/e2/f37632e9e9ed9bc9413184.jpg","is_agree":0,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"新品上市","type":"case","userid":"1869","views":"0"},{"case_id":"7","content":"7","ctime":"2018-09-21 10:26:17","favorite_id":"825","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/18/0e/29/ec/78/38168069a68a29695afdc4.jpg","is_agree":1,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"北京车展","type":"case","userid":"1869","views":"0"},{"case_id":"9","content":"9","ctime":"2018-09-21 10:26:15","favorite_id":"824","filepath":"1536917288_temp_uuid_95aedecca38b8da4ba306ca837886cec.mp4","image_default":"http://mpai.liandao.mobi/uploads/75/5e/c5/3c/cb/1a9e341f76710ca88a0a03.jpg","is_agree":0,"length":"00:59","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","store_id":"266","store_name":"网红视频工作室","title":"测试视频","type":"case","userid":"1869","views":"0"},{"case_id":"8","content":"8","ctime":"2018-09-21 10:26:13","favorite_id":"823","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/c0/39/fb/28/38/16fd84cebdb4414f148bd6.jpg","is_agree":0,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"移民","type":"case","userid":"1869","views":"0"}],"totalnum":"4","totalpage":"1"}
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
         * info : [{"case_id":"6","content":"6","ctime":"2018-09-21 10:26:24","favorite_id":"826","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/76/b7/33/18/e2/f37632e9e9ed9bc9413184.jpg","is_agree":0,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"新品上市","type":"case","userid":"1869","views":"0"},{"case_id":"7","content":"7","ctime":"2018-09-21 10:26:17","favorite_id":"825","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/18/0e/29/ec/78/38168069a68a29695afdc4.jpg","is_agree":1,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"北京车展","type":"case","userid":"1869","views":"0"},{"case_id":"9","content":"9","ctime":"2018-09-21 10:26:15","favorite_id":"824","filepath":"1536917288_temp_uuid_95aedecca38b8da4ba306ca837886cec.mp4","image_default":"http://mpai.liandao.mobi/uploads/75/5e/c5/3c/cb/1a9e341f76710ca88a0a03.jpg","is_agree":0,"length":"00:59","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","store_id":"266","store_name":"网红视频工作室","title":"测试视频","type":"case","userid":"1869","views":"0"},{"case_id":"8","content":"8","ctime":"2018-09-21 10:26:13","favorite_id":"823","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/c0/39/fb/28/38/16fd84cebdb4414f148bd6.jpg","is_agree":0,"length":"","logo":"http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg","store_id":"265","store_name":"嘻嘻童年","title":"移民","type":"case","userid":"1869","views":"0"}]
         * totalnum : 4
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
             * case_id : 6
             * content : 6
             * ctime : 2018-09-21 10:26:24
             * favorite_id : 826
             * filepath :
             * image_default : http://mpai.liandao.mobi/uploads/76/b7/33/18/e2/f37632e9e9ed9bc9413184.jpg
             * is_agree : 0
             * length :
             * logo : http://mpai.liandao.mobi/uploads/a9/0e/bf/60/80/4cd0e0edde5a8e2ed8fc4c.jpg
             * store_id : 265
             * store_name : 嘻嘻童年
             * title : 新品上市
             * type : case
             * userid : 1869
             * views : 0
             */

            private String case_id;
            private String content;
            private String ctime;
            private String favorite_id;
            private String filepath;
            private String image_default;
            private int is_agree;
            private String length;
            private String logo;
            private String store_id;
            private String store_name;
            private String title;
            private String type;
            private String userid;
            private String views;
            private String store_rank;
            private String agree;

            public String getAgree() {
                return agree;
            }

            public void setAgree(String agree) {
                this.agree = agree;
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

            public int getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(int is_agree) {
                this.is_agree = is_agree;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }
        }
    }
}
