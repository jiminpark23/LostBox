package com.example.lostbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private TextView nickname;  // 사용자 닉네임을 보여주기 위함

    public MyPageFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("LostBox");

        nickname = view.findViewById((R.id.tv_nickname));  // 닉네임 표시를 위함
        TextView signout = view.findViewById(R.id.tv_signOut);  // 로그아웃 기능을 위함

        fetchUserName();    // 사용자 이름을 가져옴
        signout.setOnClickListener(this);

        return view;
    }

    private void fetchUserName() {
        // 현재 사용자의 UID를 가져옴
        String uid = mFirebaseAuth.getCurrentUser().getUid();

        // UserAccount 레퍼런스에서 현재 사용자의 이름을 가져옴
        DatabaseReference userRef = mDatabaseRef.child("UserAccount").child(uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 사용자 이름을 가져와서 TextView에 설정
                if (snapshot.exists()) {
                    UserAccount user = snapshot.getValue(UserAccount.class);
                    if (user != null) {
                        String name = user.getName();
                        nickname.setText(name);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_signOut) {
            signOut();
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();

        // 로그아웃 후 동작 (로그인 페이지로 전환)
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // 이전 화면을 종료하고 돌아가는 경우

    }
}
