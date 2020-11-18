package com.hym.eshoplib.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.http.CommonApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.RequiredTextView;

/**
 * Created by 胡彦明 on 2018/10/10.
 * <p>
 * Description 上传获奖证书
 * <p>
 * otherTips
 */

public class UploadAwardCardFragment extends BaseKitFragment {
    ShopDetailBean data;
    @BindView(R.id.tv_card_1)
    TextView tvCard1;
    @BindView(R.id.iv_card_1)
    ImageView ivCard1;
    @BindView(R.id.iv_delet_1)
    ImageView ivDelet1;
    @BindView(R.id.tv_card_2)
    TextView tvCard2;
    @BindView(R.id.iv_card_2)
    ImageView ivCard2;
    @BindView(R.id.iv_delet_2)
    ImageView ivDelet2;
    @BindView(R.id.tv_card_3)
    TextView tvCard3;
    @BindView(R.id.iv_card_3)
    ImageView ivCard3;
    @BindView(R.id.iv_delet_3)
    ImageView ivDelet3;
    Unbinder unbinder;
    String url1, url2, url3;
    Handler handler;
    String qiniuToken;
    @BindView(R.id.tv_title)
    RequiredTextView tvTitle;
    private int MsgWhat;

    public static UploadAwardCardFragment newInstance(Bundle args) {
        UploadAwardCardFragment fragment = new UploadAwardCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_upload_study_card;
    }

