package com.kys.mohalla.ui;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kys.mohalla.Adapter.ItemAdapter;
import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.databinding.SnacksFragmentBinding;
import com.kys.mohalla.databinding.StationeryFragmentBinding;

import java.util.ArrayList;

public class StationeryFragment extends Fragment {

    private StationeryFragmentBinding binding;
    RecyclerView recyclerView;

    public StationeryFragment() {    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StationeryFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        ArrayList<ItemModel> list = new ArrayList<>();
        ItemAdapter adapter = new ItemAdapter(list, getContext());
        recyclerView = binding.stationeryRecyclerView;
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseDatabase.getInstance().getReference().child("StationeryItems").addValueEventListener(new ValueEventListener() {
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