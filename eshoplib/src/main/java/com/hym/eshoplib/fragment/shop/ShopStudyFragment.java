package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.fragment.me.Openshop.CommonSelectFragment;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 学历信息
 * <p>
 * otherTips
 */

public class ShopStudyFragment extends BaseKitFragment {
    ShopDetailBean data;
    @BindView(R.id.tv_study)
    TextView tvStudy;
    @BindView(R.id.et_school)
    ClearEditText etSchool;
    @BindView(R.id.tv_study_card)
    TextView tvStudyCard;
    Unbinder unbinder;
    String ids;
    @BindView(R.id.et_zhuanye)
    ClearEditText etZhuanye;

    public static ShopStudyFragment newInstance(Bundle args) {
        ShopStudyFragment fragment = new ShopStudyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_study;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("学历信息");
        data = (ShopDetailBean) getArguments().getSerializable("data");
        if (data == null) {
            ToastUtil.toast("数据异常，请检查您的网络后重试");
            pop();
            return;
        }
        tvStudy.setText(data.getData().getEducation().getEducation() + "");
        etSchool.setText(data.getData().getEducation().getUniversity() + "");
        etZhuanye.setText(data.getData().getMajor()+"");
        if (data.getData().getEducation().getAttachment() == null || data.getData().getEducation().getAttachment().size() == 0) {
            //tvStudyCard.setHint("未上传");
        } else {
            // tvStudyCard.setHint("已上传");
        }
        tvStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择学历
                Bundle bundle = new Bundle();
                bundle.putInt("type", 3);
                bundle.putString("text", tvStudy.getText().toString());
                startForResult(CommonSelectFragment.newInstance(bundle), 0x01);
            }
        });
        tvStudyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data);
                startForResult(UploadStudyCardFragment.newInstance(bundle), 0x02);
            }
        });
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tvStudy.getText().toString())){
                    ToastUtil.toast("请选择学历");
                    return;
                }
                if(TextUtils.isEmpty(etSchool.getText().toString())){
                    ToastUtil.toast("请输入毕业院校");
                    return;
                }
//                if(TextUtils.isEmpty(etZhuanye.getText().toString())){
//                    ToastUtil.toast("请输入专业");
//                    return;
//                }
                ShopApi.EditShop2(null, null, null,
                        null, null, tvStudy.getText().toString(),
                        etSchool.getText().toString(), null, null,
                        ids, null, etZhuanye.getText().toString(),new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("修改成功");
                                pop();

                            }
                        }, Object.class);
            }
        });

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x01) {
                int position = data.getInt("position", -1);
                String text = data.getString("text", "");
                tvStudy.setText(text);
            } else if (requestCode == 0x02) {
                ids = data.getString("ids", "");
                Logger.d("url=" + ids);
            }
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
