package com.hym.eshoplib.fragment.me.Openshop;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.bean.shop.ShopTypeBean;
import com.hym.eshoplib.fragment.city.SelectCityFragmentCommon;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.CheckUtils;
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
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/8/27.
 * <p>
 * Description 开设工作室步骤1
 * <p>
 * otherTips
 */

public class OpenShopStep1 extends BaseKitFragment {
    @BindView(R.id.iv_upload_image)
    ImageView ivUploadImage;
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_city)
    TextView tvCity;
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
    @BindView(R.id.tv_card_2)
    TextView tvCard2;
    @BindView(R.id.tv_card_3)
    TextView tvCard3;
    Unbinder unbinder;
    ShopDetailBean bean;
    boolean isEdit = false;
    @BindView(R.id.iv_delet_1)
    ImageView ivDelet1;
    @BindView(R.id.iv_delet_2)
    ImageView ivDelet2;
    @BindView(R.id.iv_delet_3)
    ImageView ivDelet3;
    int MsgWhat = -1;
    Handler handler;
    @BindView(R.id.iv_card_1)
    ImageView ivCard1;
    @BindView(R.id.iv_card_2)
    ImageView ivCard2;
    @BindView(R.id.iv_card_3)
    ImageView ivCard3;

    //记录图片的本地地址
    String avatar;//头像
    String card_1;//身份证
    String card_2;
    String card_3;
    ShopTypeBean.DataBean typeBean;//记录工作室类型
    ServerCityBean.DataBean.InfoBean cityBean;//所在城市
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.et_other_name)
    ClearEditText etOtherName;

    public static OpenShopStep1 newInstance(Bundle args) {
        OpenShopStep1 fragment = new OpenShopStep1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_openshop_step1;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;
                switch (msg.what) {
                    case 1:
                        //头像
                        CommonApi.uploadFile(_mActivity, files,
                                new ResponseImpl<UploadFilesBean>() {
                                    @Override
                                    public void onSuccess(UploadFilesBean data) {

                                        ImageUtil.getInstance()
                                                .loadRoundCornerImage(OpenShopStep1.this,
                                                        url, ivUploadImage, 5);
                                        avatar = data.getData().getAttachment_id().get(0);
                                    }
                                }, UploadFilesBean.class);
                        break;

                    case 2:
                        //身份证正面
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, url, ivCard1, 5);
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
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, url, ivCard2, 5);
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
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, url, ivCard3, 5);
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


        isEdit = getArguments().getBoolean("isedit", false);

        SoftHideKeyBoardUtil.assistActivity(_mActivity);
        showBackButton();
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        setTitle("申请开设工作室");
        setRight_tv("下一步", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();

                if (isEdit && bean != null && bean.getData().getBase().getIs_verify().equals("1")) {


                    DialogManager.getInstance().initSimpleDialog(_mActivity, "提示",
                            "信息修改后需要平台审核您确定要修改么", "取消",
                            "确定", new SimpleDialog.SimpleDialogOnClickListener() {
                                @Override
                                public void negativeClick(Dialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void positiveClick(Dialog dialog) {
                                    dialog.dismiss();
                                    checkData();
                                }
                            }).show();
                } else {
                    checkData();
                }


            }
        });
        //适配身份证
        int height = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 40)) / 3;
        tvCard1.getLayoutParams().height = height;
        tvCard2.getLayoutParams().height = height;
        tvCard3.getLayoutParams().height = height;
        ivCard1.getLayoutParams().height = height;
        ivCard2.getLayoutParams().height = height;
        ivCard3.getLayoutParams().height = height;


    }

    private boolean checkData() {
        if (isEdit) {
            //编辑
            return EditShop();
        } else {
            //新开
            return OpenShop();

        }

    }

    private boolean OpenShop() {
        if (TextUtils.isEmpty(avatar)) {
            ToastUtil.toast("请上传工作室头像");
            return false;
        }
        String shopName = etName.getText().toString();
        if (TextUtils.isEmpty(shopName)) {
            ToastUtil.toast("请输入工作室名称");
            return false;
        }

        if (typeBean == null || TextUtils.isEmpty(typeBean.getCategory_id())) {
            ToastUtil.toast("请选择工作室类别");
            return false;
        }
        String category_id = typeBean.getCategory_id();

        if (cityBean == null || TextUtils.isEmpty(cityBean.getRegion_id())) {
            ToastUtil.toast("请选择所在城市");
            return false;
        }
        String region_id = cityBean.getRegion_id();
        //
        String realName = etRealname.getText().toString();
        if (TextUtils.isEmpty(realName)) {
            ToastUtil.toast("请输入真实姓名");
            return false;
        }
        String card_id = etIdCard.getText().toString();
        if (card_id.length() != 15 && card_id.length() != 18) {
            ToastUtil.toast("请输入正确的身份证号");
            return false;
        }


        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            ToastUtil.toast("请输入正确的手机号");
            return false;
        }
