package cn.hym.superlib.utils.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;


public class DialogView extends Dialog {

    public DialogView(Context context, int themeResId, int layoutId, int gravity) {
        super(context, themeResId);
        setContentView(layoutId);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();



        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = gravity;

        window.setAttributes(params);

    }
}
