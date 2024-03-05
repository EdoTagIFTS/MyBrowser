package com.example.mybrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class SitiCursorAdapter extends CursorAdapter {
    public SitiCursorAdapter(Context context, Cursor cursor){
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.siti_adapter, parent, false);
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewSitiName = (TextView) view.findViewById(R.id.siteName);
        textViewSitiName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
    }
}
