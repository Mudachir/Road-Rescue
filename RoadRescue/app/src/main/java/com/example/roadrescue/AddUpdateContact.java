package com.example.roadrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddUpdateContact extends AppCompatActivity implements View.OnClickListener {

    private ImageView profiledp;
    private EditText nameEdittxt, phoneEdittxt, emailEdittxt;
    private FloatingActionButton fab;

    private String id, contactName, contactPhone, contactEmail;
    private boolean EditMode;

    SavedContactsHelper savedContactsHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_contact);


        savedContactsHelper = new SavedContactsHelper( this);
        SQLiteDatabase sqLiteDatabase = savedContactsHelper.getWritableDatabase();




        ImageView backicon = findViewById(R.id.backicon);

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddUpdateContact.this,SosContactActivity.class));
            }
        });



        profiledp = findViewById(R.id.profiledp);
        nameEdittxt = (EditText) findViewById(R.id.nameEdittxt);
        phoneEdittxt = (EditText) findViewById(R.id.phoneEdittxt);
        emailEdittxt = (EditText) findViewById(R.id.emailEdittxt);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        Intent intent = getIntent();
        EditMode = intent.getBooleanExtra("EditMode",false);

        if(EditMode){
            id = intent.getStringExtra("ID");
            contactName = intent.getStringExtra("NAME");
            contactPhone = intent.getStringExtra("PHONE");
            contactEmail = intent.getStringExtra("EMAIL");

            nameEdittxt.setText(contactName);
            phoneEdittxt.setText(contactPhone);
            emailEdittxt.setText(contactEmail);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactName = nameEdittxt.getText().toString();
                contactPhone = phoneEdittxt.getText().toString();
                contactEmail = emailEdittxt.getText().toString();



                if ((!contactName.isEmpty() || !contactPhone.isEmpty() || !contactEmail.isEmpty())){

                    if(EditMode){
                       savedContactsHelper.editContact(
                                id,contactName,contactPhone,contactEmail

                        );
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

                    }else {
                        long id = savedContactsHelper.addContact(
                                contactName,contactPhone,contactEmail

                        );

                        Toast.makeText(getApplicationContext(), "Contact Saved"+id, Toast.LENGTH_SHORT).show();

                    }

                }
                else {
                    Toast.makeText(AddUpdateContact.this, "Please fill out the details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {

    }




}