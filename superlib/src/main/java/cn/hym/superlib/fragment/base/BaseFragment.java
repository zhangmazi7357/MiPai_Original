package cn.hym.superlib.fragment.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hym.httplib.interfaces.IHttpResultListener;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.R;
import cn.hym.superlib.utils.HttpStatusUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by  胡彦明 on 2017/5/2.
 * <p>
 * Description 与具体业务无关的最底层的BaseFragment 需要继承后才可以使用
 * <p>
 * OtherTips
 */

public abstract class BaseFragment extends SupportFragment {
    private View baseView;//在使用自定义toolbar时候的根布局 =toolBarView+childView
    private LinearLayout ll_base_root;

    public LinearLayout getLl_base_root() {
        return ll_base_root;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutResID = getContentViewResId();
        if (layoutResID <= 0) {
            throw new RuntimeException("layoutResID==0 have u create your layout?");
        }
        if (baseView == null) {
            //为空时初始化。
            if (showToolBar() && getToolBarResId() != 0) {
                //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
                baseView = inflater.inflate(R.layout.activity_base, container, false);//根布局
                ll_base_root = (LinearLayout) baseView.findViewById(R.id.ll_base_root);
                ViewStub mVs_toolbar = (ViewStub) baseView.findViewById(R.id.vs_toolbar);//toolbar容器
                FrameLayout fl_container = (FrameLayout) baseView.findViewById(R.id.fl_container);//子布局容器
                mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
                mVs_toolbar.inflate();//填充toolbar
                inflater.inflate(layoutResID, fl_container, true);//子布局
            } else {
                //不显示通用toolbar
                baseView = inflater.inflate(layoutResID, container, false);

            }
        }
        return baseView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        baseView = null;
    }

    //获取contentView 资源id
    public abstract int getContentViewResId();

    //是否显示通用toolBar
    public abstract boolean showToolBar();

    //获取自定义toolbarview 资源id 默认为0，showToolBar()方法必须返回true才有效
    public abstract int getToolBarResId();

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
            // showProgressDialog();
        }

        @Override
        public void onFinish(int mark) {
            //请求结束
            //  dissMissDialog();
        }

        @Override
        public void onDataError(String status, String errormessage) {
            //请求成功但是数据异常
            HttpStatusUtil.handleErrorStatus(status, errormessage);
            Logger.d(errormessage);
            if (!status.equals("6666")) {
                ToastUtil.toast(errormessage);
            }
            // dissMissDialog();
        }

        @Override
        public void onFailed(Exception e) {
            //由于网络问题导致的失败
            Logger.d(e.toString());
            ToastUtil.toast("Internet Error,please check your Internet connecttion and try it again");
            // dissMissDialog();

        }

        @Override
        public void onEmptyData() {
            Logger.d("空数据");

        }

        @Override
        public void dataRes(int code, String data) {
            //请求回来的原始数据未处理过的
            Logger.d("sourceData=" + data);

        }
    }

}
