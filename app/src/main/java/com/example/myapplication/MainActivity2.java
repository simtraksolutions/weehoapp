package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.navigation.NavigationView;

import android.widget.DatePicker;
import java.util.Calendar;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class MainActivity2 extends AppCompatActivity {
    private SignInClient oneTapClient;
    private int RC_SIGN_IN=100;
    private String[] countryCodes = {"+1", "+91", "+44", "+86", "+81"};
    private Button buttonlogin;
    TextView account,forgot;
    FirebaseAuth auth;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    private EditText Email,Password,Phone,Code;
    String texte ,textp;
    GoogleSignInClient mGoogleSignInClient;
    private BeginSignInRequest signUpRequest;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonlogin=findViewById(R.id.button3);
        account = findViewById(R.id.textView3);
        forgot=findViewById(R.id.textView2);
        Email=findViewById(R.id.editTextTextEmailAddress2);
        Password=findViewById(R.id.editTextTextPassword);
        Phone=findViewById(R.id.editTextPhone2);
        Code = findViewById(R.id.autoCompleteTextViewCountryCode1);
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbarl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        drawerLayout = findViewById(R.id.drawer_layoutl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemid = item.getItemId();
                if(itemid == R.id.performer1){
                    Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid==R.id.performer2) {
                    Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                    startActivity(intent);
                } else if (itemid==R.id.performer3) {
                    Intent intent = new Intent(MainActivity2.this,MainActivity6.class);
                    startActivity(intent);
                }
                return false;

            }
        });
        ProgressBar progressBar = findViewById(R.id.progrssbar);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity8.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity9.class);
                startActivity(intent);
            }
        });
        int maxlength = 10;
        Phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlength)});
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
                }else{
                            vaildata();

                                    progressBar.setVisibility(View.VISIBLE);
                                    buttonlogin.setVisibility(View.INVISIBLE);
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91 "+Phone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                MainActivity2.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            progressBar.setVisibility(View.GONE);
                                            buttonlogin.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        buttonlogin.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String verificationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        buttonlogin.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(MainActivity2.this, MainActivity7.class);
                                        intent.putExtra("mobile",Phone.getText().toString());
                                        intent.putExtra("verificationid",verificationid);
                                        String userphone = Phone.getText().toString();
                                        String useremail = Email.getText().toString();
                                        String usercode = Code.getText().toString();
                                        intent.putExtra("keymail",useremail);
                                        intent.putExtra("keyphone",userphone);
                                        intent.putExtra("keycode",usercode);
                                        startActivity(intent);
                                    }
                                }
                        );


                }
            }
        });
    }


    private void vaildata() {
        texte = Email.getText().toString().trim();
        textp = Password.getText().toString().trim();
        if (texte.isEmpty() || textp.isEmpty()){
            Toast.makeText(this, "Please provide all fields.", Toast.LENGTH_SHORT).show();
        }else{
            user();
        }
    }



    private void user() {
        auth.signInWithEmailAndPassword(texte,textp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(MainActivity2.this,MainActivity7.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity2.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                String usermail1=personEmail.toString();
                intent.putExtra("keymail1",usermail1);
                startActivity(intent);
            }


        } catch (ApiException e) {

            Log.d("Message",e.toString());
        }
    }
    private boolean isempty(){
        String textE=Email.getText().toString().trim();
        String textP=Password.getText().toString().trim();
        String textPh=Phone.getText().toString().trim();
        if (textE.isEmpty()){
            return textE.isEmpty();
        } else if (textP.isEmpty()) {
            return textP.isEmpty();
        } else if (textPh.isEmpty()) {
                return textPh.isEmpty();
        } else{
            return textE.isEmpty() && textP.isEmpty() && textPh.isEmpty();
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