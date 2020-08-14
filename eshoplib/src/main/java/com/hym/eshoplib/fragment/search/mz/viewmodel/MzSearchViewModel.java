package com.hym.eshoplib.fragment.search.mz.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hym.eshoplib.fragment.search.mz.model.MzSearchAllModel;
import com.hym.eshoplib.http.mz.MzNewApi;

import cn.hym.superlib.mz.MzAbsViewModel;

public class MzSearchViewModel extends MzAbsViewModel {

    private String TAG = "MzSearchViewModel";


    // 搜索内容
    private MutableLiveData<String> content = new MutableLiveData<>();

    // 类型  1:全部 , 2:工作室
    private MutableLiveData<Integer> type = new MutableLiveData<>();

    // 智能排序  1:时间  2:价格  3:好评优先   4:星级
    private MutableLiveData<Integer> sortType = new MutableLiveData<>();

    // 页码
    private MutableLiveData<Integer> p = new MutableLiveData<>();

    // 城市
    private MutableLiveData<String> city;


    ///////////////////////  搜索内容  /////////////////
    public MutableLiveData<String> getContent() {
        return content;
    }

    public void setContent(String mContent) {
        content.setValue(mContent);
    }


    ////////////////////////  页码 ////////////////////////////
    public MutableLiveData<Integer> getP() {
        if (p.getValue() == null) {
            p.setValue(1);
        }
        return p;
    }

    public void setP(Integer mP) {
        p.setValue(mP);
    }

    /////////////////    类型   默认 全部 ：1 /////////////////////
    public MutableLiveData<Integer> getType() {

        if (type.getValue() == null) {
            type.setValue(1);
        }
        return type;
    }

    public void setType(Integer mType) {
        type.setValue(mType);
    }


    ///////////////      智能排序  /////////////
    public LiveData<Integer> getSearchSortType() {
        return sortType;
    }

    public void setSearchSortType(Integer mSortType) {
        sortType.setValue(mSortType);
    }


    public LiveData<MzSearchAllModel> search(int page) {


        MutableLiveData<MzSearchAllModel> liveData = new MutableLiveData<>();

//        Log.e(TAG, "当前搜索参数 ： 内容 =" + content.getValue() +
//                ",类型 =" + type.getValue() +
//                ",智能 = " + sortType.getValue());


        MzNewApi.search(content.getValue(),
                String.valueOf(type.getValue()),
                String.valueOf(sortType.getValue()),
                String.valueOf(page),

                new ResponseImpl<MzSearchAllModel>() {
                    @Override
                    public void onSuccess(MzSearchAllModel data) {

//                        Log.e(TAG, "onSuccess: " + data.toString());
                        liveData.setValue(data);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        liveData.setValue(null);
                    }
                }, MzSearchAllModel.class);

        return liveData;


    }


}