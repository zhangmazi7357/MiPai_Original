package com.hym.eshoplib.fragment.me.Openshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/4/12.
 * <p>
 * Description 提交审核成功
 * <p>
 * otherTips
 */

public class OpenSuccessFragment extends BaseKitFragment {
    @BindView(R.id.tv_message)
    TextView tvMessage;
    Unbinder unbinder;

    public static OpenSuccessFragment newInstance(Bundle args) {
        OpenSuccessFragment fragment = new OpenSuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    SuperButton btn_view_order;
    Button btn_homepage;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_pay_success;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        btn_view_order = (SuperButton) view.findViewById(R.id.btn_view_order);
        btn_homepage = (Button) view.findViewById(R.id.btn_homepage);
        btn_view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回首页
                Intent intent = new Intent(_mActivity, MainActivity.class);
                startActivity(intent);
                _mActivity.finish();


            }
        });
        btn_homepage.setText("个人主页");
        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看订单  //进入订单详情
                Intent intent = new Intent(_mActivity, MainActivity.class);
                intent.putExtra("position",3);
                startActivity(intent);
                _mActivity.finish();
            }
        });
    }

    @Override
    public void doLogic() {
        // showBackButton();
        setTitle("提交成功");
        setRight_tv(R.string.Finish, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完成
                _mActivity.finish();
            }
        });
        tvMessage.setText("上传的工作室信息已提交成功\n请等待审核~");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean onBackPressedSupport() {
        _mActivity.finish();
        return true;
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
}
