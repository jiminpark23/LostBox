package com.example.lostbox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;                 // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;             // 실시간 데이터베이스
    private EditText mEtName, mEtId, mEtPwd, mEtPhone, mEtEmail; // 회원가입 입력필드
    private Button mBtnSignUp;                          // 회원가입 입력버튼

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("LostBox");

        mEtName = findViewById(R.id.et_signUp_name);
        mEtId = findViewById(R.id.et_signUp_id);
        mEtPwd = findViewById(R.id.et_signUp_pwd);
        mEtPhone = findViewById(R.id.et_signUp_phone);
        mEtEmail = findViewById(R.id.et_signUp_email);
        mBtnSignUp = findViewById(R.id.btn_signUp);

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // 회원가입 처리 시작
               String strName = mEtName.getText().toString();
               String strId = mEtId.getText().toString();
               String strPwd = mEtPwd.getText().toString();
               String strPhone = mEtPhone.getText().toString();
               String strEmail = mEtEmail.getText().toString();

               // Firebase Auth 진행
               mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                           Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                           startActivity(intent);

                           UserAccount account = new UserAccount();
                           account.setIdToken(firebaseUser.getUid());
                           account.setEmailId(firebaseUser.getEmail());
                           account.setPassword(strPwd);
                           account.setName(strName);
                           account.setId(strId);
                           account.setPhone(strPhone);

                           // setValue : database에 insert(삽입)하는 행위
                           mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                           finish();   // 현재 액티비티 파괴
                           Toast.makeText(SignupActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(SignupActivity.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

           }
        });
    }
}
