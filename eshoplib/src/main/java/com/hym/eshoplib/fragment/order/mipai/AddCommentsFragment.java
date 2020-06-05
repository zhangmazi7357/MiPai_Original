package com.hym.eshoplib.fragment.order.mipai;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.order.CommentLableListBean;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 胡彦明 on 2018/9/20.
 * <p>
 * Description 添加评价
 * <p>
 * otherTips
 */

public class AddCommentsFragment extends BaseKitFragment {
    String log_id;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    Unbinder unbinder;
    String url;
    BaseListAdapter<CommentLableListBean.DataBean.LabelListBean> adapter;
    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;
    HashMap<Integer,String> ids=new HashMap<>();
    public static AddCommentsFragment newInstance(Bundle args) {
        AddCommentsFragment fragment = new AddCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_add_comment;
    }

    @Override
    public void doLogic() {
        showBackButton();
        log_id = getArguments().getString("id", "");
        url = getArguments().getString("url", "");
        ImageUtil.getInstance().loadCircleImage(AddCommentsFragment.this, url, ivShopLogo);
        setTitle("订单评价");
        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new BaseListAdapter<CommentLableListBean.DataBean.LabelListBean>(R.layout.item_label, null) {
            @Override
            public void handleView(BaseViewHolder helper, final CommentLableListBean.DataBean.LabelListBean item, final int position) {
                TextView btn_lable=helper.getView(R.id.btn_label);
                int width= (ScreenUtil.getScreenWidth(_mActivity)-ScreenUtil.dip2px(_mActivity,130))/3;
                btn_lable.getLayoutParams().width=width;
                btn_lable.getLayoutParams().height=width/2;
                btn_lable.setText(item.getLabel_name()+"");
                if(item.isSelect()){
                    btn_lable.setBackgroundResource(R.drawable.shape_grayb3985b_solid_conner5);
                    btn_lable.setTextColor(ContextCompat.getColor(_mActivity,R.color.white));
                    btn_lable.invalidate();
                }else {
                    btn_lable.setBackgroundResource(R.drawable.shape_grayf3ebd8_solid_conner5);
                    btn_lable.setTextColor(ContextCompat.getColor(_mActivity,R.color.mipaiTextColorSelect));
                    btn_lable.invalidate();
                }
                btn_lable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(item.isSelect()){
                            item.setSelect(false);
                            ids.remove(position);
                        }else {
                            if(ids.size()==3){
                                ToastUtil.toast("最多只可以选择3个标签");
                                return;
                            }
                            item.setSelect(true);
                            ids.put(position,item.getLabel_id());
                        }
                        adapter.notifyDataSetChanged();

                    }
                });


            }
        };
        rvList.setAdapter(adapter);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id="";
                if(TextUtils.isEmpty(log_id)){
                    ToastUtil.toast("数据异常");
                    return;
                }
//                if(ratingbar.getRating()==0){
//                    ToastUtil.toast("请选择星级");
//                    return;
//                }
                if(ids.size()==0){
                    ToastUtil.toast("请至少选择一个标签");
                    return;
                }
                Iterator<Integer>it=ids.keySet().iterator();
                while (it.hasNext()){
                    int key=it.next();
                    id+=ids.get(key)+",";
                }
                String rating=ratingbar.getRating()+"";
                Logger.d("id="+id+",rating="+rating+",log_id="+log_id);
                ShopApi.addComment(log_id, ratingbar.getRating() + "", id, new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("感谢您的评价，祝您生活愉快");
                        EventBus.getDefault().post( new UpdateDataEvent());
                        _mActivity.finish();
                    }
                },Object.class);


            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.GetCommentLabel(log_id,new ResponseImpl<CommentLableListBean>() {
            @Override
            public void onSuccess(CommentLableListBean data) {
                adapter.setNewData(data.getData().getLabel_list());

            }
        }, CommentLableListBean.class);


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
