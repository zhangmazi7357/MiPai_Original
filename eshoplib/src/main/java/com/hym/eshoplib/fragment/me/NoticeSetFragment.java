package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hym.eshoplib.R;

import cn.hym.superlib.fragment.base.BaseKitFragment;

/**
 * Created by 胡彦明 on 2018/8/5.
 * <p>
 * Description 通知设置Fragment
 * <p>
 * otherTips
 */

public class NoticeSetFragment extends BaseKitFragment{
    public static NoticeSetFragment newInstance(Bundle args) {
        NoticeSetFragment fragment = new NoticeSetFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getContentViewResId() {
        return R.layout.fragment_notice_setting;
    }

    @Override
    public void doLogic() {
        showBackButton();
        setTitle("通知");

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
