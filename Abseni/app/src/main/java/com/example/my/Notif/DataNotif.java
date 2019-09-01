package com.example.my.Notif;

public class DataNotif {
    String pesan, tanggal, key;

    public DataNotif() {
    }

    public DataNotif(String pesan, String tanggal) {
        this.pesan = pesan;
        this.tanggal = tanggal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
