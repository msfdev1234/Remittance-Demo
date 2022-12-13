package com.madmobiledevs.tayseer_tech.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madmobiledevs.tayseer_tech.R;

public class SavedCountry_ViewHolder extends RecyclerView.ViewHolder{

    public TextView amount_Snt_TxtVw, amount_Recieved_TxtVw, S_CountryNAme, R_CountryNAme, s_SenderName, R_RecieverName, purpose_dm_11, purpose1, purpose2;

    public SavedCountry_ViewHolder(@NonNull View itemView) {
        super(itemView);

        amount_Snt_TxtVw = itemView.findViewById(R.id.amount_Snt_TxtVw);
        amount_Recieved_TxtVw = itemView.findViewById(R.id.amount_Recieved_TxtVw);
        S_CountryNAme = itemView.findViewById(R.id.S_CountryNAme);
        R_CountryNAme = itemView.findViewById(R.id.R_CountryNAme);
        s_SenderName = itemView.findViewById(R.id.s_SenderName);
        R_RecieverName = itemView.findViewById(R.id.R_RecieverName);
        purpose_dm_11 = itemView.findViewById(R.id.purpose_dm_11);
        purpose1 = itemView.findViewById(R.id.purpose1);
        purpose2 = itemView.findViewById(R.id.purpose2);


    }

}
