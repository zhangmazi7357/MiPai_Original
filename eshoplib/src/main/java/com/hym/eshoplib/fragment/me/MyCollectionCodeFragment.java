package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.CollectionCodeBean;
import com.hym.eshoplib.http.mz.MzNewApi;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import utils.QRCodeUtil;

/**
 * 我的收款码
 */

public class MyCollectionCodeFragment extends BaseKitFragment {


    Unbinder unbinder;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.bt_makeCode)
    Button btMakeCode;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;


    public static MyCollectionCodeFragment newInstance(Bundle args) {
        MyCollectionCodeFragment fragment = new MyCollectionCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_collection_code;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("收款码");


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.bt_makeCode)
    public void onClick() {

        String amount = etAmount.getText().toString();
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(_mActivity, "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }
        makeCode(amount);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void makeCode(String amount) {

        MzNewApi.collectionCode(amount, new ResponseImpl<CollectionCodeBean>() {
            @Override
            public void onSuccess(CollectionCodeBean data) {

                String code_url = data.getData().getCode_url();
                if (!TextUtils.isEmpty(code_url)) {
                    QRCodeUtil.createQRCode(_mActivity, code_url, ivCollection);
                }
            }


        }, CollectionCodeBean.class);
    }


}
