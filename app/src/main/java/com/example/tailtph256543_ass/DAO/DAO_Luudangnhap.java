package com.example.tailtph256543_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tailtph256543_ass.DTO.DTO_luu;
import com.example.tailtph256543_ass.MyDatabase.DBHelper;


public class DAO_Luudangnhap {
    DBHelper dbhelper;
    SQLiteDatabase db;

    public DAO_Luudangnhap(Context context) {
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public int Update(DTO_luu dto_luu) {
        ContentValues values = new ContentValues();
        values.put("username", dto_luu.getUserName());
        values.put("pasword", dto_luu.getPassWord());
        values.put("trangthai", dto_luu.getStatus());

        String[] index = new String[]{
                String.valueOf(dto_luu.getId())
        };
        return db.update("luuaccount", values, "id=?", index);
    }

    public DTO_luu getData(){
        DTO_luu data = new DTO_luu();
        Cursor c =db.rawQuery("select * from luuaccount",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
                data.setId(c.getInt(0));
                data.setUserName(c.getString(1));
                data.setPassWord(c.getString(2));
                data.setStatus(c.getInt(3));
        }
        return data;
    }
}
