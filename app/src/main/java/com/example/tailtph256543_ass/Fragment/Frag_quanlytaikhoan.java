package com.example.tailtph256543_ass.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tailtph256543_ass.Adapter.Adapter_lichsu;
import com.example.tailtph256543_ass.DAO.DAO_User;
import com.example.tailtph256543_ass.DAO.DAO_ngaysua;
import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.DTO.DTO_ngaysua;
import com.example.tailtph256543_ass.Quanlycongviec;
import com.example.tailtph256543_ass.R;


import java.util.Calendar;
import java.util.List;

public class Frag_quanlytaikhoan extends Fragment {

    TextView matKhau, fullname, username;
    RecyclerView rc_list;
    Button chinhSua, xemct;
    ImageButton showOrhide;

    DTO_User user;
    Quanlycongviec activity;
    int a = 0;

    Adapter_lichsu lichsu;
    List<DTO_ngaysua> list;
    DAO_ngaysua ngaysua;
    Calendar calendar;
    int ngay;
    int thang;
    int nam;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlytaikhoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fullname = view.findViewById(R.id.tv_fullname);
        username = view.findViewById(R.id.tv_username);

        rc_list = view.findViewById(R.id.rc_lichsu);
        chinhSua = view.findViewById(R.id.btn_chinhsua);

        ngaysua = new DAO_ngaysua(getContext());
         xemct =view.findViewById(R.id.btn_xemchitiet);
        activity = (Quanlycongviec) getContext();
        user = activity.Guidata();
        list = ngaysua.getData(user);
        lichsu = new Adapter_lichsu(getContext(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(manager);
        rc_list.setAdapter(lichsu);
        fullname.setText(user.getFullname());
        username.setText(user.getUsername());

//     xemct.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//
//    }
//});
        chinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_suataikhoan, null, false);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                EditText tenND = view1.findViewById(R.id.edt_tennguoidung);
                EditText pass = view1.findViewById(R.id.edt_newPass);
                EditText rePass = view1.findViewById(R.id.edt_reNewPass);
                EditText hoTen = view1.findViewById(R.id.edt_fullname);
                Button sua = view1.findViewById(R.id.btn_sua);
                calendar = Calendar.getInstance();
                ngay = calendar.get(Calendar.DAY_OF_MONTH);
                thang = calendar.get(Calendar.MONTH)+1;
                nam = calendar.get(Calendar.YEAR);

                tenND.setText(user.getUsername());
                hoTen.setText(user.getFullname());

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setTitle("Cảnh báo");
                        builder1.setMessage("Bạn có muốn lưu thay đổi này ?");
                        builder1.setIcon(R.drawable.baseline_warning_24);

                        builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (pass.getText().toString().equals(rePass.getText().toString())) {
                                    DAO_User daoUser = new DAO_User(getContext());
                                    user.setUsername(tenND.getText().toString());
                                    user.setPassword(pass.getText().toString());
                                    user.setFullname(hoTen.getText().toString());
                                    if (daoUser.UpdateRow(user) > 0) {
                                        DTO_ngaysua dto_ngaysua = new DTO_ngaysua();
                                        dto_ngaysua.setNgaySua(String.format("%d/%d/%d",ngay,thang,nam));
                                        dto_ngaysua.setId_user(user.getId());
                                        ngaysua.them(dto_ngaysua);
                                        list.clear();
                                        list.addAll(ngaysua.getData(user));
                                        lichsu.notifyDataSetChanged();
                                        Toast.makeText(getContext(), "Hoàn tất chỉnh sửa", Toast.LENGTH_SHORT).show();
                                        dialog1.dismiss();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(getContext(), "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Mật khẩu mới và mật khuẩu nhập lại không khớp nhau", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        Dialog dialog1 = builder1.create();
                        dialog1.show();

                    }
                });


            }
        });
    }
}
