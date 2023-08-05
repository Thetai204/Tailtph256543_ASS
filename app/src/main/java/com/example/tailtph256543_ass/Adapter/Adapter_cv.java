package com.example.tailtph256543_ass.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tailtph256543_ass.DAO.DAO_cv;
import com.example.tailtph256543_ass.DTO.DTO_cv;
import com.example.tailtph256543_ass.Fragment.Frag_bixoa;
import com.example.tailtph256543_ass.Fragment.Fragment_all;
import com.example.tailtph256543_ass.Fragment.Fragment_chuahoanthanh;
import com.example.tailtph256543_ass.Fragment.Fragment_moi;
import com.example.tailtph256543_ass.Fragment.Fragmnet_hoanthanh;
import com.example.tailtph256543_ass.Quanlycongviec;
import com.example.tailtph256543_ass.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Adapter_cv extends RecyclerView.Adapter<Adapter_cv.ViewHolder> implements Filterable {
    Context context;
    List<DTO_cv> list;
    List<DTO_cv> listOld;
    Fragment_all frag_all;
    Fragment_moi frag_moi;
    Frag_bixoa frag_bixoa;
    Fragmnet_hoanthanh frag_hoanthanh;
    Fragment_chuahoanthanh frag_chuahoanthanh;

    DAO_cv dao_cv;
    DTO_cv dto_cv;
    Calendar lich = Calendar.getInstance();
    int frag_status = 0;
    int ngay_check, thang_check, nam_check;
    int idUser = 0;


    public Adapter_cv(Context context, List<DTO_cv> list, int sta, int id_user) {
        this.context = context;
        this.list = list;
        listOld = list;
        frag_status = sta;
        idUser=id_user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_congviec, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());
        holder.status.setText(status(list.get(position).getStatus()));
        dto_cv = list.get(position);
        dao_cv= new DAO_cv(context);
        frag_all = new Fragment_all();
        frag_chuahoanthanh = new Fragment_chuahoanthanh();
        frag_hoanthanh = new Fragmnet_hoanthanh();
        frag_bixoa = new Frag_bixoa();
        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dto_cv=list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Trạng thái");
                String[] st = new String[]{
                        "Mới tạo", "Đang làm", "Hoàn thành"
                };
                builder.setItems(st, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            dto_cv.setStatus(0);
                        } else if (which == 1) {
                            dto_cv.setStatus(1);
                        } else if (which == 2) {
                            dto_cv.setStatus(2);
                        }
                        if (dao_cv.UpdateRow(dto_cv) > 0) {
                            Toast.makeText(context, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            checkFrag(frag_status);
                            dialog.dismiss();

                        } else {
                            Toast.makeText(context, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                Dialog dialog1 = builder.create();
                dialog1.show();
            }
        });
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_more, null, false);
                builder.setView(view);
                Button sua = view.findViewById(R.id.btn_update_task);
                Button xoa = view.findViewById(R.id.btn_xoa_task);
                Button chitiet = view.findViewById(R.id.btn_xemchitiet);
                Dialog dialog = builder.create();
                dialog.show();
                if(frag_status==-1){
                    xoa.setVisibility(View.GONE);
                }

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.dialog_add_cv, null, false);
                        builder.setView(view1);
                        TextView tenbang = view1.findViewById(R.id.tv_title_ns);
                        EditText tieuDe = view1.findViewById(R.id.edt_tencongviec);
                        EditText noiDung = view1.findViewById(R.id.edt_noidung);
                        EditText start = view1.findViewById(R.id.edt_start);
                        EditText end = view1.findViewById(R.id.edt_end);
                        Button them = view1.findViewById(R.id.btn_add);
                        Dialog dialog1 = builder.create();
                        dialog1.show();

                        tenbang.setText("Sửa công việc");
                        tieuDe.setText(dto_cv.getName());
                        noiDung.setText(dto_cv.getConten());
                        start.setText(dto_cv.getStart());
                        end.setText(dto_cv.getEnd());
                        them.setText("Sửa");


                        start.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int ngay = lich.get(Calendar.DAY_OF_MONTH);
                                int thang = lich.get(Calendar.MONTH);
                                int nam = lich.get(Calendar.YEAR);

                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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

                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        if (ngay_check <= dayOfMonth && thang_check <= month && nam_check <= year) {
                                            end.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
                                            Toast.makeText(context, "" + view, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Ngày kết thúc phải nhiều hơn ngày bắt đầu", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }, nam, thang, ngay);
                                datePickerDialog.show();
                            }
                        });
                        //ẩn background trắng của dialog
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        them.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!tieuDe.getText().toString().isEmpty() && !noiDung.getText().toString().isEmpty() && !start.getText().toString().isEmpty() && !end.getText().toString().isEmpty()) {
                                    dto_cv.setName(tieuDe.getText().toString());
                                    dto_cv.setConten(noiDung.getText().toString());
                                    dto_cv.setStart(start.getText().toString());
                                    dto_cv.setEnd(end.getText().toString());

                                    Quanlycongviec quanLyCongViec = (Quanlycongviec) context;
                                    dto_cv.setId_user(quanLyCongViec.Guidata().getId());
                                    if (dao_cv.UpdateRow(dto_cv) > 0) {
                                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        dialog1.dismiss();

                                    } else {
                                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                    checkFrag(frag_status);
                                } else {
                                    Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                        View view1 = inflater1.inflate(R.layout.dialog_xac_nhan, null, false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();

                        ImageButton xacNhan = view1.findViewById(R.id.ibtn_dongy);
                        ImageButton khong = view1.findViewById(R.id.ibtn_khong);

                        xacNhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dto_cv.setStatus(-1);
                                if (dao_cv.UpdateRow(dto_cv) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();


                                  checkFrag(frag_status);
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                                dialog1.dismiss();
                            }
                        });

                        khong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });

                    }
                });

                chitiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                        View view1 = inflater1.inflate(R.layout.dialog_ctcv, null, false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView tenCV = view1.findViewById(R.id.tv_tencv_ct);
                        TextView noidungCV = view1.findViewById(R.id.tv_ndcv_ct);
                        TextView trangthaiCV = view1.findViewById(R.id.tv_trangthaicv_ct);
                        TextView startCV = view1.findViewById(R.id.tv_startcv_ct);
                        TextView endCV = view1.findViewById(R.id.tv_endcv_ct);
                        Button dong = view1.findViewById(R.id.btn_close);

                        tenCV.setText(dto_cv.getName());
                        noidungCV.setText(dto_cv.getConten());
                        trangthaiCV.setText(status(dto_cv.getStatus()));
                        startCV.setText(dto_cv.getStart());
                        endCV.setText(dto_cv.getEnd());

                        dong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
            }


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.toString().isEmpty()) {
                    list = listOld;
                } else {
                    List<DTO_cv> listnew = new ArrayList<>();
                    for (DTO_cv task : listOld
                    ) {
                        if (constraint.toString().toLowerCase().contains(task.getName())) {
                            listnew.add(task);
                        }
                    }
                    list = listnew;

                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<DTO_cv>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, status;
        ImageButton menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_ten_cv);
            status = itemView.findViewById(R.id.tv_trang_thai);
            menu = itemView.findViewById(R.id.Setting);

        }
    }

    public String status(int st) {
        String status = "Đang làm";
        if (st == 0) {
            status = "Mới";
        } else if (st == 2) {
            status = "Hoàn thành";
        } else if (st == -1) {
            status = "Xóa";
        }
        return status;
    }
    public void checkFrag(int a){
        if(a==-1){
            list.clear();
            list.addAll(frag_bixoa.loc(dao_cv.getData(idUser)));
            notifyDataSetChanged();
        }else if(a==0){
            list.clear();
            list.addAll(frag_moi.loc(dao_cv.getData(idUser)));
            notifyDataSetChanged();
        }else if(a==1){
            list.clear();
            list.addAll(frag_chuahoanthanh.loc(dao_cv.getData(idUser)));
            notifyDataSetChanged();
        }else if(a==2){
            list.clear();
            list.addAll(frag_hoanthanh.loc(dao_cv.getData(idUser)));
            notifyDataSetChanged();
        }else {
            list.clear();
            list.addAll(frag_all.loc(dao_cv.getData(idUser)));
            notifyDataSetChanged();
        }
    }
}
