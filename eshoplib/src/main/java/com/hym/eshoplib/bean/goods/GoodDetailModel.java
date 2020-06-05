package com.hym.eshoplib.bean.goods;

import java.io.Serializable;
import java.util.List;

public class GoodDetailModel implements Serializable{
    /**
     * data : {"agree_count":"2","attachment":[],"attachment_key":[],"auth":1,"case_id":"2100","content_id":"1888","ctime":"2019-07-16 16:00:56","details":"YYYYY","equipment":"MMMMMMMMMMM","expression":"","filepath":"http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lqRTWLIu9z1OKnpRqSEN5Vt53F4n","image_default":"http://mpai.liandao.mobi/uploads/db/8f/4c/6c/f8/ad30684bd9627b60ae86a2.jpeg","industry":"购物旅行-旅游度假","introduce":"NNNNNNN","is_agree":0,"is_del":"0","is_favorite":0,"is_index":"1","is_mine":"0","is_verify":"1","length":"04:16","original_price":"77.00","other":"","present_price":"11.00","qaction_id":"z1.0A1E1B437936C295985D2DBEB837C9E6","qcode":"0","region_id":"39","region_name":"-国内港台","remarks":"ZZZZZZZZZZZZZ","share_url":"http://wenjie.youwoapp.cn/activity/index/detail?content_id=2100","shooting_time":"1568610574","staffing":"XXXXXXXX","store_id":"1194","store_logo":"http://mpai.liandao.mobi/uploads/92/26/ea/c6/43/2700b77fd5aa92f384bd42.jpeg","store_name":"北京剑风影视文化","store_rank":"2.5","title":"骑行-在路上","toptime":"1567144785","type":"1","userid":"4254","video":"纪录片-梦想","videotype":"2,3","views":"707"}
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
         * agree_count : 2
         * attachment : []
         * attachment_key : []
         * auth : 1
         * case_id : 2100
         * content_id : 1888
         * ctime : 2019-07-16 16:00:56
         * details : YYYYY
         * equipment : MMMMMMMMMMM
         * expression :
         * filepath : http://mpic.liandao.mobi/kY7fqlsXHUEFn6qttLfWjIkUvFE=/lqRTWLIu9z1OKnpRqSEN5Vt53F4n
         * image_default : http://mpai.liandao.mobi/uploads/db/8f/4c/6c/f8/ad30684bd9627b60ae86a2.jpeg
         * industry : 购物旅行-旅游度假
         * introduce : NNNNNNN
         * is_agree : 0
         * is_del : 0
         * is_favorite : 0
         * is_index : 1
         * is_mine : 0
         * is_verify : 1
         * length : 04:16
         * original_price : 77.00
         * other :
         * present_price : 11.00
         * qaction_id : z1.0A1E1B437936C295985D2DBEB837C9E6
         * qcode : 0
         * region_id : 39
         * region_name : -国内港台
         * remarks : ZZZZZZZZZZZZZ
         * share_url : http://wenjie.youwoapp.cn/activity/index/detail?content_id=2100
         * shooting_time : 1568610574
         * staffing : XXXXXXXX
         * store_id : 1194
         * store_logo : http://mpai.liandao.mobi/uploads/92/26/ea/c6/43/2700b77fd5aa92f384bd42.jpeg
         * store_name : 北京剑风影视文化
         * store_rank : 2.5
         * title : 骑行-在路上
         * toptime : 1567144785
         * type : 1
         * userid : 4254
         * video : 纪录片-梦想
         * videotype : 2,3
         * views : 707
         */

        private String agree_count;
        private int auth;
        private String case_id;
        private String content_id;
        private String ctime;
        private String details;
        private String equipment;
        private String expression;
        private String filepath;
        private String image_default;
        private String industry;
        private String introduce;
        private int is_agree;
        private String is_del;
        private int is_favorite;
        private String is_index;
        private String is_mine;
        private String is_verify;
        private String length;
        private String original_price;
        private String other;
        private String present_price;
        private String qaction_id;
        private String qcode;
        private String region_id;
        private String region_name;
        private String remarks;
        private String share_url;
        private String shooting_time;
        private String staffing;
        private String store_id;
        private String store_logo;
        private String store_name;
        private String store_rank;
        private String title;
        private String toptime;
        private String type;
        private String userid;
        private String video;
        private String videotype;
        private String views;
        private String tel;
        private String category_id;
        private String weight;
        private List<String> attachment;
        private List<?> attachment_key;

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAgree_count() {
            return agree_count;
        }

        public void setAgree_count(String agree_count) {
            this.agree_count = agree_count;
        }

        public int getAuth() {
            return auth;
        }

        public void setAuth(int auth) {
            this.auth = auth;
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

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
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

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getIs_agree() {
            return is_agree;
        }

        public void setIs_agree(int is_agree) {
            this.is_agree = is_agree;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public String getIs_index() {
            return is_index;
        }

        public void setIs_index(String is_index) {
            this.is_index = is_index;
        }

        public String getIs_mine() {
            return is_mine;
        }

        public void setIs_mine(String is_mine) {
            this.is_mine = is_mine;
        }

        public String getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(String is_verify) {
            this.is_verify = is_verify;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getPresent_price() {
            return present_price;
        }

        public void setPresent_price(String present_price) {
            this.present_price = present_price;
        }

        public String getQaction_id() {
            return qaction_id;
        }

        public void setQaction_id(String qaction_id) {
            this.qaction_id = qaction_id;
        }

        public String getQcode() {
            return qcode;
        }

        public void setQcode(String qcode) {
            this.qcode = qcode;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShooting_time() {
            return shooting_time;
        }

        public void setShooting_time(String shooting_time) {
            this.shooting_time = shooting_time;
        }

        public String getStaffing() {
            return staffing;
        }

        public void setStaffing(String staffing) {
            this.staffing = staffing;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_rank() {
            return store_rank;
        }

        public void setStore_rank(String store_rank) {
            this.store_rank = store_rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getToptime() {
            return toptime;
        }

        public void setToptime(String toptime) {
            this.toptime = toptime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideotype() {
            return videotype;
        }

        public void setVideotype(String videotype) {
            this.videotype = videotype;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public List<String> getAttachment() {
            return attachment;
        }

        public void setAttachment(List<String> attachment) {
            this.attachment = attachment;
        }

        public List<?> getAttachment_key() {
            return attachment_key;
        }

        public void setAttachment_key(List<?> attachment_key) {
            this.attachment_key = attachment_key;
        }
    }
}
