package com.example.mybrowser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SitiDatabaseHelper {
    static final String TAG = SitiDatabaseHelper.class.getSimpleName();
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "sitidatabase.db";

    static final String TABLE_NAME = "siti_table";
    static final String SITI_TABLE_COLUMN_ID = "_id";
    static final String SITI_TABLE_COLUMN_NAME = "website_name";
    static final String SITI_TABLE_COLUMN_URL = "website_url";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    public SitiDatabaseHelper(Context context){
        openHelper = new DatabaseOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void updateData(String siteName, String siteURL, String siteId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SITI_TABLE_COLUMN_NAME, siteName);
        contentValues.put(SITI_TABLE_COLUMN_URL, siteURL);
        database.update(TABLE_NAME, contentValues, SITI_TABLE_COLUMN_ID+"=?", new String[]{siteId} );
    }

    public void insertData(String siteName, String siteURL){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SITI_TABLE_COLUMN_NAME, siteName);
        contentValues.put(SITI_TABLE_COLUMN_URL, siteURL);
        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData(){
        String buildSQL = "SELECT * FROM " + TABLE_NAME;
        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }

    public Cursor getUrlById(int id){
        String buildSQL = "SELECT " + SITI_TABLE_COLUMN_URL + " FROM " + TABLE_NAME + " WHERE " + SITI_TABLE_COLUMN_ID + "=?";
        Log.d(TAG, "getURL SQL: " + buildSQL);
        String[] selectionArgs = {String.valueOf(id)};
        return database.rawQuery(buildSQL, selectionArgs);
    }

    public Cursor getNameById(int id) {
        String buildSQL = "SELECT " + SITI_TABLE_COLUMN_NAME + " FROM " + TABLE_NAME + " WHERE " + SITI_TABLE_COLUMN_ID + "=?";
        Log.d(TAG, "getName SQL: " + buildSQL);
        String[] selectionArgs = {String.valueOf(id)};
        return database.rawQuery(buildSQL, selectionArgs);
    }
}
