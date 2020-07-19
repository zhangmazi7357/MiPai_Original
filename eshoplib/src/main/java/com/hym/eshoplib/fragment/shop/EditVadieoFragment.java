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
import com.hym.eshoplib.bean.shop.ProductDetailBean;
import com.hym.eshoplib.http.CommonApi;
import com.hym.eshoplib.http.shopapi.ShopApi;
import com.hym.imagelib.ImageUtil;
import com.hym.photolib.utils.PhotoUtil;
import com.jph.takephoto.model.TImage;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.UpLoadVadieoProductAdapter;
import cn.hym.superlib.bean.UploadFilesBean;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SoftHideKeyBoardUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.widgets.view.RequiredTextView;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by 胡彦明 on 2018/9/11.
 * <p>
 * Description 编辑产品
 * <p>
 * otherTips
 */

public class EditVadieoFragment extends BaseKitFragment {
    UpLoadVadieoProductAdapter adapter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    String qiniuToken;
    int imageType=-1;// 1 上传封面，2上传产品
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
    TextView tv_upload_title;
    TextView tv_vadieo_title_type;
    String id;//产品id
    ProductDetailBean data;//产品
    TextView tv_upload_subtitle;
    private EditText etServiceName;
    private EditText etPresentPrice;
    private EditText etRemarks;
    private EditText etOriginalPrice;
    private EditText etStaffing;
    private EditText etShootingTime;
    private EditText etEquipment;
    private EditText etIntroduce;
    private EditText etDetail;
    private EditText etShopTime;
    private EditText etPaisheCount;
    private EditText etHuazhuang;
    private EditText etSheyingshi;
    private EditText etShejishi;
    private EditText etTime;
    private EditText etHuazhuangping;
    private EditText etLocatiom;
    private EditText etCeHua;
    private String cateId;
    private String tyid;

    private List<LocalMedia> selectList = new ArrayList<>();
    private String location;
    private String etPrice;
    private String length;
    private String otherOrLocation;

