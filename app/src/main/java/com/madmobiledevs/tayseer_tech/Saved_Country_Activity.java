package com.madmobiledevs.tayseer_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.madmobiledevs.tayseer_tech.LoadingDialougs.LoadingDialog;
import com.madmobiledevs.tayseer_tech.Model.SavedData;
import com.madmobiledevs.tayseer_tech.ViewHolder.SavedCountry_ViewHolder;

public class Saved_Country_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    private FirebaseRecyclerAdapter<SavedData, SavedCountry_ViewHolder> adapter;

    DatabaseReference savedData_Ref;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_country);

       recyclerView = findViewById(R.id.recyclerVw_Saved);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadingDialog = new LoadingDialog(this);

        savedData_Ref = FirebaseDatabase.getInstance().getReference().child("savedData");

        showAllData();

    }

    private void  showAllData(){
        loadingDialog.startLoadingDialog();
        loadingDialog.dismissDialog();

        FirebaseRecyclerOptions<SavedData> options=
                new FirebaseRecyclerOptions.Builder<SavedData>()
                        .setQuery(savedData_Ref,SavedData.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<SavedData, SavedCountry_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SavedCountry_ViewHolder holder, int position, @NonNull SavedData model) {
                holder.S_CountryNAme.setText(model.getSendingCountry());
                holder.R_CountryNAme.setText(model.getReceivingCountry());
                holder.s_SenderName.setText(model.getSenderName());
                holder.R_RecieverName.setText(model.getReceiverName());
                holder.purpose1.setText(model.getPurpose());
                holder.purpose2.setText("");
                holder.purpose_dm_11.setText("");

                holder.amount_Snt_TxtVw.setText(String.valueOf(model.getSendingAmount())+" "+model.getSendingCurrency());
                holder.amount_Recieved_TxtVw.setText(String.valueOf(model.getReceivingAmount())+" "+model.getReceivingCurrency());



            }

            @NonNull
            @Override
            public SavedCountry_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_converted_layout,parent, false);
                SavedCountry_ViewHolder holder= new SavedCountry_ViewHolder(view);
                return holder;
            }
        };


        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}