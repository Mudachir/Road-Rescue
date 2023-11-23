package com.example.roadrescue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HospitalActivity extends AppCompatActivity {
    CardView DocCard, TestCard, HosCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        TestCard = findViewById(R.id.TestCard);
        HosCard = findViewById(R.id.HosCard);
        DocCard = findViewById(R.id.DocCard);
        DocCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalActivity.this, Doctor.class);
                startActivity(intent);
            }
        });

        TestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        HosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalActivity.this, NearByHospital.class);
                startActivity(intent);
            }
        });


    }
}