package com.hym.eshoplib.bean.goods;

import java.io.Serializable;
import java.util.List;

public class GoodDetailModel implements Serializable {


    /**
     * data : {"case_id":"2762","content_id":"2338","type":"2","store_id":"1398","image_default":"http://mpai.liandao.mobi/uploads/21/04/36/0e/cd/721044ad107ee223ae862a.jpeg","title":"职业照、形象照49元/人","views":"359","length":"5-10分钟","filepath":"","industry":"-","video":"-","expression":"","other":"万科时代中心·十里","region_id":"0","is_index":"0","is_verify":"1","is_del":"0","ctime":"2020-01-08 09:31:17","text":null,"inform":null,"userid":"4616","toptime":"1578394825","qaction_id":"0","qcode":"1","videotype":"1,","tyid":"1","original_price":"299","present_price":"49.00","staffing":"化妆＋拍摄＋修图","shooting_time":"1天","equipment":"Canon5D3","introduce":"此次特价拍摄产品是由\u201c觅拍\u201d官方发起，庆祝新春的酬宾活动，由优秀的摄影师、化妆师、修图师组成的拍摄团队，旨在回馈\u201c觅拍\u201d新老用户，为您带去最极致的体验，请大家分享导航栏活动链接。","details":"服务类型：化妆＋拍摄＋修图 \n摄影器材：使用佳能5D系列全画幅专业人像摄影器材\n摄  影  师：拥有多年人像摄影经验的职业摄影师 \n化  妆  师：经过职业化妆培训并考核认证的专业化妆师\n拍摄张数：1张精修\n付款成功后，请您联系客服预约拍摄时间","remarks":"付款后联系","dresser":"","photographer":"","nums":"张张张张张张张张张张张","times":"","designer":"","planner":"","cosmetics":"","tags":"可上门,不满意免费重拍,分享送底片","onetype":"1","twotype":"1,2,35","lon":"116.502409","lat":"39.915185","address":"北京市朝阳区万科时代中心","project_img":"","score":"0","com_rate":"0","attachment":["http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FpT8P1gPI79vvsPrx__UMJilgBV6","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fqx4pOE_d1omcwdH7R-Vi4bUF-Oz","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FnbHFpftBDBVIKDKavrBFjYlNdDs","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fll6_H0xuInVjGFhomcrOunZHplA","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FqnFaQ_3tF6ykrSmSni-iPtSLHFf","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FjKmJYEX7riiGXfbGnkFbTYqrwsS","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fheps65m73XCR90uXds7tVt_svJB","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fu4C_PBnmTjzSV1nwYJ345b0UE3z","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FpKUk9rtW8mMKmcDaJUN-By2the8"],"attachment_key":["ASE0JUPsTBkBS1ag-VY5jmPChco=/FpT8P1gPI79vvsPrx__UMJilgBV6","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fqx4pOE_d1omcwdH7R-Vi4bUF-Oz","ASE0JUPsTBkBS1ag-VY5jmPChco=/FnbHFpftBDBVIKDKavrBFjYlNdDs","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fll6_H0xuInVjGFhomcrOunZHplA","ASE0JUPsTBkBS1ag-VY5jmPChco=/FqnFaQ_3tF6ykrSmSni-iPtSLHFf","ASE0JUPsTBkBS1ag-VY5jmPChco=/FjKmJYEX7riiGXfbGnkFbTYqrwsS","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fheps65m73XCR90uXds7tVt_svJB","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fu4C_PBnmTjzSV1nwYJ345b0UE3z","ASE0JUPsTBkBS1ag-VY5jmPChco=/FpKUk9rtW8mMKmcDaJUN-By2the8"],"region_name":"","is_favorite":0,"is_agree":0,"agree_count":"0","store_logo":"http://mpai.liandao.mobi/uploads/bf/6b/c2/01/c5/a5a89f742f94054d7a4469.jpg","store_name":"觅拍官方账号","store_rank":"0","is_mine":"0","tel":"12345678999","share_url":"http://mpai.liandao.mobi/activity/index/detail?content_id=2762","weight":298,"auth":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * case_id : 2762
         * content_id : 2338
         * type : 2
         * store_id : 1398
         * image_default : http://mpai.liandao.mobi/uploads/21/04/36/0e/cd/721044ad107ee223ae862a.jpeg
         * title : 职业照、形象照49元/人
         * views : 359
         * length : 5-10分钟
         * filepath :
         * industry : -
         * video : -
         * expression :
         * other : 万科时代中心·十里
         * region_id : 0
         * is_index : 0
         * is_verify : 1
         * is_del : 0
         * ctime : 2020-01-08 09:31:17
         * text : null
         * inform : null
         * userid : 4616
         * toptime : 1578394825
         * qaction_id : 0
         * qcode : 1
         * videotype : 1,
         * tyid : 1
         * original_price : 299
         * present_price : 49.00
         * staffing : 化妆＋拍摄＋修图
         * shooting_time : 1天
         * equipment : Canon5D3
         * introduce : 此次特价拍摄产品是由“觅拍”官方发起，庆祝新春的酬宾活动，由优秀的摄影师、化妆师、修图师组成的拍摄团队，旨在回馈“觅拍”新老用户，为您带去最极致的体验，请大家分享导航栏活动链接。
         * details : 服务类型：化妆＋拍摄＋修图
         摄影器材：使用佳能5D系列全画幅专业人像摄影器材
         摄  影  师：拥有多年人像摄影经验的职业摄影师
         化  妆  师：经过职业化妆培训并考核认证的专业化妆师
         拍摄张数：1张精修
         付款成功后，请您联系客服预约拍摄时间
         * remarks : 付款后联系
         * dresser :
         * photographer :
         * nums : 张张张张张张张张张张张
         * times :
         * designer :
         * planner :
         * cosmetics :
         * tags : 可上门,不满意免费重拍,分享送底片
         * onetype : 1
         * twotype : 1,2,35
         * lon : 116.502409
         * lat : 39.915185
         * address : 北京市朝阳区万科时代中心
         * project_img :
         * score : 0
         * com_rate : 0
         * attachment : ["http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FpT8P1gPI79vvsPrx__UMJilgBV6","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fqx4pOE_d1omcwdH7R-Vi4bUF-Oz","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FnbHFpftBDBVIKDKavrBFjYlNdDs","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fll6_H0xuInVjGFhomcrOunZHplA","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FqnFaQ_3tF6ykrSmSni-iPtSLHFf","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FjKmJYEX7riiGXfbGnkFbTYqrwsS","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fheps65m73XCR90uXds7tVt_svJB","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/Fu4C_PBnmTjzSV1nwYJ345b0UE3z","http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FpKUk9rtW8mMKmcDaJUN-By2the8"]
         * attachment_key : ["ASE0JUPsTBkBS1ag-VY5jmPChco=/FpT8P1gPI79vvsPrx__UMJilgBV6","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fqx4pOE_d1omcwdH7R-Vi4bUF-Oz","ASE0JUPsTBkBS1ag-VY5jmPChco=/FnbHFpftBDBVIKDKavrBFjYlNdDs","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fll6_H0xuInVjGFhomcrOunZHplA","ASE0JUPsTBkBS1ag-VY5jmPChco=/FqnFaQ_3tF6ykrSmSni-iPtSLHFf","ASE0JUPsTBkBS1ag-VY5jmPChco=/FjKmJYEX7riiGXfbGnkFbTYqrwsS","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fheps65m73XCR90uXds7tVt_svJB","ASE0JUPsTBkBS1ag-VY5jmPChco=/Fu4C_PBnmTjzSV1nwYJ345b0UE3z","ASE0JUPsTBkBS1ag-VY5jmPChco=/FpKUk9rtW8mMKmcDaJUN-By2the8"]
         * region_name :
         * is_favorite : 0
         * is_agree : 0
         * agree_count : 0
         * store_logo : http://mpai.liandao.mobi/uploads/bf/6b/c2/01/c5/a5a89f742f94054d7a4469.jpg
         * store_name : 觅拍官方账号
         * store_rank : 0
         * is_mine : 0
         * tel : 12345678999
         * share_url : http://mpai.liandao.mobi/activity/index/detail?content_id=2762
         * weight : 298
         * auth : 1
         */

