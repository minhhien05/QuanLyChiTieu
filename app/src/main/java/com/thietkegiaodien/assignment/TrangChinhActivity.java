package com.thietkegiaodien.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.thietkegiaodien.assignment.dao.KhoanThuChiDAO;
import com.thietkegiaodien.assignment.dao.LoaiThuChiDAO;
import com.thietkegiaodien.assignment.fragment.KhoanChi_LoaiChiFragment;
import com.thietkegiaodien.assignment.fragment.KhoanThu_LoaiThuFragment;
import com.thietkegiaodien.assignment.fragment.ThongKeFragment;


public class TrangChinhActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh);
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_thu:
                        fragment = new KhoanThu_LoaiThuFragment();
                        KhoanThuChiDAO.TrangThai="Thu";
                        LoaiThuChiDAO.TrangThai="Thu";
                        break;
                    case R.id.nav_chi:
                        fragment = new KhoanChi_LoaiChiFragment();
                        KhoanThuChiDAO.TrangThai="Chi";
                        LoaiThuChiDAO.TrangThai="Chi";
                        break;
                    case R.id.nav_thongke:
                        fragment = new ThongKeFragment();
                        break;
                    case R.id.nav_gioithieu:
                        break;
                    case R.id.nav_caidat:
                        break;
                    case R.id.nav_thoat:
                        Toast.makeText(TrangChinhActivity.this,"Thoát...",Toast.LENGTH_LONG).show();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,fragment).commit();
                navigationView.setCheckedItem(menuItem.getItemId());
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
