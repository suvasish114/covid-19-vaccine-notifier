package com.example.vaccinenotifier;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fields
        ImageView tv_district = findViewById(R.id.tv_District);
        ImageView tv_pincode = findViewById(R.id.tv_Pincode);
        ImageView tv_about = findViewById(R.id.tv_About);
        FragmentManager fragmentManager = getSupportFragmentManager();

        // for auto execution for 1st time --------------------------------------------
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(false)
                    .add(R.id.home_fg_container, pincodeFragment.class, null)
                    .commit();
        }

        // onclick listner methods ----------------------------------------------------
        tv_pincode.setOnClickListener(new View.OnClickListener() {
            // action for pincode button
            @Override
                public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(false)
                        .replace(R.id.home_fg_container, pincodeFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        tv_district.setOnClickListener(new View.OnClickListener(){
            // action for district button
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(false)
                        .replace(R.id.home_fg_container, districtFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(false)
                        .replace(R.id.home_fg_container, AboutFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}