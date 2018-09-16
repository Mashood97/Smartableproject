package com.example.okcomputers.smartableproject.AfterLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.okcomputers.smartableproject.R;

public class OrderConfirm extends AppCompatActivity {

    private Button recieptbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        //In this Activity we tap on a button order confirm and will get to the Reciept Activity.
        recieptbtn = (Button)findViewById(R.id.RecieptBtn);
        recieptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderConfirm.this,RecieptActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


}
