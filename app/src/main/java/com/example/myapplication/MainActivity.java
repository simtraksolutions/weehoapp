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


public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
private TextView showtext;
private DrawerLayout drawerLayout;
Button buttonr, buttonb;
ImageView sideup, sidedown;
CardView clickcard, showcard;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        clickcard = findViewById(R.id.cardView4);
        buttonr = findViewById(R.id.buttonreadmore);
        buttonb = findViewById(R.id.eventbook);
        showtext = findViewById(R.id.textView388);
        showcard = findViewById(R.id.cardViewS);
        sideup = findViewById(R.id.imageView29);
        sidedown = findViewById(R.id.imageView3);
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
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemid = item.getItemId();
                if(itemid == R.id.performer1){
                    Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity.this,MainActivity6.class);
                    startActivity(intent);
                } else if (itemid==R.id.about) {
                    Intent intent = new Intent(MainActivity.this,MainActivity1.class);
                    startActivity(intent);
                }
                return false;

            }
        });
        clickcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showcard.getVisibility()==View.GONE){
                    showcard.setVisibility(View.VISIBLE);
                    showtext.setVisibility(View.VISIBLE);
                    buttonr.setVisibility(View.VISIBLE);
                    buttonb.setVisibility(View.VISIBLE);
                    sideup.setVisibility(View.VISIBLE);
                    sidedown.setVisibility(View.GONE);
                }else{
                    showcard.setVisibility(View.GONE);
                    showtext.setVisibility(View.GONE);
                    buttonr.setVisibility(View.GONE);
                    sideup.setVisibility(View.GONE);
                    sidedown.setVisibility(View.VISIBLE);
                    buttonb.setVisibility(View.GONE);
                }

            }
        });
    }



}