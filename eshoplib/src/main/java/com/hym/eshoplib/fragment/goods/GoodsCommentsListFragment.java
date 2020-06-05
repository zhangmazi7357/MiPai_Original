package com.hym.eshoplib.fragment.goods;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.goods.GoodsCommentsBean;
import com.hym.eshoplib.http.goods.GoodsApi;
import com.hym.imagelib.ImageUtil;

import java.util.List;

import cn.hym.superlib.activity.ImagePagerActivity;
import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.utils.http.HttpResultUtil;
import cn.hym.superlib.utils.view.ScreenUtil;

/**
 * Created by 胡彦明 on 2018/4/18.
 * <p>
 * Description 商品评论列表
 * <p>
 * otherTips
 */

public class GoodsCommentsListFragment extends BaseListFragment<GoodsCommentsBean.DataBean.InfoBean>{
    String spid;
    String rank_type;//评论级别-非必须(1：好评，2：中评，3：差评)
    TextView btn_1,btn_2,btn_3,btn_4;
    public static GoodsCommentsListFragment newInstance(Bundle args) {
        GoodsCommentsListFragment fragment = new GoodsCommentsListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getItemRestId() {
        return R.layout.item_shopgoods_comments;
    }

    @Override
    public void excuteLogic() {
        spid=getArguments().getString("id");
        showBackButton();
        setTitle(R.string.Reviews);
        getVs_header().setLayoutResource(R.layout.header_comments);
        View header=getVs_header().inflate();
        btn_1= (TextView) header.findViewById(R.id.btn_1);
        btn_2= (TextView) header.findViewById(R.id.btn_2);
        btn_3=(TextView) header.findViewById(R.id.btn_3);
        btn_4=(TextView) header.findViewById(R.id.btn_4);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(1);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(2);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(3);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(4);
            }
        });

    }

    @Override
    public void getData(final boolean refresh, int pageSize, final int pageNum) {
        GoodsApi.GetCommentList(_mActivity,spid,rank_type,pageSize+"",pageNum+"", new ResponseImpl<GoodsCommentsBean>() {
            @Override
            public void onSuccess(GoodsCommentsBean data) {
                int total=Integer.parseInt(data.getData().getTotalpage());
                if(refresh){
                    setPageNum(HttpResultUtil.onRefreshSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }else {
                    setPageNum(HttpResultUtil.onLoardMoreSuccess(total,pageNum,data.getData().getInfo(),getAdapter()));
                }


            }

            @Override
            public void onEmptyData() {
                super.onEmptyData();
                getAdapter().setNewData(null);
            }
        },GoodsCommentsBean.class);


    }

    @Override
    public void bindData(BaseViewHolder helper, GoodsCommentsBean.DataBean.InfoBean item, int position) {
        ImageView iv_icon=  helper.getView(R.id.iv_icon);
        TextView tv_name= helper.getView(R.id.tv_name);
        TextView tv_time= helper.getView(R.id.tv_time);
        TextView tv_spe= helper.getView(R.id.tv_spec);
        TextView tv_type= helper.getView(R.id.tv_type);
        TextView tv_content= helper.getView(R.id.tv_content);
        GridLayout gridView= helper.getView(R.id.girdlayout);
        ImageUtil.getInstance().loadCircleImage(GoodsCommentsListFragment.this,item.getAvatar(),iv_icon);
        tv_name.setText(item.getNickname()+"");
        tv_time.setText(item.getCtime()+"");
        tv_spe.setText(item.getProperty()+"");
        tv_content.setText(item.getContent()+"");
        //添加图片
        final List<String> images = item.getAttachment();
        gridView.removeAllViews();
        ViewGroup.LayoutParams lp= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < images.size(); i++) {
            String url = images.get(i);
            ImageView iv = new ImageView(_mActivity);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 3, 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 3, 1f);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            layoutParams.setMargins(0, ScreenUtil.dip2px(_mActivity,5),ScreenUtil.dip2px(_mActivity,5),0);
            layoutParams.height =(ScreenUtil.getScreenWidth(_mActivity)/2-ScreenUtil.dip2px(_mActivity,20))/3;
            layoutParams.width = (ScreenUtil.getScreenWidth(_mActivity)/2-ScreenUtil.dip2px(_mActivity,20))/3;
            iv.setLayoutParams(layoutParams);
            ImageUtil.getInstance().loadImage(_mActivity, url, iv);
            final int image_position = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
                    ImagePagerActivity.startImagePagerActivity((_mActivity), images, image_position, imageSize);
                }
            });
            gridView.addView(iv);
        }

    }


    private void updateData(int position){

            switch (position){
            case 1:
                rank_type="";
                btn_1.setBackgroundResource(R.drawable.shape_orange_solid_conner3);
                btn_1.setTextColor(ContextCompat.getColor(_mActivity,R.color.white));

                btn_2.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_2.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_3.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_3.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_4.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_4.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));

                break;
            case 2:
                rank_type="1";
                btn_2.setBackgroundResource(R.drawable.shape_orange_solid_conner3);
                btn_2.setTextColor(ContextCompat.getColor(_mActivity,R.color.white));

                btn_1.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_1.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_3.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_3.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_4.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_4.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                break;
            case 3:
                rank_type="2";
                btn_3.setBackgroundResource(R.drawable.shape_orange_solid_conner3);
                btn_3.setTextColor(ContextCompat.getColor(_mActivity,R.color.white));

                btn_1.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_1.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_2.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_2.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_4.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_4.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));

                break;
            case 4:
                rank_type="3";
                btn_4.setBackgroundResource(R.drawable.shape_orange_solid_conner3);
                btn_4.setTextColor(ContextCompat.getColor(_mActivity,R.color.white));

                btn_1.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_1.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_2.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_2.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                btn_3.setBackgroundResource(R.drawable.shape_orange_stroke_white_solid_conner3);
                btn_3.setTextColor(ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                break;
        }
        onRefresh();

    }
}
