package com.example.my.Poin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.SharedPref;
import com.example.my.Notif.DataNotif;
import com.example.my.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PoinActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<DataPoin>jumlahPoin = new ArrayList<>();
    DatabaseReference dtbase;
    SharedPref sharedPref;
    int poinTotal=0;
    TextView poin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poin);
        toolbar = findViewById(R.id.tool_app);
        setSupportActionBar(toolbar);
        setTitle("Poin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        poin = findViewById(R.id.txt_poin);
        dtbase = FirebaseDatabase.getInstance().getReference();
        sharedPref = new SharedPref(this);
        isiData();
    }

    private void isiData(){

        dtbase.child("poin").child(sharedPref.getNama()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getData : dataSnapshot.getChildren())
                {
                    DataPoin data1 = getData.getValue(DataPoin.class);
                    data1.setKey(getData.getKey());
                    jumlahPoin.add(new DataPoin(data1.getJumlah_poin()));
                    poinTotal = poinTotal + data1.getJumlah_poin();

                }
                poin.setText(String.valueOf(poinTotal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
