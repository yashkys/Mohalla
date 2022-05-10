package com.kys.mohalla.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kys.mohalla.Adapter.ItemAdapter;
import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.databinding.SnacksFragmentBinding;

import java.util.ArrayList;

public class SnacksFragment extends Fragment {

    private SnacksFragmentBinding binding;
    RecyclerView recyclerView;

    public SnacksFragment() {    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SnacksFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        ArrayList<ItemModel> list = new ArrayList<>();
        ItemAdapter adapter = new ItemAdapter(list, getContext());
        recyclerView = binding.snacksRecyclerView;
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseDatabase.getInstance().getReference().child("SnackItems").addValueEventListener(new ValueEventListener() {
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