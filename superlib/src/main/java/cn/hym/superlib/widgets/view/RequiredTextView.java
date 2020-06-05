package cn.hym.superlib.widgets.view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import cn.hym.superlib.R;

/**
 * Created by 胡彦明 on 2018/2/25.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class RequiredTextView extends AppCompatTextView{

    boolean isRequird=true;
    public RequiredTextView(Context context) {
        super(context);
        init(context,null);
    }

    public RequiredTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public RequiredTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs) {
        /**
         * 如果在xml中使用 必须使用 app:requiredText 属性设置text
         */
        setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RequiredTextView);
        String mText = ta.getString(R.styleable.RequiredTextView_requiredText);
        isRequird= ta.getBoolean(R.styleable.RequiredTextView_required, true);
        setText(mText,BufferType.SPANNABLE);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
         if(isRequird){
             String result=text+"*";
             SpannableString spannableString = new SpannableString(result);
             ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.jiuzhou_orange));
             spannableString.setSpan(colorSpan, result.length()-1, result.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
             super.setText(spannableString, type);
         }else {
             super.setText(text, type);
         }
    }

    public boolean isRequird() {
        return isRequird;
    }

    public void setRequird(boolean requird) {
        isRequird = requird;
    }
}
