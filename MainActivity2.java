package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class MainActivity2 extends AppCompatActivity {
    private SignInClient oneTapClient;
    private int RC_SIGN_IN=100;
    private Button buttonlogin;
    private EditText Email,Password;
    GoogleSignInClient mGoogleSignInClient;
    private BeginSignInRequest signUpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonlogin=findViewById(R.id.button3);
        Email=findViewById(R.id.editTextTextEmailAddress2);
        Password=findViewById(R.id.editTextTextPassword);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)

                        .setServerClientId(getString(R.string.web_client_id))

                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isempty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Empty Fields")
                            .setMessage("Please fill in all the required fields.")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (isvalidE()==false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Email address")
                            .setMessage("Please enter valid email address.")
                            .setPositiveButton("OK",null)
                            .show();
                } else {
                        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                        startActivity(intent);
                }
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(this, ":User email : "+personEmail, Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(MainActivity2.this, MainActivity3.class));


        } catch (ApiException e) {

            Log.d("Message",e.toString());
        }
    }
    private boolean isempty(){
        String textE=Email.getText().toString().trim();
        String textP=Password.getText().toString().trim();
        if (textE.isEmpty()){
            return textE.isEmpty();
        } else if (textP.isEmpty()) {
            return textP.isEmpty();
        }else{
            return textE.isEmpty() && textP.isEmpty();
        }
    }
    private boolean isvalidE(){
        String isE = Email.getText().toString().trim();
        if (isE.contains("@gmail.com")){
            return true;
        }
        return false;
    }

}