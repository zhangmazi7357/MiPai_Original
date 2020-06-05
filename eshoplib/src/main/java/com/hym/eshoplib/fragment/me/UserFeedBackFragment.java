package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.http.me.MeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/5/6.
 * <p>
 * Description 用户反馈
 * <p>
 * otherTips
 */

public class UserFeedBackFragment extends BaseKitFragment {
    @BindView(R.id.tv_title)
    RequiredTextView tvTitle;
    @BindView(R.id.et_expect)
    ClearEditText etExpect;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.fl_other)
    FrameLayout flOther;
    @BindView(R.id.btn_confirm)
    Button btnSubmit;
    Unbinder unbinder;

    public static UserFeedBackFragment newInstance(Bundle args) {
        UserFeedBackFragment fragment = new UserFeedBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_user_feedback;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle(R.string.FeedbackHelp);
        tvTitle.setText(R.string.Feedbackcontent);
        etExpect.setHint(R.string.PleaseEnterFeedbackcontent);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=etExpect.getText().toString();
                if(TextUtils.isEmpty(content)){
                    ToastUtil.toast("请留下您的宝贵意见");
                    return;
                }
                MeApi.FeedBack(content, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("感谢您的反馈");
                        pop();

                    }
                },Object.class);

            }
        });
        etExpect.requestFocus();
        showSoftInput(etExpect);

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
