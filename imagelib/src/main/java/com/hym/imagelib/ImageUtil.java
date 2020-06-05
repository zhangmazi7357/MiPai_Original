package com.hym.imagelib;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import android.widget.ImageView;

import com.hym.imagelib.interfaces.IImageLoader;


/**
 * Created by 胡彦明 on 2017/6/25.
 * <p>
 * Description 图片加载工具
 * <p>
 * otherTips
 */

public class ImageUtil {
    IImageLoader iImageLoader;
    private ImageUtil(){

    }
    private static volatile ImageUtil sInstance;

    public static  ImageUtil getInstance(){
        if(sInstance==null){
            synchronized(ImageUtil.class){
                if(sInstance==null){
                    sInstance=new ImageUtil();
                }
            }
        }
        return sInstance;
    }
    protected void init( IImageLoader iImageLoader){
        this.iImageLoader=iImageLoader;

    }
    public <P> void loadGif(Fragment fragment, P path, ImageView target) {
        checkInit();
        iImageLoader.loadGif( fragment,  path,  target);
    }
    //加载图片

    public <P> void loadImage(Context context, P path, ImageView target) {
        checkInit();
        iImageLoader.loadImage(context,path,target);
    }

    public <P> void loadImage(Fragment fragment, P path, ImageView target) {
        checkInit();
        iImageLoader.loadImage( fragment,  path,  target);
    }

    public <P> void loadImage(Activity activity, P path, ImageView target) {
        checkInit();
        iImageLoader.loadImage(activity,path,target);

    }
    //加载圆形图片

    public <P> void loadCircleImage(Context context, P path, ImageView target) {
        checkInit();
        iImageLoader.loadCircleImage(context,path,target);
    }


    public <P> void loadCircleImage(Fragment fragment, P path, ImageView target) {
        checkInit();
        iImageLoader.loadCircleImage(fragment,path,target);
    }


    public <P> void loadCircleImage(Activity activity, P path, ImageView target) {
        checkInit();
        iImageLoader.loadCircleImage(activity,path,target);
    }
    //加载圆角图片

    public <P> void loadRoundCornerImage(Context context, P path, ImageView target,int radiusDp) {
        checkInit();
        iImageLoader.loadRoundCornerImage(context,path,target,radiusDp);
    }

    /**
     *
     * @param fragment
     * @param path
     * @param target
     * @param radiusDp dp
     * @param <P>
     */
    public <P> void loadRoundCornerImage(Fragment fragment,P path, ImageView target,int radiusDp) {
        checkInit();
        iImageLoader.loadRoundCornerImage(fragment,path,target,radiusDp);
    }


    public <P> void loadRoundCornerImage(Activity activity, P path, ImageView target,int radiusDp) {
        checkInit();
        iImageLoader.loadRoundCornerImage(activity,path,target,radiusDp);
    }
    private  void checkInit(){
        if(this.iImageLoader==null){
            throw new RuntimeException("you need excute init() before use it!");
        }
    }



}
