package com.hym.eshoplib.fragment.shop.mzupload.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductOneTypeBean;
import com.hym.eshoplib.fragment.shop.mzupload.adapter.MzProductOneTypeAdapter;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;

import cn.hym.superlib.mz.widgets.MzHeaderBar;

import java.util.List;

import cn.hym.superlib.mz.MzBaseActivity;

/**
 * 产品分类
 */
public class MzProductSortActivity extends MzBaseActivity {


    private String TAG = "MzProductSort = ";
    private MzHeaderBar headerBar;
    private RecyclerView sortRecyclerView;

    private MzProductOneTypeAdapter adapter;
    private ProductOneTypeBean.DataBean selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_product_sort);

        headerBar = findViewById(R.id.headerBar);
        sortRecyclerView = findViewById(R.id.sortRecyclerView);


        headerBar.setHeaderBarListener(new MzHeaderBar.HeaderBarListener() {
            @Override
            public void back() {
                finish();
            }

            @Override
            public void rightButton() {

                choiceSort();
            }
        });


        MzNewApi.getOneType(new ResponseImpl<ProductOneTypeBean>() {
            @Override
            public void onSuccess(ProductOneTypeBean data) {
                List<ProductOneTypeBean.DataBean> list = data.getData();
                adapter.setDatas(list);
            }
        }, ProductOneTypeBean.class);


        initRecyclerView();

    }


    private void initRecyclerView() {
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MzProductOneTypeAdapter();

        sortRecyclerView.setAdapter(adapter);


        adapter.setClickListener(new MzProductOneTypeAdapter.MzClickListener() {
            @Override
            public void onClick(int position) {
                selectItem = adapter.getItemPosition(position);
            }
        });


    }


    private void choiceSort() {
        if (selectItem == null) {
            Toast.makeText(this, "请选择一个类型", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        // 传递序列化参数 ;
        intent.putExtra(MzConstant.VALUE_PRODUCT_ONE, selectItem);
        setResult(MzConstant.RESULT_CODE_UPLOAD, intent);

        // 返回到  UploadImageFragment ;
        finish();
    }


}