//        String ohterName = etOtherName.getText().toString();
//        if (TextUtils.isEmpty(ohterName)) {
//            ToastUtil.toast("请输入紧急联系人姓名");
//            return false;
//        }
//        String otherPhone = etOtherPhone.getText().toString();
//        if (TextUtils.isEmpty(otherPhone) || otherPhone.length() != 11) {
//            ToastUtil.toast("请输入正确的紧急联系人号码");
//            return false;
//        }
//        if (realName.equals(ohterName)) {
//            ToastUtil.toast("紧急联系人不能与您本人重复");
//            return false;
//        }
//        if (phone.equals(otherPhone)) {
//            ToastUtil.toast("紧急联系人号码不能与本人手机号码重复");
//            return false;
//        }
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
            ToastUtil.toast("请输入正确的邮箱号");
            return false;
        }
        if (TextUtils.isEmpty(card_1)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }
        if (TextUtils.isEmpty(card_2)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }
        if (TextUtils.isEmpty(card_3)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }
        String attachment_id = card_1 + "," + card_2 + "," + card_3;

        String address = "测试地址";
        String linkname = "测试名字";
        String linkPhone = "17612341234";

        //校验全部通过调用接口
        ShopApi.OpenShopStep1(avatar,
                shopName,
                category_id,
                region_id,
                realName,
                card_id,
                address,
                phone,
                linkname, linkPhone,
                email,
                attachment_id,
                new ResponseImpl<Object>() {
                    @Override
                    public void onStart(int mark) {
                        setShowProgressDialog(true);
                        super.onStart(mark);
                    }

                    @Override
                    public void onSuccess(Object data) {
                        startForResult(OpenShopStep2.newInstance(new Bundle()), 0x03);
                    }
                }, Object.class);

        return true;
    }

    private boolean EditShop() {
        //头像 如果没修改就不传入
        //商铺名称
        String shopName = etName.getText().toString();
        if (TextUtils.isEmpty(shopName)) {
            ToastUtil.toast("请输入工作室名称");
            return false;
        }
        //工作室类别
        String category_id = "";
        if (typeBean != null && !TextUtils.isEmpty(typeBean.getCategory_id())) {
            category_id = typeBean.getCategory_id();
        }
        //所在城市
        String region_id = "";
        if (cityBean != null && !TextUtils.isEmpty(cityBean.getRegion_id())) {
            region_id = cityBean.getRegion_id();
        }

        String realName = etRealname.getText().toString();
        if (TextUtils.isEmpty(realName)) {
            ToastUtil.toast("请输入真实姓名");
            return false;
        }

        String card_id = etIdCard.getText().toString();
        if (!CheckUtils.isLegalId(card_id)) {
            ToastUtil.toast("请输入正确的身份证号");
            return false;
        }


        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            ToastUtil.toast("请输入正确的手机号");
            return false;
        }

