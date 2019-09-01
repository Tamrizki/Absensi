package com.example.my.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.Home.DialogCheckin.DataHarian;
import com.example.my.R;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    List<DataHarian> getArray;
    Context mContext;

    public AdapterHistory(List<DataHarian> getArray, Context mContext) {
        this.getArray = getArray;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_custom_history, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        DataHarian getData = getArray.get(i);
        viewHolder.aktivitas.setText(getData.getAktivitas());
        viewHolder.status.setText(getData.getStatus());
        viewHolder.waktuSubmit.setText(getData.getJam());
        viewHolder.waktuDanJam.setText(getData.getTanggal());
    }

    @Override
    public int getItemCount() {
        return getArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView waktuSubmit, waktuDanJam, status, aktivitas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            waktuDanJam = itemView.findViewById(R.id.txt_waktu_checkin);
            waktuSubmit = itemView.findViewById(R.id.waktu_submit);
            status = itemView.findViewById(R.id.text_checkin);
            aktivitas = itemView.findViewById(R.id.txt_isi_aktivitas);

        }
    }
}
