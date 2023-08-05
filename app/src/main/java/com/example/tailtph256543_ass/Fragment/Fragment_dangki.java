package com.example.tailtph256543_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tailtph256543_ass.DAO.DAO_User;
import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.R;

public class Fragment_dangki extends Fragment {
    EditText taiKhoan, fullname, password, nlpassword, email;
    Button dangKy;


    DTO_User dto_user;
    DAO_User daoUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dangky, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taiKhoan = view.findViewById(R.id.edt_tentaikhoan);
        fullname = view.findViewById(R.id.edt_fullname);
        password = view.findViewById(R.id.edt_password);
        nlpassword = view.findViewById(R.id.edt_nlpassword);
        email = view.findViewById(R.id.edt_email);


        dangKy = view.findViewById(R.id.btn_dangky);


        dangKy.setEnabled(false);


        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDangKy();


            }

        });
    }

    public void setDangKy() {
        daoUser = new DAO_User(getContext());
        String checkEmail = "[aA0-zZ9]+@+[a-z]+.+[a-z]{2,3}";
        if (taiKhoan.getText().toString().isEmpty() || fullname.getText().toString().isEmpty() || password.getText().toString().isEmpty() || nlpassword.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Không được để trống , vui lòng thử lại", Toast.LENGTH_SHORT).show();
        } else if (!email.getText().toString().matches(checkEmail)) {
            Toast.makeText(getContext(), "Định dạng email không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!password.getText().toString().equals(nlpassword.getText().toString())) {
            Toast.makeText(getContext(), "Mật khẩu không khớp, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
        } else {
            dto_user = new DTO_User();

            dto_user.setUsername(taiKhoan.getText().toString());
            dto_user.setPassword(fullname.getText().toString());
            dto_user.setEmail(password.getText().toString());
            dto_user.setFullname(email.getText().toString());
            if (daoUser.AddRow(dto_user) > 0) {
                Frag_dangnhap frag_dangnhap = new Frag_dangnhap();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_, frag_dangnhap).commit();
                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }

        }
    }
}




