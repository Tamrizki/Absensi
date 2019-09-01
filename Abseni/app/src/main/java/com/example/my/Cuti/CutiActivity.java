package com.example.my.Cuti;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.Izin.DataIzin;
import com.example.my.Izin.HistoryPerizinan.AdapterHistoryPerizinan;
import com.example.my.Izin.HistoryPerizinan.DataPerizinan;
import com.example.my.Izin.HistoryPerizinan.HistoryPerizinan_Activity;
import com.example.my.MainActivity;
import com.example.my.R;
import com.example.my.SharedPref;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class CutiActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner posisi_c;
    Button dari_c, sampai_c, kirim_c;
    TextView alasan_c;
    SharedPref sharedPref;
    DatabaseReference dbr;
    RecyclerView recyclerView_cuti;
    ArrayList<DataCuti> dataCutis;
    Toolbar toolbar;
    String bln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);

        init();
        setSupportActionBar(toolbar);
        setTitle("Pengajuan Cuti");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView_cuti.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        try {
            isiData();
        }catch (Exception e)
        {}

        recyclerView_cuti.setLayoutManager(layoutManager);
        kirim_c.setOnClickListener(this);
        dari_c.setOnClickListener(this);
        sampai_c.setOnClickListener(this);

    }

    private void init() {
    posisi_c = findViewById(R.id.spinner_typeCuti);
    dari_c = findViewById(R.id.btn_dariCuti);
    sampai_c = findViewById(R.id.btn_sampaiCuti);
    kirim_c = findViewById(R.id.btn_submit_cuti);
    alasan_c = findViewById(R.id.txt_alasanCuti);
    sharedPref = new SharedPref(CutiActivity.this);
    dbr = FirebaseDatabase.getInstance().getReference();
    dataCutis = new ArrayList<>();
    recyclerView_cuti = findViewById(R.id.recy_cuti);
        toolbar = findViewById(R.id.app_bar_cuti);
    }

    public void KirimData(){
        sharedPref = new SharedPref(CutiActivity.this);
        dbr.child("cuti")
                .child(sharedPref.getNama())
                .push()
                .setValue(new DataCuti(dari_c.getText().toString(), sampai_c.getText().toString(), alasan_c.getText().toString(), posisi_c.getSelectedItem().toString()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(CutiActivity.this, CutiActivity.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_submit_cuti:
                KirimData();
                break;
            case R.id.btn_dariCuti:
                showDateDialog(1, dari_c);
                break;
            case R.id.btn_sampaiCuti:
                showDateDialog(1, sampai_c);
                break;
        }
    }
    private void isiData(){
        sharedPref = new SharedPref(this);
        dbr.child("cuti").child(sharedPref.getNama()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getCuti : dataSnapshot.getChildren())
                {
                    DataCuti data = getCuti.getValue(DataCuti.class);
                    data.setKey(getCuti.getKey());
                    dataCutis.add(data);
                }
                AdapterCuti adapterCuti = new AdapterCuti(dataCutis, CutiActivity.this);
                recyclerView_cuti.setAdapter(adapterCuti);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void showDateDialog(final int a, final Button button){
        Calendar cal = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                bln(month);
                switch (button.getId())
                {
                    case R.id.btn_dariCuti:
                        dari_c.setText(dayOfMonth+", "+bln+" "+year);break;
                    case R.id.btn_sampaiCuti:
                        sampai_c.setText(dayOfMonth+", "+bln+" "+year);break;
                }
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(CutiActivity.this, listener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    private void bln(int b){
        switch (b){
            case 1:bln="Januari";break;
            case 2:bln="Februari";break;
            case 3:bln="Maret";break;
            case 4:bln="April";break;
            case 5:bln="Mei";break;
            case 6:bln="Juni";break;
            case 7:bln="Juli";break;
            case 8:bln="Agustus";break;
            case 9:bln="September";break;
            case 10:bln="Oktober";break;
            case 11:bln="November";break;
            case 12:bln="Desember";break;
        }
    }
}
