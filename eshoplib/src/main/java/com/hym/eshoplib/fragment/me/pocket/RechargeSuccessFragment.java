package com.hym.eshoplib.fragment.me.pocket;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.MainActivity;

import cn.hym.superlib.fragment.base.BaseKitFragment;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/4/12.
 * <p>
 * Description 充值成功
 * <p>
 * otherTips
 */

public class RechargeSuccessFragment extends BaseKitFragment{
    public static RechargeSuccessFragment newInstance(Bundle args) {
        RechargeSuccessFragment fragment = new RechargeSuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }
    SuperButton btn_view_order;
    Button btn_homepage;
    String id;
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_pay_success;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        btn_view_order= (SuperButton) view.findViewById(R.id.btn_view_order);
        btn_homepage= (Button) view.findViewById(R.id. btn_homepage);
        btn_view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回首页
                Intent intent=new Intent(_mActivity, MainActivity.class);
                startActivity(intent);
                _mActivity.finish();

            }
        });
        btn_homepage.setText("查看我的钱包");
        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看我的钱包
                Bundle bundleMoney=getActionBundle(ActionActivity.ModelType_me,ActionActivity.Action_MyPocket);
                ActionActivity.start(_mActivity, bundleMoney);
                _mActivity.finish();
            }
        });
    }

    @Override
    public void doLogic() {
        setTitle("充值成功");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean onBackPressedSupport() {
        _mActivity.finish();
        return true;
    }
}
