package com.example.my.Profile;

import java.io.Serializable;

public class DataProfile implements Serializable {
    String nama, alamat_asal, alamat_saat_ini, email, no_telephone, projek_saat_ini, wali, no_wali, pass, key;

    public DataProfile(String nama, String alamat_asal, String alamat_saat_ini, String email, String no_telephone, String projek_saat_ini, String wali, String no_wali, String pass, String key) {
        this.nama = nama;
        this.alamat_asal = alamat_asal;
        this.alamat_saat_ini = alamat_saat_ini;
        this.email = email;
        this.no_telephone = no_telephone;
        this.projek_saat_ini = projek_saat_ini;
        this.wali = wali;
        this.no_wali = no_wali;
        this.pass = pass;
        this.key = key;
    }

    public DataProfile() {
    }

    public DataProfile(String nama, String alamat_asal, String alamat_saat_ini, String email, String no_telephone, String projek_saat_ini, String wali, String no_wali) {
        this.nama = nama;
        this.alamat_asal = alamat_asal;
        this.alamat_saat_ini = alamat_saat_ini;
        this.email = email;
        this.no_telephone = no_telephone;
        this.projek_saat_ini = projek_saat_ini;
        this.wali = wali;
        this.no_wali = no_wali;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat_asal() {
        return alamat_asal;
    }

    public void setAlamat_asal(String alamat_asal) {
        this.alamat_asal = alamat_asal;
    }

    public String getAlamat_saat_ini() {
        return alamat_saat_ini;
    }

    public void setAlamat_saat_ini(String alamat_saat_ini) {
        this.alamat_saat_ini = alamat_saat_ini;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telephone() {
        return no_telephone;
    }

    public void setNo_telephone(String no_telephone) {
        this.no_telephone = no_telephone;
    }

    public String getProjek_saat_ini() {
        return projek_saat_ini;
    }

    public void setProjek_saat_ini(String projek_saat_ini) {
        this.projek_saat_ini = projek_saat_ini;
    }

    public String getWali() {
        return wali;
    }

    public void setWali(String wali) {
        this.wali = wali;
    }

    public String getNo_wali() {
        return no_wali;
    }

    public void setNo_wali(String no_wali) {
        this.no_wali = no_wali;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
