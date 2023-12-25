package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yalantis.ucrop.UCrop;


import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity10 extends AppCompatActivity {

    TextView textView, name, phone, email;
    private static final int REQUEST_IMAGE_PICK = 1;
    private DrawerLayout drawerLayout;
    private ImageView edit, display;
    private Button button;
    private Toolbar toolbar;
    private Spinner spinner, state;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText dateEditText,address, city;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        drawerLayout = findViewById(R.id.draw_layout6);
        address = findViewById(R.id.editTextTextMultiLine);
        display = findViewById(R.id.imgview);
        button = findViewById(R.id.button7);
        city = findViewById(R.id.editTextText2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        state = findViewById(R.id.state);
        ArrayAdapter<CharSequence> adapterstate = ArrayAdapter.createFromResource(this, R.array.state_name, R.layout.my_selected_items);
        adapterstate.setDropDownViewResource(R.layout.my_drop_item);
        state.setAdapter(adapterstate);
        edit = findViewById(R.id.editlogo);
        edit.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });


        toolbar = findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);

        dateEditText = findViewById(R.id.editTextText4);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        email = findViewById(R.id.textView27);
        phone = findViewById(R.id.textView29);
        name = findViewById(R.id.textView31);

        Intent intent = getIntent();
        String username = intent.getStringExtra("keyname");
        String useremail = intent.getStringExtra("keymail");
        String userphone = intent.getStringExtra("keyphone");
        SharedPreferences shad = getSharedPreferences("email",MODE_PRIVATE);
        String usemail = shad.getString("usermail","");
        String uphone = shad.getString("userphone","");
        Log.d("MainActivity3", "Retrieved value from SharedPreferences: " + usemail);
        email.setText(usemail);
        phone.setText(uphone);
        NavigationView navigationView = findViewById(R.id.nav_view6);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        SharedPreferences sharedPreferencesf = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Retrieve data
        String namef = sharedPreferencesf.getString("Name", "");
        String emailf = sharedPreferencesf.getString("Email", "");
        String phoneNumber = sharedPreferencesf.getString("PhoneNumber", "");
        email.setText(emailf);
        phone.setText(phoneNumber);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();
                if (itemid == R.id.performer1) {
                    Intent intent = new Intent(MainActivity10.this, MainActivity4.class);
                    startActivity(intent);
                    return true;
                } else if (itemid == R.id.performer2) {
                    Intent intent = new Intent(MainActivity10.this, MainActivity5.class);
                    startActivity(intent);
                } else if (itemid == R.id.performer3) {
                    Intent intent = new Intent(MainActivity10.this, MainActivity6.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dob = dateEditText.getText().toString().trim();
                String addre = address.getText().toString().trim();
                String getstate = state.getSelectedItem().toString();
                String getcity = city.getText().toString();
                SharedPreferences profileshard = getSharedPreferences("profile",MODE_PRIVATE);
                SharedPreferences.Editor profedi = profileshard.edit();
                profedi.putString("dob",dob);
                profedi.putString("address",addre);
                profedi.putString("city",getcity);
                profedi.putString("state",getstate);
                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("state", getstate);
                hashmap.put("city", getcity);
                profedi.apply();

                Toast.makeText(MainActivity10.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
        SharedPreferences profileshard = getSharedPreferences("profile",MODE_PRIVATE);
        String date = profileshard.getString("dob","");
        String useraddress = profileshard.getString("address","");
        String getcty = profileshard.getString("city","");
        String getstat = profileshard.getString("state","");
        dateEditText.setText(date);
        address.setText(useraddress);
        city.setText(getcty);
//        state.setText(getstat);
        SharedPreferences sharedimg = getSharedPreferences("my_img", MODE_PRIVATE);
        String imagePath = sharedimg.getString("image_path", "");
        if (!imagePath.isEmpty()) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(imagePath);
            // Display the imageBitmap in an ImageView or wherever needed
            display.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                Uri croppedImageUri = Uri.fromFile(new File(getCacheDir(), "cropped_image.jpg"));

                UCrop.Options options = new UCrop.Options();
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                options.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                options.setStatusBarColor(getResources().getColor(com.google.android.material.R.color.design_default_color_background));
                options.setActiveControlsWidgetColor(getResources().getColor(R.color.divider_color));

                UCrop.of(imageUri, croppedImageUri)
                        .withOptions(options)
                        .start(MainActivity10.this);
            }
        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            if (data != null) {
                Uri croppedImageUri = UCrop.getOutput(data);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), croppedImageUri);
                    // Display the cropped image in an ImageView
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

// Save the byte array to a file (you can choose internal or external storage)
                    File directory = new File(getFilesDir(), "images"); // Internal storage
// File directory = new File(Environment.getExternalStorageDirectory(), "images"); // External storage
                    if (!directory.exists()) {
                        directory.mkdirs(); // Create the directory if it doesn't exist
                    }
                    String fileName = "my_image.png";
                    File imageFile = new File(directory, fileName);

                    try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                        fos.write(byteArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences sharedimg = getSharedPreferences("my_img", MODE_PRIVATE);
                    SharedPreferences.Editor imgeditor = sharedimg.edit();
                    imgeditor.putString("image_path", imageFile.getAbsolutePath()); // Store the image path
                    imgeditor.apply();
                    display.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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
}
