package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/9/21.
 * <p>
 * Description 工作室信息
 * <p>
 * otherTips
 */

public class ShopInfoFragment extends BaseKitFragment {
    @BindView(R.id.tv_info)
    RequiredTextView tvInfo;
    @BindView(R.id.tv_card_info)
    RequiredTextView tvCardInfo;
    @BindView(R.id.tv_work_info)
    RequiredTextView tvWorkInfo;
    @BindView(R.id.tv_return)
    TextView tvReturn;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_study)
    RequiredTextView tvStudy;
    @BindView(R.id.tv_circle)
    TextView tvCircle;
    @BindView(R.id.tv_prize)
    RequiredTextView tvPrize;
    Unbinder unbinder;
    @BindView(R.id.tv_actor_info)
    RequiredTextView tvActorInfo;
    @BindView(R.id.vider_actor_info)
    View viderActorInfo;

    public static ShopInfoFragment newInstance(Bundle args) {
        ShopInfoFragment fragment = new ShopInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ShopDetailBean data;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_info;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
//        tvInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (data == null) {
//                    ToastUtil.toast("数据异常");
//                    return;
//                }
//                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_chage_price);
//                bundle.putString("id", data.getData().getBase().getCategory_id());
//                ActionActivity.start(_mActivity, bundle);
//            }
//        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
            @Override
            public void onSuccess(ShopDetailBean data) {
                ShopInfoFragment.this.data = data;
                bindData();

            }
        }, ShopDetailBean.class);

    }

    private void bindData() {
        if (ShopInfoFragment.this.data == null) {
            return;
        }
        ((ShopInfoPagerFragment) getParentFragment()).setToken(data.getData().getQiniu_token());
        String refundType = data.getData().getRefund();
        switch (refundType) {
            //退款情况，1：接收不满意全额退款，2：接收不满意部分退款，3：定制产品不接受退款
            case "1":
                tvReturn.setText("接受不满意全额退款");
                break;
            case "2":
                tvReturn.setText("接受不满意部分退款");
                break;
            case "3":
                tvReturn.setText("定制产品不接受退款");
                break;
            case "0":
                tvReturn.setText("定制产品不接受退款");
                break;
        }
        tvInvoice.setText(data.getData().getInvoice().equals("1") ? "可开发票" : "不提供发票");
        tvCircle.setText(TextUtils.isEmpty(data.getData().getJob()) ? "未填写" : "已填写");
        if(data.getData().getBase().getCategory_id().equals("46")){
            tvActorInfo.setVisibility(View.VISIBLE);
            viderActorInfo.setVisibility(View.VISIBLE);
            tvActorInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_actor_info);
                    bundle.putInt("type",1);
                    ActionActivity.start(_mActivity, bundle);
                }
            });
        }


    }

    @Override
    public boolean showToolBar() {
        return false;
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

    @OnClick({R.id.tv_info, R.id.tv_card_info, R.id.tv_work_info, R.id.tv_return, R.id.tv_invoice, R.id.tv_study, R.id.tv_circle, R.id.tv_prize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_info:
                //进入工作室基本信息
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editBaseInfo);
                bundle.putSerializable("data", data);
                ActionActivity.start(_mActivity, bundle);
                break;
            case R.id.tv_card_info:
                //身份证信息
                Bundle bundle_card = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editCardInfo);
                bundle_card.putSerializable("data", data);
                ActionActivity.start(_mActivity, bundle_card);
                break;
            case R.id.tv_work_info:
                //从业信息
                Bundle bundle_work = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editWorkInfo);
                bundle_work.putSerializable("data", data);
                ActionActivity.start(_mActivity, bundle_work);
                break;
            case R.id.tv_return:
                //退款情况
                ActionActivity.start(_mActivity, getBundle(1));
                break;
            case R.id.tv_invoice:
                //发票
                ActionActivity.start(_mActivity, getBundle(2));
                break;
            case R.id.tv_study:
                //学历
                Bundle bundle_study = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editStudyInfo);
                bundle_study.putSerializable("data", data);
                ActionActivity.start(_mActivity, bundle_study);
                break;
            case R.id.tv_circle:
                Bundle bundle_circle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editCircleInfo);
                bundle_circle.putString("des", data.getData().getJob());
                ActionActivity.start(_mActivity, bundle_circle);
                break;
            case R.id.tv_prize:
                //获奖情况
                Bundle bundle_award = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_editAwardInfo);
                bundle_award.putSerializable("data", data);
                ActionActivity.start(_mActivity, bundle_award);
                break;
        }
    }

    private Bundle getBundle(int i) {
        Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Shop_commonselect);
        bundle.putBoolean("isedit", true);
        switch (i) {
            case 1:
                //退款
                bundle.putInt("type", 1);
                bundle.putString("text", tvReturn.getText().toString());
                break;
            case 2:
                //发票
                bundle.putInt("type", 2);
                bundle.putString("text", tvInvoice.getText().toString());
                break;

        }
        return bundle;
    }

    @Override
    public boolean openEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(UpdateDataEvent event) {
        initData(null);
    }
}