//        String ohterName = etOtherName.getText().toString();
//        if (TextUtils.isEmpty(ohterName)) {
//            ToastUtil.toast("请输入紧急联系人姓名");
//            return false;
//        }
//
//        String otherPhone = etOtherPhone.getText().toString();
//        if (TextUtils.isEmpty(otherPhone) || otherPhone.length() != 11) {
//            ToastUtil.toast("请输入正确的紧急联系人号码");
//            return false;
//        }
//        if (realName.equals(ohterName)) {
//            ToastUtil.toast("紧急联系人不能与您本人重复");
//            return false;
//        }
//        if (phone.equals(otherPhone)) {
//            ToastUtil.toast("紧急联系人号码不能与本人手机号码重复");
//            return false;
//        }

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
            ToastUtil.toast("请输入正确的邮箱号");
            return false;
        }

        if (TextUtils.isEmpty(card_1)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }
        if (TextUtils.isEmpty(card_2)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }
        if (TextUtils.isEmpty(card_3)) {
            ToastUtil.toast("请上传证件照片");
            return false;
        }

        String attachment_id = card_1 + "," + card_2 + "," + card_3;
        //校验全部通过调用接口
        ShopApi.EditShop(avatar,
                shopName,
                category_id,
                region_id,
                realName,
                card_id,
                "",
                phone,
                "",
                "",
                email,
                attachment_id,
                new ResponseImpl<Object>() {
                    @Override
                    public void onStart(int mark) {
                        setShowProgressDialog(true);
                        super.onStart(mark);
                    }

                    @Override
                    public void onSuccess(Object data) {
                        Bundle bundle = new Bundle();
                        //bundle.putSerializable("data",bean);
                        startForResult(OpenShopStep2.newInstance(bundle), 0x03);
                    }
                }, Object.class);
        return true;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (isEdit) {

            //编辑状态
            ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
                @Override
                public void onSuccess(ShopDetailBean data) {

                    Log.e("OpenShopStep1", "onSuccess: " + JSONObject.toJSONString(data));

                    bean = data;
                    String verify = data.getData().getBase().getIs_verify();
                    if (verify.equals("-1")) {
                        tvTips.setVisibility(View.VISIBLE);
                        tvTips.setText("未通过原因：" + bean.getData().getRejection_reason() + "");
                    } else {
                        tvTips.setVisibility(View.GONE);
                    }
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, bean.getData().getBase().getLogo(), ivUploadImage, 5);

                    etName.setText(bean.getData().getBase().getStore_name() + "");
                    tvType.setText(bean.getData().getBase().getCategory_name() + "");
                    tvCity.setText(bean.getData().getBase().getRegion_name() + "");
                    etRealname.setText(bean.getData().getCard_info().getReal_name() + "");
                    etIdCard.setText(bean.getData().getCard_info().getCard_no() + "");
                    etPhone.setText(bean.getData().getCard_info().getTel() + "");
                    etOtherName.setText(bean.getData().getCard_info().getLinkname() + "");
                    etOtherPhone.setText(bean.getData().getCard_info().getLinkphone() + "");
                    etEmail.setText(bean.getData().getCard_info().getEmail() + "");

                    String card_1 = bean.getData().getCard_info().getCardphoto_up();
                    OpenShopStep1.this.card_1 = bean.getData().getCard_info().getCard_id0();
                    OpenShopStep1.this.card_2 = bean.getData().getCard_info().getCard_id1();
                    OpenShopStep1.this.card_3 = bean.getData().getCard_info().getCard_id2();

                    if (!TextUtils.isEmpty(card_1)) {
                        ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, card_1, ivCard1, 5);
                        tvCard1.setVisibility(View.GONE);
                        ivDelet1.setVisibility(View.VISIBLE);
                        ivCard1.setVisibility(View.VISIBLE);
                    }
                    String card_2 = bean.getData().getCard_info().getCardphoto_back();
                    if (!TextUtils.isEmpty(card_2)) {
                        ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, card_2, ivCard2, 5);
                        tvCard2.setVisibility(View.GONE);
                        ivDelet2.setVisibility(View.VISIBLE);
                        ivCard2.setVisibility(View.VISIBLE);
                    }
                    String card_3 = bean.getData().getCard_info().getCardphoto_standard();
                    if (!TextUtils.isEmpty(card_3)) {
                        ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep1.this, card_3, ivCard3, 5);
                        tvCard3.setVisibility(View.GONE);
                        ivDelet3.setVisibility(View.VISIBLE);
                        ivCard3.setVisibility(View.VISIBLE);
                    }


                }
            }, ShopDetailBean.class);
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

    @OnClick({R.id.iv_upload_image, R.id.tv_type, R.id.tv_city,
            R.id.tv_card_1, R.id.iv_delet_1, R.id.tv_card_2,
            R.id.iv_delet_2, R.id.tv_card_3, R.id.iv_delet_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_upload_image:    // 工作室头像
                MsgWhat = 1;
                PhotoUtil.ShowDialog(OpenShopStep1.this, 1, true, 2);
                break;
            case R.id.tv_type:
                //选择工作室类型
                Bundle bundleType = new Bundle();
                if (typeBean != null) {
                    bundleType.putSerializable("data", typeBean);
                }
                startForResult(SelectShopTypeFragment.newInstance(bundleType), 0x01);
                break;
            case R.id.tv_city:
                Bundle bundle = new Bundle();
                startForResult(SelectCityFragmentCommon.newInstance(bundle), 0x02);
                break;
            case R.id.tv_card_1:
                MsgWhat = 2;
                PhotoUtil.ShowDialog(OpenShopStep1.this, 1, false, 2);
                break;
            case R.id.iv_delet_1:
                card_1 = "";
                tvCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.GONE);
                ivCard1.setVisibility(View.GONE);
                break;
            case R.id.tv_card_2:
                MsgWhat = 3;
                PhotoUtil.ShowDialog(OpenShopStep1.this, 1, false, 2);
                break;
            case R.id.iv_delet_2:
                card_2 = "";
                tvCard2.setVisibility(View.VISIBLE);
                ivDelet2.setVisibility(View.GONE);
                ivCard2.setVisibility(View.GONE);
                break;
            case R.id.tv_card_3:
                MsgWhat = 4;
                PhotoUtil.ShowDialog(OpenShopStep1.this, 1, false, 2);
                break;
            case R.id.iv_delet_3:
                card_3 = "";
                tvCard3.setVisibility(View.VISIBLE);
                ivDelet3.setVisibility(View.GONE);
                ivCard3.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 对应 PhotoUtil.showDialog()
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                /**
                 *  相机拍
                 * @param resultCamera
                 */
                @Override
                public void onResultCamera(ArrayList<LocalMedia> resultCamera) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamera.get(0));
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

                /**
                 *  图库选择
                 * @param resultCamera
                 */
                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultCamera) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamera.get(0));
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

    @Override
    public boolean onBackPressedSupport() {
        hideSoftInput();

        exitDialog();
        return true;

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 0x01:
                //选择工作室类型
                typeBean = (ShopTypeBean.DataBean) data.getSerializable("data");
                if (typeBean != null) {
                    tvType.setText(typeBean.getCategory_name() + "");
                }
                break;
            case 0x02:
                //选择城市
                cityBean = (ServerCityBean.DataBean.InfoBean) data.getSerializable("data");
                if (cityBean != null) {
                    tvCity.setText(cityBean.getRegion_name());
                }

                break;
            case 0x03:
                //重下一步跳转回来，由申请状态变成编辑状态
                isEdit = data.getBoolean("isedit", false);
                break;
        }

    }

    // exit
    private void exitDialog() {
        DialogManager.getInstance()
                .initSimpleDialog(_mActivity,
                        "提示",
                        "确定退出吗",
                        "取消",
                        "确定",
                        new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();
                                _mActivity.finish();
                            }
                        }).show();


    }

}
