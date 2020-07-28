package cn.hym.superlib.mz.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hym.superlib.R;


/**
 * 自定义一个简单顶部栏
 */
public class MzHeaderBar extends FrameLayout {


    private LinearLayout back;
    private TextView tvTitle;
    private TextView tvRight;

    public MzHeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MzHeaderBar);


        View root = LayoutInflater.from(context).inflate(R.layout.mz_layout_header_bar, this, true);

        boolean showBack = ta.getBoolean(R.styleable.MzHeaderBar_showBack, true);
        String title = ta.getString(R.styleable.MzHeaderBar_header_title);
        String rightTv = ta.getString(R.styleable.MzHeaderBar_rightTv);
        boolean showRight = ta.getBoolean(R.styleable.MzHeaderBar_showRightTv, true);

        back = root.findViewById(R.id.back);
        tvTitle = root.findViewById(R.id.title);
        tvRight = root.findViewById(R.id.rightTv);

        back.setVisibility(showBack ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(rightTv)) {
            tvRight.setText(rightTv);
        }


        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerBarListener != null) {
                    headerBarListener.back();
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerBarListener != null) {

                    headerBarListener.rightButton();
                }
            }
        });


        ta.recycle();

    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }


    public interface HeaderBarListener {
        void back();

        void rightButton();
    }

    private HeaderBarListener headerBarListener;

    public void setHeaderBarListener(HeaderBarListener headerBarListener) {
        this.headerBarListener = headerBarListener;
    }
}
