package com.example.my.Cuti;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.R;

import java.util.ArrayList;

public class AdapterCuti extends RecyclerView.Adapter<AdapterCuti.VHolder_cuti> {

    ArrayList<DataCuti>data;
    Context mContext;

    public AdapterCuti(ArrayList<DataCuti> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VHolder_cuti onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_cuti, viewGroup, false);
        return (new VHolder_cuti(v));
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder_cuti vHolder_cuti, int i) {
        DataCuti getData = data.get(i);
        vHolder_cuti.alasan.setText(getData.getAlasan_c());
        vHolder_cuti.tanggal.setText(getData.getDari_c()+" Sampai "+getData.sampai_c);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VHolder_cuti extends RecyclerView.ViewHolder {
        TextView alasan, tanggal;
        public VHolder_cuti(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.txt_tanggalCuti);
            alasan = itemView.findViewById(R.id.tv_alasanCuti);
        }
    }
}
