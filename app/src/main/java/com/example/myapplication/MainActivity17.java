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
    private String finalnamedb;
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
        String receivedText1 = Dataholder.getInstance().getFinalText();
        SharedPreferences sharedPreferencesem = getSharedPreferences("MyPrefs_e", MODE_PRIVATE);
        String receivedText = sharedPreferencesem.getString("finaldbem","");
//        SharedPreferences sharedPreferencesphonedbfirebase = getSharedPreferences("MyPrefs_p", MODE_PRIVATE);
//        String receivedPhone = sharedPreferencesphonedbfirebase.getString("finaldbph","");
        Log.d("DBemail","Here is the email id "+receivedText);
//        Log.d("DBphone","Here is the email id "+receivedPhone);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity17.this, MainActivity10.class);

                startActivity(intent);
            }
        });
        {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersInfo");
            Query query = databaseReference.orderByChild("mail").equalTo(receivedText);
            DatabaseReference dbeventinfo = FirebaseDatabase.getInstance().getReference("UsersEvents");
            Query queryeve = dbeventinfo.orderByChild("mail").equalTo(receivedText);

//        dbeventinfo.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot userSnapshot1 : snapshot.getChildren()){
//                    String getoccasion = snapshot.child("occasion").getValue(String.class);
//                    String datedb = snapshot.child("Date").getValue(String.class);
//                    String getevent = snapshot.child("event").getValue(String.class);
//                    String categorydb = snapshot.child("Category").getValue(String.class);
//                    t3.setText("Event date:    " + datedb);
//                    t5.setText("Occasion:      " + getoccasion);
//                    t4.setText("Event:           " + getevent);
//                    t2.setText("Category:              " + categorydb);
//                    dbeventinfo.keepSynced(true);
//                                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferencese.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                    editor.putString("date", datedb);
//                    editor.putString("occasion", getoccasion);
//                    editor.putString("event", getevent);
//                    editor.putString("category", categorydb);
//                    editor.apply();
//
//                    // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                    // Set data to your TextViews or other UI elements if required
//                    // nameTextView.setText(name);
//                    // phoneTextView.setText(phoneNumber);
//                }
//                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////
////// Retrieve data
////
////
//        String date = sharedPreferencese.getString("date", "");
//        String occas = sharedPreferencese.getString("occasion", "");
//        String eve = sharedPreferencese.getString("event", "");
//        String catg = sharedPreferencese.getString("category", "");
//        t3.setText("Event date:    " + date);
//        t5.setText("Occasion:      " + occas);
//        t4.setText("Event:           " + eve);
//        t2.setText("Category:              " + catg);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name1 = snapshot.child("name").getValue(String.class);
                        String phoneNumber = snapshot.child("number").getValue(String.class);
                        String maild = snapshot.child("mail").getValue(String.class);
//                    name.setText(name1);
//                    email.setText(maild);
                        t1.setText("Number:        " + phoneNumber);
                        finalnamedb = name1;
                        SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesf.edit();

// Save data
//                    editor.putString("Name", name1);
//                    editor.putString("Email", receivedText);

                        editor.putString("PhoneNumber", phoneNumber);
                        editor.apply();

                        // Use the retrieved data as needed
//                    Log.d("FirebaseData", "Name: " + name1);
//                    Log.d("FirebaseData", "Email: " + receivedText);
                        Log.d("FirebaseData", "Phone Number: " + phoneNumber);

                        // Set data to your TextViews or other UI elements if required
                        // nameTextView.setText(name);
                        // phoneTextView.setText(phoneNumber);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                    Log.e("FirebaseData", "Error: " + error.getMessage());
                }
            });
            SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data


            String phoneNumber1 = sharedPreferencesf.getString("PhoneNumber", "");
            t1.setText("Number:        " + phoneNumber1);
