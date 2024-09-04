package com.example.healthcare1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText relogin, reemail, password1, password2;
    Button button1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        relogin = findViewById(R.id.relogin);
        reemail = findViewById(R.id.reemail);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        tv = findViewById(R.id.tv);
        button1 = findViewById(R.id.button1);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, login.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext(), "healthcare1", null, 1);

                String username = relogin.getText().toString();
                String email = reemail.getText().toString();
                String password = password1.getText().toString();
                String confirmPassword = password2.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(register.this, "Password must contain at least one letter, one digit, and one special character", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(register.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else {
                    // Registration is successful, you can proceed to save the user data in your database
                    // Replace the following line with your database code
                     db.register(username, email, password);

                    Toast.makeText(register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register.this, login.class));
                }
            }
        });
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least one letter, one digit, and one special character
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$");
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
