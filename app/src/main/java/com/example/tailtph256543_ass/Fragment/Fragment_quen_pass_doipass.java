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


public class Fragment_quen_pass_doipass extends Fragment {
    EditText matKhau,reMakhau;
    Button doiPass;
    DTO_User dto_user;
    DAO_User daoUser;
    MainActivity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quenmatkhau,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matKhau = view.findViewById(R.id.edt_pass_quen);
        reMakhau = view.findViewById(R.id.edt_repass_quen);
        doiPass = view.findViewById(R.id.btn_doipass);
        daoUser = new DAO_User(getContext());
        activity = (MainActivity) getContext();

        doiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(matKhau.getText().toString().equals(reMakhau.getText().toString())){
                    nhanDuLieu();
                    dto_user.setPassword(matKhau.getText().toString());
                    if(daoUser.UpdateRow(dto_user)>0){
                        Frag_dangnhap frag_dangnhap =new Frag_dangnhap();
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frag_,frag_dangnhap).commit();
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Mật khẩu nhập không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void nhanDuLieu(){
dto_user=activity.nhanData();
    }
}
