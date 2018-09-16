package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.Utils;
import com.example.okcomputers.smartableproject.app.Menu_activity;
import com.example.okcomputers.smartableproject.app.WaitForOrderConfirm;
import com.example.okcomputers.smartableproject.model.EstimateTimeClass;
import com.example.okcomputers.smartableproject.model.FoodItemclass;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;

public class EstimatedTimeActivity extends AppCompatActivity {

    //In this activity when we add something from sub menu it will be in a listview with estimated time of each dish.
    private ListView mlistView;
    private ArrayList<String>list;
    private ArrayAdapter<String> adapter;
    private DatabaseReference ref;
    private FirebaseDatabase database;
     EstimateTimeClass fooditem;
    private Button btn,btn1;
    String uid;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimated_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       android.support.v7.app.ActionBar actionBar = getSupportActionBar();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Estimate Time");

        btn = findViewById(R.id.Proceed);

        user = FirebaseAuth.getInstance().getCurrentUser();
        fooditem = new EstimateTimeClass();
        mlistView = (ListView) findViewById(R.id.EstlistView);
        database = FirebaseDatabase.getInstance();
        uid = FirebaseAuth.getInstance().getUid();
        ref = database.getReference("Orders");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.est_list_item, R.id.EstItem, list);
        //ref.orderByChild("UserId").equalTo(uid)

        //here we order the child with a session id i.e order by session id means get the session id from firebase and compare
        //it with the model class utils session id if both are equal then it will go inside the method and datasnap gets the data
        //from firebase database so we use for loop to get every data of child and datasnapshot.getchildren() means to get all the
        //children from firebase database.
        ref.orderByChild("SessionId").equalTo(Utils.sessionId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    fooditem  = data.getValue(EstimateTimeClass.class);
                    //list.add(data.getKey())
                    list.add(fooditem.getItemName());
                    mlistView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    //update the menu
    public void Update(View view)
    {
        Intent i =new Intent(EstimatedTimeActivity.this, Menu_activity.class);
        startActivity(i);
    }
    //proceed to another activity.
public void Proceed(View view)
{
    Intent i =new Intent(EstimatedTimeActivity.this, WaitForOrderConfirm.class);
    startActivity(i);
    finish();
    }

}