package com.example.my;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public static final String cek = "Tes";
    public static final String Nama = "sNama";
    public static final String SUDAH_LOGIN = "sLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPref(Context context) {
        sp = context.getSharedPreferences(cek, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveString(String key, String value){
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public void saveBoolean(String key, Boolean value){
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }
    public void ClearAll(){
        spEditor.clear();
        spEditor.commit();
    }

    public String getNama() {
        return sp.getString(Nama, "");
    }

    public boolean getSudahLogin() {
        return sp.getBoolean(SUDAH_LOGIN, false);
    }
}
