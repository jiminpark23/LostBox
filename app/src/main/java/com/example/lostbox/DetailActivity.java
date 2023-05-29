package com.example.lostbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView detail_title, detail_money, detail_ex, detail_date, detail_place;
    ImageView detail_image;
    String title, money, ex, date, place;
    int image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_title = findViewById(R.id.title_detail);
        detail_image = findViewById(R.id.image_detail);
        //detail_money = findViewById(R.id.detail_money);
        detail_ex = findViewById(R.id.detail_text);
        detail_place = findViewById(R.id.detail_place);
        detail_date = findViewById(R.id.detail_date);

        //엑티비이에서 받아온 데이터
        Intent intent = getIntent();

        title = intent.getExtras().getString("title");
        //money = intent.getExtras().getString("money");
        ex = intent.getExtras().getString("ex");
        date = intent.getExtras().getString("date");
        place = intent.getExtras().getString("place");
        image = intent.getExtras().getInt("image");

        detail_title.setText(title);
        detail_image.setImageResource(image);
        //detail_money.setText(money);
        detail_ex.setText(ex);
        detail_place.setText(place);
        detail_date.setText(date);

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
