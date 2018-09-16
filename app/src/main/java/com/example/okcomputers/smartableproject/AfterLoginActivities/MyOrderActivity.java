package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.adapter.MyOrderAdapter;
import com.example.okcomputers.smartableproject.adapter.RecieptAdapter;
import com.example.okcomputers.smartableproject.model.MyOrderClass;
import com.example.okcomputers.smartableproject.model.RecieptClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {

    //this is an activity appears from nav drawer.
    //In this all the orders will appear of a specific user orders and save in firebase database.
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<MyOrderClass> list;
    MyOrderAdapter adapter;
    MyOrderClass orderclass;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        recyclerView = findViewById(R.id.recyclerMyOrder);
        list = new ArrayList<>();
        recyclerView.scrollToPosition(0);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        uid = FirebaseAuth.getInstance().getUid();

        //the order is saved with the name WebOrders and we get the order no of each node i.e order 1 order 2 of each user.
        reference = FirebaseDatabase.getInstance().getReference().child("WebOrders");
        reference.orderByChild("UserId").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    orderclass = dataSnapshot1.getValue(MyOrderClass.class);
                    orderclass.setOrder(Integer.parseInt(dataSnapshot1.getKey()));
                    list.add(orderclass);
                }
                adapter = new MyOrderAdapter(MyOrderActivity.this, list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                toast.show();
            }
        });
    }
}