//       databaseReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                        // Get the values for each user using userSnapshot instead of dataSnapshot
//                        String number = dataSnapshot.child("number").getValue(String.class);
////                        String getcity = dataSnapshot.child("city").getValue(String.class);
////                        String getdate = dataSnapshot.child("date").getValue(String.class);
////                        String getevent = dataSnapshot.child("event").getValue(String.class);
////                        String getoccasion = dataSnapshot.child("occasion").getValue(String.class);
//                        Log.d("FirebaseData", "User Name: " + number);
////                        Log.d("FirebaseData", "User getcity: " + getcity);
////                        Log.d("FirebaseData", "User getdate: " + getdate);
////                        Log.d("FirebaseData", "User getevent: " + getevent);
////                        Log.d("FirebaseData", "User getoccasion: " + getoccasion);
//                        t1.setText("Number:        " + number);
////                        t2.setText("City:              " + getcity);
////                        t3.setText("Event date:    " + getdate);
////                        t4.setText("Event:           " + getevent);
////                        t5.setText("Occasion:      " + getoccasion);
//                        databaseReference.keepSynced(true);
//                        }
//
//
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity17.this, "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
            queryeve.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String getoccasion = snapshot.child("occasion").getValue(String.class);
                        String datedb = snapshot.child("Date").getValue(String.class);
                        String getevent = snapshot.child("event").getValue(String.class);
                        String categorydb = snapshot.child("Category").getValue(String.class);
//                    name.setText(name1);
//                    email.setText(maild);
                        t3.setText("Event date:    " + datedb);
                        t5.setText("Occasion:      " + getoccasion);
                        t4.setText("Event:           " + getevent);
                        t2.setText("Category:              " + categorydb);
//                    t1.setText(phoneNumber);
                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencese.edit();

// Save data
//                    editor.putString("Name", name1);
//                    editor.putString("Email", receivedText);

                        editor.putString("date", datedb);
                        editor.putString("occasion", getoccasion);
                        editor.putString("event", getevent);
                        editor.putString("category", categorydb);
                        editor.apply();

                        // Use the retrieved data as needed
//                    Log.d("FirebaseData", "Name: " + name1);
//                    Log.d("FirebaseData", "Email: " + receivedText);
//                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);

                        // Set data to your TextViews or other UI elements if required
                        // nameTextView.setText(name);
                        // phoneTextView.setText(phoneNumber);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                    Log.e("FirebaseData", "Error: " + error.getMessage());
                }
            });
            SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data


            String date = sharedPreferencese.getString("date", "");
            String occas = sharedPreferencese.getString("occasion", "");
            String eve = sharedPreferencese.getString("event", "");
            String catg = sharedPreferencese.getString("category", "");
            t3.setText("Event date:    " + date);
            t5.setText("Occasion:      " + occas);
            t4.setText("Event:           " + eve);
            t2.setText("Category:              " + catg);
