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

public class login extends AppCompatActivity {

    EditText login1, password1;
    Button button1, button2;
    TextView forget;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login1 = findViewById(R.id.login1);
        password1 = findViewById(R.id.password1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        forget=findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, updatepass.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = login1.getText().toString();
                String pass = password1.getText().toString();




                // Replace these hardcoded credentials with your authentication logic
                if (username.equals("Admin") && pass.equals("Admin@123")) {
                    // Successful login, navigate to the admin dashboard
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"Access Succesfully",Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(getApplicationContext(),"Access denide",Toast.LENGTH_SHORT).show();
                }




                Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
                if (username.length() == 0 || pass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(username, pass)) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        // Save username to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(login.this, Homeactivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid password or username", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, register.class));
            }
        });
    }
}
