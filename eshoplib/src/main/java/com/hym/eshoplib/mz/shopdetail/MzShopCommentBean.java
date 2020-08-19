package com.hym.eshoplib.mz.shopdetail;

import java.util.List;

/**
 * 商品评价 ;
 */
public class MzShopCommentBean {


    /**
     * data : {"totalnum":"8","currentpage":"1","totalpage":"1","comment_rate":"75%","is_replay":0,"tags":[{"name":"服务很差","nums":2},{"name":"非常好，下次继续合作","nums":1},{"name":"片子质量很棒","nums":1},{"name":"态度诚恳","nums":1}],"info":[{"id":"14","pid":"0","case_id":"2809","userid":"4894","content":"试试","score":"5","ctime":"2020-08-14 16:01:07","images":"1597392059_IMG_CMP_139980186.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[{"id":"15","pid":"14","case_id":"2809","userid":"4894","content":"谢谢支持","ctime":"2020-08-14 15:01:07","nickname":"17666666666"}]},{"id":"13","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:59:10","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"12","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:58:58","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"11","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:58:51","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"10","pid":"0","case_id":"2809","userid":"4894","content":"可以的","score":"5","ctime":"2020-08-14 15:56:42","images":"1597391794_IMG_CMP_173980393.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"9","pid":"0","case_id":"2809","userid":"4894","content":"ZAGG开斋","score":"5","ctime":"2020-08-14 14:59:11","images":"1597388346_IMG_CMP_70410132.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"8","pid":"0","case_id":"2809","userid":"4894","content":"墨迹","score":null,"ctime":"2020-08-14 14:54:53","images":"1597388086_IMG_CMP_66581537.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"7","pid":"0","case_id":"2809","userid":"4894","content":"还可以","score":null,"ctime":"2020-08-14 14:53:59","images":"1597388029_IMG_CMP_125601706.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]}]}
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
         * totalnum : 8
         * currentpage : 1
         * totalpage : 1
         * comment_rate : 75%
         * is_replay : 0
         * tags : [{"name":"服务很差","nums":2},{"name":"非常好，下次继续合作","nums":1},{"name":"片子质量很棒","nums":1},{"name":"态度诚恳","nums":1}]
         * info : [{"id":"14","pid":"0","case_id":"2809","userid":"4894","content":"试试","score":"5","ctime":"2020-08-14 16:01:07","images":"1597392059_IMG_CMP_139980186.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[{"id":"15","pid":"14","case_id":"2809","userid":"4894","content":"谢谢支持","ctime":"2020-08-14 15:01:07","nickname":"17666666666"}]},{"id":"13","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:59:10","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"12","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:58:58","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"11","pid":"0","case_id":"2809","userid":"4894","content":"改行","score":"4","ctime":"2020-08-14 15:58:51","images":"1597391922_IMG_CMP_142381085.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"10","pid":"0","case_id":"2809","userid":"4894","content":"可以的","score":"5","ctime":"2020-08-14 15:56:42","images":"1597391794_IMG_CMP_173980393.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"9","pid":"0","case_id":"2809","userid":"4894","content":"ZAGG开斋","score":"5","ctime":"2020-08-14 14:59:11","images":"1597388346_IMG_CMP_70410132.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"8","pid":"0","case_id":"2809","userid":"4894","content":"墨迹","score":null,"ctime":"2020-08-14 14:54:53","images":"1597388086_IMG_CMP_66581537.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]},{"id":"7","pid":"0","case_id":"2809","userid":"4894","content":"还可以","score":null,"ctime":"2020-08-14 14:53:59","images":"1597388029_IMG_CMP_125601706.jpeg,","avatar":"http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png","nickname":"17666666666","agree_count":"0","is_agree":"0","replay":[]}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
        private String comment_rate;
        private int is_replay;
        private List<TagsBean> tags;
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

        public String getComment_rate() {
            return comment_rate;
        }

        public void setComment_rate(String comment_rate) {
            this.comment_rate = comment_rate;
        }

        public int getIs_replay() {
            return is_replay;
        }

        public void setIs_replay(int is_replay) {
            this.is_replay = is_replay;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class TagsBean {
            /**
             * name : 服务很差
             * nums : 2
             */

            private String name;
            private int nums;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }
        }

        public static class InfoBean {
            /**
             * id : 14
             * pid : 0
             * case_id : 2809
             * userid : 4894
             * content : 试试
             * score : 5
             * ctime : 2020-08-14 16:01:07
             * images : 1597392059_IMG_CMP_139980186.jpeg,
             * avatar : http://mpai.liandao.mobi/uploads/4a/bd/d6/a0/0a/f1311be55cb14d70f9b67e.png
             * nickname : 17666666666
             * agree_count : 0
             * is_agree : 0
             * replay : [{"id":"15","pid":"14","case_id":"2809","userid":"4894","content":"谢谢支持","ctime":"2020-08-14 15:01:07","nickname":"17666666666"}]
             */

            private String id;
            private String pid;
            private String case_id;
            private String userid;
            private String content;
            private String score;
            private String ctime;
            private String images;
            private String avatar;
            private String nickname;
            private String agree_count;
            private String is_agree;
            private List<ReplayBean> replay;

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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
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

            public List<ReplayBean> getReplay() {
                return replay;
            }

            public void setReplay(List<ReplayBean> replay) {
                this.replay = replay;
            }

            public static class ReplayBean {
                /**
                 * id : 15
                 * pid : 14
                 * case_id : 2809
                 * userid : 4894
                 * content : 谢谢支持
                 * ctime : 2020-08-14 15:01:07
                 * nickname : 17666666666
                 */

                private String id;
                private String pid;
                private String case_id;
                private String userid;
                private String content;
                private String ctime;
                private String nickname;

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

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }
            }
        }
    }
}
