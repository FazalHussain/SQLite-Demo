package com.cds.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fazal on 5/18/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_Name = "student.db";
    public static final String Tables_Name = "StudentRecord";


    public DatabaseHelper(Context context) {
        super(context, DB_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Tables_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Marks TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Tables_Name);
        onCreate(db);
    }

    public boolean insertData(String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Marks",marks);

        long result = db.insert(Tables_Name,null,contentValues);
        return result!=-1 ? true : false;

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ Tables_Name,null);
        return cursor;
    }
    //Update Record
    public boolean UpdateRecord(String id,String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("Name",name);
        contentValues.put("Marks",marks);

        db.update(Tables_Name,contentValues,"ID = ?",new String[]{ id });
        return true;

    }

    //Delete Record
    public boolean DeleteRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tables_Name,"ID = ?",new String[]{ id });
        return true;

    }

    //Delete Allo
    public boolean DeleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+Tables_Name);
        return true;

    }
}
