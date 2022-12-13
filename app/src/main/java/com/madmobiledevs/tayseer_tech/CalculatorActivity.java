package com.madmobiledevs.tayseer_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity {
    Spinner spinner;

    String receivingCountry_Name, sendingCountry_Name, sendingCountry_Currency, receivingCountry_Currency, sendingPersonName, receivingPersonName;
    EditText payingCOuntry, sendingCOuntry, amountToSend_Ed, amountToReceive_Ed, name_Sender_Input, name_Receiver_Input;
    double exchangeRate;

    LoadingDialog loadingDialog;

    Button button_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        loadingDialog = new LoadingDialog(this);

        Intent intent = getIntent();
        receivingCountry_Name = intent.getStringExtra("payingCountryName");
        sendingCountry_Name = intent.getStringExtra("sendingCountryName");
        sendingCountry_Currency = intent.getStringExtra("sendingCountryName");
        receivingCountry_Currency = intent.getStringExtra("sendingCountryName");
        sendingPersonName = "a";
        receivingPersonName = "a";

        exchangeRate = Double.parseDouble(intent.getStringExtra("exchangeRate"));

        exchangeRate = 80;

        spinner = findViewById(R.id.spinner1);
        payingCOuntry = findViewById(R.id.PC_Name);
        sendingCOuntry = findViewById(R.id.SC_Name);
        amountToSend_Ed = findViewById(R.id.AmountSend_Name);
        amountToReceive_Ed = findViewById(R.id.AmountRecieve_Name);

        name_Sender_Input = findViewById(R.id.name_Sender_Input);
        name_Receiver_Input = findViewById(R.id.name_Receiver_Input);
        button_Send = findViewById(R.id.button_Send);

        payingCOuntry.setText(receivingCountry_Name);
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

        button_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildCount();
            }
        });
    }

    private void getChildCount() {
        loadingDialog.startLoadingDialog();

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("savedData");
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (int i = 0; i<1; i++){
                    count = (int) snapshot.getChildrenCount();
                }
                if (count<1){
                    count = 0;
                }

                addToDatabase(Integer.toString(count+1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void addToDatabase(String childID){


        DecimalFormat df = new DecimalFormat("#.#");

        if (TextUtils.isEmpty(sendingCOuntry.getText()) || TextUtils.isEmpty(payingCOuntry.getText()) || TextUtils.isEmpty(name_Sender_Input.getText())
                || TextUtils.isEmpty(name_Receiver_Input.getText()) || TextUtils.isEmpty(amountToSend_Ed.getText().toString())){
            Toast.makeText(this, "Please Input Complete Details..!", Toast.LENGTH_SHORT).show();
            loadingDialog.dismissDialog();

        }else {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("savedData").child(childID);

            final HashMap<String, Object> objectMap= new HashMap<>();

            objectMap.put("senderName",name_Sender_Input.getText().toString());
            objectMap.put("receiverName",name_Receiver_Input.getText().toString());
            objectMap.put("sendingCountry",sendingCountry_Name);
            objectMap.put("receivingCountry",receivingCountry_Name);
            objectMap.put("purpose",spinner.getSelectedItem().toString());
            objectMap.put("sendingCurrency","USD");
            objectMap.put("receivingCurrency","GBP");

            objectMap.put("sendingAmount",df.format(Double.parseDouble(amountToSend_Ed.getText().toString())));
            objectMap.put("receivingAmount",df.format(Double.parseDouble(amountToReceive_Ed.getText().toString())));

            databaseReference.updateChildren(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        loadingDialog.dismissDialog();
                        Intent intent = new Intent(CalculatorActivity.this, Saved_Country_Activity.class);
                        startActivity(intent);
                    }
                }
            });


        }



    }



}