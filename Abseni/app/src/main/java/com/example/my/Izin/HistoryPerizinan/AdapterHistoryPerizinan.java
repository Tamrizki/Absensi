package com.example.my.Izin.HistoryPerizinan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.Izin.DataIzin;
import com.example.my.Izin.DialogHistoryIzin;
import com.example.my.R;

import java.util.List;

public class AdapterHistoryPerizinan extends RecyclerView.Adapter<AdapterHistoryPerizinan.ViewHolder> {

    List<DataPerizinan> getData;
    Context context;

    public AdapterHistoryPerizinan(List<DataPerizinan> getData, Context context) {
        this.getData = getData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_custom_izin, viewGroup, false);
        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataPerizinan dataPerizinan = getData.get(i);
        viewHolder.alasan.setText(dataPerizinan.getAlasan());
        viewHolder.tanggal.setText(dataPerizinan.getTanggal());
        viewHolder.status.setText(dataPerizinan.getTipeIzin());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataPerizinan.getTipeIzin().equalsIgnoreCase("Potong Gaji"))
                {
                    Toast.makeText(context, "Status Perizinan adalah "+dataPerizinan.getTipeIzin(), Toast.LENGTH_SHORT).show();
                }else
                {
                    DialogHistoryIzin dialog = new DialogHistoryIzin(context, ""+dataPerizinan.getTanggal(), dataPerizinan.getKey());
                    dialog.show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return getData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, tanggal, alasan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.text_izinStatus);
            tanggal = itemView.findViewById(R.id.text_izinTanggal);
            alasan = itemView.findViewById(R.id.text_izinAlasan);

        }
    }
}
