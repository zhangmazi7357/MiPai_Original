package cn.hym.superlib.mz.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * android:focusable=”true”
 * android:focusableInTouchMode=”true”
 * android:ellipsize=”marquee”
 * android:marqueeRepeatLimit=”marquee_forever”
 */
public class HorizontalTextView extends AppCompatTextView {
    public HorizontalTextView(Context context) {
        super(context);
    }

    public HorizontalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {//返回 true 始终有焦点
        return true;
//        return super.isFocused();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {//当有焦点的时候 开启动画  没有的时候 什么都不做保持状态
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }
}
