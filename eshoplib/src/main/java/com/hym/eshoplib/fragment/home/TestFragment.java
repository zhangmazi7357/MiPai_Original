package com.hym.eshoplib.fragment.home;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hym.eshoplib.R;

import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2019/8/27.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class TestFragment extends BaseKitFragment{

    public static TestFragment newInstance(Bundle args) {
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getContentViewResId() {
        return R.layout.item_add_image;
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
