package com.hym.eshoplib.fragment.search.mz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.hym.eshoplib.databinding.MzActivitySearchResultBinding;
import com.hym.eshoplib.mz.MzConstant;

import cn.hym.superlib.mz.MzBaseActivity;

/**
 * mz 搜索结果;
 */
public class MzSearchResultActivity extends MzBaseActivity {


    MzActivitySearchResultBinding binding;
    private String keyWord = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MzActivitySearchResultBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        keyWord = intent.getStringExtra(MzConstant.KEY_SEARCH_KEYWORD);
    }


    @Override
    protected void onStart() {
        super.onStart();
//        binding.mzBar.et_search.setText();
    }
}