package com.example.healthcare1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class finddoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finddoctor);

        CardView back = findViewById(R.id.card_view6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(finddoctorActivity.this, Homeactivity.class));
            }
        });

        CardView familyPhysician = findViewById(R.id.card_view1);
        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(finddoctorActivity.this, doctordetails.class);
                it.putExtra("title", "Family Physicians");
                startActivity(it);
            }
        });

        CardView dietitian = findViewById(R.id.card_view2);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(finddoctorActivity.this, doctordetails.class);
                it.putExtra("title", "Dietitian");
                startActivity(it);
            }
        });

        CardView dentist = findViewById(R.id.card_view3);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(finddoctorActivity.this, doctordetails.class);
                it.putExtra("title", "Dentist");
                startActivity(it);
            }
        });

        CardView surgeon = findViewById(R.id.card_view4);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(finddoctorActivity.this, doctordetails.class);
                it.putExtra("title", "Surgeon");
                startActivity(it);
            }
        });

        CardView cardiologists = findViewById(R.id.card_view5);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(finddoctorActivity.this, doctordetails.class);
                it.putExtra("title", "Cardiologists");
                startActivity(it);
            }
        });
    }
}
