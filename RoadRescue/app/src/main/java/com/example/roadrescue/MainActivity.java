package com.example.roadrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        ImageView imageView = findViewById(R.id.option);
        registerForContextMenu(imageView);






        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        CardView soscontact = findViewById(R.id.cardSoscontact);

        CardView repairTutorial = findViewById(R.id.cardRepairTutorial);

        CardView weather = findViewById(R.id.cardWeather);

        CardView NearByPlaces = findViewById((R.id.cardNearByPlaces));

        CardView Hospital = findViewById((R.id.cardHospital));

        CardView ChatBot = findViewById(R.id.cardChatbot);
         CardView carpooling = findViewById(R.id.cardCarpooling);

        ChatBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ChatBotActivity.class));
            }
        });

        Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HospitalActivity.class));
            }
        });

        carpooling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Carpooling.class));
            }
        });



        NearByPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CurrentMapsActivity.class));
            }
        });


        repairTutorial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HomeRepair.class));

            }

        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Weather.class));

            }
        });

        soscontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SosContactActivity.class));

            }
        });

        ImageView hamburgerMenu = findViewById(R.id.option);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        hamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the navigation drawer
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_profile) {
//                     Redirect to ProfileActivity when "Profile" is clicked
                    Intent profileIntent = new Intent(getApplicationContext(), LogoutActivity.class);
                    startActivity(profileIntent);
                } else if (itemId == R.id.menu_logout) {
//                    // Handle logout here if needed
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else if (itemId == R.id.menu_location){
                    Intent intent = new Intent(MainActivity.this,CurrentLocationActivity.class);
                    startActivity(intent);

                }
                // Close the drawer after item selection
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });


    }






    }

