package cn.hym.superlib.fragment.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.httplib.interfaces.IHttpResultListener;
import com.ldoublem.thumbUplib.ThumbUpView;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import cn.hym.superlib.R;
import cn.hym.superlib.utils.HttpStatusUtil;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.SystemBarUtil;

/**
 * Created by  胡彦明 on 2017/5/3.
 * <p>
 * Description 请继承此fragment完成开发
 * <p>
 * OtherTips
 */
public abstract class BaseKitFragment extends BaseFragment {

    private static String TAG = "===BaseFragment = ";
    private Toolbar toolbar;
    private TextView tv_title, tv_left, tv_right;
    private ImageView iv_right, iv_right2, iv_left;
    private boolean showProgressDialog = false;//默认不显示
    private Dialog progressDialog;
    private ThumbUpView like_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            initViews(view, savedInstanceState);
        }
        if (showToolBar()) {
            handleStatusbarHeight(view);
        }
        doLogic();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData(savedInstanceState);
    }


    //处理状态栏高度
    private void handleStatusbarHeight(View root) {
        View v = root.findViewById(R.id.head_status_bar);
        if (v != null) {
            int statusBarheight = SystemBarUtil.getSystemBarHeight(_mActivity);
            v.getLayoutParams().height = statusBarheight;
        }

    }

    //使用butterknife 绑定，也可以重写方法自行findview
    public void initViews(View view) {
        progressDialog = DialogUtil.getLoadingDialog(BaseKitFragment.this, "Loading", true);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolBar(toolbar);
    }

    //使用butterknife 绑定，也可以重写方法自行findview
    public void initViews(View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    //是否显示通用toolbar
    @Override
    public boolean showToolBar() {
        return true;
    }

    //通用toolbar_id,默认toolbar
    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_common;
    }

    //获取当前toolbar
    public Toolbar getToolbar() {
        return toolbar;
    }

    //可重写getToolBarResId() 方式注入toolbar,也可直接注入
    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    //初始化toolbar可重写覆盖自定的toolbar,base中实现的是通用的toolbar
    public void initToolBar(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        tv_left = (TextView) toolbar.findViewById(R.id.tv_left);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_right = (TextView) toolbar.findViewById(R.id.tv_toolbar_right);
        iv_left = (ImageView) toolbar.findViewById(R.id.iv_left);
        iv_right = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right);
        iv_right2 = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right2);
        like_view = (ThumbUpView) toolbar.findViewById(R.id.view_like);

    }

    public ThumbUpView getLike_view() {
        return like_view;
    }

    //显示返回按钮
    public void showBackButton() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_common_back_btn);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftInput();
                    pop();
