package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class MainActivity27 extends AppCompatActivity {

    private Spinner dateSelectorSpinner;
    private ArrayAdapter<CharSequence> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        dateSelectorSpinner = findViewById(R.id.dateSelectorSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.date_selection_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        dateSelectorSpinner.setAdapter(adapter);

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
}
