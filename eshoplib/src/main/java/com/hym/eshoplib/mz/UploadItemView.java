package com.hym.eshoplib.mz;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hym.eshoplib.R;

/**
 * 自定义一个用于上传图片和视频 item 的组合控件
 */
public class UploadItemView extends LinearLayout {

    private TextView tvTitle;
    private TextView etContent;
    private ImageView arrow;

    public UploadItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UploadItemView);


        View root = LayoutInflater.from(context).inflate(R.layout.layout_mz_upload_item_view, this, true);
        tvTitle = root.findViewById(R.id.tv_title);
        etContent = root.findViewById(R.id.et_content);
        arrow = root.findViewById(R.id.arrow);

        String title = ta.getString(R.styleable.UploadItemView_title);
        int titleColor = ta.getColor(R.styleable.UploadItemView_titleTextColor, getResources().getColor(R.color.black));
        String hint = ta.getString(R.styleable.UploadItemView_android_hint);
        int hintColor = ta.getColor(R.styleable.UploadItemView_android_textColorHint, getResources().getColor(R.color.gray));
        boolean showArrow = ta.getBoolean(R.styleable.UploadItemView_isShowArrow, false);


        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title.trim());
        }

        tvTitle.setTextColor(titleColor);

        if (!TextUtils.isEmpty(hint)) {
            etContent.setHint(hint);
        }
        etContent.setHintTextColor(hintColor);
        arrow.setVisibility(showArrow ? View.VISIBLE : View.GONE);

        ta.recycle();
    }

    /**
     * 设置内容
     *
     * @param title
     */
    public void setContent(String title) {
        if (!TextUtils.isEmpty(title)) {
            etContent.setText(title);
        }
    }


}
