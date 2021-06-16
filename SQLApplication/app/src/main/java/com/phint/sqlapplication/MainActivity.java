package com.phint.sqlapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtContact, edtAge;
    Button btnInsert, btnUpdate, btnDelete, btnView, btnDeleteVer2;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtContact = findViewById(R.id.edtContact);
        edtAge = findViewById(R.id.edtAge);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnViewAll);
        btnDeleteVer2 = findViewById(R.id.btnDeleteVer2);

        dbHelper = new DBHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String contact = edtContact.getText().toString();
                String age = edtAge.getText().toString();

                boolean checkInsertData = dbHelper.insertData(name,contact,age);
                if(checkInsertData){
                    Toast.makeText(MainActivity.this, "New Data Inserted!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "New Data Not Inserted!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String contact = edtContact.getText().toString();
                String age = edtAge.getText().toString();

                boolean checkUpdateData = dbHelper.updateData(name,contact,age);
                if(checkUpdateData){
                    Toast.makeText(MainActivity.this, "Data Updated!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Updated!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();

                boolean checkDeleteData = dbHelper.deleteData(name);
                if(checkDeleteData){
                    Toast.makeText(MainActivity.this, "Data Deleted!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Deleted!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDeleteVer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();

                boolean checkDeleteData = dbHelper.deleteDataVer2(name);
                if(checkDeleteData){
                    Toast.makeText(MainActivity.this, "Data Deleted!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Deleted!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getData();
                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "There Is No Data!", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("Name: "+cursor.getString(0)+"\n");
                    buffer.append("Contact: "+cursor.getString(1)+"\n");
                    buffer.append("Age: "+cursor.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Users Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}