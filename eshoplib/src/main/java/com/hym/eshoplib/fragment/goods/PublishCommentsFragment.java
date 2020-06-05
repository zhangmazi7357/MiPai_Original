package com.hym.eshoplib.fragment.goods;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.adapter.UpLoadImageAdapter;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/4/18.
 * <p>
 * Description 发布商品评价
 * <p>
 * otherTips
 */

public class PublishCommentsFragment extends BaseKitFragment {

    @BindView(R2.id.iv_icon)
    ImageView ivIcon;
    @BindView(R2.id.tv_nice)
    TextView tvNice;
    @BindView(R2.id.tv_common)
    TextView tvCommon;
    @BindView(R2.id.tv_bad)
    TextView tvBad;
    @BindView(R2.id.tv_title)
    RequiredTextView tvTitle;
    @BindView(R2.id.et_expect)
    ClearEditText etExpect;
    @BindView(R2.id.tv_count)
    TextView tvCount;
    @BindView(R2.id.fl_other)
    FrameLayout flOther;
    @BindView(R2.id.ll_main)
    LinearLayout llMain;
    @BindView(R2.id.tv_goods_name)
    TextView tv_goods_name;
    Unbinder unbinder;
    UpLoadImageAdapter adapter;
    String order_id;
    String rank_type="1";
    String sp_id;
    String url;
    String attchment_id;
    public static PublishCommentsFragment newInstance(Bundle args) {
        PublishCommentsFragment fragment = new PublishCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_publish_comments;
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        order_id=getArguments().getString("order_id","");
        sp_id=getArguments().getString("id","");
        url=getArguments().getString("url","");
        tv_goods_name.setText(getArguments().getString("name")+"");
        showBackButton();
        setTitle(R.string.Releasereview);
        setRight_tv(R.string.Release, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布
                if(TextUtils.isEmpty(etExpect.getText().toString())){
                    ToastUtil.toast("请输入评价内容");
                    return;
                }
                String imageIds;
                ArrayList<File> arr = new ArrayList<>();
                for(int i=0;i<adapter.getData().size();i++){
                    UpLoadImageBean bean=adapter.getData().get(i);
                    if(bean.getItemType()==UpLoadImageBean.type_normal){
                        File file=new File(bean.getImage().getCompressPath());
                        if(file.exists()){
                            arr.add(file);
                        }

                    }
                }
                if(arr.size()>0){
                    //先上传图片
                    CommonApi.uploadFile(_mActivity,arr.toArray(new File[arr.size()]), new ResponseImpl<UploadFilesBean>() {
                        @Override
                        public void onSuccess(UploadFilesBean data) {
                            attchment_id="";
                            for(String id:data.getData().getAttachment_id()){
                                attchment_id+=id+",";
                            }
                            //ToastUtil.toast("先上传图片图片id="+attchment_id);
                            String params=getParams();
                            GoodsApi.addGoodsComments(_mActivity,params, new ResponseImpl<Object>() {
                                @Override
                                public void onSuccess(Object data) {
                                    ToastUtil.toast("评价成功");
                                    EventBus.getDefault().post(new UpdateDataEvent());
                                    _mActivity.finish();

                                }
                            },Object.class);


                        }
                    },UploadFilesBean.class);
                }else {
                    //没有图片直接发布
                    //ToastUtil.toast("没有图片直接发布");
                    String params=getParams();
                    GoodsApi.addGoodsComments(_mActivity,params, new ResponseImpl<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.toast("评价成功");
                            EventBus.getDefault().post(new UpdateDataEvent());
                            _mActivity.finish();

                        }
                    },Object.class);
                }


            }
        });
        //上传你图片
        View uploadImage=LayoutInflater.from(_mActivity).inflate(R.layout.layout_upload_image,null,false);
        RequiredTextView tv_title= (RequiredTextView) uploadImage.findViewById(R.id.tv_title);
        tv_title.setRequird(false);
        tv_title.setText("");
        TextView tv_max= (TextView) uploadImage.findViewById(R.id.tv_max);
        RecyclerView rvList= (RecyclerView) uploadImage.findViewById(R.id.rv_list);
        rvList.setLayoutManager(new GridLayoutManager(_mActivity,4));
        adapter=new UpLoadImageAdapter(this,null);
        adapter.addData(new UpLoadImageBean());
        rvList.setAdapter(adapter);
        adapter.setListener(new UpLoadImageAdapter.onDeleteListener() {
            @Override
            public void onDelete(int position) {
                //editMap.remove(position);
                //评论参数-必须[{"to_order_item_id":订单item_id,"specification_id":商品ID,"memo":"评论内容","rank_type":评价等级，1：好评，2：中评，3：差评,"attachmentid":"评论图片ID多个用逗号连接"}]
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter2, View view, int position) {
                if(adapter.getData().get(position).getItemType()==1){
                    //ToastUtil.toast("普通图片");
                    //更换图片
                }else {
                    //ToastUtil.toast("添加图片");
                    PhotoUtil.ShowDialog(PublishCommentsFragment.this,10-adapter.getData().size(),true);
                }
            }
        });
        llMain.addView(uploadImage);
        setListeners();

    }

    private String getParams() {
        List<CommentsBean>list=new ArrayList<>();
        CommentsBean bean=new CommentsBean();
        bean.setTo_order_item_id(order_id);
        bean.setSpecification_id(sp_id);
        bean.setRank_type(rank_type);
        bean.setMemo(etExpect.getText().toString());
        bean.setAttachmentid(attchment_id);
        list.add(bean);
        String result=JSON.toJSONString(list);
        Logger.d("参数="+result);
        return result;



    }

    private void setListeners() {
        etExpect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCount.setText(s.toString().length()+"/300");

            }
        });
        tvNice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeType(1);
            }
        });
        tvCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeType(2);
            }
        });
        tvBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeType(3);
            }
        });
    }
    private void changeType(int position){
        switch (position){
            case 1:
                rank_type="1";
                tvNice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_checked,0,0,0);
                tvCommon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                tvBad.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                break;
            case 2:
                rank_type="2";
                tvNice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                tvCommon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_checked,0,0,0);
                tvBad.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                break;
            case 3:
                rank_type="3";
                tvNice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                tvCommon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_unchecked,0,0,0);
                tvBad.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cb_checked,0,0,0);
                break;
        }

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImageUtil.getInstance().loadImage(PublishCommentsFragment.this,url,ivIcon);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
                @Override
                public void onResultCamara(ArrayList<TImage> resultCamara) {
                    //ToastUtil.toast("相机url="+resultCamara.get(0).getCompressPath());
                    getImageData(resultCamara);
                }

                @Override
                public void onResultGalary(ArrayList<TImage> resultGalayr) {
                    // ToastUtil.toast("相册url="+resultCamara.get(0).getCompressPath());
                    getImageData(resultGalayr);

                }
            });
        }
    }
    public List<UpLoadImageBean > getImageData(ArrayList<TImage> source){
        List<UpLoadImageBean >imageBeen=new ArrayList<UpLoadImageBean>();
        for(TImage bean:source){
            imageBeen.add(new UpLoadImageBean(bean));
        }
        int oldSize=adapter.getData().size();
        if(oldSize==0){
            //第一次添加
            if(imageBeen.size()==9){
                //一次性添加了4次图片，则直接加入
                adapter.setNewData(imageBeen);
            }else {
                //没有一次性添加完毕，则追加 addIcon
                imageBeen.add(new UpLoadImageBean());
                adapter.setNewData(imageBeen);

            }



        }//不是第一次添加，并且第二次就 加满
        else if(imageBeen.size()+oldSize==10){
            //说明有9张图片，1个添加图片，移除原adapter的最后一张图片,并且将所有的图片都加入adapter
            Logger.d("不是第一次");
            adapter.getData().remove(adapter.getData().size()-1);
            adapter.notifyDataSetChanged();
            adapter.getData().addAll(imageBeen);
            adapter.notifyDataSetChanged();

        } else {
            //不是第一次添加。并且第二次也没加满
            //oldsize>0 原来有数据，但是还未添加满。则此时一定有add icon，remove 原来的，addicon 同时加入新数据，最后再添加addicon
            adapter.getData().remove(adapter.getData().size()-1);
            adapter.notifyDataSetChanged();
            imageBeen.add(new UpLoadImageBean());
            adapter.getData().addAll(imageBeen);
            adapter.notifyDataSetChanged();

        }
        return imageBeen;
    }
    //用于商品评论
    private class CommentsBean{
        /**
         * to_order_item_id : 232
         * specification_id : 15
         * memo : haohao
         * rank_type : 1
         * attachmentid : 3321,3310
         */

        private String to_order_item_id;
        private String specification_id;
        private String memo;
        private String rank_type;
        private String attachmentid;

        public String getTo_order_item_id() {
            return to_order_item_id;
        }

        public void setTo_order_item_id(String to_order_item_id) {
            this.to_order_item_id = to_order_item_id;
        }

        public String getSpecification_id() {
            return specification_id;
        }

        public void setSpecification_id(String specification_id) {
            this.specification_id = specification_id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getRank_type() {
            return rank_type;
        }

        public void setRank_type(String rank_type) {
            this.rank_type = rank_type;
        }

        public String getAttachmentid() {
            return attachmentid;
        }

        public void setAttachmentid(String attachmentid) {
            this.attachmentid = attachmentid;
        }
    }
}
