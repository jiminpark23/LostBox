package com.example.lostbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class FindWrittingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_writing);


        TextView btn_back =(TextView) findViewById(R.id.btn_back);
        Spinner spn = (Spinner) findViewById(R.id.spinner);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter spn_adapter = ArrayAdapter.createFromResource(this, R.array.카테고리, android.R.layout.simple_spinner_item);
        spn.setAdapter(spn_adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue = adapterView.getItemAtPosition(i).toString();
                // 선택된 값(selectedValue)을 원하는 대로 활용
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // 선택이 해제됐을 때 수행할 작업을 추가하거나 무시
            }
        });
    }
}
