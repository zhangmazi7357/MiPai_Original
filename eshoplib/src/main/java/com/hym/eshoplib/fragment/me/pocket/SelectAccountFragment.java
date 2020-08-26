package com.hym.eshoplib.fragment.me.pocket;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.me.GetCashAccountBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.eshoplib.util.MipaiDialogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.common.dialog.DialogManager;
import cn.hym.superlib.utils.common.dialog.SimpleDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 选择提现账户
 * <p>
 * otherTips
 */

public class SelectAccountFragment extends BaseListFragment<GetCashAccountBean.DataBean> {
    public int select_position = -1;
    String account_id;

    public static SelectAccountFragment newInstance(Bundle args) {
        SelectAccountFragment fragment = new SelectAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_getcash_account;
    }

    @Override
    public void excuteLogic() {
        account_id = getArguments().getString("id", "");
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选中账户
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", getAdapter().getData().get(position));
                setFragmentResult(RESULT_OK, bundle);
                pop();
            }
        });
        setContainerColor(R.color.resource_gray_f1f1f1);
        setTitle("选择提现账户");
        showBackButton();
        getVs_footer().setLayoutResource(R.layout.footer_btn);
        View view = getVs_footer().inflate();
        TextView tv_upload = view.findViewById(R.id.tv_upload);
        tv_upload.setText("添加提现账户");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加提现账户
                MipaiDialogUtil.dismiss();
                final BaseListAdapter<String> adapter = new BaseListAdapter<String>(R.layout.item_check, null) {
                    @Override
                    public void handleView(BaseViewHolder helper, final String item, final int position) {
                        TextView tv_content = helper.getView(R.id.text);
                        if (select_position == position) {

                            tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_checked, 0);
                        } else {
                            tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_unchecked, 0);
                        }
                        ;
                        tv_content.setText(item);
                        tv_content.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                select_position = position;
                                notifyDataSetChanged();
                            }
                        });


                    }
                };
                adapter.getData().add("支付宝账户");
                adapter.getData().add("银行卡账户");
                adapter.notifyDataSetChanged();
                MipaiDialogUtil.showSpetificDialog(_mActivity, "请选择提现账户类型", adapter, "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MipaiDialogUtil.dismiss();
                    }
                }, "确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MipaiDialogUtil.dismiss();
                        Bundle bundle = new Bundle();
                        if (select_position == 0) {
                            MipaiDialogUtil.dismiss();
                            // ToastUtil.toast("支付宝");
                            bundle.putInt("type", 0);
                            startForResult(AddGetCashAccountFragment.newInstance(bundle), 0x01);
                        } else if (select_position == 1) {
                            MipaiDialogUtil.dismiss();
                            //ToastUtil.toast("银行卡号");
                            bundle.putInt("type", 1);
                            startForResult(AddGetCashAccountFragment.newInstance(bundle), 0x01);
                        } else {
                            ToastUtil.toast("请选择类型");
                        }


                    }
                }, false);

            }
        });

    }

    @Override
    public void getData(final boolean refresh, int pageSize, int pageNum) {
        MeApi.GetBank(new ResponseImpl<GetCashAccountBean>() {
            @Override
            public void onSuccess(GetCashAccountBean data) {
                getAdapter().setNewData(data.getData());

            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        }, GetCashAccountBean.class);

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void bindData(BaseViewHolder helper, GetCashAccountBean.DataBean item, final int position) {
        TextView tv_1 = helper.getView(R.id.tv_1);
        TextView tv_2 = helper.getView(R.id.tv_2);
        TextView tv_3 = helper.getView(R.id.tv_3);
        TextView tv_4 = helper.getView(R.id.tv_4);
        TextView tv_5 = helper.getView(R.id.tv_5);
        if (item.getType().equals("1")) {
            //支付宝
            tv_1.setText("支付宝账号");
            tv_2.setText("支付宝昵称：" + item.getBankname());
            tv_3.setText("支付宝账号：" + item.getBankcard());
            tv_4.setText("真实姓名：" + item.getBankuser());
            tv_5.setText("支付宝绑定手机号：" + item.getPhone());
        } else if (item.getType().equals("2")) {
            //银行卡号
            tv_1.setText("银行卡号");
            tv_2.setText("银行卡号：" + item.getBankcard());
            tv_3.setText("开户银行：" + item.getBankname());
            tv_4.setText("真实姓名：" + item.getBankuser());
            tv_5.setText("银行预留手机号：" + item.getPhone());

        }
        ImageView iv_select = helper.getView(R.id.iv_select);
        if (account_id.equals(item.getUserbank_id())) {
            iv_select.setVisibility(View.VISIBLE);
        } else {
            iv_select.setVisibility(View.GONE);
        }
        ImageView iv_more = helper.getView(R.id.iv_more);
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.getSelectDialog(_mActivity, "编辑", "删除", new DialogUtil.OnSelectDialogListener() {
                    @Override
                    public void onBtnOneClick(final Dialog dialog) {
                        //编辑
                        dialog.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", getAdapter().getData().get(position));
                        bundle.putBoolean("isedit", true);
                        if (getAdapter().getData().get(position).getType().equals("1")) {
                            //编辑支付宝
                            bundle.putInt("type", 0);
                            startForResult(AddGetCashAccountFragment.newInstance(bundle), 0x01);
                        } else {
                            //编辑银行卡
                            bundle.putInt("type", 1);
                            startForResult(AddGetCashAccountFragment.newInstance(bundle), 0x01);
                        }

                    }

                    @Override
                    public void onBtnTwoClick(Dialog dialog) {
                        //删除
                        dialog.dismiss();
                        String confirm = getResources().getString(R.string.Confirm);
                        String cancle = getResources().getString(R.string.Cancel);


                        DialogManager.getInstance().initSimpleDialog(_mActivity, "提示",
                                "您确定要删这个账号么", cancle, confirm,
                                new SimpleDialog.SimpleDialogOnClickListener() {
                                    @Override
                                    public void negativeClick(Dialog dialog) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void positiveClick(Dialog dialog) {
                                        dialog.dismiss();
                                        MeApi.DelBank(getAdapter().getData().get(position).getUserbank_id(),
                                                new ResponseImpl<Object>() {
                                                    @Override
                                                    public void onStart(int mark) {
                                                        setShowProgressDialog(true);
                                                        super.onStart(mark);
                                                    }

                                                    @Override
                                                    public void onFinish(int mark) {
                                                        super.onFinish(mark);
                                                        setShowProgressDialog(false);
                                                    }

                                                    @Override
                                                    public void onSuccess(Object data) {
                                                        onRefresh();

                                                    }


                                                }, Object.class);

                                    }
                                })
                                .show();


                    }
                });
            }
        });


    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateData(UpdateDataEvent event) {
        onRefresh();
    }
}
