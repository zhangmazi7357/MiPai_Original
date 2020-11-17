package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.CollectionCodeBean;
import com.hym.eshoplib.event.CollectionEvent;
import com.hym.eshoplib.http.mz.MzNewApi;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.DialogView;
import utils.QRCodeUtil;

/**
 * 我的收款码
 */

public class MyCollectionCodeFragment extends BaseKitFragment {


    Unbinder unbinder;
    @BindView(R.id.et_amount)
    EditText etAmount;
    //    @BindView(R.id.bt_makeCode_wx)
//    Button btMakeCode;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;

    @BindView(R.id.ivType)
    ImageView ivType;

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

    @OnClick({R.id.bt_makeCode_wx, R.id.bt_makeCode_ali})
    public void onClick(View view) {
        String amount = etAmount.getText().toString();
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(_mActivity, "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()) {
            case R.id.bt_makeCode_wx:

                makeCode(amount, "wx");

                break;
            case R.id.bt_makeCode_ali:

                makeCode(amount, "zfb");

                break;
        }


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


    private void makeCode(String amount, String payType) {

        MzNewApi.collectionCode(amount, payType, new ResponseImpl<CollectionCodeBean>() {
            @Override
            public void onSuccess(CollectionCodeBean data) {

                String code_url = data.getData().getCode_url();
                ivType.setVisibility(View.VISIBLE);

                switch (payType) {
                    case "wx":
                        ivType.setImageDrawable(getResources().getDrawable(R.drawable.ic_wechat_pay));

                        break;
                    case "zfb":

                        ivType.setImageDrawable(getResources().getDrawable(R.drawable.ic_alipay));

                        break;
                }

                if (!TextUtils.isEmpty(code_url)) {
                    QRCodeUtil.createQRCode(_mActivity, code_url, ivCollection);
                }
            }

            @Override
            public void dataRes(int code, String data) {
                super.dataRes(code, data);
            }

            @Override
            public void onDataError(String status, String errormessage) {
                super.onDataError(status, errormessage);
                ivType.setVisibility(View.GONE);
            }
        }, CollectionCodeBean.class);
    }


    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showCollection(CollectionEvent event) {


        showDialog();


    }


    private void showDialog() {
        DialogView dialogView = DialogManager.getInstance()
                .initView(_mActivity, R.layout.mz_dialog_collection, Gravity.CENTER);
        dialogView.show();


        TextView close = dialogView.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

}
