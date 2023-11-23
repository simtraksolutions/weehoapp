
    package com.example.myapplication;
    import androidx.annotation.DrawableRes;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.ActionBarDrawerToggle;
    import androidx.appcompat.app.AppCompatActivity;
    import android.annotation.SuppressLint;
    import android.app.TimePickerDialog;
    import android.content.DialogInterface;
    import android.content.SharedPreferences;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import androidx.appcompat.widget.Toolbar;
    import androidx.drawerlayout.widget.DrawerLayout;
    import android.app.DatePickerDialog;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import papaya.in.sendmail.SendMail;
    import papaya.in.sendmail.Config;
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

    import android.widget.AdapterView;
    import android.widget.DatePicker;
    import java.util.Calendar;
    import java.util.HashMap;
    import java.util.Properties;

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

    import javax.mail.Message;
    import javax.mail.MessagingException;
    import javax.mail.PasswordAuthentication;
    import javax.mail.Session;
    import javax.mail.Transport;
    import javax.mail.internet.AddressException;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeMessage;

    public class MainActivity3 extends AppCompatActivity {
        private Button button;
        private EditText dateEditText;
        private Toolbar toolbar;
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle actionBarDrawerToggle;
        private EditText  occasion;
        private Spinner dateSelectorSpinner;
        private ArrayAdapter<CharSequence> adapter123;

        private TextView name, email, number, quiz;
        private ImageView image, profile;
        private Spinner spinner,category_performer ;
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
            dateSelectorSpinner = findViewById(R.id.dateSelectorSpinner);
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
            category_performer = findViewById(R.id.spinnercate);
            ArrayAdapter<CharSequence> adapterper = ArrayAdapter.createFromResource(this, R.array.dropcategory, R.layout.my_selected_items);
            adapterper.setDropDownViewResource(R.layout.my_drop_item);
            category_performer.setAdapter(adapterper);
            category_performer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (position == 1) { // Check if "Personal Parties" is selected
                        showStateDropdown(); // Call a method to show the state dropdown

                    } else {
                        // Hide the state dropdown or handle other selections if needed
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Handle no selection
                }
            });

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

//                                            sendEmail();
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

            });adapter123 = ArrayAdapter.createFromResource(this,
                    R.array.date_selection_options, android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears
            adapter123.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Apply the adapter to the spinner
            dateSelectorSpinner.setAdapter(adapter123);

            dateSelectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    handleSelection(selectedItem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });
        }




        private void handleSelection(String selectedItem) {
            if (selectedItem.equals("Specific")) {
                showDatePickerDialog();
            } else if (selectedItem.equals("Range")) {
                showDateRangePickerDialog();
            } else if (selectedItem.equals("Not Specific")) {
                // Implement logic for not specific selection
                Toast.makeText(this, "Not Specific selected", Toast.LENGTH_SHORT).show();
            }
        }
        private void sendEmail() {
            new SendMailTask().execute();
        }

        private class SendMailTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                final String senderEmail = "urvrajsinh.jadeja113724@marwadiuniversity.ac.in";
                final String senderPassword = "Bndc@761";
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com"); // Your SMTP host
                props.put("mail.smtp.port", "465"); // Your SMTP port

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(senderEmail, senderPassword);
                            }
                        });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderEmail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jadejaurvrajsinh@gmail.com"));
                    message.setSubject("Test Email");
                    message.setText("This is a test email sent from my Android app.");

                    Transport.send(message);

                    Log.d("SendMailTask", "Email sent successfully!");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    Log.e("SendMailTask", "Failed to send email: " + e.getMessage());

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Handle success or failure, update UI if needed
            }
        }
        private void bookEvent() {
            // Perform booking logic...

            // Send emails to user and admin
            String userEmailAddress = "user@example.com";
            String adminEmailAddress = "admin@example.com";
            String eventSubject = "Event Booking Confirmation";
            String eventMessage = "Your event has been successfully booked.";

//            // Send emails using the EmailSender class or method mentioned earlier
//            EmailSender.sendEmail(userEmailAddress, eventSubject, eventMessage);
//            EmailSender.sendEmail(adminEmailAddress, eventSubject, eventMessage);
        }
        private void showDateRangePickerDialog() {
            Calendar calendar = Calendar.getInstance();
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            int startDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog startDatePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // Handle selected start date here
                        String startDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(this, "Start Date: " + startDate, Toast.LENGTH_SHORT).show();

                        // Show dialog for selecting end date after selecting start date
                        showEndDatePickerDialog(year, monthOfYear, dayOfMonth);
                    }, startYear, startMonth, startDayOfMonth);

            startDatePickerDialog.show();
        }

        private void showDatePickerDialog() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, monthOfYear, dayOfMonth1) -> {
                        // Handle selected date here
                        String selectedDate = dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1;
                        Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();

                        // Show TimePickerDialog after selecting the date
                        showTimePickerDialog();
                    }, year, month, dayOfMonth);

            datePickerDialog.show();
        }

        private void showTimePickerDialog() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute1) -> {
                        // Handle selected time here
                        String selectedTime = hourOfDay + ":" + minute1;
                        Toast.makeText(this, "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show();
                        // You can use selectedDate and selectedTime for further processing
                    }, hour, minute, false);

            timePickerDialog.show();
        }


        private void showEndDatePickerDialog(int startYear, int startMonth, int startDayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            int endDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog endDatePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // Handle selected end date here
                        String endDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(this, "End Date: " + endDate, Toast.LENGTH_SHORT).show();

                        // You can use startDate and endDate for further processing
                    }, endYear, endMonth, endDayOfMonth);

            // Set minimum date as the selected start date
            Calendar minDate = Calendar.getInstance();
            minDate.set(startYear, startMonth, startDayOfMonth);
            endDatePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

            endDatePickerDialog.show();
        }
