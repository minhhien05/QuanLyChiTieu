package com.thietkegiaodien.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thietkegiaodien.assignment.database.DbHelper;
import com.thietkegiaodien.assignment.model.KhoanThuChi;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhoanThuChiDAO {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static DbHelper helper;
    public static String TrangThai = null;
    public static ArrayList<KhoanThuChi> readAll(Context context,String trangThai){
        ArrayList<KhoanThuChi> ds = new ArrayList<>();
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql ="SELECT * FROM Khoan_TC JOIN Loai_TC ON Loai_TC.MaLoai=Khoan_TC.MaLoai WHERE TrangThai ='"+trangThai+"'";
        Cursor cs =db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int ma = cs.getInt(0);
            String ten = cs.getString(1);
            String ngay = cs.getString(2);
            Double tien = cs.getDouble(3);
            String ghichu = cs.getString(4);
            int maloai = cs.getInt(5);
            try {
                ds.add(new KhoanThuChi(ma,ten,sdf.parse(ngay),tien, ghichu, maloai));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
    public static boolean insert(KhoanThuChi ktc, Context context){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe",ktc.getTieuDe());
        values.put("Tien",ktc.getTien());
        values.put("Ngay",sdf.format(ktc.getNgay()));
        values.put("GhiChu",ktc.getGhiChu());
        values.put("MaLoai",ktc.getMaLoai());
        long row = db.insert("KHOAN_TC",null,values);
        return row > 0;
    }
    public static boolean update(KhoanThuChi ktc,Context context){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaKhoan",ktc.getMaKhoan());
        values.put("TieuDe",ktc.getTieuDe());
        values.put("Tien",ktc.getTien());
        values.put("Ngay",sdf.format(ktc.getNgay()));
        values.put("GhiChu",ktc.getGhiChu());
        long row = db.update("KHOAN_TC",values,"MaKhoan=?",new String[]{String.valueOf(ktc.getMaKhoan())});
        return row > 0;
    }
    public static boolean delete(int matc,Context context){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        long row = db.delete("KHOAN_TC","MaKhoan=?",new String[]{String.valueOf(matc)});
        return row > 0;
    }
}
