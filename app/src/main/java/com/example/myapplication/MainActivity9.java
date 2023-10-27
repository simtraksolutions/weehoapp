package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class MainActivity9 extends AppCompatActivity {
    private Button button;
    private EditText forgotEmail;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        button = findViewById(R.id.button6);
        forgotEmail = findViewById(R.id.forgotemail);
        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = forgotEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, R.string.email_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, R.string.invalid_email_format, Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity9.this, R.string.reset_email_sent, Toast.LENGTH_SHORT).show();
                    navigateToMainActivity2();
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(MainActivity9.this, R.string.email_not_found, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity9.this, R.string.reset_email_failed, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void navigateToMainActivity2() {
        Intent intent = new Intent(MainActivity9.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
}
