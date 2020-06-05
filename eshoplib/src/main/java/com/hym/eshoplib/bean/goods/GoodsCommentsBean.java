package com.hym.eshoplib.bean.goods;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/17.
 * <p>
 * Description 商品评论
 * <p>
 * otherTips
 */

public class GoodsCommentsBean {


    /**
     * data : {"currentpage":"1","info":[{"attachment":["http://jzshop.liandao.mobi/uploads/fe/75/60/07/4e/ff44506cafaaa03799824f.jpeg","http://jzshop.liandao.mobi/uploads/0e/16/6d/44/e2/726acd45b5332d0c565cbf.jpeg","http://jzshop.liandao.mobi/uploads/03/68/8c/47/9d/22ce311db1cb39bb4d4781.jpeg","http://jzshop.liandao.mobi/uploads/62/c6/dc/d7/be/1fceeac606495a20f29997.jpeg","http://jzshop.liandao.mobi/uploads/ce/b7/0e/11/49/1fea7b4523cbdabf601145.jpeg","http://jzshop.liandao.mobi/uploads/5a/b0/d2/78/37/c31164dcadeea7d345adc5.jpeg","http://jzshop.liandao.mobi/uploads/19/04/30/60/47/061dd8903dee06cb35774f.jpeg","http://jzshop.liandao.mobi/uploads/ea/47/de/e0/e4/8199d24d76e3f5d1b72d0f.jpeg"],"avatar":"http://jzshop.liandao.mobi/uploads/64/e6/65/87/06/6bdd09d37bb496a90b2208.jpg","content":"该用户未填写评论","ctime":"2018-04-16 16:48:01","id":"591","nickname":"明哥哥","pid":"0","property":":;","rank_base":"0","to_id":"2","userid":"891"}],"totalnum":"1","totalpage":"1"}
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
         * info : [{"attachment":["http://jzshop.liandao.mobi/uploads/fe/75/60/07/4e/ff44506cafaaa03799824f.jpeg","http://jzshop.liandao.mobi/uploads/0e/16/6d/44/e2/726acd45b5332d0c565cbf.jpeg","http://jzshop.liandao.mobi/uploads/03/68/8c/47/9d/22ce311db1cb39bb4d4781.jpeg","http://jzshop.liandao.mobi/uploads/62/c6/dc/d7/be/1fceeac606495a20f29997.jpeg","http://jzshop.liandao.mobi/uploads/ce/b7/0e/11/49/1fea7b4523cbdabf601145.jpeg","http://jzshop.liandao.mobi/uploads/5a/b0/d2/78/37/c31164dcadeea7d345adc5.jpeg","http://jzshop.liandao.mobi/uploads/19/04/30/60/47/061dd8903dee06cb35774f.jpeg","http://jzshop.liandao.mobi/uploads/ea/47/de/e0/e4/8199d24d76e3f5d1b72d0f.jpeg"],"avatar":"http://jzshop.liandao.mobi/uploads/64/e6/65/87/06/6bdd09d37bb496a90b2208.jpg","content":"该用户未填写评论","ctime":"2018-04-16 16:48:01","id":"591","nickname":"明哥哥","pid":"0","property":":;","rank_base":"0","to_id":"2","userid":"891"}]
         * totalnum : 1
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
             * attachment : ["http://jzshop.liandao.mobi/uploads/fe/75/60/07/4e/ff44506cafaaa03799824f.jpeg","http://jzshop.liandao.mobi/uploads/0e/16/6d/44/e2/726acd45b5332d0c565cbf.jpeg","http://jzshop.liandao.mobi/uploads/03/68/8c/47/9d/22ce311db1cb39bb4d4781.jpeg","http://jzshop.liandao.mobi/uploads/62/c6/dc/d7/be/1fceeac606495a20f29997.jpeg","http://jzshop.liandao.mobi/uploads/ce/b7/0e/11/49/1fea7b4523cbdabf601145.jpeg","http://jzshop.liandao.mobi/uploads/5a/b0/d2/78/37/c31164dcadeea7d345adc5.jpeg","http://jzshop.liandao.mobi/uploads/19/04/30/60/47/061dd8903dee06cb35774f.jpeg","http://jzshop.liandao.mobi/uploads/ea/47/de/e0/e4/8199d24d76e3f5d1b72d0f.jpeg"]
             * avatar : http://jzshop.liandao.mobi/uploads/64/e6/65/87/06/6bdd09d37bb496a90b2208.jpg
             * content : 该用户未填写评论
             * ctime : 2018-04-16 16:48:01
             * id : 591
             * nickname : 明哥哥
             * pid : 0
             * property : :;
             * rank_base : 0
             * to_id : 2
             * userid : 891
             */

            private String avatar;
            private String content;
            private String ctime;
            private String id;
            private String nickname;
            private String pid;
            private String property;
            private String rank_base;
            private String to_id;
            private String userid;
            private List<String> attachment;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public String getRank_base() {
                return rank_base;
            }

            public void setRank_base(String rank_base) {
                this.rank_base = rank_base;
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

            public List<String> getAttachment() {
                return attachment;
            }

            public void setAttachment(List<String> attachment) {
                this.attachment = attachment;
            }
        }
    }
}
