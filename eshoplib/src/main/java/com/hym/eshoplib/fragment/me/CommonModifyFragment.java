package com.hym.eshoplib.fragment.me;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperButton;
import com.hym.eshoplib.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.widgets.view.ClearEditText;

/**
 * Created by 胡彦明 on 2017/7/26.
 * <p>
 * Description 修改xxx
 * <p>
 * otherTips
 */

public class CommonModifyFragment extends BaseKitFragment {
    @BindView(R.id.et_input)
    ClearEditText etInput;
    @BindView(R.id.btn_save)
    SuperButton btnSave;
    Unbinder unbinder;

    public static CommonModifyFragment newInstance(Bundle args) {
        CommonModifyFragment fragment = new CommonModifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_common_modify;
    }

    @Override
    public void doLogic() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title", "");
        setTitle(title);
        showBackButton();
        etInput.requestFocus();
        int reqcode=bundle.getInt("reqcode",-1);
        if(reqcode==3){
            etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        setRight_tv("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result=etInput.getText().toString().trim();
                if(TextUtils.isEmpty(result)){
                    return;
                }
                Bundle returnData=new Bundle();
                returnData.putString("str",result);
                setFragmentResult(RESULT_OK,returnData);
                pop();
            }
        });



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        String result=etInput.getText().toString().trim();
        if(TextUtils.isEmpty(result)){
            return;
        }
        Bundle returnData=new Bundle();
        returnData.putString("str",result);
        setFragmentResult(RESULT_OK,returnData);
        pop();
    }
}
