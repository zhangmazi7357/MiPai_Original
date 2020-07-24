package com.hym.eshoplib.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class SpecialTimeLimteBean implements Serializable {


    /**
     * data : {"currentpage":"1","totalnum":"13","totalpage":2,"video":[{"agree":"0","auth":1,"case_id":"2538","content_id":"2335","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lq1LNnb1U549DFwTyiFxgiHH3Jru","image_default":"http://mpai.liandao.mobi/uploads/b6/02/ec/14/0c/abbdfe7640256d40c2e4fa.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"04:09","logo":"http://mpai.liandao.mobi/uploads/de/74/e9/27/c1/df16ea69f29a807661bed4.jpeg","original_price":"80.50","present_price":"50.50","rank_average":"1.5","remarks":"","shooting_time":"1569722714","staffing":"XXXXXXXX","store_id":"1396","store_name":"Leo的工作室","title":"我的小爸爸 温馨剧情短片","userid":"4671","videotype":"1,2","views":"1329","weight":"0"},{"agree":"0","auth":1,"case_id":"2378","content_id":"2099","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lhnA-TQsKtE9xTFBzLQpsLIFgSC7","image_default":"http://mpai.liandao.mobi/uploads/bf/3e/89/79/da/b9c0f00f9d0f81fa43060d.JPEG","introduce":"NNNNNNN","is_agree":"0","length":"01:15","logo":"http://mpai.liandao.mobi/uploads/c4/83/65/de/a6/c3e480f8afd4d553d1fc17.jpg","original_price":"200.00","present_price":"42.00","rank_average":"2","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"XXXXXXXX","store_id":"1284","store_name":"青春工作室","title":"广告","userid":"4438","videotype":"1","views":"731","weight":"1"},{"agree":"1","auth":1,"case_id":"2332","content_id":"2108","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lgC-QDjfWqb-2cqweF-tkZaoAStS","image_default":"http://mpai.liandao.mobi/uploads/a4/8d/eb/4b/18/b5afc8af0d98f80d08dae4.jpg","introduce":"NNNNNNN","is_agree":"0","length":"04:18","logo":"http://mpai.liandao.mobi/uploads/73/29/62/95/ac/b6eb3afb5b800a30249eaa.png","original_price":"46.50","present_price":"11.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1565231646","staffing":"XXXXXXXX","store_id":"1287","store_name":"刘年德工作室","title":"中国电信 宣传片","userid":"4443","videotype":"1,3","views":"674","weight":"0"},{"agree":"2","auth":1,"case_id":"2203","content_id":"1782","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/loU4xqN8oo_4DihlDmH0N_dz6NEJ","image_default":"http://mpai.liandao.mobi/uploads/51/23/9b/ea/60/575b8b1ec5db571b3f6bb7.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"01:45","logo":"http://mpai.liandao.mobi/uploads/e0/e9/f0/fa/50/fdf0dc98b980ec9f3598b4.jpeg","original_price":"77.00","present_price":"63.00","rank_average":"3","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1569722714","staffing":"XXXXXXXX","store_id":"1155","store_name":"群艺影像工作室","title":"G20《喜欢你，在一起》","userid":"4143","videotype":"1","views":"267","weight":"1"},{"agree":"2","auth":1,"case_id":"2207","content_id":"1782","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lnXCSTsNTgqlME4jxkucLSTeHnMc","image_default":"http://mpai.liandao.mobi/uploads/f3/20/4d/52/70/3a100f500cd49d3473eb4b.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"01:30","logo":"http://mpai.liandao.mobi/uploads/e0/e9/f0/fa/50/fdf0dc98b980ec9f3598b4.jpeg","original_price":"88.20","present_price":"79.00","rank_average":"3","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"XXXXXXXX","store_id":"1155","store_name":"群艺影像工作室","title":"你好2017公益广告","userid":"4143","videotype":"1,3","views":"199","weight":"1"},{"agree":"0","auth":1,"case_id":"2425","content_id":"2219","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/llX7NOTm1hwr07MCfVe3larAsm51","image_default":"http://mpai.liandao.mobi/uploads/13/6b/8f/ee/19/4052b1632f8d8e25ddefbc.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"14:01","logo":"http://mpai.liandao.mobi/uploads/e2/62/69/c3/4b/c662743ee8c9226be39859.jpeg","original_price":"50.50","present_price":"25.50","rank_average":"2.5","remarks":"","shooting_time":"1566550958","staffing":"XXXXXXXX","store_id":"1332","store_name":"金雷影视","title":"微电影《橘子红了》","userid":"4528","videotype":"1,2","views":"100","weight":"0"},{"agree":"1","auth":1,"case_id":"2392","content_id":"2195","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/llOwNNd49aB2IcraBhyzwsgvIwJ1","image_default":"http://mpai.liandao.mobi/uploads/ab/86/18/52/50/69d40b2f7e7b4ef9324fa7.jpg","introduce":"NNNNNNN","is_agree":"0","length":"02:55","logo":"http://mpai.liandao.mobi/uploads/e5/b3/ae/be/d0/0c6fbf499c0688c9d36a71.jpg","original_price":"100.00","present_price":"38.00","rank_average":"2.5","remarks":"","shooting_time":"1565231646","staffing":"XXXXXXXX","store_id":"1321","store_name":"怪物印象","title":"巴比伦艺术花园品牌广告","userid":"4507","videotype":"1","views":"114","weight":"1"},{"agree":"5","auth":1,"case_id":"1825","content_id":"208","details":"","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/li26KErKQcTJSLtx9hzBHKpwv1sv","image_default":"http://mpai.liandao.mobi/uploads/55/2d/0e/01/2b/b21f9e1979b891586c73a3.jpg","introduce":"","is_agree":"0","length":"06:32","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"46.50","present_price":"42.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"","staffing":"","store_id":"384","store_name":"片库","title":"韩国搞笑短片\u2014\u2014《僵尸女孩》","userid":"2041","videotype":"1,2,4","views":"240","weight":"3"},{"agree":"2","auth":1,"case_id":"1876","content_id":"208","details":"","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lmCuUbRI1Z6D84dZLUBEJcrhuy98","image_default":"http://mpai.liandao.mobi/uploads/6d/64/e7/c6/a9/26f44411e68f4ce9e3b357.png","introduce":"","is_agree":"0","length":"03:58","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"45.00","present_price":"38.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"","store_id":"384","store_name":"片库","title":"Dream Crazy","userid":"2041","videotype":"1,2,4","views":"53","weight":"3"},{"agree":"2","auth":1,"case_id":"1990","content_id":"208","details":"YYYYY","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lo8Q48Mz0NtJtn5ePYV93TRLt0r9","image_default":"http://mpai.liandao.mobi/uploads/bc/0e/a7/b1/fd/8d5f1786ad627c18c5083d.jpg","introduce":"","is_agree":"0","length":"02:04","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"200.00","present_price":"50.50","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1566550958","staffing":"","store_id":"384","store_name":"片库","title":"泰国广告终于对乔布斯和马云下手了","userid":"2041","videotype":"1,2,4","views":"98","weight":"3"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity, Serializable {
        /**
         * currentpage : 1
         * totalnum : 13
         * totalpage : 2
         * video : [{"agree":"0","auth":1,"case_id":"2538","content_id":"2335","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lq1LNnb1U549DFwTyiFxgiHH3Jru","image_default":"http://mpai.liandao.mobi/uploads/b6/02/ec/14/0c/abbdfe7640256d40c2e4fa.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"04:09","logo":"http://mpai.liandao.mobi/uploads/de/74/e9/27/c1/df16ea69f29a807661bed4.jpeg","original_price":"80.50","present_price":"50.50","rank_average":"1.5","remarks":"","shooting_time":"1569722714","staffing":"XXXXXXXX","store_id":"1396","store_name":"Leo的工作室","title":"我的小爸爸 温馨剧情短片","userid":"4671","videotype":"1,2","views":"1329","weight":"0"},{"agree":"0","auth":1,"case_id":"2378","content_id":"2099","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lhnA-TQsKtE9xTFBzLQpsLIFgSC7","image_default":"http://mpai.liandao.mobi/uploads/bf/3e/89/79/da/b9c0f00f9d0f81fa43060d.JPEG","introduce":"NNNNNNN","is_agree":"0","length":"01:15","logo":"http://mpai.liandao.mobi/uploads/c4/83/65/de/a6/c3e480f8afd4d553d1fc17.jpg","original_price":"200.00","present_price":"42.00","rank_average":"2","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"XXXXXXXX","store_id":"1284","store_name":"青春工作室","title":"广告","userid":"4438","videotype":"1","views":"731","weight":"1"},{"agree":"1","auth":1,"case_id":"2332","content_id":"2108","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lgC-QDjfWqb-2cqweF-tkZaoAStS","image_default":"http://mpai.liandao.mobi/uploads/a4/8d/eb/4b/18/b5afc8af0d98f80d08dae4.jpg","introduce":"NNNNNNN","is_agree":"0","length":"04:18","logo":"http://mpai.liandao.mobi/uploads/73/29/62/95/ac/b6eb3afb5b800a30249eaa.png","original_price":"46.50","present_price":"11.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1565231646","staffing":"XXXXXXXX","store_id":"1287","store_name":"刘年德工作室","title":"中国电信 宣传片","userid":"4443","videotype":"1,3","views":"674","weight":"0"},{"agree":"2","auth":1,"case_id":"2203","content_id":"1782","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/loU4xqN8oo_4DihlDmH0N_dz6NEJ","image_default":"http://mpai.liandao.mobi/uploads/51/23/9b/ea/60/575b8b1ec5db571b3f6bb7.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"01:45","logo":"http://mpai.liandao.mobi/uploads/e0/e9/f0/fa/50/fdf0dc98b980ec9f3598b4.jpeg","original_price":"77.00","present_price":"63.00","rank_average":"3","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1569722714","staffing":"XXXXXXXX","store_id":"1155","store_name":"群艺影像工作室","title":"G20《喜欢你，在一起》","userid":"4143","videotype":"1","views":"267","weight":"1"},{"agree":"2","auth":1,"case_id":"2207","content_id":"1782","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lnXCSTsNTgqlME4jxkucLSTeHnMc","image_default":"http://mpai.liandao.mobi/uploads/f3/20/4d/52/70/3a100f500cd49d3473eb4b.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"01:30","logo":"http://mpai.liandao.mobi/uploads/e0/e9/f0/fa/50/fdf0dc98b980ec9f3598b4.jpeg","original_price":"88.20","present_price":"79.00","rank_average":"3","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"XXXXXXXX","store_id":"1155","store_name":"群艺影像工作室","title":"你好2017公益广告","userid":"4143","videotype":"1,3","views":"199","weight":"1"},{"agree":"0","auth":1,"case_id":"2425","content_id":"2219","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/llX7NOTm1hwr07MCfVe3larAsm51","image_default":"http://mpai.liandao.mobi/uploads/13/6b/8f/ee/19/4052b1632f8d8e25ddefbc.jpeg","introduce":"NNNNNNN","is_agree":"0","length":"14:01","logo":"http://mpai.liandao.mobi/uploads/e2/62/69/c3/4b/c662743ee8c9226be39859.jpeg","original_price":"50.50","present_price":"25.50","rank_average":"2.5","remarks":"","shooting_time":"1566550958","staffing":"XXXXXXXX","store_id":"1332","store_name":"金雷影视","title":"微电影《橘子红了》","userid":"4528","videotype":"1,2","views":"100","weight":"0"},{"agree":"1","auth":1,"case_id":"2392","content_id":"2195","details":"YYYYY","equipment":"MMMMMMMMMMM","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/llOwNNd49aB2IcraBhyzwsgvIwJ1","image_default":"http://mpai.liandao.mobi/uploads/ab/86/18/52/50/69d40b2f7e7b4ef9324fa7.jpg","introduce":"NNNNNNN","is_agree":"0","length":"02:55","logo":"http://mpai.liandao.mobi/uploads/e5/b3/ae/be/d0/0c6fbf499c0688c9d36a71.jpg","original_price":"100.00","present_price":"38.00","rank_average":"2.5","remarks":"","shooting_time":"1565231646","staffing":"XXXXXXXX","store_id":"1321","store_name":"怪物印象","title":"巴比伦艺术花园品牌广告","userid":"4507","videotype":"1","views":"114","weight":"1"},{"agree":"5","auth":1,"case_id":"1825","content_id":"208","details":"","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/li26KErKQcTJSLtx9hzBHKpwv1sv","image_default":"http://mpai.liandao.mobi/uploads/55/2d/0e/01/2b/b21f9e1979b891586c73a3.jpg","introduce":"","is_agree":"0","length":"06:32","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"46.50","present_price":"42.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"","staffing":"","store_id":"384","store_name":"片库","title":"韩国搞笑短片\u2014\u2014《僵尸女孩》","userid":"2041","videotype":"1,2,4","views":"240","weight":"3"},{"agree":"2","auth":1,"case_id":"1876","content_id":"208","details":"","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lmCuUbRI1Z6D84dZLUBEJcrhuy98","image_default":"http://mpai.liandao.mobi/uploads/6d/64/e7/c6/a9/26f44411e68f4ce9e3b357.png","introduce":"","is_agree":"0","length":"03:58","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"45.00","present_price":"38.00","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1568610574","staffing":"","store_id":"384","store_name":"片库","title":"Dream Crazy","userid":"2041","videotype":"1,2,4","views":"53","weight":"3"},{"agree":"2","auth":1,"case_id":"1990","content_id":"208","details":"YYYYY","equipment":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lo8Q48Mz0NtJtn5ePYV93TRLt0r9","image_default":"http://mpai.liandao.mobi/uploads/bc/0e/a7/b1/fd/8d5f1786ad627c18c5083d.jpg","introduce":"","is_agree":"0","length":"02:04","logo":"http://mpai.liandao.mobi/uploads/94/97/b6/66/5d/1a7855e64e4f851e0ab03f.jpg","original_price":"200.00","present_price":"50.50","rank_average":"1.5","remarks":"ZZZZZZZZZZZZZ","shooting_time":"1566550958","staffing":"","store_id":"384","store_name":"片库","title":"泰国广告终于对乔布斯和马云下手了","userid":"2041","videotype":"1,2,4","views":"98","weight":"3"}]
         */

        private String currentpage;
        private String totalnum;
        private int totalpage;
        private List<VideoBean> video;

        private int itemType;

        public int getSelected() {
            return selected;
        }

        private int selected = 1;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

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

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public static class VideoBean implements Serializable {
            /**
             * agree : 0
             * auth : 1
             * case_id : 2538
             * content_id : 2335
             * details : YYYYY
             * equipment : MMMMMMMMMMM
             * filepath : http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lq1LNnb1U549DFwTyiFxgiHH3Jru
             * image_default : http://mpai.liandao.mobi/uploads/b6/02/ec/14/0c/abbdfe7640256d40c2e4fa.jpeg
             * introduce : NNNNNNN
             * is_agree : 0
             * length : 04:09
             * logo : http://mpai.liandao.mobi/uploads/de/74/e9/27/c1/df16ea69f29a807661bed4.jpeg
             * original_price : 80.50
             * present_price : 50.50
             * rank_average : 1.5
             * remarks :
             * shooting_time : 1569722714
             * staffing : XXXXXXXX
             * store_id : 1396
             * store_name : Leo的工作室
             * title : 我的小爸爸 温馨剧情短片
             * userid : 4671
             * videotype : 1,2
             * views : 1329
             * weight : 0
             */

            private String agree;
            private int auth;
            private String case_id;
            private String content_id;
            private String details;
            private String equipment;
            private String filepath;
            private String image_default;
            private String introduce;
            private String is_agree;
            private String length;
            private String logo;
            private String original_price;
            private String present_price;
            private String rank_average;
            private String remarks;
            private String shooting_time;
            private String staffing;
            private String store_id;
            private String store_name;
            private String title;
            private String userid;
            private String videotype;
            private String views;
            private String weight;

            public String getAgree() {
                return agree;
            }

            public void setAgree(String agree) {
                this.agree = agree;
            }

            public int getAuth() {
                return auth;
            }

            public void setAuth(int auth) {
                this.auth = auth;
            }

            public String getCase_id() {
                return case_id;
            }

            public void setCase_id(String case_id) {
                this.case_id = case_id;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getEquipment() {
                return equipment;
            }

            public void setEquipment(String equipment) {
                this.equipment = equipment;
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

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(String is_agree) {
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

            public String getRank_average() {
                return rank_average;
            }

            public void setRank_average(String rank_average) {
                this.rank_average = rank_average;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getShooting_time() {
                return shooting_time;
            }

            public void setShooting_time(String shooting_time) {
                this.shooting_time = shooting_time;
            }

            public String getStaffing() {
                return staffing;
            }

            public void setStaffing(String staffing) {
                this.staffing = staffing;
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

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getVideotype() {
                return videotype;
            }

            public void setVideotype(String videotype) {
                this.videotype = videotype;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }
        }
    }
}
