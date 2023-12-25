package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity2 extends AppCompatActivity {
    private Button buttonlogin;
    private SharedPreferences sharedPreferences;
    CardView phonenumbercard;
    private SharedPreferences.Editor editor;
    private static final int RC_SIGN_IN = 100;
    private boolean userConfirmed ; // Initialize it to false initially
    private boolean userConfirmedp = false; // Initialize it to false initially

    private GoogleSignInClient mGoogleSignInClient;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private EditText Email, Password, Phone, Code;
    TextView account,forgot,showpass,phonetext,emailtext;

    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        account = findViewById(R.id.textViewsign);
        Email = findViewById(R.id.editTextTextEmailAddress2);
        Password = findViewById(R.id.editTextTextPassword);
        Phone = findViewById(R.id.editTextPhone2);
        progressBar = findViewById(R.id.progressbar1);
        phonenumbercard = findViewById(R.id.cardView8);
        GradientDrawable customDrawable = new GradientDrawable();
        customDrawable.setColor(Color.parseColor("#F44336"));
        Intent iname = getIntent();
        String nameu = iname.getStringExtra("nameuf");
        String phoneu = iname.getStringExtra("phoneuf");
        String emailu = iname.getStringExtra("emailuf");
        String userp = Phone.getText().toString().trim();
        String pass = Password.getText().toString().trim();
//        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Handle toggle button state change here
//                if (isChecked) {
//                    phonenumbercard.setVisibility(View.VISIBLE);
//                    emailtext.setBackgroundColor(colorwhite);
//                    emailtext.setTextColor(colorblack);
//                    toggleButton.setThumbDrawable(getResources().getDrawable(R.drawable.your_thumb_drawable));
//                    phonetext.setTextColor(colorwhite);
//                    phonetext.setBackgroundColor(color);
//                    Email.setError(null);
//
//                    if(Phone.getText().toString().trim().isEmpty()){
//                        showErrorDialog("Enter Phone Number", "Please enter a phone number.");
//                    }else if (!isValidPhone()) {
//                        showErrorDialog("Invalid Phone Number", "Please enter a valid phone number.");
//                    }
//                } else {
//
//                    phonenumbercard.setVisibility(View.GONE);
//                    emailtext.setBackgroundColor(color);
//                    phonetext.setBackgroundColor(colorwhite);
//                    toggleButton.setThumbDrawable(getResources().getDrawable(R.drawable.your_thumb_disable ));
//                    phonetext.setTextColor(colorblack);
//                    emailtext.setTextColor(colorwhite);
//
//                }
//            }
//        });
        String text = Email.getText().toString();
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isEmail(text)) {//do your stuff}
                    if (isPhone(text)) {//do your stuff}
                    }
                }
            }
        });

        Email.addTextChangedListener(new TextWatcher() {
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

        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("islogin","false").equals("yes")){
            editor.putString("islogin","yes");
            String usermail = Email.getText().toString();
            String userphone = Phone.getText().toString();
            editor.putString("fmail",usermail);
            editor.putString("fphone",userphone);
            editor.commit();
            SharedPreferences getsharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            String retrievedEmail = sharedPreferences.getString("fmail", "defaultEmail");
            String retrievedPhone = sharedPreferences.getString("fphone", "defaultPhone");

            Intent intentE = new Intent(MainActivity2.this, MainActivity12.class);
            intentE.putExtra("mail1",retrievedEmail);
            intentE.putExtra("phone1",retrievedPhone);
            startActivity(intentE);
        }



        showpass = findViewById(R.id.textView26);
        Drawable menuIcon = getResources().getDrawable(R.drawable.menu_icon);





        SharedPreferences shad = getSharedPreferences("email", MODE_PRIVATE);
        String usemail = shad.getString("usermail", "");
        Log.d("MainActivity3", "Retrieved value from SharedPreferences: " + usemail);

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Password.getInputType()==144){
                    Password.setInputType(129);
                    showpass.setText("Show");
                }else{
                    Password.setInputType(144);
                    showpass.setText("Hide");
                }
                Password.setSelection(Password.getText().length());
            }
        });


        initializeViews();


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String finaltext = Email.getText().toString().trim();
                String password  = Password.getText().toString().trim();
                if (isEmail(finaltext)) {
                        if ((validateEmail(finaltext)==false && password.isEmpty()) || (finaltext.isEmpty() || password.isEmpty()) ){
                            showErrorDialog("Empty Fields", "Please fill in all the required fields.");
                        }
                            else {
                                String email = Email.getText().toString().trim();
                                String userphone = Phone.getText().toString().trim();
//                                if ((email.isEmpty() && password.isEmpty()) || (email.isEmpty() || password.isEmpty())){
//                                    showErrorDialog("Enter Details", "Please enter all fields");
//                                }else {
                                    auth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Authentication successful, user is signed in.
                                                        FirebaseUser user = auth.getCurrentUser();
                                                        Toast.makeText(MainActivity2.this, "loadingggggg", Toast.LENGTH_SHORT).show();
                                                        showConfirmationDialog(email, password);
                                                        // You can do further actions here, such as navigating to another activity.
                                                    } else {
                                                        // Authentication failed, handle the error.
                                                        showErrorDialog("Authentication Failed", "Invalid email or password.");
                                                    }

                                                }
                                            });
