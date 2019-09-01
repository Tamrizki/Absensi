package com.example.my.Notif;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.R;

import java.util.List;

public class AdapterNotif extends RecyclerView.Adapter<AdapterNotif.ViewHolder> {

    Context mContext;
    List<DataNotif>getNotif;

    public AdapterNotif(Context mContext, List<DataNotif> getNotif) {
        this.mContext = mContext;
        this.getNotif = getNotif;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_notiv, viewGroup, false);

        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DataNotif getdata = getNotif.get(i);
        viewHolder.pesan.setText(getdata.getPesan());
        viewHolder.waktu.setText(getdata.getTanggal());
    }

    @Override
    public int getItemCount() {
        return getNotif.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pesan, waktu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        pesan = itemView.findViewById(R.id.txt_notif);
        waktu = itemView.findViewById(R.id.waktu_notif);
        }
    }
}
