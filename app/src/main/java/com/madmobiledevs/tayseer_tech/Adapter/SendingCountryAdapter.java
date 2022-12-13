package com.madmobiledevs.tayseer_tech.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.madmobiledevs.tayseer_tech.Interface.ApiInterface;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.Payload1;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;
import com.madmobiledevs.tayseer_tech.Paying_Country_Activity;
import com.madmobiledevs.tayseer_tech.R;
import com.madmobiledevs.tayseer_tech.RetrofitClient;
import com.madmobiledevs.tayseer_tech.SubClasses.PCountryId;
import com.madmobiledevs.tayseer_tech.SubClasses.SCountryId;
import com.madmobiledevs.tayseer_tech.SubClasses.SystemId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendingCountryAdapter extends RecyclerView.Adapter<SendingCountryAdapter.viewHolder> {

    private static final int TYPE = 1;
    private Context context;
    private List<SendingCountry> modelArrayList=new ArrayList<SendingCountry>();

    LoadingDialog loadingDialog;
    private JsonArray listOfCountries = new JsonArray();

    public SendingCountryAdapter(Context context, List<SendingCountry> modelArrayList, LoadingDialog loadingDialog) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.loadingDialog=loadingDialog;

    }


    public SendingCountryAdapter() {
    }

    public void updateList(List<SendingCountry> list){
        modelArrayList = list;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView country_Flag;
        TextView country_Name;
        RelativeLayout relativeLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            country_Flag = (ImageView) itemView.findViewById(R.id.country_Flag);
            country_Name = (TextView) itemView.findViewById(R.id.country_Name);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.full_view);

        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_view_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        SendingCountry sendingCountry = modelArrayList.get(position);

        holder.country_Name.setText(sendingCountry.getCountryName());

        Picasso.get().load(sendingCountry.getFlagUrl())
                .into(holder.country_Flag);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getReceivingCountries(sendingCountry.getCountryName());
            }
        });

    }



    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void getReceivingCountries(String countryName) {
        loadingDialog.startLoadingDialog();

        PCountryId pCountryId = new PCountryId(244);
        SCountryId sCountryId = new SCountryId(245);
        SystemId systemId = new SystemId(19);

        Payload1 payload1 =new Payload1();

        Gson gson = new Gson();
        String jsonString = gson.toJson(payload1);

        Log.e("yhascjaldsj", jsonString);

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance("https://devapi.paysii.com").create(ApiInterface.class);
        Call<JsonObject> call = apiInterface.getRCountries(payload1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject object = response.body();
                double exchangeRAte = object.get("payload").getAsJsonObject().get("result").getAsJsonArray().get(0).getAsJsonObject().get("currencies").getAsJsonArray().get(0).getAsJsonObject().get("exchangeRateUsd").getAsDouble();
                listOfCountries = object.get("payload").getAsJsonObject().get("result").getAsJsonArray().get(0).getAsJsonObject().get("payingCountries").getAsJsonArray();
                Log.e("joooooo",listOfCountries.toString());

                loadingDialog.dismissDialog();

                Intent intent = new Intent(context, Paying_Country_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("countryList", listOfCountries.toString());
                intent.putExtra("sendingCountryName", countryName);
                intent.putExtra("exchangeRate", Double.toString(exchangeRAte));
                context.startActivity(intent);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }



}
