package com.example.my.Home;


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
import android.widget.Toast;

import com.example.my.Cuti.CutiActivity;
import com.example.my.Home.DialogCheckin.DataHarian;
import com.example.my.Home.DialogCheckin.DialogCheckin;
import com.example.my.Izin.HistoryPerizinan.HistoryPerizinan_Activity;
import com.example.my.SharedPref;
import com.example.my.Poin.PoinActivity;
import com.example.my.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {


    public HomeFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    ArrayList<DataHarian> getArr = new ArrayList<>();
    ArrayList<String> jumArray = new ArrayList<>();
    Button btnIzin, btn_checkin, btn_checkout, btn_poin, btn_cuti;

    DataHarian dataHistory;
    DatabaseReference database;
    SharedPref sharedPref;
    AdapterHistory adapterHistory;
    DialogCheckin getStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sharedPref = new SharedPref(getActivity());
        adapterHistory = new AdapterHistory(getArr, getActivity());

        isiData();
        recyclerView.setAdapter(adapterHistory);

        btnIzin.setOnClickListener(this);
        btn_checkin.setOnClickListener(this);
        btn_checkout.setOnClickListener(this);
        btn_poin.setOnClickListener(this);
        btn_cuti.setOnClickListener(this);
        return v;
    }

    private void init(View v) {
        btn_checkin = v.findViewById(R.id.btn_checkin);
        btnIzin = v.findViewById(R.id.btn_izin);
        btn_checkout = v.findViewById(R.id.btn_checkout);
        btn_poin = v.findViewById(R.id.btn_poin);
        recyclerView = v.findViewById(R.id.recy_history);
        database = FirebaseDatabase.getInstance().getReference();
        getStatus = new DialogCheckin(getActivity());
        btn_cuti = v.findViewById(R.id.btn_cuti);
    }

    private void isiData(){
        database.child("checkin").child(sharedPref.getNama()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getData : dataSnapshot.getChildren())
                {
                    dataHistory = getData.getValue(DataHarian.class);
                    dataHistory.setKey(getData.getKey());
                    getArr.add(new DataHarian(dataHistory.getStatus(), dataHistory.getTypeAbsen(), dataHistory.getAktivitas(), dataHistory.getTanggal(), dataHistory.getJam()));
                    jumArray.add(dataHistory.getStatus());
                }
                visible();
                adapterHistory.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkout:
                DialogCheckin dialog_ckout = new DialogCheckin(getActivity(), "Check Out", "");
                dialog_ckout.show();
                break;

            case R.id.btn_checkin:
                DialogCheckin dialog_ckin = new DialogCheckin(getActivity(), "Check In","");
                dialog_ckin.show();
                break;

            case R.id.btn_cuti:
                Intent intent_cuti = new Intent(getActivity(), CutiActivity.class);
                startActivity(intent_cuti);
                break;

            case R.id.btn_izin:
                    Intent intent = new Intent(getActivity(), HistoryPerizinan_Activity.class);
                    startActivity(intent);
                break;
            case R.id.btn_poin:
                    Intent in = new Intent(getActivity(), PoinActivity.class);
                    startActivity(in);
                break;
        }
    }

    public void visible() {
        if (jumArray.size()!=0) {
            if (jumArray.size() % 2 == 1) {
                btn_checkin.setVisibility(View.GONE);
                btn_checkout.setVisibility(View.VISIBLE);
            } else {
                btn_checkout.setVisibility(View.GONE);
                btn_checkin.setVisibility(View.VISIBLE);
            }
        }
        else {isiData();}
    }
}
