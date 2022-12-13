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
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.madmobiledevs.tayseer_tech.Adapter.SendingCountryAdapter;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Sending_Country_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    List<SendingCountry> modelArrayList=new ArrayList<>();

    private SendingCountryAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText search_Bar;

    String stringArray;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_country);

        loadingDialog = new LoadingDialog(this);

        search_Bar = findViewById(R.id.search_Bar_EdtTxt_SC);

        Intent intent = getIntent();
        stringArray = intent.getStringExtra("countryList");
        Log.e("HIIII",stringArray);
        //System.out.println("This is  Me : "+jsonArray.toString());

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_SC);

        mRecyclerView.setHasFixedSize(true);

        loadingDialog.startLoadingDialog();

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        getAllCountries();

        // specify an adapter (see also next example)
    //    addItemsFromJSON();



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
            JSONArray listOfCountries = new JSONArray(stringArray);
            System.out.println("hfhfgc" +listOfCountries);
            for (int i=0;i<listOfCountries.length();i++){

                JSONObject countryObject = listOfCountries.getJSONObject(i);

                SendingCountry sendingCountry = new SendingCountry();

                sendingCountry.setCountryName(countryObject.get("countryName").toString());
                sendingCountry.setCountryCode(countryObject.get("countryCode").toString());
                sendingCountry.setFlagUrl(countryObject.get("flagUrl").toString());
                sendingCountry.setSendingCountryCallingCode(countryObject.get("sendingCountryCallingCode").toString());

                sendingCountry.setMobileMaxLength(Integer.parseInt(countryObject.get("mobileMaxLength").toString()));
                sendingCountry.setMobileMinLength(Integer.parseInt(countryObject.get("mobileMinLength").toString()));
                sendingCountry.setCountryId(Integer.parseInt(countryObject.get("countryId").toString()));

                modelArrayList.add(sendingCountry);
            }

            mAdapter = new SendingCountryAdapter(getApplicationContext(), modelArrayList, loadingDialog);
            mRecyclerView.setAdapter(mAdapter);
            loadingDialog.dismissDialog();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void filter(String text){
        List<SendingCountry> temp = new ArrayList();
        for(SendingCountry d: modelArrayList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getCountryName().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        mAdapter.updateList(temp);
    }


}