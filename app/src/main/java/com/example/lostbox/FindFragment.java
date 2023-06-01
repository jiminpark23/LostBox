package com.example.lostbox;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FindFragment extends Fragment { //FindFragment<findItems> extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FindItem> findItemArrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

   }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.find_recycle);

        recyclerView = view.findViewById(R.id.find_recycle);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        findItemArrayList = new ArrayList<>();  // findItem 객체를 담을 어레이 리스트 (어댑터 쪽으로 날림)


        Button btn_findWrite = (Button) view.findViewById(R.id.find_write);
        btn_findWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindWritingActivity.class);
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance();  // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("findItems"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                findItemArrayList.clear();  //기존 배열 리스트가 존재하지 않도록 초기화
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {  // 반복문으로 데이터 List를 추출해냄
                    FindItem findItem = childSnapshot.getValue(FindItem.class);  // 만들어뒀던 FindItem 객체에 데이터를 담는다
                    //findItems findItems = snapshot.getValue(findItems);     // 만들어뒀던 findItem 객체에 데이터를 담는다
                    findItemArrayList.add(findItem);   // 담을 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비

                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // DB를 가져오던 중 에러가 발생할 시
                Log.e("MainActivity", error.getMessage());    // 에러문 출력 )
            }
        });

        adapter = new FindAdapter(findItemArrayList, requireContext());
        recyclerView.setAdapter(adapter);   // 리사이클러뷰에 어댑터 연결

        return view;
    }


}

    }


    private void getData(){
        List<String> listTitle = Arrays.asList("테스트 1", "테스트 2", "테스트 3", "테스트 4", "테스트 5");
        List<String> listPlace = Arrays.asList("단국대 1", "단국대 2", "단국대 3", "단국대 4", "단국대 5");
        List<String> listDate = Arrays.asList("2020-01-01", "2021-01-01", "2022-01-01", "2023-01-01","2024-01-01");
        List<String> listContent = Arrays.asList(
                "자전거1를 잃어버렸습니다.",
                "자전거2를 잃어버렸습니다.",
                "자전거3를 잃어버렸습니다.",
                "자전거4를 잃어버렸습니다.",
                "자전거5를 잃어버렸습니다."
        );
        List<Integer> listImg = Arrays.asList(R.drawable.bicycle1,
                R.drawable.bicycle2,
                R.drawable.bicycle3,
                R.drawable.bicycle4,
                R.drawable.bicycle5
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            FindItem data = new FindItem();
            data.setTitle(listTitle.get(i));
            data.setPlace(listPlace.get(i));
            data.setDate(listDate.get(i));
            data.setContent(listContent.get(i));
            data.setImg(listImg.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();

    }
}

//public class FindFragment extends Fragment {
//
//
//    private RecyclerView recyclerView;   //리사이클러뷰 생성
//    private FindAdapter adapter;   //어댑터 생성
//
//   public FindFragment(){
//
//   }
//
//
//ㅇ//    @Override
//ㅇ//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//ㅇ//       return inflater.inflate(R.layout.fragment_find, container, false);
//ㅇ//    }
//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_find, container, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.find_recycle);
//        init();
//        getData();
//        Button btn_findwrite = (Button) view.findViewById(R.id.find_write);
//        btn_findwrite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), FindWrittingActivity.class);
//                startActivity(intent);
//            }
//        });
//        return view;
//    }
//
//    private void init(){
//
//       if(recyclerView != null){
//           RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//ㅇ//           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//           recyclerView.setLayoutManager(linearLayoutManager);
//
//           adapter = new FindAdapter();
//           recyclerView.setAdapter(adapter);
//       }
//
//    }
//ㅇ//    private void init() {
//ㅇ//        View view = getView(); // View 객체 가져오기
//ㅇ//        recyclerView = view.findViewById(R.id.find_recycle); // recyclerView 초기화
//ㅇ//
//ㅇ//        if(recyclerView != null){
//ㅇ//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//ㅇ//            recyclerView.setLayoutManager(linearLayoutManager);
//ㅇ//
//ㅇ//            adapter = new FindAdapter();
//ㅇ//            recyclerView.setAdapter(adapter);
//ㅇ//        }
//ㅇ//    }
//
//    private void getData(){
//        List<String> listTitle = Arrays.asList("테스트 1", " 테스트 2", "테스트 3", "테스트 4", "테스트 5");
//        List<String> listPlace = Arrays.asList("단국대 1", "단국대 2", "단국대 3", "단국대 4", "단국대 5");
//        List<String> listDate = Arrays.asList("2020-01-01", "2021-01-01", "2022-01-01", "2023-01-01","2024-01-01");
//        List<String> listContent = Arrays.asList(
//                "자전거1를 잃어버렸습니다.",
//                "자전거2를 잃어버렸습니다.",
//                "자전거3를 잃어버렸습니다.",
//                "자전거4를 잃어버렸습니다.",
//                "자전거5를 잃어버렸습니다."
//        );
//        List<Integer> listImg = Arrays.asList(R.drawable.bicycle1,
//                R.drawable.bicycle2,
//                R.drawable.bicycle3,
//                R.drawable.bicycle4,
//                R.drawable.bicycle5
//        );
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            FindItem data = new FindItem();
//            data.setTitle(listTitle.get(i));
//            data.setPlace(listPlace.get(i));
//            data.setDate(listDate.get(i));
//            data.setContent(listContent.get(i));
//            //data.setImg(listImg.get(i));
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//
//    }
//}
