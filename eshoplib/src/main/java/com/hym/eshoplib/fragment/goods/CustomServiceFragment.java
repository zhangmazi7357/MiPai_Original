package com.hym.eshoplib.fragment.goods;

import android.app.Dialog;
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

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.http.goods.GoodsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/4/19.
 * <p>
 * Description 客服
 * <p>
 * otherTips
 */

public class CustomServiceFragment extends BaseKitFragment {
    @BindView(R2.id.tv_title)
    RequiredTextView tvTitle;
    @BindView(R2.id.et_expect)
    ClearEditText etExpect;
    @BindView(R2.id.tv_count)
    TextView tvCount;
    @BindView(R2.id.fl_other)
    FrameLayout flOther;
    @BindView(R2.id.btn_submit)
    SuperButton tvSubmit;
    Unbinder unbinder;
    String spid;

    public static CustomServiceFragment newInstance(Bundle args) {
        CustomServiceFragment fragment = new CustomServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_custom_service;
    }

    @Override
    public void doLogic() {
        SoftHideKeyBoardUtil.assistActivity(_mActivity);
        spid=getArguments().getString("id");
        setLeft_iv(R.drawable.ic_close_x, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                _mActivity.finish();
            }
        });
        etExpect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    tvCount.setText(s.toString().length()+"/300");
            }
        });
        etExpect.setHint("您的咨询将以邮件的方式，发送给商家，请将您要咨询的内容，和您的联系方式正确填写以保证商家可以第一时间联系您。（您发送的内容不会在软件中出现，只有商家才可以查看。）");
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content=etExpect.getText().toString();
                if(TextUtils.isEmpty(content)){
                    ToastUtil.toast("请输入咨询信息");
                    return;
                }
                String confirm = getResources().getString(R.string.Confirm);
                String cancle = getResources().getString(R.string.Cancel);
                Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, "提示", "为了保证卖家联系到您，您是否已经留下了正确的联系方式？", cancle, confirm, new DialogUtil.OnDialogHandleListener() {
                    @Override
                    public void onCancleClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();

                    }

                    @Override
                    public void onConfirmeClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        GoodsApi.FeedBack(_mActivity, spid, content, new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("反馈成功稍后客服回联系您");
                                _mActivity.finish();

                            }
                        },Object.class);
                    }
                });
                pDialog.show();


            }
        });

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
