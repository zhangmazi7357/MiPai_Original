package com.hym.eshoplib.fragment.me.editshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.city.ServerCityBean;
import com.hym.eshoplib.bean.shop.ShopDetailBean;
import com.hym.eshoplib.bean.shop.ShopTypeBean;
import com.hym.eshoplib.fragment.city.SelectCityFragmentCommon;
import com.hym.eshoplib.fragment.shop.EdityServicePriceFragment;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.ClearEditText;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by 胡彦明 on 2018/8/27.
 * <p>
 * Description 编辑工作室基本信息
 * <p>
 * otherTips
 */

public class EditshopBaseInfo extends BaseKitFragment {
    ShopDetailBean data;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.iv_upload_image)
    ImageView ivUploadImage;
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_city)
    TextView tvCity;
    Unbinder unbinder;
    Handler handler;
    String avantar;
    private int MsgWhat;
    ShopTypeBean.DataBean typeBean;//记录工作室类型
    ServerCityBean.DataBean.InfoBean cityBean;//所在城市
    public static EditshopBaseInfo newInstance(Bundle args) {
        EditshopBaseInfo fragment = new EditshopBaseInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_editshop_baseinfo;
    }

    @Override
    public void doLogic() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;
                switch (msg.what) {
                    case 1:
                        //头像
                        CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                            @Override
                            public void onSuccess(UploadFilesBean data) {
                                ImageUtil.getInstance().loadRoundCornerImage(EditshopBaseInfo.this, url, ivUploadImage, 5);
                                avantar = data.getData().getAttachment_id().get(0);
                            }
                        }, UploadFilesBean.class);
                        break;
                }

            }
        };
        showBackButton();
        setTitle("工作室基本信息");



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
            @Override
            public void onSuccess(final ShopDetailBean d) {
                EditshopBaseInfo.this.data = d;
                ImageUtil.getInstance().loadRoundCornerImage(EditshopBaseInfo.this, data.getData().getBase().getLogo(), ivUploadImage,5);
                etName.setText(data.getData().getBase().getStore_name()+"");
                tvType.setText(data.getData().getBase().getCategory_name()+"");
                tvCity.setText(data.getData().getBase().getRegion_name()+"");
                ivUploadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MsgWhat = 1;
                        //PhotoUtil.ShowDialog(EditshopBaseInfo.this, 1, true);
                        PhotoUtil.ShowDialog(EditshopBaseInfo.this, 1, true,2);
                    }
                });
//                tvType.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Bundle bundleType = new Bundle();
//                        if (typeBean != null) {
//                            bundleType.putSerializable("data", typeBean);
//                        }
//                        startForResult(SelectShopTypeFragment.newInstance(bundleType), 0x01);
//                    }
//                });
                tvCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundele = new Bundle();
                        startForResult(SelectCityFragmentCommon.newInstance(bundele), 0x02);
                    }
                });
                tvPrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data == null) {
                            ToastUtil.toast("数据异常");
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",data);
                        start(EdityServicePriceFragment.newInstance(bundle));
                    }
                });
                setRight_tv("保存", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=etName.getText().toString();
                        if(TextUtils.isEmpty(name)){
                            ToastUtil.toast("请输入工作室名称");
                            return;
                        }
                        //工作室类别
                        String category_id = "";
                        if (typeBean != null && !TextUtils.isEmpty(typeBean.getCategory_id())) {
                            category_id = typeBean.getCategory_id();
                        }
                        //所在城市
                        String region_id = "";
                        if (cityBean != null && !TextUtils.isEmpty(cityBean.getRegion_id())) {
                            region_id = cityBean.getRegion_id();
                        }
                        ShopApi.EditShop(avantar, name, category_id,
                                region_id, "", "",
                                "", "", "",
                                "", "","", new ResponseImpl<Object>() {
                                    @Override
                                    public void onStart(int mark) {
                                        setShowProgressDialog(true);
                                        super.onStart(mark);
                                    }

                                    @Override
                                    public void onSuccess(Object data) {
                                        ShopApi.getShopDetail(new ResponseImpl<ShopDetailBean>() {
                                            @Override
                                            public void onSuccess(ShopDetailBean data) {
                                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(data.getData().getBase().getUserid(),data.getData().getBase().getStore_name() , Uri.parse(data.getData().getBase().getLogo())));
                                                ToastUtil.toast("修改成功");
                                                pop();
                                            }
                                        },ShopDetailBean.class);


                                    }
                                }, Object.class);

                    }
                });

            }
        }, ShopDetailBean.class);

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
//                    message.obj = files;
//                    bundle.putString("url", url);
//                    message.setData(bundle);
//                    message.sendToTarget();
//
//                }
//            });
            PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                @Override
                public void onResultCamara(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();
                }

                @Override
                public void onResultGalary(ArrayList<LocalMedia> resultCamara) {
                    File[] files;
                    ArrayList<File> arr = new ArrayList<>();
                    String url = PhotoUtil.getFilePash(resultCamara.get(0));
                    arr.add(new File(url));
                    files = arr.toArray(new File[arr.size()]);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    message.what = MsgWhat;
                    message.obj = files;
                    bundle.putString("url", url);
                    message.setData(bundle);
                    message.sendToTarget();
                }
            });
        }

    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 0x01:
                //选择工作室类型
                typeBean = (ShopTypeBean.DataBean) data.getSerializable("data");
                if (typeBean != null) {
                    tvType.setText(typeBean.getCategory_name() + "");
                    initData(null);
                    ToastUtil.toast("更改工作室类型后，需要重新设置服务价格否则用户将无法看到您的服务信息");

                }
                break;
            case 0x02:
                //选择城市
                cityBean = (ServerCityBean.DataBean.InfoBean) data.getSerializable("data");
                if (cityBean != null) {
                    tvCity.setText(cityBean.getRegion_name());
                }

                break;
        }

    }

}
