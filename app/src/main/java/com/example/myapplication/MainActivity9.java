package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity9 extends AppCompatActivity {
Button button;
EditText forgotemail;
String mail;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        button = findViewById(R.id.button6);
        forgotemail = findViewById(R.id.forgotemail);
        auth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data();
            }
        });
    }
    private void Data(){
        mail = forgotemail.getText().toString().trim();
        if (mail.isEmpty()){
            Toast.makeText(this, "Mail required", Toast.LENGTH_SHORT).show();
        }else{
            forgetpass();
        }
    }
    private void forgetpass(){
        auth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity9.this, "Check your mail", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity9.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity9.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}