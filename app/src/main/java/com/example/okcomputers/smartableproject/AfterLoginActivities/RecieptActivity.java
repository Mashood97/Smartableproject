package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.Utils;
import com.example.okcomputers.smartableproject.adapter.FoodItemAdapter;
import com.example.okcomputers.smartableproject.adapter.RecieptAdapter;
import com.example.okcomputers.smartableproject.app.Menu_activity;
import com.example.okcomputers.smartableproject.model.EstimateTimeClass;
import com.example.okcomputers.smartableproject.model.FoodItemclass;
import com.example.okcomputers.smartableproject.model.RecieptClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecieptActivity extends AppCompatActivity {


    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<RecieptClass> list;
    RecieptAdapter adapter;
    RecieptClass foodItemclass;
    String uid;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);

        list = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recieptRecycle);
        Button finish = findViewById(R.id.ButtonFinish);

        recyclerView.scrollToPosition(0);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        uid = FirebaseAuth.getInstance().getUid();

        if (getIntent().hasExtra("SessionId"))
        {
            sessionId = getIntent().getStringExtra("SessionId");
            finish.setVisibility(View.GONE);
        }
        else
        {
            sessionId = Utils.sessionId;
        }

        reference = FirebaseDatabase.getInstance().getReference().child("Orders");
        //reference.orderByChild("UserId").equalTo(uid)
        reference.orderByChild("SessionId").equalTo(sessionId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        foodItemclass = dataSnapshot1.getValue(RecieptClass.class);
                        list.add(foodItemclass);
                    }
                    adapter = new RecieptAdapter(RecieptActivity.this, list);
                    recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                toast.show();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecieptActivity.this, Menu_activity.class);
                startActivity(i);
                list.clear();
                adapter.notifyDataSetChanged();
                finish();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        recyclerView.setAdapter(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }
}
