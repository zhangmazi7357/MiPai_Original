package com.hym.eshoplib.fragment.me.Openshop;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/8/27.
 * <p>
 * Description 开设工作室步骤2
 * <p>
 * otherTips
 */

public class OpenShopStep2 extends BaseKitFragment {
    @BindView(R.id.et_years)
    ClearEditText etYears;
    @BindView(R.id.et_circle)
    ClearEditText etCircle;
    @BindView(R.id.et_expect)
    ClearEditText etExpect;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.fl_other)
    FrameLayout flOther;
    @BindView(R.id.tv_return)
    TextView tvReturn;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_study)
    TextView tvStudy;
    @BindView(R.id.et_school)
    ClearEditText etSchool;
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
    @BindView(R.id.et_experience)
    ClearEditText etExperience;
    @BindView(R.id.et_prize)
    ClearEditText etPrize;
    @BindView(R.id.tv_card_4)
    TextView tvCard4;
    @BindView(R.id.iv_card_4)
    ImageView ivCard4;
    @BindView(R.id.iv_delet_4)
    ImageView ivDelet4;
    @BindView(R.id.tv_card_5)
    TextView tvCard5;
    @BindView(R.id.iv_card_5)
    ImageView ivCard5;
    @BindView(R.id.iv_delet_5)
    ImageView ivDelet5;
    @BindView(R.id.tv_card_6)
    TextView tvCard6;
    @BindView(R.id.iv_card_6)
    ImageView ivCard6;
    @BindView(R.id.iv_delet_6)
    ImageView ivDelet6;
    Unbinder unbinder;
    String refund_type;//退款类型
    String invoice;//是否提供发票
    ShopDetailBean data;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.et_zhuanye)
    ClearEditText etZhuanye;
    @BindView(R.id.tv_count_2)
    TextView tvCount2;
    @BindView(R.id.tv_circle)
    RequiredTextView tvCircle;
    private int MsgWhat;
    Handler handler;
    String url1, url2, url3, url4, url5, url6;
    String qiniuToken;

    public static OpenShopStep2 newInstance(Bundle args) {
        OpenShopStep2 fragment = new OpenShopStep2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_openshop_step2;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        etExpect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    tvCount.setText(s.toString().length() + "/400");
                } catch (Exception e) {
                    Logger.d(e.toString());
                }

            }
        });
        etExperience.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    tvCount2.setText(s.toString().length() + "/400");
                } catch (Exception e) {
                    Logger.d(e.toString());
                }

            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;

                CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                    @Override
                    public void onSuccess(UploadFilesBean data) {
                        Logger.d("msgWhat=" + MsgWhat);
                        switch (MsgWhat) {
                            case 1:
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard1, 5);
                                url1 = data.getData().getAttachment_id().get(0);
                                ivCard1.setVisibility(View.VISIBLE);
                                tvCard1.setVisibility(View.INVISIBLE);
                                ivDelet1.setVisibility(View.VISIBLE);

                                tvCard2.setVisibility(View.VISIBLE);

                                break;
                            case 2:
                                url2 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard2, 5);
                                ivCard2.setVisibility(View.VISIBLE);
                                tvCard2.setVisibility(View.INVISIBLE);
                                ivDelet2.setVisibility(View.VISIBLE);

                                tvCard3.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                url3 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard3, 5);
                                ivCard3.setVisibility(View.VISIBLE);
                                tvCard3.setVisibility(View.INVISIBLE);
                                ivDelet3.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                url4 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard4, 5);
                                ivCard4.setVisibility(View.VISIBLE);
                                tvCard4.setVisibility(View.INVISIBLE);
                                ivDelet4.setVisibility(View.VISIBLE);

                                tvCard5.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                url5 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard5, 5);
                                ivCard5.setVisibility(View.VISIBLE);
                                tvCard5.setVisibility(View.INVISIBLE);
                                ivDelet5.setVisibility(View.VISIBLE);

                                tvCard6.setVisibility(View.VISIBLE);
                                break;
                            case 6:
                                url6 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, url, ivCard6, 5);
                                ivCard6.setVisibility(View.VISIBLE);
                                tvCard6.setVisibility(View.INVISIBLE);
                                ivDelet6.setVisibility(View.VISIBLE);
                                break;
                        }

                    }
                }, UploadFilesBean.class);


            }
        };
        showBackButton();
        setRight_tv("下一步", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                checkData();

            }
        });
        setShowProgressDialog(true);
        setTitle("申请开设工作室");
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
        //适配长传图片
        int height = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 40)) / 3;
        tvCard1.getLayoutParams().height = height;
        tvCard2.getLayoutParams().height = height;
        tvCard3.getLayoutParams().height = height;
        ivCard1.getLayoutParams().height = height;
        ivCard2.getLayoutParams().height = height;
        ivCard3.getLayoutParams().height = height;

        tvCard4.getLayoutParams().height = height;
        tvCard5.getLayoutParams().height = height;
        tvCard6.getLayoutParams().height = height;
        ivCard4.getLayoutParams().height = height;
        ivCard5.getLayoutParams().height = height;
        ivCard6.getLayoutParams().height = height;


    }

    private void checkData() {
        String circle = etCircle.getText().toString();

        circle = "0";    // 制作周期被删掉了;

//        if (TextUtils.isEmpty(circle)) {
//            ToastUtil.toast("请输入制作周期");
//            return;
//        }

        //个人简介
        String expect = etExpect.getText().toString();
        if (TextUtils.isEmpty(expect)) {
            ToastUtil.toast("请输入个人简介");
            return;
        }
        String study = tvStudy.getText().toString();

//        if (TextUtils.isEmpty(study)) {
//            ToastUtil.toast("请选择学历");
//            return;
//        }
        String school = etSchool.getText().toString();
//        if (TextUtils.isEmpty(school)) {
//            ToastUtil.toast("请输入毕业院校");
//            return;
//        }

        if (TextUtils.isEmpty(refund_type) || refund_type.equals("0")) {
            ToastUtil.toast("请选择退款情况");
            return;
        }

        if (TextUtils.isEmpty(invoice) || invoice.equals("2")) {
            ToastUtil.toast("请选择发票情况");
            return;
        }

        String urls_1 = "";
        String urls_2 = "";
        if (!TextUtils.isEmpty(url1)) {
            urls_1 += url1 + ",";
        }
        if (!TextUtils.isEmpty(url2)) {
            urls_1 += url2 + ",";
        }
        if (!TextUtils.isEmpty(url3)) {
            urls_1 += url3;
        }

        if (!TextUtils.isEmpty(url4)) {
            urls_2 += url4 + ",";
        }
        if (!TextUtils.isEmpty(url5)) {
            urls_2 += url5 + ",";
        }
        if (!TextUtils.isEmpty(url6)) {
            urls_2 += url6;
        }
        String major = etZhuanye.getText().toString();
//        if(TextUtils.isEmpty(major)){
//            ToastUtil.toast("请输入专业");
//            return;
//        }
        ShopApi.EditShop2(etYears.getText().toString(),
                circle, expect,
                refund_type, invoice, study,
                school, etExperience.getText().toString(),
                etPrize.getText().toString(),
                urls_1, urls_2, major,

                new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        Bundle bundle = new Bundle();
                        String category_id = OpenShopStep2.this.data.getData().getBase().getCategory_id();
                        bundle.putString("id", category_id);
                        bundle.putString("name", OpenShopStep2.this.data.getData().getBase().getCategory_name());
                        bundle.putString("token", qiniuToken);
                        if (category_id.equals("46")) {
                            //演员 进入年龄身高 性别界面
                            start(ActorInfoFragment.newInstance(bundle));
                        } else {
                            start(OpenShopStep3.newInstance(bundle));
                        }
                    }
                }, Object.class);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //data= (ShopDetailBean) getArguments().getSerializable("data");
        ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
            @Override
            public void onSuccess(ShopDetailBean data) {
                OpenShopStep2.this.data = data;
                bindData();


            }
        }, ShopDetailBean.class);
    }

    private void bindData() {

        if (data != null) {
            if (data.getData().getBase().getCategory_id().equals("46")) {
                tvCircle.setText("拍摄周期", TextView.BufferType.SPANNABLE);
            } else if (data.getData().getBase().getCategory_id().equals("40")) {
                tvCircle.setText("工作周期", TextView.BufferType.SPANNABLE);
            }

            String verify = data.getData().getBase().getIs_verify();
            if (verify.equals("-1")) {
                tvTips.setVisibility(View.VISIBLE);
                tvTips.setText("未通过原因：" + data.getData().getRejection_reason() + "");
            } else {
                tvTips.setVisibility(View.GONE);
            }
            qiniuToken = data.getData().getQiniu_token();
            //专业
            etZhuanye.setText(data.getData().getMajor() + "");
            //从业时间
            etYears.setText(data.getData().getEmployment().getEmployment_time());
            //制作周期
            etCircle.setText(data.getData().getEmployment().getProduction_cycle());
            //个人简介
            etExpect.setText(data.getData().getEmployment().getRemark());
            //退款情况
            refund_type = data.getData().getRefund();
            switch (refund_type) {
                //退款情况，1：接收不满意全额退款，2：接收不满意部分退款，3：定制产品不接受退款
                case "1":
                    tvReturn.setText("接受不满意全额退款");
                    break;
                case "2":
                    tvReturn.setText("接受不满意部分退款");
                    break;
                case "3":
                    tvReturn.setText("定制产品不接受退款");
                    break;
            }
            //tvReturn.setText(data.getData().getRefund().equals("1") ? "接受不满意全额退款" : "定制产品不接受退款");
            //是否提供发票
            invoice = data.getData().getInvoice();
            switch (invoice) {
                case "1":
                    tvInvoice.setText("可开发票");
                    break;
                case "0":
                    tvInvoice.setText("不提供发票");
                    break;
            }

            //学历
            tvStudy.setText(data.getData().getEducation().getEducation());
            //学校
            etSchool.setText(data.getData().getEducation().getUniversity());
            //从业经历
            etExperience.setText(data.getData().getJob());
            //获得奖项
            etPrize.setText(data.getData().getAwards().getAwards());
            //学历证书
            List<ShopDetailBean.DataBean.EducationBean.AttachmentBean> urls_1 = data.getData().getEducation().getAttachment();
            if (urls_1 != null && urls_1.size() > 0) {
                if (urls_1.size() == 1) {
                    tvCard1.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                    ivCard1.setVisibility(View.VISIBLE);
                    ivDelet1.setVisibility(View.VISIBLE);

                    tvCard2.setVisibility(View.VISIBLE);
                    ivCard2.setVisibility(View.GONE);
                    ivDelet2.setVisibility(View.GONE);

                    tvCard3.setVisibility(View.INVISIBLE);
                    ivCard3.setVisibility(View.GONE);
                    ivDelet3.setVisibility(View.GONE);
                } else if (urls_1.size() == 2) {
                    tvCard1.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                    ivCard1.setVisibility(View.VISIBLE);
                    ivDelet1.setVisibility(View.VISIBLE);

                    tvCard2.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(1).getFilepath(), ivCard2, 5);
                    ivCard2.setVisibility(View.VISIBLE);
                    ivDelet2.setVisibility(View.VISIBLE);

                    tvCard3.setVisibility(View.VISIBLE);
                    ivCard3.setVisibility(View.GONE);
                    ivDelet3.setVisibility(View.GONE);
                } else if (urls_1.size() == 3) {
                    tvCard1.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                    ivCard1.setVisibility(View.VISIBLE);
                    ivDelet1.setVisibility(View.VISIBLE);

                    tvCard2.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(1).getFilepath(), ivCard2, 5);
                    ivCard2.setVisibility(View.VISIBLE);
                    ivDelet2.setVisibility(View.VISIBLE);

                    tvCard3.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_1.get(2).getFilepath(), ivCard3, 5);
                    ivCard3.setVisibility(View.VISIBLE);
                    ivDelet3.setVisibility(View.VISIBLE);


                }


            } else {
                tvCard1.setVisibility(View.VISIBLE);
                tvCard2.setVisibility(View.INVISIBLE);
                tvCard3.setVisibility(View.INVISIBLE);

                ivCard1.setVisibility(View.GONE);
                ivDelet1.setVisibility(View.GONE);

                ivCard2.setVisibility(View.GONE);
                ivDelet2.setVisibility(View.GONE);

                ivCard3.setVisibility(View.GONE);
                ivDelet3.setVisibility(View.GONE);
            }

            //获奖证书
            List<ShopDetailBean.DataBean.AwardsBean.AttachmentBean> urls_2 = data.getData().getAwards().getAttachment();
            if (urls_2 != null && urls_2.size() > 0) {
                if (urls_2.size() == 1) {
                    tvCard4.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(0).getFilepath(), ivCard4, 5);
                    ivCard4.setVisibility(View.VISIBLE);
                    ivDelet4.setVisibility(View.VISIBLE);

                    tvCard5.setVisibility(View.VISIBLE);
                    ivCard5.setVisibility(View.GONE);
                    ivDelet5.setVisibility(View.GONE);

                    tvCard6.setVisibility(View.INVISIBLE);
                    ivCard6.setVisibility(View.GONE);
                    ivDelet6.setVisibility(View.GONE);
                } else if (urls_2.size() == 2) {
                    tvCard4.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(0).getFilepath(), ivCard4, 5);
                    ivCard4.setVisibility(View.VISIBLE);
                    ivDelet4.setVisibility(View.VISIBLE);

                    tvCard5.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(1).getFilepath(), ivCard5, 5);
                    ivCard5.setVisibility(View.VISIBLE);
                    ivDelet5.setVisibility(View.VISIBLE);

                    tvCard6.setVisibility(View.VISIBLE);
                    ivCard6.setVisibility(View.GONE);
                    ivDelet6.setVisibility(View.GONE);
                } else if (urls_2.size() == 3) {
                    tvCard4.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(0).getFilepath(), ivCard4, 5);
                    ivCard4.setVisibility(View.VISIBLE);
                    ivDelet4.setVisibility(View.VISIBLE);

                    tvCard5.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(1).getFilepath(), ivCard5, 5);
                    ivCard5.setVisibility(View.VISIBLE);
                    ivDelet5.setVisibility(View.VISIBLE);

                    tvCard6.setVisibility(View.INVISIBLE);
                    ImageUtil.getInstance().loadRoundCornerImage(OpenShopStep2.this, urls_2.get(2).getFilepath(), ivCard6, 5);
                    ivCard6.setVisibility(View.VISIBLE);
                    ivDelet6.setVisibility(View.VISIBLE);


                }

            } else {
                tvCard4.setVisibility(View.VISIBLE);
                tvCard5.setVisibility(View.INVISIBLE);
                tvCard6.setVisibility(View.INVISIBLE);

                ivCard4.setVisibility(View.GONE);
                ivDelet4.setVisibility(View.GONE);

                ivCard5.setVisibility(View.GONE);
                ivDelet5.setVisibility(View.GONE);

                ivCard5.setVisibility(View.GONE);
                ivDelet6.setVisibility(View.GONE);
            }
            for (int i = 0; i < urls_1.size(); i++) {
                switch (i) {
                    case 0:
                        url1 = urls_1.get(i).getId();
                        break;
                    case 1:
                        url2 = urls_1.get(i).getId();
                        break;
                    case 2:
                        url3 = urls_1.get(i).getId();
                        break;
                }
            }
            for (int i = 0; i < urls_2.size(); i++) {
                switch (i) {
                    case 0:
                        url4 = urls_2.get(i).getId();
                        break;
                    case 1:
                        url5 = urls_2.get(i).getId();
                        break;
                    case 2:
                        url6 = urls_2.get(i).getId();
                        break;
                }
            }


        }
    }

    @Override
    public boolean onBackPressedSupport() {
        hideSoftInput();

        DialogManager.getInstance()
                .initSimpleDialog(_mActivity, "提示",
                        "您的信息尚未提交，确定退出吗？(点击“下一步”可保存当前页)",
                        "取消", "确定", new SimpleDialog.SimpleDialogOnClickListener() {
                            @Override
                            public void negativeClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void positiveClick(Dialog dialog) {
                                dialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("isedit", true);
                                setFragmentResult(RESULT_OK, bundle);
                                pop();
                            }
                        }).show();

        return true;

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

    @OnClick({R.id.tv_return, R.id.tv_invoice, R.id.tv_study, R.id.tv_card_1, R.id.iv_delet_1, R.id.tv_card_2, R.id.iv_delet_2, R.id.tv_card_3, R.id.iv_delet_3, R.id.tv_card_4, R.id.iv_delet_4, R.id.tv_card_5, R.id.iv_delet_5, R.id.tv_card_6, R.id.iv_delet_6})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_return:
                bundle.putInt("type", 1);
                bundle.putString("text", tvReturn.getText().toString());
                startForResult(CommonSelectFragment.newInstance(bundle), 0x01);
                break;
            case R.id.tv_invoice:
                bundle.putInt("type", 2);
                bundle.putString("text", tvInvoice.getText().toString());
                startForResult(CommonSelectFragment.newInstance(bundle), 0x01);
                break;
            case R.id.tv_study:
                bundle.putInt("type", 3);
                bundle.putString("text", tvStudy.getText().toString());
                startForResult(CommonSelectFragment.newInstance(bundle), 0x01);
                break;
            case R.id.tv_card_1:
                MsgWhat = 1;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_1:
                url1 = "";
                tvCard1.setVisibility(View.VISIBLE);
                ivCard1.setVisibility(View.GONE);
                ivDelet1.setVisibility(View.GONE);
                break;
            case R.id.tv_card_2:
                MsgWhat = 2;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_2:
                url2 = "";
                tvCard2.setVisibility(View.VISIBLE);
                ivCard2.setVisibility(View.GONE);
                ivDelet2.setVisibility(View.GONE);
                break;
            case R.id.tv_card_3:
                MsgWhat = 3;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_3:
                url3 = "";
                tvCard3.setVisibility(View.VISIBLE);
                ivCard3.setVisibility(View.GONE);
                ivDelet3.setVisibility(View.GONE);
                break;
            case R.id.tv_card_4:
                MsgWhat = 4;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_4:
                url4 = "";
                tvCard4.setVisibility(View.VISIBLE);
                ivCard4.setVisibility(View.GONE);
                ivDelet4.setVisibility(View.GONE);
                break;
            case R.id.tv_card_5:
                MsgWhat = 5;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_5:
                url5 = "";
                tvCard5.setVisibility(View.VISIBLE);
                ivCard5.setVisibility(View.GONE);
                ivDelet5.setVisibility(View.GONE);
                break;
            case R.id.tv_card_6:
                MsgWhat = 6;
                PhotoUtil.ShowDialog(OpenShopStep2.this, 1, false, 2);
                break;
            case R.id.iv_delet_6:
                url6 = "";
                tvCard6.setVisibility(View.VISIBLE);
                ivCard6.setVisibility(View.GONE);
                ivDelet6.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        //选择退款 发票学历等
        try {
            int position = data.getInt("position", -1);
            String text = data.getString("text", "");
            switch (data.getInt("type")) {
                case 1:
                    if (position == 0) {
                        refund_type = "1";//接收不满意全额退款，
                    } else if (position == 1) {
                        refund_type = "2";//接收不满意部分退款
                    } else {
                        refund_type = "3";//不可退款
                    }
                    tvReturn.setText(text);
                    break;
                case 2:
                    if (position == 0) {
                        invoice = "1";//可开发票
                    } else {
                        invoice = "0";//不可开发票
                    }
                    tvInvoice.setText(text);
                    break;
                case 3:
                    tvStudy.setText(text);
                    break;
            }
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePath(resultCamara.get(0));
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
                    String url = PhotoUtil.getFilePath(resultCamara.get(0));
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
