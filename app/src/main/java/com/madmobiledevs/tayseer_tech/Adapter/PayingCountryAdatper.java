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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.madmobiledevs.tayseer_tech.CalculatorActivity;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.PayingCountry;
import com.madmobiledevs.tayseer_tech.Model.SendingCountry;
import com.madmobiledevs.tayseer_tech.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PayingCountryAdatper extends RecyclerView.Adapter<PayingCountryAdatper.viewHolder> {

    private static final int TYPE = 1;
    private Context context;
    private List<PayingCountry> modelArrayList=new ArrayList<PayingCountry>();

    private JsonArray listOfCountries = new JsonArray();
    private String sendingCOuntry_Name;
    LoadingDialog loadingDialog;
    String exchangeRate;

    public PayingCountryAdatper(Context context, List<PayingCountry> modelArrayList, String sendingCOuntry_Name, LoadingDialog loadingDialog, String exchangeRate) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sendingCOuntry_Name = sendingCOuntry_Name;
        this.loadingDialog=loadingDialog;
        this.exchangeRate = exchangeRate;

    }

    public void updateList_P(List<PayingCountry> list){
        modelArrayList = list;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView country_Flag_P;
        TextView country_Name_P;
        RelativeLayout relativeLayout_P;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            country_Flag_P = (ImageView) itemView.findViewById(R.id.country_Flag);
            country_Name_P = (TextView) itemView.findViewById(R.id.country_Name);
            relativeLayout_P = (RelativeLayout) itemView.findViewById(R.id.full_view);

        }
    }

    @NonNull
    @Override
    public PayingCountryAdatper.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_view_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PayingCountry payingCountry = modelArrayList.get(position);

        holder.country_Name_P.setText(payingCountry.getCountryName());

        Picasso.get().load(payingCountry.getFlagUrl())
                .into(holder.country_Flag_P);

        holder.relativeLayout_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CalculatorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("payingCountryName", payingCountry.getCountryName());
                intent.putExtra("sendingCountryName", sendingCOuntry_Name);
                intent.putExtra("exchangeRate", exchangeRate);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
}
