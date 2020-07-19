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
    public static final int RESULT_CODE_UPLOAD_IMG = 111021;

    // 产品上传 图片 value
    public static final String VALUE_PRODUCT_SORT = "VALUE_PRODUCT_SORT";
    public static final String VALUE_SUB_PRODUCT_SORT = "VALUE_SUB_PRODUCT_SORT";
    public static final String VALUE_PRODUCT_TAG = "VALUE_PRODUCT_TAG";
}
