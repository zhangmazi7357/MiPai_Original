package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/13.
 * <p>
 * Description 我的收藏二手物品
 * <p>
 * otherTips
 */

public class MyCollectUsedGoodsListBean {

    /**
     * data : {"totalnum":"1","currentpage":"1","totalpage":"1","info":[{"favorite_id":"66","type":"bbs","content":"169","userid":"1710","specification_id":null,"description":null,"ctime":1515381818,"attachment_s":[{"img_path":"http://jzshop.liandao.mobihttp://wx.laopei.cn/uploads/e4/64/ae/da/a0/c8aa5854764e2523156396.jpeg"}],"is_mine":1,"region_name":"北京海淀区","content_id":"169","title":"二手德亚","price":"1000.00","image_default":""}]}
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
         * info : [{"favorite_id":"66","type":"bbs","content":"169","userid":"1710","specification_id":null,"description":null,"ctime":1515381818,"attachment_s":[{"img_path":"http://jzshop.liandao.mobihttp://wx.laopei.cn/uploads/e4/64/ae/da/a0/c8aa5854764e2523156396.jpeg"}],"is_mine":1,"region_name":"北京海淀区","content_id":"169","title":"二手德亚","price":"1000.00","image_default":""}]
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
             * favorite_id : 66
             * type : bbs
             * content : 169
             * userid : 1710
             * specification_id : null
             * description : null
             * ctime : 1515381818
             * attachment_s : [{"img_path":"http://jzshop.liandao.mobihttp://wx.laopei.cn/uploads/e4/64/ae/da/a0/c8aa5854764e2523156396.jpeg"}]
             * is_mine : 1
             * region_name : 北京海淀区
             * content_id : 169
             * title : 二手德亚
             * price : 1000.00
             * image_default :
             */

            private String favorite_id;
            private String type;
            private String content;
            private String userid;
            private Object specification_id;
            private Object description;
            private int ctime;
            private int is_mine;
            private String region_name;
            private String content_id;
            private String title;
            private String price;
            private String image_default;
            private List<AttachmentSBean> attachment_s;

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

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getIs_mine() {
                return is_mine;
            }

            public void setIs_mine(int is_mine) {
                this.is_mine = is_mine;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
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

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public List<AttachmentSBean> getAttachment_s() {
                return attachment_s;
            }

            public void setAttachment_s(List<AttachmentSBean> attachment_s) {
                this.attachment_s = attachment_s;
            }

            public static class AttachmentSBean {
                /**
                 * img_path : http://jzshop.liandao.mobihttp://wx.laopei.cn/uploads/e4/64/ae/da/a0/c8aa5854764e2523156396.jpeg
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
