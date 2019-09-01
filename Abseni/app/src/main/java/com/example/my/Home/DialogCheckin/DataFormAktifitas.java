package com.example.my.Home.DialogCheckin;

public class DataFormAktifitas {
    String aktifitas, persen;
    int no;

    public DataFormAktifitas(int no) {
        this.no = no;
    }

    public DataFormAktifitas(String aktifitas, String persen) {
        this.aktifitas = aktifitas;
        this.persen = persen;
    }

    public String getAktifitas() {
        return aktifitas;
    }

    public void setAktifitas(String aktifitas) {
        this.aktifitas = aktifitas;
    }

    public String getPersen() {
        return persen;
    }

    public void setPersen(String persen) {
        this.persen = persen;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
