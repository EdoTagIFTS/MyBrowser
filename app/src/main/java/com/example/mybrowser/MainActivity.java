package com.example.mybrowser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SitiCursorAdapter sitiAdapter;
    private SitiDatabaseHelper databaseHelper;
    public static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new SitiDatabaseHelper(this);

        listView = (ListView) findViewById(R.id.listaSiti);
        sitiAdapter = new SitiCursorAdapter(MainActivity.this, databaseHelper.getAllData());
        listView.setAdapter(sitiAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) sitiAdapter.getItem(position);
                @SuppressLint("Range") int sitoId = cursor.getInt(cursor.getColumnIndex(SitiDatabaseHelper.SITI_TABLE_COLUMN_ID));
                Cursor urlCursor = databaseHelper.getUrlById(sitoId);
                if (urlCursor != null && urlCursor.moveToFirst()){
                    @SuppressLint("Range") String urlSito = urlCursor.getString(urlCursor.getColumnIndex(SitiDatabaseHelper.SITI_TABLE_COLUMN_URL));
                    startActivity(new Intent(MainActivity.this, WebActivity.class).putExtra("URL", urlSito));
                    urlCursor.close();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @SuppressLint("Range")
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) sitiAdapter.getItem(position);
                @SuppressLint("Range") int sitoId = cursor.getInt(cursor.getColumnIndex(SitiDatabaseHelper.SITI_TABLE_COLUMN_ID));
                String[] sito = new String[3];
                Cursor nomeCursor = databaseHelper.getNameById(sitoId);
                Cursor urlCursor = databaseHelper.getUrlById(sitoId);
                if(nomeCursor!=null && urlCursor!=null && urlCursor.moveToFirst() && nomeCursor.moveToFirst()) {
                    sito[0] = nomeCursor.getString(nomeCursor.getColumnIndex(SitiDatabaseHelper.SITI_TABLE_COLUMN_NAME));
                    sito[1] = urlCursor.getString(urlCursor.getColumnIndex(SitiDatabaseHelper.SITI_TABLE_COLUMN_URL));
                    sito[2] = String.valueOf(sitoId);
                    startActivity(new Intent(MainActivity.this, AddSitoActivity.class).putExtra("sito", sito));
                }
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sitiAdapter = new SitiCursorAdapter(MainActivity.this, databaseHelper.getAllData());
        listView.setAdapter(sitiAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addSito){
            startActivity(new Intent(MainActivity.this, AddSitoActivity.class));
        }
        return true;
    }
}