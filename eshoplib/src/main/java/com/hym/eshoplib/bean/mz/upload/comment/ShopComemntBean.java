package com.hym.eshoplib.bean.mz.upload.comment;

import java.util.List;

public class ShopComemntBean {

    /**
     * data : {"totalnum":"2","currentpage":"1","totalpage":"1","info":[{"id":"3","pid":"0","case_id":"2793","userid":"223","content":"漂亮","title":"拍的很漂亮","ctime":"2020-08-06 11:56:15","images":"3.jpg,4.jpg","avatar":"","nickname":null,"agree_count":"0","is_agree":"0","replay":[]},{"id":"1","pid":"0","case_id":"2793","userid":"22","content":"漂亮","title":"拍的很漂亮","ctime":"2020-08-05 18:25:25","images":"3.jpg,4.jpg","avatar":"","nickname":null,"agree_count":"0","is_agree":"0","replay":[]}]}
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
         * totalnum : 2
         * currentpage : 1
         * totalpage : 1
         * info : [{"id":"3","pid":"0","case_id":"2793","userid":"223","content":"漂亮","title":"拍的很漂亮","ctime":"2020-08-06 11:56:15","images":"3.jpg,4.jpg","avatar":"","nickname":null,"agree_count":"0","is_agree":"0","replay":[]},{"id":"1","pid":"0","case_id":"2793","userid":"22","content":"漂亮","title":"拍的很漂亮","ctime":"2020-08-05 18:25:25","images":"3.jpg,4.jpg","avatar":"","nickname":null,"agree_count":"0","is_agree":"0","replay":[]}]
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
             * id : 3
             * pid : 0
             * case_id : 2793
             * userid : 223
             * content : 漂亮
             * title : 拍的很漂亮
             * ctime : 2020-08-06 11:56:15
             * images : 3.jpg,4.jpg
             * avatar :
             * nickname : null
             * agree_count : 0
             * is_agree : 0
             * replay : []
             */

            private String id;
            private String pid;
            private String case_id;
            private String userid;
            private String content;
            private String title;
            private String ctime;
            private String images;
            private String avatar;
            private Object nickname;
            private String agree_count;
            private String is_agree;
            private List<String> replay;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getCase_id() {
                return case_id;
            }

            public void setCase_id(String case_id) {
                this.case_id = case_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getAgree_count() {
                return agree_count;
            }

            public void setAgree_count(String agree_count) {
                this.agree_count = agree_count;
            }

            public String getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(String is_agree) {
                this.is_agree = is_agree;
            }

            public List<String> getReplay() {
                return replay;
            }

            public void setReplay(List<String> replay) {
                this.replay = replay;
            }
        }
    }
}
