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
import com.hym.eshoplib.bean.shop.PersonalAuthInfoBean;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;

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
 * Description 个人认证
 * <p>
 * otherTips
 */

public class PersonalAuthFragment extends BaseKitFragment {
    @BindView(R.id.et_realname)
    ClearEditText etRealname;
    @BindView(R.id.et_id_card)
    ClearEditText etIdCard;
    @BindView(R.id.et_address)
    ClearEditText etAddress;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_other_phone)
    ClearEditText etOtherPhone;
    @BindView(R.id.et_email)
    ClearEditText etEmail;
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
    Unbinder unbinder;
    @BindView(R.id.et_other_name)
    ClearEditText etOtherName;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private int MsgWhat;
    private String card_1, card_2, card_3;
    Handler handler;
    String type = "-1"; //默认-1未申请

    public static PersonalAuthFragment newInstance(Bundle args) {
        PersonalAuthFragment fragment = new PersonalAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_personal_auth;
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
                                ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, url, ivCard1, 5);
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
                                ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, url, ivCard2, 5);
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
                                ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, url, ivCard3, 5);
                                ivCard3.setVisibility(View.VISIBLE);
                                tvCard3.setVisibility(View.GONE);
                                ivDelet3.setVisibility(View.VISIBLE);
                                card_3 = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);
                        break;
                }

            }
        };
        setTitle("个人认证");
        showBackButton();
        //0 未申请 1 待认证 2 已认证 3已拒绝
        //type = getArguments().getString("type", "");
        setType();
        //适配身份证
        int height = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 40)) / 3;
        tvCard1.getLayoutParams().height = height;
        tvCard2.getLayoutParams().height = height;
        tvCard3.getLayoutParams().height = height;
        ivCard1.getLayoutParams().height = height;
        ivCard2.getLayoutParams().height = height;
        ivCard3.getLayoutParams().height = height;
    }

    private void setType() {
        //认证状态，0：待认证，1：已认证,2:拒绝
        switch (type) {
            case "-1":
                setButton();
                break;
            case "0":
                setRight_tv("", null);
                etRealname.setFocusable(false);
                etIdCard.setFocusable(false);
                etAddress.setFocusable(false);
                etPhone.setFocusable(false);
                etOtherName.setFocusable(false);
                etOtherPhone.setFocusable(false);
                etEmail.setFocusable(false);


                etRealname.setEnabled(false);
                etIdCard.setEnabled(false);
                etAddress.setEnabled(false);
                etPhone.setEnabled(false);
                etOtherName.setEnabled(false);
                etOtherPhone.setEnabled(false);
                etEmail.setEnabled(false);

                break;
            case "1":
                setButton();
                etRealname.setFocusable(false);
                etIdCard.setFocusable(false);
                etRealname.setEnabled(false);
                etIdCard.setEnabled(false);
                etRealname.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                etIdCard.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
                break;
            case "2":
                setButton();
                break;
        }
    }

    private void setButton() {
        setRight_tv("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realName = etRealname.getText().toString();
                if (TextUtils.isEmpty(realName)) {
                    ToastUtil.toast("请输入真实姓名");
                    return;
                }
                String card_id = etIdCard.getText().toString();
                if (card_id.length() != 15 && card_id.length() != 18) {
                    ToastUtil.toast("请输入正确的身份证号");
                    return;
                }
                String address = etAddress.getText().toString();
                if (TextUtils.isEmpty(address)) {
                    ToastUtil.toast("请输入通讯地址");
                    return;
                }
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    ToastUtil.toast("请输入正确的手机号");
                    return;
                }
                String otherNmae = etOtherName.getText().toString();
                if (TextUtils.isEmpty(otherNmae)) {
                    ToastUtil.toast("请输入紧急联系人姓名");
                    return;
                }
                if (otherNmae.equals(realName)) {
                    ToastUtil.toast("紧急联系人不能与您本人重复");
                    return;
                }
//                if(otherNmae.equals(realName)){
//                    ToastUtil.toast();
//                }
                String otherPhone = etOtherPhone.getText().toString();
                if (TextUtils.isEmpty(otherPhone) || otherPhone.length() != 11) {
                    ToastUtil.toast("请输入正确的紧急联系人号码");
                    return;
                }
                if (phone.equals(otherPhone)) {
                    ToastUtil.toast("紧急联系人号码不能与手机号重复");
                    return;
                }
                String email = etEmail.getText().toString();
                if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
                    ToastUtil.toast("请输入正确的邮箱号");
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
                String attachment_id = card_1 + "," + card_2 + "," + card_3;
                ShopApi.PersonNalAuth(realName, card_id, address, phone, otherNmae, otherPhone, email, attachment_id, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("提交成功");
                        _mActivity.finish();

                    }
                }, Object.class);

            }
        });

    }

    @OnClick({R.id.tv_card_1, R.id.iv_delet_1, R.id.tv_card_2, R.id.iv_delet_2, R.id.tv_card_3, R.id.iv_delet_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_1:
                MsgWhat = 2;
                PhotoUtil.ShowDialog(PersonalAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_1:
                card_1 = "";
                tvCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.GONE);
                ivCard1.setVisibility(View.GONE);
                break;
            case R.id.tv_card_2:
                MsgWhat = 3;
                PhotoUtil.ShowDialog(PersonalAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_2:
                card_2 = "";
                tvCard2.setVisibility(View.VISIBLE);
                ivDelet2.setVisibility(View.GONE);
                ivCard2.setVisibility(View.GONE);
                break;
            case R.id.tv_card_3:
                MsgWhat = 4;
                PhotoUtil.ShowDialog(PersonalAuthFragment.this, 1, false, 2);
                break;
            case R.id.iv_delet_3:
                card_3 = "";
                tvCard3.setVisibility(View.VISIBLE);
                ivDelet3.setVisibility(View.GONE);
                ivCard3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.GetAuthInfo("1", new ResponseImpl<PersonalAuthInfoBean>() {
            @Override
            public void onSuccess(PersonalAuthInfoBean data) {
                etRealname.setText(data.getData().getRealname() + "");
                etIdCard.setText(data.getData().getIdcard() + "");
                etAddress.setText(data.getData().getAddress() + "");
                etOtherName.setText(data.getData().getLinkname() + "");
                etPhone.setText(data.getData().getPhone() + "");
                etOtherPhone.setText(data.getData().getLinkphone() + "");
                etEmail.setText(data.getData().getEmail() + "");
                String card_1 = data.getData().getId_img().get(0).getFilepath();
                PersonalAuthFragment.this.card_1 = data.getData().getId_img().get(0).getAttachment_id();
                PersonalAuthFragment.this.card_2 = data.getData().getId_img().get(1).getAttachment_id();
                PersonalAuthFragment.this.card_3 = data.getData().getId_img().get(2).getAttachment_id();
                //0 未申请 1 待认证 2 已认证 3已拒绝
                // Logger.d("type=" + type);
                if (!TextUtils.isEmpty(card_1)) {
                    ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, card_1, ivCard1, 5);
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
                    ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, card_2, ivCard2, 5);
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
                    ImageUtil.getInstance().loadRoundCornerImage(PersonalAuthFragment.this, card_3, ivCard3, 5);
                    tvCard3.setVisibility(View.GONE);
                    if (type.equals("3")) {
                        ivDelet3.setVisibility(View.VISIBLE);
                    } else {
                        ivDelet3.setVisibility(View.GONE);
                    }
                    ivCard3.setVisibility(View.VISIBLE);
                }
                type = data.getData().getStatus();
                if (type.equals("2")) {
                    tvTips.setVisibility(View.VISIBLE);
                    tvTips.setText("拒绝原因："+data.getData().getRejection_reason());
                }
                setType();


            }

            @Override
            public void onDataError(String status, String errormessage) {
                //  super.onDataError(status, errormessage);
                dissMissDialog();
            }
        }, PersonalAuthInfoBean.class);


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
