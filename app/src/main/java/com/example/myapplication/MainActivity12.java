package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity12 extends AppCompatActivity {
    LinearLayout layout;
    CardView bookanevent, logout, createanevent, contact, performers,history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        layout = findViewById(R.id.dashboard);
        layout.setBackgroundResource(R.drawable.background1);

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("keymail");
        String usermail1 = intent.getStringExtra("keymail1");
        String usercode = intent.getStringExtra("keycode");
        String userphone = intent.getStringExtra("keyphone");

        bookanevent = findViewById(R.id.dasboardbookanevent);
        performers = findViewById(R.id.cardView15);
        logout = findViewById(R.id.cardView14);
        createanevent = findViewById(R.id.cardView10);
        history = findViewById(R.id.cardView12);
        contact = findViewById(R.id.cardView13);

        bookanevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity3.class);
                intent.putExtra("keymail", useremail);
                intent.putExtra("keymail1", usermail1);
                intent.putExtra("keyphone", userphone);
                intent.putExtra("keycode", usercode);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this,MainActivity17.class);
                startActivity(intent);
            }
        });
        createanevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity13.class);
                startActivity(intent);
            }
        });

        performers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity15.class);
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity14.class);
                startActivity(intent);
            }
        });
    }
}
