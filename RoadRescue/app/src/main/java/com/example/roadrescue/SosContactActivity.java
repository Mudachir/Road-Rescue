package com.example.roadrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SosContactActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView contactRv;
    private ImageView backicon;

    private SavedContactsHelper savedContactsHelper;

    private AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_contact);

        savedContactsHelper = new SavedContactsHelper(this);

        fab = findViewById(R.id.fab);
        contactRv = findViewById(R.id.contactRv);
        contactRv.setHasFixedSize(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SosContactActivity.this, AddUpdateContact.class);
                intent.putExtra("EditMode",false);
                startActivity(intent);
            }
        });

        loadData();





    }

    private void loadData() {
        adapterContact = new AdapterContact(this,savedContactsHelper.getAllData());
        contactRv.setAdapter(adapterContact);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }
}