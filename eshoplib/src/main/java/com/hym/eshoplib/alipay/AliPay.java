package com.hym.eshoplib.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈晖 on 2018/4/9.
 */

public class AliPay {

    private PayResultListener payResult;

    Activity activity;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private static final String APP_ID = "2018091261340223";

    public AliPay(Activity activity, PayResultListener payResult) {
        this.activity = activity;
        this.payResult = payResult;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (AliPay.this.payResult == null) {
                        return;
                    }
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        AliPay.this.payResult.paySuccess();
//                        Toast.makeText(AliPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        AliPay.this.payResult.payFail();
//                        Toast.makeText(AliPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 支付方法
     *
     * @param amount       订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param subject      商品的标题/交易标题/订单标题/订单关键字等
     * @param body         对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     * @param out_trade_no 商户网站唯一订单号
     * @param rsa2         RSA2密钥 建议使用支付宝提供的公私钥生成工具生成，
     *                     工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */

    public void pay(String amount, String subject, String body, String out_trade_no, String rsa2) {

        Map<String, String> params = buildOrderParamMap(APP_ID, amount, subject, body, out_trade_no, true);
        String orderParam = buildOrderParam(params);

        String privateKey = rsa2;
        String sign = getSign(params, privateKey, true);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void pay(final String payInfo) {

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(payInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 构造支付订单参数列表
     *
     * @param app_id
     * @param amount       订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param subject      商品的标题/交易标题/订单标题/订单关键字等
     * @param body         对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     * @param out_trade_no 商户网站唯一订单号
     * @param rsa2         是否用商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     * @return
     */
    public static Map<String, String> buildOrderParamMap(String app_id, String amount, String subject, String body, String out_trade_no, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("app_id", app_id);

        //业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
        keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":" + amount + ",\"subject\":" + subject + ",\"body\":" + body + ",\"out_trade_no\":\"" + out_trade_no + "\"}");

        keyValues.put("charset", "utf-8");

        //接口名称 alipay.trade.app.pay
        keyValues.put("method", "alipay.trade.app.pay");

        //商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
        keyValues.put("timestamp", "2016-07-29 16:55:53");

        //调用的接口版本，固定为：1.0
        keyValues.put("version", "1.0");

        return keyValues;
    }

    /**
     * 构造支付订单参数信息
     *
     * @param map 支付订单参数
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 对支付参数信息进行签名
     *
     * @param map 待签名授权信息
     * @return
     */
    public static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }

    public interface PayResultListener {
        void paySuccess();

        void payFail();
    }

}
