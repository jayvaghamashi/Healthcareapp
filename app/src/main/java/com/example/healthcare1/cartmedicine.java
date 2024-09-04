
package com.example.healthcare1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class cartmedicine extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list; // Initialize 'list' here
    SimpleAdapter sa;
    TextView cost1;
    Button checkout, tv;
    ListView listViewlsd;
    private DatePickerDialog datePickerDialog;

    private Button buttonDate;
    private String[][] packages = {};

    private Calendar selectedDate = Calendar.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartmedicine);
        tv = findViewById(R.id.back1);
        // Initialize the DatePicker and TimePicker dialogs
        initDatePicker();

        // Find and set the click listeners for the Date and Time buttons
        buttonDate = findViewById(R.id.buttondate);

        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(cartmedicine.this, BuymbookActivity.class);
                it.putExtra("price", cost1.getText());
                it.putExtra("date", buttonDate.getText());
                startActivity(it);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
        float totalamount = 0;
        ArrayList dbData = db.getCartData(username, "medicine");

        Toast.makeText(getApplicationContext(), "" + dbData, Toast.LENGTH_LONG).show();

        packages = new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "cost: " + strData[1] + "/-";
            totalamount = totalamount + Float.parseFloat(strData[1]);

        }
        cost1 = findViewById(R.id.cost1);
        cost1.setText("Total Cost: " + totalamount);

        // Initialize 'list' here
        list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}

        );
        listViewlsd = findViewById(R.id.listviewlsd1);
        listViewlsd.setAdapter(sa);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cartmedicine.this, BuymActivity.class));
            }
        });
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the DatePicker dialog when the Date button is clicked
                datePickerDialog.show();
            }
        });
    }

    // Initialize the DatePicker dialog
    private void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Handle date selection here, e.g., update a TextView
                selectedDate.set(year, month, day);
                updateSelectDateButtonText();
            }
        }, year, month, dayOfMonth);
    }

    // Update the button text with the selected date
    private void updateSelectDateButtonText() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String formattedDate = sdf.format(selectedDate.getTime());
        buttonDate.setText(formattedDate);
    }
}
