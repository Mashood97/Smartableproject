package com.example.okcomputers.smartableproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.okcomputers.smartableproject.AfterLoginActivities.FishAfterLogin;
import com.example.okcomputers.smartableproject.AfterLoginActivities.MyOrderActivity;
import com.example.okcomputers.smartableproject.AfterLoginActivities.RecieptActivity;
import com.example.okcomputers.smartableproject.AfterLoginActivities.RiceAfterlogin;
import com.example.okcomputers.smartableproject.AfterLoginActivities.SoupAfterLogin;
import com.example.okcomputers.smartableproject.AfterLoginActivities.StarterAfterLogin;
import com.example.okcomputers.smartableproject.R;
import com.example.okcomputers.smartableproject.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button rice,soup,starter,fish;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final String TAG = "LoginScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Menu");

       // NavigationView nav = (NavigationView)findViewById(R.id.nav_view);
        rice = (Button)findViewById(R.id.RiceBtn);
        starter = (Button)findViewById(R.id.StarterBtn);
        soup = (Button)findViewById(R.id.SoupBtn);
        fish = (Button)findViewById(R.id.FishBtn);

        setupFirebaseListener();

        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_activity.this,RiceAfterlogin.class);
                startActivity(i);
            }
        });

        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_activity.this,StarterAfterLogin.class);
                startActivity(i);
            }
        });
        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_activity.this,SoupAfterLogin.class);
                startActivity(i);
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_activity.this,FishAfterLogin.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        TextView textView = headerview.findViewById(R.id.emailnav);
        textView.setText(user.getEmail());

        // Get next order only fisrt time
        if(Utils.nextOrderNum == -1){
            FirebaseDatabase.getInstance().getReference("WebOrders").child("NextOrderNum").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Utils.nextOrderNum = dataSnapshot.getValue(long.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuid) {
            Intent i = new Intent(Menu_activity.this,Menu_activity.class);
            startActivity(i);
        } else if (id == R.id.orderid) {
                     Intent i = new Intent(Menu_activity.this, MyOrderActivity.class);
                    startActivity(i);

        } else if (id == R.id.signoutid) {
            FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setupFirebaseListener() {
        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener.");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }
}
