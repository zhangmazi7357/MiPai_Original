package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ShopCardInfoFragment extends BaseKitFragment {

    Unbinder unbinder;
    ShopDetailBean data;
    @BindView(R.id.tv_realname)
    TextView tvRealname;
    @BindView(R.id.tv_cardno)
    TextView tvCardno;

    @BindView(R.id.tv_phone)
    ClearEditText tvPhone;
    @BindView(R.id.tv_email)
    ClearEditText tvEmail;
    @BindView(R.id.tv_photo)
    TextView tvPhoto;

    public static ShopCardInfoFragment newInstance(Bundle args) {
        ShopCardInfoFragment fragment = new ShopCardInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shopcard_info;
    }

    @Override
    public void doLogic() {
        data = (ShopDetailBean) getArguments().getSerializable("data");
        showBackButton();
        setTitle("身份验证信息");
        if (data == null) {
            return;
        }
        tvRealname.setText(data.getData().getCard_info().getReal_name() + "");
        tvCardno.setText(data.getData().getCard_info().getCard_no() + "");

        tvPhone.setText(data.getData().getCard_info().getTel() + "");

        tvEmail.setText(data.getData().getCard_info().getEmail() + "");
        tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Certificate);
                ArrayList<String> list = new ArrayList<String>();
                list.add(data.getData().getCard_info().getCardphoto_up());
                list.add(data.getData().getCard_info().getCardphoto_back());
                list.add(data.getData().getCard_info().getCardphoto_standard());
                bundle.putStringArrayList("data", list);
                bundle.putString("title", "身份证照片详情");
                ActionActivity.start(_mActivity, bundle);
            }
        });
        setRight_tv("提交审核", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();

                String phone = tvPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }

                String email = tvEmail.getText().toString();
                if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
                    ToastUtil.toast("请输入正确的邮箱号");
                    return;
                }


                //校验全部通过调用接口
                ShopApi.EditShop("", "", "",
                        "", "", "",
                        "",
                        phone,
                        "", "",
                        email,
                        "",
                        new ResponseImpl<Object>() {
                            @Override
                            public void onStart(int mark) {
                                setShowProgressDialog(true);
                                super.onStart(mark);
                            }

                            @Override
                            public void onSuccess(Object data) {
                                Bundle bundle = new Bundle();
                                ToastUtil.toast("修改成功,请耐心等待审核");
                                pop();
                            }
                        }, Object.class);
            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
}