//        }else{
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersInfo");
//            Query query = databaseReference.orderByChild("number").equalTo(receivedPhone);
//            DatabaseReference dbeventinfo = FirebaseDatabase.getInstance().getReference("UsersEvents");
//            Query queryeve = dbeventinfo.orderByChild("number").equalTo(receivedPhone);
////        dbeventinfo.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot userSnapshot1 : snapshot.getChildren()){
////                    String getoccasion = snapshot.child("occasion").getValue(String.class);
////                    String datedb = snapshot.child("Date").getValue(String.class);
////                    String getevent = snapshot.child("event").getValue(String.class);
////                    String categorydb = snapshot.child("Category").getValue(String.class);
////                    t3.setText("Event date:    " + datedb);
////                    t5.setText("Occasion:      " + getoccasion);
////                    t4.setText("Event:           " + getevent);
////                    t2.setText("Category:              " + categorydb);
////                    dbeventinfo.keepSynced(true);
////                                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////                    SharedPreferences.Editor editor = sharedPreferencese.edit();
////
////// Save data
//////                    editor.putString("Name", name1);
//////                    editor.putString("Email", receivedText);
////
////                    editor.putString("date", datedb);
////                    editor.putString("occasion", getoccasion);
////                    editor.putString("event", getevent);
////                    editor.putString("category", categorydb);
////                    editor.apply();
////
////                    // Use the retrieved data as needed
//////                    Log.d("FirebaseData", "Name: " + name1);
//////                    Log.d("FirebaseData", "Email: " + receivedText);
//////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
////
////                    // Set data to your TextViews or other UI elements if required
////                    // nameTextView.setText(name);
////                    // phoneTextView.setText(phoneNumber);
////                }
////                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//////
//////// Retrieve data
//////
//////
////        String date = sharedPreferencese.getString("date", "");
////        String occas = sharedPreferencese.getString("occasion", "");
////        String eve = sharedPreferencese.getString("event", "");
////        String catg = sharedPreferencese.getString("category", "");
////        t3.setText("Event date:    " + date);
////        t5.setText("Occasion:      " + occas);
////        t4.setText("Event:           " + eve);
////        t2.setText("Category:              " + catg);
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String name1 = snapshot.child("name").getValue(String.class);
//                        String phoneNumber = snapshot.child("number").getValue(String.class);
//                        String maild = snapshot.child("mail").getValue(String.class);
////                    name.setText(name1);
////                    email.setText(maild);
//                        t1.setText("Number:        " + phoneNumber);
//                        finalnamedb = name1;
//                        SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferencesf.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                        editor.putString("PhoneNumber", phoneNumber);
//                        editor.apply();
//
//                        // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
//                        Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                        // Set data to your TextViews or other UI elements if required
//                        // nameTextView.setText(name);
//                        // phoneTextView.setText(phoneNumber);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle onCancelled event
//                    Log.e("FirebaseData", "Error: " + error.getMessage());
//                }
//            });
//            SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//// Retrieve data
//
//
//            String phoneNumber1 = sharedPreferencesf.getString("PhoneNumber", "");
//            t1.setText("Number:        " + phoneNumber1);
////       databaseReference.addValueEventListener(new ValueEventListener() {
////
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
////                        // Get the values for each user using userSnapshot instead of dataSnapshot
////                        String number = dataSnapshot.child("number").getValue(String.class);
//////                        String getcity = dataSnapshot.child("city").getValue(String.class);
//////                        String getdate = dataSnapshot.child("date").getValue(String.class);
//////                        String getevent = dataSnapshot.child("event").getValue(String.class);
//////                        String getoccasion = dataSnapshot.child("occasion").getValue(String.class);
////                        Log.d("FirebaseData", "User Name: " + number);
//////                        Log.d("FirebaseData", "User getcity: " + getcity);
//////                        Log.d("FirebaseData", "User getdate: " + getdate);
//////                        Log.d("FirebaseData", "User getevent: " + getevent);
//////                        Log.d("FirebaseData", "User getoccasion: " + getoccasion);
////                        t1.setText("Number:        " + number);
//////                        t2.setText("City:              " + getcity);
//////                        t3.setText("Event date:    " + getdate);
//////                        t4.setText("Event:           " + getevent);
//////                        t5.setText("Occasion:      " + getoccasion);
////                        databaseReference.keepSynced(true);
////                        }
////
////
////                }
////
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(MainActivity17.this, "Error", Toast.LENGTH_SHORT).show();
////
////            }
////        });
//            queryeve.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String getoccasion = snapshot.child("occasion").getValue(String.class);
//                        String datedb = snapshot.child("Date").getValue(String.class);
//                        String getevent = snapshot.child("event").getValue(String.class);
//                        String categorydb = snapshot.child("Category").getValue(String.class);
////                    name.setText(name1);
////                    email.setText(maild);
//                        t3.setText("Event date:    " + datedb);
//                        t5.setText("Occasion:      " + getoccasion);
//                        t4.setText("Event:           " + getevent);
//                        t2.setText("Category:              " + categorydb);
////                    t1.setText(phoneNumber);
//                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferencese.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                        editor.putString("date", datedb);
//                        editor.putString("occasion", getoccasion);
//                        editor.putString("event", getevent);
//                        editor.putString("category", categorydb);
//                        editor.apply();
//
//                        // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                        // Set data to your TextViews or other UI elements if required
//                        // nameTextView.setText(name);
//                        // phoneTextView.setText(phoneNumber);
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle onCancelled event
//                    Log.e("FirebaseData", "Error: " + error.getMessage());
//                }
//            });
//            SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//// Retrieve data
//
//
//            String date = sharedPreferencese.getString("date", "");
//            String occas = sharedPreferencese.getString("occasion", "");
//            String eve = sharedPreferencese.getString("event", "");
//            String catg = sharedPreferencese.getString("category", "");
//            t3.setText("Event date:    " + date);
//            t5.setText("Occasion:      " + occas);
//            t4.setText("Event:           " + eve);
//            t2.setText("Category:              " + catg);
//        }
        }
        {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersInfo");
            Query query = databaseReference.orderByChild("number").equalTo(receivedText);
            DatabaseReference dbeventinfo = FirebaseDatabase.getInstance().getReference("UsersEvents");
            Query queryeve = dbeventinfo.orderByChild("number").equalTo(receivedText);

//        dbeventinfo.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot userSnapshot1 : snapshot.getChildren()){
//                    String getoccasion = snapshot.child("occasion").getValue(String.class);
//                    String datedb = snapshot.child("Date").getValue(String.class);
//                    String getevent = snapshot.child("event").getValue(String.class);
//                    String categorydb = snapshot.child("Category").getValue(String.class);
//                    t3.setText("Event date:    " + datedb);
//                    t5.setText("Occasion:      " + getoccasion);
//                    t4.setText("Event:           " + getevent);
//                    t2.setText("Category:              " + categorydb);
//                    dbeventinfo.keepSynced(true);
//                                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferencese.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                    editor.putString("date", datedb);
//                    editor.putString("occasion", getoccasion);
//                    editor.putString("event", getevent);
//                    editor.putString("category", categorydb);
//                    editor.apply();
//
//                    // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                    // Set data to your TextViews or other UI elements if required
//                    // nameTextView.setText(name);
//                    // phoneTextView.setText(phoneNumber);
//                }
//                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////
////// Retrieve data
////
////
//        String date = sharedPreferencese.getString("date", "");
//        String occas = sharedPreferencese.getString("occasion", "");
//        String eve = sharedPreferencese.getString("event", "");
//        String catg = sharedPreferencese.getString("category", "");
//        t3.setText("Event date:    " + date);
//        t5.setText("Occasion:      " + occas);
//        t4.setText("Event:           " + eve);
//        t2.setText("Category:              " + catg);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name1 = snapshot.child("name").getValue(String.class);
                        String phoneNumber = snapshot.child("number").getValue(String.class);
                        String maild = snapshot.child("mail").getValue(String.class);
//                    name.setText(name1);
//                    email.setText(maild);
                        t1.setText("Number:        " + phoneNumber);
                        finalnamedb = name1;
                        SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesf.edit();

// Save data
//                    editor.putString("Name", name1);
//                    editor.putString("Email", receivedText);

                        editor.putString("PhoneNumber", phoneNumber);
                        editor.apply();

                        // Use the retrieved data as needed
//                    Log.d("FirebaseData", "Name: " + name1);
//                    Log.d("FirebaseData", "Email: " + receivedText);
                        Log.d("FirebaseData", "Phone Number: " + phoneNumber);

                        // Set data to your TextViews or other UI elements if required
                        // nameTextView.setText(name);
                        // phoneTextView.setText(phoneNumber);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                    Log.e("FirebaseData", "Error: " + error.getMessage());
                }
            });
            SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data


            String phoneNumber1 = sharedPreferencesf.getString("PhoneNumber", "");
            t1.setText("Number:        " + phoneNumber1);
