package com.hym.eshoplib.fragment.shop.mzupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
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
import com.hym.eshoplib.mz.MzConstant;
import com.hym.eshoplib.mz.MzHeaderBar;
import com.hym.httplib.interfaces.IHttpResultListener;

import java.util.Arrays;
import java.util.List;

import app.App;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * 产品分类
 */
public class MzProductSortActivity extends AppCompatActivity {


    private String TAG = "MzProductSort = ";
    private MzHeaderBar headerBar;
    private RecyclerView sortRecyclerView;

    private MzProductSortAdapter adapter;
    private String selectItem;

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

        net(new IHttpResultListener<Object>() {
            @Override
            public void onStart(int mark) {
                Log.e(TAG, "onStart: " + mark);
            }

            @Override
            public void onSuccess(Object data) {
                Log.e(TAG, "onSuccess: " + JSONObject.toJSONString(data));
            }

            @Override
            public void onDataError(String status, String errormessage) {
                Log.e(TAG, "onDataError: " + status);
            }

            @Override
            public void onEmptyData() {
                Log.e(TAG, "onEmptyData: ");
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "onFailed: " + e.getMessage());
            }

            @Override
            public void onFinish(int mark) {
                Log.e(TAG, "onFinish: " + mark);
            }

            @Override
            public void dataRes(int mark, String data) {
                Log.e(TAG, "dataRes: mark = " + mark + ",data = " + data);
            }
        }, Object.class);


        initRecyclerView();

    }


    private void initRecyclerView() {
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MzProductSortAdapter();

        sortRecyclerView.setAdapter(adapter);
        String[] productArray = getResources().getStringArray(R.array.productSort);
        List<String> productList = Arrays.asList(productArray);
        adapter.setDatas(productList);

        adapter.setClickListener(new MzProductSortAdapter.MzClickListener() {
            @Override
            public void onClick(int position) {
                selectItem = adapter.getItemPosition(position);
            }
        });


    }


    private <T> void net(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = new HttpUtil.BaseHttpRequest();
        request.setUrl("192.168.1.28/api");
        request.setApp("Store");
        request.setClassName("GetoneType");
        request.AddParems("token", UserUtil.getToken(App.instance));
        String channelid = SharePreferenceUtil.getStringData(this, "channelid");
        Log.e(TAG, "token  = " + UserUtil.getToken(App.instance));
        Log.e(TAG, " channelid = " + channelid);
        request.addParamsNotEmpty("channelid", channelid);
        post(request, listener, clazz);
    }

    private void choiceSort() {


        if (TextUtils.isEmpty(selectItem)) {
            Toast.makeText(this, "请选择一个类型", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(MzConstant.VALUE_PRODUCT_SORT, selectItem);
        setResult(MzConstant.RESULT_CODE_UPLOAD_IMG, intent);

        // 返回到  UploadImageFragment ;
        finish();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }


}