    public static EditVadieoFragment newInstance(Bundle args) {
        EditVadieoFragment fragment = new EditVadieoFragment();
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
        return R.layout.fragment_uploadvadieo;
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
                        ImageUtil.getInstance().loadRoundCornerImage(EditVadieoFragment.this, url, iv_image,5);
                        iv_image.setVisibility(View.VISIBLE);
                        tv_add.setVisibility(View.GONE);
                        image_default=data.getData().getAttachment_id().get(0);
                    }
                },UploadFilesBean.class);
            }
        };
        qiniuToken=getArguments().getString("token","");
        id=getArguments().getString("id","");
        if(TextUtils.isEmpty(qiniuToken)||TextUtils.isEmpty(id)){
            ToastUtil.toast("数据异常");
            pop();
            return;
        }
        setShowProgressDialog(true);
        showBackButton();
        setTitle("编辑视频");
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });
        rvList.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new UpLoadVadieoProductAdapter(this, null);
        adapter.setShowDelete(false);
        adapter.setToken(qiniuToken);
        adapter.addData(new UpLoadImageBean());
        rvList.setAdapter(adapter);
        adapter.setListener(new UpLoadVadieoProductAdapter.onDeleteListener() {
            @Override
            public void onDelete(int position) {

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter2, View view, int position) {
                if (adapter.getData().get(position).getItemType() == 1) {
                   // ToastUtil.toast("预览视频");
                    PictureSelector.create(EditVadieoFragment.this).externalPictureVideo(adapter.getData().get(position).getImage().getCompressPath());
                } else {
                    //上传视频
                    // 进入相册 以下是例子：不需要的api可以不写
                    PictureSelector.create(EditVadieoFragment.this)
                            .openGallery(PictureMimeType.ofVideo())
                            .theme( R.style.picture_default_style)
                            .maxSelectNum(1)
                            .minSelectNum(1)
                            .selectionMode(PictureConfig.SINGLE )
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(false)
                            .enableCrop(true)
                            .compress(true)
                            .glideOverride(160, 160)
                            .previewEggs(true)
                            .withAspectRatio(16, 9)
                            .hideBottomControls(false)
                            .isGif(false)
                            .freeStyleCropEnabled(true)
                            .circleDimmedLayer(true)
                            .showCropFrame(true)
                            .showCropGrid(true)
                            .openClickSound(true)
                            .selectionMedia(selectList)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        });
        adapter.setMax(1);
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.header_upload_image, null, false);
        tv_upload_title=header.findViewById(R.id.tv_upload_title);
        tv_upload_title.setText("上传视频");
        tv_upload_subtitle=header.findViewById(R.id.tv_upload_subtitle);
        tv_upload_subtitle.setText("(请上传500M以内的产品，建议mp4格式，其他格式产品将在审核后可观看)");
        iv_image=header.findViewById(R.id.iv_image);
        tv_add=header.findViewById(R.id.tv_add);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageType=1;
                PhotoUtil.ShowDialog(EditVadieoFragment.this, 1, false,2);
            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageType=1;
                PhotoUtil.ShowDialog(EditVadieoFragment.this, 1, false,2);
            }
        });
        View footer = LayoutInflater.from(_mActivity).inflate(R.layout.footer_upload_image, null, false);
        tv_vadieo_title_type=footer.findViewById(R.id.tv_type_title);
        tv_vadieo_title_type.setText("视频类型");
        tv_industry=footer.findViewById(R.id.tv_type);
        tv_industry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,ActionActivity.Action_SelectIndustry);
                bundle.putString("type","1");
                ActionActivity.startForResult(EditVadieoFragment.this, bundle,0x11);

            }
        });
        tv_image_type=footer.findViewById(R.id.tv_immage_type);
        tv_image_type.setHint("请选择视频类型");
        tv_image_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,ActionActivity.Action_SelectIndustry);
                bundle.putString("type","2");
                ActionActivity.startForResult(EditVadieoFragment.this, bundle,0x22);
            }
        });
        tv_region=footer.findViewById(R.id.tv_region);
        tv_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=BaseActionActivity.getActionBundle(ActionActivity.ModelType_Shop,ActionActivity.Action_SelectRegion);
                bundle.putString("id",region_id);
                ActionActivity.startForResult(EditVadieoFragment.this, bundle,0x33);
            }
        });
        LinearLayout llOther = footer.findViewById(R.id.ll_other);
        LinearLayout llImageType = footer.findViewById(R.id.ll_type_title);
        LinearLayout llWorkType = footer.findViewById(R.id.ll_wrok_type);
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

        RequiredTextView rl =  footer.findViewById(R.id.rtv_location);
        rl.setTextColor(Color.parseColor("#ff3333"));
        RequiredTextView rp =  footer.findViewById(R.id.rtv_price);
        rp.setTextColor(Color.parseColor("#ff3333"));
        RequiredTextView tvTitle = footer.findViewById(R.id.rtv_title);
        tvTitle.setTextColor(Color.parseColor("#ff3333"));

        et_title = footer.findViewById(R.id.et_title);
        et_other = footer.findViewById(R.id.et_other);

        etPresentPrice = footer.findViewById(R.id.et_present_price);
        etRemarks = footer.findViewById(R.id.et_remarks);
        etOriginalPrice = footer.findViewById(R.id.et_original_price);
        etStaffing = footer.findViewById(R.id.et_staffing);
        etShootingTime = footer.findViewById(R.id.et_shooting_time);
        etEquipment = footer.findViewById(R.id.et_equipment);
        etIntroduce = footer.findViewById(R.id.et_introduce);
        etDetail = footer.findViewById(R.id.et_detailed);
        etShopTime = footer.findViewById(R.id.et_shop_time);//时长 差
        etPaisheCount = footer.findViewById(R.id.et_paishe_count);
        etHuazhuang = footer.findViewById(R.id.et_huazhuang);
        etSheyingshi = footer.findViewById(R.id.et_sheyingshi);
        etShejishi = footer.findViewById(R.id.et_shejishi);
        etTime = footer.findViewById(R.id.et_time);
        etHuazhuangping = footer.findViewById(R.id.et_huazhuangping);
        etLocatiom = footer.findViewById(R.id.et_location);  //差
        etCeHua = footer.findViewById(R.id.et_cehuashi);

        cateId = getArguments().getString("cateId");

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
        }
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);

    }
    //上传产品
    private void upLoad() {
        String attachment=adapter.getData().get(0).getQiniuFileName();
        Logger.d("attachment="+attachment);
//        if(TextUtils.isEmpty(attachment)){
//            ToastUtil.toast("请上传产品");
//            return;
//        }
        String title=et_title.getText().toString();
        if(TextUtils.isEmpty(title)){
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
            length = adapter.getData().get(0).getDuration();
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
        length = shopTime;
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
//        if(TextUtils.isEmpty(other)){
//            ToastUtil.toast("请输入产品相关说明（10字以内）");
//            return;
//        }
        ShopApi.editVideoProduct(id,image_default, attachment, title, industry_id, image_type_id, region_id, otherOrLocation,length,
                etPrice, remarks, originalPrice, staffing, shootingTime, equipment, introduce,
                detail,tyid,shopTime,paisheCount,huazhuang,sheyingshi,shejishi,time,huazhuangping,cehua,new ResponseImpl<Object>() {
            @Override
            public void onSuccess(Object data) {
                ToastUtil.toast("修改成功");
                _mActivity.setResult(RESULT_OK);
                pop();
            }

            @Override
            public void onStart(int mark) {
                setShowProgressDialog(true);
                super.onStart(mark);
            }
        },Object.class);


    }



    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ShopApi.getProductDetail(id, new ResponseImpl<ProductDetailBean>() {
            @Override
            public void onSuccess(final ProductDetailBean result) {
                data = result;
                et_title.setText(data.getData().getTitle() + "");
                // 工作室信息
                ImageUtil.getInstance().loadRoundCornerImage(EditVadieoFragment.this, data.getData().getImage_default(), iv_image,5);
                iv_image.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.GONE);
                //产品信息
                tv_industry.setText(data.getData().getIndustry() + "");
                tv_image_type.setText(data.getData().getVideo() + "");
                tv_region.setText(data.getData().getRegion_name() + "");
                et_other.setText(data.getData().getOther() + "");
                ProductDetailBean.DataBean data = result.getData();
                etCeHua.setText(data.getPlanner());
                etHuazhuangping.setText(data.getCosmetics());
                etTime.setText(data.getTimes());
                etShejishi.setText(data.getDesigner());
                etSheyingshi.setText(data.getPhotographer());
                etHuazhuang.setText(data.getDresser());
                etPaisheCount.setText(data.getNums()+"张");
                etShopTime.setText(data.getLength());
                etDetail.setText(data.getDetails());
                etIntroduce.setText(data.getIntroduce());
                etEquipment.setText(data.getEquipment());
                etShootingTime.setText(data.getShooting_time());
                etStaffing.setText(data.getStaffing());
                etOriginalPrice.setText(data.getOriginal_price());
                etRemarks.setText(data.getRemarks());
                etPresentPrice.setText(data.getPresent_price());
                etLocatiom.setText(data.getOther());//可以复用
                List<UpLoadImageBean>list=new ArrayList<>();
                TImage tImage=new TImage(result.getData().getFilepath(), TImage.FromType.OTHER);
                tImage.setCompressPath(result.getData().getFilepath());
                UpLoadImageBean bean=new UpLoadImageBean(tImage);
                bean.setDuration(result.getData().getLength());
                bean.setHasUpload(true);
                list.add(bean);
                adapter.setNewData(list);


            }
        }, ProductDetailBean.class);


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
        if (resultCode == _mActivity.RESULT_OK) {
           switch (requestCode){
               case 0x11:
                   //选择行业
                   industry_id=data.getExtras().getString("id","");
                   String name=data.getExtras().getString("name","");
                   tv_industry.setText(name);
                  // Logger.d("id="+industry_id+",name="+name);
                   break;
               case 0x22:
                   //图片类型
                   image_type_id=data.getExtras().getString("id","");
                   tv_image_type.setText(data.getExtras().getString("name",""));
                   break;
               case 0x33:
                   //所在地区
                   region_id=data.getExtras().getString("id","");
                   tv_region.setText(data.getExtras().getString("name",""));
                   break;
               case PictureConfig.CHOOSE_REQUEST:
                   // 图片选择结果回调
                   selectList = PictureSelector.obtainMultipleResult(data);
                   List<UpLoadImageBean>list=new ArrayList<>();
                   // 例如 LocalMedia 里面返回三种path
                   // 1.media.getPath(); 为原图path
                   // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                   // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                   // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                   for (LocalMedia media : selectList) {
                       Logger.d("视频路径》"+ media.getPath());
                       String path = ""; //相当于缩略图的地址
                       String duration = timeParse(media.getDuration());//视频时长
                       if (media.isCut() && !media.isCompressed()) {
                           // 裁剪过
                           path = media.getCutPath();
                       } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                           // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                           path = media.getCompressPath();
                       } else {
                           // 原图
                           path = media.getPath();
                       }
                       TImage tImage=new TImage(path, TImage.FromType.OTHER);
                       tImage.setCompressPath(path);
                       UpLoadImageBean bean=new UpLoadImageBean(tImage);
                       bean.setDuration(duration);
                       list.add(bean);

                   }
                   adapter.setNewData(list);


                   break;
               default:
