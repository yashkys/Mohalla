package com.kys.mohalla.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.R;

import java.util.ArrayList;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.viewHolder>{
    ArrayList<ItemModel> list;
    Context context;

    public ItemAdapter(ArrayList<ItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_item,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ItemModel model = list.get(position);

        holder.ItemImage.setImageResource(model.getItemImage());
        holder.ItemName.setText(model.getItemName());
        holder.ItemPrice.setText(model.getItemPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ReadItemActivity.class);
//                intent.putExtra("url",model.getUrl());
//                context.startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.parse(model.getUrl()), "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Intent newIntent = Intent.createChooser(intent, "Open File");
                try {
                    context.startActivity(newIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "Error in opening file Download a pdf Viewer in ur device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView ItemImage;
        TextView ItemPrice, ItemName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ItemImage = itemView.findViewById(R.id.itemimg);
            ItemName = itemView.findViewById(R.id.itemName);
            ItemPrice = itemView.findViewById(R.id.itemPrice);

        }
    }
}

