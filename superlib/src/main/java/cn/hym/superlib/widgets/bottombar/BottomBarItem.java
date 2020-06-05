package cn.hym.superlib.widgets.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hym.superlib.R;
import cn.hym.superlib.utils.view.ScreenUtil;


/**
 * @author ChayChan
 * @description: 底部tab条目
 * @date 2017/6/23  9:14
 */

public class BottomBarItem extends LinearLayout {

    private Context mContext;
    private int mIconNormalResourceId;//普通状态图标的资源id
    private int mIconSelectedResourceId;//选中状态图标的资源id
    private String mText;//文本
    private int mTextSize = 12;//文字大小 默认为12sp
    private int mTextColorNormal = 0xFF999999;    //描述文本的默认显示颜色
    private int mTextColorSelected = 0xFF46C01B;  //述文本的默认选中显示颜色
    private int mMarginTop = 0;//文字和图标的距离,默认0dp
    private boolean mOpenTouchBg = false;// 是否开启触摸背景，默认关闭
    private Drawable mTouchDrawable;//触摸时的背景
    private int mIconWidth;//图标的宽度
    private int mIconHeight;//图标的高度
    private int mItemPadding;//BottomBarItem的padding


    private ImageView mImageView;
    private TextView mTvUnread;
    private TextView mTvNotify;
    private TextView mTvMsg;
    private TextView mTextView;

    private int mUnreadTextSize = 10; //未读数默认字体大小10sp
    private int mMsgTextSize = 6; //消息默认字体大小6sp
    private int unreadNumThreshold = 999;

    private View mRootView;//bottombaritem的根view

    public BottomBarItem(Context context) {
        this(context, null);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItem);

        mIconNormalResourceId = ta.getResourceId(R.styleable.BottomBarItem_iconNormal, -1);
        mIconSelectedResourceId = ta.getResourceId(R.styleable.BottomBarItem_iconSelected, -1);

