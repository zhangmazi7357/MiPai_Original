package com.hym.eshoplib.fragment.guide;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hym.eshoplib.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2019/3/28.
 * <p>
 * Description 操作引导页步骤1
 * <p>
 * otherTips
 */

public class GuideFragment_step_1 extends BaseKitFragment {

    @BindView(R.id.iv_skip)
    ImageView ivSkip;
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.ll_buttons)
    LinearLayout llButtons;
    Unbinder unbinder;
    @BindView(R.id.iv_iwant)
    TextView ivIwant;

    public static GuideFragment_step_1 newInstance(Bundle args) {
        GuideFragment_step_1 fragment = new GuideFragment_step_1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_guide_1;
    }

    @Override
    public void doLogic() {
        _mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏

        int button_width=ScreenUtil.getScreenWidth(_mActivity) / 2;
        int button_height=button_width/5;
        llButtons.getLayoutParams().width =button_width;
        RelativeLayout.LayoutParams layoutparams= (RelativeLayout.LayoutParams) ivIwant.getLayoutParams();
        layoutparams.setMargins(0,ScreenUtil.getScreenHeight(_mActivity)/4,0,0);

//        RelativeLayout.LayoutParams layoutParams2= (RelativeLayout.LayoutParams) llButtons.getLayoutParams();
//        layoutParams2.setMargins(0, SystemBarUtil.getSystemBarHeight(_mActivity),0,0);

        llButtons.setPadding(0,ScreenUtil.dip2px(_mActivity,50)+button_height,0,0);

        btn1.getLayoutParams().height=button_height;
        btn2.getLayoutParams().height=button_height;
        btn3.getLayoutParams().height=button_height;
        btn4.getLayoutParams().height=button_height;



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

    @OnClick({R.id.iv_skip, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onViewClicked(View view) {
        int select=0;
        switch (view.getId()) {
            case R.id.iv_skip:
                break;
            case R.id.btn_1:
                select=1;
                break;
            case R.id.btn_2:
                select=2;
                break;
            case R.id.btn_3:
                select=3;
                break;
            case R.id.btn_4:
                select=4;
                break;
        }
        Bundle bundle=new Bundle();
        bundle.putInt("select",select);
        start(GuideFragment_step_2.newInstance(bundle));

    }
}
