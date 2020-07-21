package com.hym.eshoplib.fragment.shop.mzupload;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductSortBean;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.MzHeaderBar;
import com.hym.httplib.interfaces.IHttpResultListener;

import java.util.Arrays;
import java.util.List;

import app.App;
import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * 产品分类
 */
public class MzProductSortActivity extends MzBaseActivity {


    private String TAG = "MzProductSort = ";
    private MzHeaderBar headerBar;
    private RecyclerView sortRecyclerView;

    private MzProductSortAdapter adapter;
    private ProductSortBean.DataBean selectItem;

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


        MzNewApi.getOneType(new ResponseImpl<ProductSortBean>() {
            @Override
            public void onSuccess(ProductSortBean data) {
                Log.e(TAG, "请求分类 = " + JSONObject.toJSONString(data));
                List<ProductSortBean.DataBean> list = data.getData();
                adapter.setDatas(list);
            }
        }, ProductSortBean.class);


        initRecyclerView();

    }


    private void initRecyclerView() {
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MzProductSortAdapter();

        sortRecyclerView.setAdapter(adapter);


        adapter.setClickListener(new MzProductSortAdapter.MzClickListener() {
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
        intent.putExtra(MzConstant.VALUE_PRODUCT_SORT, selectItem);
        setResult(MzConstant.RESULT_CODE_UPLOAD_IMG, intent);

        // 返回到  UploadImageFragment ;
        finish();
    }


}