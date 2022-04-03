package com.kys.mohalla.ui.stationery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kys.mohalla.R;
import com.kys.mohalla.databinding.GroceryFragmentBinding;
import com.kys.mohalla.databinding.StationeryFragmentBinding;
import com.kys.mohalla.ui.grocery.GroceryViewModel;

public class StationeryFragment extends Fragment {

    private StationeryFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StationeryViewModel stationeryViewModel =
                new ViewModelProvider(this).get(StationeryViewModel.class);

        binding = StationeryFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStationery;
        stationeryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}