package com.thietkegiaodien.assignment.model;

public class LoaiThuChi {
    private int maLoai;
    private String tenLoai;
    private String trangThai;

    public LoaiThuChi(int maLoai, String tenLoai, String trangThai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    public LoaiThuChi(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiThuChi() {
    }

    public LoaiThuChi(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
