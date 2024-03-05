package com.example.mybrowser;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class AddSitoActivity extends AppCompatActivity {
    String[] dati = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sito);
        EditText nomeSitoView = findViewById(R.id.nomeSito);
        EditText urlSitoView = findViewById(R.id.urlSito);

        if(getIntent().getExtras()!=null){
            dati = getIntent().getExtras().getStringArray("sito");
        }

        if(dati!=null){
            nomeSitoView.setText(dati[0]);
            urlSitoView.setText(dati[1]);
        }

        final String[] sito = new String[3];
        nomeSitoView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sito[0] = s.toString();
            }
        });

        urlSitoView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sito[1] = s.toString();
            }
        });

        Button salva = findViewById(R.id.saveSitoBtn);

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SitiDatabaseHelper databaseHelper = new SitiDatabaseHelper(AddSitoActivity.this);
                if(dati==null){
                    databaseHelper.insertData(sito[0], sito[1]);
                }else {
                    databaseHelper.updateData(sito[0], sito[1], dati[2]);
                }
            }
        });
    }
}