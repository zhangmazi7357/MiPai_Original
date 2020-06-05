package com.hym.eshoplib.bean.shop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ServiceDetailBean implements Serializable{

    /**
     * data : {"region_id":"1","store_name":"神司工作室","logo":"http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_id":"259","content_id":"19","title":null,"price":"800.00","rank_average":"0","praise_rate":"0","remark":"制作者信息","ctime":null,"production_cycle":"15","employment_time":"3","refund_type":"1","invoice":"1","job":"","awards":["http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"],"awards_memo":"小金人","education":"本科","university":"斯坦福","userid":"1859","comment":"0","region_name":"北京","univer":["http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"],"is_favorite":0,"cate_list":[{"price":"800.00","content_id":"19","category_name":"PPT方案"},{"price":"900.00","content_id":"20","category_name":"WORD方案"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getIs_spec() {
            return is_spec;
        }

        public void setIs_spec(String is_spec) {
            this.is_spec = is_spec;
        }

        /**
         * region_id : 1
         * store_name : 神司工作室
         * logo : http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png
         * store_id : 259
         * content_id : 19
         * title : null
         * price : 800.00
         * rank_average : 0
         * praise_rate : 0
         * remark : 制作者信息
         * ctime : null
         * production_cycle : 15
         * employment_time : 3
         * refund_type : 1
         * invoice : 1
         * job :
         * awards : ["http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"]
         * awards_memo : 小金人
         * education : 本科
         * university : 斯坦福
         * userid : 1859
         * comment : 0
         * region_name : 北京
         * univer : ["http://localhost:8883/uploads/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png"]
         * is_favorite : 0
         * cate_list : [{"price":"800.00","content_id":"19","category_name":"PPT方案"},{"price":"900.00","content_id":"20","category_name":"WORD方案"}]
         */

        private String is_spec;
        private String share_url;
        private String region_id;
        private String store_name;
        private String logo;
        private String store_id;
        private String content_id;
        private Object title;
        private String price;
        private String rank_average;
        private String praise_rate;
        private String remark;
        private Object ctime;
        private String production_cycle;
        private String employment_time;
        private String refund_type;
        private String invoice;
        private String job;
        private String awards_memo;
        private String education;
        private String university;
        private String userid;
        private String comment;
        private String region_name;
        private int is_favorite;
        private List<String> awards;
        private List<String> univer;
        private List<CateListBean> cate_list;
        private String is_mine;
        private String now_price;
        private String category_id;
        private String major;
        private String certificate_auth;
        private String xuelizs_auth;
        private String gender;
        private String age;
        private String height;
        private String weight;
        private String auth_user;
        private String auth_store;

        public String getAuth_user() {
            return auth_user;
        }

        public void setAuth_user(String auth_user) {
            this.auth_user = auth_user;
        }

        public String getAuth_store() {
            return auth_store;
        }

        public void setAuth_store(String auth_store) {
            this.auth_store = auth_store;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getCertificate_auth() {
            return certificate_auth;
        }

        public void setCertificate_auth(String certificate_auth) {
            this.certificate_auth = certificate_auth;
        }

        public String getXuelizs_auth() {
            return xuelizs_auth;
        }

        public void setXuelizs_auth(String xuelizs_auth) {
            this.xuelizs_auth = xuelizs_auth;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getNow_price() {
            return now_price;
        }

        public void setNow_price(String now_price) {
            this.now_price = now_price;
        }

        public String getIs_mine() {
            return is_mine;
        }

        public void setIs_mine(String is_mine) {
            this.is_mine = is_mine;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRank_average() {
            return rank_average;
        }

        public void setRank_average(String rank_average) {
            this.rank_average = rank_average;
        }

        public String getPraise_rate() {
            return praise_rate;
        }

        public void setPraise_rate(String praise_rate) {
            this.praise_rate = praise_rate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getCtime() {
            return ctime;
        }

        public void setCtime(Object ctime) {
            this.ctime = ctime;
        }

        public String getProduction_cycle() {
            return production_cycle;
        }

        public void setProduction_cycle(String production_cycle) {
            this.production_cycle = production_cycle;
        }

        public String getEmployment_time() {
            return employment_time;
        }

        public void setEmployment_time(String employment_time) {
            this.employment_time = employment_time;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getAwards_memo() {
            return awards_memo;
        }

        public void setAwards_memo(String awards_memo) {
            this.awards_memo = awards_memo;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public List<String> getAwards() {
            return awards;
        }

        public void setAwards(List<String> awards) {
            this.awards = awards;
        }

        public List<String> getUniver() {
            return univer;
        }

        public void setUniver(List<String> univer) {
            this.univer = univer;
        }

        public List<CateListBean> getCate_list() {
            return cate_list;
        }

        public void setCate_list(List<CateListBean> cate_list) {
            this.cate_list = cate_list;
        }

        public static class CateListBean implements Serializable{
            /**
             * price : 800.00
             * content_id : 19
             * category_name : PPT方案
             */

            private String price;
            private String content_id;
            private String category_name;
            private String memo;
            private String is_spec;

            public String getIs_spec() {
                return is_spec;
            }

            public void setIs_spec(String is_spec) {
                this.is_spec = is_spec;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }
        }
    }
}