        private String case_id;
        private String content_id;
        private String type;
        private String store_id;
        private String image_default;
        private String title;
        private String views;
        private String length;
        private String filepath;
        private String industry;
        private String video;
        private String expression;
        private String other;
        private String region_id;
        private String is_index;
        private String is_verify;
        private String is_del;
        private String ctime;
        private String text;
        private String inform;
        private String userid;
        private String toptime;
        private String qaction_id;
        private String qcode;
        private String videotype;
        private String tyid;
        private String original_price;
        private String present_price;
        private String staffing;
        private String shooting_time;
        private String equipment;
        private String introduce;
        private String details;
        private String remarks;
        private String dresser;
        private String photographer;
        private String nums;
        private String times;
        private String designer;
        private String planner;
        private String cosmetics;
        private String tags;
        private String onetype;
        private String twotype;
        private String lon;
        private String lat;
        private String address;
        private String project_img;
        private String score;
        private String com_rate;
        private String region_name;
        private int is_favorite;
        private int is_agree;
        private String agree_count;
        private String store_logo;
        private String store_name;
        private String store_rank;
        private String is_mine;
        private String tel;
        private String share_url;
        private int weight;
        private int auth;
        private List<String> attachment;
        private List<String> attachment_key;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getImage_default() {
            return image_default;
        }

