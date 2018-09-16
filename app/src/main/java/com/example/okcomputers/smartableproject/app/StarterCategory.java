package com.example.okcomputers.smartableproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.okcomputers.smartableproject.AfterLoginActivities.StarterAfterLogin;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.adapter.FoodItemAdapter;
import com.example.okcomputers.smartableproject.adapter.FoodItemAdapterBeforeLogin;
import com.example.okcomputers.smartableproject.model.FoodItemclass;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StarterCategory extends AppCompatActivity {

    //We use recycler view and adapter and firebase to retrieve data from it.

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<FoodItemclass> list;
    FoodItemAdapterBeforeLogin adapter;
    private Button orderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Starter");

        //initialize recycler view and layoutmangaer means which layout you want to be arrange it.
        recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        //scrolling
        recyclerView.scrollToPosition(0);

        //database reference to add or get data from firebase. here it means goto the firebase find the node starter and get its child.
        reference = FirebaseDatabase.getInstance().getReference().child("Starter");
        //array list to store the data from firebase.
        list = new ArrayList<>();


        //using child event listener from firebase so it'll get all data from firebase starter child values to into arraylist.
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                // here we use model class food item class and datasnapshot is used to get the data from firebase
                FoodItemclass foodItemclass = dataSnapshot.getValue(FoodItemclass.class);
                //we add items into array list
                list.add(foodItemclass);

                //food item adapter before login add data into adapter.
                adapter = new FoodItemAdapterBeforeLogin(StarterCategory.this,list);

                //we set the adapter to recycler view.
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Login) {
            Intent i = new Intent(StarterCategory.this, LoginActivity.class);
            startActivity(i);
        }
        if (id == R.id.SignUp) {
            Intent i = new Intent(StarterCategory.this, SignupActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
