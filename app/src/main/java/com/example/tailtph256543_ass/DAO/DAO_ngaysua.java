package com.example.tailtph256543_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.DTO.DTO_ngaysua;
import com.example.tailtph256543_ass.MyDatabase.DBHelper;


import java.util.ArrayList;
import java.util.List;

public class DAO_ngaysua {
    DBHelper dbhelper;
    SQLiteDatabase db ;

    public DAO_ngaysua(Context context){
        dbhelper =new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public Long them(DTO_ngaysua dto_ngaysua){
        ContentValues values = new ContentValues();
        values.put("ngaysua",dto_ngaysua.getNgaySua());
        values.put("id_user",dto_ngaysua.getId_user());
        return db.insert("tb_lichsu",null,values);
    }

    public List<DTO_ngaysua> getData (DTO_User user){
        List<DTO_ngaysua> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_lichsu where id_user = "+user.getId(),null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                DTO_ngaysua dto_ngaysua = new DTO_ngaysua();
                dto_ngaysua.setId(c.getInt(0));
                dto_ngaysua.setNgaySua(c.getString(1));
                dto_ngaysua.setId_user(c.getInt(2));
                list.add(dto_ngaysua);
            }while (c.moveToNext());
        }
        return list;
    }
}
