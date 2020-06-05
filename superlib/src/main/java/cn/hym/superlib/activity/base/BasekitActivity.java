package cn.hym.superlib.activity.base;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.httplib.interfaces.IHttpResultListener;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.R;
import cn.hym.superlib.utils.HttpStatusUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.SystemBarUtil;


/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description 请继承此activity完成开发
 * <p>
 * OtherTips
 */

public abstract class BasekitActivity extends BaseActivity {
    private boolean showProgressDialog=false;//默认不显示
    private Toolbar toolbar;
    private TextView tv_title,tv_left,tv_right;;
    private ImageView iv_right ,iv_right2;
    private Dialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTransStatusBar();
        if(initTransStatusBar()&&showToolBar()){
            //如果使用透明状态栏，并且使用通用toolbar的情况下则处理状态栏高度
            handleStatusbarHeight();
            toolbar= (Toolbar) findViewById(R.id.toolbar);
            initToolBar(toolbar);

        }
        initViews();
        doLogic();
    }
    @Override
    public void onDestroy() {
        dissMissDialog();
        super.onDestroy();
    }
    //设置透明状态栏透明状态栏，重写后可以取消该效果,return true 使用透明状态栏，return false 不使用。 return true 的情况下需要自己编写处理逻辑
    public boolean initTransStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return true;
    }
    //初始化toolbar可重写覆盖自定的toolbar,base中实现的是通用的toolbar
    public void initToolBar(Toolbar toolbar){
        if(toolbar==null){
            return;
        }
        tv_left=(TextView)toolbar.findViewById(R.id.tv_left);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_right = (TextView) toolbar.findViewById(R.id.tv_toolbar_right);
        iv_right = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right);
        iv_right2= (ImageView) toolbar.findViewById(R.id.iv_toolbar_right2);

    }
    //处理状态栏高度
    public   void  handleStatusbarHeight(){
        View v=findViewById(R.id.head_status_bar);
        if(v!=null){
            int statusBarheight= SystemBarUtil.getSystemBarHeight(this);
            v.getLayoutParams().height=statusBarheight;
        }

    };
    //是否使用toolbar
    @Override
    public boolean showToolBar() {
        return false;
    }
    //自定义toolbar资源id
    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_common;
    }
    //显示返回按钮，只有使用toolbar时才有效
    public void showBackButton(){
        if(toolbar!=null){
            toolbar.setNavigationIcon(R.drawable.ic_common_back_btn);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                }
            });
        }
    }
    // 设置标题 只有满足特定规则的toolbar时才有效，具体参考代码
    public void setTitle(String title){
        if(tv_title!=null){
            tv_title.setText(title+"");
            tv_title.setVisibility(View.VISIBLE);
        }
    }

    //重载方法
    public void setTitle(@StringRes int res){
        if (tv_title != null) {
            tv_title.setText(res);
            tv_title.setVisibility(View.VISIBLE);
        }
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
    public void setRight_tvTestSize(int size){
        if(tv_right!=null){
            tv_right.setTextSize(size);
        }
    }
    //设置右侧tv文字
    public void setRight_tvContent(String conent){
        if(tv_right!=null){
            tv_right.setText(conent+"");
        }
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
    //是否显示进度对话框
    public void setShowProgressDialog(boolean showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
    }
    //注入进度对话框
    public void setProgressDialog(Dialog progressDialog) {
        this.progressDialog = progressDialog;
    }
    //显示dialog
    public void showProgressDialog(){
        if (showProgressDialog&&progressDialog != null&&!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }
    //关闭dialog
    public void dissMissDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        if(getRefreshLayout()!=null){
            getRefreshLayout().setRefreshing(false);
        }

    }
    //当刷新是通过SwipeRefreshLayout时，重写此方法，可以在刷新数据结束后自动停止刷新状态
    public SwipeRefreshLayout getRefreshLayout(){
        return null;
    }
    //如果不使用Butterknife 可以重写此方法，进行findviewByid操作初始化控件
    public void initViews(){

    }
    //执行业务逻辑
    public abstract void doLogic();
    /**
     * Created by  胡彦明 on 2017/5/10.
     * <p>
     * Description http回调接口实现类，实现部分方法用于封装通用逻辑
     * <p>
     * OtherTips
     */

    public abstract class ResponseImpl<T > implements IHttpResultListener<T> {
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
        public void onDataError(String status,String errormessage) {
            //请求成功但是数据异常
            HttpStatusUtil.handleErrorStatus(status,errormessage);
            Logger.d(errormessage);
            ToastUtil.toast(errormessage);
            dissMissDialog();
        }

        @Override
        public void onFailed(Exception e) {
            //由于网络问题导致的失败
            Logger.d(e.toString());
            ToastUtil.toast("Internet Error,please check your Internet connecttion and try it again");
            dissMissDialog();

        }

        @Override
        public void onEmptyData() {
            Logger.d("空数据");

        }

        @Override
        public void dataRes(int code, String data) {
            //请求回来的原始数据未处理过的
            Logger.d("sourceData="+data);

        }
    }

}
