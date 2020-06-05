package cn.hym.superlib.widgets.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.hym.superlib.R;


/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description 可以点击删除已输入文本的 editext
 * <p>
 * OtherTips 使用
 *                  <com.sunfield.hymlibrary.widgets.view.ClearEditText
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content"
                      android:padding="10dp"
                      app:delete_drawable="@drawable/ic_et_delete" />
 */

public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener {

    Drawable deleteDrawable;
    boolean isShowing;
    int deleteWidth;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DeleteEditText);
            deleteDrawable = a.getDrawable(0);
            if(deleteDrawable==null){
                deleteDrawable= ContextCompat.getDrawable(getContext(),R.drawable.ic_et_delete);
            }
            deleteWidth = a.getDimensionPixelOffset(R.styleable.DeleteEditText_delete_width, deleteDrawable.getMinimumWidth());
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });

        setOnFocusChangeListener(this);
    }

    private void setDrawable() {
        //  绘制删除按钮
        if (getText().toString().length() > 0 && isFocused()) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, deleteDrawable, null);
            isShowing = true;
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            isShowing = false;
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (deleteDrawable != null && isShowing) {
                int eventX = (int) event.getRawX();
                int eventY = (int) event.getRawY();
                Rect rect = new Rect();
                getGlobalVisibleRect(rect);
                rect.left = rect.right - deleteWidth - getPaddingRight();
                if (rect.contains(eventX, eventY)) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        setDrawable();
    }
}
