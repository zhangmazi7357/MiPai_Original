package cn.hym.superlib.activity.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.hym.superlib.R;
import cn.hym.superlib.utils.common.DoubleClickHelper;
import cn.hym.superlib.utils.common.KeyBoardUtils;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.bottombar.BottomBarItem;
import cn.hym.superlib.widgets.bottombar.BottomBarLayout;
import me.yokeyword.fragmentation.SupportFragment;
//import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2018/1/9.
 * <p>
 * Description 用于简化制作主页面
 * <p>
 * otherTips
 */

public abstract class BaseMainActivity extends BasekitActivity
        implements BottomBarLayout.OnItemSelectedListener {

    public BottomBarLayout bottombar;
    private SupportFragment[] mFragments;
    private List<Class<? extends SupportFragment>> mFragmensClasses;
    private List<BottomBarItem> bottomBarItems;
    private int current_index = 0;//当前选中tab
    private static final String SAVED_KEY_TAB = "cunrrent_tab_index";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_base_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottombar = (BottomBarLayout) findViewById(R.id.bottombar);
        mFragments = getSupportFragments();
        mFragmensClasses = getClasses();
        if (mFragments == null || mFragmensClasses == null || mFragments.length != mFragmensClasses.size()) {
            throw new IllegalArgumentException("fragment集合大小必须与class集合大小相等");
        }
        bottomBarItems = getBottomItems();
        if (bottomBarItems == null || bottomBarItems.size() != mFragments.length) {
            throw new IllegalArgumentException("按钮数量必须与fragment数量相等");
        }
        if (mFragments.length == 0) {
            throw new IllegalArgumentException("fragments不能为空");
        }
        if (savedInstanceState == null) {
            loadMultipleRootFragment(R.id.fl_container, 0, mFragments);

        } else {
            //销毁时做恢复
            if (mFragments == null) {
                mFragments = new SupportFragment[mFragmensClasses.size()];
            }
            for (int i = 0; i < mFragmensClasses.size(); i++) {
                mFragments[i] = findFragment(mFragmensClasses.get(i));
            }
            //恢复销毁时状态
            current_index = savedInstanceState.getInt(SAVED_KEY_TAB, 0);
            Logger.d("销毁时做恢复 index=" + current_index + "==size=" + mFragments.length);
            Logger.d(mFragments);


        }
        bottombar.removeAllViews();
        for (BottomBarItem item : bottomBarItems) {
            bottombar.addView(item);
        }
//        if(bottombar!=null&& bottombar.getBottomItem(1)!=null){
//            bottombar.getBottomItem(1).setmUnreadTextSize(12);
//        }
        bottombar.setOnItemSelectedListener(this);
        bottombar.setViewPager(null);//此步是必须
        changeTab(current_index);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存当前tab用于恢复
        // Logger.d("意外销毁时进行保存");
        outState.putInt(SAVED_KEY_TAB, current_index);
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        //保存当前tab用于恢复
//        //Logger.d("意外销毁时进行保存");
//        outState.putInt(SAVED_KEY_TAB, current_index);
//        super.onSaveInstanceState(outState, outPersistentState);
//
//    }

    public abstract SupportFragment[] getSupportFragments();

    public abstract List<Class<? extends SupportFragment>> getClasses();

    public abstract List<BottomBarItem> getBottomItems();

    public BottomBarItem getDefaultBottomBarItem() {
        BottomBarItem item = new BottomBarItem(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        item.setLayoutParams(layoutParams);
        item.setmTextColorNormal(ContextCompat.getColor(this, R.color.mipaiTextColorNormal));
        item.setmTextColorSelected(ContextCompat.getColor(this, R.color.mipaiTextColorSelect));
        //item.setmMarginTop(ScreenUtil.dip2px(this,5));
        //item.setmOpenTouchBg(true);
        //item.setmTouchDrawable(ContextCompat.getDrawable(this,R.drawable.selector_bottombar_touchbg));
        return item;
    }

    @Override
    public void onItemSelected(BottomBarItem bottomBarItem, int position) {
        changeTab(position);

    }

    @Override
    public void onItemReSelected(BottomBarItem bottomBarItem, int position) {


    }


    //切换界面
    public void changeTab(int index) {
        // ToastUtil.toast("index="+index+"==current_index="+current_index);
        KeyBoardUtils.hideSoftInput(this, getWindow().getDecorView());
        showHideFragment(mFragments[index], mFragments[current_index]);
        current_index = index;//更新当前index

    }

    @Override
    public void onBackPressedSupport() {

        if (DoubleClickHelper.isOnDoubleClick()) {
            finish();
        } else {
            ToastUtil.toast("再按一次返回键退出");
        }
    }

}
