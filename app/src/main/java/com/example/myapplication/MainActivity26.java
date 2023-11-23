package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity26 extends AppCompatActivity {
    private Spinner mainSpinner, subSpinner;
    private Map<String, List<String>> subDropdownItems;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle savedInstanceState1 = savedInstanceState;
        super.onCreate(savedInstanceState1);

        setContentView(R.layout.activity_main26);


        mainSpinner = findViewById(R.id.mainSpinner);

        // Initialize your data (dummy data for demonstration)
        setupData();

        ArrayAdapter<String> mainAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(subDropdownItems.keySet()));
        mainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainSpinner.setAdapter(mainAdapter);

        mainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = mainAdapter.getItem(position);
                if (selectedItem != null) {
                    showSubDropdown(selectedItem, view);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle no selection
            }
        });
    }

    // Initialize the data (Replace this with your actual data retrieval logic)
    private void setupData() {
        // Initialize sub dropdown items mapped to main dropdown items
        subDropdownItems = new HashMap<>();
        subDropdownItems.put("Item 1", Arrays.asList("Subitem 1.1", "Subitem 1.2", "Subitem 1.3"));
        subDropdownItems.put("Item 2", Arrays.asList("Subitem 2.1", "Subitem 2.2", "Subitem 2.3"));
        subDropdownItems.put("Item 3", Arrays.asList("Subitem 3.1", "Subitem 3.2", "Subitem 3.3"));
    }
        // Method to populate the child Spinner based on the selected parent item


    private void showSubDropdown(String selectedItem, View anchorView) {
        List<String> subDropdown = subDropdownItems.get(selectedItem);
        if (subDropdown != null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.popup_sub_spinner, null);
            Spinner subSpinner = popupView.findViewById(R.id.subSpinner);

            ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subDropdown);
            subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subSpinner.setAdapter(subAdapter);

            PopupWindow popupWindow = new PopupWindow(popupView, anchorView.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.START);
            }
        }
    }
}