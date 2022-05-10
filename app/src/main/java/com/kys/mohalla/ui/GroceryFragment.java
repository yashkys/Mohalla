package com.kys.mohalla.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kys.mohalla.Adapter.ItemAdapter;
import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.databinding.GroceryFragmentBinding;

import java.util.ArrayList;

public class GroceryFragment extends Fragment {

    GroceryFragmentBinding binding;
    RecyclerView recyclerView;

    public GroceryFragment() {    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = GroceryFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        ArrayList<ItemModel> list = new ArrayList<>();
        ItemAdapter adapter = new ItemAdapter(list, getContext());
        recyclerView = binding.groceryRecyclerView;
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseDatabase.getInstance().getReference().child("GroceryItems").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ItemModel model = snapshot1.getValue(ItemModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
