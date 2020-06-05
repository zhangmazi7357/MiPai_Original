package com.hym.eshoplib.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.orhanobut.logger.Logger;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JzvdStd;

/**
 * Created by pc on 2018/1/17.
 */

public class JzvdStdVolumeAfterFullscreen extends JzvdStd {
    public JzvdStdVolumeAfterFullscreen(Context context) {
        super(context);
    }

    public JzvdStdVolumeAfterFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPrepared() {
        super.onPrepared();
        try {
            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                JZMediaManager.instance().jzMediaInterface.setVolume(1f, 1f);
            } else {
                JZMediaManager.instance().jzMediaInterface.setVolume(0f, 0f);
            }
        }catch (Exception e){
            Logger.d(e.toString());
        }
    }

    /**
     * 进入全屏模式的时候关闭静音模式
     */
    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
        JZMediaManager.instance().jzMediaInterface.setVolume(1f, 1f);
        tv_see_count.setVisibility(GONE);
        tv_time_long.setVisibility(GONE);
    }

    /**
     * 退出全屏模式的时候开启静音模式
     */
    @Override
    public void playOnThisJzvd() {
        super.playOnThisJzvd();
        JZMediaManager.instance().jzMediaInterface.setVolume(0f, 0f);
        tv_see_count.setVisibility(VISIBLE);
        tv_time_long.setVisibility(VISIBLE);
    }

    @Override
    public void startWindowTiny() {
        //super.startWindowTiny();
    }
}
