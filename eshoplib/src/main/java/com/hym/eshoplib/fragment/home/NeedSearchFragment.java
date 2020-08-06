package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hjq.toast.ToastUtils;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.SearchHistoryBean;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;
import me.yokeyword.fragmentation.SupportFragment;

public class NeedSearchFragment extends BaseKitFragment {
    @BindView(R.id.tfl_bottom)
    TagFlowLayout tflBottom;
    Unbinder unbinder;
    @BindView(R.id.head_status_bar)
    View headStatusBar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.tv_right)
    TextView tvRight;
//    @BindView(R.id.iv_toolbar_right)
//    ImageView ivToolbarRight;
//    @BindView(R.id.ll_toolbar_bg)
//    LinearLayout llToolbarBg;
    @BindView(R.id.btn_comment)
    Button btnComment;
    @BindView(R.id.tfl_mid)
    TagFlowLayout tflMid;

    @Override
    public void doLogic() {
        tvRight.setText("取消");
        ivBack.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.VISIBLE);
/*
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keywords = etSearch.getText().toString();
                    goSearch(keywords);
                }
                return false;
            }
        });
*/

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    pop();
                } else {
                    _mActivity.finish();
                }
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getHistory();
    }

    private void getHistory() {
        dissMissDialog();
        GoodsApi.getCommentForMeData(new ResponseImpl<SearchHistoryBean>() {
            @Override
            public void onSuccess(SearchHistoryBean data) {
                String word = "";
                List<String> hotword = data.getData().getHotword();
                if (hotword.size() > 0){
                    for (int i = 0;i < hotword.size();i++){
                        word += hotword.get(i)+" ";
                    }
                }
                etSearch.setHint(word);
                List<String> seek = data.getData().getSeek();
                List<String> shot = data.getData().getShot();
                tflMid.setAdapter(new TagAdapter<String>(seek) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_search_tab, parent, false);
                        textView.setText(s);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etSearch.setText(textView.getText().toString());
                                // goSearch(textView.getText().toString());
                            }
                        });
                        return textView;
                    }
                });


                tflBottom.setAdapter(new TagAdapter<String>(shot) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        final SuperButton textView = (SuperButton) LayoutInflater.from(_mActivity).inflate(R.layout.item_search_tab, parent, false);
                        textView.setText(s);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etSearch.setText(textView.getText().toString());
                                // goSearch(textView.getText().toString());
                            }
                        });
                        return textView;
                    }
                });
            }

            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
                showSoftInput(etSearch);
            }
        }, SearchHistoryBean.class);
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.need_search_fragment;
    }

    public static SupportFragment newInstance(Bundle extras) {
        NeedSearchFragment needSearchFragment = new NeedSearchFragment();
        needSearchFragment.setArguments(extras);
        return needSearchFragment;
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

    @OnClick(R.id.btn_comment)
    public void onClick() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            ToastUtil.toast("请先登录");
            Bundle bundle = new Bundle();
            bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
            bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
            LoginMainActivity.start(_mActivity, bundle);
            return;
        }

        String search = etSearch.getText().toString();
        if (TextUtils.isEmpty(search)) {
            ToastUtils.show("请选择需要推荐的类型");
            return;
        }
        // 构造 TextMessage 实例
        TextMessage myTextMessage = TextMessage.obtain(search);
        /* 生成 Message 对象。
         */
        final Message myMessage = Message.obtain("3681", Conversation.ConversationType.PRIVATE, myTextMessage);

        RongIM.connect(UserUtil.getRongYunToken(_mActivity), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {

                RongIMClient.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {

                    }

                    @Override
                    public void onSuccess(Message message) {
                        if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                            pop();
                        } else {
                            _mActivity.finish();
                        }
                        ToastUtil.toast("您的需求已经提交");
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {

                    }
                });
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        });
    }
}
