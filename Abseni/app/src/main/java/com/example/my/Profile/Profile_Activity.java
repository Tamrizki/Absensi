package com.example.my.Profile;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.DialogEditProfile;
import com.example.my.SharedPref;
import com.example.my.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile_Activity extends AppCompatActivity {
//    private Button edit;
    Toolbar toolbar;
    DatabaseReference database;
    TextView nama, alamat, alamatSekarang, email, noTelp, projek, wali, noTelp_wali;
    SharedPref sharedPref;
    ArrayList<DataProfile>arrProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        init();
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ambilDatabase();

    }

    private void ambilDatabase() {

        sharedPref = new SharedPref(this);
        database.child("anggota").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getDataP : dataSnapshot.getChildren())
                {
                    DataProfile dataProfile = getDataP.getValue(DataProfile.class);
                    dataProfile.setKey(getDataP.getKey());
                    arrProfil.add(dataProfile);
                }

                for (int i = 0; i < arrProfil.size() ; i++) {
                    if (arrProfil.get(i).getNama().equals(sharedPref.getNama()))
                    {
                        UbahET(i);
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void UbahET(int a) {
        nama.setText(arrProfil.get(a).getNama());
        alamat.setText(arrProfil.get(a).getAlamat_asal());
        alamatSekarang.setText(arrProfil.get(a).getAlamat_saat_ini());
        email.setText(arrProfil.get(a).getEmail());
        noTelp.setText(arrProfil.get(a).getNo_telephone());
        projek.setText(arrProfil.get(a).getProjek_saat_ini());
        wali.setText(arrProfil.get(a).getWali());
        noTelp_wali.setText(arrProfil.get(a).getNo_wali());
    }

    private void init() {
        database = FirebaseDatabase.getInstance().getReference();
        nama = findViewById(R.id.p_name);
        alamat = findViewById(R.id.p_alamatAsal);
        alamatSekarang = findViewById(R.id.p_alamatSaatIni);
        email = findViewById(R.id.p_email);
        noTelp= findViewById(R.id.p_telephone);
        projek = findViewById(R.id.p_projek);
        wali = findViewById(R.id.p_wali);
        noTelp_wali = findViewById(R.id.p_telephoneWali);
        arrProfil = new ArrayList<>();
    }
}
