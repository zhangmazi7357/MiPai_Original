package com.hym.eshoplib.fragment.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.hym.eshoplib.R;
import com.hym.eshoplib.databinding.ActivityAllShopComemntBinding;
import com.hym.eshoplib.mz.MzConstant;

import cn.hym.superlib.mz.MzBaseActivity;

/**
 * 商品全部评论 ;
 */
public class AllShopCommentActivity extends MzBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String caseId = "";

    private ActivityAllShopComemntBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllShopComemntBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        caseId = getIntent().getStringExtra(MzConstant.KEY_DETAIL_COMMENT_CASE_ID);
    }


    @Override
    public void onRefresh() {

    }

    private void initRecyclerView() {
        binding.swipe.setOnRefreshListener(this);

    }
}