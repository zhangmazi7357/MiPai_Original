package cn.hym.superlib.fragment.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.R;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2018/3/12.
 * <p>
 * Description 上边是导航栏 下边是多个fragment的 tab类型的fragment
 * <p>
 * otherTips
 */

public abstract class BaseTabFragment extends BaseKitFragment {
    CommonTabLayout tabLayout;
    FrameLayout fl_fragments;
    private SupportFragment[] mFragments ;//要加载的fragment,
    private ArrayList<CustomTabEntity> tabs;//tab数量，tab必须与fragment，的数量必须一致
    private List<Class<? extends SupportFragment>> mFragmensClasses;//用户恢复fragment类对象
    private int current_index = 0;//当前选中tab
    private static final String SAVED_KEY_TAB = "cunrrent_tab_index";
    private boolean isOneFragment=false;//支持一个对个tab对应一个fragment的方式 既，每次点击tab改变的只是fragment里的参数
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_base_tab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = getSupportFragments();
        mFragmensClasses = getClasses();
        if(!isOneFragment()){
            if (mFragments == null || mFragmensClasses == null || mFragments.length != mFragmensClasses.size()) {
                throw new IllegalArgumentException("fragment集合大小必须与class集合大小相等");
            }
        }
        tabs = getTabs();
        if(!isOneFragment()){
            if (tabs == null || tabs.size() != mFragments.length) {
                throw new IllegalArgumentException("tab数量必须与fragment数量相等");
            }
        }
        if (mFragments.length == 0) {
            throw new IllegalArgumentException("fragments不能为空");
        }
        if(!isOneFragment()){
            loadMultipleRootFragment(R.id.fl_fragments, 0, mFragments);
//            if (savedInstanceState == null) {
//                loadMultipleRootFragment(R.id.fl_fragments, 0, mFragments);
//
//            } else {
//                setShowProgressDialog(false);
//                //销毁时做恢复
//                if (mFragments == null) {
//                    mFragments = new SupportFragment[mFragmensClasses.size()];
//                }
//                for (int i = 0; i < mFragmensClasses.size(); i++) {
//                    mFragments[i] = findFragment(mFragmensClasses.get(i));
//                }
//                //恢复销毁时状态
//                current_index = savedInstanceState.getInt(SAVED_KEY_TAB, 0);
//                Logger.d("BaseTab销毁时做恢复 index="+current_index+"==size="+mFragments.length);
//
//            }
        }else {
            //单个fragment
            loadRootFragment(R.id.fl_fragments, mFragments[0]);
//            if (savedInstanceState == null) {
//                loadRootFragment(R.id.fl_fragments, mFragments[0]);
//
//            } else {
//                setShowProgressDialog(false);
//                //销毁时做恢复
//                if (mFragments == null) {
//                    mFragments = new SupportFragment[mFragmensClasses.size()];
//                }
//                for (int i = 0; i < mFragmensClasses.size(); i++) {
//                     mFragments[i] = findFragment(mFragmensClasses.get(i));
//                }
//                //恢复销毁时状态
//                current_index = savedInstanceState.getInt(SAVED_KEY_TAB, 0);
//                Logger.d("BaseTab销毁时做恢复 index="+current_index+"==size="+mFragments.length);
//            }

        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //保存当前tab用于恢复
        // Logger.d("意外销毁时进行保存");
        outState.putInt(SAVED_KEY_TAB, current_index);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void initViews(View view, @Nullable Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        tabLayout= (CommonTabLayout) view.findViewById(R.id.tablayout);
        fl_fragments= (FrameLayout) view.findViewById(R.id.fl_fragments);
        if(savedInstanceState==null){
            initTab();
        }
    }

    private void initTab() {
        Logger.d("initTab");
        if(tabLayout==null||tabLayout.getTabCount()>0){
            return;
        }
        tabLayout.setTabData(tabs);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
               if(!isOneFragment()){
                   //只有点击才会触发
                   showHideFragment(mFragments[position],mFragments[current_index]);
                   current_index = position;
               }else {
                  tabChange(position);
                   current_index=position;
               }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public boolean isOneFragment() {
        return isOneFragment;
    }
    public void tabChange(int position){

    }

    public CommonTabLayout getTabLayout() {
        return tabLayout;
    }

    public abstract SupportFragment[] getSupportFragments();

    public abstract List<Class<? extends SupportFragment>> getClasses();

    public abstract ArrayList<CustomTabEntity> getTabs();

}
