package com.thietkegiaodien.assignment.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.dao.KhoanThuChiDAO;
import com.thietkegiaodien.assignment.model.KhoanThuChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class KhoanTCRCVAdapter extends RecyclerView.Adapter<KhoanTCRCVAdapter.KhoanTCHolder> {
    Activity context;
    ArrayList<KhoanThuChi> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public KhoanTCRCVAdapter(Activity context, ArrayList<KhoanThuChi> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public KhoanTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.khoantc_item,parent,false);
        return (new KhoanTCHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanTCHolder holder, final int position){
        holder.tvTieuDe.setText(list.get(position).getTieuDe());
        holder.tvNgay.setText(sdf.format(list.get(position).getNgay()));
        holder.tvTien.setText(String.valueOf(list.get(position).getTien()));
        holder.tvGhiChu.setText(list.get(position).getGhiChu());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit(position);
            }
        });
    }
    @Override
    public int getItemCount(){
        return list.size();
    }

    public static class KhoanTCHolder extends RecyclerView.ViewHolder{
        TextView tvTieuDe, tvNgay, tvTien,tvGhiChu;
        ImageView ivEdit, ivDelete;
        CardView card;
        public KhoanTCHolder(View item){
            super(item);
            tvTieuDe = item.findViewById(R.id.tvTieuDe);
            tvNgay = item.findViewById(R.id.tvNgay);
            tvTien = item.findViewById(R.id.tvTien);
            tvGhiChu = item.findViewById(R.id.tvGhiChu);
            ivEdit = item.findViewById(R.id.ivEdit);
            card = item.findViewById(R.id.khoantc_item);
        }
    }
    private void Edit(final int position){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.edit_item);

        final EditText ettieude,etNgay,etGhiChu,etTien;
        ImageView ivLich;
        final Calendar lich;
        Button btndongy,btnHuy,btnXoa;
        ivLich = dialog.findViewById(R.id.ivlich);
        ettieude = dialog.findViewById(R.id.ettieude);
        etNgay = dialog.findViewById(R.id.etNgay);
        etGhiChu = dialog.findViewById(R.id.etGhiChu);
        etTien = dialog.findViewById(R.id.etTien);
        btndongy=dialog.findViewById(R.id.btnDongY);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        btnXoa = dialog.findViewById(R.id.btnXoa);
        lich = Calendar.getInstance();
        ettieude.setText(list.get(position).getTieuDe());
        etNgay.setText(sdf.format(list.get(position).getNgay()));
        etGhiChu.setText(list.get(position).getGhiChu());
        etTien.setText(String.valueOf(list.get(position).getTien()));
        ivLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etNgay.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },
                        lich.get(Calendar.YEAR),
                        lich.get(Calendar.MONTH),
                        lich.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int matc = list.get(position).getMaKhoan();
                String ten = ettieude.getText().toString();
                String ngay = etNgay.getText().toString();
                String ghichu = etGhiChu.getText().toString();
                double tien = Double.parseDouble(etTien.getText().toString());
                int maloai = list.get(position).getMaLoai();
                try {
                    KhoanThuChi ktc = new KhoanThuChi(matc, ten,sdf.parse(ngay),tien, ghichu, maloai);
                    if (KhoanThuChiDAO.update(ktc,context)){
                        Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_LONG).show();
                        list.clear();
                        list.addAll(KhoanThuChiDAO.readAll(context, KhoanThuChiDAO.TrangThai));
                        notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maThuChi = list.get(position).getMaKhoan();
                if(KhoanThuChiDAO.delete(maThuChi,context)){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(KhoanThuChiDAO.readAll(context,KhoanThuChiDAO.TrangThai));
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
                else
                    Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
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
}
