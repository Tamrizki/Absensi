package com.example.my.Izin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.my.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterIzin extends RecyclerView.Adapter<AdapterIzin.VHolder> {

    ArrayList<DataIzin> getData = new ArrayList<>();
    Context mContext;

    public AdapterIzin() {
    }

    public AdapterIzin(ArrayList<DataIzin> getData, Context mContext) {
        this.getData = getData;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_menu_izin,viewGroup, false);
        return (new VHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder holder, int i) {
        final DataIzin data = getData.get(i);
//        holder.nomer.setText(data.getNomer());
        holder.alasan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setAlasan(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.tanggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setTanggal(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.setTipeIzin(holder.spin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return getData.size();
    }

    public class VHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button tanggal;
        private TextView alasan, nomer;
        private Spinner spin;
        String bln;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            init();

            tanggal.setOnClickListener(this);
        }

        private void init() {
            tanggal = itemView.findViewById(R.id.btn_tanggal_izin);
            alasan = itemView.findViewById(R.id.txt_alasanIzin);
            spin = itemView.findViewById(R.id.spinner_typeIzin);
            nomer = itemView.findViewById(R.id.jdl_izin);

        }
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btn_tanggal_izin:
                    showDateDialog(1);
                    break;
            }
        }
        public void showDateDialog(final int a){
            Calendar cal = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    bln(month);
                    tanggal.setText(dayOfMonth+", "+bln+" "+year);
                }
            };

            DatePickerDialog dialog = new DatePickerDialog(mContext, listener,
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
}
