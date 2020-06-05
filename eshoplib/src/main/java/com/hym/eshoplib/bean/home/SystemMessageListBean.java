package com.hym.eshoplib.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/25.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class SystemMessageListBean implements Serializable{

    /**
     * data : {"totalnum":1,"currentpage":"1","totalpage":"1","info":[{"msg_id":"2504","to_id":"1859","date":"2018-08-21 14:03:13","status":"1","content_id":"1","title":"测试信息","image_default":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","memo":"这是描述"}]}
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
         * totalnum : 1
         * currentpage : 1
         * totalpage : 1
         * info : [{"msg_id":"2504","to_id":"1859","date":"2018-08-21 14:03:13","status":"1","content_id":"1","title":"测试信息","image_default":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","memo":"这是描述"}]
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

        public static class InfoBean implements Serializable{
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            /**
             * msg_id : 2504
             * to_id : 1859
             * date : 2018-08-21 14:03:13
             * status : 1
             * content_id : 1
             * title : 测试信息
             * image_default : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
             * memo : 这是描述
             */

            private String type;
            private String msg_id;
            private String to_id;
            private String date;
            private String status;
            private String content_id;
            private String title;
            private String image_default;
            private String memo;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(String msg_id) {
                this.msg_id = msg_id;
            }

            public String getTo_id() {
                return to_id;
            }

            public void setTo_id(String to_id) {
                this.to_id = to_id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
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
