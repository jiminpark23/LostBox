package com.example.lostbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoundDetailActivity extends AppCompatActivity {
    TextView fdetail_title, fdetail_ex, fdetail_date, fdetail_place;
    ImageView fdetail_image;
    String title, ex, date, place;
    int image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_found);

        fdetail_title = findViewById(R.id.title_detail);
        fdetail_image = findViewById(R.id.image_detail);
        //detail_money = findViewById(R.id.detail_money);
        fdetail_ex = findViewById(R.id.detail_text);
        fdetail_place = findViewById(R.id.detail_place);
        fdetail_date = findViewById(R.id.detail_date);

        //엑티비이에서 받아온 데이터
        Intent intent = getIntent();

        title = intent.getExtras().getString("title");
        //money = intent.getExtras().getString("money");
        ex = intent.getExtras().getString("ex");
        date = intent.getExtras().getString("date");
        place = intent.getExtras().getString("place");
        image = intent.getExtras().getInt("image");

        fdetail_title.setText(title);
        fdetail_image.setImageResource(image);
        //detail_money.setText(money);
        fdetail_ex.setText(ex);
        fdetail_place.setText(place);
        fdetail_date.setText(date);

        TextView btn_back =(TextView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
