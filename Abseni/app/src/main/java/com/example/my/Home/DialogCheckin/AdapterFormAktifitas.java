package com.example.my.Home.DialogCheckin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.my.R;

import java.util.ArrayList;

public class AdapterFormAktifitas extends RecyclerView.Adapter<AdapterFormAktifitas.Holder> {

    ArrayList<DataFormAktifitas>arrAktif;
    Context mContext;

    public AdapterFormAktifitas(ArrayList<DataFormAktifitas> arrAktif, Context mContext) {
        this.arrAktif = arrAktif;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_aktifitas, viewGroup, false);
        return (new Holder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        final DataFormAktifitas getData = arrAktif.get(i);
        holder.tv_aktifitas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getData.setAktifitas(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.sp_persen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getData.setPersen(holder.sp_persen.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAktif.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        EditText tv_aktifitas;
        Spinner sp_persen;
        public Holder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View item) {
            tv_aktifitas = item.findViewById(R.id.text_aktivitas);
            sp_persen = item.findViewById(R.id.sp_persen);
        }
    }
}
