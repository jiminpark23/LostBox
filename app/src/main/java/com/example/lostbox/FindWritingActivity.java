package com.example.lostbox;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindWritingActivity extends AppCompatActivity {
    private EditText etTitle, etPlace, etDate, etContent, etImg;
    private Button btnAdd;

    private DatabaseReference databaseReference;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_writing);

        etTitle = findViewById(R.id.et_find_title);
        etPlace = findViewById(R.id.et_find_place);
        etDate = findViewById(R.id.et_find_date);
        etContent = findViewById(R.id.et_find_content);
        etImg = findViewById(R.id.iv_find_img);
        btnAdd = findViewById(R.id.btn_find_add);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("findItems");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String place = etPlace.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                String img = etImg.getText().toString().trim();

                if (!title.isEmpty() && !place.isEmpty() && !date.isEmpty() && !content.isEmpty() && !img.isEmpty()) {
                    // 새로운 FindItem 객체 생성
                    FindItem findItem = new FindItem();
                    findItem.setTitle(title);
                    findItem.setPlace(place);
                    findItem.setDate(date);
                    findItem.setContent(content);
                    findItem.setImg(img);

                    // 데이터베이스에 FindItem 객체 추가
                    String itemId = databaseReference.push().getKey();
                    databaseReference.child(itemId).setValue(findItem);

                    // 작성 완료 후 액티비티 종료
                    finish();
                }
            }
        });
    }
}



//package com.example.lostbox;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class FindWrittingActivity extends AppCompatActivity {
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_writting);
//
//
//        TextView btn_back =(TextView) findViewById(R.id.btn_back);
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
