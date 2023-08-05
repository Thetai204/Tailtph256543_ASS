package com.example.tailtph256543_ass;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.Fragment.Frag_bixoa;
import com.example.tailtph256543_ass.Fragment.Fragment_all;
import com.example.tailtph256543_ass.Fragment.Fragment_chuahoanthanh;
import com.example.tailtph256543_ass.Fragment.Fragment_moi;
import com.example.tailtph256543_ass.Fragment.Fragmnet_hoanthanh;
import com.google.android.material.navigation.NavigationView;

public class Quanlycongviec extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    View header;
    DTO_User user;

    FragmentManager manager;
    Fragment_all frag_all;
    Fragment_moi frag_moi;
    Fragmnet_hoanthanh frag_hoanthanh;
    Fragment_chuahoanthanh frag_chuahoanthanh;
    Frag_bixoa frag_bixoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cong_viec);
        toolbar = findViewById(R.id.tb_);
        drawerLayout = findViewById(R.id.layout_chinh);
        navigationView = findViewById(R.id.navi);
        frag_all = new Fragment_all();
        frag_moi = new Fragment_moi();
        frag_hoanthanh = new Fragmnet_hoanthanh();
        frag_chuahoanthanh = new Fragment_chuahoanthanh();
        frag_bixoa = new Frag_bixoa();

        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.frag_02,frag_all).commit();

        //hiển thị dữ liệu lên header
        Intent intent = getIntent();
        user = new DTO_User();
        user = (DTO_User) intent.getSerializableExtra("data");

        header = navigationView.getHeaderView(0);
        ImageView avatar = header.findViewById(R.id.imv_avatar);
        TextView name = header.findViewById(R.id.tv_username_head);
        name.setText(user.getFullname());


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản lý công việc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.it_quanlycongviec) {
                    toolbar.setTitle("Quản lý công việc");
                    manager.beginTransaction().replace(R.id.frag_02,frag_all).commit();
                } else if (item.getItemId() == R.id.it_cv_moi) {
                    toolbar.setTitle("Công việc mới tạo");
                    manager.beginTransaction().replace(R.id.frag_02,frag_moi).commit();
                } else if (item.getItemId() == R.id.it_cv_dalam) {
                    toolbar.setTitle("Công việc đã làm");
                    manager.beginTransaction().replace(R.id.frag_02,frag_hoanthanh).commit();
                }else if (item.getItemId() == R.id.it_cv_chualam) {
                    toolbar.setTitle("Công việc chưa làm");
                    manager.beginTransaction().replace(R.id.frag_02,frag_chuahoanthanh).commit();
                }else if (item.getItemId() == R.id.it_ql_taikhoan) {
                    toolbar.setTitle("Quản lý tài khoản");

                }else if (item.getItemId() == R.id.it_dangxuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Quanlycongviec.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn đăng xuất ?");
                    builder.setIcon(R.drawable.baseline_warning_24);

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                } else if (item.getItemId()==R.id.it_cv_bixoa) {
                    toolbar.setTitle("Công việc bị xóa");
                    manager.beginTransaction().replace(R.id.frag_02,frag_bixoa).commit();
                }
                drawerLayout.close();
                return false;
            }
        });
    }
    public DTO_User Guidata(){
        return user;
    }
}
