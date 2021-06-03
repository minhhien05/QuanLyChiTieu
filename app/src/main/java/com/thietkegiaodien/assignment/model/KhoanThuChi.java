package com.thietkegiaodien.assignment.model;

import android.widget.EditText;

import java.util.Date;

public class KhoanThuChi {
    private int maKhoan;
    private String tieuDe;
    private Date ngay;
    private double tien;
    private String ghiChu;
    private int maLoai;

    public KhoanThuChi() {
    }

    public KhoanThuChi(String tieuDe, Date ngay, double tien, String ghiChu) {
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.ghiChu = ghiChu;
    }

    public KhoanThuChi(int maKhoan, String tieuDe, Date ngay, double tien, String ghiChu, int maLoai) {
        this.maKhoan = maKhoan;
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.ghiChu = ghiChu;
        this.maLoai = maLoai;
    }

    public KhoanThuChi(String tieuDe, Date ngay, double tien, String ghiChu, int maLoai) {
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.ghiChu = ghiChu;
        this.maLoai = maLoai;
    }


    public int getMaKhoan() {
        return maKhoan;
    }

    public void setMaKhoan(int maKhoan) {
        this.maKhoan = maKhoan;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
