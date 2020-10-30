package com.hym.eshoplib.bean.shop;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ProductDetailBean {


    /**
     * data : {"agree_count":"0","attachment":["http://mpic.liandao.mobi/1574947552_1574947552719344.jpg"],"attachment_key":["1574947552_1574947552719344.jpg"],"auth":1,"case_id":"2716","category_id":"1","content_id":"2031","cosmetics":"","ctime":"2019-11-28 21:26:28","designer":"","details":"详情","dresser":"","equipment":"","expression":"","filepath":"","image_default":"http://mpai.liandao.mobi/uploads/65/95/c3/96/e7/b000d4678695075d435e48.jpg","industry":"-","introduce":"","is_agree":0,"is_del":"0","is_favorite":0,"is_index":"0","is_mine":"1","is_verify":"0","length":"","nums":"","original_price":"15.00","other":"","photographer":"","planner":"经验","present_price":"12.00","qaction_id":"0","qcode":"1","region_id":"0","region_name":"","remarks":"备注","share_url":"http://wenjie.youwoapp.cn/activity/index/detail?content_id=2716","shooting_time":"","staffing":"","store_id":"1255","store_logo":"http://mpai.liandao.mobi/uploads/9e/98/0a/64/78/9f80564c3b39e0945ac88b.jpg","store_name":"文隽工作室","store_rank":"3","tel":"16619893234","times":"1天","title":"标题","toptime":"0","tyid":"4","type":"2","userid":"3784","video":"-","videotype":"1","views":"1"}
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
         * agree_count : 0
         * attachment : ["http://mpic.liandao.mobi/1574947552_1574947552719344.jpg"]
         * attachment_key : ["1574947552_1574947552719344.jpg"]
         * auth : 1
         * case_id : 2716
         * category_id : 1
         * content_id : 2031
         * cosmetics :
         * ctime : 2019-11-28 21:26:28
         * designer :
         * details : 详情
         * dresser :
         * equipment :
         * expression :
         * filepath :
         * image_default : http://mpai.liandao.mobi/uploads/65/95/c3/96/e7/b000d4678695075d435e48.jpg
         * industry : -
         * introduce :
         * is_agree : 0
         * is_del : 0
         * is_favorite : 0
         * is_index : 0
         * is_mine : 1
         * is_verify : 0
         * length :
         * nums :
         * original_price : 15.00
         * other :
         * photographer :
         * planner : 经验
         * present_price : 12.00
         * qaction_id : 0
         * qcode : 1
         * region_id : 0
         * region_name :
         * remarks : 备注
         * share_url : http://wenjie.youwoapp.cn/activity/index/detail?content_id=2716
         * shooting_time :
         * staffing :
         * store_id : 1255
         * store_logo : http://mpai.liandao.mobi/uploads/9e/98/0a/64/78/9f80564c3b39e0945ac88b.jpg
         * store_name : 文隽工作室
         * store_rank : 3
         * tel : 16619893234
         * times : 1天
         * title : 标题
         * toptime : 0
         * tyid : 4
         * type : 2
         * userid : 3784
         * video : -
         * videotype : 1
         * views : 1
         */

        private String agree_count;
        private String auth;
        private String case_id;
        private String category_id;
        private String content_id;
        private String cosmetics;
        private String ctime;
        private String designer;
        private String details;
        private String dresser;
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
        private String nums;
        private String original_price;
        private String other;
        private String photographer;
        private String planner;
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
        private String tel;
        private String times;
        private String title;
        private String toptime;
        private String tyid;
        private String type;
        private String userid;
        private String video;
        private String videotype;
        private String views;
        private List<String> attachment;
        private List<String> attachment_key;


        // 后加的
        private String onetype;
        private String twotype;
        private String lon;
        private String lat;
        private String address;
        private String project_img;
        private String tags;
        private String onetypeStr;
        private String twotypeStr;

        public String getOnetypeStr() {
            return onetypeStr;
        }

        public void setOnetypeStr(String onetypeStr) {
            this.onetypeStr = onetypeStr;
        }

        public String getTwotypeStr() {
            return twotypeStr;
        }

        public void setTwotypeStr(String twotypeStr) {
            this.twotypeStr = twotypeStr;
        }

        public String getProject_img() {
            return project_img;
        }

        public void setProject_img(String project_img) {
            this.project_img = project_img;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getOnetype() {
            return onetype;
        }

        public void setOnetype(String onetype) {
            this.onetype = onetype;
        }

        public String getTwotype() {
            return twotype;
        }

        public void setTwotype(String twotype) {
            this.twotype = twotype;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAgree_count() {
            return agree_count;
        }

        public void setAgree_count(String agree_count) {
            this.agree_count = agree_count;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getCase_id() {
            return case_id;
        }

        public void setCase_id(String case_id) {
            this.case_id = case_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getCosmetics() {
            return cosmetics;
        }

        public void setCosmetics(String cosmetics) {
            this.cosmetics = cosmetics;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(String designer) {
            this.designer = designer;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getDresser() {
            return dresser;
        }

        public void setDresser(String dresser) {
            this.dresser = dresser;
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

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
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

        public String getPhotographer() {
            return photographer;
        }

        public void setPhotographer(String photographer) {
            this.photographer = photographer;
        }

        public String getPlanner() {
            return planner;
        }

        public void setPlanner(String planner) {
            this.planner = planner;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
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

        public String getTyid() {
            return tyid;
        }

        public void setTyid(String tyid) {
            this.tyid = tyid;
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

        public List<String> getAttachment_key() {
            return attachment_key;
        }

        public void setAttachment_key(List<String> attachment_key) {
            this.attachment_key = attachment_key;
        }
    }
}
