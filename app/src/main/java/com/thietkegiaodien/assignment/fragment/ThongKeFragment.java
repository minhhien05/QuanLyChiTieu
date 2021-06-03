package com.thietkegiaodien.assignment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.dao.ThongKeDAO;

import org.fabiomsr.moneytextview.MoneyTextView;

public class ThongKeFragment extends Fragment {
    TextView tvTThu,tvTChi,tvTongThuChi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        tvTThu = view.findViewById(R.id.tkTongThu);
        tvTChi = view.findViewById(R.id.tkTongChi);
        tvTongThuChi = view.findViewById(R.id.tvTongThuChi);
        int tongThu = (int) ThongKeDAO.tongTienTheoTT(getContext(),"Thu");
        int tongChi = (int) ThongKeDAO.tongTienTheoTT(getContext(),"Chi");
        int tong = tongThu + (-tongChi);
        tvTThu.setText("Tổng Thu: " +tongThu);
        tvTChi.setText("Tổng Chi: " +tongChi);
        tvTongThuChi.setText("Tổng Thu Chi: " +tong);
        return view;
    }
}
