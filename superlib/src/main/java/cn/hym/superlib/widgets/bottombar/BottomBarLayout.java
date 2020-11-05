package cn.hym.superlib.widgets.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.R;

/**
 * @author 胡彦明
 * @description: 底部页签根节点
 * @date 2017/6/23  11:02
 */
public class BottomBarLayout extends LinearLayout implements ViewPager.OnPageChangeListener {

    private static final String STATE_INSTANCE = "instance_state";
    private static final String STATE_ITEM = "state_item";


    private ViewPager mViewPager;
    private int mChildCount;//子条目个数
    private List<BottomBarItem> mItemViews = new ArrayList<>();
    private int mCurrentItem;//当前条目的索引
    private boolean mSmoothScroll;

    public BottomBarLayout(Context context) {
        this(context, null);
    }

    public BottomBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    private onInteruptClick interuptClick;

    public void setInteruptClick(onInteruptClick interuptClick) {
        this.interuptClick = interuptClick;
    }

    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomBarLayout);
        mSmoothScroll = ta.getBoolean(R.styleable.BottomBarLayout_smoothScroll,true);
        ta.recycle();

    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }

    public void setViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        init();
    }

    private void init() {
        mChildCount = getChildCount();
        if (mViewPager!=null&&mViewPager.getAdapter().getCount() != mChildCount) {
            throw new IllegalArgumentException("LinearLayout的子View数量必须和ViewPager条目数量一致");
        }
        for (int i = 0; i < mChildCount; i++) {
            if (getChildAt(i) instanceof BottomBarItem) {
                BottomBarItem bottomBarItem = (BottomBarItem) getChildAt(i);
                mItemViews.add(bottomBarItem);
                //设置点击监听
                bottomBarItem.setOnClickListener(new MyOnClickListener(i));
            } else {
                throw new IllegalArgumentException("子View必须是BottomBarItem");
            }
        }
        if(mItemViews!=null&&!mItemViews.isEmpty()&&mItemViews.get(mCurrentItem)!=null){
            mItemViews.get(mCurrentItem).setStatus(true);//设置选中项
        }
        if(mViewPager!=null){
            mViewPager.addOnPageChangeListener(this);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetState();
        mItemViews.get(position).setStatus(true);
        if (onItemSelectedListener != null){
            onItemSelectedListener.onItemSelected(getBottomItem(position),position);
        }
        mCurrentItem = position;//记录当前位置

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyOnClickListener implements OnClickListener {

        private int currentIndex;

        public MyOnClickListener(int i) {
            this.currentIndex = i;
        }

        @Override
        public void onClick(View v) {
            if(interuptClick!=null){
                if(interuptClick.interupt(currentIndex)){
                    return ;
                }

            }
            resetState();
            mItemViews.get(currentIndex).setStatus(true);
            //回调点击的位置
            if(mViewPager!=null){
                if(mCurrentItem==currentIndex){
                    if(onItemSelectedListener!=null){
                        onItemSelectedListener.onItemReSelected(getBottomItem(currentIndex),currentIndex);
                    }
                }else {
                    mViewPager.setCurrentItem(currentIndex, mSmoothScroll);
                }

            }else {
                if (onItemSelectedListener != null ) {
                    if(mCurrentItem==currentIndex){
                        onItemSelectedListener.onItemReSelected(getBottomItem(currentIndex),currentIndex);
                    }else {
                        onItemSelectedListener.onItemSelected(getBottomItem(currentIndex),currentIndex);
                    }

                }
                mCurrentItem = currentIndex;//记录当前位置,如果vp不为空时，则由vp来记录
            }

        }
    }
    public interface  onInteruptClick{
        public boolean interupt(int index);
    }

    /**
     * 重置所有按钮的状态
     */
    private void resetState() {
        for (int i = 0; i < mChildCount; i++) {
            mItemViews.get(i).setStatus(false);
        }
    }

    public void setCurrentItem(int currentItem) {
        mCurrentItem = currentItem;
        if(mViewPager!=null){
            mViewPager.setCurrentItem(mCurrentItem,mSmoothScroll);
        }
    }

    /**
     * 设置未读数
     * @param position 底部标签的下标
     * @param unreadNum 未读数
     */
    public void setUnread(int position,int unreadNum){
        mItemViews.get(position).setUnreadNum(unreadNum);
    }

    public void  getUnRead(){

    }

    /**
     * 设置提示消息
     * @param position 底部标签的下标
     * @param msg 未读数
     */
    public void setMsg(int position,String msg){
        mItemViews.get(position).setMsg(msg);
    }

    /**
     * 隐藏提示消息
     * @param position 底部标签的下标
     */
    public void hideMsg(int position){
        mItemViews.get(position).hideMsg();
    }

    /**
     * 显示提示的小红点
     * @param position 底部标签的下标
     */
    public void showNotify(int position){
        mItemViews.get(position).showNotify();
    }

    /**
     * 隐藏提示的小红点
     * @param position 底部标签的下标
     */
    public void hideNotify(int position){
        mItemViews.get(position).hideNotify();
    }

    public int getCurrentItem() {
        return mCurrentItem;
    }

    public void setSmoothScroll(boolean mSmoothScroll) {
        this.mSmoothScroll = mSmoothScroll;
    }

    public BottomBarItem getBottomItem(int position){
        return mItemViews.get(position);
    }

    /**
     * @return 当View被销毁的时候，保存数据
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_ITEM, mCurrentItem);
        return bundle;
    }

    /**
     * @param state 用于恢复数据使用
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentItem = bundle.getInt(STATE_ITEM);
            //重置所有按钮状态
            resetState();
            //恢复点击的条目颜色
            mItemViews.get(mCurrentItem).setStatus(true);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    private OnItemSelectedListener onItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(BottomBarItem bottomBarItem, int position);
        void onItemReSelected(BottomBarItem bottomBarItem, int position);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
