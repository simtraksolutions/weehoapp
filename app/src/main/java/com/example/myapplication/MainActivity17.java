package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity17 extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private static final String HISTORY_PREFS_NAME = "HistoryPrefs";
    private static final String HISTORY_KEY = "HistoryList";

    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> historyList;
    private FirebaseDatabase firebaseDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Get the values for each user using userSnapshot instead of dataSnapshot
                    String username = userSnapshot.child("name").getValue(String.class);
                    String email = userSnapshot.child("number").getValue(String.class);
                    saveHistory(username);
                    saveHistory(email);
                    saveHistory("qawsedrft");
                    saveHistory("plokijuhyg");
                    saveHistory("mknjbhvgcf");
                    saveHistory("uj12334");
                    saveHistory("123456");

                    // Do something with the data, e.g., display it in the log
                    Log.d("MainActivity", "Username: " + username + ", Email: " + email);
                }
                saveHistory("uj12334");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                saveHistory("uj097");
            }
        });


        String uj = "Urvrajsinh123456";
        saveHistory(uj);
        saveHistory("jadeja");
        saveHistory("ujklp");

        historyListView = findViewById(R.id.historyListView);
        historyList = loadHistory();

        // Set up the adapter to display the historyList
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(historyAdapter);
    }

    private ArrayList<String> loadHistory() {
        SharedPreferences historyPrefs = getSharedPreferences(HISTORY_PREFS_NAME, MODE_PRIVATE);
        Set<String> historySet = historyPrefs.getStringSet(HISTORY_KEY, new HashSet<>());
        return new ArrayList<>(historySet);
    }
    private void saveHistory(String historyItem) {
        SharedPreferences historyPrefs = getSharedPreferences(HISTORY_PREFS_NAME, MODE_PRIVATE);
        Set<String> historySet = historyPrefs.getStringSet(HISTORY_KEY, new HashSet<>());
        historySet.add(historyItem);

        // Save the updated set back to SharedPreferences
        SharedPreferences.Editor editor = historyPrefs.edit();
        editor.putStringSet(HISTORY_KEY, historySet);
        editor.apply();
    }

}

//package com.example.myapplication;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MainActivity17 extends AppCompatActivity {
//    private DatabaseReference databaseReference;
//    private TextView nameTextView, eventname, cityname, occasionname;
//    private ConstraintLayout constraintLayout;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main17);
//
//        constraintLayout = findViewById(R.id.constraint);
//        nameTextView = findViewById(R.id.nameTextView);
//        eventname = findViewById(R.id.eventname);
//        cityname = findViewById(R.id.cityname);
//        occasionname = findViewById(R.id.occasionname);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        Intent intent1 = getIntent();
//        if (intent1.hasExtra("databasename") || intent1.hasExtra("")) {
//
//
//            databaseReference.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    // Your existing onDataChange code here
//                    String name = dataSnapshot.child("name").getValue(String.class);
//                    String number = dataSnapshot.child("number").getValue(String.class);
//                    String city = dataSnapshot.child("city").getValue(String.class);
//                    String event = dataSnapshot.child("event").getValue(String.class);
//                    String date = dataSnapshot.child("date").getValue(String.class);
//                    String occasion = dataSnapshot.child("occasion").getValue(String.class);
//
//                    // Now you can use this data as per your requirement, e.g., display it in TextViews.
//                    // For example:
//                    nameTextView = findViewById(R.id.nameTextView);
//                    nameTextView.setText(date);
//                    eventname = findViewById(R.id.eventname);
//                    eventname.setText(event);
//                    cityname = findViewById(R.id.cityname);
//                    cityname.setText(city);
//                    occasionname = findViewById(R.id.occasionname);
//                    occasionname.setText(occasion);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Log.e("MainActivity17", "Error fetching data: " + databaseError.getMessage());
//                }
//            });
//
//        } else {
//            // Handle case where intent extra is missing or null
//            nameTextView = findViewById(R.id.nameTextView);
//            nameTextView.setText("date");
//            eventname = findViewById(R.id.eventname);
//            eventname.setText("event");
//            cityname = findViewById(R.id.cityname);
//            cityname.setText("city");
//            occasionname = findViewById(R.id.occasionname);
//            occasionname.setText("occasion");
//            Log.e("MainActivity17", "Intent extra 'databasename' is missing or null.");
//        }
//    }
//}
