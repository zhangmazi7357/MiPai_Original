package com.hym.eshoplib.fragment.order.mipai;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.order.CommentLableListBean;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import app.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.mz.MzOrderImageAdapter;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
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

    private String TAG = "AddCommentsFragment";

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.ratingbar)
    MaterialRatingBar ratingbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;

    @BindView(R.id.mz_order_et)
    EditText mzOrderEt;

    @BindView(R.id.mz_order_rv)
    RecyclerView mzOrderRv;


    private String qiniuToken = "";
    private MzOrderImageAdapter mzAdapter;

    private String log_id;
    BaseListAdapter<CommentLableListBean.DataBean.LabelListBean> adapter;
    Unbinder unbinder;
    private HashMap<Integer, String> ids = new HashMap<>();             // tagID
    private String shopLogoUrl;                                          // 店铺logo

    private String mzCaseId = "";               // 产品 id;
    private String mzContent;              //评论内容；
    private String mzStore = "0";                    // 星级
    private String mzTagId = "";               //标签id;
    private String mzImages = "";               // 上传图片 ;
    private String mzPid = "";                  // 回复评论id ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

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

        Bundle bundle = getArguments();


        log_id = bundle.getString("id", "");
        shopLogoUrl = bundle.getString("url", "");
        mzCaseId = bundle.getString(MzConstant.KEY_ORDER_CASE_ID);


        ImageUtil.getInstance()
                .loadCircleImage(AddCommentsFragment.this,
                        shopLogoUrl, ivShopLogo);


        setTitle("订单评价");

        initOrderCommentText();

        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));

        adapter = new BaseListAdapter<CommentLableListBean.DataBean.LabelListBean>(R.layout.item_label, null) {
            @Override
            public void handleView(BaseViewHolder helper, final CommentLableListBean.DataBean.LabelListBean item, final int position) {
                TextView btn_lable = helper.getView(R.id.btn_label);

                int width = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 130)) / 3;
                btn_lable.getLayoutParams().width = width;
                btn_lable.getLayoutParams().height = width / 2;
                btn_lable.setText(item.getLabel_name() + "");

                if (item.isSelect()) {
                    btn_lable.setBackgroundResource(R.drawable.shape_grayb3985b_solid_conner5);
                    btn_lable.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
                } else {
                    btn_lable.setBackgroundResource(R.drawable.shape_grayf3ebd8_solid_conner5);
                    btn_lable.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                }
                btn_lable.invalidate();
                btn_lable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.isSelect()) {
                            item.setSelect(false);
                            ids.remove(position);
                        } else {
                            if (ids.size() == 3) {
                                ToastUtil.toast("最多只可以选择3个标签");
                                return;
                            }
                            item.setSelect(true);
                            ids.put(position, item.getLabel_id());
                        }
                        adapter.notifyDataSetChanged();

                    }
                });


            }
        };
        rvList.setAdapter(adapter);


        mzOrderEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mzContent = s.toString();
            }
        });


        // 提交评价 ;
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upLoadComment();

            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "token : " + UserUtil.getToken(App.instance));

        // 标签
        ShopApi.GetCommentLabel(log_id, new ResponseImpl<CommentLableListBean>() {
            @Override
            public void onSuccess(CommentLableListBean data) {
                adapter.setNewData(data.getData().getLabel_list());

            }
        }, CommentLableListBean.class);


    }


    // 评价图片
    private void initOrderCommentText() {

        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);

        mzOrderRv.setLayoutManager(manager);

        mzAdapter = new MzOrderImageAdapter(this, null);

        mzAdapter.addData(new UpLoadImageBean());
        mzOrderRv.setAdapter(mzAdapter);

        mzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (mzAdapter.getData().get(position).getItemType() == UpLoadImageBean.type_normal) {

                    // 可能是预览图片吧;
                    ArrayList<String> images_str = new ArrayList<String>();
                    images_str.add(mzAdapter.getData().get(position).getImage().getCompressPath());
                    int width = ScreenUtil.getScreenWidth(_mActivity);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                    ImagePagerActivity.startImagePagerActivity(_mActivity, images_str, position, imageSize);

                } else {


                    PhotoUtil.ShowDialogPictureSelector(AddCommentsFragment.this,
                            4 - adapter.getData().size(), false);

                }
            }
        });


        MzNewApi.getQiniuToken(new ResponseImpl<Object>() {
            @Override
            public void onSuccess(Object data) {
                String token = JSONObject.toJSONString(data);
                try {
                    org.json.JSONObject j = new org.json.JSONObject(token);
                    org.json.JSONObject data1 = j.getJSONObject("data");

                    qiniuToken = data1.getString("qiniu_token");
                    mzAdapter.setToken(qiniuToken);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, Object.class);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
                    getImageData(mzAdapter, resultCamara);
                }

                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultGalary) {
                    getImageData(mzAdapter, resultGalary);
                }


            });

        }
    }


    // 将图片显示在 列表上
    public List<UpLoadImageBean> getImageData(BaseQuickAdapter adapter,
                                              ArrayList<LocalMedia> source) {
        List<UpLoadImageBean> imageBeen = new ArrayList<UpLoadImageBean>();

        for (LocalMedia bean : source) {
            TImage tImage = new TImage(PhotoUtil.getFilePath(bean), TImage.FromType.OTHER);
            tImage.setCompressPath(PhotoUtil.getFilePath(bean));
            imageBeen.add(new UpLoadImageBean(tImage));
        }


        int oldSize = adapter.getData().size();
        if (oldSize == 0) {
            //第一次添加
            if (imageBeen.size() == 3) {
                //一次性添加了3次图片，则直接加入
                adapter.setNewData(imageBeen);
            } else {
                //没有一次性添加完毕，则追加 addIcon
                imageBeen.add(new UpLoadImageBean());
                adapter.setNewData(imageBeen);

            }
        }
        //不是第一次添加，并且第二次就 加满
        else if (imageBeen.size() + oldSize == 4) {
            //说明有9张图片，1个添加图片，移除原adapter的最后一张图片,并且将所有的图片都加入adapter
            Logger.d("不是第一次");
            adapter.getData().remove(adapter.getData().size() - 1);
            adapter.notifyDataSetChanged();
            adapter.getData().addAll(imageBeen);
            adapter.notifyDataSetChanged();

        } else {
            //不是第一次添加。并且第二次也没加满
            //oldsize>0 原来有数据，但是还未添加满。则此时一定有add icon，remove 原来的，addicon 同时加入新数据，最后再添加addicon
            adapter.getData().remove(adapter.getData().size() - 1);
            adapter.notifyDataSetChanged();
            imageBeen.add(new UpLoadImageBean());
            adapter.getData().addAll(imageBeen);
            adapter.notifyDataSetChanged();

        }
        return imageBeen;
    }


    /**
     * 添加评论
     */
    private void upLoadComment() {


        if (ids.size() == 0) {
            ToastUtil.toast("请至少选择一个标签");
            return;
        }
        Iterator<Integer> it = ids.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            mzTagId += ids.get(key) + ",";
        }


        if (TextUtils.isEmpty(mzContent)) {
            ToastUtil.toast("请输入评价内容");
            return;
        }

        int rating = (int) ratingbar.getRating();
        mzStore = String.valueOf(rating);

        mzImages = getProDetailPic();


        // 发布评价 需要 caseId ;
        MzNewApi.sendComment(mzCaseId, log_id,
                mzContent, mzStore,
                mzTagId, mzImages, mzPid,
                new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {

//                        Log.e(TAG, "发布评价 返回 json : " + JSONObject.toJSONString(data));
                        ToastUtil.toast("评价已发布");

                        pop();

                    }

                    @Override
                    public void dataRes(int code, String data) {
                        super.dataRes(code, data);
//                        Log.e(TAG, "dataRes: " + data);
                    }
                }, Object.class);

    }

    // 拿到上传图片 地址 ;
    private String getProDetailPic() {
        String result = "";
        for (int i = 0; i < mzAdapter.getData().size(); i++) {
            String file_name = mzAdapter.getData().get(i).getQiniuFileName();

            if (!TextUtils.isEmpty(file_name)) {
                result += file_name + ",";
            }
        }
        return result;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
