package com.hym.eshoplib.http.shopapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.hym.httplib.interfaces.IHttpResultListener;

import java.util.Map;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;
import static cn.hym.superlib.utils.http.ApiExcuter.postTest;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 新闻和活动
 * <p>
 * otherTips
 */

public class ShopApi {

    //获取店铺信息
    public static <T> void getShopDetail(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetStudioinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //获取城市列表
    public static <T> void getCityList(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Consignee");
        request.setClassName("GetArea");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("index", "1");
        post(request, listener, clazz);

    }

    //获取城市列表
    public static <T> void getCityList2(String pid, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Consignee");
        request.setClassName("GetArea");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("pid", pid);
//        request.AddParems("psize","10");
//        request.AddParems("p",p);
        post(request, listener, clazz);

    }

    //工作室类型
    public static <T> void getShopType(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCategory");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //开设工作室步骤1
    public static <T> void OpenShopStep1(String logo, String store_name, String category_id,
                                         String region_id, String real_name, String card_no,
                                         String address,
                                         String tel,
                                         String linkname, String linkphone,
                                         String email, String attachment,
                                         IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("Apply");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("logo", logo);
        request.AddParems("store_name", store_name);
        request.AddParems("category_id", category_id);

        request.AddParems("region_id", region_id);
        request.AddParems("real_name", real_name);
        request.AddParems("card_no", card_no);


        request.AddParems("tel", tel);

        request.AddParems("address", address);
        request.AddParems("linkname", linkname);
        request.AddParems("linkphone", linkphone);

        request.AddParems("email", email);
        request.AddParems("attachment", attachment);


//        Log.e("ShopApi", "申请工作室 :" + new JSONObject().get(request).toString());


        post(request, listener, clazz);

    }

    //编辑工作室第一页
    public static <T> void EditShop(String logo, String store_name, String category_id,
                                    String region_id, String real_name, String card_no,
                                    String address,
                                    String tel,
                                    String linkname, String linkphone,
                                    String email, String attachment,
                                    IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("EditStudioinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "1");
        request.addParamsNotEmpty("logo", logo);
        request.addParamsNotEmpty("store_name", store_name);
        request.addParamsNotEmpty("category_id", category_id);

        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("real_name", real_name);
        request.addParamsNotEmpty("card_no", card_no);

        request.addParamsNotEmpty("address", address);
        request.addParamsNotEmpty("tel", tel);
        request.addParamsNotEmpty("linkname", linkname);
        request.addParamsNotEmpty("linkphone", linkphone);

        request.addParamsNotEmpty("email", email);
        request.addParamsNotEmpty("attachment", attachment);

        post(request, listener, clazz);

    }

    //编辑工作室第二页
    public static <T> void EditShop2(String employment_time, String production_cycle, String remark,
                                     String refund_type, String invoice, String education,
                                     String university, String job, String awards,
                                     String ed_attachment, String aw_attachment,
                                     String major,
                                     IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("EditStudioinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");
        request.addParamsNotNull("employment_time", employment_time);
        request.addParamsNotNull("production_cycle", production_cycle);
        request.addParamsNotNull("remark", remark);

        request.addParamsNotNull("refund_type", refund_type);
        request.addParamsNotNull("invoice", invoice);
        request.addParamsNotNull("education", education);

        request.addParamsNotNull("university", university);
        request.addParamsNotNull("job", job);
        request.addParamsNotNull("awards", awards);

        request.addParamsNotNull("ed_attachment", ed_attachment);
        request.addParamsNotNull("aw_attachment", aw_attachment);
        request.addParamsNotNull("major", major);
        post(request, listener, clazz);

    }

    //编辑演员信息
    public static <T> void EditShop3(String gender, String age, String height, String weight,
                                     IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("EditStudioinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");
        request.addParamsNotNull("gender", gender);
        request.addParamsNotNull("age", age);
        request.addParamsNotNull("height", height);
        request.addParamsNotNull("weight", weight);
        post(request, listener, clazz);

    }

    //获取产品列表,自己工作室的
    public static <T> void getProductsList(String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("mine", "1");
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);

    }

    //获取产品列表任意工作室的
    public static <T> void getProductsList2(String store_id, String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("store_id", store_id);
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);

    }

    //首页更多视频
    public static <T> void getProductsList3(String search, String p, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", type);
        request.addParamsNotEmpty("search", search);
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);

    }

