package com.example.tailtph256543_ass.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tailtph256543_ass.Adapter.Adapter_cv;
import com.example.tailtph256543_ass.DAO.DAO_cv;
import com.example.tailtph256543_ass.DTO.DTO_cv;
import com.example.tailtph256543_ass.Quanlycongviec;
import com.example.tailtph256543_ass.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_all extends Fragment {
    RecyclerView lv_list;
    List<DTO_cv> list;
    List<DTO_cv> listCheck;
    SearchView sv_;
    ImageButton add;

    DTO_cv dto_task;
    DAO_cv dao_cv;
    Adapter_cv adapter_task;
    Calendar lich = Calendar.getInstance();
    int ngay_check,thang_check,nam_check;
    Quanlycongviec quanLyCongViec ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ql_congviec, container, false);
    }
public List<DTO_cv> loc(List<DTO_cv> list){
    List<DTO_cv> listCheck = new ArrayList<>();
    for (DTO_cv cv:list
    ) {
        if(cv.getStatus()!=-1){
            listCheck.add(cv);
        }
    }
    return listCheck;
}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_list = view.findViewById(R.id.rcv_congviec);

        add = view.findViewById(R.id.btn_add);
        quanLyCongViec = (Quanlycongviec) getContext();
        dao_cv = new DAO_cv(getContext());
        dto_task = new DTO_cv();
        list = dao_cv.getData(quanLyCongViec.Guidata().getId());

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lv_list.setLayoutManager(manager);
        listCheck = loc(list);
        adapter_task = new Adapter_cv(getContext(), listCheck,5,quanLyCongViec.Guidata().getId());
        lv_list.setAdapter(adapter_task);


sv_.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter_task.getFilter().filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter_task.getFilter().filter(newText);
        return true;
    }
});

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_cv, null, false);
                builder.setView(view1);
                EditText tieuDe = view1.findViewById(R.id.edt_tencongviec);
                EditText noiDung = view1.findViewById(R.id.edt_noidung);
                EditText start = view1.findViewById(R.id.edt_start);
                EditText end = view1.findViewById(R.id.edt_end);
                Button them = view1.findViewById(R.id.btn_add);

                Dialog dialog = builder.create();


                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ngay = lich.get(Calendar.DAY_OF_MONTH);
                        int thang = lich.get(Calendar.MONTH);
                        int nam = lich.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                start.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
                                ngay_check = dayOfMonth;
                                thang_check = month;
                                nam_check = year;
                            }
                        }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });

                end.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {



                        int ngay = lich.get(Calendar.DAY_OF_MONTH);
                        int thang = lich.get(Calendar.MONTH);
                        int nam = lich.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                if(ngay_check<=dayOfMonth&&thang_check<=month&&nam_check<=year){
                                    end.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
                                    Toast.makeText(getContext(), ""+view, Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getContext(), "Ngày kết thúc phải nhiều hơn ngày bắt đầu", Toast.LENGTH_SHORT).show();
                                }

                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });
                //ẩn background trắng của dialog
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!tieuDe.getText().toString().isEmpty()&&!noiDung.getText().toString().isEmpty()&&!start.getText().toString().isEmpty()&&!end.getText().toString().isEmpty()){
                            dto_task.setName(tieuDe.getText().toString());
                            dto_task.setConten(noiDung.getText().toString());
                            dto_task.setStart(start.getText().toString());
                            dto_task.setEnd(end.getText().toString());
                            dto_task.setStatus(0);
                            Quanlycongviec quanLyCongViec = (Quanlycongviec) getContext();
                            dto_task.setId_user(quanLyCongViec.Guidata().getId());
                            if (dao_cv.AddRow(dto_task) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                            listCheck.clear();
                            listCheck.addAll(loc(dao_cv.getData(quanLyCongViec.Guidata().getId())));

                            adapter_task.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }
}
