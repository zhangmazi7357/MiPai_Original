package com.hym.eshoplib.fragment.shop.mzupload.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hym.eshoplib.R;
import com.hym.eshoplib.bean.mz.upload.ProductTagBean;
import com.hym.eshoplib.fragment.shop.mzupload.adapter.MzProductTagAdapter;
import com.hym.eshoplib.mz.MzConstant;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.mz.widgets.MzHeaderBar;

/**
 * 产品标签
 */
public class MzProductTagActivity extends AppCompatActivity {

    private MzHeaderBar headerBar;
    private RecyclerView tagRecyclerView;
    private MzProductTagAdapter adapter;

    private ArrayList<String> tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mz_product_tag);


        headerBar = findViewById(R.id.headerBar);
        initTag();

        headerBar.setHeaderBarListener(new MzHeaderBar.HeaderBarListener() {
            @Override
            public void back() {
                finish();
            }

            @Override
            public void rightButton() {
                choiceTag();
            }
        });


    }

    private void choiceTag() {
        if (tagList.size() <= 0) {
            Toast.makeText(this, "至少选择一个标签", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putStringArrayListExtra(MzConstant.VALUE_PRODUCT_TAG, tagList);
        setResult(MzConstant.RESULT_CODE_UPLOAD, intent);
        finish();
    }


    private void initTag() {

        tagList = new ArrayList<>();

        String[] tagArray = getResources().getStringArray(R.array.productTag);
        List<ProductTagBean> tagLists = new ArrayList<>();
        for (int i = 0; i < tagArray.length; i++) {
            tagLists.add(new ProductTagBean(tagArray[i]));
        }

        tagRecyclerView = findViewById(R.id.tagRecyclerView);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MzProductTagAdapter();
        adapter.setDatas(tagLists);

        tagRecyclerView.setAdapter(adapter);

        adapter.setTagClickListener(new MzProductTagAdapter.TagClickListener() {
            @Override
            public void tagClick(int position) {

                ProductTagBean item = adapter.getItemPosition(position);

                if (item.isChecked()) {
                    tagList.add(item.getTag());
                } else {
                    if (tagList.size() > 0)
                        tagList.remove(item);
                }
            }
        });

    }

}