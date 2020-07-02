package cn.hym.superlib.manager;

import android.app.Activity;
import android.text.TextUtils;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 集成所有的友盟功能
 */
public class OtherPlatformManager {


    private static OtherPlatformManager INSTANCE = null;

    private OtherPlatformManager() {

    }


    public static OtherPlatformManager getInstance() {
        if (INSTANCE == null) {
            synchronized (OtherPlatformManager.class) {
                if (INSTANCE == null) {
                    return INSTANCE = new OtherPlatformManager();
                }
            }
        }

        return INSTANCE;
    }


    public void share(Activity activity, String webUrl,
                      String title, String description,
                      String imageUrl, int imageId,
                      SHARE_MEDIA platform, final OtherShareCallback callback) {

        UMWeb web = new UMWeb(webUrl);
        web.setTitle(title);
        web.setDescription(description);
        if (TextUtils.isEmpty(imageUrl)) {
            web.setThumb(new UMImage(activity, imageId));  // 本地缩略图
        } else {
            web.setThumb(new UMImage(activity, imageUrl));
        }


        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        callback.shareOnResult(share_media);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        callback.shareError(share_media, throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        callback.shareCancel(share_media);
                    }
                }).share();

    }


    interface OtherShareCallback {
        void shareOnResult(SHARE_MEDIA share_media);

        void shareCancel(SHARE_MEDIA share_media);

        void shareError(SHARE_MEDIA share_media, Throwable throwable);
    }
}
