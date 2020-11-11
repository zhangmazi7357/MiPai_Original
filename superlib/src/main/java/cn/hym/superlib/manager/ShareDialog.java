package cn.hym.superlib.manager;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hym.imagelib.ImageUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hym.superlib.R;

/**
 * 用来分享的 Dialog ;
 */
public class ShareDialog extends DialogFragment implements View.OnClickListener {

    private LinearLayout shareWxCircle;
    private LinearLayout shareWx;
    private LinearLayout shareQq;
    private LinearLayout shareQZone;
    private LinearLayout shareWb;
    private TextView cancel;


    private ShareBean shareBean;


    public ShareDialog(ShareBean bean) {
        this.shareBean = bean;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_share, container, false);
        ButterKnife.bind(this, rootView);
        shareWxCircle = rootView.findViewById(R.id.share_wx);
        shareWx = rootView.findViewById(R.id.share_wx_c);
        shareQq = rootView.findViewById(R.id.share_qq);
        shareQZone = rootView.findViewById(R.id.share_qqkj);
        shareWb = rootView.findViewById(R.id.share_wb);
        cancel = rootView.findViewById(R.id.cancel);

        shareWxCircle.setOnClickListener(this);
        shareWx.setOnClickListener(this);
        shareQq.setOnClickListener(this);
        shareQZone.setOnClickListener(this);
        shareWb.setOnClickListener(this);
        cancel.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

    }


    @OnClick
    public void onClick(View view) {


        int id = view.getId();
        if (id == R.id.share_wx_c) {

            UMWeb web = new UMWeb(shareBean.getUrl());
            String title = shareBean.getTitle();
            web.setTitle(title + " | 拍照片、拍视频、上觅拍");//标题
            web.setThumb(new UMImage(getActivity(), shareBean.getImgUrl()));  //缩略图
            new ShareAction(getActivity())
                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                    .withMedia(web)
                    .share();
            dismiss();

        } else if (id == R.id.share_wx) {

            share(SHARE_MEDIA.WEIXIN);
            dismiss();

        } else if (id == R.id.share_qq) {


            share(SHARE_MEDIA.QQ);

        } else if (id == R.id.share_qqkj) {

            share(SHARE_MEDIA.QZONE);

        } else if (id == R.id.share_wb) {

            share(SHARE_MEDIA.SINA);
            dismiss();

        } else if (id == R.id.cancel) {

            dismiss();
        }
    }


    private void share(SHARE_MEDIA platform) {

        UMWeb web = new UMWeb(shareBean.getUrl());

        String title = shareBean.getTitle();
        if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
            title = shareBean.getTitle() + "| 找觅拍";
        }

        web.setTitle(title);

        web.setDescription("拍照片、拍视频、上觅拍！");
        web.setThumb(new UMImage(getActivity(), shareBean.getImgUrl()));


        new ShareAction(getActivity())
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).share();

    }
}