//                   PhotoUtil.getImageList(requestCode, data, new PhotoUtil.OnImageResult() {
//                       @Override
//                       public void onResultCamara(ArrayList<TImage> resultCamara) {
//                           //ToastUtil.toast("相机url="+resultCamara.get(0).getCompressPath());
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
//                           }
//
//                       }
//
//                       @Override
//                       public void onResultGalary(ArrayList<TImage> resultGalayr) {
//                           // ToastUtil.toast("相册url="+resultCamara.get(0).getCompressPath());
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
//                           }
//
//
//                       }
//                   });
                   PhotoUtil.getImageList2(requestCode, data, new PhotoUtil.OnImageResult2() {
                       @Override
                       public void onResultCamera(ArrayList<LocalMedia> resultCamara) {
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
                           message.obj = files;
                           bundle.putString("url", url);
                           message.setData(bundle);
                           message.sendToTarget();
                       }
                   });
           }
        }
    }
    @Override
    public boolean onBackPressedSupport() {
        hideSoftInput();
        DialogUtil.getTowButtonDialog(_mActivity, "提示", "您的产品还没有保存,您确定要退出么?", "取消", "确定", new DialogUtil.OnDialogHandleListener() {
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
    public  String timeParse(long duration) {
        String time = "";
        if(duration > 1000L) {
            time = timeParseMinute(duration);
        } else {
            long minute = duration / 60000L;
            long seconds = duration % 60000L;
            long second = (long)Math.round((float)seconds / 1000.0F);
            if(minute < 10L) {
                time = time + "0";
            }

            time = time + minute + ":";
            if(second < 10L) {
                time = time + "0";
            }

            time = time + second;
        }

        return time;
    }

    private SimpleDateFormat msFormat = new SimpleDateFormat("mm:ss");

    private String timeParseMinute(long duration) {
        try {
            return msFormat.format(duration);
        } catch (Exception e) {
            e.printStackTrace();
            return "0:00";
        }
    }

}
