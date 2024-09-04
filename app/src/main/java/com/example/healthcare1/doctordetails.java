
package com.example.healthcare1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class doctordetails extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name : Harshad Zinzala", "Hospital Address : sitaram society", "Experince : 5 years", "Mobile No 9313023071", " 1000"},
            {"Doctor Name : Yagnik Hariyani", "Hospital Address : Hirabaug society", "Experience : 7 years", "Mobile No 6359438336", " 1120"},
            {"Doctor Name : Prakash Rakhasiya", "Hospital Address : Dhrupark society", "Experience : 3 years", "Mobile No 9924483996", " 700"},
            {"Doctor Name : Jay Vaghamashi", "Hospital Address : Hariom society", "Experience : 6 years", "Mobile No 9537220866", "1000"},
            {"Doctor Name : Raj Vaghamashi", "Hospital Address : Santkrupa society", "Experience : 4 years", "Mobile No 9766699906", "1200"}
    };

    private String[][] doctor_details2 = {
            {"Doctor Name : Bhautik Dolar", "Hospital Address : sitaram society", "Experience : 5 years", "Mobile No 9313446853", " 1000"},
            {"Doctor Name : Harshit Ahir", "Hospital Address : Hirabaug society", "Experience : 7 years", "Mobile No 9173916767", " 1120"},
            {"Doctor Name : Raju Ahir", "Hospital Address : Santkrupa society", "Experience : 4 years", "Mobile No 9766699906", " 1200"},
            {"Doctor Name : Kuldip Gondliya", "Hospital Address : Sham society", "Experience : 4 years", "Mobile No 9766234506", " 1500"},
            {"Doctor Name : Tushar Rathod", "Hospital Address : Ram society", "Experience : 4 years", "Mobile No 9756799906", " 1400"}
    };

    private String[][] doctor_detail3 = {
            {"Doctor Name : Piyush Dolar", "Hospital Address : sitaram society", "Experience : 2 years", "Mobile No 7698361750", " 1000"},
            {"Doctor Name : Bansi Ahir", "Hospital Address : Hariom society", "Experience : 3 years", "Mobile No 9313672284", " 1120"},
            {"Doctor Name : Milan Katariya", "Hospital Address : Hariom society", "Experience : 3 years", "Mobile No 6353273493", "700"},
            {"Doctor Name : Dilip Katariya", "Hospital Address : Hariom society", "Experience : 6 years", "Mobile No 9924396543", " 1000"},
            {"Doctor Name : Vaibhav Katariya", "Hospital Address : Santkrupa society", "Experience : 4 years", "Mobile No 81410286183", " 1200"}
    };

    private String[][] doctor_detail4 = {

            {"Doctor Name : John Smith", "Hospital Address : Maple Hospital", "Experience : 8 years", "Mobile No 1234567890", "900"},
            {"Doctor Name : Sarah Johnson", "Hospital Address : Oakwood Clinic", "Experience : 10 years", "Mobile No 9876543210", "1100"},
            {"Doctor Name : Michael Brown", "Hospital Address : Elmwood Medical Center", "Experience : 6 years", "Mobile No 5555555555", "750"},
            {"Doctor Name : Emily Davis", "Hospital Address : Pinecrest Health Center", "Experience : 9 years", "Mobile No 1112223333", "950"},
            {"Doctor Name : Robert Wilson", "Hospital Address : Cedar Hospital", "Experience : 7 years", "Mobile No 9998887777", "1200"}
    };


    private String[][] doctor_details5 = {

            {"Doctor Name : Jane Doe", "Hospital Address : Willow Clinic", "Experience : 5 years", "Mobile No 7778889999", "800"},
            {"Doctor Name : David Lee", "Hospital Address : Birch Medical Center", "Experience : 12 years", "Mobile No 3332221111", "1300"},
            {"Doctor Name : Susan Taylor", "Hospital Address : Redwood Hospital", "Experience : 4 years", "Mobile No 4443332222", "700"},
            {"Doctor Name : Christopher Hall", "Hospital Address : Aspen Health Center", "Experience : 11 years", "Mobile No 6667778888", "1250"},
            {"Doctor Name : Lisa White", "Hospital Address : Pineview Clinic", "Experience : 6 years", "Mobile No 8889990000", "850"}


};

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    SimpleAdapter sa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);
        tv = findViewById(R.id.d_title);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        assert title != null;
        if (title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dietitian") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_detail3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_detail4;
        else
            doctor_details = doctor_details5;

        btn = findViewById(R.id.back1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(doctordetails.this, finddoctorActivity.class));
            }
        });

        for (int i = 0; i < doctor_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", " Fees :  " +  doctor_details[i][4]  + " /- ");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listviewdt);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent it =new Intent(doctordetails.this, BookAppointmentActivity.class);
               it.putExtra("text1",title);
               it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}
















