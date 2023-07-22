package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.yalantis.ucrop.UCrop;


import android.provider.MediaStore;
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


import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity10 extends AppCompatActivity {

    TextView textView, name, phone, email;
    private static final int REQUEST_IMAGE_PICK = 1;
    private DrawerLayout drawerLayout;
    private ImageView edit, display;
    private Button button;
    private Toolbar toolbar;
    private Spinner spinner;

    private EditText dateEditText;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        drawerLayout = findViewById(R.id.draw_layout6);
        display = findViewById(R.id.imgview);
        button = findViewById(R.id.button7);
        edit = findViewById(R.id.editlogo);
        edit.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });


        toolbar = findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);

        dateEditText = findViewById(R.id.dateEditText);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        email = findViewById(R.id.textView27);
        phone = findViewById(R.id.textView29);
        name = findViewById(R.id.textView31);
        Intent intent = getIntent();
        String username = intent.getStringExtra("keyname");
        String useremail = intent.getStringExtra("keymail");
        String userphone = intent.getStringExtra("keyphone");
        email.setText(useremail);
        phone.setText(userphone);
        name.setText(username);
        NavigationView navigationView = findViewById(R.id.nav_view6);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
