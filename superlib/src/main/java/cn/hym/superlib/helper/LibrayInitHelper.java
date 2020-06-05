package cn.hym.superlib.helper;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.utils.common.ToastUtil;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by 胡彦明 on 2017/9/23.
 * <p>
 * Description 初始化辅助工具
 * <p>
 * otherTips
 */

public class LibrayInitHelper {
    private LibrayInitHelper(){

    }
    private static volatile LibrayInitHelper sInstance;
    private boolean inited=false;
    //单例
    public static LibrayInitHelper getInstance(){
        if(sInstance==null){
            synchronized(LibrayInitHelper.class){
                if(sInstance==null){
                    sInstance=new LibrayInitHelper();
                }
            }
        }
        return sInstance;
    }
    public void init(Application context, final boolean debug){
        if(inited){
            return;
        }
        //执行各种初始化操作
        //初始化日志工具
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return debug;
            }
        });
        //fragmentation
        Fragmentation.builder()//初始化fragmentation
                .stackViewMode(Fragmentation.NONE)
                .debug(debug)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        //线上模式时会捕获异常。
                        //Logger.d("framentation异常="+e.toString());
                    }
                })
                .install();
        //Toast
        ToastUtil.init(context);
        inited=true;

    }
}
