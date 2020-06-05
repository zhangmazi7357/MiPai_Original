package com.hym.eshoplib.fragment.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.activity.MainActivity;
import com.hym.eshoplib.bean.me.MedetailBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.util.ChatUtils;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.hym.imagelib.ImageUtil;
import com.hym.loginmodule.activity.LoginMainActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import constant.StringConstants;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/7/20.
 * <p>
 * Description 我的
 * <p>
 * otherTips
 */

public class MeFragment extends BaseKitFragment {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.tv_function)
    TextView tvFunction;
    @BindView(R.id.iv_go_Userdetail)
    ImageView ivGoUserdetail;
    @BindView(R.id.tv_myorder)
    SuperTextView tvMyorder;
    @BindView(R.id.tv_order_1)
    TextView tvOrder1;
    @BindView(R.id.tv_order_2)
    TextView tvOrder2;
    @BindView(R.id.tv_order_3)
    TextView tvOrder3;
    @BindView(R.id.tv_order_4)
    TextView tvOrder4;
    @BindView(R.id.tv_order_5)
    TextView tvOrder5;
    @BindView(R.id.tv_myMonye)
    SuperTextView tvMyMonye;
    @BindView(R.id.tv_myCollect)
    SuperTextView tvMyCollect;
    @BindView(R.id.tv_myInviteCode)
    SuperTextView tvMyInviteCode;
    @BindView(R.id.tv_service_1)
    TextView tvService1;
    @BindView(R.id.tv_tv_service_2)
    TextView tvTvService2;
    @BindView(R.id.tv_tv_service_3)
    TextView tvTvService3;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;
    @BindView(R.id.rl_godetail)
    RelativeLayout rlGodetail;
    MedetailBean medetailBean;
    @BindView(R.id.tv_preview)
    TextView tvPreview;
    @BindView(R.id.ll_function)
    LinearLayout llFunction;
    @BindView(R.id.ll_preview)
    LinearLayout llPreview;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.fl_avatar)
    FrameLayout flAvatar;
    @BindView(R.id.tv_auth)
    SuperTextView tvAuth;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;

    public static MeFragment newInstance(Bundle args) {
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getUserInfo();

    }

    public void getUserInfo() {
        if (!UserUtil.getIsLogin(_mActivity)) {
            ToastUtil.toast("请先登录");
            Bundle bundle = new Bundle();
            bundle.putInt(BaseActionActivity.key_model_type, LoginMainActivity.ModelType_Login);
            bundle.putInt(BaseActionActivity.key_action_type, LoginMainActivity.Action_login);
            LoginMainActivity.start(_mActivity, bundle);
            ((MainActivity) _mActivity).changeTab(0);
            ((MainActivity) _mActivity).bottombar.setCurrentItem(0);
            return;

        }

        MeApi.getUserinfo("", new ResponseImpl<MedetailBean>() {
            @Override
            public void onFinish(int mark) {
                super.onFinish(mark);
                setShowProgressDialog(false);
            }

            @Override
            public void onSuccess(MedetailBean data) {
                medetailBean = data;
                String isStore = data.getData().getIs_store();
                llPreview.setVisibility(View.GONE);
                //tvPreview.setVisibility(View.GONE);
                //-------------是否为工作室，1：是，0：待审核，-1：审核未通过，-2：否
                switch (isStore) {
                    case "1":
                        //是工作室，显示等级，显示如何提升星级
                        ratingbar.setVisibility(View.VISIBLE);
                        tvFunction.setText("如何提升星级");
                        //  tvPreview.setVisibility(View.VISIBLE);
                        llPreview.setVisibility(View.VISIBLE);
                        tvPreview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //预览工作室
                                String content_id = medetailBean.getData().getService_id();
                                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_ShopDetail);
                                //bundle.putInt("type",getArguments().getInt("type",1));//工作室类型，对应首页
                                bundle.putString("id", content_id);
                                ActionActivity.start(_mActivity, bundle);
                            }
                        });
                        break;
                    case "-2":
                        //不是工作室
                        ratingbar.setVisibility(View.GONE);
                        tvFunction.setText("申请开设工作室");
                        break;
                    case "0":
                        ratingbar.setVisibility(View.GONE);
                        tvFunction.setText("审核中");
                        break;
                    case "-1":
                        ratingbar.setVisibility(View.GONE);
                        tvFunction.setText("审核未通过");
                        break;
                    case "2":
                        //待提交
                        ratingbar.setVisibility(View.GONE);
                        tvFunction.setText("待提交审核");
                        break;
                }
                tvName.setText(data.getData().getNickname() + "");
                if (!TextUtils.isEmpty(data.getData().getAvatar())) {
                    ImageUtil.getInstance().loadCircleImage(MeFragment.this, data.getData().getAvatar(), ivAvatar);
                }
                tvMyMonye.setRightString(data.getData().getBts_bean().equals("0") ? "0.00" : data.getData().getBts_bean());
                ratingbar.setRating(Float.parseFloat(data.getData().getRank_average()));

