package com.example.tailtph256543_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tailtph256543_ass.DTO.DTO_cv;
import com.example.tailtph256543_ass.MyDatabase.DBHelper;


import java.util.ArrayList;
import java.util.List;

public class DAO_cv {
    DBHelper dbhelper;
    SQLiteDatabase db;
    public DAO_cv(Context context){
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long AddRow(DTO_cv cv){
        ContentValues values = new ContentValues();
        values.put("name",cv.getName());
        values.put("content",cv.getConten());
        values.put("status",cv.getStatus());
        values.put("start",cv.getStart());
        values.put("endl",cv.getEnd());
        values.put("id_user",cv.getId_user());

        return db.insert("tasks",null,values);
    }

    public int DeleteRow(DTO_cv cv){
        String [] index = new String[]{
                String.valueOf(cv.getId())
        };
        return db.delete("tasks","id=?",index);
    }

    public int UpdateRow(DTO_cv cv){
        ContentValues values = new ContentValues();
        values.put("id",cv.getId());
        values.put("name",cv.getName());
        values.put("content",cv.getConten());
        values.put("status",cv.getStatus());
        values.put("start",cv.getStart());
        values.put("endl",cv.getEnd());
        values.put("id_user",cv.getId_user());

        String [] index = new String[]{
                String.valueOf(cv.getId())
        };

        return db.update("tasks",values,"id=?",index);
    }

    public List<DTO_cv> getData(int id_user){
        List<DTO_cv> list = new ArrayList<>();
        Cursor c =db.rawQuery("select * from tasks where id_user = "+ id_user,null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                DTO_cv task = new DTO_cv();
                task.setId(c.getInt(0));
                task.setName(c.getString(1));
                task.setConten(c.getString(2));
                task.setStatus(c.getInt(3));
                task.setStart(c.getString(4));
                task.setEnd(c.getString(5));
                task.setId_user(c.getInt(6));
                list.add(task);
            }while (c.moveToNext());
        }
        return list;
    }
}
