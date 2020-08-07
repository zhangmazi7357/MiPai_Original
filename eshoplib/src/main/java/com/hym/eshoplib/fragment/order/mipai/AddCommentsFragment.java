package com.hym.eshoplib.fragment.order.mipai;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.order.CommentLableListBean;
import com.hym.eshoplib.bean.shop.ShopProductsBean;
import com.hym.eshoplib.fragment.shop.UpLoadImageFragment;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.eshoplib.mz.decoration.SpacesItemDecoration;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.event.UpdateDataEvent;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.mz.MzOrderImageAdapter;
import cn.hym.superlib.mz.MzProImageDetailAdapter;
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

    @BindView(R.id.mz_order_text)
    EditText mzOrderText;
    @BindView(R.id.mz_order_rv)
    RecyclerView mzOrderRv;


    private String qiniuToken = "";
    private MzOrderImageAdapter mzAdapter;

    String log_id;
    BaseListAdapter<CommentLableListBean.DataBean.LabelListBean> adapter;
    Unbinder unbinder;
    HashMap<Integer, String> ids = new HashMap<>();
    String url;

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
        log_id = getArguments().getString("id", "");
        url = getArguments().getString("url", "");

        ImageUtil.getInstance()
                .loadCircleImage(AddCommentsFragment.this, url, ivShopLogo);

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
                    btn_lable.invalidate();
                } else {
                    btn_lable.setBackgroundResource(R.drawable.shape_grayf3ebd8_solid_conner5);
                    btn_lable.setTextColor(ContextCompat.getColor(_mActivity, R.color.mipaiTextColorSelect));
                    btn_lable.invalidate();
                }
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

        // 提交评价 ;
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "";

                if (TextUtils.isEmpty(log_id)) {
                    ToastUtil.toast("数据异常");
                    return;
                }

                if (ids.size() == 0) {
                    ToastUtil.toast("请至少选择一个标签");
                    return;
                }

                Iterator<Integer> it = ids.keySet().iterator();

                while (it.hasNext()) {
                    int key = it.next();
                    id += ids.get(key) + ",";
                }

                String rating = ratingbar.getRating() + "";

//                Logger.d("id=" + id + ",rating=" + rating + ",log_id=" + log_id);


                ShopApi.addComment(log_id,
                        ratingbar.getRating() + "",
                        id, new ResponseImpl<Object>() {

                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.toast("感谢您的评价，祝您生活愉快");
                                EventBus.getDefault().post(new UpdateDataEvent());
                                _mActivity.finish();

                            }

                        }, Object.class);


            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        // 标签
        ShopApi.GetCommentLabel(log_id, new ResponseImpl<CommentLableListBean>() {
            @Override
            public void onSuccess(CommentLableListBean data) {
                adapter.setNewData(data.getData().getLabel_list());

            }
        }, CommentLableListBean.class);


    }


    private void initOrderCommentText() {

        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);

        mzOrderRv.setLayoutManager(manager);

        // 设置个间距
        HashMap<String, Integer> spacesVelue = new HashMap<>();
        spacesVelue.put(SpacesItemDecoration.TOP_SPACE, 10);
        spacesVelue.put(SpacesItemDecoration.BOTTOM_SPACE, 20);
        spacesVelue.put(SpacesItemDecoration.LEFT_SPACE, 0);
        spacesVelue.put(SpacesItemDecoration.RIGHT_SPACE, 0);

        mzOrderRv.addItemDecoration(new SpacesItemDecoration(2, spacesVelue, false));

        mzAdapter = new MzOrderImageAdapter(this, null);

        mzAdapter.addData(new UpLoadImageBean());
        mzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mzAdapter.getData().get(position).getItemType() == 1) {

                    // 可能是预览图片吧;
                    ArrayList<String> images_str = new ArrayList<String>();
                    images_str.add(mzAdapter.getData().get(position).getImage().getCompressPath());
                    int width = ScreenUtil.getScreenWidth(_mActivity);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                    ImagePagerActivity.startImagePagerActivity(_mActivity, images_str, position, imageSize);

                } else {

//                    imageType = 3;
                    PhotoUtil.ShowDialog(AddCommentsFragment.this,
                            10 - adapter.getData().size(), false, 2);


                }
            }
        });

        // 拿到七牛 token ;
        ShopApi.getProductsList("1", new ResponseImpl<ShopProductsBean>() {
                    @Override
                    public void onSuccess(ShopProductsBean data) {
                    }

                    @Override
                    public void dataRes(int code, String data) {
                        super.dataRes(code, data);

                        try {
                            org.json.JSONObject j = new org.json.JSONObject(data);
                            org.json.JSONObject data1 = j.getJSONObject("data");
                            qiniuToken = data1.getString("qiniu_token");

                            mzAdapter.setToken(qiniuToken);
                            mzOrderRv.setAdapter(mzAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                ShopProductsBean.class);


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


    public List<UpLoadImageBean> getImageData(BaseQuickAdapter adapter,
                                              ArrayList<LocalMedia> source) {
        List<UpLoadImageBean> imageBeen = new ArrayList<UpLoadImageBean>();

        for (LocalMedia bean : source) {
            TImage tImage = new TImage(PhotoUtil.getFilePash(bean), TImage.FromType.OTHER);
            tImage.setCompressPath(PhotoUtil.getFilePash(bean));
            imageBeen.add(new UpLoadImageBean(tImage));
        }


        int oldSize = adapter.getData().size();
        if (oldSize == 0) {
            //第一次添加
            if (imageBeen.size() == 9) {
                //一次性添加了9次图片，则直接加入
                adapter.setNewData(imageBeen);
            } else {
                //没有一次性添加完毕，则追加 addIcon
                imageBeen.add(new UpLoadImageBean());
                adapter.setNewData(imageBeen);

            }
        }
        //不是第一次添加，并且第二次就 加满
        else if (imageBeen.size() + oldSize == 10) {
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
