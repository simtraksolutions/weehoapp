package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity7 extends AppCompatActivity {
    Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    TextView displayphone, resend;
    ProgressBar progressBar;
    String verificationId;
    EditText num1, num2, num3, num4, num5, num6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Intent intent = getIntent();
        String useremail = intent.getStringExtra("keymail");
        String usermail1 = intent.getStringExtra("keymail1");
        String usercode = intent.getStringExtra("keycode");
        String userphone = intent.getStringExtra("keyphone");
        editor.putString("fmail", useremail);
        editor.putString("fphone", userphone);
        editor.commit();
        if(sharedPreferences.getString("islogin","false").equals("yes")){
            editor.putString("islogin","yes");
            editor.commit();
            startActivity(new Intent(MainActivity7.this,MainActivity12.class));

        }
        button = findViewById(R.id.button4);
        num1 = findViewById(R.id.input1);
        num2 = findViewById(R.id.input2);
        num3 = findViewById(R.id.input3);
        num4 = findViewById(R.id.input4);
        num5 = findViewById(R.id.input5);
        num6 = findViewById(R.id.input6);
        resend = findViewById(R.id.textresendotp);
        displayphone = findViewById(R.id.textView21);
        progressBar = findViewById(R.id.progressbar1);

        verificationId = getIntent().getStringExtra("verificationid");

        setupOTPinputs();
        displayphone.setText(usercode + " " + userphone);

        String savedEmail = sharedPreferences.getString("fmail", "");
        String savedPhone = sharedPreferences.getString("fphone", "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                if (isempty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please enter the otp.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    String code = num1.getText().toString().trim() + num2.getText().toString().trim() + num3.getText().toString().trim() +
                            num4.getText().toString().trim() + num5.getText().toString().trim() + num6.getText().toString().trim();

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                editor.putString("islogin","yes");
                                // Save email and phone number to shared preferences
                                editor.putString("fmail", useremail);
                                editor.putString("fphone", userphone);
                                editor.commit();
                                Intent intent = new Intent(MainActivity7.this, MainActivity12.class);
                                intent.putExtra("fmail",savedEmail);
                                intent.putExtra("fphone",savedPhone);
                                intent.putExtra("keymail", useremail);
                                intent.putExtra("keymail1", usermail1);
                                intent.putExtra("keyphone", userphone);
                                intent.putExtra("keycode", usercode);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity7.this, "The verification code entered is invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91 " + userphone, // Use the correct variable here
                        60,
                        TimeUnit.SECONDS,
                        MainActivity7.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity7.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.VISIBLE);
                                verificationId = newverificationid;
                                Toast.makeText(MainActivity7.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void setupOTPinputs() {
        // ... (No changes here)
        num1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    num2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    num3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    num4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    num5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    num6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isempty() {
        // ... (No changes here)
        String text1 = num1.getText().toString().trim();
        String text2 = num2.getText().toString().trim();
        String text3 = num3.getText().toString().trim();
        String text4 = num4.getText().toString().trim();
        String text5 = num5.getText().toString().trim();
        String text6 = num6.getText().toString().trim();
        if(text1.isEmpty()){
            return text1.isEmpty();
        } else if (text2.isEmpty()) {
            return  text2.isEmpty();
        }else if (text3.isEmpty()) {
            return  text3.isEmpty();
        }else if (text4.isEmpty()) {
            return  text4.isEmpty();
        }else if (text5.isEmpty()) {
            return  text5.isEmpty();
        }else if (text6.isEmpty()) {
            return  text6.isEmpty();
        }else {
            return text1.isEmpty() && text2.isEmpty() && text3.isEmpty() && text4.isEmpty() && text5.isEmpty() && text6.isEmpty();
        }
    }
}
