package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity14 extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText name,message;
    TextView number,email;
    private Button button;
    Menu item;
    private DrawerLayout drawerLayout;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        name = findViewById(R.id.editTextText);
        message = findViewById(R.id.editTextTextMultiLine);
        email = findViewById(R.id.textView_email);
        number = findViewById(R.id.text_number);
        button = findViewById(R.id.button);
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        drawerLayout = findViewById(R.id.draw_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
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
                    Intent intent = new Intent(MainActivity14.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity14.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity14.this,MainActivity6.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity14.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill the occasion field.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    String getname = name.getText().toString();
                    Intent intent1 = new Intent(MainActivity14.this , MainActivity17.class);
                    intent1.putExtra("databasename",getname);
                    startActivity(intent1);
                    String getmessage = message.getText().toString();
                    String getnumber = number.getText().toString();
                    String getemail = email.getText().toString();


                    HashMap<String, Object> hashmap = new HashMap<>();
                    hashmap.put("name", getname);
                    hashmap.put("number", getnumber);
                    hashmap.put("message", getmessage);
                    hashmap.put("email",getemail);


                    databaseReference.child("Users")
                            .child(getname)
                            .setValue(hashmap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(MainActivity14.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity14.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    Toast.makeText(MainActivity14.this, "Your form has been submitted", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    number.setText("");
                    email.setText("");
                    message.setText("");

                }
            }

        });

    }
    private boolean areFieldsEmpty() {

        String text2 = name.getText().toString().trim();


        // Get the text from more EditText fields if added
        if (text2.isEmpty()) {
            return text2.isEmpty();
        } else {
            return text2.isEmpty() ; // Add more conditions for additional fields
        }
    }
}