//                if(!SharePreferenceUtil.getBooleangData(_mActivity,"isauth")&&!medetailBean.getData().getAuth_personal().equals("2")&&!medetailBean.getData().getAuth_business().equals("2")){
//                    Dialog dilog=MipaiDialogUtil.getAuthDialog(_mActivity, "认证信息", "", new MipaiDialogUtil.OnBtnSlectListener() {
//                        @Override
//                        public void on1(Dialog dialog) {
//                            dialog.dismiss();
//                            //个人认证
//                            Bundle bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_me,ActionActivity.Action_Auth_Personal);
//                            bundle.putString("type",medetailBean.getData().getAuth_personal());
//                            ActionActivity.start(_mActivity,bundle);
//
//
//                        }
//
//                        @Override
//                        public void on2(Dialog dialog) {
//                            dialog.dismiss();
//                            Bundle bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_me,ActionActivity.Action_Auth_Business);
//                            bundle.putString("type",medetailBean.getData().getAuth_business());
//                            ActionActivity.start(_mActivity,bundle);
//                        }
//                    });
//                    dilog.show();
//                    SharePreferenceUtil.setBooleanData(_mActivity,"isauth",true);
//                }

                ivVip.setVisibility(View.GONE);
                if (medetailBean.getData().getAuth_personal().equals("2")) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_person_circle);
                }
                if (medetailBean.getData().getAuth_business().equals("2")) {
                    ivVip.setVisibility(View.VISIBLE);
                    ivVip.setImageResource(R.drawable.ic_business_circle);
                }
            }
        }, MedetailBean.class);
    }

    @Override
    public void doLogic() {
        setTitle("我的");

        ImageView iv_setting = setRight_iv(R.drawable.ic_setting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置
                if (medetailBean == null) {
                    ToastUtil.toast("数据异常请刷新后重试");
                    return;
                }
                Bundle bundle = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Setting);
                bundle.putString("phone", medetailBean.getData().getSecret_phone());
                ActionActivity.start(_mActivity, bundle);

            }
        });
