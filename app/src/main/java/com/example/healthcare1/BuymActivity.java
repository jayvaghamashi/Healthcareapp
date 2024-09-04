package com.example.healthcare1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BuymActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Medicine 1: Aspirin", "", "", "", "599"},
                    {"Medicine 2: Ibuprofen", "", "", "", "349"},
                    {"Medicine 3: Antibiotic - Amoxicillin", "", "", "", "999"},
                    {"Medicine 4: Antacid - Tums", "", "", "", "299"},
                    {"Medicine 5: Antihistamine - Benadryl", "", "", "", "499"},
                    {"Medicine 6: Painkiller - Acetaminophen", "", "", "", "699"},
                    {"Medicine 7: Cough Syrup", "", "", "", "749"},
                    {"Medicine 8: Allergy Medication - Zyrtec", "", "", "", "899"},
                    {"Medicine 9: Feronia - Zyrtec", "", "", "", "800"}
            };
    private String[] PackageDetails = {
            "Aspirin\n" +
                    "Usage: Pain relief\n" +
                    "Dosage: 1 tablet every 4-6 hours\n",
            "Ibuprofen\n" +
                    "Usage: Pain relief, anti-inflammatory\n" +
                    "Dosage: 1-2 tablets every 4-6 hours\n",
            "Amoxicillin\n" +
                    "Usage: Antibiotic\n" +
                    "Dosage: As prescribed by your doctor\n",
            "Tums\n" +
                    "Usage: Antacid\n" +
                    "Dosage: 2-4 tablets as needed\n",
            "Benadryl\n" +
                    "Usage: Antihistamine\n" +
                    "Dosage: As directed on the label\n",
            "Acetaminophen\n" +
                    "Usage: Pain relief, fever reducer\n" +
                    "Dosage: As directed on the label\n",
            "Cough Syrup\n" +
                    "Usage: Cough and cold relief\n" +
                    "Dosage: As directed on the label\n",
            "Zyrtec\n" +
                    "Usage: Allergy relief\n" +
                    "Dosage: As directed on the label\n",
            "Diabetes Management\n" +
                    "Usage: Blood sugar control\n" +
                    "Dosage: As prescribed by your doctor\n"
    };
HashMap<String,String> item;
ArrayList list;
SimpleAdapter sa;
ListView listbuy;
Button back ,cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buym);
        back=findViewById(R.id.back2);
        cart=findViewById(R.id.back3);
        listbuy=findViewById(R.id.listbuy);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuymActivity.this,Homeactivity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuymActivity.this,cartmedicine.class));
            }
        });
        list= new ArrayList();
        for(int i =0;i<packages.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","total Cost :"+packages[i][4]+"/-");
            list.add(item);



        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        listbuy.setAdapter(sa);
        listbuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(BuymActivity.this,Buymdetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",PackageDetails[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}