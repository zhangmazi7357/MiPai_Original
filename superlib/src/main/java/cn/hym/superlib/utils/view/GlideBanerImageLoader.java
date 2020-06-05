package cn.hym.superlib.utils.view;

import android.content.Context;
import android.widget.ImageView;

import com.hym.imagelib.ImageUtil;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by  胡彦明 on 2017/5/15.
 * <p>
 * Description banner图片解析器，这里使用glide作为解析
 * <p>
 * OtherTips
 */

public class GlideBanerImageLoader implements ImageLoaderInterface<ImageView> {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageUtil.getInstance().loadImage(context,path,imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
