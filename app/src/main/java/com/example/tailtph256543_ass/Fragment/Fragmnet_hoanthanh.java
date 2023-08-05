package com.example.tailtph256543_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;

public class Fragmnet_hoanthanh extends Fragment {
    RecyclerView lv_list;
    List<DTO_cv> list;
    List<DTO_cv> listCheck;
    SearchView sv_;
    DTO_cv dto_cv;
    DAO_cv dao_cv;
    Adapter_cv adapter_cv;
    Quanlycongviec quanLyCongViec ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cong_viec_hoan_thanh,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_list = view.findViewById(R.id.rcv_congviechoanthanh);

        quanLyCongViec = (Quanlycongviec) getContext();
        dao_cv = new DAO_cv(getContext());
        dto_cv = new DTO_cv();
        list = dao_cv.getData(quanLyCongViec.Guidata().getId());

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lv_list.setLayoutManager(manager);
        listCheck = loc(list);
        adapter_cv = new Adapter_cv(getContext(), listCheck,2,quanLyCongViec.Guidata().getId());
        lv_list.setAdapter(adapter_cv);

        sv_.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_cv.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_cv.getFilter().filter(newText);
                return true;
            }
        });
    }
    public List<DTO_cv> loc(List<DTO_cv> list){
        List<DTO_cv> listCheck = new ArrayList<>();
        for (DTO_cv task:list) {
            if(task.getStatus()==2){
                listCheck.add(task);
            }
        }
        return listCheck;
    }
}
