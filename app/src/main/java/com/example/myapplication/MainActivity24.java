package com.example.myapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainActivity24 extends AppCompatActivity {
Button miceeventbook;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);
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
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemid = item.getItemId();
                if(itemid == R.id.performer1){
                    Intent intent = new Intent(MainActivity24.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity24.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity24.this,MainActivity6.class);
                    startActivity(intent);
                } else if (itemid==R.id.about) {
                    Intent intent = new Intent(MainActivity24.this,MainActivity1.class);
                    startActivity(intent);
                }
                return false;

            }
        });
        miceeventbook = findViewById(R.id.micebookeventbtn);
        miceeventbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity24.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}