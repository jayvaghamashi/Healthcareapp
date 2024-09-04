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

public class Buymdetails extends AppCompatActivity {
Button back , cost;
TextView tv3,tv4;
EditText ed3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymdetails);
        back=findViewById(R.id.back1);
        cost =findViewById(R.id.back2);
        tv3=findViewById(R.id.cost1);
        ed3=findViewById(R.id.multiline);
        tv4=findViewById(R.id.d_title2);
        ed3.setKeyListener(null);
        Intent intent=getIntent();
        tv4.setText(intent.getStringExtra("text1"));
        ed3.setText(intent.getStringExtra("text2"));
        tv3.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Buymdetails.this,BuymActivity.class));
            }
        });
        cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String product = tv4.getText().toString();

                float price = Float.parseFloat(intent.getStringExtra("text3"));
                Database db = new Database(getApplicationContext(), "healthcare1", null, 1);

                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(Buymdetails.this, "Product already added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addcart(username, product, price, "medicine");
                    Toast.makeText(Buymdetails.this, "Record inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Buymdetails.this, BuymActivity.class));
                }
            }
        });
    }
}