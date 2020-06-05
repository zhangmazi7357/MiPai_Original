package com.hym.eshoplib.fragment.me.pocket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allen.library.SuperTextView;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.LogDetailBean;
import com.hym.eshoplib.http.me.MeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/10/8.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class LogDetailFragment extends BaseKitFragment {
    String log_id;
    @BindView(R.id.tv_1)
    SuperTextView tv1;
    @BindView(R.id.tv_2)
    SuperTextView tv2;
    @BindView(R.id.tv_3)
    SuperTextView tv3;
    @BindView(R.id.tv_4)
    SuperTextView tv4;
    @BindView(R.id.tv_5)
    SuperTextView tv5;
    @BindView(R.id.tv_6)
    SuperTextView tv6;
    @BindView(R.id.tv_remark)
    SuperTextView tvRemark;
    @BindView(R.id.tv_order_num)
    SuperTextView tvOrderNum;
    @BindView(R.id.tv_nick)
    SuperTextView tvNick;
    @BindView(R.id.tv_alipay_acount)
    SuperTextView tvAlipayAcount;
    @BindView(R.id.tv_realname)
    SuperTextView tvRealname;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    Unbinder unbinder;

    public static LogDetailFragment newInstance(Bundle args) {
        LogDetailFragment fragment = new LogDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_log_detail;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("明细详情");
        log_id = getArguments().getString("id");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MeApi.BeanDetail(log_id, new ResponseImpl<LogDetailBean>() {
            @Override
            public void onSuccess(LogDetailBean data) {
                tv1.setRightString(data.getData().getLog_number()+"");//流水号
                tv2.setRightString(data.getData().getCause()+"");//类型
                tv3.setRightString(data.getData().getValue()+"");//变动值
                tv5.setRightString(data.getData().getCtime()+"");//时间
                tv6.setRightString(data.getData().getAfter_value()+"");//余额
                tvRemark.setRightString(data.getData().getRemark()+"");//备注
                tvOrderNum.setRightString(data.getData().getOrder_number()+"");//订单编号
                String type=data.getData().getType();
                //类型1：充值， 2：消费， 3：收入，4：提现，5：收入（退款）
                switch (type){
                    case "1":
                        tv3.setLeftString("收入");
//                        tv4.setVisibility(View.VISIBLE);
//                        tv4.setLeftString("支付方式");
//                        tv4.setRightString(data.getData().getPaytype().equals("1")?"支付宝":"微信");
                        break;
                    case "2":
                        //在线支付
                        tv3.setLeftString("支出");
                        break;
                    case "3":
                        tv3.setLeftString("收入");
                        break;
                    case "4":
                        tv3.setLeftString("支出");
                        tv4.setVisibility(View.VISIBLE);
                        tv4.setLeftString("支付方式");
                        tv4.setRightString(data.getData().getPaytype().equals("1")?"支付宝":"微信");
                        //提现显示支付宝账号
                        tvOrderNum.setVisibility(View.GONE);
                        llAlipay.setVisibility(View.VISIBLE);
                        tvNick.setRightString(data.getData().getDeposit_bank()+"");//支付宝昵称
                        tvAlipayAcount.setRightString(data.getData().getPay_account()+"");
                        tvRealname.setRightString(data.getData().getAccount()+"");//真实姓名
                        break;
                    case "5":
                        tv3.setLeftString("收入");
                        break;
                }

            }
        }, LogDetailBean.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
