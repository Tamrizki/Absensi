package com.example.my.Home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.my.Home.DialogCheckin.DialogCheckin;
import com.example.my.R;

public class DialogEditHistory extends Dialog implements View.OnClickListener {
    Button edit, cenlce;

    public DialogEditHistory(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_edit_history);
        init();
        edit.setOnClickListener(this);
        cenlce.setOnClickListener(this);
    }


    private void init() {

        edit = findViewById(R.id.btn_edit_history);
        cenlce = findViewById(R.id.btn_cancle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_edit_history:
                dismiss();
                DialogCheckin dialog = new DialogCheckin(getContext());
                dialog.show();
            case R.id.btn_cancle:
                dismiss();

        }
    }
}
