package com.example.my.Login;

import java.io.Serializable;

public class Login_Data implements Serializable {
    private String nama, pass, key;

    public Login_Data(String nama, String pass) {
        this.nama = nama;
        this.pass = pass;
    }

    public Login_Data() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return  nama +
                "\n"
                + pass;
    }
}
