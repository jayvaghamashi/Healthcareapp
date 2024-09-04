package com.example.healthcare1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Homeactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+username, Toast.LENGTH_SHORT).show();

        CardView exit =findViewById(R.id.card_view6);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Homeactivity.this, login.class));
                finishAffinity();

            }
        });
        CardView finddoctor = findViewById(R.id.card_view3);
        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homeactivity.this,finddoctorActivity.class));
            }
        });
        CardView labtest = findViewById(R.id.card_view1);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homeactivity.this,LabActivity.class));
            }
        });
        CardView Orderdetails = findViewById(R.id.card_view5);
        Orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homeactivity.this, orderdetailsActivity.class));
            }
        });
        CardView buymedicine = findViewById(R.id.card_view2);
        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homeactivity.this,BuymActivity.class));
            }
        });
        CardView health = findViewById(R.id.card_view4);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Homeactivity.this,healthartical.class));
            }
        });
    }
}