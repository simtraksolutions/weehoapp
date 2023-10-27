package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity16 extends AppCompatActivity {
CardView cardView1,cardview2,cardView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        cardView1 = findViewById(R.id.dasboardMandeeepsingh);
        cardview2 = findViewById(R.id.cardViewrupalibhattacharjee);
        cardView3 = findViewById(R.id.cardViewNaviingandharv);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentM = new Intent(MainActivity16.this,MainActivity4.class);
                startActivity(intentM);
            }
        });
        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(MainActivity16.this,MainActivity5.class);
                startActivity(intentR);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentN = new Intent(MainActivity16.this,MainActivity6.class);
                startActivity(intentN);
            }
        });
    }
}