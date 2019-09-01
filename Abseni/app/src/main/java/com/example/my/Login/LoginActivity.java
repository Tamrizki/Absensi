package com.example.my.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my.MainActivity;
import com.example.my.R;
import com.example.my.SharedPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, pass;
    Button login;
    ArrayList<Login_Data> arrData = new ArrayList<>();
    DatabaseReference database;
    String sUsername, sPass;
    int masuk = 0;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        CekData();

        if (sharedPref.getSudahLogin())
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {}

        login.setOnClickListener(this);

    }

    private void init() {
        username = findViewById(R.id.txt_username);
        pass = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        database = FirebaseDatabase.getInstance().getReference();
        sharedPref = new SharedPref(this);
    }

    @Override
    public void onClick(View v) {
        sPass = pass.getText().toString();
        sUsername = username.getText().toString();
        switch (v.getId())
        {
            case R.id.btn_login:


                    if (sUsername.equals(""))
                    {
                        username.setError("form harus terisi dengan benar!");
                        username.requestFocus();
                    }
                    else if (sPass.equals(""))
                    {
                        pass.setError("form harus terisi dengan benar!");
                        pass.requestFocus();
                    }
                    else
                        {
                        CekAnggota();
//                      ========================================
                        if (masuk == 1)
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(this, "Username atau Password anda salah!", Toast.LENGTH_SHORT).show();
                        }
                    }
                break;
        }
    }

    private void CekData() {
        database.child("anggota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot getData : dataSnapshot.getChildren())
                {
                    Login_Data ldata = getData.getValue(Login_Data.class);
                    ldata.setKey(getData.getKey());
                    arrData.add(ldata);
                    arrData.add(new Login_Data(ldata.getNama().toString(), ldata.getPass().toString()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Database Gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CekAnggota() {
        for (int i = 0; i < arrData.size() ; i++) {
            if (sUsername.equals(arrData.get(i).getNama()) && sPass.equals(arrData.get(i).getPass()))
            {
                masuk = 1;
                sharedPref.saveBoolean(sharedPref.SUDAH_LOGIN, true);
                sharedPref.saveString(sharedPref.Nama, ""+arrData.get(i).getNama());

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

                break;
            }
            else
                {
                masuk = 0;
            }
        }

    }
}