//                            }
                    }
                } else if(isPhone(finaltext)) {
                    if(isValidPhone(finaltext)){
                        showConfirmationDialogP(userp,pass);
                        progressBar.setVisibility(View.VISIBLE);
                        SharedPreferences shad = getSharedPreferences("email", MODE_PRIVATE);
                        SharedPreferences.Editor editorf = shad.edit();
                        String finalphone = Phone.getText().toString();
                        String finalemail = Email.getText().toString();
                        String h1 = phoneu;
                        String h2 = emailu;
                        editorf.putString("usermail",h1);
                        editorf.putString("userphone", h2);
                        editorf.apply();

                        Log.d("MainActivity2", "Value helloooo set in SharedPreferences: " );

                        Toast.makeText(MainActivity2.this, "Loading...", Toast.LENGTH_SHORT).show();
                    }else{
                        showErrorDialog("Invalid Input", "Please enter a valid phone number.");
                    }
                }
                else {
                    showErrorDialog("Invalid Input", "Please enter a valid email address or phone number.");
                }
            }
        });

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

    }

            private void initializeViews() {
        buttonlogin = findViewById(R.id.button3);


        forgot = findViewById(R.id.textView2);
        Code = findViewById(R.id.autoCompleteTextViewCountryCode1);
        auth = FirebaseAuth.getInstance();

    }
    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void validateData() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        String userphone = Email.getText().toString();

        SharedPreferences sharedPreferencesem = getSharedPreferences("MyPrefs_e", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesem.edit();

// Save data
        editor.putString("finaldbem", userphone);
        editor.apply();
        // Send verification code and authenticate with Firebase
        sendVerificationCode("+91" + userphone, email, password);


    }

    private void sendVerificationCode(String phoneNumber, String email, String password) {
        progressBar.setVisibility(View.GONE);
        buttonlogin.setVisibility(View.INVISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // Verification completed, now authenticate with Firebase
                        progressBar.setVisibility(View.VISIBLE);
                        buttonlogin.setVisibility(View.INVISIBLE);
                        String userphone = Email.getText().toString();
                        authenticateWithFirebase(email, password,userphone);
                        showConfirmationDialogP(userphone,password);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        // Verification process failed
                        progressBar.setVisibility(View.GONE);
                        buttonlogin.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        showErrorDialog("Verification Failed", "Phone number verification failed.");
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // Code sent to user's phone, store the verification ID for later use
                        progressBar.setVisibility(View.VISIBLE);
                        buttonlogin.setVisibility(View.INVISIBLE);
                        Intent iname = getIntent();
                        String nameu = iname.getStringExtra("nameuf");
                        String phoneu = iname.getStringExtra("phoneuf");
                        String emailu = iname.getStringExtra("emailuf");
                        String email = Email.getText().toString();
                        String usercode = Code.getText().toString();
                        String userphone = Email.getText().toString();
                        Intent intent = new Intent(MainActivity2.this, MainActivity7.class);
                        intent.putExtra("verificationid", verificationId);
                        intent.putExtra("keymail",emailu);
                        intent.putExtra("keyphone", userphone);
                        Log.d("MainActivity2","sent emailu is "+emailu);
                        Log.d("MainActivity2","sent name is "+phoneu);
                        intent.putExtra("keycode", usercode);
                        startActivity(intent);
                    }
                }
        );
    }

    private void authenticateWithFirebase(String email, String password ,String userphone) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Authentication successful, proceed to the next screen
                            // You can start your MainActivity here
                            sendVerificationCode("+91" + userphone, email, password);

                        } else {
                            // Authentication failed, show error dialog
                            showErrorDialog("Authentication Error", "Invalid email or password.");
                        }

                        progressBar.setVisibility(View.GONE);
                        buttonlogin.setVisibility(View.VISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle authentication failure
                        showErrorDialog("Authentication Error", "An error occurred during authentication.");
                        progressBar.setVisibility(View.GONE);
                        buttonlogin.setVisibility(View.VISIBLE);
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                // You can use account to get user details or proceed to the next screen
                String personEmail = account.getEmail();
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("keymail1", personEmail);
                startActivity(intent);
            }
        } catch (ApiException e) {
            // Handle Google sign-in failure
            showErrorDialog("Google Sign-In Error", "Google sign-in failed.");
        }
    }


    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private boolean isEmpty() {
        return Email.getText().toString().trim().isEmpty() &&
                Password.getText().toString().trim().isEmpty() ;

    }



    private boolean isValidPhone(String phonenumber) {
        String phone = "^[0-9]{10}$";
        if (!Pattern.matches(phone, phonenumber)){
            Email.setError("Invalid phone number (must contain 10 digits)");
            return false;
        }else{
            Email.setError(null); // Clear any previous error
            return true;
        }
    }
    private boolean validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (!Pattern.matches(emailPattern, email)) {
            Phone.setError("Invalid email address");
            return false;
        } else {
            Phone.setError(null); // Clear any previous error
            return true;
        }
    }
    private void showConfirmationDialog(String email, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Login")
                .setMessage("Do you want to log in with the provided credentials?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "Yes," proceed with login

                        auth.signInWithEmailAndPassword(email,password);
                        progressBar.setVisibility(View.VISIBLE);

                        buttonlogin.setVisibility(View.INVISIBLE);
                        String dbem = email.toString();
                        Intent intentdbem = new Intent(MainActivity2.this, MainActivity12.class);
                        intentdbem.putExtra("em",dbem);
                        Log.d("MainActivity2m", "Retrieved value from SharedPreferences: mail" + dbem);
                        String finalText = Email.getText().toString().trim();
                        Dataholder.getInstance().setFinalText(finalText);
                        SharedPreferences sharedPreferencesem = getSharedPreferences("MyPrefs_e", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesem.edit();

// Save data
                        editor.putString("finaldbem", finalText);
                        editor.apply();
                        startActivity(intentdbem);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "No," do not proceed with login
                        userConfirmed = false;
                    }
                })
                .show();
    }
    private void showConfirmationDialogP(String userphone, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Confirm Login")
                .setMessage("Do you want to log in with the provided credentials?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "Yes," proceed with login
                       validateData();
                        progressBar.setVisibility(View.VISIBLE);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "No," do not proceed with login
                        Toast.makeText(MainActivity2.this, "Big prablem", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
            public static boolean isEmail(String text) {
                String expression = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(text);
                return m.matches();
            }

            public static boolean isPhone(String text) {
                return text.matches("[0-9]+");
            }
}
