package cn.hym.superlib.mz.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hym.superlib.R;


/**
 * 自定义一个简单顶部栏
 */
public class MzHeaderBar extends FrameLayout {

    private LinearLayout back;
    private TextView tvTitle;
    private LinearLayout lRightTv;
    private TextView tvRight;
    private LinearLayout lRightIv;
    private ImageView rightIv;
    private int rightDrawableId = -1;

    public MzHeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MzHeaderBar);


        View root = LayoutInflater.from(context).inflate(R.layout.mz_layout_header_bar, this, true);

        boolean showBack = ta.getBoolean(R.styleable.MzHeaderBar_showBack, true);
        String title = ta.getString(R.styleable.MzHeaderBar_header_title);
        String rightTv = ta.getString(R.styleable.MzHeaderBar_rightTv);
        boolean showRightTv = ta.getBoolean(R.styleable.MzHeaderBar_showRightTv, true);
        rightDrawableId = ta.getResourceId(R.styleable.MzHeaderBar_rightIvDrawable, rightDrawableId);
        // boolean showRightIv = ta.getBoolean(R.styleable.MzHeaderBar_showRightTv, false);

        back = root.findViewById(R.id.back);
        tvTitle = root.findViewById(R.id.title);
        lRightTv = root.findViewById(R.id.l_rightTv);
        tvRight = root.findViewById(R.id.rightTv);
        rightIv = root.findViewById(R.id.rightIv);
        lRightIv = root.findViewById(R.id.l_rightIv);

        back.setVisibility(showBack ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(rightTv)) {
            tvRight.setText(rightTv);
        }

        // rightIv.setVisibility(showRightIv ? View.VISIBLE : View.GONE);

        if (rightDrawableId != -1) {
            rightIv.setVisibility(VISIBLE);
            rightIv.setImageDrawable(getResources().getDrawable(rightDrawableId));
        } else {
            rightIv.setVisibility(GONE);
        }


        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerBarListener != null) {
                    headerBarListener.back();
                }
            }
        });


        lRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerBarListener != null) {

                    headerBarListener.rightButton();
                }
            }
        });

        lRightIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rightDrawableListener != null) {
                    rightDrawableListener.onRightDrawableClick();
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

    public interface RightDrawableListener {
        void onRightDrawableClick();
    }

    private HeaderBarListener headerBarListener;

    private RightDrawableListener rightDrawableListener;

    public void setHeaderBarListener(HeaderBarListener headerBarListener) {
        this.headerBarListener = headerBarListener;
    }

    public void setRightDrawableListener(RightDrawableListener rightDrawableListener) {
        this.rightDrawableListener = rightDrawableListener;
    }

}
