package com.example.tailtph256543_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tailtph256543_ass.DTO.DTO_User;
import com.example.tailtph256543_ass.MyDatabase.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_User {
    DBHelper dbhelper;
    SQLiteDatabase db;

    public DAO_User(Context context) {
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long AddRow(DTO_User dto_user) {
        ContentValues values = new ContentValues();
        values.put("username", dto_user.getUsername());
        values.put("email", dto_user.getEmail());
        values.put("password", dto_user.getPassword());
        values.put("fullname", dto_user.getFullname());
        return db.insert("Users", null, values);
    }

    public int DeleteRow(DTO_User dto_user) {
        String[] index = new String[]{
                String.valueOf(dto_user.getId())
        };
        return db.delete("Users", "id=?", index);
    }

    public int UpdateRow(DTO_User dto_user) {
        ContentValues values = new ContentValues();
        values.put("id", dto_user.getId());
        values.put("username", dto_user.getUsername());
        values.put("email", dto_user.getEmail());
        values.put("password", dto_user.getPassword());
        values.put("fullname", dto_user.getFullname());

        String[] index = new String[]{
                String.valueOf(dto_user.getId())
        };

        return db.update("Users", values, "id=?", index);
    }

    public List<DTO_User> getAll() {
        List<DTO_User> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Users", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(0);
                String username = c.getString(1);
                String email = c.getString(2);
                String password = c.getString(3);
                String fullname = c.getString(4);
                DTO_User user = new DTO_User(id, username, email, password, fullname);
                list.add(user);
            } while (c.moveToNext());
        }
        return list;
    }
}
