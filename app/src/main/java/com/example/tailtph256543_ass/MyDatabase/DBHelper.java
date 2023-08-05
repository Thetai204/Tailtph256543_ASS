package com.example.tailtph256543_ass.MyDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String NAMEDB = "NAME";
    static final int VERTIONDB =1;
    public DBHelper (Context context){
        super(context,NAMEDB,null,VERTIONDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user = "CREATE TABLE Users (     id       INTEGER PRIMARY KEY AUTOINCREMENT                      UNIQUE                      NOT NULL,     username TEXT    UNIQUE                      NOT NULL,     email    TEXT    UNIQUE                      NOT NULL,     password TEXT    NOT NULL,     fullname TEXT    NOT NULL );";
        String Congviec = "CREATE TABLE tasks (     id      INTEGER PRIMARY KEY AUTOINCREMENT                     NOT NULL                     UNIQUE,     name    TEXT    NOT NULL,     content TEXT,     status  INTEGER NOT NULL                     DEFAULT (0),     start   TEXT    NOT NULL,     endl    TEXT    NOT NULL,     id_user INTEGER NOT NULL                     REFERENCES Users (id) )";
        String Firt = "CREATE TABLE phienChay (     solan   INTEGER PRIMARY KEY AUTOINCREMENT,     sophien INTEGER )";
        String luuDangNhap = "CREATE TABLE luuaccount (     id        INTEGER PRIMARY KEY,     username  TEXT,     pasword   TEXT,     trangthai INTEGER )";
        String ngaysua= "CREATE TABLE tb_lichsu (     id      INTEGER PRIMARY KEY AUTOINCREMENT,     ngaysua TEXT,     id_user INTEGER REFERENCES Users (id) );";

        db.execSQL(user);
        db.execSQL(Congviec);
        db.execSQL(Firt);
        db.execSQL(luuDangNhap);
        db.execSQL(ngaysua);
        db.execSQL("insert into Users values(1,'tai','luongthetai@gmail.com ','1','Luong the tai')");
        db.execSQL("insert into phienChay values (1,0)");
        db.execSQL("insert into luuaccount values(1,'ok','ok',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
