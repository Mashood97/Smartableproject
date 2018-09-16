package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.adapter.FoodItemAdapter;
import com.example.okcomputers.smartableproject.model.FoodItemclass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FishAfterLogin extends AppCompatActivity {

    //same work as before login fish category just a difference is that it'll goto order now activity to add quantity.
    //recycler view is used to recycle the views thats a work as list its best example is a contact list it upgrades till then a user want.
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<FoodItemclass> list;
    FoodItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_after_login);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Fish");

        recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.scrollToPosition(0);

        reference = FirebaseDatabase.getInstance().getReference().child("SeaFood");
        list = new ArrayList<>();


        //here we use add child event listener so that we can add more child from admin and it will update it.
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                FoodItemclass foodItemclass = dataSnapshot.getValue(FoodItemclass.class);
                list.add(foodItemclass);


                adapter = new FoodItemAdapter(FishAfterLogin.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
