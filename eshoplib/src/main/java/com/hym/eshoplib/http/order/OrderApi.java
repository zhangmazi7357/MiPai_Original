package com.hym.eshoplib.http.order;

import android.content.Context;
import android.text.TextUtils;

import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 订单
 * <p>
 * otherTips
 */

public class OrderApi {

    //计算订单价格，创建订单时

    /**
     * @param specification_id 规格
     * @param buy_num          数量
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void gettTotalForCreateOrder(Context context, String specification_id, String buy_num, String coupon_log_id,
                                                   IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetTotal");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("c_type", "1");//参数类型  1:立即购买，2:购物车-必须
        request.AddParems("specification_id", specification_id);//商品规格ID-非必需（c_type=1时必须）
        request.AddParems("buy_num", buy_num);//购买数量-非必需（c_type=1时必须）
        if (!TextUtils.isEmpty(coupon_log_id)) {
            request.AddParems("coupon_log_id", coupon_log_id);
        }
        post(request, listener, clazz);

    }
    //计算订单价格，购物车中

    /**
     * @param store_id 店铺ID-非必需（计算指定店铺购物车商品时必须）
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void gettTotalForShoppingCar(Context context, String store_id, String coupon_log_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetTotal");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("c_type", "2");//参数类型  1:立即购买，2:购物车-必须
        request.AddParems("store_id", store_id);
        if (!TextUtils.isEmpty(coupon_log_id)) {
            request.AddParems("coupon_log_id", coupon_log_id);
        }
        post(request, listener, clazz);

    }

    //获取用户余额
    public static <T> void getAsset(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetAsset");
        request.AddParems("token", UserUtil.getToken(context));
        post(request, listener, clazz);

    }

    //获取用户余额
    public static <T> void createOrder(Context context, String consignee_id,
                                       String c_type, String specification_id, String buy_num,
                                       String invoice, String is_return, String refund_usermsg,
                                       IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CreatOrder");
        request.AddParems("token", UserUtil.getToken(context));
        request.addParamsNotEmpty("consignee_id", consignee_id);
        request.addParamsNotEmpty("c_type", c_type);
        request.addParamsNotEmpty("specification_id", specification_id);
        request.addParamsNotEmpty("buy_num", buy_num);
        request.addParamsNotEmpty("invoice", invoice);
        request.addParamsNotEmpty("is_return", is_return);
        request.addParamsNotEmpty("refund_usermsg", refund_usermsg);
        post(request, listener, clazz);

    }

    //生成 会员充值订单
    public static <T> void createVipOrder(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("FastCreate");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("pay_account", "19.9");
        post(request, listener, clazz);

    }

    //生成详情页订单
    public static <T> void createDetailOrder(Context context, IHttpResultListener<T> listener, Class<T> clazz, String pay_account) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("FastCreate");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("pay_account", pay_account);
        post(request, listener, clazz);
    }

    //分享成为vip会员
    public static <T> void shareVipOrder(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ShareLink");
        request.AddParems("token", UserUtil.getToken(context));
        post(request, listener, clazz);

    }

    //微信支付签名
    public static <T> void WxPay(Context context, String child_order_number, String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("WxPay");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_number", child_order_number);
        request.AddParems("type", type);
        post(request, listener, clazz);

    }

    //支付宝支付
    public static <T> void aliPay(Context context, String child_order_number, String use_balance, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("AliPay");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_number", child_order_number);
        request.AddParems("use_balance", use_balance);
        post(request, listener, clazz);

    }

    //paypal
    public static <T> void PayPal(Context context, String child_order_number, String use_balance, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("PayPal");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_number", child_order_number);
        request.AddParems("use_balance", use_balance);
        post(request, listener, clazz);

    }

    //paypal回调
    public static <T> void PayPalResult(Context context, String child_order_number, String paymentId, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("PayPalNotify");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_number", child_order_number);
        request.addParamsNotEmpty("paymentId", paymentId);
        post(request, listener, clazz);

    }

    public static <T> void getOrderList(Context context, String orderstatus, String pay_status, String pageNum, IHttpResultListener<T> listener, Class<T> clazz) {
        //订单状态-非必需（
        // 订单状态   1 => '待订单审核',4 => '待发货确认',5 =>// '待收货确认',
        // 6 => '已完成',7 => '待评价',10 => '提交退单',11 =>
        // '待退单审核',12 => '待收货确认',13 => '待退款确认',14 => '待收款确认',15 => '售后已完成'）
        //支付状态-非必需（1:已支付，0:未支付）
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("ListOrder");
        request.AddParems("token", UserUtil.getToken(context));
        request.addParamsNotEmpty("orderstatus", orderstatus);
        request.addParamsNotEmpty("pay_status", pay_status);
        request.AddParems("psize", "10");
        if (!TextUtils.isEmpty(pageNum)) {
            request.AddParems("p", pageNum);
        }
        post(request, listener, clazz);

    }

    //订单详情
    public static <T> void DetailOrder(Context context, String order_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("DetailOrder");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("order_id", order_id);
        post(request, listener, clazz);

    }

    //确认收货
    public static <T> void ConfirmOrder(Context context, String order_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("StatusOrder");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("order_id", order_id);
        request.addParamsNotEmpty("type", "7");
        post(request, listener, clazz);

    }

    //取消订单
    public static <T> void CancelOrder(Context context, String child_order_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("Cancel");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_id", child_order_id);
        post(request, listener, clazz);

    }

    //物流详情
    public static <T> void GetExpress(Context context, String child_order_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetExpress");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("child_order_id", child_order_id);
        post(request, listener, clazz);

    }

    //获取增票资质
    public static <T> void GetAptitude(Context context, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        ;
        request.setApp("Cas");
        request.setClassName("GetAptitude");
        request.AddParems("token", UserUtil.getToken(context));
        post(request, listener, clazz);
    }

    //觅拍
    //获取用户订单列表
    public static <T> void getUserOrderList(String status, String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();

        request.setApp("Activity");
        request.setClassName("GetUserList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("status", status);//订单状态-非必须，默认全部（1:待接受预约,2:待付款,3:待确认收货(2+3),4:评价中心,5退款/售后）
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);
    }

    //支付宝支付
    public static <T> void aliPayMipai(String type, String child_order_number, String paystatus, String password, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("AliPay");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("child_order_number", child_order_number);
        request.AddParems("type", type);
        request.addParamsNotEmpty("paystatus", paystatus);//是否余额支付-非必须（3：是）
        request.addParamsNotEmpty("password", password);
        post(request, listener, clazz);
    }

    //获取订单详情
    public static <T> void UserContentDetail(String log_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("UserContentDetail");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("log_id", log_id);
        post(request, listener, clazz);
    }

    //修改订单价格
    public static <T> void ChangeTotal(String log_id, String money, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("ChangeTotal");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("money", money);
        request.AddParems("log_id", log_id);
        post(request, listener, clazz);
    }

    //申请退款
    public static <T> void CustApply(String log_id, String reason_id, String money, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        ;
        request.setApp("Activity");
        request.setClassName("CustApply");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("reason_id", reason_id);
        request.AddParems("money", money);
        request.AddParems("log_id", log_id);
        post(request, listener, clazz);
    }


}
