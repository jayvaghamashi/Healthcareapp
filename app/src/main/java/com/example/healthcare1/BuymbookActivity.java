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

public class BuymbookActivity extends AppCompatActivity {
    EditText name,address,pincode,number;
    Button book;
    TextView back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymbook);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);
        number=findViewById(R.id.number);
        book=findViewById(R.id.book);
        back=findViewById(R.id.back);
        Intent intent =getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
                db.addOrder(username,name.getText().toString(),address.getText().toString(),Integer.parseInt(pincode.getText().toString()),number.getText().toString(),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(username,"medicine");
                Toast.makeText(getApplicationContext(),"your Booking is done successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuymbookActivity.this,Homeactivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuymbookActivity.this,cartmedicine.class));
            }
        });

    }
}