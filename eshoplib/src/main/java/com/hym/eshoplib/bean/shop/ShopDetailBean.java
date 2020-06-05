package com.hym.eshoplib.bean.shop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/8/28.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ShopDetailBean implements Serializable{

    /**
     * data : {"base":{"logo":"/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_name":"神司工作室","category_id":"文案策划","service":[{"name":"PPT方案","price":"800"},{"name":"WORD方案","price":"900"}],"region_name":"-北京"},"card_info":{"real_name":"文艾力","card_no":"210000000000000000","address":"沈阳市","tel":"13355554444","tel_bak":"13355554444","email":"123@qq.com","cardphoto_up":"http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","cardphoto_back":"http://localhost:8883/uploads/2e/51/6f/cd/87/4e36d708ec508e4cc81d01.jpg","cardphoto_standard":"http://localhost:8883/uploads/10/15/25/71/e3/fbfb7a11441b225621094d.jpg"},"employment":{"employment_time":"5","production_cycle":"15","remark":"制作者信息"},"refund":"1","invoice":"1","education":{"education":"本科","university":"斯坦福","attachment":["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]},"job":"","awards":{"awards":"小金人","attachment":["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]},"qiniu_token":"fasdfsafasdfadsfadsfasdfasdfadsfa"}
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
         * base : {"logo":"/ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png","store_name":"神司工作室","category_id":"文案策划","service":[{"name":"PPT方案","price":"800"},{"name":"WORD方案","price":"900"}],"region_name":"-北京"}
         * card_info : {"real_name":"文艾力","card_no":"210000000000000000","address":"沈阳市","tel":"13355554444","tel_bak":"13355554444","email":"123@qq.com","cardphoto_up":"http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","cardphoto_back":"http://localhost:8883/uploads/2e/51/6f/cd/87/4e36d708ec508e4cc81d01.jpg","cardphoto_standard":"http://localhost:8883/uploads/10/15/25/71/e3/fbfb7a11441b225621094d.jpg"}
         * employment : {"employment_time":"5","production_cycle":"15","remark":"制作者信息"}
         * refund : 1
         * invoice : 1
         * education : {"education":"本科","university":"斯坦福","attachment":["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]}
         * job :
         * awards : {"awards":"小金人","attachment":["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]}
         * qiniu_token : fasdfsafasdfadsfadsfasdfasdfadsfa
         */

        private BaseBean base;
        private CardInfoBean card_info;
        private EmploymentBean employment;
        private String refund;
        private String invoice;
        private EducationBean education;
        private String job;
        private AwardsBean awards;
        private String qiniu_token;
        private String rejection_reason;
        private String store_id;
        private String major;
        private String age;
        private String height;
        private String gender;
        private String weight;

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getRejection_reason() {
            return rejection_reason;
        }

        public void setRejection_reason(String rejection_reason) {
            this.rejection_reason = rejection_reason;
        }

        public BaseBean getBase() {
            return base;
        }

        public void setBase(BaseBean base) {
            this.base = base;
        }

        public CardInfoBean getCard_info() {
            return card_info;
        }

        public void setCard_info(CardInfoBean card_info) {
            this.card_info = card_info;
        }

        public EmploymentBean getEmployment() {
            return employment;
        }

        public void setEmployment(EmploymentBean employment) {
            this.employment = employment;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public EducationBean getEducation() {
            return education;
        }

        public void setEducation(EducationBean education) {
            this.education = education;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public AwardsBean getAwards() {
            return awards;
        }

        public void setAwards(AwardsBean awards) {
            this.awards = awards;
        }

        public String getQiniu_token() {
            return qiniu_token;
        }

        public void setQiniu_token(String qiniu_token) {
            this.qiniu_token = qiniu_token;
        }

        public static class BaseBean implements Serializable{
            /**
             * logo : /ae/e4/57/f0/39/ba8eaa6d9128a15ceec016.png
             * store_name : 神司工作室
             * category_id : 文案策划
             * service : [{"name":"PPT方案","price":"800"},{"name":"WORD方案","price":"900"}]
             * region_name : -北京
             */

            private String logo;
            private String store_name;
            private String category_id;
            private String category_name;
            private String region_name;
            private List<ServiceBean> service;
            private String is_verify;
            private String store_id;
            private String userid;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getIs_verify() {
                return is_verify;
            }

            public void setIs_verify(String is_verify) {
                this.is_verify = is_verify;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public List<ServiceBean> getService() {
                return service;
            }

            public void setService(List<ServiceBean> service) {
                this.service = service;
            }

            public static class ServiceBean implements Serializable{
                /**
                 * name : PPT方案
                 * price : 800
                 */

                private String name;
                private String price;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }

        public static class CardInfoBean implements Serializable{
            /**
             * real_name : 文艾力
             * card_no : 210000000000000000
             * address : 沈阳市
             * tel : 13355554444
             * tel_bak : 13355554444
             * email : 123@qq.com
             * cardphoto_up : http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg
             * cardphoto_back : http://localhost:8883/uploads/2e/51/6f/cd/87/4e36d708ec508e4cc81d01.jpg
             * cardphoto_standard : http://localhost:8883/uploads/10/15/25/71/e3/fbfb7a11441b225621094d.jpg
             */

            private String real_name;
            private String card_no;
            private String address;
            private String tel;
            private String tel_bak;
            private String email;
            private String cardphoto_up;
            private String cardphoto_back;
            private String cardphoto_standard;
            private String card_id0;
            private String card_id1;

            public String getCard_id0() {
                return card_id0;
            }

            public void setCard_id0(String card_id0) {
                this.card_id0 = card_id0;
            }

            public String getCard_id1() {
                return card_id1;
            }

            public void setCard_id1(String card_id1) {
                this.card_id1 = card_id1;
            }

            public String getCard_id2() {
                return card_id2;
            }

            public void setCard_id2(String card_id2) {
                this.card_id2 = card_id2;
            }

            private String card_id2;

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getTel_bak() {
                return tel_bak;
            }
            private String linkphone;
            private String linkname;

            public String getLinkphone() {
                return linkphone;
            }

            public void setLinkphone(String linkphone) {
                this.linkphone = linkphone;
            }

            public String getLinkname() {
                return linkname;
            }

            public void setLinkname(String linkname) {
                this.linkname = linkname;
            }

            public void setTel_bak(String tel_bak) {
                this.tel_bak = tel_bak;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getCardphoto_up() {
                return cardphoto_up;
            }

            public void setCardphoto_up(String cardphoto_up) {
                this.cardphoto_up = cardphoto_up;
            }

            public String getCardphoto_back() {
                return cardphoto_back;
            }

            public void setCardphoto_back(String cardphoto_back) {
                this.cardphoto_back = cardphoto_back;
            }

            public String getCardphoto_standard() {
                return cardphoto_standard;
            }

            public void setCardphoto_standard(String cardphoto_standard) {
                this.cardphoto_standard = cardphoto_standard;
            }
        }

        public static class EmploymentBean implements Serializable{
            /**
             * employment_time : 5
             * production_cycle : 15
             * remark : 制作者信息
             */

            private String employment_time;
            private String production_cycle;
            private String remark;

            public String getEmployment_time() {
                return employment_time;
            }

            public void setEmployment_time(String employment_time) {
                this.employment_time = employment_time;
            }

            public String getProduction_cycle() {
                return production_cycle;
            }

            public void setProduction_cycle(String production_cycle) {
                this.production_cycle = production_cycle;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }

        public static class EducationBean implements Serializable{
            /**
             * education : 本科
             * university : 斯坦福
             * attachment : ["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]
             */

            private String education;
            private String university;
            private List<AttachmentBean>attachment;

            public List<AttachmentBean> getAttachment() {
                return attachment;
            }

            public void setAttachment(List<AttachmentBean> attachment) {
                this.attachment = attachment;
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





            public static class AttachmentBean implements Serializable{
                String filepath;
                String id;

                public String getFilepath() {
                    return filepath;
                }

                public void setFilepath(String filepath) {
                    this.filepath = filepath;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }


        public static class AwardsBean implements Serializable{
            /**
             * awards : 小金人
             * attachment : ["http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg","http://localhost:8883/uploads/3e/9f/2e/0b/66/bbc8f85374d99b6b81871b.jpg"]
             */

            private String awards;

            private List<AttachmentBean>attachment;
            public String getAwards() {
                return awards;
            }

            public void setAwards(String awards) {
                this.awards = awards;
            }


            public List<AttachmentBean> getAttachment() {
                return attachment;
            }

            public void setAttachment(List<AttachmentBean> attachment) {
                this.attachment = attachment;
            }

            public static class AttachmentBean implements Serializable{
                String filepath;
                String id;

                public String getFilepath() {
                    return filepath;
                }

                public void setFilepath(String filepath) {
                    this.filepath = filepath;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
