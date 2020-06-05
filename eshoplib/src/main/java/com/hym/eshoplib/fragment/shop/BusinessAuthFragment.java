package com.hym.eshoplib.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.BusinessAuthInfoBean;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/12/17.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class BusinessAuthFragment extends BaseKitFragment {
    @BindView(R.id.et_company)
    ClearEditText etCompany;
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.et_id_card)
    ClearEditText etIdCard;
    @BindView(R.id.et_no)
    ClearEditText etNo;
    @BindView(R.id.et_address)
    ClearEditText etAddress;
    @BindView(R.id.et_company_type)
    ClearEditText etCompanyType;
    @BindView(R.id.tv_card_1)
    TextView tvCard1;
    @BindView(R.id.iv_card_1)
    ImageView ivCard1;
    @BindView(R.id.iv_delet_1)
    ImageView ivDelet1;
    @BindView(R.id.tv_card_2)
    TextView tvCard2;
    @BindView(R.id.iv_card_2)
    ImageView ivCard2;
    @BindView(R.id.iv_delet_2)
    ImageView ivDelet2;
    @BindView(R.id.tv_card_3)
    TextView tvCard3;
    @BindView(R.id.iv_card_3)
    ImageView ivCard3;
    @BindView(R.id.iv_delet_3)
    ImageView ivDelet3;
    @BindView(R.id.tv_card_4)
    TextView tvCard4;
    @BindView(R.id.iv_card_4)
    ImageView ivCard4;
    @BindView(R.id.iv_delet_4)
    ImageView ivDelet4;
    Unbinder unbinder;
    @BindView(R.id.et_faren_phone)
    ClearEditText etFarenPhone;
    @BindView(R.id.et_other_name)
    ClearEditText etOtherName;
    @BindView(R.id.et_other_phone)
    ClearEditText etOtherPhone;
    @BindView(R.id.et_email)
    ClearEditText etEmail;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private String type = "-1";
    private String card_1, card_2, card_3, card_4;
    private Handler handler;
    private int MsgWhat;

    public static BusinessAuthFragment newInstance(Bundle args) {
        BusinessAuthFragment fragment = new BusinessAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_business_auth;
    }

    @Override
    public void doLogic() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;
                switch (msg.what) {
                    case 2:
                        //身份证正面
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, url, ivCard1, 5);
                                ivCard1.setVisibility(View.VISIBLE);
                                tvCard1.setVisibility(View.GONE);
                                ivDelet1.setVisibility(View.VISIBLE);
                                card_1 = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);

                        break;
                    case 3:
                        //身份证背面
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, url, ivCard2, 5);
                                ivCard2.setVisibility(View.VISIBLE);
                                tvCard2.setVisibility(View.GONE);
                                ivDelet2.setVisibility(View.VISIBLE);
                                card_2 = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);

                        break;
                    case 4:
                        //手持身份证
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, url, ivCard3, 5);
                                ivCard3.setVisibility(View.VISIBLE);
                                tvCard3.setVisibility(View.GONE);
                                ivDelet3.setVisibility(View.VISIBLE);
                                card_3 = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);
                        break;
                    case 5:
                        //营业执照
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, url, ivCard4, 5);
                                ivCard4.setVisibility(View.VISIBLE);
                                tvCard4.setVisibility(View.GONE);
                                ivDelet4.setVisibility(View.VISIBLE);
                                card_4 = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);
                        break;
                }

            }
        };
        showBackButton();
        setTitle("企业认证");
        //适配身份证
        int height = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 40)) / 3;
        tvCard1.getLayoutParams().height = height;
        tvCard2.getLayoutParams().height = height;
        tvCard3.getLayoutParams().height = height;

        ivCard1.getLayoutParams().height = height;
        ivCard2.getLayoutParams().height = height;
        ivCard3.getLayoutParams().height = height;

        tvCard4.getLayoutParams().height = height;
        tvCard4.getLayoutParams().width = height;
        ivCard4.getLayoutParams().height = height;
        ivCard4.getLayoutParams().width = height;
        //0 未申请 1 待认证 2 已认证 3已拒绝
        // type = getArguments().getString("type", "");
        setType();

    }

    private void setButton() {
        setRight_tv("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company_name = etCompany.getText().toString();
                if (TextUtils.isEmpty(company_name)) {
                    ToastUtil.toast("请输入公司名称");
                    return;
                }
                String name = etName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.toast("请输入法人姓名");
                    return;
                }
                String faren_phone = etFarenPhone.getText().toString();
                if (TextUtils.isEmpty(faren_phone) || faren_phone.length() != 11) {
                    ToastUtil.toast("请输入正确的法人手机号");
                    return;
                }
                String card_id = etIdCard.getText().toString();
                if (card_id.length() != 15 && card_id.length() != 18) {
                    ToastUtil.toast("请输入正确的身份证号");
                    return;
                }
                String no = etNo.getText().toString();
                if (TextUtils.isEmpty(no)) {
                    ToastUtil.toast("请输入统一社会信用代码");
                    return;
                }
                String otherNmae = etOtherName.getText().toString();
                if (TextUtils.isEmpty(otherNmae)) {
                    ToastUtil.toast("请输入紧急联系人姓名");
                    return;
                }
                if (otherNmae.equals(name)) {
                    ToastUtil.toast("紧急联系人不能与法人姓名重复");
                    return;
                }
                String otherPhone = etOtherPhone.getText().toString();
                if (TextUtils.isEmpty(otherPhone) || otherPhone.length() != 11) {
                    ToastUtil.toast("请输入正确的紧急联系人号码");
                    return;
                }
                if (otherPhone.equals(faren_phone)) {
                    ToastUtil.toast("紧急联系人号码不能与法人手机号重复");
                    return;
                }
                String email = etEmail.getText().toString();
                if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
                    ToastUtil.toast("请输入正确的邮箱号");
                    return;
                }
                String address = etAddress.getText().toString();
                if (TextUtils.isEmpty(address)) {
                    ToastUtil.toast("请输入详细地址");
                    return;
                }

                String company_type = etCompanyType.getText().toString();
                if (TextUtils.isEmpty(company_name)) {
                    ToastUtil.toast("请输入所属行业");
                    return;
                }

                if (TextUtils.isEmpty(card_1)) {
                    ToastUtil.toast("请上传证件照片");
                    return;
                }
                if (TextUtils.isEmpty(card_2)) {
                    ToastUtil.toast("请上传证件照片");
                    return;
                }
                if (TextUtils.isEmpty(card_3)) {
                    ToastUtil.toast("请上传证件照片");
                    return;
                }
                if (TextUtils.isEmpty(card_4)) {
                    ToastUtil.toast("请上传营业执照副本");
                    return;
                }
                String attachment_id = card_1 + "," + card_2 + "," + card_3;
                ShopApi.BusinessNalAuth(company_name, name, faren_phone, card_id, no, address, otherNmae, otherPhone, email, company_type, attachment_id, card_4, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("提交成功");
                        _mActivity.finish();
                    }
                }, Object.class);


            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.GetAuthInfo("2", new ResponseImpl<BusinessAuthInfoBean>() {
            @Override
            public void onSuccess(BusinessAuthInfoBean data) {
                etCompany.setText(data.getData().getCompny_name() + "");
                etName.setText(data.getData().getRealname() + "");
                etFarenPhone.setText(data.getData().getPhone() + "");
                etOtherName.setText(data.getData().getLinkname() + "");
                etOtherPhone.setText(data.getData().getLinkphone() + "");
                etEmail.setText(data.getData().getEmail() + "");
                etIdCard.setText(data.getData().getIdcard() + "");
                etNo.setText(data.getData().getCredit_code() + "");
                etAddress.setText(data.getData().getAddress() + "");
                etCompanyType.setText(data.getData().getCompny_type());
                String card_1 = data.getData().getId_img().get(0).getFilepath();
                BusinessAuthFragment.this.card_1 = data.getData().getId_img().get(0).getAttachment_id();
                BusinessAuthFragment.this.card_2 = data.getData().getId_img().get(1).getAttachment_id();
                BusinessAuthFragment.this.card_3 = data.getData().getId_img().get(2).getAttachment_id();
                BusinessAuthFragment.this.card_4 = data.getData().getAu_img().get(0).getAttachment_id();
                //0 未申请 1 待认证 2 已认证 3已拒绝
                Logger.d("type=" + type);
                if (!TextUtils.isEmpty(card_1)) {
                    ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, card_1, ivCard1, 5);
                    tvCard1.setVisibility(View.GONE);
                    if (type.equals("3")) {
                        ivDelet1.setVisibility(View.VISIBLE);
                    } else {
                        ivDelet1.setVisibility(View.GONE);
                    }
                    ivCard1.setVisibility(View.VISIBLE);
                }
                String card_2 = data.getData().getId_img().get(1).getFilepath();
                if (!TextUtils.isEmpty(card_2)) {
                    ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, card_2, ivCard2, 5);
                    tvCard2.setVisibility(View.GONE);
                    if (type.equals("3")) {
                        ivDelet2.setVisibility(View.VISIBLE);
                    } else {
                        ivDelet2.setVisibility(View.GONE);
                    }
                    ivCard2.setVisibility(View.VISIBLE);
                }
                String card_3 = data.getData().getId_img().get(2).getFilepath();
                if (!TextUtils.isEmpty(card_3)) {
                    ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, card_3, ivCard3, 5);
                    tvCard3.setVisibility(View.GONE);
                    if (type.equals("3")) {
                        ivDelet3.setVisibility(View.VISIBLE);
                    } else {
                        ivDelet3.setVisibility(View.GONE);
                    }
                    ivCard3.setVisibility(View.VISIBLE);
                }
                String card_4 = data.getData().getAu_img().get(0).getFilepath();
                if (!TextUtils.isEmpty(card_4)) {
                    ImageUtil.getInstance().loadRoundCornerImage(BusinessAuthFragment.this, card_4, ivCard4, 5);
                    tvCard4.setVisibility(View.GONE);
                    if (type.equals("3")) {
                        ivDelet4.setVisibility(View.VISIBLE);
                    } else {
                        ivDelet4.setVisibility(View.GONE);
                    }
                    ivCard4.setVisibility(View.VISIBLE);
                }
                type = data.getData().getStatus();
                if(type.equals("2")){
                    tvTips.setVisibility(View.VISIBLE);
                    tvTips.setText("未通过原因："+data.getData().getRejection_reason()+"");
                }
                setType();


            }

            @Override
            public void onDataError(String status, String errormessage) {
                //  super.onDataError(status, errormessage);
                dissMissDialog();
            }
        }, BusinessAuthInfoBean.class);

    }

    private void setType() {
        switch (type) {
            case "-1":
                setButton();
                break;
            case "0":
                setRight_tv("", null);
                etCompany.setFocusable(false);
                etName.setFocusable(false);
                etIdCard.setFocusable(false);
                etNo.setFocusable(false);
                etAddress.setFocusable(false);
                etCompanyType.setFocusable(false);
                //后加字段
                etEmail.setFocusable(false);
                etOtherName.setFocusable(false);
                etOtherPhone.setFocusable(false);
                etFarenPhone.setFocusable(false);

                etCompany.setEnabled(false);
                etName.setEnabled(false);
                etIdCard.setEnabled(false);
                etNo.setEnabled(false);
                etAddress.setEnabled(false);
                etCompanyType.setEnabled(false);
                //后加字段
                etEmail.setEnabled(false);
                etOtherName.setEnabled(false);
                etOtherPhone.setEnabled(false);
                etFarenPhone.setEnabled(false);
                break;
            case "1":
                setButton();
                etCompany.setFocusable(false);
                etName.setFocusable(false);
                etIdCard.setFocusable(false);
                etNo.setFocusable(false);

                etCompany.setEnabled(false);
                etName.setEnabled(false);
                etIdCard.setEnabled(false);
                etNo.setEnabled(false);


                etCompany.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                etName.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                etIdCard.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                etNo.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                break;
            case "2":
                setButton();
                break;
        }
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

    @OnClick({R.id.tv_card_1, R.id.iv_delet_1, R.id.tv_card_2, R.id.iv_delet_2, R.id.tv_card_3, R.id.iv_delet_3, R.id.tv_card_4, R.id.iv_delet_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_1:
                MsgWhat = 2;
                PhotoUtil.ShowDialog(BusinessAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_1:
                card_1 = "";
                tvCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.GONE);
                ivCard1.setVisibility(View.GONE);
                break;
            case R.id.tv_card_2:
                MsgWhat = 3;
                PhotoUtil.ShowDialog(BusinessAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_2:
                card_2 = "";
                tvCard2.setVisibility(View.VISIBLE);
                ivDelet2.setVisibility(View.GONE);
                ivCard2.setVisibility(View.GONE);
                break;
            case R.id.tv_card_3:
                MsgWhat = 4;
                PhotoUtil.ShowDialog(BusinessAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_3:
                card_3 = "";
                tvCard3.setVisibility(View.VISIBLE);
                ivDelet3.setVisibility(View.GONE);
                ivCard3.setVisibility(View.GONE);
                break;
            case R.id.tv_card_4:
                MsgWhat = 5;
                PhotoUtil.ShowDialog(BusinessAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_4:
                card_4 = "";
                tvCard4.setVisibility(View.VISIBLE);
                ivDelet4.setVisibility(View.GONE);
                ivCard4.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamara(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();

                }

                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();
                }
            });
        }

    }
}