//        public void showDatePickerDialog(View view) {
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                    new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                            // Update the selected date in the EditText
//                            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
//                            dateEditText.setText(selectedDate);
//                        }
//                    }, year, month, dayOfMonth);
//            datePickerDialog.show();
//        }

        private boolean areFieldsEmpty() {
            String[] dropdownItems = getResources().getStringArray(R.array.dropdown_items);
            String[] dropdownstateItems = getResources().getStringArray(R.array.state_name);
            String item1 = dropdownItems[0];
            String stateitem = dropdownstateItems[0];
            String text1 = name.getText().toString().trim();

            String text3 = email.getText().toString().trim();
            String text4 = number.getText().toString().trim();


            String text7 = occasion.getText().toString().trim();

            // Get the text from more EditText fields if added
            if (text1.isEmpty() ||  text3.isEmpty() || text4.isEmpty() ||  text7.isEmpty()) {
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

            String getname = name.getText().toString();

//            String getevent = spinner.getSelectedItem().toString();
            String getnumber = number.getText().toString();
//            String getdate = dateEditText.getText().toString();
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

            hashmap.put("name", getname);
            hashmap.put("number", getnumber);

//            hashmap.put("event", getevent);
//            hashmap.put("date", getdate);
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


            occasion.setText("");
            dateEditText.setText("");
        }
        private void showStateDropdown() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select State");

            ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                    R.array.performers, R.layout.my_selected_items);
            stateAdapter.setDropDownViewResource(R.layout.my_drop_item);

            final Spinner stateSpinner = new Spinner(this);
            stateSpinner.setAdapter(stateAdapter);

            builder.setView(stateSpinner);

            builder.setPositiveButton("OK", (dialog, which) -> {
                // Handle the selection if needed
                int selectedPosition = stateSpinner.getSelectedItemPosition();
                String selectedState = getResources().getStringArray(R.array.performers)[selectedPosition];

                // Retrieve the selected item from the main spinner
                String selectedMainItem = category_performer.getSelectedItem().toString();

                // Perform actions with the selections
                String message = "Performer Category: " + selectedMainItem + "\nPerformer Name: " + selectedState;
                Toast.makeText(MainActivity3.this, message, Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Cancel", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
