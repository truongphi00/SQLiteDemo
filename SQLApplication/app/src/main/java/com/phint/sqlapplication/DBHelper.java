package com.phint.sqlapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, age TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
        onCreate(db);
    }

    public Boolean insertData(String name, String contact, String age){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("age",age);
        contentValues.put("status","valid");
        long result = database.insert("Userdetails",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateData(String name, String contact, String age){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("age",age);
        Cursor cursor = database.rawQuery("Select name, contact, age, status from Userdetails where name = ?",new String[] {name});
        if (cursor.getCount()>0){
            int result = database.update("Userdetails",contentValues,"name=?",new String[] {name});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteData(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status","invalid");
        Cursor cursor = database.rawQuery("Select name, contact, age, status from Userdetails where name = ?",new String[] {name});
        if (cursor.getCount()>0){
            long result = database.update("Userdetails",contentValues,"name=?",new String[] {name});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteDataVer2(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select name, contact, age, status from Userdetails where name = ?",new String[] {name});
        if (cursor.getCount()>0){
            int result = database.delete("Userdetails","name=?",new String[] {name});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select name, contact, age from Userdetails where status='valid'",null);
        return cursor;
    }
}
