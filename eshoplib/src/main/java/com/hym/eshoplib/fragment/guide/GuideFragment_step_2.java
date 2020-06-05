package com.hym.eshoplib.fragment.guide;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.MainActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2019/3/28.
 * <p>
 * Description 引导页2
 * <p>
 * otherTips
 */

public class GuideFragment_step_2 extends BaseKitFragment {

    @BindView(R.id.iv_skip)
    ImageView ivSkip;
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.ll_buttons)
    LinearLayout llButtons;
    Unbinder unbinder;
    int select=0;
    public static GuideFragment_step_2 newInstance(Bundle args) {
        GuideFragment_step_2 fragment = new GuideFragment_step_2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_guide_2;
    }

    @Override
    public void doLogic() {
        select = getArguments().getInt("select", 0);
        int button_width = ScreenUtil.getScreenWidth(_mActivity) / 2;
        //int button_height=button_width/5;
        llButtons.getLayoutParams().width = button_width;
//        btn1.getLayoutParams().height=button_height;
//        btn2.getLayoutParams().height=button_height;
       // llButtons.setPadding(0,ScreenUtil.dip2px(_mActivity,10)+button_height,0,0);
        Logger.d("select=" + select);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_skip, R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        int select_2=0;
        switch (view.getId()) {
            case R.id.iv_skip:
                break;
            case R.id.btn_1:
                select_2=1;
                break;
            case R.id.btn_2:
                select_2=2;
                break;
        }
        select(select,select_2);
    }
    public void select(int s1,int s2){
        int guide=0;//默认不引导
        /**
         *  1. 广告片+diy既     s1=1   s2=2  引导=文案策划+导演+摄像师+剪辑师+演员/模特+化妆师 guide=1
         *  2.宣传片+diy 既     s1=2  s2=2   引导=导演+摄像师+剪辑师+演员/模特+化妆师          guide=2
         *  3.电商视频+diy 既  s1=3  s2=2   引导=摄像师+剪辑师+演员/模特+化妆师                guide=3
         *  4.个人视频+diy 既  s1=4  s2=2   引导=摄像师+剪辑师+化妆师                          guide=4
         *  5,视频团队                                                                         guide=5
         *  6.第一个跳过第二个算则diy s1=0 s2=2 引导=导演+摄像师+剪辑师                        guide=6
         *  0.不引导                                                                           guide=0
         *
         *
         */
         if(s2==1){
             //如果选择了团队则无视1选项直接引导视频团队
             guide=5;
         }else if(s2==2){
             //选择了diy ，则根据s1进行判断
             switch (s1){
                 case 0:
                     guide=6;
                     break;
                 case 1:
                     guide=1;
                     break;
                 case 2:
                     guide=2;
                     break;
                 case 3:
                     guide=3;
                     break;
                 case 4:
                     guide=4;
                     break;
             }
         }else {
             //选择了跳过，则不引导

         }

         Bundle bundle=new Bundle();
         bundle.putInt("guide",guide);
         Intent intent=new Intent(_mActivity, MainActivity.class);
         intent.putExtras(bundle);
         _mActivity.startActivity(intent);
         _mActivity.finish();


    }

}
