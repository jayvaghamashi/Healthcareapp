package com.example.healthcare1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthDetails extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);

        tv1 = findViewById(R.id.healthtitle);
        img = findViewById(R.id.imagehealth);
        back = findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDetails.this, healthartical.class));
                finish(); // Optional: Close this activity when going back
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String text1 = intent.getStringExtra("text1");
            tv1.setText(text1);

            int text2 = intent.getIntExtra("text2", 0);
            img.setImageResource(text2);
        }
    }
}
