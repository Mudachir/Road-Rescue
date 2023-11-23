package com.example.roadrescue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        int[] imageId = {R.drawable.mri_test,R.drawable.blood_tests,R.drawable.cbc_test,R.drawable.endoscopy_test,R.drawable.kidneyfunction_test,R.drawable.liverfunction_test,R.drawable.thyroid_test,R.drawable.ultrasound_test,
                R.drawable.urinalysis_test,R.drawable.xray_test};
        String[] name = {"MRI-Lubana","MRI-Labaid","CBC Test-Labaid","CBC Test-Squre Hospital","Kidney Function Test","Liver Function Test","Thyroid Test","Ultrasound Test","Urinalysis Test","Xray"};
        String[] phoneNo = {"500","600","550","600","450","700","840","360","250","480"};
        String[] country = {"Bashundhara","Uttara","Banani","Nikunja","Gulshan","Dhanmondi","Puran Dhaka","Gazipur","Shanti Niketon","Badda"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            User user = new User(name[i], "", phoneNo[i], country[i], imageId[i]);
            userArrayList.add(user);
        }

        ListAdapter listAdapter = new ListAdapter(TestActivity.this, R.layout.list_item, userArrayList);


        listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TestActivity.this, UserActivity.class);
                i.putExtra("name", name[position]);
                i.putExtra("phone", phoneNo[position]);
                i.putExtra("country", country[position]);
                i.putExtra("imageId", imageId[position]); // Ensure imageId[position] is a valid resource ID.

                startActivity(i);
            }
        });
    }
}
