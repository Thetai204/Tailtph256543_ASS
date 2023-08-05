package com.example.tailtph256543_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tailtph256543_ass.MyDatabase.DBHelper;


public class DAO_Phien {
    DBHelper dbhelper;
    SQLiteDatabase db;

    public DAO_Phien(Context context) {
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }
public int soPhien(){
        int so = 1;
    ContentValues values = new ContentValues();
    values.put("sophien",1);

    String [] index = new String[]{
            String.valueOf(so)
    };
        return db.update("phienChay",values,"solan=?",index);
}

public int so(){
        int a = 0;
    Cursor c = db.rawQuery("select * from phienChay",null);
    if(c!=null&&c.getCount()>0){
        c.moveToFirst();
        a=c.getInt(1);
    }
        return a;
}
}
