package com.example.roadrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private TextView nameTv, phoneTv, emailTv;

    private String id;
    private  SavedContactsHelper savedContactsHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        savedContactsHelper = new SavedContactsHelper(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");



        nameTv = findViewById(R.id.nameTv);
        phoneTv = findViewById(R.id.phone_tv);
        emailTv = findViewById(R.id.email_tv);

        LoadDataById();

    }

    private void LoadDataById(){

        String selectQuery = "SELECT * FROM "+ SavedContacts.TABLE_NAME + " WHERE " + SavedContacts.CONTACT_ID +"=\"" +id + "\"";
        SQLiteDatabase db = savedContactsHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {


                      String name =  ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_NAME));
                      String phone =  ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_PHONE));
                      String email =  ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_EMAIL));

                      nameTv.setText(name);
                      phoneTv.setText(phone);
                      emailTv.setText(email);


            }while (cursor.moveToNext());
        }
    }


}