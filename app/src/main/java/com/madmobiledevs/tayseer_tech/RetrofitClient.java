package com.madmobiledevs.tayseer_tech;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


   public static Retrofit retrofit;

   public static Retrofit getRetrofitInstance(String urlBase){



      if(retrofit==null){
         retrofit= new Retrofit.Builder()
                 .baseUrl(urlBase)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
      }
      return retrofit;
   }
}
