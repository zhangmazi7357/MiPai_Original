package com.hym.eshoplib.fragment.search.mz;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hym.eshoplib.http.mz.MzNewApi;

import cn.hym.superlib.mz.MzAbsViewModel;

public class MzSearchViewModel extends MzAbsViewModel {

    private String TAG = "MzSearchViewModel";

    private Integer searchType;    // 搜索类型
    private Integer searchP;       // 搜索页数


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


    public MutableLiveData<String> getContent() {
        return content;
    }

    public void setContent(String mContent) {
        content.setValue(mContent);
    }

    public MutableLiveData<Integer> getType() {
        return type;
    }

    public void setType(Integer mType) {
        type.setValue(mType);
    }


    public LiveData<MzSearchResultModel> search() {

        String searchStr = content.getValue();

        // 类型
        if (type.getValue() == null) {
            searchType = 1;
        } else {
            searchType = type.getValue();
        }

        // 智能搜索 ;
        Integer searchSortType = sortType.getValue();

        // 页码 ;
        if (p.getValue() == null) {
            searchP = 1;
        } else {
            searchP = p.getValue();
        }


//        Log.e(TAG, "搜索内容 = " + searchStr);
//        Log.e(TAG, "搜索类型 = " + searchType);
//        Log.e(TAG, "搜索智能排序 =" + searchSortType);

        MutableLiveData<MzSearchResultModel> liveData = new MutableLiveData<>();

        MzNewApi.search(searchStr, String.valueOf(searchType),
                String.valueOf(searchSortType),
                String.valueOf(searchP),
                new ResponseImpl<MzSearchResultModel>() {
                    @Override
                    public void onSuccess(MzSearchResultModel data) {

                        Log.e(TAG, "onSuccess: " + data.toString());
                        liveData.setValue(data);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        super.onFailed(e);
                        Log.e(TAG, "onFailed: " + e.getMessage());
                        liveData.setValue(null);
                    }
                }, MzSearchResultModel.class);

        return liveData;


    }


}
