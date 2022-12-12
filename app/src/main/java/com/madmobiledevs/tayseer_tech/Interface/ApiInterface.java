package com.madmobiledevs.tayseer_tech.Interface;

import com.google.gson.JsonObject;
import com.madmobiledevs.tayseer_tech.Model.Payload;
import com.madmobiledevs.tayseer_tech.Model.Payload1;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/orm/lookups/getsendingcountries")
    Call<JsonObject> getCountries(@Body Payload mReqBody);

    @POST("/orm/lookups/getsendingcountryconfig")
    Call<JsonObject> getRCountries(@Body Payload1 mReqBody1);
}
