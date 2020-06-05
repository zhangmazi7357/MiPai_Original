package com.hym.eshoplib.bean.me;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/13.
 * <p>
 * Description 我发布的代办意向
 * <p>
 * otherTips
 */

public class AgencyIntentionListBean implements Serializable{


    /**
     * data : {"currentpage":"1","info":[{"agency_id":"0","attachment_s":[{"filepath":"http://jzshop.liandao.mobi/uploads/59/03/e0/75/03/1789fc63f2331284c62d80.jpeg"}],"category_id":"2","category_name":"代办","company":"公司名","ctime":"2018-05-29 15:17:36","email":"9966477@qq.com","id":"114","is_del":"0","memo":"带图","name":"明哥哥","phone":"18940105285","title":"","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"2,4","category_name":"代办、法律","company":"","ctime":"2018-05-29 14:33:30","email":"9966477@qq.com","id":"112","is_del":"0","memo":"恩","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"2,4","category_name":"代办、法律","company":"灭个","ctime":"2018-05-28 13:36:35","email":"9966477@qq.com","id":"101","is_del":"0","memo":"Mr 您","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"0","category_name":"","company":"","ctime":"2018-05-14 10:54:46","email":"9966477@qq.com","id":"94","is_del":"0","memo":"你好","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"0","attachment_s":[],"category_id":"0","category_name":"","company":"","ctime":"2018-04-01 18:36:52","email":"9966477@163.com","id":"52","is_del":"0","memo":"你好","name":"明哥","phone":"18888888888","title":"","userid":"891"}],"totalnum":"5","totalpage":1}
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
         * currentpage : 1
         * info : [{"agency_id":"0","attachment_s":[{"filepath":"http://jzshop.liandao.mobi/uploads/59/03/e0/75/03/1789fc63f2331284c62d80.jpeg"}],"category_id":"2","category_name":"代办","company":"公司名","ctime":"2018-05-29 15:17:36","email":"9966477@qq.com","id":"114","is_del":"0","memo":"带图","name":"明哥哥","phone":"18940105285","title":"","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"2,4","category_name":"代办、法律","company":"","ctime":"2018-05-29 14:33:30","email":"9966477@qq.com","id":"112","is_del":"0","memo":"恩","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"2,4","category_name":"代办、法律","company":"灭个","ctime":"2018-05-28 13:36:35","email":"9966477@qq.com","id":"101","is_del":"0","memo":"Mr 您","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"10","attachment_s":[],"category_id":"0","category_name":"","company":"","ctime":"2018-05-14 10:54:46","email":"9966477@qq.com","id":"94","is_del":"0","memo":"你好","name":"明哥哥","phone":"18940105285","title":"内资公司注册流程","userid":"891"},{"agency_id":"0","attachment_s":[],"category_id":"0","category_name":"","company":"","ctime":"2018-04-01 18:36:52","email":"9966477@163.com","id":"52","is_del":"0","memo":"你好","name":"明哥","phone":"18888888888","title":"","userid":"891"}]
         * totalnum : 5
         * totalpage : 1
         */

        private String currentpage;
        private String totalnum;
        private int totalpage;
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
            /**
             * agency_id : 0
             * attachment_s : [{"filepath":"http://jzshop.liandao.mobi/uploads/59/03/e0/75/03/1789fc63f2331284c62d80.jpeg"}]
             * category_id : 2
             * category_name : 代办
             * company : 公司名
             * ctime : 2018-05-29 15:17:36
             * email : 9966477@qq.com
             * id : 114
             * is_del : 0
             * memo : 带图
             * name : 明哥哥
             * phone : 18940105285
             * title :
             * userid : 891
             */

            private String agency_id;
            private String category_id;
            private String category_name;
            private String company;
            private String ctime;
            private String email;
            private String id;
            private String is_del;
            private String memo;
            private String name;
            private String phone;
            private String title;
            private String userid;
            private List<AttachmentSBean> attachment_s;

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

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public List<AttachmentSBean> getAttachment_s() {
                return attachment_s;
            }

            public void setAttachment_s(List<AttachmentSBean> attachment_s) {
                this.attachment_s = attachment_s;
            }

            public static class AttachmentSBean implements Serializable{
                /**
                 * filepath : http://jzshop.liandao.mobi/uploads/59/03/e0/75/03/1789fc63f2331284c62d80.jpeg
                 */

                private String filepath;

                public String getFilepath() {
                    return filepath;
                }

                public void setFilepath(String filepath) {
                    this.filepath = filepath;
                }
            }
        }
    }
}
