package com.madmobiledevs.tayseer_tech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CalculatorActivity extends AppCompatActivity {
    Spinner spino;

    String payingCountry_Name, sendingCountry_Name;
    EditText payingCOuntry, sendingCOuntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        payingCountry_Name = intent.getStringExtra("payingCountryName");
        sendingCountry_Name = intent.getStringExtra("sendingCountryName");

        Spinner spinner = findViewById(R.id.spinner1);
        payingCOuntry = findViewById(R.id.PC_Name);
        sendingCOuntry = findViewById(R.id.SC_Name);

        payingCOuntry.setText(payingCountry_Name);
        sendingCOuntry.setText(sendingCountry_Name);


        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.purposes, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}