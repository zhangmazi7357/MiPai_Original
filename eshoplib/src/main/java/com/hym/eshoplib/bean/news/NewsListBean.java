package com.hym.eshoplib.bean.news;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/8/1.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class NewsListBean implements Serializable{

    /**
     * data : {"totalnum":2,"currentpage":1,"totalpage":1,"info":[{"agency_id":"2","title":"纠纷调解","ctime":"2018-06-15 21:20:03","image":"http://mpai.liandao.mobi/uploads/7e/b6/66/a1/2f/64da98d13964c582f1228a.jpg","is_del":"0","type":"1","category_id":"5","memo":"纠纷调解的定义、纠纷调解的作用、纠纷调解使用的范围","views":"0","from":"","is_index":"0","url":"http://jzshop.liandao.mobi/agency/index/detail?content_id=2","is_favorite":0},{"agency_id":"1","title":"法律文书","ctime":"2018-06-15 21:17:31","image":"http://mpai.liandao.mobi/uploads/b3/3c/37/07/9a/be7375ed9141cfca20d943.jpg","is_del":"0","type":"1","category_id":"5","memo":"法律文书类别： 诉讼法律文书、仲裁法律文书、刑事法律文书、非诉法律文书、律师专用法律文书\r\n分类","views":"0","from":"","is_index":"1","url":"http://jzshop.liandao.mobi/agency/index/detail?content_id=1","is_favorite":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * totalnum : 2
         * currentpage : 1
         * totalpage : 1
         * info : [{"agency_id":"2","title":"纠纷调解","ctime":"2018-06-15 21:20:03","image":"http://mpai.liandao.mobi/uploads/7e/b6/66/a1/2f/64da98d13964c582f1228a.jpg","is_del":"0","type":"1","category_id":"5","memo":"纠纷调解的定义、纠纷调解的作用、纠纷调解使用的范围","views":"0","from":"","is_index":"0","url":"http://jzshop.liandao.mobi/agency/index/detail?content_id=2","is_favorite":0},{"agency_id":"1","title":"法律文书","ctime":"2018-06-15 21:17:31","image":"http://mpai.liandao.mobi/uploads/b3/3c/37/07/9a/be7375ed9141cfca20d943.jpg","is_del":"0","type":"1","category_id":"5","memo":"法律文书类别： 诉讼法律文书、仲裁法律文书、刑事法律文书、非诉法律文书、律师专用法律文书\r\n分类","views":"0","from":"","is_index":"1","url":"http://jzshop.liandao.mobi/agency/index/detail?content_id=1","is_favorite":0}]
         */

        private int totalnum;
        private int currentpage;
        private int totalpage;
        private List<InfoBean> info;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
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

        public static class InfoBean implements Serializable{
            public int getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(int is_agree) {
                this.is_agree = is_agree;
            }

            /**
             * agency_id : 2
             * title : 纠纷调解
             * ctime : 2018-06-15 21:20:03
             * image : http://mpai.liandao.mobi/uploads/7e/b6/66/a1/2f/64da98d13964c582f1228a.jpg
             * is_del : 0
             * type : 1
             * category_id : 5
             * memo : 纠纷调解的定义、纠纷调解的作用、纠纷调解使用的范围
             * views : 0
             * from :
             * is_index : 0
             * url : http://jzshop.liandao.mobi/agency/index/detail?content_id=2
             * is_favorite : 0
             */

            private int is_agree;
            private String agency_id;
            private String title;
            private String ctime;
            private String image;
            private String is_del;
            private String type;
            private String category_id;
            private String memo;
            private String views;
            private String from;
            private String is_index;
            private String url;
            private int is_favorite;
            private int agree;

            public int getAgree() {
                return agree;
            }

            public void setAgree(int agree) {
                this.agree = agree;
            }

            public String getAgency_id() {
                return agency_id;
            }

            public void setAgency_id(String agency_id) {
                this.agency_id = agency_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getIs_index() {
                return is_index;
            }

            public void setIs_index(String is_index) {
                this.is_index = is_index;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }
        }
    }
}
