package com.hym.eshoplib.fragment.search.mz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import com.google.android.material.tabs.TabLayout;
import com.hym.eshoplib.R;
import com.hym.eshoplib.databinding.MzActivitySearchResultBinding;
import com.hym.eshoplib.mz.MzConstant;

import java.util.ArrayList;
import java.util.List;

import cn.hym.superlib.mz.MzBaseActivity;

/**
 * mz 搜索结果;
 */
public class MzSearchResultActivity extends MzBaseActivity {


    private MzActivitySearchResultBinding binding;
    private String keyWord = "";


    private View containerView;   // fragment的容器;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MzActivitySearchResultBinding
                .inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        keyWord = intent.getStringExtra(MzConstant.KEY_SEARCH_KEYWORD);

        initTab();
        intDropDownMenu();
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

        containerView = LayoutInflater.from(this).inflate(R.layout.mz_result_container_view, null, false);
        transaction.add(R.id.mz_container, allFragment).show(allFragment);
        transaction.add(R.id.mz_container, shopFragment).hide(shopFragment);


        transaction.commit();

        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("全部"));
        binding.mzTabLayout.addTab(binding.mzTabLayout.newTab().setText("工作室"));

        binding.mzTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                binding.dropDownMenu.closeMenu();
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


    private void intDropDownMenu() {
        List<String> tabs = new ArrayList<>();
        tabs.add("年龄");
        tabs.add("性别");
        tabs.add("身高");

        List<View> pops = new ArrayList<>();
        ImageView img = new ImageView(this);
        pops.add(img);
        TextView t = new TextView(this);
        t.setText("秀儿");
        pops.add(t);

        TextView t2 = new TextView(this);
        t2.setText("云龙");
        pops.add(t2);


        binding.dropDownMenu.setDropDownMenu(tabs, pops, containerView);


    }
}