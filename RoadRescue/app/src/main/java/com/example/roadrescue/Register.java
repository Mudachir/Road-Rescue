package com.example.roadrescue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword, editTextUsername;
    Button buttonReg;

    CircleImageView rg_profileimg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    Uri imageURI;
    String imageuri;

    FirebaseDatabase database;
    FirebaseStorage storage;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        editTextEmail= findViewById(R.id.rgemail);
        editTextPassword= findViewById(R.id.rgpassword);
        editTextUsername=findViewById(R.id.rgusername);
        buttonReg = findViewById(R.id.btn_register);
        rg_profileimg =  findViewById(R.id.profilerg);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        rg_profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),10);

            }
        });



        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password,username;
                email = String.valueOf(editTextEmail.getText().toString());
                password = String.valueOf(editTextPassword.getText().toString());
                username = String.valueOf(editTextUsername.getText().toString());
                String status = "Hey I'm using this Application";

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this, "Enter username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    String id = task.getResult().getUser().getUid();
                                    DatabaseReference reference = database.getReference().child("user").child(id);
                                    StorageReference storageReference = storage.getReference().child("Upload").child(id);

                                    if(imageURI!=null){
                                        storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            imageuri = uri.toString();
                                                            Users users = new Users(id,username,email,password,imageuri,status);
                                                            reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful()){
                                                                        Toast.makeText(Register.this, "Account created",
                                                                                Toast.LENGTH_SHORT).show();
                                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else {
                                                                        Toast.makeText(Register.this, "Authentication failed.",
                                                                                Toast.LENGTH_SHORT).show();

                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                    else{
                                        String status = "Hey I'm using this Application";
                                        imageuri = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-c9fbf.appspot.com/o/man.jpg?alt=media&token=0a4d591b-b747-4861-9e4d-e818fd5a67cf";
                                        Users users = new Users(id,username,email,password,imageuri,status);
                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(Register.this, "Account created",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else {
                                                    Toast.makeText(Register.this, "Authentication failed.",
                                                            Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                    }




                                } else {
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10 && resultCode == RESULT_OK && data != null){
            if(data!=null){
                imageURI = data.getData();
                rg_profileimg.setImageURI(imageURI);
            }
        }
    }
}