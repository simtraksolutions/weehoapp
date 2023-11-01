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
Button buttonr, buttonb,buttonreadfriends, buttonfriendsb, buttonreadcorparties, buttonbookcorparti,btnreadpers,btnbookpers,btnreadfam,btnbookfam,btnreadceleb,btnbookceleb,btnreadmice,btnbookmice;
ImageView sideup, sidedown,sideupf,sidedownf,sideupcorpa,sidedowncorpa,sideuppers,sidedownpers,sideupfam,sidedownfam,sideupceleb,sidedownceleb,sideupmice,sidedownmice;
CardView clickcard, showcard, clickcardf , showcardf,clickcardcorpa,showcardcorpa,clkcardpers,shcardpers,clkfam,shfam,clkceleb,shceleb,clkmice,shmice;
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
        clickcardf = findViewById(R.id.cardViewf);
        buttonreadfriends = findViewById(R.id.buttonreadmoref);
        buttonfriendsb = findViewById(R.id.eventbookf);
        sideupf = findViewById(R.id.imageViewf);
        sidedownf = findViewById(R.id.imageViewfriends);
        showcardf = findViewById(R.id.cardViewfp);
        clickcardcorpa = findViewById(R.id.cardViewC);
        showcardcorpa = findViewById(R.id.cardViewcorporateparties);
        buttonreadcorparties = findViewById(R.id.buttonreadmorecorp);
        buttonbookcorparti = findViewById(R.id.eventbookcorp);
        sideupcorpa = findViewById(R.id.imageViewc);
        sidedowncorpa = findViewById(R.id.imageViewcorpar);
        clkcardpers = findViewById(R.id.cardViewP);
        shcardpers = findViewById(R.id.cardViewpersonal);
        sideuppers = findViewById(R.id.imageViewp);
        sidedownpers = findViewById(R.id.imageViewpersoc);
        btnreadpers = findViewById(R.id.buttonreadpersonal);
        btnbookpers = findViewById(R.id.eventbookpersonal);
        clkfam = findViewById(R.id.cardViewfami);
        shfam = findViewById(R.id.cardViewfam);
        sideupfam = findViewById(R.id.imageViewfami);
        sidedownfam = findViewById(R.id.imageViewfamily);
        btnreadfam = findViewById(R.id.buttonreadfam);
        btnbookfam = findViewById(R.id.eventbookfam);
        clkceleb = findViewById(R.id.cardViewCOCELE);
        shceleb = findViewById(R.id.cardViewcocelebration);
        btnreadceleb = findViewById(R.id.buttonreadcocelebration);
        btnbookceleb = findViewById(R.id.eventbookcocelebration);
        sideupceleb = findViewById(R.id.imageViewcocele);
        sidedownceleb = findViewById(R.id.imageViewcocel);
        clkmice = findViewById(R.id.cardViewMICE);
        sideupmice = findViewById(R.id.imageViewmiceup);
        sidedownmice = findViewById(R.id.imageViewmice);
        shmice = findViewById(R.id.cardViewmice);
        btnreadmice = findViewById(R.id.buttonreadmice);
        btnbookmice = findViewById(R.id.eventbookmice);
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
        buttonr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity18.class);
                startActivity(intent);
            }
        });
        buttonreadfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity19.class);
                startActivity(intent);
            }
        });
        buttonreadcorparties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity20.class);
                startActivity(intent);
            }
        });
        btnreadpers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity21.class);
                startActivity(intent);
            }
        });
        btnreadfam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity22.class);
                startActivity(intent);
            }
        });
        btnreadceleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity23.class);
                startActivity(intent);
            }
        });
        btnreadmice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity24.class);
                startActivity(intent);
            }
        });
        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        buttonfriendsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        buttonbookcorparti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnbookpers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnbookfam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnbookceleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnbookmice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
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
        clickcardf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showcardf.getVisibility()==View.GONE){
                    showcardf.setVisibility(View.VISIBLE);
                    buttonreadfriends.setVisibility(View.VISIBLE);
                    buttonfriendsb.setVisibility(View.VISIBLE);
                    sideupf.setVisibility(View.VISIBLE);
                    sidedownf.setVisibility(View.GONE);
                }else{
                    showcardf.setVisibility(View.GONE);
                    buttonreadfriends.setVisibility(View.GONE);
                    buttonfriendsb.setVisibility(View.GONE);
                    sideupf.setVisibility(View.GONE);
                    sidedownf.setVisibility(View.VISIBLE);
                }
            }
        });
        clickcardcorpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showcardcorpa.getVisibility()==View.GONE){
                    showcardcorpa.setVisibility(View.VISIBLE);
                    buttonreadcorparties.setVisibility(View.VISIBLE);
                    buttonbookcorparti.setVisibility(View.VISIBLE);
                    sideupcorpa.setVisibility(View.VISIBLE);
                    sidedowncorpa.setVisibility(View.GONE);
                }else{
                    showcardcorpa.setVisibility(View.GONE);
                    buttonreadcorparties.setVisibility(View.GONE);
                    buttonbookcorparti.setVisibility(View.GONE);
                    sideupcorpa.setVisibility(View.GONE);
                    sidedowncorpa.setVisibility(View.VISIBLE);
                }
            }
        });
        clkcardpers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shcardpers.getVisibility()==View.GONE){
                    shcardpers.setVisibility(View.VISIBLE);
                    btnbookpers.setVisibility(View.VISIBLE);
                    btnreadpers.setVisibility(View.VISIBLE);
                    sidedownpers.setVisibility(View.GONE);
                    sideuppers.setVisibility(View.VISIBLE);
                }else{
                    shcardpers.setVisibility(View.GONE);
                    btnreadpers.setVisibility(View.GONE);
                    btnbookpers.setVisibility(View.GONE);
                    sideuppers.setVisibility(View.GONE);
                    sidedownpers.setVisibility(View.VISIBLE);
                }
            }
        });
        clkfam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shfam.getVisibility()==View.GONE){
                    shfam.setVisibility(View.VISIBLE);
                    btnbookfam.setVisibility(View.VISIBLE);
                    btnreadfam.setVisibility(View.VISIBLE);
                    sidedownfam.setVisibility(View.GONE);
                    sideupfam.setVisibility(View.VISIBLE);
                }else{
                    shfam.setVisibility(View.GONE);
                    btnreadfam.setVisibility(View.GONE);
                    btnbookfam.setVisibility(View.GONE);
                    sideupfam.setVisibility(View.GONE);
                    sidedownfam.setVisibility(View.VISIBLE);
                }
            }
        });
        clkceleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shceleb.getVisibility()==View.GONE){
                    shceleb.setVisibility(View.VISIBLE);
                    btnreadceleb.setVisibility(View.VISIBLE);
                    btnbookceleb.setVisibility(View.VISIBLE);
                    sidedownceleb.setVisibility(View.GONE);
                    sideupceleb.setVisibility(View.VISIBLE);
                }else{
                    shceleb.setVisibility(View.GONE);
                    btnbookceleb.setVisibility(View.GONE);
                    btnreadceleb.setVisibility(View.GONE);
                    sideupceleb.setVisibility(View.GONE);
                    sidedownceleb.setVisibility(View.VISIBLE);
                }
            }
        });
        clkmice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shmice.getVisibility()==View.GONE){
                    shmice.setVisibility(View.VISIBLE);
                    btnreadmice.setVisibility(View.VISIBLE);
                    btnbookmice.setVisibility(View.VISIBLE);
                    sidedownmice.setVisibility(View.GONE);
                    sideupmice.setVisibility(View.VISIBLE);
                }else{
                    shmice.setVisibility(View.GONE);
                    btnreadmice.setVisibility(View.GONE);
                    btnbookmice.setVisibility(View.GONE);
                    sideupmice.setVisibility(View.GONE);
                    sidedownmice.setVisibility(View.VISIBLE);
                }
            }
        });
    }



}