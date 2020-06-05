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

public class OnTopPosCallbac2 extends OnBaseCallback {


    public OnTopPosCallbac2() {

    }

    public OnTopPosCallbac2(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.leftMargin = rectF.right - rectF.width()-rectF.width();
        marginInfo.bottomMargin = bottomMargin+rectF.height()+offset;
    }
}
