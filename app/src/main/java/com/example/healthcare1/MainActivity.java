package com.example.healthcare1;

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

public class MainActivity extends AppCompatActivity {
    ListView listViewdt;
    Button back1;
    TextView tv1;
    private String[][] orderDetail = {};
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.ordertext);
        back1 = findViewById(R.id.back1);
        listViewdt = findViewById(R.id.listviewdt1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, login.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        if (!id.isEmpty()) {
            try {
                int userId = Integer.parseInt(id);

                Database db = new Database(getApplicationContext(), "healthcare1", null, 1);
                ArrayList<String> dbData = db.getUserData(userId);

                if (dbData != null && !dbData.isEmpty()) {
                    orderDetail = new String[dbData.size()][];

                    for (int i = 0; i < dbData.size(); i++) {
                        orderDetail[i] = new String[4];
                        String arrData = dbData.get(i);
                        String[] strData = arrData.split("\\$");

                        orderDetail[i][0] = "ID: " + strData[0];
                        orderDetail[i][1] = "Username: " + strData[1];
                        orderDetail[i][2] = "Email: " + strData[2];
                        orderDetail[i][3] = "Password: " + strData[3];
                    }

                    list = new ArrayList<>();

                    for (int i = 0; i < orderDetail.length; i++) {
                        HashMap<String, String> item = new HashMap<>();
                        item.put("line1", orderDetail[i][0]);
                        item.put("line2", orderDetail[i][1]);
                        item.put("line3", orderDetail[i][2]);
                        item.put("line4", orderDetail[i][3]);
                        list.add(item);
                    }

                    sa = new SimpleAdapter(this, list,
                            R.layout.multi_lines,
                            new String[]{"line1", "line2", "line3", "line4"},
                            new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d}
                    );

                    listViewdt.setAdapter(sa);
                } else {
                    // Handle the case where dbData is null or empty.
                }
            } catch (NumberFormatException e) {
                // Handle the case where id cannot be parsed as an integer, e.g., show an error message.
            }
        } else {
            // Handle the case where id is empty, e.g., show an error message or redirect to a login screen.
        }
    }
}