    @Override
    public void doLogic() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;
                CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                    @Override
                    public void onSuccess(UploadFilesBean data) {
                        Logger.d("msgWhat=" + MsgWhat);
                        switch (MsgWhat) {
                            case 1:
                                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, url, ivCard1, 5);
                                url1 = data.getData().getAttachment_id().get(0);
                                ivCard1.setVisibility(View.VISIBLE);
                                tvCard1.setVisibility(View.INVISIBLE);
                                ivDelet1.setVisibility(View.VISIBLE);
                                tvCard2.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                url2 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, url, ivCard2, 5);
                                ivCard2.setVisibility(View.VISIBLE);
                                tvCard2.setVisibility(View.INVISIBLE);
                                ivDelet2.setVisibility(View.VISIBLE);
                                tvCard3.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                url3 = data.getData().getAttachment_id().get(0);
                                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, url, ivCard3, 5);
                                ivCard3.setVisibility(View.VISIBLE);
                                tvCard3.setVisibility(View.INVISIBLE);
                                ivDelet3.setVisibility(View.VISIBLE);
                                break;
                        }

                    }
                }, UploadFilesBean.class);


            }
        };
        data = (ShopDetailBean) getArguments().getSerializable("data");
        if (data == null) {
            ToastUtil.toast("数据异常，请检查您的网络后重试");
            pop();
            return;
        }
        showBackButton();
        setTitle("获奖证书");
        tvTitle.setText("上传获奖证书");
        setRight_tv("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urls_1 = "";
                if (!TextUtils.isEmpty(url1)) {
                    urls_1 += url1 + ",";
                }
                if (!TextUtils.isEmpty(url2)) {
                    urls_1 += url2 + ",";
                }
                if (!TextUtils.isEmpty(url3)) {
                    urls_1 += url3;
                }
                if (!TextUtils.isEmpty(urls_1)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ids", urls_1);
                    Logger.d("url=" + urls_1);
                    setFragmentResult(RESULT_OK, bundle);
                    pop();
                } else {
                    ToastUtil.toast("请选择要上传的图片");
                }

            }
        });
        //学历证书
        List<ShopDetailBean.DataBean.AwardsBean.AttachmentBean> urls_1 = data.getData().getAwards().getAttachment();
        if (urls_1 != null && urls_1.size() > 0) {
            if (urls_1.size() == 1) {
                tvCard1.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                ivCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.VISIBLE);


                tvCard2.setVisibility(View.VISIBLE);
                ivCard2.setVisibility(View.GONE);
                ivDelet2.setVisibility(View.GONE);

                tvCard3.setVisibility(View.INVISIBLE);
                ivCard3.setVisibility(View.GONE);
                ivDelet3.setVisibility(View.GONE);
                url1 = urls_1.get(0).getId();
            } else if (urls_1.size() == 2) {
                tvCard1.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                ivCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.VISIBLE);

                tvCard2.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(1).getFilepath(), ivCard2, 5);
                ivCard2.setVisibility(View.VISIBLE);
                ivDelet2.setVisibility(View.VISIBLE);

                tvCard3.setVisibility(View.VISIBLE);
                ivCard3.setVisibility(View.GONE);
                ivDelet3.setVisibility(View.GONE);
                url1 = urls_1.get(0).getId();
                url2 = urls_1.get(1).getId();
            } else if (urls_1.size() == 3) {
                tvCard1.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(0).getFilepath(), ivCard1, 5);
                ivCard1.setVisibility(View.VISIBLE);
                ivDelet1.setVisibility(View.VISIBLE);

                tvCard2.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(1).getFilepath(), ivCard2, 5);
                ivCard2.setVisibility(View.VISIBLE);
                ivDelet2.setVisibility(View.VISIBLE);

                tvCard3.setVisibility(View.INVISIBLE);
                ImageUtil.getInstance().loadRoundCornerImage(UploadAwardCardFragment.this, urls_1.get(2).getFilepath(), ivCard3, 5);
                ivCard3.setVisibility(View.VISIBLE);
                ivDelet3.setVisibility(View.VISIBLE);
                url1 = urls_1.get(0).getId();
                url2 = urls_1.get(1).getId();
                url3 = urls_1.get(2).getId();


            }


        } else {
            tvCard1.setVisibility(View.VISIBLE);
            tvCard2.setVisibility(View.INVISIBLE);
            tvCard3.setVisibility(View.INVISIBLE);

            ivCard1.setVisibility(View.GONE);
            ivDelet1.setVisibility(View.GONE);

            ivCard2.setVisibility(View.GONE);
            ivDelet2.setVisibility(View.GONE);

            ivCard3.setVisibility(View.GONE);
            ivDelet3.setVisibility(View.GONE);
        }
        //适配长传图片
        int height = (ScreenUtil.getScreenWidth(_mActivity) - ScreenUtil.dip2px(_mActivity, 40)) / 3;
        tvCard1.getLayoutParams().height = height;
        tvCard2.getLayoutParams().height = height;
        tvCard3.getLayoutParams().height = height;
        ivCard1.getLayoutParams().height = height;
        ivCard2.getLayoutParams().height = height;
        ivCard3.getLayoutParams().height = height;

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

    @OnClick({R.id.tv_card_1, R.id.iv_delet_1, R.id.tv_card_2, R.id.iv_delet_2, R.id.tv_card_3, R.id.iv_delet_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_1:
                MsgWhat = 1;
                PhotoUtil.ShowDialog(UploadAwardCardFragment.this, 1, false,2);
                break;
            case R.id.iv_delet_1:
                url1 = "";
                tvCard1.setVisibility(View.VISIBLE);
                ivCard1.setVisibility(View.GONE);
                ivDelet1.setVisibility(View.GONE);
                break;
            case R.id.tv_card_2:
                MsgWhat = 2;
                PhotoUtil.ShowDialog(UploadAwardCardFragment.this, 1, false,2);
                break;
            case R.id.iv_delet_2:
                url2 = "";
                tvCard2.setVisibility(View.VISIBLE);
                ivCard2.setVisibility(View.GONE);
                ivDelet2.setVisibility(View.GONE);
                break;
            case R.id.tv_card_3:
                MsgWhat = 3;
                PhotoUtil.ShowDialog(UploadAwardCardFragment.this, 1, false,2);
                break;
            case R.id.iv_delet_3:
                url3 = "";
                tvCard3.setVisibility(View.VISIBLE);
                ivCard3.setVisibility(View.GONE);
                ivDelet3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
//                @Override
//                public void onResultCamara(ArrayList<TImage> resultCamara) {
//                    File[] files;
//                    ArrayList<File> arr = new ArrayList<>();
//                    String url = resultCamara.get(0).getCompressPath();
//                    arr.add(new File(url));
//                    files = arr.toArray(new File[arr.size()]);
//                    Message message = handler.obtainMessage();
//                    Bundle bundle = new Bundle();
//                    message.what = MsgWhat;
//                    message.obj = files;
//                    bundle.putString("url", url);
//                    message.setData(bundle);
//                    message.sendToTarget();
//
//                }
//
//                @Override
//                public void onResultGalary(ArrayList<TImage> resultGalayr) {
//                    File[] files;
//                    ArrayList<File> arr = new ArrayList<>();
//                    String url = resultGalayr.get(0).getCompressPath();
//                    arr.add(new File(url));
//                    files = arr.toArray(new File[arr.size()]);
//                    Message message = handler.obtainMessage();
//                    Bundle bundle = new Bundle();
//                    message.what = MsgWhat;
//                    Logger.d("message.what=" + message.what + ",,msgwhat=" + MsgWhat);
//                    message.obj = files;
//                    bundle.putString("url", url);
//                    message.setData(bundle);
//                    message.sendToTarget();
//
//                }
//            });
            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePath(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    Logger.d("message.what=" + message.what + ",,msgwhat=" + MsgWhat);
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();
                }

                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePath(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    Logger.d("message.what=" + message.what + ",,msgwhat=" + MsgWhat);
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();
                }
            });
        }
    }
}