        mText = ta.getString(R.styleable.BottomBarItem_itemText);
        mTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemTextSize, ScreenUtil.sp2px(mContext, mTextSize));

        mTextColorNormal = ta.getColor(R.styleable.BottomBarItem_textColorNormal, mTextColorNormal);
        mTextColorSelected = ta.getColor(R.styleable.BottomBarItem_textColorSelected, mTextColorSelected);

        mMarginTop = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemMarginTop, ScreenUtil.dip2px(mContext, mMarginTop));

        mOpenTouchBg = ta.getBoolean(R.styleable.BottomBarItem_openTouchBg, mOpenTouchBg);
        mTouchDrawable = ta.getDrawable(R.styleable.BottomBarItem_touchDrawable);

        mIconWidth = ta.getDimensionPixelSize(R.styleable.BottomBarItem_iconWidth, 0);
        mIconHeight = ta.getDimensionPixelSize(R.styleable.BottomBarItem_iconHeight, 0);
        mItemPadding = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemPadding, 0);

        mUnreadTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_unreadTextSize, ScreenUtil.sp2px(mContext, mUnreadTextSize));
        mMsgTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_msgTextSize, ScreenUtil.sp2px(mContext, mMsgTextSize));
        unreadNumThreshold = ta.getInteger(R.styleable.BottomBarItem_unreadThreshold,99);
        ta.recycle();
        //checkvalue()
        init();
    }
    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        mRootView = View.inflate(mContext, R.layout.item_bottom_bar, null);
        if (mItemPadding != 0) {
            //如果有设置item的padding
            mRootView.setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);
        }
        mImageView = (ImageView)  mRootView.findViewById(R.id.iv_icon);
        mTvUnread = (TextView)  mRootView.findViewById(R.id.tv_unred_num);
        mTvMsg = (TextView)  mRootView.findViewById(R.id.tv_msg);
        mTvNotify = (TextView)  mRootView.findViewById(R.id.tv_point);
        mTextView = (TextView)  mRootView.findViewById(R.id.tv_text);
        if(mIconNormalResourceId!=-1){
            mImageView.setImageResource(mIconNormalResourceId);
        }
        if (mIconWidth != 0 && mIconHeight != 0) {
            //如果有设置图标的宽度和高度，则设置ImageView的宽高
            FrameLayout.LayoutParams imageLayoutParams = (FrameLayout.LayoutParams) mImageView.getLayoutParams();
            imageLayoutParams.width = mIconWidth;
            imageLayoutParams.height = mIconHeight;
            mImageView.setLayoutParams(imageLayoutParams);
        }

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);//设置底部文字字体大小
        mTvUnread.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnreadTextSize);//设置未读数的字体大小
        mTvMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMsgTextSize);//设置提示文字的字体大小

        mTextView.setTextColor(mTextColorNormal);//设置底部文字字体颜色
        mTextView.setText(mText);//设置标签文字

        LayoutParams textLayoutParams = (LayoutParams) mTextView.getLayoutParams();
        textLayoutParams.topMargin = mMarginTop;
        mTextView.setLayoutParams(textLayoutParams);

        if (mOpenTouchBg) {
            //如果有开启触摸背景
            setBackground(mTouchDrawable);
        }

        addView( mRootView);
    }
    //根据设置重置属性
    private void reSetArg(){
        if (mItemPadding != 0) {
            //如果有设置item的padding
            mRootView.setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);
        }
        if(mIconNormalResourceId!=-1){
            mImageView.setImageResource(mIconNormalResourceId);
        }
        if (mIconWidth != 0 && mIconHeight != 0) {
            //如果有设置图标的宽度和高度，则设置ImageView的宽高
            FrameLayout.LayoutParams imageLayoutParams = (FrameLayout.LayoutParams) mImageView.getLayoutParams();
            imageLayoutParams.width = mIconWidth;
            imageLayoutParams.height = mIconHeight;
            mImageView.setLayoutParams(imageLayoutParams);
        }

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);//设置底部文字字体大小
        mTvUnread.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnreadTextSize);//设置未读数的字体大小
        mTvMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMsgTextSize);//设置提示文字的字体大小

        mTextView.setTextColor(mTextColorNormal);//设置底部文字字体颜色
        mTextView.setText(mText);//设置标签文字

        LayoutParams textLayoutParams = (LayoutParams) mTextView.getLayoutParams();
        textLayoutParams.topMargin = mMarginTop;
        mTextView.setLayoutParams(textLayoutParams);

        if (mOpenTouchBg) {
            //如果有开启触摸背景
            setBackground(mTouchDrawable);
        }

    }


    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }


    public void setStatus(boolean isSelected) {
        mImageView.setImageResource(isSelected ? mIconSelectedResourceId : mIconNormalResourceId);
        mTextView.setTextColor(isSelected ? mTextColorSelected : mTextColorNormal);
    }

    private void setTvVisiable(TextView tv) {
        //都设置为不可见
        mTvUnread.setVisibility(GONE);
        mTvMsg.setVisibility(GONE);
        mTvNotify.setVisibility(GONE);
        tv.setVisibility(VISIBLE);//设置为可见
    }


    /**
     * 设置未读数
     *
     * @param unreadNum 小于等于{@link cn.hym.superlib.widgets.bottombar.BottomBarItem#unreadNumThreshold}则隐藏，
     *                  大于0小于{@link cn.hym.superlib.widgets.bottombar.BottomBarItem#unreadNumThreshold}则显示对应数字，
     *                  超过{@link cn.hym.superlib.widgets.bottombar.BottomBarItem#unreadNumThreshold}
     *                  显示{@link cn.hym.superlib.widgets.bottombar.BottomBarItem#unreadNumThreshold}+
     */
    public void setUnreadNum(int unreadNum) {
        setTvVisiable(mTvUnread);
        if (unreadNum <= 0) {
            mTvUnread.setVisibility(GONE);
        } else if (unreadNum <= unreadNumThreshold) {
            mTvUnread.setText(String.valueOf(unreadNum));
        } else {
           // mTvUnread.setText(String.format(Locale.CHINA, "%d+", unreadNum));
            mTvUnread.setText("99+");
        }
    }

    public void setMsg(String msg) {
        setTvVisiable(mTvMsg);
        mTvMsg.setText(msg);
    }

    public void hideMsg() {
        mTvMsg.setVisibility(GONE);
    }

    public void showNotify() {
        setTvVisiable(mTvNotify);
    }

    public void hideNotify() {
        mTvNotify.setVisibility(GONE);
    }

    public void setmIconNormalResourceId(int mIconNormalResourceId) {
        this.mIconNormalResourceId = mIconNormalResourceId;
        reSetArg();
}

    public void setmIconSelectedResourceId(int mIconSelectedResourceId) {
        this.mIconSelectedResourceId = mIconSelectedResourceId;
        reSetArg();
    }

    public void setmText(String mText) {
        this.mText = mText;
        reSetArg();
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        reSetArg();
    }

    public void setmTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
        reSetArg();
    }

    public void setmTextColorSelected(int mTextColorSelected) {
        this.mTextColorSelected = mTextColorSelected;
        reSetArg();
    }

    public void setmMarginTop(int mMarginTop) {
        this.mMarginTop = mMarginTop;
        reSetArg();
    }

    public void setmOpenTouchBg(boolean mOpenTouchBg) {
        this.mOpenTouchBg = mOpenTouchBg;
        reSetArg();
    }

    public void setmTouchDrawable(Drawable mTouchDrawable) {
        this.mTouchDrawable = mTouchDrawable;
        reSetArg();
    }

    public void setmIconWidth(int mIconWidth) {
        this.mIconWidth = mIconWidth;
        reSetArg();
    }

    public void setmIconHeight(int mIconHeight) {
        this.mIconHeight = mIconHeight;
        reSetArg();
    }

    public void setmItemPadding(int mItemPadding) {
        this.mItemPadding = mItemPadding;
        reSetArg();
    }

    public void setmUnreadTextSize(int mUnreadTextSize) {
        this.mUnreadTextSize = mUnreadTextSize;
        reSetArg();
    }

    public void setmMsgTextSize(int mMsgTextSize) {
        this.mMsgTextSize = mMsgTextSize;
        reSetArg();
    }

    public void setUnreadNumThreshold(int unreadNumThreshold) {
        this.unreadNumThreshold = unreadNumThreshold;
        reSetArg();
    }

    @Override
    public String toString() {
        return "BottomBarItem{" +
                "mIconNormalResourceId=" + mIconNormalResourceId +
                ", mIconSelectedResourceId=" + mIconSelectedResourceId +
                ", mText='" + mText + '\'' +
                ", mTextColorNormal=" + mTextColorNormal +
                ", mTextColorSelected=" + mTextColorSelected +
                '}';
    }
}
