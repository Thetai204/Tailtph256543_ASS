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
import com.example.tailtph256543_ass.MainActivity;
import com.example.tailtph256543_ass.R;


import java.util.List;

public class Fragment_quen_pass_xacthuc extends Fragment {
    EditText taiKhoan, Email;
    Button xacThuc;
    List<DTO_User> list;
    DAO_User daoUser;
    DTO_User dto_user;
    MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quenmatkhau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taiKhoan = view.findViewById(R.id.edt_tentaikhoan_quen);
        Email = view.findViewById(R.id.edt_email_quen);
        xacThuc = view.findViewById(R.id.btn_xacthuc);
        daoUser = new DAO_User(getContext());
        list = daoUser.getAll();
        activity = (MainActivity) getContext();

        xacThuc.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {
                for (DTO_User user : list) {
                    if (user.getUsername().equals(taiKhoan.getText().toString()) && user.getEmail().equals(Email.getText().toString())) {

                        Fragment_quen_pass_doipass frag_quen_pass_doipass = activity.truyenPassDP();
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        i = 0;
                        dto_user = user;
                        manager.beginTransaction().replace(R.id.frag_, frag_quen_pass_doipass).commit();


                        break;
                    } else if (user.getUsername().equals(taiKhoan.getText().toString()) && !user.getEmail().equals(Email.getText().toString())) {
                        Toast.makeText(getContext(), "Email không đúng", Toast.LENGTH_SHORT).show();

                    } else if (!user.getUsername().equals(taiKhoan.getText().toString()) && !user.getEmail().equals(Email.getText().toString())) {
                        i = 1;
                    }
                }
                if (i == 1) {
                    Toast.makeText(getContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public DTO_User guidata() {
        return dto_user;
    }
}
