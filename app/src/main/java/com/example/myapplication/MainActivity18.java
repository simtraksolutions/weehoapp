package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
public class MainActivity18 extends AppCompatActivity {
    Button birthday_book_event_btn;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        drawerLayout = findViewById(R.id.draw_layout);
        birthday_book_event_btn = findViewById(R.id.birthdaybookeventbtn);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemid = item.getItemId();
                if(itemid == R.id.performer1){
                    Intent intent = new Intent(MainActivity18.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity18.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity18.this,MainActivity6.class);
                    startActivity(intent);
                } else if (itemid==R.id.about) {
                    Intent intent = new Intent(MainActivity18.this,MainActivity1.class);
                    startActivity(intent);
                }
                return false;

            }
        });
        toggle.syncState();
        birthday_book_event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity18.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}