package com.example.okcomputers.smartableproject.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.okcomputers.smartableproject.AfterLoginActivities.OrderConfirm;
import com.example.okcomputers.smartableproject.R;

public class WaitForOrderConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_order_confirm);


        // activity which pops up after order now activity.
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(5000);
                    Intent i = new Intent(WaitForOrderConfirm.this,OrderConfirm.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
