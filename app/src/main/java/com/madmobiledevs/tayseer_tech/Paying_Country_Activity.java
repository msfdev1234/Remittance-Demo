package com.madmobiledevs.tayseer_tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.madmobiledevs.tayseer_tech.Adapter.PayingCountryAdatper;
import com.madmobiledevs.tayseer_tech.Adapter.SendingCountryAdapter;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.PayingCountry;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Paying_Country_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    LoadingDialog loadingDialog;

    //List<PayingCountry> modelArrayList=new ArrayList<>();

    private PayingCountryAdatper mAdapter_P;
    private RecyclerView.LayoutManager layoutManager_P;

    private List<PayingCountry> modelArrayList=new ArrayList<>();

    String stringArray_P, sendingCountry_Name;

    private EditText search_Bar;
    String exchangeRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying_country);

        loadingDialog = new LoadingDialog(this);

        search_Bar = findViewById(R.id.search_Bar_EdtTxt_PC);

        Intent intent = getIntent();
        stringArray_P = intent.getStringExtra("countryList");
        sendingCountry_Name = intent.getStringExtra("sendingCountryName");
        exchangeRate = intent.getStringExtra("exchangeRate");
        Log.e("HIIII",stringArray_P.toString());



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_PC);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_P = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager_P);

        getAllCountries();

        search_Bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void getAllCountries(){
        try {
            JSONArray listOfCountries = new JSONArray(stringArray_P);
            for (int i=0;i<listOfCountries.length();i++){

                JSONObject countryObject = listOfCountries.getJSONObject(i);

                PayingCountry payingCountry = new PayingCountry();

                payingCountry.setCountryName(countryObject.get("countryName").toString());
                payingCountry.setCountryCode(countryObject.get("countryCode").toString());
                payingCountry.setFlagUrl(countryObject.get("flagUrl").toString());
                payingCountry.setPayingCountryCallingCode(countryObject.get("payingCountryCallingCode").toString());

                payingCountry.setMobileMaxLength(Integer.parseInt(countryObject.get("mobileMaxLength").toString()));
                payingCountry.setMobileMinLength(Integer.parseInt(countryObject.get("mobileMinLength").toString()));
                payingCountry.setCountryId(Integer.parseInt(countryObject.get("countryId").toString()));

                modelArrayList.add(payingCountry);
            }

            mAdapter_P = new PayingCountryAdatper(getApplicationContext(), modelArrayList, sendingCountry_Name,loadingDialog, exchangeRate);
            mRecyclerView.setAdapter(mAdapter_P);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void filter(String text){
        List<PayingCountry> temp = new ArrayList();
        for(PayingCountry d: modelArrayList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getCountryName().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        mAdapter_P.updateList_P(temp);
    }
}