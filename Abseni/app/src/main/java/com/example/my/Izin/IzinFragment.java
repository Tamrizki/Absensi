package com.example.my.Izin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.my.SharedPref;
import com.example.my.MainActivity;
import com.example.my.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class IzinFragment extends Fragment implements View.OnClickListener {


    public IzinFragment() {
        // Required empty public constructor
    }

    Button submit;
    ImageButton add;
    RecyclerView recyclerView;
    ArrayList<DataIzin>arrData = new ArrayList<>();
    DataIzin getData;
    AdapterIzin adapterIzin;

    DataIzin dataizin;
    Gson gsonBuilder;
    String gsonn;
    SharedPref sharedPref;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_izin, container, false);
        init(v);

        submit.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrData.add(new DataIzin(1));
        adapterIzin = new AdapterIzin(arrData, getActivity());
        recyclerView.setAdapter(adapterIzin);

        add.setOnClickListener(this);


        return v;
    }

    private void init(View v) {
        submit = v.findViewById(R.id.btn_submit_izin);
        add = v.findViewById(R.id.btn_add);
        recyclerView = v.findViewById(R.id.recy_izin);
        getData = new DataIzin();
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View v) {
        gsonBuilder = new GsonBuilder().create();

        switch (v.getId()){
            case R.id.btn_submit_izin:
                for (int i = 0; i < arrData.size(); i++) {
                    gsonn = new Gson().toJson(arrData.get(i));
                    dataizin = gsonBuilder.fromJson(gsonn, DataIzin.class);
                    simpanData(dataizin);
                }
                break;

            case R.id.btn_add:
                int halaman = arrData.size();
                arrData.add(new DataIzin((halaman+1)));
                adapterIzin.notifyDataSetChanged();

                break;
        }
    }
    public void simpanData(DataIzin data){
        sharedPref = new SharedPref(getActivity());
        databaseReference.child("perizinan")
                .child(sharedPref.getNama())
                .push()
                .setValue(new DataIzin(data.getTipeIzin(), data.getTanggal(), data.getAlasan()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Data berhasil terkirim!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Gagal mengajukan perizinan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
