package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.http.me.MeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/8/6.
 * <p>
 * Description 我的钱包
 * <p>
 * otherTips
 */

public class MyPocketFragment extends BaseKitFragment {
    @BindView(R.id.tv_recharge)
    SuperTextView tvRecharge;
    @BindView(R.id.tv_getcash)
    SuperTextView tvGetcash;
    Unbinder unbinder;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    public static MyPocketFragment newInstance(Bundle args) {
        MyPocketFragment fragment = new MyPocketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_mypocket;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("我的钱包");
        setRight_tv("明细", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_MyLogDetail));
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
            }

            @Override
            public void onSuccess(MedetailBean data) {
                tvMoney.setText(data.getData().getBts_bean().equals("0")?"0.00":data.getData().getBts_bean());

            }
        },MedetailBean.class);

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

    @OnClick({R.id.tv_recharge, R.id.tv_getcash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                start(RechargeFragment.newInstance(new Bundle()));
                break;
            case R.id.tv_getcash:
                Bundle bundle=new Bundle();
                bundle.putString("num",tvMoney.getText().toString());
                start(GetCashFragment.newInstance(bundle));
                break;
        }
    }
}
