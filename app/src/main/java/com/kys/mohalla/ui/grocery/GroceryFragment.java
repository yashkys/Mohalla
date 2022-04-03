package com.kys.mohalla.ui.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kys.mohalla.Adapter.ItemAdapter;
import com.kys.mohalla.MainActivity;
import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.databinding.GroceryFragmentBinding;

import java.util.ArrayList;

public class GroceryFragment extends Fragment {

    private GroceryFragmentBinding binding;

    ArrayList<ItemModel> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GroceryViewModel groceryViewModel =
                new ViewModelProvider(this).get(GroceryViewModel.class);

        binding = GroceryFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = new ArrayList<>();
        ItemAdapter adapter = new ItemAdapter(list,getContext());
        binding.groceryRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.groceryRecyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase.getInstance().getReference().child("GroceryItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    ItemModel model = snapshot1.getValue(ItemModel.class);
//                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
       });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}