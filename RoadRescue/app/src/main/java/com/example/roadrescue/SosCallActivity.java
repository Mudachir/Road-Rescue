package com.example.roadrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SosCallActivity extends AppCompatActivity {

    EditText txt_phonenumber;
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_call);

        txt_phonenumber =(EditText) findViewById(R.id.txt_number);
        btn_call = (Button) findViewById(R.id.btn_call);


        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phoneNumber");


        txt_phonenumber.setText(phoneNumber);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcall = new Intent(Intent.ACTION_CALL);
                String number = txt_phonenumber.getText().toString();
                if(number.trim().isEmpty()){
                    Toast.makeText(SosCallActivity.this, "Please enter number",Toast.LENGTH_SHORT).show();

                }
                else{
                    intentcall.setData(Uri.parse("tel:"+number));
                }

                if(ActivityCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(SosCallActivity.this, "Please grant permission",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intentcall);
                }
            }
        });
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(SosCallActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);

    }
    }
