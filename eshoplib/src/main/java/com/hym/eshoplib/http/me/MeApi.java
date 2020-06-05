package com.hym.eshoplib.http.me;

import android.text.TextUtils;

import com.hym.httplib.interfaces.IHttpResultListener;

import java.io.File;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/2/24.
 * <p>
 * Description 个人信息api
 * <p>
 * otherTips
 */

public class MeApi {

    //获取用户信息
    public static <T>void getUserinfo(String userid,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetUserinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("userid", userid);
        post(request,listener,clazz);

    }

    //获取我的收藏房源
    public static <T>void getCollectHouse(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("type","house");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的收藏房源
    public static <T>void getCollectAriticle(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("type","activity");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的收藏商品
    public static <T>void getCollectGoods(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("type","goods");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的收藏房源
    public static <T>void getCollectUsedGoods(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("type","bbs");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的收藏代办
    public static <T>void getCollectAgency(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("type","agency");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的租房意向
    public static <T>void getHouseIntentionList(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("House");
        request.setClassName("UserReleaseList");
        request.AddParems("type","bbs");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的代办意向
    public static <T>void getAgencyIntentionList(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Agency");
        request.setClassName("GetUserList");
        request.AddParems("type","bbs");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取我的发布
    public static <T>void getPublishList(String p, String type, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Bbs");
        request.setClassName("GetUserList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("type",type);//状态值-非必须(1：上架，2：下架，3：售罄)
        request.AddParems("mine","1");
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //更新头像

    public static <T>void updateUserHeadIcon(File[] files, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("UpdateAvatar");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.setFilesKey("avatar");
        request.setFiles(files);
        post(request,listener,clazz);
    }
    /**
     * 修改用户信息
     * @param nickname
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void updateUserInfo(String nickname, String gender, String phone, String age, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("UpdateInfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        if(!TextUtils.isEmpty(nickname)){
            request.AddParems("nickname",nickname);
        }
        if(!TextUtils.isEmpty(gender)){
            request.AddParems("gender",gender);
        }
        if(!TextUtils.isEmpty(phone)){
            request.AddParems("phone",phone);
        }
        if(!TextUtils.isEmpty(age)){
            request.AddParems("age",age);
        }
        post(request,listener,clazz);

    }

    //我要开店
    public static <T>void setupShop(
            String company_name, String name, String phone, String addr,
            String email, String business_category, String goods_cate, String memo,
            String attachmentid,
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("SetupShop");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("company_name",company_name);
        request.addParamsNotEmpty("name",name);
        request.addParamsNotEmpty("phone",phone);
        request.addParamsNotEmpty("addr",addr);
        request.addParamsNotEmpty("email",email);
        request.addParamsNotEmpty("business_category",business_category);
        request.addParamsNotEmpty("goods_cate",goods_cate);
        request.addParamsNotEmpty("memo",memo);
        request.addParamsNotEmpty("attachmentid",attachmentid);
        post(request,listener,clazz);

    }
    //承接业务
    public static <T>void business(
            String name, String phone, String addr,
            String email, String cooperation_memo, String region_id, String company, String company_intro,
            String memo, String attachmentid,
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("Business");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("name",name);
        request.addParamsNotEmpty("phone",phone);
        request.addParamsNotEmpty("addr",addr);
        request.addParamsNotEmpty("email",email);
        request.addParamsNotEmpty("cooperation_memo",cooperation_memo);
        request.addParamsNotEmpty("region_id",region_id);
        request.addParamsNotEmpty("company",company);
        request.addParamsNotEmpty("company_intro",company_intro);
        request.addParamsNotEmpty("memo",memo);
        request.addParamsNotEmpty("attachmentid",attachmentid);
        post(request,listener,clazz);
    }
    //其他合作
    public static <T>void other(
            String name, String phone, String addr,
            String email, String cooperation_memo, String region_id, String company, String company_intro,
            String memo, String attachmentid,
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("Cooperation");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("name",name);
        request.addParamsNotEmpty("phone",phone);
        request.addParamsNotEmpty("addr",addr);
        request.addParamsNotEmpty("email",email);
        request.addParamsNotEmpty("summary",cooperation_memo);
        request.addParamsNotEmpty("region_id",region_id);
        request.addParamsNotEmpty("company",company);
        request.addParamsNotEmpty("company_intro",company_intro);
        request.addParamsNotEmpty("memo",memo);
        request.addParamsNotEmpty("attachmentid",attachmentid);
        post(request,listener,clazz);
    }
    //其他合作
    public static <T>void house(
            String name, String phone, String address, String email, String title,
            String category_id, String area, String layout_id, String renovation,
            String conf, String price,
            String memo, String attachmentid,
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("House");
        request.setClassName("Resources");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("name",name);
        request.addParamsNotEmpty("phone",phone);
        request.addParamsNotEmpty("address",address);
        request.addParamsNotEmpty("email",email);
        request.addParamsNotEmpty("title",title);

        request.addParamsNotEmpty("category_id",category_id);
        request.addParamsNotEmpty("area",area);
        request.addParamsNotEmpty("layout_id",layout_id);
        request.addParamsNotEmpty("renovation",renovation);

        request.addParamsNotEmpty("conf",conf);
        request.addParamsNotEmpty("price",price);
        request.addParamsNotEmpty("memo",memo);
        request.addParamsNotEmpty("attachmentid",attachmentid);
        post(request,listener,clazz);
    }

    //添加增票资质
    public static <T>void AddAptitude(
            String company, String taxpayer_number, String registered_address,
            String tel, String bank, String bank_account, String attachmentid,
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("AddAptitude");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("company",company);
        request.addParamsNotEmpty("taxpayer_number",taxpayer_number);
        request.addParamsNotEmpty("registered_address",registered_address);
        request.addParamsNotEmpty("tel",tel);
        request.addParamsNotEmpty("bank",bank);
        request.addParamsNotEmpty("bank_account",bank_account);
        request.addParamsNotEmpty("attachment_id",attachmentid);
        post(request,listener,clazz);
    }
    //获取增票资质
    public static <T>void GetAptitude(
            IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("GetAptitude");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);
    }

    //我的活动
    public static <T>void getCollectGoods(String status, String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetUserList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("is_mine","1");
        request.AddParems("status",status);
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取余额流水
    public static <T>void CustomList(String status, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Bts");
        request.setClassName("CustList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("status",status);
        post(request,listener,clazz);
    }
    //退货原因
    public static <T>void GetReason(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Goods");
        request.setClassName("GetReason");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);
    }
    //申请退货
    public static <T>void CustApply(String item_id, String reason_id, String memo, String attachment, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CustApply");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("item_id",item_id);
        request.AddParems("reason_id",reason_id);
        request.addParamsNotEmpty("memo",memo);
        request.AddParems("attachment",attachment);
        post(request,listener,clazz);

    }
    //用户反馈
    public static <T>void FeedBack(String content, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("FeedBack");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type","1");
        request.AddParems("content",content);
        post(request,listener,clazz);

    }
    //获取收藏的工作室
    public static <T>void getCollectShop( String p,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type","store");
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //获取收藏的产品
    public static <T>void getCollectProduct( String p,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetFavorites");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type","case");
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //验证是否设置了支付密码
    public static <T>void IssetPaypass( IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("IssetPaypass");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }
    //验证支付密码验证码
    public static <T>void CheckVerify(String phone,String verify, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("CheckVerify");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("phone",phone);
        request.AddParems("verify",verify);
        post(request,listener,clazz);

    }
    //设置/修改/忘记支付密码
    public static <T>void SetPaypassword(String password,String repassword, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("SetPaypassword");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("password",password);
        request.AddParems("repassword",repassword);
        post(request,listener,clazz);

    }
    //验证旧支付密码
    public static <T>void CheckPaypass(String password, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("CheckPaypass");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("password", password);
        post(request,listener,clazz);

    }
    //获取邀请列表
    public static <T>void GetInviteList(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("GetInviteList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);
    }
    //填写邀请码
    public static <T>void AddInviter(String invitecode,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("AddInviter");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("invitecode", invitecode);
        post(request,listener,clazz);
    }
    //获取余额流水
    public static <T>void BeanList(String type,String p,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Bts");
        request.setClassName("BeanList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        if(!type.equals("0")){
            request.AddParems("type", type);
        }
        request.AddParems("p", p);
        request.AddParems("psize", "10");
        post(request,listener,clazz);
    }
    //生成充值订单
    public static <T>void ScoreUpdate(String score_num,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();;
        request.setApp("Cas");
        request.setClassName("ScoreUpdate");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("score_num", score_num);
        post(request,listener,clazz);
    }
    //获取明细详情
    public static <T>void BeanDetail(String log_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("BeanDetail");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("log_id", log_id);
        post(request,listener,clazz);

    }
    //获取用户提现账户
    public static <T>void GetBank(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetBank");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }
    //添加提现账户
    public static <T>void AddBank(String type,String bank,String account,String real_name,String phone,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddBank");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("type", type);
        request.AddParems("bank", bank);
        request.AddParems("account", account);
        request.AddParems("real_name", real_name);
        request.AddParems("phone", phone);
        post(request,listener,clazz);

    }
    //修改提现账户
    public static <T>void EditBank(String userbank_id,String bank,String account,String real_name,String phone,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("EditBank");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("userbank_id", userbank_id);
        request.AddParems("bank", bank);
        request.AddParems("account", account);
        request.AddParems("real_name", real_name);
        request.AddParems("phone", phone);
        post(request,listener,clazz);

    }
    //
    public static <T>void WithdrawsCash(String userbank_id,String amount,String paypassword,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("WithdrawsCash");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("userbank_id", userbank_id);
        request.AddParems("amount", amount);
        request.AddParems("paypassword", paypassword);
        post(request,listener,clazz);

    }
    //判断是否已绑定微信
    public static <T>void OtherInfo(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("OtherInfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }
    //修改手机号
    public static <T>void ResetPhone(String phone,String verify,String password,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ResetPhone");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("phone", phone);
        request.AddParems("verify", verify);
        request.AddParems("password", password);
        post(request,listener,clazz);

    }
    //删除提现账号
    public static <T>void DelBank(String userbank_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("DelBank");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("userbank_id", userbank_id);
        post(request,listener,clazz);

    }
    //提现 费率
    public static <T>void PoundageInfo(String amount,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request= HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("PoundageInfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("amount", amount);
        post(request,listener,clazz);

    }



}



