package com.kys.mohalla.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.kys.mohalla.Model.ItemModel;
import com.kys.mohalla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.viewHolder>{
    ArrayList<ItemModel> list;
    Context context;

    int quantity, color;

    public ItemAdapter(ArrayList<ItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_item, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ItemModel model = list.get(position);
        Picasso.get()
                .load(model.getItemImage())
                .into(holder.ItemImage);

//        holder.ItemImage.setImageResource(model.getItemImage());
        holder.ItemName.setText(model.getItemName());
        holder.ItemPrice.setText(model.getItemPrice());

        quantity = Integer.parseInt(holder.ItemQuantity.getText().toString());
        holder.BtnMinus.setOnClickListener(view->onMinusClick(holder));
        holder.BtnPlus.setOnClickListener(view->onPlusClick(holder));


        if(quantity !=0){
            color = holder.ItemCardLayout.getContext().getColor(R.color.background);
        }else{
            color = holder.ItemCardLayout.getContext().getColor(R.color.greyTint);
        }
        holder.ItemCardLayout.setBackgroundColor(color);
    }

    private void onPlusClick(viewHolder holder) {
        if(quantity == 0){
            color = holder.ItemCardLayout.getContext().getColor(R.color.background);
            holder.ItemCardLayout.setBackgroundColor(color);
        }
        quantity++;
        holder.ItemQuantity.setText("" + quantity);
    }

    private void onMinusClick(viewHolder holder) {
        if(quantity == 0) {
            color = holder.ItemCardLayout.getContext().getColor(R.color.greyTint);
        }else {
            quantity--;
            color = (quantity != 0)? color = holder.ItemCardLayout.getContext().getColor(R.color.background) :
                    holder.ItemCardLayout.getContext().getColor(R.color.greyTint);
            holder.ItemQuantity.setText("" + quantity);
        }
        holder.ItemCardLayout.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        View ItemCardLayout;
        ImageView ItemImage, BtnMinus, BtnPlus;
        TextView ItemPrice, ItemName, ItemQuantity;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ItemImage = itemView.findViewById(R.id.itemimg);
            ItemName = itemView.findViewById(R.id.itemName);
            ItemPrice = itemView.findViewById(R.id.itemPrice);
            BtnMinus = itemView.findViewById(R.id.btn_minus);
            BtnPlus = itemView.findViewById(R.id.btn_plus);
            ItemQuantity = itemView.findViewById(R.id.item_quantity);
            ItemCardLayout = itemView.findViewById(R.id.cardLayout);
        }
    }
}
