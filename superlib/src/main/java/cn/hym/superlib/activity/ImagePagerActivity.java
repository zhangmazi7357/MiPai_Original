package cn.hym.superlib.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.R;
import cn.hym.superlib.activity.base.BasekitActivity;
import cn.hym.superlib.utils.view.ScreenUtil;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片列表显示
 */
public class ImagePagerActivity extends BasekitActivity {
    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    public static final String INTENT_IMAGESIZE = "imagesize";

    private List<View> guideViewList = new ArrayList<View>();
    private LinearLayout guideGroup;
    public ImageSize imageSize;
    private int startPos;
    private ArrayList<String> imgUrls;
    private RelativeLayout rl_root;


    public static void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImageSize imageSize){
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        intent.putExtra(INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        guideGroup = (LinearLayout) findViewById(R.id.guideGroup);
        getIntentData();
        ImageAdapter mAdapter = new ImageAdapter(this);
        mAdapter.setDatas(imgUrls);
        mAdapter.setImageSize(imageSize);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<guideViewList.size(); i++){
                    guideViewList.get(i).setSelected(i==position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(startPos);
        addGuideView(guideGroup, startPos, imgUrls);

    }

    @Override
    public void doLogic() {
        showBackButton();
       // setTitle("图片查看");

    }

    private void getIntentData() {
        startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);
        imageSize = (ImageSize) getIntent().getSerializableExtra(INTENT_IMAGESIZE);
    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if(imgUrls!=null && imgUrls.size()>0){
            guideViewList.clear();
            for (int i=0; i<imgUrls.size(); i++){
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i==startPos ? true : false);
                LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams
                        (ScreenUtil.dip2px(this,5),
                        ScreenUtil.dip2px(this,5));
                layoutParams.setMargins(ScreenUtil.dip2px(this,4), 0,ScreenUtil.dip2px(this,4), ScreenUtil.dip2px(this,5));
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try{
            return super.dispatchTouchEvent(ev);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private  class ImageAdapter extends PagerAdapter {
        private List<String> datas = new ArrayList<String>();
        private LayoutInflater inflater;
        private Context context;
        private ImageSize imageSize;
        private ImageView smallImageView = null;

        public void setDatas(List<String> datas) {
            if(datas != null )
                this.datas = datas;
        }
        public void setImageSize(ImageSize imageSize){
            this.imageSize = imageSize;
        }

        public ImageAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            if(datas == null) return 0;
            return datas.size();
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if(view != null){
                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);
                imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                               finish();
                    }

                    @Override
                    public void onOutsidePhotoTap() {
                              finish();
                    }
                });
                if(imageSize!=null){
                    //预览imageView
                    smallImageView = new ImageView(context);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageSize.getWidth(), imageSize.getHeight());
                    layoutParams.gravity = Gravity.CENTER;
                    smallImageView.setLayoutParams(layoutParams);
                    smallImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ((FrameLayout)view).addView(smallImageView);
                }

                //loading
                final ProgressBar loading = new ProgressBar(context);
                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingLayoutParams.gravity = Gravity.CENTER;
                loading.setLayoutParams(loadingLayoutParams);
                ((FrameLayout)view).addView(loading);

                final String imgurl = datas.get(position);
                RequestBuilder<Drawable> builder= Glide.with(ImagePagerActivity.this).load(imgurl);
                builder.thumbnail(0.1f);
                builder.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL));
                builder.listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.setVisibility(View.GONE);
                        return false;
                    }
                });
                loading.setVisibility(View.VISIBLE);
                builder.into(imageView);

 //               loadImageByRequestBuilder(builder,target);
//                Glide.with(context)
//                        .load(imgurl)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
//                        .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
//                        .error(R.drawable.ic_error_image).placeholder(R.drawable.ic_place_holder)
//                        .into(new GlideDrawableImageViewTarget(imageView){
//                            @Override
//                            public void onLoadStarted(Drawable placeholder) {
//                                super.onLoadStarted(placeholder);
//                               /* if(smallImageView!=null){
//                                    smallImageView.setVisibility(View.VISIBLE);
//                                    Glide.with(context).load(imgurl).into(smallImageView);
//                                }*/
//                                loading.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                                super.onLoadFailed(e, errorDrawable);
//                                /*if(smallImageView!=null){
//                                    smallImageView.setVisibility(View.GONE);
//                                }*/
//                                loading.setVisibility(View.GONE);
//                            }
//
//                            @Override
//                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
//                                super.onResourceReady(resource, animation);
//                                loading.setVisibility(View.GONE);
//                                /*if(smallImageView!=null){
//                                    smallImageView.setVisibility(View.GONE);
//                                }*/
//                            }
//                        });

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }

    @Override
    public void onDestroy() {
        guideViewList.clear();
        super.onDestroy();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_imagepager;
    }

    public static class ImageSize implements Serializable {
        private int width;
        private int height;
        public ImageSize(int width, int height){
            this.width = width;
            this.height = height;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }
    }
}
