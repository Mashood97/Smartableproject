package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.Utils;
import com.example.okcomputers.smartableproject.model.FoodItemclass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderNow extends AppCompatActivity {

    //In this activity we add quantity and forward it to another activity.
    private int quantity = 0;
    private TextView itemName,itemPrice;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    String Itemname,ItemPrice;
    int orderNo;
    private FoodItemclass foodItemclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);


        itemName = (TextView)findViewById(R.id.ItemNames);
        itemPrice = (TextView)findViewById(R.id.ItemPrice);
        Intent i = getIntent();
        Itemname = i.getStringExtra("name");
        ItemPrice = i.getStringExtra("price");
        itemName.setText(Itemname);
        itemPrice.setText(ItemPrice);
        foodItemclass = new FoodItemclass();
    }

    private void displayQuantity(int Quantity) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.QuantityText);
        quantityTextView.setText("" + Quantity);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast toast = Toast.makeText(getApplicationContext(),"Quantity must be less than 100",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
            toast.show();
        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }


    public void decrement(View view) {
        if (quantity == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Quantity Must be greater then zero", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
            toast.show();
        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }
    public void NextAct(View view)
    {
        if(quantity!=0)
        {
        OrderNow();
        Intent intent = new Intent(OrderNow.this, EstimatedTimeActivity.class);
        intent.putExtra("Name",Itemname);
        startActivity(intent);
            finish();
    }
    else {
            Toast toast = Toast.makeText(getApplicationContext(), "Select valid quantity", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
            toast.show();
        }

    }
    //full order saved
    private void OrderNow() {

        //This method is used to save all the order data into firebase database.

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        String uid = user.getUid();

        if(user !=null) {

            //it will generate the session id of orders.
            if (Utils.sessionId =="")
            {
                // FIrst time order placing
                // Generate session id
                Utils.sessionId = FirebaseDatabase.getInstance().getReference("Orders").push().getKey();

            }
            //It'll save the data into firebase database.

            //databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(Itemname);
            databaseReference = FirebaseDatabase.getInstance().getReference("Orders").push();
            databaseReference.child("Email").setValue(email);
            databaseReference.child("SessionId").setValue(Utils.sessionId);
            databaseReference.child("UserId").setValue(uid);
            databaseReference.child("ItemName").setValue(Itemname);
            databaseReference.child("ItemPrice").setValue(Integer.valueOf(ItemPrice));
            databaseReference.child("Quantity").setValue(quantity);
            databaseReference.child("TotalPrice").setValue(quantity * Integer.valueOf(ItemPrice));
            Toast toast = Toast.makeText(getApplicationContext(), "Data Saved into database", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
            toast.show();

            //it will generate the order no.
            DatabaseReference webref = FirebaseDatabase.getInstance().getReference("WebOrders").child(Utils.nextOrderNum+"");
            webref.child("Email").setValue(email);
            webref.child("SessionId").setValue(Utils.sessionId);
            webref.child("UserId").setValue(uid);

            // Change order number for next customer
            FirebaseDatabase.getInstance().getReference("WebOrders").child("NextOrderNum").setValue(Utils.nextOrderNum+1);

            //it saves the data into web orders node in firebase.
            DatabaseReference itemsef = webref.child("items").push();
            itemsef.child("ItemName").setValue(Itemname);
            itemsef.child("ItemPrice").setValue(Integer.valueOf(ItemPrice));
            itemsef.child("Quantity").setValue(quantity);
            itemsef.child("TotalPrice").setValue(quantity * Integer.valueOf(ItemPrice));



        }


    }
}
