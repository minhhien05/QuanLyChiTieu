package com.thietkegiaodien.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.thietkegiaodien.assignment.fragment.KhoanChiFragment;
import com.thietkegiaodien.assignment.fragment.KhoanThuFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_khoanthu);
        viewPager = findViewById(R.id.viewPager);
        addTab(viewPager);
    }
    public void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new KhoanThuFragment(),"Khoản Thu");
        adapter.add(new KhoanChiFragment(),"Loại Thu");
        viewPager.setAdapter(adapter);
    }
}
