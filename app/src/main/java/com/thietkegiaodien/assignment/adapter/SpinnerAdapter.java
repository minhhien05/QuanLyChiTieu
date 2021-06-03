package com.thietkegiaodien.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.model.LoaiThuChi;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiThuChi> ds;
    LayoutInflater inflater;
    public SpinnerAdapter(Context context, ArrayList<LoaiThuChi> ds) {
        this.context = context;
        this.ds = ds;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_item,null);
        TextView tvmaLoai = convertView.findViewById(R.id.tvMaloai);
        TextView tvten = convertView.findViewById(R.id.tvtentieude);
        tvmaLoai.setText(String.valueOf(ds.get(position).getMaLoai()));
        tvten.setText(ds.get(position).getTenLoai());
        return convertView;
    }
}
