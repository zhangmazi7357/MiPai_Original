package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.bean.shop.ServiceDetailBean;

import java.util.ArrayList;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;

import static cn.hym.superlib.activity.base.BaseActionActivity.getActionBundle;

/**
 * Created by 胡彦明 on 2018/8/17.
 * <p>
 * Description 工作室详情
 * <p>
 * otherTips
 */

public class ShopDetailFragment extends BaseListFragment<String> {
    TextView tvSeeCertificate;
    TextView tvSeeShcool;
    TextView tv_des,tv_work,tv_award_name,tv_study_level;
    ServiceDetailBean data;
    TextView tv_renzheng_1,tv_renzheng_2,tv_zhuanye,tv_xueli;
    LinearLayout ll_gender,ll_age,ll_height,ll_weight;
    TextView tv_gender,tv_age,tv_height,tv_weight;
    View diver_1,diver_2,diver_3,diver_4;

    public static ShopDetailFragment newInstance(Bundle args) {
        ShopDetailFragment fragment = new ShopDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getItemRestId() {
        return R.layout.item_comments;
    }

    @Override
    public void excuteLogic() {
        data= (ServiceDetailBean) getArguments().getSerializable("data");
        getRefreshLayout().setEnabled(false);
        View header= LayoutInflater.from(_mActivity).inflate(R.layout.header_shop_detail,null,false);
        tv_renzheng_1=header.findViewById(R.id.tv_renzheng_1);
        tv_renzheng_2=header.findViewById(R.id.tv_renzheng_2);
        tv_xueli=header.findViewById(R.id.tv_xueli);
        tv_zhuanye=header.findViewById(R.id.tv_zhuanye);
        tv_des=header.findViewById(R.id.tv_des);
        tv_work=header.findViewById(R.id.tv_work);
        tv_award_name=header.findViewById(R.id.tv_award_name);
        tv_study_level=header.findViewById(R.id.tv_study_level);
        tvSeeCertificate=header.findViewById(R.id.tv_see_certificate);
        tvSeeShcool=header.findViewById(R.id.tv_see_shcool);
        tvSeeCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data==null||data.getData().getAwards()==null){
                    ToastUtil.toast("工作室没有提供获奖证书");
                    return;
                }
                Bundle bundle=getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Certificate);
                bundle.putStringArrayList("data", (ArrayList<String>) data.getData().getAwards());
                ActionActivity.start(_mActivity, bundle);
            }
        });
        tvSeeShcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data==null||data.getData().getUniver()==null){
                    ToastUtil.toast("工作室没有提供学历证明");
                    return;
                }
                Bundle bundle=getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Certificate);
                bundle.putStringArrayList("data", (ArrayList<String>) data.getData().getUniver());
                ActionActivity.start(_mActivity, bundle);
            }
        });
        ll_gender=header.findViewById(R.id.ll_gender);
        ll_age=header.findViewById(R.id.ll_age);
        ll_height=header.findViewById(R.id.ll_height);
        ll_weight=header.findViewById(R.id.ll_weight);

        tv_gender=header.findViewById(R.id.tv_gender);
        tv_age=header.findViewById(R.id.tv_age);
        tv_height=header.findViewById(R.id.tv_height);
        tv_weight=header.findViewById(R.id.tv_weight);

        diver_1=header.findViewById(R.id.diver_1);
        diver_2=header.findViewById(R.id.diver_2);
        diver_3=header.findViewById(R.id.diver_3);
        diver_4=header.findViewById(R.id.diver_4);


        getAdapter().addHeaderView(header);
        setData();

    }

    private void setData() {
        if(data==null){
            return;
        }
        tv_des.setText(TextUtils.isEmpty(data.getData().getRemark())?"暂无":data.getData().getRemark());//个人简介
        tv_work.setText(TextUtils.isEmpty(data.getData().getJob())?"暂无":data.getData().getJob());
        tv_award_name.setText(TextUtils.isEmpty(data.getData().getAwards_memo())?"暂无":data.getData().getAwards_memo());
        tv_study_level.setText(TextUtils.isEmpty(data.getData().getUniversity())?"暂无":data.getData().getUniversity());
        tv_xueli.setText(TextUtils.isEmpty(data.getData().getEducation())?"暂无":data.getData().getEducation());
        tv_zhuanye.setText(TextUtils.isEmpty(data.getData().getMajor())?"暂无":data.getData().getMajor());
        if(data.getData().getCertificate_auth().equals("1")){
            tv_renzheng_1.setText("已认证");
        }else {
            tv_renzheng_1.setText("未认证");
        }
        if(data.getData().getXuelizs_auth().equals("1")){
            tv_renzheng_2.setText("已认证");
        }else {
            tv_renzheng_2.setText("未认证");
        }
        if(data.getData().getCategory_id().equals("46")){
            ll_gender.setVisibility(View.VISIBLE);
            ll_age.setVisibility(View.VISIBLE);
            ll_height.setVisibility(View.VISIBLE);
            ll_weight.setVisibility(View.VISIBLE);
            diver_1.setVisibility(View.VISIBLE);
            diver_2.setVisibility(View.VISIBLE);
            diver_3.setVisibility(View.VISIBLE);
            diver_4.setVisibility(View.VISIBLE);
            String gender="";
            if(data.getData().getGender().equals("1")){
                gender="男";
            }
            if(data.getData().getGender().equals("2")){
                gender="女";
            }
            tv_gender.setText(gender);
            tv_age.setText(data.getData().getAge()+"岁");
            tv_height.setText(data.getData().getHeight()+"cm");
            tv_weight.setText(data.getData().getWeight()+"kg");
        }


    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        getRefreshLayout().setRefreshing(false);

    }

    @Override
    public void bindData(BaseViewHolder helper, String item, int position) {

    }

    @Override
    public boolean showToolBar() {
        return false;
    }


//    @OnClick({R.id.tv_see_certificate, R.id.tv_see_shcool})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_see_shcool:
//                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Certificate));
//                break;
//            case R.id.tv_see_certificate:
//                ActionActivity.start(_mActivity, getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_Certificate));
//                break;
//        }
//    }
}
