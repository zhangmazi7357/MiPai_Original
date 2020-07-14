package com.hym.eshoplib.fragment.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.ActionActivity;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.UpLoadProductAdapter;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;
import cn.hym.superlib.widgets.view.RequiredTextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/9/11.
 * <p>
 * Description 上传图片产品
 * <p>
 * otherTips
 */

public class UpLoadImageFragment extends BaseKitFragment {
    UpLoadProductAdapter adapter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    String qiniuToken;
    int imageType = -1;// 1 上传封面，2上传产品
    ImageView iv_image;//封面
    TextView tv_add;//上传封面
    Handler handler;
    String image_default;//封面图片id
    TextView tv_industry;//行业类型
    TextView tv_image_type;//图片类型
    TextView tv_region;//产品区域
    String industry_id;//行业id
    String image_type_id;//图片类型id
    String region_id;//区域id
    EditText et_title;//标题
    EditText et_other;//其他描述


    private TextView tv_upload_subtitle;
    private EditText etRemarks;
    private EditText etPresentPrice;
    private EditText etOriginalPrice;
    private EditText etStaffing;
    private EditText etShootingTime;
    private EditText etEquipment;
    private EditText etIntroduce;
    private EditText etDetail;
    private EditText etRemarks1;
    private EditText etServiceName;
    private EditText etShopTime;
    private String cateId;
    private EditText etPaisheCount;
    private EditText etHuazhuang;
    private EditText etSheyingshi;
    private EditText etShejishi;
    private EditText etHuazhuangping;
    private String attachment;
    private EditText etLocatiom;
    private EditText etTime;
    private EditText etCeHua;
    private String tyid;
    private String otherOrLocation;


    public static UpLoadImageFragment newInstance(Bundle args) {
        UpLoadImageFragment fragment = new UpLoadImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_uploadimage;
    }

