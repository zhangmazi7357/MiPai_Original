package com.hym.imagelib;

import com.hym.imagelib.interfaces.IImageLoader;

/**
 * Created by 胡彦明 on 2017/12/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ImageInitHelper {
    public static void init(IImageLoader iImageLoader){
        if(iImageLoader!=null){
            ImageUtil.getInstance().init(iImageLoader);
        }

    }
}
