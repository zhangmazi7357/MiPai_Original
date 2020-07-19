package com.hym.eshoplib.fragment.me;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperTextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.ChangeAvantarBean;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DateUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by 胡彦明 on 2017/7/25.
 * <p>
 * Description 个人信息详情
 * <p>
 * otherTips
 */

public class MeDetailFragment extends BaseKitFragment {


    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @BindView(R.id.rl_head_icon)
    RelativeLayout rlHeadIcon;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    Unbinder unbinder;
    Handler handler;
    @BindView(R.id.iv_right3)
    ImageView ivRight3;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.iv_right4)
    ImageView ivRight4;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;
    OptionsPickerView<String> pickerView;
    ArrayList<String> genders = new ArrayList<String>();
    @BindView(R.id.tv_address)
    SuperTextView tvAddress;
    @BindView(R.id.tv_invoice)
    SuperTextView tvInvoice;
    private TimePickerView pvTime;
    MedetailBean bean;

    public static MeDetailFragment newInstance(Bundle args) {
        MeDetailFragment fragment = new MeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_me_detail;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

    }

    @Override
    public void doLogic() {
        setTitle("设置个人信息");
        genders.add(getResources().getString(R.string.male));
        genders.add(getResources().getString(R.string.female));
        showBackButton();
        setShowProgressDialog(true);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //更新昵称
                        String result = (String) msg.obj;
                        updateUserInfo(result, "", "", "", 1);
                        break;
                    case 2:
                        //更新头像
                        updateHeadIcon((File[]) msg.obj);
                        break;
                    case 3:
                        //更新年龄
                        String age = (String) msg.obj;
                        updateUserInfo("", "", "", age, 4);
                        break;

                }
            }
        };
        initTimePicker();


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
            @Override
            public void onSuccess(MedetailBean data) {



                bean = data;
                ImageUtil.getInstance().loadCircleImage(MeDetailFragment.this, data.getData().getAvatar(), ivHeadIcon);
                tvName.setText(data.getData().getNickname() + "");
                tvSex.setText(data.getData().getGender().equals("1") ? genders.get(0) : genders.get(1));
                tvAge.setText(data.getData().getAge() + "");
            }
        }, MedetailBean.class);

    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        String newTime = DateUtil.getTime();
        Calendar endDate = DateUtil.StringToCalendar(newTime);
        //时间选择器
        pvTime = new TimePickerView.Builder(_mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String changeBirthday = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
                Message message = handler.obtainMessage();
                message.what = 3;
                message.obj = changeBirthday;
                message.sendToTarget();

            }
        })//年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setCancelColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect))
                .setSubmitColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect))
                .setTitleText("请选择出生年月日").setTitleColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorNormal))
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(endDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
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


    @OnClick({R.id.rl_head_icon, R.id.rl_name, R.id.rl_sex, R.id.rl_age, R.id.tv_address, R.id.tv_invoice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head_icon:
                PhotoUtil.ShowDialog(MeDetailFragment.this, 1, true, 2);
//                PhotoManager.getInstance(_mActivity).showDialog();
                break;
            case R.id.rl_name:
                Bundle bundle = new Bundle();
                bundle.putString("title", getResources().getString(R.string.Nickname));
                startForResult(CommonModifyFragment.newInstance(bundle), 0x01);
                break;
            case R.id.rl_sex:
                //修改性别
                OptionsPickerView.Builder builder = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //ToastUtil.toast("position="+options1+"==data="+genders.get(options1));
                        updateUserInfo("", (options1 + 1) + "", "", "", 2);


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
                break;
            case R.id.rl_age:
                //修改年龄
                pvTime.show();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
//                @Override
//                public void onResultCamara(ArrayList<TImage> resultCamara) {
//                    File[] files;
//                    ArrayList<File> arr = new ArrayList<>();
//                    String url = resultCamara.get(0).getCompressPath();
//                    arr.add(new File(url));
//                    ImageUtil.getInstance().loadCircleImage(MeDetailFragment.this, url, ivHeadIcon);
//                    files = arr.toArray(new File[arr.size()]);
//                    Message message = handler.obtainMessage();
//                    message.what = 2;
//                    message.obj = files;
//                    message.sendToTarget();
//
//                }
//
//                @Override
//                public void onResultGalary(ArrayList<TImage> resultGalayr) {
//                    File[] files;
//                    ArrayList<File> arr = new ArrayList<>();
//                    String url = resultGalayr.get(0).getCompressPath();
//                    arr.add(new File(url));
//                    ImageUtil.getInstance().loadCircleImage(MeDetailFragment.this, url, ivHeadIcon);
//                    files = arr.toArray(new File[arr.size()]);
//                    Message message = handler.obtainMessage();
//                    message.what = 2;
//                    message.obj = files;
//                    message.sendToTarget();
//
//                }
//            });
            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    ImageUtil.getInstance().loadCircleImage(MeDetailFragment.this, url, ivHeadIcon);
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = files;
                    message.sendToTarget();

                }

                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    ImageUtil.getInstance().loadCircleImage(MeDetailFragment.this, url, ivHeadIcon);
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = files;
                    message.sendToTarget();
                }
            });
        }

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            final String result = data.getString("str", "");
            if (!TextUtils.isEmpty(result)) {
                Message message = handler.obtainMessage();
                message.what = requestCode;
                message.obj = result;
                message.sendToTarget();

            }
        }
    }

    private void updateUserInfo(final String userName, final String gender, final String phone, final String age, final int type) {
        // showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case 1:
                        //修改昵称
                        tvName.setText(userName);
                        break;
                    case 2:
                        //修改性别
                        if (gender.equals("1")) {
                            tvSex.setText(genders.get(0));
                        }
                        if (gender.equals("2")) {
                            tvSex.setText(genders.get(1));
                        }
                        break;
                    case 4:
                        //修改年龄
                        tvAge.setText(age);
                        break;
                }
                MeApi.updateUserInfo(userName, gender, phone, age, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        switch (type) {
                            case 1:
                                //修改昵称
                                tvName.setText(userName);
                                ToastUtil.toast("昵称修改成功");
                                break;
                            case 2:
                                //修改性别
                                if (gender.equals("1")) {
                                    tvSex.setText(genders.get(0));
                                }
                                if (gender.equals("2")) {
                                    tvSex.setText(genders.get(1));
                                }
                                break;
                            case 4:
                                //修改年龄
                                tvAge.setText(age);
                                break;
                        }


                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                        ToastUtil.toast(errormessage);
                    }
                }, Object.class);
            }
        }, 100);
    }

    //更新头像
    private void updateHeadIcon(final File[] files) {
        MeApi.updateUserHeadIcon(files, new ResponseImpl<ChangeAvantarBean>() {
            @Override
            public void onSuccess(ChangeAvantarBean data) {
                if (bean != null) {
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(bean.getData().getUserid(), bean.getData().getNickname(), Uri.parse(data.getData().getAvatar())));
                }
                ToastUtil.toast("头像更新成功");
            }
        }, ChangeAvantarBean.class);
    }


}
