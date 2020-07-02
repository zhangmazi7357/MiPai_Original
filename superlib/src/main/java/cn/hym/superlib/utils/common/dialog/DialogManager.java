package cn.hym.superlib.utils.common.dialog;

import android.app.Dialog;
import android.content.Context;

import cn.hym.superlib.R;

public class DialogManager {

    private static DialogManager INSTANCE = null;


    private DialogManager() {
    }

    public static DialogManager getInstance() {

        if (INSTANCE == null) {
            synchronized (DialogManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DialogManager();
                }
            }

        }
        return INSTANCE;
    }


    public DialogView initView(Context mContext, int layoutId, int gravity) {

        return new DialogView(mContext, R.style.MyDialogView, layoutId, gravity);
    }


    public void show(Dialog dialog) {
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    public void hide(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * 简单 dialog
     *
     * @param context
     * @param title
     * @param content
     * @param cancelText
     * @param sureText
     * @param listener
     * @return
     */
    public SimpleDialog initSimpleDialog(Context context,
                                         String title,
                                         String content,
                                         String cancelText,
                                         String sureText,
                                         SimpleDialog.SimpleDialogOnClickListener listener) {

        SimpleDialog simpleDialog = new SimpleDialog(context, title, content, cancelText, sureText);

        simpleDialog.setListener(listener);
        return simpleDialog;

    }


}
