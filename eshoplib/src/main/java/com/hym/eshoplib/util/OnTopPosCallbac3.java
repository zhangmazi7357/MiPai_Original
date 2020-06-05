package com.hym.eshoplib.util;

import android.graphics.RectF;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;

/**
 * Created by 胡彦明 on 2019/9/5.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OnTopPosCallbac3 extends OnBaseCallback {


    public OnTopPosCallbac3() {

    }

    public OnTopPosCallbac3(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.leftMargin = rectF.right - rectF.width();
        marginInfo.bottomMargin = bottomMargin+rectF.height()+offset;
    }
}
