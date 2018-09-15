package com.example.okcomputers.smartableproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.okcomputers.smartableproject.R;

public class MenuActivity extends AppCompatActivity {

    private Button rice,soup,starter,fish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rice = (Button)findViewById(R.id.RiceBtn);
        starter = (Button)findViewById(R.id.StarterBtn);
        soup = (Button)findViewById(R.id.SoupBtn);
        fish = (Button)findViewById(R.id.FishBtn);
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Menu");

        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,RiceCategory.class);
                startActivity(i);
            }
        });

        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,StarterCategory.class);
                startActivity(i);
            }
        });
        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,SoupActivity.class);
                startActivity(i);
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,FishCategory.class);
                startActivity(i);
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
            Intent i = new Intent(MenuActivity.this,LoginActivity.class);
            startActivity(i);
        }
        if(id == R.id.SignUp)
        {
            Intent i = new Intent(MenuActivity.this,SignupActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
