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

public class updatepass extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText newPasswordEditText;
    private Button updatePasswordButton;
 TextView homelogin;
    private Database database; // Your custom SQLiteOpenHelper class

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepass);
        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        updatePasswordButton = findViewById(R.id.updatePasswordButton);
        homelogin=findViewById(R.id.homelogin);
        homelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(updatepass.this, login.class));
            }
        });

        // Initialize the Database object
        database = new Database(getApplicationContext(), "healthcare1", null, 1);

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered username and new password
                String username = usernameEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString();

                // Check if username and newPassword are not empty
                if (!username.isEmpty() && !newPassword.isEmpty()) {
                    // Update the password in the database
                    updatePassword(username, newPassword);
                } else {
                    // Display a message if fields are empty
                    Toast.makeText(updatepass.this, "Please enter both username and new password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updatePassword(String username, String password){
        // Update the password in the SQLite database using your custom Database class
        database.updatePassword(username, password);

        // Optionally, you can also store the updated password in SharedPreferences or any other suitable storage mechanism
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", password);
        editor.apply();

        // Display a success message
        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
    }
}