//                    if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
//                        pop();
//                    } else {
//                        _mActivity.finish();
//                    }
                }
            });
        }
    }

    //显示返回按钮 并设置监听
    public void showBackButton(View.OnClickListener listener) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_common_back_btn);
            if (listener != null) {
                toolbar.setNavigationOnClickListener(listener);
            } else {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftInput();
                        if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                            pop();
                        } else {
                            _mActivity.finish();
                        }
                    }
                });
            }

        }
    }

    //显示中间标题
    public TextView setTitle(String title) {
        if (tv_title != null) {
            tv_title.setText(title + "");
            tv_title.setVisibility(View.VISIBLE);
        }
        return tv_title;
    }

    //重载方法
    public TextView setTitle(@StringRes int res) {
        if (tv_title != null) {
            tv_title.setText(res);
            tv_title.setVisibility(View.VISIBLE);
        }
        return tv_title;
    }

    //左侧tv
    public void setLeft_tv(String content, View.OnClickListener listener) {
        if (tv_left != null) {
            tv_left.setText(content);
            tv_left.setVisibility(View.VISIBLE);
            if (listener != null) {
                tv_left.setOnClickListener(listener);
            }
        }

    }


    public void setLeft_tv(@StringRes int res, View.OnClickListener listener) {
        if (tv_left != null) {
            tv_left.setText(res);
            tv_left.setVisibility(View.VISIBLE);
            if (listener != null) {
                tv_left.setOnClickListener(listener);
            }
        }

    }

    //设置右侧textview
    public void setRight_tv(String content, View.OnClickListener listener) {
        if (tv_right != null) {
            tv_right.setText(content + "");
            tv_right.setVisibility(View.VISIBLE);
            if (listener != null) {
                tv_right.setOnClickListener(listener);
            }
        }

    }

    public void setRight_tv(@StringRes int res, View.OnClickListener listener) {
        if (tv_right != null) {
            tv_right.setText(res);
            tv_right.setVisibility(View.VISIBLE);
            if (listener != null) {
                tv_right.setOnClickListener(listener);
            }
        }

    }

    //设置右侧tv字体大小
    public void setRight_tvTestSize(int size) {
        if (tv_right != null) {
            tv_right.setTextSize(size);
        }
    }

    //设置右侧tv文字
    public void setRight_tvContent(String conent) {
        if (tv_right != null) {
            tv_right.setText(conent + "");
        }
    }

    //设置左侧 imageview
    public ImageView setLeft_iv(@DrawableRes int res, View.OnClickListener listener) {
        if (iv_left != null) {
            iv_left.setImageResource(res);
            iv_left.setVisibility(View.VISIBLE);
            if (listener != null) {
                iv_left.setOnClickListener(listener);
            } else {
                iv_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        return iv_left;
    }

    //设置右侧 imageview
    public ImageView setRight_iv(@DrawableRes int res, View.OnClickListener listener) {
        if (iv_right != null) {
            iv_right.setImageResource(res);
            iv_right.setVisibility(View.VISIBLE);
            if (listener != null) {
                iv_right.setOnClickListener(listener);
            } else {
                iv_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        return iv_right;
    }

    //设置右侧 imageview
    public ImageView setRight_iv2(@DrawableRes int res, View.OnClickListener listener) {
        if (iv_right2 != null) {
            iv_right2.setImageResource(res);
            iv_right2.setVisibility(View.VISIBLE);
            if (listener != null) {
                iv_right2.setOnClickListener(listener);
            } else {
                iv_right2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        return iv_right2;
    }

    //注入进度对话框
    public void setProgressDialog(Dialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    //设置是否显示dialog
    public void setShowProgressDialog(boolean showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
    }

    //显示dialog
    public void showProgressDialog() {
        if (showProgressDialog && progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    //关闭dialog
    public void dissMissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (getRefreshLayout() != null) {
            getRefreshLayout().setRefreshing(false);
        }

    }

    /**
     * Created by  胡彦明 on 2017/5/10.
     * <p>
     * Description http回调接口实现类，实现部分方法用于封装通用逻辑
     * <p>
     * OtherTips
     */

    public abstract class ResponseImpl<T> implements IHttpResultListener<T> {
        @Override
        public void onStart(int mark) {
            //请求开始
            showProgressDialog();
        }

        @Override
        public void onFinish(int mark) {
            //请求结束
            dissMissDialog();
        }

        @Override
        public void onDataError(String status, String errormessage) {
//            Log.e(TAG, "onDataError: " + status);
            //请求成功但是数据异常
            HttpStatusUtil.handleErrorStatus(status, errormessage);
            Log.e(TAG, "onDataError: " + status + ",message = " + errormessage);
            if (!status.equals("6666")) {
                ToastUtil.toast(errormessage);
            }
            dissMissDialog();
        }

        @Override
        public void onFailed(Exception e) {
            //由于网络问题导致的失败
            Log.e(TAG, "onFailed: " + e.toString());
            ToastUtil.toast("Internet Error,please check your Internet connecttion and try it again");
            dissMissDialog();

        }

        @Override
        public void onEmptyData() {
            Log.e(TAG, "onEmptyData: ");

        }

        @Override
        public void dataRes(int code, String data) {

            Log.e(TAG, "dataRes: " + data);
            //请求回来的原始数据未处理过的
//            Logger.d("sourceData=" + data);

        }
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_left() {
        return tv_left;
    }

    public TextView getTv_right() {
        return tv_right;
    }

    //当刷新是通过SwipeRefreshLayout时，重写此方法，可以在刷新数据结束后自动停止刷新状态
    public SwipeRefreshLayout getRefreshLayout() {
        return null;
    }

    //执行逻辑
    public abstract void doLogic();

    //初始化数据
    public abstract void initData(@Nullable Bundle savedInstanceState);

    //是否使用eventBus
    public boolean openEventBus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//           _mActivity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        if (openEventBus()) {
            EventBus.getDefault().register(this);
            //Logger.d("被注册");
        }
    }

    @Override
    public void onDestroy() {
        dissMissDialog();
        progressDialog = null;
        super.onDestroy();
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
            // Logger.d("被解除");
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        hideSoftInput();
        return super.onBackPressedSupport();
    }
    //    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onUpdateUserInfo(UpdateUserInfoEvent event){
//
//    }

    @Override
    public void pop() {
        if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.pop();
        } else {
            _mActivity.finish();
        }
    }

    public static class CustomShareListener implements UMShareListener {

        private WeakReference<Activity> mActivity;

        public CustomShareListener(Activity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {
//            Log.e(TAG, "onStart: ");
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.e(TAG, "onResult: " + platform.toString());

            if (platform != SHARE_MEDIA.MORE
                    && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
            }


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

//            Log.e(TAG, "onError: " + t.getMessage());
            if (platform != SHARE_MEDIA.MORE
                    && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                if (t != null) {
//                    Log.d("throw", "throw:" + t.getMessage());
                    //ToastUtil.toast("分享失败");
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            // ToastUtil.toast("取消分享");
//            Log.e(TAG, "onCancel: ");
        }
    }
}