//        iv_setting.getLayoutParams().width= ScreenUtil.dip2px(_mActivity,16);
//        iv_setting.getLayoutParams().height= ScreenUtil.dip2px(_mActivity,16);
        setRight_iv2(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ToastUtil.toast("分享功能需要在B版加入分享SDK后才能演示");
                mShareListener = new CustomShareListener(_mActivity);
                mShareAction = new ShareAction(_mActivity)
                        .setDisplayList(
                                SHARE_MEDIA.WEIXIN_CIRCLE,
                                SHARE_MEDIA.WEIXIN,
                                SHARE_MEDIA.QQ,
                                SHARE_MEDIA.QZONE,
                                SHARE_MEDIA.SINA)
                        .setCallback(mShareListener);

                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);

                UMWeb web = new UMWeb(medetailBean.getData().getShare_url());
                if (medetailBean.getData().getIs_store().equals("1")) {
                    web.setThumb(new UMImage(_mActivity, medetailBean.getData().getAvatar()));
                    web.setTitle(medetailBean.getData().getNickname() + " | " + "我在觅拍开设了自己的工作室，小伙伴们快来围观啊～");
                    web.setDescription(StringConstants.Slogan);
                    // web.setDescription(medetailBean.getData().getNickname() + " | " + "我在觅拍开设了自己的工作室，小伙伴们快来围观啊～");
                } else {
                    web.setThumb(new UMImage(_mActivity, R.mipmap.ic_launcher));
                    web.setTitle("你的好友" + medetailBean.getData().getNickname() + "发来邀请");
                    web.setDescription("我在“觅拍”上拍视频，欢迎小伙伴们来围观～");
                }
                mShareAction.withMedia(web);
                mShareAction.open(config);

            }
        });
        //tvFunction.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //tvPreview.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUserInfo();

            }
        });

    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return swipeLayout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setShowProgressDialog(true);
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

    @OnClick({R.id.tv_auth, R.id.iv_avatar, R.id.iv_go_Userdetail, R.id.rl_godetail, R.id.tv_function,
            R.id.tv_myorder, R.id.tv_order_1, R.id.tv_order_2, R.id.tv_order_3,
            R.id.tv_order_4, R.id.tv_order_5, R.id.tv_myMonye, R.id.tv_myCollect,
            R.id.tv_myInviteCode, R.id.tv_service_1, R.id.tv_tv_service_2, R.id.tv_tv_service_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                //头像
                goUserCenter();
                break;
            case R.id.iv_go_Userdetail:
                //右箭头
                goUserCenter();
                break;
            case R.id.tv_function:
                //如何提升星级
                if (medetailBean == null || medetailBean.getData().getIs_store() == null) {
                    return;
                }
                String isStore = medetailBean.getData().getIs_store();
                boolean isEdit = false;
                Bundle bundle = getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_OpenShop);
                switch (isStore) {
                    case "1":
                        MipaiDialogUtil.showRiseLevelDialog(_mActivity, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MipaiDialogUtil.dismiss();
                                goUserCenter();

                            }
                        }, null);
                        break;
                    case "0":
                        //审核中
                        ToastUtil.toast("审核中请耐心等待");
                        break;
                    case "-2":
                        //不是工作室，//审核未通过都进入工作室编辑界面
                        isEdit = false;
                        bundle.putBoolean("isedit", isEdit);
                        ActionActivity.start(_mActivity, bundle);
                        break;
                    case "-1":
                        //审核未通过
                    case "2":
                        // 审核未通过，和待提交审核 都进入可编辑状态，由服务器判断编辑的类型。 具体是开设工作室，还是已经是工作室
                        isEdit = true;
                        bundle.putBoolean("isedit", isEdit);
                        ActionActivity.start(_mActivity, bundle);
                        break;

                }
                break;
            case R.id.tv_myorder:
                goOrder(0);
                break;
            case R.id.tv_order_1:
                goOrder(1);
                break;
            case R.id.tv_order_2:
                goOrder(2);
                break;
            case R.id.tv_order_3:
                goOrder(3);
                break;
            case R.id.tv_order_4:
                goOrder(4);
                break;
            case R.id.tv_order_5:
                goOrder(5);
                break;
            case R.id.tv_myMonye:
                //我的钱包
                if (medetailBean == null) {
                    return;
                }
                Bundle bundleMoney = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_MyPocket);
                //bundleMoney.putString("money",medetailBean.getData().getBts_bean().equals("0")?"0.00":medetailBean.getData().getBts_bean());
                ActionActivity.start(_mActivity, bundleMoney);
                break;
            case R.id.tv_myCollect:
                //我的收藏
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_MyCollect));
                break;
            case R.id.tv_myInviteCode:
                //我的邀请码
                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_MyInviteCode));
                break;
            case R.id.tv_service_1:
                //ToastUtil.toast("请将您的宝贵意见发送到邮箱9966477@qq.com我们第一时间查阅");
                Intent i = new Intent(Intent.ACTION_SEND);
                // i.setType("text/plain"); //模拟器请使用这行
                i.setType("message/rfc822"); // 真机上使用这行
                i.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"3401998510@qq.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "您的建议");
                i.putExtra(Intent.EXTRA_TEXT, "我们很乐意倾听您的宝贵意见。");
                startActivity(Intent.createChooser(i, "Select email application."));
                break;
            case R.id.tv_tv_service_2:
                //ToastUtil.toast("聊天功能需要在B版接入融云SDK后才可以使用");
                //调用融云聊天
                if (!UserUtil.getIsLogin(_mActivity)) {
                    ToastUtil.toast("请先登录");
                } else {
                    //如果没连接则先连接
                    if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                        reconnect(UserUtil.getRongYunToken(_mActivity));
                    } else {
                        //1觅拍客服 2觅拍小米
                        ChatUtils.ChatToCustomService(_mActivity, 1);
                    }

                }
                break;
            case R.id.tv_tv_service_3:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "010-52903805");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_auth:
                //认证信息
                Bundle bundle_auth = getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_Auth);
                bundle_auth.putString("type1", medetailBean.getData().getAuth_personal());
                bundle_auth.putString("type2", medetailBean.getData().getAuth_business());
                ActionActivity.start(_mActivity, bundle_auth);
                break;
        }
    }

    private void goUserCenter() {
        //进入个人中心
        if (medetailBean == null || medetailBean.getData().getIs_store() == null) {
            return;
        }
        if (medetailBean.getData().getIs_store().equals("1")) {
            //进入店铺详情
            boolean isEdit = true;
            Bundle bundle = getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_info);
            bundle.putString("type", medetailBean.getData().getCategory_id());
            ActionActivity.start(_mActivity, bundle);
        } else {
            //进入个人中心
            ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_me, ActionActivity.Action_MeDetail));
        }
    }

    private void goOrder(int position) {
        //我的订单
        Bundle bundle = BaseActionActivity.getActionBundle
                (EshopActionActivity.ModelType_Order, EshopActionActivity.Action_order_list);
        bundle.putInt("type", position);
        EshopActionActivity.start(_mActivity, bundle);
    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(LoginEvent event) {
        //登录成功更新nick 和头像
        Bundle bundle = event.getBundle();
        String id = bundle.getString("id");
        String name = bundle.getString("name");
        String url = bundle.getString("url");
        //Logger.d("id="+id+",name="+name+",url="+url);
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(id, name, Uri.parse(url)));
        // RongIM.getInstance().setCurrentUserInfo(new UserInfo(id,name , Uri.parse(url)));

    }

    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.d("---onTokenIncorrect--");
                ToastUtil.toast("聊天token异常");
            }

            @Override
            public void onSuccess(String s) {
                Logger.d("---onSuccess--" + s);
                //连接成功后进入 聊天界面
//                if (mDialog != null)
//                    mDialog.dismiss();
//                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
//                finish();
                //1觅拍客服 2觅拍小米
                ChatUtils.ChatToCustomService(_mActivity, 1);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                ToastUtil.toast("聊天异常：" + e.toString());
                Logger.d("聊天异常：" + e.toString());
            }
        });

    }

}
