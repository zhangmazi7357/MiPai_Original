package cn.hym.superlib.utils.common;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.imagelib.ImageUtil;

import cn.hym.superlib.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2017/12/6.
 * <p>
 * Description 获取特定类型的dialog
 * <p>
 * otherTips
 */

public class DialogUtil {
    //获取加载dialog
    public static Dialog getLoadingDialog(Fragment fragment, String title, boolean cancelable) {
        // SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//                .setTitleText(title+"");
//        pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(context, R.color.colorAccent));
//        pDialog.setCancelable(cancelable);
        Dialog dialog = new Dialog(fragment.getContext(), R.style.DialogStyle2);
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.layout_loading, null);
        ImageView ivLoading = view.findViewById(R.id.iv_loading);
        ImageUtil.getInstance().loadGif(fragment, R.drawable.loading, ivLoading);
        dialog.setContentView(view);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    //根据类型获取dialog
    public static Dialog getDialogByType(Context context, String title, String content, boolean cancleable, boolean cancleoutside, int type) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, type);
        pDialog.setTitleText(TextUtils.isEmpty(title) ? "Tips" : title);
        if (!TextUtils.isEmpty(content)) {
            pDialog.setContentText(content);
        }
        pDialog.setCancelable(cancleable);
        pDialog.setCanceledOnTouchOutside(cancleoutside);
        return pDialog;
    }

    //获取提示类型dialog
    public static Dialog getCommonTipsDialog(Context context, String title, String content) {
        return getDialogByType(context, title, content, true, true, SweetAlertDialog.NORMAL_TYPE);
    }

    //获取error类型dialog
    public static Dialog getErrorDialog(Context context, String title, String content) {
        return getDialogByType(context, title, content, true, true, SweetAlertDialog.ERROR_TYPE);
    }

    //获取success类型dialog
    public static Dialog getSuccessDialog(Context context, String title, String content) {
        return getDialogByType(context, title, content, true, true, SweetAlertDialog.SUCCESS_TYPE);
    }

    public static Dialog getTowButtonDialog
            (Context context,
             @NonNull String title,
             @NonNull String content,
             @NonNull String cancleText,
             @NonNull String confirmText,
             final OnDialogHandleListener listener) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText(title);
        pDialog.setContentText(content);
        pDialog.setCancelText(cancleText);
        pDialog.setConfirmText(confirmText);
        pDialog.showCancelButton(true);
        pDialog.setCanceledOnTouchOutside(true);
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if (listener != null) {
                    pDialog.dismiss();
                    listener.onCancleClick(sweetAlertDialog);
                }
            }
        });
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if (listener != null) {
                    pDialog.dismiss();
                    listener.onConfirmeClick(sweetAlertDialog);
                }
            }
        });
        return pDialog;
    }

    public static Dialog getSelectDialog(Context mContext, String text_1, String text_2, final OnSelectDialogListener listener) {
        final Dialog dialog = new Dialog(mContext, R.style.TakePhotoStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_take_photo_dialog, null);
        TextView tv_concle = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_camera = (TextView) view.findViewById(R.id.tv_camera);
        TextView tv_galary = (TextView) view.findViewById(R.id.tv_gallery);
        tv_camera.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
        tv_galary.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
        tv_camera.setText(text_1);
        tv_galary.setText(text_2);
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onBtnOneClick(dialog);
                }
            }
        });
        tv_galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnTwoClick(dialog);
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
        dialog.show();
        return dialog;
    }

    public interface OnSelectDialogListener {
        void onBtnOneClick(Dialog dialog);

        void onBtnTwoClick(Dialog dialog);
    }

    //dilalog 确定和取消操作
    public interface OnDialogHandleListener {
        void onCancleClick(SweetAlertDialog sDialog);

        void onConfirmeClick(SweetAlertDialog sDialog);
    }
}















