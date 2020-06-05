package com.hym.eshoplib.util;

import android.content.Context;

import io.rong.imkit.RongIM;

/**
 * Created by 胡彦明 on 2019/10/11.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ChatUtils {

    public static void ChatToCustomService(Context context,int position){
        //1 觅拍官方 老的
        //2 觅拍小觅新的

        switch (position){
            case 1:
                RongIM.getInstance().startPrivateChat(context, "2010", "觅拍客服");
                break;
            case 2:
                RongIM.getInstance().startPrivateChat(context, "3681", "觅拍\"小觅\"");
                break;

        }

    }
}
