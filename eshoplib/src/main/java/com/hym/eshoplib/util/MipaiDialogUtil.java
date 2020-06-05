package com.hym.eshoplib.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.listener.GoToPayDialogInterface;
import com.hym.eshoplib.widgets.PayPsdInputView;

import cn.hym.superlib.widgets.snapstep.SnappingStepper;
import cn.hym.superlib.widgets.snapstep.listener.SnappingStepperValueChangeListener;


/**
 * 作者：王东一 on 2016/4/5 14:51
 * update:胡彦明
 **/
public class MipaiDialogUtil {
    private static Dialog dialog = null;
    private static int count;

    //提示类对话框
    public static void showTipsDialog(Context mContext) {
        dialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tips_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();
    }

    public static void showUpdateDialog(Context mContext, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        dialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_upload_dialog, null);
        View line = view.findViewById(R.id.v_line);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        TextView textViewContent = (TextView) view.findViewById(R.id.tv_title);
        TextView textViewSubtitle = (TextView) view.findViewById(R.id.tv_message);
        TextView textViewLeft = (TextView) view.findViewById(R.id.tv_left);
        TextView textViewRight = (TextView) view.findViewById(R.id.tv_right);
        if (leftListener == null) {
            textViewLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        } else {
            textViewLeft.setOnClickListener(leftListener);
        }
        if (rightListener == null) {
            textViewRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        } else {
            textViewRight.setOnClickListener(rightListener);
        }
        dialog.show();
    }

    //支付方式选择
    public static void showPayTypeDialog(Context mContext, RecyclerView.Adapter<?> adapter, @DrawableRes @NonNull int btn_bg, String title, View.OnClickListener listener_ok) {
        dialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pay_type_dialog, null);
        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_ok.setBackgroundResource(btn_bg);
        if (listener_ok != null) {
            btn_ok.setOnClickListener(listener_ok);
        }
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        if (!TextUtils.isEmpty(title)) {
            tv_cancle.setText(title);
        }
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        if (adapter != null) {
            rv_list.setAdapter(adapter);
        }
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        dialog.show();

    }

    //选择规格
    public static void showSpetificDialog(Context mContext, String title, RecyclerView.Adapter<?> adapter, String leftstr, View.OnClickListener left, String rightstr, View.OnClickListener listener_ok, boolean showCancleIcon) {
        dialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pay_type_dialog, null);
        Button btn_left = view.findViewById(R.id.btn_left);
        View diver = view.findViewById(R.id.view_diver);
        if (left != null) {
            diver.setVisibility(View.VISIBLE);
            btn_left.setOnClickListener(left);
            btn_left.setVisibility(View.VISIBLE);
        } else {
            btn_left.setVisibility(View.GONE);
            diver.setVisibility(View.GONE);
        }
        Button btn_ok = view.findViewById(R.id.btn_ok);
        if (listener_ok != null) {
            btn_ok.setOnClickListener(listener_ok);
        }
        if (!TextUtils.isEmpty(leftstr)) {
            btn_left.setText(leftstr);
        }
        if (!TextUtils.isEmpty(rightstr)) {
            btn_ok.setText(rightstr);
        }
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        if (!showCancleIcon) {
            tv_cancle.setCompoundDrawables(null, null, null, null);
        }
        tv_cancle.setText(title);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        if (adapter != null) {
            rv_list.setAdapter(adapter);
        }
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        dialog.show();

    }

    public static PayPsdInputView showInputPwdDialog(Context mContext, String money, String sub, PayPsdInputView.onPasswordListener listener, View.OnClickListener forgetPwd) {
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_intput_pwd_dialog, null);
        TextView tv_money = view.findViewById(R.id.tv_money);
        TextView tv_sub = view.findViewById(R.id.tv_sub_title);
        tv_money.setText(money);
        if (!TextUtils.isEmpty(sub)) {
            tv_sub.setText(sub);
            tv_sub.setVisibility(View.VISIBLE);
        } else {
            tv_sub.setVisibility(View.GONE);
        }
        TextView tv_forget_pwd = view.findViewById(R.id.tv_forget_password);
        tv_forget_pwd.setOnClickListener(forgetPwd);
        PayPsdInputView psdInputView = view.findViewById(R.id.password);
        psdInputView.setComparePassword(listener);
        psdInputView.requestFocus();
        dialog.setContentView(view);
        // dialog.setCancelable(false);
//        Window win = dialog.getWindow();
//        win.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        win.setAttributes(lp);
        dialog.show();
        return psdInputView;

    }

    public static void showRiseLevelDialog(Context mContext, View.OnClickListener listener1, View.OnClickListener listener2) {
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_rise_level, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView tv_1 = view.findViewById(R.id.tv_1);
        tv_1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        if (listener1 != null) {
            tv_1.setOnClickListener(listener1);
        }
        if (listener2 != null) {
            tv_2.setOnClickListener(listener2);
        }
        dialog.setContentView(view);

        dialog.show();


    }

    //修改价格
    public static void showChangePrice(Context mContext, TextWatcher watcher, View.OnClickListener onClickListener) {
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_change_price, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        EditText editText = view.findViewById(R.id.et_price);
        editText.addTextChangedListener(watcher);
        TextView tv_confim = view.findViewById(R.id.tv_confirm);
        tv_confim.setOnClickListener(onClickListener);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        dialog.setContentView(view);
        dialog.show();


    }

    //提交退款成功
    public static void refundSuccess(Context mContext, View.OnClickListener onClickListener) {
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_refund_success, null);
        ImageView ivclose = view.findViewById(R.id.iv_close);
        ivclose.setOnClickListener(onClickListener);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();


    }

    //申请售后
    public static void customService(Context mContext, View.OnClickListener onClickListener) {
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_service, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(onClickListener);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();


    }


    public static void dismiss() {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static Dialog getAuthDialog(Context mContext, String title, String msg, final OnBtnSlectListener listener) {
        dialog = new Dialog(mContext, R.style.TakePhotoStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_auth_dialog, null);
        TextView tv_concle = (TextView) view.findViewById(R.id.tv_cancle);
        TextView tv_camera = (TextView) view.findViewById(R.id.tv_1);
        TextView tv_galary = (TextView) view.findViewById(R.id.tv_2);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_title.setText(title);
        tv_msg.setText(msg);
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.on1(dialog);
                }
            }
        });
        tv_galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.on2(dialog);
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

    public interface OnBtnSlectListener {
        void on1(Dialog dialog);

        void on2(Dialog dialog);
    }

    public static void showGoToPayDialog(Context mContext, String present_price, String title, final GoToPayDialogInterface goToPayListener) {
        count = 1;
        dialog = new Dialog(mContext, R.style.DialogStyleCommon);
        dialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_gotopay_dialog, null);
        TextView tvGoToPay = view.findViewById(R.id.tv_go_to_pay);
        ImageView ic_back = view.findViewById(R.id.ic_back);
        TextView tvPrice = view.findViewById(R.id.tv_price);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvPrice.setText(present_price + "元");
        SnappingStepper stepper = view.findViewById(R.id.stepper);
        stepper.setValue(count);
        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                count = value;
                // tvCurrentGoods.setText("\"" + name + "\"" + "数量x" + count);
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        tvGoToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPayListener.getCoutn(count);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setGravity(Gravity.BOTTOM);
        win.setAttributes(lp);
        dialog.setContentView(view);
        dialog.show();
    }
}
