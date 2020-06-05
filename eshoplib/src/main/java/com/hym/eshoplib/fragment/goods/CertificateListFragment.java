package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.imagelib.ImageUtil;

import java.util.ArrayList;

import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/8/4.
 * <p>
 * Description 查看证书
 * <p>
 * otherTips
 */

public class CertificateListFragment extends BaseListFragment<String>{
    public static CertificateListFragment newInstance(Bundle args) {
        CertificateListFragment fragment = new CertificateListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_image_gridlist;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(_mActivity,3);
    }

    @Override
    public void excuteLogic() {
        showBackButton();
        String title=getArguments().getString("title","证书详情");
        setTitle(title);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ImagePagerActivity
//                ArrayList<String> images_str=new ArrayList<String>();
//                images_str.add(VideoConstant.videoThumbs[0][0]);
//                images_str.add(VideoConstant.videoThumbs[0][1]);
//                images_str.add(VideoConstant.videoThumbs[0][2]);


                int width = ScreenUtil.getScreenWidth(_mActivity);
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(width, width / 2);
                ImagePagerActivity.startImagePagerActivity(_mActivity, getArguments().getStringArrayList("data"), position, imageSize);
            }
        });
        getRefreshLayout().setEnabled(false);
        getRv_list().setPadding(0,ScreenUtil.dip2px(_mActivity,15),0,0);

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        ArrayList<String>arr=getArguments().getStringArrayList("data");
        getAdapter().setNewData(arr);
        getRefreshLayout().setRefreshing(false);
        dissMissDialog();

    }

    @Override
    public void bindData(BaseViewHolder helper, String item, int position) {
        ImageView iv=helper.getView(R.id.iv_image);
        LinearLayout.LayoutParams layoutParam= (LinearLayout.LayoutParams) iv.getLayoutParams();
        int screenWidth=ScreenUtil.getScreenWidth(_mActivity);
        float width=(screenWidth-ScreenUtil.dip2px(_mActivity,40))/3f;
       // Logger.d("swidth="+screenWidth);
        layoutParam.height= (int) width;
        layoutParam.width= (int) width;
        if(position%3==0){
            //左侧
            layoutParam.setMargins(ScreenUtil.dip2px(_mActivity,8),0,0,ScreenUtil.dip2px(_mActivity,15));
        }else if(position%3==1){
            //中间
            layoutParam.setMargins(ScreenUtil.dip2px(_mActivity,8),0,0,ScreenUtil.dip2px(_mActivity,15));
        }else {
            layoutParam.setMargins(ScreenUtil.dip2px(_mActivity,8),0,0,ScreenUtil.dip2px(_mActivity,15));
        }
        ImageUtil.getInstance().loadImage(CertificateListFragment.this,item,iv);

    }
}
