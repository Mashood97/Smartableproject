package com.example.okcomputers.smartableproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.okcomputers.smartableproject.AfterLoginActivities.OrderNow;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.model.FoodItemclass;
import com.example.okcomputers.smartableproject.model.RecieptClass;

import java.util.ArrayList;

/**
 * Created by OK Computers on 8/26/2018.
 */

public class RecieptAdapter extends RecyclerView.Adapter<RecieptAdapter.MyViewHolder> {

    Context context;
    ArrayList<RecieptClass> profiles;

    public RecieptAdapter(Context c , ArrayList<RecieptClass> p)
    {
        context = c;
        profiles = p;
    }


    @Override
    public RecieptAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.reciept_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getItemName());
        holder.price.setText(String.valueOf(profiles.get(position).getTotalPrice()));
        holder.quantity.setText(String.valueOf(profiles.get(position).getQuantity()));
            }


    @Override
    public int getItemCount() {
        return profiles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView quantity,name,price;
        public MyViewHolder(View itemView) {
            super(itemView);
            quantity = (TextView) itemView.findViewById(R.id.ItemQuantityNo);
            name = (TextView) itemView.findViewById(R.id.ItemRecieptName);
            price = (TextView)itemView.findViewById(R.id.ItemTotalPrice);
        }


    }
}
