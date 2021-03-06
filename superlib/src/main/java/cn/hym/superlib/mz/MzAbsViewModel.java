package cn.hym.superlib.mz;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.hym.httplib.interfaces.IHttpResultListener;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.event.lgoin.LoginEvent;
import cn.hym.superlib.utils.HttpStatusUtil;
import cn.hym.superlib.utils.common.ToastUtil;

public class MzAbsViewModel extends ViewModel {


    private String TAG = "MzAbsViewModel";

    public abstract class ResponseImpl<T> implements IHttpResultListener<T> {
        @Override
        public void onStart(int mark) {
            //请求开始

        }

        @Override
        public void onFinish(int mark) {
            //请求结束
//            Log.e(TAG, "onFinish: ");
        }

        @Override
        public void onDataError(String status, String errormessage) {
            //请求成功但是数据异常
            HttpStatusUtil.handleErrorStatus(status, errormessage);
            Log.e(TAG, "onDataError: " + status + ",errormessage = " + errormessage);

            if (!status.equals("6666")) {
                ToastUtil.toast(errormessage);
            }

        }

        @Override
        public void onFailed(Exception e) {
            //由于网络问题导致的失败
//            Log.e(TAG, "onFailed: " + e.toString());
            ToastUtil.toast("Internet Error,Please check your Internet " +
                    "connection and try it again");


        }

        @Override
        public void onEmptyData() {
//            Log.e(TAG, "空数据");

        }

        @Override
        public void dataRes(int code, String data) {
            //请求回来的原始数据未处理过的
//            Log.e(TAG, "元数据: " + data);
        }
    }

}
