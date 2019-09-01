package com.example.my.Home.DialogCheckin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.SharedPref;
import com.example.my.MainActivity;
import com.example.my.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialogCheckin extends Dialog  implements View.OnClickListener {
    ArrayList<DataFormAktifitas>getArrAk;
    private Context context;
    TextView textCheck;
    RecyclerView recy;
    Button add, submit;
    AdapterFormAktifitas adapter;
    int jum = 1;
    Gson gson;
    DatabaseReference database;
    SharedPref sharedPref;
    Spinner sp_type;
    DataFormAktifitas data;
    String jenis;
    String key;

    public DialogCheckin(@NonNull Context context, String jenis, String key) {
        super(context);
        this.jenis = jenis;
        this.key = key;
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_alert_checkin);
        initView();

        recy.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recy.setLayoutManager(layoutManager);

        getArrAk.add(new DataFormAktifitas(jum));
        adapter = new AdapterFormAktifitas(getArrAk, context);
        recy.setAdapter(adapter);
        add.setOnClickListener(this);
        submit.setOnClickListener(this);
//        Toast.makeText(context, "Namanya "+sharedPref.getNama()+" dan Keynya "+key, Toast.LENGTH_SHORT).show();

    }

    public DialogCheckin(@NonNull Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_alert_checkin);
        initView();

        recy.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recy.setLayoutManager(layoutManager);

        getArrAk.add(new DataFormAktifitas(jum));
        adapter = new AdapterFormAktifitas(getArrAk, context);
        recy.setAdapter(adapter);
        add.setOnClickListener(this);
        submit.setOnClickListener(this);

    }




    private void initView() {
        textCheck = findViewById(R.id.textCheck);
        textCheck.setText(jenis);
        recy = findViewById(R.id.recy_aktivitas);
        add = findViewById(R.id.add_todoList);
        submit = findViewById(R.id.btn_submit_checkin);
        getArrAk = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference();
        sp_type = findViewById(R.id.spinner_type);
        sharedPref = new SharedPref(context);
    }

    @Override
    public void onClick(View v) {
        gson = new GsonBuilder().create();
        String aktifitas = "";
        for (int i = 0; i < getArrAk.size(); i++) {
            String sGson = new Gson().toJson(getArrAk.get(i));
            data = gson.fromJson(sGson, DataFormAktifitas.class);
            aktifitas = aktifitas+data.getAktifitas()+" - "+data.getPersen()+"%, ";
        }
        switch (v.getId()){
            case R.id.add_todoList:
                jum = jum+1;
                getArrAk.add(new DataFormAktifitas(jum));
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_submit_checkin:
                if (data.getAktifitas()==null || data.getPersen().equalsIgnoreCase("-%-"))
                {
                    Toast.makeText(context, "Form harus terisi semua!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    database.child("checkin")
                            .child(sharedPref.getNama())
                            .push()
                            .setValue(new DataHarian(jenis, sp_type.getSelectedItem().toString(), aktifitas, tanggal(), waktu()))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(context, "Berhasil "+jenis+"!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    context.startActivity(new Intent(context, MainActivity.class));
                    hapusData();
                    dismiss();

                }
                break;
        }
    }
    private void hapusData(){
        if (key.equalsIgnoreCase("")){
        }else {
            sp_type.getItemAtPosition(2);
            database.child("perizinan")
                    .child(sharedPref.getNama())
                    .child(key)
                    .removeValue();
        }
    }
     private String tanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private String waktu(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
