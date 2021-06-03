package com.thietkegiaodien.assignment.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.dao.KhoanThuChiDAO;
import com.thietkegiaodien.assignment.dao.LoaiThuChiDAO;
import com.thietkegiaodien.assignment.model.KhoanThuChi;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import java.util.ArrayList;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.LoaiTCHolder>{
    Activity context;
    ArrayList<LoaiThuChi> list;
    public LoaiThuAdapter(Activity context, ArrayList<LoaiThuChi> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public LoaiTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.thuchi_item,parent,false);
        return (new LoaiTCHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiTCHolder holder, final int position) {
        holder.tvMaLoai.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.tvTenLoai.setText(list.get(position).getTenLoai());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.sualoaithu_item);

                final EditText ettenLoai;
                final TextView tvmaLoai;
                Button btnSua,btnHuy;

                ettenLoai = dialog.findViewById(R.id.txtsuaTL);
                tvmaLoai = dialog.findViewById(R.id.tvsuaML);
                btnSua = dialog.findViewById(R.id.btnSua);
                btnHuy = dialog.findViewById(R.id.btnHuySua);

                ettenLoai.setText(list.get(position).getTenLoai());
                tvmaLoai.setText(String.valueOf(list.get(position).getMaLoai()));
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = ettenLoai.getText().toString();
                        int ma = Integer.parseInt(tvmaLoai.getText().toString());
                        LoaiThuChi ltc = new LoaiThuChi(ma,ten);
                        if(LoaiThuChiDAO.update(context,ltc)){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                            list.clear();
                            list.addAll(LoaiThuChiDAO.getAll(context,LoaiThuChiDAO.TrangThai));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_LONG).show();
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
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.xoathu_item);
                final TextView tenloai = dialog.findViewById(R.id.txtXoaTenLoai);
                final TextView maloai = dialog.findViewById(R.id.tvXoaMaLoai);
                Button btnXoa = dialog.findViewById(R.id.btnxoa);
                Button btnhuy = dialog.findViewById(R.id.btnhuyLoai);
                tenloai.setText(list.get(position).getTenLoai());
                maloai.setText(String.valueOf(list.get(position).getMaLoai()));
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = tenloai.getText().toString();
                        int ma =Integer.parseInt(maloai.getText().toString());
                        LoaiThuChi ltc = new LoaiThuChi(ma,ten);
                        if (LoaiThuChiDAO.delete(ltc,context)){
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(LoaiThuChiDAO.getAll(context,KhoanThuChiDAO.TrangThai));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else
                            Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class LoaiTCHolder extends RecyclerView.ViewHolder{
        TextView tvMaLoai, tvTenLoai;
        ImageView ivEdit, ivDelete;
        CardView card;
        public LoaiTCHolder(View item){
            super(item);
            tvMaLoai = item.findViewById(R.id.tvMaLoai);
            tvTenLoai = item.findViewById(R.id.tvTenLoai);
            ivEdit = item.findViewById(R.id.ivedit);
            ivDelete = item.findViewById(R.id.ivdelete);
            card = item.findViewById(R.id.thuchi_item);
        }
    }
}
