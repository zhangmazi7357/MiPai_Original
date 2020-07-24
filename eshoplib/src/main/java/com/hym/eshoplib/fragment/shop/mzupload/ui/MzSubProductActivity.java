package com.hym.eshoplib.fragment.shop.mzupload.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductTwoTypeBean;
import com.hym.eshoplib.fragment.shop.mzupload.adapter.MzProductTwoTypeAdapter;
import com.hym.eshoplib.http.mz.MzNewApi;
import com.hym.eshoplib.mz.MzConstant;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.mz.MzBaseActivity;
import cn.hym.superlib.mz.widgets.MzHeaderBar;

/**
 * 二级产品分类;
 */
public class MzSubProductActivity extends MzBaseActivity {

    private String TAG = "TwoType";
    private RecyclerView subRecyclerView;
    private MzHeaderBar headerBar;
    private MzProductTwoTypeAdapter adapter;
    private String oneTypeId;
    private List<ProductTwoTypeBean.DataBean> twoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_sub_product);

        getBundle();

        getTwoType();
        initRecyclerView();
    }


    private void getBundle() {
        oneTypeId = getIntent().getStringExtra(MzConstant.PRODUCT_SORT_ID);
        if (TextUtils.isEmpty(oneTypeId)) {
            Toast.makeText(this, "请先选择产品分类", Toast.LENGTH_SHORT).show();
            finish();
        }

        headerBar = findViewById(R.id.headerBar);
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


    }


    private void initRecyclerView() {

        //
        twoList = new ArrayList<>();

        subRecyclerView = findViewById(R.id.subRecyclerView);
        subRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MzProductTwoTypeAdapter();

        subRecyclerView.setAdapter(adapter);

        adapter.setTwoTypeClickListener(new MzProductTwoTypeAdapter.TwoTypeClickListener() {
            @Override
            public void twoTypeClick(int position) {
                ProductTwoTypeBean.DataBean item = adapter.getItemPosition(position);

                if (item.isChecked()) {
                    twoList.add(item);
                } else {
                    if (twoList.size() > 0)
                        twoList.remove(item);
                }

            }
        });

    }


    private void getTwoType() {
        MzNewApi.getTwoType(new ResponseImpl<ProductTwoTypeBean>() {
            @Override
            public void onSuccess(ProductTwoTypeBean data) {
                List<ProductTwoTypeBean.DataBean> allList = data.getData();

                List<ProductTwoTypeBean.DataBean> twoTypeList = new ArrayList<>();
                for (int i = 0; i < allList.size(); i++) {
                    ProductTwoTypeBean.DataBean dataBean = allList.get(i);
                    if (oneTypeId.equals(dataBean.getOnetype_id())) {
                        twoTypeList.add(dataBean);
                    }
                }

                adapter.setDatas(twoTypeList);
            }
        }, ProductTwoTypeBean.class);
    }


    // 请选择至少一个类型;
    private void choiceSort() {

        if (twoList.size() <= 0) {
            Toast.makeText(this, "请选择一个类型", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        // 传递序列化参数 ;

        intent.putParcelableArrayListExtra(MzConstant.VALUE_PRODUCT_TWO, (ArrayList<? extends Parcelable>) twoList);
        setResult(MzConstant.RESULT_CODE_UPLOAD, intent);

        // 返回到  UploadImageFragment ;
        finish();
    }

}