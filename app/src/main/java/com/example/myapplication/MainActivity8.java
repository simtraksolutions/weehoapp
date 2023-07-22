package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity8 extends AppCompatActivity {
Button button;
String texte , textp;
FirebaseAuth auth;
EditText name,email,password,confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        button=findViewById(R.id.button5);
        name=findViewById(R.id.editTextText3);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.editTextTextEmailAddress3);
        password=findViewById(R.id.editTextTextPassword2);
        confirmpassword=findViewById(R.id.editTextTextPassword3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfieldempty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity8.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill in all the required fields.")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (isevalid()==false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity8.this);
                    builder.setTitle("Email address")
                            .setMessage("Please enter valid email address.")
                            .setPositiveButton("OK",null)
                            .show();
                }else {
                    
                    registeruser();

                }
            }
        });

    }

    private void registeruser() {
        texte = email.getText().toString().trim();
        textp = password.getText().toString().trim();
        auth.createUserWithEmailAndPassword(texte,textp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity8.this, "user created", Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(MainActivity8.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity8.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private  boolean isevalid(){
        String iSEM=email.getText().toString().trim();
        if (iSEM.contains("@gmail.com")){
            return true;
        }
        return false;
    }
    private boolean isfieldempty(){
        String text1=name.getText().toString().trim();
        String text2=email.getText().toString().trim();
        String text3=password.getText().toString().trim();
        String text4=confirmpassword.getText().toString().trim();
        if (text1.isEmpty()){
            return text1.isEmpty();
        } else if (text2.isEmpty()) {
            return  text2.isEmpty();
        } else if (text3.isEmpty()) {
            return text3.isEmpty();
        } else if (text4.isEmpty()) {
            return text4.isEmpty();
        }
        return text1.isEmpty() && text2.isEmpty() && text3.isEmpty() && text4.isEmpty() ;
    }
}