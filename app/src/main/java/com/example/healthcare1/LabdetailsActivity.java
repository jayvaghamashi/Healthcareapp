package com.example.healthcare1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabdetailsActivity extends AppCompatActivity {
    TextView tv1,ct2;
    EditText ed1;
    Button back1 , card2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labdetails);
        tv1=findViewById(R.id.d_title2);
        ct2=findViewById(R.id.cost1);
        ed1=findViewById(R.id.multiline);
        ed1.setKeyListener(null);
        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));
        ed1.setText(intent.getStringExtra("text2"));
        ct2.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");
        back1=findViewById(R.id.back1);
        card2=findViewById(R.id.back2);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabdetailsActivity.this,LabActivity.class));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String product = tv1.getText().toString();

                // Make sure you're getting the "text3" extra correctly from the intent
                float price = Float.parseFloat(intent.getStringExtra("text3"));

                Database db = new Database(getApplicationContext(), "healthcare1", null, 1);

                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(LabdetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addcart(username, product, price, "lab");
                    Toast.makeText(LabdetailsActivity.this, "Record inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabdetailsActivity.this, LabActivity.class));
                }
            }
        });

    }
}