    @Override
    public void doLogic() {
        SoftHideKeyBoardUtil.assistActivity(_mActivity);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String url = msg.getData().getString("url");
                File[] files = (File[]) msg.obj;
                CommonApi.uploadFile(_mActivity, files, new ResponseImpl<UploadFilesBean>() {
                    @Override
                    public void onSuccess(UploadFilesBean data) {
                        ImageUtil.getInstance().loadRoundCornerImage(UpLoadImageFragment.this, url, iv_image, 5);
                        iv_image.setVisibility(View.VISIBLE);
                        tv_add.setVisibility(View.GONE);
                        image_default = data.getData().getAttachment_id().get(0);
                    }
                }, UploadFilesBean.class);
            }
        };

        qiniuToken = getArguments().getString("token", "");
        setShowProgressDialog(false);
        showBackButton();
        setTitle("上传图片");
        setRight_tv("上传", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });
        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new UpLoadProductAdapter(this, null);
        adapter.setToken(qiniuToken);
        adapter.addData(new UpLoadImageBean());
        rvList.setAdapter(adapter);
        adapter.setListener(new UpLoadProductAdapter.onDeleteListener() {
            @Override
            public void onDelete(int position) {
                //editMap.remove(position);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter2, View view, int position) {
                if (adapter.getData().get(position).getItemType() == 1) {
                    //ToastUtil.toast("普通图片");
                    //更换图片
                    ArrayList<String> images_str = new ArrayList<String>();
                    images_str.add(adapter.getData().get(position).getImage().getCompressPath());
                    int width = ScreenUtil.getScreenWidth(_mActivity);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                    ImagePagerActivity.startImagePagerActivity(_mActivity, images_str, position, imageSize);
                } else {
                    //ToastUtil.toast("添加图片");
                    imageType = 2;
                    PhotoUtil.ShowDialog(UpLoadImageFragment.this, 10 - adapter.getData().size(), false, 2);
                }
            }
        });
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_upload_image, null, false);
        tv_upload_subtitle = header.findViewById(R.id.tv_upload_subtitle);
        tv_upload_subtitle.setText("(请上传2M以内的产品，jpg／png格式)");
        iv_image = header.findViewById(R.id.iv_image);
        tv_add = header.findViewById(R.id.tv_add);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传封面
                imageType = 1;
                PhotoUtil.ShowDialog(UpLoadImageFragment.this, 1, true, 3);
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传封面
                imageType = 1;
                PhotoUtil.ShowDialog(UpLoadImageFragment.this, 1, true, 3);
            }
        });
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_upload_image, null, false);
        tv_industry = footer.findViewById(R.id.tv_type);
        tv_industry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_SelectIndustry);
                // bundle.putString("id",industry_id);
                bundle.putString("type", "1");
                ActionActivity.startForResult(UpLoadImageFragment.this, bundle, 0x11);

            }
        });
        tv_image_type = footer.findViewById(R.id.tv_immage_type);
        tv_image_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_SelectIndustry);
                // bundle.putString("id",industry_id);
                bundle.putString("type", "3");
                ActionActivity.startForResult(UpLoadImageFragment.this, bundle, 0x22);
            }
        });
        tv_region = footer.findViewById(R.id.tv_region);
        tv_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop, ActionActivity.Action_SelectRegion);
                bundle.putString("id", region_id);
                ActionActivity.startForResult(UpLoadImageFragment.this, bundle, 0x33);
            }
        });
        LinearLayout llOther = footer.findViewById(R.id.ll_other);
        LinearLayout llImageType = footer.findViewById(R.id.ll_type_title);
        LinearLayout llWorkType = footer.findViewById(R.id.ll_wrok_type);
        LinearLayout fuwuName = footer.findViewById(R.id.ll_fuwu_name);
        LinearLayout llShopTime = footer.findViewById(R.id.ll_shop_time);
        LinearLayout llLocation = footer.findViewById(R.id.ll_location);
        LinearLayout llStaffing = footer.findViewById(R.id.ll_staffing);
        LinearLayout llShootingTime = footer.findViewById(R.id.ll_shooting_time);
        LinearLayout llEquipment = footer.findViewById(R.id.ll_equipment);
        LinearLayout llIntroduce = footer.findViewById(R.id.ll_introduce);
        LinearLayout llDetail = footer.findViewById(R.id.ll_detail);
        LinearLayout llRemarks = footer.findViewById(R.id.ll_remarks);
        LinearLayout llBeforePrice = footer.findViewById(R.id.ll_before_price);
        LinearLayout llPrice = footer.findViewById(R.id.ll_price);
        LinearLayout llHuazhuang = footer.findViewById(R.id.ll_huazhuang);
        LinearLayout llSheyingshi = footer.findViewById(R.id.ll_sheyingshi);
        LinearLayout llPaisheCount = footer.findViewById(R.id.ll_paishe_count);
        LinearLayout llTime = footer.findViewById(R.id.ll_time);
        LinearLayout llShejishi = footer.findViewById(R.id.ll_shejishi);
        LinearLayout llCehuashi = footer.findViewById(R.id.ll_cehuashi);
        LinearLayout llHuazhuangping = footer.findViewById(R.id.ll_huazhuangping);
        LinearLayout llTitle = footer.findViewById(R.id.ll_title);
        LinearLayout llRegin = footer.findViewById(R.id.ll_region);

        RequiredTextView rs =  footer.findViewById(R.id.rtv_service);
        rs.setTextColor(Color.parseColor("#ff3333"));
        RequiredTextView rl =  footer.findViewById(R.id.rtv_location);
        rl.setTextColor(Color.parseColor("#ff3333"));
        RequiredTextView rp =  footer.findViewById(R.id.rtv_price);
        rp.setTextColor(Color.parseColor("#ff3333"));
        RequiredTextView tvTitle = footer.findViewById(R.id.rtv_title);
        tvTitle.setTextColor(Color.parseColor("#ff3333"));
        et_title = footer.findViewById(R.id.et_title);
        et_other = footer.findViewById(R.id.et_other);
        etServiceName = footer.findViewById(R.id.et_service_name);
        etPresentPrice = footer.findViewById(R.id.et_present_price);
        etRemarks = footer.findViewById(R.id.et_remarks);
        etOriginalPrice = footer.findViewById(R.id.et_original_price);
        etStaffing = footer.findViewById(R.id.et_staffing);
        etShootingTime = footer.findViewById(R.id.et_shooting_time);
        etEquipment = footer.findViewById(R.id.et_equipment);
        etIntroduce = footer.findViewById(R.id.et_introduce);
        etDetail = footer.findViewById(R.id.et_detailed);
        etShopTime = footer.findViewById(R.id.et_shop_time);
        etPaisheCount = footer.findViewById(R.id.et_paishe_count);
        etHuazhuang = footer.findViewById(R.id.et_huazhuang);
        etSheyingshi = footer.findViewById(R.id.et_sheyingshi);
        etShejishi = footer.findViewById(R.id.et_shejishi);
        etTime = footer.findViewById(R.id.et_time);
        etHuazhuangping = footer.findViewById(R.id.et_huazhuangping);
        etLocatiom = footer.findViewById(R.id.et_location);
        etCeHua = footer.findViewById(R.id.et_cehuashi);

        cateId = getArguments().getString("cateId");
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);

        if (cateId.equals("5") || cateId.equals("3") || cateId.equals("2")) {

            llShopTime.setVisibility(View.VISIBLE);
            llLocation.setVisibility(View.VISIBLE);
            llStaffing.setVisibility(View.VISIBLE);
            llShootingTime.setVisibility(View.VISIBLE);
            llEquipment.setVisibility(View.VISIBLE);
            llIntroduce.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.VISIBLE);
            llRemarks.setVisibility(View.VISIBLE);
            llBeforePrice.setVisibility(View.VISIBLE);
            llPrice.setVisibility(View.VISIBLE);
            tyid = "1";
        } else if (cateId.equals("8")) {

            llShopTime.setVisibility(View.VISIBLE);
            llLocation.setVisibility(View.VISIBLE);
            llEquipment.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.VISIBLE);
            llRemarks.setVisibility(View.VISIBLE);
            llBeforePrice.setVisibility(View.VISIBLE);
            llPrice.setVisibility(View.VISIBLE);
            llHuazhuang.setVisibility(View.VISIBLE);
            llSheyingshi.setVisibility(View.VISIBLE);
            llPaisheCount.setVisibility(View.VISIBLE);
            et_title.setHint("写真照、职业照、活动跟拍、聚会拍摄、旅拍");
            etShopTime.setHint("2小时");
            etEquipment.setHint("Canon5D3");
            etStaffing.setHint("专业化妆师/无化妆师");
            tyid = "2";

           /* 化妆师：专业化妆师/无化妆师
            摄影师：过往经验，成功案例
            拍摄张数：精修x张，底版几张*/
        } else if (cateId.equals("7")) {

            llPrice.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.VISIBLE);
            llRemarks.setVisibility(View.VISIBLE);
            llBeforePrice.setVisibility(View.VISIBLE);
            llTime.setVisibility(View.VISIBLE);
            llShejishi.setVisibility(View.VISIBLE);
            et_title.setHint("logo设计、海报设计、VI设计、照片修图");
            etShootingTime.setHint("1-3天");
            tyid = "3";
            /*设计师：过往经验，成功案例*/
        } else if (cateId.equals("1") || cateId.equals("4")) {
            llPrice.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.VISIBLE);
            llRemarks.setVisibility(View.VISIBLE);
            llBeforePrice.setVisibility(View.VISIBLE);
            llTime.setVisibility(View.VISIBLE);
            if (cateId.equals("1")){
                llCehuashi.setVisibility(View.VISIBLE);
            }
            et_title.setHint("广告语创作、广告创意、视频创意");
            etShootingTime.setHint("1-3天");
            tyid = "4";
            /*策划师：过往经验、成功案例*/
        } else if (cateId.equals("40")) {

            llTime.setVisibility(View.VISIBLE);
            llLocation.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.VISIBLE);
            llRemarks.setVisibility(View.VISIBLE);
            llBeforePrice.setVisibility(View.VISIBLE);
            llPrice.setVisibility(View.VISIBLE);
            llHuazhuang.setVisibility(View.VISIBLE);
            llHuazhuangping.setVisibility(View.VISIBLE);
            et_title.setHint("生活妆、上镜妆、新娘妆");
           // etShootingTime.setHint("1小时");
            etTime.setHint("1小时");
            tyid = "5";
           /* 化妆品：使用品牌
            化妆师：过往经验、成功案例*/
        } else {
            llTitle.setVisibility(View.VISIBLE);
            llWorkType.setVisibility(View.VISIBLE);
            llImageType.setVisibility(View.VISIBLE);
            llOther.setVisibility(View.VISIBLE);
            llRegin.setVisibility(View.VISIBLE);
            tvTitle.setText("设置标题");
        }
    }

    //上传产品
    private void upLoad() {
        String title = "";
        String serviceName = "";
        String location = "";
        String etPrice = "";
        if (TextUtils.isEmpty(image_default)) {
            ToastUtil.toast("请上传产品封面");
            return;
        }
        String attachment = getAttachment();
        Logger.d("attachment=" + attachment);
        if (TextUtils.isEmpty(attachment)) {
            ToastUtil.toast("请上传产品");
            return;
        }
        title = et_title.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toast("请输入标题");
            return;
        }
        if (cateId.equals("5") || cateId.equals("3") || cateId.equals("2") || cateId.equals("8")) {
          /*  serviceName = etServiceName.getText().toString();
            if (TextUtils.isEmpty(serviceName)) {
                ToastUtil.toast("请输入服务名称");
                return;
            }*/
            location = etLocatiom.getText().toString();
            if (TextUtils.isEmpty(location)) {
                ToastUtil.toast("请输入地点");
                return;
            }
            otherOrLocation = location;
            etPrice = etPresentPrice.getText().toString();
            if (TextUtils.isEmpty(etPrice)) {
                ToastUtil.toast("请输入价格");
                return;
            }
        } else if (cateId.equals("7")) {
            etPrice = etPresentPrice.getText().toString();
            if (TextUtils.isEmpty(etPrice)) {
                ToastUtil.toast("请输入价格");
                return;
            }
        } else if (cateId.equals("1") || cateId.equals("40") || cateId.equals("4")) {
            /*serviceName = etServiceName.getText().toString();
            if (TextUtils.isEmpty(serviceName)) {
                ToastUtil.toast("请输入服务名称");
                return;
            }*/
            etPrice = etPresentPrice.getText().toString();
            if (TextUtils.isEmpty(etPrice)) {
                ToastUtil.toast("请输入价格");
                return;
            }
        } else {
            if (TextUtils.isEmpty(industry_id)) {
                ToastUtil.toast("请选择行业类型");
                return;
            }
            String other = et_other.getText().toString();
            otherOrLocation = other;
        }

