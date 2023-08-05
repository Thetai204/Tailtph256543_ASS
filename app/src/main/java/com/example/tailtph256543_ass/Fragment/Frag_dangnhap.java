package com.example.tailtph256543_ass.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tailtph256543_ass.DAO.DAO_Luudangnhap;
import com.example.tailtph256543_ass.DAO.DAO_User;
import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.DTO.DTO_luu;
import com.example.tailtph256543_ass.MainActivity;
import com.example.tailtph256543_ass.Quanlycongviec;
import com.example.tailtph256543_ass.R;


import java.util.List;

public class Frag_dangnhap extends Fragment {
    EditText tenTaiKhoan,matKhau;
    CheckBox luuTK;
    TextView dangKy,quenMK;
    Button dangNhap;
    List<DTO_User> list;
    DAO_User daoUser;
    DTO_User dto_user;
    MainActivity  activity;

    DTO_luu luu;
    DAO_Luudangnhap dao_luudangnhap;

    int KTluu= 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tenTaiKhoan= view.findViewById(R.id.edt_tentaikhoan_dangnhap);
        matKhau= view.findViewById(R.id.edt_matkhau_dangnhap);
        luuTK= view.findViewById(R.id.cbo_luudangnhap);
        dangKy= view.findViewById(R.id.tv_dangky);
        quenMK= view.findViewById(R.id.tv_quenmatkhau);
        dangNhap = view.findViewById(R.id.btn_dangnhap);
        daoUser = new DAO_User(getContext());
        activity= (MainActivity) getContext();
        list = daoUser.getAll();
        dao_luudangnhap = new DAO_Luudangnhap(getContext());
        luu = dao_luudangnhap.getData();



        if(luu.getStatus()==1){
            luuTK.setChecked(true);
            KTluu=1;
            tenTaiKhoan.setText(luu.getUserName());
            matKhau.setText(luu.getPassWord());
        }

luuTK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            KTluu = 1;
        }else {
            KTluu=0;
        }
    }
});

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_dangki fragmentDangki= new Fragment_dangki();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_,fragmentDangki);
            }
        });

        dangNhap.setOnClickListener(new View.OnClickListener() {
            int check = 0;
            @Override
            public void onClick(View v) {
            for (DTO_User user : list){
                check = 0;
                if(user.getUsername().equals(tenTaiKhoan.getText().toString())&&user.getPassword().equals(matKhau.getText().toString())){
                    luu.setUserName(tenTaiKhoan.getText().toString());
                    luu.setPassWord(matKhau.getText().toString());
                    luu.setStatus(KTluu);
                    dao_luudangnhap.Update(luu);
                    dto_user = user;
                    Intent intent = new Intent(getContext(), Quanlycongviec.class);
                   intent.putExtra("data",dto_user);
                    startActivity(intent);

                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    check=1;
                    break;
                }
            }
            if(check==0){
                Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
            }
        });

        quenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_quen_pass_xacthuc frag_quen_pass_xacthuc = activity.truyenPassXT();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_,frag_quen_pass_xacthuc).commit();
            }
        });

    }
}
