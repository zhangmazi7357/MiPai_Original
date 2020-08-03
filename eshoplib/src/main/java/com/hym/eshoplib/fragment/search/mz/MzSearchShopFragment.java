package com.hym.eshoplib.fragment.search.mz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.eshoplib.R;
import com.hym.eshoplib.databinding.FragmentMzSearchShopBinding;

/**
 * mz 搜索结果 工作室 Fragment ;
 */
public class MzSearchShopFragment extends Fragment {

    private FragmentMzSearchShopBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMzSearchShopBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}