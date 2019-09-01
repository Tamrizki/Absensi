package com.example.my.Izin.HistoryPerizinan;

import java.io.Serializable;

public class DataPerizinan implements Serializable {
    private String tipeIzin, tanggal, alasan, key;

    public DataPerizinan(String tipeIzin, String tanggal, String alasan, String key) {
        this.tipeIzin = tipeIzin;
        this.tanggal = tanggal;
        this.alasan = alasan;
        this.key = key;
    }

    public DataPerizinan(String tipeIzin, String tanggal, String alasan) {
        this.tipeIzin = tipeIzin;
        this.tanggal = tanggal;
        this.alasan = alasan;
    }

    public DataPerizinan() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return tipeIzin + "\n"
                + tanggal + "\n"
                + alasan;
    }
}
