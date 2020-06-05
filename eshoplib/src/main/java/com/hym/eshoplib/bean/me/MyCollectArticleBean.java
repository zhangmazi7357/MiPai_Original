package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/24.
 * <p>
 * Description 我的收藏活动
 * <p>
 * otherTips
 */

public class MyCollectArticleBean {

    /**
     * data : {"totalnum":"1","currentpage":"1","totalpage":"1","info":[{"favorite_id":"421","type":"activity","content":"38","userid":"891","specification_id":null,"description":null,"ctime":"2018-04-20 16:14:36","title":"大公司要闻速览 | 滴滴拟5亿美元投入单车；朱啸虎清仓ofo","memo":"哈罗单车完成新一轮近7亿美金融资","image_default":"http://jzshop.liandao.mobi/uploads/45/07/20/73/ef/b9282559172ddb5c76777a.jpg"}]}
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
         * info : [{"favorite_id":"421","type":"activity","content":"38","userid":"891","specification_id":null,"description":null,"ctime":"2018-04-20 16:14:36","title":"大公司要闻速览 | 滴滴拟5亿美元投入单车；朱啸虎清仓ofo","memo":"哈罗单车完成新一轮近7亿美金融资","image_default":"http://jzshop.liandao.mobi/uploads/45/07/20/73/ef/b9282559172ddb5c76777a.jpg"}]
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
             * favorite_id : 421
             * type : activity
             * content : 38
             * userid : 891
             * specification_id : null
             * description : null
             * ctime : 2018-04-20 16:14:36
             * title : 大公司要闻速览 | 滴滴拟5亿美元投入单车；朱啸虎清仓ofo
             * memo : 哈罗单车完成新一轮近7亿美金融资
             * image_default : http://jzshop.liandao.mobi/uploads/45/07/20/73/ef/b9282559172ddb5c76777a.jpg
             */

            private String favorite_id;
            private String type;
            private String content;
            private String userid;
            private Object specification_id;
            private Object description;
            private String ctime;
            private String title;
            private String memo;
            private String image_default;

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

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }
        }
    }
}
