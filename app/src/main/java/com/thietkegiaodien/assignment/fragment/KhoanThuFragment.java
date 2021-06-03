package com.thietkegiaodien.assignment.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.textfield.TextInputLayout;
import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.adapter.KhoanTCRCVAdapter;
import com.thietkegiaodien.assignment.adapter.LoaiThuAdapter;
import com.thietkegiaodien.assignment.adapter.SpinnerAdapter;
import com.thietkegiaodien.assignment.dao.KhoanThuChiDAO;
import com.thietkegiaodien.assignment.dao.LoaiThuChiDAO;
import com.thietkegiaodien.assignment.model.KhoanThuChi;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KhoanThuFragment extends Fragment {
    RecyclerView rcvKhoan;
    KhoanTCRCVAdapter adapter;
    ArrayList<KhoanThuChi> list;
    ArrayList<LoaiThuChi> spnlist;
    FloatingActionButton fbThem;
    DatePickerDialog datePickerDialog;
    Calendar lich;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable final Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, container, false);

        rcvKhoan = view.findViewById(R.id.rcvKhoan);
        fbThem = view.findViewById(R.id.fbThem);
        lich = Calendar.getInstance();
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvKhoan.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        spnlist = new ArrayList<>();

        list = KhoanThuChiDAO.readAll(getContext(),"Thu");
        spnlist = LoaiThuChiDAO.getAll(getContext(),"Thu");

        adapter = new KhoanTCRCVAdapter(getActivity(), list);
        rcvKhoan.setAdapter(adapter);

        fbThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.khoanthuinsert_item);
                final Spinner spnDM = dialog.findViewById(R.id.spnDanhMuc);
                final EditText tiltien = dialog.findViewById(R.id.txtTien);
                final EditText tilghichu = dialog.findViewById(R.id.txtGhiChu);
                final TextView tilNgay = dialog.findViewById(R.id.Ngay);
                Button btnLuu = dialog.findViewById(R.id.btnLuu);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                ImageView iv = dialog.findViewById(R.id.ivLich);

                final int[] maloai = {0};
                final String[] tenloai = {null};
                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(),spnlist);
                spnDM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maloai[0] = spnlist.get(position).getMaLoai();
                        tenloai[0] = spnlist.get(position).getTenLoai();
                        Toast.makeText(getContext(), maloai[0]+"-"+ tenloai[0] , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spnDM.setAdapter(spinnerAdapter);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker vi, int year, int month, int dayOfMonth) {
                                tilNgay.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        },
                                lich.get(Calendar.YEAR),
                                lich.get(Calendar.MONTH),
                                lich.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.show();
                    }
                });

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = tenloai[0];
                        String tien = tiltien.getText().toString();
                        String ghichu = tilghichu.getText().toString();
                        String ngay = tilNgay.getText().toString();
                        int MaLoai = maloai[0];
                        try {
                            KhoanThuChi ktc = new KhoanThuChi(ten,sdf.parse(ngay), Double.parseDouble(tien), ghichu,MaLoai);
                            if (KhoanThuChiDAO.insert(ktc, getContext())) {
                               list.clear();
                               list.addAll(KhoanThuChiDAO.readAll(getContext(),KhoanThuChiDAO.TrangThai));
                               adapter.notifyDataSetChanged();
                               Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
                               dialog.dismiss();
                            } else
                                Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
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
