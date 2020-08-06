package com.hym.eshoplib.fragment.search.mz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import com.google.android.material.tabs.TabLayout;
import com.hym.eshoplib.R;
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

        binding = MzActivitySearchResultBinding
                .inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        keyWord = intent.getStringExtra(MzConstant.KEY_SEARCH_KEYWORD);

        initTab();
    }


    @Override
    protected void onStart() {
        super.onStart();

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });


    }


    private void initTab() {


        MzSearchAllFragment allFragment = new MzSearchAllFragment();
        MzSearchShopFragment shopFragment = new MzSearchShopFragment();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, allFragment).show(allFragment);
        transaction.add(R.id.container, shopFragment).hide(shopFragment);

        transaction.commit();

        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("全部"));
        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("工作室"));

        binding.mzTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (tab.getPosition()) {
                    case 0:
                        fragmentTransaction.show(allFragment).hide(shopFragment).commit();
                        break;
                    case 1:
                        fragmentTransaction.show(shopFragment).hide(allFragment).commit();

                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}