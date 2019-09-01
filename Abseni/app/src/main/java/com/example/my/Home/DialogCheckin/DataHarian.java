package com.example.my.Home.DialogCheckin;

public class DataHarian {
    String status;
    String typeAbsen;
    String aktivitas;
    String tanggal;
    String jam;
    String key;

    public DataHarian() {
    }

    public DataHarian(String status, String typeAbsen, String aktivitas, String tanggal, String jam) {
        this.status = status;
        this.typeAbsen = typeAbsen;
        this.aktivitas = aktivitas;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeAbsen() {
        return typeAbsen;
    }

    public void setTypeAbsen(String typeAbsen) {
        this.typeAbsen = typeAbsen;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
