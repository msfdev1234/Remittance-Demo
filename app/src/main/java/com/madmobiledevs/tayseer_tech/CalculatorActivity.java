package com.madmobiledevs.tayseer_tech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CalculatorActivity extends AppCompatActivity {
    Spinner spino;

    String payingCountry_Name, sendingCountry_Name;
    EditText payingCOuntry, sendingCOuntry, amountToSend_Ed, amountToReceive_Ed;
    double exchangeRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        payingCountry_Name = intent.getStringExtra("payingCountryName");
        sendingCountry_Name = intent.getStringExtra("sendingCountryName");
        exchangeRate = Double.parseDouble(intent.getStringExtra("exchangeRate"));

        Spinner spinner = findViewById(R.id.spinner1);
        payingCOuntry = findViewById(R.id.PC_Name);
        sendingCOuntry = findViewById(R.id.SC_Name);
        amountToSend_Ed = findViewById(R.id.AmountSend_Name);
        amountToReceive_Ed = findViewById(R.id.AmountRecieve_Name);

        payingCOuntry.setText(payingCountry_Name);
        sendingCOuntry.setText(sendingCountry_Name);

        amountToSend_Ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    amountToReceive_Ed.setText("");
                }else {
                    double value = Integer.parseInt(s.toString())*exchangeRate;
                    amountToReceive_Ed.setText(String.valueOf(value));
                }


            }
        });


        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.purposes, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}