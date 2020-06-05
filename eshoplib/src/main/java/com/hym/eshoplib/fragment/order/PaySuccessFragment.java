package com.hym.eshoplib.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.MainActivity;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/4/12.
 * <p>
 * Description 支付成功
 * <p>
 * otherTips
 */

public class PaySuccessFragment extends BaseKitFragment{

    public static PaySuccessFragment newInstance(Bundle args) {
        PaySuccessFragment fragment = new PaySuccessFragment();
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

        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看订单  //进入订单详情
                Bundle bundle= BaseActionActivity.getActionBundle(EshopActionActivity.ModelType_Order,EshopActionActivity.Action_order_order_detail);
                bundle.putString("id",getArguments().getString("id"));
                EshopActionActivity.start(_mActivity,bundle);
                _mActivity.finish();
            }
        });
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle(R.string.PaymentSuccessful);
        setRight_tv(R.string.Finish, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完成
                _mActivity.finish();
            }
        });

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
