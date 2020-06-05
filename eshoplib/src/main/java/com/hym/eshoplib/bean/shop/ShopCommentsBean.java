package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ShopCommentsBean {

    /**
     * data : {"totalnum":"1","currentpage":"1","totalpage":"1","info":[{"id":"16","pid":"0","to_id":"6","userid":"1859","content":"","rank_base":"0","ctime":"2018-07-16 10:34:35","rank_type":"4","category_id":"2","nums_reply":"0","avatar":"http://localhost:8883/uploads/08/96/ff/92/bc/732c302d83d0854331c0c0.jpeg","nickname":"李二毛","is_mine":1,"label_list":["服务超好","店主超赞","品质超高"],"category_name":"WORD方案","num":"1"}]}
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
         * info : [{"id":"16","pid":"0","to_id":"6","userid":"1859","content":"","rank_base":"0","ctime":"2018-07-16 10:34:35","rank_type":"4","category_id":"2","nums_reply":"0","avatar":"http://localhost:8883/uploads/08/96/ff/92/bc/732c302d83d0854331c0c0.jpeg","nickname":"李二毛","is_mine":1,"label_list":["服务超好","店主超赞","品质超高"],"category_name":"WORD方案","num":"1"}]
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
             * id : 16
             * pid : 0
             * to_id : 6
             * userid : 1859
             * content :
             * rank_base : 0
             * ctime : 2018-07-16 10:34:35
             * rank_type : 4
             * category_id : 2
             * nums_reply : 0
             * avatar : http://localhost:8883/uploads/08/96/ff/92/bc/732c302d83d0854331c0c0.jpeg
             * nickname : 李二毛
             * is_mine : 1
             * label_list : ["服务超好","店主超赞","品质超高"]
             * category_name : WORD方案
             * num : 1
             */

            private String id;
            private String pid;
            private String to_id;
            private String userid;
            private String content;
            private String rank_base;
            private String ctime;
            private String rank_type;
            private String category_id;
            private String nums_reply;
            private String avatar;
            private String nickname;
            private int is_mine;
            private String category_name;
            private String num;
            private List<String> label_list;
            private String auth;

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

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

            public String getTo_id() {
                return to_id;
            }

            public void setTo_id(String to_id) {
                this.to_id = to_id;
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

            public String getRank_base() {
                return rank_base;
            }

            public void setRank_base(String rank_base) {
                this.rank_base = rank_base;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getRank_type() {
                return rank_type;
            }

            public void setRank_type(String rank_type) {
                this.rank_type = rank_type;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getNums_reply() {
                return nums_reply;
            }

            public void setNums_reply(String nums_reply) {
                this.nums_reply = nums_reply;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getIs_mine() {
                return is_mine;
            }

            public void setIs_mine(int is_mine) {
                this.is_mine = is_mine;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<String> getLabel_list() {
                return label_list;
            }

            public void setLabel_list(List<String> label_list) {
                this.label_list = label_list;
            }
        }
    }
}
