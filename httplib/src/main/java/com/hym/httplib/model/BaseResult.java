package com.hym.httplib.model;

/**
 * Created by 胡彦明 on 2017/6/30.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public abstract class BaseResult {
    //是否成功
    public abstract boolean isSucceed();
    //是否是空数据
    public abstract boolean isEmpty();

}