//       databaseReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                        // Get the values for each user using userSnapshot instead of dataSnapshot
//                        String number = dataSnapshot.child("number").getValue(String.class);
////                        String getcity = dataSnapshot.child("city").getValue(String.class);
////                        String getdate = dataSnapshot.child("date").getValue(String.class);
////                        String getevent = dataSnapshot.child("event").getValue(String.class);
////                        String getoccasion = dataSnapshot.child("occasion").getValue(String.class);
//                        Log.d("FirebaseData", "User Name: " + number);
////                        Log.d("FirebaseData", "User getcity: " + getcity);
////                        Log.d("FirebaseData", "User getdate: " + getdate);
////                        Log.d("FirebaseData", "User getevent: " + getevent);
////                        Log.d("FirebaseData", "User getoccasion: " + getoccasion);
//                        t1.setText("Number:        " + number);
////                        t2.setText("City:              " + getcity);
////                        t3.setText("Event date:    " + getdate);
////                        t4.setText("Event:           " + getevent);
////                        t5.setText("Occasion:      " + getoccasion);
//                        databaseReference.keepSynced(true);
//                        }
//
//
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity17.this, "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
            queryeve.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String getoccasion = snapshot.child("occasion").getValue(String.class);
                        String datedb = snapshot.child("Date").getValue(String.class);
                        String getevent = snapshot.child("event").getValue(String.class);
                        String categorydb = snapshot.child("Category").getValue(String.class);
