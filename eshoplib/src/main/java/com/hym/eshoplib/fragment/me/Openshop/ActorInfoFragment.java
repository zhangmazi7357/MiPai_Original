package com.hym.eshoplib.fragment.me.Openshop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/12/12.
 * <p>
 * Description 创建演员类型时选择年龄 性别 身高
 * <p>
 * otherTips
 */

public class ActorInfoFragment extends BaseKitFragment {
    ArrayList<String> genders = new ArrayList<String>();
    @BindView(R.id.iv_right3)
    ImageView ivRight3;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.iv_right4)
    TextView ivRight4;
    @BindView(R.id.et_age)
    ClearEditText etAge;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;
    @BindView(R.id.iv_right2)
    TextView ivRight2;
    @BindView(R.id.tv_name)
    ClearEditText tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    Unbinder unbinder;
    OptionsPickerView<String> pickerView;
    String gender = "";
    @BindView(R.id.iv_right5)
    TextView ivRight5;
    @BindView(R.id.tv_weight)
    ClearEditText tvWeight;
    @BindView(R.id.rl_weight)
    RelativeLayout rlWeight;

    public static ActorInfoFragment newInstance(Bundle args) {
        ActorInfoFragment fragment = new ActorInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_actorinfo;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("个人详细信息");
        setShowProgressDialog(true);
        genders.add(getResources().getString(R.string.male));
        genders.add(getResources().getString(R.string.female));
        String btn_msg="下一步";
        final int type=getArguments().getInt("type",0);
        if(type==1){
            btn_msg="保存";
        }
        setRight_tv(btn_msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                if (!gender.equals("1")&&!gender.equals("2")) {
                    ToastUtil.toast("请选择性别");
                    return;
                }
                String age = etAge.getText().toString();
                if (TextUtils.isEmpty(age)) {
                    ToastUtil.toast("请输入年龄");
                    return;
                }
                String height = tvName.getText().toString();
//                if (TextUtils.isEmpty(height)) {
//                    ToastUtil.toast("请输入身高");
//                    return;
//                }
                String weight=tvWeight.getText().toString();
//                if(TextUtils.isEmpty(weight)){
//                    ToastUtil.toast("请输入体重");
//                    return;
//                }
                ShopApi.EditShop3(gender, age, height,weight, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                       if(type==1){
                           _mActivity.finish();
                       }else {
                           start(OpenShopStep3.newInstance(getArguments()));
                       }
                    }
                }, Object.class);


            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
            @Override
            public void onSuccess(ShopDetailBean data) {
                tvName.setText(data.getData().getHeight() + "");
                if(!TextUtils.isEmpty(data.getData().getAge())&&!data.getData().getAge().equals("0")){
                    etAge.setText(data.getData().getAge() + "");
                }
                gender = data.getData().getGender();
                if (gender.equals("1")) {
                    tvSex.setText("男");
                } else if (gender.equals("2")) {
                    tvSex.setText("女");
                }
                tvWeight.setText(data.getData().getWeight()+"");

            }
        }, ShopDetailBean.class);
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

    @OnClick(R.id.rl_sex)
    public void onViewClicked() {
        //修改性别
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //ToastUtil.toast("position="+options1+"==data="+genders.get(options1));
                //updateUserInfo("", (options1 + 1) + "", "", "", 2);
                gender = (options1 + 1) + "";
                Logger.d("gender="+gender);
                tvSex.setText(genders.get(options1));


            }
        });
        builder.setCancelColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect))
                .setSubmitColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
        //builder.setCancelColor(ContextCompat.getColor(_mActivity, R.color.gray));
        //builder.setSubmitColor(ContextCompat.getColor(_mActivity, R.color.red));
        pickerView = new OptionsPickerView(builder);
        pickerView.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {

            }
        });
        pickerView.setPicker(genders);
        pickerView.show();
    }
}
