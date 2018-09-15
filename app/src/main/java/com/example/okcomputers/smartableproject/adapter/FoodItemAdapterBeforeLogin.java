package com.example.okcomputers.smartableproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.okcomputers.smartableproject.AfterLoginActivities.OrderNow;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.app.LoginActivity;
import com.example.okcomputers.smartableproject.model.FoodItemclass;

import java.util.ArrayList;

/**
 * Created by OK Computers on 8/27/2018.
 */

public class FoodItemAdapterBeforeLogin  extends RecyclerView.Adapter<FoodItemAdapterBeforeLogin.MyViewHolder> {

    Context context;
    ArrayList<FoodItemclass> profiles;


    public FoodItemAdapterBeforeLogin(Context c , ArrayList<FoodItemclass> p)
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.price.setText(String.valueOf(profiles.get(position).getPrice()));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,LoginActivity.class);
                context.startActivity(i);
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
