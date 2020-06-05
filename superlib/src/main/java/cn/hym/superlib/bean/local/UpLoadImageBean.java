package cn.hym.superlib.bean.local;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jph.takephoto.model.TImage;

/**
 * Created by 胡彦明 on 2018/3/11.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class UpLoadImageBean implements MultiItemEntity {

    public int item_type=-1;
    public static final int type_normal=1;//普通图片
    public static final int type_add=2;//添加按钮
    private boolean hasUpload=false;
    TImage image;
    String qiniuFileName;
    String duration;//视频时长，转换好的格式

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQiniuFileName() {
        return qiniuFileName;
    }

    public void setQiniuFileName(String qiniuFileName) {
        this.qiniuFileName = qiniuFileName;
    }

    @Override
    public int getItemType() {
        return item_type;
    }
    //上传的图片
    public UpLoadImageBean(TImage image) {
        this.image = image;
        this.item_type=type_normal;
    }
    //添加按钮
    public UpLoadImageBean() {
        this.item_type=type_add;
    }

    public TImage getImage() {
        return image;
    }

    public void setImage(TImage image) {
        this.image = image;
    }

    public boolean isHasUpload() {
        return hasUpload;
    }

    public void setHasUpload(boolean hasUpload) {
        this.hasUpload = hasUpload;
    }
}
