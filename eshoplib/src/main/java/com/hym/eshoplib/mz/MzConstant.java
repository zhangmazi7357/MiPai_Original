package com.hym.eshoplib.mz;

/**
 * 自定义一个常量类 ;
 */
public class MzConstant {


    // /////////////// 产品上传  图片 请求码
    public static final int REQUEST_CODE_UPLOAD_IMAGE = 1010;               //上传图片请求码

    public static final int REQUEST_CODE_PRODUCT_SORT = 1011;           // 一级产品分类
    public static final int REQUEST_CODE_SUB_PRODUCT_SORT = 1012;       // 二级产品分类
    public static final int REQUEST_CODE_PRODUCT_TAG = 1013;            // 产品标签
    public static final int REQUEST_CODE_LOCATION = 1014;               // 摄影棚位置 ;


    //    产品上传  图片 返回码
    public static final int RESULT_CODE_UPLOAD = 111021;

    // 产品上传 图片 value
    public static final String VALUE_PRODUCT_ONE = "VALUE_PRODUCT_ONE";
    public static final String VALUE_PRODUCT_TWO = "VALUE_PRODUCT_TWO";


    public static final String PRODUCT_SORT_ID = "PRODUCT_SORT_ID";
    public static final String VALUE_SUB_PRODUCT_SORT = "VALUE_SUB_PRODUCT_SORT";
    public static final String VALUE_PRODUCT_TAG = "VALUE_PRODUCT_TAG";
    public static final String VALUE_PRODUCT_LOCATION_ADDRESS = "VALUE_PRODUCT_LOCATION_ADDRESS";
    public static final String VALUE_PRODUCT_LOCATION_LAT = "VALUE_PRODUCT_LOCATION_LAT";
    public static final String VALUE_PRODUCT_LOCATION_LON = "VALUE_PRODUCT_LOCATION_LON";


    public static final String KEY_HOME_ICON_ID = "KEY_HOME_ICON_ID";
    public static final String KEY_HOME_ICON_PRODUCT = "KEY_HOME_ICON_PRODUCT";


    public static final String KEY_SEARCH_KEYWORD = "KEY_SEARCH_KEYWORD";


    // 订单 商品信息
    public static final String KEY_ORDER_CASE_ID = "KEY_ORDER_CASE_ID";
    // 订单 号
    public static final String KEY_ORDER_ID = "KEY_ORDER_ID";

    // 商品详情 评论
    public static final String KEY_DETAIL_COMMENT_CASE_ID = "KEY_DETAIL_COMMENT_CASE_ID";

    // 商品评论 分享
    public static final String KEY_COMMENT_SHARE = "KEY_COMMENT_SHARE";


    // 彩蛋标记
    public static final String KEY_COLOR_EGG = "KEY_COLOR_EGG";
}
