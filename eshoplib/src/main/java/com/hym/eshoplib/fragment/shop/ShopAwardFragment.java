package com.hym.eshoplib.fragment.shop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 获奖信息
 * <p>
 * otherTips
 */

public class ShopAwardFragment extends BaseKitFragment {
    @BindView(R.id.et_award)
    ClearEditText etAward;
    @BindView(R.id.tv_award_card)
    TextView tvAwardCard;
    Unbinder unbinder;
    ShopDetailBean data;
    String ids;
    public static ShopAwardFragment newInstance(Bundle args) {
        ShopAwardFragment fragment = new ShopAwardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_shop_award;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("获奖情况");
        data= (ShopDetailBean) getArguments().getSerializable("data");
        if(data==null){
            ToastUtil.toast("数据异常，请检查您的网络后重试");
            pop();
            return;
        }
        String award=data.getData().getAwards().getAwards();
        if(!TextUtils.isEmpty(award)){
            etAward.setText(award);
        }
        if(data.getData().getAwards().getAttachment()==null||data.getData().getAwards().getAttachment().size()==0){
            //tvAwardCard.setHint("未上传");
        }else {
            //tvAwardCard.setHint("已上传");
        }
        tvAwardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",data);
                startForResult(UploadAwardCardFragment.newInstance(bundle),0x01);
            }
        });
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopApi.EditShop2(null, null, null,
                        null, null, null,
                        null,null, etAward.getText().toString(),
                        null, ids, null,new ResponseImpl<Object>() {
                            @Override
                            public void onSuccess(Object data) {
                                 ToastUtil.toast("修改成功");
                                pop();
                            }
                        }, Object.class);


            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if(resultCode==RESULT_OK){
            if(requestCode==0x01){
                ids=data.getString("ids","");
                Logger.d("url="+ids);
            }
        }
    }
}