//                    name.setText(name1);
//                    email.setText(maild);
                        t3.setText("Event date:    " + datedb);
                        t5.setText("Occasion:      " + getoccasion);
                        t4.setText("Event:           " + getevent);
                        t2.setText("Category:              " + categorydb);
//                    t1.setText(phoneNumber);
                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencese.edit();

// Save data
//                    editor.putString("Name", name1);
//                    editor.putString("Email", receivedText);

                        editor.putString("date", datedb);
                        editor.putString("occasion", getoccasion);
                        editor.putString("event", getevent);
                        editor.putString("category", categorydb);
                        editor.apply();

                        // Use the retrieved data as needed
//                    Log.d("FirebaseData", "Name: " + name1);
//                    Log.d("FirebaseData", "Email: " + receivedText);
//                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);

                        // Set data to your TextViews or other UI elements if required
                        // nameTextView.setText(name);
                        // phoneTextView.setText(phoneNumber);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                    Log.e("FirebaseData", "Error: " + error.getMessage());
                }
            });
            SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data


            String date = sharedPreferencese.getString("date", "");
            String occas = sharedPreferencese.getString("occasion", "");
            String eve = sharedPreferencese.getString("event", "");
            String catg = sharedPreferencese.getString("category", "");
            t3.setText("Event date:    " + date);
            t5.setText("Occasion:      " + occas);
            t4.setText("Event:           " + eve);
            t2.setText("Category:              " + catg);