/*
        String etPrice = etPresentPrice.getText().toString();
        if (TextUtils.isEmpty(etPrice)) {
            ToastUtil.toast("请选择价格");
            return;
        }*/
      /*  String serviceName = etServiceName.getText().toString();
        if (TextUtils.isEmpty(serviceName)) {
            ToastUtil.toast("请选择服务名称");
            return;
        }*/

//        if(TextUtils.isEmpty(image_type_id)){
//            ToastUtil.toast("请选择图片类型");
//            return;
//        }
//        if(TextUtils.isEmpty(region_id)){
//            ToastUtil.toast("请选择所在地区");
//            return;
//        }

        String other = et_other.getText().toString();
        String remarks = etRemarks.getText().toString();
        String originalPrice = etOriginalPrice.getText().toString();
        String staffing = etStaffing.getText().toString();
        String shootingTime = etShootingTime.getText().toString();
        String equipment = etEquipment.getText().toString();
        String introduce = etIntroduce.getText().toString();
        String detail = etDetail.getText().toString();

        //时长
        String shopTime = etShopTime.getText().toString();
        //拍摄张数
        String paisheCount = etPaisheCount.getText().toString();
        //化妆
        String huazhuang = etHuazhuang.getText().toString();
        //摄影师
        String sheyingshi = etSheyingshi.getText().toString();
        //设计师
        String shejishi = etShejishi.getText().toString();
        //
        String time = etTime.getText().toString();
        //化妆品
        String huazhuangping = etHuazhuangping.getText().toString();

        String cehua = etCeHua.getText().toString();
     /*   etRemarks = footer.findViewById(R.id.et_remarks);
        etOriginalPrice = footer.findViewById(R.id.et_original_price);
        etStaffing = footer.findViewById(R.id.et_staffing);
        etShootingTime = footer.findViewById(R.id.et_shooting_time);
        etEquipment = footer.findViewById(R.id.et_equipment);
        etIntroduce = footer.findViewById(R.id.et_introduce);
        etDetail = footer.findViewById(R.id.et_detailed);*/
