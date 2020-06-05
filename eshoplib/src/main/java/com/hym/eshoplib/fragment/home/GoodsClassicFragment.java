package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.eshoplib.R;
import com.hym.eshoplib.activity.EshopActionActivity;
import com.hym.eshoplib.bean.home.ClassicParentListBean;
import com.hym.eshoplib.bean.home.ClassicSecondListBean;
import com.hym.eshoplib.bean.home.GoodsSectionBean;
import com.hym.eshoplib.http.home.EshopHomeApi;
import com.hym.imagelib.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.adapter.BaseListAdapter;
import cn.hym.superlib.adapter.BaseSectionAdapter;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.view.ScreenUtil;

;

/**
 * Created by 胡彦明 on 2018/3/11.
 * <p>
 * Description 商品分类
 * <p>
 * otherTips
 */

public class GoodsClassicFragment extends BaseKitFragment {
    public static GoodsClassicFragment newInstance(Bundle args) {
        GoodsClassicFragment fragment = new GoodsClassicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView rvListLeft;
    RecyclerView rvListRight;
    LinearLayout llRvlist;
    SuperButton btnClear;
    SuperButton btnConfirm;
    LinearLayout llButtons;
    Unbinder unbinder;
    int left_position=0;
    TextView tv_search;
    ImageView ivBack;
    private List<ClassicParentListBean.DataBean> categoryListBeen;//1级分类
    private BaseListAdapter<ClassicParentListBean.DataBean> adapter_left;//一级分类
    private BaseSectionAdapter<GoodsSectionBean> adapter_right;//二级三级分类
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_goods_classic;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_toolbar_search;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        rvListLeft= (RecyclerView) view.findViewById(R.id.rv_list_left);
        rvListRight=(RecyclerView) view.findViewById(R.id.rv_list_right);
        llRvlist= (LinearLayout) view.findViewById(R.id.ll_rvlist);
        btnClear= (SuperButton) view.findViewById(R.id.btn_clear);
        btnConfirm= (SuperButton) view.findViewById(R.id.btn_confirm);
        llButtons= (LinearLayout) view.findViewById(R.id.ll_buttons);
        tv_search= (TextView) view.findViewById(R.id.tv_search);
        ivBack= (ImageView) view.findViewById(R.id.iv_back);
    }

    @Override
    public void doLogic() {
        setShowProgressDialog(true);
        showBackButton();
        rvListLeft.setLayoutManager(new LinearLayoutManager(_mActivity));
        rvListRight.setLayoutManager(new LinearLayoutManager(_mActivity));
        //适配rv的宽度
        rvListLeft.getLayoutParams().width=(ScreenUtil.getScreenWidth(_mActivity)-ScreenUtil.dip2px(_mActivity,10))/4;
        llRvlist.setPadding(0,0,0,0);
        tv_search.setVisibility(View.VISIBLE);
        tv_search.setHint(R.string.EnterProductName);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= BaseActionActivity.getActionBundle
                        (EshopActionActivity.ModelType_Eshop,EshopActionActivity.Action_eshop_search);
                EshopActionActivity.
                        start(_mActivity,bundle);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getList();


    }
    //获取筛选列表
    public void getList() {
        EshopHomeApi.getGoodsFilterList(_mActivity, "", "", new ResponseImpl<ClassicParentListBean>() {
            @Override
            public void onSuccess(ClassicParentListBean data) {
                categoryListBeen=data.getData();
                setData(true);
                String id=data.getData().get(0).getCategory_id();
                setData(false);
                getRightData(id);

            }
        },ClassicParentListBean.class);


    }
    public void getRightData(String id){
        EshopHomeApi.getGoodsFilterList(_mActivity, "", id, new ResponseImpl<ClassicSecondListBean>() {
            @Override
            public void onSuccess(ClassicSecondListBean data) {
                  ArrayList<GoodsSectionBean>sectionBeen=new ArrayList<GoodsSectionBean>();
                  List<ClassicSecondListBean.DataBean>parent=data.getData();
                  for(ClassicSecondListBean.DataBean parentBean:parent){
                      sectionBeen.add(new GoodsSectionBean(true,parentBean.getCategory_name()+""));
                      for(ClassicSecondListBean.DataBean.SonBean sonBean:parentBean.getSon()){
                          sectionBeen.add(new GoodsSectionBean(sonBean));
                      }
                  }
                  adapter_right.setNewData(sectionBeen);


            }
        },ClassicSecondListBean.class);
    }
    public void setData(boolean left){
        if(left){
            adapter_left=new BaseListAdapter<ClassicParentListBean.DataBean>(R.layout.item_classic_left,categoryListBeen) {
                @Override
                public void handleView(BaseViewHolder helper,ClassicParentListBean.DataBean item, int position) {
                    helper.setText(R.id.tv_classic,item.getCategory_name()+"");
                    if(position==left_position){
                        helper.setTextColor(R.id.tv_classic, ContextCompat.getColor(_mActivity,R.color.jiuzhou_orange));
                    }else {
                        helper.setTextColor(R.id.tv_classic, ContextCompat.getColor(_mActivity,R.color.black));
                    }

                }
            };
            adapter_left.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    //根据一级分类改变 商品显示
                    left_position=position;
                    String id=adapter_left.getData().get(position).getCategory_id();
                    getRightData(id);
                    adapter.notifyDataSetChanged();
                }
            });
            rvListLeft.setAdapter(adapter_left);
        }else {
            //设置商品adapter
            adapter_right=new BaseSectionAdapter<GoodsSectionBean>(R.layout.item_goods,R.layout.header_section_goods_title,null) {
                @Override
                public void handleHead(BaseViewHolder helper, GoodsSectionBean item, int position) {
                    helper.setText(R.id.tv_title,item.header);

                }

                @Override
                public void handleView(BaseViewHolder helper, GoodsSectionBean item, int position) {
                    ImageView imageView=helper.getView(R.id.iv_icon);
                    String url=item.t.getImage_default();
                    ImageUtil.getInstance().loadImage(GoodsClassicFragment.this,url,imageView);
                    helper.setText(R.id.tv_name,item.t.getCategory_name());
                    String category_id=item.t.getCategory_id();


                }
            };
            adapter_right.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if(!adapter_right.getData().get(position).isHeader){
                        String category_id= adapter_right.getData().get(position).t.getCategory_id();
                        String name=adapter_right.getData().get(position).t.getCategory_name();
                        //ToastUtil.toast("点击id="+category_id+"==name="+name);
                        Bundle bundle=new Bundle();
                        bundle.putString("id",category_id);
                        bundle.putString("name",name);
                        start(GoodsOrderByFragment.newInstance(bundle));

                    }
                }
            });
            rvListRight.setAdapter(adapter_right);
        }

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

}
