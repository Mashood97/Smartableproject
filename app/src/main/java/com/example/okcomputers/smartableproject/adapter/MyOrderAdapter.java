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
import com.example.okcomputers.smartableproject.AfterLoginActivities.RecieptActivity;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.model.MyOrderClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {


    //Same as Food item adapter difference is we send orderid and session id through intents.
    Context mContext;
    ArrayList<MyOrderClass> myorders;

    public MyOrderAdapter(Context c , ArrayList<MyOrderClass>myorders)
    {
        mContext = c;
        this.myorders = myorders;
    }
    @NonNull
    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new MyOrderAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.myorder_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.MyViewHolder holder, int position) {
        final  int pos = position;
        holder.orderNum.setText(String.valueOf(myorders.get(position).getOrder()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,RecieptActivity.class);
                i.putExtra("orderId",myorders.get(pos).getOrder());
                i.putExtra("SessionId", myorders.get(pos).SessionId);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myorders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView orderNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            orderNum = (TextView)itemView.findViewById(R.id.Ordernums);
        }
    }

}

