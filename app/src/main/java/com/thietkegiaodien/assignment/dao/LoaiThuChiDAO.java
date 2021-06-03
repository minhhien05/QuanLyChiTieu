package com.thietkegiaodien.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thietkegiaodien.assignment.database.DbHelper;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import java.util.ArrayList;

public class LoaiThuChiDAO {
    static DbHelper helper;
    public static String TrangThai = null;
    public static ArrayList<LoaiThuChi> getAll(Context context,String TrangThai){
        ArrayList<LoaiThuChi> ds = new ArrayList<>();
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql ="select * from Loai_TC where TrangThai ='"+TrangThai+"'";
        Cursor cs =db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int maLoai = cs.getInt(0);
            String tenLoai = cs.getString(1);
            ds.add(new LoaiThuChi(maLoai,tenLoai));
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
    public static boolean insert(Context context,String tenLoai,String trangThai){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",tenLoai);
        values.put("TrangThai",trangThai);
        long row = db.insert("LOAI_TC",null,values);
        return row > 0;
    }
    public static boolean update(Context context, LoaiThuChi ltc){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaLoai",ltc.getMaLoai());
        values.put("TenLoai",ltc.getTenLoai());
        long row = db.update("LOAI_TC",values,"MaLoai=?",new String[]{String.valueOf(ltc.getMaLoai())});
        return row > 0;
    }
    public static boolean delete(LoaiThuChi ltc,Context context){
        helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaLoai",ltc.getMaLoai());
        values.put("TenLoai",ltc.getTenLoai());
        long row = db.delete("LOAI_TC","TenLoai=?",new String[]{ltc.getTenLoai()});
        return row > 0;
    }
}
