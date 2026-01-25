package com.example.mynewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String Db_name="demo_db";
    private static final int Db_version=1;


    public DBHelper(@Nullable Context context) {
        super(context, Db_name, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY= "CREATE TABLE register (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, gender TEXT)";
      db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS register");
      onCreate(db);
    }

   public boolean registerUserHelper(String name, String email, String password, String gender){
        SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("name",name);
       values.put("email",email);
       values.put("password",password);
       values.put("gender",gender);

       long result=db.insert("register",null,values);
       db.close();
       if(result==-1){
           return false;
       }else{
           return true;
       }

   }


    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query: Check karo ki kya koi user is email aur password ke saath exist karta hai?
        // COLUMN names wahi use karein jo aapne table banate waqt use kiye the (e.g., "email", "password")
        Cursor cursor = db.rawQuery("SELECT * FROM register WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true; // User mil gaya!
        } else {
            cursor.close();
            return false; // Galat email ya password
        }
    }
}
