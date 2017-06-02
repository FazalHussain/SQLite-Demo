package com.cds.sqlitedemo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(this);
        //db.UpdateRecord("1","Anas#%)(&","$50");
        //db.DeleteRecord("1");
        //db.DeleteAll();
        Cursor cursor = db.getData();
        if(cursor.getCount()>0){
            StringBuffer sb = new StringBuffer();
            while (cursor.moveToNext()){
                sb.append(cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n\n");
            }

            new AlertDialog.Builder(this)
                    .setTitle("Data")
                    .setMessage(sb)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        }

    }
}