//        }else{
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersInfo");
//            Query query = databaseReference.orderByChild("number").equalTo(receivedPhone);
//            DatabaseReference dbeventinfo = FirebaseDatabase.getInstance().getReference("UsersEvents");
//            Query queryeve = dbeventinfo.orderByChild("number").equalTo(receivedPhone);
////        dbeventinfo.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot userSnapshot1 : snapshot.getChildren()){
////                    String getoccasion = snapshot.child("occasion").getValue(String.class);
////                    String datedb = snapshot.child("Date").getValue(String.class);
////                    String getevent = snapshot.child("event").getValue(String.class);
////                    String categorydb = snapshot.child("Category").getValue(String.class);
////                    t3.setText("Event date:    " + datedb);
////                    t5.setText("Occasion:      " + getoccasion);
////                    t4.setText("Event:           " + getevent);
////                    t2.setText("Category:              " + categorydb);
////                    dbeventinfo.keepSynced(true);
////                                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////                    SharedPreferences.Editor editor = sharedPreferencese.edit();
////
////// Save data
//////                    editor.putString("Name", name1);
//////                    editor.putString("Email", receivedText);
////
////                    editor.putString("date", datedb);
////                    editor.putString("occasion", getoccasion);
////                    editor.putString("event", getevent);
////                    editor.putString("category", categorydb);
////                    editor.apply();
////
////                    // Use the retrieved data as needed
//////                    Log.d("FirebaseData", "Name: " + name1);
//////                    Log.d("FirebaseData", "Email: " + receivedText);
//////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
////
////                    // Set data to your TextViews or other UI elements if required
////                    // nameTextView.setText(name);
////                    // phoneTextView.setText(phoneNumber);
////                }
////                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//////
//////// Retrieve data
//////
//////
////        String date = sharedPreferencese.getString("date", "");
////        String occas = sharedPreferencese.getString("occasion", "");
////        String eve = sharedPreferencese.getString("event", "");
////        String catg = sharedPreferencese.getString("category", "");
////        t3.setText("Event date:    " + date);
////        t5.setText("Occasion:      " + occas);
////        t4.setText("Event:           " + eve);
////        t2.setText("Category:              " + catg);
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String name1 = snapshot.child("name").getValue(String.class);
//                        String phoneNumber = snapshot.child("number").getValue(String.class);
//                        String maild = snapshot.child("mail").getValue(String.class);
////                    name.setText(name1);
////                    email.setText(maild);
//                        t1.setText("Number:        " + phoneNumber);
//                        finalnamedb = name1;
//                        SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferencesf.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                        editor.putString("PhoneNumber", phoneNumber);
//                        editor.apply();
//
//                        // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
//                        Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                        // Set data to your TextViews or other UI elements if required
//                        // nameTextView.setText(name);
//                        // phoneTextView.setText(phoneNumber);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle onCancelled event
//                    Log.e("FirebaseData", "Error: " + error.getMessage());
//                }
//            });
//            SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//// Retrieve data
//
//
//            String phoneNumber1 = sharedPreferencesf.getString("PhoneNumber", "");
//            t1.setText("Number:        " + phoneNumber1);
////       databaseReference.addValueEventListener(new ValueEventListener() {
////
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
////                        // Get the values for each user using userSnapshot instead of dataSnapshot
////                        String number = dataSnapshot.child("number").getValue(String.class);
//////                        String getcity = dataSnapshot.child("city").getValue(String.class);
//////                        String getdate = dataSnapshot.child("date").getValue(String.class);
//////                        String getevent = dataSnapshot.child("event").getValue(String.class);
//////                        String getoccasion = dataSnapshot.child("occasion").getValue(String.class);
////                        Log.d("FirebaseData", "User Name: " + number);
//////                        Log.d("FirebaseData", "User getcity: " + getcity);
//////                        Log.d("FirebaseData", "User getdate: " + getdate);
//////                        Log.d("FirebaseData", "User getevent: " + getevent);
//////                        Log.d("FirebaseData", "User getoccasion: " + getoccasion);
////                        t1.setText("Number:        " + number);
//////                        t2.setText("City:              " + getcity);
//////                        t3.setText("Event date:    " + getdate);
//////                        t4.setText("Event:           " + getevent);
//////                        t5.setText("Occasion:      " + getoccasion);
////                        databaseReference.keepSynced(true);
////                        }
////
////
////                }
////
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(MainActivity17.this, "Error", Toast.LENGTH_SHORT).show();
////
////            }
////        });
//            queryeve.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String getoccasion = snapshot.child("occasion").getValue(String.class);
//                        String datedb = snapshot.child("Date").getValue(String.class);
//                        String getevent = snapshot.child("event").getValue(String.class);
//                        String categorydb = snapshot.child("Category").getValue(String.class);
////                    name.setText(name1);
////                    email.setText(maild);
//                        t3.setText("Event date:    " + datedb);
//                        t5.setText("Occasion:      " + getoccasion);
//                        t4.setText("Event:           " + getevent);
//                        t2.setText("Category:              " + categorydb);
////                    t1.setText(phoneNumber);
//                        SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferencese.edit();
//
//// Save data
////                    editor.putString("Name", name1);
////                    editor.putString("Email", receivedText);
//
//                        editor.putString("date", datedb);
//                        editor.putString("occasion", getoccasion);
//                        editor.putString("event", getevent);
//                        editor.putString("category", categorydb);
//                        editor.apply();
//
//                        // Use the retrieved data as needed
////                    Log.d("FirebaseData", "Name: " + name1);
////                    Log.d("FirebaseData", "Email: " + receivedText);
////                    Log.d("FirebaseData", "Phone Number: " + phoneNumber);
//
//                        // Set data to your TextViews or other UI elements if required
//                        // nameTextView.setText(name);
//                        // phoneTextView.setText(phoneNumber);
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle onCancelled event
//                    Log.e("FirebaseData", "Error: " + error.getMessage());
//                }
//            });
//            SharedPreferences sharedPreferencese = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//// Retrieve data
//
//
//            String date = sharedPreferencese.getString("date", "");
//            String occas = sharedPreferencese.getString("occasion", "");
//            String eve = sharedPreferencese.getString("event", "");
//            String catg = sharedPreferencese.getString("category", "");
//            t3.setText("Event date:    " + date);
//            t5.setText("Occasion:      " + occas);
//            t4.setText("Event:           " + eve);
//            t2.setText("Category:              " + catg);
//        }
        }
        }



}
