package cn.hym.superlib.widgets;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerStop extends ViewPager {


    float x, y;

    public ViewPagerStop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerStop(Context context) {
        super(context);
    }
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                return super.onInterceptHoverEvent(event);
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - event.getX()) > Math.abs(y - event.getY()))
                    return true;
                else return false;
            case MotionEvent.ACTION_UP:
                return super.onInterceptHoverEvent(event);
        }
        return super.onInterceptHoverEvent(event);
    }


}