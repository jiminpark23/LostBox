package com.example.lostbox;

import com.example.lostbox.R;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

interface SuccessHandler {
    void onSuccess(String uri);
}

interface ErrorHandler {
    void onError();
}

public class FindWritingActivity extends AppCompatActivity {
    private EditText etTitle, etPlace, etDate, etPay, etContent, etImg;
    private Button btnAdd;

    private Uri selectedUri;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private DatabaseReference findDB;

    private DatabaseReference databaseReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_writing);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        findDB = FirebaseDatabase.getInstance().getReference().child(DBKey.DB_ARTICLES);
        //databaseReference = FirebaseDatabase.getInstance().getReference().child(DBKey.DB_ARTICLES);

        Button imageAddButton = findViewById(R.id.btn_upload);
        imageAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(FindWritingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    startContentProvider();
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(FindWritingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showPermissionContextPopup();
                } else {
                    ActivityCompat.requestPermissions(FindWritingActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1010);
                }
            }

            private void showPermissionContextPopup() {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindWritingActivity.this);
                builder.setTitle("권한이 필요합니다.")
                        .setMessage("사진을 가져오기 위해 필요합니다.")
                        .setPositiveButton("앱 설정", (dialog, which) -> {
                            openAppSettings();
                        })
                        .create()
                        .show();
            }

            private void openAppSettings() {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this);
        }

        etTitle = findViewById(R.id.et_find_title);
        etPlace = findViewById(R.id.et_find_place);
        etDate = findViewById(R.id.et_find_date);
        etPay = findViewById(R.id.et_find_pay);
        etContent = findViewById(R.id.et_find_content);
        etImg = findViewById(R.id.find_img);
        btnAdd = findViewById(R.id.btn_find_add);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("findItems");

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = etTitle.getText().toString();
//                String place = etPlace.getText().toString();
//                String date = etDate.getText().toString();
//                String pay = etPay.getText().toString();
//                String content = etContent.getText().toString();
//
//                if (etTitle != null && etPlace != null && etDate != null && etPay != null && etContent != null) {
//                    if (!title.isEmpty() && !place.isEmpty() && !date.isEmpty() && !content.isEmpty()) {
//                        showProgress();
//
//                        SuccessHandler successHandler = new SuccessHandler() {
//                            @Override
//                            public void onSuccess(String uri) {
//                                uploadArticle(currentUser.getUid(), title, date, place, pay, uri);
//                            }
//                        };
//
//                        ErrorHandler errorHandler = new ErrorHandler() {
//                            @Override
//                            public void onError() {
//                                hideProgress();
//                                Toast.makeText(FindWritingActivity.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                            }
//                        };
//
//                        if (selectedUri != null) {
//                            uploadPhoto(selectedUri, successHandler, errorHandler);
//                        } else {
//                            successHandler.onSuccess("");
//                        }
//
//                        // 데이터 쓰기 완료 후 액티비티 종료
//                        Toast.makeText(FindWritingActivity.this, "데이터가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                } else {
//                    Toast.makeText(FindWritingActivity.this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String place = etPlace.getText().toString();
                String date = etDate.getText().toString();
                String pay = etPay.getText().toString();
                String content = etContent.getText().toString();
                ImageView photoImageView = findViewById(R.id.iv_find_img);
                String img = selectedUri != null ? selectedUri.toString() : "";
                photoImageView.setImageURI(selectedUri);


                if (etTitle != null && etPlace != null && etDate != null && etPay != null && etContent != null) {
                    if (!title.isEmpty() && !place.isEmpty() && !date.isEmpty() && !content.isEmpty()) {
                        showProgress();
                        // 새로운 FindItem 객체 생성
                        FindItem findItem = new FindItem();
                        findItem.setTitle(title);
                        findItem.setPlace(place);
                        findItem.setDate(date);
                        findItem.setPay(pay);
                        findItem.setContent(content);
                        findItem.setImg(img);

                        // 데이터베이스에 FindItem 객체 추가
                        String findItemId = databaseReference.push().getKey();
                        databaseReference.child(findItemId).setValue(findItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // 데이터 쓰기 성공
                                    uploadArticle(currentUser.getUid(), title, date, place, pay, img);
                                    Toast.makeText(FindWritingActivity.this, "데이터가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    // 데이터 쓰기 실패
                                    Exception exception = task.getException();
                                    if (exception != null) {
                                        Log.e("Firebase", "Error writing data: " + exception.getMessage());
                                    }
                                    Toast.makeText(FindWritingActivity.this, "데이터 저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        // 작성 완료 후 액티비티 종료
                        finish();
                    }
                } else {
                    // EditText가 null인 경우에 대한 처리 추가
                    Toast.makeText(FindWritingActivity.this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ActivityResultLauncher<Intent> contentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            ImageView photoImageView = findViewById(R.id.iv_find_img);
                            photoImageView.setImageURI(uri);
                            selectedUri = uri;
                        } else {
                            Toast.makeText(FindWritingActivity.this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

    private void startContentProvider() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        contentLauncher.launch(Intent.createChooser(intent, "Select Image"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1010) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startContentProvider();
            } else {
                Toast.makeText(FindWritingActivity.this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == 2020) {
            Uri uri = data.getData();
            if (uri != null) {
                ImageView photoImageView = findViewById(R.id.iv_find_img);
                photoImageView.setImageURI(uri);
                selectedUri = uri;
            } else {
                Toast.makeText(FindWritingActivity.this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(FindWritingActivity.this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
//    });
//}

    private void uploadPhoto(Uri uri, final SuccessHandler successHandler, final ErrorHandler errorHandler) {
        String fileName = System.currentTimeMillis() + ".png";
        StorageReference storageRef = storage.getReference().child("article/photo").child(fileName);
        UploadTask uploadTask = storageRef.putFile(uri);

        uploadTask.addOnCompleteListener(FindWritingActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        successHandler.onSuccess(uri.toString());
                    }).addOnFailureListener(exception -> {
                        errorHandler.onError();
                    });
                } else {
                    errorHandler.onError();
                }
            }
        });
    }

    private void uploadArticle(String userId, String title, String date, String place, String pay, String imageUrl) {
        String articleKey = findDB.push().getKey(); // 게시글의 고유 키 생성

        // 현재 로그인된 사용자의 UID를 게시글 정보에 추가하여 저장
        ArticleModel model = new ArticleModel(userId, title, System.currentTimeMillis(), place, pay, imageUrl);
        model.setUserId(userId); // 사용자의 UID 설정

        //findDB.push().setValue(model);

        // 게시글 정보를 게시글 키 아래에 저장
        findDB.child(articleKey).setValue(model);

        hideProgress();
        Toast.makeText(FindWritingActivity.this, "아이템이 등록되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showProgress() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }
}

