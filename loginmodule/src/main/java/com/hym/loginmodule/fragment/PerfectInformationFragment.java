package com.hym.loginmodule.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.legacy.widget.Space;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.R;
import com.hym.loginmodule.R2;
import com.hym.loginmodule.http.LoginApi;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 陈晖 on 2018/3/8.
 * 完善资料
 */
@Deprecated
public class PerfectInformationFragment extends BaseKitFragment {
    @BindView(R2.id.space)
    Space space;
    @BindView(R2.id.iv_head)
    ImageView icHead;
    @BindView(R2.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R2.id.et_1)
    EditText et1;
    @BindView(R2.id.tv_select_gender)
    TextView tvSelectGender;
    @BindView(R2.id.tv_select_birthday)
    TextView tvSelectBirthday;
    @BindView(R2.id.btn_finish)
    Button btnFinish;
    Unbinder unbinder;

    OptionsPickerView<String> pickerView;
    ArrayList<String> genders = new ArrayList<String>();

    private TimePickerView pvTime;

    String imagePath = "";
    int gender = -1;

    public static PerfectInformationFragment newInstance(Bundle bundle) {
        Bundle args = new Bundle();
        PerfectInformationFragment fragment = new PerfectInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fg_perfect_my_info;
    }

    @Override
    public void doLogic() {
        showBackButton();
        showProgressDialog();
        setTitle(R.string.CompletePersonalInformatiom);
        setRight_tv(R.string.Skip, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginApi.perfectData(_mActivity, "", "", "", "", new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        EventBus.getDefault().post(new LoginEvent(getArguments()));
                        _mActivity.finish();
                    }
                }, Object.class);

            }
        });
        genders.add(getResources().getString(R.string.male));
        genders.add(getResources().getString(R.string.female));
        initTimePicker();
        //修改性别
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                gender = (options1 + 1);
                tvSelectGender.setText(genders.get(options1));

            }
        });
        //builder.setCancelColor(ContextCompat.getColor(_mActivity, R.color.gray));
        //builder.setSubmitColor(ContextCompat.getColor(_mActivity, R.color.red));
        pickerView = new OptionsPickerView(builder);
        pickerView.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {

            }
        });
        pickerView.setPicker(genders);
        icHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtil.ShowDialog(PerfectInformationFragment.this, 1, true);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                String birthDay = tvSelectBirthday.getText().toString();
                if (TextUtils.isEmpty(imagePath)) {
                    ToastUtil.toast("Please to select the head photo first.");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.toast(R.string.EnterNicknamecharacters);
                    return;
                }
                if (gender == -1) {
                    ToastUtil.toast(R.string.PleaseSelect);
                    return;
                }
                if (TextUtils.isEmpty(birthDay)) {
                    ToastUtil.toast(R.string.PleaseSelectDateofBirth);
                    return;
                }
                perfectData(gender + "", name, birthDay);
            }
        });
        tvSelectBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                pvTime.show();
            }
        });
        tvSelectGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                pickerView.show();
            }
        });
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 21);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        //时间选择器
        pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                tvSelectBirthday.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setContentSize(14)//滚轮文字大小
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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

    private void perfectData(final String gender, final String nickName, final String age) {
        File imageFile = new File(imagePath);
        File[] file = new File[]{imageFile};
        LoginApi.uploadFile(getActivity(), file, new ResponseImpl<UploadFilesBean>() {
            @Override
            public void onSuccess(UploadFilesBean data) {
                //图片id
                String attchmentId = data.getData().getAttachment_id().get(0);
                LoginApi.perfectData(getContext(), gender, nickName, age, attchmentId, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast(getResources().getString(R.string.CompletePersonalInformatiom));
                        EventBus.getDefault().post(new LoginEvent(getArguments()));
                        _mActivity.finish();
                    }
                }, Object.class);
            }

        }, UploadFilesBean.class);
    }

    //上传文件
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
                @Override
                public void onResultCamara(ArrayList<TImage> resultCamara) {
                    imagePath = resultCamara.get(0).getCompressPath();
                    if (!TextUtils.isEmpty(imagePath))
                        ImageUtil.getInstance().loadCircleImage(PerfectInformationFragment.this, imagePath, icHead);
                }

                @Override
                public void onResultGalary(ArrayList<TImage> resultGalayr) {
                    imagePath = resultGalayr.get(0).getCompressPath();
                    if (!TextUtils.isEmpty(imagePath))
                        ImageUtil.getInstance().loadCircleImage(PerfectInformationFragment.this, imagePath, icHead);

                }
            });
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return true;
    }
}
