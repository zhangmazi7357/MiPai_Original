package cn.hym.superlib.widgets.view;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by 胡彦明 on 2018/3/17.
 * <p>
 * Description mapView容器解决scrollviewc冲突
 * <p>
 * otherTips
 */

public class MapContainerView extends FrameLayout{
    private ScrollView scrollView;
    public MapContainerView(@NonNull Context context) {
        super(context);
    }

    public MapContainerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MapContainerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            scrollView.requestDisallowInterceptTouchEvent(false);
        } else {
            scrollView.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


}
