package com.example.my.Izin.HistoryPerizinan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.my.SharedPref;
import com.example.my.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryPerizinan_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DataPerizinan>dataArray;
    Toolbar toolbar;
    DatabaseReference database;
    SharedPref sharedPref;
    DataPerizinan dataIzin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_perizinan_);

        init();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setSupportActionBar(toolbar);

        contenToolbar();
        isiData();
    }

    private void contenToolbar() {
        setTitle("Data Perizinan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        recyclerView = findViewById(R.id.recy_history_perizinan);
        toolbar = findViewById(R.id.app_bar_perizinan);
        database = FirebaseDatabase.getInstance().getReference();
        dataArray = new ArrayList<>();
    }

    private void isiData(){
        sharedPref = new SharedPref(this);
        database.child("perizinan").child(sharedPref.getNama()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getDataIzin : dataSnapshot.getChildren())
                {
                    dataIzin = getDataIzin.getValue(DataPerizinan.class);
                    dataIzin.setKey(getDataIzin.getKey());
                    dataArray.add(new DataPerizinan(dataIzin.getTipeIzin(), dataIzin.getTanggal(), dataIzin.getAlasan(), dataIzin.getKey()));

                }
                AdapterHistoryPerizinan adapter = new AdapterHistoryPerizinan(dataArray, HistoryPerizinan_Activity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
