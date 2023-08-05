package com.example.tailtph256543_ass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.tailtph256543_ass.DAO.DAO_Phien;
import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.Fragment.Frag_dangnhap;
import com.example.tailtph256543_ass.Fragment.Fragment_begin;
import com.example.tailtph256543_ass.Fragment.Fragment_dangki;
import com.example.tailtph256543_ass.Fragment.Fragment_quen_pass_doipass;
import com.example.tailtph256543_ass.Fragment.Fragment_quen_pass_xacthuc;

public class MainActivity extends AppCompatActivity {

    Fragment_begin frag_begin;
    Fragment_dangki frag_dangKy;
    Frag_dangnhap frag_dangnhap;
   DAO_Phien daoPhien;
    FragmentManager manager;
    Fragment_quen_pass_doipass frag_quen_pass_doipass;
    Fragment_quen_pass_xacthuc frag_quen_pass_xacthuc;
DTO_User dto_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag_begin = new Fragment_begin();
        frag_dangKy = new Fragment_dangki();
        frag_dangnhap = new Frag_dangnhap();

        manager = getSupportFragmentManager();
        daoPhien = new DAO_Phien(this);
        int check = daoPhien.so();

        new Thread() {
            @Override
            public void run() {
                super.run();
                manager.beginTransaction().add(R.id.frag_, frag_begin).commit();
                try {
                    sleep(2000);
                    if (check == 0) {
                        manager.beginTransaction().replace(R.id.frag_,frag_dangKy).commit();
                        daoPhien.soPhien();
                    } else {
                        manager.beginTransaction().replace(R.id.frag_, frag_dangnhap).commit();
                    }
                } catch (Exception e) {

                }
            }
        }.start();


    }

    public Fragment_quen_pass_xacthuc truyenPassXT(){
        return frag_quen_pass_xacthuc;
    }
    public Fragment_quen_pass_doipass truyenPassDP(){
        return frag_quen_pass_doipass;
    }

    public DTO_User nhanData() {
        return dto_user=frag_quen_pass_xacthuc.guidata();
    }

}