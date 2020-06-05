package com.hym.eshoplib.util;

import android.graphics.RectF;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;

/**
 * Created by 胡彦明 on 2019/9/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class OnBottomPosCallback2 extends OnBaseCallback {
    public OnBottomPosCallback2() {
    }

    public OnBottomPosCallback2(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.rightMargin = rightMargin+rightMargin/2;
        marginInfo.topMargin = rectF.top + rectF.height()+offset;
    }
}
