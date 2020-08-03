package com.hym.eshoplib.fragment.search.mz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.eshoplib.R;
import com.hym.eshoplib.databinding.FragmentMzSearchAllBinding;

/**
 * mz - 搜索全部结果 Fragment
 */
public class MzSearchAllFragment extends Fragment {


    FragmentMzSearchAllBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMzSearchAllBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}