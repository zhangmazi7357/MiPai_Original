package com.hym.eshoplib.fragment.me;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.me.MyInviteListBean;
import com.hym.eshoplib.http.me.MeApi;
import com.hym.imagelib.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/6.
 * <p>
 * Description我的邀请码
 * <p>
 * otherTips
 */

public class MyInviteCodeFragment extends BaseKitFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_my_invite_person)
    TextView tvMyInvitePerson;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    BaseListAdapter<MyInviteListBean.DataBean.ListBean>adaper;
    public static MyInviteCodeFragment newInstance(Bundle args) {
        MyInviteCodeFragment fragment = new MyInviteCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_invite_code;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        setTitle("邀请码");
        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=tvCode.getText().toString();
                if(!TextUtils.isEmpty(code)){
                    ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setPrimaryClip(ClipData.newPlainText(null, code));
                    ToastUtil.toast("复制成功");
                }else {
                    ToastUtil.toast("数据异常请检查您的网络后重试");
                }
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(_mActivity));
        adaper=new BaseListAdapter<MyInviteListBean.DataBean.ListBean>(R.layout.item_my_invite_person,null) {
            @Override
            public void handleView(BaseViewHolder helper, MyInviteListBean.DataBean.ListBean item, int position) {
                ImageUtil.getInstance().loadCircleImage(MyInviteCodeFragment.this,item.getAvatar(), (ImageView) helper.getView(R.id.iv_icon));
                helper.setText(R.id.tv_name,item.getNickname()+"");
                helper.setText(R.id.tv_time,item.getCtime()+"");



            }
        };
        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.view_empty_shoppingcart, null, false);
        ImageView iv_icon=emptyView.findViewById(R.id.iv_icon);
        iv_icon.setVisibility(View.GONE);
        TextView tv_message=emptyView.findViewById(R.id.tv_message);
        tv_message.setText("快去邀请小伙伴加入觅拍吧～");
        tv_message.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_b9b9b9));
        adaper.setEmptyView(emptyView);
        rvList.setAdapter(adaper);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MeApi.GetInviteList(new ResponseImpl<MyInviteListBean>() {
            @Override
            public void onSuccess(MyInviteListBean data) {
                if(data.getData().getIs_set()!=1){
                    setRight_tv("填写邀请码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle=new Bundle();
                            bundle.putString("code",tvCode.getText().toString());
                            startForResult(BindInviteCodeFragment.newInstance(bundle),0x01);

                        }
                    });
                }else {
                    setRight_tv("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
                tvCode.setText(data.getData().getInvitecode()+"");
                adaper.setNewData(data.getData().getList());

            }
        },MyInviteListBean.class);

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
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode==0x01){
            initData(null);
        }
    }
}
