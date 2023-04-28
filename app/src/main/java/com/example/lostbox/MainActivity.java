package com.example.lostbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;  // 하단바
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FindFragment findFragment;
    private FoundFragment foundFragment;
    private ChatFragment chatFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_find:
                        setFrag(0);
                        break;
                    case R.id.action_found:
                        setFrag(1);
                        break;
                    case R.id.action_chat:
                        setFrag(2);
                        break;
                    case R.id.action_myPage:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        findFragment = new FindFragment();
        foundFragment = new FoundFragment();
        chatFragment = new ChatFragment();
        myPageFragment = new MyPageFragment();
        setFrag(0);     // 첫 프래그먼트 화면 지정
    }


    // 프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n) {
            case 0:
                ft.replace(R.id.main_frame, findFragment).commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, foundFragment).commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, chatFragment).commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, myPageFragment).commit();
                break;
        }


    }


}




//public class MainActivity extends AppCompatActivity {
//
//    Button btn1, btn2, btn3, btn4;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btn1 = (Button)findViewById(R.id.btn_1);
//        btn2 = (Button)findViewById(R.id.btn_2);
//        btn3 = (Button)findViewById(R.id.btn_3);
//        btn4 = (Button)findViewById(R.id.btn_4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Find fragment1 = new Find();
//                transaction.replace(R.id.frameLayout_mainContent, fragment1);
//                transaction.commit();
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Found fragment2 = new Found();
//                transaction.replace(R.id.frameLayout_mainContent, fragment2);
//                transaction.commit();
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Chat fragment3 = new Chat();
//                transaction.replace(R.id.frameLayout_mainContent, fragment3);
//
//                transaction.commit();
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                MyPage fragment4 = new MyPage();
//                transaction.replace(R.id.frameLayout_mainContent, fragment4);
//                transaction.commit();
//            }
//        });
//    }
//}


