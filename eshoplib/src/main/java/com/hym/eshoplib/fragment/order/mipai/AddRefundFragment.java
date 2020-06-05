package com.hym.eshoplib.fragment.order.mipai;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.order.ReasonBean;
import com.hym.eshoplib.http.order.OrderApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.util.MipaiDialogUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/9/28.
 * <p>
 * Description 申请退款
 * <p>
 * otherTips
 */

public class AddRefundFragment extends BaseKitFragment{
    @BindView(R.id.tv_item)
    TextView tvItem;
    @BindView(R.id.et_count)
    ClearEditText etCount;
    Unbinder unbinder;
    String id;
    String reason_id;
    String reason_name;
    public static AddRefundFragment newInstance(Bundle args) {
        AddRefundFragment fragment = new AddRefundFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void doLogic() {
        id=getArguments().getString("id","");
        showBackButton();
        setTitle("申请退款");
        tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MipaiDialogUtil.dismiss();
                reason_id = "";
                final BaseListAdapter<ReasonBean.DataBean> adapter = new BaseListAdapter<ReasonBean.DataBean>(R.layout.item_check, null) {
                    public int select_position = -1;

                    @Override
                    public void handleView(BaseViewHolder helper, final ReasonBean.DataBean item, final int position) {
                        TextView tv_content = helper.getView(R.id.text);
                        if (select_position == position) {

                            tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_checked, 0);
                        } else {
                            tv_content.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cb_unchecked, 0);
                        }
                        ;
                        tv_content.setText(item.getMemo());
                        tv_content.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                select_position = position;
                                reason_id = item.getReason_id();
                                reason_name=item.getMemo();
                                notifyDataSetChanged();
                            }
                        });


                    }
                };
                ShopApi.GetReasonList("1", new ResponseImpl<ReasonBean>() {
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
                    public void onSuccess(ReasonBean data) {
                        adapter.setNewData(data.getData());
                        MipaiDialogUtil.showSpetificDialog(_mActivity, "请选择退款原因", adapter, "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //加入购物车
                                MipaiDialogUtil.dismiss();
                            }
                        }, "确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MipaiDialogUtil.dismiss();
                                tvItem.setText(reason_name);


                            }
                        }, false);
                    }
                }, ReasonBean.class);
            }
        });
        setRight_tv("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(reason_id)){
                    ToastUtil.toast("请选择退款原因");
                    return;
                }
                String money=etCount.getText().toString();
                if(TextUtils.isEmpty(money)){
                    ToastUtil.toast("请输入退款金额（请先与工作室沟通后再填写）");
                    return;
                }
                OrderApi.CustApply(id, reason_id, money, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        MipaiDialogUtil.refundSuccess(_mActivity, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MipaiDialogUtil.dismiss();
                                EventBus.getDefault().post(new UpdateDataEvent());
                                pop();
                            }
                        });


                    }
                },Object.class);
            }
        });
        etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d(s.toString());
                try {
                    if(s.toString().contains(".")){
                        String []result=s.toString().split("\\.");
                        Logger.d(result);
                        if(result[1].length()>2){
                            etCount.setText(s.toString().substring(0,s.toString().length()-1));
                            etCount.setSelection(s.toString().length()-1);
                            ToastUtil.toast("只能输入两位小数");
                        }
                    }
                }catch (Exception e){
                    Logger.d(e.toString());

                }

            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_add_refund;
    }
}
