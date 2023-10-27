    package com.example.myapplication;

    import androidx.annotation.DrawableRes;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.ActionBarDrawerToggle;
    import androidx.appcompat.app.AppCompatActivity;
    import android.annotation.SuppressLint;
    import android.content.DialogInterface;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import androidx.appcompat.widget.Toolbar;
    import androidx.drawerlayout.widget.DrawerLayout;
    import android.app.DatePickerDialog;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;

    import com.example.myapplication.MainActivity10;
    import com.example.myapplication.MainActivity11;
    import com.example.myapplication.MainActivity4;
    import com.example.myapplication.MainActivity5;
    import com.example.myapplication.MainActivity6;
    import com.example.myapplication.R;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.material.navigation.NavigationView;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    import android.widget.DatePicker;
    import java.util.Calendar;
    import java.util.HashMap;

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

    public class MainActivity3 extends AppCompatActivity {
        private Button button;
        private EditText dateEditText;
        private Toolbar toolbar;
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle actionBarDrawerToggle;
        private EditText  city, occasion;


        private TextView name, email, number, quiz;
        private ImageView image, profile;
        private Spinner spinner ,state;
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
            number = findViewById(R.id.text_number);
            name = findViewById(R.id.editTextText);
            email = findViewById(R.id.textView_email);

            Intent intent = getIntent();
            String retrievedemail = intent.getStringExtra("mail1");
            String retrievedphone = intent.getStringExtra("phone1");
            String savedEmail = intent.getStringExtra("keymail");
            String savedPhone = intent.getStringExtra("keyphone");
            SharedPreferences sharedPreferences = getSharedPreferences("demo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mail",savedEmail);
            editor.putString("phone",savedPhone);
            editor.apply();
            SharedPreferences getsharedPreferences = getSharedPreferences("demo", MODE_PRIVATE);
            String saveEmailf  = getsharedPreferences.getString("mail","No email is stored");
            String savePhonef = getsharedPreferences.getString("phone","No phone is stored");
            SharedPreferences shad = getSharedPreferences("email",MODE_PRIVATE);
            String usemail = shad.getString("usermail","");
            String uphone = shad.getString("userphone","");
            Log.d("MainActivity3", "Retrieved value from SharedPreferences: " + usemail);
            // Inside MainActivity2
            SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data
            String namef = sharedPreferencesf.getString("Name", "");
            String emailf = sharedPreferencesf.getString("Email", "");
            String phoneNumber = sharedPreferencesf.getString("PhoneNumber", "");
            name.setText(namef);
            number.setText(phoneNumber);
            email.setText(emailf);

            if(sharedPreferences.getString("islogin","false").equals("false")){
                editor.putString("islogin","yes");
                editor.commit();
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
            dateEditText = findViewById(R.id.dateEditText);


            occasion = findViewById(R.id.editTextNumberDecimal3);


            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("");

            drawerLayout = findViewById(R.id.draw_layout);
            profile = findViewById(R.id.imageView17);


            button = findViewById(R.id.button);



            String useremail = intent.getStringExtra("keymail");

            String userphone = intent.getStringExtra("keyphone");
            // Retrieve saved email and phone number from shared preferences




            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity3.this, MainActivity10.class);
                    String username = name.getText().toString();
                    intent.putExtra("keyname", username);
                    intent.putExtra("keyphone", userphone);
                    intent.putExtra("keymail", useremail);
                    startActivity(intent);
                }
            });

            city = findViewById(R.id.editTextText2);
            setSupportActionBar(toolbar);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.bringToFront();

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemid = item.getItemId();
                    if (itemid == R.id.performer1) {
                        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                        startActivity(intent);
                        return true;
                    } else if (itemid == R.id.performer2) {
                        Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                        startActivity(intent);
                    } else if (itemid == R.id.performer3) {
                        Intent intent = new Intent(MainActivity3.this, MainActivity6.class);
                        startActivity(intent);
                    }
                    return false;
                }
            });

            spinner = findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, R.layout.my_selected_items);
            adapter.setDropDownViewResource(R.layout.my_drop_item);
            spinner.setAdapter(adapter);
            state = findViewById(R.id.state);
            ArrayAdapter<CharSequence> adapterstate = ArrayAdapter.createFromResource(this, R.array.state_name, R.layout.my_selected_items);
            adapterstate.setDropDownViewResource(R.layout.my_drop_item);
            state.setAdapter(adapterstate);
            image = findViewById(R.id.imageView2);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUrl("https://simtrak.in/");
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (areFieldsEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                        builder.setTitle("Empty Fields")
                                .setMessage("Please fill  all the fields.")
                                .setPositiveButton("OK", null)
                                .show();
                    } else {

                        editor.putString("islogin","yes");
                        editor.commit();

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                            builder.setTitle("Confirm Login")
                                    .setMessage("Do you want to log in with the provided credentials?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // User clicked "Yes," proceed with login
                                            dash();

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // User clicked "No," do not proceed with login
                                            Toast.makeText(MainActivity3.this, "Big prablem", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .show();
                        }
                    }

            });

        }



        public void showDatePickerDialog(View view) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            // Update the selected date in the EditText
                            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                            dateEditText.setText(selectedDate);
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.show();
        }

        private boolean areFieldsEmpty() {
            String[] dropdownItems = getResources().getStringArray(R.array.dropdown_items);
            String[] dropdownstateItems = getResources().getStringArray(R.array.state_name);
            String item1 = dropdownItems[0];
            String stateitem = dropdownstateItems[0];
            String text1 = name.getText().toString().trim();
            String text2 = state.getSelectedItem().toString().trim();
            String text3 = email.getText().toString().trim();
            String text4 = number.getText().toString().trim();
            String text5 = dateEditText.getText().toString().trim();
            String text6 = spinner.getSelectedItem().toString().trim();
            String text7 = occasion.getText().toString().trim();
            String text8 = city.getText().toString().trim();
            // Get the text from more EditText fields if added
            if (text1.isEmpty() || stateitem.equals(text2) || text3.isEmpty() || text4.isEmpty() || text5.isEmpty() || item1.equals(text6) || text7.isEmpty() || text8.isEmpty()) {
                return true;
            } else {
                return false; // Add more conditions for additional fields
            }
        }

        private void openUrl(String url) {
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        private void dash(){
            String getstate = state.getSelectedItem().toString();
            String getname = name.getText().toString();
            String getcity = city.getText().toString();
            String getevent = spinner.getSelectedItem().toString();
            String getnumber = number.getText().toString();
            String getdate = dateEditText.getText().toString();
            String getoccasion = occasion.getText().toString();
            String hv = "hello";
            String umail = email.getText().toString();
            Intent intent1  = new Intent(MainActivity3.this, MainActivity12.class);
            String rname = name.getText().toString();
            String fmail = email.getText().toString();
            intent1.putExtra("nam",rname);
            intent1.putExtra("fimail",fmail);
            intent1.putExtra("msg",hv);
            startActivity(intent1);
            finish();
            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("state", getstate);
            hashmap.put("name", getname);
            hashmap.put("number", getnumber);
            hashmap.put("city", getcity);
            hashmap.put("event", getevent);
            hashmap.put("date", getdate);
            hashmap.put("mail",umail);
            hashmap.put("occasion", getoccasion);

            databaseReference.child("Users")
                    .child(getname)
                    .setValue(hashmap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(MainActivity3.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity3.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            Toast.makeText(MainActivity3.this, "Your form has been submitted", Toast.LENGTH_SHORT).show();
            name.setText("");

            city.setText("");
            occasion.setText("");
            dateEditText.setText("");
        }
    }
