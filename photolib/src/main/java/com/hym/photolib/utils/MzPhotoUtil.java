package com.hym.photolib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hym.photolib.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.List;

public class MzPhotoUtil {


    public static void showDialog(final Activity activity, final int limit, final OnResultCallbackListener listener) {

        Dialog dialog = initDialog(activity, new onSelectPhotoType() {
            @Override
            public void onCamera(Dialog dialog) {
                dialog.dismiss();
                camera(activity, limit, listener);

            }

            @Override
            public void onGallery(Dialog dialog) {
                dialog.dismiss();
                gallery(activity, limit, listener);
            }
        });

        dialog.show();

    }


    private static Dialog initDialog(Context context, final onSelectPhotoType listener) {
        final Dialog dialog = new Dialog(context, R.style.TakePhotoStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_take_photo_dialog, null);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        TextView camera = view.findViewById(R.id.tv_camera);
        TextView gallery = view.findViewById(R.id.tv_gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCamera(dialog);
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onGallery(dialog);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(true);
        return dialog;
    }


    public interface onSelectPhotoType {
        void onCamera(Dialog dialog);

        void onGallery(Dialog dialog);
    }


    private static void camera(Activity activity, int limit, OnResultCallbackListener listener) {
        List<LocalMedia> selectList = new ArrayList<>();
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofAll())
                .theme(R.style.picture_default_style)
                .maxSelectNum(limit)
                .minSelectNum(1)
                .selectionMode(limit > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                .isPreviewImage(true)
                .isPreviewVideo(true)
                .isCamera(true)
                .isCompress(true)
                .glideOverride(160, 160)
                .isPreviewEggs(true)
                .withAspectRatio(1, 1)
                .hideBottomControls(false)
                .isGif(false)
                .freeStyleCropEnabled(true)
                .circleDimmedLayer(true)
                .showCropFrame(true)
                .showCropGrid(true)
                .selectionMedia(selectList)
                .forResult(listener);
    }


    private static void gallery(Activity activity, int limit, OnResultCallbackListener listener) {


        List<LocalMedia> selectList = new ArrayList<>();

        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // 必填  预览
                .theme(R.style.picture_default_style)
                .maxSelectNum(limit)
                .minSelectNum(1)
                .selectionMode(limit > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                .isPreviewImage(true)
                .isEnableCrop(false)
                .isCompress(true)
                .glideOverride(160, 160)
                .isPreviewEggs(true)
                .withAspectRatio(1, 1)
                .hideBottomControls(false)
                .isGif(false)
                .freeStyleCropEnabled(true)
                .circleDimmedLayer(true)
                .showCropFrame(true)
                .showCropGrid(true)
                .isOpenClickSound(false)
                .selectionMedia(selectList)
                .forResult(listener);
    }
}
