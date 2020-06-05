package com.hym.eshoplib.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/16.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class HomeDataBean implements Serializable{


    /**
     * data : {"agency":[{"agency_id":"6","category_id":"0","ctime":"2018-08-10 15:45:48","from":"网易","image":"","image_default":"/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","inform":"阿卡丽打发打发速度快解放路卡束带结发<img src=\"/upload/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg\" alt=\"\" />","is_del":"0","is_index":"1","is_verify":"0","memo":"测试描述","title":"测试新闻","type":"1","views":"0"}],"banner":[{"attachmentid":"5043","board_id":"1","content":"","filepath":"http://mpai.liandao.mobi/uploads/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg","go_type":"3","link_url":"","page_id":"3","specification_id":"259","title":"测试"},{"attachmentid":"5042","board_id":"1","content":"","filepath":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","go_type":"1","link_url":"","page_id":"3","specification_id":"1","title":"测试"}],"category":[{"category_en_name":"","category_id":"1","category_japan_name":"ニュース","category_name":"文案策划","image":"","memo":"","pid":"0","type":"0"},{"category_en_name":"","category_id":"2","category_japan_name":"イベント","category_name":"导演","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"","category_id":"3","category_japan_name":"ヘルプ","category_name":"摄像师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"","category_id":"4","category_japan_name":"その他","category_name":"剪辑师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Visitor's guide","category_id":"5","category_japan_name":"","category_name":"影视制作团队","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Survive & Prosper","category_id":"7","category_japan_name":"","category_name":"平面设计师","image":"","memo":"","pid":"0","type":"0"},{"category_en_name":"Health & Well-being","category_id":"6","category_japan_name":"","category_name":"三维动画师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Family & Education","category_id":"8","category_japan_name":"","category_name":"图片摄影师","image":"","pid":"0","type":"0"}],"userinfo":{"email":"","nickname":"网红视频工作室","phone":"18940105285"},"video":[{"agree":"2","case_id":"12","content_id":"38","filepath":"http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4","image_default":"http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg","is_agree":"1","length":"00:09","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","rank_average":"0","store_id":"266","store_name":"网红视频工作室","title":"哈哈","views":"0"}]}
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
         * agency : [{"agency_id":"6","category_id":"0","ctime":"2018-08-10 15:45:48","from":"网易","image":"","image_default":"/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","inform":"阿卡丽打发打发速度快解放路卡束带结发<img src=\"/upload/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg\" alt=\"\" />","is_del":"0","is_index":"1","is_verify":"0","memo":"测试描述","title":"测试新闻","type":"1","views":"0"}]
         * banner : [{"attachmentid":"5043","board_id":"1","content":"","filepath":"http://mpai.liandao.mobi/uploads/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg","go_type":"3","link_url":"","page_id":"3","specification_id":"259","title":"测试"},{"attachmentid":"5042","board_id":"1","content":"","filepath":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","go_type":"1","link_url":"","page_id":"3","specification_id":"1","title":"测试"}]
         * category : [{"category_en_name":"","category_id":"1","category_japan_name":"ニュース","category_name":"文案策划","image":"","memo":"","pid":"0","type":"0"},{"category_en_name":"","category_id":"2","category_japan_name":"イベント","category_name":"导演","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"","category_id":"3","category_japan_name":"ヘルプ","category_name":"摄像师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"","category_id":"4","category_japan_name":"その他","category_name":"剪辑师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Visitor's guide","category_id":"5","category_japan_name":"","category_name":"影视制作团队","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Survive & Prosper","category_id":"7","category_japan_name":"","category_name":"平面设计师","image":"","memo":"","pid":"0","type":"0"},{"category_en_name":"Health & Well-being","category_id":"6","category_japan_name":"","category_name":"三维动画师","image":"","memo":"","pid":"0","type":"1"},{"category_en_name":"Family & Education","category_id":"8","category_japan_name":"","category_name":"图片摄影师","image":"","pid":"0","type":"0"}]
         * userinfo : {"email":"","nickname":"网红视频工作室","phone":"18940105285"}
         * video : [{"agree":"2","case_id":"12","content_id":"38","filepath":"http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4","image_default":"http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg","is_agree":"1","length":"00:09","logo":"http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg","rank_average":"0","store_id":"266","store_name":"网红视频工作室","title":"哈哈","views":"0"}]
         */

        private UserinfoBean userinfo;
        private List<AgencyBean> agency;
        private List<BannerBean> banner;
        private List<CategoryBean> category;
        private List<VideoBean> video;
        private List<AndroidVersionBean>android_version;

        public List<AndroidVersionBean> getAndroid_version() {
            return android_version;
        }

        public void setAndroid_version(List<AndroidVersionBean> android_version) {
            this.android_version = android_version;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public List<AgencyBean> getAgency() {
            return agency;
        }

        public void setAgency(List<AgencyBean> agency) {
            this.agency = agency;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }


        public static class UserinfoBean implements Serializable{
            /**
             * email :
             * nickname : 网红视频工作室
             * phone : 18940105285
             */

            private String email;
            private String nickname;
            private String phone;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class AgencyBean implements Serializable{
            /**
             * agency_id : 6
             * category_id : 0
             * ctime : 2018-08-10 15:45:48
             * from : 网易
             * image :
             * image_default : /f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
             * inform : 阿卡丽打发打发速度快解放路卡束带结发<img src="/upload/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg" alt="" />
             * is_del : 0
             * is_index : 1
             * is_verify : 0
             * memo : 测试描述
             * title : 测试新闻
             * type : 1
             * views : 0
             */

            private String agency_id;
            private String category_id;
            private String ctime;
            private String from;
            private String image;
            private String image_default;
            private String inform;
            private String is_del;
            private String is_index;
            private String is_verify;
            private String memo;
            private String title;
            private String type;
            private String views;
            private int is_agree;
            private String url;
            private String agree;

            public String getAgree() {
                return agree;
            }

            public void setAgree(String agree) {
                this.agree = agree;
            }

            public int getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(int is_agree) {
                this.is_agree = is_agree;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAgency_id() {
                return agency_id;
            }

            public void setAgency_id(String agency_id) {
                this.agency_id = agency_id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getInform() {
                return inform;
            }

            public void setInform(String inform) {
                this.inform = inform;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getIs_index() {
                return is_index;
            }

            public void setIs_index(String is_index) {
                this.is_index = is_index;
            }

            public String getIs_verify() {
                return is_verify;
            }

            public void setIs_verify(String is_verify) {
                this.is_verify = is_verify;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }
        }

        public static class BannerBean implements Serializable{
            /**
             * attachmentid : 5043
             * board_id : 1
             * content :
             * filepath : http://mpai.liandao.mobi/uploads/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg
             * go_type : 3
             * link_url :
             * page_id : 3
             * specification_id : 259
             * title : 测试
             */

            private String attachmentid;
            private String board_id;
            private String content;
            private String filepath;
            private String go_type;
            private String link_url;
            private String page_id;
            private String specification_id;
            private String title;
            private String url;
            private BannerBean.news_info news_info;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public BannerBean.news_info getNews_info() {
                return news_info;
            }

            public void setNews_info(BannerBean.news_info news_info) {
                this.news_info = news_info;
            }

            public String getAttachmentid() {
                return attachmentid;
            }

            public void setAttachmentid(String attachmentid) {
                this.attachmentid = attachmentid;
            }

            public String getBoard_id() {
                return board_id;
            }

            public void setBoard_id(String board_id) {
                this.board_id = board_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getGo_type() {
                return go_type;
            }

            public void setGo_type(String go_type) {
                this.go_type = go_type;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getPage_id() {
                return page_id;
            }

            public void setPage_id(String page_id) {
                this.page_id = page_id;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
            public static class news_info implements Serializable{
                /**
                 * agency_id : 6
                 * category_id : 0
                 * ctime : 2018-08-10 15:45:48
                 * from : 网易
                 * image :
                 * image_default : /f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
                 * inform : 阿卡丽打发打发速度快解放路卡束带结发<img src="/upload/ea/75/fb/dd/3f/c86848051506f36ed667d1.jpeg" alt="" />
                 * is_del : 0
                 * is_index : 1
                 * is_verify : 0
                 * memo : 测试描述
                 * title : 测试新闻
                 * type : 1
                 * views : 0
                 */

                private String agency_id;
                private String category_id;
                private String ctime;
                private String from;
                private String image;
                private String image_default;
                private String inform;
                private String is_del;
                private String is_index;
                private String is_verify;
                private String memo;
                private String title;
                private String type;
                private String views;
                private int is_agree;
                private String url;
                private String agree;

                public String getAgree() {
                    return agree;
                }

                public void setAgree(String agree) {
                    this.agree = agree;
                }

                public int getIs_agree() {
                    return is_agree;
                }

                public void setIs_agree(int is_agree) {
                    this.is_agree = is_agree;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getAgency_id() {
                    return agency_id;
                }

                public void setAgency_id(String agency_id) {
                    this.agency_id = agency_id;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getInform() {
                    return inform;
                }

                public void setInform(String inform) {
                    this.inform = inform;
                }

                public String getIs_del() {
                    return is_del;
                }

                public void setIs_del(String is_del) {
                    this.is_del = is_del;
                }

                public String getIs_index() {
                    return is_index;
                }

                public void setIs_index(String is_index) {
                    this.is_index = is_index;
                }

                public String getIs_verify() {
                    return is_verify;
                }

                public void setIs_verify(String is_verify) {
                    this.is_verify = is_verify;
                }

                public String getMemo() {
                    return memo;
                }

                public void setMemo(String memo) {
                    this.memo = memo;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getViews() {
                    return views;
                }

                public void setViews(String views) {
                    this.views = views;
                }

                @Override
                public String toString() {
                    return "news_info{" +
                            "agency_id='" + agency_id + '\'' +
                            ", category_id='" + category_id + '\'' +
                            ", ctime='" + ctime + '\'' +
                            ", from='" + from + '\'' +
                            ", image='" + image + '\'' +
                            ", image_default='" + image_default + '\'' +
                            ", inform='" + inform + '\'' +
                            ", is_del='" + is_del + '\'' +
                            ", is_index='" + is_index + '\'' +
                            ", is_verify='" + is_verify + '\'' +
                            ", memo='" + memo + '\'' +
                            ", title='" + title + '\'' +
                            ", type='" + type + '\'' +
                            ", views='" + views + '\'' +
                            ", is_agree=" + is_agree +
                            ", url='" + url + '\'' +
                            ", agree='" + agree + '\'' +
                            '}';
                }
            }
        }

        public static class CategoryBean implements Serializable{
            /**
             * category_en_name :
             * category_id : 1
             * category_japan_name : ニュース
             * category_name : 文案策划
             * image :
             * memo :
             * pid : 0
             * type : 0
             */

            private String category_en_name;
            private String category_id;
            private String category_japan_name;
            private String category_name;
            private String image;
            private String memo;
            private String pid;
            private String type;

            public String getCategory_en_name() {
                return category_en_name;
            }

            public void setCategory_en_name(String category_en_name) {
                this.category_en_name = category_en_name;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCategory_japan_name() {
                return category_japan_name;
            }

            public void setCategory_japan_name(String category_japan_name) {
                this.category_japan_name = category_japan_name;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class VideoBean implements Serializable{
            /**
             * agree : 2
             * case_id : 12
             * content_id : 38
             * filepath : http://pdyijsbww.bkt.clouddn.com/1536917564_e57df36d772f719cbcb65ab80ceb0653.mp4
             * image_default : http://mpai.liandao.mobi/uploads/fa/5b/f2/2c/72/22bd64e043ab9f810671ba.jpg
             * is_agree : 1
             * length : 00:09
             * logo : http://mpai.liandao.mobi/uploads/a4/7a/13/14/60/e24630ddf405fb3b94b4a3.jpg
             * rank_average : 0
             * store_id : 266
             * store_name : 网红视频工作室
             * title : 哈哈
             * views : 0
             */

            private String agree;
            private String case_id;
            private String content_id;
            private String filepath;
            private String image_default;
            private String is_agree;
            private String length;
            private String logo;
            private String rank_average;
            private String store_id;
            private String store_name;
            private String title;
            private String views;
            private String auth;

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

            public String getAgree() {
                return agree;
            }

            public void setAgree(String agree) {
                this.agree = agree;
            }

            public String getCase_id() {
                return case_id;
            }

            public void setCase_id(String case_id) {
                this.case_id = case_id;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getIs_agree() {
                return is_agree;
            }

            public void setIs_agree(String is_agree) {
                this.is_agree = is_agree;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getRank_average() {
                return rank_average;
            }

            public void setRank_average(String rank_average) {
                this.rank_average = rank_average;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }
        }
        public static class AndroidVersionBean implements Serializable{
            /**
             * id : 24
             * v_code : 7
             * v_name : 魅族
             * content :
             * filepath : http://app.meizu.com/apps/public/detail?package_name=com.hym.eshoplib
             * size :
             * filepath_tdc :
             * status : 0
             * ctime : 0000-00-00 00:00:00
             * mtime : 0000-00-00 00:00:00
             * platform : android
             */

            private String id;
            private String v_code;
            private String v_name;
            private String content;
            private String filepath;
            private String size;
            private String filepath_tdc;
            private String status;
            private String ctime;
            private String mtime;
            private String platform;

            @Override
            public String toString() {
                return "AndroidVersionBean{" +
                        "id='" + id + '\'' +
                        ", v_code='" + v_code + '\'' +
                        ", v_name='" + v_name + '\'' +
                        ", content='" + content + '\'' +
                        ", filepath='" + filepath + '\'' +
                        ", size='" + size + '\'' +
                        ", filepath_tdc='" + filepath_tdc + '\'' +
                        ", status='" + status + '\'' +
                        ", ctime='" + ctime + '\'' +
                        ", mtime='" + mtime + '\'' +
                        ", platform='" + platform + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getV_code() {
                return v_code;
            }

            public void setV_code(String v_code) {
                this.v_code = v_code;
            }

            public String getV_name() {
                return v_name;
            }

            public void setV_name(String v_name) {
                this.v_name = v_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getFilepath_tdc() {
                return filepath_tdc;
            }

            public void setFilepath_tdc(String filepath_tdc) {
                this.filepath_tdc = filepath_tdc;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getMtime() {
                return mtime;
            }

            public void setMtime(String mtime) {
                this.mtime = mtime;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }
        }
    }
}
