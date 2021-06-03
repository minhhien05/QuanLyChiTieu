package com.thietkegiaodien.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.adapter.LoaiThuAdapter;
import com.thietkegiaodien.assignment.dao.LoaiThuChiDAO;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import java.util.ArrayList;

public class LoaiChiFragment extends Fragment {
    RecyclerView rcvLoaiThu;
    LoaiThuAdapter adapter;
    FloatingActionButton fbThemLoai;
    ArrayList<LoaiThuChi> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_chi, container, false);

        rcvLoaiThu = view.findViewById(R.id.rcvLoai);
        fbThemLoai = view.findViewById(R.id.fbThemLoai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvLoaiThu.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list = LoaiThuChiDAO.getAll(getContext(),"Chi");
        adapter = new LoaiThuAdapter(getActivity(), list);
        rcvLoaiThu.setAdapter(adapter);
        fbThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.loaithuinsert_item);
                final EditText tiltenloai = dialog.findViewById(R.id.txttenloai);
                final EditText spnTT = dialog.findViewById(R.id.spnTrangthai);
                Button btnLuu = dialog.findViewById(R.id.btnluu);
                Button btnHuy = dialog.findViewById(R.id.btnhuy);

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = tiltenloai.getText().toString();
                        String trangThai = spnTT.getText().toString();
                        if(LoaiThuChiDAO.insert(getContext(),ten,trangThai)){
                            Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
                            list.clear();
                            list.addAll(LoaiThuChiDAO.getAll(getContext(),LoaiThuChiDAO.TrangThai));
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else
                            Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_LONG).show();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });
        return view;
    }
}
