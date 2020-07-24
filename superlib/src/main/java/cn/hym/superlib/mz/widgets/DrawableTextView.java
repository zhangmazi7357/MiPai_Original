package cn.hym.superlib.mz.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import cn.hym.superlib.R;

/**
 * 自定义可控制图片尺寸的 TextView ;
 */
public class DrawableTextView extends AppCompatTextView {

    private int startWidth = 0;
    private int startHeight = 0;
    private int endWidth = 0;
    private int endHeight = 0;
    private int topWidth = 0;
    private int topHeight = 0;
    private int bottomWidth = 0;
    private int bottomHeight = 0;


    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView);

        startWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableStartWidth, 0);
        startHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableStartHeight, 0);
        endWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableEndWidth, 0);
        endHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableEndHeight, 0);
        topWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableTopWidth, 0);
        topHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableTopHeight, 0);
        bottomWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableBottomWidth, 0);
        bottomHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_drawableBottomHeight, 0);

        ta.recycle();

        setDrawablesSize();
    }


    private void setDrawablesSize() {
        Drawable[] compoundDrawables = getCompoundDrawables();

        for (int i = 0; i < compoundDrawables.length; i++) {
            switch (i) {
                case 0:
                    setDrawableBounds(compoundDrawables[0], startWidth, startHeight);
                    break;
                case 1:
                    setDrawableBounds(compoundDrawables[1], topWidth, topHeight);
                    break;
                case 2:
                    setDrawableBounds(compoundDrawables[2], endWidth, endHeight);
                    break;
                case 3:
                    setDrawableBounds(compoundDrawables[0], bottomWidth, bottomHeight);
                    break;
            }
        }

        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1],
                compoundDrawables[2], compoundDrawables[3]);
    }


    private void setDrawableBounds(Drawable drawable, int width, int height) {

        if (drawable != null) {
            drawable.setBounds(0, 0, width, height);

            if (width == 0 || height == 0) {
                double scale = (double) drawable.getIntrinsicHeight() / (double) drawable.getIntrinsicWidth();
                Rect bounds = drawable.getBounds();

                //高宽只给一个值时，自适应
                if (width == 0 && height != 0) {
                    bounds.right = (int) (bounds.bottom / scale);
                    drawable.setBounds(bounds);
                }
                if (width != 0 && height == 0) {
                    bounds.bottom = (int) (bounds.right * scale);
                    drawable.setBounds(bounds);
                }

            }
        }
    }


}