    //更多精选视频
    public static <T> void getProductsList4(String search, String p, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", type);
        request.addParamsNotEmpty("search", search);
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        request.AddParems("chosen", "1");
        post(request, listener, clazz);

    }

    //获取七牛token
    public static <T> void getQiniuToken(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetRongToken");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //获取行业类型列表
    public static <T> void getIndustryList(String category_id, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetCategory");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("category_id", category_id);
        request.AddParems("type", type);
        post(request, listener, clazz);

    }

    //获取产品区域
    public static <T> void getProductRegion(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("House");
        request.setClassName("GetArea");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //上传图片产品
    public static <T> void upLoadImageProduct(String image_default,
                                              String attachment,
                                              String title,
                                              String industry,
                                              String video,
                                              String region_id, String other,
                                              String etPrice, String remarks,
                                              String originalPrice, String staffing,
                                              String shootingTime,
                                              String equipment, String introduce,
                                              String detail,
                                              String tyid,
                                              String shopTime,
                                              String paisheCount,
                                              String huazhuang,
                                              String sheyingshi,
                                              String shejishi,
                                              String time,
                                              String huazhuangpin,
                                              String cehua,
                                              String oneType,
                                              String twoType,
                                              String project_img,
                                              String address,
                                              String lon,
                                              String lat,
                                              String tags,
                                              IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();


        request.setApp("Store");
        request.setClassName("NewAddProduction");

        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");

        request.addParamsNotEmpty("image_default", image_default);
        request.addParamsNotEmpty("attachment", attachment);
        request.addParamsNotEmpty("title", title);
        request.addParamsNotEmpty("industry", industry);
        request.addParamsNotEmpty("video", video);
        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("other", other);

        request.AddParems("present_price", etPrice);

        request.addParamsNotEmpty("original_price", originalPrice);
        request.addParamsNotEmpty("staffing", staffing);
        request.addParamsNotEmpty("shooting_time", shootingTime);
        request.addParamsNotEmpty("equipment", equipment);
        request.addParamsNotEmpty("introduce", introduce);
        request.addParamsNotEmpty("details", detail);
        request.addParamsNotEmpty("remarks", remarks);

        request.addParamsNotEmpty("tyid", tyid);
        request.addParamsNotEmpty("times", time);
        request.addParamsNotEmpty("nums", paisheCount);
        request.addParamsNotEmpty("dresser", huazhuang);
        request.addParamsNotEmpty("photographer", sheyingshi);
        request.addParamsNotEmpty("designer", shejishi);
        request.addParamsNotEmpty("planner", cehua);
        request.addParamsNotEmpty("cosmetics", huazhuangpin);

        request.addParamsNotEmpty("onetype", oneType);
        request.addParamsNotEmpty("twotype", twoType);
        request.addParamsNotEmpty("project_img", project_img);
        request.addParamsNotEmpty("address", address);
        request.addParamsNotEmpty("lon", lon);
        request.addParamsNotEmpty("lat", lat);
        request.addParamsNotEmpty("tags", tags);


        Log.e("UpLoad", "==" + JSONObject.toJSONString(request));


        post(request, listener, clazz);

    }

    //上传视频产品
    public static <T> void upLoadVideoProduct(String image_default,
                                              String filepath,
                                              String title,
                                              String industry,
                                              String video,
                                              String region_id,
                                              String other,
                                              String length,
                                              String etPrice, String remarks,
                                              String originalPrice, String staffing,
                                              String shootingTime,
                                              String equipment, String introduce,
                                              String detail,
                                              String tyid, String shopTime,
                                              String paisheCount, String huazhuang,
                                              String sheyingshi, String shejishi,
                                              String time, String huazhuangping,
                                              String cehua,
                                              String oneType,
                                              String twoType,
                                              String project_img,
                                              String address,
                                              String lon,
                                              String lat,
                                              String tags,
                                              IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("NewAddProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "1");
        request.addParamsNotEmpty("image_default", image_default);
        request.addParamsNotEmpty("filepath", filepath);
        request.addParamsNotEmpty("title", title);
        request.addParamsNotEmpty("industry", industry);
        request.addParamsNotEmpty("video", video);
        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("other", other);
        request.addParamsNotEmpty("length", length);

        request.AddParems("present_price", etPrice);
        request.addParamsNotEmpty("original_price", originalPrice);
        request.addParamsNotEmpty("staffing", staffing);
        request.addParamsNotEmpty("shooting_time", shootingTime);
        request.addParamsNotEmpty("equipment", equipment);
        request.addParamsNotEmpty("introduce", introduce);
        request.addParamsNotEmpty("details", detail);
        request.addParamsNotEmpty("remarks", remarks);

        request.addParamsNotEmpty("tyid", tyid);
        request.addParamsNotEmpty("times", time);
        request.addParamsNotEmpty("nums", paisheCount);
        request.addParamsNotEmpty("dresser", huazhuang);
        request.addParamsNotEmpty("photographer", sheyingshi);
        request.addParamsNotEmpty("designer", shejishi);
        request.addParamsNotEmpty("planner", cehua);
        request.addParamsNotEmpty("cosmetics", huazhuangping);

        request.addParamsNotEmpty("onetype", oneType);
        request.addParamsNotEmpty("twotype", twoType);
        request.addParamsNotEmpty("project_img", project_img);
        request.addParamsNotEmpty("address", address);
        request.addParamsNotEmpty("lon", lon);
        request.addParamsNotEmpty("lat", lat);
        request.addParamsNotEmpty("tags", tags);

//        Log.e("UpLoad", "video = " + JSONObject.toJSONString(request));
        post(request, listener, clazz);

    }

    //编辑视频
    public static <T> void editVideoProduct(String case_id, String image_default, String filepath, String title,
                                            String industry, String video, String region_id, String other, String length,
                                            String etPrice, String remarks, String originalPrice,
                                            String staffing, String shootingTime, String equipment,
                                            String introduce, String detail, String tyid, String shopTime,
                                            String paisheCount, String huazhuang, String sheyingshi,
                                            String shejishi, String time, String huazhuangping, String cehua,

                                            String oneType,
                                            String twoType,
                                            String project_img,
                                            String address,
                                            String lon,
                                            String lat,
                                            String tags,
                                            IHttpResultListener<T> listener, Class<T> clazz) {

        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("EditProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "1");
        request.addParamsNotEmpty("case_id", case_id);
        request.addParamsNotEmpty("image_default", image_default);
        request.addParamsNotEmpty("filepath", filepath);
        request.addParamsNotEmpty("title", title);
        request.addParamsNotEmpty("industry", industry);
        request.addParamsNotEmpty("video", video);
        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("other", other);
        request.addParamsNotEmpty("length", length);

        request.AddParems("present_price", etPrice);
        request.addParamsNotEmpty("original_price", originalPrice);
        request.addParamsNotEmpty("staffing", staffing);
        request.addParamsNotEmpty("shooting_time", shootingTime);
        request.addParamsNotEmpty("equipment", equipment);
        request.addParamsNotEmpty("introduce", introduce);
        request.addParamsNotEmpty("details", detail);
        request.addParamsNotEmpty("remarks", remarks);

        request.addParamsNotEmpty("tyid", tyid);
        request.addParamsNotEmpty("times", time);
        request.addParamsNotEmpty("nums", paisheCount);
        request.addParamsNotEmpty("dresser", huazhuang);
        request.addParamsNotEmpty("photographer", sheyingshi);
        request.addParamsNotEmpty("designer", shejishi);
        request.addParamsNotEmpty("planner", cehua);
        request.addParamsNotEmpty("cosmetics", huazhuangping);


        request.addParamsNotEmpty("onetype", oneType);
        request.addParamsNotEmpty("twotype", twoType);
        request.addParamsNotEmpty("project_img", project_img);
        request.addParamsNotEmpty("address", address);
        request.addParamsNotEmpty("lon", lon);
        request.addParamsNotEmpty("lat", lat);
        request.addParamsNotEmpty("tags", tags);

        post(request, listener, clazz);

    }

    //编辑图片
    public static <T> void editImageProduct(String case_id, String image_default, String attachment, String title,
                                            String industry, String video, String region_id, String other, String length,
                                            String etPrice, String remarks, String originalPrice, String staffing, String shootingTime,
                                            String equipment, String introduce, String detail, String tyid, String shopTime,
                                            String paisheCount, String huazhuang, String sheyingshi, String shejishi, String time,
                                            String huazhuangping, String cehua,

                                            String oneType,
                                            String twoType,
                                            String project_img,
                                            String address,
                                            String lon,
                                            String lat,
                                            String tags,
                                            IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("EditProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");
        request.addParamsNotEmpty("case_id", case_id);
        request.addParamsNotEmpty("image_default", image_default);
        request.addParamsNotEmpty("attachment", attachment);
        request.addParamsNotEmpty("title", title);
        request.addParamsNotEmpty("industry", industry);
        request.addParamsNotEmpty("video", video);
        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("other", other);
        request.addParamsNotEmpty("length", length);

        request.AddParems("present_price", etPrice);
        request.addParamsNotEmpty("original_price", originalPrice);
        request.addParamsNotEmpty("staffing", staffing);
        request.addParamsNotEmpty("shooting_time", shootingTime);
        request.addParamsNotEmpty("equipment", equipment);
        request.addParamsNotEmpty("introduce", introduce);
        request.addParamsNotEmpty("details", detail);
        request.addParamsNotEmpty("remarks", remarks);

        request.addParamsNotEmpty("tyid", tyid);
        request.addParamsNotEmpty("times", time);
        request.addParamsNotEmpty("nums", paisheCount);
        request.addParamsNotEmpty("dresser", huazhuang);
        request.addParamsNotEmpty("photographer", sheyingshi);
        request.addParamsNotEmpty("designer", shejishi);
        request.addParamsNotEmpty("planner", cehua);
        request.addParamsNotEmpty("cosmetics", huazhuangping);


        request.addParamsNotEmpty("onetype", oneType);
        request.addParamsNotEmpty("twotype", twoType);
        request.addParamsNotEmpty("project_img", project_img);
        request.addParamsNotEmpty("address", address);
        request.addParamsNotEmpty("lon", lon);
        request.addParamsNotEmpty("lat", lat);
        request.addParamsNotEmpty("tags", tags);

        //  request.addParamsNotEmpty("tyid", "4");
        // request.addParamsNotEmpty("present_price ", "1元");
        post(request, listener, clazz);

    }

    //上传价格
    public static <T> void addPrice(String info, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("AddPrice");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("info", info);
        post(request, listener, clazz);

    }

    //删除产品
    public static <T> void deleteProduct(String case_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("DelProduction");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("case_id", case_id);
        post(request, listener, clazz);

    }

    //获取工作室列表
    public static <T> void getShopList(String region_id, String order, String sort, String category_id, String gender, String age_min, String age_max, String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        request.AddParems("order", order);
        request.AddParems("sort", sort);
        request.AddParems("category_id", category_id);
        request.AddParems("region_id", region_id);
        request.addParamsNotEmpty("gender", gender);
        request.addParamsNotEmpty("age_min", age_min);
        request.addParamsNotEmpty("age_max", age_max);
        post(request, listener, clazz);

    }

    //搜索工作室
    public static <T> void searchShopList(String key, String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        request.AddParems("key", key);

        post(request, listener, clazz);

    }

    //获取工作室详情
    public static <T> void GetContentDetail(String content_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetContentDetail");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        post(request, listener, clazz);

    }

    //获取工作室评论
    public static <T> void getShopComments(String content_id, String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCommentList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);

    }

    //收藏
    public static <T> void AddFavorite(String content_id, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddFavorite");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        request.AddParems("type", type);//类型-必须（store：工作室，case：产品）
        post(request, listener, clazz);

    }

    //点赞
    public static <T> void Addagree(String content_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddAgree");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        post(request, listener, clazz);

    }

    //新闻点赞
    public static <T> void Addagree2(String content_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddAgree");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        request.addParamsNotEmpty("type", "agency");
        post(request, listener, clazz);

    }

    //产品详情
    public static <T> void getProductDetail(String case_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProductionContent");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("case_id", case_id);
        post(request, listener, clazz);
        Map<String, String> params = request.getParams();

    }

    //立即预约
    public static <T> void attachNow(String content_id, String num, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("Add");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "1");
        request.AddParems("content_id", content_id);
        request.AddParems("num", num);
        post(request, listener, clazz);

    }

    /**
     * 详情页立即支付生成订单
     */
    public static <T> void CreateDetailOrder(Context context, String content_id, String buy_num, String case_id,
                                             IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("NewAdd");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("type", "1");//参数类型  1:立即购买，2:购物车-必须
        request.AddParems("content_id", content_id);
        request.AddParems("num", buy_num);
        request.AddParems("category", "1");
        request.AddParems("cid", case_id);
/*
        if(!TextUtils.isEmpty(coupon_log_id)){
            request.AddParems("coupon_log_id",coupon_log_id);
        }
*/
        post(request, listener, clazz);

    }

    //立即预约
    public static <T> void attachNowShoppingcart(String content_id, String cart_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("Add");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");
        request.AddParems("content_id", content_id);
        request.AddParems("cart_id", cart_id);
        post(request, listener, clazz);

    }

    //提醒对方
    public static <T> void EditNotice(String log_id, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("EditNotice");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        request.AddParems("type", type);//提醒类型-必须（1:提醒接受预约,2:提醒确认拍摄结束,3:提醒确认收货）
        post(request, listener, clazz);

    }

    //获取各种取消原因
    public static <T> void GetReasonList(String type, IHttpResultListener<T> listener, Class<T> clazz) {
        //类型-必须（1：退款原因，2：取消原因+拒绝接受原因，4：拒绝退款原因）
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetReasonList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", type);//类型-必须（1：退款原因，2：取消原因+拒绝接受原因，4：拒绝退款原因）
        post(request, listener, clazz);

    }

    //取消预约或者取消订单
    public static <T> void cancleOrder(String log_id, String cancel_reason, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("Cancel");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        request.AddParems("cancel_reason", cancel_reason);
        post(request, listener, clazz);

    }

    //删除订单
    public static <T> void delete(String log_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("Delete");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        post(request, listener, clazz);

    }

    //编辑订单
    public static <T> void editeOrder(String log_id, String status, String cancel_reason, String refusal_reason, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("EditStatus");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        request.AddParems("status", status);//状态-必须（1：同意预约,2：拒绝预约,3：取消订单,4：确认拍摄结束,5：确认收货,6：同意退款，7：拒绝退款）
        request.addParamsNotEmpty("cancel_reason", cancel_reason);//取消原因ID-非必须（status = 3时必须）
        request.addParamsNotEmpty("refusal_reason", refusal_reason);//拒绝原因ID-非必须（status = 2时必须）
        post(request, listener, clazz);

    }

    //获取评价标签
    public static <T> void GetCommentLabel(String log_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCommentLabel");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        post(request, listener, clazz);

    }

    //添加评价
    public static <T> void addComment(String log_id, String rank_type, String label, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("AddComment");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        request.AddParems("rank_type", rank_type);
        request.AddParems("label", label);
        post(request, listener, clazz);

    }

    //评价详情
    public static <T> void GetCommentDetail(String comment_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCommentDetail");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("comment_id", comment_id);
        post(request, listener, clazz);

    }

    //评价详情
    public static <T> void changeCount(String cart_id, String quantity, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CartEdit");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("cart_id", cart_id);
        request.AddParems("quantity", quantity);
        post(request, listener, clazz);

    }

    //更新播放次数
    public static <T> void AppendPlayinfo(String case_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("AppendPlayinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("case_id", case_id);
        post(request, listener, clazz);

    }

    //获取工作室id
    public static <T> void GetStoreid(String userid, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetStoreid");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("userid", userid);
        post(request, listener, clazz);

    }

    //获取认证信息
    public static <T> void GetAuthInfo(String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetAuthInfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", type);
        post(request, listener, clazz);

    }

    //个人认证
    public static <T> void PersonNalAuth(
            String real_name, String idcard,
            String address, String tel, String linkname, String tel_bak,
            String email, String attachment,
            IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Apply");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "1");

        request.AddParems("realname", real_name);
        request.AddParems("idcard", idcard);

        request.AddParems("address", address);
        request.AddParems("phone", tel);
        request.AddParems("linkname", linkname);
        request.AddParems("linkphone", tel_bak);

        request.AddParems("email", email);
        request.AddParems("id_attachment", attachment);
        post(request, listener, clazz);

    }

    //企业认证
    public static <T> void BusinessNalAuth(String compny_name, String realname, String phone, String idcard,
                                           String credit_code, String address,
                                           String linkname, String tel_bak, String email, String compny_type,
                                           String id_attachment, String au_attachment,
                                           IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("Apply");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", "2");
        request.AddParems("compny_name", compny_name);
        request.AddParems("realname", realname);
        request.AddParems("phone", phone);
        request.AddParems("idcard", idcard);
        request.AddParems("credit_code", credit_code);
        request.AddParems("address", address);
        request.AddParems("linkname", linkname);
        request.AddParems("linkphone", tel_bak);
        request.AddParems("email", email);
        request.AddParems("compny_type", compny_type);
        request.AddParems("id_attachment", id_attachment);
        request.AddParems("au_attachment", au_attachment);
        post(request, listener, clazz);

    }


}
