package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity15 extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private CardView  comedians, dancers, magicians, musicians;
    TextView actors,singers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.draw_layout);
        actors = findViewById(R.id.actors);
        comedians = findViewById(R.id.cardViewComedians);
        dancers = findViewById(R.id.cardViewdancer);
        singers = findViewById(R.id.singers);
        magicians = findViewById(R.id.cardViewmagicians);
        musicians = findViewById(R.id.cardViewMusicians);
        singers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity15.this, MainActivity16.class);
                startActivity(intent);
            }
        });
    }
}
