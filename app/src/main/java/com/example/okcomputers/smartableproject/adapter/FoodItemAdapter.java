package com.example.okcomputers.smartableproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.AfterLoginActivities.OrderNow;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.model.FoodItemclass;

import java.util.ArrayList;

/**
 * Created by OK Computers on 8/26/2018.
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.MyViewHolder> {

    Context context;
    ArrayList<FoodItemclass> profiles;


    public FoodItemAdapter(Context c , ArrayList<FoodItemclass> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.name.setText(profiles.get(position).getName());
        holder.price.setText(String.valueOf(profiles.get(position).getPrice()));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OrderNow.class);
                i.putExtra("name",profiles.get(position).getName());
                i.putExtra("price",String.valueOf(profiles.get(position).getPrice()));
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,price;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.ItemNameIds);
            price = (TextView) itemView.findViewById(R.id.ItemPriceId);
            btn = (Button)itemView.findViewById(R.id.gotoNExtAct);
        }


    }
}
