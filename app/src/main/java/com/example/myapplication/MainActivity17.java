package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity17 extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private TextView nameTextView,eventname,cityname,occasionname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        // Assuming you want to retrieve data for a specific user, you can pass the user's name to the activity.
        // For example, let's say you want to retrieve data for the user "John Doe":
        String userName = "urvrajsinh";

        // Add a ValueEventListener to retrieve the data for the specified user
        databaseReference.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method will be called whenever data at the specified database reference changes.
                // You can access the data from the dataSnapshot parameter.
                // For example, if you're retrieving the user's name, number, and city:
                String name = dataSnapshot.child("name").getValue(String.class);
                String number = dataSnapshot.child("number").getValue(String.class);
                String city = dataSnapshot.child("city").getValue(String.class);
                String event = dataSnapshot.child("event").getValue(String.class);
                String date = dataSnapshot.child("date").getValue(String.class);
                String occasion = dataSnapshot.child("occasion").getValue(String.class);

                // Now you can use this data as per your requirement, e.g., display it in TextViews.
                // For example:
                nameTextView = findViewById(R.id.nameTextView);
                nameTextView.setText(date);
                eventname = findViewById(R.id.eventname);
                eventname.setText(event);
                cityname = findViewById(R.id.cityname);
                cityname.setText(city);
                occasionname = findViewById(R.id.occasionname);
                occasionname.setText(occasion);

                // Similarly, you can set the other data in their respective TextViews.
                // You can also use this data for any other processing or calculations as needed.

                // NOTE: Make sure to handle cases where the dataSnapshot may be null or data doesn't exist.
                // You can add appropriate checks to avoid NullPointerExceptions.

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // This method will be triggered in case of any errors while retrieving data.
                Log.e("MainActivity17", "Error fetching data: " + databaseError.getMessage());
            }
        });
    }
}
