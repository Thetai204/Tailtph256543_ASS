package com.example.tailtph256543_ass.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tailtph256543_ass.DTO.DTO_ngaysua;
import com.example.tailtph256543_ass.R;

import java.util.List;

public class Adapter_lichsu extends RecyclerView.Adapter<Adapter_lichsu.ViewHoder>{
Context context;
    List<DTO_ngaysua> list;

    public Adapter_lichsu(Context context, List<DTO_ngaysua> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sua,null,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
holder.ngaysua.setText("Ngày chỉnh sửa "+list.get(position).getNgaySua());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
TextView ngaysua;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ngaysua = itemView.findViewById(R.id.tv_chinhsua);
        }
    }
}
