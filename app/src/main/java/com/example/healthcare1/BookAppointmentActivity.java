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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    TextView tv1,tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button buttonDate, buttonTime,button1;
    private Calendar selectedDate = Calendar.getInstance();
    private Calendar selectedTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv1 = findViewById(R.id.textView2);
        ed1 = findViewById(R.id.fullname);
        ed2 = findViewById(R.id.address);
        ed3 = findViewById(R.id.contactnumber);
        ed4 = findViewById(R.id.fees);
        tv=findViewById(R.id.tv22);
        button1=findViewById(R.id.button1);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv1.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees : " + fees + " /- ");

        // Initialize the DatePicker and TimePicker dialogs
        initDatePicker();
        initTimePicker();

        // Find and set the click listeners for the Date and Time buttons
        buttonDate = findViewById(R.id.buttondate);
        buttonTime = findViewById(R.id.buttontime);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,doctordetails.class));
            }
        });
             button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                   public void onClick(View view) {
                                Database db = new Database(getApplicationContext(),"healthcare1",null,1);
                                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                                String username = sharedPreferences.getString("username", "").toString();
                                if (db.checkAppointmentExists(
                                        username,                      // username
                                        title + " => " + fullname,    // title (or another column, if it's not the same)
                                        address,                       // address
                                        contact,                      // contact
                                   //     fees,                     //fess
                                        buttonDate.getText().toString(), // date
                                        buttonTime.getText().toString()  // time
                                ) == 1){
                                    Toast.makeText(BookAppointmentActivity.this, "Appointment alredy Book", Toast.LENGTH_SHORT).show();
                                }else {
                                    db.addOrder(username,title+" => "+fullname,address,0,contact,buttonDate.getText().toString(),buttonTime.getText().toString(),Float.parseFloat(fees),"appointment");
                                    Toast.makeText(BookAppointmentActivity.this, "Your Appointement done successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(BookAppointmentActivity.this,Homeactivity.class));
                                }

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
