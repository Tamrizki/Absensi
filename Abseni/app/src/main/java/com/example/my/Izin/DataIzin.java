package com.example.my.Izin;


import java.util.ArrayList;

public class DataIzin  {
    private String tipeIzin, tanggal, alasan;
    private int nomer;

    public DataIzin() {
    }

    public DataIzin(int nomer) {
        this.nomer = nomer;
    }
    public DataIzin(String tipeIzin, String tanggal, String alasan) {
        this.tipeIzin = tipeIzin;
        this.tanggal = tanggal;
        this.alasan = alasan;
    }

    public void setTipeIzin(String tipeIzin) {
        this.tipeIzin = tipeIzin;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getTipeIzin() {
        return tipeIzin;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getAlasan() {
        return alasan;
    }
}
