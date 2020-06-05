package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 从业信息
 * <p>
 * otherTips
 */

public class ShopWorkInfoFragment extends BaseKitFragment {
    @BindView(R.id.et_years)
    ClearEditText etYears;
    @BindView(R.id.et_circle)
    ClearEditText etCircle;
    @BindView(R.id.et_expect)
    ClearEditText etExpect;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.fl_other)
    FrameLayout flOther;
    Unbinder unbinder;
    ShopDetailBean data;
    @BindView(R.id.et_work)
    ClearEditText etWork;
    @BindView(R.id.tv_count_2)
    TextView tvCount2;
    @BindView(R.id.fl_word)
    FrameLayout flWord;
    @BindView(R.id.tv_circle)
    RequiredTextView tvCircle;

    public static ShopWorkInfoFragment newInstance(Bundle args) {
        ShopWorkInfoFragment fragment = new ShopWorkInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_workinfo;
    }

    @Override
    public void doLogic() {
        SoftHideKeyBoardUtil.assistActivity(_mActivity);
        showBackButton();
        setTitle("从业信息");
        data = (ShopDetailBean) getArguments().getSerializable("data");
        etExpect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    tvCount.setText(s.toString().length() + "/400");
                } catch (Exception e) {
                    Logger.d(e.toString());
                }

            }
        });
        etWork.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    tvCount2.setText(s.toString().length() + "/400");
                } catch (Exception e) {
                    Logger.d(e.toString());
                }
            }
        });
        if (data != null) {
            if (data.getData().getBase().getCategory_id().equals("46")) {
                tvCircle.setText("拍摄周期", TextView.BufferType.SPANNABLE);
            } else if (data.getData().getBase().getCategory_id().equals("40")) {
                tvCircle.setText("工作室周期", TextView.BufferType.SPANNABLE);
            }
            String year = data.getData().getEmployment().getEmployment_time();
            String circle = data.getData().getEmployment().getProduction_cycle();
            String remark = data.getData().getEmployment().getRemark();
            String job = data.getData().getJob();
            if (!TextUtils.isEmpty(year)) {
                etYears.setText(year);
            }
            if (!TextUtils.isEmpty(circle)) {
                etCircle.setText(circle);
            }
            if (!TextUtils.isEmpty(remark)) {
                etExpect.setText(remark);
            }
            if (!TextUtils.isEmpty(job)) {
                etWork.setText(job);
            }
            setRight_tv("提交审核", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String year = etYears.getText().toString();
                    String circle = etCircle.getText().toString();
                    String remark = etExpect.getText().toString();
                    String job = etWork.getText().toString();
                    if (TextUtils.isEmpty(circle)) {
                        ToastUtil.toast("请输入制作周期");
                        return;
                    }
                    if (TextUtils.isEmpty(remark)) {
                        ToastUtil.toast("请输入个人简介");
                        return;
                    }
                    ShopApi.EditShop2(year, circle, remark,
                            null, null, null,
                            null, job, null,
                            null, null, null, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("提交成功，请等待审核”");
                                    pop();
                                }
                            }, Object.class);


                }
            });
        }


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
