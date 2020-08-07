package cn.hym.superlib.mz.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cn.hym.superlib.R;

public class MzShopDetailTitleView extends LinearLayout {


    private String detailTitle;
    private int titleTextSize = 14;

    public MzShopDetailTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MzShopDetailTitleView);

        View rootView = LayoutInflater.from(context).inflate(R.layout.mz_shop_detail_title_view, this, true);
        TextView titleTv = rootView.findViewById(R.id.title);

        detailTitle = ta.getString(R.styleable.MzShopDetailTitleView_detailTitle);
        titleTextSize = ta.getDimensionPixelSize(R.styleable.MzShopDetailTitleView_title_textSize, titleTextSize);

        if (!TextUtils.isEmpty(detailTitle)) {
            titleTv.setText(detailTitle);
        }

        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);

        ta.recycle();
    }


}
