package com.example.my.Cuti;

public class DataCuti {
    String dari_c, sampai_c, alasan_c, jabatan_c, key;

    public DataCuti() {
    }

    public DataCuti(String dari_c, String sampai_c, String alasan_c, String jabatan_c) {
        this.dari_c = dari_c;
        this.sampai_c = sampai_c;
        this.alasan_c = alasan_c;
        this.jabatan_c = jabatan_c;
    }

//    public DataCuti(String dari_c, String sampai_c, String alasan_c) {
//        this.dari_c = dari_c;
//        this.sampai_c = sampai_c;
//        this.alasan_c = alasan_c;
//    }

    public String getDari_c() {
        return dari_c;
    }

    public void setDari_c(String dari_c) {
        this.dari_c = dari_c;
    }

    public String getSampai_c() {
        return sampai_c;
    }

    public void setSampai_c(String sampai_c) {
        this.sampai_c = sampai_c;
    }

    public String getAlasan_c() {
        return alasan_c;
    }

    public void setAlasan_c(String alasan_c) {
        this.alasan_c = alasan_c;
    }

    public String getJabatan_c() {
        return jabatan_c;
    }

    public void setJabatan_c(String jabatan_c) {
        this.jabatan_c = jabatan_c;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
