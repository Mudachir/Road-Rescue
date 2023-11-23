package com.example.roadrescue;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Dr. Silvia Hossain","Rehabilitation Specialist, LABAID",R.drawable.zahan));
        items.add(new Item("Palash Kumar Dey","Pediatric (Children) Surgery Specialist, EverCare",R.drawable.milon));
        items.add(new Item("Dr. Md. Mahmud Ali","Medicine & Diabetes Specialist, LABAID",R.drawable.ahsan));
        items.add(new Item("Dr. S.M. Abu Ahsan","Liver Diseases & Medicine Specialist, UNITED Hospital",R.drawable.rahman));
        items.add(new Item("Dr. K.M Zafrul Hossain Ripon","Piles & Hernia Surgery Specialist, LABAID",R.drawable.paik));





        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter3(getApplicationContext(),items));

    }
}