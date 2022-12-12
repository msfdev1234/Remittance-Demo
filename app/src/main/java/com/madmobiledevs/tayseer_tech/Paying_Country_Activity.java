package com.madmobiledevs.tayseer_tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.madmobiledevs.tayseer_tech.Adapter.PayingCountryAdatper;
import com.madmobiledevs.tayseer_tech.Adapter.SendingCountryAdapter;
import com.madmobiledevs.tayseer_tech.Model.PayingCountry;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Paying_Country_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //List<PayingCountry> modelArrayList=new ArrayList<>();

    private RecyclerView.Adapter mAdapter_P;
    private RecyclerView.LayoutManager layoutManager_P;

    private List<PayingCountry> modelArrayList=new ArrayList<>();

    String stringArray_P, sendingCountry_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying_country);

        Intent intent = getIntent();
        stringArray_P = intent.getStringExtra("countryList");
        sendingCountry_Name = intent.getStringExtra("sendingCountryName");
        Log.e("HIIII",stringArray_P.toString());



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_PC);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_P = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager_P);

        getAllCountries();
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

            mAdapter_P = new PayingCountryAdatper(getApplicationContext(), modelArrayList, sendingCountry_Name);
            mRecyclerView.setAdapter(mAdapter_P);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}