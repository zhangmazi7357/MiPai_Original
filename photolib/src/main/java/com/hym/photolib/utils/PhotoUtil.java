package com.hym.photolib.utils;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.hym.photolib.R;
import com.hym.photolib.activity.PicturesActivity;
import com.jph.takephoto.model.TImage;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡彦明 on 2017/12/9.
 * <p>
 * Description 相册工具
 * <p>
 * otherTips
 */

public class PhotoUtil {

    private static String TAG = "PhotoUtil";

    public static final int REQUEST_CODE_CAMARA = 0x111;//请求码 拍照
    public static final int REQUEST_CODE_GALARY = 0x222;//请求码 手机相册

    public static void ShowDialog(final Fragment from, final int limt, final boolean isCrop, int type) {


        switch (type) {
            case 1:
                //takephoto 实现
                ShowDialog(from, limt, isCrop);
                break;
            case 2:
                //pictureSelector实现
                ShowDialogPictureSelector(from, limt, isCrop);
                break;
            case 3:
                ShowDialogPictureSelectorRec(from, limt, isCrop);
                break;
        }

    }

    private static void ShowDialogPictureSelectorRec(final Fragment from, final int limt, final boolean isCrop) {
        final Dialog dialog = PhotoDialogUtil.getSelectDialog(from.getContext(), new PhotoDialogUtil.onSelectPhotoType() {
            @Override
            public void onCamara(Dialog dialog) {
                dialog.dismiss();
                List<LocalMedia> selectList = new ArrayList<>();
                PictureSelector.create(from)
                        .openCamera(PictureMimeType.ofAll())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(limt)
                        .minSelectNum(1)
                        .selectionMode(limt > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                        .previewImage(true)
                        .previewVideo(true)
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(true)
                        .enableCrop(isCrop)
                        .compress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .hideBottomControls(false)
                        .isGif(false)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(false)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .openClickSound(true)
                        .selectionMedia(selectList)
                        .forResult(REQUEST_CODE_CAMARA);

            }

            @Override
            public void onGalary(Dialog dialog) {
                dialog.dismiss();
                List<LocalMedia> selectList = new ArrayList<>();
                PictureSelector.create(from)
                        .openGallery(PictureMimeType.ofAll())
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 必填  预览
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(limt)
                        .minSelectNum(1)
                        .selectionMode(limt > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                        .previewImage(true)
                        .previewVideo(true)
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(false)
                        .enableCrop(isCrop)
                        .compress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .hideBottomControls(false)
                        .isGif(false)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(false)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .openClickSound(true)
                        .selectionMedia(selectList)
                        .forResult(REQUEST_CODE_GALARY);

            }
        });
        dialog.show();
    }

    /**
     * @param from   从fragment 中启动相册
     * @param limt   选择数量上限
     * @param isCrop 是否裁剪
     */
    public static void ShowDialog(final Fragment from, final int limt, final boolean isCrop) {
        final Dialog dialog = PhotoDialogUtil.getSelectDialog(from.getContext(), new PhotoDialogUtil.onSelectPhotoType() {
            @Override
            public void onCamara(Dialog dialog) {
                dialog.dismiss();
                PicturesActivity.Start(
                        from,
                        PicturesActivity.TYPE_CAMERA,
                        REQUEST_CODE_CAMARA,
                        limt,
                        isCrop);

            }

            @Override
            public void onGalary(Dialog dialog) {
                dialog.dismiss();
                PicturesActivity.Start(
                        from,
                        PicturesActivity.TYPE_GALARY,
                        REQUEST_CODE_GALARY,
                        limt,
                        isCrop);
            }
        });
        dialog.show();
    }

    //pictureSelector实现
    public static void ShowDialogPictureSelector(final Fragment from, final int limt, final boolean isCrop) {

        final Dialog dialog = PhotoDialogUtil.getSelectDialog(from.getContext(), new PhotoDialogUtil.onSelectPhotoType() {
            @Override
            public void onCamara(Dialog dialog) {
                dialog.dismiss();
                List<LocalMedia> selectList = new ArrayList<>();
                PictureSelector.create(from)
                        .openCamera(PictureMimeType.ofAll())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(limt)
                        .minSelectNum(1)
                        .selectionMode(limt > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                        .previewImage(true)
                        .previewVideo(true)
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(true)
                        .enableCrop(isCrop)
                        .compress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .hideBottomControls(false)
                        .isGif(false)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(true)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .openClickSound(true)
                        .selectionMedia(selectList)
                        .forResult(REQUEST_CODE_CAMARA);

            }

            @Override
            public void onGalary(Dialog dialog) {
                dialog.dismiss();
                List<LocalMedia> selectList = new ArrayList<>();

                Log.e(TAG, "onGalary: ");


                PictureSelector.create(from)
                        .openGallery(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(limt)
                        .minSelectNum(1)
                        .selectionMode(limt > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                        .isPreviewImage(true)
                        .isEnableCrop(isCrop)
                        .isCompress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .hideBottomControls(false)
                        .isGif(false)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(true)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .isOpenClickSound(false)
                        .selectionMedia(selectList)
                        .forResult(REQUEST_CODE_GALARY);

            }
        });
        dialog.show();
    }

    //处理返回结果
    public static ArrayList<TImage> getImageList(int requestCode, Intent result, OnImageResult listener) {
        ArrayList<TImage> datas = null;
        if (result != null) {
            datas = (ArrayList<TImage>) result.getSerializableExtra("images");
            switch (requestCode) {
                //相机
                case REQUEST_CODE_CAMARA:
                    if (listener != null) {
                        listener.onResultCamara(datas);
                    }
                    break;
                //相册
                case REQUEST_CODE_GALARY:
                    if (listener != null) {
                        listener.onResultGalary(datas);
                    }
                    break;
            }
        }
        return datas;
    }

    public static ArrayList<LocalMedia> getImageList2(int requestCode, Intent result, OnImageResult2 listener) {
        ArrayList<LocalMedia> datas = null;
        if (result != null) {
            datas = (ArrayList<LocalMedia>) result.getSerializableExtra(PictureConfig.EXTRA_RESULT_SELECTION);
            switch (requestCode) {
                //相机
                case REQUEST_CODE_CAMARA:
                    if (listener != null) {
                        listener.onResultCamera(datas);
                    }
                    break;
                //相册
                case REQUEST_CODE_GALARY:
                    if (listener != null) {
                        listener.onResultGalary(datas);
                    }
                    break;
            }
        }
        return datas;
    }

    public interface OnImageResult {
        void onResultCamara(ArrayList<TImage> resultCamara);

        void onResultGalary(ArrayList<TImage> resultGalary);
    }

    //使用pictureselecttor实现
    public interface OnImageResult2 {
        void onResultCamera(ArrayList<LocalMedia> resultCamara);

        void onResultGalary(ArrayList<LocalMedia> resultGalary);
    }

    public static String getFilePash(LocalMedia media) {
        String path = "";
        if (media.isCut() && !media.isCompressed()) {
            // 裁剪过
            path = media.getCutPath();
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = media.getCompressPath();
        } else {
            // 原图
            path = media.getPath();
        }
        return path;
    }

}