        public void setImage_default(String image_default) {
            this.image_default = image_default;
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

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
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

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public Object getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getInform() {
            return inform;
        }

        public void setInform(String inform) {
            this.inform = inform;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getToptime() {
            return toptime;
        }

        public void setToptime(String toptime) {
            this.toptime = toptime;
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

        public String getVideotype() {
            return videotype;
        }

        public void setVideotype(String videotype) {
            this.videotype = videotype;
        }

        public String getTyid() {
            return tyid;
        }

        public void setTyid(String tyid) {
            this.tyid = tyid;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getPresent_price() {
            return present_price;
        }

        public void setPresent_price(String present_price) {
            this.present_price = present_price;
        }

        public String getStaffing() {
            return staffing;
        }

        public void setStaffing(String staffing) {
            this.staffing = staffing;
        }

        public String getShooting_time() {
            return shooting_time;
        }

        public void setShooting_time(String shooting_time) {
            this.shooting_time = shooting_time;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getDresser() {
            return dresser;
        }

        public void setDresser(String dresser) {
            this.dresser = dresser;
        }

        public String getPhotographer() {
            return photographer;
        }

        public void setPhotographer(String photographer) {
            this.photographer = photographer;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(String designer) {
            this.designer = designer;
        }

        public String getPlanner() {
            return planner;
        }

        public void setPlanner(String planner) {
            this.planner = planner;
        }

        public String getCosmetics() {
            return cosmetics;
        }

        public void setCosmetics(String cosmetics) {
            this.cosmetics = cosmetics;
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

        public String getProject_img() {
            return project_img;
        }

        public void setProject_img(String project_img) {
            this.project_img = project_img;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCom_rate() {
            return com_rate;
        }

        public void setCom_rate(String com_rate) {
            this.com_rate = com_rate;
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

        public int getIs_agree() {
            return is_agree;
        }

        public void setIs_agree(int is_agree) {
            this.is_agree = is_agree;
        }

        public String getAgree_count() {
            return agree_count;
        }

        public void setAgree_count(String agree_count) {
            this.agree_count = agree_count;
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

        public String getIs_mine() {
            return is_mine;
        }

        public void setIs_mine(String is_mine) {
            this.is_mine = is_mine;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getAuth() {
            return auth;
        }

        public void setAuth(int auth) {
            this.auth = auth;
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
