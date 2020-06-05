package com.hym.eshoplib.fragment.me.Openshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.http.shopapi.ShopApi;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/8/29.
 * <p>
 * Description 退款情况 是否提供发票 学历
 * <p>
 * otherTips
 */

public class CommonSelectFragment extends BaseListFragment<String>{
    ArrayList<String>data=new ArrayList<>();
    int type=-1;
    String current_item="";
    public int current_position=-1;
    public static CommonSelectFragment newInstance(Bundle args) {
        CommonSelectFragment fragment = new CommonSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }
    boolean isedit=false;
    @Override
    public int getItemRestId() {
        return R.layout.item_check;
    }

    @Override
    public void excuteLogic() {
        showBackButton();
        type=getArguments().getInt("type",-1);
        isedit=getArguments().getBoolean("isedit",false);
        current_item=getArguments().getString("text","");
        String title="";
        switch (type){
            case 1:
                //退款情况
                title="请选择退款情况";
                data.add("接受不满意全额退款");
                data.add("接受不满意部分退款");
                data.add("定制产品不接受退款");
                break;
            case 2:
                //是否提供发票
                title="请选择是否提供发票";
                data.add("可开发票");
                data.add("不提供发票");
                break;
            case 3:
                //学历
                title="请选择学历";
                data.add("高中");
                data.add("中技");
                data.add("中专");
                data.add("大专");
                data.add("本科");
                data.add("硕士");
                data.add("MBA");
                data.add("MFA");
                data.add("博士");
                break;
        }
        setTitle(title);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(isedit){
                    current_position=position;
                    current_item= getAdapter().getData().get(position);
                    getAdapter().notifyDataSetChanged();
                    return;
                }
                Bundle bundle=new Bundle();
                bundle.putInt("type",type);
                bundle.putInt("position",position);
                bundle.putString("text",getAdapter().getData().get(position));
                setFragmentResult(RESULT_OK,bundle);
                pop();

            }
        });
        if(isedit){
            //编辑
            setRight_tv("保存", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (type){
                        case 1:
                            //退款情况
                            if(current_position==-1){
                                ToastUtil.toast("请选择退款情况");
                                return;
                            }
                            String  refund_type="";
                            if(current_position==2){
                                refund_type="0";
                            }else {
                                refund_type=(current_position+1)+"";
                            }
                            ShopApi.EditShop2(null, null, null,
                                    refund_type, null, null,
                                    null, null,null,
                                    null, null, null,new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            EventBus.getDefault().post(new UpdateDataEvent());
                                            _mActivity.finish();
                                        }
                                    }, Object.class);
                            break;
                        case 2:
                            //是否提供发票
                            ShopApi.EditShop2(null, null, null,
                                    null, current_position==0?"1":"0", null,
                                    null, null,null,
                                    null, null, null,new ResponseImpl<Object>() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            EventBus.getDefault().post(new UpdateDataEvent());
                                            _mActivity.finish();
                                        }
                                    }, Object.class);
                            break;
                        case 3:
                            //学历
                            break;
                    }
                }
            });
        }

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        dissMissDialog();
        getAdapter().setNewData(data);


    }

    @Override
    public void bindData(BaseViewHolder helper, String item, int position) {
        ImageView iv_check=helper.getView(R.id.iv_select);
        if(current_item.equals(item)){
            iv_check.setVisibility(View.VISIBLE);
        }else {
            iv_check.setVisibility(View.GONE);
        }
        helper.setText(R.id.text,item);

    }
}
