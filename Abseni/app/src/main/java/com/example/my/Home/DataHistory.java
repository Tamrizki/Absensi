package com.example.my.Home;

public class DataHistory {
    private String status, waktuSubmit, tgl_jam, aktivitas, key;

    public DataHistory(String status, String waktuSubmit, String tgl_jam, String aktivitas) {
        this.status = status;
        this.waktuSubmit = waktuSubmit;
        this.tgl_jam = tgl_jam;
        this.aktivitas = aktivitas;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktuSubmit() {
        return waktuSubmit;
    }

    public void setWaktuSubmit(String waktuSubmit) {
        this.waktuSubmit = waktuSubmit;
    }

    public String getTgl_jam() {
        return tgl_jam;
    }

    public void setTgl_jam(String tgl_jam) {
        this.tgl_jam = tgl_jam;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }
}
