package cn.hym.superlib.adapter;

import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.util.List;

import cn.hym.superlib.R;
import cn.hym.superlib.bean.local.UpLoadImageBean;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/3/11.
 * <p>
 * Description 上传视频adapter
 * <p>
 * otherTips
 */

public class UpLoadVadieoProductAdapter extends BaseMultiItemQuickAdapter<UpLoadImageBean, BaseViewHolder> {

    Fragment fragment;
    onDeleteListener listener;
    boolean showMain = true;//显示主图
    boolean showDelete = true;//显示删除按钮
    String token;
    Configuration config;
    UploadManager uploadManager;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isShowMain() {
        return showMain;
    }

    public void setShowMain(boolean showMain) {
        this.showMain = showMain;
    }

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public void setListener(onDeleteListener listener) {
        this.listener = listener;
    }

    public UpLoadVadieoProductAdapter(Fragment fragment, List<UpLoadImageBean> data) {
        super(data);
        this.fragment = fragment;
        addItemType(UpLoadImageBean.type_normal, R.layout.item_upload_video);
        addItemType(UpLoadImageBean.type_add, R.layout.item_add_image);
        config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone1)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        uploadManager = new UploadManager(config);

    }

    int max = 8;

    public void setMax(int max) {
        this.max = max - 1;
    }

    @Override
    protected void convert(final BaseViewHolder helper,final UpLoadImageBean item) {
        int width = ScreenUtil.getScreenWidth(fragment.getContext()) - ScreenUtil.dip2px(fragment.getContext(), 40);
        switch (item.getItemType()) {
            case UpLoadImageBean.type_normal:
                //适配
                final int position = helper.getLayoutPosition() - getHeaderLayoutCount();
                FrameLayout fl = helper.getView(R.id.fl);
                fl.getLayoutParams().width = width / 3;
                fl.getLayoutParams().height = width / 3;
                final ImageView iv_icon = helper.getView(R.id.iv_icon);
                //iv_icon.setPadding(ScreenUtil.dip2px(fragment.getContext(),5),ScreenUtil.dip2px(fragment.getContext(),5),ScreenUtil.dip2px(fragment.getContext(),5),ScreenUtil.dip2px(fragment.getContext(),5));
                ImageView iv_delete = helper.getView(R.id.iv_delete);
                TextView tv_main = helper.getView(R.id.tv_main_image);
                // Logger.d("layoutPosition="+helper.getLayoutPosition()+"===adapterPosition="+helper.getAdapterPosition());
                if (helper.getLayoutPosition() == 0 && showMain) {
                    tv_main.setVisibility(View.VISIBLE);
                } else {
                    tv_main.setVisibility(View.GONE);
                }
                iv_delete.setVisibility(showDelete ? View.VISIBLE : View.GONE);
                iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ToastUtil.toast("删除position="+helper.getAdapterPosition());

                        if (listener != null) {
                            listener.onDelete(position);
                        }
                        mData.remove(position);
                        if (mData.size() == max) {
                            boolean hasAdd = false;
                            for (UpLoadImageBean bean : mData) {
                                if (bean.getItemType() == UpLoadImageBean.type_add) {
                                    hasAdd = true;
                                    break;
                                }
                            }
                            if (!hasAdd) {
                                mData.add(new UpLoadImageBean());
                            }
                        }
                        notifyDataSetChanged();

                    }
                });
                final LinearLayout ll_uploading = helper.getView(R.id.ll_uploading);
                final TextView tv_percent = helper.getView(R.id.tv_percent);
                final ProgressBar progressBar = helper.getView(R.id.progressbar);
                tv_percent.setText("上传中..."+"0%");
                progressBar.setProgress(0);
                final String path = item.getImage().getCompressPath();
//                MediaMetadataRetriever retr = new MediaMetadataRetriever();
//                retr.setDataSource(path);
//                String fileheight = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
//                String filewidth = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
//                Logger.d("视频宽="+filewidth+",视频高"+fileheight);
                String qiniufile_name =   System.currentTimeMillis() / 1000 + "_"+path.substring(path.lastIndexOf("/")+1);
                //Logger.d("qiniufilename=" + qiniufile_name + ",token=" + token);
                TextView tv_time_long=helper.getView(R.id.tv_time_long);
                tv_time_long.setVisibility(View.VISIBLE);
                tv_time_long.setText(item.getDuration());
                boolean hasUpload = item.isHasUpload();
                if (!hasUpload) {
                    //未上传
                    Logger.d("adaptertoken="+token);
                    if(TextUtils.isEmpty(token)){
                        ToastUtil.toast("token异常请检查您的网络后刷新重试");
                        return;
                    }
                    ll_uploading.setVisibility(View.VISIBLE);
                    iv_icon.setVisibility(View.GONE);
                    uploadManager.put(path, qiniufile_name, token, new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {
                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                           try{
                               if(info.isOK()) {
                                   ImageUtil.getInstance().loadRoundCornerImage(fragment, item.getImage().getCompressPath(), iv_icon, 5);
                                   iv_icon.setVisibility(View.VISIBLE);
                                   ll_uploading.setVisibility(View.GONE);
                                   item.setHasUpload(true);
                                   item.setQiniuFileName(key);
                                   Logger.d("qiniu="+ "Upload Success");
                               } else {
                                   Logger.d("qiniu="+ "Upload Fail");
                                   //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                               }
                               Logger.d("key="+key + ",\r\n " + info + ",\r\n " + response);
                           }catch (Exception e){
                               Logger.d(e.toString());
                           }
                        }
                    }, new UploadOptions(null, null, false,
                            new UpProgressHandler() {
                                public void progress(String key, double percent) {
                                   try {
                                       int percent_int=(int) (percent*100);
                                       //Logger.d("key="+key + ",percent " + percent);
                                       tv_percent.setText("上传中..."+percent_int+"%");
                                       progressBar.setProgress(percent_int);
                                   }catch (Exception e){
                                       e.toString();
                                   }
                                }
                            }, null));

                } else {
                    //已上传
                    ImageUtil.getInstance().loadRoundCornerImage(fragment, item.getImage().getCompressPath(), iv_icon, 5);
                    iv_icon.setVisibility(View.VISIBLE);
                    ll_uploading.setVisibility(View.GONE);
                }

                break;
            case UpLoadImageBean.type_add:
                TextView tv_add = helper.getView(R.id.tv_add);
                tv_add.getLayoutParams().width = width / 3-ScreenUtil.dip2px(fragment.getContext(),15);
                tv_add.getLayoutParams().height = width / 3-ScreenUtil.dip2px(fragment.getContext(),15);
                break;
        }
    }

    public interface onDeleteListener {
        public void onDelete(int position);
    }

}
