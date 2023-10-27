package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity13 extends AppCompatActivity {
    private Toolbar toolbar;
    ImageView profile;
    private EditText name, city, occasion,dateEditText;
    TextView number,email;
    Menu item;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DrawerLayout drawerLayout;
    private Button button;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        profile = findViewById(R.id.imageView17);
        name = findViewById(R.id.editTextText);
        email = findViewById(R.id.textView_email);
        number = findViewById(R.id.text_number);
        city = findViewById(R.id.editTextText2);
        occasion = findViewById(R.id.editTextNumberDecimal3);
        dateEditText = findViewById(R.id.dateEditText);
        Intent intent = getIntent();
        String useremail = intent.getStringExtra("keymail");
        String usermail1 = intent.getStringExtra("keymail1");
        String userphone = intent.getStringExtra("keyphone");
        SharedPreferences shad = getSharedPreferences("email",MODE_PRIVATE);
        String usemail = shad.getString("usermail","");
        String uphone = shad.getString("userphone","");
        Log.d("MainActivity3", "Retrieved value from SharedPreferences: " + usemail);
        number.setText(uphone);
        email.setText(usemail);
        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.button);
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
                    Intent intent = new Intent(MainActivity13.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity13.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity13.this,MainActivity6.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity13.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill the occasion field.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    String getname = name.getText().toString();
                    Intent intent1 = new Intent(MainActivity13.this , MainActivity17.class);
                    intent1.putExtra("databasename",getname);
                    startActivity(intent1);
                    String getcity = city.getText().toString();
                    String getnumber = number.getText().toString();
                    String getdate = dateEditText.getText().toString();
                    String getoccasion = occasion.getText().toString();

                    HashMap<String, Object> hashmap = new HashMap<>();
                    hashmap.put("name", getname);
                    hashmap.put("number", getnumber);
                    hashmap.put("city", getcity);
                    hashmap.put("date", getdate);
                    hashmap.put("occasion", getoccasion);


                    databaseReference.child("Users")
                            .child(getname)
                            .setValue(hashmap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(MainActivity13.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity13.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    Toast.makeText(MainActivity13.this, "Your form has been submitted", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    number.setText("");
                    city.setText("");
                    occasion.setText("");
                    dateEditText.setText("");
                }
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity13.this, MainActivity10.class);
                String username = name.getText().toString();
                intent.putExtra("keyname", username);
                intent.putExtra("keyphone", userphone);
                intent.putExtra("keymail", useremail);
                startActivity(intent);
            }
        });

    }
    private boolean areFieldsEmpty() {

        String text2 = name.getText().toString().trim();
        String text3 = city.getText().toString().trim();

        // Get the text from more EditText fields if added
        if (text2.isEmpty()) {
            return text2.isEmpty();
        } else if (text2.isEmpty()) {
            return text2.isEmpty();
        }else {
            return text2.isEmpty() && text3.isEmpty() ; // Add more conditions for additional fields
        }
    }
    public void showDatePickerDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the selected date in the EditText
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateEditText.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}