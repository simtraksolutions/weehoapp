package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.DatePickerDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.MainActivity10;
import com.example.myapplication.MainActivity11;
import com.example.myapplication.MainActivity4;
import com.example.myapplication.MainActivity5;
import com.example.myapplication.MainActivity6;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.DatePicker;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

public class MainActivity3 extends AppCompatActivity {
    private Button button;
    private EditText dateEditText;
    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private EditText name, city, occasion;

    private TextView email, number, quiz;
    private ImageView image, profile;
    private Spinner spinner;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        dateEditText = findViewById(R.id.dateEditText);

        number = findViewById(R.id.text_number);
        occasion = findViewById(R.id.editTextNumberDecimal3);

        quiz = findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity11.class);
                startActivity(intent);
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        drawerLayout = findViewById(R.id.draw_layout);
        profile = findViewById(R.id.imageView17);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        button = findViewById(R.id.button);
        name = findViewById(R.id.editTextText);
        email = findViewById(R.id.textView_email);
        Intent intent = getIntent();
        String useremail = intent.getStringExtra("keymail");
        String usermail1 = intent.getStringExtra("keymail1");
        String userphone = intent.getStringExtra("keyphone");
        if (useremail == null) {
            email.setText(usermail1);
            number.setText(userphone);
        } else {
            email.setText(useremail);
            number.setText(userphone);
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity10.class);
                String username = name.getText().toString();
                intent.putExtra("keyname", username);
                intent.putExtra("keyphone", userphone);
                intent.putExtra("keymail", useremail);
                startActivity(intent);
            }
        });

        city = findViewById(R.id.editTextText2);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();
                if (itemid == R.id.performer1) {
                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid == R.id.performer2) {
                    Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                    startActivity(intent);
                } else if (itemid == R.id.performer3) {
                    Intent intent = new Intent(MainActivity3.this, MainActivity6.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        image = findViewById(R.id.imageView2);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://simtrak.in/");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill the occasion field.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    String getname = name.getText().toString();
                    Intent intent1 = new Intent(MainActivity3.this , MainActivity17.class);
                    intent1.putExtra("databasename",getname);
                    startActivity(intent1);
                    String getcity = city.getText().toString();
                    String getevent = spinner.getSelectedItem().toString();
                    String getnumber = number.getText().toString();
                    String getdate = dateEditText.getText().toString();
                    String getoccasion = occasion.getText().toString();

                    HashMap<String, Object> hashmap = new HashMap<>();
                    hashmap.put("name", getname);
                    hashmap.put("number", getnumber);
                    hashmap.put("city", getcity);
                    hashmap.put("event", getevent);
                    hashmap.put("date", getdate);
                    hashmap.put("occasion", getoccasion);


                    databaseReference.child("Users")
                            .child(getname)
                            .setValue(hashmap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(MainActivity3.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity3.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    Toast.makeText(MainActivity3.this, "Your form has been submitted", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    city.setText("");
                    occasion.setText("");
                    dateEditText.setText("");
                }
            }
        });

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

    private boolean areFieldsEmpty() {
        String text6 = spinner.getSelectedItem().toString().trim();
        // Get the text from more EditText fields if added
        if (text6.isEmpty()) {
            return true;
        } else {
            return false; // Add more conditions for additional fields
        }
    }

    private void openUrl(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
