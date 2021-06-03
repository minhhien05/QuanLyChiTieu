package com.thietkegiaodien.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thietkegiaodien.assignment.R;
import com.thietkegiaodien.assignment.ViewPagerAdapter;

public class KhoanChi_LoaiChiFragment extends Fragment {
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthu,container,false);
        viewPager = view.findViewById(R.id.viewPager);
        addTab(viewPager);
        ((TabLayout)view.findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
        return view;
    }
    public void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.add(new KhoanChiFragment(),"Khoản Chi");
        adapter.add(new LoaiChiFragment(),"Loại Chi");
        viewPager.setAdapter(adapter);
    }
}
