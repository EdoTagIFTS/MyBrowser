package com.example.mybrowser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, SitiDatabaseHelper.DATABASE_NAME, null, SitiDatabaseHelper.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String buildSQL = "CREATE TABLE " + SitiDatabaseHelper.TABLE_NAME + "(" + SitiDatabaseHelper.SITI_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " + SitiDatabaseHelper.SITI_TABLE_COLUMN_NAME + " TEXT, " + SitiDatabaseHelper.SITI_TABLE_COLUMN_URL + " TEXT)";
        Log.d(SitiDatabaseHelper.TAG, "onCreate SQL: " + buildSQL);
        db.execSQL(buildSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String buildSQL = "DROP TABLE IF EXIST " + SitiDatabaseHelper.TABLE_NAME;
        Log.d(SitiDatabaseHelper.TAG, "onUpgrade SQL: " + buildSQL);
        db.execSQL(buildSQL);
        onCreate(db);
    }
}
