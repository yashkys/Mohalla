package com.kys.mohalla.ui.cart;

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
import com.kys.mohalla.databinding.CartFragmentBinding;
import com.kys.mohalla.databinding.GroceryFragmentBinding;
import com.kys.mohalla.ui.grocery.GroceryViewModel;

public class CartFragment extends Fragment {

    private CartFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        GroceryViewModel groceryViewModel =
                new ViewModelProvider(this).get(GroceryViewModel.class);

        binding = CartFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCart;
        //groceryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}