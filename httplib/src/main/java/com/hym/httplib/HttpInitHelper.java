package com.hym.httplib;

import android.content.Context;

import com.hym.httplib.interfaces.IHttpManager;

/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class HttpInitHelper {
    public static void init(Context context, IHttpManager iHttpManager,boolean debug){
        if(iHttpManager!=null){
            HttpManager.getInstance().init(context,iHttpManager,debug);
        }

    }
}
