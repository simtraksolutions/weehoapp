package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity3 extends AppCompatActivity {
    private Button button;

    private String[] countryCodes = {"+1", "+91", "+44", "+86", "+81"};
    private String[] countryNames = {"USA", "India", "UK", "China", "Japan"};
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private EditText name,email,number,city,budget;
    private ImageView image;
    private Spinner spinner;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        AutoCompleteTextView autoCompleteTextViewCountryCode = findViewById(R.id.autoCompleteTextViewCountryCode);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Forms");
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        button=findViewById(R.id.button);
        name=findViewById(R.id.editTextText);
        email=findViewById(R.id.editTextTextEmailAddress);
        number=findViewById(R.id.editTextNumberDecimal);
        city=findViewById(R.id.editTextText2);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        budget=findViewById(R.id.editTextNumberDecimal3);
        image=findViewById(R.id.imageView2);

        int maxlength = 10;
        number.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlength)});

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
                            .setMessage("Please fill in all the required fields.")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (less_digiphone()==true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setTitle("Phone number")
                            .setMessage("Please enter valid 10-Digit phone number")
                            .setPositiveButton("OK",null)
                            .show();
                } else if (invalidemail()==false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setTitle("Email")
                            .setMessage("Please enter valid email address")
                            .setPositiveButton("OK",null)
                            .show();
                } else {

                    Toast.makeText(MainActivity3.this, "Your form has been submitted", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    number.setText("");
                    city.setText("");
                    budget.setText("");
                }




            }
        });

    }

    private boolean areFieldsEmpty() {
        String text1 = name.getText().toString().trim();
        String text2 = email.getText().toString().trim();
        String text3 = number.getText().toString().trim();
        String text4 = city.getText().toString().trim();

        String text6 = budget.getText().toString().trim();
        // Get the text from more EditText fields if added
        if (text1.isEmpty()){
            return text1.isEmpty();
        } else if (text2.isEmpty()) {
            return text2.isEmpty();
        } else if (text3.isEmpty()) {
            return text3.isEmpty();
        } else if (text4.isEmpty()) {
            return text4.isEmpty();
        } else if (text6.isEmpty()){
            return text6.isEmpty();
        }else {
            return text1.isEmpty() && text2.isEmpty() && text3.isEmpty() && text4.isEmpty() && text6.isEmpty(); // Add more conditions for additional fields
        }
    }
    private boolean less_digiphone(){
        String text7 = number.getText().toString().trim();
        if (text7.length()<10){
            return true;
        }
        return false;
    }
    private boolean invalidemail(){
        String mail = email.getText().toString();
        if (mail.contains("@gmail.com")){
            return true;
        }
        return false;
    }
    private void openUrl(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
