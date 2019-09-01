package com.example.my.Izin;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.my.Home.DialogCheckin.DialogCheckin;
import com.example.my.R;

public class DialogHistoryIzin extends Dialog implements View.OnClickListener {
    Button ya, tidak;
    CardView dialog;
    String tanggalPengganti, key;

    public DialogHistoryIzin(@NonNull Context context, String tanggalPengganti, String key) {
        super(context);
        this.tanggalPengganti = tanggalPengganti;
        this.key = key;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_ganti_hari);
        init();
        ya.setOnClickListener(this);
        tidak.setOnClickListener(this);
    }

    public DialogHistoryIzin(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_ganti_hari);
        init();
        ya.setOnClickListener(this);
        tidak.setOnClickListener(this);
    }

    private void init() {
        ya = findViewById(R.id.btnya);
        tidak = findViewById(R.id.btn_cancle);
        dialog = findViewById(R.id.dialog_ganti_hari);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_cancle:
                dismiss();
                break;
            case R.id.btnya:
                dismiss();
                DialogCheckin dialog = new DialogCheckin(getContext(), "Check In [ Pengganti tanggal "+tanggalPengganti+" ]", key);
                dialog.show();
                break;
        }
    }
}
