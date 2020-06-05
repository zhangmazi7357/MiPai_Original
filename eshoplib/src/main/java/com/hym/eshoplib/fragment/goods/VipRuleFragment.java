package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2019/9/4.
 * <p>
 * Description 会员权益
 * <p>
 * otheTips
 */

public class VipRuleFragment extends BaseKitFragment {
    @BindView(R.id.tv_des)
    TextView tvDes;
    Unbinder unbinder;

    public static VipRuleFragment newInstance(Bundle args) {
        VipRuleFragment fragment = new VipRuleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_vip_rule;
    }

    @Override
    public void doLogic() {
        setTitle("会员权益");
        tvDes.setText(
                "1.无限畅聊，喜欢的工作室一个都不放过\n\n" +
                        "2.平台推荐：只需说出需求，专属客服迅速为您匹配符合您需求的最优质额工作室\n\n" +
                        "更多特权。敬请期待。");


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
}
