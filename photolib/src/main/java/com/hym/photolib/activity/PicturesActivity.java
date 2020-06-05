package com.hym.photolib.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.Fragment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 胡彦明 on 2017/5/29.
 * <p>
 * Description 相册图片选择工具
 * <p>
 * otherTips
 */

public class PicturesActivity extends TakePhotoActivity{
    public static final int TYPE_CAMERA=0x01;
    public static final int TYPE_GALARY=0x02;
    int limit=9;
    boolean iscrop=false;
    public static void Start(Activity from,int type,int requestCode,int limit){
        Intent intent=new Intent(from,PicturesActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("limit",limit);
        from.startActivityForResult(intent,requestCode);

    }
    public static void Start(Activity from,int type,int requestCode,int limit,boolean iscrop){
        Intent intent=new Intent(from,PicturesActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("limit",limit);
        intent.putExtra("crop",iscrop);
        from.startActivityForResult(intent,requestCode);

    }
    public static void Start(Fragment from, int type, int requestCode,int limit){
        Intent intent=new Intent(from.getActivity(),PicturesActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("limit",limit);
        from.startActivityForResult(intent,requestCode);

    }
    public static void Start(Fragment from, int type, int requestCode,int limit,boolean iscrop){
        Intent intent=new Intent(from.getActivity(),PicturesActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("limit",limit);
        intent.putExtra("crop",iscrop);
        from.startActivityForResult(intent,requestCode);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type=getIntent().getIntExtra("type",-1);
        limit=getIntent().getIntExtra("limit",9);
        iscrop=getIntent().getBooleanExtra("crop",false);
        takePhote(type,getTakePhoto());


    }
    private void takePhote(int type, TakePhoto takePhoto){
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto,true);
        configTakePhotoOption(takePhoto);
        switch (type){
            case TYPE_CAMERA:
                //拍照
                if(iscrop){
                   takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
                    return;
                }
                takePhoto.onPickFromCapture(imageUri);
                break;
            case TYPE_GALARY:
                //相册
                if(iscrop){
                    takePhoto.onPickMultipleWithCrop(limit,getCropOptions());
                    return;
                }
                takePhoto.onPickMultiple(limit);//默认9张图
                break;
        }

    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        finish();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        finish();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Intent intent=new Intent();
        ArrayList<TImage>images=result.getImages();
       // Log.i("image","图片路径=" + images.get(0).getCompressPath());
        intent.putExtra("images",images);
        setResult(RESULT_OK,intent);
        finish();
    }
    //配置 图片相关属性
    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);//使用自带相册
        builder.setCorrectImage(true);//纠正图片拍照角度
        takePhoto.setTakePhotoOptions(builder.create());

    }
    //压缩相关配置
    private void configCompress(TakePhoto takePhoto,boolean luban){
        int maxSize= 307200;//图片最大不超过300kb
        int width= 1440; //图片比例1080*1920
        int height= 2960;
        boolean showProgressBar=true;//压缩时显示进度条
        boolean enableRawFile = true;//压缩后保存原图
        CompressConfig config;
        if(!luban){
            //采用自己的压缩方式
            config=new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width>=height? width:height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        }else {
            //采用鲁班的压缩方式
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config=CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config,showProgressBar);


    }
    //裁剪图片
    private CropOptions getCropOptions(){
        int height= 800;//裁剪宽高比
        int width= 800;
        CropOptions.Builder builder=new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);//宽x高
        builder.setOutputX(width).setOutputY(height);//宽/高
        builder.setWithOwnCrop(false);//是否使用自带裁剪工具，true 是false 三方
        return builder.create();
    }

}
