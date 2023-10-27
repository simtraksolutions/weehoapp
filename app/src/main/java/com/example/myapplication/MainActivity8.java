package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity8 extends AppCompatActivity {
Button button;
String texte , textp;
FirebaseAuth auth;
TextView showpass,confirmshowpass;
EditText name,emailedittext,password,confirmpassword,Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        button=findViewById(R.id.button5);
        name=findViewById(R.id.editTextText3);
        showpass=findViewById(R.id.textView26);
        confirmshowpass=findViewById(R.id.showpass);
        Phone = findViewById(R.id.editTextphone);
        auth=FirebaseAuth.getInstance();
        emailedittext=findViewById(R.id.editTextTextEmailAddress3);
        emailedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail(s.toString());
            }
        });
        password=findViewById(R.id.editTextTextPassword2);
        confirmpassword=findViewById(R.id.editTextTextPassword3);
//        SharedPreferences shad = getSharedPreferences("email",MODE_PRIVATE);
//        SharedPreferences.Editor editorf = shad.edit();
//        editorf.putString("username","helooo");
//        editorf.apply();


        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getInputType()==144){
                    password.setInputType(129);
                    showpass.setText("Show");
                }
                else {
                    password.setInputType(144);
                    showpass.setText("Hide");
                }
                password.setSelection(password.getText().length());
            }
        });
        confirmshowpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmpassword.getInputType()==144){
                    confirmpassword.setInputType(129);
                    confirmshowpass.setText("Show");
                }
                else{
                    confirmpassword.setInputType(144);
                    confirmshowpass.setText("Hide");
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfieldempty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity8.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill in all the required fields.")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (isValidPhone()==false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity8.this);
                    builder.setTitle("Phone number")
                            .setMessage("Please Enter valid Phone number")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    
                    registeruser();


                }
            }
        });


    }

    private void registeruser() {
        texte = emailedittext.getText().toString().trim();
        textp = password.getText().toString().trim();
        auth.createUserWithEmailAndPassword(texte,textp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity8.this, "user created", Toast.LENGTH_SHORT).show();

                    String nameu = name.getText().toString();
                    String phoneu = Phone.getText().toString();
                    String emailu = emailedittext.getText().toString();
                    Intent iname =  new Intent(MainActivity8.this,MainActivity2.class);
                    iname.putExtra("nameuf",nameu);
                    iname.putExtra("phoneuf",phoneu);
                    iname.putExtra("emailuf",emailu);
                    // Inside MainActivity
                    SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferencesf.edit();

// Save data
                    editor.putString("Name", nameu);
                    editor.putString("Email", emailu);
                    editor.putString("PhoneNumber", phoneu);
                    editor.apply();
                    startActivity(iname);

                }else {
                    Toast.makeText(MainActivity8.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





    private boolean isfieldempty(){
        String text1=name.getText().toString().trim();
        String text2=emailedittext.getText().toString().trim();
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
    private void validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (!Pattern.matches(emailPattern, email)) {
            emailedittext.setError("Invalid email address");
        } else {
            emailedittext.setError(null); // Clear any previous error
        }
    }
    private boolean isValidPhone() {
        String phone = Phone.getText().toString().trim();
        return phone.length() == 10; // Assuming a valid phone has 10 digits
    }

}
