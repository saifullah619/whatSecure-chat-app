package com.murtaza.infosecuritychatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Chat.db";
    public static final int DB_VER = 1;
    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE USERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(create);

        insertData("Murtaza", "murtazahassnain17@gmail.com", "abcd", sqLiteDatabase);
        insertData("Saifullah","saifullah@gmail.com", "abcd", sqLiteDatabase);
        insertData("Raza","raza@gmail.com", "abcd", sqLiteDatabase);
    }

    public Boolean insertData(String name, String email, String password, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        double result = database.insert("USERS", null, values);
        return result != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
