package cn.hym.superlib.utils.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import cn.hym.superlib.R;

/**
 * 搞一个简单点 通用的 dialog;
 */
public class SimpleDialog extends Dialog {

    private TextView title;
    private TextView content;
    private Button cancelBtn;
    private Button sureBtn;

    private SimpleDialogOnClickListener listener;

    public void setListener(SimpleDialogOnClickListener listener) {
        this.listener = listener;
    }

    public SimpleDialog(Context context, String titleStr, String contentStr,
                        String cancelStr, String sureStr) {
        super(context, R.style.MyDialogView);
        setContentView(R.layout.dialog_sample_view);
        title = findViewById(R.id.simple_dialog_title);
        content = findViewById(R.id.simple_dialog_content);
        cancelBtn = findViewById(R.id.simple_dialog_cancel);
        sureBtn = findViewById(R.id.simple_dialog_sure);

        if (!TextUtils.isEmpty(titleStr)) {
            title.setText(titleStr);
        }

        if (!TextUtils.isEmpty(contentStr)) {
            content.setText(contentStr);
        }

        if (!TextUtils.isEmpty(cancelStr)) {
            cancelBtn.setText(cancelStr);
        }
        if (!TextUtils.isEmpty(sureStr)) {
            sureBtn.setText(sureStr);
        }

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

//        params.width = WindowManager.LayoutParams.MATCH_PARENT;

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager localWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        localWindowManager.getDefaultDisplay().getMetrics(dm);
        params.width = (int) (dm.widthPixels * 0.8);


        window.setAttributes(params);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.negativeClick(SimpleDialog.this);
                }

            }
        });
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.positiveClick(SimpleDialog.this);
            }
        });


    }

    public interface SimpleDialogOnClickListener {
        // 消极的
        void negativeClick(Dialog dialog);

        // 积极地
        void positiveClick(Dialog dialog);
    }
}
