package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.eshoplib.R;
import com.hym.eshoplib.http.shopapi.ShopApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 从业经历
 * <p>
 * otherTips
 */

public class ShopCircleFragment extends BaseKitFragment {
    String des;
    @BindView(R.id.et_des)
    ClearEditText etDes;
    Unbinder unbinder;

    public static ShopCircleFragment newInstance(Bundle args) {
        ShopCircleFragment fragment = new ShopCircleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_circle;
    }

    @Override
    public void doLogic() {
        des = getArguments().getString("des", "");
        setTitle("从业经历");
        etDes.setText(des);
        showBackButton();
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etDes.getText().toString())){
                    ToastUtil.toast("请输入从业经历");
                }else {
                    ShopApi.EditShop2(null, null, null,
                            null, null, null,
                            null, etDes.getText().toString(), "",
                            null, null, null,new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("修改成功");
                                    pop();
                                }
                            }, Object.class);

                }
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
