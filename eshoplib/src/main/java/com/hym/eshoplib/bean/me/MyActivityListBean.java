package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/25.
 * <p>
 * Description 我的活动列表
 * <p>
 * otherTips
 */

public class MyActivityListBean {

    /**
     * data : {"totalnum":5,"currentpage":"1","totalpage":"1","info":[{"activity_id":"42","userid":"891","log_id":"194","ctime":"2018-04-24 20:33:57","status":"0","orderstatus":"1","content_id":"42","image_default":"http://jzshop.liandao.mobi/uploads/ed/df/c7/44/ab/087adc590d5a2ec7cb55c7.jpeg","title":"贾跃亭投资资金到位：FF91即将开造 年底交付 ","price":"88.00","memo":"贾跃亭投资资金到位：FF91即将开造 年底交付 "},{"activity_id":"39","userid":"891","log_id":"193","ctime":"2018-04-24 20:29:05","status":"0","orderstatus":"1","content_id":"39","image_default":"http://jzshop.liandao.mobi/uploads/5d/37/0f/2d/66/5c738a55f404aac956bd8a.jpg","title":"区块链投资机构扎堆兴起：新成立12家公司11家被投","price":"99.00","memo":"在创投圈，如果问投资人近期的投资风口是什么行业？"},{"activity_id":"41","userid":"891","log_id":"192","ctime":"2018-04-24 20:24:34","status":"0","orderstatus":"0","content_id":"41","image_default":"http://jzshop.liandao.mobi/uploads/dc/8d/90/63/e6/12c25f35a7a7e25f775175.jpg","title":"科技神回复 | 罗永浩称锤子新机高配版价格超六千，有六千还买个锤子啊！","price":"1.00","memo":"小米等5款手机壳含有毒有害物，苹果单项超标近50倍"},{"activity_id":"38","userid":"891","log_id":"185","ctime":"2018-04-23 21:45:58","status":"0","orderstatus":"1","content_id":"38","image_default":"http://jzshop.liandao.mobi/uploads/45/07/20/73/ef/b9282559172ddb5c76777a.jpg","title":"大公司要闻速览 | 滴滴拟5亿美元投入单车；朱啸虎清仓ofo","price":"0.00","memo":"哈罗单车完成新一轮近7亿美金融资"},{"activity_id":"1","userid":"891","log_id":"4","ctime":"2017-10-18 19:43:58","status":"0","orderstatus":"0","content_id":"1","image_default":"","title":"","price":"0.00","memo":""}]}
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
         * totalnum : 5
         * currentpage : 1
         * totalpage : 1
         * info : [{"activity_id":"42","userid":"891","log_id":"194","ctime":"2018-04-24 20:33:57","status":"0","orderstatus":"1","content_id":"42","image_default":"http://jzshop.liandao.mobi/uploads/ed/df/c7/44/ab/087adc590d5a2ec7cb55c7.jpeg","title":"贾跃亭投资资金到位：FF91即将开造 年底交付 ","price":"88.00","memo":"贾跃亭投资资金到位：FF91即将开造 年底交付 "},{"activity_id":"39","userid":"891","log_id":"193","ctime":"2018-04-24 20:29:05","status":"0","orderstatus":"1","content_id":"39","image_default":"http://jzshop.liandao.mobi/uploads/5d/37/0f/2d/66/5c738a55f404aac956bd8a.jpg","title":"区块链投资机构扎堆兴起：新成立12家公司11家被投","price":"99.00","memo":"在创投圈，如果问投资人近期的投资风口是什么行业？"},{"activity_id":"41","userid":"891","log_id":"192","ctime":"2018-04-24 20:24:34","status":"0","orderstatus":"0","content_id":"41","image_default":"http://jzshop.liandao.mobi/uploads/dc/8d/90/63/e6/12c25f35a7a7e25f775175.jpg","title":"科技神回复 | 罗永浩称锤子新机高配版价格超六千，有六千还买个锤子啊！","price":"1.00","memo":"小米等5款手机壳含有毒有害物，苹果单项超标近50倍"},{"activity_id":"38","userid":"891","log_id":"185","ctime":"2018-04-23 21:45:58","status":"0","orderstatus":"1","content_id":"38","image_default":"http://jzshop.liandao.mobi/uploads/45/07/20/73/ef/b9282559172ddb5c76777a.jpg","title":"大公司要闻速览 | 滴滴拟5亿美元投入单车；朱啸虎清仓ofo","price":"0.00","memo":"哈罗单车完成新一轮近7亿美金融资"},{"activity_id":"1","userid":"891","log_id":"4","ctime":"2017-10-18 19:43:58","status":"0","orderstatus":"0","content_id":"1","image_default":"","title":"","price":"0.00","memo":""}]
         */

        private int totalnum;
        private String currentpage;
        private String totalpage;
        private List<InfoBean> info;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
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
             * activity_id : 42
             * userid : 891
             * log_id : 194
             * ctime : 2018-04-24 20:33:57
             * status : 0
             * orderstatus : 1
             * content_id : 42
             * image_default : http://jzshop.liandao.mobi/uploads/ed/df/c7/44/ab/087adc590d5a2ec7cb55c7.jpeg
             * title : 贾跃亭投资资金到位：FF91即将开造 年底交付
             * price : 88.00
             * memo : 贾跃亭投资资金到位：FF91即将开造 年底交付
             */

            private String activity_id;
            private String userid;
            private String log_id;
            private String ctime;
            private String status;
            private String orderstatus;
            private String content_id;
            private String image_default;
            private String title;
            private String price;
            private String memo;

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getOrderstatus() {
                return orderstatus;
            }

            public void setOrderstatus(String orderstatus) {
                this.orderstatus = orderstatus;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
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

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }
        }
    }
}
