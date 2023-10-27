package com.example.myapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class MainActivity17 extends AppCompatActivity {
    private DatabaseReference databaseReference;

    private static final String HISTORY_PREFS_NAME = "HistoryPrefs";
    private static final String HISTORY_KEY = "HistoryList";
    TextView textView,t1,t2,t3,t4,t5;
    private DrawerLayout drawerLayout;
    ImageView profile;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> historyList;
    private FirebaseDatabase firebaseDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        toolbar = findViewById(R.id.toolbar);
        profile = findViewById(R.id.imageView17);
        firebaseDatabase = FirebaseDatabase.getInstance();
        t1 = findViewById(R.id.editTextText);
        t2 = findViewById(R.id.editTextcity);
        t3 = findViewById(R.id.editTextdate);
        t4 = findViewById(R.id.editTextevent);
        t5 = findViewById(R.id.editTextoccassion);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.draw_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();
                if(itemid == R.id.performer1){
                    Intent intent = new Intent(MainActivity17.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity17.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity17.this,MainActivity6.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        Intent intent1 = getIntent();
        String fmail = intent1.getStringExtra("fimail");
        String hv = intent1.getStringExtra("msg");
        String ujsinh = intent1.getStringExtra("nam");
        Log.d("namee","user name issss : "+ujsinh);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/"+ujsinh);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity17.this, MainActivity10.class);

                startActivity(intent);
            }
        });

       databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Get the values for each user using userSnapshot instead of dataSnapshot
                        String number = dataSnapshot.child("number").getValue(String.class);
                        String getcity = dataSnapshot.child("city").getValue(String.class);
                        String getdate = dataSnapshot.child("date").getValue(String.class);
                        String getevent = dataSnapshot.child("event").getValue(String.class);
                        String getoccasion = dataSnapshot.child("occasion").getValue(String.class);
                        Log.d("FirebaseData", "User Name: " + number);
                        Log.d("FirebaseData", "User getcity: " + getcity);
                        Log.d("FirebaseData", "User getdate: " + getdate);
                        Log.d("FirebaseData", "User getevent: " + getevent);
                        Log.d("FirebaseData", "User getoccasion: " + getoccasion);
                        t1.setText("Number:        " + number);
                        t2.setText("City:              " + getcity);
                        t3.setText("Event date:    " + getdate);
                        t4.setText("Event:           " + getevent);
                        t5.setText("Occasion:      " + getoccasion);
                        databaseReference.keepSynced(true);
                        }


                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity17.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        }



}
