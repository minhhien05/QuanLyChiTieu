package com.thietkegiaodien.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // Tạo CSDL
    public DbHelper(Context context){
        super(context,"QLChiTieu",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng loại thu chi
        String sql = "CREATE TABLE LOAI_TC(MaLoai integer PRIMARY KEY AUTOINCREMENT, TenLoai text, TrangThai text)";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Lãi ngân hàng','Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Lương','Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Bán hàng','Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Thu nợ','Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Sinh hoạt hằng ngày','Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Đóng tiền học','Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES ('Du lịch','Chi')";
        db.execSQL(sql);

        // Tạo bảng khoản thu chi
        sql = "CREATE TABLE KHOAN_TC(MaKhoan integer PRIMARY KEY AUTOINCREMENT, TieuDe text, Ngay date, Tien real," +
                " GhiChu text, MaLoai integer REFERENCES LOAI_TC(MaLoai))";
        db.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe, Ngay, Tien, GhiChu, MaLoai) VALUES ('Lương tháng tháng 1/2020','2020-01-05',2000,'Lương cao quá',2)";
        db.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe, Ngay, Tien, GhiChu, MaLoai) VALUES ('Thu nợ tháng 5/2020','2020-05-25',500,'Nợ lâu quá',4)";
        db.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe, Ngay, Tien, GhiChu, MaLoai) VALUES ('Tiền học tháng 9','2020-09-14',8000000,'Học phí cao quá',6)";
        db.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe, Ngay, Tien, GhiChu, MaLoai) VALUES ('Du lịch Vũng Tàu','2020-08-25',2000000,'Vui quá',7)";
        db.execSQL(sql);

        // Tạo bảng quản trị
        sql = "CREATE TABLE QUANTRI(Username text PRIMARY KEY, Password text)";
        db.execSQL(sql);
        sql = "INSERT INTO QUANTRI VALUES ('admin','admin')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LOAI_TC");
        db.execSQL("DROP TABLE IF EXISTS KHOAN_TC");
    }
}
