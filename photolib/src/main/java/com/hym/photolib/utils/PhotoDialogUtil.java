package com.hym.photolib.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hym.photolib.R;

/**
 * Created by 胡彦明 on 2017/12/9.
 * <p>
 * Description 相册选择对话框
 * <p>
 * otherTips
 */

public class PhotoDialogUtil {
    protected static Dialog getSelectDialog(Context mContext, final onSelectPhotoType listener){
        final Dialog dialog= new Dialog(mContext, R.style.TakePhotoStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_take_photo_dialog, null);
        TextView tv_concle= (TextView) view.findViewById(R.id.tv_cancle);
        TextView tv_camera=(TextView)view.findViewById(R.id.tv_camera);
        TextView tv_galary=(TextView)view.findViewById(R.id.tv_galary);
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onCamara(dialog);
                }
            }
        });
        tv_galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onGalary(dialog);
                }
            }
        });

        tv_concle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(true);
        return dialog;
    }
    public interface onSelectPhotoType{
        void onCamara(Dialog dialog);
        void onGalary(Dialog dialog);
    }
}
