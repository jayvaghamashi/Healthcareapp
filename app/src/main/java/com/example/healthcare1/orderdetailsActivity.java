
package com.example.healthcare1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class orderdetailsActivity extends AppCompatActivity {
    ListView listViewdt;
    Button back1;
    TextView tv1;
    private String[][] OrderDetail = {};
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        tv1 = findViewById(R.id.ordertext);
        back1 = findViewById(R.id.back1);
        listViewdt = findViewById(R.id.listvieworder);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(orderdetailsActivity.this, Homeactivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
        ArrayList<String> dbData = db.getOrderData(username);

        OrderDetail = new String[dbData.size()][];

        for (int i = 0; i < OrderDetail.length; i++) {
            OrderDetail[i] = new String[5];
            String arrData = dbData.get(i);
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));

           // OrderDetail[i][0] = strData[0];
            OrderDetail[i][1] = strData[0];

            // Check if strData has at least 8 elements before accessing strData[7]
            if (strData.length > 7) {
                if (strData[7].equals("medicine")) {
                    OrderDetail[i][3] = "Del: " + strData[1];
                } else {
                    OrderDetail[i][3] = "Del: " + strData[1] + " Time :- " + strData[5];
                }

                OrderDetail[i][2] = "Date :-" + strData[4]+ "\nMo_NO :-"+strData[2] ;
                OrderDetail[i][4] = "RS ;-"+strData[6]+"\n type :-"+strData[7];
            } else {
                // Handle the case where there are not enough elements in strData
                // You might want to provide default values or handle this case accordingly.
                // For example:
                OrderDetail[i][3] = "Delivery information not available";
                OrderDetail[i][2] = "Price information not available";
                OrderDetail[i][4] = "Type not available";
            }
        }


        list = new ArrayList<>();

        for (int i = 0; i < OrderDetail.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", OrderDetail[i][0]);
            item.put("line2", OrderDetail[i][1]);
            item.put("line3", OrderDetail[i][2]);
            item.put("line4", OrderDetail[i][3]);
            item.put("line5", OrderDetail[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        listViewdt.setAdapter(sa);
    }
}
