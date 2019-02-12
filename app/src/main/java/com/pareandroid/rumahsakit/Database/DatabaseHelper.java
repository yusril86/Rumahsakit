package com.pareandroid.rumahsakit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {

     static final String DB_NAME = "Dokterumum.db";
    public static final String DB_TABLE = "umum";

    //kolom
    public static final String Column_ID = "id";
    public static final String Column_Nama = "nama";
    public static final String Column_Jenis_kelamin = "jenis_kelamin";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
                Column_ID + " INTEGER PRIMARY KEY autoincrement, " +
                Column_Nama + " TEXT NOT NULL, " +
                Column_Jenis_kelamin + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(db);

    }

    public ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + DB_TABLE;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Column_ID, cursor.getString(0));
                map.put(Column_Nama, cursor.getString(1));
                map.put(Column_Jenis_kelamin, cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

//Tambah data
    public void tambahkandata(String nama,String jenis_kelamin) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + DB_TABLE + " (nama , jenis_kelamin)" +
                "VALUES ('" + nama + "', '" + jenis_kelamin + "')";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();

    }

    //Update Data
    public void update(int id, String nama, String jenis_kelamin) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + DB_TABLE + " SET "
                + Column_Nama + "='" + nama + "', "
                + Column_Jenis_kelamin + "='" + jenis_kelamin + "'"
                + " WHERE " + Column_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    //Hapus data
    public void delete(int id ) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + DB_TABLE + " WHERE " + Column_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}