//        if(TextUtils.isEmpty(other)){
//            ToastUtil.toast("请输入产品相关说明（10字以内）");
//            return;
//        }
        ShopApi.upLoadImageProduct(image_default, attachment, title, industry_id, image_type_id, region_id,
                otherOrLocation, etPrice, remarks, originalPrice, staffing, shootingTime, equipment, introduce,
                detail,tyid,shopTime,paisheCount,huazhuang,sheyingshi,shejishi,time,huazhuangping,cehua,
                new ResponseImpl<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.toast("上传成功");
                        _mActivity.setResult(RESULT_OK);
                        setFragmentResult(RESULT_OK, new Bundle());
                        pop();
                    }

                    @Override
                    public void onStart(int mark) {
                        setShowProgressDialog(true);
                        super.onStart(mark);
                    }

                    @Override
                    public void onDataError(String status, String errormessage) {
                        super.onDataError(status, errormessage);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                    }
                }, Object.class);
    }

    private String getAttachment() {
        String result = "";
        for (int i = 0; i < adapter.getData().size(); i++) {
            String file_name = adapter.getData().get(i).getQiniuFileName();
            if (!TextUtils.isEmpty(file_name)) {
                result += file_name + ",";
            }

        }
        return result;
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
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("requestcode=" + requestCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0x11:
                    //选择行业
                    industry_id = data.getExtras().getString("id", "");
                    String name = data.getExtras().getString("name", "");
                    tv_industry.setText(name);
                    //  Log.e("hangye","id="+industry_id+",name="+name);
                    // Logger.d("id="+industry_id+",name="+name);
                    break;
                case 0x22:
                    //图片类型
                    image_type_id = data.getExtras().getString("id", "");
                    tv_image_type.setText(data.getExtras().getString("name", ""));
                    break;
                case 0x33:
                    //所在地区
                    region_id = data.getExtras().getString("id", "");
                    tv_region.setText(data.getExtras().getString("name", ""));
                    break;
                default:
//                   PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
//                       @Override
//                       public void onResultCamara(ArrayList<TImage> resultCamara) {
//                          // ToastUtil.toast("相机url="+resultCamara.get(0).getCompressPath());
//                           if(imageType==1){
//                               //上传封面
//                               File[] files;
//                               ArrayList<File> arr = new ArrayList<>();
//                               String url = resultCamara.get(0).getCompressPath();
//                               arr.add(new File(url));
//                               files = arr.toArray(new File[arr.size()]);
//                               Message message = handler.obtainMessage();
//                               Bundle bundle = new Bundle();
//                               message.obj = files;
//                               bundle.putString("url", url);
//                               message.setData(bundle);
//                               message.sendToTarget();
//                           }else {
//                               getImageData(resultCamara);
//                           }
//
//                       }
//
//                       @Override
//                       public void onResultGalary(ArrayList<TImage> resultGalayr) {
//                           //ToastUtil.toast("相册url="+resultGalayr.get(0).getCompressPath());
//                           if(imageType==1){
//                               //上传封面
//                               File[] files;
//                               ArrayList<File> arr = new ArrayList<>();
//                               String url = resultGalayr.get(0).getCompressPath();
//                               arr.add(new File(url));
//                               files = arr.toArray(new File[arr.size()]);
//                               Message message = handler.obtainMessage();
//                               Bundle bundle = new Bundle();
//                               message.obj = files;
//                               bundle.putString("url", url);
//                               message.setData(bundle);
//                               message.sendToTarget();
//                           }else {
//                               getImageData(resultGalayr);
//                           }
//
//
//                       }
//                   });
                    PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                        @Override
                        public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
                            if (imageType == 1) {
                                File[] files;
                                ArrayList<File> arr = new ArrayList<>();
                                String url = PhotoUtil.getFilePash(resultCamara.get(0));
                                arr.add(new File(url));
                                files = arr.toArray(new File[arr.size()]);
                                Message message = handler.obtainMessage();
                                Bundle bundle = new Bundle();
                                message.obj = files;
                                bundle.putString("url", url);
                                message.setData(bundle);
                                message.sendToTarget();
                            } else {
                                getImageData(resultCamara);
                            }
                        }

                        @Override
                        public void onResultGalary(ArrayList<LocalMedia> resultCamara) {
                            if (imageType == 1) {
                                File[] files;
                                ArrayList<File> arr = new ArrayList<>();
                                String url = PhotoUtil.getFilePash(resultCamara.get(0));
                                arr.add(new File(url));
                                files = arr.toArray(new File[arr.size()]);
                                Message message = handler.obtainMessage();
                                Bundle bundle = new Bundle();
                                message.obj = files;
                                bundle.putString("url", url);
                                message.setData(bundle);
                                message.sendToTarget();
                            } else {
                                getImageData(resultCamara);
                            }
                        }
                    });
            }
        }
    }

    //    public List<UpLoadImageBean> getImageData(ArrayList<TImage> source) {
