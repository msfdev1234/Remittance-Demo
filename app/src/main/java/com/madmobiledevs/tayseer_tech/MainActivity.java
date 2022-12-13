package com.madmobiledevs.tayseer_tech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.madmobiledevs.tayseer_tech.Interface.ApiInterface;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.Payload;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;
import com.madmobiledevs.tayseer_tech.SubClasses.SystemId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button check_Button;
    JsonArray listOfCountries;

    LoadingDialog loadingDialog;

    List<SendingCountry> modelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfCountries = new JsonArray();
        loadingDialog = new LoadingDialog(this);

        check_Button = findViewById(R.id.check_button1);

       // getSendingCountries();

        check_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getSendingCountries();

            }
        });
    }

    private void getSendingCountries() {

        loadingDialog.startLoadingDialog();

        SystemId systemId = new SystemId(19);

        Payload payload =new Payload(systemId);

        ApiInterface apiInterface=RetrofitClient.getRetrofitInstance("https://devapi.paysii.com").create(ApiInterface.class);
        Call<JsonObject> call = apiInterface.getCountries(payload);

       call.enqueue(new Callback<JsonObject>() {
           @Override
           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               JsonObject object = response.body();
               listOfCountries = object.get("payload").getAsJsonObject().get("result").getAsJsonArray();

               Log.e("Mainnnnnnnnnnnnnn",listOfCountries.toString());
               loadingDialog.dismissDialog();

               Intent intent = new Intent(MainActivity.this, Sending_Country_Activity.class);
               intent.putExtra("countryList", listOfCountries.toString());
               startActivity(intent);

           }

           @Override
           public void onFailure(Call<JsonObject> call, Throwable t) {

           }
       });

    }


}