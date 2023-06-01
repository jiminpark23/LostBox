package com.example.lostbox;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FindFragment extends Fragment {


    private RecyclerView recyclerView;   //리사이클러뷰 생성
    private FindAdapter adapter;   //어댑터 생성

   public FindFragment(){

   }


//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//       return inflater.inflate(R.layout.fragment_find, container, false);
//    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.find_recycle);
        init();
        getData();
        Button btn_findwrite = (Button) view.findViewById(R.id.find_write);
        btn_findwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindWrittingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void init(){

       if(recyclerView != null){
           RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
           recyclerView.setLayoutManager(linearLayoutManager);

           adapter = new FindAdapter();
           recyclerView.setAdapter(adapter);
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