//        List<UpLoadImageBean> imageBeen = new ArrayList<UpLoadImageBean>();
//        for (TImage bean : source) {
//            imageBeen.add(new UpLoadImageBean(bean));
//        }
//        int oldSize = adapter.getData().size();
//        if (oldSize == 0) {
//            //第一次添加
//            if (imageBeen.size() == 9) {
//                //一次性添加了9次图片，则直接加入
//                adapter.setNewData(imageBeen);
//            } else {
//                //没有一次性添加完毕，则追加 addIcon
//                imageBeen.add(new UpLoadImageBean());
//                adapter.setNewData(imageBeen);
//
//            }
//
//
//        }//不是第一次添加，并且第二次就 加满
//        else if (imageBeen.size() + oldSize == 10) {
//            //说明有9张图片，1个添加图片，移除原adapter的最后一张图片,并且将所有的图片都加入adapter
//            Logger.d("不是第一次");
//            adapter.getData().remove(adapter.getData().size() - 1);
//            adapter.notifyDataSetChanged();
//            adapter.getData().addAll(imageBeen);
//            adapter.notifyDataSetChanged();
//
//        } else {
//            //不是第一次添加。并且第二次也没加满
//            //oldsize>0 原来有数据，但是还未添加满。则此时一定有add icon，remove 原来的，addicon 同时加入新数据，最后再添加addicon
//            adapter.getData().remove(adapter.getData().size() - 1);
//            adapter.notifyDataSetChanged();
//            imageBeen.add(new UpLoadImageBean());
//            adapter.getData().addAll(imageBeen);
//            adapter.notifyDataSetChanged();
//
//        }
//        return imageBeen;
//    }
    public List<UpLoadImageBean> getImageData(ArrayList<LocalMedia> source) {
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


        }//不是第一次添加，并且第二次就 加满
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
    public boolean onBackPressedSupport() {
        hideSoftInput();
        DialogUtil.getTowButtonDialog(_mActivity, "提示", "您的产品还没有上传,您确定要退出么?", "取消", "确定", new DialogUtil.OnDialogHandleListener() {
            @Override
            public void onCancleClick(SweetAlertDialog sDialog) {

            }

            @Override
            public void onConfirmeClick(SweetAlertDialog sDialog) {
                pop();

            }
        }).show();
        return true;

    }


}
