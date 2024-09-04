package com.example.healthcare1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class CarddataActivity extends AppCompatActivity {
HashMap<String,String> item;
ArrayList list;
SimpleAdapter sa;
TextView cost1;
Button checkout,tv;
ListView listViewlsd;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
private Button buttonDate, buttonTime;
private String[][] packages={};

    private Calendar selectedDate = Calendar.getInstance();
    private Calendar selectedTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carddata);

         tv=findViewById(R.id.back1);
        // Initialize the DatePicker and TimePicker dialogs
        initDatePicker();
        initTimePicker();

        // Find and set the click listeners for the Date and Time buttons
        buttonDate = findViewById(R.id.buttondate);
        buttonTime = findViewById(R.id.buttontime);
        checkout=findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent it =new Intent(CarddataActivity.this, LabBookActivity.class);
             it.putExtra("price",cost1.getText());
             it.putExtra("date",buttonDate.getText());
             it.putExtra("time",buttonTime.getText());
             startActivity(it);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
        float totalamount =0;
        ArrayList dbData = db.getCartData(username,"lab");
       // Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_LONG).show();

        packages =new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for (int i =0;i<dbData.size();i++)
        {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="cost :"+strData[1]+"/-";
            totalamount =totalamount + Float.parseFloat(strData[1]);

        }
        cost1=findViewById(R.id.cost1);
        cost1.setText("Total Cost :"+totalamount);

        list=new ArrayList();

        for(int i =0;i<packages.length;i++) {
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
        listViewlsd=findViewById(R.id.listviewlsd);
        listViewlsd.setAdapter(sa);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CarddataActivity.this,LabActivity.class));
            }
        });

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the DatePicker dialog when the Date button is clicked
                datePickerDialog.show();
            }
        });

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the TimePicker dialog when the Time button is clicked
                timePickerDialog.show();
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

    // Initialize the TimePicker dialog
    private void initTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // Handle time selection here, e.g., update a TextView
                selectedTime.set(Calendar.HOUR_OF_DAY, hour);
                selectedTime.set(Calendar.MINUTE, minute);
                updateSelectTimeButtonText();
            }
        }, hourOfDay, minute, true);
    }

    // Update the button text with the selected time
    private void updateSelectTimeButtonText() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        String formattedTime = sdf.format(selectedTime.getTime());
        buttonTime.setText(formattedTime);
    }




}
