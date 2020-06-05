package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/14.
 * <p>
 * Description 我的发布列表
 * <p>
 * otherTips
 */

public class MyPublishListBean {

    /**
     * data : {"totalnum":"4","currentpage":1,"totalpage":1,"info":[{"content_id":"204","userid":"891","category_id":"36","title":"二手天逸","body":"你好恩","ctime":1521016461,"type":"1","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg","dtime":"2018-03-14 16:34:21","count":"0","quality_id":"1","price":"200.00","region_id":"4","region_name":"北京市西城区","language":"1","visit_time":"2018-03-14 16:34:21","shelves_time":"2018-03-14 14:43:51","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg"},{"img_path":"http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京东城区"},{"content_id":"202","userid":"891","category_id":"24","title":"三手小姑娘","body":"三手小姑娘","ctime":1521007107,"type":"1","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/4c/04/08/a4/8c/d9a7fcf7c17a95d9a21152.png","dtime":"2018-03-14 13:58:27","count":"0","quality_id":"1","price":"999.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-14 14:51:03","shelves_time":"2018-03-14 13:58:27","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/4c/04/08/a4/8c/d9a7fcf7c17a95d9a21152.png"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京海淀区"},{"content_id":"198","userid":"891","category_id":"34","title":"三手老爷们","body":"哈哈哈","ctime":1520774320,"type":"3","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg","dtime":"2018-03-11 21:18:40","count":"0","quality_id":"1","price":"380.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-14 13:55:37","shelves_time":"2018-03-11 21:18:40","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg"}],"is_mine":1,"type_name":"已售罄","comment_count":"0","region":"北京海淀区"},{"content_id":"196","userid":"891","category_id":"14","title":"二手钢琴","body":"9层新的钢琴谁买谁合适咯","ctime":1520771959,"type":"1","top":"0","image_default":"","dtime":"2018-03-11 20:39:19","count":"0","quality_id":"1","price":"2000.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-13 16:43:55","shelves_time":"2018-03-11 20:39:19","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/50/d9/01/0b/46/37e30b5dddbd3d0ffbd95c.jpg"},{"img_path":"http://jzshop.liandao.mobi/uploads/f5/58/ce/82/26/8277aa7e2b6e680b54e359.jpg"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京海淀区"}]}
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
         * totalnum : 4
         * currentpage : 1
         * totalpage : 1
         * info : [{"content_id":"204","userid":"891","category_id":"36","title":"二手天逸","body":"你好恩","ctime":1521016461,"type":"1","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg","dtime":"2018-03-14 16:34:21","count":"0","quality_id":"1","price":"200.00","region_id":"4","region_name":"北京市西城区","language":"1","visit_time":"2018-03-14 16:34:21","shelves_time":"2018-03-14 14:43:51","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg"},{"img_path":"http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京东城区"},{"content_id":"202","userid":"891","category_id":"24","title":"三手小姑娘","body":"三手小姑娘","ctime":1521007107,"type":"1","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/4c/04/08/a4/8c/d9a7fcf7c17a95d9a21152.png","dtime":"2018-03-14 13:58:27","count":"0","quality_id":"1","price":"999.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-14 14:51:03","shelves_time":"2018-03-14 13:58:27","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/4c/04/08/a4/8c/d9a7fcf7c17a95d9a21152.png"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京海淀区"},{"content_id":"198","userid":"891","category_id":"34","title":"三手老爷们","body":"哈哈哈","ctime":1520774320,"type":"3","top":"0","image_default":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg","dtime":"2018-03-11 21:18:40","count":"0","quality_id":"1","price":"380.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-14 13:55:37","shelves_time":"2018-03-11 21:18:40","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg"}],"is_mine":1,"type_name":"已售罄","comment_count":"0","region":"北京海淀区"},{"content_id":"196","userid":"891","category_id":"14","title":"二手钢琴","body":"9层新的钢琴谁买谁合适咯","ctime":1520771959,"type":"1","top":"0","image_default":"","dtime":"2018-03-11 20:39:19","count":"0","quality_id":"1","price":"2000.00","region_id":"2","region_name":"北京海淀区","language":"1","visit_time":"2018-03-13 16:43:55","shelves_time":"2018-03-11 20:39:19","attachment_s":[{"img_path":"http://jzshop.liandao.mobi/uploads/50/d9/01/0b/46/37e30b5dddbd3d0ffbd95c.jpg"},{"img_path":"http://jzshop.liandao.mobi/uploads/f5/58/ce/82/26/8277aa7e2b6e680b54e359.jpg"}],"is_mine":1,"type_name":"已上架","comment_count":"0","region":"北京海淀区"}]
         */

        private String totalnum;
        private int currentpage;
        private int totalpage;
        private List<InfoBean> info;

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

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
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
             * content_id : 204
             * userid : 891
             * category_id : 36
             * title : 二手天逸
             * body : 你好恩
             * ctime : 1521016461
             * type : 1
             * top : 0
             * image_default : http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg
             * dtime : 2018-03-14 16:34:21
             * count : 0
             * quality_id : 1
             * price : 200.00
             * region_id : 4
             * region_name : 北京市西城区
             * language : 1
             * visit_time : 2018-03-14 16:34:21
             * shelves_time : 2018-03-14 14:43:51
             * attachment_s : [{"img_path":"http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg"},{"img_path":"http://jzshop.liandao.mobi/uploads/f0/e0/49/a9/5c/f33e99d127d24dbb3d4f00.jpg"}]
             * is_mine : 1
             * type_name : 已上架
             * comment_count : 0
             * region : 北京东城区
             */

            private String content_id;
            private String userid;
            private String category_id;
            private String title;
            private String body;
            private int ctime;
            private String type;
            private String top;
            private String image_default;
            private String dtime;
            private String count;
            private String quality_id;
            private String price;
            private String region_id;
            private String region_name;
            private String language;
            private String visit_time;
            private String shelves_time;
            private int is_mine;
            private String type_name;
            private String comment_count;
            private String region;
            private List<AttachmentSBean> attachment_s;

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getDtime() {
                return dtime;
            }

            public void setDtime(String dtime) {
                this.dtime = dtime;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getQuality_id() {
                return quality_id;
            }

            public void setQuality_id(String quality_id) {
                this.quality_id = quality_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getVisit_time() {
                return visit_time;
            }

            public void setVisit_time(String visit_time) {
                this.visit_time = visit_time;
            }

            public String getShelves_time() {
                return shelves_time;
            }

            public void setShelves_time(String shelves_time) {
                this.shelves_time = shelves_time;
            }

            public int getIs_mine() {
                return is_mine;
            }

            public void setIs_mine(int is_mine) {
                this.is_mine = is_mine;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public List<AttachmentSBean> getAttachment_s() {
                return attachment_s;
            }

            public void setAttachment_s(List<AttachmentSBean> attachment_s) {
                this.attachment_s = attachment_s;
            }

            public static class AttachmentSBean {
                /**
                 * img_path : http://jzshop.liandao.mobi/uploads/c9/28/be/1c/ae/0c011bce51efbd1287121d.jpg
                 */

                private String img_path;

                public String getImg_path() {
                    return img_path;
                }

                public void setImg_path(String img_path) {
                    this.img_path = img_path;
                }
            }
        }
    }
}
