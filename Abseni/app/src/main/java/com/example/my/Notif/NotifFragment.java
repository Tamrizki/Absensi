package com.example.my.Notif;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my.SharedPref;
import com.example.my.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotifFragment extends Fragment {

    public NotifFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList<DataNotif> getNotif = new ArrayList<>();
    DatabaseReference database;
    SharedPref sharedPref;
    AdapterNotif adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notif, container, false);
        init(v);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterNotif(getActivity(), getNotif);
        isiData();
        recyclerView.setAdapter(adapter);

        return v;
    }

    private void init(View v) {
        database = FirebaseDatabase.getInstance().getReference();
        recyclerView = v.findViewById(R.id.recy_Notiv);
        sharedPref = new SharedPref(getActivity());
    }

    private void isiData(){
        database.child("pemberitahuan").child(sharedPref.getNama()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getData : dataSnapshot.getChildren())
                {
                    DataNotif dataNotif = getData.getValue(DataNotif.class);
                    dataNotif.setKey(getData.getKey());
                    getNotif.add(new DataNotif(dataNotif.getPesan(), dataNotif.getTanggal()));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
