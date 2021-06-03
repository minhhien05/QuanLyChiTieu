package com.thietkegiaodien.assignment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thietkegiaodien.assignment.database.DbHelper;

public class ThongKeDAO {
    public static double tongTienTheoTT(Context context, String TrangThai){
        double tongTien = 0;
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String str = "SELECT SUM(Tien) AS TongTien from KHOAN_TC JOIN LOAI_TC on KHOAN_TC.MaLoai = LOAI_TC.MaLoai where TrangThai ='" +TrangThai+"'";
        Cursor cs = db.rawQuery(str,null);
        cs.moveToFirst();
        if(cs.getCount() > 0){
            tongTien = cs.getFloat(0);
        }
        return tongTien;
    }
}
